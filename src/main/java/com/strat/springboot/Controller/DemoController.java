package com.strat.springboot.Controller;


import com.strat.springboot.Controller.service.SingletonDemo;
import com.strat.springboot.Controller.service.SingletonEnum;
import com.strat.springboot.Controller.service.quartz.MyJob;
import org.quartz.JobBuilder;
import org.quartz.JobDetail;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.SchedulerFactory;
import org.quartz.SimpleScheduleBuilder;
import org.quartz.Trigger;
import org.quartz.TriggerBuilder;
import org.quartz.impl.StdSchedulerFactory;
import org.quartz.impl.calendar.AnnualCalendar;
import org.quartz.impl.calendar.MonthlyCalendar;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by Administrator on 2017/4/11.
 * 注意：controller的代码必须和DemoApplication.java位于同级目录或之下
 */
@RestController
@RequestMapping("/api")
public class DemoController {

    @RequestMapping("/demo")
    public String demo() {

        return "欢迎来到Demo! &nbsp;<a><b>www.baidu.com</b></a>";
    }

    @RequestMapping(value = "/singleton",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> singleton() {
        SingletonDemo singletonDemo = SingletonDemo.getDefault();
        singletonDemo.printText("This is a singleton demo");

        SingletonEnum.INSTANCE.fun1();

        return new ResponseEntity("singleton demo",HttpStatus.OK);
    }

    @RequestMapping(value = "/quartz/addTest",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> addQuartz() throws SchedulerException {

        // define the job and tie it to our MyJob class
        JobDetail job = JobBuilder.newJob(MyJob.class)
                .withIdentity("testJob_1","group_1")
                .build();

        // Trigger the job to run now, and then repeat every 40 seconds
        Trigger trigger = TriggerBuilder
                .newTrigger()
                .withIdentity("trigger_1","group_1")
                .withSchedule(SimpleScheduleBuilder.simpleSchedule()
                    .withIntervalInSeconds(2)
                    .withRepeatCount(20)
                    )
                  .build();
        SchedulerFactory sf = new StdSchedulerFactory();
        Scheduler sched = sf.getScheduler();

        // Tell quartz to schedule the job using our trigger
        sched.scheduleJob(job,trigger);

        sched.start();

        return new ResponseEntity("addQuartz ok!",HttpStatus.OK);
    }
}

