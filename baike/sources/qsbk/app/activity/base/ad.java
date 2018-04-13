package qsbk.app.activity.base;

import android.content.DialogInterface;
import android.content.DialogInterface.OnCancelListener;

class ad implements OnCancelListener {
    final /* synthetic */ CommDialogActivity a;

    ad(CommDialogActivity commDialogActivity) {
        this.a = commDialogActivity;
    }

    public void onCancel(DialogInterface dialogInterface) {
        this.a.finish();
    }
}
