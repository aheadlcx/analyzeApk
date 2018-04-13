package com.budejie.www.activity.video;

import android.content.Context;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.media.MediaPlayer.OnBufferingUpdateListener;
import android.media.MediaPlayer.OnCompletionListener;
import android.media.MediaPlayer.OnErrorListener;
import android.media.MediaPlayer.OnPreparedListener;
import android.media.MediaPlayer.OnVideoSizeChangedListener;
import android.net.Uri;
import android.os.Build;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.util.Log;
import android.view.GestureDetector;
import android.view.GestureDetector.OnGestureListener;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceHolder.Callback;
import android.view.SurfaceView;
import android.view.View;
import android.widget.RelativeLayout.LayoutParams;
import com.budejie.www.activity.video.NativePlayer.b;
import com.budejie.www.activity.video.f.c;
import com.lt.a;

public class VideoView extends SurfaceView implements OnGestureListener, c {
    private boolean A;
    private boolean B;
    private GestureDetector C;
    private boolean D;
    private int E;
    private OnCompletionListener F;
    private b G;
    private OnErrorListener H;
    private OnBufferingUpdateListener I;
    protected boolean a;
    OnVideoSizeChangedListener b;
    OnPreparedListener c;
    Callback d;
    private String e;
    private Uri f;
    private int g;
    private int h;
    private int i;
    private SurfaceHolder j;
    private MediaPlayer k;
    private Context l;
    private int m;
    private int n;
    private int o;
    private int p;
    private f q;
    private b r;
    private OnCompletionListener s;
    private OnPreparedListener t;
    private int u;
    private OnErrorListener v;
    private String w;
    private int x;
    private boolean y;
    private boolean z;

    public void setContainerH(int i) {
        this.E = i;
    }

    public VideoView(Context context) {
        super(context);
        this.e = "VideoView";
        this.h = 0;
        this.i = 0;
        this.j = null;
        this.k = null;
        this.a = true;
        this.B = true;
        this.C = null;
        this.b = new VideoView$2(this);
        this.c = new VideoView$3(this);
        this.F = new VideoView$4(this);
        this.G = new VideoView$5(this);
        this.H = new VideoView$6(this);
        this.I = new VideoView$7(this);
        this.d = new VideoView$8(this);
        this.l = context;
        f();
    }

