package com.tencent.open.utils;

import android.content.Context;
import android.os.Bundle;
import com.tencent.open.a.f;

class Util$1 extends Thread {
    final /* synthetic */ Context a;
    final /* synthetic */ Bundle b;

    Util$1(Context context, Bundle bundle) {
        this.a = context;
        this.b = bundle;
    }

    public void run() {
        try {
            HttpUtils.openUrl2(this.a, "http://cgi.qplus.com/report/report", "GET", this.b);
        } catch (Exception e) {
            f.e("openSDK_LOG.Util", "reportBernoulli has exception: " + e.getMessage());
        }
    }
}
