package com.rok.xml.rest;

import org.springframework.context.annotation.ConditionContext;
import org.springframework.context.annotation.ConfigurationCondition;
import org.springframework.core.type.AnnotatedTypeMetadata;

/**
 * Created by roman.kulikov on 6/29/2017.
 * All rights reserved =D
 */
public class ConditionalEjb implements ConfigurationCondition {

    @Override
    public ConfigurationPhase getConfigurationPhase() {
        return ConfigurationPhase.PARSE_CONFIGURATION;
    }

    @Override
    public boolean matches(ConditionContext context, AnnotatedTypeMetadata metadata) {
        String property = context.getEnvironment().getProperty("xml.editor.bean.type");
        System.out.println("COEJB: " + property);
        if ("ejb".equals(property)) {
            return true;
        } else {
            return false;
        }
    }
}