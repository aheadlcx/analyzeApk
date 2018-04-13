package com.sina.weibo.sdk.web;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import com.alipay.sdk.sys.a;
import com.baidu.mobads.interfaces.IXAdRequestInfo;
import com.sina.weibo.sdk.WeiboAppManager;
import com.sina.weibo.sdk.auth.AuthInfo;
import com.sina.weibo.sdk.auth.WbAppInfo;
import com.sina.weibo.sdk.constant.WBPageConstants.ParamKey;
import com.sina.weibo.sdk.web.param.DefaultWebViewRequestParam;
import java.util.HashMap;
import java.util.Map.Entry;
import org.eclipse.paho.client.mqttv3.MqttTopic;

public class WeiboPageUtils {
    private static WeiboPageUtils a;
    private WbAppInfo b = null;
    private AuthInfo c;
    private Context d;

    private WeiboPageUtils(Context context, AuthInfo authInfo) {
        this.b = WeiboAppManager.getInstance(context).getWbAppInfo();
        this.c = authInfo;
        this.d = context;
    }

    public static WeiboPageUtils getInstance(Context context, AuthInfo authInfo) {
        if (a == null) {
            a = new WeiboPageUtils(context, authInfo);
        }
        return a;
    }

    public void startUserMainPage(String str) {
        startUserMainPage(str, false);
    }

    public void startUserMainPage(String str, boolean z) {
        if (z || this.b == null || !this.b.isLegal()) {
            a(b("http://m.weibo.cn/u/" + str + "?", null));
            return;
        }
        HashMap hashMap = new HashMap();
        hashMap.put("uid", str);
        try {
            this.d.startActivity(a("sinaweibo://userinfo?", hashMap));
        } catch (Exception e) {
        }
    }

    public void startWeiboDetailPage(String str, String str2) {
        startWeiboDetailPage(str, str2, false);
    }

    public void startWeiboDetailPage(String str, String str2, boolean z) {
        if (z || this.b == null || !this.b.isLegal()) {
            a(b("http://m.weibo.cn/" + str2 + MqttTopic.TOPIC_LEVEL_SEPARATOR + str + "?", null));
            return;
        }
        HashMap hashMap = new HashMap();
        hashMap.put(ParamKey.MBLOGID, str);
        try {
            this.d.startActivity(a("sinaweibo://detail?", hashMap));
        } catch (Exception e) {
        }
    }

    public void startWeiboTopPage(String str) {
        startWeiboTopPage(str, false);
    }

    public void startWeiboTopPage(String str, boolean z) {
        if (z || this.b == null || !this.b.isLegal()) {
            new HashMap().put("id", str);
            a(b("http://media.weibo.cn/article?", null));
            return;
        }
        HashMap hashMap = new HashMap();
        hashMap.put("object_id", "1022:" + str);
        try {
            this.d.startActivity(a("sinaweibo://article?", hashMap));
        } catch (Exception e) {
        }
    }

    public void shareToWeibo(String str) {
        shareToWeibo(str, false);
    }

    public void shareToWeibo(String str, boolean z) {
        if (z || this.b == null || !this.b.isLegal()) {
            new HashMap().put("content", str);
            a(b("http://m.weibo.cn/mblog?", null));
            return;
        }
        HashMap hashMap = new HashMap();
        hashMap.put("content", str);
        try {
            this.d.startActivity(a("sinaweibo://sendweibo?", hashMap));
        } catch (Exception e) {
        }
    }

    public void commentWeibo(String str) {
        commentWeibo(str, false);
    }

    public void commentWeibo(String str, boolean z) {
        if (z || this.b == null || !this.b.isLegal()) {
            new HashMap().put("id", str);
            a(b("http://m.weibo.cn/comment?", null));
            return;
        }
        HashMap hashMap = new HashMap();
        hashMap.put("srcid", str);
        try {
            this.d.startActivity(a("sinaweibo://comment?", hashMap));
        } catch (Exception e) {
        }
    }

    public void openWeiboSearchPage(String str) {
        openWeiboSearchPage(str, false);
    }

    public void openWeiboSearchPage(String str, boolean z) {
        if (z || this.b == null || !this.b.isLegal()) {
            HashMap hashMap = new HashMap();
            hashMap.put(IXAdRequestInfo.COST_NAME, str);
            hashMap.put("type", "all");
            hashMap.put("containerid", "100103");
            a(b("http://m.weibo.cn/main/pages/index?", null));
            return;
        }
        hashMap = new HashMap();
        hashMap.put(IXAdRequestInfo.COST_NAME, str);
        try {
            this.d.startActivity(a("sinaweibo://searchall?", hashMap));
        } catch (Exception e) {
        }
    }

    public void gotoMyHomePage() {
        gotoMyHomePage(false);
    }

    public void gotoMyHomePage(boolean z) {
        if (z || this.b == null || !this.b.isLegal()) {
            new HashMap().put("cookie", "0_all");
            a(b("http://m.weibo.cn/index/router?", null));
            return;
        }
        try {
            this.d.startActivity(a("sinaweibo://gotohome?", null));
        } catch (Exception e) {
        }
    }

    public void gotoMyProfile() {
        gotoMyProfile(false);
    }

    public void gotoMyProfile(boolean z) {
        if (z || this.b == null || !this.b.isLegal()) {
            new HashMap().put("cookie", "3");
            a(b("http://m.weibo.cn/index/router?", null));
            return;
        }
        try {
            this.d.startActivity(a("sinaweibo://myprofile?", null));
        } catch (Exception e) {
        }
    }

    public void startOtherPage(String str) {
        startOtherPage(str, null);
    }

    public void startOtherPage(String str, HashMap<String, String> hashMap) {
        if (!TextUtils.isEmpty(str)) {
            a(b(str, hashMap));
        }
    }

    private Intent a(String str, HashMap<String, String> hashMap) {
        return new Intent("android.intent.action.VIEW", Uri.parse(b(str, hashMap)));
    }

    private String b(String str, HashMap<String, String> hashMap) {
        String str2 = str + "luicode=10000360&&lfid=OP_" + this.c.getAppKey();
        if (hashMap == null) {
            return str2;
        }
        String str3 = str2;
        for (Entry entry : hashMap.entrySet()) {
            str3 = str3 + a.b + entry.getKey().toString() + "=" + entry.getValue().toString();
        }
        return str3;
    }

    private void a(String str) {
        Intent intent = new Intent();
        intent.setClass(this.d, WeiboSdkWebActivity.class);
        DefaultWebViewRequestParam defaultWebViewRequestParam = new DefaultWebViewRequestParam(this.c, WebRequestType.DEFAULT, null, null, str, this.d);
        Bundle bundle = new Bundle();
        defaultWebViewRequestParam.fillBundle(bundle);
        intent.putExtras(bundle);
        this.d.startActivity(intent);
    }
}
