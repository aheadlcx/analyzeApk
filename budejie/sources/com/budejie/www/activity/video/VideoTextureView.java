package com.budejie.www.activity.video;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.SurfaceTexture;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.media.MediaPlayer.OnBufferingUpdateListener;
import android.media.MediaPlayer.OnCompletionListener;
import android.media.MediaPlayer.OnErrorListener;
import android.media.MediaPlayer.OnPreparedListener;
import android.media.MediaPlayer.OnVideoSizeChangedListener;
import android.net.Uri;
import android.os.Build;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.util.Log;
import android.view.GestureDetector;
import android.view.GestureDetector.OnGestureListener;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.Surface;
import android.view.TextureView;
import android.view.TextureView.SurfaceTextureListener;
import android.view.View;
import android.widget.RelativeLayout.LayoutParams;
import com.budejie.www.activity.video.NativePlayer.b;
import com.budejie.www.activity.video.f.c;
import com.budejie.www.adapter.b.a;
import com.umeng.analytics.MobclickAgent;

@SuppressLint({"NewApi"})
public class VideoTextureView extends TextureView implements OnGestureListener, c {
    private boolean A;
    private GestureDetector B;
    private Surface C;
    private boolean D;
    private boolean E;
    private OnCompletionListener F;
    private b G;
    private OnErrorListener H;
    private OnBufferingUpdateListener I;
    private SurfaceTextureListener J;
    private Handler K;
    protected boolean a;
    OnVideoSizeChangedListener b;
    OnPreparedListener c;
    private String d;
    private Uri e;
    private int f;
    private boolean g;
    private int h;
    private int i;
    private MediaPlayer j;
    private Context k;
    private int l;
    private int m;
    private int n;
    private int o;
    private f p;
    private b q;
    private OnCompletionListener r;
    private OnPreparedListener s;
    private int t;
    private OnErrorListener u;
    private String v;
    private int w;
    private boolean x;
    private boolean y;
    private boolean z;

