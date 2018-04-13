package cn.v6.sixrooms.room.engine;

import cn.v6.sixrooms.base.VLAsyncHandler;
import cn.v6.sixrooms.constants.CommonStrs;
import org.json.JSONException;
import org.json.JSONObject;

final class i extends VLAsyncHandler<String> {
    final /* synthetic */ ReportUserEngine a;

    i(ReportUserEngine reportUserEngine) {
        this.a = reportUserEngine;
        super(null, 0);
    }

    protected final void handler(boolean z) {
        if (z) {
            try {
                JSONObject jSONObject = new JSONObject((String) getParam());
                String string = jSONObject.getString("flag");
                String string2 = jSONObject.getString("content");
                if ("001".equals(string)) {
                    ReportUserEngine.a(this.a).result(string2);
                    return;
                } else {
                    ReportUserEngine.a(this.a).handleErrorInfo(string, string2);
                    return;
                }
            } catch (JSONException e) {
                ReportUserEngine.a(this.a).error(1007);
                e.printStackTrace();
                return;
            }
        }
        if (CommonStrs.NET_CONNECT_FAIL.equals(getStr())) {
            ReportUserEngine.a(this.a).error(1006);
        }
    }
}
