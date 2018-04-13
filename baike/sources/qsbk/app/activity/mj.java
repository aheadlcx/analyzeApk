package qsbk.app.activity;

import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;

class mj implements OnClickListener {
    final /* synthetic */ GroupInfoActivity a;

    mj(GroupInfoActivity groupInfoActivity) {
        this.a = groupInfoActivity;
    }

    public void onClick(DialogInterface dialogInterface, int i) {
        this.a.r();
    }
}
