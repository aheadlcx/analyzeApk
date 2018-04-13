package com.sina.weibo.sdk.auth;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import com.sina.weibo.sdk.a.a;
import com.sina.weibo.sdk.a.d;
import com.sina.weibo.sdk.a.f;
import com.sina.weibo.sdk.a.h;
import com.sina.weibo.sdk.a.i;
import com.sina.weibo.sdk.a.j;
import com.sina.weibo.sdk.b;
import com.sina.weibo.sdk.c;
import com.sina.weibo.sdk.net.e;
import com.sina.weibo.sdk.web.WebRequestType;
import com.sina.weibo.sdk.web.WeiboSdkWebActivity;
import com.tencent.connect.common.Constants;
import com.tencent.open.GameAppOperation;
import com.tencent.stat.DeviceInfo;
import com.umeng.analytics.pro.x;
import mtopsdk.mtop.antiattack.CheckCodeDO;

public class BaseSsoHandler {
    protected Activity a;
    protected d b;
    protected final int c = 3;
    protected int d = -1;
    protected int e = 3;

    public BaseSsoHandler(Activity activity) {
        this.a = activity;
        a.a(this.a).a(b.b().getAppKey());
    }

    public void a(d dVar) {
        a(32973, dVar, BaseSsoHandler$AuthType.ALL);
    }

    private void a(int i, d dVar, BaseSsoHandler$AuthType baseSsoHandler$AuthType) {
        a();
        if (dVar == null) {
            throw new RuntimeException("please set auth listener");
        }
        this.b = dVar;
        if (baseSsoHandler$AuthType != BaseSsoHandler$AuthType.WebOnly) {
            Object obj = null;
            if (baseSsoHandler$AuthType == BaseSsoHandler$AuthType.SsoOnly) {
                obj = 1;
            }
            if (c()) {
                a(i);
            } else if (obj != null) {
                this.b.onFailure(new e());
            } else {
                b();
            }
        } else if (dVar != null) {
            b();
        }
    }

    protected void a(int i) {
        try {
            c a = c.a(this.a).a();
            Intent intent = new Intent();
            intent.setClassName(a.a(), a.b());
            intent.putExtras(b.b().getAuthBundle());
            intent.putExtra("_weibo_command_type", 3);
            intent.putExtra("_weibo_transaction", String.valueOf(System.currentTimeMillis()));
            intent.putExtra(DeviceInfo.TAG_ANDROID_ID, j.b(this.a, b.b().getAppKey()));
            if (h.a(this.a, intent)) {
                a(intent, i);
                try {
                    this.a.startActivityForResult(intent, this.d);
                } catch (Exception e) {
                    if (this.b != null) {
                        this.b.onFailure(new e());
                    }
                    d();
                }
            }
        } catch (Exception e2) {
        }
    }

    protected void a(Intent intent, int i) {
    }

    protected void a() {
        this.d = 32973;
    }

    protected void b() {
        AuthInfo b = b.b();
        e eVar = new e(b.getAppKey());
        eVar.a(Constants.PARAM_CLIENT_ID, b.getAppKey());
        eVar.a("redirect_uri", b.getRedirectUrl());
        eVar.a(Constants.PARAM_SCOPE, b.getScope());
        eVar.a("response_type", CheckCodeDO.CHECKCODE_USER_INPUT_KEY);
        eVar.a(GameAppOperation.QQFAV_DATALINE_VERSION, "0041005000");
        eVar.a("luicode", "10000360");
        b a = a.a(this.a);
        if (!(a == null || TextUtils.isEmpty(a.c()))) {
            eVar.a("trans_token", a.c());
            eVar.a("trans_access_token", a.c());
        }
        eVar.a("lfid", "OP_" + b.getAppKey());
        Object b2 = j.b(this.a, b.getAppKey());
        if (!TextUtils.isEmpty(b2)) {
            eVar.a(DeviceInfo.TAG_ANDROID_ID, b2);
        }
        eVar.a("packagename", b.getPackageName());
        eVar.a("key_hash", b.getKeyHash());
        String str = "https://open.weibo.cn/oauth2/authorize?" + eVar.c();
        if (f.a(this.a)) {
            String str2 = null;
            if (this.b != null) {
                com.sina.weibo.sdk.web.c a2 = com.sina.weibo.sdk.web.c.a();
                str2 = a2.b();
                a2.a(str2, this.b);
            }
            com.sina.weibo.sdk.web.b.a aVar = new com.sina.weibo.sdk.web.b.a(b, WebRequestType.AUTH, str2, "微博登录", str, this.a);
            Intent intent = new Intent(this.a, WeiboSdkWebActivity.class);
            Bundle bundle = new Bundle();
            aVar.c(bundle);
            intent.putExtras(bundle);
            this.a.startActivity(intent);
            return;
        }
        i.a(this.a, "Error", "Application requires permission to access the Internet");
    }

    public void a(int i, int i2, Intent intent) {
        if (32973 != i) {
            return;
        }
        if (i2 == -1) {
            if (h.a(this.a, c.a(this.a).a(), intent)) {
                Object c = j.c(intent.getStringExtra(x.aF));
                Object c2 = j.c(intent.getStringExtra("error_type"));
                Object c3 = j.c(intent.getStringExtra("error_description"));
                d.a("WBAgent", "error: " + c + ", error_type: " + c2 + ", error_description: " + c3);
                if (TextUtils.isEmpty(c) && TextUtils.isEmpty(c2) && TextUtils.isEmpty(c3)) {
                    b a = b.a(intent.getExtras());
                    if (a != null && a.a()) {
                        d.a("WBAgent", "Login Success! " + a.toString());
                        a.a(this.a, a);
                        this.b.onSuccess(a);
                        return;
                    }
                    return;
                } else if ("access_denied".equals(c) || "OAuthAccessDeniedException".equals(c)) {
                    d.a("WBAgent", "Login canceled by user.");
                    this.b.cancel();
                    return;
                } else {
                    d.a("WBAgent", "Login failed: " + c);
                    this.b.onFailure(new e(c2, c3));
                    return;
                }
            }
            this.b.onFailure(new e("your install weibo app is counterfeit", "8001"));
        } else if (i2 != 0) {
        } else {
            if (intent != null) {
                this.b.cancel();
            } else {
                this.b.cancel();
            }
        }
    }

    @Deprecated
    public boolean c() {
        return b.a(this.a);
    }

    protected void d() {
    }
}
