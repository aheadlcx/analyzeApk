package qsbk.app.activity;

import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import qsbk.app.model.GroupNotice;

class ni implements OnClickListener {
    final /* synthetic */ GroupNotice a;
    final /* synthetic */ GroupNoticeActivity b;

    ni(GroupNoticeActivity groupNoticeActivity, GroupNotice groupNotice) {
        this.b = groupNoticeActivity;
        this.a = groupNotice;
    }

    public void onClick(DialogInterface dialogInterface, int i) {
        dialogInterface.dismiss();
        this.b.b(this.a);
    }
}
