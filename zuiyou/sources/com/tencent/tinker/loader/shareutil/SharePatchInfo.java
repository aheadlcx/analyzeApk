package com.tencent.tinker.loader.shareutil;

import android.os.Build;
import android.util.Log;
import com.tencent.tinker.loader.TinkerRuntimeException;
import java.io.Closeable;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;

public class SharePatchInfo {
    public static final String DEFAULT_DIR = "odex";
    public static final String FINGER_PRINT = "print";
    public static final int MAX_EXTRACT_ATTEMPTS = 2;
    public static final String NEW_VERSION = "new";
    public static final String OAT_DIR = "dir";
    public static final String OLD_VERSION = "old";
    private static final String TAG = "Tinker.PatchInfo";
    public String fingerPrint;
    public String newVersion;
    public String oatDir;
    public String oldVersion;

    public SharePatchInfo(String str, String str2, String str3, String str4) {
        this.oldVersion = str;
        this.newVersion = str2;
        this.fingerPrint = str3;
        this.oatDir = str4;
    }

    public static SharePatchInfo readAndCheckPropertyWithLock(File file, File file2) {
        Throwable e;
        Throwable th;
        SharePatchInfo sharePatchInfo = null;
        if (!(file == null || file2 == null)) {
            File parentFile = file2.getParentFile();
            if (!parentFile.exists()) {
                parentFile.mkdirs();
            }
            ShareFileLockHelper fileLock;
            try {
                fileLock = ShareFileLockHelper.getFileLock(file2);
                try {
                    sharePatchInfo = readAndCheckProperty(file);
                    if (fileLock != null) {
                        try {
                            fileLock.close();
                        } catch (Throwable e2) {
                            Log.w(TAG, "releaseInfoLock error", e2);
                        }
                    }
                } catch (Exception e3) {
                    e = e3;
                    try {
                        throw new TinkerRuntimeException("readAndCheckPropertyWithLock fail", e);
                    } catch (Throwable th2) {
                        e = th2;
                        if (fileLock != null) {
                            try {
                                fileLock.close();
                            } catch (Throwable e22) {
                                Log.w(TAG, "releaseInfoLock error", e22);
                            }
                        }
                        throw e;
                    }
                }
            } catch (Throwable e222) {
                th = e222;
                fileLock = null;
                e = th;
                throw new TinkerRuntimeException("readAndCheckPropertyWithLock fail", e);
            } catch (Throwable e2222) {
                th = e2222;
                fileLock = null;
                e = th;
                if (fileLock != null) {
                    fileLock.close();
                }
                throw e;
            }
        }
        return sharePatchInfo;
    }

    public static boolean rewritePatchInfoFileWithLock(File file, SharePatchInfo sharePatchInfo, File file2) {
        if (file == null || sharePatchInfo == null || file2 == null) {
            return false;
        }
        File parentFile = file2.getParentFile();
        if (!parentFile.exists()) {
            parentFile.mkdirs();
        }
        ShareFileLockHelper shareFileLockHelper = null;
        try {
            shareFileLockHelper = ShareFileLockHelper.getFileLock(file2);
            boolean rewritePatchInfoFile = rewritePatchInfoFile(file, sharePatchInfo);
            if (shareFileLockHelper == null) {
                return rewritePatchInfoFile;
            }
            try {
                shareFileLockHelper.close();
                return rewritePatchInfoFile;
            } catch (Throwable e) {
                Log.i(TAG, "releaseInfoLock error", e);
                return rewritePatchInfoFile;
            }
        } catch (Throwable e2) {
            throw new TinkerRuntimeException("rewritePatchInfoFileWithLock fail", e2);
        } catch (Throwable th) {
            if (shareFileLockHelper != null) {
                try {
                    shareFileLockHelper.close();
                } catch (Throwable e3) {
                    Log.i(TAG, "releaseInfoLock error", e3);
                }
            }
        }
    }

