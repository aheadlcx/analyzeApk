package qsbk.app.fragments;

import android.text.TextUtils;
import android.view.View;
import android.view.View.OnClickListener;
import qsbk.app.fragments.BaseLiveTabFragment.DoubleLiveRoomCell;
import qsbk.app.live.LivePullLauncher;

class g implements OnClickListener {
    final /* synthetic */ DoubleLiveRoomCell a;

    g(DoubleLiveRoomCell doubleLiveRoomCell) {
        this.a = doubleLiveRoomCell;
    }

    public void onClick(View view) {
        int c = (this.a.q * 2) + 1;
        if (TextUtils.equals(this.a.a.a(), LivePullLauncher.STSOURCE_livelist)) {
            c -= 2;
        }
        this.a.d.a(c);
        this.a.d.onClick();
    }
}
