package qsbk.app.nearby.ui;

import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;

class e implements OnClickListener {
    final /* synthetic */ HometownDialogFragment a;

    e(HometownDialogFragment hometownDialogFragment) {
        this.a = hometownDialogFragment;
    }

    public void onClick(DialogInterface dialogInterface, int i) {
        if (this.a.t != null) {
            this.a.t.onCancel(this.a.q);
        }
    }
}
