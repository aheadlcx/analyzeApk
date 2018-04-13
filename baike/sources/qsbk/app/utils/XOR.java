package qsbk.app.utils;

public class XOR {
    public static String encode(String str, String str2) {
        byte[] bytes = str.getBytes();
        byte[] bytes2 = str2.getBytes();
        byte[] bArr = new byte[bytes.length];
        for (int i = 0; i < bytes.length; i++) {
            bArr[i] = (byte) (bytes[i] ^ bytes2[i % bytes2.length]);
        }
        return Base64.encodeBytes(bArr);
    }
}
