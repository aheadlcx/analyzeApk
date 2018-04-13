package qsbk.app.activity;

import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;

class mm implements OnClickListener {
    final /* synthetic */ GroupInfoActivity a;

    mm(GroupInfoActivity groupInfoActivity) {
        this.a = groupInfoActivity;
    }

    public void onClick(DialogInterface dialogInterface, int i) {
        dialogInterface.dismiss();
    }
}
