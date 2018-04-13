package cn.v6.sixrooms.login.engines;

import cn.v6.sixrooms.constants.CommonInts;
import cn.v6.sixrooms.encrypt.MyEncrypt;
import cn.v6.sixrooms.login.beans.PassportLoginAndRegisterParams;
import cn.v6.sixrooms.login.interfaces.PassportRegisterCallback;
import cn.v6.sixrooms.net.NetworkServiceSingleton;
import cn.v6.sixrooms.utils.AppInfoUtils;
import cn.v6.sixrooms.utils.LogUtils;
import cn.v6.sixrooms.utils.MD5Utils;
import cn.v6.sixrooms.utils.phone.HistoryOpenHelper;
import com.baidu.mobads.interfaces.IXAdRequestInfo;
import com.umeng.analytics.pro.x;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;
import mtopsdk.mtop.antiattack.CheckCodeDO;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONObject;

public class PassportRegisterEngine {
    private static final String a = PassportRegisterEngine.class.getSimpleName();
    private PassportLoginAndRegisterParams b;
    private PassportRegisterCallback c;

    public void setParams(PassportLoginAndRegisterParams passportLoginAndRegisterParams) {
        this.b = passportLoginAndRegisterParams;
    }

    public void setPassportRegisterCallback(PassportRegisterCallback passportRegisterCallback) {
        this.c = passportRegisterCallback;
    }

    public void perRegister(String str, boolean z) {
        try {
            NetworkServiceSingleton.createInstance().sendAsyncRequest(new d(this, z), "http://passport.6.cn/sso/aPadPre.php?username=" + URLEncoder.encode(str, "UTF-8") + "&domain=Android&act=0&v=1", "");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void register() {
        List arrayList = new ArrayList();
        BasicNameValuePair basicNameValuePair = new BasicNameValuePair("pck", this.b.getPck());
        BasicNameValuePair basicNameValuePair2 = new BasicNameValuePair("dc", b());
        arrayList.add(basicNameValuePair);
        arrayList.add(basicNameValuePair2);
        NetworkServiceSingleton.createInstance().sendAsyncRequest(new e(this), "http://passport.6.cn/sso/aPadReg.php?un=" + MD5Utils.getMD5Str(this.b.getUsername()) + "&domain=Android", arrayList);
    }

    private String b() {
        int parseInt = Integer.parseInt(this.b.getCode().substring(0, 4)) % 32;
        String username = this.b.getUsername();
        String password = this.b.getPassword(false);
        String secret = MyEncrypt.instance().getSecret(this.b.getUsername(), AppInfoUtils.getUUID(), this.b.getPassword(false), this.b.getCode());
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put(HistoryOpenHelper.COLUMN_USERNAME, username);
            jSONObject.put("password", password);
            jSONObject.put("prod", "10007");
            jSONObject.put(x.c, secret);
            jSONObject.put(IXAdRequestInfo.V, "1");
            jSONObject.put("phone", this.b.getPhoneNumber());
            jSONObject.put(CheckCodeDO.CHECKCODE_USER_INPUT_KEY, this.b.getIdentifyingCode());
            LogUtils.e(a, "encryptData---jsonObj----" + jSONObject.toString());
            return MyEncrypt.instance().encrypt(jSONObject.toString(), AppInfoUtils.getUUID(), parseInt);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    static /* synthetic */ void a(PassportRegisterEngine passportRegisterEngine, String str) {
        if (str.contains("-200")) {
            passportRegisterEngine.c.perRegisterError(1003);
        } else if (str.contains("-208")) {
            passportRegisterEngine.c.perRegisterError(1001);
        } else if (str.contains("-211")) {
            passportRegisterEngine.c.perRegisterError(1008);
        } else if (str.contains("-212")) {
            passportRegisterEngine.c.perRegisterError(1011);
        } else if (str.contains("-100") || str.contains("-101")) {
            passportRegisterEngine.c.perRegisterError(1009);
        } else {
            str.contains("-301");
            passportRegisterEngine.c.perRegisterError(1009);
        }
    }

    static /* synthetic */ void b(PassportRegisterEngine passportRegisterEngine, String str) {
        if (str.contains("-100") || str.contains("-101") || str.contains("-102") || str.contains("-103")) {
            passportRegisterEngine.c.getTicketError(1009);
        } else if (str.contains("-208") || str.contains("-210")) {
            passportRegisterEngine.c.getTicketError(1001);
        } else if (str.contains("-211")) {
            passportRegisterEngine.c.getTicketError(1008);
        } else if (str.contains("-212")) {
            passportRegisterEngine.c.getTicketError(1011);
        } else if (str.contains("-250")) {
            passportRegisterEngine.c.getTicketError(1002);
        } else if (str.contains("-251")) {
            passportRegisterEngine.c.getTicketError(1003);
        } else if (str.contains("-201")) {
            passportRegisterEngine.c.getTicketError(CommonInts.PCK_ERROE_CODE);
        } else {
            if (!(str.contains("-220") || str.contains("-206"))) {
                if (str.contains("-300") || str.contains("-301") || str.contains("-302") || str.contains("-400") || str.contains("-900") || str.contains("-902") || str.contains("-903") || str.contains("-910")) {
                    passportRegisterEngine.c.getTicketError(1009);
                    return;
                } else if (str.contains("-214")) {
                    passportRegisterEngine.c.getTicketError(1004);
                    return;
                }
            }
            passportRegisterEngine.c.getTicketError(1009);
        }
    }
}
