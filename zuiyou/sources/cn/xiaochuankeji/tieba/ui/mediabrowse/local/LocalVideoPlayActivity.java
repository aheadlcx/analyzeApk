package cn.xiaochuankeji.tieba.ui.mediabrowse.local;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.view.PointerIconCompat;
import android.text.TextUtils;
import android.view.SurfaceHolder;
import android.view.SurfaceHolder.Callback;
import android.view.SurfaceView;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.Animation.AnimationListener;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.MediaController.MediaPlayerControl;
import android.widget.ProgressBar;
import cn.xiaochuankeji.tieba.R;
import cn.xiaochuankeji.tieba.background.ad.AdvertismentBean;
import cn.xiaochuankeji.tieba.background.ad.AdvertismentSoftBean;
import cn.xiaochuankeji.tieba.common.d.a;
import cn.xiaochuankeji.tieba.ui.base.h;
import com.zhihu.matisse.internal.ui.widget.AspectRatioFrameLayout2;
import tv.danmaku.ijk.media.player.IMediaPlayer;
import tv.danmaku.ijk.media.player.IMediaPlayer$OnCompletionListener;
import tv.danmaku.ijk.media.player.IMediaPlayer$OnErrorListener;
import tv.danmaku.ijk.media.player.IMediaPlayer$OnInfoListener;
import tv.danmaku.ijk.media.player.IMediaPlayer$OnPreparedListener;
import tv.danmaku.ijk.media.player.IMediaPlayer$OnVideoSizeChangedListener;

public class LocalVideoPlayActivity extends h implements Callback {
    private boolean d = false;
    private FrameLayout e;
    private AspectRatioFrameLayout2 f;
    private SurfaceView g;
    private SurfaceHolder h;
    private View i;
    private ProgressBar j;
    private ImageView k;
    private LinearLayout l;
    private a m;
    private MediaPlayerControl n;
    private int o;
    private int p;
    private int q = 0;
    private String r;
    private AdvertismentBean s;
    private AdvertismentSoftBean t;
    private ImageView u;
    private AnimationDrawable v;
    private ImageView w;
    private Handler x = new Handler(this) {
        final /* synthetic */ LocalVideoPlayActivity a;

        {
            this.a = r1;
        }

        public void handleMessage(Message message) {
            switch (message.what) {
                case 1:
                    this.a.x();
                    return;
                case 2:
                    this.a.v();
                    return;
                default:
                    return;
            }
        }
    };

    public static void a(Context context, String str, AdvertismentBean advertismentBean) {
        Intent intent = new Intent(context, LocalVideoPlayActivity.class);
        intent.putExtra("video_uri", str);
        intent.putExtra("ad_bean", advertismentBean);
        context.startActivity(intent);
    }

    public static void a(Context context, String str, AdvertismentSoftBean advertismentSoftBean) {
        Intent intent = new Intent(context, LocalVideoPlayActivity.class);
        intent.putExtra("video_uri", str);
        intent.putExtra("ad_soft_bean", advertismentSoftBean);
        context.startActivity(intent);
    }

    protected int a() {
        return R.layout.layout_activity_local_video;
    }

