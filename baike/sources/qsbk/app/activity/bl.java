package qsbk.app.activity;

class bl implements Runnable {
    final /* synthetic */ AuditNativeActivity a;

    bl(AuditNativeActivity auditNativeActivity) {
        this.a = auditNativeActivity;
    }

    public void run() {
        AuditNativeActivity.k(this.a).start();
    }
}
