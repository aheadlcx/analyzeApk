package in.srain.cube.views.ptr.header;

import android.view.animation.Animation;
import android.view.animation.Animation.AnimationListener;
import in.srain.cube.views.ptr.PtrUIHandlerHook;

class c implements AnimationListener {
    final /* synthetic */ PtrUIHandlerHook a;
    final /* synthetic */ MaterialHeader b;

    c(MaterialHeader materialHeader, PtrUIHandlerHook ptrUIHandlerHook) {
        this.b = materialHeader;
        this.a = ptrUIHandlerHook;
    }

    public void onAnimationStart(Animation animation) {
    }

    public void onAnimationEnd(Animation animation) {
        this.a.resume();
    }

    public void onAnimationRepeat(Animation animation) {
    }
}
