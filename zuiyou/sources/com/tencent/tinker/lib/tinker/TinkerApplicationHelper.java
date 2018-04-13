package com.tencent.tinker.lib.tinker;

import android.content.Intent;
import com.tencent.tinker.lib.util.TinkerLog;
import com.tencent.tinker.loader.TinkerRuntimeException;
import com.tencent.tinker.loader.app.ApplicationLike;
import com.tencent.tinker.loader.shareutil.ShareConstants;
import com.tencent.tinker.loader.shareutil.ShareIntentUtil;
import com.tencent.tinker.loader.shareutil.SharePatchFileUtil;
import com.tencent.tinker.loader.shareutil.ShareTinkerInternals;
import java.io.File;
import java.util.HashMap;

public class TinkerApplicationHelper {
    private static final String TAG = "Tinker.TinkerApplicationHelper";

    public static boolean isTinkerEnableAll(ApplicationLike applicationLike) {
        if (applicationLike != null && applicationLike.getApplication() != null) {
            return ShareTinkerInternals.isTinkerEnabledAll(applicationLike.getTinkerFlags());
        }
        throw new TinkerRuntimeException("tinkerApplication is null");
    }

    public static boolean isTinkerEnableForDex(ApplicationLike applicationLike) {
        if (applicationLike != null && applicationLike.getApplication() != null) {
            return ShareTinkerInternals.isTinkerEnabledForDex(applicationLike.getTinkerFlags());
        }
        throw new TinkerRuntimeException("tinkerApplication is null");
    }

    public static boolean isTinkerEnableForNativeLib(ApplicationLike applicationLike) {
        if (applicationLike != null && applicationLike.getApplication() != null) {
            return ShareTinkerInternals.isTinkerEnabledForNativeLib(applicationLike.getTinkerFlags());
        }
        throw new TinkerRuntimeException("tinkerApplication is null");
    }

    public static boolean isTinkerEnableForResource(ApplicationLike applicationLike) {
        if (applicationLike != null && applicationLike.getApplication() != null) {
            return ShareTinkerInternals.isTinkerEnabledForResource(applicationLike.getTinkerFlags());
        }
        throw new TinkerRuntimeException("tinkerApplication is null");
    }

    public static File getTinkerPatchDirectory(ApplicationLike applicationLike) {
        if (applicationLike != null && applicationLike.getApplication() != null) {
            return SharePatchFileUtil.getPatchDirectory(applicationLike.getApplication());
        }
        throw new TinkerRuntimeException("tinkerApplication is null");
    }

    public static boolean isTinkerLoadSuccess(ApplicationLike applicationLike) {
        if (applicationLike == null || applicationLike.getApplication() == null) {
            throw new TinkerRuntimeException("tinkerApplication is null");
        }
        Intent tinkerResultIntent = applicationLike.getTinkerResultIntent();
        if (tinkerResultIntent != null && ShareIntentUtil.getIntentReturnCode(tinkerResultIntent) == 0) {
            return true;
        }
        return false;
    }

    public static HashMap<String, String> getLoadDexesAndMd5(ApplicationLike applicationLike) {
        if (applicationLike == null || applicationLike.getApplication() == null) {
            throw new TinkerRuntimeException("tinkerApplication is null");
        }
        Intent tinkerResultIntent = applicationLike.getTinkerResultIntent();
        if (tinkerResultIntent != null && ShareIntentUtil.getIntentReturnCode(tinkerResultIntent) == 0) {
            return ShareIntentUtil.getIntentPatchDexPaths(tinkerResultIntent);
        }
        return null;
    }

    public static HashMap<String, String> getLoadLibraryAndMd5(ApplicationLike applicationLike) {
        if (applicationLike == null || applicationLike.getApplication() == null) {
            throw new TinkerRuntimeException("tinkerApplication is null");
        }
        Intent tinkerResultIntent = applicationLike.getTinkerResultIntent();
        if (tinkerResultIntent != null && ShareIntentUtil.getIntentReturnCode(tinkerResultIntent) == 0) {
            return ShareIntentUtil.getIntentPatchLibsPaths(tinkerResultIntent);
        }
        return null;
    }

    public static HashMap<String, String> getPackageConfigs(ApplicationLike applicationLike) {
        if (applicationLike == null || applicationLike.getApplication() == null) {
            throw new TinkerRuntimeException("tinkerApplication is null");
        }
        Intent tinkerResultIntent = applicationLike.getTinkerResultIntent();
        if (tinkerResultIntent != null && ShareIntentUtil.getIntentReturnCode(tinkerResultIntent) == 0) {
            return ShareIntentUtil.getIntentPackageConfig(tinkerResultIntent);
        }
        return null;
    }

