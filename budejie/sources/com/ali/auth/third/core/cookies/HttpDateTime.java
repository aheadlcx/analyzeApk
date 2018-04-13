package com.ali.auth.third.core.cookies;

import android.text.format.Time;
import com.ali.auth.third.core.rpc.protocol.RpcException.ErrorCode;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class HttpDateTime {
    private static final Pattern HTTP_DATE_ANSIC_PATTERN = Pattern.compile(HTTP_DATE_ANSIC_REGEXP);
    private static final String HTTP_DATE_ANSIC_REGEXP = "[ ]([A-Za-z]{3,9})[ ]+([0-9]{1,2})[ ]([0-9]{1,2}:[0-9][0-9]:[0-9][0-9])[ ]([0-9]{2,4})";
    private static final Pattern HTTP_DATE_RFC_PATTERN = Pattern.compile(HTTP_DATE_RFC_REGEXP);
    private static final String HTTP_DATE_RFC_REGEXP = "([0-9]{1,2})[- ]([A-Za-z]{3,9})[- ]([0-9]{2,4})[ ]([0-9]{1,2}:[0-9][0-9]:[0-9][0-9])";

    private static class TimeOfDay {
        int hour;
        int minute;
        int second;

        TimeOfDay(int i, int i2, int i3) {
            this.hour = i;
            this.minute = i2;
            this.second = i3;
        }
    }

    public static long parse(String str) throws IllegalArgumentException {
        int month;
        int year;
        TimeOfDay time;
        int i;
        int i2 = 1;
        Matcher matcher = HTTP_DATE_RFC_PATTERN.matcher(str);
        int date;
        if (matcher.find()) {
            date = getDate(matcher.group(1));
            month = getMonth(matcher.group(2));
            year = getYear(matcher.group(3));
            time = getTime(matcher.group(4));
            i = date;
        } else {
            Matcher matcher2 = HTTP_DATE_ANSIC_PATTERN.matcher(str);
            if (matcher2.find()) {
                month = getMonth(matcher2.group(1));
                date = getDate(matcher2.group(2));
                TimeOfDay time2 = getTime(matcher2.group(3));
                year = getYear(matcher2.group(4));
                time = time2;
                i = date;
            } else {
                throw new IllegalArgumentException();
            }
        }
        if (year >= 2038) {
            year = 2038;
            month = 0;
        } else {
            i2 = i;
        }
        Time time3 = new Time("UTC");
        time3.set(time.second, time.minute, time.hour, i2, month, year);
        return time3.toMillis(false);
    }

    private static int getDate(String str) {
        if (str.length() == 2) {
            return ((str.charAt(0) - 48) * 10) + (str.charAt(1) - 48);
        }
        return str.charAt(0) - 48;
    }

    private static int getMonth(String str) {
        switch (((Character.toLowerCase(str.charAt(0)) + Character.toLowerCase(str.charAt(1))) + Character.toLowerCase(str.charAt(2))) - 291) {
            case 9:
                return 11;
            case 10:
                return 1;
            case 22:
                return 0;
            case 26:
                return 7;
            case 29:
                return 2;
            case 32:
                return 3;
            case 35:
                return 9;
            case 36:
                return 4;
            case 37:
                return 8;
            case 40:
                return 6;
            case 42:
                return 5;
            case 48:
                return 10;
            default:
                throw new IllegalArgumentException();
        }
    }

    private static int getYear(String str) {
        if (str.length() == 2) {
            int charAt = ((str.charAt(0) - 48) * 10) + (str.charAt(1) - 48);
            if (charAt >= 70) {
                return charAt + 1900;
            }
            return charAt + ErrorCode.SERVER_SESSIONSTATUS;
        } else if (str.length() == 3) {
            return ((((str.charAt(0) - 48) * 100) + ((str.charAt(1) - 48) * 10)) + (str.charAt(2) - 48)) + 1900;
        } else {
            if (str.length() == 4) {
                return ((((str.charAt(0) - 48) * 1000) + ((str.charAt(1) - 48) * 100)) + ((str.charAt(2) - 48) * 10)) + (str.charAt(3) - 48);
            }
            return 1970;
        }
    }

    private static TimeOfDay getTime(String str) {
        int i;
        int charAt = str.charAt(0) - 48;
        if (str.charAt(1) != ':') {
            i = 2;
            charAt = (charAt * 10) + (str.charAt(1) - 48);
        } else {
            i = 1;
        }
        i++;
        int i2 = i + 1;
        i = ((str.charAt(i) - 48) * 10) + (str.charAt(i2) - 48);
        i2 = (i2 + 1) + 1;
        int i3 = i2 + 1;
        int i4 = i3 + 1;
        return new TimeOfDay(charAt, i, ((str.charAt(i2) - 48) * 10) + (str.charAt(i3) - 48));
    }
}
