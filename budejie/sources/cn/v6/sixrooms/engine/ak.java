package cn.v6.sixrooms.engine;

import android.text.TextUtils;
import cn.v6.sixrooms.base.VLAsyncHandler;
import cn.v6.sixrooms.constants.CommonInts;
import cn.v6.sixrooms.constants.CommonStrs;
import cn.v6.sixrooms.constants.UrlStrs;
import cn.v6.sixrooms.encrypt.MyEncrypt;
import cn.v6.sixrooms.net.NetworkServiceSingleton;
import cn.v6.sixrooms.utils.AppInfoUtils;
import cn.v6.sixrooms.utils.LogUtils;
import cn.v6.sixrooms.utils.ShuMeiParameterUtils;
import java.net.URLEncoder;
import org.json.JSONObject;

final class ak extends VLAsyncHandler<String> {
    final /* synthetic */ PassportLoginEngine a;

    ak(PassportLoginEngine passportLoginEngine) {
        this.a = passportLoginEngine;
        super(null, 0);
    }

    protected final void handler(boolean z) {
        if (z) {
            String str = (String) getParam();
            if (!str.startsWith("[") && !str.endsWith("]")) {
                try {
                    Object decrypt = MyEncrypt.instance().decrypt(str, AppInfoUtils.getUUID(), Integer.parseInt(this.a.a.getCode().substring(0, 4)) % 32);
                    LogUtils.d("PassportLoginEngine", "doLogin---info----" + decrypt);
                    if (TextUtils.isEmpty(decrypt)) {
                        this.a.b.error(1007);
                        return;
                    }
                    NetworkServiceSingleton.createInstance().sendAsyncRequest(new al(this.a, new JSONObject(decrypt).getString("ticket")), (UrlStrs.LOGIN_CLIENT + "?ticket=" + URLEncoder.encode(new JSONObject(decrypt).getString("ticket")) + "&av=1.5" + ShuMeiParameterUtils.getParameterStr()).replace("|", "%7C"), "");
                } catch (Exception e) {
                    e.printStackTrace();
                    this.a.b.error(1007);
                }
            } else if (str.contains("-100") || str.contains("-101") || str.contains("-102") || str.contains("-103")) {
                this.a.b.loginSuccess(1009, null);
            } else {
                if (!str.contains("-200")) {
                    if (str.contains("-203") || str.contains("-204") || str.contains("-205") || str.contains("-208") || str.contains("-210")) {
                        this.a.b.loginSuccess(1001, null);
                        return;
                    } else if (str.contains("-211")) {
                        this.a.b.loginSuccess(1008, null);
                        return;
                    } else if (str.contains("-212")) {
                        this.a.b.loginSuccess(1011, null);
                        return;
                    } else if (str.contains("-201")) {
                        this.a.b.loginSuccess(CommonInts.PCK_ERROE_CODE, null);
                        return;
                    } else if (!(str.contains("-206") || str.contains("-207"))) {
                        if (str.contains("-222") || str.contains("-223")) {
                            this.a.b.loginSuccess(1010, null);
                            return;
                        } else if (str.contains("-250")) {
                            this.a.b.loginSuccess(1002, null);
                            return;
                        } else if (str.contains("-300") || str.contains("-301") || str.contains("-302") || str.contains("-400") || str.contains("-700")) {
                            this.a.b.loginSuccess(1009, null);
                            return;
                        } else {
                            str.contains("-900");
                        }
                    }
                }
                this.a.b.loginSuccess(1009, null);
            }
        } else if (CommonStrs.NET_CONNECT_FAIL.equals(getStr()) && this.a.b != null) {
            this.a.b.error(1006);
        }
    }
}
