package qsbk.app.utils;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;

final class bn implements OnClickListener {
    final /* synthetic */ Activity a;
    final /* synthetic */ String b;

    bn(Activity activity, String str) {
        this.a = activity;
        this.b = str;
    }

    public void onClick(DialogInterface dialogInterface, int i) {
        Util.doDownloadAPK(this.a, this.b);
        dialogInterface.dismiss();
    }
}
