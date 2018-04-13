package com.facebook.cache.disk;

public class a implements g {
    public f a() {
        return new f(this) {
            final /* synthetic */ a a;

            {
                this.a = r1;
            }

            public /* synthetic */ int compare(Object obj, Object obj2) {
                return a((com.facebook.cache.disk.c.a) obj, (com.facebook.cache.disk.c.a) obj2);
            }

            public int a(com.facebook.cache.disk.c.a aVar, com.facebook.cache.disk.c.a aVar2) {
                long b = aVar.b();
                long b2 = aVar2.b();
                if (b < b2) {
                    return -1;
                }
                return b2 == b ? 0 : 1;
            }
        };
    }
}
