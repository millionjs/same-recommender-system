package com.same.microservice.engine.platform;

import com.alibaba.fastjson.parser.ParserConfig;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.data.mongo.MongoDataAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.DataSourceTransactionManagerAutoConfiguration;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.scheduling.annotation.EnableScheduling;

@Slf4j
@EnableScheduling
@SpringBootApplication(
        scanBasePackages = {
                "com.same.microservice.engine.platform",
                "com.same.engine.platform.biz.common"
        },
        exclude = {MongoDataAutoConfiguration.class, DataSourceAutoConfiguration.class, DataSourceTransactionManagerAutoConfiguration.class}
)
public class EnginePlatformServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(EnginePlatformServiceApplication.class, args);
        log.info("EnginePlatformServiceApplication start successfully...");
    }
}

