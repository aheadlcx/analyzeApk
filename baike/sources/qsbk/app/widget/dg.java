package qsbk.app.widget;

import android.support.v7.widget.RecyclerView;
import in.srain.cube.views.ptr.PtrDefaultHandler;
import in.srain.cube.views.ptr.PtrFrameLayout;

class dg extends PtrDefaultHandler {
    final /* synthetic */ PtrLayout a;

    dg(PtrLayout ptrLayout) {
        this.a = ptrLayout;
    }

    public void onRefreshBegin(PtrFrameLayout ptrFrameLayout) {
        if (PtrLayout.d(this.a) != null) {
            if (PtrLayout.e(this.a)) {
                this.a.updateLoadingStartTime();
                PtrLayout.d(this.a).onRefresh();
                PtrLayout.a(this.a, false);
            } else {
                return;
            }
        }
        if ((PtrLayout.f(this.a) instanceof RecyclerView) && this.a.c != null) {
            this.a.c.reset();
        }
    }
}
