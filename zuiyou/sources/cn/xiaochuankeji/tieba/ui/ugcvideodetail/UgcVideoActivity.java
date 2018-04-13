package cn.xiaochuankeji.tieba.ui.ugcvideodetail;

import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.media.AudioManager;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.support.v7.widget.LinearLayoutManager;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.FrameLayout.LayoutParams;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import cn.xiaochuan.base.BaseApplication;
import cn.xiaochuankeji.aop.permission.PermissionItem;
import cn.xiaochuankeji.tieba.R;
import cn.xiaochuankeji.tieba.background.AppController;
import cn.xiaochuankeji.tieba.background.beans.GrayConfigBean;
import cn.xiaochuankeji.tieba.background.d.f;
import cn.xiaochuankeji.tieba.background.danmaku.DanmakuItem;
import cn.xiaochuankeji.tieba.background.data.post.Moment;
import cn.xiaochuankeji.tieba.background.net.XCError;
import cn.xiaochuankeji.tieba.background.utils.g;
import cn.xiaochuankeji.tieba.background.utils.share.UgcVideoShareStruct;
import cn.xiaochuankeji.tieba.json.EmptyJson;
import cn.xiaochuankeji.tieba.json.MemberInfoBean;
import cn.xiaochuankeji.tieba.json.UgcVideoInfoBean;
import cn.xiaochuankeji.tieba.json.UgcVideoShareJson;
import cn.xiaochuankeji.tieba.json.imgjson.ServerImgJson;
import cn.xiaochuankeji.tieba.receiver.AudioStateReceiver;
import cn.xiaochuankeji.tieba.ui.base.h;
import cn.xiaochuankeji.tieba.ui.mediabrowse.NetworkConnectivityReceiver;
import cn.xiaochuankeji.tieba.ui.mediabrowse.component.RoundProgressBar;
import cn.xiaochuankeji.tieba.ui.member.MemberDetailActivity;
import cn.xiaochuankeji.tieba.ui.post.LikedUsersActivity;
import cn.xiaochuankeji.tieba.ui.ugcvideodetail.a.c;
import cn.xiaochuankeji.tieba.ui.ugcvideodetail.danmaku.a.d;
import cn.xiaochuankeji.tieba.ui.utils.e;
import cn.xiaochuankeji.tieba.ui.videomaker.VideoRecordActivity;
import cn.xiaochuankeji.tieba.ui.widget.SDBottomSheet;
import cn.xiaochuankeji.tieba.ui.widget.image.WebImageView;
import com.alibaba.fastjson.JSON;
import com.danikula.videocache.q;
import com.facebook.imagepipeline.request.ImageRequest;
import com.marshalchen.ultimaterecyclerview.UltimateRecyclerView;
import com.marshalchen.ultimaterecyclerview.layoutmanagers.ScrollSmoothLineaerLayoutManager;
import com.tencent.wcdb.database.SQLiteDatabase;
import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import org.greenrobot.eventbus.ThreadMode;
import org.greenrobot.eventbus.l;
import org.json.JSONObject;
import rx.j;

public class UgcVideoActivity extends h implements OnPageChangeListener, OnClickListener, d {
    private static final org.aspectj.lang.a.a ac = null;
    private static final int f = e.a(6.0f);
    private TextView A;
    private ImageView B;
    private e C;
    private a D;
    private cn.xiaochuankeji.tieba.ui.ugcvideodetail.a.a E;
    private b F;
    private ArrayList<UgcVideoInfoBean> G = new ArrayList();
    private cn.xiaochuankeji.tieba.api.ugcvideo.a H = new cn.xiaochuankeji.tieba.api.ugcvideo.a();
    private UgcVideoInfoBean I;
    private long J;
    private a K;
    private c L;
    private View M;
    private CustomVolumeView N;
    private AudioStateReceiver O;
    private cn.xiaochuankeji.tieba.ui.ugcvideodetail.danmaku.b P;
    private String Q;
    private long R = 0;
    private int S = 0;
    private boolean T = false;
    private boolean U = true;
    private UgcVideoInfoBean V;
    private Moment W;
    private boolean X = false;
    private long Y;
    private boolean Z;
    private int aa;
    private rx.d<EmptyJson> ab;
    NetworkConnectivityReceiver d;
    cn.xiaochuankeji.tieba.ui.mediabrowse.NetworkConnectivityReceiver.a e;
    private ViewPager g;
    private UltimateRecyclerView h;
    private ImageView i;
    private LinearLayout j;
    private TextView k;
    private View l;
    private WebImageView m;
    private View n;
    private View o;
    private View p;
    private View q;
    private RelativeLayout r;
    private RoundProgressBar s;
    private TextView t;
    private h u;
    private ImageView v;
    private ImageView w;
    private TextView x;
    private LinearLayout y;
    private d z;

    class a extends FragmentPagerAdapter {
        final /* synthetic */ UgcVideoActivity a;

        public a(UgcVideoActivity ugcVideoActivity, FragmentManager fragmentManager) {
            this.a = ugcVideoActivity;
            super(fragmentManager);
        }

        public Fragment getItem(int i) {
            UgcVideoInfoBean ugcVideoInfoBean = (UgcVideoInfoBean) this.a.G.get(i);
            if (this.a.E.a().g == i) {
                return g.a(ugcVideoInfoBean, this.a.Q);
            }
            return g.a(ugcVideoInfoBean, this.a.Q + "-ugcvideodetail");
        }

        public int getCount() {
            return this.a.G.size();
        }

        public int getItemPosition(Object obj) {
            UgcVideoInfoBean ugcVideoInfoBean = (UgcVideoInfoBean) ((Fragment) obj).getArguments().getSerializable("s_key_video_info_bean");
            for (int i = 0; i < this.a.G.size(); i++) {
                if (((UgcVideoInfoBean) this.a.G.get(i)).id == ugcVideoInfoBean.id) {
                    return i;
                }
            }
            return -2;
        }

