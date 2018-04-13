package com.fasterxml.jackson.databind.util;

import com.fasterxml.jackson.core.io.NumberInput;
import java.text.DateFormat;
import java.text.FieldPosition;
import java.text.ParseException;
import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;

public class StdDateFormat extends DateFormat {
    protected static final String[] ALL_FORMATS = new String[]{DATE_FORMAT_STR_ISO8601, DATE_FORMAT_STR_ISO8601_Z, "EEE, dd MMM yyyy HH:mm:ss zzz", DATE_FORMAT_STR_PLAIN};
    protected static final DateFormat DATE_FORMAT_ISO8601 = new SimpleDateFormat(DATE_FORMAT_STR_ISO8601, DEFAULT_LOCALE);
    protected static final DateFormat DATE_FORMAT_ISO8601_Z = new SimpleDateFormat(DATE_FORMAT_STR_ISO8601_Z, DEFAULT_LOCALE);
    protected static final DateFormat DATE_FORMAT_PLAIN = new SimpleDateFormat(DATE_FORMAT_STR_PLAIN, DEFAULT_LOCALE);
    protected static final DateFormat DATE_FORMAT_RFC1123 = new SimpleDateFormat("EEE, dd MMM yyyy HH:mm:ss zzz", DEFAULT_LOCALE);
    public static final String DATE_FORMAT_STR_ISO8601 = "yyyy-MM-dd'T'HH:mm:ss.SSSZ";
    protected static final String DATE_FORMAT_STR_ISO8601_Z = "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'";
    protected static final String DATE_FORMAT_STR_PLAIN = "yyyy-MM-dd";
    protected static final String DATE_FORMAT_STR_RFC1123 = "EEE, dd MMM yyyy HH:mm:ss zzz";
    private static final Locale DEFAULT_LOCALE = Locale.US;
    private static final TimeZone DEFAULT_TIMEZONE = TimeZone.getTimeZone("UTC");
    public static final StdDateFormat instance = new StdDateFormat();
    protected transient DateFormat _formatISO8601;
    protected transient DateFormat _formatISO8601_z;
    protected transient DateFormat _formatPlain;
    protected transient DateFormat _formatRFC1123;
    protected Boolean _lenient;
    protected final Locale _locale;
    protected transient TimeZone _timezone;

    static {
        DATE_FORMAT_RFC1123.setTimeZone(DEFAULT_TIMEZONE);
        DATE_FORMAT_ISO8601.setTimeZone(DEFAULT_TIMEZONE);
        DATE_FORMAT_ISO8601_Z.setTimeZone(DEFAULT_TIMEZONE);
        DATE_FORMAT_PLAIN.setTimeZone(DEFAULT_TIMEZONE);
    }

    public StdDateFormat() {
        this._locale = DEFAULT_LOCALE;
    }

    @Deprecated
    public StdDateFormat(TimeZone timeZone, Locale locale) {
        this._timezone = timeZone;
        this._locale = locale;
    }

    protected StdDateFormat(TimeZone timeZone, Locale locale, Boolean bool) {
        this._timezone = timeZone;
        this._locale = locale;
        this._lenient = bool;
    }

    public static TimeZone getDefaultTimeZone() {
        return DEFAULT_TIMEZONE;
    }

    public StdDateFormat withTimeZone(TimeZone timeZone) {
        if (timeZone == null) {
            timeZone = DEFAULT_TIMEZONE;
        }
        return (timeZone == this._timezone || timeZone.equals(this._timezone)) ? this : new StdDateFormat(timeZone, this._locale, this._lenient);
    }

    public StdDateFormat withLocale(Locale locale) {
        return locale.equals(this._locale) ? this : new StdDateFormat(this._timezone, locale, this._lenient);
    }

    public StdDateFormat clone() {
        return new StdDateFormat(this._timezone, this._locale, this._lenient);
    }

    @Deprecated
    public static DateFormat getISO8601Format(TimeZone timeZone) {
        return getISO8601Format(timeZone, DEFAULT_LOCALE);
    }

    public static DateFormat getISO8601Format(TimeZone timeZone, Locale locale) {
        return _cloneFormat(DATE_FORMAT_ISO8601, DATE_FORMAT_STR_ISO8601, timeZone, locale, null);
    }

