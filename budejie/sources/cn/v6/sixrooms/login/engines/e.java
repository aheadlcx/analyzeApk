package cn.v6.sixrooms.login.engines;

import android.text.TextUtils;
import cn.v6.sixrooms.base.VLAsyncHandler;
import cn.v6.sixrooms.constants.CommonStrs;
import cn.v6.sixrooms.encrypt.MyEncrypt;
import cn.v6.sixrooms.utils.AppInfoUtils;
import cn.v6.sixrooms.utils.LogUtils;
import org.json.JSONException;
import org.json.JSONObject;

final class e extends VLAsyncHandler<String> {
    final /* synthetic */ PassportRegisterEngine a;

    e(PassportRegisterEngine passportRegisterEngine) {
        this.a = passportRegisterEngine;
        super(null, 0);
    }

    protected final void handler(boolean z) {
        if (z) {
            String str = (String) getParam();
            if (str == null) {
                return;
            }
            if (str.startsWith("[") || str.endsWith("]")) {
                PassportRegisterEngine.b(this.a, str);
                return;
            }
            try {
                Object decrypt = MyEncrypt.instance().decrypt(str, AppInfoUtils.getUUID(), Integer.parseInt(this.a.b.getCode().substring(0, 4)) % 32);
                LogUtils.d(PassportRegisterEngine.a, "doRegister---info----" + decrypt);
                if (TextUtils.isEmpty(decrypt)) {
                    this.a.c.error(1007);
                    return;
                }
                this.a.c.getTicketSuccess(new JSONObject(decrypt).getString("ticket"));
            } catch (JSONException e) {
                e.printStackTrace();
                this.a.c.error(1007);
            }
        } else if (CommonStrs.NET_CONNECT_FAIL.equals(getStr())) {
            this.a.c.error(1007);
        }
    }
}
