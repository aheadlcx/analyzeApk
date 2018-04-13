package cn.xiaochuankeji.tieba.ui.mediabrowse;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.graphics.Bitmap;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Build.VERSION;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.text.TextUtils;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;
import cn.xiaochuankeji.aop.permission.b;
import cn.xiaochuankeji.aop.permission.c;
import cn.xiaochuankeji.tieba.R;
import cn.xiaochuankeji.tieba.background.AppController;
import cn.xiaochuankeji.tieba.background.danmaku.DanmakuItem;
import cn.xiaochuankeji.tieba.background.data.Comment;
import cn.xiaochuankeji.tieba.background.data.ServerImage;
import cn.xiaochuankeji.tieba.background.data.ServerVideo;
import cn.xiaochuankeji.tieba.background.data.post.Post;
import cn.xiaochuankeji.tieba.background.modules.chat.models.beans.MessageEvent;
import cn.xiaochuankeji.tieba.background.modules.chat.models.beans.MessageEvent.MessageEventType;
import cn.xiaochuankeji.tieba.background.picture.PictureImpl;
import cn.xiaochuankeji.tieba.background.picture.PictureImpl.Type;
import cn.xiaochuankeji.tieba.background.post.PostRecommendQueryList;
import cn.xiaochuankeji.tieba.background.post.r;
import cn.xiaochuankeji.tieba.background.utils.newshare.CommentShareDataModel;
import cn.xiaochuankeji.tieba.background.utils.newshare.PostShareDataModel;
import cn.xiaochuankeji.tieba.background.utils.newshare.ShareDataModel;
import cn.xiaochuankeji.tieba.ui.base.h;
import cn.xiaochuankeji.tieba.ui.homepage.HomePageActivity;
import cn.xiaochuankeji.tieba.ui.mediabrowse.component.ImgDownloadFinishAnimView;
import cn.xiaochuankeji.tieba.ui.mediabrowse.component.RoundProgressBar;
import cn.xiaochuankeji.tieba.ui.mediabrowse.component.d;
import cn.xiaochuankeji.tieba.ui.mediabrowse.component.g;
import cn.xiaochuankeji.tieba.ui.my.assessor.UserAssessActivity;
import cn.xiaochuankeji.tieba.ui.post.postdetail.PostDetailActivity;
import cn.xiaochuankeji.tieba.ui.topic.TopicDetailActivity;
import cn.xiaochuankeji.tieba.ui.widget.SDBottomSheet;
import cn.xiaochuankeji.tieba.ui.widget.TBViewPager;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.c.a.e;
import com.facebook.imagepipeline.request.ImageRequestBuilder;
import com.iflytek.cloud.SpeechConstant;
import com.sina.weibo.sdk.constant.WBConstants;
import java.io.File;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import org.greenrobot.eventbus.ThreadMode;
import org.greenrobot.eventbus.l;
import rx.d$a;
import rx.j;

@b(a = {"android.permission.WRITE_EXTERNAL_STORAGE"}, b = "正常浏览大图", d = "确认", f = "拒绝该权限后无法正常浏览大图", h = "拒绝", j = "设置权限", l = true, m = true)
public class MediaBrowseActivity extends h implements OnClickListener, cn.xiaochuankeji.tieba.ui.danmaku.d.a, SDBottomSheet.b {
    private static final org.aspectj.lang.a.a Z = null;
    private d A;
    private long B;
    private int C = -1;
    private Post D;
    private Comment E;
    private boolean F;
    private EntranceType G;
    private String H;
    private String I;
    private Handler J = new Handler();
    private cn.xiaochuankeji.tieba.background.utils.share.b K;
    private a L;
    private cn.xiaochuankeji.tieba.ui.danmaku.d M;
    private cn.xiaochuankeji.tieba.background.danmaku.a N;
    private NetworkConnectivityReceiver O;
    private int P = -1;
    private cn.xiaochuankeji.tieba.background.utils.a.b Q;
    private Map<Long, DownloadProgressEvent> R;
    private long S;
    private long T;
    private e U;
    private volatile long V;
    private int W;
    private final int X = 500;
    private final int Y = 700;
    private ArrayList<cn.htjyb.b.a> d;
    private ArrayList<cn.htjyb.b.a> e;
    private ArrayList<ServerImage> f;
    private HashMap<Long, ServerVideo> g;
    private HashMap<Long, ServerVideo> h = new HashMap();
    private List<ServerVideo> i;
    private int j;
    private TBViewPager k;
    private View l;
    private TextView m;
    private TextView n;
    private View o;
    private ImageView p;
    private TextView q;
    private View r;
    private TextView s;
    private RoundProgressBar t;
    private TextView u;
    private ImageView v;
    private View w;
    private View x;
    private long y;
    private boolean z;

    class a {
        String a;
        String b;
        Bitmap c;
        String d;
        final /* synthetic */ MediaBrowseActivity e;

        a(MediaBrowseActivity mediaBrowseActivity) {
            this.e = mediaBrowseActivity;
        }
    }

    static {
        N();
    }

    private static void N() {
        org.aspectj.a.b.b bVar = new org.aspectj.a.b.b("MediaBrowseActivity.java", MediaBrowseActivity.class);
        Z = bVar.a("method-execution", bVar.a("4", "onCreate", "cn.xiaochuankeji.tieba.ui.mediabrowse.MediaBrowseActivity", "android.os.Bundle", "savedInstanceState", "", "void"), 296);
    }

    private static String a(Context context) {
        if (HomePageActivity.class.isInstance(context)) {
            return r.d();
        }
        if (PostDetailActivity.class.isInstance(context)) {
            return "post";
        }
        if (TopicDetailActivity.class.isInstance(context)) {
            return "topic";
        }
        if (UserAssessActivity.class.isInstance(context)) {
            return "assess";
        }
        return "other";
    }

