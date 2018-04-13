package com.crashlytics.android.internal;

import java.io.Closeable;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.zip.GZIPOutputStream;

/* renamed from: com.crashlytics.android.internal.aj */
public class C0008aj {
    private final File a;
    private final String b;
    private C0010aq c = new C0010aq(this.d);
    private File d;
    private File e = new File(this.a, this.b);

    public C0008aj(File file, String str, String str2) throws IOException {
        this.a = file;
        this.b = str2;
        this.d = new File(file, str);
        if (!this.e.exists()) {
            this.e.mkdirs();
        }
    }

    public void a(byte[] bArr) throws IOException {
        this.c.a(bArr);
    }

    public int a() {
        return this.c.a();
    }

    public void a(String str) throws IOException {
        Closeable fileInputStream;
        Throwable th;
        Closeable closeable = null;
        this.c.close();
        File file = this.d;
        File file2 = new File(this.e, str);
        try {
            fileInputStream = new FileInputStream(file);
            try {
                Closeable gZIPOutputStream = new GZIPOutputStream(new FileOutputStream(file2));
                try {
                    C0003ab.a((InputStream) fileInputStream, (OutputStream) gZIPOutputStream, new byte[1024]);
                    C0003ab.a(fileInputStream, "Failed to close file input stream");
                    C0003ab.a(gZIPOutputStream, "Failed to close gzip output stream");
                    file.delete();
                    this.c = new C0010aq(this.d);
                } catch (Throwable th2) {
                    th = th2;
                    closeable = gZIPOutputStream;
                    C0003ab.a(fileInputStream, "Failed to close file input stream");
                    C0003ab.a(closeable, "Failed to close gzip output stream");
                    file.delete();
                    throw th;
                }
            } catch (Throwable th3) {
                th = th3;
                C0003ab.a(fileInputStream, "Failed to close file input stream");
                C0003ab.a(closeable, "Failed to close gzip output stream");
                file.delete();
                throw th;
            }
        } catch (Throwable th4) {
            th = th4;
            fileInputStream = null;
            C0003ab.a(fileInputStream, "Failed to close file input stream");
            C0003ab.a(closeable, "Failed to close gzip output stream");
            file.delete();
            throw th;
        }
    }

    public List<File> a(int i) {
        List<File> arrayList = new ArrayList();
        for (Object add : this.e.listFiles()) {
            arrayList.add(add);
            if (arrayList.size() > 0) {
                break;
            }
        }
        return arrayList;
    }

    public void a(List<File> list) {
        for (File name : list) {
            C0003ab.c(String.format("deleting sent analytics file %s", new Object[]{name.getName()}));
            name.delete();
        }
    }

    public List<File> c() {
        return Arrays.asList(this.e.listFiles());
    }

    public void d() {
        try {
            this.c.close();
        } catch (IOException e) {
        }
        this.d.delete();
    }

    public boolean b() {
        return this.c.b();
    }

    public boolean a(int i, int i2) {
        return this.c.a(i, i2);
    }
}
