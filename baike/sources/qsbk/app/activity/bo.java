package qsbk.app.activity;

import android.view.View;
import qsbk.app.widget.MyScrollView.OnDirection;

class bo implements OnDirection {
    final /* synthetic */ AuditNativeActivity a;

    bo(AuditNativeActivity auditNativeActivity) {
        this.a = auditNativeActivity;
    }

    public void toRight(View view) {
        if (AuditNativeActivity.r(this.a)) {
            AuditNativeActivity.a(this.a, false);
            AuditNativeActivity.c(this.a, 0);
            AuditNativeActivity.b(this.a, 2);
        }
    }
}