    protected void i_() {
        this.e = (FrameLayout) findViewById(R.id.rootView);
        this.w = (ImageView) findViewById(R.id.iv_close);
        this.f = (AspectRatioFrameLayout2) findViewById(R.id.video_frame);
        this.g = (SurfaceView) findViewById(R.id.video_surface_view);
        this.h = this.g.getHolder();
        this.h.addCallback(this);
        this.i = findViewById(R.id.video_play_error);
        this.j = (ProgressBar) findViewById(R.id.video_progressbar);
        this.k = (ImageView) findViewById(R.id.btn_play);
        this.l = (LinearLayout) findViewById(R.id.llLoadingContainer);
        this.u = (ImageView) findViewById(R.id.ivLoading);
        this.v = (AnimationDrawable) this.u.getDrawable();
        this.k.setSelected(false);
        j();
        this.e.setOnClickListener(new OnClickListener(this) {
            final /* synthetic */ LocalVideoPlayActivity a;

            {
                this.a = r1;
            }

            public void onClick(View view) {
                this.a.k.setVisibility(0);
                this.a.j();
            }
        });
        this.k.setOnClickListener(new OnClickListener(this) {
            final /* synthetic */ LocalVideoPlayActivity a;

            {
                this.a = r1;
            }

            public void onClick(View view) {
                boolean z = false;
                if (this.a.n.isPlaying()) {
                    if (this.a.m != null) {
                        this.a.m.pause();
                        if (this.a.s != null) {
                            cn.xiaochuankeji.tieba.ui.post.a.a.a(this.a.s, (int) PointerIconCompat.TYPE_ALIAS);
                        }
                        if (this.a.t != null) {
                            cn.xiaochuankeji.tieba.ui.post.a.a.a(this.a.t, (int) PointerIconCompat.TYPE_ALIAS);
                        }
                        this.a.k.setVisibility(0);
                    }
                } else if (this.a.m != null) {
                    this.a.m.start();
                    if (this.a.s != null) {
                        cn.xiaochuankeji.tieba.ui.post.a.a.a(this.a.s, 1011);
                    }
                    if (this.a.t != null) {
                        cn.xiaochuankeji.tieba.ui.post.a.a.a(this.a.t, (int) PointerIconCompat.TYPE_ALIAS);
                    }
                    this.a.j();
                }
                ImageView c = this.a.k;
                if (!this.a.k.isSelected()) {
                    z = true;
                }
                c.setSelected(z);
            }
        });
        this.w.setOnClickListener(new OnClickListener(this) {
            final /* synthetic */ LocalVideoPlayActivity a;

            {
                this.a = r1;
            }

            public void onClick(View view) {
                this.a.v();
                this.a.finish();
            }
        });
    }

    protected boolean a(Bundle bundle) {
        super.a(bundle);
        this.r = getIntent().getStringExtra("video_uri");
        this.s = (AdvertismentBean) getIntent().getSerializableExtra("ad_bean");
        this.t = (AdvertismentSoftBean) getIntent().getSerializableExtra("ad_soft_bean");
        if (TextUtils.isEmpty(this.r)) {
            return false;
        }
        return true;
    }

    public void onResume() {
        super.onResume();
        if (this.d) {
            k();
        }
    }

    public void onPause() {
        super.onPause();
        if (this.n != null && this.q == 0) {
            this.q = this.n.getCurrentPosition();
        }
        v();
        this.x.removeCallbacksAndMessages(null);
    }

    public void surfaceCreated(SurfaceHolder surfaceHolder) {
        if (this.h != null) {
            this.h.removeCallback(this);
        }
        this.h = surfaceHolder;
        this.d = true;
        this.h.addCallback(this);
        k();
    }

    public void surfaceChanged(SurfaceHolder surfaceHolder, int i, int i2, int i3) {
    }

    public void surfaceDestroyed(SurfaceHolder surfaceHolder) {
        this.d = false;
        this.h = null;
        v();
    }

    private void j() {
        Animation alphaAnimation = new AlphaAnimation(1.0f, 0.0f);
        alphaAnimation.setDuration(3000);
        alphaAnimation.setAnimationListener(new AnimationListener(this) {
            final /* synthetic */ LocalVideoPlayActivity a;

            {
                this.a = r1;
            }

            public void onAnimationStart(Animation animation) {
            }

            public void onAnimationEnd(Animation animation) {
                this.a.k.setVisibility(8);
            }

            public void onAnimationRepeat(Animation animation) {
            }
        });
        this.k.startAnimation(alphaAnimation);
    }

    private void k() {
        if (this.d) {
            v();
            w();
            this.j.setProgress(0);
            x();
        }
    }

    private void v() {
        this.o = 0;
        this.p = 0;
        if (this.m != null) {
            this.m.g();
            this.m = null;
            this.n = null;
        }
    }

