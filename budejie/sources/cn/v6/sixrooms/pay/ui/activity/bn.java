package cn.v6.sixrooms.pay.ui.activity;

import com.slidingmenu.lib.SlidingMenu.f;

final class bn implements f {
    final /* synthetic */ WeixinPayActivity a;

    bn(WeixinPayActivity weixinPayActivity) {
        this.a = weixinPayActivity;
    }

    public final void onOpened() {
        this.a.finish();
    }
}
