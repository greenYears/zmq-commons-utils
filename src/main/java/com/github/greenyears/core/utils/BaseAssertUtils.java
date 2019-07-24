package com.github.greenyears.core.utils;

import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;

import java.util.Collection;

/**
 * assert util.
 *
 * @author zhoumeiqin
 */
public abstract class BaseAssertUtils {

    /**
     * 异常处理.
     *
     * @param message
     */
    abstract void exceptionHandler(String message);

    /**
     * 异常处理.
     *
     * @param code
     * @param message
     */
    abstract void exceptionHandler(String code, String message);

    public void state(boolean expression, String message) {
        if (!expression) {
            exceptionHandler(message);
        }
    }

    public void state(boolean expression, String code, String message) {
        if (!expression) {
            exceptionHandler(code, message);
        }
    }

    public void isTrue(boolean expression, String message) {
        if (!expression) {
            exceptionHandler(message);
        }
    }

    public void isTrue(boolean expression, String code, String message) {
        if (!expression) {
            exceptionHandler(code, message);
        }
    }

    public void isNull(Object object, String message) {
        if (object != null) {
            exceptionHandler(message);
        }
    }

    public void isNull(Object object, String code, String message) {
        if (object != null) {
            exceptionHandler(code, message);
        }
    }

    public void notNull(Object object, String message) {
        if (object == null) {
            exceptionHandler(message);
        }
    }

    public void notNull(Object object, String code, String message) {
        if (object == null) {
            exceptionHandler(code, message);
        }
    }

    public void isBlank(String str, String message) {
        if (!StringUtils.isBlank(str)) {
            exceptionHandler(message);
        }
    }

    public void isBlank(String str, String code, String message) {
        if (!StringUtils.isBlank(str)) {
            exceptionHandler(code, message);
        }
    }

    public void notBlank(String str, String message) {
        if (StringUtils.isBlank(str)) {
            exceptionHandler(message);
        }
    }

    public void notBlank(String str, String code, String message) {
        if (StringUtils.isBlank(str)) {
            exceptionHandler(code, message);
        }
    }


    public void doesNotContain(String textToSearch, String substring, String message) {
        if (StringUtils.isNotBlank(textToSearch) && StringUtils.isNotBlank(substring) && textToSearch.contains(substring)) {
            exceptionHandler(message);
        }
    }

    public void doesNotContain(String textToSearch, String substring, String code, String message) {
        if (StringUtils.isNotBlank(textToSearch) && StringUtils.isNotBlank(substring) && textToSearch.contains(substring)) {
            exceptionHandler(code, message);
        }
    }

    public void noNullElements(Object[] array, String message) {
        if (array != null) {
            Object[] var2 = array;
            int var3 = array.length;

            for (int var4 = 0; var4 < var3; ++var4) {
                Object element = var2[var4];
                if (element == null) {
                    exceptionHandler(message);
                }
            }
        }
    }

    public void noNullElements(Object[] array, String code, String message) {
        if (array != null) {
            Object[] var2 = array;
            int var3 = array.length;

            for (int var4 = 0; var4 < var3; ++var4) {
                Object element = var2[var4];
                if (element == null) {
                    exceptionHandler(code, message);
                }
            }
        }
    }

    public void notEmpty(Collection<?> collection, String code, String message) {
        if (CollectionUtils.isEmpty(collection)) {
            exceptionHandler(code, message);
        }
    }

    private boolean endsWithSeparator(String msg) {
        return msg.endsWith(":") || msg.endsWith(";") || msg.endsWith(",") || msg.endsWith(".");
    }

    private String messageWithTypeName(String msg, Object typeName) {
        return msg + (msg.endsWith(" ") ? "" : ": ") + typeName;
    }
}
