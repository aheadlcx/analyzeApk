package com.tencent.smtt.sdk.a;

import android.content.ActivityNotFoundException;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.content.pm.ResolveInfo;
import android.graphics.Point;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import com.tencent.smtt.sdk.TbsConfig;
import com.tencent.smtt.sdk.WebView;
import java.io.File;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.List;
import java.util.Set;

public class d {

    public static class a {
        public int a = -1;
        public int b = -1;
        public String c = "";
        public String d = "0";
        public String e = null;
    }

    private static class b {
        public String a;
        public String b;

        private b() {
            this.a = "";
            this.b = "";
        }
    }

    public static int a(Context context, String str, HashMap<String, String> hashMap, WebView webView) {
        if (context == null) {
            return 3;
        }
        if (!a(str)) {
            str = "http://" + str;
        }
        try {
            Uri parse = Uri.parse(str);
            if (parse == null) {
                return 2;
            }
            a a = a(context);
            if (a.a == -1) {
                return 4;
            }
            if (a.a == 2 && a.b < 33) {
                return 5;
            }
            Intent intent;
            Intent intent2 = new Intent("android.intent.action.VIEW");
            b a2;
            if (a.a == 2) {
                if (a.b >= 33 && a.b <= 39) {
                    intent2.setClassName(TbsConfig.APP_QB, "com.tencent.mtt.MainActivity");
                    intent = intent2;
                } else if (a.b < 40 || a.b > 45) {
                    if (a.b >= 46) {
                        intent2 = new Intent("com.tencent.QQBrowser.action.VIEW");
                        a2 = a(context, parse);
                        if (!(a2 == null || TextUtils.isEmpty(a2.a))) {
                            intent2.setClassName(a2.b, a2.a);
                        }
                        intent = intent2;
                    }
                    intent = intent2;
                } else {
                    intent2.setClassName(TbsConfig.APP_QB, "com.tencent.mtt.SplashActivity");
                    intent = intent2;
                }
            } else if (a.a == 1) {
                if (a.b == 1) {
                    intent2.setClassName("com.tencent.qbx5", "com.tencent.qbx5.MainActivity");
                    intent = intent2;
                } else {
                    if (a.b == 2) {
                        intent2.setClassName("com.tencent.qbx5", "com.tencent.qbx5.SplashActivity");
                        intent = intent2;
                    }
                    intent = intent2;
                }
            } else if (a.a != 0) {
                intent2 = new Intent("com.tencent.QQBrowser.action.VIEW");
                a2 = a(context, parse);
                if (!(a2 == null || TextUtils.isEmpty(a2.a))) {
                    intent2.setClassName(a2.b, a2.a);
                }
                intent = intent2;
            } else if (a.b < 4 || a.b > 6) {
                if (a.b > 6) {
                    intent2 = new Intent("com.tencent.QQBrowser.action.VIEW");
                    a2 = a(context, parse);
                    if (!(a2 == null || TextUtils.isEmpty(a2.a))) {
                        intent2.setClassName(a2.b, a2.a);
                    }
                    intent = intent2;
                }
                intent = intent2;
            } else {
                intent2.setClassName("com.tencent.qbx", "com.tencent.qbx.SplashActivity");
                intent = intent2;
            }
            intent.setData(parse);
            if (hashMap != null) {
                Set<String> keySet = hashMap.keySet();
                if (keySet != null) {
                    for (String str2 : keySet) {
                        String str3 = (String) hashMap.get(str2);
                        if (!TextUtils.isEmpty(str3)) {
                            intent.putExtra(str2, str3);
                        }
                    }
                }
            }
            try {
                intent.putExtra("loginType", d(context));
                intent.addFlags(268435456);
                if (webView != null) {
                    intent.putExtra("AnchorPoint", new Point(webView.getScrollX(), webView.getScrollY()));
                    intent.putExtra("ContentSize", new Point(webView.getContentWidth(), webView.getContentHeight()));
                }
                context.startActivity(intent);
                return 0;
            } catch (ActivityNotFoundException e) {
                return 4;
            }
        } catch (Exception e2) {
            return 2;
        }
    }

