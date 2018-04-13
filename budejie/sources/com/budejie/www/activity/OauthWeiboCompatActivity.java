package com.budejie.www.activity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import com.budejie.www.R;
import com.budejie.www.activity.base.BaseCompatActivity;
import com.budejie.www.activity.label.h;
import com.budejie.www.http.n;
import com.budejie.www.util.au;
import com.budejie.www.util.p;
import com.sina.weibo.sdk.auth.b;
import com.sina.weibo.sdk.auth.d;
import com.sina.weibo.sdk.auth.e;
import com.sina.weibo.sdk.share.a;
import com.tencent.tauth.IUiListener;
import com.tencent.tauth.Tencent;
import com.tencent.tauth.UiError;
import com.umeng.analytics.MobclickAgent;

public abstract class OauthWeiboCompatActivity extends BaseCompatActivity implements d, a, IUiListener {
    public static b c;
    public Tencent a;
    public com.sina.weibo.sdk.auth.a.a b;
    public com.sina.weibo.sdk.share.b d;

    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        this.a = Tencent.createInstance("100336987", this);
        this.b = new com.sina.weibo.sdk.auth.a.a(this);
        c = OauthWeiboBaseAct.mAccessToken;
        this.d = new com.sina.weibo.sdk.share.b(this);
        this.d.a();
    }

    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        if (this.d != null) {
            this.d.a(intent, (a) this);
        }
    }

    protected void onActivityResult(int i, int i2, Intent intent) {
        super.onActivityResult(i, i2, intent);
        if (i == 716 && i2 == -1) {
            p.a((Activity) this, p.a, 640, 640);
        } else if (i == 728 && i2 == -1) {
            if (intent != null) {
                intent.putExtra("label_id", h.a().b());
                intent.putExtra("label_name", h.a().c());
                p.a(intent, (Activity) this, RecordActivity.class, "");
            }
        } else if (i == 714 && i2 == -1) {
            if (intent != null) {
                p.a((Activity) this, intent, 640, 640);
            }
        } else if (i2 == -1) {
            Tencent.onActivityResultData(i, i2, intent, this);
            if (i == 32973 && this.b != null) {
                this.b.a(i, i2, intent);
            }
        }
    }

    public void onCancel() {
        MobclickAgent.onEvent((Context) this, "weibo_bind", "qzone_or_sina:cancel");
    }

    public void onComplete(Object obj) {
    }

    public void onError(UiError uiError) {
        MobclickAgent.onEvent((Context) this, "weibo_bind", "qzone_bind_error:" + uiError.errorMessage);
    }

    public void onSuccess(b bVar) {
    }

    public void onFailure(e eVar) {
        MobclickAgent.onEvent((Context) this, "weibo_bind", "sina_bind_error:" + eVar.a());
    }

    public void onWbShareSuccess() {
        n.e(this);
    }

    public void onWbShareCancel() {
    }

    public void onWbShareFail() {
        au.a((int) R.string.share_failed);
    }

    public void cancel() {
    }
}