    private static String a(EntranceType entranceType) {
        if (entranceType == EntranceType.PostItem || entranceType == EntranceType.Post_RecommendImgTxt || entranceType == EntranceType.Post_RecommendIndex || entranceType == EntranceType.Post_RecommendVideo || entranceType == EntranceType.Post_RecommendUgc) {
            return "post";
        }
        if (entranceType == EntranceType.CommentImage || entranceType == EntranceType.Review_RecommendIndex || entranceType == EntranceType.Review_RecommendImgTxt || entranceType == EntranceType.Review_RecommendUgc || entranceType == EntranceType.Review_RecommendVideo) {
            return "review";
        }
        if (entranceType == EntranceType.Subject) {
            return SpeechConstant.SUBJECT;
        }
        return r.a;
    }

    public static void a(Context context, int i, Post post, ArrayList<cn.htjyb.b.a> arrayList, boolean z, EntranceType entranceType) {
        if (arrayList == null || arrayList.size() > i) {
            Intent intent = new Intent(context, MediaBrowseActivity.class);
            intent.putExtra("key_post", post);
            intent.putExtra("key_post", post);
            intent.putExtra("key_images", arrayList);
            intent.putExtra("kCurrentIndex", i);
            intent.putExtra("key_simple_layout", z);
            intent.putExtra("key_entrance_type", entranceType);
            intent.putExtra("key_page_from", a(context));
            intent.putExtra("key_page_owner", a(entranceType));
            context.startActivity(intent);
        }
    }

    public static void a(Context context, int i, Post post, ArrayList<cn.htjyb.b.a> arrayList, ArrayList<cn.htjyb.b.a> arrayList2, ArrayList<ServerImage> arrayList3, HashMap<Long, ServerVideo> hashMap, EntranceType entranceType) {
        if (arrayList2 == null || arrayList2.size() > i) {
            Intent intent = new Intent(context, MediaBrowseActivity.class);
            intent.putExtra("key_post", post);
            intent.putExtra("key_images", arrayList2);
            intent.putExtra("key_thumbs", arrayList);
            intent.putExtra("kCurrentIndex", i);
            intent.putExtra("kPostImgs", arrayList3);
            intent.putExtra("kImgVideos", hashMap);
            intent.putExtra("key_entrance_type", entranceType);
            intent.putExtra("key_page_from", a(context));
            intent.putExtra("key_page_owner", a(entranceType));
            context.startActivity(intent);
        }
    }

    public static void a(Context context, int i, Post post, Comment comment, ArrayList<cn.htjyb.b.a> arrayList, ArrayList<cn.htjyb.b.a> arrayList2, ArrayList<ServerImage> arrayList3, HashMap<Long, ServerVideo> hashMap, EntranceType entranceType, long j, long j2) {
        if (arrayList2 == null || arrayList2.size() > i) {
            Intent intent = new Intent(context, MediaBrowseActivity.class);
            intent.putExtra("key_post", post);
            intent.putExtra("key_comment", comment);
            intent.putExtra("key_images", arrayList2);
            intent.putExtra("key_thumbs", arrayList);
            intent.putExtra("kCurrentIndex", i);
            intent.putExtra("kPostImgs", arrayList3);
            intent.putExtra("kImgVideos", hashMap);
            intent.putExtra("kCommentId", j);
            intent.putExtra("key_entrance_type", entranceType);
            intent.putExtra("key_page_from", a(context));
            intent.putExtra("key_page_owner", a(entranceType));
            intent.putExtra("key_parent_comment_id", j2);
            context.startActivity(intent);
        }
    }

    protected boolean i() {
        return false;
    }

    static final void a(MediaBrowseActivity mediaBrowseActivity, Bundle bundle, org.aspectj.lang.a aVar) {
        if (VERSION.SDK_INT >= 19) {
            mediaBrowseActivity.getWindow().clearFlags(67108864);
            mediaBrowseActivity.getWindow().clearFlags(134217728);
        }
        if (VERSION.SDK_INT >= 21) {
            mediaBrowseActivity.getWindow().clearFlags(Integer.MIN_VALUE);
        }
        if (VERSION.SDK_INT >= 26) {
            mediaBrowseActivity.getWindow().setFlags(1024, 1024);
        }
        com.android.a.a.b.a(mediaBrowseActivity.getWindow(), false);
        super.onCreate(bundle);
        mediaBrowseActivity.overridePendingTransition(R.anim.scale_in, 0);
        mediaBrowseActivity.Q = cn.xiaochuankeji.tieba.background.utils.a.a.a().a(mediaBrowseActivity);
        mediaBrowseActivity.Q.a(2);
    }

    protected void onCreate(Bundle bundle) {
        org.aspectj.lang.a a = org.aspectj.a.b.b.a(Z, this, this, bundle);
        c.c().a(new a(new Object[]{this, bundle, a}).linkClosureAndJoinPoint(69648));
    }

    protected int a() {
        return R.layout.activity_media_brwose;
    }

