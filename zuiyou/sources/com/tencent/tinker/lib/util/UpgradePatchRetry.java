package com.tencent.tinker.lib.util;

import android.content.Context;
import android.content.Intent;
import com.tencent.tinker.lib.service.TinkerPatchService;
import com.tencent.tinker.lib.tinker.Tinker;
import com.tencent.tinker.lib.tinker.TinkerInstaller;
import com.tencent.tinker.loader.shareutil.SharePatchFileUtil;
import java.io.Closeable;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;

public class UpgradePatchRetry {
    private static final String RETRY_COUNT_PROPERTY = "times";
    private static final String RETRY_FILE_MD5_PROPERTY = "md5";
    private static final String RETRY_INFO_NAME = "patch.retry";
    private static final int RETRY_MAX_COUNT = 5;
    private static final String TAG = "Tinker.UpgradePatchRetry";
    private static final String TEMP_PATCH_NAME = "temp.apk";
    private static UpgradePatchRetry sInstance;
    private Context context = null;
    private boolean isRetryEnable = true;
    private int maxRetryCount = 5;
    private File retryInfoFile = null;
    private File tempPatchFile = null;

    static class a {
        String a;
        String b;

        a(String str, String str2) {
            this.a = str;
            this.b = str2;
        }

        static a a(File file) {
            String property;
            Object e;
            Throwable th;
            String str = null;
            Properties properties = new Properties();
            Closeable fileInputStream;
            try {
                fileInputStream = new FileInputStream(file);
                try {
                    properties.load(fileInputStream);
                    property = properties.getProperty(UpgradePatchRetry.RETRY_FILE_MD5_PROPERTY);
                    try {
                        str = properties.getProperty(UpgradePatchRetry.RETRY_COUNT_PROPERTY);
                        SharePatchFileUtil.closeQuietly(fileInputStream);
                    } catch (IOException e2) {
                        e = e2;
                        try {
                            TinkerLog.e(UpgradePatchRetry.TAG, "fail to readRetryProperty:" + e, new Object[0]);
                            SharePatchFileUtil.closeQuietly(fileInputStream);
                            return new a(property, str);
                        } catch (Throwable th2) {
                            th = th2;
                            SharePatchFileUtil.closeQuietly(fileInputStream);
                            throw th;
                        }
                    }
                } catch (IOException e3) {
                    IOException iOException = e3;
                    property = str;
                    TinkerLog.e(UpgradePatchRetry.TAG, "fail to readRetryProperty:" + e, new Object[0]);
                    SharePatchFileUtil.closeQuietly(fileInputStream);
                    return new a(property, str);
                }
            } catch (IOException e32) {
                e = e32;
                fileInputStream = str;
                property = str;
                TinkerLog.e(UpgradePatchRetry.TAG, "fail to readRetryProperty:" + e, new Object[0]);
                SharePatchFileUtil.closeQuietly(fileInputStream);
                return new a(property, str);
            } catch (Throwable th3) {
                fileInputStream = str;
                th = th3;
                SharePatchFileUtil.closeQuietly(fileInputStream);
                throw th;
            }
            return new a(property, str);
        }

        static void a(File file, a aVar) {
            Closeable fileOutputStream;
            Throwable e;
            if (aVar != null) {
                File parentFile = file.getParentFile();
                if (!parentFile.exists()) {
                    parentFile.mkdirs();
                }
                Properties properties = new Properties();
                properties.put(UpgradePatchRetry.RETRY_FILE_MD5_PROPERTY, aVar.a);
                properties.put(UpgradePatchRetry.RETRY_COUNT_PROPERTY, aVar.b);
                try {
                    fileOutputStream = new FileOutputStream(file, false);
                    try {
                        properties.store(fileOutputStream, null);
                        SharePatchFileUtil.closeQuietly(fileOutputStream);
                    } catch (Exception e2) {
                        e = e2;
                        try {
                            TinkerLog.printErrStackTrace(UpgradePatchRetry.TAG, e, "retry write property fail", new Object[0]);
                            SharePatchFileUtil.closeQuietly(fileOutputStream);
                        } catch (Throwable th) {
                            e = th;
                            SharePatchFileUtil.closeQuietly(fileOutputStream);
                            throw e;
                        }
                    }
                } catch (Exception e3) {
                    e = e3;
                    fileOutputStream = null;
                    TinkerLog.printErrStackTrace(UpgradePatchRetry.TAG, e, "retry write property fail", new Object[0]);
                    SharePatchFileUtil.closeQuietly(fileOutputStream);
                } catch (Throwable th2) {
                    e = th2;
                    fileOutputStream = null;
                    SharePatchFileUtil.closeQuietly(fileOutputStream);
                    throw e;
                }
            }
        }
    }

    public UpgradePatchRetry(Context context) {
        this.context = context;
        this.retryInfoFile = new File(SharePatchFileUtil.getPatchTempDirectory(context), RETRY_INFO_NAME);
        this.tempPatchFile = new File(SharePatchFileUtil.getPatchTempDirectory(context), TEMP_PATCH_NAME);
    }

    public static UpgradePatchRetry getInstance(Context context) {
        if (sInstance == null) {
            sInstance = new UpgradePatchRetry(context);
        }
        return sInstance;
    }

    public void setRetryEnable(boolean z) {
        this.isRetryEnable = z;
    }

    public void setMaxRetryCount(int i) {
        if (i <= 0) {
            TinkerLog.e(TAG, "max count must large than 0", new Object[0]);
        } else {
            this.maxRetryCount = i;
        }
    }

