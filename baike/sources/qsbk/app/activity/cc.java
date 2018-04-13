package qsbk.app.activity;

class cc implements Runnable {
    final /* synthetic */ AuditNativeActivity a;

    cc(AuditNativeActivity auditNativeActivity) {
        this.a = auditNativeActivity;
    }

    public void run() {
        AuditNativeActivity.j(this.a).clearAnimation();
        AuditNativeActivity.d(this.a).setDuration(AuditNativeActivity.d(this.a).getDuration() + 1);
        AuditNativeActivity.j(this.a).startAnimation(AuditNativeActivity.d(this.a));
        AuditNativeActivity.a(this.a, null);
    }
}
