package com.tencent.weibo.sdk.android.component.sso;

import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.net.Uri;
import com.tencent.weibo.sdk.android.component.sso.tools.Base64;
import com.tencent.weibo.sdk.android.component.sso.tools.Cryptor;
import com.tencent.weibo.sdk.android.component.sso.tools.MD5Tools;
import java.io.ByteArrayInputStream;
import java.security.cert.CertificateException;
import java.security.cert.CertificateFactory;
import java.security.cert.X509Certificate;
import java.util.Random;
import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;

public final class AuthHelper {
    static final String ENCRYPT_KEY = "&-*)Wb5_U,[^!9'+";
    static final int ERROR_WEIBO_INSTALL_NEEDED = -2;
    static final int ERROR_WEIBO_UPGRADE_NEEDED = -1;
    static final byte SDK_VERSION = (byte) 1;
    static final int SUPPORT_WEIBO_MIN_VERSION = 44;
    static final String WEIBO_AUTH_URL = "TencentAuth://weibo";
    static final String WEIBO_PACKAGE = "com.tencent.WBlog";
    static final int WEIBO_VALIDATE_OK = 0;
    protected static String appSecret;
    protected static long appid;
    protected static OnAuthListener listener;
    private static AuthReceiver mReceiver = new AuthReceiver();

    public static final void register(Context context, long j, String str, OnAuthListener onAuthListener) {
        appid = j;
        appSecret = str;
        listener = onAuthListener;
        IntentFilter intentFilter = new IntentFilter("com.tencent.sso.AUTH");
        intentFilter.addCategory("android.intent.category.DEFAULT");
        context.registerReceiver(mReceiver, intentFilter);
    }

    public static final void unregister(Context context) {
        context.unregisterReceiver(mReceiver);
    }

    public static final boolean auth(Context context, String str) {
        int validateWeiboApp = validateWeiboApp(context);
        if (validateWeiboApp == 0) {
            long currentTimeMillis = System.currentTimeMillis() / 1000;
            long abs = (long) Math.abs(new Random().nextInt());
            String apkSignature = getApkSignature(context);
            byte[] generateSignature = generateSignature(appid, appSecret, currentTimeMillis, abs);
            if (generateSignature != null) {
                generateSignature = encypt(generateSignature);
                String packageName = context.getPackageName();
                PackageManager packageManager = context.getPackageManager();
                String str2 = "";
                try {
                    str2 = packageManager.getApplicationLabel(packageManager.getApplicationInfo(packageName, 0)).toString();
                } catch (NameNotFoundException e) {
                }
                Intent intent = new Intent("android.intent.action.VIEW", Uri.parse(WEIBO_AUTH_URL));
                intent.putExtra("com.tencent.sso.APP_ID", appid);
                intent.putExtra("com.tencent.sso.TIMESTAMP", currentTimeMillis);
                intent.putExtra("com.tencent.sso.NONCE", abs);
                intent.putExtra("com.tencent.sso.SDK_VERSION", (byte) 1);
                intent.putExtra("com.tencent.sso.PACKAGE_NAME", packageName);
                intent.putExtra("com.tencent.sso.ICON_MD5", apkSignature);
                intent.putExtra("com.tencent.sso.APP_NAME", str2);
                intent.putExtra("com.tencent.sso.SIGNATURE", generateSignature);
                intent.putExtra("com.tencent.sso.RESERVER", str);
                context.startActivity(intent);
                return true;
            } else if (listener == null) {
                return false;
            } else {
                listener.onAuthFail(-1, "");
                return false;
            }
        } else if (validateWeiboApp == -1) {
            if (listener == null) {
                return false;
            }
            listener.onWeiboVersionMisMatch();
            return false;
        } else if (validateWeiboApp != -2 || listener == null) {
            return false;
        } else {
            listener.onWeiBoNotInstalled();
            return false;
        }
    }

    private static int validateWeiboApp(Context context) {
        PackageManager packageManager = context.getPackageManager();
        try {
            PackageInfo packageInfo = packageManager.getPackageInfo(WEIBO_PACKAGE, 16);
            if (packageInfo == null || packageInfo.versionCode < 44 || packageManager.queryIntentActivities(new Intent("android.intent.action.VIEW", Uri.parse(WEIBO_AUTH_URL)), 65536).size() <= 0) {
                return -1;
            }
            return 0;
        } catch (NameNotFoundException e) {
            return -2;
        }
    }

    private static byte[] generateSignature(long j, String str, long j2, long j3) {
        byte[] bArr = null;
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append(j);
        stringBuffer.append(j2);
        stringBuffer.append(j3);
        stringBuffer.append(1);
        try {
            Mac instance = Mac.getInstance("HmacSHA1");
            instance.init(new SecretKeySpec(str.getBytes("UTF-8"), instance.getAlgorithm()));
            bArr = instance.doFinal(stringBuffer.toString().getBytes("UTF-8"));
        } catch (Exception e) {
        }
        return Base64.encode(bArr).getBytes();
    }

    private static byte[] encypt(byte[] bArr) {
        return new Cryptor().encrypt(bArr, ENCRYPT_KEY.getBytes());
    }

    private static String getApkSignature(Context context) {
        try {
            X509Certificate x509Certificate = (X509Certificate) CertificateFactory.getInstance("X.509").generateCertificate(new ByteArrayInputStream(context.getPackageManager().getPackageInfo(context.getPackageName(), 64).signatures[0].toByteArray()));
            StringBuffer stringBuffer = new StringBuffer();
            stringBuffer.append(x509Certificate.getPublicKey().toString());
            stringBuffer.append(x509Certificate.getSerialNumber().toString());
            return MD5Tools.toMD5(stringBuffer.toString());
        } catch (NameNotFoundException e) {
            e.printStackTrace();
            return "";
        } catch (CertificateException e2) {
            e2.printStackTrace();
            return "";
        } catch (Exception e3) {
            e3.printStackTrace();
            return "";
        }
    }
}
