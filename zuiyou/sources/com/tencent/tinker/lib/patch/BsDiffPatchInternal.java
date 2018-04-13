package com.tencent.tinker.lib.patch;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.os.SystemClock;
import com.tencent.tinker.bsdiff.BSPatch;
import com.tencent.tinker.lib.tinker.Tinker;
import com.tencent.tinker.lib.util.TinkerLog;
import com.tencent.tinker.loader.shareutil.ShareBsDiffPatchInfo;
import com.tencent.tinker.loader.shareutil.ShareConstants;
import com.tencent.tinker.loader.shareutil.SharePatchFileUtil;
import com.tencent.tinker.loader.shareutil.ShareSecurityCheck;
import com.tencent.tinker.loader.shareutil.ShareTinkerInternals;
import java.io.Closeable;
import java.io.File;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;

public class BsDiffPatchInternal extends BasePatchInternal {
    private static final String TAG = "Tinker.BsDiffPatchInternal";

    protected static boolean tryRecoverLibraryFiles(Tinker tinker, ShareSecurityCheck shareSecurityCheck, Context context, String str, File file) {
        if (tinker.isEnabledForNativeLib()) {
            String str2 = (String) shareSecurityCheck.getMetaContentMap().get(ShareConstants.SO_META_FILE);
            if (str2 == null) {
                TinkerLog.w(TAG, "patch recover, library is not contained", new Object[0]);
                return true;
            }
            long elapsedRealtime = SystemClock.elapsedRealtime();
            elapsedRealtime = SystemClock.elapsedRealtime() - elapsedRealtime;
            TinkerLog.i(TAG, "recover lib result:%b, cost:%d", Boolean.valueOf(patchLibraryExtractViaBsDiff(context, str, str2, file)), Long.valueOf(elapsedRealtime));
            return patchLibraryExtractViaBsDiff(context, str, str2, file);
        }
        TinkerLog.w(TAG, "patch recover, library is not enabled", new Object[0]);
        return true;
    }

    private static boolean patchLibraryExtractViaBsDiff(Context context, String str, String str2, File file) {
        return extractBsDiffInternals(context, str + "/" + ShareConstants.SO_PATH + "/", str2, file, 5);
    }

