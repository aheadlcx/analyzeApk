package cn.xiaochuankeji.tieba.ui.topic;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.RecyclerView.LayoutManager;
import android.support.v7.widget.RecyclerView.OnScrollListener;
import android.widget.RelativeLayout;
import cn.xiaochuankeji.aop.permission.c;
import cn.xiaochuankeji.tieba.R;
import cn.xiaochuankeji.tieba.api.topic.b;
import cn.xiaochuankeji.tieba.background.utils.g;
import cn.xiaochuankeji.tieba.json.topic.TopicRoleApplyListJson;
import cn.xiaochuankeji.tieba.network.custom.exception.ClientErrorException;
import cn.xiaochuankeji.tieba.ui.base.h;
import org.aspectj.lang.a.a;
import rx.j;

public class TopicAdminManageActivity extends h {
    private static final a m = null;
    private long d;
    private b e;
    private RecyclerView f;
    private a g;
    private int h = 0;
    private int i = 20;
    private int j = -1;
    private int k = -1;
    private RelativeLayout l;

    static {
        v();
    }

    private static void v() {
        org.aspectj.a.b.b bVar = new org.aspectj.a.b.b("TopicAdminManageActivity.java", TopicAdminManageActivity.class);
        m = bVar.a("method-execution", bVar.a("4", "onCreate", "cn.xiaochuankeji.tieba.ui.topic.TopicAdminManageActivity", "android.os.Bundle", "savedInstance", "", "void"), 101);
    }

    public static void a(Context context, long j) {
        Intent intent = new Intent(context, TopicAdminManageActivity.class);
        intent.putExtra("topic_id", j);
        context.startActivity(intent);
    }

    protected int a() {
        return R.layout.layout_admin_apply_manage;
    }

    protected void i_() {
        this.l = (RelativeLayout) findViewById(R.id.rootView);
        this.f = (RecyclerView) findViewById(R.id.apply_list_view);
        final LayoutManager linearLayoutManager = new LinearLayoutManager(this, 1, false);
        this.f.setLayoutManager(linearLayoutManager);
        this.g = new a(this, this.d);
        this.f.setAdapter(this.g);
        this.f.setOnScrollListener(new OnScrollListener(this) {
            final /* synthetic */ TopicAdminManageActivity b;

            public void onScrollStateChanged(RecyclerView recyclerView, int i) {
                super.onScrollStateChanged(recyclerView, i);
                if (i == 0 && this.b.j + 1 == this.b.g.getItemCount() && this.b.k > 0) {
                    this.b.k();
                }
            }

            public void onScrolled(RecyclerView recyclerView, int i, int i2) {
                super.onScrolled(recyclerView, i, i2);
                this.b.j = linearLayoutManager.findLastVisibleItemPosition();
            }
        });
        j();
    }

    public void s() {
        this.e.b(this.d, "close").a(rx.a.b.a.a()).b(new j<Void>(this) {
            final /* synthetic */ TopicAdminManageActivity a;

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
                g.a("已关闭招募，即将返回");
                new Handler(Looper.getMainLooper()).postDelayed(new Runnable(this) {
                    final /* synthetic */ AnonymousClass2 a;

                    {
                        this.a = r1;
                    }

                    public void run() {
                        this.a.a.finish();
                    }
                }, 1000);
            }
        });
    }

    static final void a(TopicAdminManageActivity topicAdminManageActivity, Bundle bundle, org.aspectj.lang.a aVar) {
        topicAdminManageActivity.d = topicAdminManageActivity.getIntent().getLongExtra("topic_id", 0);
        topicAdminManageActivity.e = new b();
        super.onCreate(bundle);
    }

    protected void onCreate(Bundle bundle) {
        org.aspectj.lang.a a = org.aspectj.a.b.b.a(m, this, this, bundle);
        c.c().a(new f(new Object[]{this, bundle, a}).linkClosureAndJoinPoint(69648));
    }

    private void j() {
        this.e.a(this.d, this.h, this.i).a(rx.a.b.a.a()).b(new j<TopicRoleApplyListJson>(this) {
            final /* synthetic */ TopicAdminManageActivity a;

            {
                this.a = r1;
            }

            public /* synthetic */ void onNext(Object obj) {
                a((TopicRoleApplyListJson) obj);
            }

            public void onCompleted() {
            }

            public void onError(Throwable th) {
                g.a("没有查到申请人");
            }

            public void a(TopicRoleApplyListJson topicRoleApplyListJson) {
                this.a.g.a(topicRoleApplyListJson.applyList);
                this.a.h = topicRoleApplyListJson.applyList.size() - 1;
                this.a.k = topicRoleApplyListJson.hasMore;
            }
        });
    }

    private void k() {
        this.e.a(this.d, this.h, this.i).a(rx.a.b.a.a()).b(new j<TopicRoleApplyListJson>(this) {
            final /* synthetic */ TopicAdminManageActivity a;

            {
                this.a = r1;
            }

            public /* synthetic */ void onNext(Object obj) {
                a((TopicRoleApplyListJson) obj);
            }

            public void onCompleted() {
            }

            public void onError(Throwable th) {
            }

            public void a(TopicRoleApplyListJson topicRoleApplyListJson) {
                this.a.g.b(topicRoleApplyListJson.applyList);
                this.a.h = this.a.h + topicRoleApplyListJson.applyList.size();
            }
        });
    }
}
