package cn.xiaochuankeji.tieba.ui.topic;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.text.TextUtils;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.FrameLayout;
import cn.htjyb.netlib.d;
import cn.xiaochuankeji.aop.permission.c;
import cn.xiaochuankeji.tieba.R;
import cn.xiaochuankeji.tieba.background.beans.Member;
import cn.xiaochuankeji.tieba.background.data.post.AbstractPost;
import cn.xiaochuankeji.tieba.background.modules.chat.models.beans.MessageEvent;
import cn.xiaochuankeji.tieba.background.modules.chat.models.beans.MessageEvent.MessageEventType;
import cn.xiaochuankeji.tieba.background.net.XCError;
import cn.xiaochuankeji.tieba.background.picture.PictureImpl.Type;
import cn.xiaochuankeji.tieba.background.topic.BlockTopicActionRequest;
import cn.xiaochuankeji.tieba.background.topic.CancelBlockTopicActionRequest;
import cn.xiaochuankeji.tieba.background.topic.Topic;
import cn.xiaochuankeji.tieba.background.topic.TopicDetail;
import cn.xiaochuankeji.tieba.background.topic.TopicDetail.OnQueryTopicDetailFinishedListener;
import cn.xiaochuankeji.tieba.background.topic.TopicDetail.TopPostInfo;
import cn.xiaochuankeji.tieba.background.topic.TopicShareReportRequest;
import cn.xiaochuankeji.tieba.background.topic.TopicUtilityClass;
import cn.xiaochuankeji.tieba.background.utils.share.TopicShareStruct;
import cn.xiaochuankeji.tieba.background.utils.share.WebPageShareStruct;
import cn.xiaochuankeji.tieba.json.topic.QueryFobiddenJson;
import cn.xiaochuankeji.tieba.json.topic.TopImageConfigJson;
import cn.xiaochuankeji.tieba.ui.base.g;
import cn.xiaochuankeji.tieba.ui.base.h;
import cn.xiaochuankeji.tieba.ui.discovery.TopicActivity;
import cn.xiaochuankeji.tieba.ui.homepage.PostLoadedTipsView;
import cn.xiaochuankeji.tieba.ui.publish.PublishPostActivity;
import cn.xiaochuankeji.tieba.ui.topic.ui.PostListFragment;
import cn.xiaochuankeji.tieba.ui.topic.ui.PostListFragment.FragmentType;
import cn.xiaochuankeji.tieba.ui.utils.e;
import cn.xiaochuankeji.tieba.ui.videomaker.VideoRecordActivity;
import cn.xiaochuankeji.tieba.ui.voice.VoiceCreateActivity;
import cn.xiaochuankeji.tieba.ui.widget.SDBottomSheet;
import cn.xiaochuankeji.tieba.ui.widget.SDBottomSheet.b;
import cn.xiaochuankeji.tieba.ui.widget.SDTopSheet;
import cn.xiaochuankeji.tieba.ui.widget.StickyNavLayout;
import cn.xiaochuankeji.tieba.ui.widget.f;
import cn.xiaochuankeji.tieba.ui.widget.indicator.MagicIndicator;
import cn.xiaochuankeji.tieba.ui.widget.indicator.i;
import cn.xiaochuankeji.tieba.ui.widget.indicator.o;
import com.alibaba.fastjson.JSON;
import com.tencent.wcdb.database.SQLiteDatabase;
import in.srain.cube.views.ptr.PtrFrameLayout;
import java.util.ArrayList;
import java.util.List;
import org.greenrobot.eventbus.ThreadMode;
import org.greenrobot.eventbus.l;
import org.json.JSONException;
import org.json.JSONObject;
import rx.j;

public class TopicDetailActivity extends h implements OnClickListener, OnQueryTopicDetailFinishedListener, b, cn.xiaochuankeji.tieba.ui.widget.StickyNavLayout.a {
    private static final org.aspectj.lang.a.a I = null;
    public static int d = 0;
    private static String e = "from";
    private static String f = "pid";
    private static String g = "topic_info";
    private PostListFragment A;
    private Topic B;
    private long C;
    private PtrFrameLayout D;
    private boolean E = false;
    private volatile boolean F = false;
    private cn.xiaochuankeji.tieba.background.utils.a.b G;
    private PostLoadedTipsView H;
    private v h;
    private ViewPager i;
    private MagicIndicator j;
    private o k;
    private FrameLayout l;
    private a m;
    private StickyNavLayout n;
    private long o;
    private TopicDetail p;
    private String q = "index";
    private long r = 0;
    private boolean s = false;
    private d t = null;
    private int u = -1;
    private CancelBlockTopicActionRequest v;
    private BlockTopicActionRequest w;
    private TopImageConfigJson x;
    private cn.xiaochuankeji.tieba.api.topic.b y;
    private PostListFragment z;

