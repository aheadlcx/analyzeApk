package com.umeng.update;

import android.app.NotificationManager;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.ServiceInfo;
import android.net.Uri;
import android.os.Handler;
import android.os.Message;
import com.umeng.update.net.j;
import java.io.File;
import java.io.IOException;
import u.upd.a;
import u.upd.b;
import u.upd.m;

public class UmengUpdateAgent {
    private static UmengUpdateListener a = null;
    private static UmengDialogButtonListener b = null;
    private static UmengDownloadListener c = null;
    private static Context d;
    private static c e = new c();
    private static Handler f;

    public static void setDefault() {
        setDeltaUpdate(true);
        setUpdateAutoPopup(true);
        setUpdateOnlyWifi(true);
        setRichNotification(true);
        setDialogListener(null);
        setDownloadListener(null);
        setUpdateListener(null);
        setAppkey(null);
        setChannel(null);
        setUpdateUIStyle(0);
    }

    public static void setUpdateCheckConfig(boolean z) {
        UpdateConfig.setUpdateCheck(z);
    }

    public static void setUpdateOnlyWifi(boolean z) {
        UpdateConfig.setUpdateOnlyWifi(z);
    }

    public static void setUpdateAutoPopup(boolean z) {
        UpdateConfig.setUpdateAutoPopup(z);
    }

    public static void setUpdateUIStyle(int i) {
        UpdateConfig.setStyle(i);
    }

    public static void setDeltaUpdate(boolean z) {
        UpdateConfig.setDeltaUpdate(z);
    }

    public static void setAppkey(String str) {
        UpdateConfig.setAppkey(str);
    }

    public static void setChannel(String str) {
        UpdateConfig.setChannel(str);
    }

    public static void setRichNotification(boolean z) {
        UpdateConfig.setRichNotification(z);
    }

    public static void setUpdateListener(UmengUpdateListener umengUpdateListener) {
        a = umengUpdateListener;
    }

    public static void setDialogListener(UmengDialogButtonListener umengDialogButtonListener) {
        b = umengDialogButtonListener;
    }

    public static void setDownloadListener(UmengDownloadListener umengDownloadListener) {
        c = umengDownloadListener;
    }

    private static void b(int i, UpdateResponse updateResponse) {
        Message message = new Message();
        message.what = i;
        message.obj = updateResponse;
        f.sendMessage(message);
    }

    public static void silentUpdate(Context context) {
        UpdateConfig.setUpdateForce(false);
        UpdateConfig.setSilentDownload(true);
        b(context.getApplicationContext());
    }

    public static void forceUpdate(Context context) {
        UpdateConfig.setUpdateForce(true);
        UpdateConfig.setSilentDownload(false);
        b(context.getApplicationContext());
    }

    public static void update(Context context) {
        UpdateConfig.setUpdateForce(false);
        UpdateConfig.setSilentDownload(false);
        b(context.getApplicationContext());
    }

    public static void update(Context context, String str, String str2) {
        UpdateConfig.setAppkey(str);
        UpdateConfig.setChannel(str2);
        update(context);
    }

    private static void b(Context context) {
        if (context == null) {
            try {
                b.b(UpdateConfig.a, "unexpected null context in update");
                return;
            } catch (Exception e) {
                b.b(UpdateConfig.a, "Exception occurred in Mobclick.update(). ", e);
                return;
            }
        }
        f = new UmengUpdateAgent$1(context.getMainLooper());
        c(context);
        if (!a.d(context)) {
            if (UpdateConfig.isSilentDownload()) {
                b(2, null);
                return;
            } else if (UpdateConfig.isUpdateOnlyWifi() && !UpdateConfig.isUpdateForce()) {
                b(2, null);
                return;
            }
        }
        if (e.a()) {
            b(4, null);
            b.a(UpdateConfig.a, "Is updating now.");
            f.post(new UmengUpdateAgent$2(context));
            return;
        }
        d = context;
        new Thread(new UmengUpdateAgent$a(context.getApplicationContext())).start();
    }

    private static void b(Context context, UpdateResponse updateResponse, int i) {
        try {
            if (!isIgnore(context, updateResponse)) {
                File downloadedFile = downloadedFile(context, updateResponse);
                boolean z = downloadedFile != null;
                if (z || !UpdateConfig.isSilentDownload()) {
                    switch (i) {
                        case 0:
                            e.a(context, updateResponse, z, downloadedFile);
                            return;
                        case 1:
                            ((NotificationManager) context.getSystemService("notification")).notify(0, e.b(context, updateResponse, z, downloadedFile).a());
                            return;
                        default:
                            return;
                    }
                    b.b(UpdateConfig.a, "Fail to create update dialog box.", e);
                }
                startDownload(context, updateResponse);
            }
        } catch (Exception e) {
            b.b(UpdateConfig.a, "Fail to create update dialog box.", e);
        }
    }

    public static void showUpdateDialog(Context context, UpdateResponse updateResponse) {
        b(context, updateResponse, 0);
    }

