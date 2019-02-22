package com.github.core;

import com.github.core.utils.AssertUtil;
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
        AssertUtil.notBlank(str, message);
    }
}
