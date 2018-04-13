package cn.v6.sixrooms.ui.phone;

import com.slidingmenu.lib.SlidingMenu.f;

final class be implements f {
    final /* synthetic */ HistoryActivity a;

    be(HistoryActivity historyActivity) {
        this.a = historyActivity;
    }

    public final void onOpened() {
        this.a.finish();
    }
}
