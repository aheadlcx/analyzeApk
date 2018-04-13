package cn.xiaochuankeji.tieba.ui.widget;

import android.content.Context;
import android.net.Uri;
import android.util.AttributeSet;
import android.util.Log;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceHolder.Callback;
import android.view.SurfaceView;
import android.view.View;
import android.view.accessibility.AccessibilityEvent;
import android.view.accessibility.AccessibilityNodeInfo;
import android.widget.MediaController;
import android.widget.MediaController.MediaPlayerControl;
import cn.xiaochuankeji.tieba.background.utils.g;
import cn.xiaochuankeji.tieba.common.d.a;
import com.izuiyou.a.a.b;
import java.util.Map;
import tv.danmaku.ijk.media.player.IMediaPlayer;
import tv.danmaku.ijk.media.player.IMediaPlayer$OnCompletionListener;
import tv.danmaku.ijk.media.player.IMediaPlayer$OnErrorListener;
import tv.danmaku.ijk.media.player.IMediaPlayer$OnInfoListener;
import tv.danmaku.ijk.media.player.IMediaPlayer$OnPreparedListener;
import tv.danmaku.ijk.media.player.IMediaPlayer$OnVideoSizeChangedListener;

public class VideoView extends SurfaceView implements MediaPlayerControl {
    Context a;
    IMediaPlayer$OnVideoSizeChangedListener b;
    IMediaPlayer$OnPreparedListener c;
    Callback d;
    private String e;
    private Uri f;
    private Map<String, String> g;
    private SurfaceHolder h;
    private a i;
    private int j;
    private int k;
    private int l;
    private int m;
    private MediaController n;
    private IMediaPlayer$OnCompletionListener o;
    private IMediaPlayer$OnPreparedListener p;
    private IMediaPlayer$OnErrorListener q;
    private IMediaPlayer$OnInfoListener r;
    private IMediaPlayer$OnCompletionListener s;
    private IMediaPlayer$OnErrorListener t;

    public VideoView(Context context) {
        super(context);
        this.e = "VideoView";
        this.h = null;
        this.i = null;
        this.b = new IMediaPlayer$OnVideoSizeChangedListener(this) {
            final /* synthetic */ VideoView a;

            {
                this.a = r1;
            }

            public void onVideoSizeChanged(IMediaPlayer iMediaPlayer, int i, int i2, int i3, int i4) {
                this.a.j = iMediaPlayer.getVideoWidth();
                this.a.k = iMediaPlayer.getVideoHeight();
                if (this.a.j != 0 && this.a.k != 0) {
                    this.a.getHolder().setFixedSize(this.a.j, this.a.k);
                    this.a.requestLayout();
                }
            }
        };
        this.c = new IMediaPlayer$OnPreparedListener(this) {
            final /* synthetic */ VideoView a;

            {
                this.a = r1;
            }

            public void onPrepared(IMediaPlayer iMediaPlayer) {
                if (this.a.p != null) {
                    this.a.p.onPrepared(iMediaPlayer);
                }
                if (this.a.n != null) {
                    this.a.n.setEnabled(true);
                }
                this.a.j = iMediaPlayer.getVideoWidth();
                this.a.k = iMediaPlayer.getVideoHeight();
                if (this.a.j != 0 && this.a.k != 0) {
                    this.a.getHolder().setFixedSize(this.a.j, this.a.k);
                    if (this.a.l != this.a.j || this.a.m != this.a.k) {
                        return;
                    }
                    if (this.a.isPlaying() && this.a.getCurrentPosition() == 0) {
                        if (this.a.n != null) {
                            this.a.n.show();
                        }
                    } else if (this.a.n != null) {
                        this.a.n.show(0);
                    }
                }
            }
        };
        this.s = new IMediaPlayer$OnCompletionListener(this) {
            final /* synthetic */ VideoView a;

            {
                this.a = r1;
            }

            public void onCompletion(IMediaPlayer iMediaPlayer) {
                if (this.a.n != null) {
                    this.a.n.hide();
                }
                if (this.a.o != null) {
                    this.a.o.onCompletion(iMediaPlayer);
                }
            }
        };
        this.t = new IMediaPlayer$OnErrorListener(this) {
            final /* synthetic */ VideoView a;

            {
                this.a = r1;
            }

            public boolean onError(IMediaPlayer iMediaPlayer, int i, int i2) {
                Log.d(this.a.e, "Error: " + i + "," + i2);
                b.e("视频播放错误, framework_err:" + i + "  ,impl_err:" + i2);
                if (this.a.n != null) {
                    this.a.n.hide();
                }
                if ((this.a.q == null || !this.a.q.onError(iMediaPlayer, i, i2)) && this.a.getWindowToken() != null) {
                    if (this.a.o != null) {
                        this.a.o.onCompletion(iMediaPlayer);
                    }
                    g.a("无法播放此视频");
                }
                return true;
            }
        };
        this.d = new Callback(this) {
            final /* synthetic */ VideoView a;

            {
                this.a = r1;
            }

            public void surfaceChanged(SurfaceHolder surfaceHolder, int i, int i2, int i3) {
                this.a.l = i2;
                this.a.m = i3;
                boolean a = this.a.a();
                Object obj = (this.a.j == i2 && this.a.k == i3) ? 1 : null;
                if (this.a.i != null && a && obj != null) {
                    this.a.start();
                }
            }

            public void surfaceCreated(SurfaceHolder surfaceHolder) {
                this.a.h = surfaceHolder;
                this.a.c();
            }

            public void surfaceDestroyed(SurfaceHolder surfaceHolder) {
                this.a.h = null;
                if (this.a.n != null) {
                    this.a.n.hide();
                }
                this.a.a(true);
            }
        };
        this.a = context;
        b();
    }

