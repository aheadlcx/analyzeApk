package cn.xiaochuankeji.tieba.background.utils.monitor.crash;

import java.io.File;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class a {
    private final File a;

    public a(File file) {
        this.a = file;
    }

    public static String a(String str) {
        MessageDigest messageDigest = null;
        try {
            messageDigest = MessageDigest.getInstance("MD5");
        } catch (NoSuchAlgorithmException e) {
        }
        messageDigest.update(str.getBytes());
        byte[] digest = messageDigest.digest();
        StringBuilder stringBuilder = new StringBuilder(digest.length * 2);
        for (byte b : digest) {
            if ((b & 255) < 16) {
                stringBuilder.append("0");
            }
            stringBuilder.append(Integer.toHexString(b & 255));
        }
        return stringBuilder.toString() + ".log";
    }

    public File a() {
        return this.a;
    }

    public boolean b() {
        return this.a.getName().endsWith(".logged");
    }

    public void c() {
        if (!b()) {
            this.a.renameTo(new File(this.a.getAbsolutePath().replace(".log", ".logged")));
        }
    }
}
