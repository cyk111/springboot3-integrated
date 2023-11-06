package com.cyk.springboot3.quartz.entity;

import lombok.Data;

import java.util.Date;
import java.util.Map;

/**
 * @author cyk
 * @date 2023/11/6 16:16
 */
@Data
public class JobInfo {
    private String jobName;

    private String jobGroup;

    private Map<String, Object> jsonParams;

    private String cron;

    private String timeZoneId;

    private Date triggerTime;

}
