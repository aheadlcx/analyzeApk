package cn.xiaochuankeji.tieba.push.service;

import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.telephony.TelephonyManager;
import android.util.Pair;
import cn.xiaochuan.base.BaseApplication;
import cn.xiaochuankeji.tieba.network.custom.a.c;
import cn.xiaochuankeji.tieba.network.f;
import java.io.ByteArrayInputStream;
import java.io.Closeable;
import java.io.IOException;
import java.io.InputStream;
import java.net.Socket;
import java.security.GeneralSecurityException;
import java.util.Arrays;
import java.util.concurrent.TimeUnit;
import java.util.zip.GZIPInputStream;
import javax.net.ssl.SSLSocketFactory;
import okhttp3.aa;
import okhttp3.ab;
import okhttp3.w.a;
import okhttp3.y$a;
import okio.Okio;

final class e {
    private static void a(Closeable closeable) {
        if (closeable != null) {
            try {
                closeable.close();
            } catch (Throwable th) {
            }
        }
    }

    static void a(Socket socket) {
        if (socket != null) {
            try {
                a(socket.getInputStream());
            } catch (IOException e) {
            }
            try {
                a(socket.getOutputStream());
            } catch (IOException e2) {
            }
            try {
                socket.close();
            } catch (IOException e3) {
            }
        }
    }

    static String a() {
        ConnectivityManager connectivityManager = (ConnectivityManager) BaseApplication.getAppContext().getSystemService("connectivity");
        if (connectivityManager == null) {
            return "none";
        }
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        if (activeNetworkInfo == null) {
            return "none";
        }
        TelephonyManager telephonyManager = (TelephonyManager) BaseApplication.getAppContext().getSystemService("phone");
        return activeNetworkInfo.getTypeName() + ":" + b(telephonyManager == null ? -1 : telephonyManager.getNetworkType());
    }

    private static String b(int i) {
        switch (i) {
            case 1:
                return "GPRS";
            case 2:
                return "EDGE";
            case 3:
                return "UMTS";
            case 4:
                return "CDMA";
            case 5:
                return "CDMA - EvDo rev. 0";
            case 6:
                return "CDMA - EvDo rev. A";
            case 7:
                return "CDMA - 1xRTT";
            case 8:
                return "HSDPA";
            case 9:
                return "HSUPA";
            case 10:
                return "HSPA";
            case 11:
                return "iDEN";
            case 12:
                return "CDMA - EvDo rev. B";
            case 13:
                return "LTE";
            case 14:
                return "CDMA - eHRPD";
            case 15:
                return "HSPA+";
            case 16:
                return "GSM";
            case 17:
                return "TD_SCDMA";
            case 18:
                return "IWLAN";
            default:
                return "UNKNOWN";
        }
    }

    static String a(String str) throws Exception {
        a aVar = new a();
        aVar.a(new c());
        SSLSocketFactory fVar = new f();
        aVar.a(new cn.xiaochuankeji.tieba.network.custom.a());
        try {
            aVar.a(fVar, f.a());
        } catch (GeneralSecurityException e) {
            e.printStackTrace();
        }
        aVar.a(10, TimeUnit.SECONDS).b(15, TimeUnit.SECONDS).c(15, TimeUnit.SECONDS);
        aa a = aVar.a().a(new y$a().a(str).a().d()).a();
        ab g = a.g();
        try {
            String string = g.string();
            return string;
        } finally {
            if (g != null) {
                g.close();
            }
            if (a != null) {
                a.close();
            }
        }
    }

    static byte[] a(byte[] bArr, byte[] bArr2) {
        Object obj = new byte[(bArr.length + bArr2.length)];
        System.arraycopy(bArr, 0, obj, 0, bArr.length);
        System.arraycopy(bArr2, 0, obj, bArr.length, bArr2.length);
        return obj;
    }

    static byte[] a(byte[] bArr, byte[] bArr2, int i) {
        Object obj = new byte[(bArr.length + i)];
        System.arraycopy(bArr, 0, obj, 0, bArr.length);
        System.arraycopy(bArr2, 0, obj, bArr.length, i);
        return obj;
    }

    static byte[] a(int i) {
        byte[] bArr = new byte[5];
        int i2 = 0;
        do {
            bArr[i2] = (byte) ((i & 127) | 128);
            i2++;
            i >>= 7;
        } while (i != 0);
        int i3 = i2 - 1;
        bArr[i3] = (byte) (bArr[i3] & 127);
        return Arrays.copyOf(bArr, i2);
    }

    static Pair<Long, Integer> a(byte[] bArr) {
        int i = 0;
        long j = 0;
        int i2 = 0;
        while (i2 < 64 && i < bArr.length) {
            int i3 = i + 1;
            byte b = bArr[i];
            j |= ((long) (b & 127)) << i2;
            if ((b & 128) == 0) {
                return Pair.create(Long.valueOf(j), Integer.valueOf(i3));
            }
            i2 += 7;
            i = i3;
        }
        return Pair.create(Long.valueOf(-1), Integer.valueOf(0));
    }

    static byte[] b(byte[] bArr) {
        GZIPInputStream gZIPInputStream;
        IOException e;
        Throwable th;
        Closeable closeable = null;
        if (!(bArr == null || bArr.length == 0)) {
            InputStream byteArrayInputStream = new ByteArrayInputStream(bArr);
            try {
                gZIPInputStream = new GZIPInputStream(byteArrayInputStream);
                try {
                    closeable = Okio.buffer(Okio.source(gZIPInputStream));
                    bArr = closeable.readByteArray();
                    try {
                        byteArrayInputStream.close();
                    } catch (IOException e2) {
                        e2.printStackTrace();
                    }
                    if (gZIPInputStream != null) {
                        try {
                            gZIPInputStream.close();
                        } catch (IOException e22) {
                            e22.printStackTrace();
                        }
                    }
                    okhttp3.internal.c.a(closeable);
                } catch (IOException e3) {
                    e22 = e3;
                    try {
                        e22.printStackTrace();
                        try {
                            byteArrayInputStream.close();
                        } catch (IOException e222) {
                            e222.printStackTrace();
                        }
                        if (gZIPInputStream != null) {
                            try {
                                gZIPInputStream.close();
                            } catch (IOException e2222) {
                                e2222.printStackTrace();
                            }
                        }
                        okhttp3.internal.c.a(closeable);
                        return bArr;
                    } catch (Throwable th2) {
                        th = th2;
                        try {
                            byteArrayInputStream.close();
                        } catch (IOException e4) {
                            e4.printStackTrace();
                        }
                        if (gZIPInputStream != null) {
                            try {
                                gZIPInputStream.close();
                            } catch (IOException e5) {
                                e5.printStackTrace();
                            }
                        }
                        okhttp3.internal.c.a(closeable);
                        throw th;
                    }
                }
            } catch (IOException e6) {
                e2222 = e6;
                gZIPInputStream = null;
                e2222.printStackTrace();
                byteArrayInputStream.close();
                if (gZIPInputStream != null) {
                    gZIPInputStream.close();
                }
                okhttp3.internal.c.a(closeable);
                return bArr;
            } catch (Throwable th3) {
                th = th3;
                gZIPInputStream = null;
                byteArrayInputStream.close();
                if (gZIPInputStream != null) {
                    gZIPInputStream.close();
                }
                okhttp3.internal.c.a(closeable);
                throw th;
            }
        }
        return bArr;
    }
}
