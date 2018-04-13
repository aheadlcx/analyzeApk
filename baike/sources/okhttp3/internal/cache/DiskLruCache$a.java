package okhttp3.internal.cache;

import com.facebook.cache.disk.DefaultDiskStorage.FileType;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Arrays;
import okhttp3.internal.Util;
import okhttp3.internal.cache.DiskLruCache.Snapshot;
import okio.BufferedSink;
import okio.Source;

final class DiskLruCache$a {
    final String a;
    final long[] b;
    final File[] c;
    final File[] d;
    boolean e;
    DiskLruCache$Editor f;
    long g;
    final /* synthetic */ DiskLruCache h;

    DiskLruCache$a(DiskLruCache diskLruCache, String str) {
        this.h = diskLruCache;
        this.a = str;
        this.b = new long[diskLruCache.d];
        this.c = new File[diskLruCache.d];
        this.d = new File[diskLruCache.d];
        StringBuilder append = new StringBuilder(str).append('.');
        int length = append.length();
        for (int i = 0; i < diskLruCache.d; i++) {
            append.append(i);
            this.c[i] = new File(diskLruCache.c, append.toString());
            append.append(FileType.TEMP);
            this.d[i] = new File(diskLruCache.c, append.toString());
            append.setLength(length);
        }
    }

    void a(String[] strArr) throws IOException {
        if (strArr.length != this.h.d) {
            throw b(strArr);
        }
        int i = 0;
        while (i < strArr.length) {
            try {
                this.b[i] = Long.parseLong(strArr[i]);
                i++;
            } catch (NumberFormatException e) {
                throw b(strArr);
            }
        }
    }

    void a(BufferedSink bufferedSink) throws IOException {
        for (long writeDecimalLong : this.b) {
            bufferedSink.writeByte(32).writeDecimalLong(writeDecimalLong);
        }
    }

    private IOException b(String[] strArr) throws IOException {
        throw new IOException("unexpected journal line: " + Arrays.toString(strArr));
    }

    Snapshot a() {
        int i = 0;
        if (Thread.holdsLock(this.h)) {
            Source[] sourceArr = new Source[this.h.d];
            long[] jArr = (long[]) this.b.clone();
            int i2 = 0;
            while (i2 < this.h.d) {
                try {
                    sourceArr[i2] = this.h.b.source(this.c[i2]);
                    i2++;
                } catch (FileNotFoundException e) {
                    while (i < this.h.d && sourceArr[i] != null) {
                        Util.closeQuietly(sourceArr[i]);
                        i++;
                    }
                    try {
                        this.h.a(this);
                    } catch (IOException e2) {
                    }
                    return null;
                }
            }
            return new Snapshot(this.h, this.a, this.g, sourceArr, jArr);
        }
        throw new AssertionError();
    }
}
