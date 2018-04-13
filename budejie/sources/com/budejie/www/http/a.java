package com.budejie.www.http;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.Bitmap.CompressFormat;
import android.graphics.Bitmap.Config;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.drawable.Drawable;
import android.os.Environment;
import android.util.Log;
import com.budejie.www.activity.BudejieApplication;
import com.budejie.www.http.NetWorkUtil.RequstMethod;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class a {
    Activity a;
    com.budejie.www.f.a b;
    private j c = new j();

    public a(Activity activity, com.budejie.www.f.a aVar) {
        this.a = activity;
        this.b = aVar;
    }

    public void a(String str, String str2, String str3, String str4, String str5, String str6, String str7, int i) {
        BudejieApplication.a.a(this.a, "http://api.budejie.com/api/api_open.php", "post", this.c.a(this.a, str, str2, str3, str4, str5, str6, str7), this.b, null, i);
    }

    public void a(String str, net.tsz.afinal.a.a<?> aVar) {
        BudejieApplication.a.a(RequstMethod.GET, "http://api.budejie.com/api/api_open.php", this.c.b(this.a, str), (net.tsz.afinal.a.a) aVar);
    }

    public String a(Drawable drawable) {
        Bitmap createBitmap = Bitmap.createBitmap(180, 180, Config.ARGB_8888);
        Canvas canvas = new Canvas(createBitmap);
        drawable.setBounds(0, 0, 180, 180);
        drawable.draw(canvas);
        return a(createBitmap);
    }

    public static String a(Bitmap bitmap) {
        File file;
        IOException e;
        int i = 80;
        OutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        bitmap.compress(CompressFormat.JPEG, 80, byteArrayOutputStream);
        Log.i("SisterUtil", (byteArrayOutputStream.toByteArray().length / 1024) + "");
        while (byteArrayOutputStream.toByteArray().length / 1024 > 100) {
            Log.i("SisterUtil", (byteArrayOutputStream.toByteArray().length / 1024) + "");
            byteArrayOutputStream.reset();
            bitmap.compress(CompressFormat.JPEG, i, byteArrayOutputStream);
            i -= 5;
        }
        InputStream byteArrayInputStream = new ByteArrayInputStream(byteArrayOutputStream.toByteArray());
        Bitmap decodeStream = BitmapFactory.decodeStream(byteArrayInputStream, null, null);
        try {
            String str = Environment.getExternalStorageDirectory().getPath() + "/mimi/profile";
            file = new File(str);
            if (!file.exists()) {
                file.mkdirs();
            }
            file = new File(str + "/" + System.currentTimeMillis() + ".jpg");
            try {
                OutputStream fileOutputStream = new FileOutputStream(file);
                decodeStream.compress(CompressFormat.JPEG, 80, fileOutputStream);
                fileOutputStream.flush();
                fileOutputStream.close();
                byteArrayInputStream.close();
            } catch (IOException e2) {
                e = e2;
                e.printStackTrace();
                return file.getPath();
            }
        } catch (IOException e3) {
            IOException iOException = e3;
            file = null;
            e = iOException;
            e.printStackTrace();
            return file.getPath();
        }
        return file.getPath();
    }
}