    protected boolean a(Bundle bundle) {
        Bundle extras = getIntent().getExtras();
        this.y = extras.getLong("kCommentId");
        this.T = extras.getLong("key_parent_comment_id");
        this.j = extras.getInt("kCurrentIndex");
        this.D = (Post) extras.getSerializable("key_post");
        this.E = (Comment) extras.getSerializable("key_comment");
        if (this.D != null) {
            this.B = this.D._ID;
            this.C = this.D._share;
        }
        this.d = (ArrayList) extras.getSerializable("key_images");
        if (this.d == null || this.d.isEmpty()) {
            return false;
        }
        this.e = (ArrayList) extras.getSerializable("key_thumbs");
        this.F = extras.getBoolean("key_simple_layout", false);
        this.G = (EntranceType) extras.getSerializable("key_entrance_type");
        this.H = extras.getString("key_page_from");
        this.I = extras.getString("key_page_owner");
        this.f = (ArrayList) extras.getSerializable("kPostImgs");
        this.g = (HashMap) extras.getSerializable("kImgVideos");
        this.K = cn.xiaochuankeji.tieba.background.utils.share.b.a();
        if (this.D != null) {
            this.L = new a(this);
            this.L.a = this.D._postContent;
            if (this.D._topic != null) {
                StringBuilder stringBuilder = new StringBuilder();
                a aVar = this.L;
                aVar.a = stringBuilder.append(aVar.a).append(" ").append(this.D._topic._topicName).toString();
            }
            this.L.b = cn.xiaochuankeji.tieba.background.utils.d.a.a(this.D);
        }
        this.M = new cn.xiaochuankeji.tieba.ui.danmaku.d(this, this);
        this.N = new cn.xiaochuankeji.tieba.background.danmaku.a();
        if (this.D != null) {
            long j;
            long j2 = this.y != 0 ? this.y : this.D._ID;
            if (this.D._topic != null) {
                j = this.D._topic._topicID;
            } else {
                j = 0;
            }
            Iterator it = this.d.iterator();
            boolean z = false;
            int i = 0;
            while (it.hasNext()) {
                Type type = (Type) ((cn.htjyb.b.a) it.next()).getType();
                if (type != Type.kVideo) {
                    i++;
                }
                if (type == Type.kGif || type == Type.kMP4) {
                    z = true;
                }
            }
            cn.xiaochuankeji.tieba.background.utils.a.e.a().a(j2, this.H, i, j, 0, v(), z);
            cn.xiaochuankeji.tieba.background.utils.a.e.a().a(this.B, this.T, this.E == null ? 0 : this.E._status);
        }
        if (this.j < 0 || this.j >= this.d.size()) {
            this.j = 0;
        }
        if (((cn.htjyb.b.a) this.d.get(this.j)).getType() == Type.kVideo || ((cn.htjyb.b.a) this.d.get(this.j)).getType() == Type.kMP4) {
            this.S = ((cn.htjyb.b.a) this.d.get(this.j)).getPictureID();
        }
        if (!g()) {
            return true;
        }
        this.U = com.c.a.c.b(this);
        if (this.d.size() <= 1 || this.j <= 0) {
            return true;
        }
        this.U.b(false);
        return true;
    }

    protected void c() {
        this.k = (TBViewPager) findViewById(R.id.viewPager);
        this.x = findViewById(R.id.llVideoBar);
        this.l = findViewById(R.id.btn_back);
        this.m = (TextView) findViewById(R.id.tvPosition_top);
        this.n = (TextView) findViewById(R.id.tvPosition_top_1);
        this.o = findViewById(R.id.ivSave_top);
        this.p = (ImageView) findViewById(R.id.tvShare_top);
        this.q = (TextView) findViewById(R.id.tvPosition_bottom);
        this.r = findViewById(R.id.ivSave_bottom);
        this.s = (TextView) findViewById(R.id.tvShare_bottom);
        this.t = (RoundProgressBar) findViewById(R.id.iv_progress);
        this.u = (TextView) findViewById(R.id.tv_percent);
        this.v = (ImageView) findViewById(R.id.iv_download_finsh);
        this.w = findViewById(R.id.llImageBar);
    }

    protected void i_() {
        this.A = new d(getSupportFragmentManager(), this, this.B, this.d, this.e);
        this.k.setAdapter(this.A);
        this.k.setCurrentItem(this.j);
        this.k.setEnabled(false);
        this.R = new HashMap(this.A.getCount());
        if (this.E == null) {
            this.s.setText(this.C != -1 ? cn.xiaochuankeji.tieba.ui.utils.d.b(this.C) : "");
        }
        G();
        if (this.D == null) {
            this.s.setVisibility(8);
        }
        if (this.G == EntranceType.Subject) {
            this.p.setVisibility(8);
            this.s.setVisibility(8);
        }
        if (this.g != null && this.g.size() > 0) {
            new cn.xiaochuankeji.tieba.api.post.c().a(this.D._ID, this.H, this.E == null ? -1 : this.E._id).a(rx.f.a.a(cn.xiaochuankeji.tieba.background.a.p().e())).b(new j<JSONObject>(this) {
                final /* synthetic */ MediaBrowseActivity a;

                {
                    this.a = r1;
                }

                public /* synthetic */ void onNext(Object obj) {
                    a((JSONObject) obj);
                }

                public void onCompleted() {
                }

                public void onError(Throwable th) {
                }

                public void a(JSONObject jSONObject) {
                    JSONArray jSONArray = jSONObject.getJSONArray("videos");
                    if (jSONArray != null && jSONArray.size() != 0) {
                        this.a.i = new ArrayList();
                        for (int i = 0; i < jSONArray.size(); i++) {
                            ServerVideo serverVideo = new ServerVideo(new org.json.JSONObject(jSONArray.getJSONObject(i)));
                            if (!this.a.h.containsKey(Long.valueOf(serverVideo.videoId))) {
                                this.a.i.add(serverVideo);
                            }
                            this.a.h.put(Long.valueOf(serverVideo.videoId), serverVideo);
                        }
                        this.a.runOnUiThread(new Runnable(this) {
                            final /* synthetic */ AnonymousClass1 a;

                            {
                                this.a = r1;
                            }

                            public void run() {
                                if (this.a.a.A != null) {
                                    this.a.a.A.a(this.a.a.i);
                                    this.a.a.G();
                                }
                            }
                        });
                    }
                }
            });
        }
    }

    private cn.xiaochuankeji.tieba.ui.mediabrowse.component.c F() {
        Iterator it = this.A.a().iterator();
        while (it.hasNext()) {
            cn.xiaochuankeji.tieba.ui.mediabrowse.component.c cVar = (cn.xiaochuankeji.tieba.ui.mediabrowse.component.c) it.next();
            if (cVar.c() == this.j) {
                return cVar;
            }
        }
        return null;
    }

    public boolean j() {
        return this.F;
    }

    private void G() {
        if (this.d != null && this.d.size() > 0) {
            Object obj = (this.j + 1) + "";
            this.m.setText(obj);
            this.n.setText(" / " + this.d.size());
            this.q.setText(obj + " / " + this.d.size());
        }
        if (((Type) ((cn.htjyb.b.a) this.d.get(this.j)).getType()) == Type.kVideo) {
            this.w.setVisibility(4);
            c(true);
            return;
        }
        this.w.setVisibility(0);
        c(false);
    }

