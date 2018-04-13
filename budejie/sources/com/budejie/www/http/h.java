package com.budejie.www.http;

import android.text.TextUtils;

public class h {
    private StringBuilder a = new StringBuilder();

    public h(String str) {
        this.a.append(str);
    }

    public h a(String str) {
        this.a.append(str);
        return this;
    }

    public h a() {
        this.a.append(".json");
        return this;
    }

    public h a(long j) {
        return b(String.valueOf(j));
    }

    public h b(String str) {
        if (TextUtils.isEmpty(str)) {
            str = "0";
        }
        return a(str, "20");
    }

    public h a(String str, String str2) {
        b();
        c(str, str2);
        return this;
    }

    public h c(String str) {
        if (TextUtils.isEmpty(str)) {
            str = "0";
        }
        return b(str, "20");
    }

    public h b(String str, String str2) {
        d();
        c(str, str2);
        return this;
    }

    public h c(String str, String str2) {
        e().append(str).append("-").append(str2);
        return this;
    }

    public h a(int i) {
        e().append(i);
        return this;
    }

    public h d(String str) {
        e().append(str);
        return this;
    }

    public h b() {
        e().append("budejie-android-").append("6.9.2");
        return this;
    }

    public h c() {
        e().append("xiaomi");
        return this;
    }

    public h d() {
        a("budejie-android-").a("6.9.2");
        return this;
    }

    public StringBuilder e() {
        return this.a.append("/");
    }

    public String toString() {
        return this.a.toString();
    }
}
