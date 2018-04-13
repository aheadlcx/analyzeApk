package com.tencent.tinker.lib.patch;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.os.Build.VERSION;
import android.os.SystemClock;
import com.tencent.tinker.commons.dexpatcher.DexPatchApplier;
import com.tencent.tinker.lib.tinker.Tinker;
import com.tencent.tinker.lib.util.TinkerLog;
import com.tencent.tinker.loader.TinkerDexOptimizer;
import com.tencent.tinker.loader.TinkerRuntimeException;
import com.tencent.tinker.loader.shareutil.ShareConstants;
import com.tencent.tinker.loader.shareutil.ShareDexDiffPatchInfo;
import com.tencent.tinker.loader.shareutil.ShareElfFile;
import com.tencent.tinker.loader.shareutil.SharePatchFileUtil;
import com.tencent.tinker.loader.shareutil.ShareSecurityCheck;
import com.tencent.tinker.loader.shareutil.ShareTinkerInternals;
import com.tencent.tinker.ziputils.ziputil.TinkerZipEntry;
import com.tencent.tinker.ziputils.ziputil.TinkerZipFile;
import com.tencent.tinker.ziputils.ziputil.TinkerZipOutputStream;
import com.tencent.tinker.ziputils.ziputil.TinkerZipUtil;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.Closeable;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Vector;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;
import java.util.zip.ZipInputStream;
import java.util.zip.ZipOutputStream;

public class DexDiffPatchInternal extends BasePatchInternal {
    protected static final int MAX_WAIT_COUNT = 30;
    protected static final String TAG = "Tinker.DexDiffPatchInternal";
    protected static final int WAIT_ASYN_OAT_TIME = 15000;
    private static HashMap<ShareDexDiffPatchInfo, File> classNDexInfo = new HashMap();
    private static boolean isVmArt = ShareTinkerInternals.isVmArt();
    private static ArrayList<File> optFiles = new ArrayList();
    private static ArrayList<ShareDexDiffPatchInfo> patchList = new ArrayList();

    protected static boolean tryRecoverDexFiles(Tinker tinker, ShareSecurityCheck shareSecurityCheck, Context context, String str, File file) {
        if (tinker.isEnabledForDex()) {
            String str2 = (String) shareSecurityCheck.getMetaContentMap().get(ShareConstants.DEX_META_FILE);
            if (str2 == null) {
                TinkerLog.w(TAG, "patch recover, dex is not contained", new Object[0]);
                return true;
            }
            long elapsedRealtime = SystemClock.elapsedRealtime();
            elapsedRealtime = SystemClock.elapsedRealtime() - elapsedRealtime;
            TinkerLog.i(TAG, "recover dex result:%b, cost:%d", Boolean.valueOf(patchDexExtractViaDexDiff(context, str, str2, file)), Long.valueOf(elapsedRealtime));
            return patchDexExtractViaDexDiff(context, str, str2, file);
        }
        TinkerLog.w(TAG, "patch recover, dex is not enabled", new Object[0]);
        return true;
    }

