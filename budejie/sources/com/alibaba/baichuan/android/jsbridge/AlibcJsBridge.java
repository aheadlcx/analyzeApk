package com.alibaba.baichuan.android.jsbridge;

import android.os.Handler;
import android.os.Handler.Callback;
import android.os.Looper;
import android.os.Message;
import android.text.TextUtils;
import com.alibaba.baichuan.android.jsbridge.AlibcJsBridgeService.AlibcJSAPIAuthCheck;
import com.alibaba.baichuan.android.jsbridge.plugin.AlibcApiPlugin;
import com.alibaba.baichuan.android.jsbridge.plugin.AlibcPluginEntryManager;
import com.alibaba.baichuan.android.trade.utils.log.AlibcLogger;

public class AlibcJsBridge implements Callback {
    public static final int CALL_EXECUTE = 0;
    public static final int CLOSED = 4;
    public static final int NO_METHOD = 2;
    public static final int NO_PERMISSION = 3;
    private static AlibcJsBridge a;
    private static Handler b;
    private boolean c = true;
    private boolean d;
    private boolean e = false;

    private AlibcJsBridge() {
        b = new Handler(Looper.getMainLooper(), this);
    }

    private AlibcJsCallMethodContext a(String str) {
        if (str == null || !str.startsWith("bchybrid://")) {
            return null;
        }
        try {
            AlibcJsCallMethodContext alibcJsCallMethodContext = new AlibcJsCallMethodContext();
            int indexOf = str.indexOf(58, "bchybrid://".length());
            alibcJsCallMethodContext.objectName = str.substring("bchybrid://".length(), indexOf);
            int indexOf2 = str.indexOf(47, indexOf);
            alibcJsCallMethodContext.token = str.substring(indexOf + 1, indexOf2);
            indexOf = str.indexOf(63, indexOf2);
            if (indexOf > 0) {
                alibcJsCallMethodContext.methodName = str.substring(indexOf2 + 1, indexOf);
                alibcJsCallMethodContext.params = str.substring(indexOf + 1);
            } else {
                alibcJsCallMethodContext.methodName = str.substring(indexOf2 + 1);
            }
            if (alibcJsCallMethodContext.objectName.length() > 0 && alibcJsCallMethodContext.token.length() > 0 && alibcJsCallMethodContext.methodName.length() > 0) {
                return alibcJsCallMethodContext;
            }
        } catch (StringIndexOutOfBoundsException e) {
        }
        return null;
    }

    private void a(AlibcJsCallMethodContext alibcJsCallMethodContext, String str) {
        AlibcLogger.d("AlibcJsBridge", String.format("callMethod-obj:%s method:%s param:%s sid:%s", new Object[]{alibcJsCallMethodContext.objectName, alibcJsCallMethodContext.methodName, alibcJsCallMethodContext.params, alibcJsCallMethodContext.token}));
        if (!this.c || alibcJsCallMethodContext.webview == null) {
            AlibcLogger.w("AlibcJsBridge", "jsbridge is closed.");
            startCall(4, alibcJsCallMethodContext);
            return;
        }
        if (!(this.d || AlibcJsBridgeService.getJSBridgePreprocessors() == null || AlibcJsBridgeService.getJSBridgePreprocessors().isEmpty())) {
            for (AlibcJSAPIAuthCheck apiAuthCheck : AlibcJsBridgeService.getJSBridgePreprocessors()) {
                if (!apiAuthCheck.apiAuthCheck(str, alibcJsCallMethodContext.objectName, alibcJsCallMethodContext.methodName, alibcJsCallMethodContext.params)) {
                    AlibcLogger.w("AlibcJsBridge", "preprocessor call fail, callMethod cancel.");
                    startCall(3, alibcJsCallMethodContext);
                    return;
                }
            }
        }
        aftercallMethod(alibcJsCallMethodContext, str);
    }

