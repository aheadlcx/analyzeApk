package cn.xiaochuankeji.aop.permission;

import android.app.Activity;
import android.content.Context;
import android.content.res.Resources.NotFoundException;
import android.os.Handler;
import android.os.Looper;
import android.text.TextUtils;
import java.util.HashMap;
import java.util.Map;
import junit.framework.Assert;
import org.aspectj.lang.NoAspectBoundException;
import org.aspectj.lang.b;

public class c {
    public static final c a = null;
    private static Map<String, CheckPermissionItem> b = new HashMap();
    private static Map<String, Boolean> c = new HashMap();
    private static Handler d = new Handler(Looper.getMainLooper());
    private static Throwable e;

    public static String b(Context context, String str, int i) {
        return c(context, str, i);
    }

    public static c c() {
        if (a != null) {
            return a;
        }
        throw new NoAspectBoundException("cn.xiaochuankeji.aop.permission.PermissionAspect", e);
    }

    public static Handler d() {
        return d;
    }

    public static Map e() {
        return c;
    }

    private static void f() {
        a = new c();
    }

    static {
        try {
            f();
        } catch (Throwable th) {
            e = th;
        }
    }

    public void a(final b bVar, final b bVar2) throws Throwable {
        if (bVar2 == null) {
            try {
                bVar.c();
                return;
            } catch (NoSuchMethodException e) {
                bVar.c();
                return;
            }
        }
        if (bVar2.m()) {
            bVar.c();
        }
        String[] a = bVar2.a();
        if (a != null && a.length > 0) {
            Context context = d.a;
            PermissionItem permissionItem = new PermissionItem(a);
            Object b = b(context, bVar2.b(), bVar2.c());
            Object b2 = b(context, bVar2.d(), bVar2.e());
            Object b3 = b(context, bVar2.f(), bVar2.g());
            Object b4 = b(context, bVar2.h(), bVar2.i());
            Object b5 = b(context, bVar2.j(), bVar2.k());
            if (!(TextUtils.isEmpty(b) || TextUtils.isEmpty(b2))) {
                permissionItem.rationalMessage(b).rationalButton(b2);
            }
            if (!(TextUtils.isEmpty(b3) || TextUtils.isEmpty(b4))) {
                permissionItem.deniedMessage(b3).deniedButton(b4);
            }
            if (!TextUtils.isEmpty(b5)) {
                permissionItem.settingText(b5);
            }
            permissionItem.needGotoSetting(bVar2.l());
            a.a(context).a(permissionItem, new e(this) {
                final /* synthetic */ c c;

                public void permissionGranted() {
                    if (!bVar2.m()) {
                        try {
                            bVar.c();
                        } catch (Throwable th) {
                            th.printStackTrace();
                        }
                    }
                }

                public void permissionDenied() {
                }
            });
        }
    }

    public void a(final b bVar) throws Throwable {
        final Activity activity = (Activity) bVar.a();
        if (!e().containsKey(activity.getClass().getName())) {
            d().postDelayed(new Runnable(this) {
                final /* synthetic */ c c;

                public void run() {
                    b bVar = (b) activity.getClass().getAnnotation(b.class);
                    if (bVar != null) {
                        String[] a = bVar.a();
                        if (a != null && a.length > 0) {
                            c.b(activity, a, c.c(activity, bVar.b(), bVar.c()), c.c(activity, bVar.d(), bVar.e()), c.c(activity, bVar.f(), bVar.g()), c.c(activity, bVar.h(), bVar.i()), c.c(activity, bVar.j(), bVar.k()), bVar.l(), bVar.m());
                            return;
                        }
                        return;
                    }
                    String name = bVar.b().a().getName();
                    if (c.b.containsKey(name)) {
                        CheckPermissionItem checkPermissionItem = (CheckPermissionItem) c.b.get(name);
                        c.b(activity, checkPermissionItem.permissionItem.permissions, checkPermissionItem.permissionItem.rationalMessage, checkPermissionItem.permissionItem.rationalButton, checkPermissionItem.permissionItem.deniedMessage, checkPermissionItem.permissionItem.deniedButton, checkPermissionItem.permissionItem.settingText, checkPermissionItem.permissionItem.needGotoSetting, checkPermissionItem.permissionItem.runIgnorePermission);
                    }
                }
            }, 100);
            c.put(activity.getClass().getName(), Boolean.valueOf(true));
        }
        bVar.c();
    }

    private static String c(Context context, String str, int i) {
        if (context == null) {
            return null;
        }
        if (!TextUtils.isEmpty(str) || i <= 0) {
            return str;
        }
        try {
            return context.getString(i);
        } catch (NotFoundException e) {
            return str;
        }
    }

    private static void b(final Activity activity, String[] strArr, String str, String str2, String str3, String str4, String str5, boolean z, final boolean z2) {
        boolean z3 = strArr != null && strArr.length > 0;
        Assert.assertTrue(z3);
        PermissionItem permissionItem = new PermissionItem(strArr);
        if (!(TextUtils.isEmpty(str) || TextUtils.isEmpty(str2))) {
            permissionItem.rationalMessage(str).rationalButton(str2);
        }
        if (!(TextUtils.isEmpty(str3) || TextUtils.isEmpty(str4))) {
            permissionItem.deniedMessage(str3).deniedButton(str4);
        }
        if (!TextUtils.isEmpty(str5)) {
            permissionItem.settingText(str5);
        }
        permissionItem.needGotoSetting(z);
        a.a((Context) activity).a(permissionItem, new e() {
            public void permissionGranted() {
                c.c.remove(activity.getClass().getName());
            }

            public void permissionDenied() {
                c.c.remove(activity.getClass().getName());
                if (!z2) {
                    activity.finish();
                }
            }
        });
    }
}
