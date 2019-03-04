package com.github.greenyears.core.utils;

import com.google.common.base.Splitter;
import com.google.common.collect.Lists;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.CollectionUtils;

import java.time.*;
import java.time.format.DateTimeFormatter;
import java.time.temporal.TemporalUnit;
import java.util.Date;
import java.util.List;

/**
 * calculate util.
 *
 * @author zhoumeiqin
 */
@Slf4j
public class CalculateUtil {
    /**
     * format_yyyy-MM-dd HH:mm:ss.
     */
    public static final String FMT_1 = "yyyy-MM-dd HH:mm:ss";
    public static final String FMT_2 = "yyyy-MM-dd HH:mm:ss.SSS";
    public static final String FMT_3 = "yyyy-MM-dd'T'HH:mm:ss";
    public static final String FMT_4 = "yyyy-MM-dd";
    public static final String FMT_5 = "yyyyMMdd";

    /**
     * 获取当前的时间.
     *
     * @return LocalDateTime类型的当前时间
     */
    public static LocalDateTime now() {
        return LocalDateTime.now();
    }

    /**
     * 当前时间的格式化输出.
     *
     * @param format 时间格式化表达式
     * @return 当前时间的格式化字符串
     */
    public static String nowFormat(String format) {
        LocalDateTime now = LocalDateTime.now();
        return format(now, format);
    }

    /**
     * 将date转换成LocalDateTime的类型.
     *
     * @param date 源时间
     * @return 转换后的LocalDateTime类型时间
     */
    public static LocalDateTime toLocalDateTime(Date date) {
        Instant instant = date.toInstant();
        ZoneId zoneId = ZoneId.systemDefault();
        return instant.atZone(zoneId).toLocalDateTime();
    }

    /**
     * 将时间戳(毫秒)转换成LocalDateTime类型时间
     *
     * @param timestamp 时间戳
     * @return LocalDateTime类型时间
     */
    public static LocalDateTime toLocalDateTime(Long timestamp) {
        return toLocalDateTime(new Date(timestamp));
    }

    /**
     * 将LocalDateTime类型转换成Date类型.
     *
     * @param localDateTime 源时间
     * @return Date类型目标时间
     */
    public static Date toDate(LocalDateTime localDateTime) {
        ZoneId zoneId = ZoneId.systemDefault();
        ZonedDateTime zonedDateTime = localDateTime.atZone(zoneId);
        return Date.from(zonedDateTime.toInstant());
    }

    /**
     * 将时间戳(毫秒)转换成Date类型.
     *
     * @param timestamp 时间戳(毫秒)
     * @return Date类型时间
     */
    public static Date toDate(long timestamp) {
        return new Date(timestamp);
    }

    /**
     * 将LocalDateTime类型转换成时间戳(毫秒).
     *
     * @param localDateTime 源时间
     * @return 时间戳(毫秒)
     */
    public static long toMillisecond(LocalDateTime localDateTime) {
        return localDateTime.toInstant(ZoneOffset.of("+8")).toEpochMilli();
    }

    /**
     * 将Date类型转换成时间戳
     *
     * @param date 源时间
     * @return 时间戳(毫秒)
     */
    public static long toMillisecond(Date date) {
        LocalDateTime localDateTime = toLocalDateTime(date);
        return localDateTime.toInstant(ZoneOffset.of("+8")).toEpochMilli();
    }


    /**
     * 将LocalDateTime类型转换成时间戳(秒).
     *
     * @param localDateTime 时间
     * @return 时间戳(秒)
     */
    public static int toSecond(LocalDateTime localDateTime) {
        return (int) localDateTime.toEpochSecond(ZoneOffset.of("+8"));
    }

    /**
     * 将Date类型转换成时间戳.
     *
     * @param date 时间
     * @return 时间戳(秒)
     */
    public static long toSecond(Date date) {
        LocalDateTime localDateTime = toLocalDateTime(date);
        return (int) localDateTime.toEpochSecond(ZoneOffset.of("+8"));
    }

    /**
     * 获取当前时间的偏移时间的开始时间.
     * <p>
     * 例如，当前时间为2019-03-04, offset为1，
     * 则，dayStart(1)返回的结果是2019-03-05 00:00:00的LocalDateTime类型时间
     * </p>
     *
     * @param offset 偏移量，0今天，1明天，-1昨天，依次类推
     * @return LocalDateTime类型的时间的开始时间
     */
    public static LocalDateTime dayStart(int offset) {
        return LocalDateTime.of(LocalDate.now().plusDays(offset), LocalTime.MIN);
    }

    /**
     * 获取某个时间的开始时间.
     *
     * @param date 源时间
     * @return LocalDateTime类型的开始时间
     */
    public static LocalDateTime dayStart(Date date) {
        return LocalDateTime.of(toLocalDateTime(date).toLocalDate(), LocalTime.MIN);
    }

