package qsbk.app.activity;

import android.os.Build.VERSION;
import android.util.Log;
import android.view.View;
import android.view.ViewTreeObserver.OnGlobalLayoutListener;
import qsbk.app.R;

class cm implements OnGlobalLayoutListener {
    final /* synthetic */ View a;
    final /* synthetic */ View b;
    final /* synthetic */ AuditNativeActivity2 c;

    cm(AuditNativeActivity2 auditNativeActivity2, View view, View view2) {
        this.c = auditNativeActivity2;
        this.a = view;
        this.b = view2;
    }

    public void onGlobalLayout() {
        this.c.R = this.c.n.getHeight() - this.a.findViewById(R.id.arrowContainer).getHeight();
        Log.d(AuditNativeActivity2.a, "initContentViews: mWhiteContentMinHeight " + this.c.R);
        this.b.setMinimumHeight(this.c.R);
        if (VERSION.SDK_INT >= 16) {
            this.a.getViewTreeObserver().removeOnGlobalLayoutListener(this);
        } else {
            this.a.getViewTreeObserver().removeGlobalOnLayoutListener(this);
        }
    }
}