    public VideoView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
        this.l = context;
        f();
    }

    public VideoView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.e = "VideoView";
        this.h = 0;
        this.i = 0;
        this.j = null;
        this.k = null;
        this.a = true;
        this.B = true;
        this.C = null;
        this.b = new VideoView$2(this);
        this.c = new VideoView$3(this);
        this.F = new VideoView$4(this);
        this.G = new VideoView$5(this);
        this.H = new VideoView$6(this);
        this.I = new VideoView$7(this);
        this.d = new VideoView$8(this);
        this.l = context;
        f();
    }

    public void setComplete(boolean z) {
        this.D = z;
    }

    public int getVideoWidth() {
        return this.m;
    }

    public int getVideoHeight() {
        return this.n;
    }

    protected void onMeasure(int i, int i2) {
        Log.i("@@@", "onMeasure");
        setMeasuredDimension(getDefaultSize(this.m, i), getDefaultSize(this.n, i2));
    }

    public void f() {
        Log.d(this.e, "initVideoView()");
        this.E = 0;
        this.C = new GestureDetector(this);
        this.m = 0;
        this.n = 0;
        getHolder().addCallback(this.d);
        getHolder().setSizeFromLayout();
        if (!i.f()) {
            getHolder().setType(3);
        }
        setKeepScreenOn(true);
        setFocusable(true);
        setFocusableInTouchMode(true);
        requestFocus();
        this.h = 0;
        this.i = 0;
        getHolder().setFormat(-3);
    }

    public void setVideoPath(String str) {
        if (!TextUtils.isEmpty(str)) {
            str = a.a(this.l).a(str);
        }
        setVideoURI(Uri.parse(str.trim()));
    }

    public void setVideoURI(Uri uri) {
        try {
            this.f = uri;
            this.x = 0;
            i();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void g() {
        setKeepScreenOn(false);
        new VideoView$1(this).start();
    }

    private void i() {
        if (this.f != null && this.j != null) {
            try {
                if (this.k != null) {
                    this.k.release();
                }
                if (i.f()) {
                    this.k = new NativePlayer();
                } else {
                    this.k = new MediaPlayer();
                }
                this.k.setDataSource(this.l, this.f);
                this.k.prepareAsync();
                this.k.setLooping(false);
                this.k.setOnPreparedListener(this.c);
                this.k.setOnVideoSizeChangedListener(this.b);
                this.g = -1;
                this.k.setOnCompletionListener(this.F);
                this.k.setOnErrorListener(this.H);
                this.k.setOnBufferingUpdateListener(this.I);
                this.u = 0;
                this.k.setDisplay(this.j);
                this.k.setAudioStreamType(3);
                this.k.setScreenOnWhilePlaying(true);
                this.h = 1;
                j();
            } catch (Throwable e) {
                if (this.q != null) {
                    this.q.r();
                }
                Log.w(this.e, "Unable to open content: " + this.f, e);
                this.h = -1;
                this.i = -1;
                this.H.onError(this.k, 1, 0);
            } catch (Throwable e2) {
                if (this.q != null) {
                    this.q.r();
                }
                Log.w(this.e, "Unable to open content: " + this.f, e2);
                this.h = -1;
                this.i = -1;
                this.H.onError(this.k, 1, 0);
            }
        }
    }

    public void setMircroMediaController(f fVar) {
        this.q = fVar;
        j();
    }

    private void j() {
        if (this.k != null && this.q != null) {
            View view;
            this.q.setMediaPlayer(this);
            if (getParent() instanceof View) {
                view = (View) getParent();
            } else {
                view = this;
            }
            this.q.setAnchorView(view);
            this.q.setEnabled(h());
        }
    }

    public void setOnPreparedListener(OnPreparedListener onPreparedListener) {
        this.t = onPreparedListener;
    }

    public void setOnCompletionListener(OnCompletionListener onCompletionListener) {
        this.s = onCompletionListener;
    }

    public void setOnLoadingPerListener(b bVar) {
        this.r = bVar;
    }

    public void setOnErrorListener(OnErrorListener onErrorListener) {
        this.v = onErrorListener;
    }

    public boolean onTouchEvent(MotionEvent motionEvent) {
        return this.C.onTouchEvent(motionEvent);
    }

    public boolean onTrackballEvent(MotionEvent motionEvent) {
        if (h() && this.q == null) {
        }
        return false;
    }

    public boolean onKeyDown(int i, KeyEvent keyEvent) {
        boolean z = false;
        if ((this.h == 2 || this.h == 3 || this.h == 4) && (i == 25 || i == 24)) {
            return false;
        }
        if (!(i == 4 || i == 24 || i == 25 || i == 82 || i == 5 || i == 6)) {
            z = true;
        }
        if (h() && r0 && this.q != null) {
            if (i == 79 || i == 85) {
                if (this.k.isPlaying()) {
                    b();
                    return true;
                }
                a();
                return true;
            } else if (i == 86 && this.k.isPlaying()) {
                b();
            }
        }
        return super.onKeyDown(i, keyEvent);
    }

    public void a() {
        Log.d(this.e, "start() mCurrentState = " + this.h);
        Log.d(this.e, "start() mTargetState = " + this.i);
        if (h() && !this.D) {
            try {
                this.k.start();
                k.a(this.l).a(true);
                k.a(this.l).g();
                this.h = 3;
                this.i = 3;
            } catch (IllegalStateException e) {
                e.printStackTrace();
            }
        }
        this.D = false;
    }

    public void b() {
        if (h() && this.k.isPlaying()) {
            this.k.pause();
            this.h = 4;
        }
        this.i = 4;
    }

    private void b(boolean z) {
        int height = getHeight();
        int width = getWidth();
        int i;
        int i2;
        if (z) {
            int i3;
            i = this.m;
            i2 = this.n;
            if (i > 0 && i2 > 0) {
                if (i * height > width * i2) {
                    i2 = (i * height) / i2;
                    if (i2 - width > 0) {
                        i = i2 - width;
                    } else {
                        i = 0;
                    }
                    int i4 = height;
                    height = 0;
                    i3 = i2;
                    i2 = i;
                    i = i4;
                } else if (i * height < width * i2) {
                    i2 = (i2 * width) / i;
                    height = i2 - height > 0 ? i2 - height : 0;
                    i = i2;
                    i2 = 0;
                    i3 = width;
                }
                a(i3, i, i2, height);
                return;
            }
            i = height;
            i2 = 0;
            height = 0;
            i3 = width;
            a(i3, i, i2, height);
            return;
        }
        i = this.m;
        i2 = this.n;
        if (i > 0 && i2 > 0) {
            if (i * height > width * i2) {
                height = (width * i2) / i;
                i = width;
            } else if (i * height < width * i2) {
                i = (i * height) / i2;
            }
            if (i >= width) {
                a(i, height, i - width, 0);
            } else {
                a(i, height, 0, 0);
            }
        }
        i = width;
        if (i >= width) {
            a(i, height, 0, 0);
        } else {
            a(i, height, i - width, 0);
        }
    }

    private void a(int i, int i2, int i3, int i4) {
        LayoutParams layoutParams = (LayoutParams) getLayoutParams();
        if (!Build.MODEL.equals("GT-I9003")) {
            layoutParams.leftMargin = (-i3) / 2;
            layoutParams.rightMargin = (-i3) / 2;
            layoutParams.topMargin = (-i4) / 2;
            layoutParams.bottomMargin = (-i4) / 2;
        }
        layoutParams.height = i2;
        layoutParams.width = i;
        setLayoutParams(layoutParams);
    }

    public int getDuration() {
        if (!h()) {
            this.g = -1;
            return this.g;
        } else if (this.g > 0) {
            return this.g;
        } else {
            this.g = this.k.getDuration();
            return this.g;
        }
    }

    public int getCurrentPosition() {
        if (h()) {
            return this.k.getCurrentPosition();
        }
        return 0;
    }

    public int getCurrentState() {
        return this.h;
    }

    public void a(int i) {
        Log.d(this.e, "seekTo msec=" + i);
        if (h()) {
            Log.d(this.e, "mMediaPlayer.seekTo" + i);
            this.k.seekTo(i);
            this.x = 0;
            return;
        }
        Log.d(this.e, "mSeekWhenPrepared=" + i);
        this.x = i;
    }

    public boolean c() {
        boolean isPlaying;
        try {
            if (this.k != null) {
                isPlaying = this.k.isPlaying();
                if (!h() && r0) {
                    return true;
                }
            }
        } catch (IllegalStateException e) {
            e.printStackTrace();
            isPlaying = false;
        } catch (Exception e2) {
            e2.printStackTrace();
        }
        isPlaying = false;
        return !h() ? false : false;
    }

    public int getBufferPercentage() {
        if (this.k != null) {
            return this.u;
        }
        return 0;
    }

    public boolean h() {
        return (this.k == null || this.h == -1 || this.h == 0 || this.h == 1) ? false : true;
    }

    public void setVideoTitle(String str) {
        this.w = str;
    }

    public boolean d() {
        return this.y;
    }

    public String getVideoTitle() {
        return this.w;
    }

    public void a(boolean z) {
        if (this.k == null) {
            return;
        }
        if (z) {
            AudioManager audioManager = (AudioManager) this.l.getSystemService("audio");
            float streamVolume = (1.0f * ((float) audioManager.getStreamVolume(3))) / ((float) audioManager.getStreamMaxVolume(3));
            this.k.setVolume(streamVolume, streamVolume);
            return;
        }
        this.k.setVolume(0.0f, 0.0f);
    }

    public boolean e() {
        return this.h == 5;
    }

    public boolean onDown(MotionEvent motionEvent) {
        return true;
    }

    public void onShowPress(MotionEvent motionEvent) {
    }

    public boolean onSingleTapUp(MotionEvent motionEvent) {
        return true;
    }

    public boolean onScroll(MotionEvent motionEvent, MotionEvent motionEvent2, float f, float f2) {
        return false;
    }

    public void onLongPress(MotionEvent motionEvent) {
    }

    public boolean onFling(MotionEvent motionEvent, MotionEvent motionEvent2, float f, float f2) {
        return false;
    }
}
