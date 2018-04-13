package com.budejie.www.activity.video;

import android.os.Environment;
import java.io.File;

public class d {
    public static String a;
    public static String b = "/microvideo";
    public static String c = "/video/";
    public static String d = "/thumbnails/";

    public static String a() {
        if (a != null) {
            return a;
        }
        a = Environment.getExternalStorageDirectory().getAbsolutePath() + b;
        File file = new File(a);
        if (!file.exists()) {
            file.mkdirs();
        }
        return a;
    }

    public static String b() {
        if (a == null) {
            a = a();
        }
        File file = new File(a + d);
        if (!file.exists()) {
            file.mkdirs();
        }
        return file.getAbsolutePath();
    }
}
