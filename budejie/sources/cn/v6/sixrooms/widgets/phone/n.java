package cn.v6.sixrooms.widgets.phone;

import cn.v6.sixrooms.bean.GuardPropBean;
import cn.v6.sixrooms.bean.GuardPropDetailBean;
import cn.v6.sixrooms.bean.ShopItemBean;
import cn.v6.sixrooms.bean.ShopItemCarBean;
import cn.v6.sixrooms.engine.CommodityInfoEngine.CallBack;
import java.util.List;

final class n implements CallBack {
    final /* synthetic */ FullScreenOpenGuardDialog a;

    n(FullScreenOpenGuardDialog fullScreenOpenGuardDialog) {
        this.a = fullScreenOpenGuardDialog;
    }

    public final void success(List<GuardPropBean> list) {
        this.a.h.clear();
        this.a.h.addAll(list);
        this.a.i = (GuardPropDetailBean) ((GuardPropBean) this.a.h.get(0)).getDetails().get(0);
        this.a.j = (GuardPropDetailBean) ((GuardPropBean) this.a.h.get(1)).getDetails().get(0);
        this.a.b.setText(this.a.i.getPrice() + "六币");
        this.a.c.setText("（" + this.a.i.getDays() + "）天");
        this.a.e.setText(this.a.j.getPrice() + "六币");
        this.a.f.setText("（" + this.a.j.getDays() + "）天");
        this.a.q.setVisibility(8);
        this.a.p.setVisibility(0);
    }

    public final void handleErrorInfo(String str, String str2) {
        this.a.k.handleErrorResult(str, str2, this.a.k);
    }

    public final void error(int i) {
        this.a.k.showErrorToast(i);
    }

    public final void gotShopItems(ShopItemBean shopItemBean) {
    }

    public final void gotShopCars(ShopItemCarBean shopItemCarBean) {
    }
}
