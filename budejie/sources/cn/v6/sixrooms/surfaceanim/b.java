package cn.v6.sixrooms.surfaceanim;

import cn.v6.sixrooms.surfaceanim.animinterface.IOnAnimDrawListener;

final class b implements Runnable {
    final /* synthetic */ IOnAnimDrawListener a;
    final /* synthetic */ int b;
    final /* synthetic */ AnimRenderManager c;

    b(AnimRenderManager animRenderManager, IOnAnimDrawListener iOnAnimDrawListener, int i) {
        this.c = animRenderManager;
        this.a = iOnAnimDrawListener;
        this.b = i;
    }

    public final void run() {
        this.a.onDrawState(this.b);
    }
}
