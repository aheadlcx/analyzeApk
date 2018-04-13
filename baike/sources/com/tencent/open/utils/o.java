package com.tencent.open.utils;

import android.os.Bundle;

class o extends Thread {
    final /* synthetic */ Bundle a;
    final /* synthetic */ e b;

    o(e eVar, Bundle bundle) {
        this.b = eVar;
        this.a = bundle;
    }

    public void run() {
        try {
            this.b.a(i.d(HttpUtils.openUrl2(this.b.c, "http://cgi.connect.qq.com/qqconnectopen/openapi/policy_conf", "GET", this.a).a));
        } catch (Exception e) {
            e.printStackTrace();
        }
        this.b.g = 0;
    }
}
