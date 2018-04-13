package cn.v6.sixrooms.engine;

import android.text.TextUtils;
import cn.v6.sixrooms.base.VLAsyncHandler;
import cn.v6.sixrooms.constants.CommonStrs;
import com.alibaba.baichuan.android.trade.constants.AppLinkConstants;
import mtopsdk.mtop.antiattack.CheckCodeDO;
import org.json.JSONException;
import org.json.JSONObject;

final class m extends VLAsyncHandler<String> {
    final /* synthetic */ CoopEncyptEngine a;

    m(CoopEncyptEngine coopEncyptEngine) {
        this.a = coopEncyptEngine;
        super(null, 0);
    }

    protected final void handler(boolean z) {
        if (z) {
            try {
                JSONObject jSONObject = new JSONObject((String) getParam());
                if ("001".equals(jSONObject.getString("flag"))) {
                    JSONObject jSONObject2 = jSONObject.getJSONObject("content");
                    String string = jSONObject2.getString(CheckCodeDO.CHECKCODE_CHECK_URL_KEY);
                    Object string2 = jSONObject2.getString(AppLinkConstants.TIME);
                    if (TextUtils.isEmpty(string) || TextUtils.isEmpty(string2) || string.length() < 2) {
                        this.a.a.error(1017);
                        return;
                    } else {
                        this.a.a.success(string, string2);
                        return;
                    }
                }
                this.a.a.error(1017);
            } catch (JSONException e) {
                this.a.a.error(1007);
                e.printStackTrace();
            }
        } else if (CommonStrs.NET_CONNECT_FAIL.equals(getStr()) && this.a.a != null) {
            this.a.a.error(1006);
        }
    }
}