        public long getItemId(int i) {
            return ((UgcVideoInfoBean) this.a.G.get(i)).id;
        }
    }

    public static class b {
        public boolean a;
        public UgcVideoInfoBean b;
        public Moment c;
        public long d;
    }

    private static void M() {
        org.aspectj.a.b.b bVar = new org.aspectj.a.b.b("UgcVideoActivity.java", UgcVideoActivity.class);
        ac = bVar.a("method-execution", bVar.a("4", "onCreate", "cn.xiaochuankeji.tieba.ui.ugcvideodetail.UgcVideoActivity", "android.os.Bundle", "savedInstanceState", "", "void"), 287);
    }

    public UgcVideoActivity() {
        boolean z = true;
        if (AppController.instance().allowCellular()) {
            z = false;
        }
        this.Z = z;
        this.aa = -1;
        this.d = new NetworkConnectivityReceiver();
        this.e = new cn.xiaochuankeji.tieba.ui.mediabrowse.NetworkConnectivityReceiver.a(this) {
            final /* synthetic */ UgcVideoActivity a;

            {
                this.a = r1;
            }

            public void a(boolean z, int i) {
                this.a.aa = i;
                if (this.a.aa == 1) {
                    AppController.instance().setAllowCellular(false);
                }
            }
        };
    }

    static {
        M();
    }

    public static void a(Context context, long j, String str) {
        a(context, j, str, 0, 0);
    }

    public static void a(Context context, long j, String str, long j2, int i) {
        Parcelable eVar;
        Intent intent = new Intent(context, UgcVideoActivity.class);
        if (0 == j2) {
            eVar = new cn.xiaochuankeji.tieba.ui.ugcvideodetail.a.e(j);
        } else {
            eVar = new cn.xiaochuankeji.tieba.ui.ugcvideodetail.a.e(j, j2, i);
        }
        intent.putExtra("sKeyDataHandler", eVar);
        intent.putExtra("key_from", str);
        intent.setFlags(SQLiteDatabase.CREATE_IF_NECESSARY);
        context.startActivity(intent);
    }

    public static void b(Context context, long j, String str) {
        b(context, j, str, 0, 0);
    }

    public static void b(Context context, long j, String str, long j2, int i) {
        Parcelable cVar;
        Intent intent = new Intent(context, UgcVideoActivity.class);
        if (0 == j2) {
            cVar = new c(j);
        } else {
            cVar = new c(j, j2, i);
        }
        intent.putExtra("sKeyDataHandler", cVar);
        intent.putExtra("key_from", str);
        intent.setFlags(SQLiteDatabase.CREATE_IF_NECESSARY);
        context.startActivity(intent);
    }

    public static void a(Context context, UgcVideoInfoBean ugcVideoInfoBean, boolean z, String str, Moment moment) {
        Parcelable dVar;
        Intent intent = new Intent(context, UgcVideoActivity.class);
        if (z) {
            dVar = new cn.xiaochuankeji.tieba.ui.ugcvideodetail.a.d(ugcVideoInfoBean, moment);
        } else {
            dVar = new cn.xiaochuankeji.tieba.ui.ugcvideodetail.a.b(ugcVideoInfoBean, moment);
        }
        intent.putExtra("sKeyDataHandler", dVar);
        intent.putExtra("key_from", str);
        intent.setFlags(SQLiteDatabase.CREATE_IF_NECESSARY);
        context.startActivity(intent);
    }

    protected boolean a(Bundle bundle) {
        this.E = (cn.xiaochuankeji.tieba.ui.ugcvideodetail.a.a) getIntent().getParcelableExtra("sKeyDataHandler");
        this.Q = getIntent().getStringExtra("key_from");
        this.F = new b(this, new cn.xiaochuankeji.tieba.ui.ugcvideodetail.b.a(this) {
            final /* synthetic */ UgcVideoActivity a;

            {
                this.a = r1;
            }

            public void a(boolean z, String str) {
                this.a.r.setVisibility(8);
                g.a(str);
            }

            public void a(int i, int i2) {
                int i3 = (int) ((((float) i2) * 100.0f) / ((float) i));
                this.a.s.setMax(100);
                this.a.s.setProgress(i3);
                this.a.t.setText("下载中" + i3 + "%");
            }

            public void a() {
                this.a.r.setVisibility(0);
            }
        });
        this.U = cn.xiaochuankeji.tieba.d.g.a().c.equals("wifi");
        return true;
    }

