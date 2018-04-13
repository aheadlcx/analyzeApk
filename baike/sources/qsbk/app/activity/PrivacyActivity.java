package qsbk.app.activity;

import android.content.Intent;
import android.os.Bundle;
import qsbk.app.QsbkApp;
import qsbk.app.R;
import qsbk.app.activity.base.BaseActionBarActivity;
import qsbk.app.im.IMNotifyManager;
import qsbk.app.widget.NotificationSettingItem;
import qsbk.app.widget.SettingItem;

public class PrivacyActivity extends BaseActionBarActivity {
    private NotificationSettingItem a;
    private SettingItem b;

    protected /* synthetic */ CharSequence getCustomTitle() {
        return f();
    }

    protected String f() {
        return "设置";
    }

    protected int a() {
        return R.layout.layout_privacy;
    }

    protected void a(Bundle bundle) {
        setActionbarBackable();
        this.a = (NotificationSettingItem) findViewById(R.id.network_publish_dynamic);
        this.a.setChecked(IMNotifyManager.isInvisUserCenter(this));
        this.a.setOnCheckedChangeListener(new zx(this));
        this.b = (SettingItem) findViewById(R.id.black_list_manage);
        this.b.setOnClickListener(new zy(this));
    }

    public void gotoPersonalBlackListActivity() {
        if (QsbkApp.currentUser != null) {
            startActivity(new Intent(this, BlacklistActivity.class));
        } else {
            a(null);
        }
    }

    private void a(Class<?> cls) {
        Intent intent = new Intent(this, ActionBarLoginActivity.class);
        if (cls != null) {
            intent.putExtra("targetClass", cls);
        }
        startActivity(intent);
    }

    protected void onStop() {
        super.onStop();
        if (g()) {
            IMNotifyManager.saveSettingToCloud();
        }
    }

    private boolean g() {
        return QsbkApp.currentUser != null;
    }
}
