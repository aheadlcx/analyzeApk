package com.zhihu.matisse.internal.lib;

import android.content.Context;
import android.graphics.SurfaceTexture;
import android.net.Uri;
import android.util.Log;
import android.view.Surface;
import android.view.SurfaceHolder;
import android.widget.MediaController.MediaPlayerControl;
import com.iflytek.cloud.SpeechConstant;
import java.io.IOException;
import tv.danmaku.ijk.media.player.AndroidMediaPlayer;
import tv.danmaku.ijk.media.player.IMediaPlayer;
import tv.danmaku.ijk.media.player.IMediaPlayer$OnBufferingUpdateListener;
import tv.danmaku.ijk.media.player.IMediaPlayer$OnCompletionListener;
import tv.danmaku.ijk.media.player.IMediaPlayer$OnErrorListener;
import tv.danmaku.ijk.media.player.IMediaPlayer$OnInfoListener;
import tv.danmaku.ijk.media.player.IMediaPlayer$OnPreparedListener;
import tv.danmaku.ijk.media.player.IMediaPlayer$OnSeekCompleteListener;
import tv.danmaku.ijk.media.player.IMediaPlayer$OnVideoSizeChangedListener;
import tv.danmaku.ijk.media.player.IjkMediaPlayer;

public class IjkSmartPlayer2 implements MediaPlayerControl {
    private static final int STATE_ERROR = -1;
    private static final int STATE_IDLE = 0;
    private static final int STATE_PAUSED = 4;
    private static final int STATE_PLAYBACK_COMPLETED = 5;
    private static final int STATE_PLAYING = 3;
    private static final int STATE_PREPARED = 2;
    private static final int STATE_PREPARING = 1;
    private static final String TAG = IjkSmartPlayer2.class.getName();
    private boolean mAndroidMediaPlayerAutoUsedIfError = true;
    private boolean mAndroidMediaPlayerUsed;
    private IMediaPlayer$OnBufferingUpdateListener mBufferingUpdateListener = new IMediaPlayer$OnBufferingUpdateListener() {
        public void onBufferingUpdate(IMediaPlayer iMediaPlayer, int i) {
            IjkSmartPlayer2.this.mCurrentBufferPercentage = i;
            if (IjkSmartPlayer2.this.mOnBufferingUpdateListener != null) {
                IjkSmartPlayer2.this.mOnBufferingUpdateListener.onBufferingUpdate(iMediaPlayer, i);
            }
        }
    };
    private IMediaPlayer$OnCompletionListener mCompletionListener = new IMediaPlayer$OnCompletionListener() {
        public void onCompletion(IMediaPlayer iMediaPlayer) {
            IjkSmartPlayer2.this.mCurrentState = 5;
            IjkSmartPlayer2.this.mTargetState = 5;
            if (IjkSmartPlayer2.this.mOnCompletionListener != null) {
                IjkSmartPlayer2.this.mOnCompletionListener.onCompletion(IjkSmartPlayer2.this.mMediaPlayer);
            }
        }
    };
    private final Context mContext;
    private int mCurrentBufferPercentage;
    private int mCurrentState = 0;
    private long mDuration;
    private IMediaPlayer$OnErrorListener mErrorListener = new IMediaPlayer$OnErrorListener() {
        public boolean onError(IMediaPlayer iMediaPlayer, int i, int i2) {
            IjkSmartPlayer2.this.mCurrentState = -1;
            IjkSmartPlayer2.this.mTargetState = -1;
            boolean z = false;
            if (IjkSmartPlayer2.this.mAndroidMediaPlayerAutoUsedIfError && iMediaPlayer.getCurrentPosition() <= 0 && IjkSmartPlayer2.this.tryAndroidMediaPlayer()) {
                z = true;
            }
            if (z || IjkSmartPlayer2.this.mOnErrorListener == null) {
                return true;
            }
            return IjkSmartPlayer2.this.mOnErrorListener.onError(iMediaPlayer, i, i2);
        }
    };
    private IMediaPlayer$OnInfoListener mInfoListener = new IMediaPlayer$OnInfoListener() {
        public boolean onInfo(IMediaPlayer iMediaPlayer, int i, int i2) {
            if (IjkSmartPlayer2.this.mOnInfoListener != null) {
                IjkSmartPlayer2.this.mOnInfoListener.onInfo(iMediaPlayer, i, i2);
            }
            return true;
        }
    };
    private IMediaPlayer mMediaPlayer;
    private IMediaPlayer$OnBufferingUpdateListener mOnBufferingUpdateListener;
    private IMediaPlayer$OnCompletionListener mOnCompletionListener;
    private IMediaPlayer$OnErrorListener mOnErrorListener;
    private IMediaPlayer$OnInfoListener mOnInfoListener;
    private IMediaPlayer$OnPreparedListener mOnPreparedListener;
    private IMediaPlayer$OnSeekCompleteListener mOnSeekCompleteListener;
    private IMediaPlayer$OnVideoSizeChangedListener mOnVideoSizeChangedListener;
    private IMediaPlayer$OnPreparedListener mPreparedListener = new IMediaPlayer$OnPreparedListener() {
        public void onPrepared(IMediaPlayer iMediaPlayer) {
            IjkSmartPlayer2.this.mCurrentState = 2;
            if (IjkSmartPlayer2.this.mOnPreparedListener != null) {
                IjkSmartPlayer2.this.mOnPreparedListener.onPrepared(IjkSmartPlayer2.this.mMediaPlayer);
            }
            if (IjkSmartPlayer2.this.mSeekWhenPrepared != 0) {
                IjkSmartPlayer2.this.seekTo(IjkSmartPlayer2.this.mSeekWhenPrepared);
            }
            if (IjkSmartPlayer2.this.mSpeedWhenPrepared != 1.0f) {
                IjkSmartPlayer2.this.setSpeed(IjkSmartPlayer2.this.mSpeedWhenPrepared);
            }
            if (IjkSmartPlayer2.this.mTargetState == 3) {
                IjkSmartPlayer2.this.start();
            }
        }
    };
    private IMediaPlayer$OnSeekCompleteListener mSeekCompleteListener = new IMediaPlayer$OnSeekCompleteListener() {
        public void onSeekComplete(IMediaPlayer iMediaPlayer) {
            if (IjkSmartPlayer2.this.mOnSeekCompleteListener != null) {
                IjkSmartPlayer2.this.mOnSeekCompleteListener.onSeekComplete(iMediaPlayer);
            }
        }
    };
    private int mSeekWhenPrepared;
    IMediaPlayer$OnVideoSizeChangedListener mSizeChangedListener = new IMediaPlayer$OnVideoSizeChangedListener() {
        public void onVideoSizeChanged(IMediaPlayer iMediaPlayer, int i, int i2, int i3, int i4) {
            if (IjkSmartPlayer2.this.mOnVideoSizeChangedListener != null) {
                IjkSmartPlayer2.this.mOnVideoSizeChangedListener.onVideoSizeChanged(iMediaPlayer, i, i2, i3, i4);
            }
        }
    };
    private float mSpeedWhenPrepared;
    private Surface mSurface;
    private SurfaceHolder mSurfaceHolder;
    private int mTargetState = 0;
    private Uri mUri;

