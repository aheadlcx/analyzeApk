package cn.xiaochuankeji.tieba.ui.ugcvideodetail;

import android.graphics.SurfaceTexture;
import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentActivity;
import android.text.TextUtils;
import android.view.GestureDetector;
import android.view.GestureDetector.SimpleOnGestureListener;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.TextureView;
import android.view.TextureView.SurfaceTextureListener;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.MediaController.MediaPlayerControl;
import cn.htjyb.netlib.NetworkMonitor;
import cn.xiaochuankeji.tieba.R;
import cn.xiaochuankeji.tieba.background.AppController;
import cn.xiaochuankeji.tieba.background.danmaku.DanmakuItem;
import cn.xiaochuankeji.tieba.background.utils.e.e;
import cn.xiaochuankeji.tieba.json.UgcVideoInfoBean;
import cn.xiaochuankeji.tieba.ui.base.f;
import cn.xiaochuankeji.tieba.ui.danmaku.HotDanmakuView;
import cn.xiaochuankeji.tieba.ui.danmaku.TopDanmakuView;
import cn.xiaochuankeji.tieba.ui.mediabrowse.component.h;
import cn.xiaochuankeji.tieba.ui.widget.AspectRatioFrameLayout;
import cn.xiaochuankeji.tieba.ui.widget.image.WebImageView;
import com.danikula.videocache.q;
import java.io.File;
import java.util.ArrayList;
import master.flame.danmaku.danmaku.model.d;
import master.flame.danmaku.danmaku.model.l;
import org.greenrobot.eventbus.ThreadMode;
import org.greenrobot.eventbus.c;
import tv.danmaku.ijk.media.player.IMediaPlayer;
import tv.danmaku.ijk.media.player.IMediaPlayer$OnCompletionListener;
import tv.danmaku.ijk.media.player.IMediaPlayer$OnErrorListener;
import tv.danmaku.ijk.media.player.IMediaPlayer$OnInfoListener;
import tv.danmaku.ijk.media.player.IMediaPlayer$OnPreparedListener;
import tv.danmaku.ijk.media.player.IMediaPlayer$OnVideoSizeChangedListener;

public class g extends f implements OnClickListener, com.danikula.videocache.b {
    private e A;
    private long B = 0;
    private long C = 0;
    private long D;
    private long E;
    private int F;
    private int G = 0;
    private Runnable H = new Runnable(this) {
        final /* synthetic */ g a;

        {
            this.a = r1;
        }

        public void run() {
            if (this.a.g && this.a.h) {
                if (this.a.A != null) {
                    this.a.F = this.a.F + 1;
                    this.a.o();
                }
                this.a.A = new e();
            }
        }
    };
    private Runnable I = new Runnable(this) {
        final /* synthetic */ g a;

        {
            this.a = r1;
        }

        public void run() {
            if (this.a.A != null) {
                this.a.o();
                this.a.A = null;
            }
        }
    };
    private UgcVideoInfoBean a;
    private String b;
    private com.danikula.videocache.f c;
    private h d;
    private boolean e = false;
    private boolean f = true;
    private boolean g = false;
    private boolean h = false;
    private WebImageView i;
    private FrameLayout j;
    private cn.xiaochuankeji.tieba.common.d.a k;
    private AspectRatioFrameLayout l;
    private HotDanmakuView m;
    private TopDanmakuView n;
    private Button o;
    private ImageView p;
    private AnimationDrawable q;
    private TextureView r;
    private GestureDetector s;
    private SurfaceTexture t;
    private cn.xiaochuankeji.tieba.ui.danmaku.e u;
    private final Handler v = new a();
    private MediaPlayerControl w;
    private float x;
    private boolean y = false;
    private boolean z = false;

    private class a extends Handler {
        final /* synthetic */ g a;

        private a(g gVar) {
            this.a = gVar;
        }

        public void handleMessage(Message message) {
            switch (message.what) {
                case 1:
                    this.a.l();
                    return;
                case 2:
                    this.a.k();
                    return;
                default:
                    return;
            }
        }
    }

    public static class b {
        private long a;

        public b(long j) {
            this.a = j;
        }

        public long a() {
            return this.a;
        }
    }

