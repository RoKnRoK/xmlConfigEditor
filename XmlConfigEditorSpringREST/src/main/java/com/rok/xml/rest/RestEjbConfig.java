package com.rok.xml.rest;

import org.springframework.context.annotation.*;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

/**
 * Created by roman.kulikov on 6/6/2017.
 * All rights reserved =D
 */
@Configuration
@EnableWebMvc
@ComponentScan(basePackages = {"com.rok.xml"})
@Import(PropertyPlaceholderConfig.class)
@Conditional(ConditionalEjb.class)
@ImportResource("classpath*:ejb-config.xml")
public class RestEjbConfig {

}