    public VideoView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
        this.a = context;
        b();
    }

    public VideoView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.e = "VideoView";
        this.h = null;
        this.i = null;
        this.b = /* anonymous class already generated */;
        this.c = /* anonymous class already generated */;
        this.s = /* anonymous class already generated */;
        this.t = /* anonymous class already generated */;
        this.d = /* anonymous class already generated */;
        this.a = context;
        b();
    }

    protected void onMeasure(int i, int i2) {
        int defaultSize = getDefaultSize(this.j, i);
        int defaultSize2 = getDefaultSize(this.k, i2);
        if (this.j > 0 && this.k > 0) {
            if (this.j * defaultSize2 > this.k * defaultSize) {
                defaultSize2 = (this.k * defaultSize) / this.j;
            } else if (this.j * defaultSize2 < this.k * defaultSize) {
                defaultSize = (this.j * defaultSize2) / this.k;
            }
        }
        setMeasuredDimension(defaultSize, defaultSize2);
    }

    public void onInitializeAccessibilityEvent(AccessibilityEvent accessibilityEvent) {
        super.onInitializeAccessibilityEvent(accessibilityEvent);
        accessibilityEvent.setClassName(VideoView.class.getName());
    }

    public void onInitializeAccessibilityNodeInfo(AccessibilityNodeInfo accessibilityNodeInfo) {
        super.onInitializeAccessibilityNodeInfo(accessibilityNodeInfo);
        accessibilityNodeInfo.setClassName(VideoView.class.getName());
    }

    private void b() {
        this.j = 0;
        this.k = 0;
        getHolder().addCallback(this.d);
        getHolder().setType(3);
        setFocusable(true);
        setFocusableInTouchMode(true);
        requestFocus();
    }

    public void setVideoPath(String str) {
        setVideoURI(Uri.parse(str));
    }

    public void setVideoURI(Uri uri) {
        a(uri, null);
    }

    public void a(Uri uri, Map<String, String> map) {
        this.f = uri;
        this.g = map;
        c();
        requestLayout();
        invalidate();
    }

    private void c() {
        if (this.f != null && this.h != null) {
            this.i = new a(this.a);
            this.i.a(this.c);
            this.i.a(this.b);
            this.i.a(this.s);
            this.i.a(this.t);
            this.i.a(this.r);
            this.i.a(this.h);
            this.i.a(this.f);
            this.i.start();
            d();
        }
    }

    public void setMediaController(MediaController mediaController) {
        if (this.n != null) {
            this.n.hide();
        }
        this.n = mediaController;
        d();
    }

    private void d() {
        if (this.i != null && this.n != null) {
            View view;
            this.n.setMediaPlayer(this);
            if (getParent() instanceof View) {
                view = (View) getParent();
            } else {
                view = this;
            }
            this.n.setAnchorView(view);
            this.n.setEnabled(a());
        }
    }

    public void setOnPreparedListener(IMediaPlayer$OnPreparedListener iMediaPlayer$OnPreparedListener) {
        this.p = iMediaPlayer$OnPreparedListener;
    }

    public void setOnCompletionListener(IMediaPlayer$OnCompletionListener iMediaPlayer$OnCompletionListener) {
        this.o = iMediaPlayer$OnCompletionListener;
    }

    public void setOnErrorListener(IMediaPlayer$OnErrorListener iMediaPlayer$OnErrorListener) {
        this.q = iMediaPlayer$OnErrorListener;
    }

    public void setOnInfoListener(IMediaPlayer$OnInfoListener iMediaPlayer$OnInfoListener) {
        this.r = iMediaPlayer$OnInfoListener;
    }

    private void a(boolean z) {
        if (this.i != null) {
            this.i.b(z);
            this.i = null;
        }
    }

    public boolean onTouchEvent(MotionEvent motionEvent) {
        if (a() && this.n != null) {
            e();
        }
        return false;
    }

    public boolean onTrackballEvent(MotionEvent motionEvent) {
        if (a() && this.n != null) {
            e();
        }
        return false;
    }

    public boolean onKeyDown(int i, KeyEvent keyEvent) {
        boolean z = (i == 4 || i == 24 || i == 25 || i == 164 || i == 82 || i == 5 || i == 6) ? false : true;
        if (a() && z && this.n != null) {
            if (i == 79 || i == 85) {
                if (this.i.isPlaying()) {
                    pause();
                    this.n.show();
                    return true;
                }
                start();
                this.n.hide();
                return true;
            } else if (i == 126) {
                if (this.i.isPlaying()) {
                    return true;
                }
                start();
                this.n.hide();
                return true;
            } else if (i != 86 && i != 127) {
                e();
            } else if (!this.i.isPlaying()) {
                return true;
            } else {
                pause();
                this.n.show();
                return true;
            }
        }
        return super.onKeyDown(i, keyEvent);
    }

    private void e() {
        if (this.n.isShowing()) {
            this.n.hide();
        } else {
            this.n.show();
        }
    }

    protected boolean a() {
        return this.i != null && this.i.h();
    }

    public void start() {
        if (this.i != null) {
            this.i.start();
        }
    }

    public void pause() {
        if (this.i != null) {
            this.i.pause();
        }
    }

    public int getDuration() {
        return this.i != null ? this.i.getDuration() : -1;
    }

    public int getCurrentPosition() {
        return this.i != null ? this.i.getCurrentPosition() : 0;
    }

    public void seekTo(int i) {
        if (this.i != null) {
            this.i.seekTo(i);
        }
    }

    public boolean isPlaying() {
        return this.i != null && this.i.isPlaying();
    }

    public int getBufferPercentage() {
        return this.i != null ? this.i.getBufferPercentage() : 0;
    }

    public boolean canPause() {
        return this.i != null ? this.i.canPause() : false;
    }

    public boolean canSeekBackward() {
        return this.i != null ? this.i.canSeekBackward() : false;
    }

    public boolean canSeekForward() {
        return this.i != null ? this.i.canSeekForward() : false;
    }

    public int getAudioSessionId() {
        return 0;
    }
}
