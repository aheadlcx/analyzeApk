package qsbk.app.core.utils;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Bitmap.CompressFormat;
import android.graphics.Bitmap.Config;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.net.Uri;
import android.os.Environment;
import android.text.format.DateFormat;
import android.view.TextureView;
import android.view.View;
import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.util.Date;
import qsbk.app.core.ui.base.BaseActivity;

public class ScreenShotUtils {
    public static void takeWindowScreeShot(BaseActivity baseActivity) {
        if (baseActivity != null && !baseActivity.isFinishing()) {
            takeViewScreeShot(baseActivity, baseActivity.getWindow().getDecorView());
        }
    }

    public static void takeViewScreeShot(BaseActivity baseActivity, View view) {
        if (baseActivity != null && !baseActivity.isFinishing()) {
            view.setDrawingCacheEnabled(true);
            Bitmap drawingCache = view.getDrawingCache();
            view.setDrawingCacheEnabled(false);
            saveBitmap(baseActivity, drawingCache);
        }
    }

    public static void takeLiveRoomScreeShot(BaseActivity baseActivity, TextureView textureView) {
        if (baseActivity != null && !baseActivity.isFinishing()) {
            View decorView = baseActivity.getWindow().getDecorView();
            decorView.setDrawingCacheEnabled(true);
            textureView.setDrawingCacheEnabled(true);
            Bitmap liveRoomScreenShot = getLiveRoomScreenShot(decorView, textureView);
            decorView.setDrawingCacheEnabled(false);
            textureView.setDrawingCacheEnabled(false);
            new Thread(new u(baseActivity, liveRoomScreenShot)).start();
        }
    }

    public static void saveBitmap(BaseActivity baseActivity, Bitmap bitmap) {
        if (baseActivity != null && !baseActivity.isFinishing() && bitmap != null) {
            try {
                String str = DateFormat.format("yyyyMMddhhmmss", new Date()).toString() + ".png";
                File file = new File(Environment.getExternalStorageDirectory().getAbsolutePath() + "/Remix/Image/" + str);
                OutputStream fileOutputStream = new FileOutputStream(file);
                bitmap.compress(CompressFormat.PNG, 100, fileOutputStream);
                fileOutputStream.flush();
                fileOutputStream.close();
                baseActivity.mHandler.post(new v());
                a(baseActivity, file, str);
            } catch (Throwable th) {
                baseActivity.mHandler.post(new w());
                th.printStackTrace();
            }
        }
    }

    public static Bitmap getLiveRoomScreenShot(View view, TextureView textureView) {
        try {
            view.setAlpha(0.0f);
            Bitmap drawingCache = view.getDrawingCache();
            Bitmap bitmap = textureView.getBitmap();
            if (drawingCache == null || bitmap == null) {
                return null;
            }
            Bitmap createBitmap = Bitmap.createBitmap(drawingCache.getWidth(), drawingCache.getHeight(), Config.ARGB_8888);
            Canvas canvas = new Canvas(createBitmap);
            canvas.drawBitmap(bitmap, (float) ((view.getWidth() - bitmap.getWidth()) / 2), (float) ((view.getHeight() - bitmap.getHeight()) / 2), new Paint());
            canvas.drawBitmap(drawingCache, 0.0f, 0.0f, new Paint());
            canvas.save();
            canvas.restore();
            view.setAlpha(1.0f);
            return createBitmap;
        } catch (OutOfMemoryError e) {
            e.printStackTrace();
            System.gc();
            return null;
        }
    }

    private static void a(Activity activity, File file, String str) {
        if (activity != null && !activity.isFinishing()) {
            Intent intent = new Intent("android.intent.action.MEDIA_SCANNER_SCAN_FILE");
            intent.setData(Uri.fromFile(file));
            activity.sendBroadcast(intent);
        }
    }
}