    public static g a(UgcVideoInfoBean ugcVideoInfoBean, String str) {
        Bundle bundle = new Bundle();
        bundle.putSerializable("s_key_video_info_bean", ugcVideoInfoBean);
        bundle.putString("key_from", str);
        g gVar = new g();
        gVar.setArguments(bundle);
        return gVar;
    }

    public void onCreate(Bundle bundle) {
        long j = 0;
        super.onCreate(bundle);
        this.x = (float) cn.xiaochuankeji.tieba.ui.utils.e.a(10.0f);
        Bundle arguments = getArguments();
        if (arguments != null) {
            this.a = (UgcVideoInfoBean) arguments.getSerializable("s_key_video_info_bean");
            this.b = arguments.getString("key_from");
        }
        if (this.c == null) {
            this.c = q.a().b();
        }
        this.d = new h();
        h hVar = this.d;
        long j2 = this.a.pid > 0 ? this.a.pid : this.a.id;
        if (this.a.pid > 0) {
            j = this.a.id;
        }
        hVar.a(j2, j, this.a.videoInfo.id, "ugcvideo");
    }

    @Nullable
    public View onCreateView(LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, @Nullable Bundle bundle) {
        View inflate = layoutInflater.inflate(R.layout.fragment_ugcvideo_play, null);
        this.j = (FrameLayout) inflate.findViewById(R.id.rootView);
        this.i = (WebImageView) inflate.findViewById(R.id.wivThumb);
        this.l = (AspectRatioFrameLayout) inflate.findViewById(R.id.video_frame);
        this.l.setAspectRatio(0.5625f);
        this.m = (HotDanmakuView) inflate.findViewById(R.id.hot_danmaku);
        this.m.setCustomTextSize(13);
        this.n = (TopDanmakuView) inflate.findViewById(R.id.top_danmaku);
        this.o = (Button) inflate.findViewById(R.id.btn_retry);
        this.o.setOnClickListener(this);
        this.p = (ImageView) inflate.findViewById(R.id.ivLoadingAnim);
        this.q = (AnimationDrawable) this.p.getDrawable();
        this.r = (TextureView) inflate.findViewById(R.id.textureView);
        return inflate;
    }

    public void onViewCreated(View view, @Nullable Bundle bundle) {
        super.onViewCreated(view, bundle);
        this.e = true;
        b();
        c();
        e();
        f();
    }

    private void b() {
        this.u = new cn.xiaochuankeji.tieba.ui.danmaku.e(0, this.a.videoInfo.id, true);
        this.u.a(new cn.xiaochuankeji.tieba.ui.danmaku.e.a(this) {
            final /* synthetic */ g a;

            {
                this.a = r1;
            }

            public void a(ArrayList<DanmakuItem> arrayList) {
                this.a.m.a((ArrayList) arrayList);
            }

            public void b(ArrayList<DanmakuItem> arrayList) {
                this.a.n.a((ArrayList) arrayList);
            }
        });
    }

    public void onDestroyView() {
        super.onDestroyView();
        this.e = false;
        this.u.a(null);
        this.m.q();
        this.m.e();
    }

    private void c() {
        this.i.setImageURI(cn.xiaochuankeji.tieba.background.utils.d.a.a("/img/view/id/", (long) this.a.img.id, "/sz/360"));
        this.r.setSurfaceTextureListener(new SurfaceTextureListener(this) {
            final /* synthetic */ g a;

            {
                this.a = r1;
            }

            public void onSurfaceTextureAvailable(SurfaceTexture surfaceTexture, int i, int i2) {
                this.a.t = surfaceTexture;
                this.a.g();
            }

            public void onSurfaceTextureSizeChanged(SurfaceTexture surfaceTexture, int i, int i2) {
            }

            public boolean onSurfaceTextureDestroyed(SurfaceTexture surfaceTexture) {
                this.a.t = null;
                this.a.j();
                return true;
            }

            public void onSurfaceTextureUpdated(SurfaceTexture surfaceTexture) {
            }
        });
    }

    public void setUserVisibleHint(boolean z) {
        super.setUserVisibleHint(z);
        this.g = z;
        if (z) {
            q();
            g();
            return;
        }
        j();
    }

    private boolean d() {
        return cn.xiaochuankeji.tieba.background.a.a().getBoolean("key_danmuku_switch_state", true);
    }

