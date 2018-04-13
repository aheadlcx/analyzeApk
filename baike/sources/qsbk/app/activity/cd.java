package qsbk.app.activity;

class cd implements Runnable {
    final /* synthetic */ AuditNativeActivity a;

    cd(AuditNativeActivity auditNativeActivity) {
        this.a = auditNativeActivity;
    }

    public void run() {
        AuditNativeActivity.j(this.a).clearAnimation();
        AuditNativeActivity.e(this.a).setDuration(AuditNativeActivity.e(this.a).getDuration() + 1);
        AuditNativeActivity.j(this.a).startAnimation(AuditNativeActivity.e(this.a));
        AuditNativeActivity.a(this.a, null);
    }
}
