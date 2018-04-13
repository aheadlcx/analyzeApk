package cn.xiaochuankeji.tieba.ui.mediabrowse.component;

import android.annotation.TargetApi;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.graphics.drawable.AnimationDrawable;
import android.os.Build.VERSION;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import android.view.GestureDetector;
import android.view.GestureDetector.SimpleOnGestureListener;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceHolder.Callback;
import android.view.SurfaceView;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnLongClickListener;
import android.view.View.OnTouchListener;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.MediaController.MediaPlayerControl;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.SeekBar;
import android.widget.SeekBar.OnSeekBarChangeListener;
import android.widget.TextView;
import cn.htjyb.netlib.NetworkMonitor;
import cn.xiaochuankeji.tieba.R;
import cn.xiaochuankeji.tieba.background.AppController;
import cn.xiaochuankeji.tieba.background.danmaku.DanmakuItem;
import cn.xiaochuankeji.tieba.background.data.Comment;
import cn.xiaochuankeji.tieba.background.data.ServerVideo;
import cn.xiaochuankeji.tieba.background.net.XCError;
import cn.xiaochuankeji.tieba.background.picture.PictureImpl;
import cn.xiaochuankeji.tieba.background.picture.PictureImpl.Type;
import cn.xiaochuankeji.tieba.background.utils.e.c;
import cn.xiaochuankeji.tieba.background.utils.e.d;
import cn.xiaochuankeji.tieba.ui.danmaku.HotDanmakuView;
import cn.xiaochuankeji.tieba.ui.danmaku.TopDanmakuView;
import cn.xiaochuankeji.tieba.ui.danmaku.e;
import cn.xiaochuankeji.tieba.ui.mediabrowse.MediaBrowseActivity;
import cn.xiaochuankeji.tieba.ui.mediabrowse.MediaBrowseWhenSelectActivity;
import cn.xiaochuankeji.tieba.ui.mediabrowse.h5video.H5VideoWebViewContainer;
import cn.xiaochuankeji.tieba.ui.widget.AspectRatioFrameLayout;
import cn.xiaochuankeji.tieba.ui.widget.image.WebImageView;
import cn.xiaochuankeji.tieba.webview.WebActivity;
import com.alibaba.sdk.android.oss.common.RequestParameters;
import com.danikula.videocache.b;
import com.danikula.videocache.f;
import com.danikula.videocache.q;
import com.tencent.open.SocialConstants;
import java.io.File;
import java.util.ArrayList;
import master.flame.danmaku.danmaku.model.l;
import org.greenrobot.eventbus.ThreadMode;
import org.json.JSONObject;
import tv.danmaku.ijk.media.player.IMediaPlayer;
import tv.danmaku.ijk.media.player.IMediaPlayer$OnCompletionListener;
import tv.danmaku.ijk.media.player.IMediaPlayer$OnErrorListener;
import tv.danmaku.ijk.media.player.IMediaPlayer$OnInfoListener;
import tv.danmaku.ijk.media.player.IMediaPlayer$OnPreparedListener;
import tv.danmaku.ijk.media.player.IMediaPlayer$OnSeekCompleteListener;
import tv.danmaku.ijk.media.player.IMediaPlayer$OnVideoSizeChangedListener;

public class g extends c implements Callback, OnClickListener, b {
    private ViewGroup A;
    private FrameLayout B;
    private View C;
    private H5VideoWebViewContainer D;
    private cn.xiaochuankeji.tieba.common.d.a E;
    private MediaPlayerControl F;
    private cn.xiaochuankeji.tieba.common.b.a G;
    private int H;
    private boolean I;
    private boolean J = true;
    private boolean K = false;
    private boolean L = false;
    private boolean M = false;
    private boolean N;
    private Animation O;
    private Animation P;
    private Animation Q;
    private Animation R;
    private int S;
    private long T;
    private long U;
    private long V;
    private int W;
    private long X;
    private long Y;
    private c Z = new c();
    private WebImageView a;
    private ServerVideo aa;
    private String ab;
    private final Handler ac = new a();
    private e ad;
    private boolean ae;
    private boolean af;
    private boolean ag = false;
    private LinearLayout ah;
    private ImageView ai;
    private AnimationDrawable aj;
    private boolean ak = false;
    private j al;
    private e am;
    private int an;
    private int ao;
    private h ap;
    private f aq;
    private int ar = 0;
    private String as = "";
    private int at = 0;
    private int au = 0;
    private boolean av = false;
    private OnTouchListener aw = new OnTouchListener(this) {
        final /* synthetic */ g a;
        private GestureDetector b = new GestureDetector(this.a.getActivity(), new SimpleOnGestureListener(this) {
            final /* synthetic */ AnonymousClass11 a;

            {
                this.a = r1;
            }

            public boolean onDoubleTap(MotionEvent motionEvent) {
                if (this.a.a.af) {
                    this.a.a.V();
                } else {
                    this.a.a.getActivity().finish();
                }
                return true;
            }

            public boolean onSingleTapConfirmed(MotionEvent motionEvent) {
                this.a.a.ac.postDelayed(this.a.a.ax, 50);
                return true;
            }
        });

        {
            this.a = r4;
        }

        public boolean onTouch(View view, MotionEvent motionEvent) {
            if (motionEvent.getAction() == 0) {
                if ((this.a.getResources().getConfiguration().orientation == 1) || this.a.al == null || !this.a.al.a() || !this.a.l()) {
                    this.a.av = false;
                } else {
                    this.a.av = true;
                }
            }
            if (this.a.av && this.a.am != null && this.a.am.a(motionEvent)) {
                return true;
            }
            return this.b.onTouchEvent(motionEvent);
        }
    };
    private Runnable ax = new Runnable(this) {
        final /* synthetic */ g a;

        {
            this.a = r1;
        }

        public void run() {
            this.a.b(!this.a.N, true, true);
        }
    };
    private boolean ay = true;
    private Runnable az = new Runnable(this) {
        final /* synthetic */ g a;

        {
            this.a = r1;
        }

        public void run() {
            this.a.b(false, false, true);
        }
    };
    private AspectRatioFrameLayout b;
    private SurfaceView c;
    private SurfaceHolder d;
    private View e;
    private HotDanmakuView f;
    private TopDanmakuView g;
    private LinearLayout h;
    private int i;
    private int j;
    private long k;
    private cn.htjyb.b.a l;
    private cn.htjyb.b.a m;
    private View n;
    private RelativeLayout o;
    private RelativeLayout p;
    private TextView q;
    private TextView r;
    private SeekBar s;
    private View t;
    private View u;
    private ImageView v;
    private ImageView w;
    private ProgressBar x;
    private RelativeLayout y;
    private RelativeLayout z;

    private class a extends Handler {
        final /* synthetic */ g a;

        private a(g gVar) {
            this.a = gVar;
        }

        public void handleMessage(Message message) {
            switch (message.what) {
                case 1:
                    this.a.B();
                    return;
                case 2:
                    this.a.C();
                    return;
                case 3:
                    this.a.x();
                    return;
                case 4:
                    this.a.E();
                    return;
                case 5:
                    this.a.S();
                    return;
                default:
                    return;
            }
        }
    }

