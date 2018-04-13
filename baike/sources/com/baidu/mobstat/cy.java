package com.baidu.mobstat;

import java.io.File;
import java.io.FileInputStream;
import java.security.MessageDigest;

public final class cy {
    private static String b(MessageDigest messageDigest, byte[] bArr) {
        messageDigest.update(bArr);
        return a(messageDigest.digest());
    }

    private static String b(MessageDigest messageDigest, File file) {
        FileInputStream fileInputStream;
        Throwable e;
        if (!file.isFile()) {
            return "";
        }
        try {
            fileInputStream = new FileInputStream(file);
            try {
                byte[] bArr = new byte[4048];
                while (true) {
                    int read = fileInputStream.read(bArr);
                    if (read == -1) {
                        break;
                    }
                    messageDigest.update(bArr, 0, read);
                }
                if (fileInputStream != null) {
                    try {
                        fileInputStream.close();
                    } catch (Throwable e2) {
                        db.b(e2);
                    }
                }
            } catch (Exception e3) {
                e2 = e3;
            }
        } catch (Exception e4) {
            e2 = e4;
            fileInputStream = null;
            try {
                db.b(e2);
                if (fileInputStream != null) {
                    try {
                        fileInputStream.close();
                    } catch (Throwable e22) {
                        db.b(e22);
                    }
                }
                return a(messageDigest.digest());
            } catch (Throwable th) {
                e22 = th;
                if (fileInputStream != null) {
                    try {
                        fileInputStream.close();
                    } catch (Throwable e5) {
                        db.b(e5);
                    }
                }
                throw e22;
            }
        } catch (Throwable th2) {
            e22 = th2;
            fileInputStream = null;
            if (fileInputStream != null) {
                fileInputStream.close();
            }
            throw e22;
        }
        return a(messageDigest.digest());
    }

    private static String a(byte[] bArr) {
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < bArr.length; i++) {
            int i2 = (bArr[i] >> 4) & 15;
            int i3 = bArr[i] & 15;
            stringBuilder.append((char) (i2 >= 10 ? (i2 + 97) - 10 : i2 + 48));
            if (i3 >= 10) {
                i2 = (i3 + 97) - 10;
            } else {
                i2 = i3 + 48;
            }
            stringBuilder.append((char) i2);
        }
        return stringBuilder.toString();
    }
}