    public static int a(Context context, String str, HashMap<String, String> hashMap, String str2, WebView webView) {
        Object obj;
        String encode;
        Object obj2 = null;
        StringBuilder stringBuilder = new StringBuilder();
        try {
            Object obj3;
            PackageManager packageManager = context.getPackageManager();
            if (packageManager != null) {
                PackageInfo packageInfo = packageManager.getPackageInfo(TbsConfig.APP_QB, 0);
                if (packageInfo != null && packageInfo.versionCode > 601000) {
                    obj3 = 1;
                    obj = obj3;
                    encode = URLEncoder.encode(str, "UTF-8");
                    if (obj != null) {
                        str = encode;
                    }
                    obj2 = obj;
                    stringBuilder.append("mttbrowser://url=").append(str).append(",product=").append("TBS").append(",packagename=").append(context.getPackageName()).append(",from=").append(str2).append(",version=").append(TbsConfig.TBS_SDK_VERSIONNAME).append(obj2 == null ? ",encoded=1" : "");
                    return a(context, stringBuilder.toString(), hashMap, webView);
                }
            }
            obj3 = null;
            obj = obj3;
        } catch (Throwable th) {
            obj = null;
        }
        try {
            encode = URLEncoder.encode(str, "UTF-8");
            if (obj != null) {
                str = encode;
            }
            obj2 = obj;
        } catch (Exception e) {
        }
        if (obj2 == null) {
        }
        stringBuilder.append("mttbrowser://url=").append(str).append(",product=").append("TBS").append(",packagename=").append(context.getPackageName()).append(",from=").append(str2).append(",version=").append(TbsConfig.TBS_SDK_VERSIONNAME).append(obj2 == null ? ",encoded=1" : "");
        return a(context, stringBuilder.toString(), hashMap, webView);
    }

    public static a a(Context context) {
        boolean z = context.getApplicationContext().getSharedPreferences("x5_proxy_setting", 0).getBoolean("qb_install_status", false);
        a aVar = new a();
        if (z) {
            return aVar;
        }
        try {
            PackageManager packageManager = context.getPackageManager();
            PackageInfo packageInfo = null;
            try {
                packageInfo = packageManager.getPackageInfo(TbsConfig.APP_QB, 0);
                aVar.a = 2;
                aVar.e = TbsConfig.APP_QB;
                aVar.c = "ADRQB_";
                if (packageInfo != null && packageInfo.versionCode > 420000) {
                    aVar.b = packageInfo.versionCode;
                    aVar.c += packageInfo.versionName.replaceAll("\\.", "");
                    aVar.d = packageInfo.versionName.replaceAll("\\.", "");
                    return aVar;
                }
            } catch (NameNotFoundException e) {
            }
            try {
                packageInfo = packageManager.getPackageInfo("com.tencent.qbx", 0);
                aVar.a = 0;
                aVar.e = "com.tencent.qbx";
                aVar.c = "ADRQBX_";
            } catch (NameNotFoundException e2) {
                try {
                    packageInfo = packageManager.getPackageInfo("com.tencent.qbx5", 0);
                    aVar.a = 1;
                    aVar.e = "com.tencent.qbx5";
                    aVar.c = "ADRQBX5_";
                } catch (NameNotFoundException e3) {
                    try {
                        packageInfo = packageManager.getPackageInfo(TbsConfig.APP_QB, 0);
                        aVar.e = TbsConfig.APP_QB;
                        aVar.a = 2;
                        aVar.c = "ADRQB_";
                    } catch (NameNotFoundException e4) {
                        try {
                            packageInfo = packageManager.getPackageInfo("com.tencent.mtt.x86", 0);
                            aVar.e = "com.tencent.mtt.x86";
                            aVar.a = 2;
                            aVar.c = "ADRQB_";
                        } catch (Exception e5) {
                            try {
                                b a = a(context, Uri.parse("http://mdc.html5.qq.com/mh?channel_id=50079&u="));
                                if (!(a == null || TextUtils.isEmpty(a.b))) {
                                    packageInfo = packageManager.getPackageInfo(a.b, 0);
                                    aVar.e = a.b;
                                    aVar.a = 2;
                                    aVar.c = "ADRQB_";
                                }
                            } catch (Exception e6) {
                            }
                        }
                    }
                }
            }
            if (packageInfo != null) {
                aVar.b = packageInfo.versionCode;
                aVar.c += packageInfo.versionName.replaceAll("\\.", "");
                aVar.d = packageInfo.versionName.replaceAll("\\.", "");
            }
        } catch (Exception e7) {
        }
        return aVar;
    }

