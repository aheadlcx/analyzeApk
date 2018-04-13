package cn.v6.sixrooms.hall;

import cn.v6.sixrooms.base.VLAsyncHandler;
import cn.v6.sixrooms.constants.CommonStrs;
import cn.v6.sixrooms.room.statistic.StatiscProxy;
import cn.v6.sixrooms.room.statistic.StatisticManager;
import cn.v6.sixrooms.room.statistic.StatisticValue;
import org.json.JSONException;
import org.json.JSONObject;

final class n extends VLAsyncHandler<String> {
    final /* synthetic */ String a;
    final /* synthetic */ HostsEngine b;

    n(HostsEngine hostsEngine, Object obj, String str) {
        this.b = hostsEngine;
        this.a = str;
        super(obj, 0);
    }

    protected final void handler(boolean z) {
        if (z) {
            try {
                JSONObject jSONObject = new JSONObject((String) getParam());
                String string = jSONObject.getString("flag");
                String string2 = jSONObject.getString("content");
                String str = (String) getHolder();
                if ("".equals(str)) {
                    str = CommonStrs.TYPE_ALL_ROOMLIST;
                }
                if ("001".equals(string)) {
                    jSONObject = jSONObject.getJSONObject("content");
                    StatiscProxy.bindHomeListData(jSONObject, str, this.a);
                    if (this.b.a != null) {
                        this.b.a.handleInfo(HostsEngine.a(jSONObject, str, StatisticValue.recid));
                        StatisticManager.getInstance().pageStatistic(StatisticValue.getInstance().getHomeTypePage(str, "LiveHallPagerHotFragment"));
                    }
                } else if (this.b.a != null) {
                    this.b.a.handleErrorInfo(string, string2);
                }
            } catch (JSONException e) {
                e.printStackTrace();
                if (this.b.a != null) {
                    this.b.a.error(1007);
                }
            }
        } else if (CommonStrs.NET_CONNECT_FAIL.equals(getStr()) && this.b.a != null) {
            this.b.a.error(1007);
        }
    }
}
