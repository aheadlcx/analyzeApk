package com.tencent.weibo.sdk.android.component;

import android.app.Activity;
import android.app.AlertDialog.Builder;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup.LayoutParams;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;
import com.ali.auth.third.login.LoginConstants;
import com.tencent.connect.common.Constants;
import com.tencent.weibo.sdk.android.api.util.BackGroudSeletor;
import com.tencent.weibo.sdk.android.api.util.Util;

public class Authorize extends Activity {
    public static final int ALERT_DOWNLOAD = 0;
    public static final int ALERT_FAV = 1;
    public static final int ALERT_NETWORK = 4;
    public static final int PROGRESS_H = 3;
    public static int WEBVIEWSTATE_1 = 0;
    Dialog _dialog;
    String _fileName;
    String _url;
    private String clientId = null;
    private ProgressDialog dialog;
    Handler handle = new Handler() {
        public void handleMessage(Message message) {
            super.handleMessage(message);
            switch (message.what) {
                case 100:
                    Authorize.this.showDialog(4);
                    return;
                default:
                    return;
            }
        }
    };
    private boolean isShow = false;
    private LinearLayout layout = null;
    String path;
    private String redirectUri = null;
    WebView webView;
    int webview_state = 0;

    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        if (Util.isNetworkAvailable(this)) {
            DisplayMetrics displayMetrics = new DisplayMetrics();
            getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
            BackGroudSeletor.setPix(displayMetrics.widthPixels + "x" + displayMetrics.heightPixels);
            try {
                this.clientId = Util.getConfig().getProperty("APP_KEY");
                this.redirectUri = Util.getConfig().getProperty("REDIRECT_URI");
                if (this.clientId == null || "".equals(this.clientId) || this.redirectUri == null || "".equals(this.redirectUri)) {
                    Toast.makeText(this, "请在配置文件中填写相应的信息", 0).show();
                }
                Log.d("redirectUri", this.redirectUri);
                getWindow().setFlags(1024, 1024);
                requestWindowFeature(1);
                this.path = "https://open.t.qq.com/cgi-bin/oauth2/authorize?client_id=" + this.clientId + "&response_type=token&redirect_uri=" + this.redirectUri + "&state=" + ((((int) Math.random()) * 1000) + 111);
                initLayout();
                return;
            } catch (Exception e) {
                e.printStackTrace();
                return;
            }
        }
        showDialog(4);
    }

    public boolean dispatchKeyEvent(KeyEvent keyEvent) {
        if (keyEvent.getAction() == 0 && keyEvent.getKeyCode() == 4) {
            setResult(1);
        }
        return super.dispatchKeyEvent(keyEvent);
    }

    public void initLayout() {
        LayoutParams layoutParams = new RelativeLayout.LayoutParams(-1, -1);
        LayoutParams layoutParams2 = new RelativeLayout.LayoutParams(-1, -2);
        LayoutParams layoutParams3 = new RelativeLayout.LayoutParams(-2, -2);
        this.dialog = new ProgressDialog(this);
        this.dialog.setProgressStyle(0);
        this.dialog.requestWindowFeature(1);
        this.dialog.setMessage("请稍后...");
        this.dialog.setIndeterminate(false);
        this.dialog.setCancelable(false);
        this.dialog.show();
        this.layout = new LinearLayout(this);
        this.layout.setLayoutParams(layoutParams);
        this.layout.setOrientation(1);
        View relativeLayout = new RelativeLayout(this);
        relativeLayout.setLayoutParams(layoutParams2);
        relativeLayout.setBackgroundDrawable(BackGroudSeletor.getdrawble("up_bg2x", getApplication()));
        relativeLayout.setGravity(0);
        View button = new Button(this);
        button.setBackgroundDrawable(BackGroudSeletor.createBgByImageIds(new String[]{"quxiao_btn2x", "quxiao_btn_hover"}, getApplication()));
        button.setText("取消");
        layoutParams3.addRule(9, -1);
        layoutParams3.addRule(15, -1);
        layoutParams3.leftMargin = 10;
        layoutParams3.topMargin = 10;
        layoutParams3.bottomMargin = 10;
        button.setLayoutParams(layoutParams3);
        button.setOnClickListener(new OnClickListener() {
            public void onClick(View view) {
                Authorize.this.finish();
            }
        });
        relativeLayout.addView(button);
        button = new TextView(this);
        button.setText("授权");
        button.setTextColor(-1);
        button.setTextSize(24.0f);
        layoutParams3 = new RelativeLayout.LayoutParams(-2, -2);
        layoutParams3.addRule(13, -1);
        button.setLayoutParams(layoutParams3);
        relativeLayout.addView(button);
        this.layout.addView(relativeLayout);
        this.webView = new WebView(this);
        this.webView.setLayoutParams(new LinearLayout.LayoutParams(-2, -2));
        WebSettings settings = this.webView.getSettings();
        this.webView.setVerticalScrollBarEnabled(false);
        settings.setJavaScriptEnabled(true);
        settings.setUseWideViewPort(true);
        settings.setLoadWithOverviewMode(false);
        this.webView.loadUrl(this.path);
        this.webView.setWebChromeClient(new WebChromeClient() {
            public void onProgressChanged(WebView webView, int i) {
                super.onProgressChanged(webView, i);
                Log.d("newProgress", new StringBuilder(String.valueOf(i)).append("..").toString());
            }
        });
        this.webView.setWebViewClient(new WebViewClient() {
            public void onPageFinished(WebView webView, String str) {
                Log.d("backurl", str);
                if (!(str.indexOf(Constants.PARAM_ACCESS_TOKEN) == -1 || Authorize.this.isShow)) {
                    Authorize.this.jumpResultParser(str);
                }
                if (Authorize.this.dialog != null && Authorize.this.dialog.isShowing()) {
                    Authorize.this.dialog.cancel();
                }
            }

            public boolean shouldOverrideUrlLoading(WebView webView, String str) {
                if (!(str.indexOf(Constants.PARAM_ACCESS_TOKEN) == -1 || Authorize.this.isShow)) {
                    Authorize.this.jumpResultParser(str);
                }
                return false;
            }
        });
        this.layout.addView(this.webView);
        setContentView(this.layout);
    }

    public void jumpResultParser(String str) {
        String[] split = str.split("#")[1].split("&");
        String str2 = split[0].split(LoginConstants.EQUAL)[1];
        String str3 = split[1].split(LoginConstants.EQUAL)[1];
        String str4 = split[2].split(LoginConstants.EQUAL)[1];
        String str5 = split[3].split(LoginConstants.EQUAL)[1];
        String str6 = split[4].split(LoginConstants.EQUAL)[1];
        String str7 = split[5].split(LoginConstants.EQUAL)[1];
        str7 = split[6].split(LoginConstants.EQUAL)[1];
        String str8 = split[7].split(LoginConstants.EQUAL)[1];
        Context applicationContext = getApplicationContext();
        if (str2 != null && !"".equals(str2)) {
            Util.saveSharePersistent(applicationContext, "ACCESS_TOKEN", str2);
            Util.saveSharePersistent(applicationContext, "EXPIRES_IN", str3);
            Util.saveSharePersistent(applicationContext, "OPEN_ID", str4);
            Util.saveSharePersistent(applicationContext, "OPEN_KEY", str5);
            Util.saveSharePersistent(applicationContext, "REFRESH_TOKEN", str6);
            Util.saveSharePersistent(applicationContext, "NAME", str7);
            Util.saveSharePersistent(applicationContext, "NICK", str8);
            Util.saveSharePersistent(applicationContext, "CLIENT_ID", this.clientId);
            Util.saveSharePersistent(applicationContext, "AUTHORIZETIME", String.valueOf(System.currentTimeMillis() / 1000));
            Toast.makeText(this, "授权成功", 0).show();
            setResult(711);
            finish();
            this.isShow = true;
        }
    }

    protected Dialog onCreateDialog(int i) {
        switch (i) {
            case 3:
                this._dialog = new ProgressDialog(this);
                ((ProgressDialog) this._dialog).setMessage("加载中...");
                break;
            case 4:
                Builder builder = new Builder(this);
                builder.setTitle("网络连接异常，是否重新连接？");
                builder.setPositiveButton("是", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialogInterface, int i) {
                        if (Util.isNetworkAvailable(Authorize.this)) {
                            Authorize.this.webView.loadUrl(Authorize.this.path);
                            return;
                        }
                        Message obtain = Message.obtain();
                        obtain.what = 100;
                        Authorize.this.handle.sendMessage(obtain);
                    }
                });
                builder.setNegativeButton("否", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Authorize.this.finish();
                    }
                });
                this._dialog = builder.create();
                break;
        }
        return this._dialog;
    }
}
