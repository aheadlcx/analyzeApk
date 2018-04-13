package com.sina.weibo.sdk.utils;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.os.Build;
import android.os.Build.VERSION;
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
import com.baidu.mobads.interfaces.utils.IXAdSystemUtils;
import com.sina.weibo.sdk.exception.WeiboException;
import com.sina.weibo.sdk.net.HttpManager;
import com.sina.weibo.sdk.net.WeiboParameters;
import com.tencent.connect.common.Constants;
import com.umeng.analytics.pro.b;
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
import java.util.HashMap;
import java.util.Map;
import javax.crypto.Cipher;
import org.json.JSONException;
import org.json.JSONObject;
import qsbk.app.model.RssArticle.Type;

public class AidTask4Plug {
    public static final int WHAT_LOAD_AID_ERR = 1002;
    public static final int WHAT_LOAD_AID_SUC = 1001;
    private static AidTask4Plug a;
    private Context b;
    private String c;
    private Map<String, AidInfo> d = new HashMap(3);
    private a e;
    private String f;
    private String g;

    public static final class AidInfo {
        private String a;
        private String b;

        public String getAid() {
            return this.a;
        }

        public String getSubCookie() {
            return this.b;
        }

        public static AidInfo parseJson(String str) throws WeiboException {
            AidInfo aidInfo = new AidInfo();
            try {
                JSONObject jSONObject = new JSONObject(str);
                if (jSONObject.has(b.J) || jSONObject.has("error_code")) {
                    LogUtil.d("AidTask", "loadAidFromNet has error !!!");
                    throw new WeiboException("loadAidFromNet has error !!!");
                }
                aidInfo.a = jSONObject.optString("aid", "");
                aidInfo.b = jSONObject.optString(Type.SUB, "");
                return aidInfo;
            } catch (JSONException e) {
                LogUtil.d("AidTask", "loadAidFromNet JSONException Msg : " + e.getMessage());
                throw new WeiboException("loadAidFromNet has error !!!");
            }
        }

        AidInfo a() {
            AidInfo aidInfo = new AidInfo();
            aidInfo.a = this.a;
            aidInfo.b = this.b;
            return aidInfo;
        }
    }

    public interface AidResultCallBack {
        void onAidGenFailed(Exception exception);

        void onAidGenSuccessed(AidInfo aidInfo);
    }

    private static class a extends Handler {
        private WeakReference<AidResultCallBack> a;

        public a(Looper looper) {
            super(looper);
        }

        public void setCallback(AidResultCallBack aidResultCallBack) {
            if (this.a == null) {
                this.a = new WeakReference(aidResultCallBack);
            } else if (((AidResultCallBack) this.a.get()) != aidResultCallBack) {
                this.a = new WeakReference(aidResultCallBack);
            }
        }

        public void handleMessage(Message message) {
            AidResultCallBack aidResultCallBack = (AidResultCallBack) this.a.get();
            switch (message.what) {
                case 1001:
                    if (aidResultCallBack != null) {
                        aidResultCallBack.onAidGenSuccessed(((AidInfo) message.obj).a());
                        return;
                    }
                    return;
                case 1002:
                    if (aidResultCallBack != null) {
                        aidResultCallBack.onAidGenFailed((WeiboException) message.obj);
                        return;
                    }
                    return;
                default:
                    return;
            }
        }
    }

    public synchronized void setAppkey(String str) {
        this.c = str;
    }

    private AidTask4Plug(Context context, String str) {
        this.b = context.getApplicationContext();
        this.e = new a(this.b.getMainLooper());
        new Thread(new d(this, str)).start();
    }

    public static synchronized AidTask4Plug getInstance(Context context, String str) {
        AidTask4Plug aidTask4Plug;
        synchronized (AidTask4Plug.class) {
            if (a == null) {
                a = new AidTask4Plug(context, str);
            }
            aidTask4Plug = a;
        }
        return aidTask4Plug;
    }

    public void aidTaskInit(String str) {
        if (!TextUtils.isEmpty(str)) {
            LogUtil.e("AidTask", "aidTaskInit ");
            b(str);
        }
    }

    private AidInfo a(String str, String str2, String str3) {
        if (TextUtils.isEmpty(str)) {
            return null;
        }
        LogUtil.e("AidTask", "aidTaskInit ");
        return initAidInfo(str, str2, str3);
    }

