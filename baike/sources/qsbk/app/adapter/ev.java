package qsbk.app.adapter;

import qsbk.app.adapter.VideoImmersionCircleAdapter.VideoImmersionCell;
import qsbk.app.video.CircleVideoPlayerView.OnControlBarListener;

class ev implements OnControlBarListener {
    final /* synthetic */ VideoImmersionCell a;

    ev(VideoImmersionCell videoImmersionCell) {
        this.a = videoImmersionCell;
    }

    public void onControlBarStateChange(boolean z) {
        if (z) {
            this.a.toLight();
            if (VideoImmersionCircleAdapter.lastSelect != null && !this.a.equals(VideoImmersionCircleAdapter.lastSelect)) {
                VideoImmersionCircleAdapter.lastSelect.onItemChange(null);
                return;
            }
            return;
        }
        this.a.b.offLight();
        if (VideoImmersionCircleAdapter.lastSelectGdt != null && VideoImmersionCircleAdapter.lastSelectGdt.getRef() != null && VideoImmersionCircleAdapter.lastSelectGdt.getRef().isPlaying() && VideoImmersionCircleAdapter.lastSelect != null && !this.a.equals(VideoImmersionCircleAdapter.lastSelect)) {
            VideoImmersionCircleAdapter.lastSelect.onItemChange(null);
        }
    }
}
