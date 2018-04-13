package com.ali.auth.third.ui.webview;

import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Build.VERSION;
import android.os.Bundle;
import android.os.Handler;
import android.text.TextUtils;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;
import android.widget.TextView;
import com.ali.auth.third.core.context.KernelContext;
import com.ali.auth.third.core.cookies.CookieManagerWrapper;
import com.ali.auth.third.core.model.ResultCode;
import com.ali.auth.third.core.trace.SDKLogger;
import com.ali.auth.third.core.util.CommonUtils;
import com.ali.auth.third.core.util.ResourceUtils;
import com.ali.auth.third.login.util.LoginStatus;
import com.ali.auth.third.ui.support.ActivityResultHandler;
import java.io.Serializable;
import java.util.Collections;
import java.util.Map;

public class BaseWebViewActivity extends Activity {
    public static final String TAG = BaseWebViewActivity.class.getSimpleName();
    protected AuthWebView authWebView;
    protected View backButton;
    public boolean canReceiveTitle;
    protected View closeButton;
    private Handler handler = new Handler();
    protected View titleBar;
    protected TextView titleText;

    class LoadUrlTask extends AsyncTask<String, Void, Void> {
        private String url;

        LoadUrlTask() {
        }

        protected Void doInBackground(String... strArr) {
            this.url = strArr[0];
            CookieManagerWrapper.INSTANCE.refreshCookie();
            return null;
        }

        protected void onPostExecute(Void voidR) {
            super.onPostExecute(voidR);
            if (CommonUtils.isNetworkAvailable()) {
                try {
                    BaseWebViewActivity.this.authWebView.resumeTimers();
                    if (VERSION.SDK_INT >= 11) {
                        BaseWebViewActivity.this.authWebView.onResume();
                    }
                } catch (Exception e) {
                }
                BaseWebViewActivity.this.authWebView.loadUrl(this.url);
                return;
            }
            CommonUtils.toast("com_taobao_tae_sdk_network_not_available_message");
        }
    }

    protected void onCreate(Bundle bundle) {
        String str = null;
        super.onCreate(bundle);
        LinearLayout linearLayout = (LinearLayout) getLayoutInflater().inflate(ResourceUtils.getRLayout(this, "com_taobao_tae_sdk_web_view_activity"), null);
        this.authWebView = createTaeWebView();
        if (this.authWebView == null) {
            LoginStatus.resetLoginFlag();
            finish();
            return;
        }
        Serializable serializableExtra;
        CharSequence string;
        this.authWebView.setWebViewClient(createWebViewClient());
        this.authWebView.setWebChromeClient(createWebChromeClient());
        linearLayout.addView(this.authWebView, new LayoutParams(-1, -1));
        setContentView(linearLayout);
        this.titleText = (TextView) findViewById(ResourceUtils.getIdentifier(this, "id", "com_taobao_tae_sdk_web_view_title_bar_title"));
        this.backButton = findViewById(ResourceUtils.getIdentifier(this, "id", "com_taobao_tae_sdk_web_view_title_bar_back_button"));
        if (this.backButton != null) {
            this.backButton.setOnClickListener(new OnClickListener() {
                public void onClick(View view) {
                    BaseWebViewActivity.this.onBackHistory();
                }
            });
        }
        this.closeButton = findViewById(ResourceUtils.getIdentifier(this, "id", "com_taobao_tae_sdk_web_view_title_bar_close_button"));
        if (this.closeButton != null) {
            this.closeButton.setOnClickListener(new OnClickListener() {
                public void onClick(View view) {
                    BaseWebViewActivity.this.finish();
                }
            });
            this.closeButton.setVisibility(8);
        }
        this.titleBar = findViewById(ResourceUtils.getIdentifier(this, "id", "com_taobao_tae_sdk_web_view_title_bar"));
        if (getIntent() != null) {
            serializableExtra = getIntent().getSerializableExtra("ui_contextParams");
        } else if (bundle != null) {
            serializableExtra = bundle.getSerializable("ui_contextParams");
        } else {
            serializableExtra = null;
        }
        if (serializableExtra instanceof Map) {
            this.authWebView.getContextParameters().putAll((Map) serializableExtra);
        }
        if (bundle != null) {
            string = bundle.getString("title");
            str = bundle.getString("url");
        } else {
            string = null;
        }
        if (TextUtils.isEmpty(string)) {
            string = getIntent().getStringExtra("title");
        }
        if (TextUtils.isEmpty(string)) {
            this.canReceiveTitle = true;
        } else {
            this.canReceiveTitle = false;
            this.titleText.setText(string);
        }
        if (TextUtils.isEmpty(str)) {
            str = getIntent().getStringExtra("url");
        }
        SDKLogger.d(TAG, "onCreate url=" + str);
        if (KernelContext.checkServiceValid()) {
            new LoadUrlTask().executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, new String[]{str});
            return;
        }
        LoginStatus.resetLoginFlag();
        finish();
    }

    protected void onSaveInstanceState(Bundle bundle) {
        super.onSaveInstanceState(bundle);
        bundle.putString("url", this.authWebView.getUrl());
        bundle.putString("title", this.titleText.getText().toString());
        bundle.putSerializable("ui_contextParams", this.authWebView.getContextParameters());
    }

    protected void onDestroy() {
        if (this.authWebView != null) {
            ViewGroup viewGroup = (ViewGroup) this.authWebView.getParent();
            if (viewGroup != null) {
                viewGroup.removeView(this.authWebView);
            }
            this.authWebView.removeAllViews();
            this.authWebView.destroy();
        }
        super.onDestroy();
    }

    public void setResult(ResultCode resultCode) {
        onFailure(resultCode);
    }

    protected void onActivityResult(int i, int i2, Intent intent) {
        super.onActivityResult(i, i2, intent);
        ActivityResultHandler activityResultHandler = (ActivityResultHandler) KernelContext.getService(ActivityResultHandler.class, Collections.singletonMap(ActivityResultHandler.REQUEST_CODE_KEY, String.valueOf(i)));
        if (activityResultHandler != null) {
            activityResultHandler.onActivityResult(2, i, i2, intent, this, null, this.authWebView);
        }
    }

    protected WebViewClient createWebViewClient() {
        return new BaseWebViewClient();
    }

    protected WebChromeClient createWebChromeClient() {
        return new BridgeWebChromeClient() {
            public void onReceivedTitle(WebView webView, String str) {
                if (BaseWebViewActivity.this.canReceiveTitle) {
                    BaseWebViewActivity.this.titleText.setText(str);
                }
            }
        };
    }

    protected AuthWebView createTaeWebView() {
        try {
            return new AuthWebView(this);
        } catch (Throwable th) {
            return null;
        }
    }

    protected void onFailure(ResultCode resultCode) {
        finish();
    }

    public void onBackPressed() {
        onBackHistory();
    }

    protected void onBackHistory() {
        setResult(ResultCode.USER_CANCEL.code, new Intent());
        LoginStatus.resetLoginFlag();
        finish();
    }

    protected void onResume() {
        super.onResume();
        if (this.authWebView != null) {
            try {
                this.authWebView.resumeTimers();
                if (VERSION.SDK_INT >= 11) {
                    this.authWebView.onResume();
                }
            } catch (Exception e) {
            }
        }
    }
}