    public AidInfo initAidInfo(String str, String str2, String str3) {
        if (TextUtils.isEmpty(str)) {
            return null;
        }
        this.c = str;
        this.f = str2;
        this.g = str3;
        AidInfo a = a(str);
        if (a == null) {
            int i = 1;
            do {
                i++;
                try {
                    String a2 = a();
                    a = AidInfo.parseJson(a2);
                    a(a2, str);
                    this.d.put(str, a);
                    return a;
                } catch (WeiboException e) {
                    LogUtil.e("AidTask", "AidTaskInit WeiboException Msg : " + e.getMessage());
                    if (i >= 3) {
                        return a;
                    }
                }
            } while (i >= 3);
            return a;
        }
        this.d.put(str, a);
        return a;
    }

    private void b(String str) {
        initAidInfo(str, null, null);
    }

    public AidInfo getAid4PlugSync(String str, String str2, String str3) {
        if (TextUtils.isEmpty(str)) {
            return null;
        }
        LogUtil.e("AidTask", "getAidSync ");
        if (this.d.get(str) == null) {
            a(str, str2, str3);
        }
        return (AidInfo) this.d.get(str);
    }

    protected synchronized AidInfo a(String str) {
        Throwable th;
        AidInfo aidInfo = null;
        synchronized (this) {
            FileInputStream fileInputStream;
            try {
                fileInputStream = new FileInputStream(a(1, str));
                try {
                    byte[] bArr = new byte[fileInputStream.available()];
                    fileInputStream.read(bArr);
                    aidInfo = AidInfo.parseJson(new String(bArr));
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
                    return aidInfo;
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
                fileInputStream = aidInfo;
                if (fileInputStream != null) {
                    fileInputStream.close();
                }
                return aidInfo;
            } catch (Throwable th3) {
                Throwable th4 = th3;
                fileInputStream = aidInfo;
                th = th4;
                if (fileInputStream != null) {
                    fileInputStream.close();
                }
                throw th;
            }
        }
        return aidInfo;
    }

    public String loadAidFromCache(String str) {
        AidInfo a = a(str);
        if (a != null) {
            return a.getAid() == null ? "" : a.getAid();
        } else {
            return "";
        }
    }

    private File a(int i, String str) {
        return new File(this.b.getFilesDir(), "weibo_sdk_aid" + str + i);
    }

    private String a() throws WeiboException {
        String str = "https://api.weibo.com/oauth2/getaid.json";
        str = this.f == null ? this.b.getPackageName() : this.f;
        String sign = this.g == null ? Utility.getSign(this.b, str) : this.g;
        String a = a(this.b);
        WeiboParameters weiboParameters = new WeiboParameters(this.c);
        weiboParameters.put("appkey", this.c);
        weiboParameters.put("mfp", a);
        weiboParameters.put("packagename", str);
        weiboParameters.put("key_hash", sign);
        try {
            str = HttpManager.openUrl(this.b, "https://api.weibo.com/oauth2/getaid.json", "GET", weiboParameters);
            LogUtil.d("AidTask", "loadAidFromNet response : " + str);
            return str;
        } catch (WeiboException e) {
            LogUtil.d("AidTask", "loadAidFromNet WeiboException Msg : " + e.getMessage());
            throw e;
        }
    }

    private synchronized void a(String str, String str2) {
        Throwable th;
        if (!TextUtils.isEmpty(str)) {
            FileOutputStream fileOutputStream = null;
            FileOutputStream fileOutputStream2;
            try {
                fileOutputStream2 = new FileOutputStream(a(1, str2));
                try {
                    fileOutputStream2.write(str.getBytes());
                    if (fileOutputStream2 != null) {
                        try {
                            fileOutputStream2.close();
                        } catch (IOException e) {
                        }
                    }
                } catch (Exception e2) {
                    if (fileOutputStream2 != null) {
                        try {
                            fileOutputStream2.close();
                        } catch (IOException e3) {
                        }
                    }
                    return;
                } catch (Throwable th2) {
                    Throwable th3 = th2;
                    fileOutputStream = fileOutputStream2;
                    th = th3;
                    if (fileOutputStream != null) {
                        try {
                            fileOutputStream.close();
                        } catch (IOException e4) {
                        }
                    }
                    throw th;
                }
            } catch (Exception e5) {
                fileOutputStream2 = null;
                if (fileOutputStream2 != null) {
                    fileOutputStream2.close();
                }
                return;
            } catch (Throwable th4) {
                th = th4;
                if (fileOutputStream != null) {
                    fileOutputStream.close();
                }
                throw th;
            }
        }
        return;
    }

    private static String a(Context context) {
        String str = "MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQDHHM0Fi2Z6+QYKXqFUX2Cy6AaWq3cPi+GSn9oeAwQbPZR75JB7Netm0HtBVVbtPhzT7UO2p1JhFUKWqrqoYuAjkgMVPmA0sFrQohns5EE44Y86XQopD4ZO+dE5KjUZFE6vrPO3rWW3np2BqlgKpjnYZri6TJApmIpGcQg9/G/3zQIDAQAB";
        String str2 = "";
        try {
            str = new String(b(context).getBytes(), "UTF-8");
        } catch (UnsupportedEncodingException e) {
            str = str2;
        }
        LogUtil.d("AidTask", "genMfpString() utf-8 string : " + str);
        try {
            str = b(str, "MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQDHHM0Fi2Z6+QYKXqFUX2Cy6AaWq3cPi+GSn9oeAwQbPZR75JB7Netm0HtBVVbtPhzT7UO2p1JhFUKWqrqoYuAjkgMVPmA0sFrQohns5EE44Y86XQopD4ZO+dE5KjUZFE6vrPO3rWW3np2BqlgKpjnYZri6TJApmIpGcQg9/G/3zQIDAQAB");
            LogUtil.d("AidTask", "encryptRsa() string : " + str);
            return str;
        } catch (Exception e2) {
            LogUtil.e("AidTask", e2.getMessage());
            return "";
        }
    }

    private static String b(Context context) {
        JSONObject jSONObject = new JSONObject();
        try {
            CharSequence b = b();
            if (!TextUtils.isEmpty(b)) {
                jSONObject.put("1", b);
            }
            b = c(context);
            if (!TextUtils.isEmpty(b)) {
                jSONObject.put("2", b);
            }
            b = d(context);
            if (!TextUtils.isEmpty(b)) {
                jSONObject.put("3", b);
            }
            b = e(context);
            if (!TextUtils.isEmpty(b)) {
                jSONObject.put("4", b);
            }
            b = f(context);
            if (!TextUtils.isEmpty(b)) {
                jSONObject.put("5", b);
            }
            b = g(context);
            if (!TextUtils.isEmpty(b)) {
                jSONObject.put(Constants.VIA_SHARE_TYPE_INFO, b);
            }
            b = c();
            if (!TextUtils.isEmpty(b)) {
                jSONObject.put("7", b);
            }
            b = h(context);
            if (!TextUtils.isEmpty(b)) {
                jSONObject.put(Constants.VIA_REPORT_TYPE_SHARE_TO_QQ, b);
            }
            b = d();
            if (!TextUtils.isEmpty(b)) {
                jSONObject.put(Constants.VIA_REPORT_TYPE_JOININ_GROUP, b);
            }
            b = e();
            if (!TextUtils.isEmpty(b)) {
                jSONObject.put(Constants.VIA_REPORT_TYPE_MAKE_FRIEND, b);
            }
            b = f();
            if (!TextUtils.isEmpty(b)) {
                jSONObject.put(Constants.VIA_REPORT_TYPE_WPA_STATE, b);
            }
            b = i(context);
            if (!TextUtils.isEmpty(b)) {
                jSONObject.put(Constants.VIA_REPORT_TYPE_START_WAP, b);
            }
            b = j(context);
            if (!TextUtils.isEmpty(b)) {
                jSONObject.put(Constants.VIA_REPORT_TYPE_START_GROUP, b);
            }
            b = g();
            if (!TextUtils.isEmpty(b)) {
                jSONObject.put("18", b);
            }
            b = k(context);
            if (!TextUtils.isEmpty(b)) {
                jSONObject.put(Constants.VIA_ACT_TYPE_NINETEEN, b);
            }
            return jSONObject.toString();
        } catch (JSONException e) {
            return "";
        }
    }

    private static String b(String str, String str2) throws Exception {
        Throwable th;
        Cipher instance = Cipher.getInstance("RSA/ECB/PKCS1Padding");
        instance.init(1, c(str2));
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
                    LogUtil.d("AidTask", "encryptRsa offset = " + i + "     len = " + a + "     enBytes len = " + doFinal.length);
                    i += a;
                } catch (Throwable th2) {
                    th = th2;
                }
            }
            byteArrayOutputStream.flush();
            byte[] toByteArray = byteArrayOutputStream.toByteArray();
            LogUtil.d("AidTask", "encryptRsa total enBytes len = " + toByteArray.length);
            toByteArray = Base64.encodebyte(toByteArray);
            LogUtil.d("AidTask", "encryptRsa total base64byte len = " + toByteArray.length);
            String str3 = "01";
            String str4 = "01" + new String(toByteArray, "UTF-8");
            LogUtil.d("AidTask", "encryptRsa total base64string : " + str4);
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

