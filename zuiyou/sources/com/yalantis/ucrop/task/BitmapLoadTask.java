package com.yalantis.ucrop.task;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.BitmapFactory.Options;
import android.graphics.Matrix;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Build.VERSION;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.text.TextUtils;
import android.util.Log;
import com.yalantis.ucrop.callback.BitmapLoadCallback;
import com.yalantis.ucrop.model.ExifInfo;
import com.yalantis.ucrop.util.BitmapLoadUtils;
import com.yalantis.ucrop.util.FileUtils;
import java.io.Closeable;
import java.io.File;
import java.io.FileDescriptor;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import okhttp3.aa;
import okhttp3.w;
import okhttp3.y$a;
import okio.Okio;

public class BitmapLoadTask extends AsyncTask<Void, Void, BitmapWorkerResult> {
    private static final String TAG = "BitmapWorkerTask";
    private final BitmapLoadCallback mBitmapLoadCallback;
    private final Context mContext;
    private Uri mInputUri;
    private Uri mOutputUri;
    private final int mRequiredHeight;
    private final int mRequiredWidth;

    public static class BitmapWorkerResult {
        Bitmap mBitmapResult;
        Exception mBitmapWorkerException;
        ExifInfo mExifInfo;

        public BitmapWorkerResult(@NonNull Bitmap bitmap, @NonNull ExifInfo exifInfo) {
            this.mBitmapResult = bitmap;
            this.mExifInfo = exifInfo;
        }

        public BitmapWorkerResult(@NonNull Exception exception) {
            this.mBitmapWorkerException = exception;
        }
    }

    public BitmapLoadTask(@NonNull Context context, @NonNull Uri uri, @Nullable Uri uri2, int i, int i2, BitmapLoadCallback bitmapLoadCallback) {
        this.mContext = context;
        this.mInputUri = uri;
        this.mOutputUri = uri2;
        this.mRequiredWidth = i;
        this.mRequiredHeight = i2;
        this.mBitmapLoadCallback = bitmapLoadCallback;
    }

    @NonNull
    protected BitmapWorkerResult doInBackground(Void... voidArr) {
        Exception e;
        boolean z = false;
        Bitmap bitmap = null;
        if (this.mInputUri == null) {
            return new BitmapWorkerResult(new NullPointerException("Input Uri cannot be null"));
        }
        try {
            processInputUri();
            try {
                Closeable openFileDescriptor = this.mContext.getContentResolver().openFileDescriptor(this.mInputUri, "r");
                if (openFileDescriptor == null) {
                    return new BitmapWorkerResult(new NullPointerException("ParcelFileDescriptor was null for given Uri: [" + this.mInputUri + "]"));
                }
                FileDescriptor fileDescriptor = openFileDescriptor.getFileDescriptor();
                Options options = new Options();
                options.inJustDecodeBounds = true;
                BitmapFactory.decodeFileDescriptor(fileDescriptor, null, options);
                if (options.outWidth == -1 || options.outHeight == -1) {
                    return new BitmapWorkerResult(new IllegalArgumentException("Bounds for bitmap could not be retrieved from the Uri: [" + this.mInputUri + "]"));
                }
                options.inSampleSize = BitmapLoadUtils.calculateInSampleSize(options, this.mRequiredWidth, this.mRequiredHeight);
                options.inJustDecodeBounds = false;
                while (!z) {
                    try {
                        bitmap = BitmapFactory.decodeFileDescriptor(fileDescriptor, null, options);
                        z = true;
                    } catch (Throwable e2) {
                        Log.e(TAG, "doInBackground: BitmapFactory.decodeFileDescriptor: ", e2);
                        options.inSampleSize *= 2;
                    }
                }
                if (bitmap == null) {
                    return new BitmapWorkerResult(new IllegalArgumentException("Bitmap could not be decoded from the Uri: [" + this.mInputUri + "]"));
                }
                if (VERSION.SDK_INT >= 16) {
                    BitmapLoadUtils.close(openFileDescriptor);
                }
                int exifOrientation = BitmapLoadUtils.getExifOrientation(this.mContext, this.mInputUri);
                int exifToDegrees = BitmapLoadUtils.exifToDegrees(exifOrientation);
                int exifToTranslation = BitmapLoadUtils.exifToTranslation(exifOrientation);
                ExifInfo exifInfo = new ExifInfo(exifOrientation, exifToDegrees, exifToTranslation);
                Matrix matrix = new Matrix();
                if (exifToDegrees != 0) {
                    matrix.preRotate((float) exifToDegrees);
                }
                if (exifToTranslation != 1) {
                    matrix.postScale((float) exifToTranslation, 1.0f);
                }
                if (matrix.isIdentity()) {
                    return new BitmapWorkerResult(bitmap, exifInfo);
                }
                return new BitmapWorkerResult(BitmapLoadUtils.transformBitmap(bitmap, matrix), exifInfo);
            } catch (Exception e3) {
                return new BitmapWorkerResult(e3);
            }
        } catch (NullPointerException e4) {
            e3 = e4;
            return new BitmapWorkerResult(e3);
        } catch (IOException e5) {
            e3 = e5;
            return new BitmapWorkerResult(e3);
        }
    }