    protected static boolean waitAndCheckDexOptFile(File file, Tinker tinker) {
        int i = 30;
        if (optFiles.isEmpty()) {
            return true;
        }
        int size = patchList.size() * 8;
        if (size <= 30) {
            i = size;
        }
        TinkerLog.i(TAG, "raw dex count: %d, dex opt dex count: %d, final wait times: %d", Integer.valueOf(patchList.size()), Integer.valueOf(optFiles.size()), Integer.valueOf(i));
        for (size = 0; size < i; size++) {
            if (!checkAllDexOptFile(optFiles, size + 1)) {
                try {
                    Thread.sleep(15000);
                } catch (InterruptedException e) {
                    TinkerLog.e(TAG, "thread sleep InterruptedException e:" + e, new Object[0]);
                }
            }
        }
        List arrayList = new ArrayList();
        Iterator it = optFiles.iterator();
        while (it.hasNext()) {
            File file2 = (File) it.next();
            TinkerLog.i(TAG, "check dex optimizer file exist: %s, size %d", file2.getPath(), Long.valueOf(file2.length()));
            if (!SharePatchFileUtil.isLegalFile(file2)) {
                TinkerLog.e(TAG, "final parallel dex optimizer file %s is not exist, return false", file2.getName());
                arrayList.add(file2);
            }
        }
        if (arrayList.isEmpty()) {
            if (VERSION.SDK_INT >= 21) {
                Iterator it2 = optFiles.iterator();
                Throwable th = null;
                while (it2.hasNext()) {
                    file2 = (File) it2.next();
                    TinkerLog.i(TAG, "check dex optimizer file format: %s, size %d", file2.getName(), Long.valueOf(file2.length()));
                    try {
                        Throwable th2;
                        if (ShareElfFile.getFileTypeByMagic(file2) == 1) {
                            ShareElfFile shareElfFile = null;
                            try {
                                ShareElfFile shareElfFile2 = new ShareElfFile(file2);
                                if (shareElfFile2 != null) {
                                    try {
                                        shareElfFile2.close();
                                        th2 = th;
                                    } catch (IOException e2) {
                                        th2 = th;
                                    }
                                    th = th2;
                                }
                            } catch (Throwable th3) {
                                if (null != null) {
                                    try {
                                        shareElfFile.close();
                                    } catch (IOException e3) {
                                    }
                                }
                            }
                        }
                        th2 = th;
                        th = th2;
                    } catch (IOException e4) {
                    }
                }
                if (!arrayList.isEmpty()) {
                    tinker.getPatchReporter().onPatchDexOptFail(file, arrayList, th == null ? new TinkerRuntimeException(ShareConstants.CHECK_DEX_OAT_FORMAT_FAIL) : new TinkerRuntimeException(ShareConstants.CHECK_DEX_OAT_FORMAT_FAIL, th));
                    return false;
                }
            }
            return true;
        }
        tinker.getPatchReporter().onPatchDexOptFail(file, arrayList, new TinkerRuntimeException(ShareConstants.CHECK_DEX_OAT_EXIST_FAIL));
        return false;
    }

    private static boolean patchDexExtractViaDexDiff(Context context, String str, String str2, File file) {
        int i = 0;
        String str3 = str + "/" + "dex" + "/";
        if (extractDexDiffInternals(context, str3, str2, file, 3)) {
            File[] listFiles = new File(str3).listFiles();
            List arrayList = new ArrayList();
            if (listFiles != null) {
                int length = listFiles.length;
                while (i < length) {
                    File file2 = listFiles[i];
                    if (file2.isFile()) {
                        arrayList.add(file2);
                    }
                    i++;
                }
            }
            return dexOptimizeDexFiles(context, arrayList, str + "/" + "odex" + "/", file);
        }
        TinkerLog.w(TAG, "patch recover, extractDiffInternals fail", new Object[0]);
        return false;
    }

