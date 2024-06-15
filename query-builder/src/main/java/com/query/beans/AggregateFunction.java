package com.query.beans;

public enum AggregateFunction {
    MAX("MAX"),
    MIN("MIN"),
    AVG("AVG"),
    COUNT("COUNT"),
    COUNT_DISTINCT("COUNT DISTINCT");

    private final String sqlFunction;

    AggregateFunction(String sqlFunction) {
        this.sqlFunction = sqlFunction;
    }

    public String getSqlFunction() {
        return sqlFunction;
    }
}
