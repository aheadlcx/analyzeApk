package cn.v6.sixrooms.widgets.phone;

import cn.v6.sixrooms.bean.GuardPropBean;
import cn.v6.sixrooms.bean.ShopItemBean;
import cn.v6.sixrooms.bean.ShopItemCarBean;
import cn.v6.sixrooms.engine.CommodityInfoEngine.CallBack;
import java.util.List;

final class z implements CallBack {
    final /* synthetic */ OpenGuardPage a;

    z(OpenGuardPage openGuardPage) {
        this.a = openGuardPage;
    }

    public final void success(List<GuardPropBean> list) {
        this.a.j.clear();
        this.a.j.addAll(list);
        this.a.l = ((GuardPropBean) this.a.j.get(0)).getDetails();
        this.a.m = ((GuardPropBean) this.a.j.get(1)).getDetails();
        this.a.i.setVisibility(8);
        this.a.b();
        this.a.d.setOnItemClickListener(this.a.q);
    }

    public final void handleErrorInfo(String str, String str2) {
        if (this.a.u != null) {
            this.a.u.onHandleErrorInfo(str, str2);
        }
        this.a.i.setVisibility(8);
    }

    public final void error(int i) {
        this.a.i.setVisibility(8);
        if (this.a.u != null) {
            this.a.u.onShowErrorCode(i);
        }
    }

    public final void gotShopItems(ShopItemBean shopItemBean) {
    }

    public final void gotShopCars(ShopItemCarBean shopItemCarBean) {
    }
}
