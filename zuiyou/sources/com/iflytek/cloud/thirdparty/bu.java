package com.iflytek.cloud.thirdparty;

import android.os.Build.VERSION;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.security.MessageDigest;
import java.util.zip.GZIPInputStream;
import java.util.zip.GZIPOutputStream;

public class bu {
    public static synchronized String a(String str) {
        String stringBuffer;
        int i = 0;
        synchronized (bu.class) {
            try {
                MessageDigest instance = MessageDigest.getInstance("MD5");
                char[] toCharArray = str.toCharArray();
                byte[] bArr = new byte[toCharArray.length];
                for (int i2 = 0; i2 < toCharArray.length; i2++) {
                    bArr[i2] = (byte) toCharArray[i2];
                }
                byte[] digest = instance.digest(bArr);
                StringBuffer stringBuffer2 = new StringBuffer();
                while (i < digest.length) {
                    int i3 = digest[i] & 255;
                    if (i3 < 16) {
                        stringBuffer2.append("0");
                    }
                    stringBuffer2.append(Integer.toHexString(i3));
                    i++;
                }
                stringBuffer = stringBuffer2.toString();
            } catch (Throwable e) {
                cb.a(e);
                stringBuffer = "";
            }
        }
        return stringBuffer;
    }

    public static byte[] a(byte[] bArr) {
        if (bArr == null) {
            return null;
        }
        for (int i = 0; i < bArr.length; i++) {
            bArr[i] = (byte) (bArr[i] ^ 5);
        }
        return bArr;
    }

    public static synchronized String b(String str) {
        String str2 = null;
        synchronized (bu.class) {
            cb.a("cut16MD5 start:" + str);
            String a = a(str);
            cb.a("cut16MD5 start:" + a);
            StringBuffer stringBuffer = new StringBuffer();
            if (!(a == null || a.length() == 0)) {
                cb.a("cut16MD5 md5 size is:" + a.length());
                for (int i = 0; i < a.length(); i++) {
                    if (i % 2 == 0) {
                        cb.a("cut16MD5 result i:" + a.charAt(i));
                        stringBuffer.append(a.charAt(i));
                    }
                    cb.a("cut16MD5 result i = :" + i);
                }
                cb.a("cut16MD5 result:" + stringBuffer);
                if (stringBuffer != null) {
                    str2 = stringBuffer.toString();
                }
            }
        }
        return str2;
    }

    public static byte[] b(byte[] bArr) {
        GZIPOutputStream gZIPOutputStream;
        Throwable e;
        Throwable th;
        byte[] bArr2 = null;
        if (bArr != null) {
            OutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            try {
                gZIPOutputStream = new GZIPOutputStream(byteArrayOutputStream);
                try {
                    gZIPOutputStream.write(bArr);
                    gZIPOutputStream.finish();
                    if (VERSION.SDK_INT < 19) {
                        gZIPOutputStream.flush();
                    }
                    bArr2 = byteArrayOutputStream.toByteArray();
                    for (int i = 0; i < bArr2.length; i++) {
                        bArr2[i] = (byte) (bArr2[i] ^ 5);
                    }
                    if (gZIPOutputStream != null) {
                        try {
                            gZIPOutputStream.close();
                        } catch (Throwable e2) {
                            cb.a(e2);
                        }
                    }
                    if (byteArrayOutputStream != null) {
                        byteArrayOutputStream.close();
                    }
                } catch (IOException e3) {
                    e2 = e3;
                    try {
                        cb.a(e2);
                        if (gZIPOutputStream != null) {
                            try {
                                gZIPOutputStream.close();
                            } catch (Throwable e22) {
                                cb.a(e22);
                            }
                        }
                        if (byteArrayOutputStream != null) {
                            byteArrayOutputStream.close();
                        }
                        return bArr2;
                    } catch (Throwable th2) {
                        th = th2;
                        if (gZIPOutputStream != null) {
                            try {
                                gZIPOutputStream.close();
                            } catch (Throwable e222) {
                                cb.a(e222);
                                throw th;
                            }
                        }
                        if (byteArrayOutputStream != null) {
                            byteArrayOutputStream.close();
                        }
                        throw th;
                    }
                }
            } catch (IOException e4) {
                e222 = e4;
                gZIPOutputStream = null;
                cb.a(e222);
                if (gZIPOutputStream != null) {
                    gZIPOutputStream.close();
                }
                if (byteArrayOutputStream != null) {
                    byteArrayOutputStream.close();
                }
                return bArr2;
            } catch (Throwable e2222) {
                gZIPOutputStream = null;
                th = e2222;
                if (gZIPOutputStream != null) {
                    gZIPOutputStream.close();
                }
                if (byteArrayOutputStream != null) {
                    byteArrayOutputStream.close();
                }
                throw th;
            }
        }
        return bArr2;
    }

