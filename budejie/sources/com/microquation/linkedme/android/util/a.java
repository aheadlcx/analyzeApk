package com.microquation.linkedme.android.util;

import android.util.Base64;
import java.io.UnsupportedEncodingException;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.Key;
import java.security.NoSuchAlgorithmException;
import java.security.spec.AlgorithmParameterSpec;
import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

public class a {
    public static String a(String str, String str2) {
        try {
            byte[] bytes = str.getBytes("UTF-8");
            Key secretKeySpec = new SecretKeySpec(str2.getBytes(), "AES");
            AlgorithmParameterSpec ivParameterSpec = new IvParameterSpec("16-Bytes--String".getBytes());
            Cipher instance = Cipher.getInstance("AES/CBC/PKCS5Padding");
            instance.init(1, secretKeySpec, ivParameterSpec);
            return Base64.encodeToString(instance.doFinal(bytes), 2);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            return "";
        } catch (InvalidKeyException e2) {
            e2.printStackTrace();
            return "";
        } catch (InvalidAlgorithmParameterException e3) {
            e3.printStackTrace();
            return "";
        } catch (NoSuchPaddingException e4) {
            e4.printStackTrace();
            return "";
        } catch (BadPaddingException e5) {
            e5.printStackTrace();
            return "";
        } catch (UnsupportedEncodingException e6) {
            e6.printStackTrace();
            return "";
        } catch (IllegalBlockSizeException e7) {
            e7.printStackTrace();
            return "";
        }
    }
}