    private static SharePatchInfo readAndCheckProperty(File file) {
        Closeable fileInputStream;
        String property;
        Object e;
        Throwable th;
        int i = 0;
        String str = null;
        String str2 = null;
        String str3 = null;
        String str4 = null;
        int i2 = 0;
        while (i < 2 && i2 == 0) {
            int i3 = i + 1;
            Properties properties = new Properties();
            try {
                fileInputStream = new FileInputStream(file);
                try {
                    properties.load(fileInputStream);
                    str4 = properties.getProperty("old");
                    str3 = properties.getProperty("new");
                    str2 = properties.getProperty(FINGER_PRINT);
                    property = properties.getProperty(OAT_DIR);
                    SharePatchFileUtil.closeQuietly(fileInputStream);
                } catch (IOException e2) {
                    e = e2;
                    try {
                        Log.w(TAG, "read property failed, e:" + e);
                        SharePatchFileUtil.closeQuietly(fileInputStream);
                        property = str;
                        if (str4 != null) {
                            str = property;
                            i = i3;
                        } else if (str3 == null) {
                            str = property;
                            i = i3;
                        } else {
                            if (!str4.equals("")) {
                            }
                            str = property;
                            i2 = 1;
                            i = i3;
                        }
                    } catch (Throwable th2) {
                        th = th2;
                    }
                }
            } catch (IOException e3) {
                e = e3;
                fileInputStream = null;
                Log.w(TAG, "read property failed, e:" + e);
                SharePatchFileUtil.closeQuietly(fileInputStream);
                property = str;
                if (str4 != null) {
                    str = property;
                    i = i3;
                } else if (str3 == null) {
                    if (str4.equals("")) {
                    }
                    str = property;
                    i2 = 1;
                    i = i3;
                } else {
                    str = property;
                    i = i3;
                }
            } catch (Throwable th3) {
                fileInputStream = null;
                th = th3;
            }
            if (str4 != null) {
                str = property;
                i = i3;
            } else if (str3 == null) {
                str = property;
                i = i3;
            } else if ((str4.equals("") || SharePatchFileUtil.checkIfMd5Valid(str4)) && SharePatchFileUtil.checkIfMd5Valid(str3)) {
                str = property;
                i2 = 1;
                i = i3;
            } else {
                Log.w(TAG, "path info file  corrupted:" + file.getAbsolutePath());
                str = property;
                i = i3;
            }
        }
        if (i2 != 0) {
            return new SharePatchInfo(str4, str3, str2, str);
        }
        return null;
        SharePatchFileUtil.closeQuietly(fileInputStream);
        throw th;
    }

    private static boolean rewritePatchInfoFile(File file, SharePatchInfo sharePatchInfo) {
        Closeable fileOutputStream;
        Object e;
        SharePatchInfo readAndCheckProperty;
        boolean z;
        Throwable th;
        if (file == null || sharePatchInfo == null) {
            return false;
        }
        if (ShareTinkerInternals.isNullOrNil(sharePatchInfo.fingerPrint)) {
            sharePatchInfo.fingerPrint = Build.FINGERPRINT;
        }
        if (ShareTinkerInternals.isNullOrNil(sharePatchInfo.oatDir)) {
            sharePatchInfo.oatDir = "odex";
        }
        Log.i(TAG, "rewritePatchInfoFile file path:" + file.getAbsolutePath() + " , oldVer:" + sharePatchInfo.oldVersion + ", newVer:" + sharePatchInfo.newVersion + ", fingerprint:" + sharePatchInfo.fingerPrint + ", oatDir:" + sharePatchInfo.oatDir);
        File parentFile = file.getParentFile();
        if (!parentFile.exists()) {
            parentFile.mkdirs();
        }
        int i = 0;
        boolean z2 = false;
        while (i < 2 && !z2) {
            int i2 = i + 1;
            Properties properties = new Properties();
            properties.put("old", sharePatchInfo.oldVersion);
            properties.put("new", sharePatchInfo.newVersion);
            properties.put(FINGER_PRINT, sharePatchInfo.fingerPrint);
            properties.put(OAT_DIR, sharePatchInfo.oatDir);
            try {
                fileOutputStream = new FileOutputStream(file, false);
                try {
                    properties.store(fileOutputStream, "from old version:" + sharePatchInfo.oldVersion + " to new version:" + sharePatchInfo.newVersion);
                    SharePatchFileUtil.closeQuietly(fileOutputStream);
                } catch (Exception e2) {
                    e = e2;
                    try {
                        Log.w(TAG, "write property failed, e:" + e);
                        SharePatchFileUtil.closeQuietly(fileOutputStream);
                        readAndCheckProperty = readAndCheckProperty(file);
                        if (readAndCheckProperty == null) {
                        }
                        z = false;
                        if (z) {
                            file.delete();
                        }
                        z2 = z;
                        i = i2;
                    } catch (Throwable th2) {
                        th = th2;
                    }
                }
            } catch (Exception e3) {
                e = e3;
                fileOutputStream = null;
                Log.w(TAG, "write property failed, e:" + e);
                SharePatchFileUtil.closeQuietly(fileOutputStream);
                readAndCheckProperty = readAndCheckProperty(file);
                if (readAndCheckProperty == null) {
                }
                z = false;
                if (z) {
                    file.delete();
                }
                z2 = z;
                i = i2;
            } catch (Throwable th3) {
                th = th3;
                fileOutputStream = null;
            }
            readAndCheckProperty = readAndCheckProperty(file);
            if (readAndCheckProperty == null && readAndCheckProperty.oldVersion.equals(sharePatchInfo.oldVersion) && readAndCheckProperty.newVersion.equals(sharePatchInfo.newVersion)) {
                z = true;
            } else {
                z = false;
            }
            if (z) {
                file.delete();
            }
            z2 = z;
            i = i2;
        }
        if (z2) {
            return true;
        }
        return false;
        SharePatchFileUtil.closeQuietly(fileOutputStream);
        throw th;
    }
}
