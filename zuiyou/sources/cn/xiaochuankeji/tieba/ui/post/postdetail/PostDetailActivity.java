package cn.xiaochuankeji.tieba.ui.post.postdetail;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.view.PointerIconCompat;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.widget.FrameLayout;
import android.widget.FrameLayout.LayoutParams;
import android.widget.ImageView;
import android.widget.ImageView.ScaleType;
import android.widget.RelativeLayout;
import cn.htjyb.ui.widget.headfooterlistview.HeaderFooterListView;
import cn.htjyb.ui.widget.headfooterlistview.header.b;
import cn.xiaochuan.base.BaseApplication;
import cn.xiaochuankeji.aop.permission.c;
import cn.xiaochuankeji.tieba.R;
import cn.xiaochuankeji.tieba.background.data.Comment;
import cn.xiaochuankeji.tieba.background.data.post.Post;
import cn.xiaochuankeji.tieba.background.data.post.PostAndCommentsRequest;
import cn.xiaochuankeji.tieba.background.data.post.PostAndCommentsRequest.OnQueryPostFinishListener;
import cn.xiaochuankeji.tieba.background.data.post.PostAndNewCommentsRequest;
import cn.xiaochuankeji.tieba.background.data.post.PostAndNewCommentsRequest.Listener;
import cn.xiaochuankeji.tieba.background.modules.chat.models.beans.MessageEvent;
import cn.xiaochuankeji.tieba.background.modules.chat.models.beans.MessageEvent.MessageEventType;
import cn.xiaochuankeji.tieba.background.post.k;
import cn.xiaochuankeji.tieba.background.post.r;
import cn.xiaochuankeji.tieba.background.upload.j;
import cn.xiaochuankeji.tieba.ui.base.h;
import cn.xiaochuankeji.tieba.ui.discovery.search.SearchAllActivity;
import cn.xiaochuankeji.tieba.ui.homepage.HomePageActivity;
import cn.xiaochuankeji.tieba.ui.mediabrowse.EntranceType;
import cn.xiaochuankeji.tieba.ui.post.PostQueryListView;
import cn.xiaochuankeji.tieba.ui.post.b.d;
import cn.xiaochuankeji.tieba.ui.post.postdetail.e.a;
import cn.xiaochuankeji.tieba.ui.post.postitem.e;
import cn.xiaochuankeji.tieba.ui.post.postitem.f;
import cn.xiaochuankeji.tieba.ui.selectlocalmedia.LocalMedia;
import cn.xiaochuankeji.tieba.ui.topic.TopicDetailActivity;
import cn.xiaochuankeji.tieba.ui.topic.data.PostDataBean;
import cn.xiaochuankeji.tieba.ui.widget.g;
import com.tencent.wcdb.database.SQLiteDatabase;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.greenrobot.eventbus.ThreadMode;
import org.greenrobot.eventbus.l;
import org.json.JSONObject;

public class PostDetailActivity extends h implements b, OnQueryPostFinishListener, Listener, a {
    private static final org.aspectj.lang.a.a Q = null;
    private String A;
    private cn.xiaochuankeji.tieba.background.post.a B;
    private boolean C = false;
    private boolean D = false;
    private boolean E = false;
    private String F;
    private cn.xiaochuankeji.tieba.background.utils.a.b G;
    private boolean H;
    private int I;
    private boolean J;
    private boolean K = false;
    private boolean L = false;
    private FrameLayout M;
    private j N;
    private VoiceDetailController O;
    private boolean P = false;
    private String d;
    private String e;
    private Post f;
    private PostDataBean g;
    private int h = 0;
    private CommentFilter i;
    private PostAndCommentsRequest j;
    private PostAndNewCommentsRequest k;
    private e l;
    private e m;
    private k n;
    private PostQueryListView o;
    private View p;
    private View q;
    private RelativeLayout r;
    private d s;
    private cn.xiaochuankeji.tieba.ui.publish.e t;
    private ArrayList<LocalMedia> u;
    private int v = 0;
    private long w;
    private ArrayList<Long> x = new ArrayList();
    private ArrayList<Long> y = new ArrayList();
    private long z;

    public static class CommentFilter implements Serializable {
        public static final int QUERY_KEY_EQ = 1;
        public static final int QUERY_KEY_GE = 2;
        private final long commentId;
        private final int queryKey;

        public CommentFilter(long j, int i) {
            this.commentId = j;
            this.queryKey = i;
        }

