package qsbk.app.activity;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.text.InputFilter;
import android.text.InputFilter.LengthFilter;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import java.util.HashMap;
import java.util.Map;
import org.eclipse.paho.client.mqttv3.internal.ClientDefaults;
import qsbk.app.Constants;
import qsbk.app.QsbkApp;
import qsbk.app.R;
import qsbk.app.activity.base.BaseActionBarActivity;
import qsbk.app.core.AsyncTask;
import qsbk.app.http.EncryptHttpTask;
import qsbk.app.http.HttpTask;
import qsbk.app.utils.ToastAndDialog;
import qsbk.app.utils.Util;
import qsbk.app.widget.CaptchaButton;

public class BindPhoneActivity extends BaseActionBarActivity {
    CaptchaButton a;
    EditText b;
    EditText c;
    EditText d;
    Button e;
    ProgressDialog f;
    View g;
    View h;
    String i;
    String j;
    String k;
    String l = "pass";
    String m;
    String n;
    boolean o;
    HttpTask p;
    HttpTask q;
    HttpTask r;
    TextWatcher s = new dj(this);
    TextWatcher t = new dl(this);

    protected /* synthetic */ CharSequence getCustomTitle() {
        return c();
    }

    public static void launch(Context context) {
        Intent intent = new Intent(context, BindPhoneActivity.class);
        if (!(context instanceof Activity)) {
            intent.addFlags(ClientDefaults.MAX_MSG_SIZE);
        }
        context.startActivity(intent);
    }

    public static void launchForResult(Activity activity, int i) {
        launchForResult(activity, null, i);
    }

    public static void launchCanSkip(Activity activity, String str, String str2) {
        Intent intent = new Intent(activity, BindPhoneActivity.class);
        intent.putExtra("skip", true);
        intent.putExtra("type", str);
        intent.putExtra("token", str2);
        activity.startActivity(intent);
    }

    public static void launchForResult(Activity activity, String str, int i) {
        Intent intent = new Intent(activity, BindPhoneActivity.class);
        intent.putExtra("secret", str);
        activity.startActivityForResult(intent, i);
    }

    public static void launchForResult(Activity activity, String str, String str2, int i) {
        Intent intent = new Intent(activity, BindPhoneActivity.class);
        intent.putExtra("type", str);
        intent.putExtra("token", str2);
        activity.startActivityForResult(intent, i);
    }

    protected String c() {
        return null;
    }

    protected int a() {
        return R.layout.activity_bind_phone;
    }

    protected void onActivityResult(int i, int i2, Intent intent) {
        super.onActivityResult(i, i2, intent);
        if (i == 1) {
            if (intent != null) {
                Object stringExtra = intent.getStringExtra("type");
                Object stringExtra2 = intent.getStringExtra("token");
                if (!(TextUtils.isEmpty(stringExtra) || TextUtils.isEmpty(stringExtra2))) {
                    this.l = stringExtra;
                    this.m = stringExtra2;
                    g();
                    return;
                }
            }
            finish();
        }
    }

    protected void a(Bundle bundle) {
        int i = 8;
        if (getIntent() != null) {
            this.n = getIntent().getStringExtra("secret");
            this.m = getIntent().getStringExtra("token");
            this.l = getIntent().getStringExtra("type");
            this.o = getIntent().getBooleanExtra("skip", false);
        }
        View inflate = LayoutInflater.from(this).inflate(R.layout.actionbar_bind_phone, null);
        inflate.findViewById(R.id.cancel).setOnClickListener(new dm(this));
        View findViewById = inflate.findViewById(R.id.skip);
        findViewById.setVisibility(this.o ? 0 : 8);
        findViewById.setOnClickListener(new dn(this));
        View findViewById2 = inflate.findViewById(R.id.actionbar_back);
        if (!this.o) {
            i = 0;
        }
        findViewById2.setVisibility(i);
        ActionBar supportActionBar = getSupportActionBar();
        supportActionBar.setDisplayShowHomeEnabled(false);
        supportActionBar.setDisplayShowTitleEnabled(false);
        supportActionBar.setDisplayHomeAsUpEnabled(false);
        supportActionBar.setDisplayShowCustomEnabled(true);
        supportActionBar.setDefaultDisplayHomeAsUpEnabled(false);
        supportActionBar.setDisplayUseLogoEnabled(false);
        supportActionBar.setHomeAsUpIndicator(null);
        supportActionBar.setCustomView(inflate);
        if (QsbkApp.currentUser == null) {
            finish();
            return;
        }
        this.l = TextUtils.isEmpty(this.l) ? "pass" : this.l;
        this.g = findViewById(R.id.phone_container);
        this.h = findViewById(R.id.pwd_container);
        this.a = (CaptchaButton) findViewById(R.id.get_code);
        this.b = (EditText) findViewById(R.id.phone);
        this.c = (EditText) findViewById(R.id.captcha);
        this.d = (EditText) findViewById(R.id.pwd);
        this.e = (Button) findViewById(R.id.bind);
        this.c.addTextChangedListener(this.s);
        this.b.addTextChangedListener(this.s);
        this.d.addTextChangedListener(this.s);
        this.b.addTextChangedListener(this.t);
        this.a.setOnClickListener(new do(this));
        f();
        g();
    }