    private static boolean checkClassNDexFiles(String str) {
        File file = null;
        if (patchList.isEmpty() || !isVmArt) {
            return false;
        }
        File file2;
        boolean z;
        Iterator it = patchList.iterator();
        ShareDexDiffPatchInfo shareDexDiffPatchInfo = null;
        while (it.hasNext()) {
            ShareDexDiffPatchInfo shareDexDiffPatchInfo2;
            ShareDexDiffPatchInfo shareDexDiffPatchInfo3 = (ShareDexDiffPatchInfo) it.next();
            File file3 = new File(str + shareDexDiffPatchInfo3.realName);
            if (ShareConstants.CLASS_N_PATTERN.matcher(file3.getName()).matches()) {
                classNDexInfo.put(shareDexDiffPatchInfo3, file3);
            }
            if (shareDexDiffPatchInfo3.rawName.startsWith(ShareConstants.TEST_DEX_NAME)) {
                File file4 = file3;
                shareDexDiffPatchInfo2 = shareDexDiffPatchInfo3;
                file2 = file4;
            } else {
                file2 = file;
                shareDexDiffPatchInfo2 = shareDexDiffPatchInfo;
            }
            file = file2;
            shareDexDiffPatchInfo = shareDexDiffPatchInfo2;
        }
        if (shareDexDiffPatchInfo != null) {
            classNDexInfo.put(ShareTinkerInternals.changeTestDexToClassN(shareDexDiffPatchInfo, classNDexInfo.size() + 1), file);
        }
        file = new File(str, ShareConstants.CLASS_N_APK_NAME);
        if (file.exists()) {
            for (ShareDexDiffPatchInfo shareDexDiffPatchInfo32 : classNDexInfo.keySet()) {
                if (!SharePatchFileUtil.verifyDexFileMd5(file, shareDexDiffPatchInfo32.rawName, shareDexDiffPatchInfo32.destMd5InArt)) {
                    TinkerLog.e(TAG, "verify dex file md5 error, entry name; %s, file len: %d", shareDexDiffPatchInfo32.rawName, Long.valueOf(file.length()));
                    z = false;
                    break;
                }
            }
            z = true;
            if (!z) {
                SharePatchFileUtil.safeDeleteFile(file);
            }
        } else {
            z = false;
        }
        if (z) {
            for (File file22 : classNDexInfo.values()) {
                SharePatchFileUtil.safeDeleteFile(file22);
            }
        }
        return z;
    }

    private static boolean mergeClassNDexFiles(Context context, File file, String str) {
        Closeable tinkerZipOutputStream;
        Closeable inputStream;
        Throwable th;
        if (patchList.isEmpty() || !isVmArt) {
            return true;
        }
        File file2 = new File(str, ShareConstants.CLASS_N_APK_NAME);
        if (classNDexInfo.isEmpty()) {
            TinkerLog.w(TAG, "classNDexInfo size: %d, no need to merge classN dex files", Integer.valueOf(classNDexInfo.size()));
            return true;
        }
        boolean z;
        long currentTimeMillis = System.currentTimeMillis();
        Closeable closeable = null;
        try {
            tinkerZipOutputStream = new TinkerZipOutputStream(new BufferedOutputStream(new FileOutputStream(file2)));
            try {
                for (ShareDexDiffPatchInfo shareDexDiffPatchInfo : classNDexInfo.keySet()) {
                    File file3 = (File) classNDexInfo.get(shareDexDiffPatchInfo);
                    if (shareDexDiffPatchInfo.isJarMode) {
                        TinkerZipFile tinkerZipFile = new TinkerZipFile(file3);
                        TinkerZipEntry entry = tinkerZipFile.getEntry("classes.dex");
                        TinkerZipEntry tinkerZipEntry = new TinkerZipEntry(entry, shareDexDiffPatchInfo.rawName);
                        inputStream = tinkerZipFile.getInputStream(entry);
                        TinkerZipUtil.extractTinkerEntry(tinkerZipEntry, inputStream, tinkerZipOutputStream);
                        SharePatchFileUtil.closeQuietly(inputStream);
                    } else {
                        TinkerZipUtil.extractLargeModifyFile(new TinkerZipEntry(shareDexDiffPatchInfo.rawName), file3, Long.parseLong(shareDexDiffPatchInfo.newDexCrC), tinkerZipOutputStream);
                    }
                }
                SharePatchFileUtil.closeQuietly(tinkerZipOutputStream);
                z = true;
            } catch (Throwable th2) {
                th = th2;
            }
        } catch (Throwable th3) {
            th = th3;
            tinkerZipOutputStream = null;
            SharePatchFileUtil.closeQuietly(tinkerZipOutputStream);
            throw th;
        }
        if (z) {
            for (ShareDexDiffPatchInfo shareDexDiffPatchInfo2 : classNDexInfo.keySet()) {
                if (SharePatchFileUtil.verifyDexFileMd5(file2, shareDexDiffPatchInfo2.rawName, shareDexDiffPatchInfo2.destMd5InArt)) {
                    TinkerLog.e(TAG, "verify dex file md5 error, entry name; %s, file len: %d", shareDexDiffPatchInfo2.rawName, Long.valueOf(file2.length()));
                    z = false;
                    break;
                }
            }
        }
        if (z) {
            while (r3.hasNext()) {
                SharePatchFileUtil.safeDeleteFile(r0);
            }
        } else {
            TinkerLog.e(TAG, "merge classN dex error, try delete temp file", new Object[0]);
            SharePatchFileUtil.safeDeleteFile(file2);
            Tinker.with(context).getPatchReporter().onPatchTypeExtractFail(file, file2, file2.getName(), 7);
        }
        TinkerLog.i(TAG, "merge classN dex file %s, result: %b, size: %d, use: %dms", file2.getPath(), Boolean.valueOf(z), Long.valueOf(file2.length()), Long.valueOf(System.currentTimeMillis() - currentTimeMillis));
        return z;
    }

