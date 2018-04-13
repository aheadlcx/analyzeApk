package com.budejie.www.activity.video;

import android.content.Intent;
import android.util.Log;
import android.view.GestureDetector.OnDoubleTapListener;
import android.view.MotionEvent;
import com.budejie.www.recommendvideo.AutoPlayVideoListActivity;
import com.umeng.analytics.MobclickAgent;

class f$1 implements OnDoubleTapListener {
    final /* synthetic */ f a;

    f$1(f fVar) {
        this.a = fVar;
    }

    public boolean onSingleTapConfirmed(MotionEvent motionEvent) {
        Log.d("MicroMediaController", "onSingleTapConfirmed--" + motionEvent.getAction());
        if (!this.a.a) {
            if (f.a(this.a) == 3) {
                MobclickAgent.onEvent(f.b(this.a), "E06_A17", "猜你喜欢点击");
                Intent intent = new Intent();
                intent.setClass(f.b(this.a), AutoPlayVideoListActivity.class);
                f.b(this.a).startActivity(intent);
            } else if (f.a(this.a) == 4 && f.c(this.a) != null) {
                f.c(this.a).a();
            } else if (f.d(this.a).c() || !f.d(this.a).d() || f.d(this.a).e()) {
                this.a.c();
            } else if (k.a(f.e(this.a)).f == null || !(k.a(f.e(this.a)).f.c() || k.a(f.e(this.a)).f.d())) {
                this.a.v();
                k.a(f.e(this.a)).t();
                f.f(this.a).setVisibility(4);
            }
        }
        return true;
    }

    public boolean onDoubleTapEvent(MotionEvent motionEvent) {
        Log.d("MicroMediaController", "onDoubleTapEvent--" + motionEvent.getAction());
        return false;
    }

    public boolean onDoubleTap(MotionEvent motionEvent) {
        Log.d("MicroMediaController", "onDoubleTap--" + motionEvent.getAction());
        if (f.g(this.a) != null) {
            f.g(this.a).b_();
        }
        return true;
    }
}
