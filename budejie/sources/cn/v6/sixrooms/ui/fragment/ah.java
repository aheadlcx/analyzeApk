package cn.v6.sixrooms.ui.fragment;

import cn.v6.sixrooms.bean.GuardPropBean;
import cn.v6.sixrooms.bean.ShopItemBean;
import cn.v6.sixrooms.bean.ShopItemCarBean;
import cn.v6.sixrooms.engine.CommodityInfoEngine.CallBack;
import java.util.List;

final class ah implements CallBack {
    final /* synthetic */ ShopCarFragment a;

    ah(ShopCarFragment shopCarFragment) {
        this.a = shopCarFragment;
    }

    public final void success(List<GuardPropBean> list) {
    }

    public final void handleErrorInfo(String str, String str2) {
        ShopCarFragment.a(this.a).hideLoadingScreen();
        ShopCarFragment.a(this.a).handleErrorResult(str, str2, ShopCarFragment.a(this.a));
    }

    public final void error(int i) {
        ShopCarFragment.a(this.a).hideLoadingScreen();
        ShopCarFragment.a(this.a).showErrorToast(i);
    }

    public final void gotShopItems(ShopItemBean shopItemBean) {
    }

    public final void gotShopCars(ShopItemCarBean shopItemCarBean) {
        ShopCarFragment.a(this.a).carBean = shopItemCarBean;
        ShopCarFragment.b(this.a);
        ShopCarFragment.a(this.a).hideLoadingScreen();
    }
}
