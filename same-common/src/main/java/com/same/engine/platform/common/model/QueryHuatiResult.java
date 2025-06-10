package com.same.engine.platform.common.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class QueryHuatiResult {

    private String main_topic;
    private String huati_id;
    private Float rank;
    private Double huati_score;
    private Integer main_impression;
    private Integer main_click;
    private Float main_ctr;
    private Integer publish_impression_1h;
    private Integer publish_comment_1h;
    private Long publish_comment_time_1h;
    private Integer publish_impression_12h;
    private Integer publish_comment_12h;
    private Long publish_comment_time_12h;
    private Integer publish_impression_24h;
    private Integer publish_comment_24h;

    // 通过额外查询补齐
    private Integer total_huati_12_hour_search_count;
    private Integer total_huati_1_hour_search_count;

    // 给每个话题挂上 笔记信息。
    public List<QueryNoteResult> queryNoteResult = new ArrayList<>();
}
