package com.nostra13.universalimageloader.a.a.a;

import android.graphics.Bitmap;
import android.graphics.Bitmap.CompressFormat;
import com.facebook.cache.disk.DefaultDiskStorage.FileType;
import com.nostra13.universalimageloader.a.a.b;
import com.nostra13.universalimageloader.b.d;
import com.nostra13.universalimageloader.b.d$a;
import java.io.BufferedOutputStream;
import java.io.Closeable;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

public abstract class a implements b {
    public static final CompressFormat a = CompressFormat.PNG;
    protected final File b;
    protected final File c;
    protected final com.nostra13.universalimageloader.a.a.b.a d;
    protected int e;
    protected CompressFormat f;
    protected int g;

    public a(File file) {
        this(file, null);
    }

    public a(File file, File file2) {
        this(file, file2, com.nostra13.universalimageloader.core.a.b());
    }

    public a(File file, File file2, com.nostra13.universalimageloader.a.a.b.a aVar) {
        this.e = 32768;
        this.f = a;
        this.g = 100;
        if (file == null) {
            throw new IllegalArgumentException("cacheDir argument must be not null");
        } else if (aVar == null) {
            throw new IllegalArgumentException("fileNameGenerator argument must be not null");
        } else {
            this.b = file;
            this.c = file2;
            this.d = aVar;
        }
    }

    public File a(String str) {
        return b(str);
    }

    public boolean a(String str, InputStream inputStream, d$a d_a) throws IOException {
        boolean a;
        Throwable th;
        File b = b(str);
        File file = new File(b.getAbsolutePath() + FileType.TEMP);
        Closeable bufferedOutputStream;
        try {
            bufferedOutputStream = new BufferedOutputStream(new FileOutputStream(file), this.e);
            a = d.a(inputStream, bufferedOutputStream, d_a, this.e);
            try {
                d.a(bufferedOutputStream);
                d.a((Closeable) inputStream);
                if (a && !file.renameTo(b)) {
                    a = false;
                }
                if (!a) {
                    file.delete();
                }
                return a;
            } catch (Throwable th2) {
                th = th2;
                d.a((Closeable) inputStream);
                if (a && !file.renameTo(b)) {
                    a = false;
                }
                if (!a) {
                    file.delete();
                }
                throw th;
            }
        } catch (Throwable th3) {
            th = th3;
            a = false;
            d.a((Closeable) inputStream);
            a = false;
            if (a) {
                file.delete();
            }
            throw th;
        }
    }

    public boolean a(String str, Bitmap bitmap) throws IOException {
        File b = b(str);
        File file = new File(b.getAbsolutePath() + FileType.TEMP);
        Closeable bufferedOutputStream = new BufferedOutputStream(new FileOutputStream(file), this.e);
        try {
            boolean compress = bitmap.compress(this.f, this.g, bufferedOutputStream);
            d.a(bufferedOutputStream);
            if (compress && !file.renameTo(b)) {
                compress = false;
            }
            if (!compress) {
                file.delete();
            }
            bitmap.recycle();
            return compress;
        } catch (Throwable th) {
            d.a(bufferedOutputStream);
            file.delete();
        }
    }

    public void a() {
        File[] listFiles = this.b.listFiles();
        if (listFiles != null) {
            for (File delete : listFiles) {
                delete.delete();
            }
        }
    }

    protected File b(String str) {
        String a = this.d.a(str);
        File file = this.b;
        if (!(this.b.exists() || this.b.mkdirs() || this.c == null || (!this.c.exists() && !this.c.mkdirs()))) {
            file = this.c;
        }
        return new File(file, a);
    }
}
