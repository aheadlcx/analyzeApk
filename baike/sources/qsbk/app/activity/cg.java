package qsbk.app.activity;

class cg implements Runnable {
    final /* synthetic */ AuditNativeActivity2 a;

    cg(AuditNativeActivity2 auditNativeActivity2) {
        this.a = auditNativeActivity2;
    }

    public void run() {
        this.a.y.performClick();
    }
}
