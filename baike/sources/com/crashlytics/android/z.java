package com.crashlytics.android;

import com.crashlytics.android.internal.v;
import java.io.File;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

class z {
    private final File a;
    private final Map<String, String> b;

    public z(File file) {
        this(file, Collections.emptyMap());
    }

    public z(File file, Map<String, String> map) {
        this.a = file;
        this.b = new HashMap(map);
        if (this.a.length() == 0) {
            this.b.putAll(ab.a);
        }
    }

    public File d() {
        return this.a;
    }

    public String b() {
        return d().getName();
    }

    public String c() {
        String b = b();
        return b.substring(0, b.lastIndexOf(46));
    }

    public Map<String, String> e() {
        return Collections.unmodifiableMap(this.b);
    }

    public boolean a() {
        v.a().b().a(Crashlytics.TAG, "Removing report at " + this.a.getPath());
        return this.a.delete();
    }
}
