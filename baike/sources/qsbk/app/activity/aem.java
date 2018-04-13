package qsbk.app.activity;

import qsbk.app.video.CircleVideoPlayerView.OnControlBarListener;

class aem implements OnControlBarListener {
    final /* synthetic */ VideoFullScreenActivity a;

    aem(VideoFullScreenActivity videoFullScreenActivity) {
        this.a = videoFullScreenActivity;
    }

    public void onControlBarStateChange(boolean z) {
        VideoFullScreenActivity.f(this.a).setVisibility(z ? 0 : 4);
    }
}