    private static boolean dexOptimizeDexFiles(Context context, List<File> list, String str, File file) {
        Tinker with = Tinker.with(context);
        optFiles.clear();
        if (list != null) {
            File file2 = new File(str);
            if (file2.exists() || file2.mkdirs()) {
                for (File optimizedPathFor : list) {
                    optFiles.add(new File(SharePatchFileUtil.optimizedPathFor(optimizedPathFor, file2)));
                }
                TinkerLog.i(TAG, "patch recover, try to optimize dex file count:%d, optimizeDexDirectory:%s", Integer.valueOf(list.size()), str);
                List vector = new Vector();
                Throwable[] thArr = new Throwable[1];
                TinkerDexOptimizer.optimizeAll(list, file2, new DexDiffPatchInternal$1(vector, thArr));
                if (!vector.isEmpty()) {
                    with.getPatchReporter().onPatchDexOptFail(file, vector, thArr[0]);
                    return false;
                }
            }
            TinkerLog.w(TAG, "patch recover, make optimizeDexDirectoryFile fail", new Object[0]);
            return false;
        }
        return true;
    }

    private static boolean checkAllDexOptFile(ArrayList<File> arrayList, int i) {
        Iterator it = arrayList.iterator();
        while (it.hasNext()) {
            if (!SharePatchFileUtil.isLegalFile((File) it.next())) {
                TinkerLog.e(TAG, "parallel dex optimizer file %s is not exist, just wait %d times", ((File) it.next()).getName(), Integer.valueOf(i));
                return false;
            }
        }
        return true;
    }

