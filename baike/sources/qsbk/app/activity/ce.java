package qsbk.app.activity;

class ce implements Runnable {
    final /* synthetic */ AuditNativeActivity a;

    ce(AuditNativeActivity auditNativeActivity) {
        this.a = auditNativeActivity;
    }

    public void run() {
        AuditNativeActivity.c(this.a, 1);
    }
}
