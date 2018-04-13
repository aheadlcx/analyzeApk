package qsbk.app.core.web.js;

import android.webkit.JavascriptInterface;

public interface IExposeApi {
    public static final String CALL_JS = "Interface.reqWeb";
    public static final String NATIVE_CALLBACK = "Interface.onNativeResp";
    public static final String PREFIX_JSPROMPT_CALL = "__native_call=>";

    void callByCallInfo(String str);

    @JavascriptInterface
    void onWebResp(String str, String str2);

    @JavascriptInterface
    void reqNative(String str, String str2, String str3, String str4);
}
