package cn.v6.sixrooms.ui.phone;

import com.slidingmenu.lib.SlidingMenu.f;

final class bl implements f {
    final /* synthetic */ MyPropActivity a;

    bl(MyPropActivity myPropActivity) {
        this.a = myPropActivity;
    }

    public final void onOpened() {
        this.a.finish();
    }
}