    public IjkSmartPlayer2(Context context) {
        this.mContext = context;
        this.mCurrentState = 0;
        this.mTargetState = 0;
    }

    public MediaPlayerControl getPlayerControl() {
        return this;
    }

    public void setDisplay(SurfaceHolder surfaceHolder) {
        this.mSurfaceHolder = surfaceHolder;
    }

    public void setDisplay(SurfaceTexture surfaceTexture) {
        this.mSurface = new Surface(surfaceTexture);
    }

    public void setDataSource(String str) {
        setDataSource(Uri.parse(str));
    }

    public void setDataSource(Uri uri) {
        this.mUri = uri;
        this.mSeekWhenPrepared = 0;
        this.mSpeedWhenPrepared = 1.0f;
    }

    public void setVolume(float f) {
        if (this.mMediaPlayer != null) {
            this.mMediaPlayer.setVolume(f, f);
        }
    }

    public void forceUseAndroidMediaPlayer() {
        this.mAndroidMediaPlayerUsed = true;
    }

    public void setAutoUseAndroidMediaPlayerIfError(boolean z) {
        this.mAndroidMediaPlayerAutoUsedIfError = z;
    }

    private void initPlayer() throws IOException {
        if (this.mAndroidMediaPlayerUsed) {
            this.mMediaPlayer = buildAndroidMediaPlayer();
        } else {
            this.mMediaPlayer = buildIjkMediaPlayer();
        }
        this.mMediaPlayer.setOnPreparedListener(this.mPreparedListener);
        this.mMediaPlayer.setOnVideoSizeChangedListener(this.mSizeChangedListener);
        this.mMediaPlayer.setOnCompletionListener(this.mCompletionListener);
        this.mMediaPlayer.setOnErrorListener(this.mErrorListener);
        this.mMediaPlayer.setOnBufferingUpdateListener(this.mBufferingUpdateListener);
        this.mMediaPlayer.setOnInfoListener(this.mInfoListener);
        this.mMediaPlayer.setOnSeekCompleteListener(this.mSeekCompleteListener);
        this.mMediaPlayer.setAudioStreamType(3);
        this.mMediaPlayer.setScreenOnWhilePlaying(true);
        this.mMediaPlayer.setDataSource(this.mContext, this.mUri);
        if (this.mSurfaceHolder != null) {
            this.mMediaPlayer.setDisplay(this.mSurfaceHolder);
        } else if (this.mSurface != null) {
            this.mMediaPlayer.setSurface(this.mSurface);
        }
    }