    public static void aftercallMethod(AlibcJsCallMethodContext alibcJsCallMethodContext, String str) {
        Object jsObject = alibcJsCallMethodContext.webview.getJsObject(alibcJsCallMethodContext.objectName);
        if (jsObject == null || !(jsObject instanceof AlibcApiPlugin)) {
            AlibcLogger.w("AlibcJsBridge", "callMethod: Plugin " + alibcJsCallMethodContext.objectName + " didn't found, you should call WVPluginManager.registerPlugin first.");
            startCall(2, alibcJsCallMethodContext);
            return;
        }
        AlibcLogger.i("AlibcJsBridge", "call new method execute.");
        alibcJsCallMethodContext.classinstance = jsObject;
        startCall(0, alibcJsCallMethodContext);
    }

    public static synchronized AlibcJsBridge getInstance() {
        AlibcJsBridge alibcJsBridge;
        synchronized (AlibcJsBridge.class) {
            if (a == null) {
                a = new AlibcJsBridge();
            }
            alibcJsBridge = a;
        }
        return alibcJsBridge;
    }

    public static void startCall(int i, AlibcJsCallMethodContext alibcJsCallMethodContext) {
        Message obtain = Message.obtain();
        obtain.what = i;
        obtain.obj = alibcJsCallMethodContext;
        b.sendMessage(obtain);
    }

    public void callMethod(AlibcWebview alibcWebview, String str) {
        AlibcLogger.d("AlibcJsBridge", "callMethod: url=" + str);
        if (this.e) {
            AlibcJsCallMethodContext a = a(str);
            if (a == null) {
                AlibcLogger.w("AlibcJsBridge", "url format error and call canceled. url=" + str);
                return;
            }
            a.webview = alibcWebview;
            new a(this, a, alibcWebview.getWebView().getUrl()).execute(new Void[0]);
            return;
        }
        AlibcLogger.w("AlibcJsBridge", "jsbridge is not init.");
    }

    public void destroy() {
        this.e = false;
    }

    public void exCallMethod(AlibcPluginEntryManager alibcPluginEntryManager, AlibcJsCallMethodContext alibcJsCallMethodContext) {
        if (alibcJsCallMethodContext != null && alibcJsCallMethodContext.objectName != null) {
            alibcJsCallMethodContext.classinstance = alibcPluginEntryManager.getEntry(alibcJsCallMethodContext.objectName);
            if (alibcJsCallMethodContext.classinstance instanceof AlibcApiPlugin) {
                AlibcLogger.i("AlibcJsBridge", "call new method execute.");
                startCall(0, alibcJsCallMethodContext);
            }
        }
    }

    public boolean handleMessage(Message message) {
        AlibcJsCallMethodContext alibcJsCallMethodContext = (AlibcJsCallMethodContext) message.obj;
        if (alibcJsCallMethodContext == null) {
            AlibcLogger.e("AlibcJsBridge", "CallMethodContext is null, and do nothing.");
            return false;
        }
        AlibcJsCallbackContext alibcJsCallbackContext = new AlibcJsCallbackContext(alibcJsCallMethodContext.webview, alibcJsCallMethodContext.token, alibcJsCallMethodContext.objectName, alibcJsCallMethodContext.methodName);
        switch (message.what) {
            case 0:
                if (!((AlibcApiPlugin) alibcJsCallMethodContext.classinstance).execute(alibcJsCallMethodContext.methodName, TextUtils.isEmpty(alibcJsCallMethodContext.params) ? "{}" : alibcJsCallMethodContext.params, alibcJsCallbackContext)) {
                    AlibcLogger.w("AlibcJsBridge", "AlibcApiPlugin execute failed. method: " + alibcJsCallMethodContext.methodName);
                    startCall(2, alibcJsCallMethodContext);
                }
                return true;
            case 2:
                alibcJsCallbackContext.error(AlibcJsResult.RET_NO_METHOD);
                return true;
            case 3:
                alibcJsCallbackContext.error(AlibcJsResult.RET_NO_PERMISSION);
                return true;
            case 4:
                alibcJsCallbackContext.error(AlibcJsResult.RET_CLOSED);
                return true;
            default:
                return false;
        }
    }

    public synchronized void init() {
        this.e = true;
    }

    public void setEnabled(boolean z) {
        this.c = z;
    }

    public void skipPreprocess() {
        this.d = true;
    }
}
