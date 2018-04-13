package qsbk.app.activity;

import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;

class lh implements OnClickListener {
    final /* synthetic */ FinishGroupActivity a;

    lh(FinishGroupActivity finishGroupActivity) {
        this.a = finishGroupActivity;
    }

    public void onClick(DialogInterface dialogInterface, int i) {
        dialogInterface.dismiss();
    }
}
