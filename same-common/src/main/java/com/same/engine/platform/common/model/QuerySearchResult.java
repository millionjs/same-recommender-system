package com.same.engine.platform.common.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class QuerySearchResult {

    private String main_topic;

    private Integer total_huati_12_hour_search_count;

    private Integer total_huati_1_hour_search_count;


}