    class a extends g {
        final /* synthetic */ TopicDetailActivity a;

        public a(TopicDetailActivity topicDetailActivity, FragmentManager fragmentManager) {
            this.a = topicDetailActivity;
            super(fragmentManager);
        }

        public Fragment getItem(int i) {
            if (i == 0) {
                if (this.a.z == null) {
                    this.a.z = PostListFragment.a(this.a.o, FragmentType.NEW);
                }
                return this.a.z;
            } else if (i != 1) {
                return null;
            } else {
                if (this.a.A == null) {
                    this.a.A = PostListFragment.a(this.a.o, FragmentType.HOT);
                }
                return this.a.A;
            }
        }

        public int getCount() {
            return 2;
        }
    }

    private static void J() {
        org.aspectj.a.b.b bVar = new org.aspectj.a.b.b("TopicDetailActivity.java", TopicDetailActivity.class);
        I = bVar.a("method-execution", bVar.a("4", "onCreate", "cn.xiaochuankeji.tieba.ui.topic.TopicDetailActivity", "android.os.Bundle", "savedInstanceState", "", "void"), 194);
    }

    static {
        J();
    }

    public static void a(Context context, Topic topic, String str, long j) {
        a(context, topic, false, str, j);
    }

    public static void a(Context context, Topic topic, String str) {
        a(context, topic, false, str, 0);
    }

    public static void a(Context context, Topic topic, boolean z, String str, long j) {
        Intent intent = new Intent(context, TopicDetailActivity.class);
        intent.setFlags(SQLiteDatabase.CREATE_IF_NECESSARY);
        intent.putExtra("topic_id", topic._topicID);
        intent.putExtra(g, topic);
        intent.putExtra("kShowShareView", z);
        if (context instanceof TopicActivity) {
            intent.putExtra("new_from", "topicTab");
        }
        intent.putExtra(e, str);
        intent.putExtra(f, j);
        context.startActivity(intent);
    }

    public static void a(Activity activity, long j, boolean z, String str, int i) {
        Intent intent = new Intent(activity, TopicDetailActivity.class);
        intent.putExtra("topic_id", j);
        intent.putExtra("kShowShareView", z);
        intent.putExtra(e, str);
        activity.startActivityForResult(intent, i);
    }

    public int j() {
        return this.u;
    }

    static final void a(TopicDetailActivity topicDetailActivity, Bundle bundle, org.aspectj.lang.a aVar) {
        super.onCreate(bundle);
        topicDetailActivity.G = cn.xiaochuankeji.tieba.background.utils.a.a.a().a(topicDetailActivity);
        d = 0;
        topicDetailActivity.x();
        topicDetailActivity.a.c();
        cn.xiaochuankeji.tieba.background.utils.h.a(topicDetailActivity, "zy_event_topicdetail_page", "页面进入");
    }

    protected void onCreate(Bundle bundle) {
        org.aspectj.lang.a a = org.aspectj.a.b.b.a(I, this, this, bundle);
        c.c().a(new h(new Object[]{this, bundle, a}).linkClosureAndJoinPoint(69648));
    }

    protected void onResume() {
        super.onResume();
        this.G.b();
        w();
        k();
        if (this.k != null) {
            this.k.a(this.i);
        }
        if (this.F) {
            this.h.postDelayed(new Runnable(this) {
                final /* synthetic */ TopicDetailActivity a;

                {
                    this.a = r1;
                }

                public void run() {
                    this.a.h.d();
                    this.a.F = false;
                }
            }, 5000);
        }
    }

    protected void onPause() {
        super.onPause();
        this.G.d();
        this.F = true;
        this.h.c();
        if (cn.xiaochuankeji.tieba.ui.voice.b.d.a().h() && cn.xiaochuankeji.tieba.ui.voice.b.d.a().i()) {
            cn.xiaochuankeji.tieba.ui.voice.b.d.a().f();
            cn.xiaochuankeji.tieba.ui.voice.b.a.a().a(cn.xiaochuankeji.tieba.ui.voice.b.d.a().b().a, cn.xiaochuankeji.tieba.ui.voice.b.d.a().b().f);
        }
    }

