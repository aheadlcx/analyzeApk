package com.budejie.www.activity.phonenumber;

import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.text.TextUtils;
import android.view.Display;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.WindowManager.LayoutParams;
import android.widget.TextView;
import android.widget.Toast;
import com.budejie.www.R;
import com.budejie.www.activity.BudejieApplication;
import com.budejie.www.activity.CreatePersonDataActivity;
import com.budejie.www.activity.base.BaseActvityWithLoadDailog;
import com.budejie.www.activity.phonenumber.g.a;
import com.budejie.www.activity.phonenumber.k.b;
import com.budejie.www.adapter.a.i;
import com.budejie.www.adapter.a.j;
import com.budejie.www.c.g;
import com.budejie.www.http.NetWorkUtil.RequstMethod;
import com.budejie.www.type.SendSMSVerifyResult;
import com.budejie.www.util.an;
import com.budejie.www.util.z;
import com.tencent.connect.common.Constants;
import com.umeng.onlineconfig.OnlineConfigAgent;
import java.util.HashMap;
import mtopsdk.mtop.antiattack.CheckCodeDO;

public class PhoneNumRegisterActivity extends BaseActvityWithLoadDailog implements a, b {
    OnClickListener a = new OnClickListener(this) {
        final /* synthetic */ PhoneNumRegisterActivity a;

        {
            this.a = r1;
        }

        public void onClick(View view) {
            this.a.a("", "验证码短信可能略有延迟", "确定取消并重新开始", "等待", "重新开始", null);
        }
    };
    OnClickListener b = new OnClickListener(this) {
        final /* synthetic */ PhoneNumRegisterActivity a;

        {
            this.a = r1;
        }

        public void onClick(View view) {
            this.a.setResult(5051);
            this.a.i.finish();
        }
    };
    OnClickListener c = new OnClickListener(this) {
        final /* synthetic */ PhoneNumRegisterActivity a;

        {
            this.a = r1;
        }

        public void onClick(View view) {
            this.a.g();
        }
    };
    OnClickListener d = new OnClickListener(this) {
        final /* synthetic */ PhoneNumRegisterActivity a;

        {
            this.a = r1;
        }

        public void onClick(View view) {
            if (TextUtils.isEmpty(j.b())) {
                this.a.b();
                return;
            }
            this.a.f();
            BudejieApplication.a.a(RequstMethod.GET, "http://api.budejie.com/api/api_open.php", this.a.c(j.b()), this.a.o);
        }
    };
    OnClickListener e = new OnClickListener(this) {
        final /* synthetic */ PhoneNumRegisterActivity a;

        {
            this.a = r1;
        }

        public void onClick(View view) {
            this.a.f();
            if (i.b() == null || i.b().size() == 0) {
                this.a.setResult(5051);
                this.a.finish();
                return;
            }
            BudejieApplication.a.a(RequstMethod.GET, "http://api.budejie.com/api/api_open.php", this.a.b(i.c()), this.a.p);
            this.a.setResult(5051);
            this.a.finish();
        }
    };
    private String f;
    private Dialog h;
    private Activity i;
    private Toast j;
    private d k;
    private e l;
    private g m;
    private net.tsz.afinal.a.a<String> n = new net.tsz.afinal.a.a<String>(this) {
        final /* synthetic */ PhoneNumRegisterActivity a;

        {
            this.a = r1;
        }

        public /* synthetic */ void onSuccess(Object obj) {
            a((String) obj);
        }

        public void onFailure(Throwable th, int i, String str) {
            super.onFailure(th, i, str);
        }

        public void a(String str) {
            super.onSuccess(str);
            SendSMSVerifyResult sendSMSVerifyResult = (SendSMSVerifyResult) z.a(str, SendSMSVerifyResult.class);
            if (sendSMSVerifyResult == null) {
                this.a.j = an.a(this.a.i, "获取验证码失败", -1);
            } else if (!Constants.DEFAULT_UIN.equals(sendSMSVerifyResult.getCode())) {
                String msg = sendSMSVerifyResult.getMsg();
                if (TextUtils.isEmpty(msg)) {
                    msg = "获取验证码失败";
                }
                this.a.j = an.a(this.a.i, msg, -1);
            }
            if (this.a.j != null) {
                this.a.j.show();
            }
        }
    };
    private net.tsz.afinal.a.a<String> o = new net.tsz.afinal.a.a<String>(this) {
        final /* synthetic */ PhoneNumRegisterActivity a;

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
                an.a(this.a.i, "订阅失败", -1).show();
                return;
            }
            HashMap d = z.d(str);
            if (d != null && !d.isEmpty()) {
                String str2 = (String) d.get(CheckCodeDO.CHECKCODE_USER_INPUT_KEY);
                String str3 = (String) d.get("msg");
                if ("0".equals(str2)) {
                    an.a(this.a.i, "订阅成功", -1).show();
                    this.a.b();
                    return;
                }
                an.a(this.a.i, str3, -1).show();
            }
        }
    };
    private net.tsz.afinal.a.a<String> p = new net.tsz.afinal.a.a<String>(this) {
        final /* synthetic */ PhoneNumRegisterActivity a;

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
                an.a(this.a.i, "关注失败", -1).show();
                return;
            }
            HashMap d = z.d(str);
            if (d != null && !d.isEmpty()) {
                String str2 = (String) d.get(CheckCodeDO.CHECKCODE_USER_INPUT_KEY);
                String str3 = (String) d.get("msg");
                if ("0".equals(str2)) {
                    this.a.m.a(i.b());
                    an.a(this.a.i, "关注成功", -1).show();
                    return;
                }
                an.a(this.a.i, str3, -1).show();
            }
        }
    };

    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.register_phone_num);
        d(R.id.navigation_bar);
        setTitle((int) R.string.sms_verify);
        a(this.a);
        this.i = this;
        this.m = new g(this.i);
        this.f = getIntent().getStringExtra("source");
        CharSequence f = com.budejie.www.util.i.a().f();
        if (bundle == null) {
            Fragment a = k.a(f, (CharSequence) "1", this.f, false);
            FragmentTransaction beginTransaction = getSupportFragmentManager().beginTransaction();
            beginTransaction.add(R.id.content, a, "sms_verify");
            beginTransaction.commit();
        }
    }

    private void a(String str, String str2, String str3, String str4, String str5, OnClickListener onClickListener) {
        View inflate = View.inflate(this, R.layout.phone_register_dialog, null);
        TextView textView = (TextView) inflate.findViewById(R.id.phone_number);
        TextView textView2 = (TextView) inflate.findViewById(R.id.rightText);
        TextView textView3 = (TextView) inflate.findViewById(R.id.belowText);
        if (!TextUtils.isEmpty(str)) {
            textView.setText(str);
        }
        if (!TextUtils.isEmpty(str2)) {
            textView2.setText(str2);
        }
        if (!TextUtils.isEmpty(str3)) {
            textView3.setText(str3);
        }
        this.h = new Dialog(this, R.style.theme_my_dialog);
        this.h.setContentView(inflate);
        Display defaultDisplay = getWindowManager().getDefaultDisplay();
        LayoutParams attributes = this.h.getWindow().getAttributes();
        attributes.width = (int) (((double) defaultDisplay.getWidth()) * 0.8d);
        this.h.getWindow().setAttributes(attributes);
        this.h.show();
        textView = (TextView) inflate.findViewById(R.id.change);
        if (!TextUtils.isEmpty(str4)) {
            textView.setText(str4);
        }
        textView2 = (TextView) inflate.findViewById(R.id.confirm);
        if (!TextUtils.isEmpty(str5)) {
            textView2.setText(str5);
        }
        textView.setOnClickListener(new OnClickListener(this) {
            final /* synthetic */ PhoneNumRegisterActivity a;

            {
                this.a = r1;
            }

            public void onClick(View view) {
                this.a.h.dismiss();
            }
        });
        textView2.setOnClickListener(new OnClickListener(this) {
            final /* synthetic */ PhoneNumRegisterActivity a;

            {
                this.a = r1;
            }

            public void onClick(View view) {
                this.a.finish();
            }
        });
    }

    public void a(String str) {
    }

    protected void onActivityResult(int i, int i2, Intent intent) {
        super.onActivityResult(i, i2, intent);
        if (i2 == -1) {
            Object obj = com.ali.auth.third.core.model.Constants.SERVICE_SCOPE_FLAG_VALUE;
            if (!"".equals(OnlineConfigAgent.getInstance().getConfigParams(this, "是否进入推荐订阅"))) {
                obj = OnlineConfigAgent.getInstance().getConfigParams(this, "是否进入推荐订阅");
            }
            if (com.ali.auth.third.core.model.Constants.SERVICE_SCOPE_FLAG_VALUE.equals(obj)) {
                g();
                return;
            }
            setResult(5051);
            finish();
            return;
        }
        setResult(5051);
        finish();
    }

    private void b() {
        setTitle((int) R.string.recommend_attention);
        a(this.c, "返回");
        b(this.e, "完成");
        this.k = d.a();
        FragmentTransaction beginTransaction = getSupportFragmentManager().beginTransaction();
        beginTransaction.replace(R.id.content, this.k, "recom_att");
        beginTransaction.commit();
    }

    private void g() {
        setTitle((int) R.string.recommend_subscription);
        a(this.b, "返回");
        b(this.d, "下一步");
        this.l = e.a();
        FragmentTransaction beginTransaction = getSupportFragmentManager().beginTransaction();
        beginTransaction.replace(R.id.content, this.l, "recom_sub");
        beginTransaction.commit();
    }

    public boolean onKeyDown(int i, KeyEvent keyEvent) {
        if (i != 4 || keyEvent.getRepeatCount() != 0) {
            return super.onKeyDown(i, keyEvent);
        }
        setResult(5051);
        finish();
        return false;
    }

    public void a() {
        Intent intent = new Intent(this, CreatePersonDataActivity.class);
        intent.putExtra("source", this.f);
        startActivityForResult(intent, 5050);
    }

    private net.tsz.afinal.a.b b(String str) {
        return new com.budejie.www.http.j().c(this.i, str, "3");
    }

    private net.tsz.afinal.a.b c(String str) {
        return new com.budejie.www.http.j().a(this.i, str, false);
    }
}