    public static String getCurrentVersion(ApplicationLike applicationLike) {
        if (applicationLike == null || applicationLike.getApplication() == null) {
            throw new TinkerRuntimeException("tinkerApplication is null");
        }
        Intent tinkerResultIntent = applicationLike.getTinkerResultIntent();
        if (tinkerResultIntent == null) {
            return null;
        }
        String stringExtra = ShareIntentUtil.getStringExtra(tinkerResultIntent, ShareIntentUtil.INTENT_PATCH_OLD_VERSION);
        String stringExtra2 = ShareIntentUtil.getStringExtra(tinkerResultIntent, ShareIntentUtil.INTENT_PATCH_NEW_VERSION);
        boolean isInMainProcess = ShareTinkerInternals.isInMainProcess(applicationLike.getApplication());
        if (stringExtra == null || stringExtra2 == null) {
            return null;
        }
        if (isInMainProcess) {
            return stringExtra2;
        }
        return stringExtra;
    }

    public static void cleanPatch(ApplicationLike applicationLike) {
        if (applicationLike == null || applicationLike.getApplication() == null) {
            throw new TinkerRuntimeException("tinkerApplication is null");
        }
        if (isTinkerLoadSuccess(applicationLike)) {
            TinkerLog.e(TAG, "it is not safety to clean patch when tinker is loaded, you should kill all your process after clean!", new Object[0]);
        }
        SharePatchFileUtil.deleteDir(SharePatchFileUtil.getPatchDirectory(applicationLike.getApplication()));
    }

    public static void loadArmV7aLibrary(ApplicationLike applicationLike, String str) {
        if (str == null || str.isEmpty() || applicationLike == null) {
            throw new TinkerRuntimeException("libName or context is null!");
        } else if (!isTinkerEnableForNativeLib(applicationLike) || !loadLibraryFromTinker(applicationLike, "lib/armeabi-v7a", str)) {
            System.loadLibrary(str);
        }
    }

    public static void loadArmLibrary(ApplicationLike applicationLike, String str) {
        if (str == null || str.isEmpty() || applicationLike == null) {
            throw new TinkerRuntimeException("libName or context is null!");
        } else if (!isTinkerEnableForNativeLib(applicationLike) || !loadLibraryFromTinker(applicationLike, "lib/armeabi", str)) {
            System.loadLibrary(str);
        }
    }

    public static boolean loadLibraryFromTinker(ApplicationLike applicationLike, String str, String str2) throws UnsatisfiedLinkError {
        if (!str2.startsWith(ShareConstants.SO_PATH)) {
            str2 = ShareConstants.SO_PATH + str2;
        }
        if (!str2.endsWith(".so")) {
            str2 = str2 + ".so";
        }
        String str3 = str + "/" + str2;
        if (isTinkerEnableForNativeLib(applicationLike) && isTinkerLoadSuccess(applicationLike)) {
            HashMap loadLibraryAndMd5 = getLoadLibraryAndMd5(applicationLike);
            if (loadLibraryAndMd5 != null) {
                String currentVersion = getCurrentVersion(applicationLike);
                if (ShareTinkerInternals.isNullOrNil(currentVersion)) {
                    return false;
                }
                File patchDirectory = SharePatchFileUtil.getPatchDirectory(applicationLike.getApplication());
                if (patchDirectory == null) {
                    return false;
                }
                String str4 = new File(patchDirectory.getAbsolutePath() + "/" + SharePatchFileUtil.getPatchVersionDirectory(currentVersion)).getAbsolutePath() + "/" + ShareConstants.SO_PATH;
                for (String currentVersion2 : loadLibraryAndMd5.keySet()) {
                    if (currentVersion2.equals(str3)) {
                        String str5 = str4 + "/" + currentVersion2;
                        File file = new File(str5);
                        if (!file.exists()) {
                            continue;
                        } else if (!applicationLike.getTinkerLoadVerifyFlag() || SharePatchFileUtil.verifyFileMd5(file, (String) loadLibraryAndMd5.get(currentVersion2))) {
                            System.load(str5);
                            TinkerLog.i(TAG, "loadLibraryFromTinker success:" + str5, new Object[0]);
                            return true;
                        } else {
                            TinkerLog.i(TAG, "loadLibraryFromTinker md5mismatch fail:" + str5, new Object[0]);
                        }
                    }
                }
            }
        }
        return false;
    }
}
