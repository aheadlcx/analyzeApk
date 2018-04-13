package com.budejie.www.activity.phonenumber;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.text.TextUtils;
import android.view.View;
import android.view.View.OnClickListener;
import com.ali.auth.third.core.model.Constants;
import com.budejie.www.R;
import com.budejie.www.activity.BudejieApplication;
import com.budejie.www.activity.base.BaseActvityWithLoadDailog;
import com.budejie.www.activity.phonenumber.g.a;
import com.budejie.www.activity.phonenumber.k.b;
import com.budejie.www.adapter.a.i;
import com.budejie.www.adapter.a.j;
import com.budejie.www.c.g;
import com.budejie.www.http.NetWorkUtil.RequstMethod;
import com.budejie.www.util.an;
import com.budejie.www.util.z;
import com.umeng.analytics.MobclickAgent;
import com.umeng.onlineconfig.OnlineConfigAgent;
import java.util.HashMap;
import mtopsdk.mtop.antiattack.CheckCodeDO;

public class PhoneNumBindingActivity extends BaseActvityWithLoadDailog implements a, b {
    OnClickListener a = new OnClickListener(this) {
        final /* synthetic */ PhoneNumBindingActivity a;

        {
            this.a = r1;
        }

        public void onClick(View view) {
        }
    };
    OnClickListener b = new OnClickListener(this) {
        final /* synthetic */ PhoneNumBindingActivity a;

        {
            this.a = r1;
        }

        public void onClick(View view) {
            MobclickAgent.onEvent(this.a, "绑定手机号界面下次再说点击次数", "绑定手机号界面下次再说点击次数");
            this.a.g();
        }
    };
    OnClickListener c = new OnClickListener(this) {
        final /* synthetic */ PhoneNumBindingActivity a;

        {
            this.a = r1;
        }

        public void onClick(View view) {
            this.a.setResult(5051);
            this.a.k.finish();
        }
    };
    OnClickListener d = new OnClickListener(this) {
        final /* synthetic */ PhoneNumBindingActivity a;

        {
            this.a = r1;
        }

        public void onClick(View view) {
            this.a.g();
        }
    };
    OnClickListener e = new OnClickListener(this) {
        final /* synthetic */ PhoneNumBindingActivity a;

        {
            this.a = r1;
        }

        public void onClick(View view) {
            if (TextUtils.isEmpty(j.b())) {
                this.a.b();
                return;
            }
            this.a.f();
            BudejieApplication.a.a(RequstMethod.GET, "http://api.budejie.com/api/api_open.php", this.a.c(j.b()), this.a.p);
        }
    };
    OnClickListener f = new OnClickListener(this) {
        final /* synthetic */ PhoneNumBindingActivity a;

        {
            this.a = r1;
        }

        public void onClick(View view) {
            this.a.f();
            if (i.b() == null || i.b().size() == 0) {
                this.a.finish();
                return;
            }
            this.a.n.a(i.b());
            BudejieApplication.a.a(RequstMethod.GET, "http://api.budejie.com/api/api_open.php", this.a.b(i.c()), this.a.o);
        }
    };
    private String h;
    private String i;
    private boolean j;
    private Activity k;
    private d l;
    private e m;
    private g n;
    private net.tsz.afinal.a.a<String> o = new net.tsz.afinal.a.a<String>(this) {
        final /* synthetic */ PhoneNumBindingActivity a;

        {
            this.a = r1;
        }

        public /* synthetic */ void onSuccess(Object obj) {
            a((String) obj);
        }

        public void onStart() {
        }

        public void onFailure(Throwable th, int i, String str) {
            super.onFailure(th, i, str);
        }

        public void a(String str) {
            this.a.e();
            if (!TextUtils.isEmpty(str)) {
                HashMap d = z.d(str);
                if (d == null || d.isEmpty()) {
                    an.a(this.a.k, "关注失败", -1).show();
                    return;
                }
                String str2 = (String) d.get(CheckCodeDO.CHECKCODE_USER_INPUT_KEY);
                String str3 = (String) d.get("msg");
                if ("0".equals(str2)) {
                    an.a(this.a.k, "关注成功", -1).show();
                    this.a.finish();
                    return;
                }
                an.a(this.a.k, str3, -1).show();
            }
        }
    };
    private net.tsz.afinal.a.a<String> p = new net.tsz.afinal.a.a<String>(this) {
        final /* synthetic */ PhoneNumBindingActivity a;

        {
            this.a = r1;
        }

        public /* synthetic */ void onSuccess(Object obj) {
            a((String) obj);
        }

        public void onStart() {
        }

        public void onFailure(Throwable th, int i, String str) {
            super.onFailure(th, i, str);
        }

        public void a(String str) {
            this.a.e();
            if (TextUtils.isEmpty(str)) {
                an.a(this.a.k, "订阅失败", -1).show();
                return;
            }
            HashMap d = z.d(str);
            if (d != null && !d.isEmpty()) {
                String str2 = (String) d.get(CheckCodeDO.CHECKCODE_USER_INPUT_KEY);
                String str3 = (String) d.get("msg");
                if ("0".equals(str2)) {
                    an.a(this.a.k, "订阅成功", -1).show();
                    this.a.b();
                    return;
                }
                an.a(this.a.k, str3, -1).show();
            }
        }
    };

    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.register_phone_num);
        d(R.id.navigation_bar);
        setTitle((int) R.string.bind_phone);
        a(null);
        this.k = this;
        this.n = new g(this.k);
        this.h = getIntent().getStringExtra("nike_name");
        this.i = getIntent().getStringExtra("source");
        this.j = getIntent().getBooleanExtra("isChangePhone", false);
        if (bundle == null) {
            a(null, "取消");
            if (!"PostInviteRow".equals(this.i) && "third_party".equals(this.i)) {
                a(this.b, "下次再说");
            }
            if (getIntent().getBooleanExtra("is_skip", false)) {
                g();
                return;
            }
            Fragment a = g.a(this.i, (CharSequence) "1");
            if (!TextUtils.isEmpty(this.h)) {
                a.getArguments().putString("nike_name", this.h);
            }
            FragmentTransaction beginTransaction = getSupportFragmentManager().beginTransaction();
            beginTransaction.add(R.id.content, a, "regist_fragment");
            beginTransaction.commit();
        }
    }

    public void a(String str) {
        a(null);
        if ("third_party".equals(this.i)) {
            a(null, "");
        }
        Fragment a = k.a((CharSequence) str, (CharSequence) "1", this.i, this.j);
        FragmentTransaction beginTransaction = getSupportFragmentManager().beginTransaction();
        beginTransaction.replace(R.id.content, a, "sms_verify");
        beginTransaction.commit();
    }

    public void a() {
        if ("third_party".equals(this.i)) {
            Object obj = Constants.SERVICE_SCOPE_FLAG_VALUE;
            if (!"".equals(OnlineConfigAgent.getInstance().getConfigParams(this, "是否进入推荐订阅"))) {
                obj = OnlineConfigAgent.getInstance().getConfigParams(this, "是否进入推荐订阅");
            }
            if (Constants.SERVICE_SCOPE_FLAG_VALUE.equals(obj)) {
                g();
                return;
            } else {
                finish();
                return;
            }
        }
        setResult(6383);
        finish();
    }

    private void b() {
        setTitle((int) R.string.recommend_attention);
        a(this.d, "返回");
        b(this.f, "完成");
        this.l = d.a();
        FragmentTransaction beginTransaction = getSupportFragmentManager().beginTransaction();
        beginTransaction.replace(R.id.content, this.l, "recom_att");
        beginTransaction.commit();
    }

    private void g() {
        setTitle((int) R.string.recommend_subscription);
        a(this.c, "返回");
        b(this.e, "下一步");
        this.m = e.a();
        FragmentTransaction beginTransaction = getSupportFragmentManager().beginTransaction();
        beginTransaction.replace(R.id.content, this.m, "recom_sub");
        beginTransaction.commit();
    }

    private net.tsz.afinal.a.b b(String str) {
        return new com.budejie.www.http.j().c(this.k, str, "3");
    }

    private net.tsz.afinal.a.b c(String str) {
        return new com.budejie.www.http.j().a(this.k, str, false);
    }
}
