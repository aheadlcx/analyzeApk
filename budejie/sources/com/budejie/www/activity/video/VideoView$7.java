package com.budejie.www.activity.video;

import android.media.MediaPlayer;
import android.media.MediaPlayer.OnBufferingUpdateListener;

class VideoView$7 implements OnBufferingUpdateListener {
    final /* synthetic */ VideoView a;

    VideoView$7(VideoView videoView) {
        this.a = videoView;
    }

    public void onBufferingUpdate(MediaPlayer mediaPlayer, int i) {
        VideoView.e(this.a, i);
    }
}