    private static boolean extractDexDiffInternals(Context context, String str, String str2, File file, int i) {
        Throwable th;
        ZipFile zipFile;
        Throwable th2;
        patchList.clear();
        ShareDexDiffPatchInfo.parseDexDiffPatchInfo(str2, patchList);
        if (patchList.isEmpty()) {
            TinkerLog.w(TAG, "extract patch list is empty! type:%s:", ShareTinkerInternals.getTypeString(i));
            return true;
        }
        File file2 = new File(str);
        if (!file2.exists()) {
            file2.mkdirs();
        }
        Tinker with = Tinker.with(context);
        ZipFile zipFile2;
        try {
            ApplicationInfo applicationInfo = context.getApplicationInfo();
            if (applicationInfo == null) {
                TinkerLog.w(TAG, "applicationInfo == null!!!!", new Object[0]);
                SharePatchFileUtil.closeZip(null);
                SharePatchFileUtil.closeZip(null);
                return false;
            }
            ZipFile zipFile3 = new ZipFile(applicationInfo.sourceDir);
            try {
                zipFile2 = new ZipFile(file);
                try {
                    if (checkClassNDexFiles(str)) {
                        TinkerLog.w(TAG, "class n dex file %s is already exist, and md5 match, just continue", ShareConstants.CLASS_N_APK_NAME);
                        SharePatchFileUtil.closeZip(zipFile3);
                        SharePatchFileUtil.closeZip(zipFile2);
                        return true;
                    }
                    Iterator it = patchList.iterator();
                    while (it.hasNext()) {
                        String str3;
                        ShareDexDiffPatchInfo shareDexDiffPatchInfo = (ShareDexDiffPatchInfo) it.next();
                        long currentTimeMillis = System.currentTimeMillis();
                        if (shareDexDiffPatchInfo.path.equals("")) {
                            str3 = shareDexDiffPatchInfo.rawName;
                        } else {
                            str3 = shareDexDiffPatchInfo.path + "/" + shareDexDiffPatchInfo.rawName;
                        }
                        String str4 = shareDexDiffPatchInfo.dexDiffMd5;
                        String str5 = shareDexDiffPatchInfo.oldDexCrC;
                        if (isVmArt || !shareDexDiffPatchInfo.destMd5InDvm.equals("0")) {
                            String str6 = isVmArt ? shareDexDiffPatchInfo.destMd5InArt : shareDexDiffPatchInfo.destMd5InDvm;
                            if (SharePatchFileUtil.checkIfMd5Valid(str6)) {
                                File file3 = new File(str + shareDexDiffPatchInfo.realName);
                                if (!file3.exists()) {
                                    file3.getParentFile().mkdirs();
                                } else if (SharePatchFileUtil.verifyDexFileMd5(file3, str6)) {
                                    TinkerLog.w(TAG, "dex file %s is already exist, and md5 match, just continue", file3.getPath());
                                } else {
                                    TinkerLog.w(TAG, "have a mismatch corrupted dex " + file3.getPath(), new Object[0]);
                                    file3.delete();
                                }
                                ZipEntry entry = zipFile2.getEntry(str3);
                                ZipEntry entry2 = zipFile3.getEntry(str3);
                                if (str5.equals("0")) {
                                    if (entry == null) {
                                        TinkerLog.w(TAG, "patch entry is null. path:" + str3, new Object[0]);
                                        with.getPatchReporter().onPatchTypeExtractFail(file, file3, shareDexDiffPatchInfo.rawName, i);
                                        SharePatchFileUtil.closeZip(zipFile3);
                                        SharePatchFileUtil.closeZip(zipFile2);
                                        return false;
                                    } else if (!extractDexFile(zipFile2, entry, file3, shareDexDiffPatchInfo)) {
                                        TinkerLog.w(TAG, "Failed to extract raw patch file " + file3.getPath(), new Object[0]);
                                        with.getPatchReporter().onPatchTypeExtractFail(file, file3, shareDexDiffPatchInfo.rawName, i);
                                        SharePatchFileUtil.closeZip(zipFile3);
                                        SharePatchFileUtil.closeZip(zipFile2);
                                        return false;
                                    }
                                } else if (str4.equals("0")) {
                                    if (!isVmArt) {
                                        continue;
                                    } else if (entry2 == null) {
                                        TinkerLog.w(TAG, "apk entry is null. path:" + str3, new Object[0]);
                                        with.getPatchReporter().onPatchTypeExtractFail(file, file3, shareDexDiffPatchInfo.rawName, i);
                                        SharePatchFileUtil.closeZip(zipFile3);
                                        SharePatchFileUtil.closeZip(zipFile2);
                                        return false;
                                    } else {
                                        if (String.valueOf(entry2.getCrc()).equals(str5)) {
                                            extractDexFile(zipFile3, entry2, file3, shareDexDiffPatchInfo);
                                            if (!SharePatchFileUtil.verifyDexFileMd5(file3, str6)) {
                                                TinkerLog.w(TAG, "Failed to recover dex file when verify patched dex: " + file3.getPath(), new Object[0]);
                                                with.getPatchReporter().onPatchTypeExtractFail(file, file3, shareDexDiffPatchInfo.rawName, i);
                                                SharePatchFileUtil.safeDeleteFile(file3);
                                                SharePatchFileUtil.closeZip(zipFile3);
                                                SharePatchFileUtil.closeZip(zipFile2);
                                                return false;
                                            }
                                        } else {
                                            TinkerLog.e(TAG, "apk entry %s crc is not equal, expect crc: %s, got crc: %s", str3, str5, String.valueOf(entry2.getCrc()));
                                            with.getPatchReporter().onPatchTypeExtractFail(file, file3, shareDexDiffPatchInfo.rawName, i);
                                            SharePatchFileUtil.closeZip(zipFile3);
                                            SharePatchFileUtil.closeZip(zipFile2);
                                            return false;
                                        }
                                    }
                                } else if (entry == null) {
                                    TinkerLog.w(TAG, "patch entry is null. path:" + str3, new Object[0]);
                                    with.getPatchReporter().onPatchTypeExtractFail(file, file3, shareDexDiffPatchInfo.rawName, i);
                                    SharePatchFileUtil.closeZip(zipFile3);
                                    SharePatchFileUtil.closeZip(zipFile2);
                                    return false;
                                } else if (!SharePatchFileUtil.checkIfMd5Valid(str4)) {
                                    TinkerLog.w(TAG, "meta file md5 invalid, type:%s, name: %s, md5: %s", ShareTinkerInternals.getTypeString(i), shareDexDiffPatchInfo.rawName, str4);
                                    with.getPatchReporter().onPatchPackageCheckFail(file, BasePatchInternal.getMetaCorruptedCode(i));
                                    SharePatchFileUtil.closeZip(zipFile3);
                                    SharePatchFileUtil.closeZip(zipFile2);
                                    return false;
                                } else if (entry2 == null) {
                                    TinkerLog.w(TAG, "apk entry is null. path:" + str3, new Object[0]);
                                    with.getPatchReporter().onPatchTypeExtractFail(file, file3, shareDexDiffPatchInfo.rawName, i);
                                    SharePatchFileUtil.closeZip(zipFile3);
                                    SharePatchFileUtil.closeZip(zipFile2);
                                    return false;
                                } else {
                                    if (String.valueOf(entry2.getCrc()).equals(str5)) {
                                        patchDexFile(zipFile3, zipFile2, entry2, entry, shareDexDiffPatchInfo, file3);
                                        if (SharePatchFileUtil.verifyDexFileMd5(file3, str6)) {
                                            TinkerLog.w(TAG, "success recover dex file: %s, size: %d, use time: %d", file3.getPath(), Long.valueOf(file3.length()), Long.valueOf(System.currentTimeMillis() - currentTimeMillis));
                                        } else {
                                            TinkerLog.w(TAG, "Failed to recover dex file when verify patched dex: " + file3.getPath(), new Object[0]);
                                            with.getPatchReporter().onPatchTypeExtractFail(file, file3, shareDexDiffPatchInfo.rawName, i);
                                            SharePatchFileUtil.safeDeleteFile(file3);
                                            SharePatchFileUtil.closeZip(zipFile3);
                                            SharePatchFileUtil.closeZip(zipFile2);
                                            return false;
                                        }
                                    }
                                    TinkerLog.e(TAG, "apk entry %s crc is not equal, expect crc: %s, got crc: %s", str3, str5, String.valueOf(entry2.getCrc()));
                                    with.getPatchReporter().onPatchTypeExtractFail(file, file3, shareDexDiffPatchInfo.rawName, i);
                                    SharePatchFileUtil.closeZip(zipFile3);
                                    SharePatchFileUtil.closeZip(zipFile2);
                                    return false;
                                }
                            }
                            TinkerLog.w(TAG, "meta file md5 invalid, type:%s, name: %s, md5: %s", ShareTinkerInternals.getTypeString(i), shareDexDiffPatchInfo.rawName, str6);
                            with.getPatchReporter().onPatchPackageCheckFail(file, BasePatchInternal.getMetaCorruptedCode(i));
                            SharePatchFileUtil.closeZip(zipFile3);
                            SharePatchFileUtil.closeZip(zipFile2);
                            return false;
                        }
                        TinkerLog.w(TAG, "patch dex %s is only for art, just continue", str3);
                    }
                    if (mergeClassNDexFiles(context, file, str)) {
                        SharePatchFileUtil.closeZip(zipFile3);
                        SharePatchFileUtil.closeZip(zipFile2);
                        return true;
                    }
                    SharePatchFileUtil.closeZip(zipFile3);
                    SharePatchFileUtil.closeZip(zipFile2);
                    return false;
                } catch (Throwable th3) {
                    th = th3;
                    zipFile = zipFile3;
                    th2 = th;
                }
            } catch (Throwable th4) {
                th = th4;
                zipFile2 = null;
                zipFile = zipFile3;
                th2 = th;
                SharePatchFileUtil.closeZip(zipFile);
                SharePatchFileUtil.closeZip(zipFile2);
                throw th2;
            }
        } catch (Throwable th5) {
            th2 = th5;
            zipFile = null;
            zipFile2 = null;
            SharePatchFileUtil.closeZip(zipFile);
            SharePatchFileUtil.closeZip(zipFile2);
            throw th2;
        }
    }

