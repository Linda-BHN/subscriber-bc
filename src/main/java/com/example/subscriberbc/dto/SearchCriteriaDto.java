package com.example.subscriberbc.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SearchCriteriaDto {
    private String key;//field name
    private String operation;//operation
    private Object value;//field value

}
