package com.strat.springboot.controller.service.quartz;

import java.util.Date;

import org.quartz.JobDetail;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

/**
 * 一个简单的quartz调用job
 * @author 123
 *
 */
public class HelloJob implements Job {

    private static Logger logger = LoggerFactory.getLogger(HelloJob.class);

    public HelloJob() {
    }

    public void execute(JobExecutionContext jobCtx)
            throws JobExecutionException {
        JobDetail jobDetail = jobCtx.getJobDetail();
        jobCtx.getMergedJobDataMap();
        jobCtx.getTrigger().getJobDataMap();
//        logger.info("Hello World! - " + new Date());
        System.out.println(jobCtx.getTrigger().getKey().toString()+ " triggered. time is:" + (new Date()));
    }
}

