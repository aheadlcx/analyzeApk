package com.tencent.open;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.webkit.WebChromeClient;

public abstract class b extends Dialog {
    protected a c;
    @SuppressLint({"NewApi"})
    protected final WebChromeClient d = new d(this);

    protected abstract void a(String str);

    public b(Context context, int i) {
        super(context, i);
    }

    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        this.c = new a();
    }
}
