package com.budejie.www.widget.curtain;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.os.Handler;
import android.os.Message;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.RelativeLayout.LayoutParams;
import android.widget.TextView;
import android.widget.Toast;
import com.androidex.widget.asyncimage.AsyncImageView;
import com.budejie.www.R;
import com.budejie.www.activity.BudejieApplication;
import com.budejie.www.ad.AdConfig;
import com.budejie.www.f.d;
import com.budejie.www.util.an;
import com.budejie.www.util.p;
import com.sprite.ads.internal.bean.data.AdItem;
import com.sprite.ads.internal.bean.data.MediaAdItem;
import com.sprite.ads.internal.bean.data.ThirdSdkItem;
import com.sprite.ads.internal.exception.ErrorCode;
import com.sprite.ads.media.ADStatusChangeListner;
import com.sprite.ads.media.MediaAd;
import com.sprite.ads.media.MediaAdListener;
import com.sprite.ads.media.MediaListener;
import com.sprite.ads.media.NativeMediaAdView;
import com.umeng.analytics.MobclickAgent;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.Random;

public class VideoADView extends RelativeLayout implements OnClickListener {
    private static final String b = VideoADView.class.getSimpleName();
    private boolean A;
    private boolean B;
    private int C;
    private int D;
    private int E;
    private Runnable F;
    private Handler G;
    private int H;
    private Runnable I;
    private a J;
    final String a;
    private View c;
    private Button d;
    private Button e;
    private MediaAdItem f;
    private View g;
    private NativeMediaAdView h;
    private final Handler i;
    private TextView j;
    private TextView k;
    private TextView l;
    private AsyncImageView m;
    private AsyncImageView n;
    private TextView o;
    private TextView p;
    private View q;
    private boolean r;
    private boolean s;
    private SharedPreferences t;
    private AlertDialog u;
    private DialogInterface.OnClickListener v;
    private long w;
    private long x;
    private long y;
    private boolean z;

    public interface a {
        void a();
    }

