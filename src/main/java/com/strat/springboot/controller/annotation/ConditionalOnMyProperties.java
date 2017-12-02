package com.strat.springboot.controller.annotation;

import com.strat.springboot.controller.condition.OnMyPropertiesCondition;
import org.springframework.context.annotation.Conditional;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * ConditionalOnMyProperties
 *
 * @author : Donald Cai
 * @date :   2017/12/2.
 */
@Target({ElementType.TYPE, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Conditional(OnMyPropertiesCondition.class)
public @interface ConditionalOnMyProperties {
    String name();
}
