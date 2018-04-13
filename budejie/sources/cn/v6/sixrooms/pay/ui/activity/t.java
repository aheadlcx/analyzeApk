package cn.v6.sixrooms.pay.ui.activity;

import com.slidingmenu.lib.SlidingMenu.f;

final class t implements f {
    final /* synthetic */ BanklineActivity a;

    t(BanklineActivity banklineActivity) {
        this.a = banklineActivity;
    }

    public final void onOpened() {
        this.a.finish();
    }
}