    public ServerVideo a(long j) {
        if (this.g == null) {
            return null;
        }
        ServerVideo serverVideo = (ServerVideo) this.g.get(Long.valueOf(j));
        if (serverVideo == null) {
            return (ServerVideo) this.h.get(Long.valueOf(j));
        }
        return serverVideo;
    }

    private void c(boolean z) {
        if (this.x != null) {
            if (this.F) {
                z = false;
            }
            if (this.z != z) {
                if (z) {
                    this.x.setVisibility(0);
                } else {
                    this.x.setVisibility(4);
                }
                this.z = z;
            }
        }
    }

    public String k() {
        return this.H;
    }

    public String v() {
        return this.I;
    }

    public Comment w() {
        return this.E;
    }

    public long x() {
        if (this.D == null || this.D._topic == null) {
            return 0;
        }
        return this.D._topic._topicID;
    }

    public long y() {
        return this.T;
    }

    public long z() {
        return this.y != 0 ? this.y : this.B;
    }

    protected void j_() {
        this.k.setOnPageChangeListener(new OnPageChangeListener(this) {
            final /* synthetic */ MediaBrowseActivity a;

            {
                this.a = r1;
            }

            public void onPageScrolled(int i, float f, int i2) {
            }

            public void onPageSelected(int i) {
                this.a.j = i;
                if (this.a.A != null && this.a.A.a() != null) {
                    Iterator it = this.a.A.a().iterator();
                    while (it.hasNext()) {
                        cn.xiaochuankeji.tieba.ui.mediabrowse.component.c cVar = (cn.xiaochuankeji.tieba.ui.mediabrowse.component.c) it.next();
                        if (cVar.c() == this.a.j) {
                            cVar.b();
                        } else {
                            cVar.a();
                        }
                    }
                    this.a.G();
                    if (this.a.d != null && this.a.d.size() != 0 && i < this.a.d.size()) {
                        this.a.S = ((cn.htjyb.b.a) this.a.d.get(i)).getPictureID();
                        this.a.d(false);
                        if (!this.a.g()) {
                            return;
                        }
                        if (i == 0) {
                            this.a.U.b(true);
                        } else {
                            this.a.U.b(false);
                        }
                    }
                }
            }

            public void onPageScrollStateChanged(int i) {
            }
        });
        this.l.setOnClickListener(new OnClickListener(this) {
            final /* synthetic */ MediaBrowseActivity a;

            {
                this.a = r1;
            }

            public void onClick(View view) {
                this.a.onBackPressed();
            }
        });
        this.o.setOnClickListener(this);
        this.p.setOnClickListener(this);
        this.r.setOnClickListener(this);
        this.s.setOnClickListener(this);
    }

    public int A() {
        return this.P;
    }

    private void H() {
        NetworkInfo activeNetworkInfo = ((ConnectivityManager) getSystemService("connectivity")).getActiveNetworkInfo();
        if (activeNetworkInfo == null || !activeNetworkInfo.isAvailable()) {
            this.P = -1;
        } else {
            this.P = activeNetworkInfo.getType();
        }
        I();
    }

    private void I() {
        if (this.P == 1) {
            AppController.instance().setAllowCellular(false);
        }
    }

    public void B() {
        com.izuiyou.a.a.b.e("bbbb_需要暂停视频");
        this.M.a();
    }

    protected void onResume() {
        super.onResume();
        H();
        this.O = new NetworkConnectivityReceiver();
        this.O.a(new cn.xiaochuankeji.tieba.ui.mediabrowse.NetworkConnectivityReceiver.a(this) {
            final /* synthetic */ MediaBrowseActivity a;

            {
                this.a = r1;
            }

            public void a(boolean z, int i) {
                this.a.P = i;
                this.a.I();
                cn.xiaochuankeji.tieba.ui.mediabrowse.component.c i2 = this.a.F();
                if (i2 instanceof g) {
                    ((g) i2).a(z, this.a.P);
                }
            }
        });
        this.O.a((Context) this);
        this.Q.b();
    }

    protected void onPause() {
        super.onPause();
        this.M.b();
        if (this.O != null) {
            this.O.b(this);
            this.O.a(null);
            this.O = null;
        }
        this.Q.d();
    }

    public void onBackPressed() {
        if (!l() && !this.M.c()) {
            Iterator it = this.A.a().iterator();
            while (it.hasNext()) {
                cn.xiaochuankeji.tieba.ui.mediabrowse.component.c cVar = (cn.xiaochuankeji.tieba.ui.mediabrowse.component.c) it.next();
                if (g.class.isInstance(cVar) && ((g) cVar).f()) {
                    return;
                }
            }
            overridePendingTransition(0, R.anim.scale_out);
            super.onBackPressed();
        }
    }

    protected void onDestroy() {
        if (this.K != null) {
            this.K.a(null);
        }
        this.J.removeCallbacksAndMessages(null);
        super.onDestroy();
        if (this.A != null) {
            this.A.b();
            this.A = null;
        }
        if (this.R != null) {
            this.R.clear();
        }
        J();
        cn.xiaochuankeji.tieba.background.utils.a.a.a().b();
        cn.xiaochuankeji.tieba.background.utils.e.d.a().b();
        cn.xiaochuankeji.tieba.background.utils.a.e.a().b();
    }

    private void J() {
        if (this.D != null) {
            PostRecommendQueryList e = r.e();
            if (e != null) {
                e.b(this.D);
            }
        }
    }

    public void onClick(View view) {
        cn.htjyb.b.a aVar = (cn.htjyb.b.a) this.d.get(this.k.getCurrentItem());
        switch (view.getId()) {
            case R.id.ivSave_top:
            case R.id.ivSave_bottom:
                if (aVar.getType() == Type.kVideo) {
                    b(aVar);
                    return;
                } else if (aVar.getType() == Type.kPostPicLarge || aVar.getType() == Type.kCommentOriginImg || aVar.getType() == Type.kPicWithUri) {
                    c(aVar);
                    return;
                } else if (aVar.getType() == Type.kGif) {
                    c(aVar);
                    return;
                } else if (aVar.getType() == Type.kMP4) {
                    c(aVar);
                    return;
                } else {
                    return;
                }
            case R.id.tvShare_top:
            case R.id.tvShare_bottom:
                aVar = (cn.htjyb.b.a) this.d.get(this.j);
                if (aVar.getType() == Type.kGif || aVar.getType() == Type.kMP4) {
                    b(cn.xiaochuankeji.tieba.background.f.b.d(aVar.getPictureID()));
                    return;
                } else {
                    K();
                    return;
                }
            default:
                return;
        }
    }