    private void processInputUri() throws NullPointerException, IOException {
        Throwable e;
        String scheme = this.mInputUri.getScheme();
        Log.d(TAG, "Uri scheme: " + scheme);
        if ("http".equals(scheme) || "https".equals(scheme)) {
            try {
                downloadFile(this.mInputUri, this.mOutputUri);
                return;
            } catch (NullPointerException e2) {
                e = e2;
            } catch (IOException e3) {
                e = e3;
            }
        } else if ("content".equals(scheme)) {
            scheme = getFilePath();
            if (TextUtils.isEmpty(scheme) || !new File(scheme).exists()) {
                try {
                    copyFile(this.mInputUri, this.mOutputUri);
                    return;
                } catch (NullPointerException e4) {
                    e = e4;
                    Log.e(TAG, "Copying failed", e);
                    throw e;
                } catch (IOException e5) {
                    e = e5;
                    Log.e(TAG, "Copying failed", e);
                    throw e;
                }
            }
            this.mInputUri = Uri.fromFile(new File(scheme));
            return;
        } else if (!"file".equals(scheme)) {
            Log.e(TAG, "Invalid Uri scheme " + scheme);
            throw new IllegalArgumentException("Invalid Uri scheme" + scheme);
        } else {
            return;
        }
        Log.e(TAG, "Downloading failed", e);
        throw e;
    }

    private String getFilePath() {
        if (ContextCompat.checkSelfPermission(this.mContext, "android.permission.READ_EXTERNAL_STORAGE") == 0) {
            return FileUtils.getPath(this.mContext, this.mInputUri);
        }
        return null;
    }

    private void copyFile(@NonNull Uri uri, @Nullable Uri uri2) throws NullPointerException, IOException {
        Closeable fileOutputStream;
        Throwable th;
        Closeable closeable = null;
        Log.d(TAG, "copyFile");
        if (uri2 == null) {
            throw new NullPointerException("Output Uri is null - cannot copy image");
        }
        try {
            Closeable openInputStream = this.mContext.getContentResolver().openInputStream(uri);
            try {
                fileOutputStream = new FileOutputStream(new File(uri2.getPath()));
                if (openInputStream == null) {
                    try {
                        throw new NullPointerException("InputStream for given input Uri is null");
                    } catch (Throwable th2) {
                        th = th2;
                        closeable = openInputStream;
                        BitmapLoadUtils.close(fileOutputStream);
                        BitmapLoadUtils.close(closeable);
                        this.mInputUri = this.mOutputUri;
                        throw th;
                    }
                }
                byte[] bArr = new byte[1024];
                while (true) {
                    int read = openInputStream.read(bArr);
                    if (read > 0) {
                        fileOutputStream.write(bArr, 0, read);
                    } else {
                        BitmapLoadUtils.close(fileOutputStream);
                        BitmapLoadUtils.close(openInputStream);
                        this.mInputUri = this.mOutputUri;
                        return;
                    }
                }
            } catch (Throwable th3) {
                th = th3;
                fileOutputStream = null;
                closeable = openInputStream;
                BitmapLoadUtils.close(fileOutputStream);
                BitmapLoadUtils.close(closeable);
                this.mInputUri = this.mOutputUri;
                throw th;
            }
        } catch (Throwable th4) {
            th = th4;
            fileOutputStream = null;
            BitmapLoadUtils.close(fileOutputStream);
            BitmapLoadUtils.close(closeable);
            this.mInputUri = this.mOutputUri;
            throw th;
        }
    }

    private void downloadFile(@NonNull Uri uri, @Nullable Uri uri2) throws NullPointerException, IOException {
        aa a;
        Throwable th;
        Log.d(TAG, "downloadFile");
        if (uri2 == null) {
            throw new NullPointerException("Output Uri is null - cannot download image");
        }
        w wVar = new w();
        Closeable source;
        try {
            a = wVar.a(new y$a().a(uri.toString()).d()).a();
            try {
                source = a.g().source();
                try {
                    OutputStream openOutputStream = this.mContext.getContentResolver().openOutputStream(uri2);
                    if (openOutputStream != null) {
                        Closeable sink = Okio.sink(openOutputStream);
                        source.readAll(sink);
                        BitmapLoadUtils.close(source);
                        BitmapLoadUtils.close(sink);
                        if (a != null) {
                            BitmapLoadUtils.close(a.g());
                        }
                        wVar.s().b();
                        this.mInputUri = this.mOutputUri;
                        return;
                    }
                    throw new NullPointerException("OutputStream for given output Uri is null");
                } catch (Throwable th2) {
                    th = th2;
                    BitmapLoadUtils.close(source);
                    BitmapLoadUtils.close(null);
                    if (a != null) {
                        BitmapLoadUtils.close(a.g());
                    }
                    wVar.s().b();
                    this.mInputUri = this.mOutputUri;
                    throw th;
                }
            } catch (Throwable th3) {
                th = th3;
                source = null;
                BitmapLoadUtils.close(source);
                BitmapLoadUtils.close(null);
                if (a != null) {
                    BitmapLoadUtils.close(a.g());
                }
                wVar.s().b();
                this.mInputUri = this.mOutputUri;
                throw th;
            }
        } catch (Throwable th4) {
            th = th4;
            a = null;
            source = null;
            BitmapLoadUtils.close(source);
            BitmapLoadUtils.close(null);
            if (a != null) {
                BitmapLoadUtils.close(a.g());
            }
            wVar.s().b();
            this.mInputUri = this.mOutputUri;
            throw th;
        }
    }

    protected void onPostExecute(@NonNull BitmapWorkerResult bitmapWorkerResult) {
        if (bitmapWorkerResult.mBitmapWorkerException == null) {
            this.mBitmapLoadCallback.onBitmapLoaded(bitmapWorkerResult.mBitmapResult, bitmapWorkerResult.mExifInfo, this.mInputUri.getPath(), this.mOutputUri == null ? null : this.mOutputUri.getPath());
        } else {
            this.mBitmapLoadCallback.onFailure(bitmapWorkerResult.mBitmapWorkerException);
        }
    }
}