    public static byte[] c(byte[] bArr) {
        byte[] bArr2;
        Throwable th;
        Throwable e;
        Throwable th2;
        if (bArr == null) {
            return null;
        }
        for (int i = 0; i < bArr.length; i++) {
            bArr[i] = (byte) (bArr[i] ^ 5);
        }
        InputStream byteArrayInputStream = new ByteArrayInputStream(bArr);
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        GZIPInputStream gZIPInputStream;
        try {
            gZIPInputStream = new GZIPInputStream(byteArrayInputStream);
            try {
                bArr2 = new byte[1024];
                while (true) {
                    int read = gZIPInputStream.read(bArr2, 0, bArr2.length);
                    if (read == -1) {
                        break;
                    }
                    byteArrayOutputStream.write(bArr2, 0, read);
                }
                bArr2 = byteArrayOutputStream.toByteArray();
            } catch (Throwable e2) {
                th = e2;
                bArr2 = null;
                th2 = th;
                try {
                    cb.a(th2);
                    if (gZIPInputStream != null) {
                        try {
                            gZIPInputStream.close();
                        } catch (Throwable th22) {
                            cb.a(th22);
                            return bArr2;
                        }
                    }
                    if (byteArrayInputStream != null) {
                        byteArrayInputStream.close();
                    }
                    if (byteArrayOutputStream != null) {
                        return bArr2;
                    }
                    byteArrayOutputStream.close();
                    return bArr2;
                } catch (Throwable th3) {
                    e2 = th3;
                    if (gZIPInputStream != null) {
                        try {
                            gZIPInputStream.close();
                        } catch (Throwable th222) {
                            cb.a(th222);
                            throw e2;
                        }
                    }
                    if (byteArrayInputStream != null) {
                        byteArrayInputStream.close();
                    }
                    if (byteArrayOutputStream != null) {
                        byteArrayOutputStream.close();
                    }
                    throw e2;
                }
            }
            try {
                byteArrayOutputStream.flush();
                if (gZIPInputStream != null) {
                    try {
                        gZIPInputStream.close();
                    } catch (Throwable th2222) {
                        cb.a(th2222);
                        return bArr2;
                    }
                }
                if (byteArrayInputStream != null) {
                    byteArrayInputStream.close();
                }
                if (byteArrayOutputStream == null) {
                    return bArr2;
                }
                byteArrayOutputStream.close();
                return bArr2;
            } catch (IOException e3) {
                th2222 = e3;
                cb.a(th2222);
                if (gZIPInputStream != null) {
                    gZIPInputStream.close();
                }
                if (byteArrayInputStream != null) {
                    byteArrayInputStream.close();
                }
                if (byteArrayOutputStream != null) {
                    return bArr2;
                }
                byteArrayOutputStream.close();
                return bArr2;
            }
        } catch (Throwable e22) {
            gZIPInputStream = null;
            th = e22;
            bArr2 = null;
            th2222 = th;
            cb.a(th2222);
            if (gZIPInputStream != null) {
                gZIPInputStream.close();
            }
            if (byteArrayInputStream != null) {
                byteArrayInputStream.close();
            }
            if (byteArrayOutputStream != null) {
                return bArr2;
            }
            byteArrayOutputStream.close();
            return bArr2;
        } catch (Throwable th4) {
            e22 = th4;
            gZIPInputStream = null;
            if (gZIPInputStream != null) {
                gZIPInputStream.close();
            }
            if (byteArrayInputStream != null) {
                byteArrayInputStream.close();
            }
            if (byteArrayOutputStream != null) {
                byteArrayOutputStream.close();
            }
            throw e22;
        }
    }
}
