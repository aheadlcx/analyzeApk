package tv.danmaku.ijk.media.player;

import android.graphics.Rect;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import java.lang.ref.WeakReference;
import tv.danmaku.ijk.media.player.pragma.DebugLog;

class IjkMediaPlayer$EventHandler extends Handler {
    private final WeakReference<IjkMediaPlayer> mWeakPlayer;

    public IjkMediaPlayer$EventHandler(IjkMediaPlayer ijkMediaPlayer, Looper looper) {
        super(looper);
        this.mWeakPlayer = new WeakReference(ijkMediaPlayer);
    }

    public void handleMessage(Message message) {
        IjkMediaPlayer ijkMediaPlayer = (IjkMediaPlayer) this.mWeakPlayer.get();
        if (ijkMediaPlayer == null || IjkMediaPlayer.access$000(ijkMediaPlayer) == 0) {
            DebugLog.w(IjkMediaPlayer.access$100(), "IjkMediaPlayer went away with unhandled events");
            return;
        }
        switch (message.what) {
            case 0:
                return;
            case 1:
                ijkMediaPlayer.notifyOnPrepared();
                return;
            case 2:
                IjkMediaPlayer.access$200(ijkMediaPlayer, false);
                ijkMediaPlayer.notifyOnCompletion();
                return;
            case 3:
                long j = (long) message.arg1;
                if (j < 0) {
                    j = 0;
                }
                long duration = ijkMediaPlayer.getDuration();
                if (duration > 0) {
                    j = (j * 100) / duration;
                } else {
                    j = 0;
                }
                if (j >= 100) {
                    j = 100;
                }
                ijkMediaPlayer.notifyOnBufferingUpdate((int) j);
                return;
            case 4:
                ijkMediaPlayer.notifyOnSeekComplete();
                return;
            case 5:
                IjkMediaPlayer.access$302(ijkMediaPlayer, message.arg1);
                IjkMediaPlayer.access$402(ijkMediaPlayer, message.arg2);
                ijkMediaPlayer.notifyOnVideoSizeChanged(IjkMediaPlayer.access$300(ijkMediaPlayer), IjkMediaPlayer.access$400(ijkMediaPlayer), IjkMediaPlayer.access$500(ijkMediaPlayer), IjkMediaPlayer.access$600(ijkMediaPlayer));
                return;
            case 99:
                if (message.obj == null) {
                    ijkMediaPlayer.notifyOnTimedText(null);
                    return;
                } else {
                    ijkMediaPlayer.notifyOnTimedText(new IjkTimedText(new Rect(0, 0, 1, 1), (String) message.obj));
                    return;
                }
            case 100:
                DebugLog.e(IjkMediaPlayer.access$100(), "Error (" + message.arg1 + "," + message.arg2 + ")");
                if (!ijkMediaPlayer.notifyOnError(message.arg1, message.arg2)) {
                    ijkMediaPlayer.notifyOnCompletion();
                }
                IjkMediaPlayer.access$200(ijkMediaPlayer, false);
                return;
            case 200:
                switch (message.arg1) {
                    case 3:
                        DebugLog.i(IjkMediaPlayer.access$100(), "Info: MEDIA_INFO_VIDEO_RENDERING_START\n");
                        break;
                }
                ijkMediaPlayer.notifyOnInfo(message.arg1, message.arg2);
                return;
            case 10001:
                IjkMediaPlayer.access$502(ijkMediaPlayer, message.arg1);
                IjkMediaPlayer.access$602(ijkMediaPlayer, message.arg2);
                ijkMediaPlayer.notifyOnVideoSizeChanged(IjkMediaPlayer.access$300(ijkMediaPlayer), IjkMediaPlayer.access$400(ijkMediaPlayer), IjkMediaPlayer.access$500(ijkMediaPlayer), IjkMediaPlayer.access$600(ijkMediaPlayer));
                return;
            default:
                DebugLog.e(IjkMediaPlayer.access$100(), "Unknown message type " + message.what);
                return;
        }
    }
}
