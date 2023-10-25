package com.cyk.springboot3.integrated.jpa.postgresql.exception;

import com.alibaba.fastjson.JSON;
import com.cyk.springboot3.integrated.jpa.postgresql.common.RequestContextHolder;
import com.cyk.springboot3.integrated.jpa.postgresql.common.ResponseState;
import com.cyk.springboot3.integrated.jpa.postgresql.common.Result;
import jakarta.validation.ConstraintViolationException;
import jakarta.validation.ValidationException;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.HttpMediaTypeNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @author cyk
 * @date 2023/10/24 15:23
 */
@Slf4j
@ControllerAdvice
public class GlobalControllerExceptionHandler {

    @Value("${debug}")
    private boolean debug;


    @ExceptionHandler
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    Result handleException(MethodArgumentNotValidException ex) {
        log.debug("Validate Exception.", ex);
        List<String> messageList = Stream.concat(
                        ex.getBindingResult()
                                .getFieldErrors()
                                .stream()
                                .map(i -> i.getField() + ": " + i.getDefaultMessage()),
                        ex.getBindingResult()
                                .getGlobalErrors()
                                .stream()
                                .map(i -> i.getObjectName() + ": " + i.getDefaultMessage()))
                .sorted(String::compareTo)
                .collect(Collectors.toList());

        String message = StringUtils.join(messageList, ", ");
        return Result.failure(ResponseState.RESPONSE_SYSTEM_ERROR.code, message);
    }

    @ExceptionHandler
    @ResponseStatus(HttpStatus.UNSUPPORTED_MEDIA_TYPE)
    @ResponseBody
    Result getErrorMessage(HttpMediaTypeNotSupportedException ex) {
        log.debug("Unsupported Media Type: context={}", RequestContextHolder.context(), ex);
        StringBuffer bf = new StringBuffer();
        bf.append("Unsupported content type: ").append(ex.getContentType());
        bf.append("Supported content types: ").append(MediaType.toString(ex.getSupportedMediaTypes()));
        return Result.failure(ResponseState.RESPONSE_SYSTEM_ERROR.code, debug==true?bf.toString():null);
    }


    @ExceptionHandler
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ResponseBody
    Result handleException(RuntimeException ex) {
        log.error("Internal Server Error: context:{}", RequestContextHolder.context(), ex);
        Throwable mostSpecificCause = ex.getCause();
        StringBuffer bf = new StringBuffer();
        if (mostSpecificCause != null) {
            bf.append(mostSpecificCause.getClass().getName());
            bf.append(mostSpecificCause.getMessage());
            return Result.failure(ResponseState.RESPONSE_SYSTEM_ERROR.code, debug == true ? bf.toString() : null);
        }
        return Result.failure(ResponseState.RESPONSE_SYSTEM_ERROR.code, debug == true ? ex.getMessage() : null);
    }


    @ExceptionHandler(value = {HttpMessageNotReadableException.class})
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ResponseBody
    Result handleException(HttpMessageNotReadableException ex) {
        log.error("HttpMessageNotReadableException Error: context:{}", RequestContextHolder.context(), ex);
        return Result.failure(ResponseState.RESPONSE_SYSTEM_ERROR.code, "网络错误");
    }

    @ExceptionHandler(value = {ValidationException.class})
    @ResponseStatus(value = HttpStatus.OK)
    @ResponseBody
    public Result handleException(ValidationException e) {
        String message;
        if (e instanceof ConstraintViolationException) {
            ConstraintViolationException ex = (ConstraintViolationException) e;
            List<String> messageList = ex.getConstraintViolations().stream()
                    .sorted(Comparator.comparing(o -> o.getPropertyPath().toString()))
                    .map(constraintViolation -> constraintViolation.getPropertyPath() + ": " + constraintViolation.getMessage())
                    .sorted(String::compareTo)
                    .collect(Collectors.toList());
            message = StringUtils.join(messageList, ", ");
        } else {
            message = e.getMessage();
        }

        Result<Object> result = Result.failure(HttpStatus.INTERNAL_SERVER_ERROR.value(), message);
        if (log.isDebugEnabled()) {
            log.debug("handle ValidationException, return result:" + JSON.toJSONString(result), e);
        }
        return result;
    }

}
