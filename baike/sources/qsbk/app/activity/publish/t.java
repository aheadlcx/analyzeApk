package qsbk.app.activity.publish;

import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import qsbk.app.activity.BindPhoneActivity;

class t implements OnClickListener {
    final /* synthetic */ s a;

    t(s sVar) {
        this.a = sVar;
    }

    public void onClick(DialogInterface dialogInterface, int i) {
        BindPhoneActivity.launchForResult(this.a.h, 0);
    }
}
