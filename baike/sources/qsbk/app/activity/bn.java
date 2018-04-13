package qsbk.app.activity;

import android.util.Log;

class bn implements Runnable {
    final /* synthetic */ AuditNativeActivity a;

    bn(AuditNativeActivity auditNativeActivity) {
        this.a = auditNativeActivity;
    }

    public void run() {
        AuditNativeActivity.d(this.a, AuditNativeActivity.l(this.a).getWidth());
        AuditNativeActivity.e(this.a, AuditNativeActivity.l(this.a).getHeight());
        AuditNativeActivity.f(this.a, AuditNativeActivity.m(this.a).getHeight());
        AuditNativeActivity.g(this.a, AuditNativeActivity.b(this.a).getHeight());
        AuditNativeActivity.h(this.a, AuditNativeActivity.n(this.a) - AuditNativeActivity.o(this.a));
        AuditNativeActivity.p(this.a);
        if (AuditNativeActivity.e()) {
            Log.e(AuditNativeActivity.f(), String.format("ViewPager height : %s, bottombar height: %s, contentMinHeight: %s", new Object[]{Integer.valueOf(AuditNativeActivity.n(this.a)), Integer.valueOf(AuditNativeActivity.o(this.a)), Integer.valueOf(AuditNativeActivity.q(this.a))}));
        }
        AuditNativeActivity.b(this.a, 0);
    }
}