        public String getQueryType() {
            String str = "equal";
            switch (this.queryKey) {
                case 1:
                    return "equal";
                case 2:
                    return "late";
                default:
                    return str;
            }
        }
    }

    static {
        K();
    }

    private static void K() {
        org.aspectj.a.b.b bVar = new org.aspectj.a.b.b("PostDetailActivity.java", PostDetailActivity.class);
        Q = bVar.a("method-execution", bVar.a("4", "onCreate", "cn.xiaochuankeji.tieba.ui.post.postdetail.PostDetailActivity", "android.os.Bundle", "savedInstanceState", "", "void"), 322);
    }

    public boolean j() {
        return true;
    }

    public void k() {
        if (this.l != null) {
            this.l.f();
        }
        C();
    }

    public void c(boolean z) {
        this.h = z ? 1 : 2;
        if (1 == this.h) {
            this.l.b();
        }
    }

    public static void a(Context context, Post post) {
        a(context, post, 0);
    }

    public static void a(Context context, Post post, String str) {
        a(context, post, 0, null, str);
    }

    public static void a(Context context, Post post, boolean z, int i, String str, EntranceType entranceType) {
        if (post == null || post._ID == 0) {
            com.izuiyou.a.a.b.d("open post with invalid data", new Exception());
            return;
        }
        Intent intent = new Intent(context, PostDetailActivity.class);
        intent.putExtra("key_from", a(context, entranceType));
        intent.putExtra("key_post", post);
        intent.putExtra("key_flag_level", z);
        intent.putExtra("key_topic_role", i);
        intent.putExtra("key_area", str);
        if ("review".equalsIgnoreCase(str)) {
            intent.putExtra("key_default_scroll_type", 1);
        }
        intent.setFlags(SQLiteDatabase.CREATE_IF_NECESSARY);
        context.startActivity(intent);
    }

    public static void a(Context context, Post post, PostDataBean postDataBean, boolean z, int i, String str, EntranceType entranceType) {
        if (postDataBean == null || postDataBean.postId == 0) {
            com.izuiyou.a.a.b.d("open post with invalid data", new Exception());
            return;
        }
        cn.xiaochuankeji.tieba.ui.voice.b.d.a().a(false);
        Intent intent = new Intent(context, PostDetailActivity.class);
        intent.putExtra("key_from", a(context, entranceType));
        intent.putExtra("key_post", post);
        intent.putExtra("key_post_data", postDataBean);
        intent.putExtra("key_flag_level", z);
        intent.putExtra("key_topic_role", i);
        intent.putExtra("key_area", str);
        if ("review".equalsIgnoreCase(str)) {
            intent.putExtra("key_default_scroll_type", 1);
        }
        intent.setFlags(SQLiteDatabase.CREATE_IF_NECESSARY);
        context.startActivity(intent);
    }

    public static void a(Context context, Post post, int i) {
        a(context, post, i, null, null);
    }

    public static void a(Context context, Post post, int i, CommentFilter commentFilter, String str, EntranceType entranceType) {
        if (post == null || post._ID == 0) {
            com.izuiyou.a.a.b.d("open post with invalid data", new Exception());
            return;
        }
        Intent intent = new Intent(context, PostDetailActivity.class);
        intent.putExtra("key_from", a(context, entranceType));
        intent.putExtra("key_post", post);
        intent.putExtra("key_default_scroll_type", i);
        intent.putExtra("key_comment_filter", commentFilter);
        intent.putExtra("key_area", str);
        intent.setFlags(SQLiteDatabase.CREATE_IF_NECESSARY);
        context.startActivity(intent);
    }

    public static void a(Context context, Post post, int i, CommentFilter commentFilter, String str) {
        a(context, post, i, commentFilter, str, null);
    }

    private static String a(Context context, EntranceType entranceType) {
        if (context == null) {
            return "unknown";
        }
        if (entranceType != null) {
            switch (entranceType) {
                case Chat:
                    return "chat";
                case Notify:
                    return "notify";
                case Push:
                    return "push";
            }
        }
        if (HomePageActivity.class.isInstance(context)) {
            return r.d();
        }
        if (TopicDetailActivity.class.isInstance(context)) {
            if (1 == ((TopicDetailActivity) context).j()) {
                return "topic_hot";
            }
            return "topic_new";
        } else if (BaseApplication.getAppContext().getClass().isInstance(context)) {
            return "push";
        } else {
            if (SearchAllActivity.class.isInstance(context)) {
                return "search";
            }
            return "other";
        }
    }

