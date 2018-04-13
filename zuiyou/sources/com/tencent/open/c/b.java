package com.tencent.open.c;

import android.content.Context;
import android.webkit.WebSettings;
import android.webkit.WebView;
import java.lang.reflect.Method;

public class b extends WebView {
    public b(Context context) {
        super(context);
    }

    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        WebSettings settings = getSettings();
        if (settings != null) {
            try {
                Method method = settings.getClass().getMethod("removeJavascriptInterface", new Class[]{String.class});
                if (method != null) {
                    method.invoke(this, new Object[]{"searchBoxJavaBridge_"});
                }
            } catch (Exception e) {
            }
        }
    }
}
