package cn.v6.sixrooms.hall;

import android.text.TextUtils;
import cn.v6.sixrooms.base.VLAsyncHandler;
import cn.v6.sixrooms.constants.CommonStrs;
import cn.v6.sixrooms.room.statistic.StatisticManager;
import cn.v6.sixrooms.room.statistic.StatisticValue;
import cn.v6.sixrooms.utils.JsonParseUtils;
import java.util.List;
import org.json.JSONException;
import org.json.JSONObject;

final class u extends VLAsyncHandler<String> {
    final /* synthetic */ String a;
    final /* synthetic */ t b;

    u(t tVar, String str) {
        this.b = tVar;
        this.a = str;
        super(null, 0);
    }

    protected final void handler(boolean z) {
        if (z || !CommonStrs.NET_CONNECT_FAIL.equals(getStr())) {
            try {
                JSONObject jSONObject = new JSONObject((String) getParam());
                String string = jSONObject.getString("flag");
                String string2 = jSONObject.getString("content");
                if ("001".equals(string)) {
                    if (!JsonParseUtils.isJsonArray(string2)) {
                        JSONObject jSONObject2 = jSONObject.getJSONObject("content");
                        if (jSONObject2.has("pagename")) {
                            Object string3 = jSONObject2.getString("pagename");
                            if (!TextUtils.isEmpty(string3)) {
                                StatisticValue.getInstance().setHomeTypePage(CommonStrs.TYPE_LOCATION, string3);
                            }
                        }
                        if (jSONObject2.has("recid")) {
                            Object string4 = jSONObject2.getString("recid");
                            if (!TextUtils.isEmpty(string4)) {
                                StatisticValue.getInstance().setTypeRecid(CommonStrs.TYPE_LOCATION, this.a, string4);
                            }
                        }
                    }
                    HostsLocationBean hostsLocationBean = (HostsLocationBean) JsonParseUtils.json2Obj(string2, HostsLocationBean.class);
                    List<ProvinceNumBean> provinceNumAry = hostsLocationBean.getProvinceNumAry();
                    if (provinceNumAry != null && provinceNumAry.size() > 0) {
                        int i = 0;
                        for (ProvinceNumBean provinceNumBean : provinceNumAry) {
                            provinceNumBean.setSelect(hostsLocationBean.getPid().equals(provinceNumBean.getPid()));
                            provinceNumBean.setPosition(i);
                            i++;
                        }
                    }
                    this.b.a.handleInfo(hostsLocationBean);
                    StatisticManager.getInstance().pageStatistic(StatisticValue.getInstance().getHomeTypePage("LiveHallPagerLocationFragment"));
                    return;
                }
                this.b.a.handleErrorInfo(string, string2);
                return;
            } catch (JSONException e) {
                this.b.a.error(1007);
                e.printStackTrace();
                return;
            }
        }
        this.b.a.error(1006);
    }
}
