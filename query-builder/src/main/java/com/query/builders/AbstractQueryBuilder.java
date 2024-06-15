package com.query.builders;


public abstract class AbstractQueryBuilder implements QueryBuilder {

    protected StringBuilder finalQuery;

    public AbstractQueryBuilder() {
        this.finalQuery = new StringBuilder();
    }
}
