package qsbk.app.adapter;

import com.qq.e.comm.constants.ErrorCode$OtherError;
import qsbk.app.adapter.VideoImmersionCircleAdapter.VideoImmersionCell;
import qsbk.app.video.SimpleVideoPlayer;
import qsbk.app.video.SimpleVideoPlayer.OnVideoEventListener;

class ew implements OnVideoEventListener {
    final /* synthetic */ VideoImmersionCell a;

    ew(VideoImmersionCell videoImmersionCell) {
        this.a = videoImmersionCell;
    }

    public void onVideoCompletion(SimpleVideoPlayer simpleVideoPlayer) {
        if (this.a.q < this.a.a.getCount() - 1) {
            this.a.a.l.smoothScrollToPositionFromTop((this.a.q + 1) + this.a.a.l.getHeaderViewsCount(), this.a.a.b, ErrorCode$OtherError.CONTENT_FORCE_EXPOSURE);
        }
    }

    public void onVideoPrepared(SimpleVideoPlayer simpleVideoPlayer) {
    }

    public void onVideoError(SimpleVideoPlayer simpleVideoPlayer, int i, int i2) {
    }

    public void onVideoBuffering(SimpleVideoPlayer simpleVideoPlayer, int i) {
    }
}
