package com.query.beans;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Objects;

@Getter
@Setter
@ToString
public class FieldInfo {
    private String name;
    private String alias;
    private String objectAlias;
    private AggregateFunction aggregateFunction;

    public boolean isAggregateField() {
        return Objects.nonNull(this.aggregateFunction);
    }
}
