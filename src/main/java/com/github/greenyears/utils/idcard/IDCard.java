package com.github.greenyears.utils.idcard;

import com.github.greenyears.core.utils.AssertUtils;
import com.github.greenyears.core.utils.DateUtil;
import lombok.Data;
import lombok.experimental.Accessors;
import org.apache.commons.lang3.StringUtils;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * 身份证.
 * <br/>
 * 仅支持二代身份证.
 * <p/>
 * IDCard
 *
 * @author zhoumeiqin
 * @date 2021/3/4
 */
@Data
@Accessors(chain = true)
public class IDCard implements Serializable {
    private static final long serialVersionUID = 7090074111662163669L;
    /**
     * 身份证号.
     */
    private String number;

    public IDCard(String number) {
        this.number = number;
    }

    /**
     * 身份证号是否满足条件.
     *
     * @return true or false
     */
    public boolean isPattern() {
        if (StringUtils.isBlank(this.number)) {
            return false;
        }
        return this.number.matches(IDCardPattern.PATTERN_2);
    }

    /**
     * 获取出生日期.
     *
     * @return
     */
    public String getBirthday() {
        AssertUtils.isTrue(this.isPattern(), "身份证号不正确");
        return this.number.substring(6, 10) + "-" + this.number.substring(10, 12) + "-" + this.number.substring(12, 14);
    }

    /**
     * 获取性别.
     *
     * @return F: 女，M：男
     */
    public String getSex() {
        AssertUtils.isTrue(this.isPattern(), "身份证号不正确");
        return Integer.parseInt(this.number.substring(this.number.length() - 4, this.number.length() - 1)) % 2 == 0 ? "F" : "M";
    }

    /**
     * 获取年龄.
     *
     * @return
     */
    public int getAge() {
        AssertUtils.isTrue(this.isPattern(), "身份证号不正确");
        final LocalDateTime now = DateUtil.now();
        final int year = now.getYear();
        return year - Integer.parseInt(this.number.substring(6, 10));
    }

    /**
     * 身份证正则表达式.
     */
    private static class IDCardPattern {
        //第一代身份证正则表达式(15位)
        @Deprecated
        public static String PATTERN_1 = "^[1-9]\\d{7}((0\\d)|(1[0-2]))(([0|1|2]\\d)|3[0-1])\\d{3}$";
        //第二代身份证正则表达式(18位)
        public static String PATTERN_2 = "^[1-9]\\d{5}[1-9]\\d{3}((0\\d)|(1[0-2]))(([0|1|2]\\d)|3[0-1])((\\d{4})|\\d{3}[A-Z])$";
    }
}
