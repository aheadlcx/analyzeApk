package com.budejie.www.activity.video;

import android.app.Activity;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.os.Handler;
import android.text.TextUtils;
import android.util.Log;
import android.view.GestureDetector;
import android.view.GestureDetector.OnGestureListener;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.SeekBar;
import android.widget.SeekBar.OnSeekBarChangeListener;
import android.widget.TextView;
import com.androidex.widget.asyncimage.AsyncImageView;
import com.budejie.www.R;
import com.budejie.www.activity.BudejieApplication;
import com.budejie.www.ad.AdManager;
import com.budejie.www.adapter.b.a;
import com.budejie.www.bean.ListItemObject;
import com.budejie.www.busevent.DetailAction;
import com.budejie.www.http.NetWorkUtil.RequstMethod;
import com.budejie.www.http.h;
import com.budejie.www.http.j;
import com.budejie.www.util.ac;
import com.budejie.www.util.ak;
import com.budejie.www.util.an;
import com.budejie.www.wallpaper.FloatWallPaperLayout;
import com.budejie.www.widget.LoadingView;
import com.sprite.ads.nati.NativeAdRef;
import com.sprite.ads.nati.view.NativeAdView;
import com.umeng.analytics.MobclickAgent;
import de.greenrobot.event.EventBus;
import java.util.Random;

public class f extends FrameLayout implements OnGestureListener {
    private b A;
    private f$e B;
    private TextView C;
    private TextView D;
    private RelativeLayout E;
    private ProgressBar F;
    private LoadingView G;
    private boolean H;
    private boolean I;
    private boolean J;
    private ListItemObject K;
    private ImageView L;
    private boolean M;
    private SharedPreferences N;
    private int O;
    private int P;
    private boolean Q;
    private boolean R;
    private boolean S;
    private ImageView T;
    private RelativeLayout U;
    private RelativeLayout V;
    private RelativeLayout W;
    public boolean a = false;
    private ImageView aa;
    private ImageView ab;
    private FloatWallPaperLayout ac;
    private int ad;
    private boolean ae;
    private Runnable af = new f$12(this);
    private OnClickListener ag = new f$5(this);
    private OnClickListener ah = new f$6(this);
    private OnClickListener ai = new f$7(this);
    private Handler aj = new f$8(this);
    private OnTouchListener ak = new f$11(this);
    private ViewGroup al;
    private OnSeekBarChangeListener am = new f$17(this);
    private d an;
    public ImageView b;
    private c c;
    private Activity d;
    private Activity e;
    private View f;
    private View g;
    private boolean h = false;
    private boolean i;
    private AsyncImageView j;
    private RelativeLayout k;
    private ImageView l;
    private ImageView m;
    private ImageView n;
    private ImageView o;
    private TextView p;
    private SeekBar q;
    private ProgressBar r;
    private RelativeLayout s;
    private boolean t;
    private int u = 500;
    private int v = 0;
    private int w = 0;
    private int x = 0;
    private String y;
    private GestureDetector z = null;

    public interface b {
        void b_();
    }

    public interface d {
        void a();

        void a(int i);

        void a(boolean z);

        void b();

        void c();
    }

    public interface c {
        void a();

        void a(int i);

        void a(boolean z);

        void b();

        boolean c();

        boolean d();

        boolean e();

        int getBufferPercentage();

        int getCurrentPosition();

        int getDuration();
    }

    public int getVideoType() {
        return this.ad;
    }

    public void setFromBarrageFullScreen(boolean z) {
        this.R = z;
    }

    public void setBarrageFullScreen(boolean z) {
        this.Q = z;
    }

    public void setComplete(boolean z) {
        this.J = z;
    }

    public void setDoubleClickCallback(b bVar) {
        this.A = bVar;
    }

    public void setVideoSpecialOPerateCallback(f$e f_e) {
        this.B = f_e;
    }

    public SeekBar getSeekBar() {
        return this.q;
    }

