package qsbk.app.activity;

import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;

class lm implements OnClickListener {
    final /* synthetic */ FinishGroupActivity a;

    lm(FinishGroupActivity finishGroupActivity) {
        this.a = finishGroupActivity;
    }

    public void onClick(DialogInterface dialogInterface, int i) {
        dialogInterface.dismiss();
    }
}
