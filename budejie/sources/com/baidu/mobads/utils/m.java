package com.baidu.mobads.utils;

import android.annotation.TargetApi;
import android.app.ActivityManager;
import android.app.ActivityManager.RunningAppProcessInfo;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.content.pm.ResolveInfo;
import android.net.Uri;
import android.os.Build.VERSION;
import android.os.Environment;
import com.ali.auth.third.core.model.Constants;
import com.baidu.mobads.a.a;
import com.baidu.mobads.interfaces.utils.IBase64;
import com.baidu.mobads.interfaces.utils.IXAdPackageUtils;
import com.baidu.mobads.interfaces.utils.IXAdPackageUtils.ApkInfo;
import com.baidu.mobads.interfaces.utils.IXAdSystemUtils;
import com.baidu.mobads.interfaces.utils.IXAdURIUitls;
import com.baidu.mobads.openad.FileProvider;
import com.baidu.mobads.openad.d.c;
import com.budejie.www.R$styleable;
import com.umeng.update.UpdateConfig;
import java.io.File;
import java.util.List;
import org.apache.commons.httpclient.HttpState;

public class m implements IXAdPackageUtils {
    public boolean isInstalled(Context context, String str) {
        try {
            ApplicationInfo applicationInfo = context.getPackageManager().getApplicationInfo(str, 0);
            if (applicationInfo == null || !str.equals(applicationInfo.packageName)) {
                return false;
            }
            return true;
        } catch (NameNotFoundException e) {
            return false;
        }
    }

    public boolean isSystemPackage(PackageInfo packageInfo) {
        return (packageInfo.applicationInfo.flags & 1) != 0;
    }

    @TargetApi(3)
    public void openApp(Context context, String str) {
        try {
            Intent launchIntentForPackage = context.getPackageManager().getLaunchIntentForPackage(str);
            launchIntentForPackage.addFlags(268435456);
            context.startActivity(launchIntentForPackage);
        } catch (Exception e) {
        }
    }

    @Deprecated
    public Intent getInstallIntent(String str) {
        try {
            Uri fromFile = Uri.fromFile(new File(str));
            Intent intent = new Intent("android.intent.action.VIEW");
            try {
                intent.addCategory("android.intent.category.DEFAULT");
                intent.addFlags(268435456);
                intent.setDataAndType(fromFile, "application/vnd.android.package-archive");
                return intent;
            } catch (Exception e) {
                return intent;
            }
        } catch (Exception e2) {
            return null;
        }
    }

    public Intent a(Context context, File file) {
        Intent intent;
        if (file != null) {
            try {
                if (file.exists()) {
                    Uri c;
                    intent = new Intent("android.intent.action.VIEW");
                    intent.addCategory("android.intent.category.DEFAULT");
                    if (a(context)) {
                        intent.addFlags(268435457);
                        c = c(context, file);
                    } else {
                        intent.addFlags(268435456);
                        c = Uri.fromFile(file);
                    }
                    if (c == null) {
                        return null;
                    }
                    intent.setDataAndType(c, "application/vnd.android.package-archive");
                    return intent;
                }
            } catch (Exception e) {
                e.printStackTrace();
                return null;
            }
        }
        intent = null;
        return intent;
    }

    public static boolean a(Context context) {
        return VERSION.SDK_INT >= 24 && d(context) >= 24;
    }

