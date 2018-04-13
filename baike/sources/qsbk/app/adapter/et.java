package qsbk.app.adapter;

import com.qq.e.ads.nativ.MediaListener;
import com.qq.e.comm.constants.ErrorCode$OtherError;
import com.qq.e.comm.util.AdError;
import qsbk.app.ad.feedsad.gdtad.GdtVideoManager;
import qsbk.app.adapter.VideoImmersionCircleAdapter.GdtVideoImmersionCell;

class et implements MediaListener {
    final /* synthetic */ GdtVideoImmersionCell a;

    et(GdtVideoImmersionCell gdtVideoImmersionCell) {
        this.a = gdtVideoImmersionCell;
    }

    public void onVideoReady(long j) {
    }

    public void onFullScreenChanged(boolean z) {
    }

    public void onVideoStart() {
        if (VideoImmersionCircleAdapter.lastSelect != null) {
            VideoImmersionCircleAdapter.lastSelect.onItemChange(null);
            VideoImmersionCircleAdapter.lastSelect = null;
        }
        this.a.t.light();
        this.a.u.light();
        GdtVideoManager.onMediaViewPlay(this.a.v);
    }

    public void onVideoPause() {
        this.a.t.offLight();
        this.a.u.offLight();
        GdtVideoManager.onMediaViewStop(this.a.v);
    }

    public void onVideoComplete() {
        this.a.t.reset();
        this.a.u.reset();
        this.a.b.setVisibility(8);
        GdtVideoManager.onMediaViewStop(this.a.v);
        if (this.a.q < this.a.p.getCount() - 1 && this.a.v.getCurrentPosition() >= this.a.v.getDuration()) {
            this.a.p.l.smoothScrollToPositionFromTop((this.a.q + 1) + this.a.p.l.getHeaderViewsCount(), this.a.p.b, ErrorCode$OtherError.CONTENT_FORCE_EXPOSURE);
        }
    }

    public void onVideoError(AdError adError) {
    }

    public void onReplayButtonClicked() {
    }

    public void onADButtonClicked() {
    }
}
