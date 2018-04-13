package u.fb;

import java.security.MessageDigest;

public class g {
    public static final String a = System.getProperty("line.separator");

    public static String a(String str) {
        try {
            MessageDigest instance = MessageDigest.getInstance("MD5");
            instance.update(str.getBytes());
            byte[] digest = instance.digest();
            StringBuffer stringBuffer = new StringBuffer();
            for (byte b : digest) {
                stringBuffer.append(Integer.toHexString(b & 255));
            }
            return stringBuffer.toString();
        } catch (Exception e) {
            b.a("helper", "getMD5 error", e);
            return "";
        }
    }

    public static boolean b(String str) {
        return str == null || str.length() == 0;
    }
}
