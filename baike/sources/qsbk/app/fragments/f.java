package qsbk.app.fragments;

import android.text.TextUtils;
import android.view.View;
import android.view.View.OnClickListener;
import qsbk.app.fragments.BaseLiveTabFragment.DoubleLiveRoomCell;
import qsbk.app.live.LivePullLauncher;

class f implements OnClickListener {
    final /* synthetic */ DoubleLiveRoomCell a;

    f(DoubleLiveRoomCell doubleLiveRoomCell) {
        this.a = doubleLiveRoomCell;
    }

    public void onClick(View view) {
        int a = this.a.q * 2;
        if (TextUtils.equals(this.a.a.a(), LivePullLauncher.STSOURCE_livelist)) {
            a -= 2;
        }
        this.a.c.a(a);
        this.a.c.onClick();
    }
}
