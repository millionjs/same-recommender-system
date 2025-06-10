package com.same.engine.platform.common.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ExcavationNoteList {

    private String note_id;
    private Integer note_rank;
    private Double note_score;

}

