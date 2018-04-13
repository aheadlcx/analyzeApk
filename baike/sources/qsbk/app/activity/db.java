package qsbk.app.activity;

class db implements Runnable {
    final /* synthetic */ AuditNativeActivity2 a;

    db(AuditNativeActivity2 auditNativeActivity2) {
        this.a = auditNativeActivity2;
    }

    public void run() {
        this.a.E.clearAnimation();
        this.a.s.setDuration(this.a.s.getDuration() + 1);
        this.a.E.startAnimation(this.a.s);
        this.a.a(null);
    }
}
