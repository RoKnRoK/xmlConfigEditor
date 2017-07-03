package com.rok.xml.rest;

import org.springframework.context.annotation.*;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

/**
 * Created by roman.kulikov on 6/29/2017.
 * All rights reserved =D
 */
@Configuration
@EnableWebMvc
@ComponentScan(basePackages = {"com.rok.xml"})
@Conditional(ConditionalSpring.class)
@ImportResource("classpath*:spring-config.xml")
public class RestSpringConfig {
}
