package cn.v6.sixrooms.engine;

import cn.v6.sixrooms.base.VLAsyncHandler;
import cn.v6.sixrooms.bean.UserBean;
import cn.v6.sixrooms.constants.CommonStrs;
import cn.v6.sixrooms.utils.JsonParseUtils;
import org.json.JSONException;
import org.json.JSONObject;

final class bh extends VLAsyncHandler<String> {
    final /* synthetic */ UserInfoEngine a;

    bh(UserInfoEngine userInfoEngine) {
        this.a = userInfoEngine;
        super(null, 0);
    }

    protected final void handler(boolean z) {
        if (z) {
            try {
                JSONObject jSONObject = new JSONObject((String) getParam());
                String string = jSONObject.getString("flag");
                String string2 = jSONObject.getString("content");
                if ("001".equals(string)) {
                    JSONObject jSONObject2 = new JSONObject(string2);
                    UserBean userBean = (UserBean) JsonParseUtils.json2Obj(string2, UserBean.class);
                    if (userBean != null) {
                        UserInfoEngine.a(jSONObject2, userBean);
                        userBean.setPicuser(jSONObject2.getJSONObject("uoption").getString("picuser"));
                        UserInfoEngine.a(this.a).handleInfo(userBean);
                        return;
                    }
                    UserInfoEngine.a(this.a).error(1006);
                    return;
                }
                UserInfoEngine.a(this.a).handleErrorInfo(string, string2);
            } catch (JSONException e) {
                UserInfoEngine.a(this.a).error(1007);
                e.printStackTrace();
            }
        } else if (CommonStrs.NET_CONNECT_FAIL.equals(getStr()) && UserInfoEngine.a(this.a) != null) {
            UserInfoEngine.a(this.a).error(1006);
        }
    }
}
