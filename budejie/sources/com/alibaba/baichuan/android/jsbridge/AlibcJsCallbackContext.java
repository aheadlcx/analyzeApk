package com.alibaba.baichuan.android.jsbridge;

import android.annotation.TargetApi;
import android.os.Build.VERSION;
import android.text.TextUtils;
import android.webkit.ValueCallback;
import android.webkit.WebView;
import com.alibaba.baichuan.android.trade.AlibcContext;
import com.alibaba.baichuan.android.trade.utils.log.AlibcLogger;
import org.json.JSONException;
import org.json.JSONObject;

public class AlibcJsCallbackContext {
    private static boolean g = (VERSION.SDK_INT >= 19);
    AlibcWebview a;
    String b;
    String c;
    String d;
    boolean e = false;
    String f = null;

    public AlibcJsCallbackContext(AlibcWebview alibcWebview) {
        this.a = alibcWebview;
    }

    public AlibcJsCallbackContext(AlibcWebview alibcWebview, String str, String str2, String str3) {
        this.a = alibcWebview;
        this.b = str;
        this.c = str2;
        this.d = str3;
    }

    private static String a(String str) {
        if (str.contains(" ")) {
            try {
                str = str.replace(" ", "\\u2028");
            } catch (Exception e) {
            }
        }
        if (str.contains(" ")) {
            try {
                str = str.replace(" ", "\\u2029");
            } catch (Exception e2) {
            }
        }
        return str.replace("\\", "\\\\").replace("'", "\\'");
    }

    private static void a(AlibcWebview alibcWebview, String str, String str2) {
        if (AlibcContext.isDebuggable() && !TextUtils.isEmpty(str2)) {
            try {
                JSONObject jSONObject = new JSONObject(str2);
            } catch (JSONException e) {
                AlibcLogger.e("AlibcJsCallbackContext", "return param is not a valid json!\n" + str + "\n" + str2);
            }
        }
        if (TextUtils.isEmpty(str2)) {
            str2 = "{}";
        }
        try {
            alibcWebview.getWebView().post(new b(alibcWebview, String.format(str, new Object[]{a(str2)})));
        } catch (Exception e2) {
            AlibcLogger.e("AlibcJsCallbackContext", "callback error. " + e2.getMessage());
        }
    }

    public static void evaluateJavascript(WebView webView, String str) {
        evaluateJavascript(webView, str, null);
    }

    @TargetApi(19)
    public static void evaluateJavascript(WebView webView, String str, ValueCallback valueCallback) {
        if (webView != null) {
            if (str != null && str.length() > 10 && "javascript:".equals(str.substring(0, 11).toLowerCase())) {
                str = str.substring(11);
            }
            if (g) {
                try {
                    webView.evaluateJavascript(str, valueCallback);
                } catch (NoSuchMethodError e) {
                    g = false;
                    evaluateJavascript(webView, str, valueCallback);
                } catch (IllegalStateException e2) {
                    g = false;
                    evaluateJavascript(webView, str, valueCallback);
                } catch (Throwable th) {
                    AlibcLogger.e("Alibc", "evaluateJavascript", th);
                }
            } else if (valueCallback == null) {
                try {
                    webView.loadUrl("javascript:" + str);
                } catch (Throwable th2) {
                    AlibcLogger.e("Alibc", "evaluateJavascript", th2);
                }
            }
        }
    }

    public static void fireEvent(AlibcWebview alibcWebview, String str, String str2) {
        a(alibcWebview, String.format("window.AliBCBridge && window.AliBCBridge.fireEvent('%s', '%%s', %s);", new Object[]{str, null}), str2);
    }

    public void error(AlibcJsResult alibcJsResult) {
        AlibcLogger.d("AlibcJsCallbackContext", "call error ");
        a(this.a, String.format("javascript:window.AliBCBridge&&window.AliBCBridge.onFailure(%s,'%%s');", new Object[]{this.b}), alibcJsResult.toJsonString());
    }

    public void fireEvent(String str, String str2) {
        a(this.a, String.format("window.AliBCBridge && window.AliBCBridge.fireEvent('%s', '%%s', %s);", new Object[]{str, this.b}), str2);
    }

    public WebView getWebview() {
        return this.a.getWebView();
    }

    public void setNeedfireNativeEvent(String str, boolean z) {
        this.f = str;
        this.e = z;
        AlibcLogger.e("AlibcJsCallbackContext", "setNeedfireNativeEvent : " + str);
    }

    public void success(AlibcJsResult alibcJsResult) {
        a(this.a, String.format("javascript:window.AliBCBridge&&window.AliBCBridge.onSuccess(%s,'%%s');", new Object[]{this.b}), alibcJsResult.toJsonString());
    }
}
