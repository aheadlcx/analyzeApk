package com.tencent.tinker.loader;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Build.VERSION;
import android.os.SystemClock;
import android.util.Log;
import com.tencent.tinker.loader.app.TinkerApplication;
import com.tencent.tinker.loader.hotplug.ComponentHotplug;
import com.tencent.tinker.loader.shareutil.ShareConstants;
import com.tencent.tinker.loader.shareutil.ShareIntentUtil;
import com.tencent.tinker.loader.shareutil.SharePatchFileUtil;
import com.tencent.tinker.loader.shareutil.SharePatchInfo;
import com.tencent.tinker.loader.shareutil.ShareSecurityCheck;
import com.tencent.tinker.loader.shareutil.ShareTinkerInternals;
import java.io.File;

public class TinkerLoader extends AbstractTinkerLoader {
    private static final String TAG = "Tinker.TinkerLoader";
    private SharePatchInfo patchInfo;

    public Intent tryLoad(TinkerApplication tinkerApplication) {
        Intent intent = new Intent();
        long elapsedRealtime = SystemClock.elapsedRealtime();
        tryLoadPatchFilesInternal(tinkerApplication, intent);
        ShareIntentUtil.setIntentPatchCostTime(intent, SystemClock.elapsedRealtime() - elapsedRealtime);
        return intent;
    }

