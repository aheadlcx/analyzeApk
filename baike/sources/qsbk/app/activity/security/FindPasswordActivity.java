package qsbk.app.activity.security;

import android.os.Bundle;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import qsbk.app.Constants;
import qsbk.app.R;
import qsbk.app.activity.base.BaseActionBarActivity;
import qsbk.app.utils.DeviceUtils;

public class FindPasswordActivity extends BaseActionBarActivity {
    protected String a;
    WebView b;
    WebViewClient c = new p(this);

    protected /* synthetic */ CharSequence getCustomTitle() {
        return f();
    }

    protected String f() {
        return "忘记密码";
    }

    protected int a() {
        return R.layout.about;
    }

    protected void a(Bundle bundle) {
        g();
        this.b.loadUrl("http://www.qiushibaike.com/new4/fetchpass?" + this.a);
    }

    protected void g() {
        setActionbarBackable();
        this.a = "uuid=" + DeviceUtils.getAndroidId() + "&Source=" + "android_" + Constants.localVersionName;
        this.b = (WebView) findViewById(R.id.about);
        this.b.setScrollBarStyle(0);
        this.b.getSettings().setBuiltInZoomControls(true);
        this.b.getSettings().setJavaScriptEnabled(true);
        this.b.setWebViewClient(this.c);
    }
}
