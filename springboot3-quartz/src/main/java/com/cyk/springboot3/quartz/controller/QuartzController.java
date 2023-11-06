package com.cyk.springboot3.quartz.controller;

import com.cyk.springboot3.quartz.entity.JobInfo;
import com.cyk.springboot3.quartz.service.JobService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * @author cyk
 * @date 2023/11/6 16:22
 */
@RestController
@RequestMapping("/quartz/job")
public class QuartzController {

    private  final JobService jobService;
    public QuartzController(JobService jobService) {
        this.jobService = jobService;
    }

    @GetMapping("/test")
    public void test(){
        JobInfo jobInfo = new JobInfo();
        jobInfo.setJobName("test-job");
        jobInfo.setJobGroup("test");
        jobInfo.setTimeZoneId("Asia/Shanghai");  //时区指定上海
        jobInfo.setCron("0/5 * * * * ? ");  //每5秒执行一次
        Map<String, Object> params = new HashMap<>();
        //添加需要调用的接口信息
        String callUrl = "http://127.0.0.1:8080/quartz/job/test/run";
        params.put("callUrl", callUrl);
        jobInfo.setJsonParams(params);
        jobService.save(jobInfo);
    }


    @GetMapping("/test/run")
    public void runTest(){
        System.out.println(new Date());
    }

    @GetMapping("/test/delete")
    public void deleteTest(){
        jobService.remove("test-job","test");
        System.out.println("任务已删除");
    }

}
