package com.budejie.www.activity.video;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.media.AudioTrack;
import android.media.MediaPlayer;
import android.media.MediaPlayer.OnBufferingUpdateListener;
import android.media.MediaPlayer.OnCompletionListener;
import android.media.MediaPlayer.OnErrorListener;
import android.media.MediaPlayer.OnInfoListener;
import android.media.MediaPlayer.OnPreparedListener;
import android.media.MediaPlayer.OnSeekCompleteListener;
import android.media.MediaPlayer.OnVideoSizeChangedListener;
import android.net.Uri;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.os.PowerManager;
import android.os.PowerManager.WakeLock;
import android.view.Surface;
import android.view.SurfaceHolder;
import java.io.IOException;
import java.lang.ref.WeakReference;

public class NativePlayer extends MediaPlayer {
    private static Rect e = null;
    private static Bitmap j;
    private int a;
    private int b = 0;
    private Surface c;
    private SurfaceHolder d;
    private a f;
    private WakeLock g = null;
    private boolean h;
    private boolean i;
    private boolean k = true;
    private OnVideoSizeChangedListener l;
    private OnSeekCompleteListener m;
    private OnPreparedListener n;
    private OnBufferingUpdateListener o;
    private OnCompletionListener p;
    private OnErrorListener q;
    private OnInfoListener r;
    private b s;

    private class a extends Handler {
        final /* synthetic */ NativePlayer a;
        private NativePlayer b;

        public a(NativePlayer nativePlayer, NativePlayer nativePlayer2, Looper looper) {
            this.a = nativePlayer;
            super(looper);
            this.b = nativePlayer2;
        }

        public void handleMessage(Message message) {
            if (this.b.a != 0) {
                switch (message.what) {
                    case 0:
                        return;
                    case 1:
                        if (this.a.n != null) {
                            this.a.n.onPrepared(this.b);
                            return;
                        }
                        return;
                    case 2:
                        if (this.a.p != null) {
                            this.a.p.onCompletion(this.b);
                        }
                        this.a.a(false);
                        return;
                    case 3:
                        if (this.a.o != null) {
                            this.a.o.onBufferingUpdate(this.b, message.arg1);
                            return;
                        }
                        return;
                    case 4:
                        if (this.a.m != null) {
                            this.a.m.onSeekComplete(this.b);
                            return;
                        }
                        return;
                    case 5:
                        if (this.a.l != null) {
                            if (NativePlayer.e == null) {
                                NativePlayer.e = new Rect();
                                NativePlayer.e.set(0, 0, message.arg1, message.arg2);
                            }
                            NativePlayer.j = Bitmap.createBitmap(message.arg1, message.arg2, Config.RGB_565);
                            this.a.l.onVideoSizeChanged(this.b, message.arg1, message.arg2);
                            return;
                        }
                        return;
                    case 100:
                        boolean onError;
                        if (this.a.q != null) {
                            onError = this.a.q.onError(this.b, message.arg1, message.arg2);
                        } else {
                            onError = false;
                        }
                        if (!(this.a.p == null || r0)) {
                            this.a.p.onCompletion(this.b);
                        }
                        this.a.a(false);
                        return;
                    case 200:
                        if (this.a.r != null) {
                            this.a.r.onInfo(this.b, message.arg1, message.arg2);
                            return;
                        }
                        return;
                    case 400:
                        if (this.a.s != null) {
                            this.a.s.a(this.b, message.arg1);
                            return;
                        }
                        return;
                    default:
                        return;
                }
            }
        }
    }

    public interface b {
        void a(MediaPlayer mediaPlayer, int i);
    }

    private static native void _nativeDraw(Bitmap bitmap);

    private native void _pause() throws IllegalStateException;

    private native void _release();

    private native void _reset();

    private native void _setAudioTrack(AudioTrack audioTrack) throws IOException;

    private native void _setVideoSurface(Surface surface) throws IOException;

    private native void _start() throws IllegalStateException;

    private native void _stop() throws IllegalStateException;

    private final native void native_finalize();

    private static final native void native_init() throws RuntimeException;

    private final native void native_setup(Object obj);

    private native int native_suspend_resume(boolean z);

    private native void setLocalLogable(boolean z);

    public native int getCurrentPosition();

    public native int getDuration();

