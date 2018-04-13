package com.tencent.tinker.loader;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.res.AssetManager;
import android.content.res.Resources;
import android.os.Build.VERSION;
import android.util.ArrayMap;
import android.util.Log;
import com.tencent.tinker.loader.shareutil.ShareConstants;
import com.tencent.tinker.loader.shareutil.ShareReflectUtil;
import java.lang.ref.WeakReference;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

class TinkerResourcePatcher {
    private static Collection<WeakReference<Resources>> a = null;
    private static Object b = null;
    private static AssetManager c = null;
    private static Method d = null;
    private static Method e = null;
    private static Field f = null;
    private static Field g = null;
    private static Field h = null;
    private static Field i = null;
    private static Field j = null;
    private static Field k = null;

    TinkerResourcePatcher() {
    }

    public static void a(Context context) throws Throwable {
        Class cls;
        Class cls2 = Class.forName("android.app.ActivityThread");
        b = ShareReflectUtil.getActivityThread(context, cls2);
        try {
            cls = Class.forName("android.app.LoadedApk");
        } catch (ClassNotFoundException e) {
            cls = Class.forName("android.app.ActivityThread$PackageInfo");
        }
        h = cls.getDeclaredField("mResDir");
        h.setAccessible(true);
        i = cls2.getDeclaredField("mPackages");
        i.setAccessible(true);
        j = cls2.getDeclaredField("mResourcePackages");
        j.setAccessible(true);
        if (context.getAssets().getClass().getName().equals("android.content.res.BaiduAssetManager")) {
            c = (AssetManager) Class.forName("android.content.res.BaiduAssetManager").getConstructor(new Class[0]).newInstance(new Object[0]);
        } else {
            c = (AssetManager) AssetManager.class.getConstructor(new Class[0]).newInstance(new Object[0]);
        }
        d = AssetManager.class.getDeclaredMethod("addAssetPath", new Class[]{String.class});
        d.setAccessible(true);
        e = AssetManager.class.getDeclaredMethod("ensureStringBlocks", new Class[0]);
        e.setAccessible(true);
        Field declaredField;
        if (VERSION.SDK_INT >= 19) {
            cls2 = Class.forName("android.app.ResourcesManager");
            Method declaredMethod = cls2.getDeclaredMethod("getInstance", new Class[0]);
            declaredMethod.setAccessible(true);
            Object invoke = declaredMethod.invoke(null, new Object[0]);
            try {
                declaredField = cls2.getDeclaredField("mActiveResources");
                declaredField.setAccessible(true);
                a = ((ArrayMap) declaredField.get(invoke)).values();
            } catch (NoSuchFieldException e2) {
                declaredField = cls2.getDeclaredField("mResourceReferences");
                declaredField.setAccessible(true);
                a = (Collection) declaredField.get(invoke);
            }
        } else {
            declaredField = cls2.getDeclaredField("mActiveResources");
            declaredField.setAccessible(true);
            a = ((HashMap) declaredField.get(b)).values();
        }
        if (a == null) {
            throw new IllegalStateException("resource references is null");
        }
        if (VERSION.SDK_INT >= 24) {
            try {
                g = Resources.class.getDeclaredField("mResourcesImpl");
                g.setAccessible(true);
            } catch (Throwable th) {
                f = Resources.class.getDeclaredField("mAssets");
                f.setAccessible(true);
            }
        } else {
            f = Resources.class.getDeclaredField("mAssets");
            f.setAccessible(true);
        }
        try {
            k = ShareReflectUtil.findField(ApplicationInfo.class, "publicSourceDir");
        } catch (NoSuchFieldException e3) {
        }
    }

    public static void a(Context context, String str) throws Throwable {
        if (str != null) {
            for (Field field : new Field[]{i, j}) {
                for (Entry value : ((Map) field.get(b)).entrySet()) {
                    Object obj = ((WeakReference) value.getValue()).get();
                    if (!(obj == null || str == null)) {
                        h.set(obj, str);
                    }
                }
            }
            if (((Integer) d.invoke(c, new Object[]{str})).intValue() == 0) {
                throw new IllegalStateException("Could not create new AssetManager");
            }
            e.invoke(c, new Object[0]);
            for (WeakReference weakReference : a) {
                Resources resources = (Resources) weakReference.get();
                if (resources != null) {
                    try {
                        f.set(resources, c);
                    } catch (Throwable th) {
                        Object obj2 = g.get(resources);
                        Field findField = ShareReflectUtil.findField(obj2, "mAssets");
                        findField.setAccessible(true);
                        findField.set(obj2, c);
                    }
                    a(resources);
                    resources.updateConfiguration(resources.getConfiguration(), resources.getDisplayMetrics());
                }
            }
            if (VERSION.SDK_INT >= 24) {
                try {
                    if (k != null) {
                        k.set(context.getApplicationInfo(), str);
                    }
                } catch (Throwable th2) {
                }
            }
            if (!b(context)) {
                throw new TinkerRuntimeException(ShareConstants.CHECK_RES_INSTALL_FAIL);
            }
        }
    }

    private static void a(Resources resources) {
        Log.w("Tinker.ResourcePatcher", "try to clear typedArray cache!");
        try {
            Field findField = ShareReflectUtil.findField(Resources.class, "mTypedArrayPool");
            Object obj = findField.get(resources);
            Field findField2 = ShareReflectUtil.findField(obj, "mPool");
            Constructor constructor = obj.getClass().getConstructor(new Class[]{Integer.TYPE});
            constructor.setAccessible(true);
            findField.set(resources, constructor.newInstance(new Object[]{Integer.valueOf(((Object[]) findField2.get(obj)).length)}));
        } catch (Throwable th) {
            Log.e("Tinker.ResourcePatcher", "clearPreloadTypedArrayIssue failed, ignore error: " + th);
        }
    }

    private static boolean b(Context context) {
        try {
            context.getAssets().open("only_use_to_test_tinker_resource.txt");
            Log.i("Tinker.ResourcePatcher", "checkResUpdate success, found test resource assets file only_use_to_test_tinker_resource.txt");
            return true;
        } catch (Throwable th) {
            Log.e("Tinker.ResourcePatcher", "checkResUpdate failed, can't find test resource assets file only_use_to_test_tinker_resource.txt e:" + th.getMessage());
            return false;
        }
    }
}