    protected void onDestroy() {
        super.onDestroy();
        cn.xiaochuankeji.tieba.background.utils.share.b.a().a(null);
        this.n.b(this);
        if (this.k != null) {
            this.k.c();
        }
        cn.xiaochuankeji.tieba.background.utils.a.a.a().b();
    }

    private void w() {
        if (this.y == null) {
            this.y = new cn.xiaochuankeji.tieba.api.topic.b();
        }
    }

    protected int a() {
        return R.layout.activity_topic_detail;
    }

    protected boolean a(Bundle bundle) {
        this.o = getIntent().getLongExtra("topic_id", 0);
        String stringExtra = getIntent().getStringExtra(e);
        if (stringExtra == null) {
            stringExtra = this.q;
        }
        this.q = stringExtra;
        this.r = getIntent().getLongExtra(f, 0);
        this.p = new TopicDetail(this.o);
        this.B = (Topic) getIntent().getSerializableExtra(g);
        if (this.B != null) {
            this.p._topic = this.B;
            this.p._isAttention = this.B._isAttention;
        }
        return true;
    }

    protected void c() {
        this.D = (PtrFrameLayout) findViewById(R.id.ptrFrameLayout);
        this.H = (PostLoadedTipsView) findViewById(R.id.tips_view);
        this.H.setVisibility(8);
        this.n = (StickyNavLayout) findViewById(R.id.stickyNavLayout);
        this.i = (ViewPager) findViewById(R.id.id_stickynavlayout_viewpager);
        this.i.setSaveEnabled(false);
        this.j = (MagicIndicator) findViewById(R.id.id_stickynavlayout_indicator);
        this.l = (FrameLayout) findViewById(R.id.id_stickynavlayout_topview);
        this.h = new v(this);
        this.l.addView(this.h, 0);
        this.h.a(this.p);
        if (getIntent().getExtras().getBoolean("kShowShareView")) {
            I();
        } else {
            I();
        }
    }

    protected void i_() {
        String[] strArr = new String[]{"新帖", "热门"};
        i bVar = new cn.xiaochuankeji.tieba.ui.widget.indicator.b(this);
        bVar.setAdjustMode(true);
        this.k = new o(strArr);
        bVar.setAdapter(this.k);
        this.j.setNavigator(bVar);
        this.i.setOnPageChangeListener(new OnPageChangeListener(this) {
            final /* synthetic */ TopicDetailActivity a;

            {
                this.a = r1;
            }

            public void onPageScrolled(int i, float f, int i2) {
                this.a.j.a(i, f, i2);
            }

            public void onPageSelected(int i) {
                this.a.j.a(i);
                if (cn.xiaochuankeji.tieba.ui.voice.b.d.a().h()) {
                    cn.xiaochuankeji.tieba.ui.voice.b.d.a().f();
                    cn.xiaochuankeji.tieba.ui.voice.b.a.a().a(cn.xiaochuankeji.tieba.ui.voice.b.d.a().b().a, cn.xiaochuankeji.tieba.ui.voice.b.d.a().b().f);
                }
                if (i == 0) {
                    if (this.a.u == 1) {
                        this.a.u = 0;
                        if (this.a.z != null) {
                            this.a.z.b(false);
                        }
                    }
                } else if (i == 1) {
                    if (!this.a.s) {
                        this.a.p.saveStamp();
                        this.a.s = true;
                    }
                    if (this.a.u == 0) {
                        if (this.a.A != null) {
                            this.a.A.b(false);
                        }
                        this.a.u = 1;
                    }
                }
            }

            public void onPageScrollStateChanged(int i) {
                this.a.j.b(i);
            }
        });
    }

    protected void j_() {
        this.h.b().setOnClickListener(this);
        this.n.a((cn.xiaochuankeji.tieba.ui.widget.StickyNavLayout.a) this);
    }

    private void x() {
        cn.xiaochuankeji.tieba.ui.widget.g.a((Activity) this, true);
        this.p.query(this, this.q, this.r, this.B == null ? null : this.B.click_cb);
    }

    public void onClick(View view) {
        if (this.h.b() == view && this.p._topic != null) {
            if (this.p._topic.flag == 0) {
                if (1 == this.p._isadm) {
                    f.a("提示", "你是本话题话事人,取消关注将取消话事人资格", this, new cn.xiaochuankeji.tieba.ui.widget.f.a(this) {
                        final /* synthetic */ TopicDetailActivity a;

                        {
                            this.a = r1;
                        }

                        public void a(boolean z) {
                            if (z) {
                                this.a.p._isadm = 0;
                                this.a.G();
                                this.a.C();
                            }
                        }
                    }, true);
                } else {
                    C();
                }
            } else if (this.p._topic.flag != 1) {
            } else {
                if (this.p._topic.role > 1) {
                    cn.xiaochuankeji.tieba.background.utils.g.b("身为话题的管理员，不能取消关注哦");
                } else {
                    C();
                }
            }
        }
    }

