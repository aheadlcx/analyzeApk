package cn.v6.sixrooms.ui.fragment;

import cn.v6.sixrooms.utils.GlobleValue;

final class ai implements Runnable {
    final /* synthetic */ String a;
    final /* synthetic */ ShopCarFragment b;

    ai(ShopCarFragment shopCarFragment, String str) {
        this.b = shopCarFragment;
        this.a = str;
    }

    public final void run() {
        ShopCarFragment.d(this.b).getShopItems(this.a, GlobleValue.getUserBean().getId(), "", ShopCarFragment.c(this.b));
    }
}
