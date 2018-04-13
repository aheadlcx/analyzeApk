package com.crashlytics.android;

import com.crashlytics.android.internal.r;
import com.crashlytics.android.internal.v;
import java.io.File;
import java.io.FilenameFilter;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

final class ab {
    static final Map<String, String> a = Collections.singletonMap("X-CRASHLYTICS-INVALID-SESSION", "1");
    private static final FilenameFilter b = new ac();
    private static final short[] c = new short[]{(short) 10, (short) 20, (short) 30, (short) 60, (short) 120, (short) 300};
    private final Object d = new Object();
    private final v e;
    private Thread f;

    public ab(v vVar) {
        if (vVar == null) {
            throw new IllegalArgumentException("createReportCall must not be null.");
        }
        this.e = vVar;
    }

    public final synchronized void a(float f) {
        if (this.f == null) {
            this.f = new Thread(new ad(this, f), "Crashlytics Report Uploader");
            this.f.start();
        }
    }

    final boolean a(z zVar) {
        boolean z = false;
        synchronized (this.d) {
            try {
                boolean a = this.e.a(new u(r.a(v.a().getContext(), v.a().f()), zVar));
                v.a().b().b(Crashlytics.TAG, "Crashlytics report upload " + (a ? "complete: " : "FAILED: ") + zVar.b());
                if (a) {
                    zVar.a();
                    z = true;
                }
            } catch (Throwable e) {
                v.a().b().a(Crashlytics.TAG, "Error occurred sending report " + zVar, e);
            }
        }
        return z;
    }

    final List<z> a() {
        v.a().b().a(Crashlytics.TAG, "Checking for crash reports...");
        synchronized (this.d) {
            File[] listFiles = v.a().h().listFiles(b);
        }
        List<z> linkedList = new LinkedList();
        for (File file : listFiles) {
            v.a().b().a(Crashlytics.TAG, "Found crash report " + file.getPath());
            linkedList.add(new z(file));
        }
        if (linkedList.size() == 0) {
            v.a().b().a(Crashlytics.TAG, "No reports found.");
        }
        return linkedList;
    }
}