    private static boolean extractDexToJar(ZipFile zipFile, ZipEntry zipEntry, File file, String str) throws IOException {
        Throwable th;
        Closeable closeable = null;
        boolean z = false;
        int i = 0;
        while (i < 2 && !z) {
            Closeable bufferedInputStream;
            int i2 = i + 1;
            OutputStream fileOutputStream = new FileOutputStream(file);
            InputStream inputStream = zipFile.getInputStream(zipEntry);
            TinkerLog.i(TAG, "try Extracting " + file.getPath(), new Object[0]);
            try {
                Closeable zipOutputStream = new ZipOutputStream(new BufferedOutputStream(fileOutputStream));
                try {
                    bufferedInputStream = new BufferedInputStream(inputStream);
                    try {
                        byte[] bArr = new byte[16384];
                        zipOutputStream.putNextEntry(new ZipEntry("classes.dex"));
                        for (int read = bufferedInputStream.read(bArr); read != -1; read = bufferedInputStream.read(bArr)) {
                            zipOutputStream.write(bArr, 0, read);
                        }
                        zipOutputStream.closeEntry();
                        SharePatchFileUtil.closeQuietly(bufferedInputStream);
                        SharePatchFileUtil.closeQuietly(zipOutputStream);
                        z = SharePatchFileUtil.verifyDexFileMd5(file, str);
                        TinkerLog.i(TAG, "isExtractionSuccessful: %b", Boolean.valueOf(z));
                        if (!z) {
                            file.delete();
                            if (file.exists()) {
                                TinkerLog.e(TAG, "Failed to delete corrupted dex " + file.getPath(), new Object[0]);
                            }
                        }
                        i = i2;
                    } catch (Throwable th2) {
                        th = th2;
                        closeable = zipOutputStream;
                    }
                } catch (Throwable th3) {
                    th = th3;
                    bufferedInputStream = null;
                    closeable = zipOutputStream;
                }
            } catch (Throwable th4) {
                th = th4;
                bufferedInputStream = null;
            }
        }
        return z;
        SharePatchFileUtil.closeQuietly(bufferedInputStream);
        SharePatchFileUtil.closeQuietly(closeable);
        throw th;
    }

