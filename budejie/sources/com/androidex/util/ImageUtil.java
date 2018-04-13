package com.androidex.util;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.BitmapFactory.Options;
import android.net.Uri;
import android.text.TextUtils;
import android.util.Log;
import com.androidex.widget.asyncimage.AsyncImageView;
import com.nostra13.universalimageloader.core.d;
import com.nostra13.universalimageloader.core.download.ImageDownloader.Scheme;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

public class ImageUtil {
    public static final String TAG = "ImageUtil";

    public static File getImageFile(String str) {
        File file = new File("");
        if (!TextUtils.isEmpty(str) && AsyncImageView.mImageDir != null) {
            return d.a().e().a(str);
        }
        if (Scheme.ofUri(str) == Scheme.FILE) {
            return new File(Scheme.FILE.crop(str));
        }
        return file;
    }

    public static Options getFastOptions() {
        Options options = new Options();
        options.inScaled = false;
        options.inDither = false;
        options.inJustDecodeBounds = false;
        options.inPurgeable = true;
        options.inInputShareable = true;
        return options;
    }

    public static Bitmap loadBitmapFast(Context context, String str) {
        if (TextUtils.isEmpty(str)) {
            return null;
        }
        return loadBitmapFast(context, Uri.parse(str));
    }

    public static Bitmap loadBitmapFast(Context context, Uri uri) {
        if (uri == null) {
            return null;
        }
        return loadBitmapCommon(context, uri, true);
    }

    private static Bitmap loadBitmapCommon(Context context, Uri uri, boolean z) {
        Throwable th;
        Bitmap bitmap = null;
        InputStream openFileInputStream;
        try {
            openFileInputStream = openFileInputStream(uri.getPath());
            if (openFileInputStream == null) {
                IOUtil.closeInStream(openFileInputStream);
            } else {
                try {
                    ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
                    byte[] bArr = new byte[1024];
                    while (true) {
                        int read = openFileInputStream.read(bArr);
                        if (read == -1) {
                            break;
                        }
                        byteArrayOutputStream.write(bArr, 0, read);
                    }
                    byte[] toByteArray = byteArrayOutputStream.toByteArray();
                    bitmap = BitmapFactory.decodeByteArray(toByteArray, 0, toByteArray.length, getFastOptions());
                    IOUtil.closeInStream(openFileInputStream);
                } catch (OutOfMemoryError e) {
                    try {
                        System.gc();
                        IOUtil.closeInStream(openFileInputStream);
                        return bitmap;
                    } catch (Throwable th2) {
                        th = th2;
                        IOUtil.closeInStream(openFileInputStream);
                        throw th;
                    }
                } catch (Exception e2) {
                    IOUtil.closeInStream(openFileInputStream);
                    return bitmap;
                }
            }
        } catch (OutOfMemoryError e3) {
            Object obj = bitmap;
            System.gc();
            IOUtil.closeInStream(openFileInputStream);
            return bitmap;
        } catch (Exception e4) {
            openFileInputStream = bitmap;
            IOUtil.closeInStream(openFileInputStream);
            return bitmap;
        } catch (Throwable th3) {
            Throwable th4 = th3;
            openFileInputStream = bitmap;
            th = th4;
            IOUtil.closeInStream(openFileInputStream);
            throw th;
        }
        return bitmap;
    }

    private static InputStream openFileInputStream(String str) {
        try {
            return new FileInputStream(str);
        } catch (FileNotFoundException e) {
            Log.e(TAG, "openFileInputStream ex = " + e.toString() + ", path=" + str);
            return null;
        }
    }
}
