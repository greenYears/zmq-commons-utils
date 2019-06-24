package com.github.greenyears.core.utils;

import java.util.Arrays;
import java.util.Iterator;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 扩展字符串.
 *
 * @author zhoumeiqin
 * @date 2019-04-02
 */
public class ExpandStringUtil {
    private static final Pattern NAMES_PATTERN = Pattern.compile("\\{([^/]+?)\\}");

    /**
     * Remove nested "{}" such as in URI vars with regular expressions.
     *
     * @param source
     * @return
     */
    private static String sanitizeSource(String source) {
        int level = 0;
        StringBuilder sb = new StringBuilder();
        for (char c : source.toCharArray()) {
            if (c == '{') {
                level++;
            }
            if (c == '}') {
                level--;
            }
            if (level > 1 || (level == 1 && c == '}')) {
                continue;
            }
            sb.append(c);
        }
        return sb.toString();
    }

    private static Object getValue(Iterator<Object> iterator, String name) {
        if (!iterator.hasNext()) {
            throw new IllegalArgumentException("Not enough variable values available to expand '" + name + "'");
        }
        return iterator.next();
    }

    /**
     * 展开字符串
     * @author zhoumeiqin
     * @date 2019-06-24 17:04
     * @param source
     * @param variables
     * @return java.lang.String
     **/
    public static String expandStringComponent(String source, Object... variables) {
        if (variables == null || variables.length == 0) {
            return source;
        }
        Iterator<Object> iterator = Arrays.asList(variables).iterator();
        if (source == null) {
            return null;
        }
        if (source.indexOf('{') == -1) {
            return source;
        }
        if (source.indexOf(':') != -1) {
            source = sanitizeSource(source);
        }
        Matcher matcher = NAMES_PATTERN.matcher(source);
        StringBuffer sb = new StringBuffer();
        while (matcher.find()) {
            String match = matcher.group(1);
            String variableName = getVariableName(match);
            Object variableValue = getValue(iterator, variableName);
            String variableValueString = getVariableValueAsString(variableValue);
            String replacement = Matcher.quoteReplacement(variableValueString);
            matcher.appendReplacement(sb, replacement);
        }
        matcher.appendTail(sb);
        return sb.toString();
    }


    private static String getVariableName(String match) {
        int colonIdx = match.indexOf(':');
        return (colonIdx != -1 ? match.substring(0, colonIdx) : match);
    }

    private static String getVariableValueAsString(Object variableValue) {
        return (variableValue != null ? variableValue.toString() : "");
    }
}