    private static PublicKey c(String str) throws Exception {
        return KeyFactory.getInstance("RSA").generatePublic(new X509EncodedKeySpec(Base64.decode(str.getBytes())));
    }

    private static String b() {
        try {
            return "Android " + VERSION.RELEASE;
        } catch (Exception e) {
            return "";
        }
    }

    private static String c(Context context) {
        try {
            return ((TelephonyManager) context.getSystemService("phone")).getDeviceId();
        } catch (Exception e) {
            return "";
        }
    }

    private static String d(Context context) {
        try {
            return ((TelephonyManager) context.getSystemService("phone")).getDeviceId();
        } catch (Exception e) {
            return "";
        }
    }

    private static String e(Context context) {
        try {
            return ((TelephonyManager) context.getSystemService("phone")).getSubscriberId();
        } catch (Exception e) {
            return "";
        }
    }

    private static String f(Context context) {
        try {
            WifiManager wifiManager = (WifiManager) context.getSystemService("wifi");
            if (wifiManager == null) {
                return "";
            }
            WifiInfo connectionInfo = wifiManager.getConnectionInfo();
            return connectionInfo != null ? connectionInfo.getMacAddress() : "";
        } catch (Exception e) {
            return "";
        }
    }

    private static String g(Context context) {
        try {
            return ((TelephonyManager) context.getSystemService("phone")).getSimSerialNumber();
        } catch (Exception e) {
            return "";
        }
    }