    @l(a = ThreadMode.MAIN)
    public void downloadProgress(DownloadProgressEvent downloadProgressEvent) {
        long j = downloadProgressEvent.videoId;
        if (j > 0) {
            this.R.put(Long.valueOf(j), downloadProgressEvent);
            d(true);
        }
    }

    private void d(boolean z) {
        DownloadProgressEvent downloadProgressEvent = (DownloadProgressEvent) this.R.get(Long.valueOf(this.S));
        if (downloadProgressEvent != null) {
            if (downloadProgressEvent.eventType == 1) {
                this.o.setVisibility(0);
                this.t.setVisibility(8);
                this.u.setVisibility(8);
                this.v.setVisibility(8);
                if (z) {
                    e(true);
                }
            } else if (downloadProgressEvent.eventType == 0) {
                this.o.setVisibility(8);
                this.t.setVisibility(0);
                this.u.setVisibility(0);
                this.u.setText(downloadProgressEvent.eventValue + "%");
                this.t.setProgress(downloadProgressEvent.eventValue);
                if (downloadProgressEvent.eventValue == 99) {
                    this.v.setVisibility(0);
                }
            } else if (downloadProgressEvent.eventType == -1) {
                this.o.setVisibility(0);
                this.t.setVisibility(8);
                this.u.setVisibility(8);
                this.v.setVisibility(8);
                cn.xiaochuankeji.tieba.background.utils.g.a("网络不佳，下载失败");
            }
        }
    }

    private void b(cn.htjyb.b.a aVar) {
        String str = "最右下载视频";
        if (!(this.D == null || TextUtils.isEmpty(this.D._postContent))) {
            str = this.D._postContent;
        }
        if (this.G == EntranceType.CommentImage) {
            PictureDownloadService.a((Context) this, str, aVar);
            return;
        }
        ServerVideo a = a(aVar.getPictureID());
        if (a != null) {
            CharSequence charSequence = a.downloadUrl;
            Object obj = a.srcUrl;
            if (TextUtils.isEmpty(charSequence) && TextUtils.isEmpty(obj)) {
                cn.xiaochuankeji.tieba.background.utils.g.b("该视频不能下载");
                return;
            }
            org.greenrobot.eventbus.c.a().d(new DownloadProgressEvent(this.S, 0, 0));
            if (TextUtils.isEmpty(charSequence)) {
                PictureDownloadService.a((Context) this, str, cn.xiaochuankeji.tieba.background.a.f().a(obj, Type.kVideo, aVar.getPictureID()));
            } else {
                PictureDownloadService.a(this, str, charSequence, Type.kVideo, aVar.getPictureID());
            }
            Map hashMap = new HashMap();
            hashMap.put("owner", v());
            hashMap.put("watermark", TextUtils.isEmpty(charSequence) ? "0" : "1");
            cn.xiaochuankeji.tieba.background.utils.monitor.a.b.a().a("download", "video", aVar.getPictureID(), z(), "mediabrowse", hashMap);
        }
    }

    public void a(int i) {
        if (i == 2325) {
            onClick(this.r);
        } else if (i != 18) {
            cn.htjyb.b.a aVar = (cn.htjyb.b.a) this.d.get(this.k.getCurrentItem());
            this.W = i;
            if (aVar.getType() == Type.kGif || aVar.getType() == Type.kMP4) {
                if (i == 1 && this.V < 5242880) {
                    a(i, aVar);
                } else if (i != 2 || this.V >= 10485760) {
                    b(i);
                } else {
                    a(i, aVar);
                }
            } else if (aVar.getType() == Type.kVideo) {
                b(i);
            } else if (aVar.getType() != Type.kPostPicLarge && aVar.getType() != Type.kCommentOriginImg && aVar.getType() != Type.kPicWithUri) {
            } else {
                if (i == 5) {
                    b(i);
                } else {
                    c(i);
                }
            }
        } else if ("review".equalsIgnoreCase(v())) {
            this.D.copyCommentsLink();
        } else {
            this.D.copyLink();
        }
    }

    private void K() {
        SDBottomSheet sDBottomSheet = new SDBottomSheet(this, this);
        sDBottomSheet.a(sDBottomSheet.c(), null);
        sDBottomSheet.b();
    }

    private void b(int i) {
        if (this.E != null) {
            final ShareDataModel commentShareDataModel = new CommentShareDataModel(this.E, this.D, i);
            commentShareDataModel.prepareData(new cn.xiaochuankeji.tieba.background.utils.newshare.ShareDataModel.a(this) {
                final /* synthetic */ MediaBrowseActivity b;

                public void a() {
                    cn.xiaochuankeji.tieba.background.utils.share.b.a().a(this.b, commentShareDataModel);
                    this.b.a(commentShareDataModel.getABTestId());
                }
            });
            return;
        }
        final ShareDataModel postShareDataModel = new PostShareDataModel(this.D, this.D.comments.size() > 0 ? (Comment) this.D.comments.get(0) : null, i);
        postShareDataModel.prepareData(new cn.xiaochuankeji.tieba.background.utils.newshare.ShareDataModel.a(this) {
            final /* synthetic */ MediaBrowseActivity b;

            public void a() {
                cn.xiaochuankeji.tieba.background.utils.share.b.a().a(this.b, postShareDataModel);
                this.b.a(postShareDataModel.getABTestId());
            }
        });
    }

