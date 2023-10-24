package com.cyk.springboot3.integrated.jpa.mysql.common;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * @author cyk
 * @date 2023/10/24 11:14
 */
@Getter
@AllArgsConstructor
public enum ResponseStatus {


    SUCCESS("200", "success"),
    FAIL("500", "failed"),
    HTTP_STATUS_200("200", "ok"),
    HTTP_STATUS_400("400", "request error"),
    HTTP_STATUS_401("401", "no authentication"),
    HTTP_STATUS_403("403", "no authorities"),
    HTTP_STATUS_500("500", "server error");

    /**
     * response code
     */
    private final String responseCode;

    /**
     * description.
     */
    private final String description;


    public static final List<ResponseStatus> HTTP_STATUS_ALL = Collections.unmodifiableList(
            Arrays.asList(HTTP_STATUS_200, HTTP_STATUS_400, HTTP_STATUS_401, HTTP_STATUS_403, HTTP_STATUS_500
            ));


}
