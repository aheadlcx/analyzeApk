package com.budejie.www.activity.video;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build.VERSION;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.widget.RelativeLayout;
import android.widget.SeekBar;
import com.budejie.www.R;
import com.budejie.www.activity.BudejieApplication;
import com.budejie.www.activity.BudejieApplication.Status;
import com.budejie.www.activity.HomeGroup;
import com.budejie.www.activity.detail.PostDetailActivity;
import com.budejie.www.activity.posts.PostsActivity;
import com.budejie.www.activity.video.f.b;
import com.budejie.www.ad.AdConfig;
import com.budejie.www.bean.ListItemObject;
import com.budejie.www.busevent.DetailAction;
import com.budejie.www.http.i;
import com.budejie.www.http.j;
import com.budejie.www.recommendvideo.AutoPlayVideoListActivity;
import com.budejie.www.util.aa;
import com.budejie.www.util.an;
import com.budejie.www.util.ao;
import com.budejie.www.widget.curtain.CurtainVideoContainerLayout;
import com.budejie.www.widget.curtain.FloatVideoLayout;
import com.budejie.www.widget.curtain.FloatVideoRootLayout;
import com.sprite.ads.nati.NativeAd;
import com.sprite.ads.nati.NativeAdRef;
import com.umeng.analytics.MobclickAgent;
import de.greenrobot.event.EventBus;

public class k {
    public static boolean a = (VERSION.SDK_INT >= 14);
    private static k i;
    private static k j;
    public f b;
    public boolean c;
    public String d;
    public Activity e;
    public FloatVideoLayout f;
    public boolean g;
    public f h;
    private Context k;
    private Context l;
    private ListItemObject m;
    private a n;
    private VideoTextureView o;
    private BudejieApplication p;
    private boolean q;
    private b r;
    private f$e s;
    private com.budejie.www.adapter.e.a t;
    private SharedPreferences u;
    private int v;
    private long w;
    private NativeAdRef x;
    private a y;
    private VideoTextureView z;

    public class a implements Cloneable {
        public int a;
        public String b;
        public RelativeLayout c;
        final /* synthetic */ k d;

        public a(k kVar, int i, RelativeLayout relativeLayout) {
            this.d = kVar;
            this.a = i;
            this.c = relativeLayout;
        }

        protected Object clone() throws CloneNotSupportedException {
            return super.clone();
        }
    }

    public NativeAdRef a() {
        return this.x;
    }

    public boolean b() {
        return this.c || this.g;
    }

    public SeekBar c() {
        if (this.b != null) {
            return this.b.getSeekBar();
        }
        return null;
    }

    public a d() {
        return this.n;
    }

    public void a(boolean z) {
        this.q = z;
    }

    public VideoTextureView e() {
        return this.o;
    }

    public View f() {
        return this.n != null ? this.n.c : null;
    }

    private k(Context context) {
        this.k = context;
        this.p = (BudejieApplication) context.getApplicationContext();
        this.u = context.getSharedPreferences("weiboprefer", 0);
    }

    public static synchronized k a(Context context) {
        k kVar;
        synchronized (k.class) {
            if (context instanceof AutoPlayVideoListActivity) {
                if (j == null) {
                    j = new k(context);
                }
                if (context != null) {
                    j.l = context;
                }
                kVar = j;
            } else {
                if (i == null) {
                    i = new k(context);
                }
                if (context != null) {
                    i.l = context;
                }
                kVar = i;
            }
        }
        return kVar;
    }

    public void a(ListItemObject listItemObject, a aVar, b bVar) {
        a(0, listItemObject, aVar, bVar, null, null, 0);
    }

    public void a(ListItemObject listItemObject, a aVar, b bVar, int i) {
        a(0, listItemObject, aVar, bVar, null, null, i);
    }

