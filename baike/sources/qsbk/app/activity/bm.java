package qsbk.app.activity;

class bm implements Runnable {
    final /* synthetic */ AuditNativeActivity a;

    bm(AuditNativeActivity auditNativeActivity) {
        this.a = auditNativeActivity;
    }

    public void run() {
        AuditNativeActivity.f(this.a);
    }
}