    static final void a(PostDetailActivity postDetailActivity, Bundle bundle, org.aspectj.lang.a aVar) {
        super.onCreate(bundle);
        postDetailActivity.G = cn.xiaochuankeji.tieba.background.utils.a.a.a().a(postDetailActivity);
        postDetailActivity.J = postDetailActivity.getIntent().getBooleanExtra("key_flag_level", false);
        postDetailActivity.I = postDetailActivity.getIntent().getIntExtra("key_topic_role", 0);
        if (!postDetailActivity.isFinishing()) {
            if (postDetailActivity.g != null) {
                postDetailActivity.P = true;
                postDetailActivity.y();
            } else {
                if (postDetailActivity.f._topic != null && postDetailActivity.f._topic._topicID > 0) {
                    postDetailActivity.B();
                }
                if (postDetailActivity.f._topic == null && postDetailActivity.f._topic != null && postDetailActivity.f._topic._topicID > 0) {
                    postDetailActivity.z();
                }
                if (postDetailActivity.f._topic == null) {
                    postDetailActivity.z();
                }
            }
            postDetailActivity.C();
            cn.xiaochuankeji.tieba.background.utils.h.a(postDetailActivity, "zy_event_postdetail_page", "页面进入");
            cn.xiaochuankeji.tieba.ui.voice.b.d.a().a(true);
        }
    }

    protected void onCreate(Bundle bundle) {
        org.aspectj.lang.a a = org.aspectj.a.b.b.a(Q, this, this, bundle);
        c.c().a(new c(new Object[]{this, bundle, a}).linkClosureAndJoinPoint(69648));
    }

    private void y() {
        this.O = new VoiceDetailController(this.g, this);
        final HeaderFooterListView m = this.o.m();
        m.addHeaderView(this.O.b(), null, false);
        J();
        m.setScrolledListener(new HeaderFooterListView.a(this) {
            final /* synthetic */ PostDetailActivity b;

            public void a(int i, int i2, int i3) {
                if (i <= 2 || this.b.a == null) {
                    this.b.a.b();
                } else {
                    this.b.a.a();
                    this.b.a.getIvExtraOption().setSelected(!this.b.l.i());
                }
                if (this.b.l != null) {
                    this.b.l.b(m.getLastVisiblePosition());
                }
            }
        });
    }

    public void t() {
        super.t();
        if (this.l != null) {
            this.l.a(this.a.getOptionImageView());
        }
    }

    private void z() {
        g.a((Activity) this, true);
    }

    private void A() {
        if (this.o.m().isShown()) {
            g.c(this);
        }
    }

    private void B() {
        if (this.f._imgList.size() > 1) {
            this.m = new f(this, this.f._imgList.size());
        } else if (this.f._imgList.size() == 1) {
            this.m = new cn.xiaochuankeji.tieba.ui.post.postitem.h(this);
        } else {
            this.m = new cn.xiaochuankeji.tieba.ui.post.postitem.g(this);
        }
        this.q = LayoutInflater.from(this).inflate(R.layout.view_postdetail_header, null);
        this.p = this.m.i();
        ((FrameLayout) this.q.findViewById(R.id.postitem_container)).addView(this.p);
        final HeaderFooterListView m = this.o.m();
        m.addHeaderView(this.q, null, false);
        this.m.a(this.f, new b.b(this) {
            final /* synthetic */ PostDetailActivity b;

            public void a(boolean z) {
                if (!this.b.isFinishing()) {
                    m.setVisibility(0);
                    this.b.A();
                    this.b.H = true;
                    this.b.J();
                }
            }
        });
        this.m.j();
        this.m.d(-1);
        if (this.f.postType == 1) {
            z();
            m.setVisibility(4);
        }
        m.setScrolledListener(new HeaderFooterListView.a(this) {
            final /* synthetic */ PostDetailActivity b;

            public void a(int i, int i2, int i3) {
                if (i <= 2 || this.b.a == null) {
                    this.b.a.b();
                } else {
                    this.b.a.a();
                    this.b.a.getIvExtraOption().setSelected(!this.b.l.i());
                }
                if (this.b.l != null) {
                    this.b.l.b(m.getLastVisiblePosition());
                }
            }
        });
    }