    public void s() {
        c(false);
    }

    private void c(boolean z) {
        if (this.p._topic != null) {
            SDBottomSheet sDBottomSheet = new SDBottomSheet(this, this);
            if (z) {
                sDBottomSheet.a(sDBottomSheet.c(), null);
            } else {
                ArrayList arrayList = new ArrayList();
                if (this.p._topic.flag == 0 && this.p._isadm == 1) {
                    arrayList.add(new SDBottomSheet.c(R.drawable.icon_option_edit_topic, "编辑话题", 11));
                    arrayList.add(new SDBottomSheet.c(R.drawable.icon_option_topic_admin, "卸任话事人", 13));
                }
                if (this.p.blocked == 1) {
                    arrayList.add(new SDBottomSheet.c(R.drawable.icon_option_topic_block, "取消屏蔽", 15));
                } else {
                    arrayList.add(new SDBottomSheet.c(R.drawable.icon_option_topic_block, "屏蔽该话题", 14));
                }
                sDBottomSheet.a(sDBottomSheet.c(), arrayList);
            }
            sDBottomSheet.b();
        }
    }

    public void a(int i) {
        if (i == 4 || i == 2 || i == 3 || i == 5 || i == 1) {
            b(i);
            return;
        }
        switch (i) {
            case 11:
                if (this.p._topic != null) {
                    TopicCreateActivity.a(this, this.p._topic, "kEditTopic", this.p._brief);
                    return;
                }
                return;
            case 12:
                if (!this.p._isAttention) {
                    cn.xiaochuankeji.tieba.background.utils.g.a("请先关注此话题,才能申请话事人");
                    return;
                }
                return;
            case 13:
                G();
                return;
            case 14:
                E();
                return;
            case 15:
                F();
                return;
            case 18:
                cn.xiaochuankeji.tieba.ui.utils.d.a("#最右#发现一个超给力的话题，快来围观！请戳链接>>" + cn.xiaochuankeji.tieba.background.utils.d.a.a(this.p._topicID));
                cn.xiaochuankeji.tieba.background.utils.g.a("复制成功");
                return;
            default:
                return;
        }
    }

    @l(a = ThreadMode.MAIN)
    public void postRemove(EventPostTopRemoved eventPostTopRemoved) {
        long j = eventPostTopRemoved.postId;
        if (j >= 0 && this.p.topPostInfos != null) {
            for (int i = 0; i < this.p.topPostInfos.size(); i++) {
                if (((TopPostInfo) this.p.topPostInfos.get(i)).pid == j) {
                    this.p.topPostInfos.remove(i);
                    B();
                    return;
                }
            }
        }
    }

    @l(a = ThreadMode.MAIN)
    public void postAdd(TopPostInfo topPostInfo) {
        if (topPostInfo != null && topPostInfo.img_id > 0) {
            com.izuiyou.a.a.b.b(topPostInfo.text);
            for (int i = 0; i < this.p.topPostInfos.size(); i++) {
                if (((TopPostInfo) this.p.topPostInfos.get(i)).pid == topPostInfo.pid) {
                    ((TopPostInfo) this.p.topPostInfos.get(i)).img_id = topPostInfo.img_id;
                    ((TopPostInfo) this.p.topPostInfos.get(i)).text = topPostInfo.text;
                    B();
                    return;
                }
            }
            if (this.p.topPostInfos != null) {
                this.p.topPostInfos.add(0, topPostInfo);
                B();
            }
        }
    }

    @l(a = ThreadMode.MAIN)
    public void createPost(cn.xiaochuankeji.tieba.ui.topic.data.a aVar) {
        if (this.z != null) {
            this.z.b(true);
        }
        if (this.i != null && this.i.getCurrentItem() != 0) {
            this.i.setCurrentItem(0, true);
        }
    }

    private void b(int i) {
        String str = null;
        String str2 = this.p._topicName;
        String a = cn.xiaochuankeji.tieba.background.utils.d.a.a(this.p._topicID);
        cn.htjyb.b.a z = z();
        if (z != null && z.hasLocalFile()) {
            str = z.cachePath();
        }
        WebPageShareStruct topicShareStruct = new TopicShareStruct(str2, str, a);
        cn.xiaochuankeji.tieba.background.utils.share.b a2 = cn.xiaochuankeji.tieba.background.utils.share.b.a();
        a2.a(new cn.xiaochuankeji.tieba.background.utils.share.b.a(this) {
            final /* synthetic */ TopicDetailActivity a;

            {
                this.a = r1;
            }

            public void a(boolean z) {
                this.a.y();
            }
        });
        a2.a(i, (Activity) this, topicShareStruct);
    }

