package com.parser.util;

import org.apache.commons.lang3.StringUtils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public final class EDIUtil {

    private EDIUtil() {}

    public static Date parseDate(String dateString, String format) {
        if (StringUtils.isAnyBlank(dateString, dateString)) return null;
        try {
            return new SimpleDateFormat(format).parse(dateString);
        } catch (ParseException e) {
            throw new IllegalArgumentException("Invalid date format: " + dateString, e);
        }
    }

    public static Double parseDouble(String doubleString) {
        if (StringUtils.isBlank(doubleString)) return null;
        return Double.parseDouble(doubleString);
    }
}
