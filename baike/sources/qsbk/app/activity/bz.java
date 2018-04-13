package qsbk.app.activity;

class bz implements Runnable {
    final /* synthetic */ AuditNativeActivity a;

    bz(AuditNativeActivity auditNativeActivity) {
        this.a = auditNativeActivity;
    }

    public void run() {
        AuditNativeActivity.f(this.a);
    }
}
