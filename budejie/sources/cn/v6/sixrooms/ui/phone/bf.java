package cn.v6.sixrooms.ui.phone;

import com.slidingmenu.lib.SlidingMenu.f;

final class bf implements f {
    final /* synthetic */ MsgVerifyFragmentActivity a;

    bf(MsgVerifyFragmentActivity msgVerifyFragmentActivity) {
        this.a = msgVerifyFragmentActivity;
    }

    public final void onOpened() {
        this.a.finish();
    }
}
