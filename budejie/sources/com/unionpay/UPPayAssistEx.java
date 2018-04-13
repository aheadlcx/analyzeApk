package com.unionpay;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.net.Uri;
import android.os.Bundle;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

public class UPPayAssistEx {
    public static final int PLUGIN_NOT_FOUND = -1;
    public static final int PLUGIN_VALID = 0;
    private static String a = "SpId";
    private static String b = "paydata";
    private static String c = "SysProvide";
    private static String d = "UseTestMode";
    private static String e = "SecurityChipType";
    private static String f = "uppayuri";
    private static String g = "resultIntentAction";
    private static String h = "reqOriginalId";
    private static String i = "com.unionpay.uppay";
    private static String j = "com.unionpay.uppay.PayActivity";
    private static String k = "ex_mode";
    private static int l = 10;

    private static int a(Activity activity, String str, String str2) {
        try {
            if (!a(activity)) {
                return -1;
            }
            Bundle bundle = new Bundle();
            bundle.putInt(h, 1);
            bundle.putString(f, str);
            bundle.putString(g, str2);
            Intent intent = new Intent();
            intent.putExtras(bundle);
            intent.setClassName(i, j);
            activity.startActivity(intent);
            return 0;
        } catch (NameNotFoundException e) {
            return -1;
        }
    }

    private static int a(Activity activity, String str, String str2, String str3, String str4) {
        try {
            if (!a(activity)) {
                return -1;
            }
            Bundle bundle = new Bundle();
            a(str3, bundle, str4);
            bundle.putString(a, str);
            bundle.putString(c, str2);
            bundle.putString(b, str3);
            bundle.putString(e, null);
            bundle.putInt(h, 0);
            Intent intent = new Intent();
            intent.putExtras(bundle);
            intent.setClassName(i, j);
            activity.startActivityForResult(intent, l);
            return 0;
        } catch (NameNotFoundException e) {
            return -1;
        }
    }

    private static void a(String str, Bundle bundle, String str2) {
        if (str != null && str.trim().length() > 0) {
            if (str.trim().charAt(0) != '<') {
                bundle.putString(k, str2);
            } else if (str2 == null || !str2.trim().equalsIgnoreCase("00")) {
                bundle.putBoolean(d, true);
            } else {
                bundle.putBoolean(d, false);
            }
        }
    }

    private static boolean a(Activity activity) {
        String str = "com.unionpay.uppay";
        String[] strArr = new String[]{"30820267308201d0a00302010202044", "ecb7d98300d06092a8", "64886f70d01010505003078310b30090603550", "406130238363111300f060355040813085", "368616e676", "861693111300f060355040713085368616e67686169311730", "15060355040a130e4368696e6120556e696f6e50617931173015060355040b130e4", "368696e612055", "6e696f6e5061793111300f06035504031308556e696f6e5061", "79301e170d3131313132323130343634385a170d333631313135313034", "3634385a3078310b300906035504061302383631", "11300f060355040813085368616e67686169311130", "0f060355040713085368616e676861693117", "3015060355040a130e4368696e6120556e696", "f6e50617931173015060355040b130e4368696e6120556e696", "f6e5061793111300f06035504031308556e696f6e50617930819f300d060", "92a864886f70d010101050003818d0030818902818100c42e6236d5054ffccaa", "a430ecd929d562", "b1ff56cef0e21c87260c63ce3ca868bf5974c14", "d9255940da7b6cd07483f4b4243fd1825b2705", "08eb9b5c67474d027fa03ce35109b11604083ab6bb4df2c46240f879f", "8cc1d6ed5e1b2cc00489215aec3fc2eac008e767b0215981cb5e", "e94ddc285669ec06b8a405dd4341eac4ea7030203010001300d06092a864886f70d010105050003818", "1001a3e74c601e3beb1b7ae4f9ab2872a0aaf1dbc2cba89c7528cd", "54aa526e7a37d8ba2311a1d3d2ab79b3fbeaf3ebb9e7da9e7cdd9be1ae5a53595f47", "b1fdf62b0f540fca5458b063af9354925a6c3505a18ff164b6b195f6e517eaee1fb783", "64c2f89fdffa16729c9779f99562bc189d2ce4722ba0faedb11aa22d0d9db228fda"};
        PackageManager packageManager = activity.getPackageManager();
        packageManager.getActivityInfo(activity.getComponentName(), 128);
        packageManager.getApplicationInfo(str, 8192);
        PackageInfo packageInfo = packageManager.getPackageInfo(str, 4160);
        String toCharsString = packageInfo.signatures[0].toCharsString();
        String str2 = "";
        for (int i = 0; i < 27; i++) {
            str2 = str2 + strArr[i];
        }
        return toCharsString != null && toCharsString.equals(str2) && packageInfo.versionCode >= 31;
    }

    public static boolean installUPPayPlugin(Context context) {
        try {
            InputStream open = context.getAssets().open("UPPayPluginEx.apk");
            FileOutputStream openFileOutput = context.openFileOutput("UPPayPluginEx.apk", 1);
            byte[] bArr = new byte[1024];
            while (true) {
                int read = open.read(bArr);
                if (read <= 0) {
                    break;
                }
                openFileOutput.write(bArr, 0, read);
            }
            openFileOutput.close();
            open.close();
            String absolutePath = context.getFilesDir().getAbsolutePath();
            String str = absolutePath + File.separator + "UPPayPluginEx.apk";
            if (absolutePath != null) {
                Intent intent = new Intent("android.intent.action.VIEW");
                intent.setDataAndType(Uri.parse("file:" + str), "application/vnd.android.package-archive");
                context.startActivity(intent);
                return true;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }

    public static int startPay(Activity activity, String str, String str2, String str3, String str4) {
        return a(activity, str, str2, str3, str4);
    }

    public static void startPayByJAR(Activity activity, Class cls, String str, String str2, String str3, String str4) {
        Bundle bundle = new Bundle();
        a(str3, bundle, str4);
        bundle.putString(a, str);
        bundle.putString(c, str2);
        bundle.putString(b, str3);
        bundle.putInt(h, 2);
        Intent intent = new Intent();
        intent.putExtras(bundle);
        intent.setClass(activity, cls);
        activity.startActivityForResult(intent, l);
    }

    public static int startPayFromBrowser(Activity activity, String str, String str2) {
        return a(activity, str, str2);
    }
}
