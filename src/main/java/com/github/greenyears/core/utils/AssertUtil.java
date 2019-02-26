package com.github.greenyears.core.utils;

import com.github.greenyears.core.exception.ApiException;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;

import java.util.Collection;

/**
 * assert util.
 *
 * @author zhoumeiqin
 * @date 2018/10/23
 */
public class AssertUtil {


    public static void state(boolean expression, String message) {
        if (!expression) {
            throw new ApiException(message);
        }
    }

    public static void isTrue(boolean expression, String message) {
        if (!expression) {
            throw new ApiException(message);
        }
    }

    public static void isNull(Object object, String message) {
        if (object != null) {
            throw new ApiException(message);
        }
    }

    public static void notNull(Object object, String message) {
        if (object == null) {
            throw new ApiException(message);
        }
    }

    public static void isBlank(String str, String message) {
        if (!StringUtils.isBlank(str)) {
            throw new ApiException(message);
        }
    }

    public static void notBlank(String str, String message) {
        if (StringUtils.isBlank(str)) {
            throw new ApiException(message);
        }
    }


    public static void doesNotContain(String textToSearch, String substring, String message) {
        if (StringUtils.isNotBlank(textToSearch) && StringUtils.isNotBlank(substring) && textToSearch.contains(substring)) {
            throw new ApiException(message);
        }
    }

    public static void noNullElements(Object[] array, String message) {
        if (array != null) {
            Object[] var2 = array;
            int var3 = array.length;

            for (int var4 = 0; var4 < var3; ++var4) {
                Object element = var2[var4];
                if (element == null) {
                    throw new ApiException(message);
                }
            }
        }
    }

    public static void notEmpty(Collection<?> collection, String message) {
        if (CollectionUtils.isEmpty(collection)) {
            throw new ApiException(message);
        }
    }

    private static boolean endsWithSeparator(String msg) {
        return msg.endsWith(":") || msg.endsWith(";") || msg.endsWith(",") || msg.endsWith(".");
    }

    private static String messageWithTypeName(String msg, Object typeName) {
        return msg + (msg.endsWith(" ") ? "" : ": ") + typeName;
    }
}
