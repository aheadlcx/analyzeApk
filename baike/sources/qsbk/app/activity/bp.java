package qsbk.app.activity;

import android.view.View;

class bp implements Runnable {
    final /* synthetic */ View a;
    final /* synthetic */ AuditNativeActivity b;

    bp(AuditNativeActivity auditNativeActivity, View view) {
        this.b = auditNativeActivity;
        this.a = view;
    }

    public void run() {
        this.a.startAnimation(AuditNativeActivity.s(this.b));
    }
}
