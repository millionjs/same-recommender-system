package com.same.engine.platform.common.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;

@Component
@Slf4j
public class EnvConfig {
    public static volatile ENV envType = ENV.DEV;

    @Resource
    Environment env;

    @PostConstruct
    private void init() {

        String profile = env.getProperty("env");
        String xhsEnv = env.getProperty("XHS_ENV");
        String[] activeProfiles = env.getActiveProfiles();

        log.info("[enginex-offline-platform] >>>>>>> profile:{}", profile);
        log.info("[enginex-offline-platform] >>>>>>> xhsEnv:{}", xhsEnv);
        log.info("[enginex-offline-platform] >>>>>>> activeProfiles:{}", activeProfiles[0]);

        if ("dev".equalsIgnoreCase(xhsEnv)) {
            envType = ENV.DEV;
        } else if ("shadow".equalsIgnoreCase(xhsEnv)) {
            envType = ENV.SHADOW;
        } else if ("prod".equalsIgnoreCase(xhsEnv) || "pro".equalsIgnoreCase(xhsEnv)) {
            envType = ENV.PROD;
        } else if ("beta".equalsIgnoreCase(xhsEnv) || "staging".equalsIgnoreCase(xhsEnv)) {
            envType = ENV.BETA;
        } else if ("sit".equalsIgnoreCase(xhsEnv)) {
            envType = ENV.SIT;
        }

        log.info("[enginex-offline-platform] >>>>>>> EnvType:{}", envType);
    }

    public static boolean isProd() {
        return envType == ENV.PROD;
    }

    public static boolean isBeta() {
        return envType == ENV.SHADOW || envType == ENV.BETA;
    }

    public static boolean isDev() {
        return envType == ENV.DEV;
    }


    public static boolean isSit() {
        return envType == ENV.SIT;
    }

    public enum ENV {
        DEV,
        SHADOW,
        PROD,
        BETA,
        SIT
    }
}
