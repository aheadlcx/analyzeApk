package qsbk.app.im;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.InvocationTargetException;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;
import qsbk.app.QsbkApp;
import qsbk.app.R;
import qsbk.app.core.AsyncTask;
import qsbk.app.core.web.plugin.Plugin;
import qsbk.app.core.web.ui.QsbkWebView;
import qsbk.app.core.web.ui.WebInterface;
import qsbk.app.http.SimpleHttpTask;
import qsbk.app.utils.UIHelper;

public class IMChatingUrlContentDisplayActivity extends Activity implements WebInterface {
    private static final String a = IMChatingUrlContentDisplayActivity.class.getSimpleName();
    private static String b = "http://im.qiushibaike.com/open_link";
    private static String c = "http://im.qiushibaike.com/report_link";
    private QsbkWebView d;
    private ProgressBar e;
    private ImageView f;
    private TextView g;
    private ImageView h;
    private ImageView i;
    private ImageView j;
    private TextView k;
    private String l;
    private String m;

    protected void a() {
        if (UIHelper.isNightTheme()) {
            setTheme(R.style.Night);
        } else {
            setTheme(R.style.Day);
        }
    }

    protected void onCreate(Bundle bundle) {
        a();
        requestWindowFeature(1);
        super.onCreate(bundle);
        setContentView(R.layout.im_chating_url_content_display_activity);
        b();
        a(this.l);
    }

    private void a(String str) {
        if (TextUtils.isEmpty(str)) {
            throw new IllegalArgumentException();
        }
        this.d.loadUrl(str);
    }

    private void b() {
        this.m = getIntent().getStringExtra("url");
        try {
            this.l = b + "?link=" + URLEncoder.encode(this.m, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        this.e = (ProgressBar) findViewById(R.id.progressbar);
        this.d = (QsbkWebView) findViewById(R.id.webview);
        this.f = (ImageView) findViewById(R.id.ic_close);
        this.g = (TextView) findViewById(R.id.title);
        this.h = (ImageView) findViewById(R.id.ic_back);
        this.i = (ImageView) findViewById(R.id.ic_go);
        this.j = (ImageView) findViewById(R.id.ic_refresh);
        this.k = (TextView) findViewById(R.id.ic_report);
        this.f.setOnClickListener(new ga(this));
        this.d.init(this, new HashMap());
        this.d.getSettings().setJavaScriptEnabled(true);
        this.d.setWebViewClient(new gb(this));
        this.d.setWebChromeClient(new ge(this));
        this.k.setOnClickListener(new gf(this));
        this.j.setOnClickListener(new gi(this));
        this.h.setOnClickListener(new gj(this));
        this.i.setOnClickListener(new gk(this));
    }

    public void report(String str, String str2) {
        Map hashMap = new HashMap();
        hashMap.put("reason", str2);
        hashMap.put("user_id", Long.valueOf(Long.parseLong(QsbkApp.currentUser.userId)));
        hashMap.put("link", str);
        SimpleHttpTask simpleHttpTask = new SimpleHttpTask(c, new gl(this));
        simpleHttpTask.setMapParams(hashMap);
        simpleHttpTask.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, new Void[0]);
    }

    public void startActivityForResult(Plugin plugin, Intent intent, int i) {
    }

    public void setFocusPlugin(Plugin plugin) {
    }

    public Activity getCurActivity() {
        return this;
    }

    protected void onPause() {
        super.onPause();
        try {
            Class.forName("android.webkit.WebView").getMethod("onPause", (Class[]) null).invoke(this.d, (Object[]) null);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (IllegalArgumentException e2) {
            e2.printStackTrace();
        } catch (InvocationTargetException e3) {
            e3.printStackTrace();
        } catch (NoSuchMethodException e4) {
            e4.printStackTrace();
        } catch (ClassNotFoundException e5) {
            e5.printStackTrace();
        }
    }

    protected void onResume() {
        super.onResume();
        try {
            Class.forName("android.webkit.WebView").getMethod("onResume", (Class[]) null).invoke(this.d, (Object[]) null);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (IllegalArgumentException e2) {
            e2.printStackTrace();
        } catch (InvocationTargetException e3) {
            e3.printStackTrace();
        } catch (NoSuchMethodException e4) {
            e4.printStackTrace();
        } catch (ClassNotFoundException e5) {
            e5.printStackTrace();
        }
    }

    protected void onDestroy() {
        try {
            if (this.d != null) {
                this.d.loadUrl("");
            }
            this.d.onDestroy();
        } catch (Exception e) {
            e.printStackTrace();
        }
        super.onDestroy();
    }

    public boolean onKeyDown(int i) {
        if (i != 4 || this.d == null || !this.d.canGoBack()) {
            return false;
        }
        this.d.goBack();
        return true;
    }
}
