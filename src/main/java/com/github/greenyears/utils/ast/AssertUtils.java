package com.github.greenyears.utils.ast;

import com.github.greenyears.core.exception.ApiException;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;

import java.util.Collection;

/**
 * assert util.
 *
 * @author zhoumeiqin
 */
public class AssertUtils {

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

    /**
     * if then expression is true, then run callback.
     *
     * @param expression expression
     * @param callback   callback
     */
    public static void isTrueRun(boolean expression, AssertCallback callback) {
        if (expression) {
            callback.run();
        }
    }

    public static void isNull(Object object, String message) {
        if (object != null) {
            throw new ApiException(message);
        }
    }

    /**
     * if the object is null, then run callback.
     *
     * @param object   object
     * @param callback callback
     */
    public static void isNullRun(Object object, AssertCallback callback) {
        if (object == null) {
            callback.run();
        }
    }

    public static void notNull(Object object, String message) {
        if (object == null) {
            throw new ApiException(message);
        }
    }

    /**
     * if object is not null, run callback.
     *
     * @param object   object
     * @param callback callback
     */
    public static void notNullRun(Object object, AssertCallback callback) {
        if (object != null) {
            callback.run();
        }
    }

    public static void isBlank(String str, String message) {
        if (!StringUtils.isBlank(str)) {
            throw new ApiException(message);
        }
    }

    /**
     * if string is blank, then run callback.
     *
     * @param str      string
     * @param callback callback
     */
    public static void isBlankRun(String str, AssertCallback callback) {
        if (StringUtils.isBlank(str)) {
            callback.run();
        }
    }

    public static void notBlank(String str, String message) {
        if (StringUtils.isBlank(str)) {
            throw new ApiException(message);
        }
    }

    /**
     * if string is not blank, run callback.
     *
     * @param str      string
     * @param callback callback
     */
    public static void notBlankRun(String str, AssertCallback callback) {
        if (StringUtils.isNotBlank(str)) {
            callback.run();
        }
    }


    public static void doesNotContain(String textToSearch, String substring, String message) {
        if (StringUtils.isNotBlank(textToSearch) && StringUtils.isNotBlank(substring) && textToSearch.contains(substring)) {
            throw new ApiException(message);
        }
    }

    /**
     * if textToSearch contain subString, then run callback.
     *
     * @param textToSearch source
     * @param substring    target
     * @param callback     callback
     */
    public static void isContainRun(String textToSearch, String substring, AssertCallback callback) {
        if (StringUtils.isNotBlank(textToSearch) && StringUtils.isNotBlank(substring) && textToSearch.contains(substring)) {
            callback.run();
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

    /**
     * if collection is empty, run callback.
     *
     * @param collection collection
     * @param callback   callback
     */
    public static void isEmptyRun(Collection<?> collection, AssertCallback callback) {
        if (CollectionUtils.isEmpty(collection)) {
            callback.run();
        }
    }

    private static boolean endsWithSeparator(String msg) {
        return msg.endsWith(":") || msg.endsWith(";") || msg.endsWith(",") || msg.endsWith(".");
    }

    private static String messageWithTypeName(String msg, Object typeName) {
        return msg + (msg.endsWith(" ") ? "" : ": ") + typeName;
    }
}
