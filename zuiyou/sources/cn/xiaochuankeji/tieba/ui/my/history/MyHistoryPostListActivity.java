package cn.xiaochuankeji.tieba.ui.my.history;

import android.arch.lifecycle.q;
import android.content.Context;
import android.content.Intent;
import android.support.v4.app.FragmentActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.View.OnClickListener;
import cn.xiaochuankeji.tieba.R;
import cn.xiaochuankeji.tieba.ui.base.h;
import cn.xiaochuankeji.tieba.ui.voice.b.a;
import cn.xiaochuankeji.tieba.ui.voice.b.d;
import cn.xiaochuankeji.tieba.ui.widget.NavigationBar;
import cn.xiaochuankeji.tieba.widget.CustomEmptyView;

public class MyHistoryPostListActivity extends h {
    private a d;
    private MyHistoryModel e;
    private NavigationBar f;
    private CustomEmptyView g;

    public static void a(Context context) {
        context.startActivity(new Intent(context, MyHistoryPostListActivity.class));
    }

    protected int a() {
        return R.layout.activity_history_post_list;
    }

    protected void i_() {
        j();
        k();
        v();
    }

    private void j() {
        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.history_post_list);
        this.d = new a(this);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(this.d);
        recyclerView.setAnimation(null);
    }

    private void k() {
        this.f = (NavigationBar) findViewById(R.id.navBar);
        this.f.setTitle("浏览历史");
        this.g = (CustomEmptyView) findViewById(R.id.custom_empty_view);
        this.g.a(R.drawable.ic_post_empty, "空空如也～");
        com.scwang.smartrefresh.layout.a.h hVar = (com.scwang.smartrefresh.layout.a.h) findViewById(R.id.refresh_layout);
        hVar.i(false);
        hVar.j(false);
        hVar.h(false);
        hVar.k(false);
    }

    private void v() {
        this.e = (MyHistoryModel) q.a((FragmentActivity) this).a(MyHistoryModel.class);
        this.e.a(this.d);
        this.e.a(new b(this) {
            final /* synthetic */ MyHistoryPostListActivity a;

            {
                this.a = r1;
            }

            public void a() {
                this.a.f.setOptionImg(R.drawable.nav_delete);
                this.a.g.c();
            }

            public void b() {
                this.a.f.e();
                this.a.g.b();
            }
        });
    }

    protected void onPause() {
        super.onPause();
        if (d.a().h() && d.a().i()) {
            d.a().f();
            a.a().a(d.a().b().a, d.a().b().f);
        }
    }

    public void s() {
        new cn.xiaochuankeji.tieba.ui.widget.b.a.a(this, "提示", "确定清空历史记录吗？").b("确定", new OnClickListener(this) {
            final /* synthetic */ MyHistoryPostListActivity a;

            {
                this.a = r1;
            }

            public void onClick(View view) {
                this.a.e.a(new a(this) {
                    final /* synthetic */ AnonymousClass2 a;

                    {
                        this.a = r1;
                    }

                    public void a() {
                        this.a.a.f.e();
                        this.a.a.g.b();
                    }
                });
            }
        }).a("取消", null).a();
    }
}
