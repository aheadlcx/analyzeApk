package com.facebook.common.memory;

public class d implements c {
    private static d a = null;

    public static synchronized d a() {
        d dVar;
        synchronized (d.class) {
            if (a == null) {
                a = new d();
            }
            dVar = a;
        }
        return dVar;
    }

    public void a(b bVar) {
    }
}
