package com.sina.weibo.sdk.a;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.os.Build;
import android.os.Build.VERSION;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.os.StatFs;
import android.provider.Settings.Secure;
import android.telephony.TelephonyManager;
import android.text.TextUtils;
import android.util.DisplayMetrics;
import android.view.WindowManager;
import com.alibaba.baichuan.android.jsbridge.AlibcJsResult;
import com.baidu.mobads.interfaces.utils.IXAdSystemUtils;
import com.sina.weibo.sdk.exception.WeiboException;
import com.sina.weibo.sdk.net.HttpManager;
import com.sina.weibo.sdk.net.e;
import com.tencent.connect.common.Constants;
import com.tencent.stat.DeviceInfo;
import com.umeng.analytics.pro.x;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.lang.ref.WeakReference;
import java.security.KeyFactory;
import java.security.PublicKey;
import java.security.spec.X509EncodedKeySpec;
import java.util.ArrayList;
import java.util.concurrent.locks.ReentrantLock;
import javax.crypto.Cipher;
import org.json.JSONException;
import org.json.JSONObject;

public class a {
    private static a a;
    private Context b;
    private String c;
    private a d;
    private volatile ReentrantLock e = new ReentrantLock(true);
    private c f;
    private String g;
    private String h;
    private ArrayList<l> i = new ArrayList();

    public static final class a {
        private String a;
        private String b;

        public String a() {
            return this.a;
        }

        public static a a(String str) throws WeiboException {
            a aVar = new a();
            try {
                JSONObject jSONObject = new JSONObject(str);
                if (jSONObject.has(x.aF) || jSONObject.has("error_code")) {
                    d.a("AidTask", "loadAidFromNet has error !!!");
                    throw new WeiboException("loadAidFromNet has error !!!");
                }
                aVar.a = jSONObject.optString(DeviceInfo.TAG_ANDROID_ID, "");
                aVar.b = jSONObject.optString("sub", "");
                return aVar;
            } catch (JSONException e) {
                d.a("AidTask", "loadAidFromNet JSONException Msg : " + e.getMessage());
                throw new WeiboException("loadAidFromNet has error !!!");
            }
        }

        a b() {
            a aVar = new a();
            aVar.a = this.a;
            aVar.b = this.b;
            return aVar;
        }
    }

    public interface b {
        void a(a aVar);

        void a(Exception exception);
    }

    private static class c extends Handler {
        private WeakReference<b> a;

        public c(Looper looper) {
            super(looper);
        }

        public void handleMessage(Message message) {
            b bVar = (b) this.a.get();
            switch (message.what) {
                case 1001:
                    if (bVar != null) {
                        bVar.a(((a) message.obj).b());
                        return;
                    }
                    return;
                case 1002:
                    if (bVar != null) {
                        bVar.a((WeiboException) message.obj);
                        return;
                    }
                    return;
                default:
                    return;
            }
        }
    }

    private a(Context context) {
        this.b = context.getApplicationContext();
        this.f = new c(this.b.getMainLooper());
        new Thread(new Runnable(this) {
            final /* synthetic */ a a;

            {
                this.a = r1;
            }

            public void run() {
                for (int i = 0; i < 1; i++) {
                    try {
                        this.a.a(i).delete();
                    } catch (Exception e) {
                    }
                }
            }
        }).start();
    }

    public static synchronized a a(Context context) {
        a aVar;
        synchronized (a.class) {
            if (a == null) {
                a = new a(context);
            }
            aVar = a;
        }
        return aVar;
    }

    public void a(String str) {
        if (!TextUtils.isEmpty(str)) {
            d.c("AidTask", "aidTaskInit ");
            b(str);
        }
    }

    public void a(String str, String str2, String str3) {
        if (!TextUtils.isEmpty(str)) {
            this.c = str;
            this.g = str2;
            this.h = str3;
            new Thread(new Runnable(this) {
                final /* synthetic */ a a;

                {
                    this.a = r1;
                }

                public void run() {
                    if (this.a.e.tryLock()) {
                        a a = this.a.a();
                        if (a == null) {
                            int i = 1;
                            do {
                                i++;
                                try {
                                    String b = this.a.c();
                                    a a2 = a.a(b);
                                    this.a.c(b);
                                    this.a.d = a2;
                                    this.a.a(this.a.d);
                                    break;
                                } catch (WeiboException e) {
                                    d.c("AidTask", "AidTaskInit WeiboException Msg : " + e.getMessage());
                                    if (i >= 3) {
                                    }
                                }
                            } while (i >= 3);
                        } else {
                            this.a.d = a;
                        }
                        this.a.e.unlock();
                        return;
                    }
                    d.c("AidTask", "tryLock : false, return");
                }
            }).start();
        }
    }

