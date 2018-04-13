package cn.v6.sixrooms.pay.ui.activity;

import com.slidingmenu.lib.SlidingMenu.f;

final class af implements f {
    final /* synthetic */ CoopPayAcitvity a;

    af(CoopPayAcitvity coopPayAcitvity) {
        this.a = coopPayAcitvity;
    }

    public final void onOpened() {
        this.a.finish();
    }
}
