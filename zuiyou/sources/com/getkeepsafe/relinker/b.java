package com.getkeepsafe.relinker;

import android.content.Context;
import java.io.File;

public class b {

    public interface a {
        void a(Context context, String[] strArr, String str, File file, c cVar);
    }

    public interface b {
        void a(String str);

        String[] a();

        void b(String str);

        String c(String str);

        String d(String str);
    }

    public interface c {
        void a();

        void a(Throwable th);
    }

    public interface d {
        void a(String str);
    }

    public static void a(Context context, String str) {
        a(context, str, null, null);
    }

    public static void a(Context context, String str, c cVar) {
        a(context, str, null, cVar);
    }

    public static void a(Context context, String str, String str2, c cVar) {
        new c().a(context, str, str2, cVar);
    }
}