    static final void a(UgcVideoActivity ugcVideoActivity, Bundle bundle, org.aspectj.lang.a aVar) {
        ugcVideoActivity.requestWindowFeature(1);
        ugcVideoActivity.getWindow().setFlags(1024, 1024);
        ugcVideoActivity.getWindow().addFlags(128);
        IntentFilter intentFilter = new IntentFilter("android.intent.action.SCREEN_OFF");
        ugcVideoActivity.O = new AudioStateReceiver();
        ugcVideoActivity.registerReceiver(ugcVideoActivity.O, intentFilter);
        ugcVideoActivity.registerReceiver(ugcVideoActivity.O, new IntentFilter("android.intent.action.SCREEN_ON"));
        ugcVideoActivity.L();
        super.onCreate(bundle);
        ugcVideoActivity.E.a(new cn.xiaochuankeji.tieba.ui.ugcvideodetail.a.a.a(ugcVideoActivity) {
            final /* synthetic */ UgcVideoActivity a;

            {
                this.a = r1;
            }

            public void a(UgcVideoInfoBean ugcVideoInfoBean, Moment moment) {
                this.a.G.clear();
                this.a.G.add(ugcVideoInfoBean);
                this.a.D.notifyDataSetChanged();
                this.a.a(ugcVideoInfoBean);
                this.a.b(ugcVideoInfoBean);
                this.a.V = ugcVideoInfoBean;
                this.a.W = moment;
                this.a.X = true;
                this.a.Y = this.a.V.id;
            }

            public void a() {
                boolean z = true;
                cn.xiaochuankeji.tieba.ui.ugcvideodetail.a.a.b a = this.a.E.a();
                final int i = a.g;
                this.a.G.clear();
                if (a.b != null) {
                    this.a.G.add(a.b);
                }
                this.a.G.addAll(a.c);
                this.a.D.notifyDataSetChanged();
                this.a.g.setCurrentItem(i, false);
                if (a.c.size() > 0) {
                    this.a.c(false);
                    this.a.C.b(i);
                    this.a.C.a(a.f);
                    this.a.C.notifyDataSetChanged();
                    this.a.h.post(new Runnable(this) {
                        final /* synthetic */ AnonymousClass11 b;

                        public void run() {
                            this.b.a.a(i, true);
                        }
                    });
                    if (1 == a.d) {
                        this.a.h.f();
                    }
                } else {
                    this.a.c(true);
                }
                UgcVideoActivity ugcVideoActivity = this.a;
                if (a.c.size() <= 0) {
                    z = false;
                }
                ugcVideoActivity.T = z;
                this.a.a(e.a(this.a.T ? 68.0f : 46.0f));
                UgcVideoInfoBean j = this.a.j();
                if (j != null) {
                    this.a.a(j);
                    this.a.b(j);
                    if (this.a.X) {
                        Iterator it = this.a.G.iterator();
                        while (it.hasNext()) {
                            j = (UgcVideoInfoBean) it.next();
                            if (this.a.W != null) {
                                for (int i2 = 0; i2 < this.a.W.ugcVideos.size(); i2++) {
                                    if (((UgcVideoInfoBean) this.a.W.ugcVideos.get(i2)).id == j.id) {
                                        this.a.W.ugcVideos.remove(i2);
                                        this.a.W.ugcVideos.add(i2, j);
                                        break;
                                    }
                                }
                            } else if (this.a.V.id == j.id) {
                                this.a.V = j;
                                if (this.a.V.pid != 0) {
                                    this.a.V.mainPost = a.b;
                                    return;
                                }
                                return;
                            }
                        }
                    }
                }
            }

            public void a(long j, int i) {
                this.a.R = j;
                this.a.S = i;
                this.a.onClick(this.a.A);
            }

            public void b() {
                this.a.c(false);
                cn.xiaochuankeji.tieba.ui.ugcvideodetail.a.a.b a = this.a.E.a();
                this.a.G.clear();
                if (a.b != null) {
                    this.a.G.add(a.b);
                }
                this.a.G.addAll(a.c);
                this.a.D.notifyDataSetChanged();
                this.a.g.setCurrentItem(1, false);
                this.a.C.b(1);
                this.a.C.a(a.f);
                this.a.C.notifyDataSetChanged();
                this.a.h.g.scrollToPosition(0);
                UgcVideoInfoBean j = this.a.j();
                if (j != null) {
                    this.a.a(j);
                    this.a.b(j);
                    if (a.b != null) {
                        j = (UgcVideoInfoBean) a.c.get(0);
                        a.b.subImgs = a.b.subImgs != null ? a.b.subImgs : new ArrayList();
                        a.b.subImgs.add(j.img);
                        j = a.b;
                        j.reviews++;
                    }
                }
            }

            public void a(boolean z, int i, UgcVideoInfoBean ugcVideoInfoBean) {
                Iterator it;
                cn.xiaochuankeji.tieba.ui.ugcvideodetail.a.a.b a = this.a.E.a();
                this.a.G.clear();
                if (!z) {
                    this.a.G.add(a.b);
                    if (a.c.size() > 0) {
                        this.a.G.addAll(a.c);
                    }
                    this.a.D.notifyDataSetChanged();
                    this.a.g.setCurrentItem(i, false);
                    this.a.C.b(i);
                    this.a.C.a(a.f);
                    this.a.C.notifyDataSetChanged();
                    this.a.h.g.scrollToPosition(0);
                    if (a.c.size() == 0) {
                        this.a.c(true);
                    }
                } else if (a.c.size() > 0) {
                    it = a.c.iterator();
                    while (it.hasNext()) {
                        UgcVideoInfoBean ugcVideoInfoBean2 = (UgcVideoInfoBean) it.next();
                        ugcVideoInfoBean2.mainPost = null;
                        ugcVideoInfoBean2.pid = 0;
                    }
                    this.a.G.addAll(a.c);
                    this.a.D.notifyDataSetChanged();
                    this.a.g.setCurrentItem(0, false);
                    this.a.C.b(0);
                    this.a.C.a(a.f);
                    this.a.C.notifyDataSetChanged();
                    this.a.h.g.scrollToPosition(0);
                }
                if (!z) {
                    List<ServerImgJson> list = a.b.subImgs;
                    if (list != null && list.size() > 0) {
                        for (ServerImgJson serverImgJson : list) {
                            if (serverImgJson.id == ugcVideoInfoBean.img.id) {
                                list.remove(serverImgJson);
                                ugcVideoInfoBean2 = a.b;
                                ugcVideoInfoBean2.reviews--;
                                break;
                            }
                        }
                    }
                }
                if (this.a.X) {
                    if (this.a.W != null) {
                        for (UgcVideoInfoBean ugcVideoInfoBean22 : this.a.W.ugcVideos) {
                            if (ugcVideoInfoBean22.id == ugcVideoInfoBean.id) {
                                this.a.W.ugcVideos.remove(ugcVideoInfoBean22);
                                break;
                            }
                        }
                    } else if (this.a.V != null && this.a.V.id == ugcVideoInfoBean.id) {
                        this.a.V = null;
                    }
                }
                if (z && a.c.size() == 0) {
                    this.a.z();
                    this.a.finish();
                    return;
                }
                ugcVideoInfoBean22 = this.a.j();
                if (ugcVideoInfoBean22 != null) {
                    this.a.a(ugcVideoInfoBean22);
                    this.a.b(ugcVideoInfoBean22);
                }
            }

            public void c() {
                cn.xiaochuankeji.tieba.ui.ugcvideodetail.a.a.b a = this.a.E.a();
                this.a.G.clear();
                this.a.G.add(a.b);
                this.a.G.addAll(a.c);
                this.a.D.notifyDataSetChanged();
                if (a.d == 0 && this.a.h.g()) {
                    this.a.h.h();
                }
                this.a.C.notifyDataSetChanged();
            }
        });
        ugcVideoActivity.E.b();
    }

