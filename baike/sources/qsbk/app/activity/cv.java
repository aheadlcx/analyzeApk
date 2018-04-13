package qsbk.app.activity;

import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;

class cv implements OnClickListener {
    final /* synthetic */ AuditNativeActivity2 a;

    cv(AuditNativeActivity2 auditNativeActivity2) {
        this.a = auditNativeActivity2;
    }

    public void onClick(DialogInterface dialogInterface, int i) {
        this.a.T = i;
    }
}
