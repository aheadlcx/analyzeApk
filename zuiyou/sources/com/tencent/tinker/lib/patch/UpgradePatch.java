package com.tencent.tinker.lib.patch;

import android.content.Context;
import android.os.Build;
import com.tencent.tinker.lib.service.PatchResult;
import com.tencent.tinker.lib.tinker.Tinker;
import com.tencent.tinker.lib.util.TinkerLog;
import com.tencent.tinker.loader.shareutil.ShareConstants;
import com.tencent.tinker.loader.shareutil.SharePatchFileUtil;
import com.tencent.tinker.loader.shareutil.SharePatchInfo;
import com.tencent.tinker.loader.shareutil.ShareSecurityCheck;
import com.tencent.tinker.loader.shareutil.ShareTinkerInternals;
import java.io.File;
import java.io.IOException;

public class UpgradePatch extends AbstractPatch {
    private static final String TAG = "Tinker.UpgradePatch";

    public boolean tryPatch(Context context, String str, PatchResult patchResult) {
        Tinker with = Tinker.with(context);
        File file = new File(str);
        if (!with.isTinkerEnabled() || !ShareTinkerInternals.isTinkerEnableWithSharedPreferences(context)) {
            TinkerLog.e(TAG, "UpgradePatch tryPatch:patch is disabled, just return", new Object[0]);
            return false;
        } else if (SharePatchFileUtil.isLegalFile(file)) {
            ShareSecurityCheck shareSecurityCheck = new ShareSecurityCheck(context);
            int checkTinkerPackage = ShareTinkerInternals.checkTinkerPackage(context, with.getTinkerFlags(), file, shareSecurityCheck);
            if (checkTinkerPackage != 0) {
                TinkerLog.e(TAG, "UpgradePatch tryPatch:onPatchPackageCheckFail", new Object[0]);
                with.getPatchReporter().onPatchPackageCheckFail(file, checkTinkerPackage);
                return false;
            }
            String md5 = SharePatchFileUtil.getMD5(file);
            if (md5 == null) {
                TinkerLog.e(TAG, "UpgradePatch tryPatch:patch md5 is null, just return", new Object[0]);
                return false;
            }
            SharePatchInfo sharePatchInfo;
            patchResult.patchVersion = md5;
            TinkerLog.i(TAG, "UpgradePatch tryPatch:patchMd5:%s", md5);
            String absolutePath = with.getPatchDirectory().getAbsolutePath();
            File patchInfoLockFile = SharePatchFileUtil.getPatchInfoLockFile(absolutePath);
            File patchInfoFile = SharePatchFileUtil.getPatchInfoFile(absolutePath);
            SharePatchInfo readAndCheckPropertyWithLock = SharePatchInfo.readAndCheckPropertyWithLock(patchInfoFile, patchInfoLockFile);
            if (readAndCheckPropertyWithLock == null) {
                sharePatchInfo = new SharePatchInfo("", md5, Build.FINGERPRINT, "odex");
            } else if (readAndCheckPropertyWithLock.oldVersion == null || readAndCheckPropertyWithLock.newVersion == null || readAndCheckPropertyWithLock.oatDir == null) {
                TinkerLog.e(TAG, "UpgradePatch tryPatch:onPatchInfoCorrupted", new Object[0]);
                with.getPatchReporter().onPatchInfoCorrupted(file, readAndCheckPropertyWithLock.oldVersion, readAndCheckPropertyWithLock.newVersion);
                return false;
            } else if (SharePatchFileUtil.checkIfMd5Valid(md5)) {
                sharePatchInfo = new SharePatchInfo(readAndCheckPropertyWithLock.oldVersion, md5, Build.FINGERPRINT, readAndCheckPropertyWithLock.oatDir.equals(ShareConstants.INTERPRET_DEX_OPTIMIZE_PATH) ? ShareConstants.CHANING_DEX_OPTIMIZE_PATH : readAndCheckPropertyWithLock.oatDir);
            } else {
                TinkerLog.e(TAG, "UpgradePatch tryPatch:onPatchVersionCheckFail md5 %s is valid", md5);
                with.getPatchReporter().onPatchVersionCheckFail(file, readAndCheckPropertyWithLock, md5);
                return false;
            }
            String str2 = absolutePath + "/" + SharePatchFileUtil.getPatchVersionDirectory(md5);
            TinkerLog.i(TAG, "UpgradePatch tryPatch:patchVersionDirectory:%s", str2);
            File file2 = new File(str2 + "/" + SharePatchFileUtil.getPatchVersionFile(md5));
            try {
                if (!md5.equals(SharePatchFileUtil.getMD5(file2))) {
                    SharePatchFileUtil.copyFileUsingStream(file, file2);
                    TinkerLog.w(TAG, "UpgradePatch copy patch file, src file: %s size: %d, dest file: %s size:%d", file.getAbsolutePath(), Long.valueOf(file.length()), file2.getAbsolutePath(), Long.valueOf(file2.length()));
                }
                if (!DexDiffPatchInternal.tryRecoverDexFiles(with, shareSecurityCheck, context, str2, file2)) {
                    TinkerLog.e(TAG, "UpgradePatch tryPatch:new patch recover, try patch dex failed", new Object[0]);
                    return false;
                } else if (!BsDiffPatchInternal.tryRecoverLibraryFiles(with, shareSecurityCheck, context, str2, file2)) {
                    TinkerLog.e(TAG, "UpgradePatch tryPatch:new patch recover, try patch library failed", new Object[0]);
                    return false;
                } else if (!ResDiffPatchInternal.tryRecoverResourceFiles(with, shareSecurityCheck, context, str2, file2)) {
                    TinkerLog.e(TAG, "UpgradePatch tryPatch:new patch recover, try patch resource failed", new Object[0]);
                    return false;
                } else if (!DexDiffPatchInternal.waitAndCheckDexOptFile(file, with)) {
                    TinkerLog.e(TAG, "UpgradePatch tryPatch:new patch recover, check dex opt file failed", new Object[0]);
                    return false;
                } else if (SharePatchInfo.rewritePatchInfoFileWithLock(patchInfoFile, sharePatchInfo, patchInfoLockFile)) {
                    TinkerLog.w(TAG, "UpgradePatch tryPatch: done, it is ok", new Object[0]);
                    return true;
                } else {
                    TinkerLog.e(TAG, "UpgradePatch tryPatch:new patch recover, rewrite patch info failed", new Object[0]);
                    with.getPatchReporter().onPatchInfoCorrupted(file, sharePatchInfo.oldVersion, sharePatchInfo.newVersion);
                    return false;
                }
            } catch (IOException e) {
                TinkerLog.e(TAG, "UpgradePatch tryPatch:copy patch file fail from %s to %s", file.getPath(), file2.getPath());
                with.getPatchReporter().onPatchTypeExtractFail(file, file2, file.getName(), 1);
                return false;
            }
        } else {
            TinkerLog.e(TAG, "UpgradePatch tryPatch:patch file is not found, just return", new Object[0]);
            return false;
        }
    }
}