    protected void onCreate(Bundle bundle) {
        org.aspectj.lang.a a = org.aspectj.a.b.b.a(ac, this, this, bundle);
        cn.xiaochuankeji.aop.permission.c.c().a(new f(new Object[]{this, bundle, a}).linkClosureAndJoinPoint(69648));
    }

    private void z() {
        if (this.X) {
            b bVar = new b();
            UgcVideoInfoBean ugcVideoInfoBean;
            if (this.W != null) {
                bVar.a = true;
                bVar.c = this.W;
                if (this.W.ugcVideos.size() > 0 && ((UgcVideoInfoBean) this.W.ugcVideos.get(0)).id == this.W.id) {
                    ugcVideoInfoBean = (UgcVideoInfoBean) this.W.ugcVideos.get(0);
                    ugcVideoInfoBean.plays++;
                }
            } else {
                bVar.d = this.Y;
                bVar.b = this.V;
                if (bVar.b != null) {
                    ugcVideoInfoBean = bVar.b;
                    ugcVideoInfoBean.plays++;
                }
            }
            org.greenrobot.eventbus.c.a().d(bVar);
        }
    }

    private void a(int i) {
        if (cn.xiaochuankeji.tieba.background.a.a().getBoolean("key_show_review_guide", true)) {
            this.L = new c(this);
            this.L.setBottomMargin(i);
            ((ViewGroup) this.b).addView(this.L);
            this.L.setOnClickListener(new OnClickListener(this) {
                final /* synthetic */ UgcVideoActivity a;

                {
                    this.a = r1;
                }

                public void onClick(View view) {
                    this.a.K();
                }
            });
        }
    }

    private void A() {
        SharedPreferences a = cn.xiaochuankeji.tieba.background.a.a();
        if (a.getBoolean("key_first_come_in", true)) {
            a.edit().putBoolean("key_first_come_in", false).apply();
            this.y.postDelayed(new Runnable(this) {
                final /* synthetic */ UgcVideoActivity a;

                {
                    this.a = r1;
                }

                public void run() {
                    this.a.K.b();
                }
            }, 700);
            this.y.postDelayed(new Runnable(this) {
                final /* synthetic */ UgcVideoActivity a;

                {
                    this.a = r1;
                }

                public void run() {
                    this.a.K.c();
                }
            }, 1500);
        }
    }

    public void c(boolean z) {
        LayoutParams layoutParams;
        if (z) {
            this.h.setVisibility(8);
            this.z.c();
            layoutParams = (LayoutParams) this.y.getLayoutParams();
            layoutParams.topMargin = e.a(83.0f);
            this.y.setLayoutParams(layoutParams);
            this.K.a(true);
        } else {
            this.h.setVisibility(0);
            this.z.d();
            layoutParams = (LayoutParams) this.y.getLayoutParams();
            layoutParams.topMargin = e.a(61.0f);
            this.y.setLayoutParams(layoutParams);
            this.K.a(false);
        }
        this.K.d();
    }

    public UgcVideoInfoBean j() {
        if (this.G.size() == 0) {
            return null;
        }
        return (UgcVideoInfoBean) this.G.get(this.g.getCurrentItem());
    }

    private void a(UgcVideoInfoBean ugcVideoInfoBean) {
        if (ugcVideoInfoBean != null) {
            MemberInfoBean memberInfoBean = ugcVideoInfoBean.member;
            this.m.setWebImage(cn.xiaochuankeji.tieba.background.f.b.a(memberInfoBean.getId(), memberInfoBean.getAvatarId()));
            this.k.setText(memberInfoBean.getNickName());
            int i = memberInfoBean.getId() == cn.xiaochuankeji.tieba.background.a.g().c() ? 1 : 0;
            if (ugcVideoInfoBean.member.getFollowStatus() == 0 && i == 0) {
                this.l.setVisibility(0);
            } else {
                this.l.setVisibility(8);
            }
            if (memberInfoBean.getId() == cn.xiaochuankeji.tieba.background.a.g().c()) {
                this.p.setVisibility(0);
                this.o.setVisibility(8);
                return;
            }
            this.p.setVisibility(8);
            this.o.setVisibility(0);
        }
    }

    private void b(UgcVideoInfoBean ugcVideoInfoBean) {
        if (ugcVideoInfoBean != null) {
            this.x.setText(cn.xiaochuankeji.tieba.ui.utils.d.b(ugcVideoInfoBean.likeCount));
            if (ugcVideoInfoBean.liked == 1) {
                this.w.setSelected(true);
                this.v.setSelected(false);
                this.x.setTextColor(Color.parseColor("#56c6fa"));
            } else if (ugcVideoInfoBean.liked == -1) {
                this.w.setSelected(false);
                this.v.setSelected(true);
                this.x.setTextColor(Color.parseColor("#e84766"));
            } else {
                this.w.setSelected(false);
                this.v.setSelected(false);
                this.x.setTextColor(Color.parseColor("#ffffff"));
            }
        }
    }

    protected int a() {
        return R.layout.activity_ugcvideo;
    }

