package com.budejie.www.activity.video;

import android.media.MediaPlayer;
import android.media.MediaPlayer.OnErrorListener;
import android.util.Log;
import com.umeng.analytics.MobclickAgent;

class VideoView$6 implements OnErrorListener {
    final /* synthetic */ VideoView a;

    VideoView$6(VideoView videoView) {
        this.a = videoView;
    }

    public boolean onError(MediaPlayer mediaPlayer, int i, int i2) {
        Log.d(VideoView.d(this.a), "Error: " + i + "," + i2);
        MobclickAgent.onEvent(VideoView.o(this.a), "E03-A05", "视频播放错误:" + i + ":" + i2 + " url:" + VideoView.p(this.a));
        VideoView.a(this.a, -1);
        VideoView.b(this.a, -1);
        if (VideoView.f(this.a) != null) {
            VideoView.f(this.a).r();
        }
        if ((VideoView.q(this.a) == null || !VideoView.q(this.a).onError(VideoView.a(this.a), i, i2)) && this.a.getWindowToken() != null) {
        }
        return true;
    }
}
