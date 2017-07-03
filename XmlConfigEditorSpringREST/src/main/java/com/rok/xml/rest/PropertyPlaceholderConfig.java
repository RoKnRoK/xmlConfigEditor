package com.rok.xml.rest;

import org.springframework.beans.factory.config.PropertyPlaceholderConfigurer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;

/**
 * Created by roman.kulikov on 6/29/2017.
 * All rights reserved =D
 */
@Configuration
public class PropertyPlaceholderConfig {

    @Bean
    public static PropertyPlaceholderConfigurer propertyConfigurer() {
        PropertyPlaceholderConfigurer propertyPlaceholderConfigurer = new PropertyPlaceholderConfigurer();
        ClassPathResource location = new ClassPathResource("module.properties");
        propertyPlaceholderConfigurer.setLocation(location);
        System.out.println("propertyPlaceholderConfigurer path:" + location.getFile().getAbsolutePath());

//         Allow for other PropertyPlaceholderConfigurer instances.
//        propertyPlaceholderConfigurer.setIgnoreUnresolvablePlaceholders(true);
        return propertyPlaceholderConfigurer;
    }
}
