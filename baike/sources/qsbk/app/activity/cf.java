package qsbk.app.activity;

import android.view.View;
import qsbk.app.R;

class cf implements Runnable {
    final /* synthetic */ int a;
    final /* synthetic */ AuditNativeActivity$a b;

    cf(AuditNativeActivity$a auditNativeActivity$a, int i) {
        this.b = auditNativeActivity$a;
        this.a = i;
    }

    public void run() {
        this.b.b.setAdapter(null);
        this.b.c.addView((View) this.b.a.get(this.a));
        if (AuditNativeActivity.y(this.b.d).get()) {
            AuditNativeActivity.h(this.b.d);
            return;
        }
        ((View) this.b.a.get(this.a)).findViewById(R.id.bottom_layout).setVisibility(0);
        this.b.c.setVisibility(0);
    }
}
