package qsbk.app.video;

import qsbk.app.video.VideoEditPlayView.OnPreparedListener;

class ag implements OnPreparedListener {
    final /* synthetic */ VideoEditActivity a;

    ag(VideoEditActivity videoEditActivity) {
        this.a = videoEditActivity;
    }

    public void onPrepared() {
        this.a.k.setVisibility(0);
        this.a.g();
    }
}
