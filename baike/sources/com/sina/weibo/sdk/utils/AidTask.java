package com.sina.weibo.sdk.utils;

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
import java.util.ArrayList;
import java.util.concurrent.locks.ReentrantLock;
import javax.crypto.Cipher;
import org.json.JSONException;
import org.json.JSONObject;
import qsbk.app.model.RssArticle.Type;

public class AidTask {
    public static final String AID_TAG = "weibo_aid_value";
    public static final int WHAT_LOAD_AID_ERR = 1002;
    public static final int WHAT_LOAD_AID_SUC = 1001;
    private static AidTask a;
    private Context b;
    private String c;
    private AidInfo d;
    private volatile ReentrantLock e = new ReentrantLock(true);
    private a f;
    private String g;
    private String h;
    private ArrayList<WeiboUtilListener> i = new ArrayList();

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

    private AidTask(Context context) {
        this.b = context.getApplicationContext();
        this.f = new a(this.b.getMainLooper());
        new Thread(new a(this)).start();
    }

    public static synchronized AidTask getInstance(Context context) {
        AidTask aidTask;
        synchronized (AidTask.class) {
            if (a == null) {
                a = new AidTask(context);
            }
            aidTask = a;
        }
        return aidTask;
    }

    public void aidTaskInit(String str) {
        if (!TextUtils.isEmpty(str)) {
            LogUtil.e("AidTask", "aidTaskInit ");
            a(str);
        }
    }

    public void initAidInfo(String str, String str2, String str3) {
        if (!TextUtils.isEmpty(str)) {
            this.c = str;
            this.g = str2;
            this.h = str3;
            new Thread(new b(this)).start();
        }
    }

    private void a(String str) {
        initAidInfo(str, null, null);
    }

    public AidInfo getAidSync(String str) throws WeiboException {
        if (TextUtils.isEmpty(str)) {
            return null;
        }
        LogUtil.e("AidTask", "getAidSync ");
        if (this.d == null) {
            aidTaskInit(str);
        }
        return this.d;
    }

    public void getAidAsync(String str, AidResultCallBack aidResultCallBack) {
        if (!TextUtils.isEmpty(str)) {
            if (this.d == null || aidResultCallBack == null) {
                a(str, aidResultCallBack);
            } else {
                aidResultCallBack.onAidGenSuccessed(this.d.a());
            }
        }
    }

    private void a(String str, AidResultCallBack aidResultCallBack) {
        a(str, null, null, aidResultCallBack);
    }

    private void a(String str, String str2, String str3, AidResultCallBack aidResultCallBack) {
        if (!TextUtils.isEmpty(str)) {
            this.c = str;
            this.h = str3;
            this.g = str2;
            new Thread(new c(this, aidResultCallBack)).start();
        }
    }

