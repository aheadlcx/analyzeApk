package cn.xiaochuankeji.tieba.ui.my.liked;

import android.arch.lifecycle.q;
import android.content.Context;
import android.content.Intent;
import android.support.v4.app.FragmentActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import cn.xiaochuankeji.tieba.R;
import cn.xiaochuankeji.tieba.ui.base.h;
import cn.xiaochuankeji.tieba.ui.voice.b.d;
import cn.xiaochuankeji.tieba.ui.widget.NavigationBar;
import cn.xiaochuankeji.tieba.widget.CustomEmptyView;
import com.scwang.smartrefresh.layout.e.a;
import org.greenrobot.eventbus.ThreadMode;
import org.greenrobot.eventbus.l;

public class MyLikedPostListActivity extends h {
    private com.scwang.smartrefresh.layout.a.h d;
    private CustomEmptyView e;
    private b f;
    private MyLikedModel g;

    public static void a(Context context) {
        context.startActivity(new Intent(context, MyLikedPostListActivity.class));
    }

    protected int a() {
        return R.layout.activity_my_liked_post_list;
    }

    protected void i_() {
        j();
        k();
        v();
    }

    private void j() {
        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.liked_post_list);
        this.f = new b(this);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(this.f);
        recyclerView.setAnimation(null);
    }

    private void k() {
        ((NavigationBar) findViewById(R.id.navBar)).setTitle("我顶过的帖子");
        this.e = (CustomEmptyView) findViewById(R.id.custom_empty_view);
        this.e.a(R.drawable.empty_tip_support_post, "点赞之后的你更帅气");
        this.d = (com.scwang.smartrefresh.layout.a.h) findViewById(R.id.refresh_layout);
        this.d.i(false);
        this.d.j(false);
        this.d.b(new a(this) {
            final /* synthetic */ MyLikedPostListActivity a;

            {
                this.a = r1;
            }

            public void a(final com.scwang.smartrefresh.layout.a.h hVar) {
                this.a.g.b(new a(this) {
                    final /* synthetic */ AnonymousClass1 b;

                    public void a(boolean z) {
                        if (z) {
                            this.b.a.d.u();
                        } else {
                            this.b.a.d.t();
                        }
                    }

                    public void a() {
                        hVar.u();
                    }
                });
            }
        });
    }

    private void v() {
        this.g = (MyLikedModel) q.a((FragmentActivity) this).a(MyLikedModel.class);
        this.g.a(this.f);
        this.g.a(new a(this) {
            final /* synthetic */ MyLikedPostListActivity a;

            {
                this.a = r1;
            }

            public void a(boolean z) {
                this.a.d.k(z);
                this.a.e.c();
            }

            public void a() {
                this.a.e.b();
            }
        });
    }

    protected void onPause() {
        super.onPause();
        if (d.a().h() && d.a().i()) {
            d.a().f();
            cn.xiaochuankeji.tieba.ui.voice.b.a.a().a(d.a().b().a, d.a().b().f);
        }
    }

    @l(a = ThreadMode.MAIN)
    public void onDeleteHollow(a aVar) {
        if (this.f.a(aVar.a) == 0) {
            this.e.b();
        }
    }
}
