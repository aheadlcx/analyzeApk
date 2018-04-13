package qsbk.app.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.content.LocalBroadcastManager;
import android.view.KeyEvent;
import android.view.View;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import qsbk.app.QsbkApp;
import qsbk.app.R;
import qsbk.app.activity.base.BaseActionBarActivity;
import qsbk.app.core.AsyncTask;
import qsbk.app.utils.FileUtils;
import qsbk.app.utils.ListViewHelper;
import qsbk.app.utils.TipsManager;
import qsbk.app.utils.UIHelper;
import qsbk.app.utils.UserLogoutHelper;
import qsbk.app.utils.image.Utils;
import qsbk.app.widget.SettingItem;

public class ActionBarUserSettingNavi extends BaseActionBarActivity {
    public static final int RESULT_CHANGED_THEME = 10;
    private SettingItem a;
    private SettingItem b;
    private SettingItem c;
    private SettingItem d;
    private SettingItem e;
    private SettingItem f;
    private SettingItem g;
    private SettingItem h;
    private View i;
    private SettingItem j;
    private boolean k = true;
    private boolean l = true;
    private List<File> m = new ArrayList();
    private AsyncTask n;
    private UserLogoutHelper o = null;
    private int p = 0;

    protected /* synthetic */ CharSequence getCustomTitle() {
        return c();
    }

    private static void b(List<File> list) {
        if (list != null) {
            for (File file : list) {
                if (file.isFile()) {
                    file.delete();
                }
            }
        }
    }

    protected void c_() {
        if (UIHelper.isNightTheme()) {
            setTheme(R.style.Setting_Night);
        } else {
            setTheme(R.style.Setting);
        }
    }

    private void f() {
        if (QsbkApp.currentUser != null) {
            this.h.setVisibility(0);
            if (this.i != null) {
                this.i.setVisibility(0);
                return;
            }
            return;
        }
        this.h.setVisibility(8);
        if (this.i != null) {
            this.i.setVisibility(8);
        }
    }

    protected void onResume() {
        f();
        this.a.showTip(TipsManager.shouldShowSecurityBind(this));
        super.onResume();
    }

    private void g() {
        this.a = (SettingItem) findViewById(R.id.security_safe);
        this.b = (SettingItem) findViewById(R.id.message_info_remind);
        this.c = (SettingItem) findViewById(R.id.small_paper);
        this.d = (SettingItem) findViewById(R.id.personal_privacy);
        this.e = (SettingItem) findViewById(R.id.common);
        this.j = (SettingItem) findViewById(R.id.external_cache_size_settingitem);
        this.f = (SettingItem) findViewById(R.id.about);
        this.g = (SettingItem) findViewById(R.id.feedback);
        this.h = (SettingItem) findViewById(R.id.logout);
        this.i = findViewById(R.id.about_divider);
        this.o = new UserLogoutHelper(this);
        this.o.setOnLogoutFinish(new af(this));
        this.h.setOnClickListener(new ak(this));
        f();
        findViewById(R.id.left).setOnClickListener(new al(this));
        findViewById(R.id.right).setOnClickListener(new am(this));
    }

    private void i() {
        this.a.setOnClickListener(new an(this));
        this.b.setOnClickListener(new ao(this));
        this.c.setOnClickListener(new ap(this));
        this.d.setOnClickListener(new aq(this));
        this.e.setOnClickListener(new ar(this));
        this.g.setOnClickListener(new ag(this));
        this.f.setOnClickListener(new ah(this));
        this.n = new ai(this).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, new Void[0]);
        this.j.setOnClickListener(new aj(this));
    }

    private long c(List<File> list) {
        File externalCacheDir = Utils.getExternalCacheDir(this);
        if (externalCacheDir == null || externalCacheDir.getAbsolutePath().equalsIgnoreCase(getCacheDir().getAbsolutePath())) {
            return 0;
        }
        return 0 + FileUtils.getFileSize(externalCacheDir, list);
    }

    public void gotoSecuritySafeActivity() {
        if (QsbkApp.currentUser != null) {
            startActivity(new Intent(this, SecurityBindActivity.class));
            TipsManager.setShowedSecurityBind(this);
            Intent intent = new Intent(TipsManager.SHOW_SECURITY_BIND);
            intent.putExtra(TipsManager.SHOW_SECURITY_BIND, false);
            LocalBroadcastManager.getInstance(this).sendBroadcast(intent);
            return;
        }
        a(null);
    }

    public void gotoMessageInfoRemindActivity() {
        startActivity(new Intent(this, NotificationSettingActivity.class));
    }

    public void gotoSmallPaperActivity() {
        if (QsbkApp.currentUser != null) {
            startActivity(new Intent(this, MessageInfoReminderActivity.class));
        } else {
            a(null);
        }
    }

    public void gotoPersonalPrivacyActivity() {
        if (QsbkApp.currentUser != null) {
            startActivity(new Intent(this, PrivacyActivity.class));
        } else {
            a(null);
        }
    }

    public void gotoCommonActivity() {
        startActivity(new Intent(this, CommonSettingActivity.class));
    }

    public void gotoAboutActivity() {
        Intent intent = new Intent();
        intent.setClass(this, AboutSettingActivity.class);
        a(intent);
    }

    protected String c() {
        return "设置";
    }

    protected int a() {
        return R.layout.actionbar_activity_usersetting_navi;
    }

    protected void a(Bundle bundle) {
        setActionbarBackable();
        g();
        i();
    }

    protected void a(Intent intent) {
        startActivity(intent);
    }

    protected void a(Class<?> cls) {
        Intent intent = new Intent(this, ActionBarLoginActivity.class);
        if (cls != null) {
            intent.putExtra("targetClass", cls);
        }
        startActivity(intent);
    }

    public void finish() {
        if (this.n != null) {
            this.n.cancel(true);
        }
        setResult(-1);
        super.finish();
    }

    public boolean onKeyDown(int i, KeyEvent keyEvent) {
        if (i != 4) {
            return super.onKeyDown(i, keyEvent);
        }
        finish();
        return true;
    }

    protected void onNewIntent(Intent intent) {
        setIntent(intent);
    }

    protected void onStop() {
        if (this.k != this.l) {
            ListViewHelper.setSelectionSaveble(this, this.k);
        }
        super.onStop();
    }
}
