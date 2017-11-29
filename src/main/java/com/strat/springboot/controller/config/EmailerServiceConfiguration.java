package com.strat.springboot.controller.config;

import com.strat.springboot.controller.coditional.LinuxCondition;
import com.strat.springboot.controller.coditional.WindowsCondition;
import com.strat.springboot.controller.service.EmailService;
import com.strat.springboot.controller.service.LinuxEmailService;
import com.strat.springboot.controller.service.WindowsEmailService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Conditional;
import org.springframework.context.annotation.Configuration;

/**
 * @author : Donald
 * @date : 2017/11/29 14:42.
 * @description :
 */
@Configuration
public class EmailerServiceConfiguration {
    
    @Bean(name = "emailerService")
    @Conditional(WindowsCondition.class)
    public EmailService windowsEmailerService(){
        return new WindowsEmailService();
    }
    
    @Bean(name="emailerService")
    @Conditional(LinuxCondition.class)
    public EmailService linuxEmailerService(){
        return new LinuxEmailService();
    }
    
}
