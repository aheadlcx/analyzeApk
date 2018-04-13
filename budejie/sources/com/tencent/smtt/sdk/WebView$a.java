package com.tencent.smtt.sdk;

import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.content.Context;
import android.graphics.Canvas;
import android.os.Build.VERSION;
import android.os.Handler;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.webkit.WebSettings;
import android.webkit.WebView;
import java.lang.reflect.Method;

class WebView$a extends WebView {
    final /* synthetic */ WebView a;

    public WebView$a(WebView webView, Context context) {
        this(webView, context, null);
    }

    public WebView$a(WebView webView, Context context, AttributeSet attributeSet) {
        this.a = webView;
        super(context, attributeSet);
        CookieSyncManager.createInstance(WebView.a(webView)).startSync();
        try {
            Method declaredMethod = Class.forName("android.webkit.WebViewWorker").getDeclaredMethod("getHandler", new Class[0]);
            declaredMethod.setAccessible(true);
            ((Handler) declaredMethod.invoke(null, new Object[0])).getLooper().getThread().setUncaughtExceptionHandler(new m());
            WebView.mSysWebviewCreated = true;
        } catch (Exception e) {
        }
    }

    public void a() {
        super.computeScroll();
    }

    public void a(int i, int i2, int i3, int i4) {
        super.onScrollChanged(i, i2, i3, i4);
    }

    @TargetApi(9)
    public void a(int i, int i2, boolean z, boolean z2) {
        if (VERSION.SDK_INT >= 9) {
            super.onOverScrolled(i, i2, z, z2);
        }
    }

    @TargetApi(9)
    public boolean a(int i, int i2, int i3, int i4, int i5, int i6, int i7, int i8, boolean z) {
        return VERSION.SDK_INT >= 9 ? super.overScrollBy(i, i2, i3, i4, i5, i6, i7, i8, z) : false;
    }

    public boolean a(MotionEvent motionEvent) {
        return super.onTouchEvent(motionEvent);
    }

    public boolean b(MotionEvent motionEvent) {
        return super.dispatchTouchEvent(motionEvent);
    }

    public boolean c(MotionEvent motionEvent) {
        return super.onInterceptTouchEvent(motionEvent);
    }

    public void computeScroll() {
        if (this.a.mWebViewCallbackClient != null) {
            this.a.mWebViewCallbackClient.computeScroll(this);
        } else {
            super.computeScroll();
        }
    }

    protected void dispatchDraw(Canvas canvas) {
        try {
            super.dispatchDraw(canvas);
            if (!WebView.d() && WebView.e() != null) {
                canvas.save();
                canvas.drawPaint(WebView.e());
                canvas.restore();
            }
        } catch (Throwable th) {
            th.printStackTrace();
        }
    }

    public boolean dispatchTouchEvent(MotionEvent motionEvent) {
        return this.a.mWebViewCallbackClient != null ? this.a.mWebViewCallbackClient.dispatchTouchEvent(motionEvent, this) : super.dispatchTouchEvent(motionEvent);
    }

    public WebSettings getSettings() {
        try {
            return super.getSettings();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public boolean onInterceptTouchEvent(MotionEvent motionEvent) {
        return this.a.mWebViewCallbackClient != null ? this.a.mWebViewCallbackClient.onInterceptTouchEvent(motionEvent, this) : super.onInterceptTouchEvent(motionEvent);
    }

    @TargetApi(9)
    public void onOverScrolled(int i, int i2, boolean z, boolean z2) {
        if (this.a.mWebViewCallbackClient != null) {
            this.a.mWebViewCallbackClient.onOverScrolled(i, i2, z, z2, this);
        } else if (VERSION.SDK_INT >= 9) {
            super.onOverScrolled(i, i2, z, z2);
        }
    }

    protected void onScrollChanged(int i, int i2, int i3, int i4) {
        if (this.a.mWebViewCallbackClient != null) {
            this.a.mWebViewCallbackClient.onScrollChanged(i, i2, i3, i4, this);
            return;
        }
        super.onScrollChanged(i, i2, i3, i4);
        WebView.b(this.a, i, i2, i3, i4);
    }

    @SuppressLint({"ClickableViewAccessibility"})
    public boolean onTouchEvent(MotionEvent motionEvent) {
        if (!hasFocus()) {
            requestFocus();
        }
        if (this.a.mWebViewCallbackClient != null) {
            return this.a.mWebViewCallbackClient.onTouchEvent(motionEvent, this);
        }
        try {
            return super.onTouchEvent(motionEvent);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @TargetApi(9)
    public boolean overScrollBy(int i, int i2, int i3, int i4, int i5, int i6, int i7, int i8, boolean z) {
        return this.a.mWebViewCallbackClient != null ? this.a.mWebViewCallbackClient.overScrollBy(i, i2, i3, i4, i5, i6, i7, i8, z, this) : VERSION.SDK_INT >= 9 ? super.overScrollBy(i, i2, i3, i4, i5, i6, i7, i8, z) : false;
    }
}
