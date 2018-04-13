package com.tencent.tinker.loader.shareutil;

import android.content.Intent;
import android.util.Log;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;

public class ShareIntentUtil {
    public static final String INTENT_PATCH_COST_TIME = "intent_patch_cost_time";
    public static final String INTENT_PATCH_DEXES_PATH = "intent_patch_dexes_path";
    public static final String INTENT_PATCH_EXCEPTION = "intent_patch_exception";
    public static final String INTENT_PATCH_INTERPRET_EXCEPTION = "intent_patch_interpret_exception";
    public static final String INTENT_PATCH_LIBS_PATH = "intent_patch_libs_path";
    public static final String INTENT_PATCH_MISMATCH_DEX_PATH = "intent_patch_mismatch_dex_path";
    public static final String INTENT_PATCH_MISMATCH_LIB_PATH = "intent_patch_mismatch_lib_path";
    public static final String INTENT_PATCH_MISSING_DEX_PATH = "intent_patch_missing_dex_path";
    public static final String INTENT_PATCH_MISSING_LIB_PATH = "intent_patch_missing_lib_path";
    public static final String INTENT_PATCH_NEW_VERSION = "intent_patch_new_version";
    public static final String INTENT_PATCH_OAT_DIR = "intent_patch_oat_dir";
    public static final String INTENT_PATCH_OLD_VERSION = "intent_patch_old_version";
    public static final String INTENT_PATCH_PACKAGE_CONFIG = "intent_patch_package_config";
    public static final String INTENT_PATCH_PACKAGE_PATCH_CHECK = "intent_patch_package_patch_check";
    public static final String INTENT_PATCH_SYSTEM_OTA = "intent_patch_system_ota";
    public static final String INTENT_RETURN_CODE = "intent_return_code";
    private static final String TAG = "ShareIntentUtil";

    public static void setIntentReturnCode(Intent intent, int i) {
        intent.putExtra(INTENT_RETURN_CODE, i);
    }

    public static int getIntentReturnCode(Intent intent) {
        return getIntExtra(intent, INTENT_RETURN_CODE, ShareConstants.ERROR_LOAD_GET_INTENT_FAIL);
    }

    public static void setIntentPatchCostTime(Intent intent, long j) {
        intent.putExtra(INTENT_PATCH_COST_TIME, j);
    }

    public static long getIntentPatchCostTime(Intent intent) {
        return intent.getLongExtra(INTENT_PATCH_COST_TIME, 0);
    }

    public static Throwable getIntentPatchException(Intent intent) {
        Serializable serializableExtra = getSerializableExtra(intent, INTENT_PATCH_EXCEPTION);
        if (serializableExtra != null) {
            return (Throwable) serializableExtra;
        }
        return null;
    }

    public static Throwable getIntentInterpretException(Intent intent) {
        Serializable serializableExtra = getSerializableExtra(intent, INTENT_PATCH_INTERPRET_EXCEPTION);
        if (serializableExtra != null) {
            return (Throwable) serializableExtra;
        }
        return null;
    }

    public static HashMap<String, String> getIntentPatchDexPaths(Intent intent) {
        Serializable serializableExtra = getSerializableExtra(intent, INTENT_PATCH_DEXES_PATH);
        if (serializableExtra != null) {
            return (HashMap) serializableExtra;
        }
        return null;
    }

    public static HashMap<String, String> getIntentPatchLibsPaths(Intent intent) {
        Serializable serializableExtra = getSerializableExtra(intent, INTENT_PATCH_LIBS_PATH);
        if (serializableExtra != null) {
            return (HashMap) serializableExtra;
        }
        return null;
    }

    public static HashMap<String, String> getIntentPackageConfig(Intent intent) {
        Serializable serializableExtra = getSerializableExtra(intent, INTENT_PATCH_PACKAGE_CONFIG);
        if (serializableExtra != null) {
            return (HashMap) serializableExtra;
        }
        return null;
    }

    public static ArrayList<String> getStringArrayListExtra(Intent intent, String str) {
        ArrayList<String> arrayList = null;
        if (intent != null) {
            try {
                arrayList = intent.getStringArrayListExtra(str);
            } catch (Exception e) {
                Log.e(TAG, "getStringExtra exception:" + e.getMessage());
            }
        }
        return arrayList;
    }

    public static String getStringExtra(Intent intent, String str) {
        String str2 = null;
        if (intent != null) {
            try {
                str2 = intent.getStringExtra(str);
            } catch (Exception e) {
                Log.e(TAG, "getStringExtra exception:" + e.getMessage());
            }
        }
        return str2;
    }

    public static Serializable getSerializableExtra(Intent intent, String str) {
        Serializable serializable = null;
        if (intent != null) {
            try {
                serializable = intent.getSerializableExtra(str);
            } catch (Exception e) {
                Log.e(TAG, "getSerializableExtra exception:" + e.getMessage());
            }
        }
        return serializable;
    }

    public static int getIntExtra(Intent intent, String str, int i) {
        if (intent != null) {
            try {
                i = intent.getIntExtra(str, i);
            } catch (Exception e) {
                Log.e(TAG, "getIntExtra exception:" + e.getMessage());
            }
        }
        return i;
    }

    public static boolean getBooleanExtra(Intent intent, String str, boolean z) {
        if (intent != null) {
            try {
                z = intent.getBooleanExtra(str, z);
            } catch (Exception e) {
                Log.e(TAG, "getBooleanExtra exception:" + e.getMessage());
            }
        }
        return z;
    }

    public static long getLongExtra(Intent intent, String str, long j) {
        if (intent != null) {
            try {
                j = intent.getLongExtra(str, j);
            } catch (Exception e) {
                Log.e(TAG, "getIntExtra exception:" + e.getMessage());
            }
        }
        return j;
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static void fixIntentClassLoader(android.content.Intent r2, java.lang.ClassLoader r3) {
        /*
        r0 = android.content.Intent.class;
        r1 = "mExtras";
        r1 = com.tencent.tinker.loader.shareutil.ShareReflectUtil.findField(r0, r1);	 Catch:{ Throwable -> 0x001d }
        r0 = r1.get(r2);	 Catch:{ Throwable -> 0x001d }
        r0 = (android.os.Bundle) r0;	 Catch:{ Throwable -> 0x001d }
        if (r0 != 0) goto L_0x0019;
    L_0x0011:
        r0 = new android.os.Bundle;	 Catch:{ Throwable -> 0x001d }
        r0.<init>();	 Catch:{ Throwable -> 0x001d }
        r1.set(r2, r0);	 Catch:{ Throwable -> 0x001d }
    L_0x0019:
        r2.setExtrasClassLoader(r3);
    L_0x001c:
        return;
    L_0x001d:
        r0 = move-exception;
        r0.printStackTrace();	 Catch:{ all -> 0x0025 }
        r2.setExtrasClassLoader(r3);
        goto L_0x001c;
    L_0x0025:
        r0 = move-exception;
        r2.setExtrasClassLoader(r3);
        throw r0;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.tencent.tinker.loader.shareutil.ShareIntentUtil.fixIntentClassLoader(android.content.Intent, java.lang.ClassLoader):void");
    }
}