    private static boolean extractBsDiffInternals(Context context, String str, String str2, File file, int i) {
        ZipFile zipFile;
        ZipFile zipFile2;
        Throwable th;
        ArrayList arrayList = new ArrayList();
        ShareBsDiffPatchInfo.parseDiffPatchInfo(str2, arrayList);
        if (arrayList.isEmpty()) {
            TinkerLog.w(TAG, "extract patch list is empty! type:%s:", ShareTinkerInternals.getTypeString(i));
            return true;
        }
        File file2 = new File(str);
        if (!file2.exists()) {
            file2.mkdirs();
        }
        Tinker with = Tinker.with(context);
        ApplicationInfo applicationInfo = context.getApplicationInfo();
        if (applicationInfo == null) {
            TinkerLog.w(TAG, "applicationInfo == null!!!!", new Object[0]);
            return false;
        }
        ZipFile zipFile3 = null;
        ZipFile zipFile4 = null;
        try {
            zipFile = new ZipFile(applicationInfo.sourceDir);
            try {
                zipFile2 = new ZipFile(file);
                Closeable closeable;
                Closeable closeable2;
                try {
                    Iterator it = arrayList.iterator();
                    while (it.hasNext()) {
                        String str3;
                        ShareBsDiffPatchInfo shareBsDiffPatchInfo = (ShareBsDiffPatchInfo) it.next();
                        long currentTimeMillis = System.currentTimeMillis();
                        if (shareBsDiffPatchInfo.path.equals("")) {
                            str3 = shareBsDiffPatchInfo.name;
                        } else {
                            str3 = shareBsDiffPatchInfo.path + "/" + shareBsDiffPatchInfo.name;
                        }
                        String str4 = shareBsDiffPatchInfo.md5;
                        if (SharePatchFileUtil.checkIfMd5Valid(str4)) {
                            File file3 = new File(str + (shareBsDiffPatchInfo.path + "/" + shareBsDiffPatchInfo.name));
                            if (!file3.exists()) {
                                file3.getParentFile().mkdirs();
                            } else if (str4.equals(SharePatchFileUtil.getMD5(file3))) {
                                TinkerLog.w(TAG, "bsdiff file %s is already exist, and md5 match, just continue", file3.getPath());
                            } else {
                                TinkerLog.w(TAG, "have a mismatch corrupted dex " + file3.getPath(), new Object[0]);
                                file3.delete();
                            }
                            String str5 = shareBsDiffPatchInfo.patchMd5;
                            ZipEntry entry = zipFile2.getEntry(str3);
                            if (entry == null) {
                                TinkerLog.w(TAG, "patch entry is null. path:" + str3, new Object[0]);
                                with.getPatchReporter().onPatchTypeExtractFail(file, file3, shareBsDiffPatchInfo.name, i);
                                SharePatchFileUtil.closeZip(zipFile);
                                SharePatchFileUtil.closeZip(zipFile2);
                                return false;
                            } else if (str5.equals("0")) {
                                if (!BasePatchInternal.extract(zipFile2, entry, file3, str4, false)) {
                                    TinkerLog.w(TAG, "Failed to extract file " + file3.getPath(), new Object[0]);
                                    with.getPatchReporter().onPatchTypeExtractFail(file, file3, shareBsDiffPatchInfo.name, i);
                                    SharePatchFileUtil.closeZip(zipFile);
                                    SharePatchFileUtil.closeZip(zipFile2);
                                    return false;
                                }
                            } else if (SharePatchFileUtil.checkIfMd5Valid(str5)) {
                                ZipEntry entry2 = zipFile.getEntry(str3);
                                if (entry2 == null) {
                                    TinkerLog.w(TAG, "apk entry is null. path:" + str3, new Object[0]);
                                    with.getPatchReporter().onPatchTypeExtractFail(file, file3, shareBsDiffPatchInfo.name, i);
                                    SharePatchFileUtil.closeZip(zipFile);
                                    SharePatchFileUtil.closeZip(zipFile2);
                                    return false;
                                }
                                str5 = shareBsDiffPatchInfo.rawCrc;
                                if (String.valueOf(entry2.getCrc()).equals(str5)) {
                                    closeable = null;
                                    closeable2 = null;
                                    closeable = zipFile.getInputStream(entry2);
                                    closeable2 = zipFile2.getInputStream(entry);
                                    BSPatch.patchFast(closeable, closeable2, file3);
                                    SharePatchFileUtil.closeQuietly(closeable);
                                    SharePatchFileUtil.closeQuietly(closeable2);
                                    if (SharePatchFileUtil.verifyFileMd5(file3, str4)) {
                                        TinkerLog.w(TAG, "success recover bsdiff file: %s, use time: %d", file3.getPath(), Long.valueOf(System.currentTimeMillis() - currentTimeMillis));
                                    } else {
                                        TinkerLog.w(TAG, "Failed to recover diff file " + file3.getPath(), new Object[0]);
                                        with.getPatchReporter().onPatchTypeExtractFail(file, file3, shareBsDiffPatchInfo.name, i);
                                        SharePatchFileUtil.safeDeleteFile(file3);
                                        SharePatchFileUtil.closeZip(zipFile);
                                        SharePatchFileUtil.closeZip(zipFile2);
                                        return false;
                                    }
                                }
                                TinkerLog.e(TAG, "apk entry %s crc is not equal, expect crc: %s, got crc: %s", str3, str5, String.valueOf(entry2.getCrc()));
                                with.getPatchReporter().onPatchTypeExtractFail(file, file3, shareBsDiffPatchInfo.name, i);
                                SharePatchFileUtil.closeZip(zipFile);
                                SharePatchFileUtil.closeZip(zipFile2);
                                return false;
                            } else {
                                TinkerLog.w(TAG, "meta file md5 mismatch, type:%s, name: %s, md5: %s", ShareTinkerInternals.getTypeString(i), shareBsDiffPatchInfo.name, str5);
                                with.getPatchReporter().onPatchPackageCheckFail(file, BasePatchInternal.getMetaCorruptedCode(i));
                                SharePatchFileUtil.closeZip(zipFile);
                                SharePatchFileUtil.closeZip(zipFile2);
                                return false;
                            }
                        }
                        TinkerLog.w(TAG, "meta file md5 mismatch, type:%s, name: %s, md5: %s", ShareTinkerInternals.getTypeString(i), shareBsDiffPatchInfo.name, shareBsDiffPatchInfo.md5);
                        with.getPatchReporter().onPatchPackageCheckFail(file, BasePatchInternal.getMetaCorruptedCode(i));
                        SharePatchFileUtil.closeZip(zipFile);
                        SharePatchFileUtil.closeZip(zipFile2);
                        return false;
                    }
                    SharePatchFileUtil.closeZip(zipFile);
                    SharePatchFileUtil.closeZip(zipFile2);
                    return true;
                } catch (Throwable th2) {
                    th = th2;
                }
            } catch (Throwable th3) {
                th = th3;
                zipFile2 = null;
                SharePatchFileUtil.closeZip(zipFile);
                SharePatchFileUtil.closeZip(zipFile2);
                throw th;
            }
        } catch (Throwable th4) {
            th = th4;
            zipFile2 = null;
            zipFile = null;
            SharePatchFileUtil.closeZip(zipFile);
            SharePatchFileUtil.closeZip(zipFile2);
            throw th;
        }
    }
}
