package qsbk.app.utils;

import android.content.Context;
import com.crashlytics.android.Crashlytics;
import java.io.File;
import java.io.IOException;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import qsbk.app.Constants;

public final class UILDiskCacheCleaner {
    private static final String[] a = new String[]{"ENOSPC", "No space", "nospace"};
    private static final ThreadPoolExecutor b = new ThreadPoolExecutor(0, 1, 60, TimeUnit.SECONDS, new LinkedBlockingQueue());

    private static void b(File file) {
        if (file != null) {
            try {
                File[] listFiles = file.listFiles(new bh());
                if (listFiles != null && listFiles.length >= 1) {
                    for (File file2 : listFiles) {
                        if (file2.isFile() && file2.exists()) {
                            file2.delete();
                        }
                    }
                }
            } catch (Throwable th) {
                Crashlytics.logException(new RuntimeException("SelfCatchedException : ", th));
            }
        }
    }

    public static void clearUILIndividualCache(Context context) {
        b.execute(new bi(context));
    }

    public static void clearOldUILUnlimiteCache(Context context) {
        if (Constants.localVersion <= 65) {
            b.execute(new bj(context));
        }
    }

    public static void clearOnIOException(Context context, IOException iOException) {
        if (iOException != null && context != null) {
            String iOException2 = iOException.toString();
            if (iOException2 != null) {
                for (CharSequence contains : a) {
                    if (iOException2.contains(contains)) {
                        clearUILIndividualCache(context);
                        return;
                    }
                }
            }
        }
    }
}