    protected int a() {
        return R.layout.activity_post_detail;
    }

    protected boolean a(Bundle bundle) {
        if (getIntent().getExtras() == null) {
            return false;
        }
        Bundle extras = getIntent().getExtras();
        this.d = extras.getString("key_from");
        this.e = extras.getString("key_area", "");
        this.f = (Post) extras.getSerializable("key_post");
        this.g = (PostDataBean) extras.getSerializable("key_post_data");
        this.h = extras.getInt("key_default_scroll_type");
        this.i = (CommentFilter) extras.getSerializable("key_comment_filter");
        if (this.f != null && 0 != this.f._ID) {
            return true;
        }
        com.izuiyou.a.a.b.e("init data with a null post");
        cn.xiaochuankeji.tieba.background.utils.g.a("帖子数据错误");
        return false;
    }

    protected void c() {
        this.M = (FrameLayout) findViewById(R.id.input_container);
        this.o = (PostQueryListView) findViewById(R.id.list);
        this.o.setRefreshStayMinTime(500);
        this.r = (RelativeLayout) findViewById(R.id.rootView);
    }

    protected void i_() {
        this.o.m().setDescendantFocusability(393216);
        this.o.setRefreshHeaderCallBack(this);
        if (this.a != null) {
            this.a.setExtraOptionImg(c.a.d.a.a.a().d(R.drawable.ic_switch_comment_text));
        }
    }

    private void a(String str) {
        if (!TextUtils.isEmpty(str)) {
            if (str.equals("帖子不存在")) {
                cn.xiaochuankeji.tieba.background.utils.g.a(str);
                if (!isFinishing()) {
                    onBackPressed();
                }
                MessageEvent messageEvent = new MessageEvent(MessageEventType.MESSAGE_POST_HAS_DELETED);
                messageEvent.setData(this.f);
                org.greenrobot.eventbus.c.a().d(messageEvent);
                return;
            }
            cn.xiaochuankeji.tieba.background.utils.g.a(str);
        }
    }

    private void C() {
        this.D = false;
        if (this.i == null) {
            d(false);
            return;
        }
        this.o.f();
        D();
    }

    private void d(boolean z) {
        if (this.j == null) {
            this.j = new PostAndCommentsRequest(this.f._ID);
        }
        if (this.d.startsWith("index")) {
            this.j.setSrcType(this.d);
        } else if (this.d.startsWith("topic")) {
            this.j.setSrcType("topic");
        } else if (this.d.equals("push")) {
            this.j.setSrcType("push");
        } else {
            this.j.setSrcType(this.d);
        }
        this.j.setArea(this.e);
        this.j.registerOnQueryPostFinishListener(this);
        if (z) {
            this.L = false;
        }
        this.j.query();
    }

    private void D() {
        if (this.k == null) {
            this.k = new PostAndNewCommentsRequest(this.f._ID, this.i.commentId, this.i.getQueryType());
        }
        if (this.d.startsWith("index")) {
            this.k.setSrcType(this.d);
        } else if (this.d.startsWith("topic")) {
            this.k.setSrcType("topic");
        } else if (this.d.equals("push")) {
            this.k.setSrcType("push");
        } else {
            this.k.setSrcType(this.d);
        }
        this.k.setListener(this);
        this.k.query();
    }

    protected void onResume() {
        super.onResume();
        this.G.b();
    }

    protected void onPause() {
        super.onPause();
        this.G.d();
        if (cn.xiaochuankeji.tieba.ui.voice.b.d.a().h() && cn.xiaochuankeji.tieba.ui.voice.b.d.a().i()) {
            cn.xiaochuankeji.tieba.ui.voice.b.d.a().f();
            cn.xiaochuankeji.tieba.ui.voice.b.a.a().a(cn.xiaochuankeji.tieba.ui.voice.b.d.a().b().a, cn.xiaochuankeji.tieba.ui.voice.b.d.a().b().f);
        }
    }