    private void c(final int i) {
        cn.htjyb.b.a aVar = (cn.htjyb.b.a) this.d.get(this.j);
        final String str = cn.xiaochuankeji.tieba.background.a.e().B() + UUID.randomUUID() + "share.jpg";
        a(cn.xiaochuankeji.tieba.background.f.b.d(aVar.getPictureID()), str, new j<File>(this) {
            final /* synthetic */ MediaBrowseActivity c;

            public /* synthetic */ void onNext(Object obj) {
                a((File) obj);
            }

            public void onCompleted() {
            }

            public void onError(Throwable th) {
                this.c.runOnUiThread(new Runnable(this) {
                    final /* synthetic */ AnonymousClass15 a;

                    {
                        this.a = r1;
                    }

                    public void run() {
                        cn.xiaochuankeji.tieba.background.utils.g.a("图片下载失败");
                        cn.xiaochuankeji.tieba.ui.widget.g.c(this.a.c);
                    }
                });
            }

            public void a(File file) {
                cn.xiaochuankeji.tieba.ui.widget.g.c(this.c);
                this.c.K.a(i, this.c, this.c.L.c, str);
                this.c.a("");
            }
        });
    }

    private void a(final int i, final String str) {
        cn.htjyb.b.a aVar = (cn.htjyb.b.a) this.d.get(this.j);
        if (this.d.size() != 1) {
            this.L.d = cn.xiaochuankeji.tieba.background.f.b.a(aVar.getPictureID(), false).b();
        } else if (aVar.getType() == Type.kCommentOriginImg) {
            this.L.d = cn.xiaochuankeji.tieba.background.f.b.a(aVar.getPictureID(), false).b();
        } else {
            this.L.d = cn.xiaochuankeji.tieba.background.f.b.a(aVar.getPictureID(), true).b();
        }
        com.facebook.drawee.a.a.c.c().a(ImageRequestBuilder.a(Uri.parse(this.L.d)).a(true).n(), Boolean.valueOf(true)).a(new cn.xiaochuankeji.tieba.ui.widget.bigImage.c(this, Uri.parse(this.L.d)) {
            final /* synthetic */ MediaBrowseActivity c;

            protected void a(int i) {
            }

            protected void a(final Bitmap bitmap) {
                this.c.runOnUiThread(new Runnable(this) {
                    final /* synthetic */ AnonymousClass16 b;

                    public void run() {
                        cn.xiaochuankeji.tieba.ui.widget.g.c(this.b.c);
                        this.b.c.K.b(i, this.b.c, bitmap, str);
                        this.b.c.a("");
                    }
                });
            }

            protected void a(Throwable th) {
            }
        }, new com.facebook.imagepipeline.d.a(Runtime.getRuntime().availableProcessors()).d());
    }

    private void a(String str) {
        int i = 3;
        if (this.C == -1) {
            return;
        }
        if (this.E == null) {
            this.C++;
            this.D._share = this.C;
            this.s.setText(cn.xiaochuankeji.tieba.ui.utils.d.b(this.C));
            MessageEvent messageEvent = new MessageEvent(MessageEventType.MESSAGE_POST_SHARE);
            messageEvent.setData(this.D);
            org.greenrobot.eventbus.c.a().d(messageEvent);
            if (this.d != null) {
                long j = this.B;
                String str2 = (String) cn.xiaochuankeji.tieba.d.d.b.get(Integer.valueOf(this.W));
                if (this.j < this.d.size()) {
                    long j2;
                    if (this.j < this.f.size()) {
                        ServerImage serverImage = (ServerImage) this.f.get(this.j);
                        if (!serverImage.isVideo()) {
                            if (serverImage.isGif()) {
                                i = 2;
                            } else {
                                i = 1;
                            }
                        }
                        j2 = serverImage.postImageId;
                    } else {
                        j2 = ((cn.htjyb.b.a) this.d.get(this.j)).getPictureID();
                    }
                    cn.xiaochuankeji.tieba.background.i.a.a(j, "mediabrowse", str2, i, j2, str);
                } else if (this.i == null || this.i.size() <= this.j - this.f.size()) {
                    cn.xiaochuankeji.tieba.background.i.a.a(j, "mediabrowse", str2, 3, ((ServerVideo) this.h.get(Integer.valueOf(this.j - this.f.size()))).imageId, str);
                }
            }
        } else if (this.f != null) {
            long j3;
            int i2;
            if (this.j < this.f.size()) {
                ServerImage serverImage2 = (ServerImage) this.f.get(this.j);
                if (!serverImage2.isVideo()) {
                    if (serverImage2.isGif()) {
                        i = 2;
                    } else {
                        i = 1;
                    }
                }
                j3 = serverImage2.postImageId;
                i2 = i;
            } else {
                j3 = ((cn.htjyb.b.a) this.d.get(this.j)).getPictureID();
                i2 = 3;
            }
            cn.xiaochuankeji.tieba.background.i.a.a(this.E._pid, this.E._id, "mediabrowse", (String) cn.xiaochuankeji.tieba.d.d.b.get(Integer.valueOf(this.W)), i2, j3, str);
        }
    }

    private void a(final int i, final cn.htjyb.b.a aVar) {
        String d = cn.xiaochuankeji.tieba.background.f.b.d(aVar.getPictureID());
        String D = cn.xiaochuankeji.tieba.background.a.e().D();
        cn.xiaochuankeji.tieba.ui.widget.g.a((Activity) this, "正在下载图片");
        if (D == null) {
            cn.xiaochuankeji.tieba.background.utils.g.a("路径错误!");
        } else {
            a(d, D + aVar.getPictureID() + ".gif", new j<File>(this) {
                final /* synthetic */ MediaBrowseActivity c;

                public /* synthetic */ void onNext(Object obj) {
                    a((File) obj);
                }

                public void onCompleted() {
                }

                public void onError(Throwable th) {
                    this.c.runOnUiThread(new Runnable(this) {
                        final /* synthetic */ AnonymousClass17 a;

                        {
                            this.a = r1;
                        }

                        public void run() {
                            cn.xiaochuankeji.tieba.background.utils.g.a("图片下载失败");
                            cn.xiaochuankeji.tieba.ui.widget.g.c(this.a.c);
                        }
                    });
                }

                public void a(File file) {
                    this.c.a(i, file.getAbsolutePath());
                    Map hashMap = new HashMap();
                    hashMap.put("owner", this.c.v());
                    hashMap.put("watermark", "0");
                    cn.xiaochuankeji.tieba.background.utils.monitor.a.b.a().a("download", WBConstants.GAME_PARAMS_GAME_IMAGE_URL, aVar.getPictureID(), this.c.z(), "mediabrowse", hashMap);
                }
            });
        }
    }

