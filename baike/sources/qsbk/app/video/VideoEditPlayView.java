package qsbk.app.video;

import android.annotation.TargetApi;
import android.content.Context;
import android.graphics.SurfaceTexture;
import android.media.MediaPlayer;
import android.media.MediaPlayer.OnCompletionListener;
import android.os.Handler;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.Surface;
import android.view.TextureView;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.RelativeLayout.LayoutParams;
import java.util.Timer;
import qsbk.app.R;
import qsbk.app.utils.DebugUtil;
import qsbk.app.widget.HVScrollView;

public class VideoEditPlayView extends RelativeLayout {
    public static final int HORIZONTAL = 0;
    public static final int VERTICAL = 1;
    private static final String a = VideoEditPlayView.class.getSimpleName();
    private Context b;
    private HVScrollView c;
    private LinearLayout d;
    private ImageView e;
    private int f;
    private int g;
    private int h;
    private TextureView i;
    private MediaPlayer j;
    private String k;
    private int l;
    private float m;
    private int n;
    private OnCompletionListener o;
    private OnPauseListener p;
    private OnScrollStopListener q;
    private OnPreparedListener r;
    private Handler s;

    public interface OnPauseListener {
        void onPause();
    }

    public interface OnPreparedListener {
        void onPrepared();
    }

    public interface OnScrollStopListener {
        void onScrollStop(int i, int i2);
    }

    public VideoEditPlayView(Context context) {
        this(context, null);
    }

    public VideoEditPlayView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public VideoEditPlayView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.l = 0;
        this.m = 1.0f;
        this.n = 0;
        this.s = new Handler(new ao(this));
        a(context);
    }

    @TargetApi(11)
    private void a(Context context) {
        this.b = context;
        View inflate = LayoutInflater.from(context).inflate(R.layout.layout_video_edit_play_view, this);
        this.c = (HVScrollView) inflate.findViewById(R.id.scrollview);
        this.c.setFillViewport(true);
        this.d = (LinearLayout) inflate.findViewById(R.id.preview);
        this.i = (TextureView) findViewById(R.id.textureView);
        this.e = (ImageView) inflate.findViewById(R.id.wartermark);
    }

    public void setView(String str, int i, int i2, int i3, boolean z) {
        DebugUtil.debug(a, "setView videoWidth:" + i + "  videoHeight:" + i2);
        this.k = str;
        this.f = i3;
        if (z) {
            if (i <= i2) {
                this.g = this.f;
                this.m = (((float) this.g) * 1.0f) / ((float) i);
                this.h = (int) (((float) i2) * this.m);
            } else {
                this.h = this.f;
                this.m = (((float) this.h) * 1.0f) / ((float) i2);
                this.g = (int) (this.m * ((float) i));
            }
        } else if (i <= i2) {
            this.h = this.f;
            this.m = (((float) this.h) * 1.0f) / ((float) i2);
            this.g = (int) (this.m * ((float) i));
        } else {
            this.g = this.f;
            this.m = (((float) this.g) * 1.0f) / ((float) i);
            this.h = (int) (((float) i2) * this.m);
        }
        DebugUtil.debug(a, "setView mWidth:" + this.g + "  mHeight:" + this.h);
        LayoutParams layoutParams = (LayoutParams) this.c.getLayoutParams();
        layoutParams.width = this.f;
        layoutParams.height = this.f;
        this.c.setLayoutParams(layoutParams);
        FrameLayout.LayoutParams layoutParams2 = (FrameLayout.LayoutParams) this.d.getLayoutParams();
        if (!z) {
            if (this.g > this.h) {
                this.d.setOrientation(0);
            } else {
                this.d.setOrientation(1);
            }
            layoutParams2.width = this.f;
            layoutParams2.height = this.f;
        } else if (this.g > this.h) {
            layoutParams2.width = -2;
            layoutParams2.height = this.h;
            this.d.setOrientation(0);
            this.l = 0;
        } else {
            layoutParams2.width = this.g;
            layoutParams2.height = -2;
            this.d.setOrientation(1);
            this.l = 1;
        }
        this.d.setLayoutParams(layoutParams2);
        this.d.setGravity(17);
        layoutParams2 = (FrameLayout.LayoutParams) this.i.getLayoutParams();
        layoutParams2.width = this.g;
        layoutParams2.height = this.h;
        this.i.setLayoutParams(layoutParams2);
        this.i.setSurfaceTextureListener(new ap(this));
        this.i.requestLayout();
        this.c.setOnTouchListener(new aq(this));
        this.c.getViewTreeObserver().addOnPreDrawListener(new ar(this));
    }

    private void a(SurfaceTexture surfaceTexture) {
        DebugUtil.debug(a, "prepare");
        if (!TextUtils.isEmpty(this.k)) {
            try {
                if (this.j != null) {
                    try {
                        this.j.release();
                        this.j = null;
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
                this.j = new MediaPlayer();
                this.j.setAudioStreamType(3);
                this.j.setDataSource(this.k);
                this.j.setSurface(new Surface(surfaceTexture));
                this.j.setLooping(false);
                this.j.prepareAsync();
                this.j.setOnPreparedListener(new as(this));
                this.j.setOnErrorListener(new at(this));
                this.j.setOnInfoListener(new au(this));
                this.j.setOnCompletionListener(new av(this));
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
    }

    public void seekTo(int i) {
        if (this.j != null) {
            this.j.seekTo(i);
        }
    }

    public void play(int i, int i2) {
        this.j.seekTo(i);
        this.j.start();
        Timer timer = new Timer();
        timer.schedule(new aw(this, timer, i2), 0, 10);
    }

    public void pause() {
        if (this.j != null) {
            this.j.pause();
            if (this.p != null) {
                this.p.onPause();
            }
        }
    }

    public void stop() {
        try {
            if (this.j != null) {
                this.j.setDisplay(null);
            }
            if (this.j != null && this.j.isPlaying()) {
                this.j.stop();
            }
            if (this.j != null) {
                this.j.release();
            }
            this.j = null;
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void setOnCompletionListener(OnCompletionListener onCompletionListener) {
        this.o = onCompletionListener;
    }

    public void setOnPauseListener(OnPauseListener onPauseListener) {
        this.p = onPauseListener;
    }

    public void setOnScrollStopListener(OnScrollStopListener onScrollStopListener) {
        this.q = onScrollStopListener;
    }

    public void setOnPrePareListener(OnPreparedListener onPreparedListener) {
        this.r = onPreparedListener;
    }
}