    private void e() {
        this.m.setOnDanmakuClickListener(new master.flame.danmaku.a.f.a(this) {
            final /* synthetic */ g a;

            {
                this.a = r1;
            }

            public boolean a(l lVar) {
                d d = lVar.d();
                if (d instanceof cn.xiaochuankeji.tieba.ui.danmaku.b) {
                    this.a.y = true;
                    this.a.n.a(((cn.xiaochuankeji.tieba.ui.danmaku.b) d).a);
                }
                return true;
            }

            public boolean b(l lVar) {
                return true;
            }

            public boolean a(master.flame.danmaku.a.f fVar) {
                return true;
            }
        });
    }

    private void f() {
        this.s = new GestureDetector(getActivity(), new SimpleOnGestureListener(this) {
            final /* synthetic */ g a;

            {
                this.a = r1;
            }

            public boolean onDoubleTap(MotionEvent motionEvent) {
                this.a.getActivity().finish();
                return true;
            }

            public boolean onSingleTapConfirmed(MotionEvent motionEvent) {
                this.a.j.postDelayed(new Runnable(this) {
                    final /* synthetic */ AnonymousClass9 a;

                    {
                        this.a = r1;
                    }

                    public void run() {
                        if (this.a.a.y) {
                            this.a.a.y = false;
                            return;
                        }
                        UgcVideoActivity ugcVideoActivity = (UgcVideoActivity) this.a.a.getActivity();
                        if (this.a.a.k != null && this.a.a.p.getVisibility() != 0) {
                            if (this.a.a.k.isPlaying()) {
                                this.a.a.k.pause();
                                this.a.a.m.d();
                                this.a.a.n.a();
                                ugcVideoActivity.e(true);
                                ugcVideoActivity.d(true);
                            } else if (this.a.a.k.canPause()) {
                                this.a.a.m.c();
                                this.a.a.n.b();
                                this.a.a.k.start();
                                ugcVideoActivity.e(false);
                                ugcVideoActivity.d(false);
                            }
                        }
                    }
                }, 100);
                return true;
            }

            public boolean onFling(MotionEvent motionEvent, MotionEvent motionEvent2, float f, float f2) {
                float rawX = motionEvent.getRawX();
                float rawY = motionEvent.getRawY();
                float rawX2 = motionEvent2.getRawX();
                float rawY2 = motionEvent2.getRawY();
                if (Math.abs(rawY2 - rawY) > Math.abs(rawX2 - rawX)) {
                    FragmentActivity activity;
                    if (rawY - rawY2 > this.a.x) {
                        activity = this.a.getActivity();
                        if (activity != null && (activity instanceof UgcVideoActivity)) {
                            ((UgcVideoActivity) activity).d(true);
                        }
                    } else if (rawY2 - rawY > this.a.x) {
                        activity = this.a.getActivity();
                        if (activity != null && (activity instanceof UgcVideoActivity)) {
                            ((UgcVideoActivity) activity).d(false);
                        }
                    }
                }
                return super.onFling(motionEvent, motionEvent2, f, f2);
            }
        });
        OnTouchListener anonymousClass10 = new OnTouchListener(this) {
            final /* synthetic */ g a;

            {
                this.a = r1;
            }

            public boolean onTouch(View view, MotionEvent motionEvent) {
                return this.a.s.onTouchEvent(motionEvent);
            }
        };
        this.j.setOnClickListener(null);
        this.j.setOnTouchListener(anonymousClass10);
        this.m.setOnTouchListener(anonymousClass10);
    }

