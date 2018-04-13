package com.ali.auth.third.login;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.text.TextUtils;
import com.ali.auth.third.core.context.KernelContext;
import com.ali.auth.third.core.trace.SDKLogger;
import com.taobao.applink.util.TBAppLinkUtil;

class LoginComponent$1 extends AsyncTask<Object, Void, String> {
    final /* synthetic */ LoginComponent this$0;
    final /* synthetic */ Activity val$activity;
    final /* synthetic */ String val$apkSign;

    LoginComponent$1(LoginComponent loginComponent, String str, Activity activity) {
        this.this$0 = loginComponent;
        this.val$apkSign = str;
        this.val$activity = activity;
    }

    protected String doInBackground(Object... objArr) {
        SDKLogger.d("login", "showLogin doInBackground");
        if (TextUtils.isEmpty(this.val$apkSign)) {
            return "";
        }
        return this.this$0.generateTopAppLinkToken(this.val$apkSign);
    }

    protected void onPostExecute(String str) {
        SDKLogger.d("login", "showLogin onPostExecute signResult = " + str);
        if (TextUtils.isEmpty(str)) {
            this.this$0.showH5Login(this.val$activity);
            return;
        }
        Intent intent = new Intent();
        intent.setAction(TBAppLinkUtil.ACTION_CUSTOM);
        intent.setData(Uri.parse("tbopen://m.taobao.com/getway/oauth?" + "&appkey=" + KernelContext.getAppKey() + "&pluginName=" + "taobao.oauth.code.create" + "&apkSign=" + this.val$apkSign + "&sign=" + str));
        if (this.val$activity.getPackageManager().queryIntentActivities(intent, 0).size() > 0) {
            this.val$activity.startActivityForResult(intent, RequestCode.OPEN_TAOBAO);
        } else {
            this.this$0.showH5Login(this.val$activity);
        }
    }
}
