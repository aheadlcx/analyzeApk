package qsbk.app.core.web.ui;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import java.util.HashMap;
import qsbk.app.core.web.plugin.Plugin;
import qsbk.app.core.web.route.IWebResponse;

public abstract class WebFragment extends Fragment implements WebInterface {
    protected QsbkWebView a;
    protected Context b;
    protected HashMap<String, Class<? extends Plugin>> c = new HashMap();
    private Plugin d;

    protected abstract View a();

    protected abstract QsbkWebView b();

    protected abstract void c();

    public void onAttach(Activity activity) {
        super.onAttach(activity);
        this.b = activity;
    }

    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        c();
    }

    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        return d();
    }

    private View d() {
        View a = a();
        a(b());
        return a;
    }

    private void a(QsbkWebView qsbkWebView) {
        this.a = qsbkWebView;
        this.a.init(this, this.c);
        this.a.setWebViewClient(new QsbkWebViewClient());
        this.a.setWebChromeClient(new k(this, this.a.getExposeApi()));
    }

    public void reqWeb(String str, String str2, String str3, IWebResponse iWebResponse) {
        if (this.a != null) {
            this.a.reqWeb(str, str2, str3, iWebResponse);
        }
    }

    public void onActivityCreated(Bundle bundle) {
        super.onActivityCreated(bundle);
    }

    public void onDestroy() {
        super.onDestroy();
        if (this.a != null) {
            this.a.onDestroy();
        }
    }

    public void startActivityForResult(Plugin plugin, Intent intent, int i) {
        this.d = plugin;
        startActivityForResult(intent, i);
    }

    public void onActivityResult(int i, int i2, Intent intent) {
        if (this.d != null) {
            this.d.onActivityResult(i, i2, intent);
        }
        super.onActivityResult(i, i2, intent);
    }

    public Activity getCurActivity() {
        return getActivity();
    }

    public boolean onKeyDown(int i) {
        if (i != 4 || this.a == null || !this.a.canGoBack()) {
            return false;
        }
        this.a.goBack();
        return true;
    }
}