    private void g() {
        boolean z = true;
        if (this.t != null && this.g && this.e && this.h && !cn.xiaochuankeji.tieba.background.a.a().getBoolean("key_show_review_guide", true)) {
            boolean z2;
            if (1 == this.a.status || 5 == this.a.status) {
                z2 = true;
            } else {
                z2 = false;
            }
            long c = cn.xiaochuankeji.tieba.background.a.g().c();
            if (4 != this.a.status || c == this.a.member.getId()) {
                z = false;
            }
            if (z2 || r2) {
                cn.xiaochuankeji.tieba.background.utils.g.a("此视频已删除");
                return;
            }
            j();
            if (this.k == null) {
                z2 = this.c.a(this.a.videoInfo.url);
                if (!z2 && 0 == this.D) {
                    this.D = System.nanoTime();
                    this.E = 0;
                }
                this.k = new cn.xiaochuankeji.tieba.common.d.a(getActivity());
                this.k.a(false);
                this.k.a(new IMediaPlayer$OnPreparedListener(this) {
                    final /* synthetic */ g a;

                    {
                        this.a = r1;
                    }

                    public void onPrepared(IMediaPlayer iMediaPlayer) {
                        this.a.r();
                        if (this.a.A != null) {
                            this.a.A.c = (long) this.a.k.getDuration();
                        }
                    }
                });
                this.k.a(new IMediaPlayer$OnInfoListener(this) {
                    final /* synthetic */ g b;

                    public boolean onInfo(IMediaPlayer iMediaPlayer, int i, int i2) {
                        switch (i) {
                            case 3:
                                if (!z2 && 0 == this.b.E && this.b.D > 0) {
                                    this.b.E = System.nanoTime();
                                    break;
                                }
                            case 701:
                                if (iMediaPlayer.isPlaying()) {
                                    this.b.s();
                                    if (!z2) {
                                        this.b.b(true);
                                        this.b.d.a();
                                    }
                                    this.b.m.d();
                                }
                                if (this.b.A != null) {
                                    e n = this.b.A;
                                    n.d++;
                                    break;
                                }
                                break;
                            case IMediaPlayer.MEDIA_INFO_BUFFERING_END /*702*/:
                                break;
                        }
                        if (!z2) {
                            float f;
                            this.b.b(false);
                            if (this.b.w == null) {
                                f = 0.0f;
                            } else {
                                f = (float) this.b.w.getDuration();
                            }
                            this.b.d.a(this.b.a.videoInfo.url, 1, (f * ((float) this.b.G)) / 100.0f, null);
                        }
                        this.b.m.c();
                        if (iMediaPlayer.isPlaying()) {
                            this.b.r();
                        }
                        return true;
                    }
                });
                this.k.a(new IMediaPlayer$OnErrorListener(this) {
                    final /* synthetic */ g a;

                    {
                        this.a = r1;
                    }

                    public boolean onError(IMediaPlayer iMediaPlayer, int i, int i2) {
                        String str = this.a.a.videoInfo.url;
                        float duration = ((this.a.w == null ? 0.0f : (float) this.a.w.getDuration()) * ((float) this.a.G)) / 100.0f;
                        this.a.d.a(str, 1, duration, null);
                        this.a.d.a(str, 2, this.a.p(), (long) this.a.k.getCurrentPosition(), duration, "play error:[framework_err:" + i + ", player_error:" + i2 + "]");
                        this.a.d.a(2);
                        if (!NetworkMonitor.a()) {
                            cn.xiaochuankeji.tieba.background.utils.g.a("没有网络，请连接~");
                        }
                        this.a.j();
                        this.a.o.setVisibility(0);
                        this.a.b(false);
                        return false;
                    }
                });
                this.k.a(new IMediaPlayer$OnCompletionListener(this) {
                    final /* synthetic */ g a;

                    {
                        this.a = r1;
                    }

                    public void onCompletion(IMediaPlayer iMediaPlayer) {
                        float f;
                        String str = this.a.a.videoInfo.url;
                        if (this.a.w == null) {
                            f = 0.0f;
                        } else {
                            f = (float) this.a.w.getDuration();
                        }
                        float t = (f * ((float) this.a.G)) / 100.0f;
                        this.a.d.a(str, 1, t, null);
                        this.a.d.a(str, 1, this.a.p(), (long) this.a.k.getDuration(), t, "");
                        if (!((UgcVideoActivity) this.a.getActivity()).y()) {
                            if (this.a.d()) {
                                this.a.m.a(Long.valueOf(0));
                                this.a.n.a(0);
                            }
                            if (this.a.f && this.a.c.a(str)) {
                                this.a.g();
                                this.a.s();
                            } else if (this.a.k != null) {
                                this.a.k.seekTo(0);
                                this.a.u();
                            }
                        }
                    }
                });
                this.k.a(new IMediaPlayer$OnVideoSizeChangedListener(this) {
                    final /* synthetic */ g a;

                    {
                        this.a = r1;
                    }

                    public void onVideoSizeChanged(IMediaPlayer iMediaPlayer, int i, int i2, int i3, int i4) {
                        float f = 1.0f;
                        AspectRatioFrameLayout B = this.a.l;
                        if (i2 != 0) {
                            f = (1.0f * ((float) i)) / ((float) i2);
                        }
                        B.setAspectRatio(f);
                    }
                });
                this.k.a(this.t);
                h();
                if (this.m.a()) {
                    this.m.a(Long.valueOf(0));
                } else {
                    this.m.n();
                }
                if (d()) {
                    m();
                } else {
                    n();
                }
            }
        }
    }

