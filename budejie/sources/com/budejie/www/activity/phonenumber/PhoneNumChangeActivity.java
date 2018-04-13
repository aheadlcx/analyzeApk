package com.budejie.www.activity.phonenumber;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.widget.LinearLayout;
import com.budejie.www.R;
import com.budejie.www.activity.base.BaseActvityWithLoadDailog;
import com.budejie.www.activity.phonenumber.b.a;
import com.budejie.www.util.an;

public class PhoneNumChangeActivity extends BaseActvityWithLoadDailog implements a, c.a, k.a {
    private String a;
    private String b;
    private boolean c;
    private String d;

    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.register_phone_num);
        an.a((LinearLayout) findViewById(R.id.TitleGapLayout));
        d(R.id.navigation_bar);
        setTitle((int) R.string.bind_phone);
        a(null);
        this.a = getIntent().getStringExtra("phone");
        this.b = getIntent().getStringExtra("source");
        this.c = getIntent().getBooleanExtra("isChangePhone", false);
        if (bundle == null) {
            a(null, "取消");
            Fragment a = b.a(this.a);
            FragmentTransaction beginTransaction = getSupportFragmentManager().beginTransaction();
            beginTransaction.add(R.id.content, a, "phone_change_press");
            beginTransaction.commit();
        }
    }

    public void a() {
        setTitle((int) R.string.change_phone);
        Fragment a = c.a((CharSequence) "1");
        FragmentTransaction beginTransaction = getSupportFragmentManager().beginTransaction();
        beginTransaction.replace(R.id.content, a, "phone_change");
        beginTransaction.commit();
    }

    public void a(String str, String str2) {
        a(null);
        this.d = str2;
        Fragment a = k.a((CharSequence) str, (CharSequence) "1", this.b, this.c);
        FragmentTransaction beginTransaction = getSupportFragmentManager().beginTransaction();
        beginTransaction.replace(R.id.content, a, "sms_verify");
        beginTransaction.commit();
    }

    public void b() {
        Intent intent = new Intent();
        intent.putExtra("newPhone", this.d);
        setResult(-1, intent);
        finish();
    }
}
