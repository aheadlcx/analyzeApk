package com.ali.auth.third.ui.context;

import android.app.Activity;
import android.text.TextUtils;
import android.webkit.WebView;
import com.ali.auth.third.core.context.KernelContext;
import com.baidu.mobads.openad.c.b;
import mtopsdk.mtop.antiattack.CheckCodeDO;
import org.json.JSONObject;

public class BridgeCallbackContext {
    public int requestId;
    public WebView webView;

    public Activity getActivity() {
        return (Activity) this.webView.getContext();
    }

    public void success(final String str) {
        KernelContext.executorService.postUITask(new Runnable() {
            public void run() {
                String format;
                if (TextUtils.isEmpty(str)) {
                    format = String.format("javascript:window.HavanaBridge.onSuccess(%s);", new Object[]{Integer.valueOf(BridgeCallbackContext.this.requestId)});
                } else {
                    format = String.format("javascript:window.HavanaBridge.onSuccess(%s,'%s');", new Object[]{Integer.valueOf(BridgeCallbackContext.this.requestId), BridgeCallbackContext.formatJsonString(str)});
                }
                BridgeCallbackContext.this.callback(format);
            }
        });
    }

    public void onFailure(int i, String str) {
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put(CheckCodeDO.CHECKCODE_USER_INPUT_KEY, i);
            jSONObject.put(b.EVENT_MESSAGE, str);
            onFailure(jSONObject.toString());
        } catch (Throwable e) {
            throw new RuntimeException(e);
        }
    }

    public void onFailure(final String str) {
        KernelContext.executorService.postUITask(new Runnable() {
            public void run() {
                String format;
                if (TextUtils.isEmpty(str)) {
                    format = String.format("javascript:window.HavanaBridge.onFailure(%s,'');", new Object[]{Integer.valueOf(BridgeCallbackContext.this.requestId)});
                } else {
                    format = String.format("javascript:window.HavanaBridge.onFailure(%s,'%s');", new Object[]{Integer.valueOf(BridgeCallbackContext.this.requestId), BridgeCallbackContext.formatJsonString(str)});
                }
                BridgeCallbackContext.this.callback(format);
            }
        });
    }

    private void callback(String str) {
        if (this.webView != null) {
            this.webView.loadUrl(str);
        }
    }

    private static String formatJsonString(String str) {
        return str.replace("\\", "\\\\");
    }
}
