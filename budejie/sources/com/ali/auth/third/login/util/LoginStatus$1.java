package com.ali.auth.third.login.util;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.text.TextUtils;
import com.ali.auth.third.core.util.CommonUtils;

class LoginStatus$1 extends BroadcastReceiver {
    LoginStatus$1() {
    }

    public void onReceive(Context context, Intent intent) {
        if (intent != null && !TextUtils.equals(CommonUtils.getCurrentProcessName(), intent.getStringExtra("currentProcess"))) {
            LoginStatus.access$000().set(intent.getBooleanExtra("isLogining", false));
        }
    }
}