    protected void c() {
        super.c();
        this.g = (ViewPager) findViewById(R.id.viewPager);
        this.h = (UltimateRecyclerView) findViewById(R.id.ultimateRecyclerView);
        this.i = (ImageView) findViewById(R.id.ivCreateVideo);
        this.j = (LinearLayout) findViewById(R.id.llMemberInfo);
        this.k = (TextView) findViewById(R.id.tvMemberName);
        this.m = (WebImageView) findViewById(R.id.wivMemberAvatar);
        this.l = findViewById(R.id.vFocus);
        this.n = findViewById(R.id.ivShare);
        this.o = findViewById(R.id.ivReport);
        this.p = findViewById(R.id.ivDelete);
        this.q = findViewById(R.id.ivBack);
        this.r = (RelativeLayout) findViewById(R.id.rl_progress);
        this.s = (RoundProgressBar) findViewById(R.id.roundPBar);
        this.t = (TextView) findViewById(R.id.tv_progress);
        this.v = (ImageView) findViewById(R.id.ivHate);
        this.w = (ImageView) findViewById(R.id.ivLike);
        this.x = (TextView) findViewById(R.id.tvLikeCount);
        this.y = (LinearLayout) findViewById(R.id.llBottomContainer);
        this.B = (ImageView) findViewById(R.id.ivDanmukuSwitch);
        this.A = (TextView) findViewById(R.id.tvWriteDanmuku);
        this.M = findViewById(R.id.vPause);
        this.N = (CustomVolumeView) findViewById(R.id.pVolumeBar);
    }

    protected void i_() {
        this.d.a(this.e);
        this.B.setSelected(F());
        this.D = new a(this, getSupportFragmentManager());
        this.g.setAdapter(this.D);
        B();
        C();
        D();
        E();
        this.P = new cn.xiaochuankeji.tieba.ui.ugcvideodetail.danmaku.b();
        a(this.P);
    }

    private void B() {
        this.h.setHasFixedSize(false);
        this.h.setLayoutManager(new ScrollSmoothLineaerLayoutManager(this, 0, false, 300));
        this.h.a(new i(f));
        this.C = new e(this, this.G, false);
        this.h.g.setFocusable(false);
        this.h.setOnLoadMoreListener(new com.marshalchen.ultimaterecyclerview.UltimateRecyclerView.b(this) {
            final /* synthetic */ UgcVideoActivity a;

            {
                this.a = r1;
            }

            public void a(int i, int i2) {
                this.a.E.c();
            }
        });
        this.C.a(new cn.xiaochuankeji.tieba.ui.ugcvideodetail.e.a(this) {
            final /* synthetic */ UgcVideoActivity a;

            {
                this.a = r1;
            }

            public void a(View view, int i) {
                if (this.a.K.a()) {
                    this.a.g.setCurrentItem(i);
                } else {
                    this.a.K.b();
                }
            }

            public void a() {
                this.a.E.d();
            }
        });
        this.h.setLoadMoreView(new j(this));
        this.h.a(R.layout.view_ugcvideo_thumb_empty_null, UltimateRecyclerView.a, UltimateRecyclerView.a);
        this.h.setAdapter(this.C);
        this.h.h();
    }

    private void C() {
        ArrayList s = cn.xiaochuankeji.tieba.background.utils.c.a.c().s();
        if (s.size() > 0) {
            this.u = new h(this, s);
        }
    }

    private void D() {
        this.z = new d(this);
        View f_ = this.z.f_();
        f_.setVisibility(8);
        f_.setOnClickListener(new OnClickListener(this) {
            final /* synthetic */ UgcVideoActivity a;

            {
                this.a = r1;
            }

            public void onClick(View view) {
                this.a.I();
            }
        });
        this.y.addView(f_);
    }

    private void E() {
        this.K = new a(this);
        this.K.a(this.y);
    }

    private boolean F() {
        return cn.xiaochuankeji.tieba.background.a.a().getBoolean("key_danmuku_switch_state", true);
    }

    protected void j_() {
        super.j_();
        this.j.setOnClickListener(this);
        this.q.setOnClickListener(this);
        this.l.setOnClickListener(this);
        this.n.setOnClickListener(this);
        this.o.setOnClickListener(this);
        this.p.setOnClickListener(this);
        this.v.setOnClickListener(this);
        this.w.setOnClickListener(this);
        this.x.setOnClickListener(this);
        this.q.setOnClickListener(this);
        this.i.setOnClickListener(this);
        this.A.setOnClickListener(this);
        this.B.setOnClickListener(this);
    }

    private void a(int i, boolean z) {
        if (z) {
            int i2 = i + 1;
            this.h.g.scrollBy(((((i2 - 1) * e.a(57.0f)) + e.a(6.0f)) + (e.a(51.0f) / 2)) - (e.b() / 2), 0);
            return;
        }
        View findViewByPosition = ((LinearLayoutManager) this.h.getLayoutManager()).findViewByPosition(i);
        if (findViewByPosition != null) {
            this.h.g.smoothScrollBy(((findViewByPosition.getWidth() / 2) + findViewByPosition.getLeft()) - (this.h.getWidth() / 2), 0);
        }
    }

