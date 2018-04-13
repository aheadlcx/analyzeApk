package com.alipay.sdk.app;

import android.app.Activity;
import android.content.res.Configuration;
import android.os.Bundle;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import com.alipay.sdk.app.statistic.a;
import com.alipay.sdk.util.l;

public class H5PayActivity extends Activity {
    private WebView a;
    private WebViewClient b;

    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        try {
            Bundle extras = getIntent().getExtras();
            String string = extras.getString("url");
            if (l.b(string)) {
                String string2 = extras.getString("cookie");
                super.requestWindowFeature(1);
                try {
                    this.a = l.a((Activity) this, string, string2);
                    this.b = new b(this);
                    this.a.setWebViewClient(this.b);
                    return;
                } catch (Throwable th) {
                    a.a("biz", "GetInstalledAppEx", th);
                    finish();
                    return;
                }
            }
            finish();
        } catch (Exception e) {
            finish();
        }
    }

    public void onBackPressed() {
        if (!this.a.canGoBack()) {
            h.a = h.a();
            finish();
        } else if (((b) this.b).c) {
            i a = i.a(i.NETWORK_ERROR.h);
            h.a = h.a(a.h, a.i, "");
            finish();
        }
    }

    public void finish() {
        a();
        super.finish();
    }

    public void a() {
        Object obj = PayTask.a;
        synchronized (obj) {
            try {
                obj.notify();
            } catch (Exception e) {
            }
        }
    }

    public void onConfigurationChanged(Configuration configuration) {
        super.onConfigurationChanged(configuration);
    }

    protected void onDestroy() {
        super.onDestroy();
        if (this.a != null) {
            this.a.removeAllViews();
            ((ViewGroup) this.a.getParent()).removeAllViews();
            try {
                this.a.destroy();
            } catch (Throwable th) {
            }
            this.a = null;
        }
        if (this.b != null) {
            b bVar = (b) this.b;
            bVar.b = null;
            bVar.a = null;
        }
    }
}
