package cn.v6.sixrooms.engine;

import cn.v6.sixrooms.base.VLAsyncHandler;
import cn.v6.sixrooms.constants.CommonStrs;
import cn.v6.sixrooms.utils.LogUtils;
import cn.v6.sixrooms.utils.phone.HistoryOpenHelper;
import java.util.HashMap;
import java.util.Map;
import org.json.JSONException;
import org.json.JSONObject;

final class w extends VLAsyncHandler<String> {
    final /* synthetic */ GetAuthCodeForResetPwdEngine a;

    w(GetAuthCodeForResetPwdEngine getAuthCodeForResetPwdEngine) {
        this.a = getAuthCodeForResetPwdEngine;
        super(null, 0);
    }

    protected final void handler(boolean z) {
        if (z) {
            try {
                JSONObject jSONObject = new JSONObject((String) getParam());
                String string = jSONObject.getString("flag");
                String string2 = jSONObject.getString("content");
                LogUtils.i(GetAuthCodeForResetPwdEngine.TAG, "result_verifyCode==content" + string2);
                if ("001".equals(string)) {
                    JSONObject jSONObject2 = new JSONObject(string2);
                    string2 = jSONObject2.getString(HistoryOpenHelper.COLUMN_UID);
                    string = jSONObject2.getString("msg");
                    Map hashMap = new HashMap();
                    hashMap.put(HistoryOpenHelper.COLUMN_UID, string2);
                    hashMap.put("msg", string);
                    this.a.a.verifyCodeSucceed(hashMap);
                    return;
                }
                this.a.a.handleErrorInfo(string, string2);
            } catch (JSONException e) {
                this.a.a.error(1007);
            }
        } else if (CommonStrs.NET_CONNECT_FAIL.equals(getStr()) && this.a.a != null) {
            this.a.a.error(1006);
        }
    }
}
