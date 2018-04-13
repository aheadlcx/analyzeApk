package cn.v6.sixrooms.ui.phone;

import com.slidingmenu.lib.SlidingMenu.f;

final class aw implements f {
    final /* synthetic */ FindUsernameActivity a;

    aw(FindUsernameActivity findUsernameActivity) {
        this.a = findUsernameActivity;
    }

    public final void onOpened() {
        this.a.finish();
    }
}