    private static String c() {
        String str = "";
        try {
            Class cls = Class.forName("android.os.SystemProperties");
            return (String) cls.getMethod("get", new Class[]{String.class, String.class}).invoke(cls, new Object[]{"ro.serialno", "unknown"});
        } catch (Exception e) {
            return str;
        }
    }

    private static String h(Context context) {
        try {
            return Secure.getString(context.getContentResolver(), "android_id");
        } catch (Exception e) {
            return "";
        }
    }

    private static String d() {
        try {
            return Build.CPU_ABI;
        } catch (Exception e) {
            return "";
        }
    }

    private static String e() {
        try {
            return Build.MODEL;
        } catch (Exception e) {
            return "";
        }
    }

    private static String f() {
        try {
            StatFs statFs = new StatFs(Environment.getExternalStorageDirectory().getPath());
            return Long.toString(((long) statFs.getBlockCount()) * ((long) statFs.getBlockSize()));
        } catch (Exception e) {
            return "";
        }
    }

    private static String i(Context context) {
        try {
            DisplayMetrics displayMetrics = new DisplayMetrics();
            ((WindowManager) context.getSystemService("window")).getDefaultDisplay().getMetrics(displayMetrics);
            return String.valueOf(displayMetrics.widthPixels) + "*" + String.valueOf(displayMetrics.heightPixels);
        } catch (Exception e) {
            return "";
        }
    }

    private static String j(Context context) {
        try {
            WifiInfo connectionInfo = ((WifiManager) context.getSystemService("wifi")).getConnectionInfo();
            if (connectionInfo != null) {
                return connectionInfo.getSSID();
            }
        } catch (Exception e) {
        }
        return "";
    }

    private static String g() {
        try {
            return Build.BRAND;
        } catch (Exception e) {
            return "";
        }
    }

    private static String k(Context context) {
        String str = IXAdSystemUtils.NT_NONE;
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
                            return IXAdSystemUtils.NT_NONE;
                    }
                } else if (activeNetworkInfo.getType() == 1) {
                    return "wifi";
                }
            }
            return str;
        } catch (Exception e) {
            return str;
        }
    }
}
