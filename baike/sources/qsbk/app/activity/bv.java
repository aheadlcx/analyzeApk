package qsbk.app.activity;

import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;

class bv implements OnClickListener {
    final /* synthetic */ AuditNativeActivity a;

    bv(AuditNativeActivity auditNativeActivity) {
        this.a = auditNativeActivity;
    }

    public void onClick(DialogInterface dialogInterface, int i) {
        AuditNativeActivity.i(this.a, i);
    }
}