    public void onClick(View view) {
        int i = -12;
        UgcVideoInfoBean j;
        switch (view.getId()) {
            case R.id.ivBack:
                finish();
                return;
            case R.id.llMemberInfo:
                j = j();
                if (j != null) {
                    MemberDetailActivity.a(this, j.member.getId(), 1, 0);
                    return;
                }
                return;
            case R.id.vFocus:
                if (cn.xiaochuankeji.tieba.ui.auth.a.a(this, "ugc_video", 10)) {
                    j = j();
                    if (j != null) {
                        new cn.xiaochuankeji.tieba.background.e.b(j.member.getId(), null, null, new cn.xiaochuankeji.tieba.background.net.a.b<JSONObject>(this) {
                            final /* synthetic */ UgcVideoActivity b;

                            public /* synthetic */ void onResponse(Object obj, Object obj2) {
                                a((JSONObject) obj, obj2);
                            }

                            public void a(JSONObject jSONObject, Object obj) {
                                this.b.l.setVisibility(8);
                                j.member.setFollowStatus(1);
                                g.a("已关注");
                            }
                        }, new cn.xiaochuankeji.tieba.background.net.a.a(this) {
                            final /* synthetic */ UgcVideoActivity a;

                            {
                                this.a = r1;
                            }

                            public void onErrorResponse(XCError xCError, Object obj) {
                                g.a(xCError.getMessage());
                            }
                        }).execute();
                        return;
                    }
                    return;
                }
                return;
            case R.id.ivShare:
                H();
                return;
            case R.id.ivReport:
                j = j();
                if (j != null) {
                    this.u.a(j.pid, j.id);
                    return;
                }
                return;
            case R.id.ivDelete:
                G();
                return;
            case R.id.ivCreateVideo:
                I();
                return;
            case R.id.ivDanmukuSwitch:
                Object dVar;
                boolean z = !F();
                this.B.setSelected(z);
                g(z);
                if (z) {
                    dVar = new cn.xiaochuankeji.tieba.background.d.d();
                    dVar.a = true;
                } else {
                    dVar = new cn.xiaochuankeji.tieba.background.d.d();
                    dVar.a = false;
                }
                org.greenrobot.eventbus.c.a().d(dVar);
                return;
            case R.id.tvWriteDanmuku:
                if (this.G.size() != 0) {
                    this.A.post(new Runnable(this) {
                        final /* synthetic */ UgcVideoActivity a;

                        {
                            this.a = r1;
                        }

                        public void run() {
                            f fVar = new f();
                            fVar.a = ((UgcVideoInfoBean) this.a.G.get(this.a.g.getCurrentItem())).id;
                            org.greenrobot.eventbus.c.a().d(fVar);
                        }
                    });
                    return;
                }
                return;
            case R.id.ivHate:
            case R.id.tvLikeCount:
            case R.id.ivLike:
                if (view.getId() != R.id.ivHate) {
                    if (view.getId() == R.id.ivLike) {
                        i = 12;
                    } else if (!this.v.isSelected()) {
                        i = this.w.isSelected() ? 12 : -999;
                    }
                }
                if (cn.xiaochuankeji.tieba.ui.auth.a.a(this, "ugc_video", i)) {
                    a(view);
                    return;
                }
                return;
            default:
                return;
        }
    }

    public boolean onKeyUp(int i, KeyEvent keyEvent) {
        if (i != 25 && i != 24) {
            return super.onKeyUp(i, keyEvent);
        }
        this.N.a();
        return true;
    }

    public boolean onKeyDown(int i, KeyEvent keyEvent) {
        if (i != 25 && i != 24) {
            return super.onKeyDown(i, keyEvent);
        }
        this.N.a(i);
        return true;
    }

    private void g(boolean z) {
        cn.xiaochuankeji.tieba.background.a.a().edit().putBoolean("key_danmuku_switch_state", z).apply();
    }

    private void G() {
        final UgcVideoInfoBean j = j();
        if (j != null) {
            final com.flyco.dialog.c.a aVar = new com.flyco.dialog.c.a(this);
            ((com.flyco.dialog.c.a) ((com.flyco.dialog.c.a) ((com.flyco.dialog.c.a) ((com.flyco.dialog.c.a) aVar.b("您确认要删除该视频")).a("删除视频")).a(1).c(2)).a(new String[]{"取消", "确定"})).show();
            aVar.a(new com.flyco.dialog.a.a[]{new com.flyco.dialog.a.a(this) {
                final /* synthetic */ UgcVideoActivity b;

                public void a() {
                    aVar.dismiss();
                }
            }, new com.flyco.dialog.a.a(this) {
                final /* synthetic */ UgcVideoActivity c;

                public void a() {
                    rx.d d;
                    if (0 == j.pid) {
                        d = this.c.H.d(j.id);
                    } else {
                        d = this.c.H.e(j.id);
                    }
                    d.a(rx.a.b.a.a()).b(new j<EmptyJson>(this) {
                        final /* synthetic */ AnonymousClass6 a;

                        {
                            this.a = r1;
                        }

                        public /* synthetic */ void onNext(Object obj) {
                            a((EmptyJson) obj);
                        }

                        public void onCompleted() {
                        }

                        public void onError(Throwable th) {
                            g.b(th.getMessage());
                        }

                        public void a(EmptyJson emptyJson) {
                            if (emptyJson != null) {
                                g.b("删帖成功");
                                this.a.c.E.b(j);
                            }
                        }
                    });
                    aVar.dismiss();
                }
            }});
        }
    }

    private void H() {
        final UgcVideoInfoBean j = j();
        if (j != null) {
            SDBottomSheet sDBottomSheet = new SDBottomSheet(this, new cn.xiaochuankeji.tieba.ui.widget.SDBottomSheet.b(this) {
                final /* synthetic */ UgcVideoActivity b;

                public void a(int i) {
                    long j = 0;
                    if (i == 4 || i == 2 || i == 3 || i == 5 || i == 1) {
                        long j2;
                        long j3;
                        if (0 == j.pid) {
                            j2 = j.id;
                            j3 = (long) j.img.id;
                        } else {
                            j2 = j.pid;
                            j = j.id;
                            j3 = (long) j.img.id;
                        }
                        this.b.a(j2, j, j3, i);
                    } else if (i == 13) {
                        String str = j.videoInfo.logoUrl;
                        if (TextUtils.isEmpty(str)) {
                            str = j.videoInfo.url;
                        }
                        Map hashMap = new HashMap();
                        hashMap.put("owner", j.pid > 0 ? "ugcvideo_review" : "ugcvideo");
                        hashMap.put("watermark", TextUtils.isEmpty(j.videoInfo.logoUrl) ? "0" : "1");
                        cn.xiaochuankeji.tieba.background.utils.monitor.a.b.a().a("download", "video", (long) j.img.id, j.id, "mediabrowse", hashMap);
                        cn.xiaochuankeji.aop.permission.a.a(this.b).a(new PermissionItem("android.permission.WRITE_EXTERNAL_STORAGE").deniedMessage("下载文件需要使用该权限").settingText("去设置").runIgnorePermission(false).needGotoSetting(true).rationalMessage("下载文件需要使用该权限"), new cn.xiaochuankeji.aop.permission.e(this) {
                            final /* synthetic */ AnonymousClass7 b;

                            public void permissionGranted() {
                                this.b.b.F.a(str);
                            }

                            public void permissionDenied() {
                            }
                        });
                    } else if (i == 18) {
                        cn.xiaochuankeji.tieba.ui.utils.d.a("#最右#分享一条有趣的内容给你，不好看算我输。请戳链接>>" + cn.xiaochuankeji.tieba.background.utils.d.a.b(j.pid, j.id));
                        g.a("复制成功");
                    }
                }
            });
            SDBottomSheet.c cVar = new SDBottomSheet.c(R.drawable.toast_download, "下载", 13);
            ArrayList arrayList = new ArrayList();
            arrayList.add(cVar);
            sDBottomSheet.a(sDBottomSheet.c(), arrayList);
            sDBottomSheet.b();
        }
    }

