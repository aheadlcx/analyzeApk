package com.facebook.a;

import com.facebook.common.internal.g;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

public class b implements a {
    private final File a;

    private b(File file) {
        this.a = (File) g.a((Object) file);
    }

    public File c() {
        return this.a;
    }

    public InputStream a() throws IOException {
        return new FileInputStream(this.a);
    }

    public long b() {
        return this.a.length();
    }

    public boolean equals(Object obj) {
        if (obj == null || !(obj instanceof b)) {
            return false;
        }
        return this.a.equals(((b) obj).a);
    }

    public int hashCode() {
        return this.a.hashCode();
    }

    public static b a(File file) {
        return file != null ? new b(file) : null;
    }
}
