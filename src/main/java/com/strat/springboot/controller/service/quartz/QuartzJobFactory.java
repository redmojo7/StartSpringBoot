package com.strat.springboot.controller.service.quartz;

import org.apache.commons.lang3.time.DateFormatUtils;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

import java.util.Date;

/**
 * 任务调度工厂类
 * @author Joyce.Luo
 * @date 2015-3-31 下午03:38:35
 * @version V3.0
 * @since Tomcat6.0,Jdk1.6
 * @copyright Copyright (c) 2015
 */
public class QuartzJobFactory implements Job {
    private static final Logger logger = LogManager.getLogger(QuartzJobFactory.class.getName());

    private String datePattern = "yyyy-MM-dd hh:mm:ss a";

    @Override
    public void execute(JobExecutionContext context) throws JobExecutionException {
        QuartzJob scheduleJob = (QuartzJob) context.getMergedJobDataMap().get("backup_job");
        logger.info("定时任务开始执行，任务名称[" + scheduleJob.getJobName() + "]");
        Date previousFireTime = context.getPreviousFireTime();
        if (null != previousFireTime) {
            logger.info("定时任务上次调度时间：" + DateFormatUtils.format(previousFireTime,datePattern));
        }
        logger.info("定时任务下次调度时间：" + DateFormatUtils.format(context.getNextFireTime(),datePattern));
        // 执行业务逻辑
    }
}

