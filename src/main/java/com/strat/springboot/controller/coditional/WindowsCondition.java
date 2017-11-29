package com.strat.springboot.controller.coditional;

import org.springframework.context.annotation.Condition;
import org.springframework.context.annotation.ConditionContext;
import org.springframework.core.type.AnnotatedTypeMetadata;

/**
 * @author : Donald
 * @date : 2017/11/29 14:38.
 * @description :
 */
public class WindowsCondition implements Condition {
    
    @Override
    public boolean matches(ConditionContext context, AnnotatedTypeMetadata metadata) {
        return context.getEnvironment().getRequiredProperty("os.name").contains("Windows");
    }
}
