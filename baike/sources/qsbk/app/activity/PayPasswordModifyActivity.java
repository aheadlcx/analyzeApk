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
import java.util.HashMap;
import java.util.Map;
import qsbk.app.Constants;
import qsbk.app.R;
import qsbk.app.activity.base.BaseActionBarActivity;
import qsbk.app.core.AsyncTask;
import qsbk.app.http.EncryptHttpTask;
import qsbk.app.http.HttpTask;

public class PayPasswordModifyActivity extends BaseActionBarActivity {
    EditText a;
    EditText b;
    EditText c;
    Button d;
    View e;
    ProgressDialog f;
    String g;
    String h;
    String i;
    HttpTask j;
    TextWatcher k = new zg(this);

    protected /* synthetic */ CharSequence getCustomTitle() {
        return f();
    }

    public static void launch(Activity activity) {
        activity.startActivity(new Intent(activity, PayPasswordModifyActivity.class));
    }

    protected String f() {
        return getString(R.string.modify_pay_pwd);
    }

    protected int a() {
        return R.layout.activity_pay_pwd_modify;
    }

    protected void a(Bundle bundle) {
        setActionbarBackable();
        this.a = (EditText) findViewById(R.id.old_pay_pwd);
        this.b = (EditText) findViewById(R.id.new_pwd);
        this.c = (EditText) findViewById(R.id.repeat_pwd);
        this.d = (Button) findViewById(R.id.done);
        this.d.setOnClickListener(new zh(this));
        this.e = findViewById(R.id.forget_pay_pwd);
        this.e.setOnClickListener(new zi(this));
    }

    private void g() {
        String str = Constants.MODIFY_PAY_PWD;
        this.j = new EncryptHttpTask(str, str, new zj(this));
        this.j.setMapParams(getParams());
        this.j.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, new Void[0]);
        a(false);
    }

    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        this.a.addTextChangedListener(this.k);
        this.b.addTextChangedListener(this.k);
        this.c.addTextChangedListener(this.k);
    }

    protected void onDestroy() {
        super.onDestroy();
        this.a.removeTextChangedListener(this.k);
        this.b.removeTextChangedListener(this.k);
        this.c.removeTextChangedListener(this.k);
        if (this.j != null) {
            this.j.cancel(true);
        }
    }

    private void i() {
        this.g = this.a.getText().toString().trim();
        this.h = this.b.getText().toString().trim();
        this.i = this.c.getText().toString().trim();
        if (TextUtils.isEmpty(this.g) || TextUtils.isEmpty(this.h) || TextUtils.isEmpty(this.i) || this.g.length() != 6 || this.h.length() != 6 || this.i.length() != 6) {
            this.d.setEnabled(false);
        } else {
            this.d.setEnabled(true);
        }
    }

    private void a(boolean z) {
        if (this.f == null) {
            this.f = ProgressDialog.show(this, null, "请稍候...", true, z);
        }
        this.f.setCancelable(z);
        this.f.show();
    }

    private void j() {
        if (this.f != null) {
            this.f.dismiss();
        }
    }

    public Map<String, Object> getParams() {
        Map hashMap = new HashMap();
        hashMap.put("old_password", this.g);
        hashMap.put("new_password", this.h);
        return hashMap;
    }
}