    private static boolean extractDexFile(ZipFile zipFile, ZipEntry zipEntry, File file, ShareDexDiffPatchInfo shareDexDiffPatchInfo) throws IOException {
        String str = isVmArt ? shareDexDiffPatchInfo.destMd5InArt : shareDexDiffPatchInfo.destMd5InDvm;
        String str2 = shareDexDiffPatchInfo.rawName;
        boolean z = shareDexDiffPatchInfo.isJarMode;
        if (SharePatchFileUtil.isRawDexFile(str2) && z) {
            return extractDexToJar(zipFile, zipEntry, file, str);
        }
        return BasePatchInternal.extract(zipFile, zipEntry, file, str, true);
    }

    private static void patchDexFile(ZipFile zipFile, ZipFile zipFile2, ZipEntry zipEntry, ZipEntry zipEntry2, ShareDexDiffPatchInfo shareDexDiffPatchInfo, File file) throws IOException {
        Throwable th;
        Closeable closeable = null;
        Closeable closeable2;
        try {
            Closeable bufferedInputStream;
            Closeable bufferedInputStream2 = new BufferedInputStream(zipFile.getInputStream(zipEntry));
            if (zipEntry2 != null) {
                try {
                    bufferedInputStream = new BufferedInputStream(zipFile2.getInputStream(zipEntry2));
                } catch (Throwable th2) {
                    th = th2;
                    closeable2 = bufferedInputStream2;
                    SharePatchFileUtil.closeQuietly(closeable2);
                    SharePatchFileUtil.closeQuietly(closeable);
                    throw th;
                }
            }
            bufferedInputStream = null;
            try {
                boolean isRawDexFile = SharePatchFileUtil.isRawDexFile(shareDexDiffPatchInfo.rawName);
                if (!isRawDexFile || shareDexDiffPatchInfo.isJarMode) {
                    try {
                        Closeable zipOutputStream = new ZipOutputStream(new BufferedOutputStream(new FileOutputStream(file)));
                        try {
                            zipOutputStream.putNextEntry(new ZipEntry("classes.dex"));
                            if (isRawDexFile) {
                                new DexPatchApplier(bufferedInputStream2, bufferedInputStream).executeAndSaveTo(zipOutputStream);
                            } else {
                                try {
                                    ZipEntry nextEntry;
                                    closeable2 = new ZipInputStream(bufferedInputStream2);
                                    do {
                                        try {
                                            nextEntry = closeable2.getNextEntry();
                                            if (nextEntry == null) {
                                                break;
                                            }
                                        } catch (Throwable th3) {
                                            th = th3;
                                        }
                                    } while (!"classes.dex".equals(nextEntry.getName()));
                                    if (nextEntry == null) {
                                        throw new TinkerRuntimeException("can't recognize zip dex format file:" + file.getAbsolutePath());
                                    }
                                    new DexPatchApplier(closeable2, bufferedInputStream).executeAndSaveTo(zipOutputStream);
                                    SharePatchFileUtil.closeQuietly(closeable2);
                                } catch (Throwable th4) {
                                    th = th4;
                                    closeable2 = null;
                                    SharePatchFileUtil.closeQuietly(closeable2);
                                    throw th;
                                }
                            }
                            zipOutputStream.closeEntry();
                            SharePatchFileUtil.closeQuietly(zipOutputStream);
                        } catch (Throwable th5) {
                            th = th5;
                            closeable = zipOutputStream;
                            SharePatchFileUtil.closeQuietly(closeable);
                            throw th;
                        }
                    } catch (Throwable th6) {
                        th = th6;
                        SharePatchFileUtil.closeQuietly(closeable);
                        throw th;
                    }
                }
                new DexPatchApplier(bufferedInputStream2, bufferedInputStream).executeAndSaveTo(file);
                SharePatchFileUtil.closeQuietly(bufferedInputStream2);
                SharePatchFileUtil.closeQuietly(bufferedInputStream);
            } catch (Throwable th7) {
                th = th7;
                closeable = bufferedInputStream;
                closeable2 = bufferedInputStream2;
                SharePatchFileUtil.closeQuietly(closeable2);
                SharePatchFileUtil.closeQuietly(closeable);
                throw th;
            }
        } catch (Throwable th8) {
            th = th8;
            closeable2 = null;
            SharePatchFileUtil.closeQuietly(closeable2);
            SharePatchFileUtil.closeQuietly(closeable);
            throw th;
        }
    }
}
