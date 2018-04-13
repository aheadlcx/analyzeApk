package com.qq.e.comm.managers.plugin;

import com.qq.e.comm.util.FileUtil;
import com.qq.e.comm.util.GDTLogger;
import com.qq.e.comm.util.Md5Util;
import com.qq.e.comm.util.StringUtil;
import com.qq.e.comm.util.a;
import java.io.File;

final class c {
    private final File a;
    private final File b;
    private String c;
    private int d;

    public c(File file, File file2) {
        this.a = file;
        this.b = file2;
    }

    final boolean a() {
        try {
            if (!this.b.exists() || !this.a.exists()) {
                return false;
            }
            String[] split = StringUtil.readAll(this.b).split("#####");
            if (split.length != 2) {
                return false;
            }
            String str = split[1];
            int parseInteger = StringUtil.parseInteger(split[0], 0);
            a a = a.a();
            File file = this.a;
            boolean b = (file == null || !file.exists()) ? false : a.b(str, Md5Util.encode(file));
            if (!b) {
                return false;
            }
            this.c = str;
            this.d = parseInteger;
            return true;
        } catch (Throwable th) {
            GDTLogger.report("Exception while checking plugin", th);
            return false;
        }
    }

    final boolean a(File file, File file2) {
        return (file == null || file2 == null) ? false : (file.equals(this.a) || FileUtil.renameTo(this.a, file)) ? file2.equals(this.b) || FileUtil.renameTo(this.b, file2) : false;
    }

    final int b() {
        return this.d;
    }

    final String c() {
        return this.c;
    }
}
