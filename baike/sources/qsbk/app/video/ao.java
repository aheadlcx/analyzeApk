package qsbk.app.video;

import android.media.MediaPlayer;
import android.os.Handler.Callback;
import android.os.Message;
import android.view.View;
import qsbk.app.utils.DebugUtil;

class ao implements Callback {
    final /* synthetic */ VideoEditPlayView a;

    ao(VideoEditPlayView videoEditPlayView) {
        this.a = videoEditPlayView;
    }

    public boolean handleMessage(Message message) {
        if (message.what == 0) {
            int scrollX;
            View view = (View) message.obj;
            switch (this.a.l) {
                case 0:
                    scrollX = view.getScrollX();
                    break;
                case 1:
                    scrollX = view.getScrollY();
                    break;
                default:
                    scrollX = 0;
                    break;
            }
            if (this.a.n != scrollX) {
                this.a.s.sendMessageDelayed(this.a.s.obtainMessage(0, view), 10);
                this.a.n = scrollX;
            } else if (this.a.q != null) {
                this.a.q.onScrollStop(this.a.l, (int) (((float) this.a.n) / this.a.m));
            }
            DebugUtil.debug(VideoEditPlayView.a, "mWidth:" + this.a.g + " mHeight:" + this.a.h + "  mScrollDistance:" + this.a.n);
        } else if (1 == message.what) {
            MediaPlayer mediaPlayer = (MediaPlayer) message.obj;
            mediaPlayer.pause();
            mediaPlayer.seekTo(0);
            if (this.a.r != null) {
                this.a.r.onPrepared();
            }
        }
        return false;
    }
}