    private void b(String str) {
        a(str, null, null);
    }

    protected synchronized a a() {
        Throwable th;
        a aVar = null;
        synchronized (this) {
            FileInputStream fileInputStream;
            try {
                fileInputStream = new FileInputStream(a(1));
                try {
                    byte[] bArr = new byte[fileInputStream.available()];
                    fileInputStream.read(bArr);
                    aVar = a.a(new String(bArr));
                    if (fileInputStream != null) {
                        try {
                            fileInputStream.close();
                        } catch (IOException e) {
                        }
                    }
                } catch (Exception e2) {
                    if (fileInputStream != null) {
                        try {
                            fileInputStream.close();
                        } catch (IOException e3) {
                        }
                    }
                    return aVar;
                } catch (Throwable th2) {
                    th = th2;
                    if (fileInputStream != null) {
                        try {
                            fileInputStream.close();
                        } catch (IOException e4) {
                        }
                    }
                    throw th;
                }
            } catch (Exception e5) {
                fileInputStream = aVar;
                if (fileInputStream != null) {
                    fileInputStream.close();
                }
                return aVar;
            } catch (Throwable th3) {
                Throwable th4 = th3;
                fileInputStream = aVar;
                th = th4;
                if (fileInputStream != null) {
                    fileInputStream.close();
                }
                throw th;
            }
        }
        return aVar;
    }

    public String b() {
        a a = a();
        if (a != null) {
            return a.a() == null ? "" : a.a();
        } else {
            return "";
        }
    }

    private File a(int i) {
        return new File(this.b.getFilesDir(), "weibo_sdk_aid" + i);
    }

    private String c() throws WeiboException {
        String str = "https://api.weibo.com/oauth2/getaid.json";
        str = this.g == null ? this.b.getPackageName() : this.g;
        String a = this.h == null ? j.a(this.b, str) : this.h;
        String c = c(this.b);
        e eVar = new e(this.c);
        eVar.a("appkey", this.c);
        eVar.a("mfp", c);
        eVar.a("packagename", str);
        eVar.a("key_hash", a);
        try {
            str = HttpManager.a(this.b, "https://api.weibo.com/oauth2/getaid.json", "GET", eVar);
            d.a("AidTask", "loadAidFromNet response : " + str);
            return str;
        } catch (WeiboException e) {
            d.a("AidTask", "loadAidFromNet WeiboException Msg : " + e.getMessage());
            throw e;
        }
    }

    private synchronized void c(String str) {
        FileOutputStream fileOutputStream;
        Throwable th;
        if (!TextUtils.isEmpty(str)) {
            FileOutputStream fileOutputStream2 = null;
            try {
                fileOutputStream = new FileOutputStream(a(1));
                try {
                    fileOutputStream.write(str.getBytes());
                    if (fileOutputStream != null) {
                        try {
                            fileOutputStream.close();
                        } catch (IOException e) {
                        }
                    }
                } catch (Exception e2) {
                    if (fileOutputStream != null) {
                        try {
                            fileOutputStream.close();
                        } catch (IOException e3) {
                        }
                    }
                    return;
                } catch (Throwable th2) {
                    Throwable th3 = th2;
                    fileOutputStream2 = fileOutputStream;
                    th = th3;
                    if (fileOutputStream2 != null) {
                        try {
                            fileOutputStream2.close();
                        } catch (IOException e4) {
                        }
                    }
                    throw th;
                }
            } catch (Exception e5) {
                fileOutputStream = null;
                if (fileOutputStream != null) {
                    fileOutputStream.close();
                }
                return;
            } catch (Throwable th4) {
                th = th4;
                if (fileOutputStream2 != null) {
                    fileOutputStream2.close();
                }
                throw th;
            }
        }
        return;
    }

    private static String c(Context context) {
        String str = "MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQDHHM0Fi2Z6+QYKXqFUX2Cy6AaWq3cPi+GSn9oeAwQbPZR75JB7Netm0HtBVVbtPhzT7UO2p1JhFUKWqrqoYuAjkgMVPmA0sFrQohns5EE44Y86XQopD4ZO+dE5KjUZFE6vrPO3rWW3np2BqlgKpjnYZri6TJApmIpGcQg9/G/3zQIDAQAB";
        String str2 = "";
        try {
            str = new String(d(context).getBytes(), "UTF-8");
        } catch (UnsupportedEncodingException e) {
            str = str2;
        }
        d.a("AidTask", "genMfpString() utf-8 string : " + str);
        try {
            str = a(str, "MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQDHHM0Fi2Z6+QYKXqFUX2Cy6AaWq3cPi+GSn9oeAwQbPZR75JB7Netm0HtBVVbtPhzT7UO2p1JhFUKWqrqoYuAjkgMVPmA0sFrQohns5EE44Y86XQopD4ZO+dE5KjUZFE6vrPO3rWW3np2BqlgKpjnYZri6TJApmIpGcQg9/G/3zQIDAQAB");
            d.a("AidTask", "encryptRsa() string : " + str);
            return str;
        } catch (Exception e2) {
            d.c("AidTask", e2.getMessage());
            return "";
        }
    }

