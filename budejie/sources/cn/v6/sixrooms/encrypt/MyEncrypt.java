package cn.v6.sixrooms.encrypt;

import android.util.Base64;
import android.util.Log;

public class MyEncrypt {
    private static final String TAG = MyEncrypt.class.getSimpleName();
    private static MyEncrypt myEncrypt = new MyEncrypt();

    public native String aesECBdecrypt(byte[] bArr, String str, int i);

    public native byte[] aesECBencrypt(String str, String str2, int i);

    public native String encrypt(String str, String str2);

    public native String getPassword(String str, String str2, String str3);

    public native String getSecret(String str, String str2, String str3, String str4);

    public native boolean init(String str, String str2, String str3, String str4, String str5);

    public native String md5(String str, int i);

    static {
        try {
            System.loadLibrary("Encrypt");
        } catch (Exception e) {
            Log.w(TAG, "Can't load library libEncrypt.so");
        }
    }

    private MyEncrypt() {
    }

    public static MyEncrypt instance() {
        return myEncrypt;
    }

    public String encrypt(String str, String str2, int i) {
        return new String(Base64.encode(aesECBencrypt(str, str2, i), 0));
    }

    public String decrypt(String str, String str2, int i) {
        return aesECBdecrypt(Base64.decode(str, 0), str2, i);
    }
}