    private void y() {
        new TopicShareReportRequest(this.p._topicID, null, new cn.xiaochuankeji.tieba.background.net.a.b<JSONObject>(this) {
            final /* synthetic */ TopicDetailActivity a;

            {
                this.a = r1;
            }

            public /* synthetic */ void onResponse(Object obj, Object obj2) {
                a((JSONObject) obj, obj2);
            }

            public void a(JSONObject jSONObject, Object obj) {
            }
        }, new cn.xiaochuankeji.tieba.background.net.a.a(this) {
            final /* synthetic */ TopicDetailActivity a;

            {
                this.a = r1;
            }

            public void onErrorResponse(XCError xCError, Object obj) {
            }
        }).execute();
    }

    private cn.htjyb.b.a z() {
        if (this.p._topic == null || 0 == this.p._topic._topicCoverID) {
            return null;
        }
        return cn.xiaochuankeji.tieba.background.a.f().a(Type.kTopicCover280, this.p._topic._topicCoverID);
    }

    public void t() {
        SDTopSheet sDTopSheet = new SDTopSheet(this, new SDTopSheet.b(this) {
            final /* synthetic */ TopicDetailActivity a;

            {
                this.a = r1;
            }

            public void a(int i) {
                if (cn.xiaochuankeji.tieba.ui.auth.a.a(this.a, "topic_detail", 1, 1)) {
                    this.a.c(i);
                }
            }
        });
        sDTopSheet.a("发帖子", R.drawable.icon_publish_post, 1);
        sDTopSheet.a("发跟拍", R.drawable.icon_publish_ugcvideo, 2);
        if (this.p.publishTypes.contains(Integer.valueOf(2)) && cn.xiaochuankeji.tieba.background.utils.c.a.c().b()) {
            sDTopSheet.a("发声音", R.drawable.icon_publish_voice, 3);
        }
        sDTopSheet.b();
    }

    public void u() {
        if (this.p._topic.role == 4) {
            if (this.p != null && this.p.topicPosts != null) {
                TopicManageActivity.a(this, this.p);
            }
        } else if (this.p._topic.role == 2 && this.p != null && this.p._topic != null) {
            ReportedPostActivity.a(this, this.p._topic._topicID);
        }
    }

    public void onQueryTopicDetailFinished(int i, List<cn.xiaochuankeji.tieba.ui.topic.data.c> list, ArrayList<AbstractPost> arrayList, boolean z, long j) {
        cn.xiaochuankeji.tieba.ui.widget.g.c(this);
        this.h.setDataBy(this.p);
        B();
        H();
        d = this.p._topic.post_report_count;
        k();
        A();
        PostListFragment postListFragment = (PostListFragment) this.m.a(0);
        PostListFragment postListFragment2 = (PostListFragment) this.m.a(1);
        if (postListFragment != null && postListFragment2 != null) {
            if (i == 1) {
                this.i.setCurrentItem(0);
                this.u = 0;
                postListFragment.a(list, this.p.next_list_cb, z, this.p.hot_type);
                return;
            }
            this.i.setCurrentItem(1);
            this.u = 1;
            postListFragment2.a(list, this.p.next_list_cb, z, this.p.hot_type);
        }
    }

    public void a(String str) {
        if (!TextUtils.isEmpty(str) && this.H != null) {
            this.H.setText(str);
            this.H.setVisibility(0);
            this.H.postDelayed(new Runnable(this) {
                final /* synthetic */ TopicDetailActivity a;

                {
                    this.a = r1;
                }

                public void run() {
                    this.a.H.setVisibility(8);
                }
            }, 1500);
        }
    }

    private void A() {
        if (this.i != null) {
            this.m = new a(this, getSupportFragmentManager());
            this.i.setAdapter(this.m);
        }
        if (this.p.hot_type == 2) {
            this.k.a(1, "推荐");
        }
    }

