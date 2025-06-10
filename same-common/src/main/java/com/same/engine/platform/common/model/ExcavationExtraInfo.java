package com.same.engine.platform.common.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ExcavationExtraInfo {

    private String excavate_time;
    private String topic_id;

    private String topic_name;

    private Integer topic_rk;

    private Double topic_score;


    public List<ExcavationNoteList> note_list = new ArrayList<>();

}
