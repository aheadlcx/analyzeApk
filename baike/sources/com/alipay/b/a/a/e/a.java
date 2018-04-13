package com.alipay.b.a.a.e;

import com.xiaomi.mipush.sdk.Constants;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public final class a {
    private String a;
    private String b;
    private String c;
    private String d;
    private String e;
    private String f;
    private String g;

    public a(String str, String str2, String str3, String str4, String str5, String str6, String str7) {
        this.a = str;
        this.b = str2;
        this.c = str3;
        this.d = str4;
        this.e = str5;
        this.f = str6;
        this.g = str7;
    }

    public final String toString() {
        StringBuffer stringBuffer = new StringBuffer(new SimpleDateFormat("yyyyMMddHHmmssSSS").format(Calendar.getInstance().getTime()));
        stringBuffer.append(new StringBuilder(Constants.ACCEPT_TIME_SEPARATOR_SP).append(this.a).toString());
        stringBuffer.append(new StringBuilder(Constants.ACCEPT_TIME_SEPARATOR_SP).append(this.b).toString());
        stringBuffer.append(new StringBuilder(Constants.ACCEPT_TIME_SEPARATOR_SP).append(this.c).toString());
        stringBuffer.append(new StringBuilder(Constants.ACCEPT_TIME_SEPARATOR_SP).append(this.d).toString());
        if (com.alipay.b.a.a.a.a.a(this.e) || this.e.length() < 20) {
            stringBuffer.append(new StringBuilder(Constants.ACCEPT_TIME_SEPARATOR_SP).append(this.e).toString());
        } else {
            stringBuffer.append(new StringBuilder(Constants.ACCEPT_TIME_SEPARATOR_SP).append(this.e.substring(0, 20)).toString());
        }
        if (com.alipay.b.a.a.a.a.a(this.f) || this.f.length() < 20) {
            stringBuffer.append(new StringBuilder(Constants.ACCEPT_TIME_SEPARATOR_SP).append(this.f).toString());
        } else {
            stringBuffer.append(new StringBuilder(Constants.ACCEPT_TIME_SEPARATOR_SP).append(this.f.substring(0, 20)).toString());
        }
        if (com.alipay.b.a.a.a.a.a(this.g) || this.g.length() < 20) {
            stringBuffer.append(new StringBuilder(Constants.ACCEPT_TIME_SEPARATOR_SP).append(this.g).toString());
        } else {
            stringBuffer.append(new StringBuilder(Constants.ACCEPT_TIME_SEPARATOR_SP).append(this.g.substring(0, 20)).toString());
        }
        return stringBuffer.toString();
    }
}
