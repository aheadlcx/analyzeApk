package in.srain.cube.views.ptr;

import in.srain.cube.views.ptr.b.a;

class PtrFrameLayout$2 implements Runnable {
    final /* synthetic */ PtrFrameLayout a;

    PtrFrameLayout$2(PtrFrameLayout ptrFrameLayout) {
        this.a = ptrFrameLayout;
    }

    public void run() {
        if (PtrFrameLayout.a) {
            a.a(this.a.b, "mRefreshCompleteHook resume.");
        }
        PtrFrameLayout.a(this.a, true);
    }
}
