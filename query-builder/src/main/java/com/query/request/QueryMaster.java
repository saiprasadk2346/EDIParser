package com.query.request;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.query.beans.FieldInfo;
import com.query.beans.Filter;
import com.query.beans.JoinCondition;
import com.query.beans.Order;

import java.util.List;
import java.util.Map;

@JsonIgnoreProperties(ignoreUnknown = true)
public class QueryMaster {

    private List<FieldInfo> select;
    private String fromObject;
    private Map<String, JoinCondition> joinInfoMap;
    private Filter where;
    private List<FieldInfo> groupBy;
    private Filter having;
    private Map<String, Order> orderBy;
    private Integer limit;
    private Integer offset;
    // TODO: Datastore

    public List<FieldInfo> getSelect() {
        return select;
    }

    public void setSelect(List<FieldInfo> select) {
        this.select = select;
    }

    public String getFromObject() {
        return fromObject;
    }

    public void setFromObject(String fromObject) {
        this.fromObject = fromObject;
    }

    public Map<String, JoinCondition> getJoinInfoMap() {
        return joinInfoMap;
    }

    public void setJoinInfoMap(Map<String, JoinCondition> joinInfoMap) {
        this.joinInfoMap = joinInfoMap;
    }

    public Filter getWhere() {
        return where;
    }

    public void setWhere(Filter where) {
        this.where = where;
    }

    public List<FieldInfo> getGroupBy() {
        return groupBy;
    }

    public void setGroupBy(List<FieldInfo> groupBy) {
        this.groupBy = groupBy;
    }

    public Filter getHaving() {
        return having;
    }

    public void setHaving(Filter having) {
        this.having = having;
    }

    public Map<String, Order> getOrderBy() {
        return orderBy;
    }

    public void setOrderBy(Map<String, Order> orderBy) {
        this.orderBy = orderBy;
    }

    public Integer getLimit() {
        return limit;
    }

    public void setLimit(Integer limit) {
        this.limit = limit;
    }

    public Integer getOffset() {
        return offset;
    }

    public void setOffset(Integer offset) {
        this.offset = offset;
    }
}