    @SuppressLint({"NewApi"})
    public void a(int i, ListItemObject listItemObject, a aVar, b bVar, f$e f_e, com.budejie.www.adapter.e.a aVar2, int i2) {
        this.r = bVar;
        this.s = f_e;
        this.t = aVar2;
        this.k = this.l;
        this.m = listItemObject;
        if (!an.a(this.p)) {
            an.a((Activity) this.k, this.k.getString(R.string.nonet), -1).show();
        } else if (TextUtils.isEmpty(listItemObject.getVideouri())) {
            an.a((Activity) this.k, "视频文件已不存在", -1).show();
            MobclickAgent.onEvent(this.k, "视频播放错误", "视频文件已不存在");
        } else {
            this.w = System.currentTimeMillis();
            i.a(a(0, listItemObject.getVideouri()), j.a(listItemObject), j.b(this.k, listItemObject));
            this.q = true;
            g();
            if (!a) {
                Intent intent = new Intent(this.k, FullScreenVideoActivity.class);
                intent.putExtra(FullScreenVideoActivity.a, listItemObject);
                this.k.startActivity(intent);
            } else if (aVar != this.n) {
                EventBus.getDefault().post(DetailAction.PUB_VIDEO_CLOSE);
                h();
                this.c = true;
                d(this.k);
                if (!(this.f == null || this.t == null)) {
                    this.f.a(this.t, i);
                }
                if (this.f == null || !this.f.getParentView().getKeyBoardState()) {
                    this.n = aVar;
                    a(listItemObject, i2);
                    this.o = new VideoTextureView(this.k);
                    LayoutParams layoutParams = new RelativeLayout.LayoutParams(-1, -1);
                    layoutParams.addRule(13);
                    ViewGroup viewGroup = this.f == null ? this.n.c instanceof CurtainVideoContainerLayout ? (CurtainVideoContainerLayout) this.n.c : null : this.f.b;
                    viewGroup.addView(this.o, layoutParams);
                    this.b = b(listItemObject, aVar, i2);
                    this.o.setMircroMediaController(this.b);
                    this.o.setOnErrorListener(new k$1(this, listItemObject, i, aVar, bVar, f_e, i2));
                    String videouri = listItemObject.getVideouri();
                    if (!TextUtils.isEmpty(videouri) && !videouri.contains("spriteapp.cn") && videouri.startsWith("http://") && com.lt.a.a(this.k).f() && com.lt.a.a(this.k).e()) {
                        videouri = listItemObject.getVideouriBackup();
                    }
                    if (!TextUtils.isEmpty(videouri)) {
                        videouri = com.lt.a.a(this.k).a(videouri);
                    }
                    aa.b("test", "videoPath=" + videouri);
                    this.o.setVideoPath(videouri);
                    this.n.b = listItemObject.getVideouri();
                    a(viewGroup);
                    return;
                }
                ao.a(this.e);
                this.f.getParentView().b();
            }
        }
    }

    private String a(int i, String str) {
        String str2 = "";
        switch (i) {
            case 0:
                str2 = this.k.getString(R.string.track_event_play_video);
                break;
            case 1:
                str2 = this.k.getString(R.string.track_event_video_start_play);
                break;
            case 2:
                str2 = this.k.getString(R.string.track_event_video_play_failed);
                break;
        }
        if (str.contains("spriteapp.cn")) {
            return str2 + this.k.getString(R.string.track_event_video_sprite);
        }
        return str2 + this.k.getString(R.string.track_event_video_youku);
    }

    private void a(ViewGroup viewGroup) {
        new NativeAd((Activity) this.k).loadAd(AdConfig.pause, new k$2(this));
    }

    private f b(ListItemObject listItemObject, a aVar, int i) {
        f fVar = new f((Activity) this.k, listItemObject, i);
        this.d = listItemObject.getVideouri();
        fVar.setTopContext((Activity) this.k);
        fVar.setDoubleClickCallback(this.r);
        fVar.setVideoSpecialOPerateCallback(this.s);
        fVar.setWid(listItemObject.getWid());
        fVar.setStartPlayAndPlayScheduleListener(new k$3(this, fVar, i, listItemObject));
        return fVar;
    }

