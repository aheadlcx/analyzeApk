package com.tencent.connect.auth;

import android.content.DialogInterface;
import android.content.DialogInterface.OnDismissListener;
import com.tencent.open.web.security.JniInterface;

class k implements OnDismissListener {
    final /* synthetic */ a a;

    k(a aVar) {
        this.a = aVar;
    }

    public void onDismiss(DialogInterface dialogInterface) {
        try {
            JniInterface.clearAllPWD();
        } catch (Exception e) {
        }
    }
}
