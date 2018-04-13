package cn.v6.sixrooms.engine;

import android.annotation.SuppressLint;
import android.text.TextUtils;
import cn.v6.sixrooms.base.VLAsyncHandler;
import cn.v6.sixrooms.bean.CarBean;
import cn.v6.sixrooms.bean.GuardPropBean;
import cn.v6.sixrooms.bean.GuardPropDetailBean;
import cn.v6.sixrooms.bean.ShopItemBean;
import cn.v6.sixrooms.bean.ShopItemCarBean;
import cn.v6.sixrooms.bean.ShopItemCarBean.Item;
import cn.v6.sixrooms.constants.UrlStrs;
import cn.v6.sixrooms.net.NetworkServiceSingleton;
import cn.v6.sixrooms.utils.DrawableResourceUtils;
import cn.v6.sixrooms.utils.JsonParseUtils;
import cn.v6.sixrooms.utils.LogUtils;
import cn.v6.sixrooms.utils.UrlUtils;
import com.alipay.sdk.sys.a;
import com.tencent.open.SocialConstants;
import java.util.ArrayList;
import java.util.List;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

@SuppressLint({"HandlerLeak"})
public class CommodityInfoEngine {
    protected static final String TAG = "RoomPropEngine";
    private String a = "coop-mobile-getAngelPayList.php";
    private CallBack b;

    public interface CallBack {
        void error(int i);

        void gotShopCars(ShopItemCarBean shopItemCarBean);

        void gotShopItems(ShopItemBean shopItemBean);

        void handleErrorInfo(String str, String str2);

        void success(List<GuardPropBean> list);
    }

    public CommodityInfoEngine(CallBack callBack) {
        this.b = callBack;
    }

    public void getProps(String str, String str2, String str3) {
        List arrayList = new ArrayList();
        BasicNameValuePair basicNameValuePair = new BasicNameValuePair("encpass", str);
        BasicNameValuePair basicNameValuePair2 = new BasicNameValuePair("logiuid", str2);
        BasicNameValuePair basicNameValuePair3 = new BasicNameValuePair("rid", str3);
        BasicNameValuePair basicNameValuePair4 = new BasicNameValuePair(a.k, "1.5");
        arrayList.add(basicNameValuePair);
        arrayList.add(basicNameValuePair2);
        arrayList.add(basicNameValuePair3);
        arrayList.add(basicNameValuePair4);
        NetworkServiceSingleton.createInstance().sendAsyncRequest(new k(this), UrlUtils.getPadapiUrl(UrlStrs.URL_INDEX_INFO, this.a), arrayList);
    }

    public void getShopItems(String str, String str2, String str3, String str4) {
        String padapiUrl = UrlUtils.getPadapiUrl(UrlStrs.URL_INDEX_INFO, this.a);
        NetworkServiceSingleton createInstance = NetworkServiceSingleton.createInstance();
        VLAsyncHandler lVar = new l(this);
        List arrayList = new ArrayList();
        arrayList.add(new BasicNameValuePair("encpass", str));
        arrayList.add(new BasicNameValuePair("logiuid", str2));
        if (!TextUtils.isEmpty(str3)) {
            arrayList.add(new BasicNameValuePair("rid", str3));
        }
        if (!TextUtils.isEmpty(str4)) {
            arrayList.add(new BasicNameValuePair("type", str4));
        }
        arrayList.add(new BasicNameValuePair(a.k, "1.3"));
        createInstance.sendAsyncRequest(lVar, padapiUrl, arrayList);
    }

    private static void a(List<Item> list) {
        LogUtils.d(TAG, "size== " + list.size());
        for (int i = 0; i < list.size(); i++) {
            Item item = (Item) list.get(i);
            if (!DrawableResourceUtils.IPHONE_CARIDS.contains(item.getProp())) {
                CarBean carType = DrawableResourceUtils.getCarType(item.getProp(), "");
                LogUtils.d(TAG, "innerCar== " + carType);
                if (carType != null) {
                    item.setCar(carType);
                }
            }
        }
    }

    static /* synthetic */ GuardPropBean a(JSONObject jSONObject) throws JSONException {
        String string = jSONObject.getString("prop");
        JSONArray jSONArray = jSONObject.getJSONArray("list");
        List arrayList = new ArrayList();
        for (int i = 0; i < jSONArray.length(); i++) {
            arrayList.add((GuardPropDetailBean) JsonParseUtils.json2Obj(jSONArray.getJSONObject(i).toString(), GuardPropDetailBean.class));
        }
        return new GuardPropBean(string, arrayList, jSONObject.getString(SocialConstants.PARAM_APP_DESC));
    }

    static /* synthetic */ ShopItemCarBean a(ShopItemCarBean shopItemCarBean) {
        a(shopItemCarBean.getH());
        a(shopItemCarBean.getS());
        a(shopItemCarBean.getZ());
        a(shopItemCarBean.getP());
        return shopItemCarBean;
    }
}
