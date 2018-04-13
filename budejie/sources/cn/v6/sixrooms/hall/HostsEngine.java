package cn.v6.sixrooms.hall;

import android.text.TextUtils;
import cn.v6.sdk.sixrooms.coop.V6Coop;
import cn.v6.sixrooms.base.SixRoomsUtils;
import cn.v6.sixrooms.constants.CommonStrs;
import cn.v6.sixrooms.constants.UrlStrs;
import cn.v6.sixrooms.net.NetworkServiceSingleton;
import cn.v6.sixrooms.room.gift.GiftConfigUtil;
import cn.v6.sixrooms.room.statistic.StatisticValue;
import cn.v6.sixrooms.utils.GlobleValue;
import cn.v6.sixrooms.utils.JsonParseUtils;
import cn.v6.sixrooms.utils.LogUtils;
import cn.v6.sixrooms.utils.SaveUserInfoUtils;
import cn.v6.sixrooms.utils.UrlUtils;
import com.alipay.sdk.sys.a;
import java.util.ArrayList;
import java.util.List;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class HostsEngine {
    protected static final String TAG = HostsEngine.class.getSimpleName();
    private CallBack<List<LiveItemBean>> a;

    public HostsEngine(CallBack<List<LiveItemBean>> callBack) {
        this.a = callBack;
    }

    public void getHosts(String str, String str2) {
        String str3;
        List arrayList = new ArrayList();
        BasicNameValuePair basicNameValuePair = new BasicNameValuePair("type", str);
        BasicNameValuePair basicNameValuePair2 = new BasicNameValuePair("size", "20");
        BasicNameValuePair basicNameValuePair3 = new BasicNameValuePair("p", str2);
        BasicNameValuePair basicNameValuePair4 = new BasicNameValuePair("rate", GiftConfigUtil.SUPER_GIRL_GIFT_TAG);
        BasicNameValuePair basicNameValuePair5 = new BasicNameValuePair("logiuid", GlobleValue.getUserBean() == null ? "" : GlobleValue.getUserBean().getId());
        BasicNameValuePair basicNameValuePair6 = new BasicNameValuePair("encpass", SaveUserInfoUtils.getEncpass(V6Coop.getInstance().getContext()));
        BasicNameValuePair basicNameValuePair7 = new BasicNameValuePair(a.k, "2.6");
        arrayList.add(basicNameValuePair);
        arrayList.add(basicNameValuePair2);
        arrayList.add(basicNameValuePair3);
        arrayList.add(basicNameValuePair4);
        arrayList.add(basicNameValuePair5);
        arrayList.add(basicNameValuePair6);
        arrayList.add(basicNameValuePair7);
        if ("".equals(str)) {
            str3 = CommonStrs.TYPE_ALL_ROOMLIST;
        } else {
            str3 = str;
        }
        int intValue = Integer.valueOf(str2).intValue() - 1;
        if (intValue <= 0) {
            intValue = 0;
        }
        if (!TextUtils.isEmpty(StatisticValue.getInstance().getTypeRecid(str3, String.valueOf(intValue)))) {
            arrayList.add(new BasicNameValuePair("recid", StatisticValue.getInstance().getTypeRecid(str3, String.valueOf(intValue))));
            LogUtils.e(TAG, "getLiveInfoByPage ----  type -> " + str3 + "  page  -> " + intValue + "  recid -> " + StatisticValue.getInstance().getTypeRecid(str3, String.valueOf(intValue)));
        }
        NetworkServiceSingleton.createInstance().sendAsyncRequest(new n(this, str, str2), UrlUtils.getPadapiUrl(UrlStrs.URL_INDEX_INFO, "coop-mobile-getlivelistnew.php"), arrayList);
    }

    static /* synthetic */ ArrayList a(JSONObject jSONObject, String str, String str2) throws JSONException, NumberFormatException {
        String str3;
        ArrayList arrayList = new ArrayList();
        if ("".equals(str)) {
            str3 = CommonStrs.TYPE_ALL_ROOMLIST;
        } else {
            str3 = str;
        }
        if (jSONObject.has(str3)) {
            JSONArray jSONArray;
            CharSequence string = jSONObject.getString(str3);
            if (TextUtils.isEmpty(string) || "null".equals(string)) {
                jSONArray = new JSONArray();
            } else {
                jSONArray = jSONObject.getJSONArray(str3);
            }
            int length = jSONArray.length();
            for (int i = 0; i < length; i++) {
                JSONObject jSONObject2 = jSONArray.getJSONObject(i);
                if (jSONObject2 != null) {
                    LiveItemBean liveItemBean = (LiveItemBean) JsonParseUtils.json2Obj(jSONObject2.toString(), LiveItemBean.class);
                    liveItemBean.setType(str);
                    liveItemBean.setTitle(SixRoomsUtils.parseTypeToTitle(str));
                    liveItemBean.setTypeId(SixRoomsUtils.parseTypeId(str));
                    arrayList.add(liveItemBean);
                    liveItemBean.setRecid(str2);
                }
            }
        }
        return arrayList;
    }
}
