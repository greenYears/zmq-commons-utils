package com.github.core.utils;

import java.math.BigDecimal;

/**
 * @author zhoumeiqin
 * @date 2019-01-29
 */
public class BigDecimalUtil {

    /**
     * 求和.
     *
     * @param args
     * @return
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
     * @param count
     * @param args
     * @return
     */
    public static BigDecimal average(Integer count, BigDecimal... args) {
        if (count == null || count == 0) {
            return BigDecimal.ZERO;
        }
        BigDecimal sum = sum(args);
        return sum.divide(BigDecimal.valueOf(count), 2, BigDecimal.ROUND_HALF_UP);
    }
}