    public boolean onPatchRetryLoad() {
        if (!this.isRetryEnable) {
            TinkerLog.w(TAG, "onPatchRetryLoad retry disabled, just return", new Object[0]);
            return false;
        } else if (!Tinker.with(this.context).isMainProcess()) {
            TinkerLog.w(TAG, "onPatchRetryLoad retry is not main process, just return", new Object[0]);
            return false;
        } else if (!this.retryInfoFile.exists()) {
            TinkerLog.w(TAG, "onPatchRetryLoad retry info not exist, just return", new Object[0]);
            return false;
        } else if (TinkerServiceInternals.isTinkerPatchServiceRunning(this.context)) {
            TinkerLog.w(TAG, "onPatchRetryLoad tinker service is running, just return", new Object[0]);
            return false;
        } else {
            String absolutePath = this.tempPatchFile.getAbsolutePath();
            if (absolutePath == null || !new File(absolutePath).exists()) {
                TinkerLog.w(TAG, "onPatchRetryLoad patch file: %s is not exist, just return", absolutePath);
                return false;
            }
            TinkerLog.w(TAG, "onPatchRetryLoad patch file: %s is exist, retry to patch", absolutePath);
            TinkerInstaller.onReceiveUpgradePatch(this.context, absolutePath);
            return true;
        }
    }

    public void onPatchServiceStart(Intent intent) {
        if (!this.isRetryEnable) {
            TinkerLog.w(TAG, "onPatchServiceStart retry disabled, just return", new Object[0]);
        } else if (intent == null) {
            TinkerLog.e(TAG, "onPatchServiceStart intent is null, just return", new Object[0]);
        } else {
            String patchPathExtra = TinkerPatchService.getPatchPathExtra(intent);
            if (patchPathExtra == null) {
                TinkerLog.w(TAG, "onPatchServiceStart patch path is null, just return", new Object[0]);
                return;
            }
            File file = new File(patchPathExtra);
            String md5 = SharePatchFileUtil.getMD5(file);
            if (md5 == null) {
                TinkerLog.w(TAG, "onPatchServiceStart patch md5 is null, just return", new Object[0]);
                return;
            }
            a a;
            if (this.retryInfoFile.exists()) {
                a = a.a(this.retryInfoFile);
                if (a.a == null || a.b == null || !md5.equals(a.a)) {
                    copyToTempFile(file);
                    a.a = md5;
                    a.b = "1";
                } else {
                    int parseInt = Integer.parseInt(a.b);
                    if (parseInt >= this.maxRetryCount) {
                        SharePatchFileUtil.safeDeleteFile(this.tempPatchFile);
                        TinkerLog.w(TAG, "onPatchServiceStart retry more than max count, delete retry info file!", new Object[0]);
                        return;
                    }
                    a.b = String.valueOf(parseInt + 1);
                }
            } else {
                copyToTempFile(file);
                a = new a(md5, "1");
            }
            a.a(this.retryInfoFile, a);
        }
    }

    public boolean onPatchListenerCheck(String str) {
        if (!this.isRetryEnable) {
            TinkerLog.w(TAG, "onPatchListenerCheck retry disabled, just return", new Object[0]);
            return true;
        } else if (!this.retryInfoFile.exists()) {
            TinkerLog.w(TAG, "onPatchListenerCheck retry file is not exist, just return", new Object[0]);
            return true;
        } else if (str == null) {
            TinkerLog.w(TAG, "onPatchListenerCheck md5 is null, just return", new Object[0]);
            return true;
        } else {
            a a = a.a(this.retryInfoFile);
            if (!str.equals(a.a) || Integer.parseInt(a.b) < this.maxRetryCount) {
                return true;
            }
            TinkerLog.w(TAG, "onPatchListenerCheck, retry count %d must exceed than max retry count", Integer.valueOf(Integer.parseInt(a.b)));
            SharePatchFileUtil.safeDeleteFile(this.tempPatchFile);
            return false;
        }
    }

    public boolean onPatchResetMaxCheck(String str) {
        if (!this.isRetryEnable) {
            TinkerLog.w(TAG, "onPatchResetMaxCheck retry disabled, just return", new Object[0]);
        } else if (!this.retryInfoFile.exists()) {
            TinkerLog.w(TAG, "onPatchResetMaxCheck retry file is not exist, just return", new Object[0]);
        } else if (str == null) {
            TinkerLog.w(TAG, "onPatchResetMaxCheck md5 is null, just return", new Object[0]);
        } else {
            a a = a.a(this.retryInfoFile);
            if (str.equals(a.a)) {
                TinkerLog.i(TAG, "onPatchResetMaxCheck, reset max check to 1", new Object[0]);
                a.b = "1";
                a.a(this.retryInfoFile, a);
            }
        }
        return true;
    }

    public void onPatchServiceResult() {
        if (!this.isRetryEnable) {
            TinkerLog.w(TAG, "onPatchServiceResult retry disabled, just return", new Object[0]);
        } else if (this.tempPatchFile.exists()) {
            SharePatchFileUtil.safeDeleteFile(this.tempPatchFile);
        }
    }

    private void copyToTempFile(File file) {
        if (!file.getAbsolutePath().equals(this.tempPatchFile.getAbsolutePath())) {
            TinkerLog.w(TAG, "try copy file: %s to %s", file.getAbsolutePath(), this.tempPatchFile.getAbsolutePath());
            try {
                SharePatchFileUtil.copyFileUsingStream(file, this.tempPatchFile);
            } catch (IOException e) {
                TinkerLog.e(TAG, "fail to copy file: %s to %s", file.getAbsolutePath(), this.tempPatchFile.getAbsolutePath());
            }
        }
    }
}
