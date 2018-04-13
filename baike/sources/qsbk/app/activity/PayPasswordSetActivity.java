package qsbk.app.activity;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.widget.Button;
import android.widget.EditText;
import java.util.HashMap;
import java.util.Map;
import org.eclipse.paho.client.mqttv3.internal.ClientDefaults;
import qsbk.app.Constants;
import qsbk.app.R;
import qsbk.app.activity.base.BaseActionBarActivity;
import qsbk.app.core.AsyncTask;
import qsbk.app.http.EncryptHttpTask;
import qsbk.app.widget.CaptchaButton;

public class PayPasswordSetActivity extends BaseActionBarActivity {
    CaptchaButton a;
    EditText b;
    EditText c;
    Button d;
    TextWatcher e = new zk(this);
    private EncryptHttpTask f;
    private EncryptHttpTask g;
    private String h;
    private String i;
    private ProgressDialog j;

    protected /* synthetic */ CharSequence getCustomTitle() {
        return f();
    }

    public static void launch(Context context) {
        Intent intent = new Intent(context, PayPasswordSetActivity.class);
        if (!(context instanceof Activity)) {
            intent.addFlags(ClientDefaults.MAX_MSG_SIZE);
        }
        context.startActivity(intent);
    }

    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        this.b.addTextChangedListener(this.e);
        this.c.addTextChangedListener(this.e);
    }

    protected void onDestroy() {
        super.onDestroy();
        this.b.removeTextChangedListener(this.e);
        this.c.removeTextChangedListener(this.e);
        if (this.f != null) {
            this.f.cancel(true);
        }
        if (this.g != null) {
            this.g.cancel(true);
        }
    }

    protected String f() {
        return "设置支付密码";
    }

    protected int a() {
        return R.layout.activity_pay_pwd_set;
    }

    protected void a(Bundle bundle) {
        setActionbarBackable();
        this.c = (EditText) findViewById(R.id.pwd);
        this.b = (EditText) findViewById(R.id.captcha);
        this.a = (CaptchaButton) findViewById(R.id.get_code);
        this.a.setOnClickListener(new zl(this));
        this.a.setOnTickListener(new zm(this));
        this.d = (Button) findViewById(R.id.done);
        this.d.setOnClickListener(new zn(this));
    }

    private void g() {
        String str = Constants.GET_CODE;
        this.f = new EncryptHttpTask(str, str, new zo(this));
        Map hashMap = new HashMap();
        hashMap.put("purpose", "set_paypass");
        this.f.setMapParams(hashMap);
        this.f.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, new Void[0]);
    }

    private void i() {
        String str = Constants.SET_PAY_PWD;
        this.g = new EncryptHttpTask(str, str, new zp(this));
        this.g.setMapParams(getParams());
        this.g.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, new Void[0]);
        a(false);
    }

    private void j() {
        this.h = this.b.getText().toString().trim();
        this.i = this.c.getText().toString().trim();
        if (b(this.i) && a(this.h)) {
            this.d.setEnabled(true);
        } else {
            this.d.setEnabled(false);
        }
    }

    private boolean a(String str) {
        return !TextUtils.isEmpty(str);
    }

    private boolean b(String str) {
        if (TextUtils.isEmpty(str) || str.length() != 6) {
            return false;
        }
        return true;
    }

    private void a(boolean z) {
        if (this.j == null) {
            this.j = ProgressDialog.show(this, null, "请稍候...", true, z);
        }
        this.j.setCancelable(z);
        this.j.show();
    }

    private void k() {
        if (this.j != null) {
            this.j.dismiss();
        }
    }

    public Map<String, Object> getParams() {
        Map<String, Object> hashMap = new HashMap();
        hashMap.put("paypass", this.i);
        hashMap.put("code", this.h);
        return hashMap;
    }
}
