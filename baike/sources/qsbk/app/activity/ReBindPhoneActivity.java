package qsbk.app.activity;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.text.TextWatcher;
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
import qsbk.app.widget.CaptchaButton;

public class ReBindPhoneActivity extends BaseActionBarActivity {
    EditText a;
    CaptchaButton b;
    Button c;
    TextView d;
    ProgressDialog e;
    EncryptHttpTask f;
    EncryptHttpTask g;
    TextWatcher h = new aar(this);
    private String i;
    private long j = 60000;
    private long k = 1000;

    protected /* synthetic */ CharSequence getCustomTitle() {
        return f();
    }

    public static void launchForResult(Activity activity, int i) {
        activity.startActivityForResult(new Intent(activity, ReBindPhoneActivity.class), i);
    }

    protected String f() {
        return getString(R.string.bind_phone);
    }

    protected int a() {
        return R.layout.activity_rebind_phone;
    }

    protected void a(Bundle bundle) {
        setActionbarBackable();
        if (QsbkApp.currentUser == null) {
            finish();
            return;
        }
        this.a = (EditText) findViewById(R.id.verify_code);
        this.b = (CaptchaButton) findViewById(R.id.get_code);
        this.d = (TextView) findViewById(R.id.phone);
        this.c = (Button) findViewById(R.id.submit);
        this.a.addTextChangedListener(this.h);
        this.b.setOnClickListener(new aas(this));
        this.b.setOnTickListener(new aat(this));
        this.d.setText(QsbkApp.currentUser.phone);
        this.c.setOnClickListener(new aau(this));
    }

    protected void onDestroy() {
        super.onDestroy();
        if (this.a != null) {
            this.a.removeTextChangedListener(this.h);
        }
        if (this.f != null) {
            this.f.cancel(false);
        }
        if (this.g != null) {
            this.g.cancel(false);
        }
    }

    private void g() {
        String str = Constants.VERIFY_CODE;
        this.i = this.a.getText().toString().trim();
        Map hashMap = new HashMap();
        hashMap.put("purpose", "unbind_phone");
        hashMap.put("code", this.i);
        this.f = new EncryptHttpTask(str, str, new aav(this));
        this.f.setMapParams(hashMap);
        this.f.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, new Void[0]);
        a(false);
    }

    public void getCode() {
        String str = Constants.GET_CODE;
        this.g = new EncryptHttpTask(str, str, new aaw(this));
        Map hashMap = new HashMap();
        hashMap.put("purpose", "unbind_phone");
        this.g.setMapParams(hashMap);
        this.g.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, new Void[0]);
    }

    private void a(boolean z) {
        if (this.e == null) {
            this.e = ProgressDialog.show(this, null, "请稍候...", true, z);
        }
        this.e.setCancelable(z);
        this.e.show();
    }

    private void i() {
        if (this.e != null) {
            this.e.dismiss();
        }
    }

    private void j() {
        this.i = this.a.getText().toString().trim();
        this.c.setEnabled(!TextUtils.isEmpty(this.i));
    }
}
