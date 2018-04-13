package qsbk.app.video;

import android.media.MediaPlayer;
import android.media.MediaPlayer.OnPreparedListener;
import android.os.Message;
import qsbk.app.utils.DebugUtil;

class as implements OnPreparedListener {
    final /* synthetic */ VideoEditPlayView a;

    as(VideoEditPlayView videoEditPlayView) {
        this.a = videoEditPlayView;
    }

    public void onPrepared(MediaPlayer mediaPlayer) {
        DebugUtil.debug(VideoEditPlayView.a, "onPrepared");
        mediaPlayer.seekTo(0);
        mediaPlayer.start();
        Message message = new Message();
        message.what = 1;
        message.obj = mediaPlayer;
        this.a.s.sendMessageDelayed(message, 10);
    }
}
