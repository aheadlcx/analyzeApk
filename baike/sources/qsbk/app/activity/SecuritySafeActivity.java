package qsbk.app.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.content.LocalBroadcastManager;
import qsbk.app.R;
import qsbk.app.activity.base.BaseActionBarActivity;
import qsbk.app.utils.TipsManager;
import qsbk.app.utils.UIHelper;
import qsbk.app.widget.SettingItem;

public class SecuritySafeActivity extends BaseActionBarActivity {
    private SettingItem a;

    protected /* synthetic */ CharSequence getCustomTitle() {
        return f();
    }

    protected String f() {
        return "账号与安全";
    }

    protected int a() {
        return R.layout.security_safe_activity;
    }

    protected void c_() {
        if (UIHelper.isNightTheme()) {
            setTheme(R.style.Setting_Night);
        } else {
            setTheme(R.style.Setting);
        }
    }

    protected void a(Bundle bundle) {
        setActionbarBackable();
        this.a = (SettingItem) findViewById(R.id.security_bind);
        this.a.setOnClickListener(new acl(this));
    }

    void g() {
        startActivity(new Intent(this, SecurityBindActivity.class));
        TipsManager.setShowedSecurityBind(this);
        Intent intent = new Intent(TipsManager.SHOW_SECURITY_BIND);
        intent.putExtra(TipsManager.SHOW_SECURITY_BIND, false);
        LocalBroadcastManager.getInstance(this).sendBroadcast(intent);
    }
}
