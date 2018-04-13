package cn.v6.sixrooms.engine;

import cn.v6.sixrooms.base.VLAsyncHandler;
import cn.v6.sixrooms.bean.RepertoryBean;
import cn.v6.sixrooms.constants.CommonStrs;
import com.alipay.sdk.util.h;
import java.util.ArrayList;
import org.json.JSONException;
import org.json.JSONObject;

final class bc extends VLAsyncHandler<String> {
    final /* synthetic */ RoomRepertoryGiftEngine a;

    bc(RoomRepertoryGiftEngine roomRepertoryGiftEngine) {
        this.a = roomRepertoryGiftEngine;
        super(null, 0);
    }

    protected final void handler(boolean z) {
        if (z) {
            try {
                JSONObject jSONObject = new JSONObject((String) getParam());
                if ("001".equals(jSONObject.getString("flag"))) {
                    ArrayList arrayList = new ArrayList();
                    JSONObject optJSONObject = jSONObject.getJSONObject("content").optJSONObject("proplist");
                    if (optJSONObject != null) {
                        for (String str : optJSONObject.toString().replace("{", "").replace(h.d, "").replace("\"", "").split(",")) {
                            RepertoryBean repertoryBean = new RepertoryBean();
                            String[] split = str.split(":");
                            repertoryBean.setGiftID(split[0]);
                            repertoryBean.setGifTotal(split[1]);
                            arrayList.add(repertoryBean);
                        }
                    }
                    RoomRepertoryGiftEngine.a(this.a).result(arrayList);
                    return;
                }
                RoomRepertoryGiftEngine.a(this.a).result(null);
            } catch (JSONException e) {
                RoomRepertoryGiftEngine.a(this.a).error(1007);
                e.printStackTrace();
            }
        } else if (CommonStrs.NET_CONNECT_FAIL.equals(getStr()) && RoomRepertoryGiftEngine.a(this.a) != null) {
            RoomRepertoryGiftEngine.a(this.a).error(1006);
        }
    }
}