    protected void j_() {
        this.s = new d(this, new d.a(this) {
            final /* synthetic */ PostDetailActivity a;

            {
                this.a = r1;
            }

            public void a(long j, String str, String str2, cn.xiaochuankeji.tieba.background.post.a aVar, ArrayList<LocalMedia> arrayList) {
                if (!this.a.D) {
                    cn.xiaochuankeji.tieba.background.utils.g.a("帖子未加载成功");
                } else if (cn.xiaochuankeji.tieba.ui.auth.a.a(this.a, "post_detail", 4, 1112)) {
                    this.a.z = j;
                    this.a.A = str;
                    this.a.B = aVar;
                    this.a.u = arrayList;
                    this.a.F = str2;
                    this.a.t.a();
                    if (arrayList.size() == 0) {
                        this.a.F();
                        return;
                    }
                    this.a.w = System.currentTimeMillis();
                    this.a.E();
                }
            }

            public void a(ArrayList<LocalMedia> arrayList) {
                if (arrayList == null || arrayList.size() == 0) {
                    cn.xiaochuankeji.tieba.ui.selectlocalmedia.b.a(this.a, PointerIconCompat.TYPE_NO_DROP);
                } else {
                    cn.xiaochuankeji.tieba.ui.selectlocalmedia.b.c(this.a, (int) PointerIconCompat.TYPE_NO_DROP, (List) arrayList);
                }
            }
        });
        this.M.addView(this.s.f_());
        this.t = new cn.xiaochuankeji.tieba.ui.publish.e(this, new cn.xiaochuankeji.tieba.ui.publish.e.a(this) {
            final /* synthetic */ PostDetailActivity a;

            {
                this.a = r1;
            }

            public void l_() {
                this.a.t.b();
                this.a.C = true;
                if (this.a.N != null) {
                    this.a.N.a();
                }
            }
        });
    }

    private void E() {
        this.N = new j();
        StringBuilder stringBuilder = new StringBuilder("正在上传 ");
        if (((LocalMedia) this.u.get(0)).type == 1) {
            stringBuilder.append("视频");
        } else {
            stringBuilder.append("图片");
        }
        stringBuilder.append((this.v + 1) + "/" + this.u.size());
        this.t.a(stringBuilder.toString(), 10, 0);
        this.N.a(this.u, "", new cn.xiaochuankeji.tieba.background.upload.b(this) {
            final /* synthetic */ PostDetailActivity a;

            {
                this.a = r1;
            }

            public void a(long j, long j2, int i) {
                StringBuilder stringBuilder = new StringBuilder("正在上传 ");
                if (((LocalMedia) this.a.u.get(i)).type == 1) {
                    stringBuilder.append("视频");
                } else {
                    stringBuilder.append("图片");
                }
                stringBuilder.append((i + 1) + "/" + this.a.u.size());
                this.a.t.a(stringBuilder.toString(), (int) j, (int) j2);
            }
        }, new cn.xiaochuankeji.tieba.background.upload.f(this) {
            final /* synthetic */ PostDetailActivity a;

            {
                this.a = r1;
            }

            public void a(List<Long> list, List<Long> list2, HashMap<String, LocalMedia> hashMap) {
                this.a.y = (ArrayList) list;
                this.a.x = (ArrayList) list2;
                this.a.F();
            }

            public void a(String str) {
                if (this.a.t != null && this.a.t.c()) {
                    this.a.t.b();
                }
                if (TextUtils.isEmpty(str) || !str.contains("上传视频不能超过15分钟")) {
                    cn.xiaochuankeji.tieba.background.utils.g.a("上传失败");
                } else {
                    cn.xiaochuankeji.tieba.background.utils.g.a("上传视频不能超过15分钟");
                }
            }
        });
    }

    private void F() {
        if (this.l == null) {
            cn.xiaochuankeji.tieba.background.utils.g.a("评论发送失败,请重试");
            return;
        }
        this.t.a("正在发评论", 10, 0);
        this.t.a(false);
        if (0 == this.z) {
            this.l.a(this.A, this.B, this.F, this.x, this.y);
        } else {
            this.l.a(this.z, this.A, this.B, this.F, this.x, this.y);
        }
    }

    public void b(boolean z) {
        this.s.a(z);
    }

    public void s() {
        if (!this.E) {
            return;
        }
        if (this.g != null && this.O != null) {
            this.O.a(this.g, false, true);
        } else if (this.f != null) {
            this.K = true;
            this.m.a(false, false);
        }
    }

    public void onBackPressed() {
        if (!this.t.c() && !this.s.c()) {
            cn.xiaochuankeji.tieba.ui.voice.b.d.a().a(!this.P);
            super.onBackPressed();
        }
    }