    public void k() {
        if (this.p._topic.flag != 1) {
            return;
        }
        if (this.p._topic.role == 2 || this.p._topic.role == 4) {
            this.a.setSecondOptionIcon(R.drawable.topic_nav_admin);
            if (d > 0) {
                this.a.a(true, d > 99 ? "99+" : "" + d);
                this.a.a(false);
            } else {
                this.a.a(false, "");
                if (1 == this.p._topic.apply_alert) {
                    this.a.a(true);
                }
            }
            this.a.d();
        }
    }

    public void onQueryTopicDetailFailed(String str) {
        cn.xiaochuankeji.tieba.ui.widget.g.c(this);
        cn.xiaochuankeji.tieba.background.utils.g.a(str);
    }

    protected void onActivityResult(int i, int i2, Intent intent) {
        super.onActivityResult(i, i2, intent);
    }

    private void B() {
        int i = 0;
        ArrayList arrayList = new ArrayList();
        List arrayList2 = new ArrayList();
        this.C = -1;
        if (this.p.topPostInfos == null || this.p.topPostInfos.size() <= 0) {
            this.h.a(null, null);
            return;
        }
        this.C = ((TopPostInfo) this.p.topPostInfos.get(0)).pid;
        while (i < this.p.topPostInfos.size()) {
            arrayList.add(cn.xiaochuankeji.tieba.background.f.b.b(((TopPostInfo) this.p.topPostInfos.get(i)).img_id).b());
            arrayList2.add(((TopPostInfo) this.p.topPostInfos.get(i)).text);
            com.izuiyou.a.a.b.b("top title:" + ((TopPostInfo) this.p.topPostInfos.get(i)).text);
            i++;
        }
        this.h.a(arrayList, arrayList2);
    }

    private void c(final int i) {
        if (this.p._topic != null) {
            if (this.y == null) {
                this.y = new cn.xiaochuankeji.tieba.api.topic.b();
            }
            this.y.b(this.p._topic._topicID).a(rx.a.b.a.a()).b(new j<QueryFobiddenJson>(this) {
                final /* synthetic */ TopicDetailActivity b;

                public /* synthetic */ void onNext(Object obj) {
                    a((QueryFobiddenJson) obj);
                }

                public void onCompleted() {
                }

                public void onError(Throwable th) {
                }

                public void a(QueryFobiddenJson queryFobiddenJson) {
                    if (queryFobiddenJson != null && queryFobiddenJson.isFobidden) {
                        cn.xiaochuankeji.tieba.background.utils.g.b("你已被限制在该话题内发帖，请更换话题");
                    } else if (i == 1) {
                        PublishPostActivity.a(this.b, this.b.p._topic, 2);
                    } else if (i == 2) {
                        VideoRecordActivity.a(this.b, 3, this.b.p._topic, "topicdetail");
                    } else if (i == 3) {
                        VoiceCreateActivity.a(this.b, this.b.p._topicName, this.b.p._topicID);
                    }
                }
            });
        }
    }

    private void C() {
        boolean z;
        int i = 1;
        if (this.p._isAttention) {
            cn.xiaochuankeji.tieba.background.utils.h.a(this, "zy_event_topicdetail_page", "取消关注话题");
        } else {
            cn.xiaochuankeji.tieba.background.utils.h.a(this, "zy_event_topicdetail_page", "关注话题");
        }
        TopicDetail topicDetail = this.p;
        if (this.p._isAttention) {
            z = false;
        } else {
            z = true;
        }
        topicDetail._isAttention = z;
        TopicDetail topicDetail2;
        if (this.p._isAttention) {
            topicDetail2 = this.p;
            topicDetail2.mUpTotalCount++;
        } else {
            topicDetail2 = this.p;
            topicDetail2.mUpTotalCount--;
        }
        this.h.a(this.p);
        Member member = new Member(cn.xiaochuankeji.tieba.background.a.g().c());
        Member q = cn.xiaochuankeji.tieba.background.a.g().q();
        member.setName(q.getName());
        member.setGender(q.getGender());
        member.setSign(q.getSign());
        member.setAvatarID(q.getAvatarID());
        if (cn.xiaochuankeji.tieba.background.a.g().d()) {
            i = 0;
        }
        member.setIsRegistered(i);
        if (!this.p._isAttention) {
            this.p.mUppedMembers.remove(member);
        } else if (member.isRegistered()) {
            this.p.mUppedMembers.add(0, member);
        } else {
            this.p.mUppedMembers.add(member);
        }
        org.greenrobot.eventbus.c.a().d(new MessageEvent(MessageEventType.MESSAGE_TOPIC_FOLLOWED_USERS));
        D();
        TopicUtilityClass.asynchSendFollowRequest(this.p._topicID, this.p._isAttention, "topicdetail", this.p._topic == null ? null : this.p._topic.click_cb);
    }