    /**
     * 获取对应date的开始时间.
     *
     * @param date 源时间
     * @return Date类型的开始时间
     */
    public static Date dayStartToDate(Date date) {
        LocalDateTime localDateTime = LocalDateTime.of(toLocalDateTime(date).toLocalDate(), LocalTime.MIN);
        return toDate(localDateTime);
    }

    /**
     * 获取对应date的偏移日期的开始时间.
     * <p>
     * 如，date为2019-02-08, offset为2，则返回的结果是
     * 2019年02月10日的00时00分00秒的Date类型数据.
     * </p>
     *
     * @param date   时间参考系
     * @param offset 0今天，1明天，-1昨天，依次类推
     * @return Date类型的时间
     */
    public static Date dayStartToDate(Date date, int offset) {
        LocalDateTime localDateTime = LocalDateTime.of(toLocalDateTime(date).plusDays(offset).toLocalDate(), LocalTime.MIN);
        return toDate(localDateTime);
    }

    /**
     * 获取当前日期的偏移日期的开始时间的时间戳(秒).
     *
     * @param offset 时间偏移量:0今天，1明天，-1昨天，依次类推
     * @return 时间戳(秒)
     */
    public static int dayStartToSecond(int offset) {
        LocalDateTime localDateTime = dayStart(offset);
        return toSecond(localDateTime);
    }

    /**
     * 获取当前日期的偏移日期的开始时间的时间戳(毫秒).
     *
     * @param offset 时间偏移量： 0今天，1明天，-1昨天，依次类推
     * @return 时间戳(毫秒)
     */
    public static long dayStartToMillisecond(int offset) {
        LocalDateTime localDateTime = dayStart(offset);
        return toMillisecond(localDateTime);
    }

    /**
     * 获取对应date的日期的开始时间的时间戳(毫秒).
     *
     * @param date 源日期
     * @return 时间戳(毫秒)
     */
    public static long dayStartToMillisecond(Date date) {
        LocalDateTime localDateTime = LocalDateTime.of(toLocalDateTime(date).toLocalDate(), LocalTime.MIN);
        return toMillisecond(localDateTime);
    }


    /**
     * 获取当前日期的偏移时间的结束时间.
     * <p>
     * 如，当前日期是2019-03-04,offset为2，则返回的是2019年03月06日的23时59分59秒的LocalDateTime类型时间
     * </p>
     *
     * @param offset 时间偏移量： 0今天，1明天，-1昨天，依次类推
     * @return LocalDateTime类型时间
     */
    public static LocalDateTime dayEnd(int offset) {
        return LocalDateTime.of(LocalDate.now().plusDays(offset), LocalTime.MAX);
    }

    /**
     * 获取date的结束时间
     *
     * @param date 源时间
     * @return LocalDateTime类型时间
     */
    public static LocalDateTime dayEnd(Date date) {
        return LocalDateTime.of(toLocalDateTime(date).toLocalDate(), LocalTime.MAX);
    }

    /**
     * 获取Date的结束时间.
     *
     * @param date 源时间
     * @return Date类型时间
     */
    public static Date dayEndToDate(Date date) {
        LocalDateTime localDateTime = LocalDateTime.of(toLocalDateTime(date).toLocalDate(), LocalTime.MAX);
        return toDate(localDateTime);
    }

    /**
     * 获取date日期的偏移日期的结束时间.
     *
     * @param date   源日期
     * @param offset 时间偏移量：0今天，1明天，-1昨天，依次类推
     * @return Date类型时间
     */
    public static Date dayEndToDate(Date date, int offset) {
        LocalDateTime localDateTime = LocalDateTime.of(toLocalDateTime(date).plusDays(offset).toLocalDate(), LocalTime.MAX);
        return toDate(localDateTime);
    }

    /**
     * 获取当前日期的偏移日期的结束时间的时间戳(秒).
     *
     * @param offset 0今天，1明天，-1昨天，依次类推
     * @return 时间戳(秒)
     */
    public static int dayEndToSecond(int offset) {
        LocalDateTime localDateTime = dayEnd(offset);
        return toSecond(localDateTime);
    }

    /**
     * 获取当前日期的偏移日期的结束时间的时间戳(毫秒).
     *
     * @param offset 0今天，1明天，-1昨天，依次类推
     * @return 时间戳(毫秒)
     */
    public static long dayEndtToMillisend(int offset) {
        LocalDateTime localDateTime = dayEnd(offset);
        return toMillisecond(localDateTime);
    }

    /**
     * 按照formatter来将字符串text解析成Date类型的时间对象.
     *
     * @param text      一定时间格式的时间字符串
     * @param formatter 时间格式化字符串
     * @return Date类型时间
     */
    public static Date parse(String text, String formatter) {
        LocalDateTime localDateTime = LocalDateTime.parse(text, DateTimeFormatter.ofPattern(formatter));
        return toDate(localDateTime);
    }

