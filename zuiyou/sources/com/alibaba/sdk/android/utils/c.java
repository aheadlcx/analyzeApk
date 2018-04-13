package com.alibaba.sdk.android.utils;

import android.util.Log;

public class c {
    private String TAG;
    private boolean b = false;

    public c(String str) {
        if (!d.a(str)) {
            this.TAG = str;
        }
    }

    public void setLogEnabled(boolean z) {
        this.b = z;
    }

    public void a(String str) {
        if (this.b) {
            Log.d(this.TAG, str);
        }
    }

    public void b(String str) {
        if (this.b) {
            Log.i(this.TAG, str);
        }
    }

    public void c(String str) {
        if (this.b) {
            Log.e(this.TAG, str);
        }
    }

    public void a(Throwable th) {
        if (this.b && th != null) {
            Log.e(this.TAG, th.toString(), th);
        }
    }
}
