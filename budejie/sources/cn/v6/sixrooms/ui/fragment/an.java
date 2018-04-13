package cn.v6.sixrooms.ui.fragment;

import cn.v6.sixrooms.utils.GlobleValue;
import cn.v6.sixrooms.utils.SaveUserInfoUtils;

final class an implements Runnable {
    final /* synthetic */ ShopVipFragment a;

    an(ShopVipFragment shopVipFragment) {
        this.a = shopVipFragment;
    }

    public final void run() {
        ShopVipFragment.h(this.a).getShopItems(SaveUserInfoUtils.getEncpass(ShopVipFragment.a(this.a)), GlobleValue.getUserBean().getId(), "", ShopVipFragment.g(this.a));
    }
}
