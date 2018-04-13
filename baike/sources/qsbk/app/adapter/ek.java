package qsbk.app.adapter;

import android.text.TextUtils;
import com.qq.e.comm.constants.ErrorCode$OtherError;
import qsbk.app.adapter.VideoImmersionAdapter.VideoImmersionCell;
import qsbk.app.video.SimpleVideoPlayer;
import qsbk.app.video.SimpleVideoPlayer.OnVideoEventListener;

class ek implements OnVideoEventListener {
    final /* synthetic */ VideoImmersionCell a;

    ek(VideoImmersionCell videoImmersionCell) {
        this.a = videoImmersionCell;
    }

    public void onVideoCompletion(SimpleVideoPlayer simpleVideoPlayer) {
        if (this.a.q < this.a.a.getCount() - 1) {
            this.a.a.l.smoothScrollToPositionFromTop((this.a.q + 1) + this.a.a.l.getHeaderViewsCount(), this.a.a.e, ErrorCode$OtherError.CONTENT_FORCE_EXPOSURE);
        }
    }

    public void onVideoPrepared(SimpleVideoPlayer simpleVideoPlayer) {
        if (!TextUtils.isEmpty(this.a.article.id)) {
            this.a.b();
        }
    }

    public void onVideoError(SimpleVideoPlayer simpleVideoPlayer, int i, int i2) {
    }

    public void onVideoBuffering(SimpleVideoPlayer simpleVideoPlayer, int i) {
    }
}
