package in.srain.cube.views.ptr;

import in.srain.cube.views.ptr.util.PtrCLog;

class c implements Runnable {
    final /* synthetic */ PtrFrameLayout a;

    c(PtrFrameLayout ptrFrameLayout) {
        this.a = ptrFrameLayout;
    }

    public void run() {
        if (PtrFrameLayout.DEBUG) {
            PtrCLog.d(this.a.a, "mRefreshCompleteHook resume.");
        }
        PtrFrameLayout.a(this.a, true);
    }
}
