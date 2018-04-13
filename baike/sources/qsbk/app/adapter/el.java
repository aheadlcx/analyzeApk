package qsbk.app.adapter;

import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import com.qq.e.comm.constants.ErrorCode$OtherError;
import qsbk.app.QsbkApp;
import qsbk.app.adapter.VideoImmersionAdapter.VideoImmersionCell;
import qsbk.app.video.CircleVideoPlayerView;

class el implements OnTouchListener {
    final /* synthetic */ VideoImmersionCell a;

    el(VideoImmersionCell videoImmersionCell) {
        this.a = videoImmersionCell;
    }

    public boolean onTouch(View view, MotionEvent motionEvent) {
        boolean z = false;
        if (this.a.b.getBrightness() >= this.a.b.max) {
            return false;
        }
        if (motionEvent.getAction() != 1) {
            return true;
        }
        if (VideoImmersionAdapter.lastSelect != this.a) {
            this.a.a.l.smoothScrollToPositionFromTop(this.a.q + this.a.a.l.getHeaderViewsCount(), this.a.a.e, ErrorCode$OtherError.CONTENT_FORCE_EXPOSURE);
            if (VideoImmersionAdapter.lastSelect != null) {
                VideoImmersionAdapter.lastSelect.player.pause();
            }
            if (QsbkApp.getInstance().isAutoPlayConfiged()) {
                this.a.player.play();
            }
        }
        CircleVideoPlayerView circleVideoPlayerView = (CircleVideoPlayerView) this.a.player;
        if (this.a.e == view) {
            z = true;
        }
        circleVideoPlayerView.showControlBar(z, true);
        return true;
    }
}
