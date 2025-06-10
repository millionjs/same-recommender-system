package com.same.microservice.engine.platform.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BeanConfigGamma {

    @Value("${offline-engine-web}")
    private String active;

}
