package com.tencent.tinker.lib.library;

import android.content.Context;
import android.os.Build.VERSION;
import com.tencent.tinker.lib.tinker.Tinker;
import com.tencent.tinker.lib.tinker.TinkerLoadResult;
import com.tencent.tinker.lib.util.TinkerLog;
import com.tencent.tinker.loader.TinkerRuntimeException;
import com.tencent.tinker.loader.shareutil.ShareConstants;
import com.tencent.tinker.loader.shareutil.SharePatchFileUtil;
import java.io.File;

public class TinkerLoadLibrary {
    private static final String TAG = "Tinker.LoadLibrary";

    public static void loadArmLibrary(Context context, String str) {
        if (str == null || str.isEmpty() || context == null) {
            throw new TinkerRuntimeException("libName or context is null!");
        } else if (!Tinker.with(context).isEnabledForNativeLib() || !loadLibraryFromTinker(context, "lib/armeabi", str)) {
            System.loadLibrary(str);
        }
    }

    public static void loadArmV7Library(Context context, String str) {
        if (str == null || str.isEmpty() || context == null) {
            throw new TinkerRuntimeException("libName or context is null!");
        } else if (!Tinker.with(context).isEnabledForNativeLib() || !loadLibraryFromTinker(context, "lib/armeabi-v7a", str)) {
            System.loadLibrary(str);
        }
    }

    public static boolean loadLibraryFromTinker(Context context, String str, String str2) throws UnsatisfiedLinkError {
        Tinker with = Tinker.with(context);
        if (!str2.startsWith(ShareConstants.SO_PATH)) {
            str2 = ShareConstants.SO_PATH + str2;
        }
        if (!str2.endsWith(".so")) {
            str2 = str2 + ".so";
        }
        String str3 = str + "/" + str2;
        if (with.isEnabledForNativeLib() && with.isTinkerLoaded()) {
            TinkerLoadResult tinkerLoadResultIfPresent = with.getTinkerLoadResultIfPresent();
            if (tinkerLoadResultIfPresent.libs != null) {
                for (String str4 : tinkerLoadResultIfPresent.libs.keySet()) {
                    if (str4.equals(str3)) {
                        String str5 = tinkerLoadResultIfPresent.libraryDirectory + "/" + str4;
                        File file = new File(str5);
                        if (!file.exists()) {
                            continue;
                        } else if (!with.isTinkerLoadVerify() || SharePatchFileUtil.verifyFileMd5(file, (String) tinkerLoadResultIfPresent.libs.get(str4))) {
                            System.load(str5);
                            TinkerLog.i(TAG, "loadLibraryFromTinker success:" + str5, new Object[0]);
                            return true;
                        } else {
                            with.getLoadReporter().onLoadFileMd5Mismatch(file, 5);
                        }
                    }
                }
            }
        }
        return false;
    }

    public static void installNavitveLibraryABI(Context context, String str) {
        Tinker with = Tinker.with(context);
        if (with.isTinkerLoaded()) {
            TinkerLoadResult tinkerLoadResultIfPresent = with.getTinkerLoadResultIfPresent();
            if (tinkerLoadResultIfPresent.libs == null) {
                TinkerLog.i(TAG, "tinker libs is null, just return", new Object[0]);
                return;
            }
            File file = new File(tinkerLoadResultIfPresent.libraryDirectory, "lib/" + str);
            if (file.exists()) {
                ClassLoader classLoader = context.getClassLoader();
                if (classLoader == null) {
                    TinkerLog.e(TAG, "classloader is null", new Object[0]);
                    return;
                }
                TinkerLog.i(TAG, "before hack classloader:" + classLoader.toString(), new Object[0]);
                try {
                    installNativeLibraryPath(classLoader, file);
                } catch (Throwable th) {
                    TinkerLog.e(TAG, "installNativeLibraryPath fail:" + th, new Object[0]);
                }
                TinkerLog.i(TAG, "after hack classloader:" + classLoader.toString(), new Object[0]);
                return;
            }
            TinkerLog.e(TAG, "current libraryABI folder is not exist, path: %s", file.getPath());
            return;
        }
        TinkerLog.i(TAG, "tinker is not loaded, just return", new Object[0]);
    }

    private static void installNativeLibraryPath(ClassLoader classLoader, File file) throws Throwable {
        if (file == null || !file.exists()) {
            TinkerLog.e(TAG, "installNativeLibraryPath, folder %s is illegal", file);
        } else if ((VERSION.SDK_INT == 25 && VERSION.PREVIEW_SDK_INT != 0) || VERSION.SDK_INT > 25) {
            try {
                TinkerLoadLibrary$c.a(classLoader, file);
            } catch (Throwable th) {
                TinkerLog.e(TAG, "installNativeLibraryPath, v25 fail, sdk: %d, error: %s, try to fallback to V23", Integer.valueOf(VERSION.SDK_INT), th.getMessage());
                TinkerLoadLibrary$b.a(classLoader, file);
            }
        } else if (VERSION.SDK_INT >= 23) {
            try {
                TinkerLoadLibrary$b.a(classLoader, file);
            } catch (Throwable th2) {
                TinkerLog.e(TAG, "installNativeLibraryPath, v23 fail, sdk: %d, error: %s, try to fallback to V14", Integer.valueOf(VERSION.SDK_INT), th2.getMessage());
                TinkerLoadLibrary$a.a(classLoader, file);
            }
        } else if (VERSION.SDK_INT >= 14) {
            TinkerLoadLibrary$a.a(classLoader, file);
        } else {
            TinkerLoadLibrary$d.a(classLoader, file);
        }
    }
}
