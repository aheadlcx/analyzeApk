package qsbk.app.im;

import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;

class fz implements OnClickListener {
    final /* synthetic */ IMChatBaseActivityEx a;

    fz(IMChatBaseActivityEx iMChatBaseActivityEx) {
        this.a = iMChatBaseActivityEx;
    }

    public void onClick(DialogInterface dialogInterface, int i) {
        dialogInterface.dismiss();
    }
}
