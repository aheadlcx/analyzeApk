package cn.v6.sixrooms.engine;

import cn.v6.sixrooms.base.VLAsyncHandler;
import cn.v6.sixrooms.bean.ConfigureInfoBean;
import cn.v6.sixrooms.constants.CommonStrs;
import cn.v6.sixrooms.utils.JsonParseUtils;
import java.util.Map;
import org.json.JSONException;
import org.json.JSONObject;

final class z extends VLAsyncHandler<String> {
    final /* synthetic */ GetInfoEngine a;

    z(GetInfoEngine getInfoEngine) {
        this.a = getInfoEngine;
        super(null, 0);
    }

    protected final void handler(boolean z) {
        if (z) {
            try {
                JSONObject jSONObject = new JSONObject((String) getParam());
                if ("001".equals(jSONObject.getString("flag"))) {
                    String string = jSONObject.getString("content");
                    if (JsonParseUtils.isJson(string)) {
                        ConfigureInfoBean configureInfoBean = (ConfigureInfoBean) JsonParseUtils.json2Obj(string, ConfigureInfoBean.class);
                        if (jSONObject.getJSONObject("content").has("gift") && configureInfoBean != null) {
                            configureInfoBean.setGiftResMap((Map) JsonParseUtils.getGson().fromJson(jSONObject.getJSONObject("content").getString("gift"), new aa(this).getType()));
                        }
                        if (GetInfoEngine.a(this.a) != null) {
                            GetInfoEngine.a(this.a).result(configureInfoBean);
                        }
                        GetInfoEngine.a();
                    } else if (GetInfoEngine.a(this.a) != null) {
                        GetInfoEngine.a(this.a).error(1007);
                    }
                } else if (GetInfoEngine.a(this.a) != null) {
                    GetInfoEngine.a(this.a).error(1006);
                }
            } catch (JSONException e) {
                e.printStackTrace();
                if (GetInfoEngine.a(this.a) != null) {
                    GetInfoEngine.a(this.a).error(1007);
                }
            }
        } else if (CommonStrs.NET_CONNECT_FAIL.equals(getStr()) && GetInfoEngine.a(this.a) != null) {
            GetInfoEngine.a(this.a).error(1006);
        }
    }
}
