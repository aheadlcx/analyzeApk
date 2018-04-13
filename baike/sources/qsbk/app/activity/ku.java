package qsbk.app.activity;

import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;

class ku implements OnClickListener {
    final /* synthetic */ FillGroupInfoActivity a;

    ku(FillGroupInfoActivity fillGroupInfoActivity) {
        this.a = fillGroupInfoActivity;
    }

    public void onClick(DialogInterface dialogInterface, int i) {
        switch (i) {
            case 0:
                this.a.g();
                return;
            case 1:
                this.a.h_();
                return;
            default:
                return;
        }
    }
}
