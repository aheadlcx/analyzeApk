package com.bumptech.glide.load.resource.b;

import com.bumptech.glide.load.d;
import com.bumptech.glide.load.engine.j;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

public class c<T> implements d<File, T> {
    private static final a a = new a();
    private d<InputStream, T> b;
    private final a c;

    static class a {
        a() {
        }

        public InputStream a(File file) throws FileNotFoundException {
            return new FileInputStream(file);
        }
    }

    public c(d<InputStream, T> dVar) {
        this(dVar, a);
    }

    c(d<InputStream, T> dVar, a aVar) {
        this.b = dVar;
        this.c = aVar;
    }

    public j<T> a(File file, int i, int i2) throws IOException {
        InputStream inputStream = null;
        try {
            inputStream = this.c.a(file);
            j<T> a = this.b.a(inputStream, i, i2);
            if (inputStream != null) {
                try {
                    inputStream.close();
                } catch (IOException e) {
                }
            }
            return a;
        } catch (Throwable th) {
            if (inputStream != null) {
                try {
                    inputStream.close();
                } catch (IOException e2) {
                }
            }
        }
    }

    public String a() {
        return "";
    }
}
