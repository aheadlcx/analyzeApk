package com.budejie.www.activity.video;

import android.media.MediaPlayer;
import com.budejie.www.activity.video.NativePlayer.b;

class VideoView$5 implements b {
    final /* synthetic */ VideoView a;

    VideoView$5(VideoView videoView) {
        this.a = videoView;
    }

    public void a(MediaPlayer mediaPlayer, int i) {
        if (VideoView.n(this.a) != null) {
            VideoView.n(this.a).a(mediaPlayer, i);
        }
    }
}
