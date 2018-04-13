package qsbk.app.activity;

import android.os.Build;
import android.os.Bundle;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import qsbk.app.ConfigManager;
import qsbk.app.Constants;
import qsbk.app.QsbkApp;
import qsbk.app.R;
import qsbk.app.activity.base.BaseActionBarActivity;
import qsbk.app.utils.DeviceUtils;
import qsbk.app.utils.LogUtil;
import qsbk.app.utils.TimeDelta;
import qsbk.app.utils.UIHelper;
import qsbk.app.widget.CommonWebView;

public class About extends BaseActionBarActivity {
    WebView a;
    TimeDelta b = new TimeDelta();
    WebChromeClient c = new a(this);
    WebViewClient d = new c(this);
    private String e;
    private String f;

    private class a {
        final /* synthetic */ About a;

        private a(About about) {
            this.a = about;
        }

        public void jsMethod(String str) {
            this.a.a.loadUrl("file:///android_asset/feedback_success.html");
        }
    }

    protected /* synthetic */ CharSequence getCustomTitle() {
        return b();
    }

    public static String readData(InputStream inputStream, String str) throws Exception {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        byte[] bArr = new byte[1024];
        while (true) {
            int read = inputStream.read(bArr);
            if (read != -1) {
                byteArrayOutputStream.write(bArr, 0, read);
            } else {
                bArr = byteArrayOutputStream.toByteArray();
                byteArrayOutputStream.close();
                inputStream.close();
                return new String(bArr, str);
            }
        }
    }

    protected void onResume() {
        super.onResume();
        LogUtil.d("load local html start: " + this.b.getDelta());
        if ("about".equals(this.f)) {
            CharSequence config = ConfigManager.getInstance().getConfig(ConfigManager.KEY_BUILD_VERSION, "");
            try {
                this.e = readData(getAssets().open("about.html"), "UTF-8");
                this.e = this.e.replace("#AppVersion#", Constants.localVersionName).replace("#ThemeStyle#", p()).replace("#BuildVersion#", config).replace("#BuildChannel#", ConfigManager.getInstance().getChannel());
            } catch (IOException e) {
                e.printStackTrace();
            } catch (Exception e2) {
                e2.printStackTrace();
            }
            this.a.loadDataWithBaseURL("file:///android_asset/", this.e, "text/html", "utf-8", null);
        } else {
            try {
                this.e = readData(getAssets().open("feedback.html"), "UTF-8");
                this.e = this.e.replace("#PH_FEEDBACK_URL#", Constants.FEEDBACK).replace("#ThemeStyle#", p());
                this.e = this.e.replace("#PH_SOURCE#", "android_" + Constants.localVersionName);
                if (QsbkApp.currentUser == null) {
                    this.e = this.e.replace("#PH_USERID#", DeviceUtils.getAndroidId());
                } else {
                    this.e = this.e.replace("#PH_USERID#", QsbkApp.currentUser.userId + "|" + DeviceUtils.getAndroidId());
                }
                this.e = this.e.replace("#PH_THEME#", UIHelper.getTheme());
                this.e = this.e.replace("#PH_DEVICE#", Build.FINGERPRINT);
                this.a.loadDataWithBaseURL("file:///android_asset/", this.e, "text/html", "utf-8", null);
            } catch (Exception e22) {
                e22.printStackTrace();
            }
        }
        LogUtil.d("load local html end: " + this.b.getDelta());
    }

    protected int a() {
        return R.layout.about;
    }

    protected void a(Bundle bundle) {
        setActionbarBackable();
        this.f = getIntent().getStringExtra("targetPage");
        LogUtil.d("target page:" + this.f);
        this.a = (WebView) findViewById(R.id.about);
        this.a.setScrollBarStyle(0);
        this.a.getSettings().setBuiltInZoomControls(true);
        this.a.getSettings().setJavaScriptEnabled(true);
        this.a.setWebViewClient(this.d);
        this.a.setWebChromeClient(this.c);
        this.a.addJavascriptInterface(new a(), "stub");
        this.a.setBackgroundColor(0);
    }

    protected String b() {
        if ("about".equals(this.f)) {
            return getResources().getString(R.string.title_about);
        }
        return getResources().getString(R.string.title_feedback);
    }

    protected void onDestroy() {
        if (this.a != null && (this.a instanceof CommonWebView)) {
            ((CommonWebView) this.a).onDestroy();
        }
        super.onDestroy();
    }
}
