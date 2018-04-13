package cn.v6.sixrooms.ui.phone;

import com.slidingmenu.lib.SlidingMenu.f;

final class s implements f {
    final /* synthetic */ ChangeUserVisibilityActivity a;

    s(ChangeUserVisibilityActivity changeUserVisibilityActivity) {
        this.a = changeUserVisibilityActivity;
    }

    public final void onOpened() {
        this.a.finish();
    }
}
