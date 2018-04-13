package cn.v6.sixrooms.ui.phone;

import com.slidingmenu.lib.SlidingMenu.f;

final class e implements f {
    final /* synthetic */ BaseWebviewActivity a;

    e(BaseWebviewActivity baseWebviewActivity) {
        this.a = baseWebviewActivity;
    }

    public final void onOpened() {
        this.a.finish();
    }
}
