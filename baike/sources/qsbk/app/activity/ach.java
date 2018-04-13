package qsbk.app.activity;

import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import qsbk.app.activity.security.PhoneVerifyActivity;

class ach implements OnClickListener {
    final /* synthetic */ acf a;

    ach(acf acf) {
        this.a = acf;
    }

    public void onClick(DialogInterface dialogInterface, int i) {
        switch (i) {
            case 0:
                ReBindPhoneActivity.launchForResult(this.a.a.a, 22);
                return;
            case 1:
                PhoneVerifyActivity.launchForResult(this.a.a.a, 22);
                return;
            default:
                return;
        }
    }
}
