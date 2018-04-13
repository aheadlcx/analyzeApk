package qsbk.app.activity;

import android.content.Context;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.animation.AnimationUtils;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import java.util.HashMap;
import org.json.JSONObject;
import qsbk.app.QsbkApp;
import qsbk.app.R;
import qsbk.app.activity.base.BaseActionBarActivity;
import qsbk.app.database.QsbkDatabase;
import qsbk.app.utils.HttpUtils;
import qsbk.app.utils.LogUtil;
import qsbk.app.utils.SharePreferenceUtils;
import qsbk.app.utils.ToastAndDialog;

public abstract class ActionBarLoginRegisterBaseActivity extends BaseActionBarActivity {
    protected Context a = this;
    protected JSONObject b = null;
    protected Class<?> c = null;
    protected EditText d;
    protected EditText e;

    protected /* synthetic */ CharSequence getCustomTitle() {
        return c();
    }

    public void handleToken(JSONObject jSONObject) {
        if (jSONObject != null) {
            QsbkApp.valuesMap = new HashMap();
            QsbkApp.getInstance().updateCurrentUserInfo(jSONObject);
        }
    }

    protected String c() {
        return null;
    }

    protected void onCreate(Bundle bundle) {
        this.c = (Class) getIntent().getSerializableExtra("targetClass");
        super.onCreate(bundle);
        setActionbarBackable();
    }

    protected void a(Bundle bundle) {
        setTitle(c());
        this.d = (EditText) findViewById(R.id.userName);
        this.d.setOnEditorActionListener(new ae(this));
        this.e = (EditText) findViewById(R.id.passwd);
        if ("VERIFY_FAILED".equals(getIntent().getStringExtra("wrongType"))) {
            this.d.setText(SharePreferenceUtils.getSharePreferencesValue("loginName"));
        }
    }

    private String j() {
        return this.d.getText().toString().trim();
    }

    protected void f() {
        SharePreferenceUtils.setSharePreferencesValue("loginName", j());
    }

    private void a(View view) {
        View findViewById = findViewById(R.id.id_input_group);
        if (findViewById != null) {
            findViewById.setAnimation(AnimationUtils.loadAnimation(this, R.anim.shake));
            findViewById.requestLayout();
        }
    }

    protected boolean g() {
        if (!HttpUtils.isNetworkConnected(this.a)) {
            ToastAndDialog.makeNegativeToast(this.a, this.a.getResources().getString(R.string.network_not_connected)).show();
            LogUtil.d("network");
            return false;
        } else if (this.d.getText().toString().trim().equals("")) {
            a(this.d);
            LogUtil.d("name");
            return false;
        } else if (this.d.getText().toString().trim().contains("\"") || this.d.getText().toString().trim().contains("'")) {
            LogUtil.d("name invalid");
            ToastAndDialog.makeNegativeToast(this.a, "用户名不能包含特殊字符").show();
            return false;
        } else if (!TextUtils.isEmpty(this.e.getText().toString().trim())) {
            return true;
        } else {
            a(this.e);
            return false;
        }
    }

    protected void d_() {
        if (getCurrentFocus() != null) {
            ((InputMethodManager) getSystemService("input_method")).hideSoftInputFromWindow(getCurrentFocus().getWindowToken(), 2);
        }
    }

    protected HashMap<String, Object> i() {
        HashMap<String, Object> hashMap = new HashMap();
        hashMap.put(QsbkDatabase.LOGIN, this.d.getText().toString().trim());
        hashMap.put("pass", this.e.getText().toString().trim());
        return hashMap;
    }
}
