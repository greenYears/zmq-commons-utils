package com.github.greenyears.core;

import com.github.greenyears.core.utils.AssertUtils;
import org.junit.Test;

/**
 * Assert Utils Test.
 *
 * @author zhoumeiqin
 * @date 2019-02-21
 */
public class AssertUtilsTest {

    @Test
    public void testAssert() {
        String str = "";
        String message = "The message is blank";
        AssertUtils.notBlank(str, message);
    }
}