    public void g() {
        com.budejie.www.service.MediaPlayerServer.a f = this.p.f();
        if (f != null && this.q) {
            f.d();
            this.p.a(Status.end);
        }
    }

    public void h() {
        b(false);
    }

    public void b(boolean z) {
        this.v = 0;
        if (this.c) {
            if (this.f != null) {
                if (this.f.n || this.f.m) {
                    this.f.m();
                    return;
                }
                this.f.a(z);
            }
            if (this.n != null) {
                this.n.b = null;
                this.n.c.removeAllViews();
                this.n = null;
            }
            if (this.b != null) {
                this.b.z();
                this.b = null;
            }
            if (this.o != null) {
                this.o.g();
                this.o = null;
            }
            this.c = false;
        }
    }

    public void i() {
        if (this.g) {
            if (this.y != null) {
                this.y.b = null;
                this.y.c.removeAllViews();
                this.y = null;
            }
            if (this.h != null) {
                this.h.z();
                this.h = null;
            }
            if (this.z != null) {
                this.z.g();
                this.z = null;
            }
            this.g = false;
        }
    }

    public boolean j() {
        if (this.o == null || !this.o.c()) {
            return false;
        }
        return true;
    }

    public void k() {
        if (this.o != null && this.o.c()) {
            this.b.a(false);
        }
    }

    public void l() {
        if (this.o != null && this.o.c()) {
            this.b.s();
            this.f.o();
        }
    }

    public void m() {
        if (this.o != null) {
            this.b.u();
        }
    }

    public void n() {
        if (this.o != null && this.o.c() && this.o.getCurrentPosition() > 100) {
            this.b.v();
        }
    }

    public void o() {
        c(false);
    }

    public void c(boolean z) {
        if (c(this.l) && this.o != null) {
            if (!this.o.d() || (this.f != null && this.f.o && !this.f.n)) {
                h();
            } else if (this.o.d()) {
                if (!this.o.c()) {
                    this.b.w();
                } else if (z) {
                    l();
                } else {
                    this.b.a(false);
                }
                this.v = this.o.getCurrentPosition();
            }
        }
        i();
    }

    public void p() {
        if (c(this.l)) {
            i();
            h();
        }
    }

    public static void a(Context context, String str) {
        k a = a(context);
        if (a.c(context)) {
            if (a.c && !a.d().b.equals(str)) {
                a.h();
            }
            if (a.g) {
                a.i();
            }
        }
    }

    private boolean c(Context context) {
        if (context instanceof Activity) {
            Activity activity = (Activity) context;
            if (this.e != null && (activity == this.e || activity.getParent() == this.e)) {
                return true;
            }
        }
        return false;
    }

    public void q() {
        if (this.o != null && !this.o.c()) {
            this.b.a(false);
        }
    }

    public void a(int i, int i2, int i3) {
        if ((this.g || this.y != null) && (this.y.a + i3 < i || this.y.a + i3 > i2)) {
            i();
        }
        if (!w() && this.n != null) {
            s();
            if (this.f != null && !this.f.getSendBarrageEditTextFocusState()) {
                if (this.n.a + i3 >= i && this.n.a + i3 <= i2) {
                    return;
                }
                if (this.f == null || !this.f.n) {
                    h();
                }
            }
        }
    }

    private boolean w() {
        if (this.k == this.l || (((this.k instanceof HomeGroup) && (this.l instanceof PostsActivity)) || ((this.k instanceof PostsActivity) && (this.l instanceof HomeGroup)))) {
            return false;
        }
        return true;
    }

    public void b(int i, int i2, int i3) {
        if (this.k == this.l && this.n != null) {
            s();
            aa.b("PlayerHelper", "mVideoLayoutGroupCell.mPosition=" + this.n.a);
            aa.b("PlayerHelper", "first=" + i);
            aa.b("PlayerHelper", "last=" + i2);
            aa.b("PlayerHelper", "headcount=" + i3);
            if (this.n.a + i3 < i || this.n.a + i3 > i2) {
                if (this.f == null) {
                    k();
                } else if (!this.f.o) {
                    this.f.i();
                    this.b.d();
                }
            } else if (this.f != null) {
                this.f.k();
                this.b.e();
            }
        }
    }

