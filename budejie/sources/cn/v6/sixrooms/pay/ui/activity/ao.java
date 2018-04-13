package cn.v6.sixrooms.pay.ui.activity;

import com.slidingmenu.lib.SlidingMenu.f;

final class ao implements f {
    final /* synthetic */ MobilePayActivity a;

    ao(MobilePayActivity mobilePayActivity) {
        this.a = mobilePayActivity;
    }

    public final void onOpened() {
        this.a.finish();
    }
}
