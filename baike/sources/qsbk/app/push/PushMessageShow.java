package qsbk.app.push;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.NotificationManager;
import android.content.Intent;
import android.net.Uri;
import android.os.Build.VERSION;
import android.os.Bundle;
import android.text.TextUtils;
import android.widget.FrameLayout;
import java.util.HashMap;
import java.util.Map;
import qsbk.app.QsbkApp;
import qsbk.app.R;
import qsbk.app.activity.base.BaseActionBarActivity;
import qsbk.app.cafe.plugin.AccountPlugin;
import qsbk.app.cafe.plugin.SharePlugin;
import qsbk.app.core.web.plugin.Plugin;
import qsbk.app.core.web.ui.WebInterface;
import qsbk.app.database.QsbkDatabase;
import qsbk.app.utils.LogUtil;
import qsbk.app.utils.SharePreferenceUtils;
import qsbk.app.widget.CommonWebView;

public class PushMessageShow extends BaseActionBarActivity implements WebInterface {
    CommonWebView a;
    FrameLayout b;
    private String c = "通知";
    private String d;
    private Plugin e;

    protected /* synthetic */ CharSequence getCustomTitle() {
        return f();
    }

    private static String a(String str) {
        return SharePreferenceUtils.getSharePreferencesValue("push_msg_url_" + str);
    }

    public static String setOpenURL(String str) {
        long currentTimeMillis = System.currentTimeMillis();
        SharePreferenceUtils.setSharePreferencesValue("push_msg_url_" + currentTimeMillis, str);
        return String.valueOf(currentTimeMillis);
    }

    protected String f() {
        return this.c;
    }

    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        a(intent);
    }

    protected int a() {
        return R.layout.push_message_show;
    }

    protected void a(Bundle bundle) {
        g();
        a(getIntent());
    }

    private void a(Intent intent) {
        this.c = intent.getStringExtra("title");
        if (TextUtils.isEmpty(this.c)) {
            setTitle("通知");
        } else {
            setTitle(this.c);
        }
        String stringExtra = intent.getStringExtra("id");
        if (!TextUtils.isEmpty(stringExtra)) {
            this.d = a(stringExtra);
        }
        if (intent.getBooleanExtra("push", false)) {
            statPushLabel();
            ((NotificationManager) getSystemService("notification")).cancel(2);
        }
        LogUtil.d("url:" + this.d);
        LogUtil.d("PushActivity re init");
        boolean booleanExtra = intent.getBooleanExtra(QsbkDatabase.LOGIN, false);
        if (TextUtils.isEmpty(this.d)) {
            this.a.loadUrl("http://www.qiushibaike.com");
            return;
        }
        int indexOf = this.d.indexOf(63);
        if ((indexOf != -1 ? this.d.substring(0, indexOf) : this.d).endsWith(".apk")) {
            b(this.d);
        } else if (!booleanExtra || QsbkApp.currentUser == null) {
            this.a.loadUrl(this.d);
        } else {
            Map hashMap = new HashMap();
            hashMap.put("Qbtoken", QsbkApp.currentUser.token);
            hashMap.put("user_id", QsbkApp.currentUser.userId);
            this.a.loadUrl(this.d, hashMap);
        }
    }

    protected void onPause() {
        super.onPause();
        try {
            Class.forName("android.webkit.WebView").getMethod("onPause", (Class[]) null).invoke(this.a, (Object[]) null);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    protected void onDestroy() {
        try {
            if (this.a != null) {
                this.a.loadUrl("");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (this.a != null) {
            this.a.onDestroy();
        }
        super.onDestroy();
    }

    private void b(String str) {
        LogUtil.d("download url:" + str);
        startActivity(new Intent("android.intent.action.VIEW", Uri.parse(str)));
    }

    @SuppressLint({"SetJavaScriptEnabled"})
    protected void g() {
        setActionbarBackable();
        this.a = (CommonWebView) findViewById(R.id.about);
        this.b = (FrameLayout) findViewById(R.id.fl_video);
        this.a.getSettings().setJavaScriptEnabled(true);
        this.a.setWebChromeClient(new i(this));
        this.a.setWebViewClient(new j(this));
        if (VERSION.SDK_INT >= 21) {
            this.a.getSettings().setMixedContentMode(0);
        }
        HashMap hashMap = new HashMap();
        hashMap.put("share", SharePlugin.class);
        hashMap.put("account", AccountPlugin.class);
        this.a.initPluginProxy(this, hashMap);
    }

    public void startActivityForResult(Plugin plugin, Intent intent, int i) {
        this.e = plugin;
        startActivityForResult(intent, i);
    }

    public void setFocusPlugin(Plugin plugin) {
        this.e = plugin;
    }

    protected void onActivityResult(int i, int i2, Intent intent) {
        if (this.e != null) {
            this.e.onActivityResult(i, i2, intent);
        }
        super.onActivityResult(i, i2, intent);
    }

    public Activity getCurActivity() {
        return this;
    }
}
