package cn.v6.sixrooms.pay.ui.activity;

import com.slidingmenu.lib.SlidingMenu.f;

final class ax implements f {
    final /* synthetic */ PayCardActivity a;

    ax(PayCardActivity payCardActivity) {
        this.a = payCardActivity;
    }

    public final void onOpened() {
        this.a.finish();
    }
}
