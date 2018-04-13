package qsbk.app.core.utils;

import android.support.v4.media.session.PlaybackStateCompat;
import android.util.Log;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.math.BigInteger;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel.MapMode;
import java.security.MessageDigest;

public class Md5Utils {
    private static final String a = Md5Utils.class.getSimpleName();

    public static String encryptMD5(byte[] bArr) {
        MessageDigest messageDigest = null;
        try {
            messageDigest = MessageDigest.getInstance("MD5");
        } catch (Throwable e) {
            Log.d(a, "md5 加密异常", e);
        }
        messageDigest.update(bArr);
        return byte2hex(messageDigest.digest());
    }

    public static String encryptMD5(String str) {
        MessageDigest messageDigest = null;
        try {
            messageDigest = MessageDigest.getInstance("MD5");
        } catch (Throwable e) {
            Log.d(a, "md5 加密异常", e);
        }
        messageDigest.update(str.getBytes());
        return byte2hex(messageDigest.digest());
    }

    public static String encryptMD5(String str, String str2) {
        MessageDigest messageDigest = null;
        try {
            messageDigest = MessageDigest.getInstance("MD5");
        } catch (Throwable e) {
            Log.d(a, "md5 加密异常", e);
        }
        messageDigest.update((str + str2).getBytes());
        return byte2hex(messageDigest.digest());
    }

    public static String byte2hex(byte[] bArr) {
        StringBuilder stringBuilder = new StringBuilder();
        for (byte b : bArr) {
            String toHexString = Integer.toHexString(b & 255);
            if (toHexString.length() == 1) {
                stringBuilder.append("0");
            }
            stringBuilder.append(toHexString.toUpperCase());
        }
        return stringBuilder.toString();
    }

    public static byte[] hex2byte(String str) {
        if (str == null) {
            return null;
        }
        String trim = str.trim();
        int length = trim.length();
        if (length <= 0 || length % 2 == 1) {
            return null;
        }
        byte[] bArr = new byte[(length / 2)];
        int i = 0;
        while (i < trim.length()) {
            try {
                bArr[i / 2] = (byte) Integer.decode("0x" + trim.substring(i, i + 2)).intValue();
                i += 2;
            } catch (Exception e) {
                return null;
            }
        }
        return bArr;
    }

    public static String getMd5ByFile(File file) {
        return getMd5ByFile(file, false);
    }

    public static String getMd5ByFile(File file, boolean z) {
        FileInputStream fileInputStream;
        String bigInteger;
        Exception e;
        FileInputStream fileInputStream2;
        Throwable th;
        long j = PlaybackStateCompat.ACTION_SET_CAPTIONING_ENABLED;
        try {
            fileInputStream = new FileInputStream(file);
            try {
                long length = file.length();
                if (length <= PlaybackStateCompat.ACTION_SET_CAPTIONING_ENABLED || z) {
                    j = length;
                }
                ByteBuffer map = fileInputStream.getChannel().map(MapMode.READ_ONLY, 0, j);
                MessageDigest instance = MessageDigest.getInstance("MD5");
                instance.update(map);
                bigInteger = new BigInteger(1, instance.digest()).toString(16);
                while (bigInteger.length() < 32) {
                    try {
                        bigInteger = "0" + bigInteger;
                    } catch (Exception e2) {
                        e = e2;
                        fileInputStream2 = fileInputStream;
                    } catch (OutOfMemoryError e3) {
                    }
                }
                if (fileInputStream != null) {
                    try {
                        fileInputStream.close();
                    } catch (IOException e4) {
                        e4.printStackTrace();
                    }
                }
            } catch (Exception e5) {
                e = e5;
                fileInputStream2 = fileInputStream;
                bigInteger = null;
                try {
                    e.printStackTrace();
                    if (fileInputStream2 != null) {
                        try {
                            fileInputStream2.close();
                        } catch (IOException e42) {
                            e42.printStackTrace();
                        }
                    }
                    return bigInteger;
                } catch (Throwable th2) {
                    th = th2;
                    fileInputStream = fileInputStream2;
                    if (fileInputStream != null) {
                        try {
                            fileInputStream.close();
                        } catch (IOException e422) {
                            e422.printStackTrace();
                        }
                    }
                    throw th;
                }
            } catch (OutOfMemoryError e6) {
                bigInteger = null;
                try {
                    System.gc();
                    if (fileInputStream != null) {
                        try {
                            fileInputStream.close();
                        } catch (IOException e4222) {
                            e4222.printStackTrace();
                        }
                    }
                    return bigInteger;
                } catch (Throwable th3) {
                    th = th3;
                    if (fileInputStream != null) {
                        fileInputStream.close();
                    }
                    throw th;
                }
            }
        } catch (Exception e52) {
            e = e52;
            fileInputStream2 = null;
            bigInteger = null;
            e.printStackTrace();
            if (fileInputStream2 != null) {
                fileInputStream2.close();
            }
            return bigInteger;
        } catch (OutOfMemoryError e7) {
            fileInputStream = null;
            bigInteger = null;
            System.gc();
            if (fileInputStream != null) {
                fileInputStream.close();
            }
            return bigInteger;
        } catch (Throwable th4) {
            th = th4;
            fileInputStream = null;
            if (fileInputStream != null) {
                fileInputStream.close();
            }
            throw th;
        }
        return bigInteger;
    }
}
