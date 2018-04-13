package com.ali.auth.third.core.cookies;

import com.ali.auth.third.login.LoginConstants;
import java.text.SimpleDateFormat;
import java.util.Locale;
import java.util.TimeZone;

public class LoginCookie {
    public String domain;
    public long expires;
    public boolean httpOnly;
    public String name;
    public String path;
    public boolean secure;
    public String value;

    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(this.name);
        stringBuilder.append(LoginConstants.EQUAL);
        stringBuilder.append(this.value);
        stringBuilder.append("; ");
        stringBuilder.append("Domain=");
        stringBuilder.append(this.domain);
        if (this.expires > 0) {
            stringBuilder.append("; ");
            stringBuilder.append("Expires=");
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("EEE, dd-MMM-yyyy HH:mm:ss 'GMT'", Locale.ENGLISH);
            simpleDateFormat.setTimeZone(TimeZone.getTimeZone("GMT+8"));
            stringBuilder.append(simpleDateFormat.format(Long.valueOf(this.expires)));
        }
        stringBuilder.append("; ");
        stringBuilder.append("Path=");
        stringBuilder.append(this.path);
        if (this.secure) {
            stringBuilder.append("; ");
            stringBuilder.append("Secure");
        }
        if (this.httpOnly) {
            stringBuilder.append("; ");
            stringBuilder.append("HttpOnly");
        }
        return stringBuilder.toString();
    }
}
