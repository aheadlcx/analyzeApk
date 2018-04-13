package com.getkeepsafe.relinker;

import java.io.Closeable;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class a implements com.getkeepsafe.relinker.b.a {
    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void a(android.content.Context r14, java.lang.String[] r15, java.lang.String r16, java.io.File r17, com.getkeepsafe.relinker.c r18) {
        /*
        r13 = this;
        r3 = 0;
        r6 = r14.getApplicationInfo();	 Catch:{ all -> 0x0142 }
        r2 = 0;
    L_0x0006:
        r4 = r2 + 1;
        r5 = 5;
        if (r2 >= r5) goto L_0x015c;
    L_0x000b:
        r5 = new java.util.zip.ZipFile;	 Catch:{ IOException -> 0x0028 }
        r2 = new java.io.File;	 Catch:{ IOException -> 0x0028 }
        r7 = r6.sourceDir;	 Catch:{ IOException -> 0x0028 }
        r2.<init>(r7);	 Catch:{ IOException -> 0x0028 }
        r7 = 1;
        r5.<init>(r2, r7);	 Catch:{ IOException -> 0x0028 }
    L_0x0018:
        if (r5 != 0) goto L_0x002b;
    L_0x001a:
        r2 = "FATAL! Couldn't find application APK!";
        r0 = r18;
        r0.a(r2);	 Catch:{ all -> 0x0085 }
        if (r5 == 0) goto L_0x0027;
    L_0x0024:
        r5.close();	 Catch:{ IOException -> 0x013c }
    L_0x0027:
        return;
    L_0x0028:
        r2 = move-exception;
        r2 = r4;
        goto L_0x0006;
    L_0x002b:
        r2 = 0;
    L_0x002c:
        r6 = r2 + 1;
        r3 = 5;
        if (r2 >= r3) goto L_0x012a;
    L_0x0031:
        r4 = 0;
        r3 = 0;
        r7 = r15.length;	 Catch:{ all -> 0x0085 }
        r2 = 0;
        r12 = r2;
        r2 = r3;
        r3 = r4;
        r4 = r12;
    L_0x0039:
        if (r4 >= r7) goto L_0x0158;
    L_0x003b:
        r2 = r15[r4];	 Catch:{ all -> 0x0085 }
        r3 = new java.lang.StringBuilder;	 Catch:{ all -> 0x0085 }
        r3.<init>();	 Catch:{ all -> 0x0085 }
        r8 = "lib";
        r3 = r3.append(r8);	 Catch:{ all -> 0x0085 }
        r8 = java.io.File.separatorChar;	 Catch:{ all -> 0x0085 }
        r3 = r3.append(r8);	 Catch:{ all -> 0x0085 }
        r2 = r3.append(r2);	 Catch:{ all -> 0x0085 }
        r3 = java.io.File.separatorChar;	 Catch:{ all -> 0x0085 }
        r2 = r2.append(r3);	 Catch:{ all -> 0x0085 }
        r0 = r16;
        r2 = r2.append(r0);	 Catch:{ all -> 0x0085 }
        r3 = r2.toString();	 Catch:{ all -> 0x0085 }
        r2 = r5.getEntry(r3);	 Catch:{ all -> 0x0085 }
        if (r2 == 0) goto L_0x008d;
    L_0x0069:
        r4 = r2;
        r2 = r3;
    L_0x006b:
        if (r2 == 0) goto L_0x007b;
    L_0x006d:
        r3 = "Looking for %s in APK...";
        r7 = 1;
        r7 = new java.lang.Object[r7];	 Catch:{ all -> 0x0085 }
        r8 = 0;
        r7[r8] = r2;	 Catch:{ all -> 0x0085 }
        r0 = r18;
        r0.a(r3, r7);	 Catch:{ all -> 0x0085 }
    L_0x007b:
        if (r4 != 0) goto L_0x0098;
    L_0x007d:
        if (r2 == 0) goto L_0x0090;
    L_0x007f:
        r3 = new com.getkeepsafe.relinker.MissingLibraryException;	 Catch:{ all -> 0x0085 }
        r3.<init>(r2);	 Catch:{ all -> 0x0085 }
        throw r3;	 Catch:{ all -> 0x0085 }
    L_0x0085:
        r2 = move-exception;
        r3 = r5;
    L_0x0087:
        if (r3 == 0) goto L_0x008c;
    L_0x0089:
        r3.close();	 Catch:{ IOException -> 0x013f }
    L_0x008c:
        throw r2;
    L_0x008d:
        r4 = r4 + 1;
        goto L_0x0039;
    L_0x0090:
        r2 = new com.getkeepsafe.relinker.MissingLibraryException;	 Catch:{ all -> 0x0085 }
        r0 = r16;
        r2.<init>(r0);	 Catch:{ all -> 0x0085 }
        throw r2;	 Catch:{ all -> 0x0085 }
    L_0x0098:
        r3 = "Found %s! Extracting...";
        r7 = 1;
        r7 = new java.lang.Object[r7];	 Catch:{ all -> 0x0085 }
        r8 = 0;
        r7[r8] = r2;	 Catch:{ all -> 0x0085 }
        r0 = r18;
        r0.a(r3, r7);	 Catch:{ all -> 0x0085 }
        r2 = r17.exists();	 Catch:{ IOException -> 0x00b5 }
        if (r2 != 0) goto L_0x00b9;
    L_0x00ac:
        r2 = r17.createNewFile();	 Catch:{ IOException -> 0x00b5 }
        if (r2 != 0) goto L_0x00b9;
    L_0x00b2:
        r2 = r6;
        goto L_0x002c;
    L_0x00b5:
        r2 = move-exception;
        r2 = r6;
        goto L_0x002c;
    L_0x00b9:
        r2 = 0;
        r3 = 0;
        r4 = r5.getInputStream(r4);	 Catch:{ FileNotFoundException -> 0x0106, IOException -> 0x0113, all -> 0x011f }
        r2 = new java.io.FileOutputStream;	 Catch:{ FileNotFoundException -> 0x0151, IOException -> 0x014c, all -> 0x0145 }
        r0 = r17;
        r2.<init>(r0);	 Catch:{ FileNotFoundException -> 0x0151, IOException -> 0x014c, all -> 0x0145 }
        r8 = r13.a(r4, r2);	 Catch:{ FileNotFoundException -> 0x0155, IOException -> 0x014f, all -> 0x0147 }
        r3 = r2.getFD();	 Catch:{ FileNotFoundException -> 0x0155, IOException -> 0x014f, all -> 0x0147 }
        r3.sync();	 Catch:{ FileNotFoundException -> 0x0155, IOException -> 0x014f, all -> 0x0147 }
        r10 = r17.length();	 Catch:{ FileNotFoundException -> 0x0155, IOException -> 0x014f, all -> 0x0147 }
        r3 = (r8 > r10 ? 1 : (r8 == r10 ? 0 : -1));
        if (r3 == 0) goto L_0x00e2;
    L_0x00d9:
        r13.a(r4);	 Catch:{ all -> 0x0085 }
        r13.a(r2);	 Catch:{ all -> 0x0085 }
        r2 = r6;
        goto L_0x002c;
    L_0x00e2:
        r13.a(r4);	 Catch:{ all -> 0x0085 }
        r13.a(r2);	 Catch:{ all -> 0x0085 }
        r2 = 1;
        r3 = 0;
        r0 = r17;
        r0.setReadable(r2, r3);	 Catch:{ all -> 0x0085 }
        r2 = 1;
        r3 = 0;
        r0 = r17;
        r0.setExecutable(r2, r3);	 Catch:{ all -> 0x0085 }
        r2 = 1;
        r0 = r17;
        r0.setWritable(r2);	 Catch:{ all -> 0x0085 }
        if (r5 == 0) goto L_0x0027;
    L_0x00fe:
        r5.close();	 Catch:{ IOException -> 0x0103 }
        goto L_0x0027;
    L_0x0103:
        r2 = move-exception;
        goto L_0x0027;
    L_0x0106:
        r4 = move-exception;
        r12 = r3;
        r3 = r2;
        r2 = r12;
    L_0x010a:
        r13.a(r3);	 Catch:{ all -> 0x0085 }
        r13.a(r2);	 Catch:{ all -> 0x0085 }
        r2 = r6;
        goto L_0x002c;
    L_0x0113:
        r4 = move-exception;
        r4 = r2;
        r2 = r3;
    L_0x0116:
        r13.a(r4);	 Catch:{ all -> 0x0085 }
        r13.a(r2);	 Catch:{ all -> 0x0085 }
        r2 = r6;
        goto L_0x002c;
    L_0x011f:
        r4 = move-exception;
        r12 = r4;
        r4 = r2;
        r2 = r12;
    L_0x0123:
        r13.a(r4);	 Catch:{ all -> 0x0085 }
        r13.a(r3);	 Catch:{ all -> 0x0085 }
        throw r2;	 Catch:{ all -> 0x0085 }
    L_0x012a:
        r2 = "FATAL! Couldn't extract the library from the APK!";
        r0 = r18;
        r0.a(r2);	 Catch:{ all -> 0x0085 }
        if (r5 == 0) goto L_0x0027;
    L_0x0134:
        r5.close();	 Catch:{ IOException -> 0x0139 }
        goto L_0x0027;
    L_0x0139:
        r2 = move-exception;
        goto L_0x0027;
    L_0x013c:
        r2 = move-exception;
        goto L_0x0027;
    L_0x013f:
        r3 = move-exception;
        goto L_0x008c;
    L_0x0142:
        r2 = move-exception;
        goto L_0x0087;
    L_0x0145:
        r2 = move-exception;
        goto L_0x0123;
    L_0x0147:
        r3 = move-exception;
        r12 = r3;
        r3 = r2;
        r2 = r12;
        goto L_0x0123;
    L_0x014c:
        r2 = move-exception;
        r2 = r3;
        goto L_0x0116;
    L_0x014f:
        r3 = move-exception;
        goto L_0x0116;
    L_0x0151:
        r2 = move-exception;
        r2 = r3;
        r3 = r4;
        goto L_0x010a;
    L_0x0155:
        r3 = move-exception;
        r3 = r4;
        goto L_0x010a;
    L_0x0158:
        r4 = r2;
        r2 = r3;
        goto L_0x006b;
    L_0x015c:
        r5 = r3;
        goto L_0x0018;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.getkeepsafe.relinker.a.a(android.content.Context, java.lang.String[], java.lang.String, java.io.File, com.getkeepsafe.relinker.c):void");
    }

    private long a(InputStream inputStream, OutputStream outputStream) throws IOException {
        long j = 0;
        byte[] bArr = new byte[4096];
        while (true) {
            int read = inputStream.read(bArr);
            if (read == -1) {
                outputStream.flush();
                return j;
            }
            outputStream.write(bArr, 0, read);
            j += (long) read;
        }
    }

    private void a(Closeable closeable) {
        if (closeable != null) {
            try {
                closeable.close();
            } catch (IOException e) {
            }
        }
    }
}
