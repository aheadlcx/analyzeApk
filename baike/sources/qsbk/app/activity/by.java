package qsbk.app.activity;

class by implements Runnable {
    final /* synthetic */ AuditNativeActivity a;

    by(AuditNativeActivity auditNativeActivity) {
        this.a = auditNativeActivity;
    }

    public void run() {
        AuditNativeActivity.a(this.a, null, AuditNativeActivity.e(this.a));
    }
}
