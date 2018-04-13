package com.ali.auth.third.ui;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.LinearLayout;
import com.ali.auth.third.core.context.KernelContext;
import com.ali.auth.third.core.trace.SDKLogger;
import com.ali.auth.third.core.util.ResourceUtils;
import com.ali.auth.third.login.LoginComponent;
import com.ali.auth.third.login.util.LoginStatus;
import com.ali.auth.third.ui.context.CallbackContext;

public class LoginActivity extends Activity {
    public static final String TAG = "login.LoginActivity";
    LinearLayout hiddenLayout;

    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView((LinearLayout) getLayoutInflater().inflate(ResourceUtils.getRLayout(this, "login_hidden"), null));
        KernelContext.context = getApplicationContext();
        this.hiddenLayout = (LinearLayout) findViewById(ResourceUtils.getIdentifier(this, "id", "login_layout"));
        this.hiddenLayout.setOnClickListener(new OnClickListener() {
            public void onClick(View view) {
                SDKLogger.e(LoginActivity.TAG, "click to destroy");
                LoginActivity.this.finish();
                LoginStatus.resetLoginFlag();
            }
        });
        this.hiddenLayout.setClickable(false);
        this.hiddenLayout.setLongClickable(false);
        if (KernelContext.checkServiceValid()) {
            CallbackContext.setActivity(this);
            SDKLogger.e(TAG, "before mtop call showLogin");
            auth();
            return;
        }
        SDKLogger.d(TAG, "static field null");
        LoginStatus.resetLoginFlag();
        finish();
    }

    protected void auth() {
        LoginComponent.INSTANCE.showLogin(this);
    }

    protected void onResume() {
        super.onResume();
        if (!KernelContext.checkServiceValid()) {
            finish();
        }
    }

    protected void onActivityResult(int i, int i2, Intent intent) {
        SDKLogger.d(TAG, "onActivityResult requestCode = " + i + " resultCode=" + i2);
        if (KernelContext.checkServiceValid()) {
            this.hiddenLayout.setClickable(true);
            this.hiddenLayout.setLongClickable(true);
            super.onActivityResult(i, i2, intent);
            if (CallbackContext.activity == null) {
                CallbackContext.setActivity(this);
            }
            CallbackContext.onActivityResult(i, i2, intent);
            return;
        }
        finish();
    }
}
