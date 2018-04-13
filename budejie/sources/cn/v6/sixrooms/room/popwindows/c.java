package cn.v6.sixrooms.room.popwindows;

import android.view.View;
import android.view.View.OnClickListener;

final class c implements OnClickListener {
    final /* synthetic */ GodUpgradeWindow a;

    c(GodUpgradeWindow godUpgradeWindow) {
        this.a = godUpgradeWindow;
    }

    public final void onClick(View view) {
        this.a.deliver(this.a.n);
        this.a.dismiss();
    }
}
