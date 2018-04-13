package cn.xiaochuankeji.tieba.ui.publish.selecttopic;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.RecyclerView.Adapter;
import android.support.v7.widget.RecyclerView.LayoutManager;
import android.text.TextUtils;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.BaseAdapter;
import android.widget.FrameLayout;
import android.widget.FrameLayout.LayoutParams;
import android.widget.LinearLayout;
import cn.htjyb.b.a.b.b;
import cn.htjyb.ui.widget.headfooterlistview.QueryListView;
import cn.htjyb.ui.widget.headfooterlistview.QueryListView.EmptyPaddingStyle;
import cn.xiaochuankeji.aop.permission.c;
import cn.xiaochuankeji.tieba.R;
import cn.xiaochuankeji.tieba.background.topic.Category;
import cn.xiaochuankeji.tieba.background.topic.RecommendTopicInitDataInPublishModel;
import cn.xiaochuankeji.tieba.background.topic.RecommendTopicInitDataInPublishModel.CallBack;
import cn.xiaochuankeji.tieba.background.topic.RecommendTopicWhenPublishList;
import cn.xiaochuankeji.tieba.background.topic.SelectTopicCacheInActivityCycle;
import cn.xiaochuankeji.tieba.background.topic.SelectTopicCacheInActivityCycle.TopicCacheData;
import cn.xiaochuankeji.tieba.background.topic.Topic;
import cn.xiaochuankeji.tieba.background.topic.TopicHistoryRecordManager;
import cn.xiaochuankeji.tieba.background.topic.TopicHistoryRecordManager.Type;
import cn.xiaochuankeji.tieba.background.topic.TopicSearcher;
import cn.xiaochuankeji.tieba.background.utils.g;
import cn.xiaochuankeji.tieba.ui.base.a;
import cn.xiaochuankeji.tieba.ui.topic.o;
import cn.xiaochuankeji.tieba.ui.topic.p;
import cn.xiaochuankeji.tieba.ui.utils.e;
import cn.xiaochuankeji.tieba.ui.widget.h;
import java.util.ArrayList;

public class SelectTopicActivity extends a implements OnItemClickListener, b, a.a, h.a {
    private static Context g;
    private static final org.aspectj.lang.a.a r = null;
    private h a;
    private FrameLayout b;
    private RecyclerView c;
    private QueryListView d;
    private QueryListView e;
    private LinearLayout f;
    private Context h;
    private String i;
    private TopicSearcher j;
    private RecommendTopicInitDataInPublishModel k;
    private RecommendTopicWhenPublishList l;
    private TopicHistoryRecordManager m;
    private b n;
    private SelectTopicCacheInActivityCycle o;
    private int p;
    private cn.xiaochuankeji.tieba.background.utils.a.h q;

    static {
        r();
    }

    private static void r() {
        org.aspectj.a.b.b bVar = new org.aspectj.a.b.b("SelectTopicActivity.java", SelectTopicActivity.class);
        r = bVar.a("method-execution", bVar.a("4", "onCreate", "cn.xiaochuankeji.tieba.ui.publish.selecttopic.SelectTopicActivity", "android.os.Bundle", "savedInstanceState", "", "void"), 90);
    }

    public static void a(Activity activity, int i, int i2) {
        g = activity;
        Intent intent = new Intent(activity, SelectTopicActivity.class);
        intent.putExtra("PARAM_ACTION_TYPE", i2);
        intent.setFlags(1073741824);
        activity.startActivityForResult(intent, i);
    }

    static final void a(SelectTopicActivity selectTopicActivity, Bundle bundle, org.aspectj.lang.a aVar) {
        super.onCreate(bundle);
        selectTopicActivity.e();
    }

    protected void onCreate(Bundle bundle) {
        org.aspectj.lang.a a = org.aspectj.a.b.b.a(r, this, this, bundle);
        c.c().a(new c(new Object[]{this, bundle, a}).linkClosureAndJoinPoint(69648));
    }

    private void e() {
        this.k.query(new CallBack(this) {
            final /* synthetic */ SelectTopicActivity a;

            {
                this.a = r1;
            }

            public void queryFinish(boolean z, String str) {
                if (z) {
                    this.a.k();
                    this.a.p();
                    return;
                }
                g.b(str);
            }
        });
    }

    private void k() {
        boolean z = true;
        long currentCId = this.k.getCurrentCId();
        long offset = this.k.getOffset();
        int more = this.k.getMore();
        ArrayList arrayList = (ArrayList) this.k.getTopicList().clone();
        SelectTopicCacheInActivityCycle selectTopicCacheInActivityCycle = this.o;
        if (more != 1) {
            z = false;
        }
        selectTopicCacheInActivityCycle.save(currentCId, arrayList, z, offset);
    }

    private void p() {
        long j;
        ArrayList arrayList = (ArrayList) this.k.getTopicCategorys().clone();
        if (this.m.getTopics().size() > 0) {
            j = -111;
            arrayList.add(0, new Category(-111, "最近"));
        } else {
            j = this.k.getCurrentCId();
        }
        Adapter aVar = new a(this, arrayList, j);
        this.c.setAdapter(aVar);
        aVar.a(this);
        a(j);
    }

    protected int a() {
        return R.layout.activity_select_topic;
    }

    protected boolean a(Bundle bundle) {
        this.p = getIntent().getExtras().getInt("PARAM_ACTION_TYPE", 0);
        this.k = RecommendTopicInitDataInPublishModel.getInstance();
        this.l = new RecommendTopicWhenPublishList();
        this.l.registerOnQueryFinishListener(this);
        this.m = TopicHistoryRecordManager.getInstance(Type.kSelect);
        this.o = SelectTopicCacheInActivityCycle.getInstance();
        this.h = g;
        g = null;
        this.j = new TopicSearcher();
        this.q = new cn.xiaochuankeji.tieba.background.utils.a.h();
        return true;
    }