    public static g a(int i, boolean z, long j, cn.htjyb.b.a aVar, cn.htjyb.b.a aVar2) {
        Bundle b = c.b(i, z, j, aVar, aVar2);
        g gVar = new g();
        gVar.setArguments(b);
        return gVar;
    }

    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        Bundle arguments = getArguments();
        this.j = arguments.getInt(RequestParameters.POSITION);
        this.k = arguments.getLong("pid");
        this.l = PictureImpl.buildPictureBy(arguments.getBundle(SocialConstants.PARAM_AVATAR_URI));
        arguments = arguments.getBundle("thumbpic");
        if (arguments != null) {
            this.m = PictureImpl.buildPictureBy(arguments);
        }
        if (this.aq == null) {
            try {
                this.aq = q.a().b();
            } catch (Exception e) {
            }
        }
        this.am = new e(new cn.xiaochuankeji.tieba.ui.mediabrowse.component.e.a(this) {
            final /* synthetic */ g a;

            {
                this.a = r1;
            }

            public void a(float f, boolean z) {
                int c = cn.xiaochuankeji.tieba.ui.utils.e.c();
                if (this.a.F != null) {
                    int duration = this.a.F.getDuration();
                    c = (int) (((f / (((float) c) * 1.0f)) * ((float) duration)) + ((float) this.a.an));
                    if (c < 0) {
                        c = 0;
                    }
                    if (c <= duration) {
                        duration = c;
                    }
                    this.a.ao = duration;
                    this.a.al.a(duration, z);
                }
            }

            public void a() {
                if (this.a.F != null) {
                    this.a.an = this.a.F.getCurrentPosition();
                    this.a.c(true);
                }
            }

            public void b() {
                this.a.c(false);
                if (this.a.isAdded() && !this.a.getActivity().isFinishing()) {
                    this.a.k();
                }
            }
        });
        this.ap = new h();
        aa();
    }

    private void k() {
        if (this.E == null) {
            return;
        }
        if (this.ao < this.E.getCurrentPosition()) {
            if (this.F != null && this.F.canSeekBackward()) {
                this.E.seekTo(this.ao);
            }
        } else if (this.ao > this.E.getCurrentPosition() && this.F != null && this.F.canSeekForward()) {
            this.E.seekTo(this.ao);
        }
    }

    private boolean l() {
        if (this.C.getVisibility() == 0 || this.ah.getVisibility() == 0) {
            return false;
        }
        return true;
    }

    private void c(boolean z) {
        if (z) {
            if (this.al.getParent() != null) {
                ((ViewGroup) this.al.getParent()).removeView(this.al);
            }
            LayoutParams layoutParams = new RelativeLayout.LayoutParams(-2, -2);
            layoutParams.addRule(13, -1);
            this.A.addView(this.al, layoutParams);
            return;
        }
        this.A.removeView(this.al);
    }

    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        View inflate = layoutInflater.inflate(R.layout.fragment_video_browse, viewGroup, false);
        a(inflate);
        return inflate;
    }

    public void onViewCreated(View view, Bundle bundle) {
        super.onViewCreated(view, bundle);
        this.A.setOnClickListener(null);
        this.A.setOnTouchListener(this.aw);
        if (getActivity() != null) {
            if (getActivity() instanceof MediaBrowseWhenSelectActivity) {
                this.p.setVisibility(4);
            }
            if ((getActivity() instanceof MediaBrowseActivity) && ((MediaBrowseActivity) getActivity()).j()) {
                this.p.setVisibility(4);
            }
        }
        if (getActivity() != null && (getActivity() instanceof MediaBrowseActivity) && ((MediaBrowseActivity) getActivity()).k() != null) {
            this.ak = ((MediaBrowseActivity) getActivity()).k().equals("assess");
        }
    }

    private void a(View view) {
        this.al = new j(getActivity());
        this.A = (ViewGroup) view.findViewById(R.id.rootView);
        this.n = view.findViewById(R.id.ivPlay);
        this.O = AnimationUtils.loadAnimation(getActivity(), R.anim.bottom_in);
        this.P = AnimationUtils.loadAnimation(getActivity(), R.anim.bottom_out);
        this.Q = AnimationUtils.loadAnimation(getActivity(), R.anim.alpha_in);
        this.R = AnimationUtils.loadAnimation(getActivity(), R.anim.alpha_out);
        this.a = (WebImageView) view.findViewById(R.id.pvThumbImg);
        this.b = (AspectRatioFrameLayout) view.findViewById(R.id.video_frame);
        this.c = (SurfaceView) view.findViewById(R.id.video_surface_view);
        this.d = this.c.getHolder();
        this.d.addCallback(this);
        this.e = view.findViewById(R.id.shutter);
        this.n.setSelected(true);
        this.f = (HotDanmakuView) view.findViewById(R.id.hot_danmaku);
        this.g = (TopDanmakuView) view.findViewById(R.id.top_danmaku);
        t();
        this.B = (FrameLayout) view.findViewById(R.id.layout_like_animation);
        this.C = view.findViewById(R.id.btn_retry);
        this.C.setOnClickListener(new OnClickListener(this) {
            final /* synthetic */ g a;

            {
                this.a = r1;
            }

            public void onClick(View view) {
                this.a.C.setVisibility(4);
                this.a.x();
            }
        });
        this.ah = (LinearLayout) view.findViewById(R.id.llLoadingContainer);
        this.ai = (ImageView) view.findViewById(R.id.ivLoading);
        this.aj = (AnimationDrawable) this.ai.getDrawable();
        this.D = (H5VideoWebViewContainer) view.findViewById(R.id.h5video_webview_container);
        p();
        this.M = true;
        m();
        this.h = (LinearLayout) view.findViewById(R.id.video_more_guide);
    }

    private void m() {
        if (isAdded() && !isDetached()) {
            View view;
            LayoutInflater from = LayoutInflater.from(getActivity());
            if (this.y == null) {
                this.y = (RelativeLayout) from.inflate(R.layout.video_tool_land, null);
            }
            if (this.z == null) {
                this.z = (RelativeLayout) from.inflate(R.layout.video_tool_portrait, null);
            }
            if (getResources().getConfiguration().orientation == 2) {
                view = this.y;
            } else {
                view = this.z;
            }
            if (this.A.indexOfChild(this.y) > 0) {
                this.A.removeViewInLayout(this.y);
            }
            if (this.A.indexOfChild(this.z) > 0) {
                this.A.removeViewInLayout(this.z);
            }
            this.A.addView(view);
            this.o = (RelativeLayout) view.findViewById(R.id.rlBottomBar);
            this.p = (RelativeLayout) view.findViewById(R.id.danmu_container);
            this.q = (TextView) view.findViewById(R.id.tvCurrentTime);
            this.r = (TextView) view.findViewById(R.id.tvTotalTime);
            this.s = (SeekBar) view.findViewById(R.id.seekBar);
            this.s.setEnabled(true);
            this.v = (ImageView) view.findViewById(R.id.btn_fullscreen_switch);
            this.w = (ImageView) view.findViewById(R.id.iv_danmu_switch);
            this.u = view.findViewById(R.id.text_danmu);
            this.t = view.findViewById(R.id.btn_small_control);
            this.t.setSelected(true);
            this.x = (ProgressBar) view.findViewById(R.id.video_progressbar);
            this.w.setSelected(cn.xiaochuankeji.tieba.background.a.a().getBoolean("key_danmuku_switch_state", true));
            if (MediaBrowseWhenSelectActivity.class.isInstance(getActivity())) {
                this.v.setVisibility(4);
            }
            if (VERSION.SDK_INT <= 13) {
                this.v.setVisibility(4);
            }
            this.w.setOnLongClickListener(new OnLongClickListener(this) {
                final /* synthetic */ g a;

                {
                    this.a = r1;
                }

                public boolean onLongClick(View view) {
                    String str = "弹幕列表";
                    WebActivity.a(this.a.getContext(), cn.xiaochuan.jsbridge.b.a(str, cn.xiaochuankeji.tieba.background.utils.d.a.d("https://$$/help/danmaku/") + "" + this.a.l.getPictureID()));
                    return false;
                }
            });
            n();
        }
    }

    private void n() {
        this.n.setOnClickListener(this);
        this.t.setOnClickListener(this);
        this.u.setOnClickListener(this);
        this.v.setOnClickListener(this);
        this.w.setOnClickListener(this);
        this.o.setOnClickListener(this);
        this.p.setOnClickListener(this);
    }

    public void onResume() {
        super.onResume();
        if (this.L) {
            b();
        }
    }

    public void onPause() {
        super.onPause();
        if (this.F != null && this.i == 0) {
            this.i = this.F.getCurrentPosition();
        }
        if (this.E != null) {
            float duration = ((this.F == null ? 0.0f : (float) this.F.getDuration()) * ((float) this.ar)) / 100.0f;
            this.ap.a(this.as, 1, duration, null);
            this.ap.a(this.as, 1, H(), (long) this.E.getCurrentPosition(), duration, null);
        }
        this.ap.a(3);
        E();
        I();
        this.ac.removeCallbacksAndMessages(null);
    }

    public void onConfigurationChanged(Configuration configuration) {
        super.onConfigurationChanged(configuration);
        if (this.E != null) {
            this.i = this.E.getCurrentPosition();
        }
        m();
        if (getResources().getConfiguration().orientation == 1) {
            e(false);
            a(true);
            b(true, true, true);
        } else {
            e(true);
            a(false);
            b(true, true, true);
        }
        v();
        if (this.E != null && this.E.isPlaying()) {
            this.t.setSelected(false);
            this.s.setEnabled(true);
            A();
        }
        if ((getActivity() instanceof MediaBrowseActivity) && ((MediaBrowseActivity) getActivity()).j()) {
            this.p.setVisibility(4);
        }
    }

    public void setUserVisibleHint(boolean z) {
        super.setUserVisibleHint(z);
        this.L = z;
        if (!this.M) {
            return;
        }
        if (this.L) {
            this.S++;
            b();
            u();
            return;
        }
        a();
    }

    public void a() {
        this.au = 0;
        this.at = 0;
        this.K = false;
        this.a.setVisibility(0);
        this.e.setVisibility(0);
        b(false, false, false);
        if (getResources().getConfiguration().orientation == 2) {
            this.x.setVisibility(4);
        }
        if (this.m != null) {
            this.a.setImageURI(this.m.downloadUrl());
        }
        E();
        I();
        this.ac.removeCallbacksAndMessages(null);
    }

    public void b() {
        this.K = true;
        this.a.setVisibility(8);
        b(true, false, true);
        x();
    }

    public void a(boolean z, int i) {
        if (z && i == 0 && this.M) {
            o();
        }
    }

    private boolean o() {
        if (!r() && (getActivity() instanceof MediaBrowseActivity)) {
            int A = ((MediaBrowseActivity) getActivity()).A();
            int bufferPercentage = this.F != null ? this.F.getBufferPercentage() : 0;
            if (A == 0 && !AppController.instance().allowCellular() && bufferPercentage < 100) {
                cn.xiaochuankeji.tieba.ui.widget.f.a("提示", "您正在使用移动网络，播放将产生流量费用。", getActivity(), new cn.xiaochuankeji.tieba.ui.widget.f.a(this) {
                    final /* synthetic */ g a;

                    {
                        this.a = r1;
                    }

                    public void a(boolean z) {
                        if (z) {
                            AppController.instance().setAllowCellular(true);
                            if (this.a.F == null) {
                                this.a.x();
                            } else {
                                this.a.X();
                            }
                        }
                    }
                }).setConfirmTip("继续观看");
                Y();
                return true;
            }
        }
        return false;
    }

    private void p() {
        if (getActivity() instanceof MediaBrowseActivity) {
            MediaBrowseActivity mediaBrowseActivity = (MediaBrowseActivity) getActivity();
            if (this.l != null) {
                this.aa = mediaBrowseActivity.a(this.l.getPictureID());
            }
        }
        this.D.setCallback(new cn.xiaochuankeji.tieba.ui.mediabrowse.h5video.H5VideoWebViewContainer.a(this) {
            final /* synthetic */ g a;

            {
                this.a = r1;
            }

            public void a(String str) {
                if (this.a.isAdded()) {
                    this.a.D.setVisibility(4);
                    this.a.ab = str;
                    com.izuiyou.a.a.b.b("h5 url:" + this.a.ab);
                    this.a.b(true, false, true);
                    if (this.a.getActivity() instanceof MediaBrowseActivity) {
                        cn.xiaochuankeji.tieba.background.utils.e.b aVar = new cn.xiaochuankeji.tieba.background.utils.e.a();
                        this.a.a(aVar);
                        aVar.a = this.a.G();
                        aVar.b = this.a.ab;
                        if (aVar.a.equals("review")) {
                            aVar.c = this.a.k;
                            aVar.d = this.a.J();
                        }
                        d.a().a(aVar);
                    }
                    this.a.x();
                }
            }

            public void a(int i, String str) {
                this.a.Z.b = 4;
                this.a.Z.d = str;
                this.a.ap.a(this.a.as, 2, this.a.H(), 0, 0.0f, str);
                this.a.O();
            }

            public void a() {
                if (this.a.aa == null || !TextUtils.isEmpty(this.a.aa.getUrl())) {
                    this.a.Z.k = 1;
                    this.a.D.setVisibility(4);
                    this.a.b(true, false, true);
                    this.a.a(false, true, false);
                } else {
                    this.a.Z.k = 2;
                    cn.xiaochuankeji.tieba.background.utils.g.a("感谢你的反馈");
                }
                this.a.O();
            }
        });
        if (getActivity() instanceof MediaBrowseActivity) {
            Comment w = ((MediaBrowseActivity) getActivity()).w();
            long j = w != null ? w._id : 0;
            if (j > 0 || this.k > 0) {
                this.ap.a(this.k, j, this.l.getPictureID(), j > 0 ? "review" : "post");
            }
        }
    }

    private String q() {
        String cachePath;
        if (this.aa != null) {
            switch (this.aa.priority) {
                case 1:
                    cachePath = new PictureImpl(this.aa.extUrl, (Type) this.l.getType(), this.l.getPictureID()).cachePath();
                    break;
                case 2:
                    cachePath = null;
                    break;
                case 3:
                    cachePath = new PictureImpl(this.aa.srcUrl, (Type) this.l.getType(), this.l.getPictureID()).cachePath();
                    break;
                case 4:
                    cachePath = this.l.cachePath();
                    break;
                default:
                    cachePath = null;
                    break;
            }
        }
        cachePath = this.l.cachePath();
        if (!TextUtils.isEmpty(cachePath) && new File(cachePath).getParentFile() == null) {
            return null;
        }
        com.izuiyou.a.a.b.b("video cache path:" + cachePath);
        return cachePath;
    }

    private boolean r() {
        Object q = q();
        return !TextUtils.isEmpty(q) && new File(q).exists();
    }

    private String s() {
        String str = null;
        if (this.aa != null) {
            switch (this.aa.priority) {
                case 1:
                    str = this.aa.extUrl;
                    break;
                case 2:
                    str = this.ab;
                    break;
                case 3:
                    str = this.aa.srcUrl;
                    break;
                case 4:
                    str = this.aa.downloadUrl;
                    break;
            }
        }
        return TextUtils.isEmpty(str) ? this.l.downloadUrl() : str;
    }

    private void t() {
        this.f.setOnDanmakuClickListener(new master.flame.danmaku.a.f.a(this) {
            final /* synthetic */ g a;

            {
                this.a = r1;
            }

            public boolean a(l lVar) {
                master.flame.danmaku.danmaku.model.d d = lVar.d();
                if (d instanceof cn.xiaochuankeji.tieba.ui.danmaku.b) {
                    this.a.g.a(((cn.xiaochuankeji.tieba.ui.danmaku.b) d).a);
                }
                this.a.ac.removeCallbacks(this.a.ax);
                return true;
            }

            public boolean b(l lVar) {
                return true;
            }

            public boolean a(master.flame.danmaku.a.f fVar) {
                return true;
            }
        });
        this.f.setOnDanmakuSwipeListener(new HotDanmakuView.b(this) {
            final /* synthetic */ g a;

            {
                this.a = r1;
            }

            public void a(master.flame.danmaku.danmaku.model.d dVar, float f, float f2) {
                this.a.a(dVar, f, f2);
                this.a.ac.removeCallbacks(this.a.ax);
            }

            public void b(master.flame.danmaku.danmaku.model.d dVar, float f, float f2) {
                this.a.b(dVar, f, f2);
                this.a.ac.removeCallbacks(this.a.ax);
            }
        });
        this.f.setOnTouchListener(this.aw);
        this.g.setSoundDanmakuListener(new TopDanmakuView.c(this) {
            final /* synthetic */ g a;

            {
                this.a = r1;
            }

            public void a(TopDanmakuView topDanmakuView, cn.xiaochuankeji.tieba.background.danmaku.e eVar) {
                if (this.a.E != null) {
                    this.a.E.a(1.0f);
                }
            }
        });
        if (this.l == null) {
            this.ad = new e(this.k, 0, false);
        } else {
            this.ad = new e(this.k, this.l.getPictureID(), false);
        }
        this.ad.a(new cn.xiaochuankeji.tieba.ui.danmaku.e.a(this) {
            final /* synthetic */ g a;

            {
                this.a = r1;
            }

            public void a(ArrayList<DanmakuItem> arrayList) {
                this.a.f.a((ArrayList) arrayList);
            }

            public void b(ArrayList<DanmakuItem> arrayList) {
                this.a.g.a((ArrayList) arrayList);
            }
        });
        if (this.k <= 0 || this.l == null || this.l.getPictureID() <= 0) {
            b(false);
        } else {
            u();
        }
    }

    private void u() {
        if (cn.xiaochuankeji.tieba.background.a.a().getBoolean("key_danmuku_switch_state", true)) {
            b(true);
        } else {
            b(false);
        }
    }

    private void v() {
        FrameLayout.LayoutParams layoutParams = (FrameLayout.LayoutParams) this.f.getLayoutParams();
        RelativeLayout.LayoutParams layoutParams2 = (RelativeLayout.LayoutParams) this.g.getLayoutParams();
        Resources resources = getResources();
        if (this.af) {
            layoutParams.topMargin = resources.getDimensionPixelSize(R.dimen.hot_danmaku_margin_top_in_fullscreen);
            layoutParams.bottomMargin = 0;
            layoutParams2.leftMargin = cn.xiaochuankeji.tieba.ui.utils.e.c() / 4;
            layoutParams2.rightMargin = layoutParams2.leftMargin;
            layoutParams2.bottomMargin = cn.xiaochuankeji.tieba.ui.utils.e.a(30.0f);
            this.v.setImageResource(R.drawable.video_fullscreen_exit);
        } else {
            layoutParams.topMargin = resources.getDimensionPixelSize(R.dimen.hot_danmaku_margin_top);
            layoutParams.bottomMargin = resources.getDimensionPixelSize(R.dimen.hot_danmaku_margin_bottom);
            layoutParams2.leftMargin = resources.getDimensionPixelSize(R.dimen.top_danmaku_margin_left);
            layoutParams2.rightMargin = resources.getDimensionPixelSize(R.dimen.top_danmaku_margin_right);
            layoutParams2.bottomMargin = resources.getDimensionPixelSize(R.dimen.top_danmaku_margin_bottom);
            this.v.setImageResource(R.drawable.video_fullscreen_enter);
        }
        this.B.removeAllViews();
    }

    public void b(boolean z) {
        if (z) {
            this.ad.a(true);
            this.f.o();
            this.g.setEnabled(true);
        } else {
            this.ad.a(false);
            this.f.p();
            this.g.setEnabled(false);
        }
        this.ae = z;
        Z();
    }

    private void a(master.flame.danmaku.danmaku.model.d dVar, float f, float f2) {
        if (dVar instanceof cn.xiaochuankeji.tieba.ui.danmaku.b) {
            final DanmakuItem danmakuItem = ((cn.xiaochuankeji.tieba.ui.danmaku.b) dVar).a;
            if (danmakuItem != null && danmakuItem.liked == 0) {
                final master.flame.danmaku.danmaku.model.d dVar2 = dVar;
                final float f3 = f;
                final float f4 = f2;
                new cn.xiaochuankeji.tieba.background.danmaku.d(danmakuItem.id, null, new cn.xiaochuankeji.tieba.background.net.a.b<JSONObject>(this) {
                    final /* synthetic */ g e;

                    public /* synthetic */ void onResponse(Object obj, Object obj2) {
                        a((JSONObject) obj, obj2);
                    }

                    public void a(JSONObject jSONObject, Object obj) {
                        if (this.e.isAdded()) {
                            danmakuItem.liked = jSONObject.optInt("liked");
                            if (danmakuItem.liked == 1) {
                                this.e.a(true, dVar2, f3, f4);
                                DanmakuItem danmakuItem = danmakuItem;
                                danmakuItem.likes++;
                            }
                        }
                    }
                }, new cn.xiaochuankeji.tieba.background.net.a.a(this) {
                    final /* synthetic */ g b;

                    public void onErrorResponse(XCError xCError, Object obj) {
                        if (this.b.isAdded()) {
                            cn.xiaochuankeji.tieba.background.utils.g.a(xCError.getMessage());
                            danmakuItem.liked = 0;
                        }
                    }
                }).execute();
            }
        }
    }

    private void a(boolean z, master.flame.danmaku.danmaku.model.d dVar, float f, float f2) {
        int[] iArr = new int[2];
        this.f.getLocationInWindow(iArr);
        int[] iArr2 = new int[2];
        this.B.getLocationInWindow(iArr2);
        final View imageView = new ImageView(getActivity());
        imageView.setImageResource(z ? R.drawable.danmaku_like_up : R.drawable.danmaku_like_down);
        int a = cn.htjyb.c.a.a(27.0f, getActivity());
        int a2 = cn.htjyb.c.a.a(32.0f, getActivity());
        LayoutParams layoutParams = new FrameLayout.LayoutParams(a, a2);
        layoutParams.leftMargin = (int) (((((float) iArr[0]) + f) - ((float) iArr2[0])) - ((float) (a / 2)));
        a = (int) (dVar.l() + ((dVar.n() - dVar.l()) / 2.0f));
        if (z) {
            layoutParams.topMargin = ((a + iArr[1]) - iArr2[1]) - a2;
        } else {
            layoutParams.topMargin = (a + iArr[1]) - iArr2[1];
        }
        this.B.addView(imageView, layoutParams);
        this.B.postDelayed(new Runnable(this) {
            final /* synthetic */ g b;

            public void run() {
                this.b.B.removeView(imageView);
            }
        }, 1000);
    }

    private void b(master.flame.danmaku.danmaku.model.d dVar, float f, float f2) {
        if (dVar instanceof cn.xiaochuankeji.tieba.ui.danmaku.b) {
            final DanmakuItem danmakuItem = ((cn.xiaochuankeji.tieba.ui.danmaku.b) dVar).a;
            if (danmakuItem != null && danmakuItem.liked == 0) {
                final master.flame.danmaku.danmaku.model.d dVar2 = dVar;
                final float f3 = f;
                final float f4 = f2;
                new cn.xiaochuankeji.tieba.background.danmaku.c(danmakuItem.id, null, new cn.xiaochuankeji.tieba.background.net.a.b<JSONObject>(this) {
                    final /* synthetic */ g e;

                    public /* synthetic */ void onResponse(Object obj, Object obj2) {
                        a((JSONObject) obj, obj2);
                    }

                    public void a(JSONObject jSONObject, Object obj) {
                        if (this.e.isAdded()) {
                            danmakuItem.liked = jSONObject.optInt("liked");
                            if (danmakuItem.liked == -1) {
                                this.e.a(false, dVar2, f3, f4);
                                DanmakuItem danmakuItem = danmakuItem;
                                danmakuItem.likes--;
                            }
                        }
                    }
                }, new cn.xiaochuankeji.tieba.background.net.a.a(this) {
                    final /* synthetic */ g b;

                    public void onErrorResponse(XCError xCError, Object obj) {
                        if (this.b.isAdded()) {
                            cn.xiaochuankeji.tieba.background.utils.g.a(xCError.getMessage());
                            danmakuItem.liked = 0;
                        }
                    }
                }).execute();
            }
        }
    }

    private void w() {
        if (this.aa != null) {
            if (this.aa.priority == 1 && TextUtils.isEmpty(this.aa.extUrl)) {
                this.aa.priority = 2;
            }
            if (this.aa.priority == 2 && TextUtils.isEmpty(this.aa.h5Video.url)) {
                this.aa.priority = 3;
            }
            if (this.aa.priority == 3 && TextUtils.isEmpty(this.aa.srcUrl)) {
                this.aa.priority = 4;
            }
            if (this.aa.priority == 4 && TextUtils.isEmpty(this.aa.getUrl())) {
                this.aa = null;
            }
        }
    }

    private void x() {
        if (this.d != null && this.l != null && this.K && this.L) {
            f(true);
            if (!o()) {
                w();
                if (this.aa != null && this.aa.priority == 2 && TextUtils.isEmpty(this.ab)) {
                    N();
                    this.D.setVisibility(0);
                    if (TextUtils.isEmpty(this.aa.getUrl())) {
                        this.D.a();
                    }
                    this.D.b();
                    this.D.a(this.aa.h5Video.url, this.aa.h5Video.topCoverHeight, this.aa.h5Video.videoHeight);
                    b(false, false, false);
                    return;
                }
                y();
            }
        }
    }

    private void y() {
        U();
        R();
        if (this.F != null) {
            z();
            B();
            C();
            this.n.setSelected(false);
            this.t.setSelected(false);
        }
    }

    private void z() {
        this.n.setSelected(false);
        this.t.setSelected(false);
        this.q.setText("00:00");
        this.r.setText("00:00");
        this.s.setProgress(0);
        this.s.setSecondaryProgress(0);
        A();
        this.x.setProgress(0);
        this.x.setSecondaryProgress(0);
        this.w.setSelected(cn.xiaochuankeji.tieba.background.a.a().getBoolean("key_danmuku_switch_state", false));
    }

    private void A() {
        if (this.s != null) {
            this.s.setOnSeekBarChangeListener(new OnSeekBarChangeListener(this) {
                final /* synthetic */ g a;

                {
                    this.a = r1;
                }

                public void onProgressChanged(SeekBar seekBar, int i, boolean z) {
                    if (z) {
                        this.a.H = i;
                        this.a.q.setText(cn.xiaochuankeji.tieba.ui.utils.d.a((long) i));
                        this.a.Z();
                    }
                }

                public void onStartTrackingTouch(SeekBar seekBar) {
                    this.a.I = true;
                }

                public void onStopTrackingTouch(SeekBar seekBar) {
                    this.a.I = false;
                    if (this.a.H < this.a.E.getCurrentPosition()) {
                        if (this.a.F != null && this.a.F.canSeekBackward()) {
                            this.a.E.seekTo(this.a.H);
                        }
                    } else if (this.a.H > this.a.E.getCurrentPosition() && this.a.F != null && this.a.F.canSeekForward()) {
                        this.a.E.seekTo(this.a.H);
                    }
                }
            });
        }
    }

    private void B() {
        if (this.F != null && this.ae) {
            this.g.b(this.F.getCurrentPosition());
        }
        this.ac.sendEmptyMessageDelayed(1, 100);
    }

    private void C() {
        if (this.F != null) {
            int currentPosition = this.F.getCurrentPosition();
            int duration = this.F.getDuration();
            this.r.setText(cn.xiaochuankeji.tieba.ui.utils.d.a((long) duration));
            this.s.setMax(duration);
            this.x.setMax(duration);
            if (!this.I) {
                this.q.setText(cn.xiaochuankeji.tieba.ui.utils.d.a((long) currentPosition));
                this.s.setProgress(currentPosition);
                this.x.setProgress(currentPosition);
            }
            if (this.J) {
                int bufferPercentage = (this.F.getBufferPercentage() * duration) / 100;
                if (bufferPercentage > this.s.getSecondaryProgress()) {
                    this.s.setSecondaryProgress(bufferPercentage);
                }
            } else {
                this.s.setSecondaryProgress(duration);
            }
            this.x.setSecondaryProgress(this.s.getSecondaryProgress());
            if (this.ae) {
                this.ad.a(currentPosition);
            }
            if (!this.al.a()) {
                this.al.setDuration((long) duration);
            }
            this.h.setVisibility(8);
            this.ac.sendEmptyMessageDelayed(2, 1000);
            if ((getActivity() instanceof MediaBrowseActivity) && duration > 0 && Math.abs(duration - currentPosition) <= 3000) {
                MediaBrowseActivity mediaBrowseActivity = (MediaBrowseActivity) getActivity();
                if (mediaBrowseActivity.C() && mediaBrowseActivity.D() && getResources().getConfiguration().orientation == 1 && !mediaBrowseActivity.E()) {
                    this.h.setVisibility(0);
                }
            }
        }
    }

    private void D() {
        this.f.d();
        this.f.q();
        this.f.b();
        this.g.a(false);
    }

    private void E() {
        Q();
        this.e.setVisibility(0);
        U();
        this.s.setOnSeekBarChangeListener(null);
        this.n.setSelected(true);
        this.t.setSelected(true);
        D();
    }

    private String F() {
        int i;
        long pictureID = this.l.getPictureID();
        if (this.aa != null) {
            i = this.aa.priority;
        } else {
            i = 0;
        }
        return String.format("%d-%d-%d", new Object[]{Integer.valueOf(this.S), Long.valueOf(pictureID), Integer.valueOf(i)});
    }

    private void a(cn.xiaochuankeji.tieba.background.utils.e.b bVar) {
        bVar.e = this.l.getPictureID();
        bVar.f = ((MediaBrowseActivity) getActivity()).z();
        bVar.g = ((MediaBrowseActivity) getActivity()).k();
        bVar.h = ((MediaBrowseActivity) getActivity()).x();
    }

    private String G() {
        MediaBrowseActivity mediaBrowseActivity = (MediaBrowseActivity) getActivity();
        String v = mediaBrowseActivity.v();
        try {
            if (!mediaBrowseActivity.D() || this.aa == null || this.aa.videoId <= 0 || !mediaBrowseActivity.f(this.aa.videoId)) {
                return v;
            }
            return "r_video";
        } catch (Exception e) {
            return v;
        }
    }

    private long H() {
        if (0 == this.X) {
            this.X = System.nanoTime();
        }
        if (0 == this.Y) {
            this.Y = System.nanoTime();
        }
        return (this.Y - this.X) / 1000000;
    }

    private void I() {
        if (this.k > 0 && (getActivity() instanceof MediaBrowseActivity)) {
            cn.xiaochuankeji.tieba.background.utils.e.e eVar = new cn.xiaochuankeji.tieba.background.utils.e.e();
            a((cn.xiaochuankeji.tieba.background.utils.e.b) eVar);
            eVar.a = G();
            eVar.b = this.U / 1000000;
            eVar.c = this.T;
            eVar.i = H();
            eVar.d = this.W;
            eVar.j = M();
            if (eVar.a.equals("review")) {
                eVar.k = this.k;
                eVar.l = J();
            }
            eVar.p = K();
            d.a().a(F(), eVar);
        }
    }

    private long J() {
        if (getActivity() == null || !(getActivity() instanceof MediaBrowseActivity)) {
            return 0;
        }
        return ((MediaBrowseActivity) getActivity()).y();
    }

    private int K() {
        if (getActivity() == null || !(getActivity() instanceof MediaBrowseActivity)) {
            return 0;
        }
        Comment w = ((MediaBrowseActivity) getActivity()).w();
        return w == null ? 0 : w._status;
    }

    private void L() {
        if (this.k > 0 && (getActivity() instanceof MediaBrowseActivity)) {
            c cVar = new c();
            a((cn.xiaochuankeji.tieba.background.utils.e.b) cVar);
            cVar.a = G();
            cVar.i = this.as;
            cVar.b = 1;
            cVar.d = "普通异常";
            cVar.c = 0;
            cVar.n = K();
            cVar.j = M();
            if (cVar.a.equals("review")) {
                cVar.l = this.k;
                cVar.m = J();
            }
            cVar.n = K();
            d.a().a(F(), cVar);
        }
    }

    private String M() {
        String str = "local";
        if (this.aa == null) {
            return str;
        }
        switch (this.aa.priority) {
            case 1:
                return "ext";
            case 2:
                return "h5";
            case 3:
                return "local";
            case 4:
                return "final_local";
            default:
                return str;
        }
    }

    private void N() {
        this.Z.b = 0;
        this.Z.d = null;
        this.Z.k = 0;
    }

    private void O() {
        if (this.k > 0 && (getActivity() instanceof MediaBrowseActivity)) {
            c cVar = new c();
            a((cn.xiaochuankeji.tieba.background.utils.e.b) cVar);
            cVar.a = G();
            if (!(this.aa == null || this.aa.h5Video == null)) {
                cVar.i = this.aa.h5Video.url;
            }
            cVar.b = this.Z.b;
            cVar.d = this.Z.d;
            cVar.j = M();
            cVar.k = this.Z.k;
            if (cVar.a.equals("review")) {
                cVar.l = this.k;
                cVar.m = J();
            }
            d.a().a(F() + "-h5", cVar);
        }
    }

    public int c() {
        return this.j;
    }

    public cn.htjyb.b.a d() {
        return this.l;
    }

    public void e() {
    }

    private void d(boolean z) {
        if (z) {
            this.ah.setVisibility(0);
            this.aj.start();
            return;
        }
        this.aj.stop();
        this.ah.setVisibility(8);
    }

    private void P() {
        this.V = System.nanoTime();
    }

    private void Q() {
        if (this.V > 0) {
            this.U += System.nanoTime() - this.V;
        }
        this.V = 0;
    }

    private void R() {
        final boolean r = r();
        if (this.E == null) {
            this.E = new cn.xiaochuankeji.tieba.common.d.a(getActivity());
            this.E.a(false);
            this.E.a(new IMediaPlayer$OnPreparedListener(this) {
                final /* synthetic */ g a;

                {
                    this.a = r1;
                }

                public void onPrepared(IMediaPlayer iMediaPlayer) {
                    com.izuiyou.a.a.b.b("player onPrepared, danmaku start and pause");
                    this.a.T = iMediaPlayer.getDuration();
                    this.a.f.n();
                    this.a.i = 0;
                }
            });
            this.E.a(new IMediaPlayer$OnInfoListener(this) {
                final /* synthetic */ g b;

                public boolean onInfo(IMediaPlayer iMediaPlayer, int i, int i2) {
                    switch (i) {
                        case 3:
                            this.b.ac.removeMessages(5);
                            if (!r && this.b.Y == 0) {
                                this.b.Y = System.nanoTime();
                                break;
                            }
                        case 701:
                            if (iMediaPlayer.isPlaying()) {
                                com.izuiyou.a.a.b.b("player onBuffering, danmaku pause");
                                this.b.f.d();
                                this.b.d(true);
                                if (!r) {
                                    this.b.W = this.b.W + 1;
                                    this.b.ap.a();
                                }
                                this.b.Q();
                                break;
                            }
                            break;
                        case IMediaPlayer.MEDIA_INFO_BUFFERING_END /*702*/:
                            break;
                    }
                    if (iMediaPlayer.isPlaying()) {
                        float f;
                        com.izuiyou.a.a.b.b("player onReady, danmaku resume");
                        this.b.f.c();
                        this.b.d(false);
                        this.b.e.setVisibility(8);
                        if (this.b.F == null) {
                            f = 0.0f;
                        } else {
                            f = (float) this.b.F.getDuration();
                        }
                        this.b.ap.a(this.b.as, 1, (f * ((float) this.b.ar)) / 100.0f, null);
                        this.b.P();
                    }
                    return true;
                }
            });
            this.E.a(new IMediaPlayer$OnSeekCompleteListener(this) {
                final /* synthetic */ g a;

                {
                    this.a = r1;
                }

                public void onSeekComplete(IMediaPlayer iMediaPlayer) {
                    if (this.a.E != null) {
                        int currentPosition = this.a.E.getCurrentPosition();
                        com.izuiyou.a.a.b.b("player onSeekComplete, danmaku seek pos=" + currentPosition);
                        boolean l = this.a.f.l();
                        this.a.f.a(Long.valueOf((long) currentPosition));
                        if (l) {
                            this.a.f.d();
                        }
                        this.a.g.a(currentPosition);
                    }
                }
            });
            this.E.a(new IMediaPlayer$OnCompletionListener(this) {
                final /* synthetic */ g a;

                {
                    this.a = r1;
                }

                public void onCompletion(IMediaPlayer iMediaPlayer) {
                    float f;
                    if (this.a.F == null) {
                        f = 0.0f;
                    } else {
                        f = (float) this.a.F.getDuration();
                    }
                    float M = (f * ((float) this.a.ar)) / 100.0f;
                    this.a.ap.a(this.a.as, 1, M, null);
                    this.a.ap.a(this.a.as, 1, this.a.H(), (long) this.a.E.getDuration(), M, "");
                    if (this.a.J && (this.a.r() || (this.a.aq != null && this.a.aq.a(this.a.as)))) {
                        this.a.J = false;
                        this.a.Q();
                        this.a.f.a(Long.valueOf(0));
                        this.a.y();
                    } else if (this.a.E != null) {
                        this.a.E.seekTo(0);
                        this.a.E.start();
                    }
                }
            });
            this.C.setVisibility(4);
            this.E.a(new IMediaPlayer$OnErrorListener(this) {
                final /* synthetic */ g a;

                {
                    this.a = r1;
                }

                public boolean onError(IMediaPlayer iMediaPlayer, int i, int i2) {
                    this.a.ac.removeMessages(5);
                    float duration = ((this.a.F == null ? 0.0f : (float) this.a.F.getDuration()) * ((float) this.a.ar)) / 100.0f;
                    this.a.ap.a(this.a.as, 1, duration, null);
                    this.a.ap.a(this.a.as, 2, this.a.H(), (long) this.a.E.getCurrentPosition(), duration, "play error:[framework_err:" + i + ", player_error:" + i2 + "]");
                    return this.a.a(iMediaPlayer, i, i2);
                }
            });
            this.E.a(new IMediaPlayer$OnVideoSizeChangedListener(this) {
                final /* synthetic */ g a;

                {
                    this.a = r1;
                }

                public void onVideoSizeChanged(IMediaPlayer iMediaPlayer, int i, int i2, int i3, int i4) {
                    float f = 1.0f;
                    if (this.a.at == 0 || this.a.au == 0) {
                        com.izuiyou.a.a.b.b("getVideoSize");
                        this.a.au = i2;
                        this.a.at = i;
                        AspectRatioFrameLayout U = this.a.b;
                        if (i2 != 0) {
                            f = (1.0f * ((float) i)) / ((float) i2);
                        }
                        U.setAspectRatio(f);
                        this.a.e.setVisibility(8);
                        this.a.s.setEnabled(true);
                    }
                }
            });
            String s = s();
            String str = "";
            com.izuiyou.a.a.b.b("play origin url:" + s);
            if (TextUtils.isEmpty(s)) {
                a(true, false, false);
                return;
            }
            if (this.aq == null || !this.aq.a(s)) {
                this.J = true;
                d(true);
            } else {
                this.J = false;
            }
            if (this.aq == null || TextUtils.isEmpty(s)) {
                str = s;
            } else {
                this.aq.f(this.as);
                this.aq.a(this, s);
                if (s.startsWith("http")) {
                    str = this.aq.a(s, true);
                } else {
                    str = s;
                }
            }
            if (TextUtils.isEmpty(str)) {
                a(true, false, false);
                return;
            }
            this.as = s;
            this.E.a(str);
            this.ap.a(!this.J, s);
        }
        this.ac.sendEmptyMessageDelayed(5, 6000);
        this.F = this.E.a();
        if (this.i > 0) {
            this.E.seekTo(this.i);
        }
        if (!r) {
            this.X = System.nanoTime();
            this.Y = 0;
        }
        N();
        com.izuiyou.a.a.b.b("set player surface holder");
        this.E.a(this.d);
        this.E.start();
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private boolean a(tv.danmaku.ijk.media.player.IMediaPlayer r9, int r10, int r11) {
        /*
        r8 = this;
        r6 = 0;
        r5 = 1;
        r0 = r8.i;
        if (r0 != 0) goto L_0x000e;
    L_0x0007:
        r0 = r9.getCurrentPosition();
        r0 = (int) r0;
        r8.i = r0;
    L_0x000e:
        r8.L();
        r0 = cn.htjyb.netlib.NetworkMonitor.a();
        r2 = r9.getCurrentPosition();
        if (r0 == 0) goto L_0x0027;
    L_0x001b:
        r1 = (r2 > r6 ? 1 : (r2 == r6 ? 0 : -1));
        if (r1 > 0) goto L_0x0027;
    L_0x001f:
        r1 = 0;
        r1 = r8.a(r5, r1, r5);
        if (r1 == 0) goto L_0x0027;
    L_0x0026:
        return r5;
    L_0x0027:
        if (r0 == 0) goto L_0x0060;
    L_0x0029:
        r0 = r8.r();
        if (r0 == 0) goto L_0x004b;
    L_0x002f:
        r0 = new java.io.File;	 Catch:{ Exception -> 0x005b }
        r1 = r8.q();	 Catch:{ Exception -> 0x005b }
        r0.<init>(r1);	 Catch:{ Exception -> 0x005b }
        r1 = r0.getAbsolutePath();	 Catch:{ Exception -> 0x005b }
        r4 = "cn.xiaochuankeji.tieba";
        r1 = r1.contains(r4);	 Catch:{ Exception -> 0x005b }
        if (r1 == 0) goto L_0x004b;
    L_0x0045:
        r0.delete();	 Catch:{ Exception -> 0x005b }
        r8.y();	 Catch:{ Exception -> 0x005b }
    L_0x004b:
        r0 = (r2 > r6 ? 1 : (r2 == r6 ? 0 : -1));
        if (r0 > 0) goto L_0x0057;
    L_0x004f:
        r0 = r8.E;
        r0 = r0.c();
        if (r0 != 0) goto L_0x0026;
    L_0x0057:
        r8.T();
        goto L_0x0026;
    L_0x005b:
        r0 = move-exception;
        r0.printStackTrace();
        goto L_0x004b;
    L_0x0060:
        r0 = "没有网络，请连接~";
        cn.xiaochuankeji.tieba.background.utils.g.a(r0);
        goto L_0x0057;
        */
        throw new UnsupportedOperationException("Method not decompiled: cn.xiaochuankeji.tieba.ui.mediabrowse.component.g.a(tv.danmaku.ijk.media.player.IMediaPlayer, int, int):boolean");
    }

    private void S() {
        if (this.ap != null) {
            this.ap.a(this.as, 5, "", "", 0, this.F == null ? 0 : (long) this.F.getCurrentPosition(), "auto cancel");
        }
        NetworkMonitor.a();
        if ((this.E == null ? 0 : (long) this.E.getCurrentPosition()) <= 0) {
            a(true, true, false);
        }
    }

    private void T() {
        this.ac.sendEmptyMessage(4);
        d(false);
        this.C.setVisibility(0);
        this.ap.a(2);
    }

    private boolean a(boolean z, boolean z2, boolean z3) {
        float f = 0.0f;
        if (this.aa != null) {
            String str = this.as;
            if (this.F != null) {
                this.F.getCurrentPosition();
            }
            if (!z3) {
                if (this.F != null) {
                    f = (float) this.F.getDuration();
                }
                f = (f * ((float) this.ar)) / 100.0f;
                this.ap.a(this.as, 1, f, null);
            }
            do {
                switch (this.aa.priority) {
                    case 1:
                        this.aa.priority = 2;
                        break;
                    case 2:
                        this.aa.priority = 3;
                        break;
                    case 3:
                        this.aa.priority = 4;
                        break;
                    case 4:
                        this.aa = null;
                        break;
                }
                if (this.ap == null || this.aa == null) {
                    if (this.aa == null) {
                        com.izuiyou.a.a.b.b("video info priority:" + this.aa.priority);
                    } else {
                        com.izuiyou.a.a.b.b("video info priority: 4");
                    }
                    if (this.aa != null) {
                        d(false);
                        this.C.setVisibility(4);
                        if (z) {
                            this.ac.sendEmptyMessage(4);
                        }
                        this.ac.sendEmptyMessage(3);
                        this.ap.a(str, s(), (long) f);
                        return true;
                    }
                }
            } while (this.ap.a(s()));
            if (this.aa == null) {
                com.izuiyou.a.a.b.b("video info priority: 4");
            } else {
                com.izuiyou.a.a.b.b("video info priority:" + this.aa.priority);
            }
            if (this.aa != null) {
                d(false);
                this.C.setVisibility(4);
                if (z) {
                    this.ac.sendEmptyMessage(4);
                }
                this.ac.sendEmptyMessage(3);
                this.ap.a(str, s(), (long) f);
                return true;
            }
        }
        return false;
    }

    public void surfaceCreated(SurfaceHolder surfaceHolder) {
        com.izuiyou.a.a.b.c("surfaceCreated");
        this.d = surfaceHolder;
        this.d.removeCallback(this);
        this.d.addCallback(this);
        x();
    }

    public void surfaceChanged(SurfaceHolder surfaceHolder, int i, int i2, int i3) {
        com.izuiyou.a.a.b.c("surface changed: width:" + i2);
    }

    public void surfaceDestroyed(SurfaceHolder surfaceHolder) {
        this.d = null;
        U();
    }

    public void onDestroy() {
        super.onDestroy();
        if (this.D != null) {
            this.D.c();
        }
        if (this.G != null) {
            this.G.a();
            this.G = null;
        }
        if (this.aq != null) {
            this.aq.a(this);
            cn.xiaochuankeji.tieba.background.a.p().e().execute(new Runnable(this) {
                final /* synthetic */ g a;

                {
                    this.a = r1;
                }

                public void run() {
                    this.a.aq.f(this.a.as);
                }
            });
        }
        if (this.ad != null) {
            this.ad.a();
        }
        if (this.f != null) {
            this.f.e();
        }
        if (this.g != null) {
            this.g.a(true);
        }
        if (this.ap != null) {
            this.ap.b();
        }
    }

    private void U() {
        if (this.E != null) {
            this.E.g();
            this.E = null;
            this.F = null;
        }
    }

    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.ivPlay:
            case R.id.btn_small_control:
                W();
                return;
            case R.id.text_danmu:
                if (!cn.xiaochuankeji.tieba.ui.auth.a.a(getActivity(), "media_browser", 2)) {
                    return;
                }
                if (this.ak) {
                    cn.xiaochuankeji.tieba.background.utils.g.a("审帖时不可以发弹幕哦~");
                    return;
                } else if (!h()) {
                    cn.xiaochuankeji.tieba.background.utils.g.a("视频还未准备好");
                    return;
                } else if (j() && (getActivity() instanceof MediaBrowseActivity)) {
                    ((MediaBrowseActivity) getActivity()).B();
                    return;
                } else {
                    return;
                }
            case R.id.iv_danmu_switch:
                if (view.isSelected()) {
                    cn.xiaochuankeji.tieba.background.utils.g.a("弹幕已关闭");
                } else {
                    cn.xiaochuankeji.tieba.background.utils.g.a("弹幕已开启");
                }
                this.w.setSelected(!view.isSelected());
                g(this.w.isSelected());
                b(view.isSelected());
                return;
            case R.id.btn_fullscreen_switch:
                V();
                return;
            default:
                return;
        }
    }

    private void e(boolean z) {
        this.af = z;
        if (z) {
            WindowManager.LayoutParams attributes = getActivity().getWindow().getAttributes();
            attributes.flags |= 1024;
            getActivity().getWindow().setAttributes(attributes);
            getActivity().getWindow().addFlags(512);
            return;
        }
        attributes = getActivity().getWindow().getAttributes();
        attributes.flags &= -1025;
        getActivity().getWindow().setAttributes(attributes);
        getActivity().getWindow().clearFlags(512);
    }

    public boolean f() {
        if (getResources().getConfiguration().orientation == 1) {
            return false;
        }
        V();
        return true;
    }

    private void V() {
        if (getResources().getConfiguration().orientation == 1) {
            getActivity().setRequestedOrientation(0);
        } else {
            getActivity().setRequestedOrientation(1);
        }
    }

    public long g() {
        return this.E == null ? 0 : (long) this.E.getCurrentPosition();
    }

    public void a(DanmakuItem danmakuItem) {
        this.f.a(danmakuItem);
    }

    public boolean h() {
        if (this.E == null || this.E.getCurrentPosition() <= 0) {
            return false;
        }
        return true;
    }

    public void i() {
        if (!this.ag) {
            X();
        }
    }

    public boolean j() {
        if (this.F == null || this.F.isPlaying()) {
            this.ag = false;
            return Y();
        }
        this.ag = true;
        return true;
    }

    private void W() {
        if (this.F == null) {
            x();
        } else if (this.F.isPlaying()) {
            Y();
        } else {
            X();
        }
    }

    private boolean X() {
        if (!(o() || this.F == null || this.F.isPlaying())) {
            this.F.start();
            this.f.c();
            this.g.b();
            this.n.setSelected(false);
            this.t.setSelected(false);
            P();
            f(true);
        }
        return false;
    }

    private boolean Y() {
        if (this.F == null || !this.F.canPause() || this.at <= 0) {
            return false;
        }
        com.izuiyou.a.a.b.b("pause video");
        this.F.pause();
        this.f.d();
        this.g.a();
        this.n.setSelected(true);
        this.t.setSelected(true);
        Q();
        f(false);
        return true;
    }

    private void b(boolean z, boolean z2, boolean z3) {
        if (this.N != z) {
            if (z) {
                this.o.setVisibility(0);
                if (z3) {
                    this.o.startAnimation(this.O);
                } else {
                    this.o.clearAnimation();
                }
                if (isAdded() && getResources() != null && getResources().getConfiguration().orientation == 2) {
                    if ((getActivity() instanceof MediaBrowseActivity) && ((MediaBrowseActivity) getActivity()).j()) {
                        this.p.setVisibility(4);
                    } else {
                        this.p.setVisibility(0);
                    }
                    if (z3) {
                        this.p.startAnimation(this.O);
                        this.p.clearAnimation();
                    }
                }
                if (this.x.getVisibility() == 0) {
                    this.x.setVisibility(4);
                    if (z3) {
                        this.x.startAnimation(this.R);
                    } else {
                        this.x.clearAnimation();
                    }
                }
                if (z2) {
                    this.n.setVisibility(0);
                    if (z3) {
                        this.n.startAnimation(this.Q);
                    } else {
                        this.n.clearAnimation();
                    }
                }
            } else {
                if (this.n.getVisibility() == 0) {
                    this.n.setVisibility(4);
                    if (z3) {
                        this.n.startAnimation(this.R);
                    } else {
                        this.n.clearAnimation();
                    }
                }
                this.o.setVisibility(8);
                if (z3) {
                    this.o.startAnimation(this.P);
                } else {
                    this.o.clearAnimation();
                }
                if (isAdded() && getResources() != null && getResources().getConfiguration().orientation == 2) {
                    this.p.setVisibility(8);
                    if (z3) {
                        this.p.startAnimation(this.P);
                        this.p.clearAnimation();
                    }
                }
                this.x.setVisibility(0);
                if (z3) {
                    this.x.startAnimation(this.Q);
                } else {
                    this.x.clearAnimation();
                }
            }
            this.N = z;
        }
        if (z) {
            Z();
        }
    }

    private void f(boolean z) {
        this.ay = z;
        Z();
    }

    private void Z() {
        this.ac.removeCallbacks(this.az);
        if (this.ay) {
            this.ac.postDelayed(this.az, 3000);
        }
    }

    private void g(boolean z) {
        cn.xiaochuankeji.tieba.background.a.a().edit().putBoolean("key_danmuku_switch_state", z).apply();
    }

    @org.greenrobot.eventbus.l(a = ThreadMode.MAIN)
    public void audioState(cn.xiaochuankeji.tieba.background.d.a aVar) {
        if (aVar != null) {
            if (aVar.a == 0 || aVar.a == 1) {
                W();
            }
        }
    }

    public void a(String str, String str2, long j, int i, String str3, boolean z) {
        if (!TextUtils.isEmpty(str) && this.ap != null) {
            this.ap.a(str, str2, j, "", i, str3, z);
        }
    }

    public void a(String str, String str2, String str3, int i, int i2, String str4) {
        if (this.ap != null && this.F != null) {
            this.ap.a(str, i, str2, str3, i2, (long) ((int) (((((float) this.ar) * 1.0f) / 100.0f) * ((float) this.F.getDuration()))), str4);
        }
    }

    public void a(File file, String str, int i) {
        if (i >= 100) {
            i = 100;
        }
        this.ar = i;
        if (this.s != null && this.F != null) {
            this.s.setSecondaryProgress(this.F.getDuration());
        }
    }

    @TargetApi(8)
    private void aa() {
    }
}
