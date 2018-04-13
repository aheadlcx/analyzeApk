package cn.xiaochuankeji.tieba.ui.my.favorite;

import android.arch.lifecycle.q;
import android.content.Context;
import android.content.Intent;
import android.support.v4.app.FragmentActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import cn.xiaochuankeji.tieba.R;
import cn.xiaochuankeji.tieba.background.favorite.Favorite;
import cn.xiaochuankeji.tieba.ui.base.h;
import cn.xiaochuankeji.tieba.ui.widget.NavigationBar;
import cn.xiaochuankeji.tieba.ui.widget.SDEditSheet;
import cn.xiaochuankeji.tieba.ui.widget.SDEditSheet.a;
import cn.xiaochuankeji.tieba.widget.CustomEmptyView;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.e.c;
import org.greenrobot.eventbus.ThreadMode;
import org.greenrobot.eventbus.l;

public class MyFavorListActivity extends h {
    private e d;
    private MyFavorListModel e;
    private SmartRefreshLayout f;
    private CustomEmptyView g;

    public static void a(Context context) {
        context.startActivity(new Intent(context, MyFavorListActivity.class));
    }

    protected int a() {
        return R.layout.activity_my_favor_list;
    }

    public void s() {
        CreateOrEditFavoriteActivity.a(this, -1);
    }

    protected void i_() {
        j();
        k();
        v();
        w();
    }

    private void j() {
        NavigationBar navigationBar = (NavigationBar) findViewById(R.id.navBar);
        navigationBar.setOptionImg(R.drawable.topic_establish_up);
        navigationBar.setTitle("我的收藏夹");
    }

    private void k() {
        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.my_favor_post_list);
        this.d = new e(this);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(this.d);
        recyclerView.setAnimation(null);
        this.d.a(new b(this) {
            final /* synthetic */ MyFavorListActivity a;

            {
                this.a = r1;
            }

            public void a(final Favorite favorite) {
                SDEditSheet sDEditSheet = new SDEditSheet(this.a, new a(this) {
                    final /* synthetic */ AnonymousClass1 b;

                    public void a(int i) {
                        this.b.a.e.a(favorite.getId(), new a(this) {
                            final /* synthetic */ AnonymousClass1 a;

                            {
                                this.a = r1;
                            }

                            public void a(boolean z) {
                                if (z) {
                                    this.a.b.a.g.b();
                                } else {
                                    this.a.b.a.g.setVisibility(8);
                                }
                            }
                        });
                    }
                }, "提示");
                sDEditSheet.a("删除", 1, true);
                sDEditSheet.b();
            }

            public void onClick(Favorite favorite) {
                MyFavorActivity.a(this.a, favorite);
            }
        });
    }

    private void v() {
        this.f = (SmartRefreshLayout) findViewById(R.id.my_favor_refresh);
        this.g = (CustomEmptyView) findViewById(R.id.my_favor_empty);
        this.f.d(false);
        this.f.a(false);
        this.f.a(new com.scwang.smartrefresh.layout.e.a(this) {
            final /* synthetic */ MyFavorListActivity a;

            {
                this.a = r1;
            }

            public void a(com.scwang.smartrefresh.layout.a.h hVar) {
                this.a.e.b(new b(this) {
                    final /* synthetic */ AnonymousClass2 a;

                    {
                        this.a = r1;
                    }

                    public void a(boolean z) {
                        if (this.a.a.f != null) {
                            if (z) {
                                this.a.a.f.n();
                            } else {
                                this.a.a.f.o();
                            }
                        }
                    }

                    public void a() {
                        if (this.a.a.f != null) {
                            this.a.a.f.n();
                        }
                    }
                });
            }
        });
        this.f.a(new c(this) {
            final /* synthetic */ MyFavorListActivity a;

            {
                this.a = r1;
            }

            public void a_(com.scwang.smartrefresh.layout.a.h hVar) {
                this.a.c(true);
            }
        });
        this.g.a(R.drawable.empty_tip_reported_post, "囊中空空，快来填充");
    }

    private void w() {
        this.e = (MyFavorListModel) q.a((FragmentActivity) this).a(MyFavorListModel.class);
        this.e.a(this.d);
        c(false);
    }

    private void c(final boolean z) {
        this.e.a(new b(this) {
            final /* synthetic */ MyFavorListActivity b;

            public void a(boolean z) {
                this.b.g.setVisibility(8);
                if (z && this.b.f != null) {
                    this.b.f.m();
                }
                if (z && this.b.f != null) {
                    this.b.f.d(true);
                    this.b.f.a(true);
                }
            }

            public void a() {
                if (z && this.b.f != null) {
                    this.b.f.m();
                }
                this.b.g.b();
            }
        });
    }

    @l(a = ThreadMode.MAIN)
    public void onCreateNewFavor(a aVar) {
        this.d.a(aVar.a);
    }

    @l(a = ThreadMode.MAIN)
    public void onDeleteFavor(b bVar) {
        this.e.b(bVar.a, new a(this) {
            final /* synthetic */ MyFavorListActivity a;

            {
                this.a = r1;
            }

            public void a(boolean z) {
                if (z) {
                    this.a.g.b();
                } else {
                    this.a.g.setVisibility(8);
                }
            }
        });
    }

    @l(a = ThreadMode.MAIN)
    public void onRenamefavor(c cVar) {
        this.d.a(cVar.b, cVar.a);
    }

    @l(a = ThreadMode.MAIN)
    public void onDeletePost(cn.xiaochuankeji.tieba.ui.my.mypost.a aVar) {
        c(false);
    }
}
