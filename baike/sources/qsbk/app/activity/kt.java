package qsbk.app.activity;

import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;

class kt implements OnClickListener {
    final /* synthetic */ FillGroupInfoActivity a;

    kt(FillGroupInfoActivity fillGroupInfoActivity) {
        this.a = fillGroupInfoActivity;
    }

    public void onClick(DialogInterface dialogInterface, int i) {
        dialogInterface.dismiss();
    }
}
