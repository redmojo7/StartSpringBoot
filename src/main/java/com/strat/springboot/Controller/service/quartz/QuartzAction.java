package com.strat.springboot.Controller.service.quartz;

import com.strat.springboot.Controller.service.QuartzService;
import org.quartz.CronExpression;
import org.quartz.CronTrigger;
import org.quartz.SchedulerException;
import org.quartz.SchedulerFactory;
import org.quartz.TriggerBuilder;
import org.quartz.TriggerKey;
import org.quartz.impl.StdScheduler;
import org.quartz.impl.StdSchedulerFactory;
import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.PostConstruct;
import javax.inject.Inject;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import static org.quartz.CronScheduleBuilder.cronSchedule;

/**
 * QuartzAction
 *
 * @author : Donald Cai
 * @date :   2017/4/22.
 */
@RestController
@RequestMapping("/api")
public class QuartzAction {

    @Inject
    private QuartzService quartzService;

    @Inject
    private QuartzManager quartzManager;

    private QuartzJob quartzJob = new QuartzJob();

    private static final Integer jobId = 001;
    private static final String jobName = "job_001";
    private static final String jobGroup = "group_001";
    private static final String time = "0/2 * * * * ?";
    private static final String time2 = "0/1 * * * * ?";

    @PostConstruct
    public void init(){
        try {
            SchedulerFactory sf = new StdSchedulerFactory();
            StdScheduler scheduler = (StdScheduler) sf.getScheduler();
            quartzManager.setScheduler(scheduler);
        } catch (SchedulerException e) {
            e.printStackTrace();
        }
        // init job
        quartzJob.setJobId(jobId);
        quartzJob.setJobGroup(jobGroup);
        quartzJob.setJobName(jobName);
        quartzJob.setCronExpression(time);//每2秒钟执行一次
//        quartzJob.setJobStatus();
    }

    @RequestMapping(value = "/quartz/test",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public void testQuartz(){
        // TODO Auto-generated method stub
        SimpleDateFormat DateFormat = new SimpleDateFormat("yyyyMMddHHmmss");
        Date d = new Date();
        String returnstr = DateFormat.format(d);

        MyJob job = new MyJob();
        String job_name ="11";
        try {
            System.out.println(returnstr+ "【系统启动】");
            quartzService.addJob(job_name,job,"0/2 * * * * ?"); //每2秒钟执行一次

            Thread.sleep(10000);
            System.out.println("【修改时间】");
            quartzService.modifyJobTime(job_name,"0/1 * * * * ?");
            Thread.sleep(20000);
            System.out.println("【移除定时】");
            quartzService.removeJob(job_name);
            Thread.sleep(10000);

            System.out.println("【添加定时任务】");
//            quartzService.addJob(job_name,job,"0/5 * * * * ?");

        }  catch (Exception e) {
            e.printStackTrace();
        }
    }

    @RequestMapping(value="/rereshSheduleTrigger",method=RequestMethod.GET)
    public void retScheduleJob() throws SchedulerException, ParseException {
        this.quartzService.test();
    }

    @RequestMapping(value = "/quartz/crateJob",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> crateJob() throws SchedulerException {

        quartzManager.initJob(quartzJob,MyJob.class);
        return new ResponseEntity(HttpStatus.OK);
    }

    @RequestMapping(value = "/quartz/updateJob",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> updateJob() {
        quartzJob.setCronExpression(time2);
        quartzManager.updateQuartzJob(quartzJob, TriggerKey.triggerKey(jobName,jobGroup),
                TriggerBuilder
                        .newTrigger()
                        .withSchedule(cronSchedule(time))
                        .build()
        );
        return new ResponseEntity(HttpStatus.OK);
    }

    @RequestMapping(value = "/quartz/deleteJob",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> deleteJob(){

        return new ResponseEntity<Void>(HttpStatus.OK);
    }

    @RequestMapping(value = "/quartz/runJob",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> runJob(){

        return new ResponseEntity<Void>(HttpStatus.OK);
    }
}
