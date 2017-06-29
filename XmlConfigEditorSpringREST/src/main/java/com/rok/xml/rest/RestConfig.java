package com.rok.xml.rest;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

/**
 * Created by roman.kulikov on 6/6/2017.
 * All rights reserved =D
 */
@Configuration
@EnableWebMvc
@ComponentScan(basePackages = {"com.rok.xml"})
@ImportResource({"classpath:config.xml"})
public class RestConfig {

}