package qsbk.app.activity;

class bk implements Runnable {
    final /* synthetic */ AuditNativeActivity a;

    bk(AuditNativeActivity auditNativeActivity) {
        this.a = auditNativeActivity;
    }

    public void run() {
        AuditNativeActivity.c(this.a, -100);
    }
}
