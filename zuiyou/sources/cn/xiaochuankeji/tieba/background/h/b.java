package cn.xiaochuankeji.tieba.background.h;

import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;

public class b {
    public long a;
    private ArrayList<a> b = new ArrayList();

    public class a {
        public String a;
        public long b;
        public long c;
        final /* synthetic */ b d;

        public a(b bVar, String str, long j, long j2) {
            this.d = bVar;
            this.a = str;
            this.b = j;
            this.c = j2;
        }
    }

    public void a(a aVar) {
        this.b.add(aVar);
        this.a += aVar.b;
    }

    private void b() {
        try {
            Collections.sort(this.b, new Comparator<a>(this) {
                final /* synthetic */ b a;

                {
                    this.a = r1;
                }

                public /* synthetic */ int compare(Object obj, Object obj2) {
                    return a((a) obj, (a) obj2);
                }

                public int a(a aVar, a aVar2) {
                    if (aVar.c > aVar2.c) {
                        return 1;
                    }
                    if (aVar.c < aVar2.c) {
                        return -1;
                    }
                    return 0;
                }
            });
        } catch (Exception e) {
        }
    }

    public void a(long j) {
        b();
        Iterator it = this.b.iterator();
        while (it.hasNext()) {
            a aVar = (a) it.next();
            new File(aVar.a).delete();
            this.a -= aVar.b;
            if (this.a <= j) {
                return;
            }
        }
    }

    public void a() {
        this.a = 0;
        this.b.clear();
    }
}
