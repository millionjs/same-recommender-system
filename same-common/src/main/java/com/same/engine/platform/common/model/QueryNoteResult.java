package com.same.engine.platform.common.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class QueryNoteResult {

    private Integer row_num;
    private String main_note_id;
    private String main_topic;
    private String huati_id;

    private Integer publish_like_12h;
    private Integer publish_comment_12h;
    private Long publish_comment_time_12h;
    private Integer main_impression;
    private Integer main_click;
    private Float main_ctr;
    private Integer publish_impression_24h;
    private Integer publish_comment_24h;
    private Integer publish_like_1h;
    private Integer publish_comment_1h;
    private Long publish_comment_time_1h;
    private Double note_score;


}
