package com.budejie.www.activity.view;

import android.annotation.SuppressLint;
import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import cn.v6.sixrooms.socket.common.SocketUtil;
import com.budejie.www.R$styleable;
import com.tencent.smtt.sdk.WebSettings;
import com.tencent.smtt.sdk.WebView;
import java.lang.reflect.Method;

@SuppressLint({"NewApi"})
public class CustomWebView extends WebView {
    private static boolean e = false;
    private static Method f = null;
    private static Method g = null;
    private static Method h = null;
    private static Method i = null;
    private int b = 100;
    private boolean c = false;
    private String d;

    public CustomWebView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        g();
    }

    public void g() {
        WebSettings settings = getSettings();
        settings.setJavaScriptEnabled(true);
        settings.setUseWideViewPort(true);
        settings.setLoadWithOverviewMode(true);
        setLongClickable(true);
        setScrollbarFadingEnabled(true);
        setScrollBarStyle(0);
        setDrawingCacheEnabled(true);
        settings.setAppCacheEnabled(true);
        settings.setDatabaseEnabled(true);
        settings.setDomStorageEnabled(true);
    }

    public boolean onTouchEvent(MotionEvent motionEvent) {
        int action = motionEvent.getAction();
        if (action == 0 || action == 5 || action == 5 || action == R$styleable.Theme_Custom_posts_detail_forward_icon || action == 517) {
            if (motionEvent.getPointerCount() > 1) {
                getSettings().setBuiltInZoomControls(true);
                getSettings().setSupportZoom(true);
            } else {
                getSettings().setBuiltInZoomControls(false);
                getSettings().setSupportZoom(false);
            }
        } else if (action == 1 || action == 6 || action == 6 || action == R$styleable.Theme_Custom_commend_icon || action == SocketUtil.TYPEID_518) {
            getSettings().setBuiltInZoomControls(false);
            getSettings().setSupportZoom(false);
        }
        return super.onTouchEvent(motionEvent);
    }

    public void loadUrl(String str) {
        this.d = str;
        super.loadUrl(str);
    }

    public void setProgress(int i) {
        this.b = i;
    }

    public int getProgress() {
        return this.b;
    }

    public String getLoadedUrl() {
        return this.d;
    }
}