    public native int getVideoHeight();

    public native int getVideoWidth();

    public native boolean isPlaying();

    public native void prepare() throws IOException, IllegalStateException;

    public native void seekTo(int i) throws IllegalStateException;

    public native void setAudioStreamType(int i);

    public native void setDataSource(String str) throws IOException, IllegalArgumentException, IllegalStateException;

    static {
        try {
            j jVar = new j();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public NativePlayer() {
        Looper myLooper = Looper.myLooper();
        if (myLooper != null) {
            this.f = new a(this, this, myLooper);
        } else {
            myLooper = Looper.getMainLooper();
            if (myLooper != null) {
                this.f = new a(this, this, myLooper);
            } else {
                this.f = null;
            }
        }
        native_init();
        native_setup(new WeakReference(this));
        setLocalLogable(this.k);
    }

    public void setDisplay(SurfaceHolder surfaceHolder) {
        this.d = surfaceHolder;
        if (surfaceHolder != null) {
            this.c = surfaceHolder.getSurface();
        } else {
            this.c = null;
        }
        b();
    }

    public void start() throws IllegalStateException {
        a(true);
        _start();
    }

    public void stop() throws IllegalStateException {
        a(false);
        _stop();
    }

    public void pause() throws IllegalStateException {
        a(false);
        _pause();
    }

    public void prepareAsync() throws IllegalStateException {
        try {
            prepare();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void setDataSource(Context context, Uri uri) {
        try {
            setDataSource(uri.toString());
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
        } catch (IllegalStateException e2) {
            e2.printStackTrace();
        } catch (IOException e3) {
            e3.printStackTrace();
        }
    }

    public void release() {
        a(false);
        b();
        this.n = null;
        this.o = null;
        this.p = null;
        this.m = null;
        this.q = null;
        this.r = null;
        this.l = null;
        _release();
        if (this.d != null) {
            Canvas lockCanvas = this.d.lockCanvas(e);
            if (lockCanvas != null) {
                lockCanvas.drawColor(-16777216);
                this.d.unlockCanvasAndPost(lockCanvas);
            }
        }
    }

    public void reset() {
        a(false);
        _reset();
        this.f.removeCallbacksAndMessages(null);
    }

    public void setWakeMode(Context context, int i) {
        boolean z;
        if (this.g != null) {
            boolean z2;
            if (this.g.isHeld()) {
                z2 = true;
                this.g.release();
            } else {
                z2 = false;
            }
            this.g = null;
            z = z2;
        } else {
            z = false;
        }
        this.g = ((PowerManager) context.getSystemService("power")).newWakeLock(536870912 | i, MediaPlayer.class.getName());
        this.g.setReferenceCounted(false);
        if (z) {
            this.g.acquire();
        }
    }

    public void setScreenOnWhilePlaying(boolean z) {
        if (this.h != z) {
            this.h = z;
            b();
        }
    }

    private void a(boolean z) {
        if (this.g != null) {
            if (z && !this.g.isHeld()) {
                this.g.acquire();
            } else if (!z && this.g.isHeld()) {
                this.g.release();
            }
        }
        this.i = z;
        b();
    }

    private void b() {
        if (this.d != null) {
            SurfaceHolder surfaceHolder = this.d;
            boolean z = this.h && this.i;
            surfaceHolder.setKeepScreenOn(z);
        }
    }

    public void setOnVideoSizeChangedListener(OnVideoSizeChangedListener onVideoSizeChangedListener) {
        this.l = onVideoSizeChangedListener;
    }

    public void setOnSeekCompleteListener(OnSeekCompleteListener onSeekCompleteListener) {
        this.m = onSeekCompleteListener;
    }

    public void setOnPreparedListener(OnPreparedListener onPreparedListener) {
        this.n = onPreparedListener;
    }

    public void setOnBufferingUpdateListener(OnBufferingUpdateListener onBufferingUpdateListener) {
        this.o = onBufferingUpdateListener;
    }

    public void setOnCompletionListener(OnCompletionListener onCompletionListener) {
        this.p = onCompletionListener;
    }

    public void setOnErrorListener(OnErrorListener onErrorListener) {
        this.q = onErrorListener;
    }

    public void setOnInfoListener(OnInfoListener onInfoListener) {
        this.r = onInfoListener;
    }
}
