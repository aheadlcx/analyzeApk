package com.spriteapp.booklibrary.util;

import android.util.Log;
import java.io.FileInputStream;
import java.io.InputStream;
import java.security.DigestInputStream;
import java.security.MessageDigest;

public class MD5Util {
    private static final String TAG = "TAG";

    public static String encryptMD5(String str) {
        return encryptMD5(str.getBytes());
    }

    public static String encryptMD5(byte[] bArr) {
        try {
            MessageDigest instance = MessageDigest.getInstance("MD5");
            instance.update(bArr);
            return ByteToHexUtil.fromByteToHex(instance.digest());
        } catch (Exception e) {
            Log.d(TAG, "encryptMD5: " + e.getMessage());
            return null;
        }
    }

    public static String getFileMD5(String str) {
        try {
            InputStream fileInputStream = new FileInputStream(str);
            DigestInputStream digestInputStream = new DigestInputStream(fileInputStream, MessageDigest.getInstance("MD5"));
            byte[] bArr = new byte[1024];
            for (int read = digestInputStream.read(bArr, 0, 1024); read != -1; read = digestInputStream.read(bArr, 0, 1024)) {
            }
            digestInputStream.close();
            fileInputStream.close();
            return ByteToHexUtil.fromByteToHex(digestInputStream.getMessageDigest().digest());
        } catch (Exception e) {
            Log.d(TAG, "getFileMD5: " + e.getMessage());
            return null;
        }
    }
}