    public static DateFormat getRFC1123Format(TimeZone timeZone, Locale locale) {
        return _cloneFormat(DATE_FORMAT_RFC1123, "EEE, dd MMM yyyy HH:mm:ss zzz", timeZone, locale, null);
    }

    @Deprecated
    public static DateFormat getRFC1123Format(TimeZone timeZone) {
        return getRFC1123Format(timeZone, DEFAULT_LOCALE);
    }

    public TimeZone getTimeZone() {
        return this._timezone;
    }

    public void setTimeZone(TimeZone timeZone) {
        if (!timeZone.equals(this._timezone)) {
            _clearFormats();
            this._timezone = timeZone;
        }
    }

    public void setLenient(boolean z) {
        Boolean valueOf = Boolean.valueOf(z);
        if (this._lenient != valueOf) {
            this._lenient = valueOf;
            _clearFormats();
        }
    }

    public boolean isLenient() {
        if (this._lenient == null) {
            return true;
        }
        return this._lenient.booleanValue();
    }

    public Date parse(String str) throws ParseException {
        Date parseAsISO8601;
        int length;
        String trim = str.trim();
        ParsePosition parsePosition = new ParsePosition(0);
        if (looksLikeISO8601(trim)) {
            parseAsISO8601 = parseAsISO8601(trim, parsePosition, true);
        } else {
            length = trim.length();
            while (true) {
                length--;
                if (length < 0) {
                    break;
                }
                char charAt = trim.charAt(length);
                if ((charAt < '0' || charAt > '9') && (length > 0 || charAt != '-')) {
                    break;
                }
            }
            if (length >= 0 || !(trim.charAt(0) == '-' || NumberInput.inLongRange(trim, false))) {
                parseAsISO8601 = parseAsRFC1123(trim, parsePosition);
            } else {
                parseAsISO8601 = new Date(Long.parseLong(trim));
            }
        }
        if (parseAsISO8601 != null) {
            return parseAsISO8601;
        }
        StringBuilder stringBuilder = new StringBuilder();
        for (String str2 : ALL_FORMATS) {
            if (stringBuilder.length() > 0) {
                stringBuilder.append("\", \"");
            } else {
                stringBuilder.append('\"');
            }
            stringBuilder.append(str2);
        }
        stringBuilder.append('\"');
        throw new ParseException(String.format("Can not parse date \"%s\": not compatible with any of standard forms (%s)", new Object[]{trim, stringBuilder.toString()}), parsePosition.getErrorIndex());
    }

    public Date parse(String str, ParsePosition parsePosition) {
        if (looksLikeISO8601(str)) {
            try {
                return parseAsISO8601(str, parsePosition, false);
            } catch (ParseException e) {
                return null;
            }
        }
        int length = str.length();
        while (true) {
            length--;
            if (length < 0) {
                break;
            }
            char charAt = str.charAt(length);
            if ((charAt < '0' || charAt > '9') && (length > 0 || charAt != '-')) {
                break;
            }
        }
        if (length >= 0 || (str.charAt(0) != '-' && !NumberInput.inLongRange(str, false))) {
            return parseAsRFC1123(str, parsePosition);
        }
        return new Date(Long.parseLong(str));
    }

    public StringBuffer format(Date date, StringBuffer stringBuffer, FieldPosition fieldPosition) {
        if (this._formatISO8601 == null) {
            this._formatISO8601 = _cloneFormat(DATE_FORMAT_ISO8601, DATE_FORMAT_STR_ISO8601, this._timezone, this._locale, this._lenient);
        }
        return this._formatISO8601.format(date, stringBuffer, fieldPosition);
    }

    public String toString() {
        String str = "DateFormat " + getClass().getName();
        TimeZone timeZone = this._timezone;
        if (timeZone != null) {
            str = str + " (timezone: " + timeZone + ")";
        }
        return str + "(locale: " + this._locale + ")";
    }

