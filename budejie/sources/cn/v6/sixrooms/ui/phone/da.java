package cn.v6.sixrooms.ui.phone;

import com.slidingmenu.lib.SlidingMenu.f;

final class da implements f {
    final /* synthetic */ ShopActivity a;

    da(ShopActivity shopActivity) {
        this.a = shopActivity;
    }

    public final void onOpened() {
        this.a.finish();
    }
}