    public void r() {
        if (this.b != null) {
            this.b.a(true);
            t();
        }
    }

    private void d(Context context) {
        if (context instanceof Activity) {
            Activity activity = (Activity) context;
            boolean z = false;
            if (activity.getParent() != null) {
                z = a(activity.getParent());
            }
            if (!z) {
                a(activity);
                return;
            }
            return;
        }
        this.f = null;
    }

    private boolean a(Activity activity) {
        if (this.e == activity && !(activity instanceof PostDetailActivity)) {
            return true;
        }
        this.f = null;
        this.e = activity;
        FloatVideoRootLayout floatVideoRootLayout;
        FloatVideoLayout floatVideoLayout;
        if (activity instanceof PostDetailActivity) {
            Fragment a = ((PostDetailActivity) activity).a();
            if (!(a == null || a.getView() == null)) {
                floatVideoRootLayout = (FloatVideoRootLayout) a.getView().findViewById(R.id.curtain_root_layout);
                if (floatVideoRootLayout == null) {
                    return false;
                }
                floatVideoRootLayout.a();
                floatVideoLayout = (FloatVideoLayout) a.getView().findViewById(R.id.curtain_layout);
                if (floatVideoLayout == null) {
                    return false;
                }
                floatVideoLayout.a(floatVideoRootLayout);
                this.f = floatVideoLayout;
            }
        } else {
            floatVideoRootLayout = (FloatVideoRootLayout) activity.findViewById(R.id.curtain_root_layout);
            if (floatVideoRootLayout == null) {
                return false;
            }
            floatVideoRootLayout.a();
            floatVideoLayout = (FloatVideoLayout) activity.findViewById(R.id.curtain_layout);
            if (floatVideoLayout == null) {
                return false;
            }
            floatVideoLayout.a(floatVideoRootLayout);
            this.f = floatVideoLayout;
        }
        return true;
    }

    public FloatVideoLayout b(Context context) {
        if (c(context)) {
            return this.f;
        }
        return null;
    }

    public void s() {
        d(false);
    }

    public void d(boolean z) {
        if (this.f != null && this.f.isShown()) {
            if (z) {
                a(this.e).k();
            }
            if (!this.f.getSendBarrageEditTextFocusState()) {
                this.f.h();
            }
        }
    }

    private void a(ListItemObject listItemObject, int i) {
        if (this.f != null) {
            this.f.a(listItemObject, i);
        }
    }

    public void t() {
        if (this.f != null) {
            this.f.g();
        }
    }

    private void e(boolean z) {
        if (this.f == null) {
        }
    }

    public void a(ListItemObject listItemObject, a aVar, int i) {
        if (!a) {
            return;
        }
        if ((this.f == null || !this.f.n) && an.a(this.p) && !TextUtils.isEmpty(listItemObject.getVideouri())) {
            i();
            this.g = true;
            this.z = new VideoTextureView(this.k);
            this.z.f();
            LayoutParams layoutParams = new RelativeLayout.LayoutParams(-1, -1);
            layoutParams.addRule(13);
            this.y = aVar;
            aVar.c.addView(this.z, layoutParams);
            this.h = c(listItemObject, aVar, i);
            this.z.setMircroMediaController(this.h);
            this.z.setVideoPath(an.o(this.k, listItemObject.getVideouri()));
        }
    }

    private f c(ListItemObject listItemObject, a aVar, int i) {
        f fVar = new f((Activity) this.k, listItemObject, i);
        fVar.setTopContext((Activity) this.k);
        fVar.setWid(listItemObject.getWid());
        fVar.setStartPlayAndPlayScheduleListener(new k$4(this, listItemObject, fVar));
        return fVar;
    }

    public void u() {
        if (this.g && this.h != null) {
            this.h.i();
        }
    }

    public boolean v() {
        return this.f != null && this.f.n;
    }
}
