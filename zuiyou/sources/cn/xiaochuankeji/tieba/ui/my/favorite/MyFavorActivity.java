package cn.xiaochuankeji.tieba.ui.my.favorite;

import android.arch.lifecycle.q;
import android.content.Context;
import android.content.Intent;
import android.support.v4.app.FragmentActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import cn.xiaochuankeji.tieba.R;
import cn.xiaochuankeji.tieba.background.favorite.Favorite;
import cn.xiaochuankeji.tieba.background.favorite.c;
import cn.xiaochuankeji.tieba.background.net.XCError;
import cn.xiaochuankeji.tieba.background.utils.g;
import cn.xiaochuankeji.tieba.ui.base.h;
import cn.xiaochuankeji.tieba.ui.voice.b.d;
import cn.xiaochuankeji.tieba.ui.widget.NavigationBar;
import cn.xiaochuankeji.tieba.ui.widget.SDPopupMenu;
import cn.xiaochuankeji.tieba.ui.widget.SDPopupMenu.b;
import cn.xiaochuankeji.tieba.ui.widget.f;
import cn.xiaochuankeji.tieba.ui.widget.f.a;
import cn.xiaochuankeji.tieba.widget.CustomEmptyView;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import org.greenrobot.eventbus.ThreadMode;
import org.greenrobot.eventbus.l;
import org.json.JSONObject;

public class MyFavorActivity extends h {
    private Favorite d;
    private int e;
    private SmartRefreshLayout f;
    private NavigationBar g;
    private CustomEmptyView h;
    private d i;
    private MyFavorModel j;

    protected int a() {
        return R.layout.activity_owner_activity;
    }

    public void s() {
        View optionImageView = this.a.getOptionImageView();
        SDPopupMenu sDPopupMenu = new SDPopupMenu(this, optionImageView, SDPopupMenu.a(optionImageView), new b(this) {
            final /* synthetic */ MyFavorActivity a;

            {
                this.a = r1;
            }

            public void b(int i) {
                if (1 == i) {
                    this.a.j();
                } else if (2 == i) {
                    this.a.k();
                }
            }
        });
        sDPopupMenu.a("编辑", 1);
        sDPopupMenu.a("删除", 2, true);
        sDPopupMenu.b();
    }

    private void j() {
        CreateOrEditFavoriteActivity.a(this, this.d.getId(), this.d.getName(), 100);
    }

    private void k() {
        f.a("提示", "删除收藏夹后该收藏夹中的内容也会被一并删除", this, new a(this) {
            final /* synthetic */ MyFavorActivity a;

            {
                this.a = r1;
            }

            public void a(boolean z) {
                if (z) {
                    new c(this.a.d.getId(), new cn.xiaochuankeji.tieba.background.net.a.b<JSONObject>(this) {
                        final /* synthetic */ AnonymousClass2 a;

                        {
                            this.a = r1;
                        }

                        public /* synthetic */ void onResponse(Object obj, Object obj2) {
                            a((JSONObject) obj, obj2);
                        }

                        public void a(JSONObject jSONObject, Object obj) {
                            g.a("删除成功");
                            org.greenrobot.eventbus.c.a().d(new b(this.a.a.d.getId()));
                            this.a.a.finish();
                        }
                    }, new cn.xiaochuankeji.tieba.background.net.a.a(this) {
                        final /* synthetic */ AnonymousClass2 a;

                        {
                            this.a = r1;
                        }

                        public void onErrorResponse(XCError xCError, Object obj) {
                            g.a(xCError.getMessage());
                        }
                    }).execute();
                }
            }
        }).setConfirmTip("删除");
    }

    public static void a(Context context, Favorite favorite) {
        Intent intent = new Intent(context, MyFavorActivity.class);
        intent.putExtra("key_favorite", favorite);
        context.startActivity(intent);
    }

    protected void i_() {
        this.d = (Favorite) getIntent().getSerializableExtra("key_favorite");
        this.e = this.d.getPostCount();
        v();
        x();
        y();
        z();
    }

    private void v() {
        this.g = (NavigationBar) findViewById(R.id.navBar);
        this.g.setOptionImg(c.a.d.a.a.a().d(R.drawable.ic_nav_more));
        w();
    }

    private void w() {
        this.g.setTitle(this.d.getName() + "(" + this.e + ")");
    }

    private void x() {
        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.my_post_list);
        this.i = new d(this);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(this.i);
        recyclerView.setAnimation(null);
    }

    private void y() {
        this.h = (CustomEmptyView) findViewById(R.id.custom_empty_view);
        this.h.a(R.drawable.empty_tip_reported_post, "空空如也，请勤劳发帖");
        this.f = (SmartRefreshLayout) findViewById(R.id.refresh_layout);
        this.f.d(false);
        this.f.c(false);
        this.f.a(false);
        this.f.b(false);
        this.f.a(new com.scwang.smartrefresh.layout.e.a(this) {
            final /* synthetic */ MyFavorActivity a;

            {
                this.a = r1;
            }

            public void a(final com.scwang.smartrefresh.layout.a.h hVar) {
                this.a.j.b(this.a.d.getId(), new MyFavorModel.a(this) {
                    final /* synthetic */ AnonymousClass3 b;

                    public void a(boolean z) {
                        if (z) {
                            this.b.a.f.n();
                        } else {
                            this.b.a.f.o();
                        }
                    }

                    public void a() {
                        hVar.u();
                    }
                });
            }
        });
    }

    private void z() {
        this.j = (MyFavorModel) q.a((FragmentActivity) this).a(MyFavorModel.class);
        this.j.a(this.i);
        this.j.a(this.d.getId(), new MyFavorModel.a(this) {
            final /* synthetic */ MyFavorActivity a;

            {
                this.a = r1;
            }

            public void a(boolean z) {
                this.a.h.setVisibility(8);
                if (z && this.a.f != null) {
                    this.a.f.d(true);
                    this.a.f.a(true);
                }
            }

            public void a() {
                this.a.h.b();
            }
        });
    }

    @l(a = ThreadMode.MAIN)
    public void onDeletePost(cn.xiaochuankeji.tieba.ui.my.mypost.a aVar) {
        this.e = this.i.a(aVar.a);
        w();
        if (this.e == 0) {
            this.h.b();
        }
    }

    @l(a = ThreadMode.MAIN)
    public void onRenamefavor(c cVar) {
        if (cVar.b == this.d.getId()) {
            this.g.setTitle(cVar.a + "(" + this.e + ")");
        }
    }

    protected void onPause() {
        super.onPause();
        if (d.a().h() && d.a().i()) {
            d.a().f();
            cn.xiaochuankeji.tieba.ui.voice.b.a.a().a(d.a().b().a, d.a().b().f);
        }
    }
}
