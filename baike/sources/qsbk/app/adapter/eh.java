package qsbk.app.adapter;

import qsbk.app.adapter.VideoImmersionAdapter.VideoImmersionCell;
import qsbk.app.video.CircleVideoPlayerView.OnControlBarListener;

class eh implements OnControlBarListener {
    final /* synthetic */ VideoImmersionCell a;

    eh(VideoImmersionCell videoImmersionCell) {
        this.a = videoImmersionCell;
    }

    public void onControlBarStateChange(boolean z) {
        if (z) {
            this.a.toLight();
            if (VideoImmersionAdapter.lastSelect != null && !this.a.equals(VideoImmersionAdapter.lastSelect)) {
                VideoImmersionAdapter.lastSelect.onItemChange(null);
                return;
            }
            return;
        }
        this.a.b.offLight();
        if (VideoImmersionAdapter.lastSelectGdt != null && VideoImmersionAdapter.lastSelectGdt.getRef() != null && VideoImmersionAdapter.lastSelectGdt.getRef().isPlaying() && VideoImmersionAdapter.lastSelect != null && !this.a.equals(VideoImmersionAdapter.lastSelect)) {
            VideoImmersionAdapter.lastSelect.onItemChange(null);
        }
    }
}