    protected synchronized AidInfo a() {
        FileInputStream fileInputStream;
        Throwable th;
        AidInfo aidInfo = null;
        synchronized (this) {
            try {
                fileInputStream = new FileInputStream(a(1));
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

    public String loadAidFromCache() {
        AidInfo a = a();
        if (a != null) {
            return a.getAid() == null ? "" : a.getAid();
        } else {
            return "";
        }
    }

    private File a(int i) {
        return new File(this.b.getFilesDir(), "weibo_sdk_aid" + i);
    }

    private String b() throws WeiboException {
        String str = "https://api.weibo.com/oauth2/getaid.json";
        str = this.g == null ? this.b.getPackageName() : this.g;
        String sign = this.h == null ? Utility.getSign(this.b, str) : this.h;
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

    private synchronized void b(String str) {
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
            str = a(str, "MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQDHHM0Fi2Z6+QYKXqFUX2Cy6AaWq3cPi+GSn9oeAwQbPZR75JB7Netm0HtBVVbtPhzT7UO2p1JhFUKWqrqoYuAjkgMVPmA0sFrQohns5EE44Y86XQopD4ZO+dE5KjUZFE6vrPO3rWW3np2BqlgKpjnYZri6TJApmIpGcQg9/G/3zQIDAQAB");
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
            CharSequence c = c();
            if (!TextUtils.isEmpty(c)) {
                jSONObject.put("1", c);
            }
            c = getImei(context);
            if (!TextUtils.isEmpty(c)) {
                jSONObject.put("2", c);
            }
            c = c(context);
            if (!TextUtils.isEmpty(c)) {
                jSONObject.put("3", c);
            }
            c = d(context);
            if (!TextUtils.isEmpty(c)) {
                jSONObject.put("4", c);
            }
            c = e(context);
            if (!TextUtils.isEmpty(c)) {
                jSONObject.put("5", c);
            }
            c = f(context);
            if (!TextUtils.isEmpty(c)) {
                jSONObject.put(Constants.VIA_SHARE_TYPE_INFO, c);
            }
            c = d();
            if (!TextUtils.isEmpty(c)) {
                jSONObject.put("7", c);
            }
            c = g(context);
            if (!TextUtils.isEmpty(c)) {
                jSONObject.put(Constants.VIA_REPORT_TYPE_SHARE_TO_QQ, c);
            }
            c = e();
            if (!TextUtils.isEmpty(c)) {
                jSONObject.put(Constants.VIA_REPORT_TYPE_JOININ_GROUP, c);
            }
            c = f();
            if (!TextUtils.isEmpty(c)) {
                jSONObject.put(Constants.VIA_REPORT_TYPE_MAKE_FRIEND, c);
            }
            c = g();
            if (!TextUtils.isEmpty(c)) {
                jSONObject.put(Constants.VIA_REPORT_TYPE_WPA_STATE, c);
            }
            c = h(context);
            if (!TextUtils.isEmpty(c)) {
                jSONObject.put(Constants.VIA_REPORT_TYPE_START_WAP, c);
            }
            c = i(context);
            if (!TextUtils.isEmpty(c)) {
                jSONObject.put(Constants.VIA_REPORT_TYPE_START_GROUP, c);
            }
            c = h();
            if (!TextUtils.isEmpty(c)) {
                jSONObject.put("18", c);
            }
            c = j(context);
            if (!TextUtils.isEmpty(c)) {
                jSONObject.put(Constants.VIA_ACT_TYPE_NINETEEN, c);
            }
            String str = "";
            try {
                c = Utility.generateUAAid(context);
            } catch (Exception e) {
                e.printStackTrace();
            }
            if (!TextUtils.isEmpty(c)) {
                jSONObject.put("20", c);
            }
            return jSONObject.toString();
        } catch (JSONException e2) {
            return "";
        }
    }

    private static String a(String str, String str2) throws Exception {
        ByteArrayOutputStream byteArrayOutputStream;
        Throwable th;
        Cipher instance = Cipher.getInstance("RSA/ECB/PKCS1Padding");
        instance.init(1, c(str2));
        byte[] bytes = str.getBytes("UTF-8");
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

    private static String c() {
        try {
            return "Android " + VERSION.RELEASE;
        } catch (Exception e) {
            return "";
        }
    }

    public static String getImei(Context context) {
        try {
            return ((TelephonyManager) context.getSystemService("phone")).getDeviceId();
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
            return ((TelephonyManager) context.getSystemService("phone")).getSubscriberId();
        } catch (Exception e) {
            return "";
        }
    }

    private static String e(Context context) {
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

    private static String f(Context context) {
        try {
            return ((TelephonyManager) context.getSystemService("phone")).getSimSerialNumber();
        } catch (Exception e) {
            return "";
        }
    }

    private static String d() {
        String str = "";
        try {
            Class cls = Class.forName("android.os.SystemProperties");
            return (String) cls.getMethod("get", new Class[]{String.class, String.class}).invoke(cls, new Object[]{"ro.serialno", "unknown"});
        } catch (Exception e) {
            return str;
        }
    }

    private static String g(Context context) {
        try {
            return Secure.getString(context.getContentResolver(), "android_id");
        } catch (Exception e) {
            return "";
        }
    }

    private static String e() {
        try {
            return Build.CPU_ABI;
        } catch (Exception e) {
            return "";
        }
    }

    private static String f() {
        try {
            return Build.MODEL;
        } catch (Exception e) {
            return "";
        }
    }

    private static String g() {
        try {
            StatFs statFs = new StatFs(Environment.getExternalStorageDirectory().getPath());
            return Long.toString(((long) statFs.getBlockCount()) * ((long) statFs.getBlockSize()));
        } catch (Exception e) {
            return "";
        }
    }

    private static String h(Context context) {
        try {
            DisplayMetrics displayMetrics = new DisplayMetrics();
            ((WindowManager) context.getSystemService("window")).getDefaultDisplay().getMetrics(displayMetrics);
            return String.valueOf(displayMetrics.widthPixels) + "*" + String.valueOf(displayMetrics.heightPixels);
        } catch (Exception e) {
            return "";
        }
    }

    private static String i(Context context) {
        try {
            WifiInfo connectionInfo = ((WifiManager) context.getSystemService("wifi")).getConnectionInfo();
            if (connectionInfo != null) {
                return connectionInfo.getSSID();
            }
        } catch (Exception e) {
        }
        return "";
    }

    private static String h() {
        try {
            return Build.BRAND;
        } catch (Exception e) {
            return "";
        }
    }

    private static String j(Context context) {
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

    public void addListener(WeiboUtilListener weiboUtilListener) {
        if (this.i == null) {
            this.i = new ArrayList();
        }
        this.i.add(weiboUtilListener);
    }

    private void a(AidInfo aidInfo) {
        Bundle bundle = new Bundle();
        if (aidInfo != null) {
            bundle.putString(AID_TAG, aidInfo.getAid());
        }
        try {
            if (this.i != null && this.i.size() > 0) {
                for (int i = 0; i < this.i.size(); i++) {
                    ((WeiboUtilListener) this.i.get(i)).onComplete(bundle);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void removeListener(WeiboUtilListener weiboUtilListener) {
        if (this.i != null && this.i.size() > 0) {
            this.i.remove(weiboUtilListener);
        }
    }
}
