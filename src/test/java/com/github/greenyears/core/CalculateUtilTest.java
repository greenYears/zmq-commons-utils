package com.github.greenyears.core;

import com.github.greenyears.core.utils.CalculateUtil;
import org.junit.Test;

/**
 * @author zhoumeiqin
 * @date 2019-02-22
 */
public class CalculateUtilTest {

    @Test
    public void testNow() {
        System.out.println(CalculateUtil.nowFormat("yyyy-MM-dd HH:mm:ss"));
    }
}
