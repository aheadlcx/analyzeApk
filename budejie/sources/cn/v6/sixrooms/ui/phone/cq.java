package cn.v6.sixrooms.ui.phone;

import com.slidingmenu.lib.SlidingMenu.f;

final class cq implements f {
    final /* synthetic */ RetrieveNameOrPasswordActivity a;

    cq(RetrieveNameOrPasswordActivity retrieveNameOrPasswordActivity) {
        this.a = retrieveNameOrPasswordActivity;
    }

    public final void onOpened() {
        this.a.finish();
    }
}