    protected void onDestroy() {
        int i = 0;
        super.onDestroy();
        if (this.N != null) {
            this.N.a();
        }
        if (this.j != null) {
            this.j.unregisterOnQueryPostFinishListener();
        }
        if (this.s != null) {
            this.s.g();
        }
        cn.xiaochuankeji.tieba.background.j.d.a().b();
        if (this.m != null) {
            this.m.e();
            this.m.o();
            this.m.c();
        }
        if (this.l != null) {
            this.l.c();
        }
        if (this.O != null) {
            this.O.c();
        }
        cn.xiaochuankeji.tieba.background.utils.a.a.a().b();
        if (!(this.f == null || this.l == null || this.G == null)) {
            long j = this.f._topic != null ? this.f._topic._topicID : 0;
            Map hashMap = new HashMap();
            hashMap.put("remain_time", Integer.valueOf(this.G.e() / 1000));
            int h = this.l.h() - 2;
            int g = this.l.g() - 2;
            String str = "max_hot";
            if (h <= 0) {
                h = 0;
            }
            hashMap.put(str, Integer.valueOf(h));
            String str2 = "max_new";
            if (g > 0) {
                i = g;
            }
            hashMap.put(str2, Integer.valueOf(i));
            cn.xiaochuankeji.tieba.background.utils.monitor.a.b.a().a("view", "postdetail", this.f._ID, j, this.d, hashMap);
        }
        cn.xiaochuankeji.tieba.ui.voice.b.d.a().a(true);
    }

    protected void onActivityResult(int i, int i2, Intent intent) {
        super.onActivityResult(i, i2, intent);
        if (-1 == i2) {
            if (PointerIconCompat.TYPE_NO_DROP == i) {
                this.s.a((ArrayList) cn.xiaochuankeji.tieba.ui.selectlocalmedia.b.b(intent));
            } else if (1 != i) {
            }
        }
    }

    public void onQueryPostFinish(boolean z, JSONObject jSONObject, ArrayList<Comment> arrayList, ArrayList<Comment> arrayList2, boolean z2, boolean z3, String str) {
        boolean z4 = true;
        if (!isFinishing()) {
            A();
            if (z) {
                this.E = true;
                this.g = PostDataBean.getPostDataBeanFromJson(jSONObject);
                this.f = new Post(jSONObject);
                if (this.l == null) {
                    this.l = new e(this, this.f, this);
                    r.a().a(this.f._ID, this.f._liked, this.f._likeCount);
                    I();
                }
                if ((arrayList == null || arrayList.size() == 0) && (arrayList2 == null || arrayList2.size() == 0)) {
                    this.o.f();
                }
                if (arrayList != null && arrayList.size() > 0) {
                    this.l.a((ArrayList) arrayList, z2);
                }
                this.l.e();
                this.l.a();
                if (this.g.localPostType() != 2) {
                    H();
                } else if (this.O == null) {
                    y();
                } else {
                    this.O.a(this.g);
                }
                J();
                this.D = true;
                this.o.l();
                if (arrayList == null || arrayList.size() <= 0) {
                    z4 = false;
                }
                e(z4);
                cn.xiaochuankeji.tieba.a.d.a(this.g);
                return;
            }
            a(str);
        }
    }

    private void e(final boolean z) {
        this.o.m().post(new Runnable(this) {
            final /* synthetic */ PostDetailActivity b;

            public void run() {
                SharedPreferences a = cn.xiaochuankeji.tieba.background.a.a();
                if (this.b.h != 0) {
                    if (a.getInt("key_share_comment_guide", 1) == 1) {
                        a.edit().putInt("key_share_comment_guide", 0).apply();
                        this.b.G();
                    }
                } else if (a.getInt("key_comment_sort", 1) == 1 && z) {
                    View findViewById = this.b.findViewById(R.id.tv_sort_way);
                    if (findViewById != null) {
                        a.edit().putInt("key_comment_sort", 0).apply();
                        this.b.a(findViewById);
                    }
                }
            }
        });
    }

    @SuppressLint({"ClickableViewAccessibility"})
    private void G() {
        final View findViewById = findViewById(16908290);
        if (findViewById instanceof FrameLayout) {
            final View imageView = new ImageView(this);
            imageView.setScaleType(ScaleType.CENTER);
            imageView.setImageResource(R.drawable.img_share_comment_guide);
            imageView.setBackgroundResource(R.color.black_50);
            ((FrameLayout) findViewById).addView(imageView, new LayoutParams(-1, -1));
            imageView.setOnTouchListener(new OnTouchListener(this) {
                final /* synthetic */ PostDetailActivity c;

                public boolean onTouch(View view, MotionEvent motionEvent) {
                    if (motionEvent.getAction() != 0) {
                        return false;
                    }
                    ((FrameLayout) findViewById).removeView(imageView);
                    return true;
                }
            });
        }
    }

