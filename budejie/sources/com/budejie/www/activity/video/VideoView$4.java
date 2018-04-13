package com.budejie.www.activity.video;

import android.media.MediaPlayer;
import android.media.MediaPlayer.OnCompletionListener;

class VideoView$4 implements OnCompletionListener {
    final /* synthetic */ VideoView a;

    VideoView$4(VideoView videoView) {
        this.a = videoView;
    }

    public void onCompletion(MediaPlayer mediaPlayer) {
        VideoView.a(this.a, 5);
        VideoView.b(this.a, 5);
        if (VideoView.f(this.a) != null) {
        }
        if (VideoView.m(this.a) != null) {
            VideoView.m(this.a).onCompletion(VideoView.a(this.a));
        }
        if (VideoView.f(this.a) != null) {
            VideoView.f(this.a).o();
        }
    }
}
