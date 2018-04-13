package qsbk.app.video;

import qsbk.app.widget.SeekView;
import qsbk.app.widget.SeekView.OnSeekViewChangeListener;

class e implements OnSeekViewChangeListener {
    final /* synthetic */ CircleVideoPlayerView a;

    e(CircleVideoPlayerView circleVideoPlayerView) {
        this.a = circleVideoPlayerView;
    }

    public void onProgressChanged(SeekView seekView, int i, boolean z) {
    }

    public void onStartTrackingTouch(SeekView seekView) {
        this.a.q = true;
        this.a.showControlBar(false, true);
        this.a.g();
    }

    public void onStopTrackingTouch(SeekView seekView) {
        if (!(this.a.e.getPlayState() == 1 || this.a.e.getPlayState() == 3)) {
            this.a.e.start();
        }
        this.a.e.seekTo((long) seekView.getProgress());
        this.a.q = false;
        this.a.f();
    }
}
