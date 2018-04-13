package com.sina.weibo.sdk.utils;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Bitmap.CompressFormat;
import android.graphics.Bitmap.Config;
import android.graphics.BitmapFactory;
import android.graphics.BitmapFactory.Options;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.text.TextUtils;
import io.agora.rtc.Constants;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class ImageUtils {
    private static void a(String str, int i, int i2) throws IOException {
        if (i <= 0) {
            throw new IllegalArgumentException("size must be greater than 0!");
        } else if (!b(str)) {
            if (str == null) {
                str = "null";
            }
            throw new FileNotFoundException(str);
        } else if (BitmapHelper.verifyBitmap(str)) {
            int i3 = i * 2;
            InputStream fileInputStream = new FileInputStream(str);
            Options options = new Options();
            options.inJustDecodeBounds = true;
            BitmapFactory.decodeStream(fileInputStream, null, options);
            try {
                fileInputStream.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
            int i4 = 0;
            while (true) {
                if ((options.outWidth >> i4) <= i3 && (options.outHeight >> i4) <= i3) {
                    break;
                }
                i4++;
            }
            options.inSampleSize = (int) Math.pow(2.0d, (double) i4);
            options.inJustDecodeBounds = false;
            Bitmap a = a(str, options);
            if (a == null) {
                throw new IOException("Bitmap decode error!");
            }
            a(str);
            c(str);
            float width = ((float) i) / ((float) (a.getWidth() > a.getHeight() ? a.getWidth() : a.getHeight()));
            if (width < 1.0f) {
                Bitmap createBitmap;
                float f = width;
                while (true) {
                    try {
                        createBitmap = Bitmap.createBitmap((int) (((float) a.getWidth()) * f), (int) (((float) a.getHeight()) * f), Config.ARGB_8888);
                        break;
                    } catch (OutOfMemoryError e2) {
                        System.gc();
                        f = (float) (((double) f) * 0.8d);
                    }
                }
                if (createBitmap == null) {
                    a.recycle();
                }
                Canvas canvas = new Canvas(createBitmap);
                Matrix matrix = new Matrix();
                matrix.setScale(f, f);
                canvas.drawBitmap(a, matrix, new Paint());
                a.recycle();
                a = createBitmap;
            }
            OutputStream fileOutputStream = new FileOutputStream(str);
            if (options == null || options.outMimeType == null || !options.outMimeType.contains("png")) {
                a.compress(CompressFormat.JPEG, i2, fileOutputStream);
            } else {
                a.compress(CompressFormat.PNG, i2, fileOutputStream);
            }
            try {
                fileOutputStream.close();
            } catch (Exception e3) {
                e3.printStackTrace();
            }
            a.recycle();
        } else {
            throw new IOException("");
        }
    }

    private static void b(String str, int i, int i2) throws IOException {
        if (i <= 0) {
            throw new IllegalArgumentException("size must be greater than 0!");
        } else if (!b(str)) {
            if (str == null) {
                str = "null";
            }
            throw new FileNotFoundException(str);
        } else if (BitmapHelper.verifyBitmap(str)) {
            InputStream fileInputStream = new FileInputStream(str);
            Options options = new Options();
            options.inJustDecodeBounds = true;
            BitmapFactory.decodeStream(fileInputStream, null, options);
            try {
                fileInputStream.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
            int i3 = 0;
            while (true) {
                if ((options.outWidth >> i3) <= i && (options.outHeight >> i3) <= i) {
                    break;
                }
                i3++;
            }
            options.inSampleSize = (int) Math.pow(2.0d, (double) i3);
            options.inJustDecodeBounds = false;
            Bitmap a = a(str, options);
            if (a == null) {
                throw new IOException("Bitmap decode error!");
            }
            a(str);
            c(str);
            OutputStream fileOutputStream = new FileOutputStream(str);
            if (options == null || options.outMimeType == null || !options.outMimeType.contains("png")) {
                a.compress(CompressFormat.JPEG, i2, fileOutputStream);
            } else {
                a.compress(CompressFormat.PNG, i2, fileOutputStream);
            }
            try {
                fileOutputStream.close();
            } catch (Exception e2) {
                e2.printStackTrace();
            }
            a.recycle();
        } else {
            throw new IOException("");
        }
    }

    public static boolean revitionPostImageSize(Context context, String str) {
        try {
            if (NetworkHelper.isWifiValid(context)) {
                a(str, Constants.ERR_VCM_UNKNOWN_ERROR, 75);
            } else {
                b(str, 1024, 75);
            }
            return true;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    private static Bitmap a(String str, Options options) {
        FileInputStream fileInputStream;
        Bitmap bitmap;
        OutOfMemoryError outOfMemoryError;
        FileInputStream fileInputStream2 = null;
        Options options2;
        if (options == null) {
            options2 = new Options();
            options2.inSampleSize = 1;
        } else {
            options2 = options;
        }
        int i = 0;
        Bitmap bitmap2 = null;
        while (i < 5) {
            try {
                InputStream fileInputStream3 = new FileInputStream(str);
                try {
                    bitmap2 = BitmapFactory.decodeStream(fileInputStream3, null, options);
                    try {
                        fileInputStream3.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    return bitmap2;
                } catch (OutOfMemoryError e2) {
                    OutOfMemoryError outOfMemoryError2 = e2;
                    fileInputStream = fileInputStream3;
                    bitmap = bitmap2;
                    outOfMemoryError = outOfMemoryError2;
                } catch (FileNotFoundException e3) {
                    return bitmap2;
                }
            } catch (OutOfMemoryError e22) {
                bitmap = bitmap2;
                outOfMemoryError = e22;
                fileInputStream = fileInputStream2;
                outOfMemoryError.printStackTrace();
                r0.inSampleSize *= 2;
                try {
                    fileInputStream.close();
                } catch (IOException e4) {
                    e4.printStackTrace();
                }
                i++;
                fileInputStream2 = fileInputStream;
                bitmap2 = bitmap;
            } catch (FileNotFoundException e32) {
                return bitmap2;
            }
        }
        return bitmap2;
    }

    private static void a(File file) {
        if (file != null && file.exists() && !file.delete()) {
            throw new RuntimeException(file.getAbsolutePath() + " doesn't be deleted!");
        }
    }

    private static boolean a(String str) {
        boolean z = false;
        if (!TextUtils.isEmpty(str)) {
            File file = new File(str);
            int i = 1;
            if (file != null) {
                while (!z && i <= 5 && file.isFile() && file.exists()) {
                    z = file.delete();
                    if (!z) {
                        i++;
                    }
                }
            }
        }
        return z;
    }

    private static boolean b(String str) {
        if (TextUtils.isEmpty(str)) {
            return false;
        }
        File file = new File(str);
        if (file == null || !file.exists()) {
            return false;
        }
        return true;
    }

    private static boolean b(File file) {
        if (file == null) {
            return false;
        }
        File parentFile = file.getParentFile();
        if (parentFile == null || parentFile.exists()) {
            return false;
        }
        if (file.exists() || file.mkdirs()) {
            return true;
        }
        return false;
    }

    private static void c(String str) {
        if (str != null) {
            File file = new File(str);
            if (file != null && !file.exists() && b(file)) {
                if (file.exists()) {
                    a(file);
                }
                try {
                    file.createNewFile();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public static boolean isWifi(Context context) {
        NetworkInfo activeNetworkInfo = ((ConnectivityManager) context.getSystemService("connectivity")).getActiveNetworkInfo();
        if (activeNetworkInfo == null || activeNetworkInfo.getType() != 1) {
            return false;
        }
        return true;
    }
}
