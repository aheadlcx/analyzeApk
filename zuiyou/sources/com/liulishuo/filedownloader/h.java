package com.liulishuo.filedownloader;

import com.liulishuo.filedownloader.a.b;
import com.liulishuo.filedownloader.g.d;
import com.liulishuo.filedownloader.message.MessageSnapshot;
import com.liulishuo.filedownloader.message.f;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class h {
    private final ArrayList<b> a;

    private static final class a {
        private static final h a = new h();
    }

    public static h a() {
        return a.a;
    }

    private h() {
        this.a = new ArrayList();
    }

    boolean b() {
        return this.a.isEmpty();
    }

    int c() {
        return this.a.size();
    }

    int a(int i) {
        int i2 = 0;
        synchronized (this.a) {
            Iterator it = this.a.iterator();
            while (it.hasNext()) {
                int i3;
                if (((b) it.next()).c(i)) {
                    i3 = i2 + 1;
                } else {
                    i3 = i2;
                }
                i2 = i3;
            }
        }
        return i2;
    }

    public b b(int i) {
        synchronized (this.a) {
            Iterator it = this.a.iterator();
            while (it.hasNext()) {
                b bVar = (b) it.next();
                if (bVar.c(i)) {
                    return bVar;
                }
            }
            return null;
        }
    }

    List<b> c(int i) {
        List<b> arrayList = new ArrayList();
        synchronized (this.a) {
            Iterator it = this.a.iterator();
            while (it.hasNext()) {
                b bVar = (b) it.next();
                if (bVar.c(i) && !bVar.A()) {
                    byte q = bVar.y().q();
                    if (!(q == (byte) 0 || q == (byte) 10)) {
                        arrayList.add(bVar);
                    }
                }
            }
        }
        return arrayList;
    }

    boolean a(b bVar) {
        return this.a.isEmpty() || !this.a.contains(bVar);
    }

    void a(List<b> list) {
        synchronized (this.a) {
            Iterator it = this.a.iterator();
            while (it.hasNext()) {
                b bVar = (b) it.next();
                if (!list.contains(bVar)) {
                    list.add(bVar);
                }
            }
            this.a.clear();
        }
    }

    public boolean a(b bVar, MessageSnapshot messageSnapshot) {
        boolean remove;
        byte b = messageSnapshot.b();
        synchronized (this.a) {
            remove = this.a.remove(bVar);
        }
        if (d.a && this.a.size() == 0) {
            d.e(this, "remove %s left %d %d", bVar, Byte.valueOf(b), Integer.valueOf(this.a.size()));
        }
        if (remove) {
            s d = bVar.z().d();
            switch (b) {
                case (byte) -4:
                    d.g(messageSnapshot);
                    break;
                case (byte) -3:
                    d.e(f.a(messageSnapshot));
                    break;
                case (byte) -2:
                    d.i(messageSnapshot);
                    break;
                case (byte) -1:
                    d.h(messageSnapshot);
                    break;
            }
        }
        d.a(this, "remove error, not exist: %s %d", bVar, Byte.valueOf(b));
        return remove;
    }

    void b(b bVar) {
        if (!bVar.y().b()) {
            bVar.C();
        }
        if (bVar.z().d().a()) {
            c(bVar);
        }
    }

    void c(b bVar) {
        if (!bVar.D()) {
            synchronized (this.a) {
                if (this.a.contains(bVar)) {
                    d.d(this, "already has %s", bVar);
                } else {
                    bVar.E();
                    this.a.add(bVar);
                    if (d.a) {
                        d.e(this, "add list in all %s %d %d", bVar, Byte.valueOf(bVar.y().q()), Integer.valueOf(this.a.size()));
                    }
                }
            }
        }
    }
}