    private void h() {
        boolean z;
        String a;
        h hVar;
        boolean z2 = true;
        String str = this.a.videoInfo.url;
        if (this.c.a(str)) {
            File c = this.c.c(str);
            if (c != null) {
                if (c.length() > 0) {
                    this.f = false;
                    z = true;
                    if (!z) {
                        this.f = true;
                        if (!this.c.b(str)) {
                            b(true);
                        }
                    }
                    this.c.a();
                    this.c.a(this, str);
                    a = this.c.a(str, true);
                    this.k.a(a);
                    hVar = this.d;
                    if (this.f) {
                        z2 = false;
                    }
                    hVar.a(z2, a);
                    this.w = this.k.a();
                    u();
                    if (!this.f) {
                        v();
                    }
                }
                c.delete();
            }
        }
        z = false;
        if (z) {
            this.f = true;
            if (this.c.b(str)) {
                b(true);
            }
        }
        this.c.a();
        this.c.a(this, str);
        a = this.c.a(str, true);
        this.k.a(a);
        hVar = this.d;
        if (this.f) {
            z2 = false;
        }
        hVar.a(z2, a);
        this.w = this.k.a();
        u();
        if (!this.f) {
            v();
        }
    }

    private void i() {
        if (this.c != null) {
            this.c.f(this.a.videoInfo.url);
        }
        if (this.d != null) {
            this.d.b();
        }
    }

    private void b(boolean z) {
        if (z) {
            this.p.setVisibility(0);
            this.q.start();
            return;
        }
        this.q.stop();
        this.p.setVisibility(8);
    }

