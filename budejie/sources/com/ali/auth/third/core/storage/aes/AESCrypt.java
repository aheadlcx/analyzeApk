package com.ali.auth.third.core.storage.aes;

import android.util.Base64;
import android.util.Log;
import com.ali.auth.third.core.util.CommonUtils;
import com.baidu.mobads.openad.c.b;
import java.io.UnsupportedEncodingException;
import java.security.GeneralSecurityException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

public final class AESCrypt {
    private static final String AES_MODE = "AES/CBC/PKCS7Padding";
    private static final String CHARSET = "UTF-8";
    public static boolean DEBUG_LOG_ENABLED = false;
    private static final String HASH_ALGORITHM = "SHA-256";
    private static final String TAG = "AESCrypt";
    private static final byte[] ivBytes = new byte[]{(byte) 0, (byte) 0, (byte) 0, (byte) 0, (byte) 0, (byte) 0, (byte) 0, (byte) 0, (byte) 0, (byte) 0, (byte) 0, (byte) 0, (byte) 0, (byte) 0, (byte) 0, (byte) 0};

    private static SecretKeySpec generateKey(String str) throws NoSuchAlgorithmException, UnsupportedEncodingException {
        MessageDigest instance = MessageDigest.getInstance(HASH_ALGORITHM);
        byte[] bytes = str.getBytes("UTF-8");
        instance.update(bytes, 0, bytes.length);
        byte[] digest = instance.digest();
        log("SHA-256 key ", digest);
        return new SecretKeySpec(digest, "AES");
    }

    public static String encrypt(String str, String str2) throws GeneralSecurityException {
        try {
            SecretKeySpec generateKey = generateKey(str);
            log(b.EVENT_MESSAGE, str2);
            String encodeToString = Base64.encodeToString(encrypt(generateKey, ivBytes, str2.getBytes("UTF-8")), 2);
            log("Base64.NO_WRAP", encodeToString);
            return encodeToString;
        } catch (Throwable e) {
            if (DEBUG_LOG_ENABLED) {
                Log.e(TAG, "UnsupportedEncodingException ", e);
            }
            throw new GeneralSecurityException(e);
        }
    }

    public static byte[] encrypt(SecretKeySpec secretKeySpec, byte[] bArr, byte[] bArr2) throws GeneralSecurityException {
        Cipher instance = Cipher.getInstance(AES_MODE);
        instance.init(1, secretKeySpec, new IvParameterSpec(bArr));
        byte[] doFinal = instance.doFinal(bArr2);
        log("cipherText", doFinal);
        return doFinal;
    }

    public static String decrypt(String str, String str2) throws GeneralSecurityException {
        try {
            SecretKeySpec generateKey = generateKey(str);
            log("base64EncodedCipherText", str2);
            byte[] decode = Base64.decode(str2, 2);
            log("decodedCipherText", decode);
            byte[] decrypt = decrypt(generateKey, ivBytes, decode);
            log("decryptedBytes", decrypt);
            String str3 = new String(decrypt, "UTF-8");
            log(b.EVENT_MESSAGE, str3);
            return str3;
        } catch (Throwable e) {
            if (DEBUG_LOG_ENABLED) {
                Log.e(TAG, "UnsupportedEncodingException ", e);
            }
            throw new GeneralSecurityException(e);
        }
    }

    public static byte[] decrypt(SecretKeySpec secretKeySpec, byte[] bArr, byte[] bArr2) throws GeneralSecurityException {
        Cipher instance = Cipher.getInstance(AES_MODE);
        instance.init(2, secretKeySpec, new IvParameterSpec(bArr));
        byte[] doFinal = instance.doFinal(bArr2);
        log("decryptedBytes", doFinal);
        return doFinal;
    }

    private static void log(String str, byte[] bArr) {
        if (DEBUG_LOG_ENABLED) {
            Log.d(TAG, str + "[" + bArr.length + "] [" + CommonUtils.getHashString(bArr) + "]");
        }
    }

    private static void log(String str, String str2) {
        if (DEBUG_LOG_ENABLED) {
            Log.d(TAG, str + "[" + str2.length() + "] [" + str2 + "]");
        }
    }

    private AESCrypt() {
    }
}
