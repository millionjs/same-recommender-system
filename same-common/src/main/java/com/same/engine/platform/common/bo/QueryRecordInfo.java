package com.same.engine.platform.common.bo;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class QueryRecordInfo {

    public Integer pageNum;

    public Integer pageSize;

    public String orderType;

    public String orderFieldName;

    // 过滤条件。
    public String searchKeyWord;

    public String type;

    public String jobType;

    public String jobDimension;

    public String jobBizLine;

    public String bizLine;

    public String scene;

    public String jobScene;

    public Integer jobId;

    public String email;

    public String status;


    public Integer id;

    public String configName;

    public String configType;

    public String configKey;

    public String configValue;

    public String configDesc;

    public Integer workFlowResult;

    public String workFlowId;

    public String workflowVersion;

    public String jobName;

}
