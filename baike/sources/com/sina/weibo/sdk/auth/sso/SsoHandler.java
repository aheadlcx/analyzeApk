package com.sina.weibo.sdk.auth.sso;

import android.app.Activity;
import android.content.Intent;
import com.sina.weibo.sdk.auth.BaseSsoHandler;
import com.sina.weibo.sdk.utils.WbAuthConstants;

public class SsoHandler extends BaseSsoHandler {
    public SsoHandler(Activity activity) {
        super(activity);
    }

    protected void a(Intent intent, int i) {
        super.a(intent, i);
        if (i == WbAuthConstants.REQUEST_CODE_GET_USER_INFO) {
            intent.putExtra(WbAuthConstants.EXTRA_REQUEST_CODE, i);
        }
    }

    protected void a() {
        super.a();
    }
}
