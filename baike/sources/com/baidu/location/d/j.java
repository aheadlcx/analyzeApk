package com.baidu.location.d;

import android.content.Context;
import android.location.Location;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Build.VERSION;
import android.os.Environment;
import android.os.Process;
import android.provider.Settings.Secure;
import android.provider.Settings.System;
import com.baidu.location.BDLocation;
import com.baidu.location.b.a;
import com.baidu.location.b.b;
import com.baidu.location.b.d;
import com.baidu.location.b.g;
import com.baidu.location.f;
import java.io.File;
import java.io.FileInputStream;
import java.lang.reflect.Method;
import java.math.BigInteger;
import java.net.Inet4Address;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.security.KeyFactory;
import java.security.MessageDigest;
import java.security.PublicKey;
import java.security.Signature;
import java.security.spec.X509EncodedKeySpec;
import java.util.Calendar;
import java.util.Enumeration;
import java.util.Locale;
import qsbk.app.utils.ListViewHelper;
import qsbk.app.widget.RefreshTipView;

public class j {
    public static int A = 3;
    public static int B = 10;
    public static int C = 2;
    public static int D = 7;
    public static int E = 20;
    public static int F = 70;
    public static int G = 120;
    public static float H = 2.0f;
    public static float I = 10.0f;
    public static float J = 50.0f;
    public static float K = 200.0f;
    public static int L = 16;
    public static float M = 0.9f;
    public static int N = 10000;
    public static float O = 0.5f;
    public static float P = 0.0f;
    public static float Q = 0.1f;
    public static int R = 30;
    public static int S = 100;
    public static int T = 0;
    public static int U = 0;
    public static int V = 0;
    public static int W = 420000;
    public static boolean X = true;
    public static boolean Y = true;
    public static int Z = 20;
    public static boolean a = false;
    public static int aa = 300;
    public static int ab = 1000;
    public static long ac = 900000;
    public static long ad = 420000;
    public static long ae = RefreshTipView.SECOND_REFRESH_INTERVAL;
    public static long af = 0;
    public static long ag = 15;
    public static long ah = ListViewHelper.DEFAULT_TIPS_TO_REFRESH_INTERVAL;
    public static int ai = 1000;
    public static int aj = 0;
    public static int ak = 30000;
    public static int al = 30000;
    public static float am = 10.0f;
    public static float an = 6.0f;
    public static float ao = 10.0f;
    public static int ap = 60;
    public static int aq = 70;
    public static int ar = 6;
    private static String as = "http://loc.map.baidu.com/sdk.php";
    private static String at = "http://loc.map.baidu.com/user_err.php";
    private static String au = "http://loc.map.baidu.com/oqur.php";
    private static String av = "http://loc.map.baidu.com/tcu.php";
    private static String aw = "http://loc.map.baidu.com/rtbu.php";
    private static String ax = "http://loc.map.baidu.com/iofd.php";
    private static String ay = "http://loc.map.baidu.com/wloc";
    public static boolean b = false;
    public static boolean c = false;
    public static int d = 0;
    public static String e = "http://loc.map.baidu.com/sdk_ep.php";
    public static String f = "no";
    public static boolean g = false;
    public static boolean h = false;
    public static boolean i = false;
    public static boolean j = false;
    public static boolean k = false;
    public static String l = "gcj02";
    public static boolean m = true;
    public static int n = 3;
    public static double o = 0.0d;
    public static double p = 0.0d;
    public static double q = 0.0d;
    public static double r = 0.0d;
    public static int s = 0;
    public static byte[] t = null;
    public static boolean u = false;
    public static int v = 0;
    public static float w = 1.1f;
    public static float x = 2.2f;
    public static float y = 2.3f;
    public static float z = 3.8f;

    public static int a(Context context) {
        try {
            return System.getInt(context.getContentResolver(), "airplane_mode_on", 0);
        } catch (Exception e) {
            return 2;
        }
    }

    public static int a(String str, String str2, String str3) {
        int i = Integer.MIN_VALUE;
        if (!(str == null || str.equals(""))) {
            int indexOf = str.indexOf(str2);
            if (indexOf != -1) {
                indexOf += str2.length();
                int indexOf2 = str.indexOf(str3, indexOf);
                if (indexOf2 != -1) {
                    String substring = str.substring(indexOf, indexOf2);
                    if (!(substring == null || substring.equals(""))) {
                        try {
                            i = Integer.parseInt(substring);
                        } catch (NumberFormatException e) {
                        }
                    }
                }
            }
        }
        return i;
    }

