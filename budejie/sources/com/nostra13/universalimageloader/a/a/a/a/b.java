package com.nostra13.universalimageloader.a.a.a.a;

import android.graphics.Bitmap;
import android.graphics.Bitmap.CompressFormat;
import com.facebook.common.time.Clock;
import com.nostra13.universalimageloader.a.a.a.a.a.c;
import com.nostra13.universalimageloader.a.a.b.a;
import com.nostra13.universalimageloader.b.d;
import com.nostra13.universalimageloader.b.d$a;
import com.nostra13.universalimageloader.b.e;
import java.io.BufferedOutputStream;
import java.io.Closeable;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;

public class b implements com.nostra13.universalimageloader.a.a.b {
    public static final CompressFormat a = CompressFormat.PNG;
    protected a b;
    protected final a c;
    protected int d;
    protected CompressFormat e;
    protected int f;
    private File g;

    public b(File file, a aVar, long j) {
        this(file, aVar, j, 0);
    }

    public b(File file, a aVar, long j, int i) {
        this.d = 32768;
        this.e = a;
        this.f = 100;
        if (file == null) {
            throw new IllegalArgumentException("cacheDir argument must be not null");
        } else if (j < 0) {
            throw new IllegalArgumentException("cacheMaxSize argument must be positive number");
        } else if (i < 0) {
            throw new IllegalArgumentException("cacheMaxFileCount argument must be positive number");
        } else if (aVar == null) {
            throw new IllegalArgumentException("fileNameGenerator argument must be not null");
        } else {
            long j2;
            int i2;
            if (j == 0) {
                j2 = Clock.MAX_TIME;
            } else {
                j2 = j;
            }
            if (i == 0) {
                i2 = Integer.MAX_VALUE;
            } else {
                i2 = i;
            }
            this.c = aVar;
            a(file, this.g, j2, i2);
        }
    }

    private void a(File file, File file2, long j, int i) {
        try {
            this.b = a.a(file, 1, 1, j, i);
        } catch (Throwable e) {
            e.a(e);
            if (file2 != null) {
                a(file2, null, j, i);
            }
            if (this.b == null) {
                throw new RuntimeException("Can't initialize disk cache", e);
            }
        }
    }

    public File a(String str) {
        c a;
        Throwable e;
        Throwable th;
        File file = null;
        try {
            a = this.b.a(b(str));
            if (a != null) {
                try {
                    file = a.a(0);
                } catch (IOException e2) {
                    e = e2;
                    try {
                        e.a(e);
                        if (a != null) {
                            a.close();
                        }
                        return file;
                    } catch (Throwable th2) {
                        th = th2;
                        if (a != null) {
                            a.close();
                        }
                        throw th;
                    }
                }
            }
            if (a != null) {
                a.close();
            }
        } catch (IOException e3) {
            e = e3;
            a = file;
            e.a(e);
            if (a != null) {
                a.close();
            }
            return file;
        } catch (Throwable e4) {
            a = file;
            th = e4;
            if (a != null) {
                a.close();
            }
            throw th;
        }
        return file;
    }

    public boolean a(String str, InputStream inputStream, d$a d_a) throws IOException {
        boolean z = false;
        a.a b = this.b.b(b(str));
        if (b != null) {
            Closeable bufferedOutputStream = new BufferedOutputStream(b.a(0), this.d);
            try {
                z = d.a(inputStream, bufferedOutputStream, d_a, this.d);
                d.a(bufferedOutputStream);
                if (z) {
                    b.a();
                } else {
                    b.b();
                }
            } catch (Throwable th) {
                d.a(bufferedOutputStream);
                b.b();
            }
        }
        return z;
    }

    public boolean a(String str, Bitmap bitmap) throws IOException {
        boolean z = false;
        a.a b = this.b.b(b(str));
        if (b != null) {
            Closeable bufferedOutputStream = new BufferedOutputStream(b.a(0), this.d);
            try {
                z = bitmap.compress(this.e, this.f, bufferedOutputStream);
                if (z) {
                    b.a();
                } else {
                    b.b();
                }
            } finally {
                d.a(bufferedOutputStream);
            }
        }
        return z;
    }

    public void a() {
        try {
            this.b.d();
        } catch (Throwable e) {
            e.a(e);
        } finally {
            a(this.b.a(), this.g, this.b.b(), this.b.c());
        }
    }

    private String b(String str) {
        return this.c.a(str);
    }

    public void a(File file) {
        this.g = file;
    }
}
