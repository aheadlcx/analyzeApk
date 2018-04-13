package com.liulishuo.filedownloader.g;

import android.annotation.SuppressLint;
import android.content.Context;
import com.liulishuo.filedownloader.exception.PathConflictException;
import com.liulishuo.filedownloader.message.f;
import com.liulishuo.filedownloader.x;
import java.io.File;
import java.io.IOException;

public class c {
    @SuppressLint({"StaticFieldLeak"})
    private static Context a;

    public interface a {
        int a(int i, String str, String str2, long j);
    }

    public interface b {
        com.liulishuo.filedownloader.a.b a(String str) throws IOException;
    }

    public interface e {
        com.liulishuo.filedownloader.f.a a(File file) throws IOException;

        boolean a();
    }

    public interface c {
        com.liulishuo.filedownloader.b.a a();
    }

    public interface d {
        int a(int i, String str, String str2, boolean z);

        int a(String str, String str2, boolean z);
    }

    public static void a(Context context) {
        a = context;
    }

    public static Context a() {
        return a;
    }

    public static boolean a(int i, String str, boolean z, boolean z2) {
        if (z || str == null) {
            return false;
        }
        File file = new File(str);
        if (!file.exists()) {
            return false;
        }
        com.liulishuo.filedownloader.message.e.a().a(f.a(i, file, z2));
        return true;
    }

    public static boolean a(int i, com.liulishuo.filedownloader.d.c cVar, x xVar, boolean z) {
        if (!xVar.a(cVar)) {
            return false;
        }
        com.liulishuo.filedownloader.message.e.a().a(f.a(i, cVar.g(), cVar.h(), z));
        return true;
    }

    public static boolean a(int i, long j, String str, String str2, x xVar) {
        if (!(str2 == null || str == null)) {
            int a = xVar.a(str, i);
            if (a != 0) {
                com.liulishuo.filedownloader.message.e.a().a(f.a(i, j, new PathConflictException(a, str, str2)));
                return true;
            }
        }
        return false;
    }
}
