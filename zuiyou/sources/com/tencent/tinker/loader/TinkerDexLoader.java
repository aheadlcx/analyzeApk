package com.tencent.tinker.loader;

import android.annotation.TargetApi;
import android.content.Intent;
import android.util.Log;
import com.tencent.tinker.loader.TinkerDexOptimizer.ResultCallback;
import com.tencent.tinker.loader.app.TinkerApplication;
import com.tencent.tinker.loader.shareutil.ShareConstants;
import com.tencent.tinker.loader.shareutil.ShareDexDiffPatchInfo;
import com.tencent.tinker.loader.shareutil.ShareIntentUtil;
import com.tencent.tinker.loader.shareutil.SharePatchFileUtil;
import com.tencent.tinker.loader.shareutil.ShareSecurityCheck;
import com.tencent.tinker.loader.shareutil.ShareTinkerInternals;
import dalvik.system.PathClassLoader;
import java.io.File;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;

public class TinkerDexLoader {
    private static final String DEFAULT_DEX_OPTIMIZE_PATH = "odex";
    private static final String DEX_MEAT_FILE = "assets/dex_meta.txt";
    private static final String DEX_PATH = "dex";
    private static final String INTERPRET_DEX_OPTIMIZE_PATH = "interpet";
    private static final String TAG = "Tinker.TinkerDexLoader";
    private static HashSet<ShareDexDiffPatchInfo> classNDexInfo = new HashSet();
    private static boolean isVmArt = ShareTinkerInternals.isVmArt();
    private static final ArrayList<ShareDexDiffPatchInfo> loadDexList = new ArrayList();

    private TinkerDexLoader() {
    }

