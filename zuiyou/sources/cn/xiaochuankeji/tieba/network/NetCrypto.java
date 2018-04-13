package cn.xiaochuankeji.tieba.network;

import cn.xiaochuan.base.BaseApplication;
import com.getkeepsafe.relinker.b;
import org.json.JSONObject;

public class NetCrypto {
    public static native String generateSign(byte[] bArr);

    private static native void native_init();

    static {
        try {
            System.loadLibrary("net_crypto");
        } catch (UnsatisfiedLinkError e) {
            b.a(BaseApplication.getAppContext(), "net_crypto");
        }
        native_init();
    }

    public static String a(String str, JSONObject jSONObject) {
        return a(str, jSONObject.toString());
    }

    public static String a(String str, String str2) {
        String generateSign = generateSign(str2.getBytes());
        if (str.contains("?")) {
            generateSign = "&sign=" + generateSign;
        } else {
            generateSign = "?sign=" + generateSign;
        }
        return str + generateSign;
    }
}
