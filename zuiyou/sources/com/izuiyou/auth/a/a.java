package com.izuiyou.auth.a;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import cn.xiaochuan.base.BaseApplication;
import com.iflytek.cloud.SpeechConstant;
import com.tencent.connect.UserInfo;
import com.tencent.tauth.IUiListener;
import com.tencent.tauth.Tencent;
import java.util.ArrayList;

public class a {
    private static a a;
    private Tencent b = Tencent.createInstance("1103537147", com.izuiyou.auth.a.a());

    public static a a() {
        if (a == null) {
            a = new a();
        }
        return a;
    }

    private a() {
    }

    public void a(Activity activity, boolean z, String str, String str2, String str3, String str4, IUiListener iUiListener) {
        Bundle bundle = new Bundle();
        if (z) {
            bundle.putString("req_type", "1");
            bundle.putString("targetUrl", str3);
            bundle.putString("title", str);
            bundle.putString("summary", str2);
            ArrayList arrayList = new ArrayList();
            arrayList.add(str4);
            bundle.putStringArrayList("imageUrl", arrayList);
            this.b.shareToQzone(activity, bundle, iUiListener);
            return;
        }
        bundle.putString("targetUrl", str3);
        bundle.putString("title", str);
        bundle.putString("imageUrl", str4);
        bundle.putString("summary", str2);
        bundle.putInt("req_type", 1);
        this.b.shareToQQ(activity, bundle, iUiListener);
    }

    public void a(Activity activity, boolean z, String str, IUiListener iUiListener) {
        if (z) {
            iUiListener.onError(null);
            return;
        }
        Bundle bundle = new Bundle();
        bundle.putString("imageLocalUrl", str);
        bundle.putInt("req_type", 5);
        bundle.putInt("cflag", 0);
        this.b.shareToQQ(activity, bundle, iUiListener);
    }

    public void a(Activity activity, IUiListener iUiListener) {
        this.b.login(activity, "get_simple_userinfo", iUiListener);
    }

    public void a(IUiListener iUiListener) {
        new UserInfo(BaseApplication.getAppContext().getApplicationContext(), this.b.getQQToken()).getUserInfo(iUiListener);
    }

    public void b(Activity activity, IUiListener iUiListener) {
        this.b.reAuth(activity, SpeechConstant.PLUS_LOCAL_ALL, iUiListener);
    }

    public void a(int i, int i2, Intent intent) {
        this.b.onActivityResult(i, i2, intent);
    }

    public void a(int i, int i2, Intent intent, IUiListener iUiListener) {
        Tencent.onActivityResultData(i, i2, intent, iUiListener);
    }

    public void a(String str, String str2, String str3) {
        this.b.setOpenId(str);
        this.b.setAccessToken(str2, str3);
    }
}
