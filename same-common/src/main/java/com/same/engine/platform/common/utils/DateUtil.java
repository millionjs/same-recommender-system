package com.same.engine.platform.common.utils;

import com.google.common.collect.Lists;
import com.same.engine.platform.common.constant.Constant;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.*;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.List;

public class DateUtil {

    public static List<String> getIntervalDate(String startDate, String endDate, String format,
                                               int order) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(format);
        LocalDate start = LocalDate.parse(startDate, formatter);
        LocalDate end = LocalDate.parse(endDate, formatter);

        return order >= 0 ? getIntervalDateAsc(start, end, formatter)
                : getIntervalDateDsc(start, end, formatter);
    }

    private static List<String> getIntervalDateAsc(LocalDate start, LocalDate end,
                                                   DateTimeFormatter formatter) {
        List<String> list = Lists.newArrayList();
        while (end.isAfter(start.plusDays(-1))) {
            list.add(formatter.format(start));
            start = start.plusDays(1);
        }
        return list;
    }

    private static List<String> getIntervalDateDsc(LocalDate start, LocalDate end,
                                                   DateTimeFormatter formatter) {
        List<String> list = Lists.newArrayList();
        while (start.isBefore(end.plusDays(1))) {
            list.add(formatter.format(end));
            end = end.plusDays(-1);
        }
        return list;
    }

    public static String formatDateToString(LocalDate date, String format) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(format);
        return formatter.format(date);
    }

    public static String getCurrentDataTime() {
        try {
            return formatDateToString(new Date(), Constant.ISO_LOCAL_DATE_TIME_HYPHEN);
        } catch (Exception e) {
            return "unknown time";
        }
    }

    public static String getTimeStringFromUnixTime(long unixTimestamp) {

        // 将Unix时间戳转换为LocalDateTime
        LocalDateTime dateTime = LocalDateTime.ofInstant(Instant.ofEpochMilli(unixTimestamp), ZoneId.systemDefault());

        // 创建一个DateTimeFormatter对象
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

        // 使用DateTimeFormatter格式化LocalDateTime
        String formattedDateTime = dateTime.format(formatter);

        return formattedDateTime;  // 输出：2024-06-14 00:24:34
    }

    public static String getCurrentDate() {
        try {
            return formatDateToString(new Date(), Constant.ISO_LOCAL_DATE_HYPHEN);
        } catch (Exception e) {
            return "unknown date";
        }
    }

    public static String getNDayBeforeDate(int daysToSubtract) {
        try {
            LocalDate todayDate = LocalDate.now();
            LocalDate nDayBeforeDate = todayDate.minusDays(daysToSubtract);
            return nDayBeforeDate.format(DateTimeFormatter.ofPattern(Constant.ISO_LOCAL_DATE_HYPHEN));
        } catch (Exception e) {
            return "unknown date";
        }
    }

    public static String getNDayBeforeDateTime(int daysToSubtract) {
        try {
            LocalDateTime todayDateTime = LocalDateTime.now();
            LocalDateTime nDayBeforeDateTime = todayDateTime.minusDays(daysToSubtract);
            return nDayBeforeDateTime.format(DateTimeFormatter.ofPattern(Constant.ISO_LOCAL_DATE_TIME_HYPHEN));
        } catch (Exception e) {
            return "unknown datetime";
        }
    }

    public static String formatDateToString(Date date, String format) {
        SimpleDateFormat formatter = new SimpleDateFormat(format);
        return formatter.format(date);
    }

    public static String formatLocalDateTimeToString(LocalDateTime date, String format) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(format);
        return formatter.format(date);
    }

    public static String fromLongToString(long time, String format) {
        Date date = convert(time, format);
        return formatDateToString(date, format);
    }

    public static String fromLongToString(long time) {
        Date date = convert(time, Constant.ISO_LOCAL_DATE_TIME_HYPHEN);
        return formatDateToString(date, Constant.ISO_LOCAL_DATE_TIME_HYPHEN);
    }

    public static Date convert(long time, String format) {
        SimpleDateFormat formatter = new SimpleDateFormat(format);
        String day = formatter.format(time);
        try {
            return formatter.parse(day);
        } catch (ParseException e) {
        }
        return new Date();
    }

    public static Date convert(String dateTime, String format) {
        SimpleDateFormat formatter = new SimpleDateFormat(format);
        try {
            return formatter.parse(dateTime);
        } catch (ParseException e) {
        }
        return new Date();
    }

    public static Long getLocalDateMills(LocalDateTime localDateTime) {
        Long milliSecond = localDateTime.toInstant(ZoneOffset.of("+8")).toEpochMilli();
        return milliSecond;
    }

    public static Date tryConvertToDate(String dateTime, String format) {
        SimpleDateFormat formatter = new SimpleDateFormat(format);
        try {
            return formatter.parse(dateTime);
        } catch (ParseException e) {
        }
        return null;
    }

    public static LocalDateTime tryConvertToLocalDate(String dateTime, String format) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(format);
        return LocalDateTime.parse(dateTime, formatter);
    }


    public static String convertToTargetLocalDate(String dateTime) {
        DateTimeFormatter targetFormatter = DateTimeFormatter.ofPattern(Constant.ISO_LOCAL_DATE_TIME_HYPHEN);
        DateTimeFormatter slashFormatter = DateTimeFormatter.ofPattern(Constant.ISO_LOCAL_DATE_TIME_SLASH);
        DateTimeFormatter slashFormatterWithoutSeconds = DateTimeFormatter.ofPattern(Constant.ISO_LOCAL_DATE_TIME_SLASH_WITHOUT_SECONDS);
        try {
            return targetFormatter.format(LocalDateTime.parse(dateTime, slashFormatter));
        } catch (Exception e) {
            try {
                return targetFormatter.format(LocalDateTime.parse(dateTime, slashFormatterWithoutSeconds));
            } catch (Exception ex) {
                throw new RuntimeException("时间格式转换异常, dateTime=" + dateTime + ", 不符合 " + Constant.ISO_LOCAL_DATE_TIME_SLASH + " 的格式规范!");
            }
        }
    }

    public static long dateTimeToLong(LocalDateTime localDateTime) {
        return localDateTime.atZone(ZoneId.systemDefault()).toInstant().toEpochMilli();
    }

    public static LocalDateTime longToDateTime(long localDateTimeLongVal) {
        return LocalDateTime.ofInstant(Instant.ofEpochMilli(localDateTimeLongVal), ZoneId.systemDefault());
    }

    public static LocalDateTime getStartOfMinute(LocalDateTime dateTime) {
        return LocalDateTime.of(dateTime.getYear(), dateTime.getMonth(), dateTime.getDayOfMonth(), dateTime.getHour(), dateTime.getMinute(), 0);
    }

    public static boolean isInTimeRange(LocalDateTime start, LocalDateTime end) {
        if (start == null || end == null) {
            return false;
        }

        LocalDateTime now = LocalDateTime.now();
        return now.isAfter(start) && now.isBefore(end);
    }

    /**
     * 有重叠 返回true，没有重叠 返回false
     * @return
     */
    public static boolean isTimeOverlap(LocalDateTime startTime1, LocalDateTime endTime1,
                                        LocalDateTime startTime2, LocalDateTime endTime2) {
        if(startTime1.isAfter(endTime1) || startTime2.isAfter(endTime2)){
            throw new RuntimeException("isTimeOverlap, 参数不合法");
        }
        return startTime1.isBefore(endTime2) && startTime2.isBefore(endTime1);
    }
}
