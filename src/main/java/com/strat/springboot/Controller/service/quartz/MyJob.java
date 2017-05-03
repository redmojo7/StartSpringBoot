package com.strat.springboot.Controller.service.quartz;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.quartz.Job;
import org.quartz.JobDetail;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

/**
 * MyJob
 *
 * @author : Donald Cai
 * @date :   2017/4/22.
 */
public class MyJob  implements Job {

    SimpleDateFormat DateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    Date d = new Date();
    String returnstr = DateFormat.format(d);

    public void execute(JobExecutionContext context) throws JobExecutionException {
        // TODO Auto-generated method stub
        JobDetail jobDetail = context.getJobDetail();
        System.out.println(returnstr+"  ★★★★★★★★★★★ "
                + context.getJobDetail().getKey());
    }
}
