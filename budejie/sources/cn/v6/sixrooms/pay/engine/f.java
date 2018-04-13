package cn.v6.sixrooms.pay.engine;

import cn.v6.sixrooms.base.VLAsyncHandler;
import cn.v6.sixrooms.constants.CommonStrs;
import cn.v6.sixrooms.pay.bean.PaySelectBean;
import cn.v6.sixrooms.pay.bean.WrapPaySelect;
import cn.v6.sixrooms.pay.bean.WrapPaySelect.CommodityInfo;
import cn.v6.sixrooms.pay.contants.CommonPay;
import cn.v6.sixrooms.utils.JsonParseUtils;
import cn.v6.sixrooms.utils.LogUtils;
import java.util.ArrayList;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

final class f extends VLAsyncHandler<String> {
    final /* synthetic */ PayInfoEngine a;

    f(PayInfoEngine payInfoEngine) {
        this.a = payInfoEngine;
        super(null, 0);
    }

    protected final void handler(boolean z) {
        if (z) {
            try {
                int i;
                PaySelectBean paySelectBean;
                JSONObject jSONObject;
                JSONObject jSONObject2 = new JSONObject((String) getParam());
                String string = jSONObject2.getString("notice");
                WrapPaySelect wrapPaySelect = new WrapPaySelect();
                List arrayList = new ArrayList();
                JSONObject jSONObject3 = jSONObject2.getJSONObject("alipayless");
                String string2 = jSONObject3.getString("msg");
                JSONArray jSONArray = jSONObject3.getJSONArray("payrate");
                for (i = 0; i < jSONArray.length(); i++) {
                    paySelectBean = new PaySelectBean();
                    jSONObject = jSONArray.getJSONObject(i);
                    paySelectBean.setType(1);
                    paySelectBean.setMoney(jSONObject.getString("r"));
                    paySelectBean.setCoin6(jSONObject.getString("c"));
                    paySelectBean.setMsg(string2);
                    arrayList.add(paySelectBean);
                }
                wrapPaySelect.setAlipayless(arrayList);
                arrayList = new ArrayList();
                jSONObject3 = jSONObject2.getJSONObject(CommonPay.GATETYPE_BANKLINE);
                string2 = jSONObject3.getString("msg");
                jSONArray = jSONObject3.getJSONArray("payrate");
                for (i = 0; i < jSONArray.length(); i++) {
                    paySelectBean = new PaySelectBean();
                    jSONObject = jSONArray.getJSONObject(i);
                    paySelectBean.setType(1);
                    paySelectBean.setMoney(jSONObject.getString("r"));
                    paySelectBean.setCoin6(jSONObject.getString("c"));
                    paySelectBean.setMsg(string2);
                    arrayList.add(paySelectBean);
                }
                wrapPaySelect.setBankline(arrayList);
                arrayList = new ArrayList();
                jSONObject3 = jSONObject2.getJSONObject(CommonPay.GATETYPE_YEEPAYSZX);
                string2 = jSONObject3.getString("msg");
                jSONArray = jSONObject3.getJSONArray("payrate");
                for (i = 0; i < jSONArray.length(); i++) {
                    paySelectBean = new PaySelectBean();
                    jSONObject = jSONArray.getJSONObject(i);
                    paySelectBean.setType(1);
                    paySelectBean.setMoney(jSONObject.getString("r"));
                    paySelectBean.setCoin6(jSONObject.getString("c"));
                    paySelectBean.setMsg(string2);
                    arrayList.add(paySelectBean);
                }
                wrapPaySelect.setYeepayszx(arrayList);
                arrayList = new ArrayList();
                jSONObject3 = jSONObject2.getJSONObject(CommonPay.GATETYPE_YEEPAYUNICOM);
                string2 = jSONObject3.getString("msg");
                jSONArray = jSONObject3.getJSONArray("payrate");
                for (i = 0; i < jSONArray.length(); i++) {
                    PaySelectBean paySelectBean2 = new PaySelectBean();
                    JSONObject jSONObject4 = jSONArray.getJSONObject(i);
                    paySelectBean2.setType(1);
                    paySelectBean2.setMoney(jSONObject4.getString("r"));
                    paySelectBean2.setCoin6(jSONObject4.getString("c"));
                    paySelectBean2.setMsg(string2);
                    arrayList.add(paySelectBean2);
                }
                wrapPaySelect.setYeepayunicom(arrayList);
                String string3 = jSONObject2.getString("wxpayapp");
                LogUtils.d("PayInfoEngine", "wxpayapp: " + string3);
                wrapPaySelect.setWxpayapp((CommodityInfo) JsonParseUtils.json2Obj(string3, CommodityInfo.class));
                PayInfoEngine.a(this.a).handleResult(wrapPaySelect, string);
            } catch (JSONException e) {
                PayInfoEngine.a(this.a).error(1007);
                e.printStackTrace();
            }
        } else if (CommonStrs.NET_CONNECT_FAIL.equals(getStr())) {
            PayInfoEngine.a(this.a).error(1006);
        }
    }
}