    private void D() {
        Topic topic = new Topic();
        topic._topicID = this.p._topicID;
        topic._isAttention = this.p._isAttention;
        MessageEvent messageEvent = new MessageEvent(MessageEventType.MESSAGE_TOPIC_TOGGLE_FOLLOW_ACTION);
        messageEvent.setData(topic);
        org.greenrobot.eventbus.c.a().d(messageEvent);
    }

    private void E() {
        if (this.w == null) {
            this.w = new BlockTopicActionRequest(this.p._topicID, cn.xiaochuankeji.tieba.background.a.g().a(), null, new cn.xiaochuankeji.tieba.background.net.a.b<JSONObject>(this) {
                final /* synthetic */ TopicDetailActivity a;

                {
                    this.a = r1;
                }

                public /* synthetic */ void onResponse(Object obj, Object obj2) {
                    a((JSONObject) obj, obj2);
                }

                public void a(JSONObject jSONObject, Object obj) {
                    cn.xiaochuankeji.tieba.background.utils.g.a("屏蔽成功,可在\"我的\"中取消");
                    this.a.p.blocked = 1;
                    this.a.w = null;
                }
            }, new cn.xiaochuankeji.tieba.background.net.a.a(this) {
                final /* synthetic */ TopicDetailActivity a;

                {
                    this.a = r1;
                }

                public void onErrorResponse(XCError xCError, Object obj) {
                    cn.xiaochuankeji.tieba.background.utils.g.a(xCError.getMessage());
                }
            });
            this.w.execute();
        }
    }

    private void F() {
        if (this.v == null) {
            this.v = new CancelBlockTopicActionRequest(this.p._topicID, cn.xiaochuankeji.tieba.background.a.g().a(), null, new cn.xiaochuankeji.tieba.background.net.a.b<JSONObject>(this) {
                final /* synthetic */ TopicDetailActivity a;

                {
                    this.a = r1;
                }

                public /* synthetic */ void onResponse(Object obj, Object obj2) {
                    a((JSONObject) obj, obj2);
                }

                public void a(JSONObject jSONObject, Object obj) {
                    this.a.p.blocked = 0;
                    cn.xiaochuankeji.tieba.background.utils.g.a("已不再屏蔽!");
                    this.a.v = null;
                }
            }, new cn.xiaochuankeji.tieba.background.net.a.a(this) {
                final /* synthetic */ TopicDetailActivity a;

                {
                    this.a = r1;
                }

                public void onErrorResponse(XCError xCError, Object obj) {
                    cn.xiaochuankeji.tieba.background.utils.g.a(xCError.getMessage());
                }
            });
            this.v.execute();
        }
    }

    private void G() {
        if (this.t == null) {
            String b = cn.xiaochuankeji.tieba.background.utils.d.a.b("/topic/retire_admin");
            JSONObject jSONObject = new JSONObject();
            cn.xiaochuankeji.tieba.background.utils.d.a.a(jSONObject);
            try {
                jSONObject.put("token", cn.xiaochuankeji.tieba.background.a.g().a());
                jSONObject.put("tid", this.p._topicID);
            } catch (JSONException e) {
                e.printStackTrace();
            }
            this.t = new cn.htjyb.netlib.f(b, cn.xiaochuankeji.tieba.background.a.d(), jSONObject, new cn.htjyb.netlib.d.a(this) {
                final /* synthetic */ TopicDetailActivity a;

                {
                    this.a = r1;
                }

                public void onTaskFinish(d dVar) {
                    this.a.t = null;
                    if (dVar.c.a) {
                        this.a.p._isadm = 0;
                        cn.xiaochuankeji.tieba.background.utils.g.a("您已卸任该话题话事人");
                        return;
                    }
                    cn.xiaochuankeji.tieba.background.utils.g.a(dVar.c.c());
                }
            });
            this.t.b();
        }
    }

    public void a_(int i) {
        if (i >= e.a(40.0f)) {
            this.a.setTitle(this.p._topicName);
            this.a.b(true);
        } else {
            this.a.setTitle(null);
            this.a.b(false);
        }
        if (i > 0) {
            this.E = false;
        } else {
            this.E = true;
        }
    }

    public void v() {
        if (this.D != null && this.E) {
            this.D.c();
        }
    }

