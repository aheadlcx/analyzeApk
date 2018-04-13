package qsbk.app.activity;

import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;

class bt implements OnClickListener {
    final /* synthetic */ AuditNativeActivity a;

    bt(AuditNativeActivity auditNativeActivity) {
        this.a = auditNativeActivity;
    }

    public void onClick(DialogInterface dialogInterface, int i) {
        dialogInterface.dismiss();
    }
}
