package qsbk.app.im;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.os.Build.VERSION;
import android.os.Bundle;
import android.text.TextUtils;
import android.webkit.CookieManager;
import android.webkit.CookieSyncManager;
import android.webkit.WebSettings.RenderPriority;
import java.util.HashMap;
import java.util.Map;
import qsbk.app.QsbkApp;
import qsbk.app.cafe.Jsnativeinteraction.ui.QsbkWebViewClient;

public class GameWebViewActivity extends OfficialMsgDetailActivity {
    private String f;

    public static void launch(Context context, String str, String str2, String str3) {
        Intent intent = new Intent(context, GameWebViewActivity.class);
        intent.putExtra("url", str);
        intent.putExtra("title", str2);
        intent.putExtra("type", str3);
        context.startActivity(intent);
    }

    protected void a(Bundle bundle) {
        setActionbarBackable();
        h();
        i();
        j();
        b("");
        a(this.a);
    }

    protected void i() {
        super.i();
        this.f = this.c.getStringExtra("type");
    }

    @SuppressLint({"SetJavaScriptEnabled"})
    protected void j() {
        this.k.getSettings().setJavaScriptEnabled(true);
        this.k.getSettings().setDomStorageEnabled(true);
        this.k.getSettings().setRenderPriority(RenderPriority.HIGH);
        this.k.setWebViewClient(new QsbkWebViewClient());
        if (VERSION.SDK_INT >= 11) {
            getWindow().setFlags(16777216, 16777216);
        }
        String path = getApplicationContext().getDir("database", 0).getPath();
        this.k.getSettings().setDatabaseEnabled(true);
        this.k.getSettings().setDatabasePath(path);
        this.k.getSettings().setRenderPriority(RenderPriority.HIGH);
        this.k.setWebChromeClient(new dd(this));
    }

    protected void a(String str) {
        if (TextUtils.isEmpty(str)) {
            throw new IllegalArgumentException();
        }
        CookieSyncManager.createInstance(this);
        CookieManager instance = CookieManager.getInstance();
        instance.setAcceptCookie(true);
        instance.removeSessionCookie();
        if (QsbkApp.currentUser == null) {
            this.k.loadUrl(str);
            CookieSyncManager.getInstance().sync();
            return;
        }
        CookieSyncManager.getInstance().sync();
        Map hashMap = new HashMap();
        hashMap.put("token", QsbkApp.currentUser.token);
        this.k.loadUrl(str, hashMap);
    }
}
