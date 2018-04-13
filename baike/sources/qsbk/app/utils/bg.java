package qsbk.app.utils;

import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import qsbk.app.AppManager;

final class bg implements OnClickListener {
    final /* synthetic */ Context a;

    bg(Context context) {
        this.a = context;
    }

    public void onClick(DialogInterface dialogInterface, int i) {
        dialogInterface.dismiss();
        AppManager.getAppManager().AppExit(this.a);
    }
}
