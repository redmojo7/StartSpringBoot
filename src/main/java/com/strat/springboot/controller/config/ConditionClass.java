package com.strat.springboot.controller.config;

import com.strat.springboot.controller.annotation.ConditionalOnMyProperties;
import com.strat.springboot.controller.domain.Dog;
import com.strat.springboot.controller.domain.User;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * ConditionClass
 *
 * @author : Donald Cai
 * @date :   2017/12/2.
 */
@Configuration
@ConditionalOnMyProperties(name = "message")
public class ConditionClass {
    @Bean
    public Dog dog() {
        return new Dog();
    }
}