    public void onFinishInflate() {
        super.onFinishInflate();
        if (this.g != null) {
            a(this.g);
        }
    }

    public f(Activity activity, ListItemObject listItemObject, int i) {
        super(activity);
        this.d = activity;
        this.K = listItemObject;
        this.ad = i;
        A();
    }

    public void setVideoItem(ListItemObject listItemObject) {
        this.K = listItemObject;
        if (this.r != null) {
            this.r.setVisibility(8);
        }
    }

    public void setTopContext(Activity activity) {
        this.e = activity;
    }

    public void setFullScreen(boolean z) {
        this.I = z;
    }

    public void a(int i, int i2) {
        this.P = i2;
        this.O = i;
    }

    private void A() {
        this.N = this.d.getSharedPreferences("weiboprefer", 0);
        this.z = new GestureDetector(this);
        this.z.setOnDoubleTapListener(new f$1(this));
    }

    public void a() {
        this.aj.removeCallbacks(this.af);
        this.E.setVisibility(8);
        this.F.setVisibility(0);
    }

    public void b() {
        this.aj.removeCallbacks(this.af);
        this.E.setVisibility(0);
        this.F.setVisibility(8);
    }

    public void c() {
        this.aj.removeCallbacks(this.af);
        Animation loadAnimation;
        if (this.E.getVisibility() == 0) {
            if (this.c.c()) {
                this.E.clearAnimation();
                loadAnimation = AnimationUtils.loadAnimation(this.d, R.anim.option_leave_from_bottom);
                loadAnimation.setAnimationListener(new f$18(this));
                this.H = true;
                this.E.startAnimation(loadAnimation);
            }
        } else if (this.c.c()) {
            this.h = true;
            n();
            B();
            this.E.setVisibility(0);
            this.F.setVisibility(8);
            this.E.clearAnimation();
            loadAnimation = AnimationUtils.loadAnimation(this.d, R.anim.option_entry_from_bottom);
            loadAnimation.setAnimationListener(new f$19(this));
            this.E.startAnimation(loadAnimation);
            this.aj.postDelayed(this.af, 5000);
        }
    }

    private void B() {
        if (!this.aj.hasMessages(2)) {
            this.aj.sendEmptyMessage(2);
        }
    }

    private void C() {
        this.aj.removeMessages(2);
    }

