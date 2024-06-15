package com.query.response;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
public class QueryBuilderResponse {

    private String query;

    public QueryBuilderResponse(String query) {
        this.query = query;
    }
}
