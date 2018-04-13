package cn.v6.sixrooms.pay.ui.activity;

import com.slidingmenu.lib.SlidingMenu.f;

final class h implements f {
    final /* synthetic */ AlipayActivity a;

    h(AlipayActivity alipayActivity) {
        this.a = alipayActivity;
    }

    public final void onOpened() {
        this.a.finish();
    }
}
