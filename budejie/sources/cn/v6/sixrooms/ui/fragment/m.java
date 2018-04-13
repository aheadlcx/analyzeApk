package cn.v6.sixrooms.ui.fragment;

import android.text.TextUtils;
import android.view.SurfaceHolder;
import android.view.SurfaceHolder.Callback;
import cn.v6.sixrooms.avsolution.common.SixPlayer;

final class m implements Callback {
    final /* synthetic */ FragmentHardwarePlayer a;

    m(FragmentHardwarePlayer fragmentHardwarePlayer) {
        this.a = fragmentHardwarePlayer;
    }

    public final void surfaceDestroyed(SurfaceHolder surfaceHolder) {
        this.a.release();
    }

    public final void surfaceCreated(SurfaceHolder surfaceHolder) {
        if (FragmentHardwarePlayer.a(this.a) == 1 && !TextUtils.isEmpty(FragmentHardwarePlayer.b(this.a))) {
            FragmentHardwarePlayer.c(this.a);
            int state = SixPlayer.getState();
            if (state == 2) {
                FragmentHardwarePlayer.c(this.a);
                SixPlayer.openRender(surfaceHolder.getSurface());
            } else if (state == 0 && FragmentHardwarePlayer.c(this.a).play(FragmentHardwarePlayer.b(this.a)) < 0) {
                this.a.onError(2);
            }
        }
    }

    public final void surfaceChanged(SurfaceHolder surfaceHolder, int i, int i2, int i3) {
    }
}