    public VideoTextureView(Context context) {
        super(context);
        this.d = "VideoTextureView";
        this.h = 0;
        this.i = 0;
        this.j = null;
        this.a = true;
        this.A = true;
        this.B = null;
        this.b = new OnVideoSizeChangedListener(this) {
            final /* synthetic */ VideoTextureView a;

            {
                this.a = r1;
            }

            public void onVideoSizeChanged(MediaPlayer mediaPlayer, int i, int i2) {
                Log.d(this.a.d, "onVideoSizeChanged width=" + i + ",height=" + i2);
                try {
                    this.a.l = mediaPlayer.getVideoWidth();
                    this.a.m = mediaPlayer.getVideoHeight();
                    if (this.a.l != 0 && this.a.m != 0) {
                        this.a.requestLayout();
                    }
                } catch (Exception e) {
                }
            }
        };
        this.c = new OnPreparedListener(this) {
            final /* synthetic */ VideoTextureView a;

            {
                this.a = r1;
            }

            /* JADX WARNING: inconsistent code. */
            /* Code decompiled incorrectly, please refer to instructions dump. */
            public void onPrepared(android.media.MediaPlayer r7) {
                /*
                r6 = this;
                r5 = 1;
                r4 = 0;
                r3 = 3;
                r0 = r6.a;
                r1 = 2;
                r0.h = r1;
                r0 = r6.a;
                r1 = r6.a;
                r2 = r6.a;
                r2 = r2.z = r5;
                r1 = r1.y = r2;
                r0.x = r1;
                r0 = r6.a;
                r0 = r0.s;
                if (r0 == 0) goto L_0x0031;
            L_0x0022:
                r0 = r6.a;
                r0 = r0.s;
                r1 = r6.a;
                r1 = r1.j;
                r0.onPrepared(r1);
            L_0x0031:
                r0 = r6.a;
                r0 = r0.p;
                if (r0 == 0) goto L_0x0042;
            L_0x0039:
                r0 = r6.a;
                r0 = r0.p;
                r0.setEnabled(r5);
            L_0x0042:
                r0 = r6.a;
                r1 = r7.getVideoWidth();
                r0.l = r1;
                r0 = r6.a;
                r1 = r7.getVideoHeight();
                r0.m = r1;
                r0 = r6.a;
                r0 = r0.w;
                if (r0 == 0) goto L_0x0061;
            L_0x005c:
                r1 = r6.a;
                r1.a(r0);
            L_0x0061:
                r1 = r6.a;
                r1 = r1.l;
                if (r1 == 0) goto L_0x0106;
            L_0x0069:
                r1 = r6.a;
                r1 = r1.m;
                if (r1 == 0) goto L_0x0106;
            L_0x0071:
                r1 = r6.a;
                r1 = r1.n;
                r2 = r6.a;
                r2 = r2.l;
                if (r1 != r2) goto L_0x00aa;
            L_0x007f:
                r1 = r6.a;
                r1 = r1.o;
                r2 = r6.a;
                r2 = r2.m;
                if (r1 != r2) goto L_0x00aa;
            L_0x008d:
                r1 = r6.a;
                r1 = r1.i;
                if (r1 != r3) goto L_0x00eb;
            L_0x0095:
                r0 = r6.a;
                r0.a();
                r0 = r6.a;
                r0 = r0.p;
                if (r0 == 0) goto L_0x00aa;
            L_0x00a2:
                r0 = r6.a;
                r0 = r0.getWindowToken();
                if (r0 == 0) goto L_0x00aa;
            L_0x00aa:
                r0 = r6.a;
                r0 = r0.A;
                if (r0 == 0) goto L_0x00d9;
            L_0x00b2:
                r0 = r6.a;
                r0.b(r4);
                r0 = r6.a;
                r0.A = r4;
                r0 = r6.a;
                r0 = r0.i;
                if (r0 != r3) goto L_0x00c9;
            L_0x00c4:
                r0 = r6.a;
                r0.a();
            L_0x00c9:
                r0 = r6.a;
                r0 = r0.p;
                if (r0 == 0) goto L_0x00d9;
            L_0x00d1:
                r0 = r6.a;
                r0 = r0.getWindowToken();
                if (r0 == 0) goto L_0x00d9;
            L_0x00d9:
                r0 = r6.a;
                r0 = r0.p;
                if (r0 == 0) goto L_0x00ea;
            L_0x00e1:
                r0 = r6.a;
                r0 = r0.p;
                r0.p();
            L_0x00ea:
                return;
            L_0x00eb:
                r1 = r6.a;
                r1 = r1.c();
                if (r1 != 0) goto L_0x00aa;
            L_0x00f3:
                if (r0 != 0) goto L_0x00fd;
            L_0x00f5:
                r0 = r6.a;
                r0 = r0.getCurrentPosition();
                if (r0 <= 0) goto L_0x00aa;
            L_0x00fd:
                r0 = r6.a;
                r0 = r0.p;
                if (r0 == 0) goto L_0x00aa;
            L_0x0105:
                goto L_0x00aa;
            L_0x0106:
                r0 = r6.a;
                r0 = r0.i;
                if (r0 != r3) goto L_0x00aa;
            L_0x010e:
                r0 = r6.a;
                r0.a();
                goto L_0x00aa;
                */
                throw new UnsupportedOperationException("Method not decompiled: com.budejie.www.activity.video.VideoTextureView.3.onPrepared(android.media.MediaPlayer):void");
            }
        };
        this.F = new OnCompletionListener(this) {
            final /* synthetic */ VideoTextureView a;

            {
                this.a = r1;
            }

            public void onCompletion(MediaPlayer mediaPlayer) {
                this.a.h = 5;
                this.a.i = 5;
                if (this.a.p != null) {
                }
                if (this.a.r != null) {
                    this.a.r.onCompletion(this.a.j);
                }
                if (this.a.p != null) {
                    this.a.p.o();
                }
            }
        };
        this.G = new b(this) {
            final /* synthetic */ VideoTextureView a;

            {
                this.a = r1;
            }

            public void a(MediaPlayer mediaPlayer, int i) {
                if (this.a.q != null) {
                    this.a.q.a(mediaPlayer, i);
                }
            }
        };
        this.H = new OnErrorListener(this) {
            final /* synthetic */ VideoTextureView a;

            {
                this.a = r1;
            }

            public boolean onError(MediaPlayer mediaPlayer, int i, int i2) {
                Log.d(this.a.d, "Error: " + i + "," + i2);
                MobclickAgent.onEvent(this.a.k, "E03-A06", "4.0以上视频播放错误:" + i + ":" + i2 + " url:" + this.a.e);
                this.a.h = -1;
                this.a.i = -1;
                try {
                    if (this.a.p != null) {
                        this.a.p.r();
                    }
                    return (this.a.u == null || this.a.u.onError(this.a.j, i, i2)) ? true : true;
                } catch (Exception e) {
                    MobclickAgent.onEvent(this.a.k, "cacheException", "VideoTextureView onError:" + e.getLocalizedMessage());
                    e.printStackTrace();
                }
            }
        };
        this.I = new OnBufferingUpdateListener(this) {
            final /* synthetic */ VideoTextureView a;

            {
                this.a = r1;
            }

            public void onBufferingUpdate(MediaPlayer mediaPlayer, int i) {
                Log.d("PlayerHelper", "onBufferingUpdate: percent=" + i);
                this.a.t = i;
            }
        };
        this.J = new SurfaceTextureListener(this) {
            final /* synthetic */ VideoTextureView a;

            {
                this.a = r1;
            }

            public void onSurfaceTextureAvailable(SurfaceTexture surfaceTexture, int i, int i2) {
                Log.d(this.a.d, "onSurfaceTextureAvailable width=" + i + ",height=" + i2);
                try {
                    this.a.C = new Surface(surfaceTexture);
                    if (this.a.g) {
                        this.a.j.setSurface(this.a.C);
                        this.a.p.h();
                        return;
                    }
                    this.a.j();
                    this.a.g = true;
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            public boolean onSurfaceTextureDestroyed(SurfaceTexture surfaceTexture) {
                Log.d(this.a.d, "onSurfaceTextureDestroyed");
                this.a.C = null;
                if (this.a.p != null) {
                    this.a.p.m();
                }
                return false;
            }

            public void onSurfaceTextureSizeChanged(SurfaceTexture surfaceTexture, int i, int i2) {
                Object obj = 1;
                Log.d(this.a.d, "onSurfaceTextureSizeChanged");
                this.a.n = i;
                this.a.o = i2;
                Object obj2 = this.a.i == 3 ? 1 : null;
                if (!(this.a.l == i && this.a.m == i2)) {
                    obj = null;
                }
                if (this.a.j != null && obj2 != null && r1 != null) {
                    if (this.a.w != 0) {
                        this.a.a(this.a.w);
                    }
                    this.a.a();
                }
            }

            public void onSurfaceTextureUpdated(SurfaceTexture surfaceTexture) {
                Log.d(this.a.d, "onSurfaceTextureUpdated");
            }
        };
        this.K = new Handler(this) {
            final /* synthetic */ VideoTextureView a;

            {
                this.a = r1;
            }

            public void handleMessage(Message message) {
                if (this.a.H != null && this.a.h == 1) {
                    if (this.a.p != null) {
                        this.a.p.r();
                    }
                    this.a.h = -1;
                    this.a.i = -1;
                    this.a.H.onError(this.a.j, 1, 0);
                }
            }
        };
        Log.d(this.d, "VideoTextureView");
        this.k = context;
        i();
    }

    public VideoTextureView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
        this.k = context;
        i();
    }

    public VideoTextureView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.d = "VideoTextureView";
        this.h = 0;
        this.i = 0;
        this.j = null;
        this.a = true;
        this.A = true;
        this.B = null;
        this.b = /* anonymous class already generated */;
        this.c = /* anonymous class already generated */;
        this.F = /* anonymous class already generated */;
        this.G = /* anonymous class already generated */;
        this.H = /* anonymous class already generated */;
        this.I = /* anonymous class already generated */;
        this.J = /* anonymous class already generated */;
        this.K = /* anonymous class already generated */;
        this.k = context;
        i();
    }