    protected void c() {
        this.b = (FrameLayout) findViewById(R.id.navBar);
        this.f = (LinearLayout) findViewById(R.id.llCategoryContainer);
        this.a = new h(this);
        this.b.addView(this.a.f_(), 0, new LayoutParams(-1, getResources().getDimensionPixelOffset(R.dimen.navbar_height)));
        this.c = (RecyclerView) findViewById(R.id.rvCategory);
        LayoutManager linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(1);
        this.c.setLayoutManager(linearLayoutManager);
        this.d = (QueryListView) findViewById(R.id.lvContent);
        this.e = (QueryListView) findViewById(R.id.lvSearchContent);
        this.e.setVisibility(8);
    }

    protected void i_() {
        BaseAdapter pVar = new p(this, this.j);
        this.e.f();
        this.e.a(this.j, pVar);
        pVar = new p(this, this.l);
        this.d.f();
        this.d.a(this.l, pVar);
        this.d.a("空空如也~", (int) R.drawable.ic_post_empty, EmptyPaddingStyle.PADDING20);
        this.d.m().setPadding(0, e.a(8.0f), 0, 0);
        this.a.a("搜索话题", this);
    }

    private void a(long j) {
        int i = 0;
        if (j == -111) {
            ArrayList topics = this.m.getTopics();
            this.l.init(topics, (long) topics.size(), 0);
            return;
        }
        this.l.clear();
        this.l.setCategoryId(j);
        TopicCacheData topicListBy = this.o.getTopicListBy(j);
        if (topicListBy != null) {
            RecommendTopicWhenPublishList recommendTopicWhenPublishList = this.l;
            topics = (ArrayList) topicListBy.topics.clone();
            long j2 = topicListBy.offset;
            if (topicListBy.more) {
                i = 1;
            }
            recommendTopicWhenPublishList.init(topics, j2, i);
            return;
        }
        this.l.refresh();
    }

    public void a(View view, long j) {
        a(j);
    }

    protected void j_() {
        this.e.m().setOnItemClickListener(this);
        this.d.m().setOnItemClickListener(this);
        TopicSearcher topicSearcher = this.j;
        b anonymousClass2 = new b(this) {
            final /* synthetic */ SelectTopicActivity a;

            {
                this.a = r1;
            }

            public void a(boolean z, boolean z2, String str) {
                cn.xiaochuankeji.tieba.ui.widget.g.c(this.a);
                if (!z) {
                    g.b(str);
                }
            }
        };
        this.n = anonymousClass2;
        topicSearcher.registerOnQueryFinishListener(anonymousClass2);
    }

    private void q() {
        cn.xiaochuankeji.tieba.ui.widget.g.a((Activity) this, true);
        this.j.clear();
        this.j.setSearchKey(this.i);
        this.q.a(this.i);
        this.j.cancelQuery();
        this.j.refresh();
    }

    public void onItemClick(AdapterView<?> adapterView, View view, int i, long j) {
        Topic topic;
        int i2 = 0;
        int headerViewsCount;
        if (adapterView == this.d.m()) {
            i2 = i - this.d.m().getHeaderViewsCount();
            if (view.getTag() != null && (view.getTag() instanceof o)) {
                headerViewsCount = i - this.d.m().getHeaderViewsCount();
                if (headerViewsCount >= 0 && headerViewsCount < this.l.itemCount()) {
                    topic = (Topic) this.l.itemAt(headerViewsCount);
                } else {
                    return;
                }
            }
            topic = null;
        } else {
            if (adapterView == this.e.m()) {
                i2 = i - this.e.m().getHeaderViewsCount();
                if (view.getTag() != null && (view.getTag() instanceof o)) {
                    headerViewsCount = i - this.e.m().getHeaderViewsCount();
                    if (headerViewsCount >= 0 && headerViewsCount < this.j.itemCount()) {
                        topic = (Topic) this.j.itemAt(headerViewsCount);
                    } else {
                        return;
                    }
                }
            }
            topic = null;
        }
        if (topic != null) {
            this.q.a("topicsug", topic._topicID, "select", i2);
            a(topic);
        }
    }

    private void a(Topic topic) {
        cn.htjyb.c.a.a((Activity) this);
        Intent intent = new Intent();
        intent.putExtra("PARAM_ACTION_TYPE", this.p);
        topic.fillToIntent(intent);
        setResult(-1, intent);
        finish();
    }

    public void a(String str) {
        this.i = str;
        if (TextUtils.isEmpty(this.i)) {
            this.f.setVisibility(0);
            this.e.setVisibility(8);
            return;
        }
        this.f.setVisibility(8);
        this.e.setVisibility(0);
        this.i = this.i.trim();
        q();
    }

    public void j() {
        cn.htjyb.c.a.a((Activity) this);
        finish();
    }

    public void a(boolean z, boolean z2, String str) {
        if (z) {
            long cId = this.l.getCId();
            ArrayList items = this.l.getItems();
            if (items.size() > 0) {
                this.o.save(cId, (ArrayList) items.clone(), this.l.hasMore(), this.l.getQueryMoreOffset());
                return;
            }
            return;
        }
        g.b(str);
    }

    protected void onDestroy() {
        super.onDestroy();
        this.j.cancelQuery();
        this.j.unregisterOnQueryFinishedListener(this.n);
        this.o.clear();
        this.l.unregisterOnQueryFinishedListener(this);
    }
}
