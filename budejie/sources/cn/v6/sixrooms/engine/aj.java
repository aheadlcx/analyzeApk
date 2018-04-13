package cn.v6.sixrooms.engine;

import android.text.TextUtils;
import cn.v6.sixrooms.base.VLAsyncHandler;
import cn.v6.sixrooms.constants.CommonStrs;
import cn.v6.sixrooms.encrypt.MyEncrypt;
import cn.v6.sixrooms.utils.AppInfoUtils;
import cn.v6.sixrooms.utils.LogUtils;
import mtopsdk.mtop.antiattack.CheckCodeDO;
import org.json.JSONObject;

final class aj extends VLAsyncHandler<String> {
    final /* synthetic */ boolean a;
    final /* synthetic */ PassportLoginEngine b;

    aj(PassportLoginEngine passportLoginEngine, boolean z) {
        this.b = passportLoginEngine;
        this.a = z;
        super(null, 0);
    }

    protected final void handler(boolean z) {
        if (z) {
            String str = (String) getParam();
            if (str.startsWith("[") && str.endsWith("]")) {
                LogUtils.i("PassportLoginEngine", "doPerLogin----result1111111:" + str);
                if (str.contains("-200")) {
                    this.b.b.loginSuccess(1010, null);
                    return;
                } else if (str.contains("-208")) {
                    this.b.b.loginSuccess(1001, null);
                    return;
                } else if (str.contains("-211")) {
                    this.b.b.loginSuccess(1008, null);
                    return;
                } else if (str.contains("-212")) {
                    this.b.b.loginSuccess(1011, null);
                    return;
                } else if (str.contains("-100") || str.contains("-101")) {
                    this.b.b.loginSuccess(1009, null);
                    return;
                } else if (str.contains("-301")) {
                    this.b.b.loginSuccess(1009, null);
                    return;
                } else {
                    this.b.b.loginSuccess(1009, null);
                    return;
                }
            }
            try {
                Object decrypt = MyEncrypt.instance().decrypt((String) getParam(), AppInfoUtils.getUUID(), 3);
                LogUtils.d("PassportLoginEngine", "doPerLogin---info----" + decrypt);
                if (TextUtils.isEmpty(decrypt)) {
                    this.b.b.error(1007);
                    return;
                }
                JSONObject jSONObject = new JSONObject(decrypt);
                this.b.a.setPck(jSONObject.getString("pck"));
                this.b.a.setCode(jSONObject.getString(CheckCodeDO.CHECKCODE_USER_INPUT_KEY));
                JSONObject jSONObject2 = new JSONObject(jSONObject.getString("gt"));
                this.b.b.preLogin(Boolean.valueOf(false), this.a, jSONObject2.getString("gt"), jSONObject2.getString("challenge"), jSONObject2.getString("success"));
            } catch (Exception e) {
                this.b.b.error(1007);
                e.printStackTrace();
            }
        } else if (CommonStrs.NET_CONNECT_FAIL.equals(getStr()) && this.b.b != null) {
            this.b.b.error(1006);
        }
    }
}
