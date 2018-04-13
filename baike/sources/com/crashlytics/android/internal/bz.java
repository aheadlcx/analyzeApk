package com.crashlytics.android.internal;

import java.io.Closeable;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

final class bz extends C0002aA<C0013ay> {
    private /* synthetic */ InputStream a;
    private /* synthetic */ OutputStream b;
    private /* synthetic */ C0013ay c;

    bz(C0013ay c0013ay, Closeable closeable, boolean z, InputStream inputStream, OutputStream outputStream) {
        this.c = c0013ay;
        this.a = inputStream;
        this.b = outputStream;
        super(closeable, z);
    }

    public final /* synthetic */ Object a() throws aD, IOException {
        byte[] bArr = new byte[this.c.i];
        while (true) {
            int read = this.a.read(bArr);
            if (read == -1) {
                return this.c;
            }
            this.b.write(bArr, 0, read);
        }
    }
}
