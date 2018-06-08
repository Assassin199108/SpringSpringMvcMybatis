package mvc5.kit;


import lombok.SneakyThrows;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Objects;

/**
 * Created with IntelliJ IDEA
 *
 * @author 喵♂呜
 * Created on 2017/6/13 10:52.
 */
public class DateKit {
    private DateKit() {}

    private static final String SIMPLE_FORMAT_VALUE = "yyyy-MM-dd HH:mm:ss";
    private static final String DATE_FORMAT_VALUE = "yyyy-MM-dd";
    private static final String TIMESTAMP_FORMAT_VALUE = "yyyyMMddHHmmssSSSZ";

    private static ThreadLocal<SimpleDateFormat> SIMPLE_FORMAT = ThreadLocal
            .withInitial(() -> new SimpleDateFormat(SIMPLE_FORMAT_VALUE));
    private static ThreadLocal<SimpleDateFormat> DATE_FORMAT = ThreadLocal
            .withInitial(() -> new SimpleDateFormat(DATE_FORMAT_VALUE));
    private static ThreadLocal<SimpleDateFormat> TIMESTAMP_FORMAT = ThreadLocal
            .withInitial(() -> new SimpleDateFormat(TIMESTAMP_FORMAT_VALUE));

    /**
     * 格式化日期格式为字符串
     *
     * @param date
     * @param format
     * @return
     */
    public static String format(Date date, String format) {
        SimpleDateFormat dateFormat = new SimpleDateFormat(format);
        return dateFormat.format(date);
    }

    /**
     * 格式化当前日期格式为字符串
     *
     * @param format
     * @return
     */
    public static String formatNow(String format) {
        return format(new Date(), format);
    }


    /**
     * 解析日期
     *
     * @param str
     *         解析字符串为Date
     * @return {@link Date}
     * @throws ParseException
     *         解析异常
     */
    @SneakyThrows
    public static Date parseTime(String str) {
        if (Objects.isNull(str)) {return null;}
        return SIMPLE_FORMAT.get().parse(str);
    }

    /**
     * 解析日期
     *
     * @param str
     *         解析字符串为Date
     * @return {@link Date}
     * @throws ParseException
     *         解析异常
     */
    @SneakyThrows
    public static Date parseDate(String str) {
        if (Objects.isNull(str)) {return null;}
        return DATE_FORMAT.get().parse(str);
    }

    /**
     * 解析日期
     *
     * @param str
     *         解析字符串为Date
     * @return {@link Date}
     * @throws ParseException
     *         解析异常
     */
    @SneakyThrows
    public static Date parseTimestamp(String str) {
        if (Objects.isNull(str)) {return null;}
        return TIMESTAMP_FORMAT.get().parse(str);
    }

    /**
     * 解析日期
     *
     * @param str
     *         解析字符串为Date
     * @return {@link Date}
     */
    public static Date parseLong(String str) {
        if (Objects.isNull(str)) {return null;}
        return new Date(Long.parseLong(str));
    }

    /**
     * 解析日期
     *
     * @param str
     *         解析字符串为Date
     * @return {@link Date}
     */
    public static Date parse(String str) {
        if (Objects.isNull(str) || str.length() == 0) {return null;}
        try {
            return parseTime(str);
        } catch (Exception e) {
            try {
                return parseDate(str);
            } catch (Exception ex) {
                try {
                    return parseLong(str);
                } catch (NumberFormatException exc) {
                    return null;
                }
            }
        }
    }

    /**
     * 格式化日期
     *
     * @param date
     *         可接受{@link Date}和{@link Long}
     * @return {@link String}
     */
    public static String format(Object date) {
        if (Objects.isNull(date)) {return null;}
        return SIMPLE_FORMAT.get().format(date);
    }

    /**
     * 格式化时间戳
     *
     * @param date
     *         数值型字符串
     * @return {@link String}
     */
    public static String formatLong(Object date) {
        if (Objects.isNull(date)) {return null;}
        if (date instanceof String) {
            return format(Long.valueOf(date.toString()));
        }
        return format(date);
    }
}
