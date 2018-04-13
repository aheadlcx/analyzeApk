package com.qq.e.comm.util;

import android.support.v4.view.InputDeviceCompat;
import android.util.Base64;
import com.baidu.mobstat.Config;
import com.tencent.connect.common.Constants;
import java.io.File;
import java.io.FileInputStream;
import java.security.MessageDigest;
import qsbk.app.core.model.CustomButton;
import qsbk.app.core.model.User;

public class Md5Util {
    private static final String[] a = new String[]{"0", "1", "2", "3", "4", "5", Constants.VIA_SHARE_TYPE_INFO, "7", Constants.VIA_SHARE_TYPE_PUBLISHVIDEO, "9", "a", CustomButton.POSITION_BOTTOM, "c", "d", Config.SESSTION_END_TIME, User.FEMALE};

    public static String byteArrayToHexString(byte[] bArr) {
        StringBuffer stringBuffer = new StringBuffer();
        for (int i : bArr) {
            int i2;
            if (i2 < 0) {
                i2 += 256;
            }
            stringBuffer.append(a[i2 / 16] + a[i2 % 16]);
        }
        return stringBuffer.toString();
    }

    public static String encode(File file) {
        Throwable th;
        if (file == null) {
            return "";
        }
        FileInputStream fileInputStream = null;
        FileInputStream fileInputStream2;
        try {
            MessageDigest instance = MessageDigest.getInstance("MD5");
            fileInputStream2 = new FileInputStream(file);
            try {
                byte[] bArr = new byte[1024];
                while (true) {
                    int read = fileInputStream2.read(bArr);
                    if (read > 0) {
                        instance.update(bArr, 0, read);
                    } else {
                        String byteArrayToHexString = byteArrayToHexString(instance.digest());
                        try {
                            fileInputStream2.close();
                            return byteArrayToHexString;
                        } catch (Exception e) {
                            return byteArrayToHexString;
                        }
                    }
                }
            } catch (Exception e2) {
                fileInputStream = fileInputStream2;
                if (fileInputStream != null) {
                    try {
                        fileInputStream.close();
                    } catch (Exception e3) {
                    }
                }
                return "";
            } catch (Throwable th2) {
                th = th2;
                if (fileInputStream2 != null) {
                    try {
                        fileInputStream2.close();
                    } catch (Exception e4) {
                    }
                }
                throw th;
            }
        } catch (Exception e5) {
            if (fileInputStream != null) {
                fileInputStream.close();
            }
            return "";
        } catch (Throwable th3) {
            Throwable th4 = th3;
            fileInputStream2 = null;
            th = th4;
            if (fileInputStream2 != null) {
                fileInputStream2.close();
            }
            throw th;
        }
    }

    public static String encode(String str) {
        try {
            String str2 = new String(str);
            try {
                return byteArrayToHexString(MessageDigest.getInstance("MD5").digest(str2.getBytes()));
            } catch (Exception e) {
                return str2;
            }
        } catch (Exception e2) {
            return null;
        }
    }

    public static String encodeBase64String(String str) {
        try {
            return byteArrayToHexString(MessageDigest.getInstance("MD5").digest(Base64.decode(str, 0)));
        } catch (Throwable e) {
            GDTLogger.e("Exception while md5 base64String", e);
            return null;
        }
    }

    public static byte[] hexStringtoByteArray(String str) {
        if (str.length() % 2 != 0) {
            return null;
        }
        byte[] bArr = new byte[(str.length() / 2)];
        for (int i = 0; i < str.length() - 1; i += 2) {
            char charAt = str.charAt(i);
            char charAt2 = str.charAt(i + 1);
            charAt = Character.toLowerCase(charAt);
            charAt2 = Character.toLowerCase(charAt2);
            int i2 = (charAt <= '9' ? charAt - 48 : (charAt - 97) + 10) << 4;
            i2 = charAt2 <= '9' ? i2 + (charAt2 - 48) : i2 + ((charAt2 - 97) + 10);
            if (i2 > 127) {
                i2 += InputDeviceCompat.SOURCE_ANY;
            }
            bArr[i / 2] = (byte) i2;
        }
        return bArr;
    }
}
