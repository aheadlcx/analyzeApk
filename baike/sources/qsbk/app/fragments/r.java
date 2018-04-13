package qsbk.app.fragments;

import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import qsbk.app.model.BaseUserInfo;

class r implements OnClickListener {
    final /* synthetic */ BaseUserInfo a;
    final /* synthetic */ int b;
    final /* synthetic */ BlacklistFragment c;

    r(BlacklistFragment blacklistFragment, BaseUserInfo baseUserInfo, int i) {
        this.c = blacklistFragment;
        this.a = baseUserInfo;
        this.b = i;
    }

    public void onClick(DialogInterface dialogInterface, int i) {
        this.c.a(this.a.userId, this.b - this.c.c.getHeaderViewsCount());
    }
}
