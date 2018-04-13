package cn.xiaochuankeji.tieba.ui.my.favorite;

import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView.OnItemLongClickListener;
import android.widget.BaseAdapter;
import cn.htjyb.ui.widget.headfooterlistview.QueryListView;
import cn.htjyb.ui.widget.headfooterlistview.QueryListView.EmptyPaddingStyle;
import cn.xiaochuankeji.aop.permission.c;
import cn.xiaochuankeji.tieba.R;
import cn.xiaochuankeji.tieba.background.favorite.Favorite;
import cn.xiaochuankeji.tieba.background.favorite.f;
import cn.xiaochuankeji.tieba.background.net.XCError;
import cn.xiaochuankeji.tieba.background.utils.g;
import cn.xiaochuankeji.tieba.ui.base.h;
import cn.xiaochuankeji.tieba.ui.widget.SDEditSheet;
import org.aspectj.a.b.b;
import org.json.JSONObject;

public class MyFavoriteListActivity extends h implements cn.xiaochuankeji.tieba.background.favorite.f.a {
    private static final org.aspectj.lang.a.a h = null;
    private f d;
    private a e;
    private int f;
    private QueryListView g;

    class a extends BaseAdapter {
        final /* synthetic */ MyFavoriteListActivity a;

        a(MyFavoriteListActivity myFavoriteListActivity) {
            this.a = myFavoriteListActivity;
        }

        public int getCount() {
            return this.a.d.itemCount();
        }

        public Object getItem(int i) {
            return null;
        }

        public long getItemId(int i) {
            return 0;
        }

        public View getView(int i, View view, ViewGroup viewGroup) {
            if (view == null) {
                view = new g(this.a);
            } else {
                g gVar = (g) view;
            }
            view.a();
            view.setData((Favorite) this.a.d.itemAt(i));
            return view;
        }
    }

    static {
        j();
    }

    private static void j() {
        b bVar = new b("MyFavoriteListActivity.java", MyFavoriteListActivity.class);
        h = bVar.a("method-execution", bVar.a("4", "onCreate", "cn.xiaochuankeji.tieba.ui.my.favorite.MyFavoriteListActivity", "android.os.Bundle", "savedInstanceState", "", "void"), 46);
    }

    static final void a(MyFavoriteListActivity myFavoriteListActivity, Bundle bundle, org.aspectj.lang.a aVar) {
        super.onCreate(bundle);
        myFavoriteListActivity.d.refresh();
    }

    protected void onCreate(Bundle bundle) {
        org.aspectj.lang.a a = b.a(h, this, this, bundle);
        c.c().a(new f(new Object[]{this, bundle, a}).linkClosureAndJoinPoint(69648));
    }

    protected boolean a(Bundle bundle) {
        this.d = f.a();
        this.e = new a(this);
        return true;
    }

    protected int a() {
        return R.layout.activity_my_favorite_list;
    }

    protected void i_() {
        this.g = (QueryListView) findViewById(R.id.queryListView);
        this.g.a(this.d, this.e);
        this.f = getIntent().getIntExtra("count", 0);
        this.g.a("囊中空空，快来填充", (int) R.drawable.empty_tip_reported_post, EmptyPaddingStyle.GoldenSection);
    }

    protected void j_() {
        super.j_();
        this.d.a((cn.xiaochuankeji.tieba.background.favorite.f.a) this);
        this.g.m().setOnItemClickListener(new OnItemClickListener(this) {
            final /* synthetic */ MyFavoriteListActivity a;

            {
                this.a = r1;
            }

            public void onItemClick(AdapterView<?> adapterView, View view, int i, long j) {
                MyFavorActivity.a(this.a, (Favorite) this.a.d.itemAt(i - 1));
            }
        });
        this.g.m().setOnItemLongClickListener(new OnItemLongClickListener(this) {
            final /* synthetic */ MyFavoriteListActivity a;

            {
                this.a = r1;
            }

            public boolean onItemLongClick(AdapterView<?> adapterView, View view, final int i, long j) {
                SDEditSheet sDEditSheet = new SDEditSheet(this.a, new cn.xiaochuankeji.tieba.ui.widget.SDEditSheet.a(this) {
                    final /* synthetic */ AnonymousClass2 b;

                    public void a(int i) {
                        if (1 == i) {
                            this.b.a.a(((Favorite) this.b.a.d.itemAt(i - 1)).getId());
                        }
                    }
                }, "提示");
                sDEditSheet.a("删除", 1, true);
                sDEditSheet.b();
                return true;
            }
        });
    }

    private void a(final long j) {
        cn.xiaochuankeji.tieba.ui.widget.f.a("提示", "删除收藏夹后该收藏夹中的内容也会被一并删除", this, new cn.xiaochuankeji.tieba.ui.widget.f.a(this) {
            final /* synthetic */ MyFavoriteListActivity b;

            public void a(boolean z) {
                if (z) {
                    new cn.xiaochuankeji.tieba.background.favorite.c(j, new cn.xiaochuankeji.tieba.background.net.a.b<JSONObject>(this) {
                        final /* synthetic */ AnonymousClass3 a;

                        {
                            this.a = r1;
                        }

                        public /* synthetic */ void onResponse(Object obj, Object obj2) {
                            a((JSONObject) obj, obj2);
                        }

                        public void a(JSONObject jSONObject, Object obj) {
                            g.a("删除成功");
                            this.a.b.d.a(j);
                        }
                    }, new cn.xiaochuankeji.tieba.background.net.a.a(this) {
                        final /* synthetic */ AnonymousClass3 a;

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

    public void s() {
        CreateOrEditFavoriteActivity.a(this, -1);
    }

    public void k_() {
        this.g.m().post(new Runnable(this) {
            final /* synthetic */ MyFavoriteListActivity a;

            {
                this.a = r1;
            }

            public void run() {
                this.a.g.m().setSelection(0);
            }
        });
        this.f++;
    }

    public void d() {
        this.f--;
        if (this.f < 0) {
            this.f = 0;
        }
    }

    protected void onDestroy() {
        super.onDestroy();
        this.d.b(this);
    }

    public void onBackPressed() {
        super.onBackPressed();
    }
}
