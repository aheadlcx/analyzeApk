package qsbk.app.activity;

class bj implements Runnable {
    final /* synthetic */ AuditNativeActivity a;

    bj(AuditNativeActivity auditNativeActivity) {
        this.a = auditNativeActivity;
    }

    public void run() {
        AuditNativeActivity.a(this.a).performClick();
    }
}
