package qsbk.app.http;

import android.text.TextUtils;
import android.util.Base64;
import java.util.Arrays;
import org.json.JSONObject;
import qsbk.app.utils.AES;
import qsbk.app.utils.HttpClient;

public class EncryptDecryptUtil {
    public static String processDecrypt(String str) {
        if (TextUtils.isEmpty(str)) {
            return "";
        }
        byte[] decode = Base64.decode(str, 2);
        if (decode == null || decode.length <= 16) {
            return str;
        }
        byte[] copyOfRange = Arrays.copyOfRange(decode, 0, 16);
        try {
            return new String(AES.decrypt(getSecret(), Arrays.copyOfRange(decode, 16, decode.length), copyOfRange));
        } catch (Exception e) {
            e.printStackTrace();
            return str;
        }
    }

    public static String processParamsEncrypt(String str) {
        try {
            JSONObject jSONObject = new JSONObject();
            jSONObject.put("id", "1");
            jSONObject.put("data", processEncrypt(str));
            str = jSONObject.toString();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return str;
    }

    public static JSONObject processJsonEncrypt(String str) {
        try {
            JSONObject jSONObject = new JSONObject();
            jSONObject.put("id", "1");
            jSONObject.put("data", processEncrypt(str));
            return jSONObject;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public static String processEncrypt(String str) {
        if (TextUtils.isEmpty(str)) {
            return "";
        }
        Object encryptIV = getEncryptIV();
        try {
            Object encryptToByteArray = AES.encryptToByteArray(getSecret(), str, encryptIV);
            Object obj = new byte[(encryptIV.length + encryptToByteArray.length)];
            System.arraycopy(encryptIV, 0, obj, 0, encryptIV.length);
            System.arraycopy(encryptToByteArray, 0, obj, encryptIV.length, encryptToByteArray.length);
            return Base64.encodeToString(obj, 2);
        } catch (Exception e) {
            e.printStackTrace();
            return str;
        }
    }

    public static byte[] getEncryptIV() {
        return AES.createIV();
    }

    public static byte[] getSecret() {
        return HttpClient.getEnString().getBytes();
    }
}
