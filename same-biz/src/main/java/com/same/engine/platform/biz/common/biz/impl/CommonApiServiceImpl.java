package com.same.engine.platform.biz.common.biz.impl;

import com.same.engine.platform.biz.common.biz.CommonApiService;
import com.same.engine.platform.common.model.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class CommonApiServiceImpl implements CommonApiService {

    @Override
    public Result getModuleTest() {
        log.info("getModuleTest api，信息为:[{}]", "success");
        return Result.success("Test ping success.");
    }

}
