package com.budejie.www.activity.video;

import android.graphics.Bitmap;
import android.graphics.Bitmap.CompressFormat;
import java.io.File;
import java.io.FileOutputStream;

public class b {
    public static boolean a(Bitmap bitmap, String str, CompressFormat compressFormat) {
        boolean z = false;
        File file = new File(str);
        if (file.exists()) {
            return true;
        }
        if (bitmap.isRecycled()) {
            return z;
        }
        try {
            z = bitmap.compress(compressFormat, 80, new FileOutputStream(file, false));
        } catch (Exception e) {
            e.a("BitmapUtil", "saveBitmapToFile Exception , " + e.toString());
        }
        if (z) {
            return z;
        }
        file.delete();
        return z;
    }
}
