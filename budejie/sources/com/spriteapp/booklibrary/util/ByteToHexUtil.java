package com.spriteapp.booklibrary.util;

public class ByteToHexUtil {
    public static String fromByteToHex(byte[] bArr) {
        StringBuilder stringBuilder = new StringBuilder();
        for (byte b : bArr) {
            if (Integer.toHexString(b & 255).length() == 1) {
                stringBuilder.append(0).append(Integer.toHexString(b & 255));
            } else {
                stringBuilder.append(Integer.toHexString(b & 255));
            }
        }
        return stringBuilder.toString();
    }
}
