package com.strat.springboot.controller;

import com.strat.springboot.controller.config.LoadLog4J;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.impl.StdSchedulerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.IOException;

@SpringBootApplication
public class DemoApplication {

	public static void main(String[] args) {

		SpringApplication.run(DemoApplication.class, args);

		// Grab the Scheduler instance from the Factory
		Scheduler scheduler = null;
		try {
			scheduler = StdSchedulerFactory.getDefaultScheduler();
			// and start it off 启动一个调度对象
			scheduler.start();
		} catch (SchedulerException e) {
			e.printStackTrace();
		}
		// load log4j properties
		try {
			LoadLog4J.load();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}
}
