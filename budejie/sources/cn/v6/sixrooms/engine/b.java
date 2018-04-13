package cn.v6.sixrooms.engine;

import cn.v6.sixrooms.base.VLAsyncHandler;
import cn.v6.sixrooms.bean.AppUpdateBean;
import cn.v6.sixrooms.constants.CommonStrs;
import cn.v6.sixrooms.utils.JsonParseUtils;
import org.json.JSONException;
import org.json.JSONObject;

final class b extends VLAsyncHandler<String> {
    final /* synthetic */ AppUpdateEngine a;

    b(AppUpdateEngine appUpdateEngine) {
        this.a = appUpdateEngine;
        super(null, 0);
    }

    protected final void handler(boolean z) {
        if (z) {
            try {
                JSONObject jSONObject = new JSONObject((String) getParam());
                if (!jSONObject.isNull("app")) {
                    AppUpdateBean appUpdateBean = (AppUpdateBean) JsonParseUtils.json2Obj(jSONObject.getJSONObject("app").toString(), AppUpdateBean.class);
                    if (this.a.mCallBack != null) {
                        this.a.mCallBack.requestUpdate(appUpdateBean);
                    }
                } else if (this.a.mCallBack != null) {
                    this.a.mCallBack.error(0);
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
        } else if (CommonStrs.NET_CONNECT_FAIL.equals(getStr()) && this.a.mCallBack != null) {
            this.a.mCallBack.error(1006);
        }
    }
}