    public void f() {
        this.E = true;
    }

    public void setComplete(boolean z) {
        this.D = z;
    }

    public int getVideoWidth() {
        return this.l;
    }

    public int getVideoHeight() {
        return this.m;
    }

    protected void onMeasure(int i, int i2) {
        int defaultSize = getDefaultSize(this.l, i);
        int defaultSize2 = getDefaultSize(this.m, i2);
        Log.d(this.d, "onMeasure mVideoWidth=" + this.l + ",mVideoHeight=" + this.m);
        Log.d(this.d, "onMeasure width=" + defaultSize + ",height=" + defaultSize2);
        if (this.l > 0 && this.m > 0) {
            if (defaultSize2 == a.b - a.f) {
                defaultSize = a.a;
            } else if (this.l * defaultSize2 > this.m * defaultSize) {
                defaultSize2 = (this.m * defaultSize) / this.l;
            } else if (this.l * defaultSize2 < this.m * defaultSize) {
                defaultSize = (this.l * defaultSize2) / this.m;
            }
        }
        setMeasuredDimension(defaultSize, defaultSize2);
    }

    private void i() {
        Log.d(this.d, "initVideoView");
        this.B = new GestureDetector(this);
        this.l = 0;
        this.m = 0;
        setSurfaceTextureListener(this.J);
        if (!this.E) {
            setKeepScreenOn(true);
        }
        setFocusable(true);
        setFocusableInTouchMode(true);
        requestFocus();
        this.h = 0;
        this.i = 0;
    }

