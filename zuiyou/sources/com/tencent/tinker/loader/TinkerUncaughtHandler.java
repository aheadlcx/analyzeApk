package com.tencent.tinker.loader;

import android.content.Context;
import android.os.Process;
import android.util.Log;
import com.tencent.tinker.loader.shareutil.SharePatchFileUtil;
import com.tencent.tinker.loader.shareutil.ShareTinkerInternals;
import java.io.Closeable;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.lang.Thread.UncaughtExceptionHandler;

public class TinkerUncaughtHandler implements UncaughtExceptionHandler {
    private static final String TAG = "Tinker.UncaughtHandler";
    private final Context context;
    private final File crashFile;
    private final UncaughtExceptionHandler ueh = Thread.getDefaultUncaughtExceptionHandler();

    public TinkerUncaughtHandler(Context context) {
        this.context = context;
        this.crashFile = SharePatchFileUtil.getPatchLastCrashFile(context);
    }

    public void uncaughtException(Thread thread, Throwable th) {
        Closeable printWriter;
        Throwable e;
        Log.e(TAG, "TinkerUncaughtHandler catch exception:" + Log.getStackTraceString(th));
        this.ueh.uncaughtException(thread, th);
        if (this.crashFile != null && (Thread.getDefaultUncaughtExceptionHandler() instanceof TinkerUncaughtHandler)) {
            File parentFile = this.crashFile.getParentFile();
            if (parentFile.exists() || parentFile.mkdirs()) {
                try {
                    printWriter = new PrintWriter(new FileWriter(this.crashFile, false));
                    try {
                        printWriter.println("process:" + ShareTinkerInternals.getProcessName(this.context));
                        printWriter.println(ShareTinkerInternals.getExceptionCauseString(th));
                        SharePatchFileUtil.closeQuietly(printWriter);
                    } catch (IOException e2) {
                        e = e2;
                        try {
                            Log.e(TAG, "print crash file error:" + Log.getStackTraceString(e));
                            SharePatchFileUtil.closeQuietly(printWriter);
                            Process.killProcess(Process.myPid());
                            return;
                        } catch (Throwable th2) {
                            e = th2;
                            SharePatchFileUtil.closeQuietly(printWriter);
                            throw e;
                        }
                    }
                } catch (IOException e3) {
                    e = e3;
                    printWriter = null;
                    Log.e(TAG, "print crash file error:" + Log.getStackTraceString(e));
                    SharePatchFileUtil.closeQuietly(printWriter);
                    Process.killProcess(Process.myPid());
                    return;
                } catch (Throwable th3) {
                    e = th3;
                    printWriter = null;
                    SharePatchFileUtil.closeQuietly(printWriter);
                    throw e;
                }
                Process.killProcess(Process.myPid());
                return;
            }
            Log.e(TAG, "print crash file error: create directory fail!");
        }
    }
}
