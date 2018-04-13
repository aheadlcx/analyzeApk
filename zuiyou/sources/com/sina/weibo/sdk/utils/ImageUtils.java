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
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class ImageUtils {
    private static void revitionImageSizeHD(String str, int i, int i2) throws IOException {
        if (i <= 0) {
            throw new IllegalArgumentException("size must be greater than 0!");
        } else if (!isFileExisted(str)) {
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
            Bitmap safeDecodeBimtapFile = safeDecodeBimtapFile(str, options);
            if (safeDecodeBimtapFile == null) {
                throw new IOException("Bitmap decode error!");
            }
            deleteDependon(str);
            makesureFileExist(str);
            float width = ((float) i) / ((float) (safeDecodeBimtapFile.getWidth() > safeDecodeBimtapFile.getHeight() ? safeDecodeBimtapFile.getWidth() : safeDecodeBimtapFile.getHeight()));
            if (width < 1.0f) {
                Bitmap createBitmap;
                float f = width;
                while (true) {
                    try {
                        createBitmap = Bitmap.createBitmap((int) (((float) safeDecodeBimtapFile.getWidth()) * f), (int) (((float) safeDecodeBimtapFile.getHeight()) * f), Config.ARGB_8888);
                        break;
                    } catch (OutOfMemoryError e2) {
                        System.gc();
                        f = (float) (((double) f) * 0.8d);
                    }
                }
                if (createBitmap == null) {
                    safeDecodeBimtapFile.recycle();
                }
                Canvas canvas = new Canvas(createBitmap);
                Matrix matrix = new Matrix();
                matrix.setScale(f, f);
                canvas.drawBitmap(safeDecodeBimtapFile, matrix, new Paint());
                safeDecodeBimtapFile.recycle();
                safeDecodeBimtapFile = createBitmap;
            }
            OutputStream fileOutputStream = new FileOutputStream(str);
            if (options == null || options.outMimeType == null || !options.outMimeType.contains("png")) {
                safeDecodeBimtapFile.compress(CompressFormat.JPEG, i2, fileOutputStream);
            } else {
                safeDecodeBimtapFile.compress(CompressFormat.PNG, i2, fileOutputStream);
            }
            try {
                fileOutputStream.close();
            } catch (Exception e3) {
                e3.printStackTrace();
            }
            safeDecodeBimtapFile.recycle();
        } else {
            throw new IOException("");
        }
    }

    private static void revitionImageSize(String str, int i, int i2) throws IOException {
        if (i <= 0) {
            throw new IllegalArgumentException("size must be greater than 0!");
        } else if (!isFileExisted(str)) {
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
            Bitmap safeDecodeBimtapFile = safeDecodeBimtapFile(str, options);
            if (safeDecodeBimtapFile == null) {
                throw new IOException("Bitmap decode error!");
            }
            deleteDependon(str);
            makesureFileExist(str);
            OutputStream fileOutputStream = new FileOutputStream(str);
            if (options == null || options.outMimeType == null || !options.outMimeType.contains("png")) {
                safeDecodeBimtapFile.compress(CompressFormat.JPEG, i2, fileOutputStream);
            } else {
                safeDecodeBimtapFile.compress(CompressFormat.PNG, i2, fileOutputStream);
            }
            try {
                fileOutputStream.close();
            } catch (Exception e2) {
                e2.printStackTrace();
            }
            safeDecodeBimtapFile.recycle();
        } else {
            throw new IOException("");
        }
    }

    public static boolean revitionPostImageSize(Context context, String str) {
        try {
            if (NetworkHelper.isWifiValid(context)) {
                revitionImageSizeHD(str, 1600, 75);
            } else {
                revitionImageSize(str, 1024, 75);
            }
            return true;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    private static Bitmap safeDecodeBimtapFile(String str, Options options) {
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
                        return bitmap2;
                    } catch (IOException e) {
                        e.printStackTrace();
                        return bitmap2;
                    }
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

    private static void delete(File file) {
        if (file != null && file.exists() && !file.delete()) {
            throw new RuntimeException(file.getAbsolutePath() + " doesn't be deleted!");
        }
    }

    private static boolean deleteDependon(String str) {
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

    private static boolean isFileExisted(String str) {
        if (TextUtils.isEmpty(str)) {
            return false;
        }
        File file = new File(str);
        if (file == null || !file.exists()) {
            return false;
        }
        return true;
    }

    private static boolean isParentExist(File file) {
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

    private static void makesureFileExist(String str) {
        if (str != null) {
            File file = new File(str);
            if (file != null && !file.exists() && isParentExist(file)) {
                if (file.exists()) {
                    delete(file);
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
