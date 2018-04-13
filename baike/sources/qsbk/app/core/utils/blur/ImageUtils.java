package qsbk.app.core.utils.blur;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.Bitmap.CompressFormat;
import android.graphics.Point;
import android.os.Build.VERSION;
import android.util.Log;
import android.view.Display;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

public class ImageUtils {
    public static void storeImage(Bitmap bitmap, String str) {
        if (str == null) {
            Log.d("ImageUtils", "Error creating media file, check storage permissions: ");
            return;
        }
        File file = new File(str);
        if (file.exists()) {
            file.delete();
        }
        try {
            OutputStream fileOutputStream = new FileOutputStream(file);
            bitmap.compress(CompressFormat.PNG, 90, fileOutputStream);
            fileOutputStream.close();
        } catch (FileNotFoundException e) {
            Log.d("ImageUtils", "File not found: " + e.getMessage());
        } catch (IOException e2) {
            Log.d("ImageUtils", "Error accessing file: " + e2.getMessage());
        }
    }

    @SuppressLint({"NewApi"})
    public static int getScreenHeight(Activity activity) {
        Display defaultDisplay = activity.getWindowManager().getDefaultDisplay();
        if (VERSION.SDK_INT < 13) {
            return defaultDisplay.getHeight();
        }
        Point point = new Point();
        defaultDisplay.getSize(point);
        return point.y;
    }

    @SuppressLint({"NewApi"})
    public static int getScreenWidth(Activity activity) {
        Display defaultDisplay = activity.getWindowManager().getDefaultDisplay();
        if (VERSION.SDK_INT < 13) {
            return defaultDisplay.getWidth();
        }
        Point point = new Point();
        defaultDisplay.getSize(point);
        return point.x;
    }
}
