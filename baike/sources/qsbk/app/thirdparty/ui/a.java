package qsbk.app.thirdparty.ui;

import android.content.DialogInterface;
import android.content.DialogInterface.OnKeyListener;
import android.view.KeyEvent;

class a implements OnKeyListener {
    final /* synthetic */ ThirdPartyDialog a;

    a(ThirdPartyDialog thirdPartyDialog) {
        this.a = thirdPartyDialog;
    }

    public boolean onKey(DialogInterface dialogInterface, int i, KeyEvent keyEvent) {
        this.a.a();
        return false;
    }
}