    public VideoADView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.i = new Handler();
        this.a = new SimpleDateFormat("yyyy-MM-dd", Locale.US).format(new Date());
        this.C = 3;
        this.D = 8;
        this.E = 5;
        this.I = new Runnable(this) {
            final /* synthetic */ VideoADView a;

            {
                this.a = r1;
            }

            public void run() {
                if (this.a.f != null) {
                    this.a.w = (long) this.a.h.getCurrentPosition();
                    long v = this.a.w;
                    if (this.a.x == v && this.a.h.isPlaying()) {
                        Log.i(VideoADView.b, "Loading...");
                        this.a.j.setTextColor(-1);
                        this.a.j.setText("Loading...");
                    } else {
                        Log.i(VideoADView.b, String.format("%s秒", new Object[]{Math.round(((double) (this.a.y - v)) / 1000.0d) + ""}));
                        this.a.j.setText(String.format("%s秒", new Object[]{Math.round(((double) (this.a.y - v)) / 1000.0d) + ""}));
                        if (!this.a.z && this.a.w >= 3000) {
                            this.a.z = true;
                            Animation a = a.a(this.a.c);
                            this.a.c.setAnimation(a);
                            a.start();
                            this.a.l.setText("<<");
                        }
                    }
                    this.a.x = v;
                    if (this.a.h.isPlaying()) {
                        this.a.i.postDelayed(this.a.I, 500);
                    }
                }
            }
        };
        this.t = context.getSharedPreferences("ad_config", 0);
        this.r = this.t.getBoolean(b, true);
        this.s = this.a.equals(this.t.getString("close_ad", null));
        if (!this.s) {
            String string = this.t.getString("last_play_time", null);
            if (string == null) {
                this.t.edit().putString("last_play_time", this.a).commit();
            } else if (!this.a.equals(string)) {
                this.t.edit().remove(string).putString("last_play_time", this.a).commit();
            }
            this.H = this.t.getInt(this.a, 0);
            this.G = new Handler(this) {
                final /* synthetic */ VideoADView a;

                {
                    this.a = r1;
                }

                public void handleMessage(Message message) {
                    try {
                        this.a.F.run();
                    } catch (InternalError e) {
                        MobclickAgent.onEvent(BudejieApplication.b().getApplicationContext(), "E07_A02", "InternalError VideoADView handleMessage()");
                    }
                }
            };
            d();
            e();
        }
    }

    public VideoADView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public VideoADView(Context context) {
        this(context, null);
    }

    private void d() {
        inflate(getContext(), R.layout.video_ad_layout, this);
        this.k = (TextView) findViewById(R.id.loading);
        this.g = findViewById(R.id.custom_container);
        this.h = (NativeMediaAdView) findViewById(R.id.mediaview);
        findViewById(R.id.close_btn).setOnClickListener(this);
        this.j = (TextView) findViewById(R.id.text_count_down);
        this.l = (TextView) findViewById(R.id.handle);
        this.m = (AsyncImageView) findViewById(R.id.img_logo);
        this.o = (TextView) findViewById(R.id.app_name);
        this.c = findViewById(R.id.ad_info_container);
        this.d = (Button) findViewById(R.id.btn_download);
        this.q = findViewById(R.id.screen_download_layout);
        this.n = (AsyncImageView) findViewById(R.id.img_logo_two);
        this.p = (TextView) findViewById(R.id.app_name_two);
        this.e = (Button) findViewById(R.id.app_download_btn);
        this.l.setOnClickListener(this);
        this.c.setOnClickListener(this);
        this.d.setOnClickListener(this);
        this.e.setOnClickListener(this);
    }

    private void e() {
        new MediaAd(this.h).loadAd(AdConfig.invideo, new MediaAdListener(this) {
            final /* synthetic */ VideoADView a;

            {
                this.a = r1;
            }

            public void loadSuccess(AdItem adItem) {
                if (adItem instanceof ThirdSdkItem) {
                    this.a.C = ((ThirdSdkItem) adItem).movie_interval;
                    this.a.D = ((ThirdSdkItem) adItem).movie_rate;
                    this.a.E = ((ThirdSdkItem) adItem).movie_limitofday;
                }
            }

            public void loadFailure(ErrorCode errorCode) {
            }
        });
        this.h.setMediaListener(new MediaListener(this) {
            final /* synthetic */ VideoADView a;

            {
                this.a = r1;
            }

            public void onVideoReady() {
                Log.i(VideoADView.b, "onVideoReady");
                this.a.B = true;
                if (this.a.A) {
                    this.a.h.play();
                }
            }

            public void onVideoStart() {
                Log.i(VideoADView.b, "onVideoStart");
                this.a.k.setVisibility(8);
                this.a.l.setVisibility(0);
                this.a.y = this.a.h.getDuration();
                this.a.i.post(this.a.I);
                this.a.j.setVisibility(0);
                this.a.H = this.a.H + 1;
                this.a.t.edit().putInt(this.a.a, this.a.H).commit();
            }

            public void onVideoComplete() {
                Animation b;
                Log.i(VideoADView.b, "onVideoComplete");
                this.a.h();
                if (((LayoutParams) this.a.c.getLayoutParams()).leftMargin == 0) {
                    b = a.b(this.a.c);
                    this.a.c.setAnimation(b);
                    b.start();
                    this.a.l.setText(">>");
                }
                this.a.q.setVisibility(0);
                b = new AlphaAnimation(0.0f, 1.0f);
                b.setDuration(600);
                b.setAnimationListener(new d(this) {
                    final /* synthetic */ AnonymousClass3 a;

                    {
                        this.a = r1;
                    }

                    public void onAnimationEnd(Animation animation) {
                        Log.i(VideoADView.b, "gone2");
                        this.a.a.g.setVisibility(4);
                    }
                });
                this.a.q.setAnimation(b);
                b.start();
                this.a.G.sendEmptyMessageDelayed(1, (long) (this.a.C * 1000));
            }

            public void onVideoStop() {
                Log.i(VideoADView.b, "onVideoStop");
            }

            public void onVideoError() {
                Log.i(VideoADView.b, "onVideoError");
                this.a.h();
                this.a.setVisibility(8);
                this.a.J.a();
            }

            public void onADClicked() {
                Log.i(VideoADView.b, "onVideoADClicked");
            }

            public void onVideoReplay() {
                Log.i(VideoADView.b, "onVideoReplay");
            }
        });
        this.h.setADStatusChangeListner(new ADStatusChangeListner(this) {
            final /* synthetic */ VideoADView a;

            {
                this.a = r1;
            }

            public void onADStatusChanged(MediaAdItem mediaAdItem) {
                if (this.a.f == mediaAdItem) {
                    this.a.g();
                }
            }
        });
        this.F = new Runnable(this) {
            final /* synthetic */ VideoADView a;

            {
                this.a = r1;
            }

            public void run() {
                Animation alphaAnimation = new AlphaAnimation(1.0f, 0.0f);
                alphaAnimation.setDuration(600);
                alphaAnimation.setAnimationListener(new d(this) {
                    final /* synthetic */ AnonymousClass5 a;

                    {
                        this.a = r1;
                    }

                    public void onAnimationEnd(Animation animation) {
                        Log.i(VideoADView.b, "gone2----");
                        this.a.a.setVisibility(8);
                        this.a.a.J.a();
                    }
                });
                this.a.q.clearAnimation();
                this.a.q.setAnimation(alphaAnimation);
                alphaAnimation.start();
            }
        };
    }

    private void f() {
        this.z = false;
        this.B = false;
        this.A = false;
        this.k.setVisibility(0);
        this.l.setVisibility(4);
        this.g.setVisibility(4);
        Animation alphaAnimation = new AlphaAnimation(0.0f, 1.0f);
        alphaAnimation.setDuration(600);
        alphaAnimation.setAnimationListener(new d(this) {
            final /* synthetic */ VideoADView a;

            {
                this.a = r1;
            }

            public void onAnimationEnd(Animation animation) {
                if (((LayoutParams) this.a.c.getLayoutParams()).leftMargin != this.a.c.getWidth()) {
                    Animation a = a.a(this.a.c, 0);
                    this.a.c.setAnimation(a);
                    a.start();
                    this.a.l.setText(">>");
                }
                this.a.g.setVisibility(0);
            }
        });
        this.k.clearAnimation();
        this.k.setAnimation(alphaAnimation);
        alphaAnimation.start();
        this.g.postDelayed(new Runnable(this) {
            final /* synthetic */ VideoADView a;

            {
                this.a = r1;
            }

            public void run() {
                this.a.A = true;
                if (this.a.B) {
                    this.a.h.play();
                }
            }
        }, 4600);
        this.m.setPostImage(this.f.getIconUri());
        this.n.setPostImage(this.f.getIconUri());
        this.j.setVisibility(8);
        this.o.setText(this.f.getTitle());
        this.p.setText(this.f.getTitle());
        g();
        this.h.onExposured(this);
        this.q.setVisibility(8);
    }

    private void g() {
        if (this.f.isAPP()) {
            switch (this.f.getAPPStatus()) {
                case 0:
                    this.d.setText("下载");
                    this.e.setText("马上下载");
                    return;
                case 1:
                    this.d.setText("启动");
                    this.e.setText("马上启动");
                    return;
                case 2:
                    this.d.setText("更新");
                    this.e.setText("马上更新");
                    return;
                case 4:
                    this.d.setText(this.h.getProgress() + "%");
                    this.e.setText(this.h.getProgress() + "%");
                    return;
                case 8:
                    this.d.setText("安装");
                    this.e.setText("马上安装");
                    return;
                case 16:
                    this.d.setText("下载失败，重新下载");
                    this.e.setText("重新下载");
                    return;
                default:
                    this.d.setText("打开");
                    this.e.setText("马上打开");
                    return;
            }
        }
        this.d.setText("打开");
        this.e.setText("马上打开");
    }

    private void h() {
        if (this.i != null && this.I != null) {
            this.i.removeCallbacks(this.I);
        }
    }

    protected void a() {
        if (this.f != null) {
            this.h.stop();
            this.h.release();
            this.G.removeMessages(1);
        }
    }

    public void b() {
        if (!this.s && "1".equals(an.b(getContext()))) {
            if (this.H != 0) {
                if (new Random().nextInt(10) < this.D) {
                }
            }
            if (this.H < this.E) {
                a();
                this.f = this.h.refreshAd();
                if (this.f != null) {
                    f();
                    setVisibility(0);
                    return;
                }
                setVisibility(8);
                this.J.a();
                return;
            }
        }
        setVisibility(8);
        this.J.a();
    }

    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.close_btn:
                if (this.r) {
                    this.v = new DialogInterface.OnClickListener(this) {
                        final /* synthetic */ VideoADView a;

                        {
                            this.a = r1;
                        }

                        public void onClick(DialogInterface dialogInterface, int i) {
                            switch (i) {
                                case -2:
                                    this.a.u.dismiss();
                                    return;
                                case -1:
                                    this.a.u.dismiss();
                                    this.a.t.edit().putBoolean(VideoADView.b, false).commit();
                                    this.a.r = false;
                                    this.a.a();
                                    this.a.setVisibility(8);
                                    this.a.J.a();
                                    this.a.s = this.a.t.edit().putString("close_ad", this.a.a).commit();
                                    Toast.makeText(this.a.getContext(), "不看广告，姐也爱你", 0).show();
                                    return;
                                default:
                                    return;
                            }
                        }
                    };
                    this.u = p.a((Activity) getContext(), "确定关闭广告？", "姐也不容易...请高抬贵手", "确定", "取消", this.v);
                    return;
                }
                a();
                setVisibility(8);
                this.J.a();
                this.s = this.t.edit().putString("close_ad", this.a).commit();
                Toast.makeText(getContext(), "不看广告，姐也爱你", 0).show();
                return;
            case R.id.handle:
                Animation b;
                if (((LayoutParams) this.c.getLayoutParams()).leftMargin == 0) {
                    b = a.b(this.c);
                    this.c.setAnimation(b);
                    b.start();
                    this.l.setText(">>");
                    return;
                }
                b = a.a(this.c);
                this.c.setAnimation(b);
                b.start();
                this.l.setText("<<");
                return;
            case R.id.ad_info_container:
            case R.id.btn_download:
            case R.id.app_download_btn:
                this.h.onClicked(view);
                return;
            default:
                return;
        }
    }

    public void setVisibleListener(a aVar) {
        this.J = aVar;
    }
}