    /**
     * 按照'yyyy-MM-dd HH:mm:ss'来将字符串text解析成Date类型的时间对象.
     *
     * @param text 满足'yyyy-MM-dd HH:mm:ss'格式的时间字符串
     * @return Date类型时间
     */
    public static Date parse(String text) {
        LocalDateTime localDateTime = LocalDateTime.parse(text, DateTimeFormatter.ofPattern(FMT_1));
        return toDate(localDateTime);
    }

    /**
     * 将Date类型时间按照format格式进行格式化.
     *
     * @param date   Date类型时间
     * @param format 格式化的格式
     * @return 格式化后的时间字符串
     */
    public static String format(Date date, String format) {
        LocalDateTime localDateTime = toLocalDateTime(date);
        return DateTimeFormatter.ofPattern(format).format(localDateTime);
    }

    /**
     * 将LocalDateTime类型时间按照format格式进行格式化.
     *
     * @param localDateTime LocalDateTime类型的时间
     * @param format        格式化的格式
     * @return 格式化后的时间字符串
     */
    public static String format(LocalDateTime localDateTime, String format) {
        return DateTimeFormatter.ofPattern(format).format(localDateTime);
    }

    /**
     * 将Date类型的时间按照'yyyy-MM-dd HH:mm:ss'格式进行格式化.
     *
     * @param date Date类型的时间
     * @return 格式化后的时间字符串
     */
    public static String format(Date date) {
        if (date == null) {
            return "";
        }
        LocalDateTime localDateTime = toLocalDateTime(date);
        return DateTimeFormatter.ofPattern(FMT_1).format(localDateTime);
    }

    /**
     * date 减去分钟数.
     *
     * @param date    源时间
     * @param minutes 要减去的分钟数
     * @return 减去minutes数值分钟后的时间类型对象
     */
    public static Date minusMinutes(Date date, long minutes) {
        LocalDateTime localDateTime = toLocalDateTime(date);
        LocalDateTime resultDateTime = localDateTime.minusMinutes(minutes);
        return toDate(resultDateTime);
    }

    /**
     * 减去天数.
     *
     * @param date 源时间
     * @param days 要减去的日期天数
     * @return 减去days后的时间
     */
    public static Date minusDays(Date date, long days) {
        LocalDateTime source = toLocalDateTime(date);
        LocalDateTime localDateTime = source.minusDays(days);
        return toDate(localDateTime);
    }

    /**
     * 当前日期减去days后的时间.
     *
     * @param days 要减去的日期天数
     * @return 当前日期减去days后的时间
     */
    public static Date minusDays(long days) {
        LocalDateTime source = LocalDateTime.now();
        LocalDateTime localDateTime = source.minusDays(days);
        return toDate(localDateTime);
    }

    /**
     * 减去对应的时间.
     *
     * @param date             源时间
     * @param amountToSubtract 要减去的时间
     * @param unit             时间单位
     * @return 最终的时间
     */
    public static Date minus(Date date, long amountToSubtract, TemporalUnit unit) {
        LocalDateTime localDateTime = toLocalDateTime(date);
        LocalDateTime resultDateTime = localDateTime.minus(amountToSubtract, unit);
        return toDate(resultDateTime);
    }

    /**
     * 计算2个时间之间相差的日期天数.
     *
     * @param t1 时间1
     * @param t2 时间2
     * @return t1和t2之间相差的天数
     */
    public static long differenceDays(LocalDateTime t1, LocalDateTime t2) {
        Duration duration = Duration.between(t1, t2);
        return duration.toDays();
    }

    /**
     * 转换成当前日期的偏移日期的时间格式化字符串.
     *
     * @param offset 0 当日 1 明日 -1昨日
     * @param format 格式化字符串
     * @return 格式化之后的时间字符串
     */
    public static String toDaysFormat(int offset, String format) {
        return LocalDateTime.now().plusDays(offset).format(DateTimeFormatter.ofPattern(format));
    }

    /**
     * 转换成Date日期的偏移日期的时间格式化字符串.
     *
     * @param date   date 参考系时间
     * @param offset 0 当日 1 明日 -1昨日
     * @param format 格式化字符串
     * @return 格式化后的时间字符串
     */
    public static String toDaysFormat(Date date, int offset, String format) {
        return toLocalDateTime(date).plusDays(offset).format(DateTimeFormatter.ofPattern(format));
    }

    /**
     * 判断date是否在string的时间内
     *
     * @param string 以','分割的时间格式化字符串，如12:00-23:59
     * @param date   date
     * @param format string的格式化类型.
     * @return 是否在string范围内
     */
    public static boolean isDateBetween(String string, Date date, String format) {
        String target = CalculateUtil.format(date, format);
        List<String> list = Lists.newArrayList(Splitter.on("-").trimResults().omitEmptyStrings().split(string));
        if (CollectionUtils.isEmpty(list)) {
            return false;
        }
        if (list.size() == 1) {
            return list.get(0).equals(target);
        }
        String start = list.get(0);
        String end = list.get(1);
        return target.compareTo(start) >= 0 && target.compareTo(end) <= 0;
    }

}
