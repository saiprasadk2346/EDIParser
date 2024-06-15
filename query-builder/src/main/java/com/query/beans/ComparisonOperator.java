package com.query.beans;

import com.fasterxml.jackson.annotation.JsonCreator;
import org.apache.commons.lang3.StringUtils;

import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public enum ComparisonOperator {
    EQ("EQ", "="),
    NE("NE", "<>"),
    EQ_CIS("EQ_CIS", "="),
    NE_CIS("NE_CIS", "<>"),
    LT("LT", "<"),
    GT("GT", ">"),
    LTE("LTE", "<="),
    GTE("GTE", ">="),
    BTW("BTW", "between"),
    NOT_BTW("NOT_BTW", "not between"),
    IN("IN", "in"),
    NOT_IN("NOT_IN", "NOT IN"),
    IS_NULL("IS_NULL", "IS NULL"),
    IS_NOT_NULL("IS_NOT_NULL", "IS NOT NULL"),
    CONTAINS("CONTAINS", "ILIKE"),
    DOES_NOT_CONTAINS("DOES_NOT_CONTAINS", "NOT ILIKE"),
    STARTS_WITH("STARTS_WITH", "ILIKE"),
    ENDS_WITH("ENDS_WITH", "ILIKE"),
    CONTAINS_CS("CONTAINS_CS", "LIKE"),
    DOES_NOT_CONTAINS_CS("DOES_NOT_CONTAIN_CS", "NOT LIKE"),
    STARTS_WITH_CS("STARTS_WITH_CS", "LIKE"),
    ENDS_WITH_CS("ENDS_WITH_CS", "LIKE");

    private final String operatorLiteral;
    private final String sqlOperator;

    private static final Map<String, ComparisonOperator> COMPARISION_OPERATOR_MAP = Stream.of(values()).collect(Collectors.toMap((s) -> s.operatorLiteral.toLowerCase(), Function.identity()));

    ComparisonOperator(String operatorLiteral, String sqlOperator) {
        this.operatorLiteral = operatorLiteral;
        this.sqlOperator = sqlOperator;
    }

    @JsonCreator
    public static ComparisonOperator fromString(String operatorLiteral) {
        return StringUtils.isBlank(operatorLiteral) ? null : (ComparisonOperator)COMPARISION_OPERATOR_MAP.get(operatorLiteral.toLowerCase());
    }

    public String getOperatorLiteral() {
        return this.operatorLiteral;
    }

    public String getSqlOperator() {
        return this.sqlOperator;
    }
}