    @TargetApi(14)
    public static boolean loadTinkerJars(TinkerApplication tinkerApplication, String str, String str2, Intent intent, boolean z) {
        if (loadDexList.isEmpty() && classNDexInfo.isEmpty()) {
            Log.w(TAG, "there is no dex to load");
            return true;
        }
        PathClassLoader pathClassLoader = (PathClassLoader) TinkerDexLoader.class.getClassLoader();
        if (pathClassLoader != null) {
            ShareDexDiffPatchInfo shareDexDiffPatchInfo;
            Log.i(TAG, "classloader: " + pathClassLoader.toString());
            String str3 = str + "/" + "dex" + "/";
            Object arrayList = new ArrayList();
            Iterator it = loadDexList.iterator();
            while (it.hasNext()) {
                shareDexDiffPatchInfo = (ShareDexDiffPatchInfo) it.next();
                if (!isJustArtSupportDex(shareDexDiffPatchInfo)) {
                    File file = new File(str3 + shareDexDiffPatchInfo.realName);
                    if (tinkerApplication.isTinkerLoadVerifyFlag()) {
                        long currentTimeMillis = System.currentTimeMillis();
                        if (SharePatchFileUtil.verifyDexFileMd5(file, getInfoMd5(shareDexDiffPatchInfo))) {
                            Log.i(TAG, "verify dex file:" + file.getPath() + " md5, use time: " + (System.currentTimeMillis() - currentTimeMillis));
                        } else {
                            ShareIntentUtil.setIntentReturnCode(intent, -13);
                            intent.putExtra(ShareIntentUtil.INTENT_PATCH_MISMATCH_DEX_PATH, file.getAbsolutePath());
                            return false;
                        }
                    }
                    arrayList.add(file);
                }
            }
            if (isVmArt && !classNDexInfo.isEmpty()) {
                File file2 = new File(str3 + ShareConstants.CLASS_N_APK_NAME);
                long currentTimeMillis2 = System.currentTimeMillis();
                if (tinkerApplication.isTinkerLoadVerifyFlag()) {
                    Iterator it2 = classNDexInfo.iterator();
                    while (it2.hasNext()) {
                        shareDexDiffPatchInfo = (ShareDexDiffPatchInfo) it2.next();
                        if (!SharePatchFileUtil.verifyDexFileMd5(file2, shareDexDiffPatchInfo.rawName, shareDexDiffPatchInfo.destMd5InArt)) {
                            ShareIntentUtil.setIntentReturnCode(intent, -13);
                            intent.putExtra(ShareIntentUtil.INTENT_PATCH_MISMATCH_DEX_PATH, file2.getAbsolutePath());
                            return false;
                        }
                    }
                }
                Log.i(TAG, "verify dex file:" + file2.getPath() + " md5, use time: " + (System.currentTimeMillis() - currentTimeMillis2));
                arrayList.add(file2);
            }
            File file3 = new File(str + "/" + str2);
            if (z) {
                final boolean[] zArr = new boolean[]{true};
                final Throwable[] thArr = new Throwable[1];
                try {
                    String currentInstructionSet = ShareTinkerInternals.getCurrentInstructionSet();
                    deleteOutOfDateOATFile(str);
                    Log.w(TAG, "systemOTA, try parallel oat dexes, targetISA:" + currentInstructionSet);
                    file3 = new File(str + "/" + "interpet");
                    TinkerDexOptimizer.optimizeAll(arrayList, file3, true, currentInstructionSet, new ResultCallback() {
                        long a;

                        public void onStart(File file, File file2) {
                            this.a = System.currentTimeMillis();
                            Log.i(TinkerDexLoader.TAG, "start to optimize dex:" + file.getPath());
                        }

                        public void onSuccess(File file, File file2, File file3) {
                            Log.i(TinkerDexLoader.TAG, "success to optimize dex " + file.getPath() + ", use time " + (System.currentTimeMillis() - this.a));
                        }

                        public void onFailed(File file, File file2, Throwable th) {
                            zArr[0] = false;
                            thArr[0] = th;
                            Log.i(TinkerDexLoader.TAG, "fail to optimize dex " + file.getPath() + ", use time " + (System.currentTimeMillis() - this.a));
                        }
                    });
                    if (!zArr[0]) {
                        Log.e(TAG, "parallel oat dexes failed");
                        intent.putExtra(ShareIntentUtil.INTENT_PATCH_INTERPRET_EXCEPTION, thArr[0]);
                        ShareIntentUtil.setIntentReturnCode(intent, -16);
                        return false;
                    }
                } catch (Serializable th) {
                    Log.i(TAG, "getCurrentInstructionSet fail:" + th);
                    deleteOutOfDateOATFile(str);
                    intent.putExtra(ShareIntentUtil.INTENT_PATCH_INTERPRET_EXCEPTION, th);
                    ShareIntentUtil.setIntentReturnCode(intent, -15);
                    return false;
                }
            }
            try {
                SystemClassLoaderAdder.installDexes(tinkerApplication, pathClassLoader, file3, arrayList);
                return true;
            } catch (Serializable th2) {
                Log.e(TAG, "install dexes failed");
                intent.putExtra(ShareIntentUtil.INTENT_PATCH_EXCEPTION, th2);
                ShareIntentUtil.setIntentReturnCode(intent, -14);
                return false;
            }
        }
        Log.e(TAG, "classloader is null");
        ShareIntentUtil.setIntentReturnCode(intent, -12);
        return false;
    }

