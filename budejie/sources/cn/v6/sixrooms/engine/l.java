package cn.v6.sixrooms.engine;

import cn.v6.sixrooms.base.VLAsyncHandler;
import cn.v6.sixrooms.bean.ShopItemBean;
import cn.v6.sixrooms.bean.ShopItemCarBean;
import cn.v6.sixrooms.constants.CommonStrs;
import cn.v6.sixrooms.ui.phone.ShopActivity;
import cn.v6.sixrooms.utils.JsonParseUtils;
import org.json.JSONException;
import org.json.JSONObject;

final class l extends VLAsyncHandler<String> {
    final /* synthetic */ CommodityInfoEngine a;

    l(CommodityInfoEngine commodityInfoEngine) {
        this.a = commodityInfoEngine;
        super(null, 0);
    }

    protected final void handler(boolean z) {
        if (z) {
            try {
                JSONObject jSONObject = new JSONObject((String) getParam());
                String string = jSONObject.getString("flag");
                String string2 = jSONObject.getString("content");
                if ("001".equals(string)) {
                    string = jSONObject.getString("type");
                    if ("vip".equals(string)) {
                        this.a.b.gotShopItems((ShopItemBean) JsonParseUtils.json2Obj(string2, ShopItemBean.class));
                        return;
                    } else if (ShopActivity.SHOP_ITEM_TYPE_GCARD.equals(string)) {
                        this.a.b.gotShopItems((ShopItemBean) JsonParseUtils.json2Obj(string2, ShopItemBean.class));
                        return;
                    } else if (ShopActivity.SHOP_ITEM_TYPE_CAR.equals(string)) {
                        this.a.b.gotShopCars(CommodityInfoEngine.a((ShopItemCarBean) JsonParseUtils.json2Obj(string2, ShopItemCarBean.class)));
                        return;
                    } else {
                        return;
                    }
                }
                this.a.b.handleErrorInfo(string, string2);
            } catch (JSONException e) {
                this.a.b.error(1007);
                e.printStackTrace();
            }
        } else if (CommonStrs.NET_CONNECT_FAIL.equals(getStr()) && this.a.b != null) {
            this.a.b.error(1006);
        }
    }
}
