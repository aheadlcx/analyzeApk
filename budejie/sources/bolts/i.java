package bolts;

import bolts.g.a;

class i {
    private g<?> a;

    public i(g<?> gVar) {
        this.a = gVar;
    }

    protected void finalize() throws Throwable {
        try {
            g gVar = this.a;
            if (gVar != null) {
                a a = g.a();
                if (a != null) {
                    a.a(gVar, new UnobservedTaskException(gVar.f()));
                }
            }
            super.finalize();
        } catch (Throwable th) {
            super.finalize();
        }
    }

    public void a() {
        this.a = null;
    }
}