    private void a(View view) {
    }

    public void onQueryPostAndNewCommentsFinish(boolean z, JSONObject jSONObject, Post post, ArrayList<Comment> arrayList, boolean z2, long j, String str) {
        if (!isFinishing()) {
            A();
            if (z) {
                this.D = true;
                this.E = true;
                this.g = PostDataBean.getPostDataBeanFromJson(jSONObject);
                this.f = post;
                this.l = new e(this, this.f, this);
                r.a().a(this.f._ID, this.f._liked, this.f._likeCount);
                I();
                if (arrayList != null && arrayList.size() > 0) {
                    this.l.a((ArrayList) arrayList, this.i.commentId, this.i.getQueryType(), z2, j);
                }
                if (this.g.localPostType() != 2) {
                    H();
                } else if (this.O == null) {
                    y();
                } else {
                    this.O.a(this.g);
                }
                J();
                cn.xiaochuankeji.tieba.a.d.a(this.g);
                return;
            }
            a(str);
        }
    }

    private void H() {
        if (this.m != null) {
            this.m.a(this.f, null);
        } else {
            B();
        }
        this.m.n();
    }

    private void I() {
        long j = 0;
        Post post = this.f;
        long j2 = this.f._ID;
        long j3 = this.f._topic != null ? this.f._topic._topicID : 0;
        if (this.f._member != null) {
            j = this.f._member.getId();
        }
        this.n = new k(post, j2, j3, j, this);
        this.n.a(new cn.xiaochuankeji.tieba.background.a.a.a<Post>(this) {
            final /* synthetic */ PostDetailActivity a;

            {
                this.a = r1;
            }
        });
        this.o.m().setItemsCanFocus(true);
        this.o.setVisibility(0);
        this.o.m().setOnItemLongClickListener(this.l);
    }

    private void J() {
        if (this.l != null) {
            if ((this.f.postType == 1 && !this.H) || this.L) {
                return;
            }
            if (1 == this.h) {
                this.l.b();
                this.L = true;
            } else if (2 == this.h) {
                this.l.d();
                this.L = true;
            }
        }
    }

    @l(a = ThreadMode.MAIN)
    public void message(MessageEvent messageEvent) {
        if (messageEvent.getEventType() == MessageEventType.MESSAGE_COMMENT_TEXT_VIEW_COLLAPSE) {
            if (((Context) messageEvent.getData()) == this) {
                int intValue = ((Integer) messageEvent.getExtraData()).intValue() + this.o.m().getHeaderViewsCount();
                if (this.o.m().getFirstVisiblePosition() == intValue) {
                    this.o.m().setSelection(intValue);
                }
            }
        } else if (messageEvent.getEventType() == MessageEventType.MESSAGE_POST_DELETE) {
            onBackPressed();
        }
    }

    public void a(boolean z, final Comment comment, final int i, String str) {
        this.v = 0;
        this.x.clear();
        this.y.clear();
        if (z) {
            this.t.a(new cn.xiaochuankeji.tieba.ui.publish.e.b(this) {
                final /* synthetic */ PostDetailActivity c;

                public void a() {
                    if (!this.c.isFinishing()) {
                        this.c.t.b();
                        this.c.t.a(true);
                        this.c.s.h();
                        if (cn.xiaochuankeji.tieba.push.e.a.a().a(this.c, 2)) {
                            cn.xiaochuankeji.tieba.background.utils.g.a("评论发送成功");
                        }
                        this.c.l.b(comment, i);
                    }
                }
            });
            return;
        }
        this.t.b();
        cn.xiaochuankeji.tieba.background.utils.g.a(str);
    }

    public void v() {
        d(true);
    }

    public boolean w() {
        return this.c;
    }

    public int x() {
        return this.J ? this.I : 0;
    }

    public void a(cn.htjyb.b.a.d dVar, boolean z) {
        if (this.a != null) {
            this.a.getIvExtraOption().setSelected(!z);
        }
        if (this.l != null) {
            this.l.a(dVar);
        }
    }
}