    private void a(long j, long j2, long j3, int i) {
        rx.d b;
        if (j2 == 0) {
            b = new cn.xiaochuankeji.tieba.api.ugcvideo.a().b(j, J());
        } else {
            b = new cn.xiaochuankeji.tieba.api.ugcvideo.a().c(j2, J());
        }
        final long j4 = j3;
        final long j5 = j2;
        final long j6 = j;
        final int i2 = i;
        b.a(rx.a.b.a.a()).b(new j<UgcVideoShareJson>(this) {
            final /* synthetic */ UgcVideoActivity e;

            public /* synthetic */ void onNext(Object obj) {
                a((UgcVideoShareJson) obj);
            }

            public void onCompleted() {
            }

            public void onError(Throwable th) {
                g.b(th.getMessage());
            }

            public void a(UgcVideoShareJson ugcVideoShareJson) {
                if (ugcVideoShareJson.shareTxt != null) {
                    Object obj = ugcVideoShareJson.shareTxt.title;
                    Object obj2 = ugcVideoShareJson.shareTxt.desp;
                    if (!TextUtils.isEmpty(obj) || !TextUtils.isEmpty(obj2)) {
                        String b;
                        String a = cn.xiaochuankeji.tieba.background.utils.d.a.a("/img/view/id/", j4, "/sz/360");
                        if (0 == j5) {
                            b = cn.xiaochuankeji.tieba.background.utils.d.a.b(j6, 0);
                        } else {
                            b = cn.xiaochuankeji.tieba.background.utils.d.a.b(j6, j5);
                        }
                        String str = null;
                        File a2 = cn.xiaochuankeji.tieba.common.c.a.a(ImageRequest.a(a));
                        if (a2 != null && a2.exists() && a2.isFile()) {
                            str = a2.getAbsolutePath();
                        }
                        cn.xiaochuankeji.tieba.background.utils.share.b.a().a(i2, this.e, new UgcVideoShareStruct(obj, obj2, str, b));
                    }
                }
            }
        });
    }

    private void I() {
        if (cn.xiaochuankeji.tieba.ui.auth.a.a(this, "ugc_video", 3)) {
            long j = 0;
            UgcVideoInfoBean ugcVideoInfoBean = this.E.a().b;
            if (ugcVideoInfoBean != null) {
                j = ugcVideoInfoBean.id;
            }
            String str = null;
            if (!TextUtils.isEmpty(this.Q) && (this.Q.equals("index") || this.Q.equals("index-postdetail") || this.Q.equals("index-ugcvideo"))) {
                str = this.Q + "-ugcvideodetail";
            }
            VideoRecordActivity.a((Activity) this, 1001, j, str);
        }
    }

    private void a(View view) {
        UgcVideoInfoBean j = j();
        if (j != null) {
            if (this.v.isSelected() || this.w.isSelected()) {
                boolean isSelected = this.w.isSelected();
                long j2 = j.id;
                if (j.pid == 0) {
                    LikedUsersActivity.a(this, j2, isSelected, 3, J(), 1002);
                } else {
                    LikedUsersActivity.a(this, j2, isSelected, 4, J(), 1002);
                }
            } else if (this.v == view) {
                a(j, false);
            } else if (this.w == view) {
                a(j, true);
            }
        }
    }

    private void a(UgcVideoInfoBean ugcVideoInfoBean, final boolean z) {
        if (this.ab == null) {
            cn.xiaochuankeji.tieba.api.ugcvideo.a aVar = new cn.xiaochuankeji.tieba.api.ugcvideo.a();
            if (ugcVideoInfoBean.pid == 0) {
                this.ab = aVar.a(ugcVideoInfoBean.id, z, J());
            } else {
                this.ab = aVar.b(ugcVideoInfoBean.id, z, J());
            }
            this.ab.a(rx.a.b.a.a()).b(new j<EmptyJson>(this) {
                final /* synthetic */ UgcVideoActivity b;

                public /* synthetic */ void onNext(Object obj) {
                    a((EmptyJson) obj);
                }

                public void onCompleted() {
                    this.b.ab = null;
                }

                public void onError(Throwable th) {
                    this.b.ab = null;
                    g.a(th.getMessage());
                }

                public void a(EmptyJson emptyJson) {
                    this.b.ab = null;
                    this.b.a(z, false);
                }
            });
        }
    }

    private String J() {
        String str = this.Q;
        if (TextUtils.isEmpty(this.Q) || this.Q.equals("other") || this.E.a().g == this.g.getCurrentItem()) {
            return str;
        }
        return this.Q + "-ugcvideodetail";
    }

    private void a(boolean z, boolean z2) {
        UgcVideoInfoBean j = j();
        if (j != null) {
            int i;
            if (z2) {
                j.liked = 0;
                if (z) {
                    i = j.likeCount - 1;
                    j.likeCount = i;
                } else {
                    i = j.likeCount + 1;
                    j.likeCount = i;
                }
                j.likeCount = i;
            } else {
                j.liked = z ? 1 : -1;
                if (z) {
                    i = j.likeCount + 1;
                    j.likeCount = i;
                } else {
                    i = j.likeCount - 1;
                    j.likeCount = i;
                }
                j.likeCount = i;
            }
            b(j);
        }
    }

