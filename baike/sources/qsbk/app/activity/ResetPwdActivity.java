package qsbk.app.activity;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import java.util.HashMap;
import java.util.Map;
import qsbk.app.Constants;
import qsbk.app.QsbkApp;
import qsbk.app.R;
import qsbk.app.activity.base.BaseActionBarActivity;
import qsbk.app.core.AsyncTask;
import qsbk.app.http.EncryptHttpTask;
import qsbk.app.http.HttpTask;
import qsbk.app.widget.CaptchaButton;

public class ResetPwdActivity extends BaseActionBarActivity {
    View a;
    TextView b;
    CaptchaButton c;
    EditText d;
    EditText e;
    EditText f;
    Button g;
    String h;
    String i;
    String j;
    ProgressDialog k;
    HttpTask l;
    EncryptHttpTask m;
    TextWatcher n = new aaz(this);
    TextWatcher o = new aba(this);

    protected /* synthetic */ CharSequence getCustomTitle() {
        return f();
    }

    public static void launch(Activity activity) {
        activity.startActivity(new Intent(activity, ResetPwdActivity.class));
    }

    protected String f() {
        return getString(R.string.rest_pwd);
    }

    protected int a() {
        return R.layout.activity_reset_pwd;
    }

    protected void a(Bundle bundle) {
        int i;
        int i2 = 0;
        setActionbarBackable();
        this.a = findViewById(R.id.old_phone_container);
        this.b = (TextView) findViewById(R.id.old_phone);
        this.c = (CaptchaButton) findViewById(R.id.get_code);
        this.d = (EditText) findViewById(R.id.phone);
        this.e = (EditText) findViewById(R.id.captcha);
        this.f = (EditText) findViewById(R.id.pwd);
        this.g = (Button) findViewById(R.id.bind);
        this.e.addTextChangedListener(this.n);
        this.d.addTextChangedListener(this.n);
        this.f.addTextChangedListener(this.n);
        this.d.addTextChangedListener(this.o);
        this.c.setOnClickListener(new abb(this));
        this.c.setOnTickListener(new abc(this));
        this.c.setEnabled(QsbkApp.currentUser != null);
        this.g.setOnClickListener(new abd(this));
        EditText editText = this.d;
        if (QsbkApp.currentUser == null) {
            i = 0;
        } else {
            i = 8;
        }
        editText.setVisibility(i);
        View view = this.a;
        if (QsbkApp.currentUser == null || !QsbkApp.currentUser.hasPhone()) {
            i2 = 8;
        }
        view.setVisibility(i2);
        if (QsbkApp.currentUser != null && QsbkApp.currentUser.hasPhone()) {
            this.b.setText(QsbkApp.currentUser.phone);
        }
    }

    private void g() {
        String str = Constants.RESET_PWD;
        Map hashMap = new HashMap();
        hashMap.put("phone", this.h);
        hashMap.put("code", this.i);
        hashMap.put("password", this.j);
        this.l = new EncryptHttpTask(str, str, new abe(this));
        this.l.setMapParams(hashMap);
        this.l.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, new Void[0]);
        a(false);
    }

    private void a(boolean z) {
        if (this.k == null) {
            this.k = ProgressDialog.show(this, null, "请稍候...", true, z);
        }
        this.k.setCancelable(z);
        this.k.show();
    }

    private void i() {
        if (this.k != null) {
            this.k.dismiss();
        }
    }

    private void j() {
        this.h = this.d.getText().toString().trim();
        this.i = this.e.getText().toString().trim();
        this.j = this.f.getText().toString().trim();
        if ((QsbkApp.currentUser != null || a(this.h)) && b(this.i) && c(this.j)) {
            this.g.setEnabled(true);
        } else {
            this.g.setEnabled(false);
        }
    }

    private boolean a(String str) {
        return !TextUtils.isEmpty(str);
    }

    private boolean b(String str) {
        return !TextUtils.isEmpty(str);
    }

    private boolean c(String str) {
        return !TextUtils.isEmpty(str);
    }

    private void k() {
        String str = Constants.GET_CODE;
        this.m = new EncryptHttpTask(str, str, new abf(this));
        Map hashMap = new HashMap();
        hashMap.put("purpose", "reset_password");
        hashMap.put("phone", this.h);
        this.m.setMapParams(hashMap);
        this.m.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, new Void[0]);
    }

    protected void onDestroy() {
        super.onDestroy();
        if (this.l != null) {
            this.l.cancel(true);
        }
        if (this.m != null) {
            this.m.cancel(true);
        }
        this.e.removeTextChangedListener(this.n);
        this.d.removeTextChangedListener(this.n);
        this.f.removeTextChangedListener(this.n);
        this.d.removeTextChangedListener(this.o);
    }
}