    private static Uri c(Context context, File file) {
        try {
            return FileProvider.getUriForFile(context, context.getPackageName() + ".bd.provider", file);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public static boolean b(Context context) {
        if (!a(context) || c(context) || c(context, k.a(context.getExternalFilesDir(null).getPath()))) {
            return true;
        }
        return false;
    }

    public static boolean c(Context context) {
        if (XAdSDKFoundationFacade.getInstance().getCommonUtils().checkSelfPermission(context, UpdateConfig.f) && c(context, k.a(Environment.getExternalStorageDirectory().getPath()))) {
            return true;
        }
        return false;
    }

    private static boolean c(Context context, String str) {
        if (!a(context)) {
            return true;
        }
        try {
            File file = new File(str + "t");
            if (!file.exists()) {
                file.mkdir();
            }
            if (c(context, file) != null) {
                return true;
            }
            return false;
        } catch (Throwable e) {
            l.a().e(e);
            return false;
        }
    }

    public Intent a(Context context, String str) {
        Intent intent = null;
        try {
            intent = a(context, new File(str));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return intent;
    }

    public void b(Context context, File file) {
        try {
            context.startActivity(a(context, file));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void b(Context context, String str) {
        try {
            context.startActivity(a(context, str));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public int getAppVersion(Context context) {
        int i = 0;
        try {
            return context.getPackageManager().getPackageInfo(context.getPackageName(), 16384).versionCode;
        } catch (NameNotFoundException e) {
            return i;
        }
    }

    public ApkInfo getLocalApkFileInfo(Context context, String str) {
        try {
            PackageInfo packageArchiveInfo = context.getPackageManager().getPackageArchiveInfo(str, 1);
            if (packageArchiveInfo != null) {
                return new ApkInfo(context, packageArchiveInfo);
            }
            return null;
        } catch (Exception e) {
            return null;
        }
    }

    public boolean isForeground(Context context, String str) {
        try {
            for (RunningAppProcessInfo runningAppProcessInfo : ((ActivityManager) context.getSystemService("activity")).getRunningAppProcesses()) {
                if (runningAppProcessInfo.processName.equals(str)) {
                    if (runningAppProcessInfo.importance == 100) {
                        return true;
                    }
                    return false;
                }
            }
            return false;
        } catch (Exception e) {
            return false;
        }
    }

    public void sendAPOIsSuccess(Context context, boolean z, int i, String str, String str2) {
        d commonUtils = XAdSDKFoundationFacade.getInstance().getCommonUtils();
        IXAdURIUitls uRIUitls = XAdSDKFoundationFacade.getInstance().getURIUitls();
        IXAdSystemUtils systemUtils = XAdSDKFoundationFacade.getInstance().getSystemUtils();
        IBase64 base64 = XAdSDKFoundationFacade.getInstance().getBase64();
        String encodeUrl = uRIUitls.encodeUrl(str);
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("aposuccess=" + z);
        if (!z) {
            stringBuilder.append("&failtime=" + i);
        }
        stringBuilder.append("&sn=" + systemUtils.getEncodedSN(context));
        stringBuilder.append("&mac=" + base64.encode(systemUtils.getMacAddress(context)));
        stringBuilder.append("&cuid=" + systemUtils.getCUID(context));
        stringBuilder.append("&pack=" + context.getPackageName());
        stringBuilder.append("&v=" + ("android_" + a.c + "_" + "4.1.30"));
        stringBuilder.append("&targetscheme=" + encodeUrl);
        stringBuilder.append("&pk=" + str2);
        try {
            c cVar = new c(uRIUitls.addParameters(commonUtils.vdUrl(stringBuilder.toString(), R$styleable.Theme_Custom_share_totop_theme), null), "");
            cVar.e = 1;
            new com.baidu.mobads.openad.d.a().a(cVar);
        } catch (Exception e) {
            XAdSDKFoundationFacade.getInstance().getAdLogger().d("XAdPackageUtils", e.getMessage());
        }
    }

    public boolean sendAPOInfo(Context context, String str, String str2, int i, int i2, int i3) {
        String str3;
        boolean z;
        PackageManager packageManager = context.getPackageManager();
        IXAdSystemUtils systemUtils = XAdSDKFoundationFacade.getInstance().getSystemUtils();
        IXAdURIUitls uRIUitls = XAdSDKFoundationFacade.getInstance().getURIUitls();
        d commonUtils = XAdSDKFoundationFacade.getInstance().getCommonUtils();
        Intent intent = new Intent("android.intent.action.VIEW", Uri.parse(str));
        intent.addFlags(268435456);
        String encodeUrl = uRIUitls.encodeUrl(str);
        List queryIntentActivities = packageManager.queryIntentActivities(intent, 65536);
        String str4 = "&sn=" + systemUtils.getEncodedSN(context) + "&fb_act=" + i2 + "&pack=" + context.getPackageName() + "&v=" + ("android_" + a.c + "_" + "4.1.30") + "&targetscheme=" + encodeUrl + "&pk=" + str2;
        String str5 = "&open=";
        if (queryIntentActivities.size() > 0) {
            str5 = str5 + Constants.SERVICE_SCOPE_FLAG_VALUE;
            encodeUrl = "";
            str3 = str5 + "&n=" + queryIntentActivities.size();
            z = true;
            int i4 = 0;
            while (i4 < queryIntentActivities.size()) {
                boolean z2;
                ResolveInfo resolveInfo = (ResolveInfo) queryIntentActivities.get(i4);
                if (i4 == 0) {
                    str3 = str3 + "&p=" + resolveInfo.activityInfo.packageName;
                } else {
                    str3 = str3 + "," + resolveInfo.activityInfo.packageName;
                }
                if (str2.equals(resolveInfo.activityInfo.packageName)) {
                    try {
                        int i5 = packageManager.getPackageInfo(resolveInfo.activityInfo.packageName, 0).versionCode;
                        if (i5 < i3) {
                            z = false;
                        }
                        str5 = encodeUrl + "&installedVersion=" + i5 + "&requiredVersion=" + i3 + "&realopen=" + z;
                        z2 = z;
                    } catch (Exception e) {
                        Exception exception = e;
                        Exception exception2 = exception;
                        str5 = encodeUrl + "&exception=true&installedVersion=" + -1 + "&requiredVersion=" + i3 + "&realopen=" + z;
                        exception2.printStackTrace();
                        z2 = z;
                    }
                } else {
                    str5 = encodeUrl;
                    z2 = z;
                }
                i4++;
                z = z2;
                encodeUrl = str5;
            }
            if (!encodeUrl.equals("")) {
                str3 = str3 + encodeUrl;
            }
        } else {
            z = false;
            str3 = str5 + HttpState.PREEMPTIVE_DEFAULT;
        }
        try {
            c cVar = new c(uRIUitls.addParameters(commonUtils.vdUrl(str4 + str3, i), null), "");
            cVar.e = 1;
            new com.baidu.mobads.openad.d.a().a(cVar);
        } catch (Exception e2) {
            XAdSDKFoundationFacade.getInstance().getAdLogger().d("XAdPackageUtils", e2.getMessage());
        }
        return z;
    }

    public void sendDialerIsSuccess(Context context, boolean z, int i, String str) {
        d commonUtils = XAdSDKFoundationFacade.getInstance().getCommonUtils();
        IXAdURIUitls uRIUitls = XAdSDKFoundationFacade.getInstance().getURIUitls();
        IXAdSystemUtils systemUtils = XAdSDKFoundationFacade.getInstance().getSystemUtils();
        IBase64 base64 = XAdSDKFoundationFacade.getInstance().getBase64();
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("callstate=" + z);
        if (!z) {
            stringBuilder.append("&duration=" + i);
        }
        stringBuilder.append("&sn=" + systemUtils.getEncodedSN(context));
        stringBuilder.append("&mac=" + base64.encode(systemUtils.getMacAddress(context)));
        stringBuilder.append("&bdr=" + VERSION.SDK_INT);
        stringBuilder.append("&cuid=" + systemUtils.getCUID(context));
        stringBuilder.append("&pack=" + context.getPackageName());
        stringBuilder.append("&v=" + ("android_" + a.c + "_" + "4.1.30"));
        stringBuilder.append("&pk=" + str);
        try {
            c cVar = new c(uRIUitls.addParameters(commonUtils.vdUrl(stringBuilder.toString(), R$styleable.Theme_Custom_post_standard_look_background), null), "");
            cVar.e = 1;
            new com.baidu.mobads.openad.d.a().a(cVar);
        } catch (Exception e) {
            XAdSDKFoundationFacade.getInstance().getAdLogger().d("XAdPackageUtils", e.getMessage());
        }
    }

    public static int d(Context context) {
        int i = 0;
        try {
            return context.getApplicationContext().getApplicationInfo().targetSdkVersion;
        } catch (Exception e) {
            e.printStackTrace();
            return i;
        }
    }
}