    public static boolean checkComplete(String str, ShareSecurityCheck shareSecurityCheck, String str2, Intent intent) {
        String str3 = (String) shareSecurityCheck.getMetaContentMap().get("assets/dex_meta.txt");
        if (str3 == null) {
            return true;
        }
        loadDexList.clear();
        classNDexInfo.clear();
        ArrayList arrayList = new ArrayList();
        ShareDexDiffPatchInfo.parseDexDiffPatchInfo(str3, arrayList);
        if (arrayList.isEmpty()) {
            return true;
        }
        Serializable hashMap = new HashMap();
        ShareDexDiffPatchInfo shareDexDiffPatchInfo = null;
        Iterator it = arrayList.iterator();
        while (it.hasNext()) {
            ShareDexDiffPatchInfo shareDexDiffPatchInfo2 = (ShareDexDiffPatchInfo) it.next();
            if (!isJustArtSupportDex(shareDexDiffPatchInfo2)) {
                if (ShareDexDiffPatchInfo.checkDexDiffPatchInfo(shareDexDiffPatchInfo2)) {
                    if (!(isVmArt && shareDexDiffPatchInfo2.rawName.startsWith(ShareConstants.TEST_DEX_NAME))) {
                        if (isVmArt && ShareConstants.CLASS_N_PATTERN.matcher(shareDexDiffPatchInfo2.realName).matches()) {
                            classNDexInfo.add(shareDexDiffPatchInfo2);
                            shareDexDiffPatchInfo2 = shareDexDiffPatchInfo;
                        } else {
                            hashMap.put(shareDexDiffPatchInfo2.realName, getInfoMd5(shareDexDiffPatchInfo2));
                            loadDexList.add(shareDexDiffPatchInfo2);
                            shareDexDiffPatchInfo2 = shareDexDiffPatchInfo;
                        }
                    }
                    shareDexDiffPatchInfo = shareDexDiffPatchInfo2;
                } else {
                    intent.putExtra(ShareIntentUtil.INTENT_PATCH_PACKAGE_PATCH_CHECK, -3);
                    ShareIntentUtil.setIntentReturnCode(intent, -8);
                    return false;
                }
            }
        }
        if (isVmArt && !(shareDexDiffPatchInfo == null && classNDexInfo.isEmpty())) {
            if (shareDexDiffPatchInfo != null) {
                classNDexInfo.add(ShareTinkerInternals.changeTestDexToClassN(shareDexDiffPatchInfo, classNDexInfo.size() + 1));
            }
            hashMap.put(ShareConstants.CLASS_N_APK_NAME, "");
        }
        String str4 = str + "/" + "dex" + "/";
        File file = new File(str4);
        if (file.exists() && file.isDirectory()) {
            File file2 = new File(str + "/" + str2 + "/");
            for (String str32 : hashMap.keySet()) {
                File file3 = new File(str4 + str32);
                if (SharePatchFileUtil.isLegalFile(file3)) {
                    file = new File(SharePatchFileUtil.optimizedPathFor(file3, file2));
                    if (!SharePatchFileUtil.isLegalFile(file)) {
                        intent.putExtra(ShareIntentUtil.INTENT_PATCH_MISSING_DEX_PATH, file.getAbsolutePath());
                        ShareIntentUtil.setIntentReturnCode(intent, -11);
                        return false;
                    }
                }
                intent.putExtra(ShareIntentUtil.INTENT_PATCH_MISSING_DEX_PATH, file3.getAbsolutePath());
                ShareIntentUtil.setIntentReturnCode(intent, -10);
                return false;
            }
            intent.putExtra(ShareIntentUtil.INTENT_PATCH_DEXES_PATH, hashMap);
            return true;
        }
        ShareIntentUtil.setIntentReturnCode(intent, -9);
        return false;
    }

    private static String getInfoMd5(ShareDexDiffPatchInfo shareDexDiffPatchInfo) {
        return isVmArt ? shareDexDiffPatchInfo.destMd5InArt : shareDexDiffPatchInfo.destMd5InDvm;
    }

    private static void deleteOutOfDateOATFile(String str) {
        SharePatchFileUtil.deleteDir(str + "/" + "odex" + "/");
        if (ShareTinkerInternals.isAfterAndroidO()) {
            SharePatchFileUtil.deleteDir(str + "/" + "dex" + "/" + ShareConstants.ANDROID_O_DEX_OPTIMIZE_PATH + "/");
        }
    }

    private static boolean isJustArtSupportDex(ShareDexDiffPatchInfo shareDexDiffPatchInfo) {
        if (!isVmArt && shareDexDiffPatchInfo.destMd5InDvm.equals("0")) {
            return true;
        }
        return false;
    }
}
