package com.same.engine.platform.common.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ExcavationResult {

    private String topic_id;

    private String topic_name;

    private Integer topic_rk;

    private Integer topic_score_rk;


    private String excavate_time;

    private String seed_note_id;

    public String note_list;

    public String extra;

    private Long collect_time;



}