    private void j() {
        if (this.k != null) {
            this.k.g();
            this.k = null;
            s();
        }
    }

    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_retry:
                this.o.setVisibility(4);
                g();
                return;
            default:
                return;
        }
    }

    private void k() {
        if (this.w != null) {
            this.u.a(this.w.getCurrentPosition());
        }
        this.v.sendEmptyMessageDelayed(2, 1000);
    }

    private void l() {
        if (this.w != null) {
            this.n.b(this.w.getCurrentPosition());
        }
        this.v.sendEmptyMessageDelayed(1, 100);
    }

    public void onDestroy() {
        super.onDestroy();
        j();
        i();
    }

    @org.greenrobot.eventbus.l(a = ThreadMode.MAIN)
    public void replayVideo(cn.xiaochuankeji.tieba.background.d.h hVar) {
        if (hVar.a != null && hVar.a.id == this.a.id) {
            this.z = false;
            u();
            r();
            this.m.c();
            if (hVar.c && hVar.b != null) {
                this.m.a(hVar.b);
            }
        }
    }

    @org.greenrobot.eventbus.l(a = ThreadMode.MAIN)
    public void pauseVideo(cn.xiaochuankeji.tieba.background.d.f fVar) {
        if (fVar.a == this.a.id) {
            if (this.k != null) {
                this.k.pause();
                s();
            }
            this.z = true;
            this.m.d();
            if (fVar.b) {
                cn.xiaochuankeji.tieba.background.d.g gVar = new cn.xiaochuankeji.tieba.background.d.g();
                gVar.a = this.a;
                if (this.k != null) {
                    gVar.b = (long) this.k.getCurrentPosition();
                } else {
                    gVar.b = 1;
                }
                c.a().d(gVar);
            }
        }
    }

    @org.greenrobot.eventbus.l(a = ThreadMode.MAIN)
    public void danmakuSwitch(cn.xiaochuankeji.tieba.background.d.d dVar) {
        if (dVar.a) {
            m();
        } else {
            n();
        }
    }

    @org.greenrobot.eventbus.l(a = ThreadMode.MAIN)
    public void videoGuideDismiss(b bVar) {
        if (bVar.a() == this.a.id) {
            g();
        }
    }

    private void m() {
        this.u.a(true);
        this.m.o();
        this.n.setEnabled(true);
        k();
        l();
    }

    private void n() {
        this.u.a(false);
        this.m.p();
        this.n.setEnabled(false);
        this.v.removeMessages(1);
        this.v.removeMessages(2);
    }

    private void o() {
        this.A.e = this.a.videoInfo.id;
        this.A.f = this.a.id;
        this.A.g = this.b;
        this.A.a = this.a.pid > 0 ? "review" : "post";
        this.A.k = this.a.pid;
        this.A.j = "local";
        this.A.m = this.a.videoInfo.url;
        this.A.o = 1;
        this.A.b = this.C / 1000000;
        if (0 == this.D) {
            this.D = System.nanoTime();
        }
        if (0 == this.E) {
            this.E = System.nanoTime();
        }
        this.A.i = p();
        cn.xiaochuankeji.tieba.background.utils.e.d.a().a(t(), this.A);
        this.C = 0;
        this.D = 0;
        this.E = 0;
    }

    private long p() {
        if (0 == this.D) {
            this.D = System.nanoTime();
        }
        if (0 == this.E) {
            this.E = System.nanoTime();
        }
        return (this.E - this.D) / 1000000;
    }

    public void onResume() {
        super.onResume();
        this.h = true;
        if (this.k != null) {
            this.k.start();
        } else {
            g();
        }
        if (!c.a().b(this)) {
            c.a().a(this);
        }
        q();
    }

    private void q() {
        this.v.removeCallbacks(this.H);
        this.v.post(this.H);
    }

    public void onPause() {
        super.onPause();
        this.h = false;
        if (this.k != null) {
            this.k.pause();
            float duration = ((this.w == null ? 0.0f : (float) this.w.getDuration()) * ((float) this.G)) / 100.0f;
            this.d.a(this.a.videoInfo.url, 1, duration, null);
            this.d.a(this.a.videoInfo.url, 1, p(), (long) this.k.getCurrentPosition(), duration, null);
        }
        this.d.a(3);
        if (c.a().b(this)) {
            c.a().c(this);
        }
        s();
        this.v.removeCallbacks(this.I);
        this.v.post(this.I);
        this.v.removeMessages(1);
        this.v.removeMessages(2);
    }

    private void r() {
        this.B = System.nanoTime();
    }

    private void s() {
        if (this.B > 0) {
            this.C += System.nanoTime() - this.B;
        }
        this.B = 0;
    }

    private String t() {
        long j = this.a.videoInfo.id;
        return String.format("%d-%d", new Object[]{Integer.valueOf(this.F), Long.valueOf(j)});
    }

    private void u() {
        if (this.k == null) {
            return;
        }
        if ((getActivity() instanceof UgcVideoActivity) && ((UgcVideoActivity) getActivity()).w() && this.k.e() && ((UgcVideoActivity) getActivity()).v()) {
            cn.xiaochuankeji.tieba.ui.widget.f.a("提示", "您正在使用移动网络，播放将产生流量费用。", getActivity(), new cn.xiaochuankeji.tieba.ui.widget.f.a(this) {
                final /* synthetic */ g a;

                {
                    this.a = r1;
                }

                public void a(boolean z) {
                    if (z) {
                        AppController.instance().setAllowCellular(true);
                        ((UgcVideoActivity) this.a.getActivity()).f(false);
                        this.a.u();
                    }
                }
            }).setConfirmTip("继续观看");
        } else if (this.z) {
            this.m.postDelayed(new Runnable(this) {
                final /* synthetic */ g a;

                {
                    this.a = r1;
                }

                public void run() {
                    this.a.m.d();
                }
            }, 200);
        } else {
            this.k.start();
            this.m.c();
        }
    }

    public void a(String str, String str2, long j, int i, String str3, boolean z) {
        if (!TextUtils.isEmpty(str) && this.d != null) {
            this.d.a(str, str2, j, "", i, str3, z);
        }
    }

    public void a(String str, String str2, String str3, int i, int i2, String str4) {
        if (this.d != null && this.w != null) {
            this.d.a(str, i, str2, str3, i2, (long) ((int) (((((float) this.G) * 1.0f) / 100.0f) * ((float) this.w.getDuration()))), str4);
        }
    }

    public void a(File file, String str, int i) {
        if (i > this.G) {
            this.G = Math.min(i, 100);
            if (this.G == 100) {
                v();
            }
        }
    }

    private void v() {
        if (getActivity() != null) {
            ((UgcVideoActivity) getActivity()).x();
        }
    }
}