    protected boolean looksLikeISO8601(String str) {
        if (str.length() >= 5 && Character.isDigit(str.charAt(0)) && Character.isDigit(str.charAt(3)) && str.charAt(4) == '-') {
            return true;
        }
        return false;
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    protected java.util.Date parseAsISO8601(java.lang.String r10, java.text.ParsePosition r11, boolean r12) throws java.text.ParseException {
        /*
        r9 = this;
        r7 = 90;
        r5 = 84;
        r6 = 58;
        r4 = 12;
        r3 = 48;
        r2 = r10.length();
        r0 = r2 + -1;
        r0 = r10.charAt(r0);
        r1 = 10;
        if (r2 > r1) goto L_0x0056;
    L_0x0018:
        r1 = java.lang.Character.isDigit(r0);
        if (r1 == 0) goto L_0x0056;
    L_0x001e:
        r1 = r9._formatPlain;
        r0 = "yyyy-MM-dd";
        if (r1 != 0) goto L_0x0032;
    L_0x0024:
        r1 = DATE_FORMAT_PLAIN;
        r2 = r9._timezone;
        r3 = r9._locale;
        r4 = r9._lenient;
        r1 = _cloneFormat(r1, r0, r2, r3, r4);
        r9._formatPlain = r1;
    L_0x0032:
        r1 = r1.parse(r10, r11);
        if (r1 != 0) goto L_0x0158;
    L_0x0038:
        r1 = new java.text.ParseException;
        r2 = "Can not parse date \"%s\": while it seems to fit format '%s', parsing fails (leniency? %s)";
        r3 = 3;
        r3 = new java.lang.Object[r3];
        r4 = 0;
        r3[r4] = r10;
        r4 = 1;
        r3[r4] = r0;
        r0 = 2;
        r4 = r9._lenient;
        r3[r0] = r4;
        r0 = java.lang.String.format(r2, r3);
        r2 = r11.getErrorIndex();
        r1.<init>(r0, r2);
        throw r1;
    L_0x0056:
        if (r0 != r7) goto L_0x0088;
    L_0x0058:
        r0 = r9._formatISO8601_z;
        r1 = "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'";
        if (r0 != 0) goto L_0x006c;
    L_0x005e:
        r0 = DATE_FORMAT_ISO8601_Z;
        r3 = r9._timezone;
        r4 = r9._locale;
        r5 = r9._lenient;
        r0 = _cloneFormat(r0, r1, r3, r4, r5);
        r9._formatISO8601_z = r0;
    L_0x006c:
        r3 = r2 + -4;
        r3 = r10.charAt(r3);
        if (r3 != r6) goto L_0x0159;
    L_0x0074:
        r3 = new java.lang.StringBuilder;
        r3.<init>(r10);
        r2 = r2 + -1;
        r4 = ".000";
        r3.insert(r2, r4);
        r10 = r3.toString();
        r8 = r1;
        r1 = r0;
        r0 = r8;
        goto L_0x0032;
    L_0x0088:
        r0 = hasTimeZone(r10);
        if (r0 == 0) goto L_0x011a;
    L_0x008e:
        r0 = r2 + -3;
        r0 = r10.charAt(r0);
        if (r0 != r6) goto L_0x00dd;
    L_0x0096:
        r0 = new java.lang.StringBuilder;
        r0.<init>(r10);
        r1 = r2 + -3;
        r2 = r2 + -2;
        r0.delete(r1, r2);
        r10 = r0.toString();
    L_0x00a6:
        r0 = r10.length();
        r1 = r10.lastIndexOf(r5);
        r1 = r0 - r1;
        r1 = r1 + -6;
        if (r1 >= r4) goto L_0x00c2;
    L_0x00b4:
        r0 = r0 + -5;
        r2 = new java.lang.StringBuilder;
        r2.<init>(r10);
        switch(r1) {
            case 5: goto L_0x0114;
            case 6: goto L_0x010f;
            case 7: goto L_0x00be;
            case 8: goto L_0x0109;
            case 9: goto L_0x0103;
            case 10: goto L_0x00fd;
            case 11: goto L_0x00f9;
            default: goto L_0x00be;
        };
    L_0x00be:
        r10 = r2.toString();
    L_0x00c2:
        r0 = r9._formatISO8601;
        r1 = "yyyy-MM-dd'T'HH:mm:ss.SSSZ";
        r2 = r9._formatISO8601;
        if (r2 != 0) goto L_0x00d8;
    L_0x00ca:
        r0 = DATE_FORMAT_ISO8601;
        r2 = r9._timezone;
        r3 = r9._locale;
        r4 = r9._lenient;
        r0 = _cloneFormat(r0, r1, r2, r3, r4);
        r9._formatISO8601 = r0;
    L_0x00d8:
        r8 = r1;
        r1 = r0;
        r0 = r8;
        goto L_0x0032;
    L_0x00dd:
        r1 = 43;
        if (r0 == r1) goto L_0x00e5;
    L_0x00e1:
        r1 = 45;
        if (r0 != r1) goto L_0x00a6;
    L_0x00e5:
        r0 = new java.lang.StringBuilder;
        r0.<init>();
        r0 = r0.append(r10);
        r1 = "00";
        r0 = r0.append(r1);
        r10 = r0.toString();
        goto L_0x00a6;
    L_0x00f9:
        r2.insert(r0, r3);
        goto L_0x00be;
    L_0x00fd:
        r1 = "00";
        r2.insert(r0, r1);
        goto L_0x00be;
    L_0x0103:
        r1 = "000";
        r2.insert(r0, r1);
        goto L_0x00be;
    L_0x0109:
        r1 = ".000";
        r2.insert(r0, r1);
        goto L_0x00be;
    L_0x010f:
        r1 = "00.000";
        r2.insert(r0, r1);
    L_0x0114:
        r1 = ":00.000";
        r2.insert(r0, r1);
        goto L_0x00be;
    L_0x011a:
        r0 = new java.lang.StringBuilder;
        r0.<init>(r10);
        r1 = r10.lastIndexOf(r5);
        r1 = r2 - r1;
        r1 = r1 + -1;
        if (r1 >= r4) goto L_0x0131;
    L_0x0129:
        switch(r1) {
            case 9: goto L_0x0154;
            case 10: goto L_0x0151;
            case 11: goto L_0x014e;
            default: goto L_0x012c;
        };
    L_0x012c:
        r1 = ".000";
        r0.append(r1);
    L_0x0131:
        r0.append(r7);
        r10 = r0.toString();
        r1 = r9._formatISO8601_z;
        r0 = "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'";
        if (r1 != 0) goto L_0x0032;
    L_0x013e:
        r1 = DATE_FORMAT_ISO8601_Z;
        r2 = r9._timezone;
        r3 = r9._locale;
        r4 = r9._lenient;
        r1 = _cloneFormat(r1, r0, r2, r3, r4);
        r9._formatISO8601_z = r1;
        goto L_0x0032;
    L_0x014e:
        r0.append(r3);
    L_0x0151:
        r0.append(r3);
    L_0x0154:
        r0.append(r3);
        goto L_0x0131;
    L_0x0158:
        return r1;
    L_0x0159:
        r8 = r1;
        r1 = r0;
        r0 = r8;
        goto L_0x0032;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.fasterxml.jackson.databind.util.StdDateFormat.parseAsISO8601(java.lang.String, java.text.ParsePosition, boolean):java.util.Date");
    }

    protected Date parseAsRFC1123(String str, ParsePosition parsePosition) {
        if (this._formatRFC1123 == null) {
            this._formatRFC1123 = _cloneFormat(DATE_FORMAT_RFC1123, "EEE, dd MMM yyyy HH:mm:ss zzz", this._timezone, this._locale, this._lenient);
        }
        return this._formatRFC1123.parse(str, parsePosition);
    }

    private static final boolean hasTimeZone(String str) {
        int length = str.length();
        if (length >= 6) {
            char charAt = str.charAt(length - 6);
            if (charAt == '+' || charAt == '-') {
                return true;
            }
            charAt = str.charAt(length - 5);
            if (charAt == '+' || charAt == '-') {
                return true;
            }
            char charAt2 = str.charAt(length - 3);
            if (charAt2 == '+' || charAt2 == '-') {
                return true;
            }
        }
        return false;
    }

    private static final DateFormat _cloneFormat(DateFormat dateFormat, String str, TimeZone timeZone, Locale locale, Boolean bool) {
        DateFormat dateFormat2;
        if (locale.equals(DEFAULT_LOCALE)) {
            dateFormat2 = (DateFormat) dateFormat.clone();
            if (timeZone != null) {
                dateFormat2.setTimeZone(timeZone);
            }
        } else {
            dateFormat2 = new SimpleDateFormat(str, locale);
            if (timeZone == null) {
                timeZone = DEFAULT_TIMEZONE;
            }
            dateFormat2.setTimeZone(timeZone);
        }
        if (bool != null) {
            dateFormat2.setLenient(bool.booleanValue());
        }
        return dateFormat2;
    }

    protected void _clearFormats() {
        this._formatRFC1123 = null;
        this._formatISO8601 = null;
        this._formatISO8601_z = null;
        this._formatPlain = null;
    }
}
