package com.jsq.forum.configuration;

import org.springframework.context.annotation.Configuration;

import org.springframework.web.servlet.config.annotation.*;



@Configuration
public class WebConfig extends WebMvcConfigurerAdapter{
    @Override
    public void addViewControllers(ViewControllerRegistry viewControllerRegistry) {
        viewControllerRegistry.addViewController("/login").setViewName("login");
    }
}
