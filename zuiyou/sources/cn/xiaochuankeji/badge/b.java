package cn.xiaochuankeji.badge;

import android.app.Notification;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ResolveInfo;
import android.os.Build;
import android.util.Log;
import cn.xiaochuankeji.badge.impl.AdwHomeBadger;
import cn.xiaochuankeji.badge.impl.ApexHomeBadger;
import cn.xiaochuankeji.badge.impl.DefaultBadger;
import cn.xiaochuankeji.badge.impl.EverythingMeHomeBadger;
import cn.xiaochuankeji.badge.impl.HuaweiHomeBadger;
import cn.xiaochuankeji.badge.impl.NewHtcHomeBadger;
import cn.xiaochuankeji.badge.impl.NovaHomeBadger;
import cn.xiaochuankeji.badge.impl.OPPOHomeBader;
import cn.xiaochuankeji.badge.impl.SamsungHomeBadger;
import cn.xiaochuankeji.badge.impl.SonyHomeBadger;
import cn.xiaochuankeji.badge.impl.VivoHomeBadger;
import cn.xiaochuankeji.badge.impl.ZTEHomeBadger;
import cn.xiaochuankeji.badge.impl.ZukHomeBadger;
import cn.xiaochuankeji.badge.impl.a;
import java.util.LinkedList;
import java.util.List;

public final class b {
    private static final List<Class<? extends a>> a = new LinkedList();
    private static volatile Boolean b;
    private static final Object c = new Object();
    private static a d;
    private static ComponentName e;
    private static int f = 0;

    static {
        a.add(AdwHomeBadger.class);
        a.add(ApexHomeBadger.class);
        a.add(DefaultBadger.class);
        a.add(NewHtcHomeBadger.class);
        a.add(NovaHomeBadger.class);
        a.add(SonyHomeBadger.class);
        a.add(a.class);
        a.add(HuaweiHomeBadger.class);
        a.add(OPPOHomeBader.class);
        a.add(SamsungHomeBadger.class);
        a.add(ZukHomeBadger.class);
        a.add(VivoHomeBadger.class);
        a.add(ZTEHomeBadger.class);
        a.add(EverythingMeHomeBadger.class);
    }

    public static boolean a(Context context, int i) {
        f = i;
        try {
            b(context, i);
            return true;
        } catch (Throwable e) {
            if (Log.isLoggable("ShortcutBadger", 3)) {
                Log.d("ShortcutBadger", "Unable to execute badge", e);
            }
            return false;
        }
    }

    public static void b(Context context, int i) throws ShortcutBadgeException {
        f = i;
        if (d != null || c(context)) {
            try {
                d.a(context, e, i);
                return;
            } catch (Exception e) {
                throw new ShortcutBadgeException("Unable to execute badge", e);
            }
        }
        throw new ShortcutBadgeException("No default launcher available");
    }

    public static boolean a(Context context) {
        return a(context, 0);
    }

    public static boolean b(Context context) {
        if (b == null) {
            synchronized (c) {
                if (b == null) {
                    String str = null;
                    int i = 0;
                    while (i < 3) {
                        try {
                            Log.i("ShortcutBadger", "Checking if platform supports badge counters, attempt " + String.format("%d/%d.", new Object[]{Integer.valueOf(i + 1), Integer.valueOf(3)}));
                            if (c(context)) {
                                d.a(context, e, 0);
                                b = Boolean.valueOf(true);
                                Log.i("ShortcutBadger", "Badge counter is supported in this platform.");
                                break;
                            }
                            str = "Failed to initialize the badge counter.";
                            i++;
                        } catch (Exception e) {
                            str = e.getMessage();
                        }
                    }
                    if (b == null) {
                        Log.w("ShortcutBadger", "Badge counter seems not supported for this platform: " + str);
                        b = Boolean.valueOf(false);
                    }
                }
            }
        }
        return b.booleanValue();
    }

    public static void a(Context context, Notification notification, int i) {
        if (notification != null) {
            try {
                Object obj = notification.getClass().getDeclaredField("extraNotification").get(notification);
                obj.getClass().getDeclaredMethod("setMessageCount", new Class[]{Integer.TYPE}).invoke(obj, new Object[]{Integer.valueOf(i)});
            } catch (Throwable e) {
                if (Log.isLoggable("ShortcutBadger", 3)) {
                    Log.d("ShortcutBadger", "Unable to execute badge", e);
                }
            }
        }
    }

    private static boolean c(Context context) {
        Intent launchIntentForPackage = context.getPackageManager().getLaunchIntentForPackage(context.getPackageName());
        if (launchIntentForPackage == null) {
            Log.e("ShortcutBadger", "Unable to find launch intent for package " + context.getPackageName());
            return false;
        }
        e = launchIntentForPackage.getComponent();
        launchIntentForPackage = new Intent("android.intent.action.MAIN");
        launchIntentForPackage.addCategory("android.intent.category.HOME");
        ResolveInfo resolveActivity = context.getPackageManager().resolveActivity(launchIntentForPackage, 65536);
        if (resolveActivity == null || resolveActivity.activityInfo.name.toLowerCase().contains("resolver")) {
            return false;
        }
        String str = resolveActivity.activityInfo.packageName;
        for (Class newInstance : a) {
            a aVar;
            try {
                aVar = (a) newInstance.newInstance();
            } catch (Exception e) {
                aVar = null;
            }
            if (aVar != null && aVar.a().contains(str)) {
                d = aVar;
                break;
            }
        }
        if (d == null) {
            if (Build.MANUFACTURER.equalsIgnoreCase("ZUK")) {
                d = new ZukHomeBadger();
            } else if (Build.MANUFACTURER.equalsIgnoreCase("OPPO")) {
                d = new OPPOHomeBader();
            } else if (Build.MANUFACTURER.equalsIgnoreCase("VIVO")) {
                d = new VivoHomeBadger();
            } else if (Build.MANUFACTURER.equalsIgnoreCase("ZTE")) {
                d = new ZTEHomeBadger();
            } else {
                d = new DefaultBadger();
            }
        }
        return true;
    }
}
