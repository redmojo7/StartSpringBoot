package com.strat.springboot.Controller.service;

import org.quartz.Job;
import org.quartz.JobBuilder;
import org.quartz.JobDetail;
import org.quartz.JobKey;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.SchedulerFactory;
import org.quartz.Trigger;
import org.quartz.TriggerBuilder;
import org.quartz.TriggerKey;
import org.quartz.impl.StdSchedulerFactory;
import org.springframework.stereotype.Service;

import java.text.ParseException;

import static org.quartz.CronScheduleBuilder.cronSchedule;


/**
 * QuartzService
 *
 * @author : Donald Cai
 * @date :   2017/4/22.
 */
@Service
public class QuartzService {

    private static SchedulerFactory sf = new StdSchedulerFactory();
    private static String JOB_GROUP_NAME = "group1";
    private static String TRIGGER_GROUP_NAME = "trigger1";



    /** */
    /**
     * 添加一个定时任务，使用默认的任务组名，触发器名，触发器组名
     *
     * @param jobName 任务名
     * @param job     任务
     * @param time    时间设置，参考quartz说明文档
     * @throws SchedulerException
     * @throws ParseException
     */
    public static void addJob(String jobName, Job job, String time)
            throws SchedulerException, ParseException {
        Scheduler sched = sf.getScheduler();


        //任务名，任务组，任务执行类
        JobDetail jobDetail = JobBuilder
                .newJob(job.getClass())
                .withIdentity(jobName, JOB_GROUP_NAME)
                .build();
        //触发器   //触发器名,触发器组 //触发器时间设定
        Trigger trigger = TriggerBuilder
                .newTrigger()
                .withIdentity(jobName, TRIGGER_GROUP_NAME)
                .withSchedule(cronSchedule(time))
                .build();

        sched.scheduleJob(jobDetail, trigger);
        //启动
        if (!sched.isShutdown())
            sched.start();
    }

    /** */
    /**
     * 添加一个定时任务
     *
     * @param jobName          任务名
     * @param jobGroupName     任务组名
     * @param triggerName      触发器名
     * @param triggerGroupName 触发器组名
     * @param job              任务
     * @param time             时间设置，参考quartz说明文档
     * @throws SchedulerException
     * @throws ParseException
     */
    public static void addJob(String jobName, String jobGroupName,
                              String triggerName, String triggerGroupName,
                              Job job, String time)
            throws SchedulerException, ParseException {
        removeJob(jobName);
        Scheduler sched = sf.getScheduler();
        JobDetail jobDetail = JobBuilder
                .newJob(job.getClass())
                .withIdentity(jobName, jobGroupName)
                .build();//任务名，任务组，任务执行类
        //触发器
        Trigger trigger = TriggerBuilder
                .newTrigger()
                .withIdentity(triggerName, triggerGroupName)
                .withSchedule(cronSchedule(time))
                .build();

        sched.scheduleJob(jobDetail, trigger);
        if (!sched.isShutdown())
            sched.start();
    }

    /** */
    /**
     * 修改一个任务的触发时间(使用默认的任务组名，触发器名，触发器组名)
     *
     * @param jobName
     * @param jobName
     * @throws SchedulerException
     * @throws ParseException
     */
    public static void modifyJobTime(String jobName, String time)
            throws SchedulerException, ParseException {
        Scheduler sched = sf.getScheduler();
        removeJob(jobName);
        Trigger trigger = TriggerBuilder
                .newTrigger()
                .withIdentity(jobName, TRIGGER_GROUP_NAME)
                .withSchedule(cronSchedule(time))
                .build();
        sched.rescheduleJob(TriggerKey.triggerKey(jobName, TRIGGER_GROUP_NAME), trigger);
    }

    /** */
    /**
     * 移除一个任务(使用默认的任务组名，触发器名，触发器组名)
     *
     * @param jobName
     * @throws SchedulerException
     */
    public static void removeJob(String jobName)
            throws SchedulerException {

        JobKey jobKey = JobKey.jobKey(jobName,JOB_GROUP_NAME);
        Scheduler sched = sf.getScheduler();
        if (sched.checkExists(jobKey)) {
            TriggerKey triggerKey = TriggerKey.triggerKey(jobName, TRIGGER_GROUP_NAME);
            sched.pauseTrigger(triggerKey);// 停止触发器
            sched.unscheduleJob(triggerKey);// 移除触发器
            sched.deleteJob(jobKey);// 删除任务
        }
    }
}
