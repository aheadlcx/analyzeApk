package qsbk.app.activity;

class bx implements Runnable {
    final /* synthetic */ AuditNativeActivity a;

    bx(AuditNativeActivity auditNativeActivity) {
        this.a = auditNativeActivity;
    }

    public void run() {
        AuditNativeActivity.a(this.a, null, AuditNativeActivity.d(this.a));
    }
}