    private static String d(Context context) {
        JSONObject jSONObject = new JSONObject();
        try {
            CharSequence d = d();
            if (!TextUtils.isEmpty(d)) {
                jSONObject.put("1", d);
            }
            d = b(context);
            if (!TextUtils.isEmpty(d)) {
                jSONObject.put("2", d);
            }
            d = e(context);
            if (!TextUtils.isEmpty(d)) {
                jSONObject.put("3", d);
            }
            d = f(context);
            if (!TextUtils.isEmpty(d)) {
                jSONObject.put("4", d);
            }
            d = g(context);
            if (!TextUtils.isEmpty(d)) {
                jSONObject.put("5", d);
            }
            d = h(context);
            if (!TextUtils.isEmpty(d)) {
                jSONObject.put("6", d);
            }
            d = e();
            if (!TextUtils.isEmpty(d)) {
                jSONObject.put(AlibcJsResult.CLOSED, d);
            }
            d = i(context);
            if (!TextUtils.isEmpty(d)) {
                jSONObject.put(Constants.VIA_REPORT_TYPE_SHARE_TO_QQ, d);
            }
            d = f();
            if (!TextUtils.isEmpty(d)) {
                jSONObject.put(Constants.VIA_REPORT_TYPE_JOININ_GROUP, d);
            }
            d = g();
            if (!TextUtils.isEmpty(d)) {
                jSONObject.put(Constants.VIA_REPORT_TYPE_MAKE_FRIEND, d);
            }
            d = h();
            if (!TextUtils.isEmpty(d)) {
                jSONObject.put(Constants.VIA_REPORT_TYPE_WPA_STATE, d);
            }
            d = j(context);
            if (!TextUtils.isEmpty(d)) {
                jSONObject.put(Constants.VIA_REPORT_TYPE_START_WAP, d);
            }
            d = k(context);
            if (!TextUtils.isEmpty(d)) {
                jSONObject.put(Constants.VIA_REPORT_TYPE_START_GROUP, d);
            }
            d = i();
            if (!TextUtils.isEmpty(d)) {
                jSONObject.put("18", d);
            }
            d = l(context);
            if (!TextUtils.isEmpty(d)) {
                jSONObject.put(Constants.VIA_ACT_TYPE_NINETEEN, d);
            }
            String str = "";
            try {
                d = j.a(context);
            } catch (Exception e) {
                e.printStackTrace();
            }
            if (!TextUtils.isEmpty(d)) {
                jSONObject.put("20", d);
            }
            return jSONObject.toString();
        } catch (JSONException e2) {
            return "";
        }
    }

    private static String a(String str, String str2) throws Exception {
        Throwable th;
        Cipher instance = Cipher.getInstance("RSA/ECB/PKCS1Padding");
        instance.init(1, d(str2));
        byte[] bytes = str.getBytes("UTF-8");
        ByteArrayOutputStream byteArrayOutputStream;
        try {
            byteArrayOutputStream = new ByteArrayOutputStream();
            int i = 0;
            while (true) {
                try {
                    int a = a(bytes, i, 117);
                    if (a == -1) {
                        break;
                    }
                    byte[] doFinal = instance.doFinal(bytes, i, a);
                    byteArrayOutputStream.write(doFinal);
                    d.a("AidTask", "encryptRsa offset = " + i + "     len = " + a + "     enBytes len = " + doFinal.length);
                    i += a;
                } catch (Throwable th2) {
                    th = th2;
                }
            }
            byteArrayOutputStream.flush();
            byte[] toByteArray = byteArrayOutputStream.toByteArray();
            d.a("AidTask", "encryptRsa total enBytes len = " + toByteArray.length);
            toByteArray = b.b(toByteArray);
            d.a("AidTask", "encryptRsa total base64byte len = " + toByteArray.length);
            String str3 = "01";
            String str4 = "01" + new String(toByteArray, "UTF-8");
            d.a("AidTask", "encryptRsa total base64string : " + str4);
            if (byteArrayOutputStream != null) {
                try {
                    byteArrayOutputStream.close();
                } catch (IOException e) {
                }
            }
            return str4;
        } catch (Throwable th3) {
            th = th3;
            byteArrayOutputStream = null;
            if (byteArrayOutputStream != null) {
                try {
                    byteArrayOutputStream.close();
                } catch (IOException e2) {
                }
            }
            throw th;
        }
    }