    private static b a(Context context, Uri uri) {
        Intent intent = new Intent("com.tencent.QQBrowser.action.VIEW");
        intent.setData(uri);
        List<ResolveInfo> queryIntentActivities = context.getPackageManager().queryIntentActivities(intent, 0);
        if (queryIntentActivities.size() <= 0) {
            return null;
        }
        b bVar = new b();
        for (ResolveInfo resolveInfo : queryIntentActivities) {
            String str = resolveInfo.activityInfo.packageName;
            if (str.contains(TbsConfig.APP_QB)) {
                bVar.a = resolveInfo.activityInfo.name;
                bVar.b = resolveInfo.activityInfo.packageName;
                return bVar;
            } else if (str.contains("com.tencent.qbx")) {
                bVar.a = resolveInfo.activityInfo.name;
                bVar.b = resolveInfo.activityInfo.packageName;
            }
        }
        return bVar;
    }

    public static boolean a(Context context, long j, long j2) {
        a a = a(context);
        boolean z = false;
        try {
            if (Long.valueOf(a.d).longValue() >= j) {
                z = true;
            }
        } catch (NumberFormatException e) {
            e.printStackTrace();
        }
        return ((long) a.b) >= j2 ? true : z;
    }

    public static boolean a(Context context, String str, int i, String str2, HashMap<String, String> hashMap, Bundle bundle) {
        try {
            Intent intent = new Intent("com.tencent.QQBrowser.action.sdk.document");
            if (hashMap != null) {
                Set<String> keySet = hashMap.keySet();
                if (keySet != null) {
                    for (String str3 : keySet) {
                        String str4 = (String) hashMap.get(str3);
                        if (!TextUtils.isEmpty(str4)) {
                            intent.putExtra(str3, str4);
                        }
                    }
                }
            }
            File file = new File(str);
            intent.putExtra("key_reader_sdk_id", 3);
            intent.putExtra("key_reader_sdk_type", i);
            if (i == 0) {
                intent.putExtra("key_reader_sdk_path", str);
            } else if (i == 1) {
                intent.putExtra("key_reader_sdk_url", str);
            }
            intent.putExtra("key_reader_sdk_format", str2);
            intent.setDataAndType(Uri.fromFile(file), "mtt/" + str2);
            intent.putExtra("loginType", d(context.getApplicationContext()));
            if (bundle != null) {
                intent.putExtra("key_reader_sdk_extrals", bundle);
            }
            context.startActivity(intent);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public static boolean a(Context context, String str, HashMap<String, String> hashMap) {
        Object obj;
        Uri parse = Uri.parse(str);
        Intent intent = new Intent("android.intent.action.VIEW");
        intent.setFlags(268435456);
        intent.setDataAndType(parse, "video/*");
        if (hashMap != null) {
            Set<String> keySet = hashMap.keySet();
            if (keySet != null) {
                for (String str2 : keySet) {
                    String str3 = (String) hashMap.get(str2);
                    if (!TextUtils.isEmpty(str3)) {
                        intent.putExtra(str2, str3);
                    }
                }
            }
        }
        try {
            intent.putExtra("loginType", d(context));
            intent.setComponent(new ComponentName(TbsConfig.APP_QB, "com.tencent.mtt.browser.video.H5VideoThrdcallActivity"));
            context.startActivity(intent);
            obj = 1;
        } catch (Throwable th) {
            obj = null;
        }
        if (obj == null) {
            try {
                intent.setComponent(null);
                context.startActivity(intent);
            } catch (Throwable th2) {
                th2.printStackTrace();
                return false;
            }
        }
        return true;
    }

    private static boolean a(String str) {
        if (str == null || str.length() == 0) {
            return false;
        }
        String trim = str.trim();
        int indexOf = trim.toLowerCase().indexOf("://");
        int indexOf2 = trim.toLowerCase().indexOf(46);
        return (indexOf <= 0 || indexOf2 <= 0 || indexOf <= indexOf2) ? trim.toLowerCase().contains("://") : false;
    }

    public static boolean b(Context context) {
        return a(context).a != -1;
    }

    public static boolean c(Context context) {
        a a = a(context);
        boolean z = false;
        try {
            if (Long.valueOf(a.d).longValue() >= 6001500) {
                z = true;
            }
        } catch (NumberFormatException e) {
            e.printStackTrace();
        }
        return a.b >= 601500 ? true : z;
    }

    private static int d(Context context) {
        String str = context.getApplicationInfo().processName;
        return str.equals("com.tencent.mobileqq") ? 13 : str.equals("com.qzone") ? 14 : str.equals("com.tencent.WBlog") ? 15 : str.equals("com.tencent.mm") ? 24 : 26;
    }
}
