package com.query.utils;

import com.query.beans.*;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;

import java.util.*;
import java.util.stream.Collectors;

import static com.query.beans.ComparisonOperator.EQ_CIS;
import static com.query.beans.ComparisonOperator.NE_CIS;
import static com.query.utils.QueryBuilderConstants.*;

public final class FilterHelper {

    public static String buildWhereFilterString(Filter where) {
        Map<String, String> aliasToConditionMap = new HashMap<>();
        for (Condition condition : where.getConditions()) {
            String conditionString = evaluateFilterCondition(condition);
            aliasToConditionMap.put(condition.getAlias(), conditionString);
        }

        String finalExpression = where.getExpression();
        for (Map.Entry<String, String> entry : aliasToConditionMap.entrySet()) {
            finalExpression = finalExpression.replace(entry.getKey(), entry.getValue());
        }
        return finalExpression;
    }

    private static String evaluateFilterCondition(Condition condition) {
        ValueType valueType = condition.getValueType();
        if (ValueType.VALUE == valueType) {
            ComparisonOperator operator = condition.getOperator();
            String conditionAsString = evaluateFilterForValueType(condition);
            Boolean includeNulls = condition.isIncludeNulls();
            if (Objects.nonNull(includeNulls) && includeNulls.booleanValue() && operator != ComparisonOperator.IS_NOT_NULL &&
                    operator != ComparisonOperator.IS_NULL) {
                return String.format("(%s OR %s)", conditionAsString, evaluateFilterForIncludeNulls(condition));
            }

            return conditionAsString;
        } else if (valueType.FIELD == valueType) {
            // TODO
        } else if (valueType.SUB_QUERY == valueType) {
            // TODO
        }
        return null;
        //throw new Exception("Not supported for ValueType: " + valueType);
    }

    private static String evaluateFilterForValueType(Condition filterCondition) {
        Object firstFilterValue = null;
        if (CollectionUtils.isNotEmpty(filterCondition.getValue())) {
            firstFilterValue = filterCondition.getValue().get(0);
        }
        String condition;
        ComparisonOperator comparisonOperator = filterCondition.getOperator();
        // TODO: null check for comparisonOperator
        String leftOperand = getWhereFieldStringWithObjectAlias(filterCondition, comparisonOperator);
        switch (comparisonOperator) {
            case EQ:
            case EQ_CIS:
            case NE:
            case NE_CIS:
            case LT:
            case GT:
            case LTE:
            case GTE:
                if ((comparisonOperator == EQ_CIS || comparisonOperator == NE_CIS) && isStringDataType(firstFilterValue)) {
                    firstFilterValue = String.format("lower(%s)", firstFilterValue);
                }
                condition = leftOperand + SPACE + firstFilterValue + SPACE;
                break;
            case BTW:
            case NOT_BTW:
                condition = leftOperand + SPACE + firstFilterValue + SPACE_WITH_AND + parse(filterCondition.getValue().get(1));
                break;
            case IS_NULL:
            case IS_NOT_NULL:
                condition = leftOperand;
                break;
            case IN:
            case NOT_IN:
                condition = leftOperand + SPACE + START_BRACKET + StringUtils.join(parse(filterCondition.getValue()), COMMA) + END_BRACKET;
                break;
            case CONTAINS:
            case DOES_NOT_CONTAINS:
            case CONTAINS_CS:
            case DOES_NOT_CONTAINS_CS:
                condition = leftOperand + SPACE + parseForLike(firstFilterValue) + SPACE;
                break;
            case STARTS_WITH:
            case STARTS_WITH_CS:
                condition = leftOperand + SPACE + parseStartWith(firstFilterValue) + SPACE;
                break;
            case ENDS_WITH:
            case ENDS_WITH_CS:
                condition = leftOperand + SPACE + parseEndsWith(firstFilterValue) + SPACE;
                break;
            default:
                throw new UnsupportedOperationException("Unsupported ComparisonOperator: " + comparisonOperator);
        }
        return condition;
    }

    private static String evaluateFilterForIncludeNulls(Condition condition) {
        return getWhereFieldStringWithObjectAlias(condition, ComparisonOperator.IS_NULL);
    }

    private static String getWhereFieldStringWithObjectAlias(Condition condition, ComparisonOperator comparisonOperator) {
        //Calcite doesn't support ILIKE operator, so converting ILIKE operators to LIKE
        String sqlOperator = comparisonOperator.getSqlOperator();
        /*if (sqlOperator.contains(ILIKE)) {
            sqlOperator = sqlOperator.replaceAll(ILIKE, LIKE);
        }*/
        String alias = StringUtils.wrap(condition.getFieldInfo().getObjectAlias(), DOUBLE_QUOTE);
        String field = StringUtils.wrap(condition.getFieldInfo().getName(), DOUBLE_QUOTE);
        return getExpressionForCondition(condition, alias, field, comparisonOperator) + SPACE + sqlOperator;
    }

    private static String getExpressionForCondition(Condition condition, String alias,
                                                    String field, ComparisonOperator comparisonOperator) {
        String expression;
        boolean aggFunctionPresent = condition.getFieldInfo().isAggregateField();
        expression = (aggFunctionPresent ? condition.getFieldInfo().getAggregateFunction().getSqlFunction() + OPEN_PARANTHESIS : StringUtils.EMPTY) + alias + DOT +
                field + (aggFunctionPresent ? CLOSE_PARANTHESIS : StringUtils.EMPTY);
        if ((EQ_CIS == comparisonOperator || NE_CIS == comparisonOperator) && isStringDataType(condition.getValue().get(0))) {
            expression = String.format("lower(%s)", expression);
        }
        return expression;
    }

    private static boolean isStringDataType(Object firstFilterValue) {
        return firstFilterValue instanceof String;
    }

    private static String parseStartWith(Object object) {
        if (object == null) {
            return null;
        }
        String value;
        if (object instanceof String || object instanceof Date) {
            value = SINGLE_QUOTE + escapeSingleQuotes(object) + WILD_CARD + SINGLE_QUOTE;
        } else {
            value = CommonUtil.valueOf(object);
        }
        return value;
    }

    private static String parseEndsWith(Object object) {
        if (object == null) {
            return null;
        }
        String value;
        if (object instanceof String || object instanceof Date) {
            value = SINGLE_QUOTE + WILD_CARD + escapeSingleQuotes(object) + SINGLE_QUOTE;
        } else {
            value = CommonUtil.valueOf(object);
        }
        return value;
    }

    public static String parse(Object object) {
        if (object == null) {
            return null;
        }
        String value;
        if (object instanceof String || object instanceof Date) {
            value = SINGLE_QUOTE + escapeSingleQuotes(object) + SINGLE_QUOTE;
        } else {
            value = CommonUtil.valueOf(object);
        }
        return value;
    }

    private static List<String> parse(List<Object> objects) {
        return objects.stream().map(FilterHelper::parse).collect(Collectors.toList());
    }

    private static String parseForLike(Object object) {
        if (object == null) {
            return null;
        }
        String value;
        if (object instanceof String || object instanceof Date) {
            value = SINGLE_QUOTE + WILD_CARD + escapeSingleQuotes(object) + WILD_CARD + SINGLE_QUOTE;
        } else {
            value = CommonUtil.valueOf(object);
        }
        return value;
    }

    private static String escapeSingleQuotes(Object object) {
        return String.valueOf(object).replace(SINGLE_QUOTE, ESCAPED_SINGLE_QUOTE);
    }
}
