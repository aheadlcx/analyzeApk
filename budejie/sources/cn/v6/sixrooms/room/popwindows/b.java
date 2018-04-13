package cn.v6.sixrooms.room.popwindows;

import android.view.View;
import android.view.View.OnClickListener;

final class b implements OnClickListener {
    final /* synthetic */ GodUpgradeWindow a;

    b(GodUpgradeWindow godUpgradeWindow) {
        this.a = godUpgradeWindow;
    }

    public final void onClick(View view) {
        this.a.dismiss();
        this.a.s.removeMessages(1);
    }
}
