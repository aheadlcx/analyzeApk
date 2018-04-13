package cn.v6.sixrooms.ui.fragment;

import cn.v6.sixrooms.utils.DialogUtils.DialogListener;

final class al implements DialogListener {
    final /* synthetic */ String a;
    final /* synthetic */ ak b;

    al(ak akVar, String str) {
        this.b = akVar;
        this.a = str;
    }

    public final void positive(int i) {
        if (i == 1) {
            ShopVipFragment.a(this.b.a).showLoadingScreen();
            ShopVipFragment.a(this.b.a).buyItem(ShopVipFragment.c(this.b.a), this.a);
        }
    }

    public final void negative(int i) {
    }
}
