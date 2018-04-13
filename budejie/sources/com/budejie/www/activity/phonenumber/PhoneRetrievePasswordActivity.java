package com.budejie.www.activity.phonenumber;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import com.budejie.www.R;
import com.budejie.www.activity.base.BaseActvityWithLoadDailog;
import com.budejie.www.activity.phonenumber.f.a;

public class PhoneRetrievePasswordActivity extends BaseActvityWithLoadDailog implements a, j.a {
    private String a;

    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.register_phone_num);
        d(R.id.navigation_bar);
        setTitle((int) R.string.retrieve_pswd);
        a(null);
        this.a = getIntent().getStringExtra("source");
        if ("MyAccountActivity".equals(this.a)) {
            setTitle((int) R.string.alter_psw);
        }
        if (bundle == null) {
            Fragment a = j.a((CharSequence) "2");
            FragmentTransaction beginTransaction = getSupportFragmentManager().beginTransaction();
            beginTransaction.add(R.id.content, a, "sms_verify");
            beginTransaction.commit();
        }
    }

    public void a(String str) {
        Fragment a = h.a((CharSequence) str);
        FragmentTransaction beginTransaction = getSupportFragmentManager().beginTransaction();
        beginTransaction.replace(R.id.content, a, "retrieve_fragment");
        beginTransaction.commit();
    }

    public void a() {
        if ("MyAccountActivity".equals(this.a)) {
            setResult(-1);
        }
        finish();
    }
}
