package cn.v6.sixrooms.login.engines;

import cn.v6.sixrooms.base.VLAsyncHandler;
import cn.v6.sixrooms.constants.CommonStrs;
import cn.v6.sixrooms.encrypt.MyEncrypt;
import cn.v6.sixrooms.utils.AppInfoUtils;
import cn.v6.sixrooms.utils.LogUtils;
import mtopsdk.mtop.antiattack.CheckCodeDO;
import org.json.JSONObject;

final class d extends VLAsyncHandler<String> {
    final /* synthetic */ boolean a;
    final /* synthetic */ PassportRegisterEngine b;

    d(PassportRegisterEngine passportRegisterEngine, boolean z) {
        this.b = passportRegisterEngine;
        this.a = z;
        super(null, 0);
    }

    protected final void handler(boolean z) {
        if (z || !CommonStrs.NET_CONNECT_FAIL.equals(getStr())) {
            String str = (String) getParam();
            if (str.startsWith("[") || str.endsWith("]")) {
                PassportRegisterEngine.a(this.b, str);
                return;
            }
            try {
                str = MyEncrypt.instance().decrypt(str, AppInfoUtils.getUUID(), 3);
                LogUtils.e(PassportRegisterEngine.a, "doPerRegister---info----" + str);
                JSONObject jSONObject = new JSONObject(str);
                this.b.b.setPck(jSONObject.getString("pck"));
                this.b.b.setCode(jSONObject.getString(CheckCodeDO.CHECKCODE_USER_INPUT_KEY));
                this.b.c.perRegisterSuccess(this.a);
                return;
            } catch (Exception e) {
                e.printStackTrace();
                this.b.c.error(1007);
                return;
            }
        }
        this.b.c.error(1006);
    }
}
