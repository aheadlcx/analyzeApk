package com.xiaomi.channel.commonutils.file;

import android.os.Environment;
import android.os.StatFs;
import android.text.TextUtils;
import com.alibaba.sdk.android.oss.common.OSSConstants;
import com.xiaomi.channel.commonutils.logger.b;
import java.io.File;

public class c {
    public static boolean a() {
        try {
            return Environment.getExternalStorageState().equals("removed");
        } catch (Throwable e) {
            b.a(e);
            return true;
        }
    }

    public static boolean b() {
        try {
            return !Environment.getExternalStorageState().equals("mounted");
        } catch (Throwable e) {
            b.a(e);
            return true;
        }
    }

    public static boolean c() {
        return e() <= OSSConstants.MIN_PART_SIZE_LIMIT;
    }

    public static boolean d() {
        return (b() || c() || a()) ? false : true;
    }

    public static long e() {
        long j = 0;
        if (b()) {
            return j;
        }
        File externalStorageDirectory = Environment.getExternalStorageDirectory();
        if (externalStorageDirectory == null || TextUtils.isEmpty(externalStorageDirectory.getPath())) {
            return j;
        }
        try {
            StatFs statFs = new StatFs(externalStorageDirectory.getPath());
            return (((long) statFs.getAvailableBlocks()) - 4) * ((long) statFs.getBlockSize());
        } catch (Throwable th) {
            return j;
        }
    }
}
