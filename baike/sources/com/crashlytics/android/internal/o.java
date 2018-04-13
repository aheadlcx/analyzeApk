package com.crashlytics.android.internal;

import com.crashlytics.android.Crashlytics;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.TreeSet;
import java.util.UUID;
import java.util.concurrent.CopyOnWriteArrayList;

final class o {
    private final bh a;
    private final ah b;
    private aK c;
    private final C0008aj d;
    private final int e;
    private List<C0004ak> f;

    private o(bh bhVar, ah ahVar, C0008aj c0008aj, int i) throws IOException {
        this.f = new CopyOnWriteArrayList();
        this.a = bhVar;
        this.d = c0008aj;
        this.b = ahVar;
        this.b.a();
        this.e = 100;
    }

    o(bh bhVar, ah ahVar, C0008aj c0008aj) throws IOException {
        this(bhVar, ahVar, c0008aj, 100);
    }

    final void a(bf bfVar) throws IOException {
        byte[] a = this.a.a(bfVar);
        if (!this.d.a(a.length, e())) {
            C0003ab.a(4, String.format(Locale.US, "session analytics events file is %d bytes, new event is %d bytes, this is over flush limit of %d, rolling it over", new Object[]{Integer.valueOf(this.d.a()), Integer.valueOf(a.length), Integer.valueOf(e())}));
            a();
        }
        this.d.a(a);
    }

    final void a(C0004ak c0004ak) {
        if (c0004ak != null) {
            this.f.add(c0004ak);
        }
    }

    final boolean a() throws IOException {
        boolean z = true;
        String str = null;
        if (this.d.b()) {
            z = false;
        } else {
            UUID randomUUID = UUID.randomUUID();
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("sa");
            stringBuilder.append("_");
            stringBuilder.append(randomUUID.toString());
            stringBuilder.append("_");
            stringBuilder.append(this.b.a());
            stringBuilder.append(".tap");
            str = stringBuilder.toString();
            this.d.a(str);
            C0003ab.a(4, String.format(Locale.US, "generated new to-send analytics file %s", new Object[]{str}));
            this.b.a();
        }
        a(str);
        return z;
    }

    private int e() {
        return this.c == null ? 8000 : this.c.c;
    }

    final void a(aK aKVar) {
        this.c = aKVar;
    }

    private void a(String str) {
        for (C0004ak c : this.f) {
            try {
                c.c();
            } catch (Throwable e) {
                v.a().b().a(Crashlytics.TAG, "One of the roll over listeners threw an exception", e);
            }
        }
    }

    final List<File> b() {
        return this.d.a(1);
    }

    final void a(List<File> list) {
        this.d.a((List) list);
    }

    final void c() {
        this.d.a(this.d.c());
        this.d.d();
    }

    final void d() {
        List<File> c = this.d.c();
        if (c.size() > this.e) {
            C0003ab.c(String.format(Locale.US, "Found %d files in session analytics roll over directory, this is greater than %d, deleting %d oldest files", new Object[]{Integer.valueOf(c.size()), Integer.valueOf(this.e), Integer.valueOf(c.size() - this.e)}));
            TreeSet treeSet = new TreeSet(new w(this));
            for (File file : c) {
                treeSet.add(new x(this, file, b(file.getName())));
            }
            List arrayList = new ArrayList();
            Iterator it = treeSet.iterator();
            while (it.hasNext()) {
                arrayList.add(((x) it.next()).a);
                if (arrayList.size() == r1) {
                    break;
                }
            }
            this.d.a(arrayList);
        }
    }

    private static long b(String str) {
        long j = 0;
        String[] split = str.split("_");
        if (split.length == 3) {
            try {
                j = Long.valueOf(split[2]).longValue();
            } catch (NumberFormatException e) {
            }
        }
        return j;
    }
}
