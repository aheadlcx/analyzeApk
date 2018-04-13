package com.budejie.www.activity.video;

import android.media.MediaPlayer;
import android.media.MediaPlayer.OnVideoSizeChangedListener;

class VideoView$2 implements OnVideoSizeChangedListener {
    final /* synthetic */ VideoView a;

    VideoView$2(VideoView videoView) {
        this.a = videoView;
    }

    public void onVideoSizeChanged(MediaPlayer mediaPlayer, int i, int i2) {
        VideoView.c(this.a, mediaPlayer.getVideoWidth());
        VideoView.d(this.a, mediaPlayer.getVideoHeight());
        if (VideoView.b(this.a) != 0 && VideoView.c(this.a) != 0) {
            this.a.getHolder().setFixedSize(VideoView.b(this.a), VideoView.c(this.a));
        }
    }
}