    private static int a(byte[] bArr, int i, int i2) {
        if (i >= bArr.length) {
            return -1;
        }
        return Math.min(bArr.length - i, i2);
    }

    private static PublicKey d(String str) throws Exception {
        return KeyFactory.getInstance("RSA").generatePublic(new X509EncodedKeySpec(b.a(str.getBytes())));
    }

    private static String d() {
        try {
            return "Android " + VERSION.RELEASE;
        } catch (Exception e) {
            return "";
        }
    }

    public static String b(Context context) {
        try {
            return ((TelephonyManager) context.getSystemService("phone")).getDeviceId();
        } catch (Exception e) {
            return "";
        }
    }

    private static String e(Context context) {
        try {
            return ((TelephonyManager) context.getSystemService("phone")).getDeviceId();
        } catch (Exception e) {
            return "";
        }
    }

    private static String f(Context context) {
        try {
            return ((TelephonyManager) context.getSystemService("phone")).getSubscriberId();
        } catch (Exception e) {
            return "";
        }
    }

    private static String g(Context context) {
        try {
            WifiManager wifiManager = (WifiManager) context.getSystemService(IXAdSystemUtils.NT_WIFI);
            if (wifiManager == null) {
                return "";
            }
            WifiInfo connectionInfo = wifiManager.getConnectionInfo();
            return connectionInfo != null ? connectionInfo.getMacAddress() : "";
        } catch (Exception e) {
            return "";
        }
    }

    private static String h(Context context) {
        try {
            return ((TelephonyManager) context.getSystemService("phone")).getSimSerialNumber();
        } catch (Exception e) {
            return "";
        }
    }

    private static String e() {
        String str = "";
        try {
            Class cls = Class.forName("android.os.SystemProperties");
            return (String) cls.getMethod("get", new Class[]{String.class, String.class}).invoke(cls, new Object[]{"ro.serialno", "unknown"});
        } catch (Exception e) {
            return str;
        }
    }

    private static String i(Context context) {
        try {
            return Secure.getString(context.getContentResolver(), "android_id");
        } catch (Exception e) {
            return "";
        }
    }

    private static String f() {
        try {
            return Build.CPU_ABI;
        } catch (Exception e) {
            return "";
        }
    }

    private static String g() {
        try {
            return Build.MODEL;
        } catch (Exception e) {
            return "";
        }
    }

    private static String h() {
        try {
            StatFs statFs = new StatFs(Environment.getExternalStorageDirectory().getPath());
            return Long.toString(((long) statFs.getBlockCount()) * ((long) statFs.getBlockSize()));
        } catch (Exception e) {
            return "";
        }
    }

    private static String j(Context context) {
        try {
            DisplayMetrics displayMetrics = new DisplayMetrics();
            ((WindowManager) context.getSystemService("window")).getDefaultDisplay().getMetrics(displayMetrics);
            return String.valueOf(displayMetrics.widthPixels) + "*" + String.valueOf(displayMetrics.heightPixels);
        } catch (Exception e) {
            return "";
        }
    }

    private static String k(Context context) {
        try {
            WifiInfo connectionInfo = ((WifiManager) context.getSystemService(IXAdSystemUtils.NT_WIFI)).getConnectionInfo();
            if (connectionInfo != null) {
                return connectionInfo.getSSID();
            }
        } catch (Exception e) {
        }
        return "";
    }

    private static String i() {
        try {
            return Build.BRAND;
        } catch (Exception e) {
            return "";
        }
    }

    private static String l(Context context) {
        String str = "none";
        try {
            NetworkInfo activeNetworkInfo = ((ConnectivityManager) context.getSystemService("connectivity")).getActiveNetworkInfo();
            if (activeNetworkInfo != null) {
                if (activeNetworkInfo.getType() == 0) {
                    switch (activeNetworkInfo.getSubtype()) {
                        case 1:
                        case 2:
                        case 4:
                        case 7:
                        case 11:
                            return "2G";
                        case 3:
                        case 5:
                        case 6:
                        case 8:
                        case 9:
                        case 10:
                        case 12:
                        case 14:
                        case 15:
                            return "3G";
                        case 13:
                            return "4G";
                        default:
                            return "none";
                    }
                } else if (activeNetworkInfo.getType() == 1) {
                    return IXAdSystemUtils.NT_WIFI;
                }
            }
            return str;
        } catch (Exception e) {
            return str;
        }
    }

    private void a(a aVar) {
        Bundle bundle = new Bundle();
        if (aVar != null) {
            bundle.putString("weibo_aid_value", aVar.a());
        }
        try {
            if (this.i != null && this.i.size() > 0) {
                for (int i = 0; i < this.i.size(); i++) {
                    ((l) this.i.get(i)).a(bundle);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
