package com.tencent.connect.auth;

import android.app.Activity;
import com.tencent.open.utils.g;
import com.tencent.tauth.IUiListener;

class d implements Runnable {
    final /* synthetic */ String a;
    final /* synthetic */ IUiListener b;
    final /* synthetic */ AuthAgent c;

    d(AuthAgent authAgent, String str, IUiListener iUiListener) {
        this.c = authAgent;
        this.a = str;
        this.b = iUiListener;
    }

    public void run() {
        g.a(AuthAgent.SECURE_LIB_FILE_NAME, AuthAgent.SECURE_LIB_NAME, 3);
        if (this.c.e != null) {
            Activity activity = (Activity) this.c.e.get();
            if (activity != null) {
                activity.runOnUiThread(new e(this, activity));
            }
        }
    }
}
