package cn.xiaochuankeji.tieba.ui.topic;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.RecyclerView.LayoutManager;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import cn.xiaochuankeji.aop.permission.c;
import cn.xiaochuankeji.tieba.R;
import cn.xiaochuankeji.tieba.api.topic.b;
import cn.xiaochuankeji.tieba.background.data.post.AbstractPost;
import cn.xiaochuankeji.tieba.background.data.post.Post;
import cn.xiaochuankeji.tieba.background.topic.TopicDetail;
import cn.xiaochuankeji.tieba.background.topic.TopicDetail.OnQueryTopicDetailFinishedListener;
import cn.xiaochuankeji.tieba.background.topic.TopicDetail.TopPostInfo;
import cn.xiaochuankeji.tieba.background.utils.g;
import cn.xiaochuankeji.tieba.json.topic.TopicRoledListJson;
import cn.xiaochuankeji.tieba.network.custom.exception.ClientErrorException;
import cn.xiaochuankeji.tieba.ui.base.h;
import cn.xiaochuankeji.tieba.ui.topic.weight.FullyLinearLayoutManager;
import cn.xiaochuankeji.tieba.ui.widget.NavigationBar;
import cn.xiaochuankeji.tieba.ui.widget.image.WebImageView;
import cn.xiaochuankeji.tieba.webview.WebActivity;
import java.util.ArrayList;
import java.util.List;
import org.aspectj.lang.a.a;
import rx.j;

public class TopicManageActivity extends h implements OnClickListener, OnQueryTopicDetailFinishedListener {
    private static final a x = null;
    private View d;
    private TextView e;
    private View f;
    private TopicDetail g;
    private RecyclerView h;
    private Button i;
    private RelativeLayout j;
    private TextView k;
    private b l;
    private WebImageView m;
    private e n;
    private RelativeLayout o;
    private RelativeLayout p;
    private LinearLayout q;
    private TextView r;
    private TextView s;
    private RecyclerView t;
    private i u;
    private RelativeLayout v;
    private NavigationBar w;

    static {
        x();
    }

    private static void x() {
        org.aspectj.a.b.b bVar = new org.aspectj.a.b.b("TopicManageActivity.java", TopicManageActivity.class);
        x = bVar.a("method-execution", bVar.a("4", "onCreate", "cn.xiaochuankeji.tieba.ui.topic.TopicManageActivity", "android.os.Bundle", "savedInstance", "", "void"), 127);
    }

    public static void a(Context context, TopicDetail topicDetail) {
        Intent intent = new Intent(context, TopicManageActivity.class);
        intent.putExtra("topic_detail", topicDetail);
        context.startActivity(intent);
    }

    protected int a() {
        return R.layout.activity_topic_manage;
    }

    protected void i_() {
        findViewById(R.id.layout_topic_info).setOnClickListener(this);
        this.p = (RelativeLayout) findViewById(R.id.rootView);
        this.h = (RecyclerView) findViewById(R.id.list_topic_admin);
        LayoutManager linearLayoutManager = new LinearLayoutManager(this, 1, false);
        this.h.setLayoutManager(linearLayoutManager);
        linearLayoutManager.setAutoMeasureEnabled(true);
        this.n = new e(this, this.g._topic.role, this.g._topicID);
        this.h.setAdapter(this.n);
        this.h.setNestedScrollingEnabled(false);
        this.i = (Button) findViewById(R.id.btn_recruit);
        this.i.setOnClickListener(this);
        this.j = (RelativeLayout) findViewById(R.id.layout_recruit_continue);
        this.k = (TextView) findViewById(R.id.tv_apply_number);
        findViewById(R.id.layout_manage_top).setOnClickListener(this);
        this.m = (WebImageView) findViewById(R.id.iv_topic_logo);
        this.o = (RelativeLayout) findViewById(R.id.layout_recruit);
        this.w = (NavigationBar) findViewById(R.id.navBar);
        this.w.setListener(this);
        this.q = (LinearLayout) findViewById(R.id.rl_escort);
        this.r = (TextView) findViewById(R.id.tv_escort_num);
        this.v = (RelativeLayout) findViewById(R.id.layout_escort_continue);
        this.s = (TextView) findViewById(R.id.tv_escort_number);
        this.t = (RecyclerView) findViewById(R.id.list_topic_escort);
        linearLayoutManager = new FullyLinearLayoutManager((Context) this, 1, false);
        this.t.setLayoutManager(linearLayoutManager);
        linearLayoutManager.setAutoMeasureEnabled(true);
        this.u = new i(this, this.g._topic.role, this.g._topicID);
        this.t.setAdapter(this.u);
        this.t.setNestedScrollingEnabled(false);
        this.f = findViewById(R.id.reported_post_tip);
        this.d = findViewById(R.id.layout_manage_reported);
        this.d.setOnClickListener(this);
        this.e = (TextView) findViewById(R.id.reported_post_num);
        w();
    }

    static final void a(TopicManageActivity topicManageActivity, Bundle bundle, org.aspectj.lang.a aVar) {
        topicManageActivity.g = (TopicDetail) topicManageActivity.getIntent().getSerializableExtra("topic_detail");
        topicManageActivity.l = new b();
        super.onCreate(bundle);
    }

