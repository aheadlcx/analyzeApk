package qsbk.app.activity;

import android.view.View;

class cn implements Runnable {
    final /* synthetic */ View a;
    final /* synthetic */ AuditNativeActivity2 b;

    cn(AuditNativeActivity2 auditNativeActivity2, View view) {
        this.b = auditNativeActivity2;
        this.a = view;
    }

    public void run() {
        this.a.startAnimation(this.b.v);
    }
}
