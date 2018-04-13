package android.support.v4.app;

import android.app.SharedElementCallback;
import android.support.v4.app.SharedElementCallback.OnSharedElementsReadyListener;

class b implements OnSharedElementsReadyListener {
    final /* synthetic */ SharedElementCallback.OnSharedElementsReadyListener a;
    final /* synthetic */ b b;

    b(b bVar, SharedElementCallback.OnSharedElementsReadyListener onSharedElementsReadyListener) {
        this.b = bVar;
        this.a = onSharedElementsReadyListener;
    }

    public void onSharedElementsReady() {
        this.a.onSharedElementsReady();
    }
}
