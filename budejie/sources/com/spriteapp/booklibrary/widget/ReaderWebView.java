package com.spriteapp.booklibrary.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import com.alibaba.wireless.security.open.nocaptcha.INoCaptchaComponent;
import com.baidu.mobads.interfaces.IXAdRequestInfo;
import com.spriteapp.booklibrary.config.HuaXiSDK;
import com.spriteapp.booklibrary.util.AppUtil;
import com.spriteapp.booklibrary.util.SharedPreferencesUtil;
import com.spriteapp.booklibrary.util.SignUtil;
import com.tencent.open.GameAppOperation;
import java.util.HashMap;
import java.util.Map;

public class ReaderWebView extends WebView {
    private WebViewClient mClient;

    public ReaderWebView(Context context) {
        super(context);
        init();
    }

    public ReaderWebView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        init();
    }

    public ReaderWebView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        init();
    }

    private void init() {
        WebSettings settings = getSettings();
        settings.setJavaScriptEnabled(true);
        settings.setDomStorageEnabled(true);
        settings.setDisplayZoomControls(false);
    }

    public void setClient(WebViewClient webViewClient) {
        this.mClient = new ReaderWebView$1(this, webViewClient);
        setWebViewClient(this.mClient);
    }

    public void loadPage(String str) {
        loadPage(str, null);
    }

    public void loadPage(String str, WebViewClient webViewClient) {
        setClient(webViewClient);
        Map hashMap = new HashMap();
        hashMap.put("client-id", HuaXiSDK.getInstance().getClientId());
        hashMap.put("timestamp", String.valueOf(SignUtil.getCurrentTime()));
        hashMap.put(GameAppOperation.QQFAV_DATALINE_VERSION, "1.0");
        hashMap.put("sign", SignUtil.createSign("1.0"));
        hashMap.put(IXAdRequestInfo.SN, AppUtil.getHeaderSnValue());
        hashMap.put(INoCaptchaComponent.token, SharedPreferencesUtil.getInstance().getString("hua_xi_token"));
        loadUrl(str, hashMap);
    }
}