    public static Object a(Context context, String str) {
        Object obj = null;
        if (context != null) {
            try {
                obj = context.getApplicationContext().getSystemService(str);
            } catch (Throwable th) {
            }
        }
        return obj;
    }

    public static Object a(Object obj, String str, Object... objArr) throws Exception {
        Class cls = obj.getClass();
        Class[] clsArr = new Class[objArr.length];
        int length = objArr.length;
        for (int i = 0; i < length; i++) {
            clsArr[i] = objArr[i].getClass();
            if (clsArr[i] == Integer.class) {
                clsArr[i] = Integer.TYPE;
            }
        }
        Method declaredMethod = cls.getDeclaredMethod(str, clsArr);
        if (!declaredMethod.isAccessible()) {
            declaredMethod.setAccessible(true);
        }
        return declaredMethod.invoke(obj, objArr);
    }

    public static String a() {
        Calendar instance = Calendar.getInstance();
        int i = instance.get(5);
        int i2 = instance.get(1);
        int i3 = instance.get(2) + 1;
        int i4 = instance.get(11);
        int i5 = instance.get(12);
        int i6 = instance.get(13);
        return String.format(Locale.CHINA, "%d-%02d-%02d %02d:%02d:%02d", new Object[]{Integer.valueOf(i2), Integer.valueOf(i3), Integer.valueOf(i), Integer.valueOf(i4), Integer.valueOf(i5), Integer.valueOf(i6)});
    }

    public static String a(a aVar, g gVar, Location location, String str, int i) {
        String b;
        StringBuffer stringBuffer = new StringBuffer(1024);
        if (aVar != null) {
            b = b.a().b(aVar);
            if (b != null) {
                stringBuffer.append(b);
            }
        }
        if (gVar != null) {
            b = i == 0 ? gVar.b() : gVar.c();
            if (b != null) {
                stringBuffer.append(b);
            }
        }
        if (location != null) {
            b = (d == 0 || i == 0) ? d.b(location) : d.c(location);
            if (b != null) {
                stringBuffer.append(b);
            }
        }
        boolean z = false;
        if (i == 0) {
            z = true;
        }
        b = b.a().a(z);
        if (b != null) {
            stringBuffer.append(b);
        }
        if (str != null) {
            stringBuffer.append(str);
        }
        if (i == 0) {
            if (aVar != null) {
                b = b.a().a(aVar);
                if (b != null && b.length() + stringBuffer.length() < 750) {
                    stringBuffer.append(b);
                }
            }
            b = stringBuffer.toString();
            if (location != null || gVar == null) {
                n = 3;
            } else {
                try {
                    float speed = location.getSpeed();
                    int i2 = d;
                    int d = gVar.d();
                    int a = gVar.a();
                    boolean e = gVar.e();
                    if (speed < an && ((i2 == 1 || i2 == 0) && (d < ap || e))) {
                        n = 1;
                    } else if (speed >= ao || (!(i2 == 1 || i2 == 0 || i2 == 3) || (d >= aq && a <= ar))) {
                        n = 3;
                    } else {
                        n = 2;
                    }
                } catch (Exception e2) {
                    n = 3;
                }
            }
            return b;
        }
        if (aVar != null) {
            b = b.a().a(aVar);
            stringBuffer.append(b);
        }
        b = stringBuffer.toString();
        if (location != null) {
        }
        n = 3;
        return b;
    }

