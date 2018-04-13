package org.apache.commons.httpclient.util;

import com.ali.auth.third.core.rpc.protocol.RpcException.ErrorCode;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;

public class DateUtil {
    private static final Collection DEFAULT_PATTERNS = Arrays.asList(new String[]{"EEE MMM d HH:mm:ss yyyy", "EEEE, dd-MMM-yy HH:mm:ss zzz", "EEE, dd MMM yyyy HH:mm:ss zzz"});
    private static final Date DEFAULT_TWO_DIGIT_YEAR_START;
    private static final TimeZone GMT = TimeZone.getTimeZone("GMT");
    public static final String PATTERN_ASCTIME = "EEE MMM d HH:mm:ss yyyy";
    public static final String PATTERN_RFC1036 = "EEEE, dd-MMM-yy HH:mm:ss zzz";
    public static final String PATTERN_RFC1123 = "EEE, dd MMM yyyy HH:mm:ss zzz";

    static {
        Calendar instance = Calendar.getInstance();
        instance.set(ErrorCode.SERVER_SESSIONSTATUS, 0, 1, 0, 0);
        DEFAULT_TWO_DIGIT_YEAR_START = instance.getTime();
    }

    public static Date parseDate(String str) throws DateParseException {
        return parseDate(str, null, null);
    }

    public static Date parseDate(String str, Collection collection) throws DateParseException {
        return parseDate(str, collection, null);
    }

    public static Date parseDate(String str, Collection collection, Date date) throws DateParseException {
        if (str == null) {
            throw new IllegalArgumentException("dateValue is null");
        }
        if (collection == null) {
            collection = DEFAULT_PATTERNS;
        }
        if (date == null) {
            date = DEFAULT_TWO_DIGIT_YEAR_START;
        }
        if (str.length() > 1 && str.startsWith("'") && str.endsWith("'")) {
            str = str.substring(1, str.length() - 1);
        }
        DateFormat dateFormat = null;
        for (String str2 : r5) {
            DateFormat dateFormat2;
            if (dateFormat == null) {
                dateFormat = new SimpleDateFormat(str2, Locale.US);
                dateFormat.setTimeZone(TimeZone.getTimeZone("GMT"));
                dateFormat.set2DigitYearStart(date);
                dateFormat2 = dateFormat;
            } else {
                dateFormat.applyPattern(str2);
                dateFormat2 = dateFormat;
            }
            try {
                return dateFormat2.parse(str);
            } catch (ParseException e) {
                dateFormat = dateFormat2;
            }
        }
        throw new DateParseException(new StringBuffer().append("Unable to parse the date ").append(str).toString());
    }

    public static String formatDate(Date date) {
        return formatDate(date, "EEE, dd MMM yyyy HH:mm:ss zzz");
    }

    public static String formatDate(Date date, String str) {
        if (date == null) {
            throw new IllegalArgumentException("date is null");
        } else if (str == null) {
            throw new IllegalArgumentException("pattern is null");
        } else {
            DateFormat simpleDateFormat = new SimpleDateFormat(str, Locale.US);
            simpleDateFormat.setTimeZone(GMT);
            return simpleDateFormat.format(date);
        }
    }

    private DateUtil() {
    }
}
