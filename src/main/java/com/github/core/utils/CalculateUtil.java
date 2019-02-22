package com.github.core.utils;

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
 * @date 2018/10/27
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
     * @return LocalDateTime
     */
    public static LocalDateTime now() {
        return LocalDateTime.now();
    }

    /**
     * 当前时间的格式化输出.
     *
     * @param format 时间格式化表达式
     * @return string
     */
    public static String nowFormat(String format) {
        LocalDateTime now = LocalDateTime.now();
        return format(now, format);
    }

    /**
     * Date to LocalDateTime.
     *
     * @param date
     * @return
     */
    public static LocalDateTime toLocalDateTime(Date date) {
        Instant instant = date.toInstant();
        ZoneId zoneId = ZoneId.systemDefault();
        return instant.atZone(zoneId).toLocalDateTime();
    }

    /**
     * Date to LocalDateTime.
     *
     * @param timestamp
     * @return
     */
    public static LocalDateTime toLocalDateTime(Long timestamp) {
        return toLocalDateTime(new Date(timestamp));
    }

    /**
     * LocalDateTime to Date.
     *
     * @param localDateTime
     * @return
     */
    public static Date toDate(LocalDateTime localDateTime) {
        ZoneId zoneId = ZoneId.systemDefault();
        ZonedDateTime zonedDateTime = localDateTime.atZone(zoneId);
        return Date.from(zonedDateTime.toInstant());
    }

    /**
     * 转换时间.
     *
     * @param timestamp
     * @return
     */
    public static Date toDate(long timestamp) {
        return new Date(timestamp);
    }

    /**
     * 将LocalDateTime类型转换成毫秒输出.
     *
     * @param localDateTime
     * @return
     */
    public static long toMillisecond(LocalDateTime localDateTime) {
        return localDateTime.toInstant(ZoneOffset.of("+8")).toEpochMilli();
    }

    /**
     * 将Date类型转换成毫秒输出.
     *
     * @param date
     * @return
     */
    public static long toMillisecond(Date date) {
        LocalDateTime localDateTime = toLocalDateTime(date);
        return localDateTime.toInstant(ZoneOffset.of("+8")).toEpochMilli();
    }


    /**
     * 将LocalDateTime类型转换成秒输出.
     *
     * @param localDateTime
     * @return
     */
    public static int toSecond(LocalDateTime localDateTime) {
        return (int) localDateTime.toEpochSecond(ZoneOffset.of("+8"));
    }

    /**
     * 将Date类型转换成秒输出.
     *
     * @param date
     * @return
     */
    public static long toSecond(Date date) {
        LocalDateTime localDateTime = toLocalDateTime(date);
        return (int) localDateTime.toEpochSecond(ZoneOffset.of("+8"));
    }

    /**
     * 获取某天的开始日期.
     *
     * @param offset 0今天，1明天，-1昨天，依次类推
     * @return
     */
    public static LocalDateTime dayStart(int offset) {
        return LocalDateTime.of(LocalDate.now().plusDays(offset), LocalTime.MIN);
    }

    /**
     * 获取某天的开始日期.
     *
     * @param date
     * @return
     */
    public static LocalDateTime dayStart(Date date) {
        return LocalDateTime.of(toLocalDateTime(date).toLocalDate(), LocalTime.MIN);
    }

    /**
     * 获取对应date的开始时间.
     *
     * @param date 时间参考系
     * @return
     */
    public static Date dayStartToDate(Date date) {
        LocalDateTime localDateTime = LocalDateTime.of(toLocalDateTime(date).toLocalDate(), LocalTime.MIN);
        return toDate(localDateTime);
    }

    /**
     * 获取对应date的偏移时间.
     *
     * @param date   时间参考系
     * @param offset 0今天，1明天，-1昨天，依次类推
     * @return
     */
    public static Date dayStartToDate(Date date, int offset) {
        LocalDateTime localDateTime = LocalDateTime.of(toLocalDateTime(date).plusDays(offset).toLocalDate(), LocalTime.MIN);
        return toDate(localDateTime);
    }

    /**
     * 获取某天的开始时间，并转换成秒单位输出.
     *
     * @param offset 0今天，1明天，-1昨天，依次类推
     * @return 秒数
     */
    public static int dayStartToSecond(int offset) {
        LocalDateTime localDateTime = dayStart(offset);
        return toSecond(localDateTime);
    }

    /**
     * 获取某天的开始时间，并转换成毫秒单位输出.
     *
     * @param offset 0今天，1明天，-1昨天，依次类推
     * @return 毫秒数
     */
    public static long dayStartToMillisecond(int offset) {
        LocalDateTime localDateTime = dayStart(offset);
        return toMillisecond(localDateTime);
    }

    /**
     * 获取对应date的开始时间, 并转换成毫秒单位输出.
     *
     * @param date
     * @return
     */
    public static long dayStartToMillisecond(Date date) {
        LocalDateTime localDateTime = LocalDateTime.of(toLocalDateTime(date).toLocalDate(), LocalTime.MIN);
        return toMillisecond(localDateTime);
    }


    /**
     * 获取某天的结束时间.
     *
     * @param offset
     * @return
     */
    public static LocalDateTime dayEnd(int offset) {
        return LocalDateTime.of(LocalDate.now().plusDays(offset), LocalTime.MAX);
    }

    /**
     * 获取某天的结束日期.
     *
     * @param date 时间参考系
     * @return
     */
    public static LocalDateTime dayEnd(Date date) {
        return LocalDateTime.of(toLocalDateTime(date).toLocalDate(), LocalTime.MAX);
    }

    /**
     * 获取某天的结束时间.
     *
     * @param date 时间参考系.
     * @return
     */
    public static Date dayEndToDate(Date date) {
        LocalDateTime localDateTime = LocalDateTime.of(toLocalDateTime(date).toLocalDate(), LocalTime.MAX);
        return toDate(localDateTime);
    }

    /**
     * 获取某天的结束时间.
     *
     * @param date   0今天，1明天，-1昨天，依次类推
     * @param offset
     * @return
     */
    public static Date dayEndToDate(Date date, int offset) {
        LocalDateTime localDateTime = LocalDateTime.of(toLocalDateTime(date).plusDays(offset).toLocalDate(), LocalTime.MAX);
        return toDate(localDateTime);
    }

    /**
     * 获取某天的结束时间.
     *
     * @param offset 0今天，1明天，-1昨天，依次类推
     * @return 秒数
     */
    public static int dayEndToSecond(int offset) {
        LocalDateTime localDateTime = dayEnd(offset);
        return toSecond(localDateTime);
    }

    /**
     * 获取某天的结束时间.
     *
     * @param offset 0今天，1明天，-1昨天，依次类推
     * @return 毫秒数
     */
    public static long dayEndtToMillisend(int offset) {
        LocalDateTime localDateTime = dayEnd(offset);
        return toMillisecond(localDateTime);
    }

    /**
     * 解析.
     *
     * @param text
     * @param formatter
     * @return
     */
    public static Date parse(String text, String formatter) {
        LocalDateTime localDateTime = LocalDateTime.parse(text, DateTimeFormatter.ofPattern(formatter));
        return toDate(localDateTime);
    }

    /**
     * 解析.
     *
     * @param text
     * @return
     */
    public static Date parse(String text) {
        LocalDateTime localDateTime = LocalDateTime.parse(text, DateTimeFormatter.ofPattern(FMT_1));
        return toDate(localDateTime);
    }

    /**
     * 格式化.
     *
     * @param date
     * @param format
     * @return
     */
    public static String format(Date date, String format) {
        LocalDateTime localDateTime = toLocalDateTime(date);
        return DateTimeFormatter.ofPattern(format).format(localDateTime);
    }

    /**
     * 格式化.
     *
     * @param localDateTime
     * @param format
     * @return
     */
    public static String format(LocalDateTime localDateTime, String format) {
        return DateTimeFormatter.ofPattern(format).format(localDateTime);
    }

    /**
     * 格式化.
     *
     * @param date
     * @return
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
     * @param date
     * @param minutes
     * @return
     */
    public static Date minusMinutes(Date date, long minutes) {
        LocalDateTime localDateTime = toLocalDateTime(date);
        LocalDateTime resultDateTime = localDateTime.minusMinutes(minutes);
        return toDate(resultDateTime);
    }

    /**
     * 减去天数.
     *
     * @param date
     * @param days
     * @return
     */
    public static Date minusDays(Date date, long days) {
        LocalDateTime source = toLocalDateTime(date);
        LocalDateTime localDateTime = source.minusDays(days);
        return toDate(localDateTime);
    }

    /**
     * 减去天数.
     *
     * @param days
     * @return
     */
    public static Date minusDays(long days) {
        LocalDateTime source = LocalDateTime.now();
        LocalDateTime localDateTime = source.minusDays(days);
        return toDate(localDateTime);
    }

    /**
     * 减去对应的时间.
     *
     * @param date
     * @param amountToSubtract
     * @param unit
     * @return
     */
    public static Date minus(Date date, long amountToSubtract, TemporalUnit unit) {
        LocalDateTime localDateTime = toLocalDateTime(date);
        LocalDateTime resultDateTime = localDateTime.minus(amountToSubtract, unit);
        return toDate(resultDateTime);
    }

    /**
     * 计算2个日期之间差的天数.
     *
     * @param t1
     * @param t2
     * @return
     */
    public static long differenceDays(LocalDateTime t1, LocalDateTime t2) {
        Duration duration = Duration.between(t1, t2);
        return duration.toDays();
    }

    /**
     * 转换成当前日期的某一天的时间字符串.
     *
     * @param offset 0 当日 1 明日 -1昨日
     * @param format
     * @return
     */
    public static String toDaysFormat(int offset, String format) {
        return LocalDateTime.now().plusDays(offset).format(DateTimeFormatter.ofPattern(format));
    }

    /**
     * 转换成当前日期的某一天的时间字符串.
     *
     * @param offset 0 当日 1 明日 -1昨日
     * @param format
     * @param date   date 参考系时间
     * @return
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
     * @return
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
