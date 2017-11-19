package com.mypieceofcode.evfinder.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.ArrayList;
import java.util.List;

@Configuration
public class WebConfig {

    @Bean
    @Autowired
    public FilterRegistrationBean filterToken(TokenFilter filter){
        FilterRegistrationBean registrationBean = new FilterRegistrationBean();
        registrationBean.setName("token");
        registrationBean.setFilter(filter);
        registrationBean.setOrder(1);
        List<String> urls = new ArrayList<>();
        urls.add("/user/*");
        urls.add("/friends");
        registrationBean.setUrlPatterns(urls);
        registrationBean.setOrder(1);
        return registrationBean;
    }
}
