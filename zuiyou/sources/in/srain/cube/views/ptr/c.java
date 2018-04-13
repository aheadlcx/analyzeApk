package in.srain.cube.views.ptr;

import in.srain.cube.views.ptr.a.a;

class c implements b {
    private b a;
    private c b;

    private boolean a(b bVar) {
        return this.a != null && this.a == bVar;
    }

    private c() {
    }

    public boolean a() {
        return this.a != null;
    }

    private b c() {
        return this.a;
    }

    public static void a(c cVar, b bVar) {
        if (bVar != null && cVar != null) {
            if (cVar.a == null) {
                cVar.a = bVar;
                return;
            }
            while (!cVar.a(bVar)) {
                if (cVar.b == null) {
                    c cVar2 = new c();
                    cVar2.a = bVar;
                    cVar.b = cVar2;
                    return;
                }
                cVar = cVar.b;
            }
        }
    }

    public static c b() {
        return new c();
    }

    public void a(PtrFrameLayout ptrFrameLayout) {
        do {
            b c = c();
            if (c != null) {
                c.a(ptrFrameLayout);
            }
            this = this.b;
        } while (this != null);
    }

    public void b(PtrFrameLayout ptrFrameLayout) {
        if (a()) {
            do {
                b c = c();
                if (c != null) {
                    c.b(ptrFrameLayout);
                }
                this = this.b;
            } while (this != null);
        }
    }

    public void c(PtrFrameLayout ptrFrameLayout) {
        do {
            b c = c();
            if (c != null) {
                c.c(ptrFrameLayout);
            }
            this = this.b;
        } while (this != null);
    }

    public void d(PtrFrameLayout ptrFrameLayout) {
        do {
            b c = c();
            if (c != null) {
                c.d(ptrFrameLayout);
            }
            this = this.b;
        } while (this != null);
    }

    public void a(PtrFrameLayout ptrFrameLayout, boolean z, byte b, a aVar) {
        do {
            b c = c();
            if (c != null) {
                c.a(ptrFrameLayout, z, b, aVar);
            }
            this = this.b;
        } while (this != null);
    }
}
