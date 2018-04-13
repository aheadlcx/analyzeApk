package cn.v6.sixrooms.engine;

import cn.v6.sixrooms.base.VLAsyncHandler;
import cn.v6.sixrooms.bean.FansBean;
import cn.v6.sixrooms.constants.CommonStrs;
import cn.v6.sixrooms.utils.JsonParseUtils;
import java.util.ArrayList;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

final class q extends VLAsyncHandler<String> {
    final /* synthetic */ FansListEngine a;

    q(FansListEngine fansListEngine) {
        this.a = fansListEngine;
        super(null, 0);
    }

    protected final void handler(boolean z) {
        int i = 0;
        if (z) {
            try {
                JSONObject jSONObject = new JSONObject((String) getParam());
                String string = jSONObject.getString("flag");
                List arrayList = new ArrayList();
                List arrayList2 = new ArrayList();
                if ("001".equals(string)) {
                    JSONObject optJSONObject = jSONObject.optJSONObject("content");
                    if (optJSONObject != null && optJSONObject.has("content")) {
                        JSONObject jSONObject2 = optJSONObject.getJSONObject("content");
                        JSONArray jSONArray = jSONObject2.getJSONArray("post");
                        for (int i2 = 0; i2 < jSONArray.length(); i2++) {
                            arrayList.add((FansBean) JsonParseUtils.json2Obj(jSONArray.getJSONObject(i2).toString(), FansBean.class));
                        }
                        JSONArray jSONArray2 = jSONObject2.getJSONArray("get");
                        while (i < jSONArray2.length()) {
                            arrayList2.add((FansBean) JsonParseUtils.json2Obj(jSONArray2.getJSONObject(i).toString(), FansBean.class));
                            i++;
                        }
                    }
                    this.a.a.fansList(arrayList, arrayList2);
                }
            } catch (JSONException e) {
                this.a.a.error(1007);
                e.printStackTrace();
            }
        } else if (CommonStrs.NET_CONNECT_FAIL.equals(getStr()) && this.a.a != null) {
            this.a.a.error(1006);
        }
    }
}