    public boolean usingIjkMediaPlayer() {
        return !this.mAndroidMediaPlayerUsed;
    }

    public boolean tryAndroidMediaPlayer() {
        if (this.mAndroidMediaPlayerUsed) {
            return false;
        }
        this.mAndroidMediaPlayerUsed = true;
        start();
        return true;
    }

    private IMediaPlayer buildIjkMediaPlayer() {
        IMediaPlayer ijkMediaPlayer = new IjkMediaPlayer();
        ijkMediaPlayer.setOption(4, "overlay-format", 842225234);
        ijkMediaPlayer.setOption(4, "framedrop", 12);
        ijkMediaPlayer.setOption(4, "start-on-prepared", 0);
        ijkMediaPlayer.setOption(1, "reconnect", 1);
        ijkMediaPlayer.setOption(1, SpeechConstant.NET_TIMEOUT, 60000000);
        ijkMediaPlayer.setOption(1, "http-detect-range-support", 0);
        ijkMediaPlayer.setOption(1, "user_agent", "IjkMediaPlayer");
        ijkMediaPlayer.setOption(2, "skip_loop_filter", 0);
        ijkMediaPlayer.setOption(4, "soundtouch", 1);
        return ijkMediaPlayer;
    }

    private IMediaPlayer buildAndroidMediaPlayer() {
        return new AndroidMediaPlayer();
    }

    public void prepare() {
        if (this.mUri != null) {
            release(false);
            try {
                this.mDuration = -1;
                this.mCurrentBufferPercentage = 0;
                initPlayer();
                this.mMediaPlayer.prepareAsync();
                this.mCurrentState = 1;
            } catch (Throwable e) {
                Log.w(TAG, "Unable to open content: " + this.mUri, e);
                this.mCurrentState = -1;
                this.mTargetState = -1;
                if (this.mOnErrorListener != null) {
                    this.mOnErrorListener.onError(this.mMediaPlayer, 1, 0);
                }
            } catch (Throwable e2) {
                Log.w(TAG, "Unable to open content: " + this.mUri, e2);
                this.mCurrentState = -1;
                this.mTargetState = -1;
                if (this.mOnErrorListener != null) {
                    this.mOnErrorListener.onError(this.mMediaPlayer, 1, 0);
                }
            }
        }
    }

    public void start() {
        if (isInPlaybackState()) {
            this.mMediaPlayer.start();
            this.mCurrentState = 3;
            return;
        }
        prepare();
        this.mTargetState = 3;
    }

    public boolean isRemoteResource() {
        return this.mUri != null && ("http".equalsIgnoreCase(this.mUri.getScheme()) || "https".equalsIgnoreCase(this.mUri.getScheme()));
    }

    public void pause() {
        if (isInPlaybackState() && this.mMediaPlayer.isPlaying()) {
            this.mMediaPlayer.pause();
            this.mCurrentState = 4;
        }
        this.mTargetState = 4;
    }

