package com.iflytek.cloud.resource;

import android.text.TextUtils;
import java.util.Locale;

public class Resource {
    public static final int TAG_ERROR_CODE = 0;
    public static final int TAG_ERROR_UNKNOWN = 1;
    public static final int TEXT_AGAIN = 9;
    public static final int TEXT_CANCEL = 4;
    public static final int TEXT_DETAIL = 3;
    public static final int TEXT_HELP_LINK = 1;
    public static final int TEXT_HELP_RECO = 13;
    public static final int TEXT_HELP_SMS = 12;
    public static final int TEXT_KNOW = 2;
    public static final int TEXT_MORE = 7;
    public static final int TEXT_PLAYBACK = 10;
    public static final int TEXT_POWER_LINK = 0;
    public static final int TEXT_RETRIEVE = 11;
    public static final int TEXT_RETRY = 8;
    public static final int TEXT_SET = 6;
    public static final int TEXT_STOP = 5;
    public static final int TITLE_AUDIO_PLAYING = 6;
    public static final int TITLE_AUDIO_REQUEST = 4;
    public static final int TITLE_CONNECTING = 1;
    public static final int TITLE_DATA_UPLOAD = 7;
    public static final int TITLE_ERROR = 5;
    public static final int TITLE_HELP = 0;
    public static final int TITLE_RECOGNIZE_WAITING = 3;
    public static final int TITLE_RECORDING = 2;
    private static Locale a = Locale.CHINA;

    private Resource() {
    }

    private static boolean a(String str) {
        return TextUtils.isEmpty(str) || Locale.CHINA.toString().equalsIgnoreCase(str) || Locale.CHINESE.toString().equalsIgnoreCase(str);
    }

    public static String getErrorDescription(int i) {
        String[] strArr = a.c;
        if (a.equals(Locale.US)) {
            strArr = b.c;
        } else if (a.equals(Locale.TRADITIONAL_CHINESE)) {
            strArr = c.c;
        }
        return (i <= 0 || i >= strArr.length) ? getErrorTag(1) : strArr[i];
    }

    public static String getErrorTag(int i) {
        String[] strArr = a.d;
        if (a.equals(Locale.US)) {
            strArr = b.d;
        } else if (a.equals(Locale.TRADITIONAL_CHINESE)) {
            strArr = c.d;
        }
        return (i < 0 || i >= strArr.length) ? "" : strArr[i];
    }

    public static String getLanguage() {
        return a.toString();
    }

    public static String getText(int i) {
        String[] strArr = a.a;
        if (a.equals(Locale.US)) {
            strArr = b.a;
        } else if (a.equals(Locale.TRADITIONAL_CHINESE)) {
            strArr = c.a;
        }
        return (i < 0 || i >= strArr.length) ? "" : strArr[i];
    }

    public static String getTitle(int i) {
        String[] strArr = a.b;
        if (a.equals(Locale.US)) {
            strArr = b.b;
        } else if (a.equals(Locale.TRADITIONAL_CHINESE)) {
            strArr = c.b;
        }
        return (i < 0 || i >= strArr.length) ? "" : strArr[i];
    }

    public static boolean matchLanguage(String str) {
        String str2 = "";
        if (str != null) {
            str2 = str.trim();
        }
        return a.toString().equalsIgnoreCase(str2) ? true : a(str2) && a(a.toString());
    }

    public static void setErrorDescription(int i, String str) {
        String[] strArr = a.c;
        if (a.equals(Locale.US)) {
            strArr = b.c;
        } else if (a.equals(Locale.TRADITIONAL_CHINESE)) {
            strArr = c.c;
        }
        if (i > 0 && i < strArr.length) {
            strArr[i] = str;
        }
    }

    public static void setText(int i, String str) {
        String[] strArr = a.a;
        if (a.equals(Locale.US)) {
            strArr = b.a;
        } else if (a.equals(Locale.TRADITIONAL_CHINESE)) {
            strArr = c.a;
        }
        if (i >= 0 && i < strArr.length) {
            strArr[i] = str;
        }
    }

    public static void setTitle(int i, String str) {
        String[] strArr = a.b;
        if (a.equals(Locale.US)) {
            strArr = b.b;
        } else if (a.equals(Locale.TRADITIONAL_CHINESE)) {
            strArr = c.b;
        }
        if (i >= 0 && i < strArr.length) {
            strArr[i] = str;
        }
    }

    public static void setUILanguage(Locale locale) {
        if (locale != null) {
            if (locale.equals(Locale.US) || locale.equals(Locale.CHINA) || locale.equals(Locale.TRADITIONAL_CHINESE)) {
                a = locale;
            }
        }
    }
}
