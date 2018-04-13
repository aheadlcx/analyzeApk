package cn.v6.sixrooms.engine;

import cn.v6.sixrooms.base.VLAsyncHandler;
import cn.v6.sixrooms.constants.CommonStrs;
import cn.v6.sixrooms.constants.UrlStrs;
import cn.v6.sixrooms.room.statistic.StatisticCodeTable;
import java.util.ArrayList;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

final class bd extends VLAsyncHandler<String> {
    final /* synthetic */ String a;
    final /* synthetic */ ServerAddressEngine b;

    bd(ServerAddressEngine serverAddressEngine, String str) {
        this.b = serverAddressEngine;
        this.a = str;
        super(null, 0);
    }

    protected final void handler(boolean z) {
        if (z) {
            try {
                JSONObject jSONObject = new JSONObject((String) getParam());
                if ("001".equals(jSONObject.getString("flag"))) {
                    int i;
                    jSONObject = jSONObject.getJSONObject("content");
                    if ("dev".equals(UrlStrs.type)) {
                        try {
                            String replace = jSONObject.toString().replace("\\", "");
                            jSONObject = new JSONObject(replace.substring(replace.indexOf("{", 1), replace.length() - 2));
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                    JSONArray jSONArray = jSONObject.getJSONArray("a");
                    List arrayList = new ArrayList();
                    for (i = 0; i < jSONArray.length(); i++) {
                        arrayList.add(jSONArray.getString(i));
                    }
                    JSONArray jSONArray2 = jSONObject.getJSONArray("b");
                    List arrayList2 = new ArrayList();
                    for (i = 0; i < jSONArray2.length(); i++) {
                        arrayList2.add(jSONArray2.getString(i));
                    }
                    if ("IM".equals(this.a)) {
                        this.b.a.retIMAddress(arrayList, arrayList2);
                    } else if (StatisticCodeTable.CHAT.equals(this.a)) {
                        this.b.a.retChatAddress(arrayList, arrayList2);
                    }
                }
            } catch (JSONException e2) {
                this.b.a.error(1007);
                e2.printStackTrace();
            }
        } else if (CommonStrs.NET_CONNECT_FAIL.equals(getStr()) && this.b.a != null) {
            this.b.a.error(1006);
        }
    }
}
