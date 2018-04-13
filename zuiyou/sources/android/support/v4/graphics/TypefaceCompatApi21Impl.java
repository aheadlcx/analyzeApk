package android.support.v4.graphics;

import android.os.ParcelFileDescriptor;
import android.support.annotation.RequiresApi;
import android.support.annotation.RestrictTo;
import android.support.annotation.RestrictTo.Scope;
import android.system.ErrnoException;
import android.system.Os;
import android.system.OsConstants;
import java.io.File;

@RequiresApi(21)
@RestrictTo({Scope.LIBRARY_GROUP})
class TypefaceCompatApi21Impl extends TypefaceCompatBaseImpl {
    private static final String TAG = "TypefaceCompatApi21Impl";

    TypefaceCompatApi21Impl() {
    }

    private File getFile(ParcelFileDescriptor parcelFileDescriptor) {
        try {
            String readlink = Os.readlink("/proc/self/fd/" + parcelFileDescriptor.getFd());
            if (OsConstants.S_ISREG(Os.stat(readlink).st_mode)) {
                return new File(readlink);
            }
            return null;
        } catch (ErrnoException e) {
            return null;
        }
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public android.graphics.Typeface createFromFontInfo(android.content.Context r8, android.os.CancellationSignal r9, @android.support.annotation.NonNull android.support.v4.provider.FontsContractCompat.FontInfo[] r10, int r11) {
        /*
        r7 = this;
        r0 = 0;
        r1 = r10.length;
        r2 = 1;
        if (r1 >= r2) goto L_0x0006;
    L_0x0005:
        return r0;
    L_0x0006:
        r1 = r7.findBestInfo(r10, r11);
        r2 = r8.getContentResolver();
        r1 = r1.getUri();	 Catch:{ IOException -> 0x0057 }
        r3 = "r";
        r3 = r2.openFileDescriptor(r1, r3, r9);	 Catch:{ IOException -> 0x0057 }
        r2 = 0;
        r1 = r7.getFile(r3);	 Catch:{ Throwable -> 0x0049, all -> 0x005d }
        if (r1 == 0) goto L_0x0026;
    L_0x0020:
        r4 = r1.canRead();	 Catch:{ Throwable -> 0x0049, all -> 0x005d }
        if (r4 != 0) goto L_0x007d;
    L_0x0026:
        r4 = new java.io.FileInputStream;	 Catch:{ Throwable -> 0x0049, all -> 0x005d }
        r1 = r3.getFileDescriptor();	 Catch:{ Throwable -> 0x0049, all -> 0x005d }
        r4.<init>(r1);	 Catch:{ Throwable -> 0x0049, all -> 0x005d }
        r5 = 0;
        r1 = super.createFromInputStream(r8, r4);	 Catch:{ Throwable -> 0x0069, all -> 0x009d }
        if (r4 == 0) goto L_0x003b;
    L_0x0036:
        if (r0 == 0) goto L_0x0059;
    L_0x0038:
        r4.close();	 Catch:{ Throwable -> 0x0044, all -> 0x005d }
    L_0x003b:
        if (r3 == 0) goto L_0x0042;
    L_0x003d:
        if (r0 == 0) goto L_0x0065;
    L_0x003f:
        r3.close();	 Catch:{ Throwable -> 0x0060 }
    L_0x0042:
        r0 = r1;
        goto L_0x0005;
    L_0x0044:
        r4 = move-exception;
        r5.addSuppressed(r4);	 Catch:{ Throwable -> 0x0049, all -> 0x005d }
        goto L_0x003b;
    L_0x0049:
        r1 = move-exception;
        throw r1;	 Catch:{ all -> 0x004b }
    L_0x004b:
        r2 = move-exception;
        r6 = r2;
        r2 = r1;
        r1 = r6;
    L_0x004f:
        if (r3 == 0) goto L_0x0056;
    L_0x0051:
        if (r2 == 0) goto L_0x0099;
    L_0x0053:
        r3.close();	 Catch:{ Throwable -> 0x0094 }
    L_0x0056:
        throw r1;	 Catch:{ IOException -> 0x0057 }
    L_0x0057:
        r1 = move-exception;
        goto L_0x0005;
    L_0x0059:
        r4.close();	 Catch:{ Throwable -> 0x0049, all -> 0x005d }
        goto L_0x003b;
    L_0x005d:
        r1 = move-exception;
        r2 = r0;
        goto L_0x004f;
    L_0x0060:
        r3 = move-exception;
        r2.addSuppressed(r3);	 Catch:{ IOException -> 0x0057 }
        goto L_0x0042;
    L_0x0065:
        r3.close();	 Catch:{ IOException -> 0x0057 }
        goto L_0x0042;
    L_0x0069:
        r2 = move-exception;
        throw r2;	 Catch:{ all -> 0x006b }
    L_0x006b:
        r1 = move-exception;
    L_0x006c:
        if (r4 == 0) goto L_0x0073;
    L_0x006e:
        if (r2 == 0) goto L_0x0079;
    L_0x0070:
        r4.close();	 Catch:{ Throwable -> 0x0074, all -> 0x005d }
    L_0x0073:
        throw r1;	 Catch:{ Throwable -> 0x0049, all -> 0x005d }
    L_0x0074:
        r4 = move-exception;
        r2.addSuppressed(r4);	 Catch:{ Throwable -> 0x0049, all -> 0x005d }
        goto L_0x0073;
    L_0x0079:
        r4.close();	 Catch:{ Throwable -> 0x0049, all -> 0x005d }
        goto L_0x0073;
    L_0x007d:
        r1 = android.graphics.Typeface.createFromFile(r1);	 Catch:{ Throwable -> 0x0049, all -> 0x005d }
        if (r3 == 0) goto L_0x0088;
    L_0x0083:
        if (r0 == 0) goto L_0x0090;
    L_0x0085:
        r3.close();	 Catch:{ Throwable -> 0x008b }
    L_0x0088:
        r0 = r1;
        goto L_0x0005;
    L_0x008b:
        r3 = move-exception;
        r2.addSuppressed(r3);	 Catch:{ IOException -> 0x0057 }
        goto L_0x0088;
    L_0x0090:
        r3.close();	 Catch:{ IOException -> 0x0057 }
        goto L_0x0088;
    L_0x0094:
        r3 = move-exception;
        r2.addSuppressed(r3);	 Catch:{ IOException -> 0x0057 }
        goto L_0x0056;
    L_0x0099:
        r3.close();	 Catch:{ IOException -> 0x0057 }
        goto L_0x0056;
    L_0x009d:
        r1 = move-exception;
        r2 = r0;
        goto L_0x006c;
        */
        throw new UnsupportedOperationException("Method not decompiled: android.support.v4.graphics.TypefaceCompatApi21Impl.createFromFontInfo(android.content.Context, android.os.CancellationSignal, android.support.v4.provider.FontsContractCompat$FontInfo[], int):android.graphics.Typeface");
    }
}
