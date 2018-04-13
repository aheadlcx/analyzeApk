package org.apache.commons.httpclient.util;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Collection;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;

public class DateParser {
    private static final Collection DEFAULT_PATTERNS = Arrays.asList(new String[]{"EEE MMM d HH:mm:ss yyyy", "EEEE, dd-MMM-yy HH:mm:ss zzz", "EEE, dd MMM yyyy HH:mm:ss zzz"});
    public static final String PATTERN_ASCTIME = "EEE MMM d HH:mm:ss yyyy";
    public static final String PATTERN_RFC1036 = "EEEE, dd-MMM-yy HH:mm:ss zzz";
    public static final String PATTERN_RFC1123 = "EEE, dd MMM yyyy HH:mm:ss zzz";

    public static Date parseDate(String str) throws DateParseException {
        return parseDate(str, null);
    }

    public static Date parseDate(String str, Collection collection) throws DateParseException {
        DateFormat dateFormat;
        if (str == null) {
            throw new IllegalArgumentException("dateValue is null");
        }
        if (collection == null) {
            collection = DEFAULT_PATTERNS;
        }
        if (str.length() > 1 && str.startsWith("'") && str.endsWith("'")) {
            str = str.substring(1, str.length() - 1);
        }
        DateFormat dateFormat2 = null;
        for (String str2 : r5) {
            if (dateFormat2 == null) {
                dateFormat2 = new SimpleDateFormat(str2, Locale.US);
                dateFormat2.setTimeZone(TimeZone.getTimeZone("GMT"));
                dateFormat = dateFormat2;
            } else {
                dateFormat2.applyPattern(str2);
                dateFormat = dateFormat2;
            }
            try {
                return dateFormat.parse(str);
            } catch (ParseException e) {
                dateFormat2 = dateFormat;
            }
        }
        throw new DateParseException(new StringBuffer().append("Unable to parse the date ").append(str).toString());
    }

    private DateParser() {
    }
}
