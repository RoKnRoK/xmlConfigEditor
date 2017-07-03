package com.rok.xml.rest;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.ConditionContext;
import org.springframework.context.annotation.ConfigurationCondition;
import org.springframework.context.annotation.Import;
import org.springframework.core.type.AnnotatedTypeMetadata;

import java.io.IOException;

/**
 * Created by roman.kulikov on 6/29/2017.
 * All rights reserved =D
 */
@Import(PropertyPlaceholderConfig.class)
public class ConditionalSpring implements ConfigurationCondition {

    private @Value("${xml.editor.bean.type}") String beanType;
    @Override
    public ConfigurationPhase getConfigurationPhase() {
        return ConfigurationPhase.PARSE_CONFIGURATION;
    }

    @Override
    public boolean matches(ConditionContext context, AnnotatedTypeMetadata metadata) {
        String property = context.getEnvironment().getProperty("xml.editor.bean.type");
        System.out.println("ENV: " + context.getEnvironment());
        System.out.println("FIELD: " + beanType);
        System.out.println("COSP: " + property);
        if ("spring".equals(property)) {
            return true;
        } else {
            return false;
        }
    }
}
