package com.budejie.www.activity.video;

import android.view.SurfaceHolder;
import android.view.SurfaceHolder.Callback;

class VideoView$8 implements Callback {
    final /* synthetic */ VideoView a;

    VideoView$8(VideoView videoView) {
        this.a = videoView;
    }

    public void surfaceChanged(SurfaceHolder surfaceHolder, int i, int i2, int i3) {
        Object obj = 1;
        VideoView.f(this.a, i2);
        VideoView.g(this.a, i3);
        Object obj2 = VideoView.j(this.a) == 3 ? 1 : null;
        if (!(VideoView.b(this.a) == i2 && VideoView.c(this.a) == i3)) {
            obj = null;
        }
        if (VideoView.a(this.a) != null && obj2 != null && r1 != null) {
            if (VideoView.g(this.a) != 0) {
                this.a.a(VideoView.g(this.a));
            }
            this.a.a();
        }
    }

    public void surfaceCreated(SurfaceHolder surfaceHolder) {
        try {
            VideoView.a(this.a, surfaceHolder);
            VideoView.r(this.a);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void surfaceDestroyed(SurfaceHolder surfaceHolder) {
        VideoView.a(this.a, null);
        if (VideoView.f(this.a) != null) {
            VideoView.f(this.a).m();
        }
    }
}
