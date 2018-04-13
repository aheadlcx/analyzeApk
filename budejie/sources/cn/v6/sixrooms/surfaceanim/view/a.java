package cn.v6.sixrooms.surfaceanim.view;

import cn.v6.sixrooms.surfaceanim.flybanner.utils.touch.SurfaceTouchEvent;
import cn.v6.sixrooms.surfaceanim.flybanner.utils.touch.TouchEntity;

final class a extends SurfaceTouchEvent {
    final /* synthetic */ AnimSurfaceViewTouch a;

    a(AnimSurfaceViewTouch animSurfaceViewTouch) {
        this.a = animSurfaceViewTouch;
    }

    public final void addTouchEntity(TouchEntity touchEntity) {
        synchronized (AnimSurfaceViewTouch.a(this.a)) {
            int lastIndexOf = AnimSurfaceViewTouch.b(this.a).lastIndexOf(touchEntity);
            if (lastIndexOf >= 0) {
                AnimSurfaceViewTouch.b(this.a).set(lastIndexOf, touchEntity);
            } else {
                AnimSurfaceViewTouch.b(this.a).add(touchEntity);
            }
        }
    }

    public final void removeTouchEntity(TouchEntity touchEntity) {
        synchronized (AnimSurfaceViewTouch.a(this.a)) {
            if (AnimSurfaceViewTouch.b(this.a).contains(touchEntity)) {
                AnimSurfaceViewTouch.b(this.a).remove(touchEntity);
            }
        }
    }
}
