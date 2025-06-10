package com.same.microservice.engine.platform.controller.common;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.same.engine.platform.biz.common.biz.CommonApiService;
import com.same.engine.platform.common.model.Result;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@RestController
@RequestMapping("/same/engine/platform/common")
@Slf4j
public class CommonApi {

    @Resource
    private CommonApiService commonApiService;

    @GetMapping("/test")
    public Result getTest() {
        JSONObject result = new JSONObject();
        result.put("result", "success");
        return Result.success(result);
    }

    @GetMapping("/get_module_test")
    public Result getTestMysql() {
        return Result.success(commonApiService.getModuleTest());
    }

}
