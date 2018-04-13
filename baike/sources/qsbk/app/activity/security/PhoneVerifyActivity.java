package qsbk.app.activity.security;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import java.util.HashMap;
import org.json.JSONObject;
import qsbk.app.Constants;
import qsbk.app.R;
import qsbk.app.activity.base.BaseActionBarActivity;
import qsbk.app.core.AsyncTask;
import qsbk.app.http.EncryptHttpTask;
import qsbk.app.utils.UIHelper;

public class PhoneVerifyActivity extends BaseActionBarActivity {
    private static final String b = PhoneVerifyActivity.class.getSimpleName();
    protected JSONObject a = null;
    private EditText c;
    private EditText d;
    private View e;
    private EncryptHttpTask f;

    protected /* synthetic */ CharSequence getCustomTitle() {
        return f();
    }

    public static void launchForResult(Activity activity, int i) {
        activity.startActivityForResult(new Intent(activity, PhoneVerifyActivity.class), i);
    }

    protected void c_() {
        if (UIHelper.isNightTheme()) {
            setTheme(R.style.Login_Night);
        } else {
            setTheme(R.style.Login);
        }
    }

    protected String f() {
        return "输入登录密码验证";
    }

    protected int a() {
        return R.layout.activity_phone_verify;
    }

    protected void a(Bundle bundle) {
        j();
        k();
        setActionbarBackable();
    }

    protected void onDestroy() {
        super.onDestroy();
        if (this.f != null && !this.f.isCancelled()) {
            this.f.cancel(true);
        }
    }

    private void j() {
        this.e = findViewById(R.id.done);
        this.c = (EditText) findViewById(R.id.phone);
        this.d = (EditText) findViewById(R.id.password);
        this.c.setHint("输入之前绑定的手机号");
        this.d.setHint("你当前登录的糗事百科密码");
    }

    private void k() {
        this.e.setOnClickListener(new q(this));
    }

    private boolean a(String str) {
        return !TextUtils.isEmpty(str);
    }

    private boolean b(String str) {
        return !TextUtils.isEmpty(str);
    }

    public void submit() {
        g();
        showLoading();
        this.f = new EncryptHttpTask(Constants.VERIFY_PHONE, new r(this));
        this.f.setMapParams(i());
        this.f.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, new Void[0]);
    }

    protected void g() {
        if (getCurrentFocus() != null) {
            ((InputMethodManager) getSystemService("input_method")).hideSoftInputFromWindow(getCurrentFocus().getWindowToken(), 2);
        }
    }

    protected HashMap<String, Object> i() {
        HashMap<String, Object> hashMap = new HashMap();
        hashMap.put("old_phone", this.c.getText().toString().trim());
        hashMap.put("password", this.d.getText().toString().trim());
        hashMap.put("purpose", "unbind_phone_by_password");
        return hashMap;
    }

    protected boolean d() {
        return true;
    }
}
