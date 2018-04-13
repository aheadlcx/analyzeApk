package tv.danmaku.ijk.media.player;

import tv.danmaku.ijk.media.player.misc.IMediaDataSource;

public abstract class AbstractMediaPlayer implements IMediaPlayer {
    private IMediaPlayer$OnBufferingUpdateListener mOnBufferingUpdateListener;
    private IMediaPlayer$OnCompletionListener mOnCompletionListener;
    private IMediaPlayer$OnErrorListener mOnErrorListener;
    private IMediaPlayer$OnInfoListener mOnInfoListener;
    private IMediaPlayer$OnPreparedListener mOnPreparedListener;
    private IMediaPlayer$OnSeekCompleteListener mOnSeekCompleteListener;
    private IMediaPlayer$OnTimedTextListener mOnTimedTextListener;
    private IMediaPlayer$OnVideoSizeChangedListener mOnVideoSizeChangedListener;

    public final void setOnPreparedListener(IMediaPlayer$OnPreparedListener iMediaPlayer$OnPreparedListener) {
        this.mOnPreparedListener = iMediaPlayer$OnPreparedListener;
    }

    public final void setOnCompletionListener(IMediaPlayer$OnCompletionListener iMediaPlayer$OnCompletionListener) {
        this.mOnCompletionListener = iMediaPlayer$OnCompletionListener;
    }

    public final void setOnBufferingUpdateListener(IMediaPlayer$OnBufferingUpdateListener iMediaPlayer$OnBufferingUpdateListener) {
        this.mOnBufferingUpdateListener = iMediaPlayer$OnBufferingUpdateListener;
    }

    public final void setOnSeekCompleteListener(IMediaPlayer$OnSeekCompleteListener iMediaPlayer$OnSeekCompleteListener) {
        this.mOnSeekCompleteListener = iMediaPlayer$OnSeekCompleteListener;
    }

    public final void setOnVideoSizeChangedListener(IMediaPlayer$OnVideoSizeChangedListener iMediaPlayer$OnVideoSizeChangedListener) {
        this.mOnVideoSizeChangedListener = iMediaPlayer$OnVideoSizeChangedListener;
    }

    public final void setOnErrorListener(IMediaPlayer$OnErrorListener iMediaPlayer$OnErrorListener) {
        this.mOnErrorListener = iMediaPlayer$OnErrorListener;
    }

    public final void setOnInfoListener(IMediaPlayer$OnInfoListener iMediaPlayer$OnInfoListener) {
        this.mOnInfoListener = iMediaPlayer$OnInfoListener;
    }

    public final void setOnTimedTextListener(IMediaPlayer$OnTimedTextListener iMediaPlayer$OnTimedTextListener) {
        this.mOnTimedTextListener = iMediaPlayer$OnTimedTextListener;
    }

    public void resetListeners() {
        this.mOnPreparedListener = null;
        this.mOnBufferingUpdateListener = null;
        this.mOnCompletionListener = null;
        this.mOnSeekCompleteListener = null;
        this.mOnVideoSizeChangedListener = null;
        this.mOnErrorListener = null;
        this.mOnInfoListener = null;
        this.mOnTimedTextListener = null;
    }

    protected final void notifyOnPrepared() {
        if (this.mOnPreparedListener != null) {
            this.mOnPreparedListener.onPrepared(this);
        }
    }

    protected final void notifyOnCompletion() {
        if (this.mOnCompletionListener != null) {
            this.mOnCompletionListener.onCompletion(this);
        }
    }

    protected final void notifyOnBufferingUpdate(int i) {
        if (this.mOnBufferingUpdateListener != null) {
            this.mOnBufferingUpdateListener.onBufferingUpdate(this, i);
        }
    }

    protected final void notifyOnSeekComplete() {
        if (this.mOnSeekCompleteListener != null) {
            this.mOnSeekCompleteListener.onSeekComplete(this);
        }
    }

    protected final void notifyOnVideoSizeChanged(int i, int i2, int i3, int i4) {
        if (this.mOnVideoSizeChangedListener != null) {
            this.mOnVideoSizeChangedListener.onVideoSizeChanged(this, i, i2, i3, i4);
        }
    }

    protected final boolean notifyOnError(int i, int i2) {
        return this.mOnErrorListener != null && this.mOnErrorListener.onError(this, i, i2);
    }

    protected final boolean notifyOnInfo(int i, int i2) {
        return this.mOnInfoListener != null && this.mOnInfoListener.onInfo(this, i, i2);
    }

    protected final void notifyOnTimedText(IjkTimedText ijkTimedText) {
        if (this.mOnTimedTextListener != null) {
            this.mOnTimedTextListener.onTimedText(this, ijkTimedText);
        }
    }

    public void setDataSource(IMediaDataSource iMediaDataSource) {
        throw new UnsupportedOperationException();
    }
}
