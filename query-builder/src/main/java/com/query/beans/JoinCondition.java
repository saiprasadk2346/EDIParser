package com.query.beans;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@JsonIgnoreProperties(ignoreUnknown = true)
public class JoinCondition {
    private String sourceFieldName;
    private String sourceObjectName;
    private String sourceObjectAlias;
    private String targetFieldName;
    private String targetObjectName;
    private String targetObjectAlias;
    private JoinType joinType;
}
