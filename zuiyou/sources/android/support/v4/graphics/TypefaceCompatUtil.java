package android.support.v4.graphics;

import android.content.Context;
import android.content.res.Resources;
import android.os.Process;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.annotation.RestrictTo;
import android.support.annotation.RestrictTo.Scope;
import android.util.Log;
import java.io.Closeable;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.channels.FileChannel.MapMode;

@RestrictTo({Scope.LIBRARY_GROUP})
public class TypefaceCompatUtil {
    private static final String CACHE_FILE_PREFIX = ".font";
    private static final String TAG = "TypefaceCompatUtil";

    private TypefaceCompatUtil() {
    }

    @Nullable
    public static File getTempFile(Context context) {
        String str = CACHE_FILE_PREFIX + Process.myPid() + "-" + Process.myTid() + "-";
        int i = 0;
        while (i < 100) {
            File file = new File(context.getCacheDir(), str + i);
            try {
                if (file.createNewFile()) {
                    return file;
                }
                i++;
            } catch (IOException e) {
            }
        }
        return null;
    }

    @Nullable
    @RequiresApi(19)
    private static ByteBuffer mmap(File file) {
        Throwable th;
        Throwable th2;
        try {
            FileInputStream fileInputStream = new FileInputStream(file);
            Throwable th3 = null;
            try {
                FileChannel channel = fileInputStream.getChannel();
                ByteBuffer map = channel.map(MapMode.READ_ONLY, 0, channel.size());
                if (fileInputStream == null) {
                    return map;
                }
                if (null != null) {
                    try {
                        fileInputStream.close();
                        return map;
                    } catch (Throwable th4) {
                        th3.addSuppressed(th4);
                        return map;
                    }
                }
                fileInputStream.close();
                return map;
            } catch (Throwable th42) {
                Throwable th5 = th42;
                th42 = th2;
                th2 = th5;
            }
            if (fileInputStream != null) {
                if (th42 != null) {
                    try {
                        fileInputStream.close();
                    } catch (Throwable th6) {
                        th42.addSuppressed(th6);
                    }
                } else {
                    fileInputStream.close();
                }
            }
            throw th2;
            throw th2;
        } catch (IOException e) {
            return null;
        }
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    @android.support.annotation.Nullable
    @android.support.annotation.RequiresApi(19)
    public static java.nio.ByteBuffer mmap(android.content.Context r12, android.os.CancellationSignal r13, android.net.Uri r14) {
        /*
        r6 = 0;
        r0 = r12.getContentResolver();
        r1 = "r";
        r7 = r0.openFileDescriptor(r14, r1, r13);	 Catch:{ IOException -> 0x0049 }
        r8 = 0;
        r9 = new java.io.FileInputStream;	 Catch:{ Throwable -> 0x003b, all -> 0x0050 }
        r0 = r7.getFileDescriptor();	 Catch:{ Throwable -> 0x003b, all -> 0x0050 }
        r9.<init>(r0);	 Catch:{ Throwable -> 0x003b, all -> 0x0050 }
        r10 = 0;
        r0 = r9.getChannel();	 Catch:{ Throwable -> 0x005c, all -> 0x007c }
        r4 = r0.size();	 Catch:{ Throwable -> 0x005c, all -> 0x007c }
        r1 = java.nio.channels.FileChannel.MapMode.READ_ONLY;	 Catch:{ Throwable -> 0x005c, all -> 0x007c }
        r2 = 0;
        r0 = r0.map(r1, r2, r4);	 Catch:{ Throwable -> 0x005c, all -> 0x007c }
        if (r9 == 0) goto L_0x002e;
    L_0x0029:
        if (r6 == 0) goto L_0x004c;
    L_0x002b:
        r9.close();	 Catch:{ Throwable -> 0x0036, all -> 0x0050 }
    L_0x002e:
        if (r7 == 0) goto L_0x0035;
    L_0x0030:
        if (r6 == 0) goto L_0x0058;
    L_0x0032:
        r7.close();	 Catch:{ Throwable -> 0x0053 }
    L_0x0035:
        return r0;
    L_0x0036:
        r1 = move-exception;
        r10.addSuppressed(r1);	 Catch:{ Throwable -> 0x003b, all -> 0x0050 }
        goto L_0x002e;
    L_0x003b:
        r0 = move-exception;
        throw r0;	 Catch:{ all -> 0x003d }
    L_0x003d:
        r1 = move-exception;
        r11 = r1;
        r1 = r0;
        r0 = r11;
    L_0x0041:
        if (r7 == 0) goto L_0x0048;
    L_0x0043:
        if (r1 == 0) goto L_0x0078;
    L_0x0045:
        r7.close();	 Catch:{ Throwable -> 0x0073 }
    L_0x0048:
        throw r0;	 Catch:{ IOException -> 0x0049 }
    L_0x0049:
        r0 = move-exception;
        r0 = r6;
        goto L_0x0035;
    L_0x004c:
        r9.close();	 Catch:{ Throwable -> 0x003b, all -> 0x0050 }
        goto L_0x002e;
    L_0x0050:
        r0 = move-exception;
        r1 = r6;
        goto L_0x0041;
    L_0x0053:
        r1 = move-exception;
        r8.addSuppressed(r1);	 Catch:{ IOException -> 0x0049 }
        goto L_0x0035;
    L_0x0058:
        r7.close();	 Catch:{ IOException -> 0x0049 }
        goto L_0x0035;
    L_0x005c:
        r0 = move-exception;
        throw r0;	 Catch:{ all -> 0x005e }
    L_0x005e:
        r1 = move-exception;
        r11 = r1;
        r1 = r0;
        r0 = r11;
    L_0x0062:
        if (r9 == 0) goto L_0x0069;
    L_0x0064:
        if (r1 == 0) goto L_0x006f;
    L_0x0066:
        r9.close();	 Catch:{ Throwable -> 0x006a, all -> 0x0050 }
    L_0x0069:
        throw r0;	 Catch:{ Throwable -> 0x003b, all -> 0x0050 }
    L_0x006a:
        r2 = move-exception;
        r1.addSuppressed(r2);	 Catch:{ Throwable -> 0x003b, all -> 0x0050 }
        goto L_0x0069;
    L_0x006f:
        r9.close();	 Catch:{ Throwable -> 0x003b, all -> 0x0050 }
        goto L_0x0069;
    L_0x0073:
        r2 = move-exception;
        r1.addSuppressed(r2);	 Catch:{ IOException -> 0x0049 }
        goto L_0x0048;
    L_0x0078:
        r7.close();	 Catch:{ IOException -> 0x0049 }
        goto L_0x0048;
    L_0x007c:
        r0 = move-exception;
        r1 = r6;
        goto L_0x0062;
        */
        throw new UnsupportedOperationException("Method not decompiled: android.support.v4.graphics.TypefaceCompatUtil.mmap(android.content.Context, android.os.CancellationSignal, android.net.Uri):java.nio.ByteBuffer");
    }

    @Nullable
    @RequiresApi(19)
    public static ByteBuffer copyToDirectBuffer(Context context, Resources resources, int i) {
        ByteBuffer byteBuffer = null;
        File tempFile = getTempFile(context);
        if (tempFile != null) {
            try {
                if (copyToFile(tempFile, resources, i)) {
                    byteBuffer = mmap(tempFile);
                    tempFile.delete();
                }
            } finally {
                tempFile.delete();
            }
        }
        return byteBuffer;
    }

    public static boolean copyToFile(File file, InputStream inputStream) {
        IOException e;
        Throwable th;
        Closeable fileOutputStream;
        try {
            fileOutputStream = new FileOutputStream(file, false);
            try {
                byte[] bArr = new byte[1024];
                while (true) {
                    int read = inputStream.read(bArr);
                    if (read != -1) {
                        fileOutputStream.write(bArr, 0, read);
                    } else {
                        closeQuietly(fileOutputStream);
                        return true;
                    }
                }
            } catch (IOException e2) {
                e = e2;
                try {
                    Log.e(TAG, "Error copying resource contents to temp file: " + e.getMessage());
                    closeQuietly(fileOutputStream);
                    return false;
                } catch (Throwable th2) {
                    th = th2;
                    closeQuietly(fileOutputStream);
                    throw th;
                }
            }
        } catch (IOException e3) {
            e = e3;
            fileOutputStream = null;
            Log.e(TAG, "Error copying resource contents to temp file: " + e.getMessage());
            closeQuietly(fileOutputStream);
            return false;
        } catch (Throwable th3) {
            th = th3;
            fileOutputStream = null;
            closeQuietly(fileOutputStream);
            throw th;
        }
    }

    public static boolean copyToFile(File file, Resources resources, int i) {
        Closeable closeable = null;
        try {
            closeable = resources.openRawResource(i);
            boolean copyToFile = copyToFile(file, closeable);
            return copyToFile;
        } finally {
            closeQuietly(closeable);
        }
    }

    public static void closeQuietly(Closeable closeable) {
        if (closeable != null) {
            try {
                closeable.close();
            } catch (IOException e) {
            }
        }
    }
}
