package qsbk.app.adapter;

import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import com.qq.e.comm.constants.ErrorCode$OtherError;
import qsbk.app.QsbkApp;
import qsbk.app.adapter.VideoImmersionCircleAdapter.VideoImmersionCell;
import qsbk.app.video.CircleVideoPlayerView;

class fa implements OnTouchListener {
    final /* synthetic */ VideoImmersionCell a;

    fa(VideoImmersionCell videoImmersionCell) {
        this.a = videoImmersionCell;
    }

    public boolean onTouch(View view, MotionEvent motionEvent) {
        boolean z = false;
        if (this.a.b.isMaxLight()) {
            return false;
        }
        if (motionEvent.getAction() != 1) {
            return true;
        }
        if (VideoImmersionCircleAdapter.lastSelect != this.a) {
            this.a.a.l.smoothScrollToPositionFromTop(this.a.q + this.a.a.l.getHeaderViewsCount(), this.a.a.b, ErrorCode$OtherError.CONTENT_FORCE_EXPOSURE);
            if (VideoImmersionCircleAdapter.lastSelect != null) {
                VideoImmersionCircleAdapter.lastSelect.playerView.pause();
            }
            if (QsbkApp.getInstance().isAutoPlayConfiged()) {
                this.a.playerView.play();
            }
        }
        CircleVideoPlayerView circleVideoPlayerView = (CircleVideoPlayerView) this.a.playerView;
        if (this.a.f == view) {
            z = true;
        }
        circleVideoPlayerView.showControlBar(z, true);
        return true;
    }
}
