package com.github.greenyears.core.utils;

import java.math.BigDecimal;

/**
 * @author zhoumeiqin
 */
public class BigDecimalUtils {

    /**
     * 求和.
     * <pre>
     *      如果参数是null,按照0处理
     * </pre>
     *
     * @param args 数字参数
     * @return 参数的总和
     */
    public static BigDecimal sum(BigDecimal... args) {
        if (args.length == 0) {
            return BigDecimal.ZERO;
        }
        BigDecimal result = BigDecimal.ZERO;
        for (BigDecimal arg : args) {
            result = result.add(arg == null ? BigDecimal.ZERO : arg);
        }
        return result;
    }

    /**
     * 平均值, 四舍五入，保留2为小数.
     *
     * @param count 平均的count
     * @param args 数字参数
     * @return 平均值
     */
    public static BigDecimal average(Integer count, BigDecimal... args) {
        if (count == null || count == 0) {
            return BigDecimal.ZERO;
        }
        BigDecimal sum = sum(args);
        return sum.divide(BigDecimal.valueOf(count), 2, BigDecimal.ROUND_HALF_UP);
    }
}