    protected void onCreate(Bundle bundle) {
        org.aspectj.lang.a a = org.aspectj.a.b.b.a(x, this, this, bundle);
        c.c().a(new q(new Object[]{this, bundle, a}).linkClosureAndJoinPoint(69648));
    }

    protected void onResume() {
        super.onResume();
        this.g.query(this, "topic", 0, null);
        v();
        if (TopicDetailActivity.d > 0) {
            this.f.setVisibility(0);
            this.e.setVisibility(0);
            this.e.setText(TopicDetailActivity.d > 99 ? "99+" : "" + TopicDetailActivity.d);
            return;
        }
        this.f.setVisibility(8);
        this.e.setVisibility(8);
    }

    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.layout_topic_info:
                TopicCreateActivity.a(this, this.g._topic, "kEditTopic", this.g._brief);
                return;
            case R.id.layout_manage_top:
                if (this.g.topPostInfos == null || this.g.topPostInfos.size() <= 0) {
                    g.a("当前没有置顶帖子");
                    return;
                }
                List arrayList = new ArrayList();
                if (this.g.topicPosts != null) {
                    for (int i = 0; i < this.g.topicPosts.size(); i++) {
                        for (int i2 = 0; i2 < this.g.topPostInfos.size(); i2++) {
                            if (((Post) this.g.topicPosts.get(i))._ID == ((TopPostInfo) this.g.topPostInfos.get(i2)).pid) {
                                arrayList.add(this.g.topicPosts.get(i));
                            }
                        }
                    }
                }
                TopicTopManageActivity.a(this, this.g.topPostInfos, this.g._topicID, arrayList);
                return;
            case R.id.layout_manage_reported:
                ReportedPostActivity.a(this, this.g._topic._topicID);
                return;
            case R.id.btn_recruit:
                k();
                return;
            case R.id.layout_recruit_continue:
                TopicAdminManageActivity.a((Context) this, this.g._topicID);
                return;
            case R.id.layout_escort_continue:
                TopicEscortApplyActivity.a((Context) this, this.g._topicID);
                return;
            default:
                return;
        }
    }

    public void onQueryTopicDetailFinished(int i, List<cn.xiaochuankeji.tieba.ui.topic.data.c> list, ArrayList<AbstractPost> arrayList, boolean z, long j) {
        w();
    }

    public void onQueryTopicDetailFailed(String str) {
    }

    public void t() {
        WebActivity.a(this, cn.xiaochuan.jsbridge.b.a("话事人规则说明", cn.xiaochuankeji.tieba.background.utils.d.a.d("https://$$/help/topic/rule")));
    }

    private void j() {
        if (this.g._topic.recruiting == 1) {
            this.o.setVisibility(8);
            this.j.setVisibility(0);
            this.k.setText(this.g._topic.apply_num + "");
            this.j.setOnClickListener(this);
        } else {
            this.o.setVisibility(0);
            this.j.setVisibility(8);
        }
        if (this.g._topic.escort_enable == 1) {
            this.q.setVisibility(0);
            if (this.g._topic.escort_recruiting == 1) {
                this.v.setVisibility(0);
                this.s.setText(this.g._topic.escort_apply_num + "");
                this.v.setOnClickListener(this);
                return;
            }
            this.v.setVisibility(8);
            return;
        }
        this.q.setVisibility(8);
    }

    private void k() {
        this.l.b(this.g._topicID, "open").a(rx.a.b.a.a()).b(new j<Void>(this) {
            final /* synthetic */ TopicManageActivity a;

            {
                this.a = r1;
            }

            public /* synthetic */ void onNext(Object obj) {
                a((Void) obj);
            }

            public void onCompleted() {
            }

            public void onError(Throwable th) {
                if (th instanceof ClientErrorException) {
                    g.a(th.getMessage());
                }
            }

            public void a(Void voidR) {
                this.a.g._topic.recruiting = 1;
                this.a.j();
            }
        });
    }

    private void v() {
        this.l.a(this.g._topicID).a(rx.a.b.a.a()).b(new j<TopicRoledListJson>(this) {
            final /* synthetic */ TopicManageActivity a;

            {
                this.a = r1;
            }

            public /* synthetic */ void onNext(Object obj) {
                a((TopicRoledListJson) obj);
            }

            public void onCompleted() {
            }

            public void onError(Throwable th) {
                th.printStackTrace();
            }

            public void a(TopicRoledListJson topicRoledListJson) {
                if (topicRoledListJson != null) {
                    this.a.n.a(topicRoledListJson.adminMembers);
                    this.a.u.a(topicRoledListJson.guardMembers);
                    this.a.r.setText("护卫队（" + topicRoledListJson.guardMembers.size() + "）");
                }
            }
        });
    }

    private void w() {
        this.m.setWebImage(cn.xiaochuankeji.tieba.background.f.b.c(this.g._topic._topicCoverID, false));
        ((TextView) findViewById(R.id.tv_topic_name)).setText(this.g._topic._topicName);
        j();
    }
}
