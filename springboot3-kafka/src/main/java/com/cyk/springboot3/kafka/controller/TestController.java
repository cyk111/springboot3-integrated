package com.cyk.springboot3.kafka.controller;

import com.cyk.springboot3.kafka.component.KafkaSender;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author cyk
 * @date 2023/10/31 19:39
 */

@RestController
@Slf4j
public class TestController {

    @Resource
    private KafkaSender kafkaSender;

    @GetMapping("/sendMessage/{msg}")
    public void sendMessage(@PathVariable("msg") String msg){
        kafkaSender.send(msg);
    }

    // ./kafka-topics.sh --zookeeper=127.0.0.1.3:2181  --list
}