    public void stop() {
        if (this.mMediaPlayer != null) {
            this.mMediaPlayer.stop();
        }
    }

    public void release() {
        release(true);
    }

    public void release(boolean z) {
        if (this.mMediaPlayer != null) {
            this.mMediaPlayer.reset();
            this.mMediaPlayer.release();
            this.mMediaPlayer = null;
            this.mSurfaceHolder = null;
            if (this.mSurface != null) {
                this.mSurface.release();
                this.mSurface = null;
            }
            this.mCurrentState = 0;
            if (z) {
                this.mTargetState = 0;
            }
        }
    }

    public int getDuration() {
        if (!isInPlaybackState()) {
            this.mDuration = -1;
            return (int) this.mDuration;
        } else if (this.mDuration > 0) {
            return (int) this.mDuration;
        } else {
            this.mDuration = this.mMediaPlayer.getDuration();
            return (int) this.mDuration;
        }
    }

    public int getCurrentPosition() {
        if (isInPlaybackState()) {
            return (int) this.mMediaPlayer.getCurrentPosition();
        }
        return 0;
    }

    public void seekTo(int i) {
        if (isInPlaybackState()) {
            this.mMediaPlayer.seekTo((long) i);
            this.mSeekWhenPrepared = 0;
            return;
        }
        this.mSeekWhenPrepared = i;
    }

    public void setSpeed(float f) {
        if (isInPlaybackState()) {
            if (this.mMediaPlayer instanceof IjkMediaPlayer) {
                ((IjkMediaPlayer) this.mMediaPlayer).setSpeed(f);
            }
            this.mSpeedWhenPrepared = 0.0f;
            return;
        }
        this.mSpeedWhenPrepared = f;
    }

    public boolean isPlaying() {
        return isInPlaybackState() && this.mMediaPlayer.isPlaying();
    }

    public int getBufferPercentage() {
        if (this.mMediaPlayer != null) {
            return this.mCurrentBufferPercentage;
        }
        return 0;
    }

    public boolean canPause() {
        return true;
    }

    public boolean canSeekBackward() {
        return true;
    }

    public boolean canSeekForward() {
        return true;
    }

    public int getAudioSessionId() {
        return 0;
    }

    public boolean isInPlaybackState() {
        return (this.mMediaPlayer == null || this.mCurrentState == -1 || this.mCurrentState == 0 || this.mCurrentState == 1) ? false : true;
    }

    public void setOnPreparedListener(IMediaPlayer$OnPreparedListener iMediaPlayer$OnPreparedListener) {
        this.mOnPreparedListener = iMediaPlayer$OnPreparedListener;
    }

    public void setOnCompletionListener(IMediaPlayer$OnCompletionListener iMediaPlayer$OnCompletionListener) {
        this.mOnCompletionListener = iMediaPlayer$OnCompletionListener;
    }

    public void setOnVideoSizeChangedListener(IMediaPlayer$OnVideoSizeChangedListener iMediaPlayer$OnVideoSizeChangedListener) {
        this.mOnVideoSizeChangedListener = iMediaPlayer$OnVideoSizeChangedListener;
    }

    public void setOnErrorListener(IMediaPlayer$OnErrorListener iMediaPlayer$OnErrorListener) {
        this.mOnErrorListener = iMediaPlayer$OnErrorListener;
    }

    public void setOnBufferingUpdateListener(IMediaPlayer$OnBufferingUpdateListener iMediaPlayer$OnBufferingUpdateListener) {
        this.mOnBufferingUpdateListener = iMediaPlayer$OnBufferingUpdateListener;
    }

    public void setOnSeekCompleteListener(IMediaPlayer$OnSeekCompleteListener iMediaPlayer$OnSeekCompleteListener) {
        this.mOnSeekCompleteListener = iMediaPlayer$OnSeekCompleteListener;
    }

    public void setOnInfoListener(IMediaPlayer$OnInfoListener iMediaPlayer$OnInfoListener) {
        this.mOnInfoListener = iMediaPlayer$OnInfoListener;
    }

    public void setLoop(boolean z) {
        this.mMediaPlayer.setLooping(z);
    }
}
