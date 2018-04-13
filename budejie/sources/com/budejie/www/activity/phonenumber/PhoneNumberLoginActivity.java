package com.budejie.www.activity.phonenumber;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.budejie.www.R;
import com.budejie.www.activity.BudejieApplication;
import com.budejie.www.activity.base.BaseActvityWithLoadDailog;
import com.budejie.www.c.m;
import com.budejie.www.http.NetWorkUtil.RequstMethod;
import com.budejie.www.http.j;
import com.budejie.www.util.ai;
import com.budejie.www.util.an;
import com.budejie.www.util.z;
import com.budejie.www.widget.erroredittext.SetErrorAbleEditText;
import com.budejie.www.widget.erroredittext.b;
import com.budejie.www.widget.erroredittext.c;
import com.budejie.www.widget.erroredittext.d;
import com.budejie.www.widget.erroredittext.i;
import com.tencent.connect.common.Constants;
import java.util.Map;
import mtopsdk.mtop.antiattack.CheckCodeDO;
import net.tsz.afinal.a.a;

public class PhoneNumberLoginActivity extends BaseActvityWithLoadDailog {
    private final String a = "86";
    private SetErrorAbleEditText b;
    private SetErrorAbleEditText c;
    private Button d;
    private b e;
    private b f;
    private m h;
    private TextView i;
    private TextView j;
    private OnClickListener k = new OnClickListener(this) {
        final /* synthetic */ PhoneNumberLoginActivity a;

        {
            this.a = r1;
        }

        public void onClick(View view) {
            if (view == this.a.d) {
                if (this.a.f.a() && this.a.e.a()) {
                    this.a.a(this.a.b.getText().toString(), this.a.c.getText().toString());
                }
            } else if (view == this.a.i) {
                this.a.startActivity(new Intent(this.a, PhoneRetrievePasswordActivity.class));
            } else if (view == this.a.j) {
                this.a.startActivityForResult(new Intent(this.a, PhoneNumRegisterActivity.class), 5050);
            }
        }
    };
    private a<String> l = new a<String>(this) {
        final /* synthetic */ PhoneNumberLoginActivity a;

        {
            this.a = r1;
        }

        public /* synthetic */ void onSuccess(Object obj) {
            a((String) obj);
        }

        public void onStart() {
            this.a.f();
        }

        public void a(String str) {
            this.a.e();
            if (TextUtils.isEmpty(str)) {
                an.a(this.a, "注册失败", -1).show();
                return;
            }
            Map d = z.d(str);
            if (d != null && !d.isEmpty()) {
                String str2 = (String) d.get("msg");
                if (Constants.DEFAULT_UIN.equals((String) d.get(CheckCodeDO.CHECKCODE_USER_INPUT_KEY))) {
                    String str3 = (String) d.get("id");
                    this.a.h.a(str3, d);
                    ai.a(this.a, str3, com.ali.auth.third.core.model.Constants.SERVICE_SCOPE_FLAG_VALUE);
                    this.a.setResult(5051);
                    this.a.finish();
                    return;
                }
                an.a(this.a, str2, -1).show();
            }
        }

        public void onFailure(Throwable th, int i, String str) {
            this.a.e();
        }
    };

    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.login_phone_num);
        d(R.id.navigation_bar);
        setTitle((int) R.string.phone_login);
        a(null);
        a();
        this.h = new m(this);
    }

    protected void onActivityResult(int i, int i2, Intent intent) {
        super.onActivityResult(i, i2, intent);
        if (i2 == 5051) {
            setResult(5051);
            finish();
        }
    }

    private void a() {
        an.a((LinearLayout) findViewById(R.id.TitleGapLayout));
        this.b = (SetErrorAbleEditText) findViewById(R.id.login_phonenumber);
        this.c = (SetErrorAbleEditText) findViewById(R.id.login_password);
        this.d = (Button) findViewById(R.id.login_btn);
        this.d.setOnClickListener(this.k);
        this.e = new b(this.b);
        this.e.a(new c("手机号码不能为空"));
        this.e.a(new i("手机号码不正确"));
        this.f = new b(this.c);
        this.f.a(new c("密码不能为空"));
        this.f.a(new d("密码格式不正确"));
        this.i = (TextView) findViewById(R.id.loginToRetrievepsw);
        this.j = (TextView) findViewById(R.id.loginToRegister);
        this.i.setOnClickListener(this.k);
        this.j.setOnClickListener(this.k);
    }

    private void a(String str, String str2) {
        BudejieApplication.a.a(RequstMethod.GET, j.j(), new j().h(this, str, str2, "86"), this.l);
    }
}
