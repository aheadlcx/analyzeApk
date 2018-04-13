package in.srain.cube.views.ptr;

import in.srain.cube.views.ptr.indicator.PtrIndicator;

class d implements PtrUIHandler {
    private PtrUIHandler a;
    private d b;

    private boolean a(PtrUIHandler ptrUIHandler) {
        return this.a != null && this.a == ptrUIHandler;
    }

    private d() {
    }

    public boolean hasHandler() {
        return this.a != null;
    }

    private PtrUIHandler a() {
        return this.a;
    }

    public static void addHandler(d dVar, PtrUIHandler ptrUIHandler) {
        if (ptrUIHandler != null && dVar != null) {
            if (dVar.a == null) {
                dVar.a = ptrUIHandler;
                return;
            }
            while (!dVar.a(ptrUIHandler)) {
                if (dVar.b == null) {
                    d dVar2 = new d();
                    dVar2.a = ptrUIHandler;
                    dVar.b = dVar2;
                    return;
                }
                dVar = dVar.b;
            }
        }
    }

    public static d create() {
        return new d();
    }

    public static d removeHandler(d dVar, PtrUIHandler ptrUIHandler) {
        if (dVar == null || ptrUIHandler == null || dVar.a == null) {
            return dVar;
        }
        d dVar2 = null;
        d dVar3 = dVar;
        do {
            if (!dVar.a(ptrUIHandler)) {
                d dVar4 = dVar;
                dVar = dVar.b;
                dVar2 = dVar4;
                continue;
            } else if (dVar2 == null) {
                dVar3 = dVar.b;
                dVar.b = null;
                dVar = dVar3;
                continue;
            } else {
                dVar2.b = dVar.b;
                dVar.b = null;
                dVar = dVar2.b;
                continue;
            }
        } while (dVar != null);
        if (dVar3 == null) {
            return new d();
        }
        return dVar3;
    }

    public void onUIReset(PtrFrameLayout ptrFrameLayout) {
        do {
            PtrUIHandler a = a();
            if (a != null) {
                a.onUIReset(ptrFrameLayout);
            }
            this = this.b;
        } while (this != null);
    }

    public void onUIRefreshPrepare(PtrFrameLayout ptrFrameLayout) {
        if (hasHandler()) {
            do {
                PtrUIHandler a = a();
                if (a != null) {
                    a.onUIRefreshPrepare(ptrFrameLayout);
                }
                this = this.b;
            } while (this != null);
        }
    }

    public void onUIRefreshBegin(PtrFrameLayout ptrFrameLayout) {
        do {
            PtrUIHandler a = a();
            if (a != null) {
                a.onUIRefreshBegin(ptrFrameLayout);
            }
            this = this.b;
        } while (this != null);
    }

    public void onUIRefreshComplete(PtrFrameLayout ptrFrameLayout) {
        do {
            PtrUIHandler a = a();
            if (a != null) {
                a.onUIRefreshComplete(ptrFrameLayout);
            }
            this = this.b;
        } while (this != null);
    }

    public void onUIPositionChange(PtrFrameLayout ptrFrameLayout, boolean z, byte b, PtrIndicator ptrIndicator) {
        do {
            PtrUIHandler a = a();
            if (a != null) {
                a.onUIPositionChange(ptrFrameLayout, z, b, ptrIndicator);
            }
            this = this.b;
        } while (this != null);
    }
}
