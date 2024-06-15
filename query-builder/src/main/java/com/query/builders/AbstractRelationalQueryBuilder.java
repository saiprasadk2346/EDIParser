package com.query.builders;

import com.query.beans.*;
import com.query.request.QueryMaster;
import com.query.response.QueryBuilderResponse;
import com.query.utils.FilterHelper;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.collections4.MapUtils;
import org.apache.commons.lang3.StringUtils;

import java.util.*;
import java.util.stream.Collectors;

import static com.query.utils.QueryBuilderConstants.*;

public abstract class AbstractRelationalQueryBuilder extends AbstractQueryBuilder implements RelationalQueryBuilder {

    protected String baseTableAlias;
    protected JoinType defaultJoinType;

    public AbstractRelationalQueryBuilder(String baseTableAlias) {
        super();
        this.defaultJoinType = JoinType.LEFT;
        this.baseTableAlias = StringUtils.defaultIfBlank(baseTableAlias, "t1");
    }

    public abstract String getDataStoreType();

    @Override
    public QueryBuilderResponse build(QueryMaster queryMaster) {
        buildSelect(queryMaster.getSelect());
        buildFromObject(queryMaster.getFromObject());
        buildJoins(queryMaster.getJoinInfoMap());
        buildWhereClause(queryMaster.getWhere());
        buildGroupBy(queryMaster.getGroupBy());
        buildHaving(queryMaster.getHaving());
        buildOrderBy(queryMaster.getOrderBy());
        buildOffset(queryMaster.getOffset());
        buildLimit(queryMaster.getLimit());
        return new QueryBuilderResponse(finalQuery.toString());
    }

    protected void buildSelect(List<FieldInfo> selectFields) {
        List<String> fullPathFields = new ArrayList<>(selectFields.size());
        for (FieldInfo fieldInfo: selectFields) {
            String name = fieldInfo.getName();
            if (fieldInfo.isAggregateField()) {
                name = String.format(fieldInfo.getAggregateFunction().getSqlFunction() + String.format("(%s.%s)", StringUtils.defaultIfBlank(fieldInfo.getObjectAlias(), baseTableAlias), name));
            } else {
                name = String.format("%s.%s", StringUtils.defaultIfBlank(fieldInfo.getObjectAlias(), baseTableAlias), name);
            }
            fullPathFields.add(String.format("%s AS %s", name, StringUtils.defaultIfBlank(fieldInfo.getAlias(), fieldInfo.getName())));
        }
        this.finalQuery.append(String.format(SELECT_QUERY, StringUtils.join(selectFields, COMMA)));
    }

    protected void buildFromObject(String objectName) {
        this.finalQuery.append(String.format(FROM_QUERY, objectName)).append(AS_WITH_SPACE).append(baseTableAlias);
    }

    protected void buildWhereClause(Filter where) {
        if (Objects.isNull(where) || CollectionUtils.isEmpty(where.getConditions())) return;
        String whereFilterString = FilterHelper.buildWhereFilterString(where);
        if (StringUtils.isNotBlank(whereFilterString)) {
            this.finalQuery.append(String.format(WHERE_QUERY, whereFilterString));
        }
    }

    protected void buildGroupBy(List<FieldInfo> groupByFieldsList) {
        if (CollectionUtils.isEmpty(groupByFieldsList)) return;
        this.finalQuery.append(String.format(GROUPBY_QUERY, groupByFieldsList.stream().map(FieldInfo::getName).collect(Collectors.joining(COMMA))));
    }

    protected void buildHaving(Filter having) {
        if (Objects.isNull(having) || CollectionUtils.isEmpty(having.getConditions())) return;
        String whereFilterString = FilterHelper.buildWhereFilterString(having);
        if (StringUtils.isNotBlank(whereFilterString)) {
            this.finalQuery.append(String.format(HAVING_QUERY, whereFilterString));
        }
    }

    protected void buildOrderBy(Map<String, Order> orderByFieldsMap) {
        List<String> orderByFields = new ArrayList<>();
        for (Map.Entry<String, Order> orderByEntry : orderByFieldsMap.entrySet()) {
            orderByFields.add(DOUBLE_QUOTE + orderByEntry.getKey() + DOUBLE_QUOTE + SPACE +
                    orderByEntry.getValue().toString().toUpperCase());
        }
        this.finalQuery.append(String
                .format(ORDERBY_QUERY, StringUtils.join(orderByFields, COMMA)));
    }

    protected void buildOffset(Integer offset) {
        this.finalQuery.append(String.format(OFFSET_QUERY, offset));
    }

    protected void buildLimit(Integer limit) {
        this.finalQuery.append(String.format(LIMIT_QUERY, limit));
    }

    protected void buildJoins(Map<String, JoinCondition> joinInfo) {
        if (MapUtils.isEmpty(joinInfo)) return;

        StringBuilder joiningString = new StringBuilder();
        for (JoinCondition joinCondition : joinInfo.values()) {
            joiningString.append(StringUtils.SPACE);
            joiningString.append(Optional.ofNullable(joinCondition.getJoinType()).orElse(defaultJoinType).toString());
            joiningString.append(StringUtils.SPACE);

            joiningString.append(DOUBLE_QUOTE).append(joinCondition.getTargetObjectName()).append(DOUBLE_QUOTE);
            joiningString.append(AS_WITH_SPACE);
            //Target Object Alias Name
            joiningString.append(DOUBLE_QUOTE).append(joinCondition.getTargetObjectAlias()).append(DOUBLE_QUOTE);
            joiningString.append(StringUtils.SPACE);
            joiningString.append(ON);
            joiningString.append(StringUtils.SPACE);
            //Source Object Alias Name
            joiningString.append(DOUBLE_QUOTE).append(joinCondition.getSourceObjectAlias()).append(DOUBLE_QUOTE);
            joiningString.append(DOT);
            joiningString.append(DOUBLE_QUOTE).append(joinCondition.getSourceFieldName()).append(DOUBLE_QUOTE);
            joiningString.append(StringUtils.SPACE);
            joiningString.append(EQUALS);
            joiningString.append(StringUtils.SPACE);
            //Target Object Alias Name
            joiningString.append(DOUBLE_QUOTE).append(joinCondition.getTargetObjectAlias()).append(DOUBLE_QUOTE);
            joiningString.append(DOT);
            joiningString.append(DOUBLE_QUOTE).append(joinCondition.getTargetFieldName()).append(DOUBLE_QUOTE);
            joiningString.append(SPACE);
        }
        this.finalQuery.append(joiningString);
    }

}
