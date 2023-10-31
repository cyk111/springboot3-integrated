package com.cyk.springboot3.kafka.component;

import com.alibaba.fastjson.JSON;
import com.cyk.springboot3.kafka.entity.Message;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * @author cyk
 * @date 2023/10/31 19:36
 */
@Component
@Slf4j
public class KafkaSender {

    private final KafkaTemplate<String, String> kafkaTemplate;


    //构造器方式注入  kafkaTemplate
    public KafkaSender(KafkaTemplate<String, String> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    public void send(String msg) {
        Message message = new Message();

        message.setId(System.currentTimeMillis());
        message.setMsg(msg);
        message.setSendTime(new Date());
        log.info("【++++++++++++++++++ message ：{}】", JSON.toJSONString(message));
        //对 topic =  hello2 的发送消息
        kafkaTemplate.send("test_send_msg",JSON.toJSONString(message));
    }

}
