package com.same.engine.platform.common.model;

import com.alibaba.fastjson.JSONObject;

@lombok.Data
public final class Result<T> {

    public static final <T> Result<T> success() {
        return new Result(true, null);
    }

    public static final <T> Result<T> success(T result) {
        return new Result(true, result);
    }

    public static <T> Result<T> success(T result, String message) {
        return new Result<>(true, result, message);
    }

    public static final <T> Result<T> fail(String message) {
        JSONObject result = new JSONObject();
        result.put("result", message);
        return new Result<T>(
                result.toJSONString(), false);
    }

    public static final Result fail(String message, String err) {
        return new Result<String>(false, null, message, err);
    }

    public static final <T> Result fail(JSONObject result) {
        return new Result(false, result);
    }

    private boolean success;

    private T data;

    private String errorMsg;

    private String detailedErr;

    public Result() {

    }

    public Result(boolean success, T result) {
        this.data = result;
        this.success = success;
    }

    public Result(String result, boolean success) {
        this.errorMsg = result;
        this.success = success;
    }

    public Result(boolean success, T data, String errorMsg) {
        this.success = success;
        this.data = data;
        this.errorMsg = errorMsg;
    }

    public Result(boolean success, T data, String errorMsg, String detailedErr) {
        this.success = success;
        this.data = data;
        this.errorMsg = errorMsg;
        this.detailedErr = detailedErr;
    }
}