    public void setVideoPath(String str) {
        if (!TextUtils.isEmpty(str)) {
            str = com.lt.a.a(this.k).a(str);
        }
        setVideoURI(Uri.parse(str.trim()));
    }

    public void setVideoURI(Uri uri) {
        try {
            this.e = uri;
            this.w = 0;
            j();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void g() {
        setKeepScreenOn(false);
        new Thread(this) {
            final /* synthetic */ VideoTextureView a;

            {
                this.a = r1;
            }

            public void run() {
                if (this.a.j != null) {
                    try {
                        this.a.j.stop();
                        this.a.j.release();
                        this.a.j = null;
                        this.a.h = 0;
                        this.a.i = 0;
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        }.start();
    }

    private void j() {
        Log.d(this.d, "openVideo mSurface == null is " + (this.C == null));
        if (this.e != null && this.C != null) {
            try {
                if (this.j != null) {
                    this.j.release();
                }
                this.j = new MediaPlayer();
                this.j.setDataSource(this.k, this.e);
                this.j.prepareAsync();
                this.j.setLooping(false);
                this.j.setOnPreparedListener(this.c);
                this.j.setOnVideoSizeChangedListener(this.b);
                this.f = -1;
                this.j.setOnCompletionListener(this.F);
                this.j.setOnErrorListener(this.H);
                this.j.setOnBufferingUpdateListener(this.I);
                this.t = 0;
                this.j.setAudioStreamType(3);
                this.j.setSurface(this.C);
                Log.d("PlayerHelper", "openVideo: prepareAsync");
                if (this.E) {
                    this.j.setLooping(true);
                }
                this.h = 1;
                k();
                if (!this.e.getHost().contains("spriteapp.cn") && !this.e.getHost().contains("bdjproxy")) {
                    this.K.sendEmptyMessageDelayed(0, 2000);
                }
            } catch (Throwable e) {
                if (this.p != null) {
                    this.p.r();
                }
                Log.w(this.d, "Unable to open content: " + this.e, e);
                this.h = -1;
                this.i = -1;
                this.H.onError(this.j, 1, 0);
            } catch (Throwable e2) {
                if (this.p != null) {
                    this.p.r();
                }
                Log.w(this.d, "Unable to open content: " + this.e, e2);
                this.h = -1;
                this.i = -1;
                this.H.onError(this.j, 1, 0);
            }
        }
    }

    public void a(boolean z) {
        if (this.j == null) {
            return;
        }
        if (z) {
            AudioManager audioManager = (AudioManager) this.k.getSystemService("audio");
            float streamVolume = (1.0f * ((float) audioManager.getStreamVolume(3))) / ((float) audioManager.getStreamMaxVolume(3));
            this.j.setVolume(streamVolume, streamVolume);
            return;
        }
        this.j.setVolume(0.0f, 0.0f);
    }

    public boolean e() {
        return this.h == 5;
    }

    public void setMircroMediaController(f fVar) {
        this.p = fVar;
        k();
    }

    private void k() {
        if (this.j != null && this.p != null) {
            View view;
            this.p.setMediaPlayer(this);
            if (getParent() instanceof View) {
                view = (View) getParent();
            } else {
                view = this;
            }
            this.p.setAnchorView(view);
            this.p.setEnabled(h());
        }
    }

    public void setOnPreparedListener(OnPreparedListener onPreparedListener) {
        this.s = onPreparedListener;
    }

    public void setOnCompletionListener(OnCompletionListener onCompletionListener) {
        this.r = onCompletionListener;
    }

    public void setOnLoadingPerListener(b bVar) {
        this.q = bVar;
    }

    public void setOnErrorListener(OnErrorListener onErrorListener) {
        this.u = onErrorListener;
    }

    public boolean onTouchEvent(MotionEvent motionEvent) {
        return this.B.onTouchEvent(motionEvent);
    }

    public boolean onTrackballEvent(MotionEvent motionEvent) {
        if (h() && this.p == null) {
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
        if (h() && r0 && this.p != null) {
            if (i == 79 || i == 85) {
                if (this.j.isPlaying()) {
                    b();
                    return true;
                }
                a();
                return true;
            } else if (i == 86 && this.j.isPlaying()) {
                b();
            }
        }
        return super.onKeyDown(i, keyEvent);
    }

    public void a() {
        if (h() && !this.D) {
            try {
                this.j.start();
                k.a(this.k).g();
                this.h = 3;
                this.i = 3;
            } catch (IllegalStateException e) {
                e.printStackTrace();
            }
        }
        this.D = false;
    }

    public void b() {
        if (h() && this.j.isPlaying()) {
            this.j.pause();
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
            i = this.l;
            i2 = this.m;
            if (i > 0 && i2 > 0) {
                if (i * height > width * i2) {
                    i2 = (i * height) / i2;
                    int i4 = height;
                    height = 0;
                    i3 = i2;
                    i2 = i2 - width > 0 ? i2 - width : 0;
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
        i = this.l;
        i2 = this.m;
        if (i > 0 && i2 > 0) {
            if (i * height > width * i2) {
                height = (width * i2) / i;
            } else if (i * height < width * i2) {
                width = (i * height) / i2;
            }
        }
        a(width, height, 0, 0);
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
        Log.d(this.d, "setVideoViewScale width=" + i + ",height=" + i2);
        setLayoutParams(layoutParams);
    }

    public int getDuration() {
        if (!h()) {
            this.f = -1;
            return this.f;
        } else if (this.f > 0) {
            return this.f;
        } else {
            this.f = this.j.getDuration();
            return this.f;
        }
    }

    public int getCurrentPosition() {
        if (h()) {
            return this.j.getCurrentPosition();
        }
        return 0;
    }

    public void a(int i) {
        Log.d(this.d, "seekTo msec=" + i);
        if (h()) {
            this.j.seekTo(i);
            this.w = 0;
            return;
        }
        this.w = i;
    }

    public boolean c() {
        boolean isPlaying;
        try {
            if (this.j != null) {
                isPlaying = this.j.isPlaying();
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
        if (this.j != null) {
            return this.t;
        }
        return 0;
    }

    public boolean h() {
        return (this.j == null || this.h == -1 || this.h == 0 || this.h == 1) ? false : true;
    }

    public void setVideoTitle(String str) {
        this.v = str;
    }

    public boolean d() {
        return this.x;
    }

    public String getVideoTitle() {
        return this.v;
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