    @l(a = ThreadMode.MAIN)
    public void ugcDanmaku(cn.xiaochuankeji.tieba.background.d.g gVar) {
        this.I = gVar.a;
        this.J = gVar.b;
        if (this.S != 0) {
            this.P.a(this.I, this.J, this.R, this.S, J());
            this.R = 0;
            this.S = 0;
            return;
        }
        this.P.a(this.I, this.J, J());
    }

    @l(a = ThreadMode.MAIN)
    public void replyDanmaku(cn.xiaochuankeji.tieba.ui.danmaku.TopDanmakuView.b bVar) {
        this.R = bVar.a;
        this.S = 4;
        onClick(this.A);
    }

    public void d(boolean z) {
        if (z) {
            this.K.b();
        } else {
            this.K.c();
        }
    }

    public void e(boolean z) {
        this.M.setVisibility(z ? 0 : 8);
    }

    protected void onActivityResult(int i, int i2, Intent intent) {
        super.onActivityResult(i, i2, intent);
        if (-1 == i2 && 1001 == i) {
            String stringExtra = intent.getStringExtra("key_published_video");
            if (!TextUtils.isEmpty(stringExtra)) {
                this.E.a((UgcVideoInfoBean) JSON.parseObject(stringExtra, UgcVideoInfoBean.class));
            }
        } else if (-1 == i2 && 1002 == i) {
            UgcVideoInfoBean j = j();
            if (j != null && intent != null && intent.getLongExtra("ugcVideoId", 0) == j.id) {
                a(j.liked == 1, true);
            }
        }
    }

    protected void onResume() {
        super.onResume();
        this.d.a(BaseApplication.getAppContext());
        this.g.addOnPageChangeListener(this);
        this.M.setVisibility(8);
        if (!org.greenrobot.eventbus.c.a().b(this)) {
            org.greenrobot.eventbus.c.a().a(this);
        }
    }

    protected void onPause() {
        super.onPause();
        this.d.b(BaseApplication.getAppContext());
        this.g.removeOnPageChangeListener(this);
        if (org.greenrobot.eventbus.c.a().b(this)) {
            org.greenrobot.eventbus.c.a().c(this);
        }
    }

    public void onBackPressed() {
        if (this.L != null && this.L.isShown()) {
            K();
        } else if (!cn.xiaochuankeji.tieba.ui.ugcvideodetail.danmaku.d.a((Activity) this)) {
            if (cn.xiaochuankeji.tieba.ui.widget.g.b(this)) {
                cn.xiaochuankeji.tieba.ui.widget.g.c(this);
            } else if (this.P == null || !this.P.b()) {
                super.onBackPressed();
            }
        }
    }

    private void K() {
        if (this.L.getParent() != null) {
            cn.xiaochuankeji.tieba.background.a.a().edit().putBoolean("key_show_review_guide", false).apply();
            ((ViewGroup) this.L.getParent()).removeView(this.L);
            if (this.T) {
                A();
            }
            org.greenrobot.eventbus.c.a().d(new cn.xiaochuankeji.tieba.ui.ugcvideodetail.g.b(((UgcVideoInfoBean) this.G.get(this.g.getCurrentItem())).id));
        }
    }

    protected void onDestroy() {
        super.onDestroy();
        this.E.e();
        this.E = null;
        unregisterReceiver(this.O);
        cn.xiaochuankeji.tieba.background.utils.e.d.a().b();
        z();
    }

    public void onPageScrolled(int i, float f, int i2) {
    }

    public void onPageSelected(int i) {
        if (this.C != null) {
            this.C.b(i);
        }
        if (g()) {
            com.c.a.c.b(this).b(i == 0);
        }
        a(i, false);
        UgcVideoInfoBean j = j();
        a(j);
        b(j);
        e(false);
    }

    public void onPageScrollStateChanged(int i) {
    }

    public void a(DanmakuItem danmakuItem) {
        cn.xiaochuankeji.tieba.background.d.h hVar = new cn.xiaochuankeji.tieba.background.d.h();
        hVar.a = this.I;
        hVar.b = danmakuItem;
        org.greenrobot.eventbus.c.a().d(hVar);
        e(false);
    }

    public void k() {
        cn.xiaochuankeji.tieba.background.d.h hVar = new cn.xiaochuankeji.tieba.background.d.h();
        hVar.a = this.I;
        org.greenrobot.eventbus.c.a().d(hVar);
        e(false);
    }

    public boolean v() {
        return this.Z;
    }

    public void f(boolean z) {
        this.Z = z;
    }

    public boolean w() {
        return this.aa == 0;
    }

    @TargetApi(8)
    private void L() {
        try {
            AudioManager audioManager = (AudioManager) getSystemService("audio");
            if (audioManager.isMusicActive() && audioManager.requestAudioFocus(null, 3, 2) != 1) {
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void x() {
        GrayConfigBean C = cn.xiaochuankeji.tieba.background.utils.c.a.c().C();
        if (C != null && C.ugcvideoCachePreloadEnabled == 1 && this.G != null && !this.G.isEmpty()) {
            UgcVideoInfoBean ugcVideoInfoBean;
            int currentItem = this.g.getCurrentItem();
            com.danikula.videocache.f b = q.a().b();
            if (currentItem > 0) {
                ugcVideoInfoBean = (UgcVideoInfoBean) this.G.get(currentItem - 1);
                if (!b.e(ugcVideoInfoBean.videoInfo.url)) {
                    b.a(this, ugcVideoInfoBean.videoInfo.url);
                }
            }
            if (currentItem < this.G.size() - 1) {
                ugcVideoInfoBean = (UgcVideoInfoBean) this.G.get(currentItem + 1);
                if (!b.e(ugcVideoInfoBean.videoInfo.url)) {
                    b.a(this, ugcVideoInfoBean.videoInfo.url);
                }
            }
        }
    }

    public boolean y() {
        if (this.U) {
            int currentItem = this.g.getCurrentItem();
            if (currentItem < this.G.size() + -1) {
                this.g.setCurrentItem(currentItem + 1);
                return true;
            }
        }
        return false;
    }
}
