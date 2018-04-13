package qsbk.app.activity;

class bu implements Runnable {
    final /* synthetic */ AuditNativeActivity a;

    bu(AuditNativeActivity auditNativeActivity) {
        this.a = auditNativeActivity;
    }

    public void run() {
        AuditNativeActivity.b(this.a).clearAnimation();
        AuditNativeActivity.b(this.a).startAnimation(AuditNativeActivity.c(this.a));
    }
}
