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

public class PayPwdResetActivity extends BaseActionBarActivity {
    View a;
    TextView b;
    CaptchaButton c;
    EditText d;
    EditText e;
    Button f;
    String g;
    String h;
    ProgressDialog i;
    HttpTask j;
    EncryptHttpTask k;
    TextWatcher l = new zq(this);

    protected /* synthetic */ CharSequence getCustomTitle() {
        return f();
    }

    public static void launch(Activity activity) {
        activity.startActivity(new Intent(activity, PayPwdResetActivity.class));
    }

    protected String f() {
        return getString(R.string.reset_pay_pwd);
    }

    protected int a() {
        return R.layout.activity_pay_pwd_reset;
    }

    protected void a(Bundle bundle) {
        int i = 0;
        setActionbarBackable();
        this.a = findViewById(R.id.old_phone_container);
        this.b = (TextView) findViewById(R.id.old_phone);
        this.c = (CaptchaButton) findViewById(R.id.get_code);
        this.d = (EditText) findViewById(R.id.captcha);
        this.e = (EditText) findViewById(R.id.pwd);
        this.f = (Button) findViewById(R.id.bind);
        this.d.addTextChangedListener(this.l);
        this.e.addTextChangedListener(this.l);
        this.c.setOnClickListener(new zr(this));
        this.c.setOnTickListener(new zs(this));
        this.c.setEnabled(QsbkApp.currentUser != null);
        this.f.setOnClickListener(new zt(this));
        View view = this.a;
        if (QsbkApp.currentUser == null || !QsbkApp.currentUser.hasPhone()) {
            i = 8;
        }
        view.setVisibility(i);
        if (QsbkApp.currentUser != null && QsbkApp.currentUser.hasPhone()) {
            this.b.setText(QsbkApp.currentUser.phone);
        }
    }

    private void g() {
        String str = Constants.RESET_PAY_PWD;
        Map hashMap = new HashMap();
        hashMap.put("code", this.g);
        hashMap.put("paypass", this.h);
        this.j = new EncryptHttpTask(str, str, new zu(this));
        this.j.setMapParams(hashMap);
        this.j.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, new Void[0]);
        a(false);
    }

    private void a(boolean z) {
        if (this.i == null) {
            this.i = ProgressDialog.show(this, null, "请稍候...", true, z);
        }
        this.i.setCancelable(z);
        this.i.show();
    }

    private void i() {
        if (this.i != null) {
            this.i.dismiss();
        }
    }

    private void j() {
        this.g = this.d.getText().toString().trim();
        this.h = this.e.getText().toString().trim();
        if (a(this.g) && b(this.h)) {
            this.f.setEnabled(true);
        } else {
            this.f.setEnabled(false);
        }
    }

    private boolean a(String str) {
        return !TextUtils.isEmpty(str);
    }

    private boolean b(String str) {
        return !TextUtils.isEmpty(str);
    }

    private void k() {
        String str = Constants.GET_CODE;
        this.k = new EncryptHttpTask(str, str, new zv(this));
        Map hashMap = new HashMap();
        hashMap.put("purpose", "reset_paypass");
        this.k.setMapParams(hashMap);
        this.k.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, new Void[0]);
    }

    protected void onDestroy() {
        super.onDestroy();
        if (this.j != null) {
            this.j.cancel(true);
        }
        if (this.k != null) {
            this.k.cancel(true);
        }
        this.d.removeTextChangedListener(this.l);
        this.e.removeTextChangedListener(this.l);
    }
}
