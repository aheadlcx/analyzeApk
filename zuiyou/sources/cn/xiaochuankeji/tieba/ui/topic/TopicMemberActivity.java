package cn.xiaochuankeji.tieba.ui.topic;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.RecyclerView.LayoutManager;
import android.support.v7.widget.RecyclerView.OnScrollListener;
import android.view.View;
import cn.xiaochuankeji.aop.permission.c;
import cn.xiaochuankeji.tieba.R;
import cn.xiaochuankeji.tieba.api.topic.b;
import cn.xiaochuankeji.tieba.background.topic.Topic;
import cn.xiaochuankeji.tieba.background.utils.g;
import cn.xiaochuankeji.tieba.json.topic.TopicFollowerListJson;
import cn.xiaochuankeji.tieba.json.topic.TopicRoledListJson;
import cn.xiaochuankeji.tieba.network.custom.exception.ClientErrorException;
import cn.xiaochuankeji.tieba.ui.base.h;
import com.tencent.wcdb.database.SQLiteDatabase;
import org.aspectj.lang.a.a;
import rx.j;

public class TopicMemberActivity extends h {
    private static final a l = null;
    private RecyclerView d;
    private Topic e;
    private int f = 0;
    private int g = 0;
    private j h;
    private b i;
    private int j = 0;
    private View k;

    static {
        v();
    }

    private static void v() {
        org.aspectj.a.b.b bVar = new org.aspectj.a.b.b("TopicMemberActivity.java", TopicMemberActivity.class);
        l = bVar.a("method-execution", bVar.a("1", "onCreate", "cn.xiaochuankeji.tieba.ui.topic.TopicMemberActivity", "android.os.Bundle", "savedInstanceState", "", "void"), 46);
    }

    public static void a(Context context, Topic topic) {
        Intent intent = new Intent(context, TopicMemberActivity.class);
        intent.setFlags(SQLiteDatabase.CREATE_IF_NECESSARY);
        intent.putExtra("TOPIC", topic);
        context.startActivity(intent);
    }

    static final void a(TopicMemberActivity topicMemberActivity, Bundle bundle, org.aspectj.lang.a aVar) {
        topicMemberActivity.e = (Topic) topicMemberActivity.getIntent().getSerializableExtra("TOPIC");
        super.onCreate(bundle);
    }

    public void onCreate(Bundle bundle) {
        org.aspectj.lang.a a = org.aspectj.a.b.b.a(l, this, this, bundle);
        c.c().a(new r(new Object[]{this, bundle, a}).linkClosureAndJoinPoint(69648));
    }

    protected int a() {
        return R.layout.activity_topic_member;
    }

    protected void c() {
        super.c();
        this.d = (RecyclerView) findViewById(R.id.list_member);
        this.i = new b();
    }

    protected void i_() {
        this.i = new b();
        this.h = new j(this, this.e._topicID, this.e._topicName, this.e._topicCoverID);
        final LayoutManager linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(1);
        this.d.setLayoutManager(linearLayoutManager);
        this.h.a(this.e.recruiting, this.e.escort_recruiting);
        this.d.setAdapter(this.h);
        this.k = findViewById(R.id.view_load_more);
        this.d.setOnScrollListener(new OnScrollListener(this) {
            final /* synthetic */ TopicMemberActivity b;

            public void onScrollStateChanged(RecyclerView recyclerView, int i) {
                super.onScrollStateChanged(recyclerView, i);
                if (i == 0 && this.b.g + 1 == this.b.h.getItemCount() && this.b.j == 1) {
                    this.b.k.setVisibility(0);
                    this.b.k();
                }
            }

            public void onScrolled(RecyclerView recyclerView, int i, int i2) {
                super.onScrolled(recyclerView, i, i2);
                this.b.g = linearLayoutManager.findLastVisibleItemPosition();
            }
        });
        j();
    }

    private void j() {
        com.izuiyou.a.a.b.b("start init memberlist");
        this.i.a(this.e._topicID).a(rx.a.b.a.a()).b(new j<TopicRoledListJson>(this) {
            final /* synthetic */ TopicMemberActivity a;

            {
                this.a = r1;
            }

            public /* synthetic */ void onNext(Object obj) {
                a((TopicRoledListJson) obj);
            }

            public void onCompleted() {
            }

            public void onError(Throwable th) {
                if (th instanceof ClientErrorException) {
                    g.a(th.getMessage());
                    return;
                }
                th.printStackTrace();
                g.a("网络错误");
            }

            public void a(TopicRoledListJson topicRoledListJson) {
                if (topicRoledListJson != null) {
                    this.a.h.a(topicRoledListJson.talentShow);
                    this.a.h.a(topicRoledListJson.adminMembers, topicRoledListJson.guardMembers, topicRoledListJson.talentMembers);
                }
            }
        });
        this.i.a(this.e._topicID, 0).a(rx.a.b.a.a()).b(new j<TopicFollowerListJson>(this) {
            final /* synthetic */ TopicMemberActivity a;

            {
                this.a = r1;
            }

            public /* synthetic */ void onNext(Object obj) {
                a((TopicFollowerListJson) obj);
            }

            public void onCompleted() {
            }

            public void onError(Throwable th) {
                if (th instanceof ClientErrorException) {
                    g.a(th.getMessage());
                    return;
                }
                th.printStackTrace();
                g.a("网络错误");
            }

            public void a(TopicFollowerListJson topicFollowerListJson) {
                if (topicFollowerListJson != null) {
                    this.a.f = topicFollowerListJson.offset;
                    this.a.j = topicFollowerListJson.hasMore;
                    this.a.h.a(topicFollowerListJson.followerList);
                }
            }
        });
    }

    private void k() {
        this.i.a(this.e._topicID, this.f).a(rx.a.b.a.a()).b(new j<TopicFollowerListJson>(this) {
            final /* synthetic */ TopicMemberActivity a;

            {
                this.a = r1;
            }

            public /* synthetic */ void onNext(Object obj) {
                a((TopicFollowerListJson) obj);
            }

            public void onCompleted() {
            }

            public void onError(Throwable th) {
                this.a.k.setVisibility(8);
            }

            public void a(TopicFollowerListJson topicFollowerListJson) {
                if (topicFollowerListJson.followerList == null || topicFollowerListJson.followerList.size() == 0) {
                    g.a("没有更多了");
                } else {
                    this.a.h.a(topicFollowerListJson.followerList);
                    this.a.f = this.a.f + topicFollowerListJson.followerList.size();
                    this.a.j = topicFollowerListJson.hasMore;
                }
                this.a.k.setVisibility(8);
            }
        });
    }
}