    private void e(boolean z) {
        cn.xiaochuankeji.tieba.background.utils.g.a(getString(R.string.download_tip));
        if (!z) {
            L();
        }
    }

    private void L() {
        final FrameLayout frameLayout = (FrameLayout) findViewById(R.id.flDownloadFinish);
        frameLayout.setVisibility(0);
        ImgDownloadFinishAnimView imgDownloadFinishAnimView = (ImgDownloadFinishAnimView) findViewById(R.id.vFinishAnim);
        final ImageView imageView = (ImageView) findViewById(R.id.ivFlag);
        imageView.setImageResource(R.drawable.img_downloading);
        imgDownloadFinishAnimView.a(500);
        imgDownloadFinishAnimView.postDelayed(new Runnable(this) {
            final /* synthetic */ MediaBrowseActivity b;

            public void run() {
                imageView.setImageResource(R.drawable.img_download_finish);
            }
        }, 500);
        imgDownloadFinishAnimView.postDelayed(new Runnable(this) {
            final /* synthetic */ MediaBrowseActivity b;

            public void run() {
                frameLayout.setVisibility(8);
            }
        }, 1200);
    }

    private void c(cn.htjyb.b.a aVar) {
        boolean z = true;
        boolean z2 = false;
        if (aVar != null) {
            String D = cn.xiaochuankeji.tieba.background.a.e().D();
            if (D == null) {
                cn.xiaochuankeji.tieba.background.utils.g.a("路径错误!");
                return;
            }
            boolean z3;
            D = D + aVar.getPictureID();
            if (aVar.getType() == Type.kVideo) {
                D = D + PictureImpl.getSavedName(aVar.downloadUrl()) + ".mp4";
                z3 = false;
            } else if (aVar.getType() == Type.kGif || aVar.getType() == Type.kMP4) {
                D = D + ".gif";
                z3 = true;
                z = false;
            } else {
                D = D + ".jpg";
                z3 = false;
                z2 = true;
                z = false;
            }
            if (new File(D).exists()) {
                e(z);
                return;
            }
            String e;
            String str;
            if (z2 || z3) {
                cn.xiaochuankeji.tieba.ui.widget.g.a((Activity) this, "正在下载图片");
                String d = cn.xiaochuankeji.tieba.background.f.b.d(aVar.getPictureID());
                if (this.f != null && this.f.size() > 0) {
                    Iterator it = this.f.iterator();
                    while (it.hasNext()) {
                        ServerImage serverImage = (ServerImage) it.next();
                        if (serverImage != null && serverImage.postImageId == aVar.getPictureID() && serverImage.isImage()) {
                            if (!serverImage.isLongImage() || this.G == EntranceType.Chat) {
                                e = cn.xiaochuankeji.tieba.background.f.b.e(aVar.getPictureID());
                                break;
                            }
                        }
                    }
                }
                e = d;
                if (this.G == EntranceType.Chat) {
                    e = cn.xiaochuankeji.tieba.background.f.b.e(aVar.getPictureID());
                }
                a(e, D, new j<File>(this) {
                    final /* synthetic */ MediaBrowseActivity a;

                    {
                        this.a = r1;
                    }

                    public /* synthetic */ void onNext(Object obj) {
                        a((File) obj);
                    }

                    public void onCompleted() {
                    }

                    public void onError(Throwable th) {
                        this.a.runOnUiThread(new Runnable(this) {
                            final /* synthetic */ AnonymousClass4 a;

                            {
                                this.a = r1;
                            }

                            public void run() {
                                cn.xiaochuankeji.tieba.background.utils.g.a("图片下载失败");
                                cn.xiaochuankeji.tieba.ui.widget.g.c(this.a.a);
                            }
                        });
                    }

                    public void a(File file) {
                        cn.xiaochuankeji.tieba.ui.widget.g.c(this.a);
                        this.a.e(false);
                        Intent intent = new Intent("android.intent.action.MEDIA_SCANNER_SCAN_FILE");
                        intent.setData(Uri.fromFile(file));
                        this.a.sendBroadcast(intent);
                    }
                });
            } else if (aVar.hasLocalFile()) {
                a(z, aVar.cachePath(), D);
            } else {
                cn.xiaochuankeji.tieba.background.utils.g.a("稍等加载成功再保存");
            }
            Map hashMap = new HashMap();
            hashMap.put("owner", v());
            hashMap.put("watermark", "0");
            e = z ? "video" : WBConstants.GAME_PARAMS_GAME_IMAGE_URL;
            if (z3) {
                str = "gif";
            } else {
                str = e;
            }
            cn.xiaochuankeji.tieba.background.utils.monitor.a.b.a().a("download", str, aVar.getPictureID(), z(), "mediabrowse", hashMap);
        }
    }

    private void a(final String str, final String str2, final j<? super File> jVar) {
        rx.d.b(new d$a<File>(this) {
            final /* synthetic */ MediaBrowseActivity d;

            public /* synthetic */ void call(Object obj) {
                a((j) obj);
            }

            public void a(final j<? super File> jVar) {
                com.facebook.drawee.a.a.c.c().b(ImageRequestBuilder.a(Uri.parse(str)).a(true).n(), Boolean.valueOf(true)).a(new cn.xiaochuankeji.tieba.ui.widget.bigImage.g(this, Uri.parse(str)) {
                    final /* synthetic */ AnonymousClass5 b;

                    protected void a(int i) {
                    }

                    protected void a(File file) {
                        File file2 = new File(str2);
                        cn.htjyb.c.a.b.a(file, file2);
                        jVar.onNext(file2);
                        jVar.onCompleted();
                    }

                    protected void a(Throwable th) {
                        jVar.onError(th);
                        jVar.onCompleted();
                    }
                }, cn.xiaochuankeji.tieba.background.a.p().d());
            }
        }).b(rx.f.a.c()).a(rx.a.b.a.a()).b((j) jVar);
    }

