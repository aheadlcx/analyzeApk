package qsbk.app.live.ui;

import android.view.View;
import io.agora.rtc.RtcEngine;
import io.agora.rtc.video.VideoCanvas;
import qsbk.app.cafe.plugin.PayUniversalPlugin;
import qsbk.app.core.utils.LogUtils;

class y implements Runnable {
    final /* synthetic */ int a;
    final /* synthetic */ int b;
    final /* synthetic */ int c;
    final /* synthetic */ int d;
    final /* synthetic */ LiveBaseActivity e;

    y(LiveBaseActivity liveBaseActivity, int i, int i2, int i3, int i4) {
        this.e = liveBaseActivity;
        this.a = i;
        this.b = i2;
        this.c = i3;
        this.d = i4;
    }

    public void run() {
        if (!this.e.isFinishing()) {
            LogUtils.d("MicConnect", "onFirstRemoteVideoDecoded. uid:" + this.a + " width:" + this.b + " height:" + this.c + " elapsed:" + this.d);
            View CreateRendererView = RtcEngine.CreateRendererView(this.e.getApplicationContext());
            LiveBaseActivity.h(this.e).put(Integer.valueOf(this.a), CreateRendererView);
            if (this.e.aa()) {
                LiveBaseActivity.b(this.e, LiveBaseActivity.i(this.e));
                if (LiveBaseActivity.j(this.e) == 0) {
                    LiveBaseActivity.a(this.e, this.a);
                    this.e.an.setMajorView(LiveBaseActivity.j(this.e) == PayUniversalPlugin.REQUEST_PAY);
                    LiveBaseActivity.a(this.e, CreateRendererView);
                    LiveBaseActivity.c(this.e, LiveBaseActivity.k(this.e));
                    LiveBaseActivity.l(this.e).addView(CreateRendererView);
                    LiveBaseActivity.m(this.e).setupRemoteVideo(new VideoCanvas(CreateRendererView, 1, this.a));
                } else if (this.a == PayUniversalPlugin.REQUEST_PAY) {
                    LiveBaseActivity.a(this.e, this.a);
                    this.e.an.setMajorView(true);
                    LiveBaseActivity.o(this.e).setupRemoteVideo(new VideoCanvas(LiveBaseActivity.n(this.e), 1, LiveBaseActivity.j(this.e)));
                }
                LiveBaseActivity.a(this.e, new z(this), 1000);
                return;
            }
            if (!this.e.isAnchor()) {
                this.e.a(CreateRendererView, this.a);
            } else if (LiveBaseActivity.p(this.e) == 3) {
                this.e.a(CreateRendererView);
            }
            LiveBaseActivity.q(this.e).setupRemoteVideo(new VideoCanvas(CreateRendererView, 1, this.a));
            LiveBaseActivity.r(this.e);
        }
    }
}