    private void f() {
        if (TextUtils.isEmpty(this.n) && QsbkApp.currentUser.isBindSocail() && TextUtils.isEmpty(this.m)) {
            SocialVerifyActivity.launch(this);
        }
    }

    private void g() {
        View view = this.h;
        int i = ("pass".equals(this.l) || !QsbkApp.currentUser.hasPwd()) ? 0 : 8;
        view.setVisibility(i);
        this.d.setHint(QsbkApp.currentUser.hasPwd() ? R.string.enter_pwd : R.string.set_pwd_6_16);
        if (!QsbkApp.currentUser.hasPwd()) {
            this.d.setFilters(new InputFilter[]{new LengthFilter(16)});
        }
        this.a.setOnTickListener(new dp(this));
        this.e.setOnClickListener(new dq(this));
        if (!TextUtils.isEmpty(this.n)) {
            this.h.setVisibility(8);
        }
    }

    private void i() {
        String str = Constants.REBIND_PHONE;
        this.p = new EncryptHttpTask(str, str, new dr(this));
        this.p.setMapParams(l());
        this.p.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, new Void[0]);
        a(false);
    }

    private void j() {
        if (QsbkApp.currentUser == null || QsbkApp.currentUser.hasPwd() || Util.isValidPwd(this.k)) {
            String str = Constants.BIND_PHONE;
            this.q = new EncryptHttpTask(str, str, new ds(this));
            this.q.setMapParams(k());
            this.q.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, new Void[0]);
            a(false);
            return;
        }
        ToastAndDialog.makeNegativeToast(this, getString(R.string.enter_new_pwd)).show();
    }

    private HashMap<String, Object> k() {
        HashMap<String, Object> hashMap = new HashMap();
        hashMap.put("phone", this.i);
        hashMap.put("code", this.j);
        hashMap.put("sns_type", n());
        hashMap.put("token", m());
        if (!QsbkApp.currentUser.hasPwd()) {
            hashMap.put("set_password", this.k);
        }
        return hashMap;
    }

    private HashMap<String, Object> l() {
        HashMap<String, Object> hashMap = new HashMap();
        hashMap.put("phone", this.i);
        hashMap.put("code", this.j);
        hashMap.put("secret", this.n);
        return hashMap;
    }

    private Object m() {
        if ("pass".equals(this.l)) {
            return this.k;
        }
        return this.m;
    }

    private Object n() {
        return this.l;
    }

    private void q() {
        this.i = this.b.getText().toString().trim();
        this.j = this.c.getText().toString().trim();
        this.k = this.d.getText().toString().trim();
        if (a(this.i) && b(this.j) && c(this.k)) {
            this.e.setEnabled(true);
        } else if (!TextUtils.isEmpty(this.n) && b(this.j)) {
            this.e.setEnabled(true);
        } else if (a(this.i) && b(this.j) && !"pass".equals(this.l) && QsbkApp.currentUser.hasPwd()) {
            this.e.setEnabled(true);
        } else {
            this.e.setEnabled(false);
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

    private void r() {
        Object obj = TextUtils.isEmpty(this.n) ? "bind_phone" : "rebind_phone";
        String str = Constants.GET_CODE;
        this.r = new EncryptHttpTask(str, str, new dk(this));
        Map hashMap = new HashMap();
        hashMap.put("purpose", obj);
        hashMap.put("phone", this.i);
        this.r.setMapParams(hashMap);
        this.r.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, new Void[0]);
    }

    protected void onDestroy() {
        super.onDestroy();
        if (this.b != null) {
            this.b.removeTextChangedListener(this.t);
            this.b.removeTextChangedListener(this.s);
        }
        if (this.c != null) {
            this.c.removeTextChangedListener(this.s);
        }
        if (this.d != null) {
            this.d.removeTextChangedListener(this.s);
        }
        if (this.p != null) {
            this.p.cancel(true);
        }
        if (this.q != null) {
            this.q.cancel(true);
        }
        if (this.r != null) {
            this.r.cancel(true);
        }
    }

    private void a(boolean z) {
        if (this.f == null) {
            this.f = ProgressDialog.show(this, null, "请稍候...", true, z);
        }
        this.f.setCancelable(z);
        this.f.show();
    }

    private void s() {
        if (this.f != null) {
            this.f.dismiss();
        }
    }
}