    private void tryLoadPatchFilesInternal(TinkerApplication tinkerApplication, Intent intent) {
        int tinkerFlags = tinkerApplication.getTinkerFlags();
        if (!ShareTinkerInternals.isTinkerEnabled(tinkerFlags)) {
            Log.w(TAG, "tryLoadPatchFiles: tinker is disable, just return");
            ShareIntentUtil.setIntentReturnCode(intent, -1);
        } else if (ShareTinkerInternals.isInPatchProcess(tinkerApplication)) {
            Log.w(TAG, "tryLoadPatchFiles: we don't load patch with :patch process itself, just return");
            ShareIntentUtil.setIntentReturnCode(intent, -1);
        } else {
            File patchDirectory = SharePatchFileUtil.getPatchDirectory(tinkerApplication);
            if (patchDirectory == null) {
                Log.w(TAG, "tryLoadPatchFiles:getPatchDirectory == null");
                ShareIntentUtil.setIntentReturnCode(intent, -2);
                return;
            }
            String absolutePath = patchDirectory.getAbsolutePath();
            if (patchDirectory.exists()) {
                File patchInfoFile = SharePatchFileUtil.getPatchInfoFile(absolutePath);
                if (patchInfoFile.exists()) {
                    File patchInfoLockFile = SharePatchFileUtil.getPatchInfoLockFile(absolutePath);
                    this.patchInfo = SharePatchInfo.readAndCheckPropertyWithLock(patchInfoFile, patchInfoLockFile);
                    if (this.patchInfo == null) {
                        ShareIntentUtil.setIntentReturnCode(intent, -4);
                        return;
                    }
                    String str = this.patchInfo.oldVersion;
                    String str2 = this.patchInfo.newVersion;
                    String str3 = this.patchInfo.oatDir;
                    if (str == null || str2 == null || str3 == null) {
                        Log.w(TAG, "tryLoadPatchFiles:onPatchInfoCorrupted");
                        ShareIntentUtil.setIntentReturnCode(intent, -4);
                        return;
                    }
                    intent.putExtra(ShareIntentUtil.INTENT_PATCH_OLD_VERSION, str);
                    intent.putExtra(ShareIntentUtil.INTENT_PATCH_NEW_VERSION, str2);
                    boolean isInMainProcess = ShareTinkerInternals.isInMainProcess(tinkerApplication);
                    Object obj = !str.equals(str2) ? 1 : null;
                    Object obj2 = (str3.equals(ShareConstants.CHANING_DEX_OPTIMIZE_PATH) && isInMainProcess) ? 1 : null;
                    str3 = ShareTinkerInternals.getCurrentOatMode(tinkerApplication, str3);
                    intent.putExtra(ShareIntentUtil.INTENT_PATCH_OAT_DIR, str3);
                    if (obj == null || !isInMainProcess) {
                        str2 = str;
                    }
                    if (ShareTinkerInternals.isNullOrNil(str2)) {
                        Log.w(TAG, "tryLoadPatchFiles:version is blank, wait main process to restart");
                        ShareIntentUtil.setIntentReturnCode(intent, -5);
                        return;
                    }
                    str = SharePatchFileUtil.getPatchVersionDirectory(str2);
                    if (str == null) {
                        Log.w(TAG, "tryLoadPatchFiles:patchName is null");
                        ShareIntentUtil.setIntentReturnCode(intent, -6);
                        return;
                    }
                    absolutePath = absolutePath + "/" + str;
                    File file = new File(absolutePath);
                    if (file.exists()) {
                        File file2 = new File(file.getAbsolutePath(), SharePatchFileUtil.getPatchVersionFile(str2));
                        if (SharePatchFileUtil.isLegalFile(file2)) {
                            ShareSecurityCheck shareSecurityCheck = new ShareSecurityCheck(tinkerApplication);
                            int checkTinkerPackage = ShareTinkerInternals.checkTinkerPackage(tinkerApplication, tinkerFlags, file2, shareSecurityCheck);
                            if (checkTinkerPackage != 0) {
                                Log.w(TAG, "tryLoadPatchFiles:checkTinkerPackage");
                                intent.putExtra(ShareIntentUtil.INTENT_PATCH_PACKAGE_PATCH_CHECK, checkTinkerPackage);
                                ShareIntentUtil.setIntentReturnCode(intent, -8);
                                return;
                            }
                            intent.putExtra(ShareIntentUtil.INTENT_PATCH_PACKAGE_CONFIG, shareSecurityCheck.getPackagePropertiesIfPresent());
                            boolean isTinkerEnabledForDex = ShareTinkerInternals.isTinkerEnabledForDex(tinkerFlags);
                            if (isTinkerEnabledForDex && !TinkerDexLoader.checkComplete(absolutePath, shareSecurityCheck, str3, intent)) {
                                Log.w(TAG, "tryLoadPatchFiles:dex check fail");
                                return;
                            } else if (!ShareTinkerInternals.isTinkerEnabledForNativeLib(tinkerFlags) || TinkerSoLoader.checkComplete(absolutePath, shareSecurityCheck, intent)) {
                                boolean isTinkerEnabledForResource = ShareTinkerInternals.isTinkerEnabledForResource(tinkerFlags);
                                Log.w(TAG, "tryLoadPatchFiles:isEnabledForResource:" + isTinkerEnabledForResource);
                                if (!isTinkerEnabledForResource || TinkerResourceLoader.checkComplete(tinkerApplication, absolutePath, shareSecurityCheck, intent)) {
                                    boolean z = ShareTinkerInternals.isVmArt() && ShareTinkerInternals.isSystemOTA(this.patchInfo.fingerPrint) && VERSION.SDK_INT >= 21 && !ShareTinkerInternals.isAfterAndroidO();
                                    intent.putExtra(ShareIntentUtil.INTENT_PATCH_SYSTEM_OTA, z);
                                    if ((isInMainProcess && obj != null) || obj2 != null) {
                                        this.patchInfo.oldVersion = str2;
                                        this.patchInfo.oatDir = str3;
                                        if (!SharePatchInfo.rewritePatchInfoFileWithLock(patchInfoFile, this.patchInfo, patchInfoLockFile)) {
                                            ShareIntentUtil.setIntentReturnCode(intent, -19);
                                            Log.w(TAG, "tryLoadPatchFiles:onReWritePatchInfoCorrupted");
                                            return;
                                        } else if (obj2 != null) {
                                            Log.i(TAG, "tryLoadPatchFiles:oatModeChanged, try to delete interpret optimize files");
                                            SharePatchFileUtil.deleteDir(absolutePath + "/" + ShareConstants.INTERPRET_DEX_OPTIMIZE_PATH);
                                        }
                                    }
                                    if (checkSafeModeCount(tinkerApplication)) {
                                        if (isTinkerEnabledForDex) {
                                            boolean loadTinkerJars = TinkerDexLoader.loadTinkerJars(tinkerApplication, absolutePath, str3, intent, z);
                                            if (z) {
                                                this.patchInfo.fingerPrint = Build.FINGERPRINT;
                                                this.patchInfo.oatDir = loadTinkerJars ? ShareConstants.INTERPRET_DEX_OPTIMIZE_PATH : "odex";
                                                obj2 = null;
                                                if (SharePatchInfo.rewritePatchInfoFileWithLock(patchInfoFile, this.patchInfo, patchInfoLockFile)) {
                                                    intent.putExtra(ShareIntentUtil.INTENT_PATCH_OAT_DIR, this.patchInfo.oatDir);
                                                } else {
                                                    ShareIntentUtil.setIntentReturnCode(intent, -19);
                                                    Log.w(TAG, "tryLoadPatchFiles:onReWritePatchInfoCorrupted");
                                                    return;
                                                }
                                            }
                                            if (!loadTinkerJars) {
                                                Log.w(TAG, "tryLoadPatchFiles:onPatchLoadDexesFail");
                                                return;
                                            }
                                        }
                                        if (!isTinkerEnabledForResource || TinkerResourceLoader.loadTinkerResources(tinkerApplication, absolutePath, intent)) {
                                            if (isTinkerEnabledForDex && isTinkerEnabledForResource) {
                                                ComponentHotplug.install(tinkerApplication, shareSecurityCheck);
                                            }
                                            if (obj2 != null) {
                                                ShareTinkerInternals.killAllOtherProcess(tinkerApplication);
                                                Log.i(TAG, "tryLoadPatchFiles:oatModeChanged, try to kill all other process");
                                            }
                                            ShareIntentUtil.setIntentReturnCode(intent, 0);
                                            Log.i(TAG, "tryLoadPatchFiles: load end, ok!");
                                            return;
                                        }
                                        Log.w(TAG, "tryLoadPatchFiles:onPatchLoadResourcesFail");
                                        return;
                                    }
                                    intent.putExtra(ShareIntentUtil.INTENT_PATCH_EXCEPTION, new TinkerRuntimeException("checkSafeModeCount fail"));
                                    ShareIntentUtil.setIntentReturnCode(intent, -25);
                                    Log.w(TAG, "tryLoadPatchFiles:checkSafeModeCount fail");
                                    return;
                                }
                                Log.w(TAG, "tryLoadPatchFiles:resource check fail");
                                return;
                            } else {
                                Log.w(TAG, "tryLoadPatchFiles:native lib check fail");
                                return;
                            }
                        }
                        Log.w(TAG, "tryLoadPatchFiles:onPatchVersionFileNotFound");
                        ShareIntentUtil.setIntentReturnCode(intent, -7);
                        return;
                    }
                    Log.w(TAG, "tryLoadPatchFiles:onPatchVersionDirectoryNotFound");
                    ShareIntentUtil.setIntentReturnCode(intent, -6);
                    return;
                }
                Log.w(TAG, "tryLoadPatchFiles:patch info not exist:" + patchInfoFile.getAbsolutePath());
                ShareIntentUtil.setIntentReturnCode(intent, -3);
                return;
            }
            Log.w(TAG, "tryLoadPatchFiles:patch dir not exist:" + absolutePath);
            ShareIntentUtil.setIntentReturnCode(intent, -2);
        }
    }

    private boolean checkSafeModeCount(TinkerApplication tinkerApplication) {
        String str = ShareConstants.TINKER_OWN_PREFERENCE_CONFIG + ShareTinkerInternals.getProcessName(tinkerApplication);
        SharedPreferences sharedPreferences = tinkerApplication.getSharedPreferences(str, 0);
        int i = sharedPreferences.getInt(ShareConstants.TINKER_SAFE_MODE_COUNT, 0) + 1;
        Log.w(TAG, "tinker safe mode preferName:" + str + " count:" + i);
        if (i >= 3) {
            sharedPreferences.edit().putInt(ShareConstants.TINKER_SAFE_MODE_COUNT, 0).commit();
            return false;
        }
        tinkerApplication.setUseSafeMode(true);
        sharedPreferences.edit().putInt(ShareConstants.TINKER_SAFE_MODE_COUNT, i).commit();
        return true;
    }
}
