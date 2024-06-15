package com.query.beans;

public enum JoinType {
    INNER ("INNER JOIN"),
    LEFT ("LEFT JOIN"),
    RIGHT ("RIGHT JOIN");

    private final String joinType;

    JoinType(String joinType) {
        this.joinType = joinType;
    }

    @Override
    public String toString() {
        return joinType;
    }
}