    public static void showUpdateNotification(Context context, UpdateResponse updateResponse) {
        b(context, updateResponse, 1);
    }

    public static File downloadedFile(Context context, UpdateResponse updateResponse) {
        try {
            File file = new File(j.a("/apk", context, new boolean[1]), updateResponse.new_md5 + ".apk");
            if (file.exists() && updateResponse.new_md5.equalsIgnoreCase(m.a(file))) {
                return file;
            }
            return null;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static boolean isIgnore(Context context, UpdateResponse updateResponse) {
        return (updateResponse.new_md5 == null || !updateResponse.new_md5.equalsIgnoreCase(UpdateConfig.getIgnoreMd5(context)) || UpdateConfig.isUpdateForce()) ? false : true;
    }

    public static void ignoreUpdate(Context context, UpdateResponse updateResponse) {
        UpdateConfig.saveIgnoreMd5(context, updateResponse.new_md5);
    }

    static void a(int i, Context context, UpdateResponse updateResponse, File file) {
        switch (i) {
            case 5:
                a(context, updateResponse, file);
                break;
            case 7:
                ignoreUpdate(context, updateResponse);
                break;
        }
        if (b != null) {
            b.onClick(i);
        }
    }

    private static void a(Context context, UpdateResponse updateResponse, File file) {
        if (file == null) {
            startDownload(context, updateResponse);
        } else {
            startInstall(context, file);
        }
    }

    public static void startInstall(Context context, File file) {
        Intent intent = new Intent("android.intent.action.VIEW");
        intent.addFlags(268435456);
        intent.setDataAndType(Uri.fromFile(file), "application/vnd.android.package-archive");
        context.startActivity(intent);
    }

    public static void startDownload(Context context, UpdateResponse updateResponse) {
        if (updateResponse.delta && UpdateConfig.isDeltaUpdate()) {
            e.a(context, updateResponse.origin, updateResponse.new_md5, updateResponse.path, updateResponse.patch_md5, c);
            e.b();
            return;
        }
        e.a(context, updateResponse.path, updateResponse.new_md5, null, null, c);
        e.c();
    }

    private static boolean c(Context context) {
        if (!UpdateConfig.isUpdateCheck()) {
            return true;
        }
        try {
            if (!Class.forName(context.getPackageName() + ".BuildConfig").getField("DEBUG").getBoolean(null)) {
                return true;
            }
            boolean z;
            try {
                if (UpdateConfig.getAppkey(context) == null) {
                    f.post(new UmengUpdateAgent$3(context));
                    return false;
                }
                int i;
                PackageInfo packageInfo = context.getPackageManager().getPackageInfo(context.getPackageName(), 4101);
                if (packageInfo.activities != null) {
                    i = 0;
                    z = false;
                    while (i < packageInfo.activities.length) {
                        try {
                            if (UpdateConfig.e.equals(packageInfo.activities[i].name)) {
                                z = true;
                            }
                            i++;
                        } catch (Exception e) {
                        }
                    }
                } else {
                    z = false;
                }
                if (z) {
                    if (packageInfo.services != null) {
                        z = false;
                        for (ServiceInfo serviceInfo : packageInfo.services) {
                            if (UpdateConfig.d.equals(serviceInfo.name)) {
                                z = true;
                            }
                        }
                    } else {
                        z = false;
                    }
                    if (z) {
                        boolean z2;
                        boolean z3;
                        if (packageInfo.requestedPermissions != null) {
                            z = false;
                            z3 = false;
                            z2 = false;
                            for (int i2 = 0; i2 < packageInfo.requestedPermissions.length; i2++) {
                                if (UpdateConfig.f.equals(packageInfo.requestedPermissions[i2])) {
                                    z2 = true;
                                } else if (UpdateConfig.g.equals(packageInfo.requestedPermissions[i2])) {
                                    z3 = true;
                                } else if (UpdateConfig.h.equals(packageInfo.requestedPermissions[i2])) {
                                    z = true;
                                }
                            }
                        } else {
                            z = false;
                            z3 = false;
                            z2 = false;
                        }
                        if (z2 && r2 && r0) {
                            z = true;
                        } else {
                            z = false;
                        }
                        if (z) {
                            try {
                                if (UpdateConfig.b.equals(context.getString(Class.forName(context.getPackageName() + ".R$string").getField(UpdateConfig.i).getInt(null)))) {
                                    z = true;
                                } else {
                                    z = false;
                                }
                                if (z) {
                                    return true;
                                }
                            } catch (Exception e2) {
                                z = false;
                            }
                            f.post(new UmengUpdateAgent$7(context));
                            return z;
                        }
                        f.post(new UmengUpdateAgent$6(context));
                        return false;
                    }
                    f.post(new UmengUpdateAgent$5(context));
                    return false;
                }
                f.post(new UmengUpdateAgent$4(context));
                return false;
            } catch (Exception e3) {
                z = false;
            }
        } catch (Exception e4) {
            return true;
        }
    }
}