    public void d() {
        try {
            if (!this.a) {
                this.a = true;
                a();
                w();
                if (this.c.c() || !this.c.d()) {
                    this.ab.setVisibility(8);
                } else {
                    this.ab.setVisibility(0);
                }
                this.W.setVisibility(0);
                this.U.setVisibility(8);
                this.V.setVisibility(8);
                this.ac.setSimpleMode(true);
                this.ac.setVisibility(4);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void e() {
        if (this.a) {
            this.a = false;
            if (this.ae) {
                this.ac.c();
            }
            q();
            this.W.setVisibility(8);
            if (!this.c.c() && this.c.d()) {
                b();
            }
        }
    }

    public void setMediaPlayer(c cVar) {
        this.c = cVar;
        if (this.ad == 3) {
            this.c.a(false);
        }
        n();
    }

    public void setAnchorView(View view) {
        this.f = view;
        LayoutParams layoutParams = new FrameLayout.LayoutParams(-1, -1);
        removeAllViews();
        addView(f(), layoutParams);
        addView(g(), layoutParams);
        if (!this.h) {
            this.aj.sendEmptyMessage(3);
            l();
            this.h = true;
        }
    }

    protected View f() {
        this.g = ((LayoutInflater) this.d.getSystemService("layout_inflater")).inflate(R.layout.micro_play_controller_new, null);
        a(this.g);
        return this.g;
    }

    protected View g() {
        View inflate = ((LayoutInflater) this.d.getSystemService("layout_inflater")).inflate(R.layout.micro_play_simple, null);
        this.W = (RelativeLayout) inflate.findViewById(R.id.play_simple_view);
        this.aa = (ImageView) inflate.findViewById(R.id.play_simple_view_close);
        this.ab = (ImageView) inflate.findViewById(R.id.play_simple_view_play);
        this.W.setOnClickListener(new f$20(this));
        this.aa.setOnClickListener(new f$21(this));
        this.ab.setOnClickListener(new f$22(this));
        return inflate;
    }

    public void h() {
        if (this.j != null && !this.j.isShown()) {
            this.j.setVisibility(0);
        }
    }

    private void a(View view) {
        long j;
        this.k = (RelativeLayout) view.findViewById(R.id.play_controller_top);
        if (this.Q) {
            this.k.setBackgroundColor(getResources().getColor(R.color.background_dark));
        }
        this.j = (AsyncImageView) view.findViewById(R.id.v_pic);
        if (this.K.getImgUrl() != null) {
            this.j.setImageListenerSpare(new f$23(this));
            this.j.setAsyncCacheImage(this.K.getImgUrl(), R.drawable.defaut_bg);
        }
        this.m = (ImageView) view.findViewById(R.id.video_play_btn);
        this.l = (ImageView) view.findViewById(R.id.play);
        this.n = (ImageView) view.findViewById(R.id.full_screen);
        if (this.I) {
            this.n.setImageDrawable(getResources().getDrawable(R.drawable.video_small_screen_selector));
        } else {
            this.n.setImageDrawable(getResources().getDrawable(R.drawable.video_full_screen_selector));
        }
        this.o = (ImageView) view.findViewById(R.id.barrage);
        this.M = this.N.getBoolean("barrage_status", true);
        setBarrageStatus(Boolean.valueOf(this.M));
        this.q = (SeekBar) view.findViewById(R.id.play_seekbar);
        this.q.setOnSeekBarChangeListener(this.am);
        this.q.setMax(1000);
        this.s = (RelativeLayout) view.findViewById(R.id.VipLoadingLayout);
        this.s.setVisibility(8);
        this.t = false;
        this.C = (TextView) view.findViewById(R.id.play_time_current);
        this.D = (TextView) view.findViewById(R.id.play_time_total);
        this.E = (RelativeLayout) view.findViewById(R.id.play_controller_bottom);
        this.F = (ProgressBar) view.findViewById(R.id.play_progressbar);
        this.F.setMax(1000);
        b(view);
        this.G = (LoadingView) view.findViewById(R.id.video_prepare_loading_view);
        if (this.ad == 0) {
            this.G.a();
        }
        this.L = (ImageView) view.findViewById(R.id.download);
        if (TextUtils.isEmpty(this.K.getVideotime())) {
            j = 0;
        } else {
            j = Long.parseLong(this.K.getVideotime()) * 1000;
        }
        if (this.ad == 4) {
            this.p = (TextView) view.findViewById(R.id.video_time_total);
            this.p.setVisibility(0);
            this.p.setText(ac.a(j));
            this.r = (ProgressBar) view.findViewById(R.id.progress_2);
            ((ProgressBar) view.findViewById(R.id.progress)).setVisibility(8);
        } else {
            this.r = (ProgressBar) view.findViewById(R.id.progress);
            ((ProgressBar) view.findViewById(R.id.progress_2)).setVisibility(8);
        }
        if (this.ad == 0) {
            this.r.setVisibility(8);
        }
        if (this.J) {
            this.r.setVisibility(8);
        }
        this.aj.sendEmptyMessageDelayed(4, 3000);
        this.D.setText(ac.a(j));
        this.l.setOnClickListener(this.ag);
        this.n.setOnClickListener(this.ah);
        this.o.setOnClickListener(this.ai);
        this.L.setOnClickListener(new f$24(this));
        this.U = (RelativeLayout) view.findViewById(R.id.play_controller_bottom_send_barrrage_1);
        this.V = (RelativeLayout) view.findViewById(R.id.play_controller_bottom_send_barrrage_2);
        this.b = (ImageView) view.findViewById(R.id.video_double_click_view);
        RelativeLayout.LayoutParams layoutParams;
        if (this.ad > 0) {
            this.L.setVisibility(8);
            this.o.setVisibility(8);
            this.n.setVisibility(8);
            layoutParams = (RelativeLayout.LayoutParams) this.D.getLayoutParams();
            layoutParams.rightMargin = 10;
            this.D.setLayoutParams(layoutParams);
            layoutParams = (RelativeLayout.LayoutParams) this.E.getLayoutParams();
            layoutParams.height = an.a(this.d, 65);
            this.E.setLayoutParams(layoutParams);
            this.U.setVisibility(8);
            this.V.setVisibility(8);
        } else if (this.I) {
            layoutParams = (RelativeLayout.LayoutParams) this.E.getLayoutParams();
            layoutParams.height = an.a(this.d, 65);
            this.E.setLayoutParams(layoutParams);
            this.U.setVisibility(8);
            this.V.setVisibility(8);
        }
        if (this.ad == 2) {
            view.findViewById(R.id.ad_mark).setVisibility(0);
            this.T = (ImageView) view.findViewById(R.id.loudspeaker_on_off);
            this.T.setVisibility(0);
            this.T.setSelected(true);
            if (this.c != null) {
                this.c.a(false);
            }
            this.T.setOnClickListener(new f$2(this));
            this.k.setDescendantFocusability(393216);
            this.k.setOnClickListener(new f$3(this));
        }
    }

    private void b(View view) {
        this.ac = (FloatWallPaperLayout) view.findViewById(R.id.wall_paper_layout);
        if (this.ad == 0 || this.ad == 5) {
            this.ae = com.budejie.www.wallpaper.b.b.a(getContext(), this.K);
            D();
            this.ac.getWallPaperLayout().setOnClickListener(new f$4(this));
        }
    }

    public void i() {
        if (this.T != null && !this.T.isSelected()) {
            this.T.performClick();
        }
    }

    private void D() {
        if (this.ac != null) {
            Object obj = (this.ad == 5 || ak.a(this.d, this.K)) ? 1 : null;
            RelativeLayout.LayoutParams layoutParams = (RelativeLayout.LayoutParams) this.ac.getLayoutParams();
            layoutParams.topMargin = (int) getResources().getDimension(obj != null ? R.dimen.wall_paper_layout_top_margin_with_title : R.dimen.wall_paper_layout_top_margin);
            this.ac.setLayoutParams(layoutParams);
        }
    }

    public void j() {
        if (this.I) {
            this.n.setImageDrawable(getResources().getDrawable(R.drawable.video_small_screen_selector));
            this.U.setVisibility(8);
            this.V.setVisibility(8);
            RelativeLayout.LayoutParams layoutParams = (RelativeLayout.LayoutParams) this.E.getLayoutParams();
            layoutParams.height = an.a(this.d, 65);
            this.E.setLayoutParams(layoutParams);
            a.g(this.j, this.K.getWidth(), this.K.getHeight());
            return;
        }
        this.n.setImageDrawable(getResources().getDrawable(R.drawable.video_full_screen_selector));
        q();
        layoutParams = (RelativeLayout.LayoutParams) this.E.getLayoutParams();
        layoutParams.height = an.a(this.d, 95);
        this.E.setLayoutParams(layoutParams);
        this.j.setLayoutParams(new RelativeLayout.LayoutParams(-1, -1));
        I();
    }

    public void setBarrageStatus(Boolean bool) {
        Log.d("setBarrageStatus", "setBarrageStatus openOrClose=" + bool);
        if (this.an != null && this.ad == 0) {
            this.an.a(this.M);
        }
        Log.d("setBarrageStatus", "setBarrageStatus isFullScreen=" + this.I);
        Log.d("setBarrageStatus", "setBarrageStatus isBarrageFullScreen=" + this.Q);
        if (this.I && !this.Q) {
            this.o.setVisibility(8);
        } else if (bool.booleanValue()) {
            this.o.setImageDrawable(getResources().getDrawable(R.drawable.video_barrage_open_selector));
        } else {
            this.o.setImageDrawable(getResources().getDrawable(R.drawable.video_barrage_close_selector));
        }
    }

    public void k() {
        this.ai.onClick(null);
    }

    public void l() {
        if (this.f != null) {
            F();
            E();
            LayoutParams layoutParams = new RelativeLayout.LayoutParams(-1, -1);
            ViewGroup viewGroup = (ViewGroup) getParent();
            if (viewGroup != null) {
                viewGroup.removeView(this);
            }
            ((RelativeLayout) this.f).addView(this, layoutParams);
            this.h = true;
        }
        n();
        this.aj.sendEmptyMessage(2);
    }

    private void E() {
    }

    public void m() {
        if (this.f != null && this.h) {
            try {
                this.aj.removeMessages(2);
            } catch (IllegalArgumentException e) {
            }
            this.h = false;
        }
    }

    protected void onConfigurationChanged(Configuration configuration) {
        if (this.ae) {
            boolean z = configuration.orientation == 2;
            this.ac.setLandscape(z);
            if (z) {
                this.ac.setVisibility(8);
            } else {
                this.ac.c();
            }
        }
    }

    private void F() {
        Log.e("MicroMediaController", "setProgress()");
        new f$f(this, null).execute(new Object[0]);
    }

    public boolean onTouchEvent(MotionEvent motionEvent) {
        this.z.onTouchEvent(motionEvent);
        return true;
    }

    public void n() {
        if (this.g != null && this.l != null) {
            if (this.c.c()) {
                this.l.setImageDrawable(getResources().getDrawable(R.drawable.video_pause_selector));
            } else if (this.c.d()) {
                this.l.setImageDrawable(getResources().getDrawable(R.drawable.video_play_selector));
            }
        }
    }

    public void o() {
        if (this.a) {
            if (this.an != null) {
                this.an.b();
            }
        } else if (this.ad == 3) {
            K();
            this.aj.removeMessages(2);
            a(true, false);
        } else if (this.ad == 5) {
            this.d.finish();
        } else if (this.ad == 6) {
            EventBus.getDefault().post(DetailAction.PUB_VIDEO_CLOSE);
        } else {
            K();
            n();
            this.aj.removeMessages(2);
            int duration = this.c.getDuration();
            if (this.q != null) {
                if (duration > 0) {
                    this.q.setMax(duration);
                    this.q.setProgress(0);
                }
                this.q.setSecondaryProgress(0);
            }
            if (this.F != null) {
                if (duration > 0) {
                    this.F.setMax(duration);
                    this.F.setProgress(0);
                }
                this.F.setSecondaryProgress(0);
            }
            a(Integer.valueOf(1));
            if (this.an != null) {
                a();
                this.an.b();
            }
        }
    }

    public void p() {
        try {
            this.D.setText(ac.a((long) this.c.getDuration()));
            this.aj.removeCallbacks(this.af);
            k.a(getContext()).u();
            this.c.a();
            if (this.an != null) {
                this.an.a();
            }
            if (!this.a) {
                q();
            }
            this.aj.sendEmptyMessageDelayed(0, 100);
            this.G.b();
            this.u = (this.c.getDuration() / 1000) / 60 > 10 ? 1000 : this.u;
            this.aj.sendEmptyMessage(8);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void q() {
        if (this.K != null && this.ad == 0 && !this.I) {
            if (((double) this.K.getHeight()) > ((double) this.K.getWidth()) * 1.5d) {
                if (this.V != null) {
                    this.V.setVisibility(4);
                }
                if (this.E != null) {
                    this.E.setBackgroundResource(R.drawable.transparent);
                }
                if (this.U != null) {
                    this.U.setVisibility(0);
                    this.U.setOnClickListener(new f$9(this));
                    return;
                }
                return;
            }
            if (this.U != null) {
                this.U.setVisibility(8);
            }
            if (this.E != null) {
                this.E.setBackgroundResource(R.drawable.play_video_bottom_bg);
            }
            if (this.V != null) {
                this.V.setVisibility(0);
                this.V.setOnClickListener(new f$10(this));
            }
        }
    }

    public void r() {
    }

    public String getWid() {
        return this.y;
    }

    public void setWid(String str) {
        this.y = str;
    }

    public synchronized void a(Integer num) {
        if (num.intValue() == 0) {
            MobclickAgent.onEvent(getContext(), "E06_A14", "视频开播次数");
        } else {
            MobclickAgent.onEvent(getContext(), "E06_A15", "视频播完次数");
        }
        String string = this.N.getString("id", null);
        Object wid = getWid();
        if (!TextUtils.isEmpty(wid)) {
            net.tsz.afinal.a.b bVar = new net.tsz.afinal.a.b();
            bVar.d("c", "video");
            bVar.d("a", "stat");
            bVar.d("pid", wid.toString());
            String str = "userid";
            if (string == null) {
                string = "0";
            }
            bVar.d(str, string);
            bVar.d("flag", num.toString());
            BudejieApplication.a.a(RequstMethod.GET, "http://api.budejie.com/api/api_open.php", bVar, new f$13(this));
        }
    }

    public void a(String str, String str2) {
        if (new Random().nextInt(33) == 0) {
            BudejieApplication.a.a(RequstMethod.POST, new h("http://log.spriteapp.com/stat").b().a("/").toString(), new j().a(getContext(), str, "", a.a(getContext()), str2 + "", "", "200", 0, ""), null);
        }
    }

    public void a(boolean z) {
        a(z, false);
    }

    public void s() {
        if (this.c != null && this.c.c()) {
            this.c.b();
        }
    }

    public boolean t() {
        if (this.c == null || !this.c.c()) {
            return false;
        }
        return true;
    }

    public void u() {
        if (this.c != null && !this.c.c() && this.c.d()) {
            J();
            this.c.a();
            if (this.ad != 6) {
                k.a(this.e).t();
            }
        }
    }

    public void a(boolean z, boolean z2) {
        try {
            if (this.c != null) {
                if (!this.c.c() || this.ad == 3) {
                    this.aj.sendEmptyMessage(2);
                    if (this.c.d()) {
                        this.m.setVisibility(8);
                        J();
                        k.a(getContext()).u();
                        this.c.a();
                        if (!(this.a || z)) {
                            this.aj.postDelayed(this.af, 5000);
                        }
                        if (this.I || this.Q) {
                            w();
                        } else if (k.a(getContext()).d() != null) {
                            w();
                        }
                    } else if (this.l.getVisibility() == 8) {
                        this.r.setVisibility(8);
                    } else if (this.ad > 0) {
                        this.r.setVisibility(0);
                    }
                } else {
                    if (this.a) {
                        this.ab.setVisibility(0);
                    } else {
                        if (this.H) {
                            this.H = false;
                            this.E.clearAnimation();
                            this.E.setVisibility(0);
                            this.F.setVisibility(8);
                            this.E.startAnimation(AnimationUtils.loadAnimation(this.d, R.anim.option_entry_from_bottom));
                        } else if (this.E.getVisibility() == 8) {
                            this.E.clearAnimation();
                            this.E.setVisibility(0);
                            this.F.setVisibility(8);
                            this.E.startAnimation(AnimationUtils.loadAnimation(this.d, R.anim.option_entry_from_bottom));
                        }
                        if (z2) {
                            G();
                        }
                    }
                    this.c.b();
                    this.aj.removeMessages(2);
                }
            }
            if (!this.ae || this.a) {
                this.ac.setVisibility(8);
            } else {
                this.ac.c();
            }
            n();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void v() {
        try {
            if (this.c != null) {
                if (this.c.c()) {
                    this.m.setVisibility(0);
                    if (this.E.getVisibility() == 0) {
                        this.E.clearAnimation();
                        Animation loadAnimation = AnimationUtils.loadAnimation(this.d, R.anim.option_leave_from_bottom);
                        loadAnimation.setAnimationListener(new f$14(this));
                        this.H = true;
                        this.E.startAnimation(loadAnimation);
                    }
                    this.r.setVisibility(8);
                    this.c.b();
                    this.aj.removeCallbacks(this.af);
                } else {
                    this.aj.sendEmptyMessage(2);
                    if (this.c.d()) {
                        this.m.setVisibility(8);
                        J();
                        k.a(getContext()).u();
                        this.c.a();
                        if (this.I) {
                            w();
                        } else {
                            w();
                        }
                    } else if (this.l.getVisibility() == 8) {
                        this.r.setVisibility(8);
                    } else if (this.ad > 0) {
                        this.r.setVisibility(0);
                    }
                }
            }
            n();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void G() {
        if (this.e != null) {
            H();
        }
    }

    private void H() {
        if (!AdManager.isAdClosed()) {
            NativeAdRef a = k.a(getContext()).a();
            if (a != null) {
                ViewGroup viewGroup = (ViewGroup) getParent();
                int i = (a.a * 4) / 5;
                if (viewGroup.getHeight() > ((i * 9) / 16) + com.budejie.www.e.b.a(this.d, 50.0f)) {
                    w();
                    this.al = (ViewGroup) View.inflate(this.d, R.layout.ad_video_pause_ad_layout, null);
                    LayoutParams layoutParams = new RelativeLayout.LayoutParams(i, (i * 9) / 16);
                    layoutParams.addRule(13);
                    viewGroup.addView(this.al, layoutParams);
                    ((NativeAdView) this.al.findViewById(R.id.ad_image)).loadAd(a, new f$15(this, (AsyncImageView) this.al.findViewById(R.id.image)));
                    this.al.findViewById(R.id.ad_close).setOnClickListener(new f$16(this));
                }
            }
        }
    }

    public void w() {
        if (!(this.al == null || this.al.getParent() == null)) {
            ((ViewGroup) this.al.getParent()).removeView(this.al);
        }
        this.al = null;
    }

    private void I() {
        if (this.al != null && this.al.isShown() && (a.a * this.K.getHeight()) / this.K.getWidth() < ((((a.a * 4) / 5) * 9) / 16) + com.budejie.www.e.b.a(this.d, 50.0f)) {
            w();
        }
    }

    public void x() {
        if (this.Q) {
            this.k.setBackgroundColor(getResources().getColor(R.color.transparent));
        }
        if (this.j != null) {
            this.j.setVisibility(4);
            this.S = true;
        }
        if (this.r != null) {
            this.r.setVisibility(4);
        }
        this.aj.sendEmptyMessage(2);
        n();
    }

    public void y() {
        if (this.r != null) {
            this.r.setVisibility(0);
        }
    }

    public void setEnabled(boolean z) {
        if (this.q != null) {
            this.q.setEnabled(true);
        }
        E();
        super.setEnabled(z);
    }

    private void J() {
        this.aj.sendEmptyMessageDelayed(0, 1000);
    }

    private void K() {
        this.aj.removeMessages(0);
    }

    public void setStartPlayAndPlayScheduleListener(d dVar) {
        this.an = dVar;
    }

    public boolean onDown(MotionEvent motionEvent) {
        return false;
    }

    public boolean onFling(MotionEvent motionEvent, MotionEvent motionEvent2, float f, float f2) {
        return false;
    }

    public void onLongPress(MotionEvent motionEvent) {
    }

    public boolean onScroll(MotionEvent motionEvent, MotionEvent motionEvent2, float f, float f2) {
        return false;
    }

    public void onShowPress(MotionEvent motionEvent) {
    }

    public boolean onSingleTapUp(MotionEvent motionEvent) {
        return false;
    }

    public void z() {
        if (this.ad == 2 && this.c != null) {
            this.c.a(false);
        }
    }
}