    private void H() {
        if (this.p != null && this.p._topic != null && this.p._topic.role >= 4) {
            final cn.xiaochuankeji.tieba.api.config.a aVar = new cn.xiaochuankeji.tieba.api.config.a();
            rx.d.a(Boolean.valueOf(true)).d(new rx.b.g<Boolean, cn.xiaochuankeji.tieba.a.a>(this) {
                final /* synthetic */ TopicDetailActivity a;

                {
                    this.a = r1;
                }

                public /* synthetic */ Object call(Object obj) {
                    return a((Boolean) obj);
                }

                public cn.xiaochuankeji.tieba.a.a a(Boolean bool) {
                    return cn.xiaochuankeji.tieba.a.b.a("top_image");
                }
            }).b(rx.f.a.c()).c(new rx.b.g<cn.xiaochuankeji.tieba.a.a, rx.d<TopImageConfigJson>>(this) {
                final /* synthetic */ TopicDetailActivity b;

                public /* synthetic */ Object call(Object obj) {
                    return a((cn.xiaochuankeji.tieba.a.a) obj);
                }

                public rx.d<TopImageConfigJson> a(cn.xiaochuankeji.tieba.a.a aVar) {
                    int i = aVar == null ? 0 : aVar.d;
                    if (!(aVar == null || TextUtils.isEmpty(aVar.e))) {
                        this.b.x = (TopImageConfigJson) JSON.parseObject(aVar.e, TopImageConfigJson.class);
                        this.b.x.dbId = aVar.a;
                        com.izuiyou.a.a.b.b("topimage_config:" + JSON.toJSONString(this.b.x));
                    }
                    return aVar.a(i);
                }
            }).b(rx.f.a.c()).a(rx.f.a.c()).b(new j<TopImageConfigJson>(this) {
                final /* synthetic */ TopicDetailActivity a;

                {
                    this.a = r1;
                }

                public /* synthetic */ void onNext(Object obj) {
                    a((TopImageConfigJson) obj);
                }

                public void onCompleted() {
                }

                public void onError(Throwable th) {
                    th.printStackTrace();
                }

                public void a(TopImageConfigJson topImageConfigJson) {
                    com.izuiyou.a.a.b.b("get config json:" + JSON.toJSONString(topImageConfigJson));
                    if ((this.a.x == null || topImageConfigJson.imageVersion > this.a.x.imageVersion) && topImageConfigJson != null && topImageConfigJson.imageIdList != null) {
                        long j = this.a.x == null ? -1 : this.a.x.dbId;
                        cn.xiaochuankeji.tieba.a.a aVar = new cn.xiaochuankeji.tieba.a.a();
                        aVar.b = System.currentTimeMillis() / 1000;
                        aVar.c = "top_image";
                        aVar.e = JSON.toJSONString(topImageConfigJson);
                        aVar.d = topImageConfigJson.imageVersion;
                        cn.xiaochuankeji.tieba.a.b.a(j, aVar);
                        this.a.x = topImageConfigJson;
                    }
                }
            });
        }
    }

    public int a(long j) throws IllegalStateException {
        if (j == this.o) {
            return this.p._topic.role;
        }
        throw new IllegalStateException("不是同一个话题");
    }

    private void I() {
        in.srain.cube.views.ptr.b eVar = new cn.xiaochuankeji.tieba.ui.homepage.e(this, R.color.CB);
        this.D.a(eVar);
        this.D.setHeaderView(eVar);
        this.D.a(true);
        this.D.setPtrHandler(new in.srain.cube.views.ptr.a(this) {
            final /* synthetic */ TopicDetailActivity a;

            {
                this.a = r1;
            }

            public boolean a(PtrFrameLayout ptrFrameLayout, View view, View view2) {
                return this.a.E;
            }

            public void a(PtrFrameLayout ptrFrameLayout) {
                if (this.a.i == null) {
                    this.a.v();
                } else if (this.a.i.getCurrentItem() == 0 && this.a.z != null) {
                    this.a.z.a(new cn.xiaochuankeji.tieba.ui.topic.ui.PostListFragment.a(this) {
                        final /* synthetic */ AnonymousClass10 a;

                        {
                            this.a = r1;
                        }

                        public void a(int i) {
                            this.a.a.D.c();
                        }
                    });
                } else if (this.a.A != null) {
                    this.a.A.a(new cn.xiaochuankeji.tieba.ui.topic.ui.PostListFragment.a(this) {
                        final /* synthetic */ AnonymousClass10 a;

                        {
                            this.a = r1;
                        }

                        public void a(int i) {
                            this.a.a.D.c();
                            if (i != 0) {
                                this.a.a.a("为您推荐了" + i + "条新内容");
                            }
                        }
                    });
                }
            }
        });
    }
}
