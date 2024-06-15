package com.query.beans;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@Getter
@Setter
@ToString
@JsonIgnoreProperties(ignoreUnknown = true)
public class Condition {
    private FieldInfo fieldInfo;
    private String alias;
    private List<Object> value;
    private ComparisonOperator operator;
    private ValueType valueType;
    private boolean includeNulls;
}
