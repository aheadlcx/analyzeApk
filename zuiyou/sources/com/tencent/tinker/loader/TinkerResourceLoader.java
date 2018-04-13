package com.tencent.tinker.loader;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import com.tencent.tinker.loader.app.TinkerApplication;
import com.tencent.tinker.loader.shareutil.ShareIntentUtil;
import com.tencent.tinker.loader.shareutil.SharePatchFileUtil;
import com.tencent.tinker.loader.shareutil.ShareResPatchInfo;
import com.tencent.tinker.loader.shareutil.ShareSecurityCheck;
import java.io.File;
import java.io.Serializable;

public class TinkerResourceLoader {
    protected static final String RESOURCE_FILE = "resources.apk";
    protected static final String RESOURCE_META_FILE = "assets/res_meta.txt";
    protected static final String RESOURCE_PATH = "res";
    private static final String TAG = "Tinker.ResourceLoader";
    private static ShareResPatchInfo resPatchInfo = new ShareResPatchInfo();

    private TinkerResourceLoader() {
    }

    public static boolean loadTinkerResources(TinkerApplication tinkerApplication, String str, Intent intent) {
        if (resPatchInfo == null || resPatchInfo.resArscMd5 == null) {
            return true;
        }
        String str2 = str + "/" + "res" + "/" + "resources.apk";
        File file = new File(str2);
        long currentTimeMillis = System.currentTimeMillis();
        if (tinkerApplication.isTinkerLoadVerifyFlag()) {
            if (SharePatchFileUtil.checkResourceArscMd5(file, resPatchInfo.resArscMd5)) {
                Log.i(TAG, "verify resource file:" + file.getPath() + " md5, use time: " + (System.currentTimeMillis() - currentTimeMillis));
            } else {
                Log.e(TAG, "Failed to load resource file, path: " + file.getPath() + ", expect md5: " + resPatchInfo.resArscMd5);
                ShareIntentUtil.setIntentReturnCode(intent, -24);
                return false;
            }
        }
        try {
            TinkerResourcePatcher.a(tinkerApplication, str2);
            Log.i(TAG, "monkeyPatchExistingResources resource file:" + str2 + ", use time: " + (System.currentTimeMillis() - currentTimeMillis));
            return true;
        } catch (Throwable th) {
            Log.e(TAG, "uninstallPatchDex failed", th);
        }
        intent.putExtra(ShareIntentUtil.INTENT_PATCH_EXCEPTION, th);
        ShareIntentUtil.setIntentReturnCode(intent, -23);
        return false;
    }

    public static boolean checkComplete(Context context, String str, ShareSecurityCheck shareSecurityCheck, Intent intent) {
        String str2 = (String) shareSecurityCheck.getMetaContentMap().get("assets/res_meta.txt");
        if (str2 == null) {
            return true;
        }
        ShareResPatchInfo.parseResPatchInfoFirstLine(str2, resPatchInfo);
        if (resPatchInfo.resArscMd5 == null) {
            return true;
        }
        if (ShareResPatchInfo.checkResPatchInfo(resPatchInfo)) {
            str2 = str + "/" + "res" + "/";
            File file = new File(str2);
            if (!file.exists() || !file.isDirectory()) {
                ShareIntentUtil.setIntentReturnCode(intent, -21);
                return false;
            } else if (SharePatchFileUtil.isLegalFile(new File(str2 + "resources.apk"))) {
                try {
                    TinkerResourcePatcher.a(context);
                    return true;
                } catch (Serializable th) {
                    Log.e(TAG, "resource hook check failed.", th);
                    intent.putExtra(ShareIntentUtil.INTENT_PATCH_EXCEPTION, th);
                    ShareIntentUtil.setIntentReturnCode(intent, -23);
                    return false;
                }
            } else {
                ShareIntentUtil.setIntentReturnCode(intent, -22);
                return false;
            }
        }
        intent.putExtra(ShareIntentUtil.INTENT_PATCH_PACKAGE_PATCH_CHECK, -8);
        ShareIntentUtil.setIntentReturnCode(intent, -8);
        return false;
    }
}
