package qsbk.app.nearby.ui;

import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import qsbk.app.nearby.ui.HometownDialogFragment.Hometown;

class f implements OnClickListener {
    final /* synthetic */ HometownDialogFragment a;

    f(HometownDialogFragment hometownDialogFragment) {
        this.a = hometownDialogFragment;
    }

    public void onClick(DialogInterface dialogInterface, int i) {
        Hometown hometown = new Hometown((String) this.a.m.get(this.a.o), ((String[]) this.a.n.get(this.a.o))[this.a.p]);
        if (this.a.t != null) {
            this.a.t.onSelected(this.a.q, hometown);
        }
    }
}
