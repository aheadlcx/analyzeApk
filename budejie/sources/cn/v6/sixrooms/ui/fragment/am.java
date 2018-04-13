package cn.v6.sixrooms.ui.fragment;

import cn.v6.sixrooms.bean.GuardPropBean;
import cn.v6.sixrooms.bean.ShopItemBean;
import cn.v6.sixrooms.bean.ShopItemCarBean;
import cn.v6.sixrooms.engine.CommodityInfoEngine.CallBack;
import cn.v6.sixrooms.ui.phone.ShopActivity;
import java.util.List;

final class am implements CallBack {
    final /* synthetic */ ShopVipFragment a;

    am(ShopVipFragment shopVipFragment) {
        this.a = shopVipFragment;
    }

    public final void success(List<GuardPropBean> list) {
    }

    public final void handleErrorInfo(String str, String str2) {
        ShopVipFragment.a(this.a).hideLoadingScreen();
        ShopVipFragment.a(this.a).handleErrorResult(str, str2, ShopVipFragment.a(this.a));
    }

    public final void error(int i) {
        ShopVipFragment.a(this.a).hideLoadingScreen();
        ShopVipFragment.a(this.a).showErrorToast(i);
    }

    public final void gotShopItems(ShopItemBean shopItemBean) {
        if (ShopActivity.SHOP_ITEM_TYPE_PURPLE_VIP.equals(ShopVipFragment.e(this.a))) {
            ShopVipFragment.a(this.a).vipBean.setZ(shopItemBean.getZ());
            ShopVipFragment.a(this.a).vipBean.setH(shopItemBean.getH());
            ShopVipFragment.a(this.a, ShopVipFragment.a(this.a).vipBean.getZ());
        } else if (ShopActivity.SHOP_ITEM_TYPE_GOLDEN_VIP.equals(ShopVipFragment.e(this.a))) {
            ShopVipFragment.a(this.a).vipBean.setZ(shopItemBean.getZ());
            ShopVipFragment.a(this.a).vipBean.setH(shopItemBean.getH());
            ShopVipFragment.a(this.a, ShopVipFragment.a(this.a).vipBean.getH());
        } else if (ShopActivity.SHOP_ITEM_TYPE_GCARD.equals(ShopVipFragment.e(this.a))) {
            ShopVipFragment.a(this.a).vipBean.setG(shopItemBean.getG());
            ShopVipFragment.a(this.a, ShopVipFragment.a(this.a).vipBean.getG());
        }
        ShopVipFragment.a(this.a).hideLoadingScreen();
        ShopVipFragment.f(this.a);
    }

    public final void gotShopCars(ShopItemCarBean shopItemCarBean) {
    }
}