    private void w() {
        if (this.m == null) {
            this.m = new a(this);
            this.m.a(false);
            this.m.a(new IMediaPlayer$OnPreparedListener(this) {
                final /* synthetic */ LocalVideoPlayActivity a;

                {
                    this.a = r1;
                }

                public void onPrepared(IMediaPlayer iMediaPlayer) {
                    this.a.q = 0;
                }
            });
            this.m.a(new IMediaPlayer$OnCompletionListener(this) {
                final /* synthetic */ LocalVideoPlayActivity a;

                {
                    this.a = r1;
                }

                public void onCompletion(IMediaPlayer iMediaPlayer) {
                    if (this.a.m != null) {
                        this.a.m.seekTo(0);
                        this.a.m.start();
                    }
                    if (this.a.s != null) {
                        cn.xiaochuankeji.tieba.background.a.d().b(this.a.s.videoPlayFinishUrl);
                        cn.xiaochuankeji.tieba.ui.post.a.a.a(this.a.s, (int) PointerIconCompat.TYPE_ALL_SCROLL);
                    }
                    if (this.a.t != null) {
                        cn.xiaochuankeji.tieba.ui.post.a.a.a(this.a.t, (int) PointerIconCompat.TYPE_ALIAS);
                    }
                }
            });
            this.m.a(new IMediaPlayer$OnInfoListener(this) {
                final /* synthetic */ LocalVideoPlayActivity a;

                {
                    this.a = r1;
                }

                public boolean onInfo(IMediaPlayer iMediaPlayer, int i, int i2) {
                    switch (i) {
                        case 701:
                            if (iMediaPlayer.isPlaying()) {
                                this.a.c(true);
                                break;
                            }
                            break;
                        case IMediaPlayer.MEDIA_INFO_BUFFERING_END /*702*/:
                            if (iMediaPlayer.isPlaying()) {
                                this.a.c(false);
                                break;
                            }
                            break;
                    }
                    return true;
                }
            });
            this.i.setVisibility(4);
            this.m.a(new IMediaPlayer$OnErrorListener(this) {
                final /* synthetic */ LocalVideoPlayActivity a;

                {
                    this.a = r1;
                }

                public boolean onError(IMediaPlayer iMediaPlayer, int i, int i2) {
                    this.a.i.setVisibility(0);
                    if (this.a.s != null) {
                        cn.xiaochuankeji.tieba.background.a.d().b(this.a.s.videoPlayFailUrl);
                    }
                    return this.a.a(iMediaPlayer);
                }
            });
            this.m.a(new IMediaPlayer$OnVideoSizeChangedListener(this) {
                final /* synthetic */ LocalVideoPlayActivity a;

                {
                    this.a = r1;
                }

                public void onVideoSizeChanged(IMediaPlayer iMediaPlayer, int i, int i2, int i3, int i4) {
                    float f = 1.0f;
                    if (this.a.o == 0 || this.a.p == 0) {
                        this.a.p = i2;
                        this.a.o = i;
                        AspectRatioFrameLayout2 l = this.a.f;
                        if (i2 != 0) {
                            f = (1.0f * ((float) i)) / ((float) i2);
                        }
                        l.setAspectRatio(f);
                    }
                }
            });
            this.m.a(this.r);
        }
        this.n = this.m.a();
        if (this.q > 0) {
            this.m.seekTo(this.q);
        }
        this.m.a(this.h);
        this.m.start();
    }

    private void x() {
        if (!(this.n == null || this.j == null)) {
            int currentPosition = this.n.getCurrentPosition();
            this.j.setMax(this.n.getDuration());
            this.j.setProgress(currentPosition);
        }
        this.x.sendEmptyMessageDelayed(1, 1000);
    }

    private boolean a(IMediaPlayer iMediaPlayer) {
        if (this.q == 0) {
            this.q = (int) iMediaPlayer.getCurrentPosition();
        }
        this.x.sendEmptyMessage(2);
        this.i.setVisibility(0);
        return true;
    }

    private void c(boolean z) {
        if (this.l != null && this.v != null) {
            if (z) {
                this.l.setVisibility(0);
                this.v.start();
                return;
            }
            this.v.stop();
            this.l.setVisibility(8);
        }
    }
}
