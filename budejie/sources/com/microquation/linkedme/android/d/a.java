package com.microquation.linkedme.android.d;

import android.annotation.SuppressLint;
import android.content.Context;
import android.util.Log;
import com.microquation.linkedme.android.f.b;

public class a implements b {
    private static final String a = com.microquation.linkedme.android.a.a;
    private int b;
    private b c;

    public a(int i) {
        this.b = i;
    }

    public a(Context context) {
        this(4);
        this.c = b.a(context);
    }

    public void a(String str) {
        a(a, str);
    }

    public void a(String str, String str2) {
        a(str, str2, null);
    }

    @SuppressLint({"LogTagMismatch"})
    public void a(String str, String str2, Throwable th) {
        if (a(3)) {
            Log.d(str, str2, th);
        }
    }

    public boolean a(int i) {
        return this.b <= i && this.c.z();
    }

    public void b(String str) {
        b(a, str);
    }

    public void b(String str, String str2) {
        b(str, str2, null);
    }

    @SuppressLint({"LogTagMismatch"})
    public void b(String str, String str2, Throwable th) {
        if (a(4)) {
            Log.i(str, str2, th);
        }
    }

    public void c(String str) {
        c(a, str);
    }

    public void c(String str, String str2) {
        c(str, str2, null);
    }

    @SuppressLint({"LogTagMismatch"})
    public void c(String str, String str2, Throwable th) {
        if (a(5)) {
            Log.w(str, str2, th);
        }
    }
}
