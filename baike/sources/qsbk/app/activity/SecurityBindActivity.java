package qsbk.app.activity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import org.json.JSONObject;
import qsbk.app.Constants;
import qsbk.app.QsbkApp;
import qsbk.app.R;
import qsbk.app.activity.base.BaseActionBarActivity;
import qsbk.app.activity.security.EmailBindActivity;
import qsbk.app.core.AsyncTask;
import qsbk.app.http.SimpleHttpTask;
import qsbk.app.model.UserInfo;
import qsbk.app.utils.SharePreferenceUtils;
import qsbk.app.utils.TipsManager;
import qsbk.app.widget.SecurityBindView;

public class SecurityBindActivity extends BaseActionBarActivity {
    public static final int REQUEST_CODE_BIND_PHONE = 21;
    public static final int REQUEST_CODE_EMAIL = 20;
    public static final int REQUEST_CODE_REPLACE_PHONE = 22;
    private View a;
    private SecurityBindView b;
    private SecurityBindView c;
    private SecurityBindView d;
    private ProgressDialog e;

    protected /* synthetic */ CharSequence getCustomTitle() {
        return f();
    }

    protected String f() {
        return getResources().getString(R.string.account_security);
    }

    protected int a() {
        return R.layout.security_bind;
    }

    protected void a(Bundle bundle) {
        if (QsbkApp.currentUser != null) {
            setActionbarBackable();
            this.a = findViewById(R.id.change_pwd_container);
            this.b = (SecurityBindView) findViewById(R.id.email);
            this.c = (SecurityBindView) findViewById(R.id.phone);
            this.d = (SecurityBindView) findViewById(R.id.social);
            this.d.setDescription("绑定/解绑");
            TipsManager.setShowedSecurityBind(this);
            m();
            n();
            return;
        }
        finish();
    }

    protected void onActivityResult(int i, int i2, Intent intent) {
        super.onActivityResult(i, i2, intent);
        a(i, i2, intent);
    }

    private void g() {
        if (this.e == null) {
            this.e = ProgressDialog.show(this, null, "请稍候...", true, false);
        }
        this.e.show();
    }

    private void i() {
        if (this.e != null) {
            this.e.dismiss();
        }
    }

    private void j() {
        g();
        new SimpleHttpTask(Constants.VERIFY_EMAIL, new acb(this)).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, new Void[0]);
    }

    protected void onDestroy() {
        super.onDestroy();
    }

    private void k() {
        BindPhoneActivity.launchForResult(this, 21);
    }

    private void a(String str) {
        BindPhoneActivity.launchForResult(this, str, 21);
    }

    private boolean l() {
        return ("active".equalsIgnoreCase(QsbkApp.currentUser.state) || UserInfo.STATE_BONDED.equalsIgnoreCase(QsbkApp.currentUser.state)) ? false : true;
    }

    private void b(String str) {
        Intent intent = new Intent(this, EmailBindActivity.class);
        Bundle bundle = new Bundle();
        EmailBindActivity.makeAction(bundle, str, QsbkApp.currentUser.email);
        intent.putExtras(bundle);
        startActivityForResult(intent, 20);
    }

    private boolean c(String str) {
        return TextUtils.isEmpty(str) || str.trim().length() == 0;
    }

    private void m() {
        int i = 8;
        boolean z = false;
        if (c(QsbkApp.currentUser.email)) {
            this.b.setMainText(getString(R.string.bind_email));
            this.b.setStatus(1);
        } else if (UserInfo.STATE_BONDED.equalsIgnoreCase(QsbkApp.currentUser.state)) {
            this.b.setMainText(QsbkApp.currentUser.email);
            this.b.setStatus(3);
        } else {
            this.b.setMainText(QsbkApp.currentUser.email);
            this.b.setStatus(2);
        }
        this.b.setOnClickListener(new acc(this));
        if (c(QsbkApp.currentUser.phone)) {
            this.c.setMainText(getString(R.string.bind_phone));
            this.c.setStatus(1);
        } else {
            this.c.setMainText(QsbkApp.currentUser.phone);
            this.c.setStatus(3);
        }
        this.c.setOnClickListener(new ace(this));
        this.d.setOnClickListener(new aci(this));
        if (c(QsbkApp.currentUser.email) && c(QsbkApp.currentUser.wx) && c(QsbkApp.currentUser.qq) && c(QsbkApp.currentUser.wb) && !QsbkApp.currentUser.hasPhone()) {
            findViewById(R.id.tips).setVisibility(0);
        } else {
            findViewById(R.id.tips).setVisibility(8);
        }
        View view = this.a;
        if (QsbkApp.currentUser.hasPwd()) {
            i = 0;
        }
        view.setVisibility(i);
        this.a.setOnClickListener(new acj(this));
        SecurityBindView securityBindView = this.c;
        if (!QsbkApp.currentUser.hasPhone()) {
            z = true;
        }
        securityBindView.setTipVisibility(z);
    }

    protected void onResume() {
        boolean z = true;
        super.onResume();
        if (QsbkApp.currentUser == null) {
            finish();
            return;
        }
        boolean z2;
        n();
        this.a.setVisibility(QsbkApp.currentUser.hasPwd() ? 0 : 8);
        SecurityBindView securityBindView = this.d;
        if (QsbkApp.currentUser.isBindSocail()) {
            z2 = false;
        } else {
            z2 = true;
        }
        securityBindView.setTipVisibility(z2);
        SecurityBindView securityBindView2 = this.c;
        if (QsbkApp.currentUser.hasPhone()) {
            z = false;
        }
        securityBindView2.setTipVisibility(z);
    }

    private void n() {
        String str = Constants.MY_INFO;
        g();
        new SimpleHttpTask(str, new ack(this)).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, new Void[0]);
    }

    private void a(JSONObject jSONObject) {
        if (QsbkApp.currentUser != null) {
            UserInfo.updateServerJson(QsbkApp.currentUser, jSONObject);
            SharePreferenceUtils.setSharePreferencesValue("loginUser", QsbkApp.currentUser.toString());
        }
    }

    private void a(int i, int i2, Intent intent) {
        if (i2 == -1) {
            switch (i) {
                case 20:
                    if (intent != null && !TextUtils.isEmpty(intent.getStringExtra("email"))) {
                        n();
                        return;
                    }
                    return;
                case 21:
                    n();
                    return;
                case 22:
                    if (intent != null) {
                        a(intent.getStringExtra("secret"));
                        return;
                    }
                    return;
                default:
                    return;
            }
        }
    }
}
