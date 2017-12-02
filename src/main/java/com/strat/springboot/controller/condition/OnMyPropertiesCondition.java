package com.strat.springboot.controller.condition;

import com.strat.springboot.controller.annotation.ConditionalOnMyProperties;
import org.springframework.boot.autoconfigure.condition.ConditionOutcome;
import org.springframework.boot.autoconfigure.condition.SpringBootCondition;
import org.springframework.context.annotation.ConditionContext;
import org.springframework.core.type.AnnotatedTypeMetadata;

/**
 * OnMyPropertiesCondition
 *
 * @author : Donald Cai
 * @date :   2017/12/2.
 */
public class OnMyPropertiesCondition extends SpringBootCondition {
    @Override
    public ConditionOutcome getMatchOutcome(ConditionContext context, AnnotatedTypeMetadata metadata) {
        Object propertiesName = metadata.getAllAnnotationAttributes(ConditionalOnMyProperties.class.getName()).get("name");
        if (propertiesName != null) {
            String value = context.getEnvironment().getProperty(propertiesName.toString());
            if (value != null) {
                return new ConditionOutcome(true, "get properties");
            }
        }
        return new ConditionOutcome(false, "none get properties");
    }
}
