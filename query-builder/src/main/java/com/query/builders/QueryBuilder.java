package com.query.builders;

import com.query.request.QueryMaster;
import com.query.response.QueryBuilderResponse;

public interface QueryBuilder {
    QueryBuilderResponse build(QueryMaster queryMaster);
}
