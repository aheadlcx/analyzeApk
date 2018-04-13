package cn.v6.sixrooms.room.engine;

import cn.v6.sixrooms.base.VLAsyncHandler;
import cn.v6.sixrooms.bean.ConfigUpdateBean;
import cn.v6.sixrooms.constants.CommonStrs;
import cn.v6.sixrooms.utils.JsonParseUtils;
import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import java.util.List;
import org.json.JSONException;
import org.json.JSONObject;

final class b extends VLAsyncHandler<String> {
    final /* synthetic */ ConfigUpdateEngine a;

    b(ConfigUpdateEngine configUpdateEngine) {
        this.a = configUpdateEngine;
        super(null, 0);
    }

    protected final void handler(boolean z) {
        if (z) {
            try {
                JSONObject jSONObject = new JSONObject((String) getParam());
                String string = jSONObject.getString("flag");
                String string2 = jSONObject.getString("content");
                if ("001".equals(string)) {
                    JsonElement parse = new JsonParser().parse(string2);
                    if (parse.isJsonArray()) {
                        List list = (List) new Gson().fromJson(string2, new c(this).getType());
                        if (list.size() == 0) {
                            this.a.a.result(null);
                            return;
                        } else {
                            this.a.a.result((ConfigUpdateBean) list.get(0));
                            return;
                        }
                    } else if (parse.isJsonObject()) {
                        this.a.a.result((ConfigUpdateBean) JsonParseUtils.json2Obj(string2, ConfigUpdateBean.class));
                        return;
                    } else {
                        return;
                    }
                }
                this.a.a.handleErrorInfo(string, string2);
            } catch (JSONException e) {
                this.a.a.error(1007);
                e.printStackTrace();
            }
        } else if (CommonStrs.NET_CONNECT_FAIL.equals(getStr())) {
            this.a.a.error(1006);
        }
    }
}