    public static String a(File file, String str) {
        if (!file.isFile()) {
            return null;
        }
        byte[] bArr = new byte[1024];
        try {
            MessageDigest instance = MessageDigest.getInstance(str);
            FileInputStream fileInputStream = new FileInputStream(file);
            while (true) {
                int read = fileInputStream.read(bArr, 0, 1024);
                if (read != -1) {
                    instance.update(bArr, 0, read);
                } else {
                    fileInputStream.close();
                    return new BigInteger(1, instance.digest()).toString(16);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public static boolean a(BDLocation bDLocation) {
        int locType = bDLocation.getLocType();
        return locType > 100 && locType < 200;
    }

    public static int b(Context context) {
        int i = -1;
        if (VERSION.SDK_INT < 23) {
            return -2;
        }
        try {
            return Secure.getInt(context.getContentResolver(), "location_mode", -1);
        } catch (Exception e) {
            return i;
        }
    }

    private static int b(Context context, String str) {
        return (context.checkPermission(str, Process.myPid(), Process.myUid()) == 0 ? 1 : 0) == 0 ? 0 : 1;
    }

    public static int b(Object obj, String str, Object... objArr) throws Exception {
        Class cls = obj.getClass();
        Class[] clsArr = new Class[objArr.length];
        int length = objArr.length;
        for (int i = 0; i < length; i++) {
            clsArr[i] = objArr[i].getClass();
            if (clsArr[i] == Integer.class) {
                clsArr[i] = Integer.TYPE;
            }
        }
        Method declaredMethod = cls.getDeclaredMethod(str, clsArr);
        if (!declaredMethod.isAccessible()) {
            declaredMethod.setAccessible(true);
        }
        return ((Integer) declaredMethod.invoke(obj, objArr)).intValue();
    }

    public static String b() {
        try {
            Enumeration networkInterfaces = NetworkInterface.getNetworkInterfaces();
            while (networkInterfaces.hasMoreElements()) {
                Enumeration inetAddresses = ((NetworkInterface) networkInterfaces.nextElement()).getInetAddresses();
                while (inetAddresses.hasMoreElements()) {
                    InetAddress inetAddress = (InetAddress) inetAddresses.nextElement();
                    if (!inetAddress.isLoopbackAddress() && (inetAddress instanceof Inet4Address)) {
                        byte[] address = inetAddress.getAddress();
                        int i = 0;
                        String str = "";
                        while (i < address.length) {
                            String toHexString = Integer.toHexString(address[i] & 255);
                            if (toHexString.length() == 1) {
                                toHexString = '0' + toHexString;
                            }
                            i++;
                            str = str + toHexString;
                        }
                        return str;
                    }
                }
            }
        } catch (Exception e) {
        }
        return null;
    }

    public static boolean b(String str, String str2, String str3) {
        try {
            PublicKey generatePublic = KeyFactory.getInstance("RSA").generatePublic(new X509EncodedKeySpec(com.baidu.a.a.a.a.b.a(str3.getBytes())));
            Signature instance = Signature.getInstance("SHA1WithRSA");
            instance.initVerify(generatePublic);
            instance.update(str.getBytes());
            return instance.verify(com.baidu.a.a.a.a.b.a(str2.getBytes()));
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public static String c() {
        return as;
    }

    public static String c(Context context) {
        int b = b(context, "android.permission.ACCESS_COARSE_LOCATION");
        int b2 = b(context, "android.permission.ACCESS_FINE_LOCATION");
        return "&per=" + b + "|" + b2 + "|" + b(context, "android.permission.READ_PHONE_STATE");
    }

    public static String d() {
        return av;
    }

    public static String d(Context context) {
        int type;
        int i = -1;
        if (context != null) {
            try {
                NetworkInfo activeNetworkInfo = ((ConnectivityManager) context.getSystemService("connectivity")).getActiveNetworkInfo();
                if (activeNetworkInfo != null && activeNetworkInfo.isAvailable()) {
                    type = activeNetworkInfo.getType();
                    i = type;
                    return "&netc=" + i;
                }
            } catch (Exception e) {
            }
        }
        type = -1;
        i = type;
        return "&netc=" + i;
    }

    public static String e() {
        try {
            if (!Environment.getExternalStorageState().equals("mounted")) {
                return null;
            }
            String path = Environment.getExternalStorageDirectory().getPath();
            File file = new File(path + "/baidu/tempdata");
            if (file.exists()) {
                return path;
            }
            file.mkdirs();
            return path;
        } catch (Exception e) {
            return null;
        }
    }

    public static String f() {
        String e = e();
        return e == null ? null : e + "/baidu/tempdata";
    }

    public static String g() {
        try {
            File file = new File(f.getServiceContext().getFilesDir() + File.separator + "lldt");
            if (!file.exists()) {
                file.mkdirs();
            }
            return file.getAbsolutePath();
        } catch (Exception e) {
            return null;
        }
    }
}
