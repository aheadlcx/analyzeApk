package com.ali.auth.third.ui.webview;

import android.net.Uri;
import android.os.Build.VERSION;
import android.text.TextUtils;
import android.webkit.JsPromptResult;
import android.webkit.JsResult;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import com.ali.auth.third.core.message.Message;
import com.ali.auth.third.core.message.MessageUtils;
import com.ali.auth.third.core.model.KernelMessageConstants;
import com.ali.auth.third.core.model.SystemMessageConstants;
import com.ali.auth.third.core.trace.SDKLogger;
import com.ali.auth.third.ui.context.BridgeCallbackContext;
import com.tencent.stat.DeviceInfo;
import java.lang.reflect.Method;

public class BridgeWebChromeClient extends WebChromeClient {
    private static final String TAG = BridgeWebChromeClient.class.getSimpleName();
    private static boolean evaluateJavascriptSupported = (VERSION.SDK_INT >= 19);

    public final boolean onJsPrompt(WebView webView, String str, String str2, String str3, JsPromptResult jsPromptResult) {
        Message createMessage;
        if (str3 == null) {
            return false;
        }
        if (str3.equals("wv_hybrid:")) {
            handleWindVaneNoHandler(webView, str2);
            jsPromptResult.confirm("");
            return true;
        } else if (!TextUtils.equals(str3, "hv_hybrid:")) {
            return false;
        } else {
            if (!(webView instanceof AuthWebView)) {
                return false;
            }
            AuthWebView authWebView = (AuthWebView) webView;
            HavanaBridgeProtocal parseMessage = parseMessage(str2);
            BridgeCallbackContext bridgeCallbackContext = new BridgeCallbackContext();
            bridgeCallbackContext.requestId = parseMessage.requestId;
            bridgeCallbackContext.webView = authWebView;
            Object bridgeObj = authWebView.getBridgeObj(parseMessage.objName);
            Message createMessage2;
            if (bridgeObj == null) {
                createMessage2 = MessageUtils.createMessage(10000, new Object[]{parseMessage.objName});
                SDKLogger.e(TAG, createMessage2.toString());
                bridgeCallbackContext.onFailure(createMessage2.code, createMessage2.message);
                jsPromptResult.confirm("");
                return true;
            }
            try {
                Method method = bridgeObj.getClass().getMethod(parseMessage.methodName, new Class[]{BridgeCallbackContext.class, String.class});
                if (method.isAnnotationPresent(BridgeMethod.class)) {
                    try {
                        String str4;
                        Object[] objArr = new Object[2];
                        objArr[0] = bridgeCallbackContext;
                        if (TextUtils.isEmpty(parseMessage.param)) {
                            str4 = "{}";
                        } else {
                            str4 = parseMessage.param;
                        }
                        objArr[1] = str4;
                        method.invoke(bridgeObj, objArr);
                    } catch (Exception e) {
                        createMessage = MessageUtils.createMessage(KernelMessageConstants.GENERIC_SYSTEM_ERROR, new Object[]{e.getMessage()});
                        SDKLogger.e(TAG, createMessage.toString() + "," + e.toString());
                        bridgeCallbackContext.onFailure(createMessage.code, createMessage.message);
                    }
                } else {
                    createMessage2 = MessageUtils.createMessage(SystemMessageConstants.JS_BRIDGE_ANNOTATION_NOT_PRESENT, new Object[]{parseMessage.objName, parseMessage.methodName});
                    SDKLogger.e(TAG, createMessage2.toString());
                    bridgeCallbackContext.onFailure(createMessage2.code, createMessage2.message);
                }
                jsPromptResult.confirm("");
                return true;
            } catch (NoSuchMethodException e2) {
                createMessage = MessageUtils.createMessage(SystemMessageConstants.JS_BRIDGE_METHOD_NOT_FOUND, new Object[]{parseMessage.objName, parseMessage.methodName});
                SDKLogger.e(TAG, createMessage + "," + e2.toString());
                bridgeCallbackContext.onFailure(createMessage.code, createMessage.message);
                jsPromptResult.confirm("");
                return true;
            }
        }
    }

    public boolean onJsAlert(WebView webView, String str, String str2, JsResult jsResult) {
        return false;
    }

    private HavanaBridgeProtocal parseMessage(String str) {
        String str2;
        Uri parse = Uri.parse(str);
        String host = parse.getHost();
        int port = parse.getPort();
        String lastPathSegment = parse.getLastPathSegment();
        parse.getQuery();
        int indexOf = str.indexOf("?");
        if (indexOf == -1) {
            str2 = null;
        } else {
            str2 = str.substring(indexOf + 1);
        }
        HavanaBridgeProtocal havanaBridgeProtocal = new HavanaBridgeProtocal();
        havanaBridgeProtocal.methodName = lastPathSegment;
        havanaBridgeProtocal.objName = host;
        havanaBridgeProtocal.param = str2;
        havanaBridgeProtocal.requestId = port;
        return havanaBridgeProtocal;
    }

    private void handleWindVaneNoHandler(WebView webView, String str) {
        try {
            int indexOf = str.indexOf(58, 9);
            String substring = str.substring(indexOf + 1, str.indexOf(47, indexOf));
            webView.post(new BridgeWebChromeClient$JavaScriptTask(webView, String.format("window.WindVane&&window.WindVane.onFailure(%s,'{\"ret\":\"HY_NO_HANDLER\"');", new Object[]{substring})));
        } catch (Throwable e) {
            SDKLogger.e(DeviceInfo.TAG_IMEI, "fail to handler windvane request, the error message is " + e.getMessage(), e);
        }
    }
}