    private void a(boolean z, final String str, final String str2) {
        cn.xiaochuankeji.tieba.background.a.p().b().execute(new Runnable(this) {
            final /* synthetic */ MediaBrowseActivity c;

            public void run() {
                boolean z = true;
                File file = new File(cn.xiaochuankeji.tieba.background.a.e().D());
                if (!file.canWrite()) {
                    try {
                        file.setWritable(true);
                    } catch (Exception e) {
                        cn.xiaochuankeji.tieba.background.utils.g.b("设置路径可写错误：" + e.getMessage());
                        return;
                    }
                }
                try {
                    org.apache.commons.io.b.a(new File(str), new File(str2));
                } catch (IOException e2) {
                    final IOException iOException = e2;
                    z = false;
                    this.c.runOnUiThread(new Runnable(this) {
                        final /* synthetic */ AnonymousClass6 b;

                        public void run() {
                            cn.xiaochuankeji.tieba.background.utils.g.b(iOException.getMessage());
                        }
                    });
                }
                this.c.J.post(new Runnable(this) {
                    final /* synthetic */ AnonymousClass6 b;

                    public void run() {
                        if (z) {
                            Intent intent = new Intent("android.intent.action.MEDIA_SCANNER_SCAN_FILE");
                            intent.setData(Uri.fromFile(new File(str2)));
                            this.b.c.sendBroadcast(intent);
                            return;
                        }
                        cn.xiaochuankeji.tieba.background.utils.g.a("保存失败");
                    }
                });
            }
        });
    }

    @l(a = ThreadMode.MAIN)
    public void message(MessageEvent messageEvent) {
        if (messageEvent.getEventType() == MessageEventType.MESSAGE_FINISH_BIG_PIC) {
            overridePendingTransition(0, R.anim.scale_out);
            finish();
        }
    }

    public void a(cn.htjyb.b.a aVar) {
        SDBottomSheet sDBottomSheet = new SDBottomSheet(this, this);
        Object obj = null;
        if (this.p.isShown() || this.s.isShown()) {
            obj = 1;
        }
        ArrayList arrayList;
        if (obj != null) {
            arrayList = new ArrayList();
            arrayList.add(new SDBottomSheet.c(R.drawable.toast_download, "保存", 2325));
            sDBottomSheet.a(sDBottomSheet.c(), arrayList);
        } else {
            arrayList = new ArrayList();
            arrayList.add(new SDBottomSheet.c(R.drawable.toast_download, "保存", 2325));
            sDBottomSheet.a(arrayList, null);
        }
        sDBottomSheet.b();
    }

    private void b(final String str) {
        rx.d.b(new d$a<Long>(this) {
            final /* synthetic */ MediaBrowseActivity b;

            public /* synthetic */ void call(Object obj) {
                a((j) obj);
            }

            public void a(j<? super Long> jVar) {
                try {
                    HttpURLConnection httpURLConnection = (HttpURLConnection) new URL(str).openConnection();
                    httpURLConnection.setConnectTimeout(3000);
                    httpURLConnection.setReadTimeout(1000);
                    httpURLConnection.setRequestMethod("HEAD");
                    httpURLConnection.setInstanceFollowRedirects(true);
                    httpURLConnection.connect();
                    this.b.V = Long.parseLong(httpURLConnection.getHeaderField("Content-Length"));
                    jVar.onNext(Long.valueOf(this.b.V));
                } catch (Throwable e) {
                    jVar.onError(e);
                }
            }
        }).b(rx.f.a.c()).a(rx.a.b.a.a()).b(new j<Long>(this) {
            final /* synthetic */ MediaBrowseActivity a;

            {
                this.a = r1;
            }

            public /* synthetic */ void onNext(Object obj) {
                a((Long) obj);
            }

            public void onCompleted() {
            }

            public void onError(Throwable th) {
                cn.xiaochuankeji.tieba.background.utils.g.b("网络异常");
            }

            public void a(Long l) {
                this.a.K();
            }
        });
    }

    public void onConfigurationChanged(Configuration configuration) {
        Object obj = 1;
        super.onConfigurationChanged(configuration);
        if (getResources().getConfiguration().orientation != 1) {
            obj = null;
        }
        if (obj != null) {
            this.k.a();
        } else {
            this.k.b();
        }
    }

    public void a(String str, String str2, long j) {
        final g gVar = (g) F();
        long g = gVar.g();
        long pictureID = ((cn.htjyb.b.a) this.d.get(this.j)).getPictureID();
        cn.xiaochuankeji.tieba.ui.widget.g.a((Activity) this);
        com.izuiyou.a.a.b.e("发弹幕前的pid:" + this.B + ",发弹幕前的rid:" + this.y);
        this.N.a(this.B, pictureID, this.y, 0, g, str, new cn.xiaochuankeji.tieba.background.danmaku.a.a(this) {
            final /* synthetic */ MediaBrowseActivity b;

            public void a(boolean z, String str, DanmakuItem danmakuItem) {
                if (!this.b.isFinishing()) {
                    this.b.M.d();
                    this.b.M();
                    cn.xiaochuankeji.tieba.ui.widget.g.c(this.b);
                    if (z) {
                        cn.xiaochuankeji.tieba.background.utils.g.a("弹幕发送成功");
                        gVar.a(danmakuItem);
                        return;
                    }
                    cn.xiaochuankeji.tieba.background.utils.g.a(str);
                }
            }
        });
    }

    public void g_() {
        M();
    }

    private void M() {
        if (F() instanceof g) {
            ((g) F()).i();
        }
    }

    public boolean C() {
        return this.g != null && this.g.size() == 1;
    }

    public boolean D() {
        return this.h != null && this.h.size() > 0;
    }

    public boolean f(long j) {
        return this.h != null && this.h.containsKey(Long.valueOf(j));
    }

    public boolean E() {
        return this.d != null && this.j == this.d.size() - 1;
    }
}
