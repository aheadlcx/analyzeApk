package cn.v6.sixrooms.surfaceanim;

import android.content.ComponentCallbacks;
import android.content.res.Configuration;
import cn.v6.sixrooms.surfaceanim.util.AnimSceneResManager;

class AnimViewControl$b implements ComponentCallbacks {
    final /* synthetic */ AnimViewControl a;

    AnimViewControl$b(AnimViewControl animViewControl) {
        this.a = animViewControl;
    }

    public final void onConfigurationChanged(Configuration configuration) {
    }

    public final void onLowMemory() {
        AnimSceneResManager.getInstance().clearAllBitmaps();
    }
}
