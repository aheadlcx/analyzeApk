package com.budejie.www.wallpaper.b;

import android.content.Context;
import android.content.Intent;
import android.os.Environment;
import android.text.TextUtils;
import com.ali.auth.third.core.model.Constants;
import com.budejie.www.activity.plate.bean.PlateBean;
import com.budejie.www.bean.ListItemObject;
import com.budejie.www.goddubbing.c.a;
import com.budejie.www.goddubbing.c.d;
import com.budejie.www.http.i;
import com.budejie.www.util.ac;
import com.ishumei.smantifraud.BuildConfig;
import com.umeng.onlineconfig.OnlineConfigAgent;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class b {
    private static String a;

    public static void a(Context context) {
        if (d.q()) {
            a = Environment.getExternalStorageDirectory().getPath() + "/Android/com.budejie.www/WallPaperFinder";
            b();
            return;
        }
        a = context.getFilesDir().getPath() + "/Android/com.budejie.www/WallPaperFinder";
        b();
    }

    private static void b() {
        File file = new File(a);
        if (!file.exists()) {
            file.mkdirs();
        }
    }

    public static String a() {
        return a;
    }

    public static boolean a(String str) {
        if (TextUtils.isEmpty(str)) {
            return false;
        }
        Object<String> c = c();
        if (a.a(c)) {
            return false;
        }
        boolean z;
        for (String equals : c) {
            if (str.equals(equals)) {
                z = true;
                break;
            }
        }
        z = false;
        return z;
    }

    public static boolean a(Context context, ListItemObject listItemObject) {
        return Constants.SERVICE_SCOPE_FLAG_VALUE.equals(OnlineConfigAgent.getInstance().getConfigParams(context, "show_wall_paper_set_icon")) && b(context, listItemObject);
    }

    public static boolean b(Context context, ListItemObject listItemObject) {
        if (listItemObject == null || !"41".equals(listItemObject.getType())) {
            return false;
        }
        int height = listItemObject.getHeight();
        int width = listItemObject.getWidth();
        if (width <= 0) {
            return false;
        }
        float f = ((float) height) / ((float) width);
        Object configParams = OnlineConfigAgent.getInstance().getConfigParams(context, "wall_paper_set_rule");
        if (TextUtils.isEmpty(configParams)) {
            return false;
        }
        float parseFloat;
        try {
            parseFloat = Float.parseFloat(configParams);
        } catch (NumberFormatException e) {
            parseFloat = 0.0f;
        }
        if (f >= parseFloat) {
            return true;
        }
        return false;
    }

    private static List<String> c() {
        List<String> arrayList = new ArrayList();
        File[] listFiles = new File(a).listFiles();
        if (listFiles == null) {
            return null;
        }
        for (File file : listFiles) {
            if (file != null && d.e(file.getPath()) > 0) {
                arrayList.add(file.getName());
            }
        }
        return arrayList;
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static void b(android.content.Context r9) {
        /*
        r2 = 0;
        r5 = new android.content.Intent;
        r5.<init>();
        r0 = 268435456; // 0x10000000 float:2.5243549E-29 double:1.32624737E-315;
        r5.addFlags(r0);
        r0 = "android.intent.action.VIEW";
        r5.setAction(r0);
        r6 = "application/vnd.android.package-archive";
        r0 = r9.getAssets();
        r1 = "app-bdj-wall-paper-release.apk";
        r3 = r0.open(r1);	 Catch:{ Exception -> 0x00a6, all -> 0x008a }
        r0 = new java.io.File;	 Catch:{ Exception -> 0x00ab, all -> 0x009c }
        r1 = new java.lang.StringBuilder;	 Catch:{ Exception -> 0x00ab, all -> 0x009c }
        r1.<init>();	 Catch:{ Exception -> 0x00ab, all -> 0x009c }
        r4 = a;	 Catch:{ Exception -> 0x00ab, all -> 0x009c }
        r1 = r1.append(r4);	 Catch:{ Exception -> 0x00ab, all -> 0x009c }
        r4 = "/bdjWallPaperEngine.apk";
        r1 = r1.append(r4);	 Catch:{ Exception -> 0x00ab, all -> 0x009c }
        r1 = r1.toString();	 Catch:{ Exception -> 0x00ab, all -> 0x009c }
        r0.<init>(r1);	 Catch:{ Exception -> 0x00ab, all -> 0x009c }
        r1 = r0.exists();	 Catch:{ Exception -> 0x00b2, all -> 0x009c }
        if (r1 == 0) goto L_0x003f;
    L_0x003c:
        r0.delete();	 Catch:{ Exception -> 0x00b2, all -> 0x009c }
    L_0x003f:
        r0.createNewFile();	 Catch:{ Exception -> 0x00b2, all -> 0x009c }
        r4 = new java.io.FileOutputStream;	 Catch:{ Exception -> 0x00b2, all -> 0x009c }
        r4.<init>(r0);	 Catch:{ Exception -> 0x00b2, all -> 0x009c }
        r1 = 1024; // 0x400 float:1.435E-42 double:5.06E-321;
        r1 = new byte[r1];	 Catch:{ Exception -> 0x0057, all -> 0x009e }
    L_0x004b:
        r2 = r3.read(r1);	 Catch:{ Exception -> 0x0057, all -> 0x009e }
        r7 = -1;
        if (r2 == r7) goto L_0x0072;
    L_0x0052:
        r7 = 0;
        r4.write(r1, r7, r2);	 Catch:{ Exception -> 0x0057, all -> 0x009e }
        goto L_0x004b;
    L_0x0057:
        r1 = move-exception;
        r2 = r3;
        r3 = r4;
    L_0x005a:
        r1.printStackTrace();	 Catch:{ all -> 0x00a1 }
        if (r2 == 0) goto L_0x0062;
    L_0x005f:
        r2.close();	 Catch:{ IOException -> 0x0085 }
    L_0x0062:
        if (r3 == 0) goto L_0x0067;
    L_0x0064:
        r3.close();	 Catch:{ IOException -> 0x0085 }
    L_0x0067:
        r0 = android.net.Uri.fromFile(r0);
        r5.setDataAndType(r0, r6);
        r9.startActivity(r5);
        return;
    L_0x0072:
        r4.flush();	 Catch:{ Exception -> 0x0057, all -> 0x009e }
        if (r3 == 0) goto L_0x007a;
    L_0x0077:
        r3.close();	 Catch:{ IOException -> 0x0080 }
    L_0x007a:
        if (r4 == 0) goto L_0x0067;
    L_0x007c:
        r4.close();	 Catch:{ IOException -> 0x0080 }
        goto L_0x0067;
    L_0x0080:
        r1 = move-exception;
        r1.printStackTrace();
        goto L_0x0067;
    L_0x0085:
        r1 = move-exception;
        r1.printStackTrace();
        goto L_0x0067;
    L_0x008a:
        r0 = move-exception;
        r3 = r2;
    L_0x008c:
        if (r3 == 0) goto L_0x0091;
    L_0x008e:
        r3.close();	 Catch:{ IOException -> 0x0097 }
    L_0x0091:
        if (r2 == 0) goto L_0x0096;
    L_0x0093:
        r2.close();	 Catch:{ IOException -> 0x0097 }
    L_0x0096:
        throw r0;
    L_0x0097:
        r1 = move-exception;
        r1.printStackTrace();
        goto L_0x0096;
    L_0x009c:
        r0 = move-exception;
        goto L_0x008c;
    L_0x009e:
        r0 = move-exception;
        r2 = r4;
        goto L_0x008c;
    L_0x00a1:
        r0 = move-exception;
        r8 = r2;
        r2 = r3;
        r3 = r8;
        goto L_0x008c;
    L_0x00a6:
        r0 = move-exception;
        r1 = r0;
        r3 = r2;
        r0 = r2;
        goto L_0x005a;
    L_0x00ab:
        r0 = move-exception;
        r1 = r0;
        r0 = r2;
        r8 = r3;
        r3 = r2;
        r2 = r8;
        goto L_0x005a;
    L_0x00b2:
        r1 = move-exception;
        r8 = r3;
        r3 = r2;
        r2 = r8;
        goto L_0x005a;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.budejie.www.wallpaper.b.b.b(android.content.Context):void");
    }

    public static void a(ListItemObject listItemObject, String str, String str2) {
        long j = 0;
        if (listItemObject != null) {
            PlateBean plateBean = listItemObject.getPlateBean(0);
            if (plateBean != null) {
                String str3 = plateBean.theme_name;
                Object videotime = listItemObject.getVideotime();
                if (!TextUtils.isEmpty(videotime)) {
                    try {
                        j = Long.parseLong(videotime) * 1000;
                    } catch (NumberFormatException e) {
                    }
                }
                String a = ac.a(j);
                String content = listItemObject.getContent();
                a = str3 + "|" + a + "|" + content + "|" + listItemObject.getWid();
                if (!TextUtils.isEmpty(str2)) {
                    a = a + "|" + str2;
                }
                i.a(str, a);
            }
        }
    }

    public static boolean c(Context context) {
        Object obj;
        try {
            obj = context.getPackageManager().getPackageInfo("com.spriteapp.bdjwallpaperengine", 0).versionName;
        } catch (Exception e) {
            obj = "";
        }
        if (BuildConfig.VERSION_NAME.equals(obj)) {
            return false;
        }
        return true;
    }

    public static void a(Context context, String str) {
        a(context, str, false);
    }

    public static void d(Context context) {
        a(context, null, true);
    }

    public static void a(Context context, String str, boolean z) {
        Intent intent = new Intent("android.intent.action.VIEW");
        intent.setClassName("com.spriteapp.bdjwallpaperengine", "com.spriteapp.bdjwallpaperengine.MainActivity");
        intent.putExtra("video_path_tag", str);
        intent.putExtra("is_set_volume_tag", z);
        intent.putExtra("has_volume_tag", e(context));
        context.startActivity(intent);
    }

    public static void a(Context context, boolean z) {
        context.getSharedPreferences("weiboprefer", 0).edit().putBoolean("wall_paper_volume_control", z).apply();
    }

    public static boolean e(Context context) {
        return context.getSharedPreferences("weiboprefer", 0).getBoolean("wall_paper_volume_control", false);
    }
}
