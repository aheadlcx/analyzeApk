package cn.xiaochuankeji.tieba.ui.my.ugcvideo;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.RecyclerView.OnScrollListener;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.FrameLayout;
import android.widget.FrameLayout.LayoutParams;
import android.widget.ImageView;
import android.widget.TextView;
import cn.htjyb.ui.widget.headfooterlistview.ViewLoadMoreFooter;
import cn.xiaochuankeji.tieba.R;
import cn.xiaochuankeji.tieba.background.data.post.Moment;
import cn.xiaochuankeji.tieba.ui.base.j;
import cn.xiaochuankeji.tieba.ui.utils.e;
import cn.xiaochuankeji.tieba.widget.CustomEmptyView;
import com.marshalchen.ultimaterecyclerview.UltimateRecyclerView;
import com.marshalchen.ultimaterecyclerview.UltimateRecyclerView.b;
import com.marshalchen.ultimaterecyclerview.grid.BasicGridLayoutManager;
import java.util.ArrayList;

public class a extends j {
    private static final int a = e.a(41.0f);
    private UltimateRecyclerView b;
    private UltimateRecyclerView c;
    private View d;
    private CustomEmptyView e;
    private ArrayList<Moment> f;
    private b g;
    private c h;
    private int i = 0;
    private int j;
    private boolean k = true;
    private a l;
    private boolean m = false;
    private OnScrollListener n = new OnScrollListener(this) {
        final /* synthetic */ a a;

        {
            this.a = r1;
        }

        public void onScrollStateChanged(RecyclerView recyclerView, int i) {
            super.onScrollStateChanged(recyclerView, i);
            if (i == 0) {
                this.a.i = 0;
            } else if (i == 1) {
                this.a.i = this.a.j;
            }
        }

        public void onScrolled(RecyclerView recyclerView, int i, int i2) {
            super.onScrolled(recyclerView, i, i2);
            if (i2 != 0) {
                int i3;
                this.a.i = this.a.i + (-i2);
                int d = this.a.i > 0 ? 0 : this.a.i;
                if (d < (-a.a)) {
                    i3 = -a.a;
                } else {
                    i3 = d;
                }
                LayoutParams layoutParams = (LayoutParams) this.a.d.getLayoutParams();
                layoutParams.topMargin = i3;
                this.a.d.setLayoutParams(layoutParams);
                this.a.j = i3;
            }
        }
    };

    public interface a {
        void a();
    }

    public a(Context context) {
        super(context);
    }

    protected View a(LayoutInflater layoutInflater) {
        return layoutInflater.inflate(R.layout.view_member_ugc_show, null);
    }

    protected void a(View view) {
        this.b = (UltimateRecyclerView) view.findViewById(R.id.gridRecyclerView);
        this.c = (UltimateRecyclerView) view.findViewById(R.id.listRecyclerView);
        this.d = view.findViewById(R.id.vFloatTitle);
    }

    public void a(ArrayList<Moment> arrayList) {
        this.f = arrayList;
        g();
        h();
        i();
        if (this.f.size() > 0) {
            this.b.setVisibility(0);
            this.d.setVisibility(0);
        }
    }

    private void g() {
        this.g = new b(e_(), this.f);
        this.b.setLayoutManager(new BasicGridLayoutManager(e_(), 3, this.g));
        this.b.a(R.layout.common_empty_view, UltimateRecyclerView.a, UltimateRecyclerView.a);
        this.b.a(new com.marshalchen.ultimaterecyclerview.grid.a(3, 2, false));
        this.b.setLoadMoreView(new ViewLoadMoreFooter(e_()));
        this.b.setOnLoadMoreListener(new b(this) {
            final /* synthetic */ a a;

            {
                this.a = r1;
            }

            public void a(int i, int i2) {
                View d = this.a.g.d();
                if (d instanceof ViewLoadMoreFooter) {
                    ((ViewLoadMoreFooter) d).a();
                }
                if (this.a.l != null) {
                    this.a.l.a();
                }
            }
        });
        this.b.setAdapter(this.g);
        this.b.h();
        this.b.a(this.n);
        if (this.m) {
            this.b.g.setId(R.id.id_stickynavlayout_innerscrollview);
        }
    }

    private void h() {
        this.h = new c(e_(), this.f);
        this.c.setLayoutManager(new LinearLayoutManager(e_()));
        this.c.a(R.layout.common_empty_view, UltimateRecyclerView.a, UltimateRecyclerView.a);
        this.c.setLoadMoreView(new ViewLoadMoreFooter(e_()));
        this.c.setOnLoadMoreListener(new b(this) {
            final /* synthetic */ a a;

            {
                this.a = r1;
            }

            public void a(int i, int i2) {
                View d = this.a.g.d();
                if (d instanceof ViewLoadMoreFooter) {
                    ((ViewLoadMoreFooter) d).a();
                }
                if (this.a.l != null) {
                    this.a.l.a();
                }
            }
        });
        this.c.setAdapter(this.h);
        this.c.h();
        this.c.a(this.n);
    }

    private void i() {
        this.d.setOnClickListener(new OnClickListener(this) {
            final /* synthetic */ a a;

            {
                this.a = r1;
            }

            public void onClick(View view) {
                if (this.a.k) {
                    this.a.k = false;
                    this.a.c.setVisibility(0);
                    this.a.c.a(0);
                    this.a.b.setVisibility(8);
                    ((ImageView) this.a.d.findViewById(R.id.ivTitleImg)).setImageResource(R.drawable.img_ugcvideo_change_list);
                    ((TextView) this.a.d.findViewById(R.id.tvTitle)).setText("帖子");
                    if (this.a.m) {
                        this.a.c.g.setId(R.id.id_stickynavlayout_innerscrollview);
                        this.a.b.g.setId(R.id.id_member_ugc_show_other_view);
                        return;
                    }
                    return;
                }
                this.a.k = true;
                this.a.c.setVisibility(8);
                this.a.b.setVisibility(0);
                this.a.b.a(0);
                ((ImageView) this.a.d.findViewById(R.id.ivTitleImg)).setImageResource(R.drawable.img_ugcvideo_change_grid);
                ((TextView) this.a.d.findViewById(R.id.tvTitle)).setText("列表");
                if (this.a.m) {
                    this.a.c.g.setId(R.id.id_member_ugc_show_other_view);
                    this.a.b.g.setId(R.id.id_stickynavlayout_innerscrollview);
                }
            }
        });
    }

    public void a(boolean z) {
        this.e = new CustomEmptyView(e_(), z);
        ((FrameLayout) f_().findViewById(R.id.rootView)).addView(this.e, new LayoutParams(-1, -1));
    }

    public void c() {
        this.m = true;
    }

    public void b(boolean z) {
        if (z) {
            this.e.b();
        } else {
            this.e.setVisibility(8);
        }
    }

    public void a(int i, String str) {
        this.e.a(i, str);
    }

    public void b(ArrayList<cn.xiaochuankeji.tieba.ui.videomaker.a.a> arrayList) {
        if (this.k) {
            this.b.setVisibility(0);
        } else {
            this.c.setVisibility(0);
        }
        this.g.a((ArrayList) arrayList);
        this.h.a((ArrayList) arrayList);
    }

    public void a(a aVar) {
        this.l = aVar;
    }

    public void d() {
        this.d.setVisibility(0);
    }

    public void c(boolean z) {
        if (this.k) {
            this.b.setVisibility(0);
            this.g.notifyDataSetChanged();
        } else {
            this.c.setVisibility(0);
            this.h.notifyDataSetChanged();
        }
        if (z) {
            this.b.f();
            this.c.f();
            return;
        }
        this.b.h();
        this.c.h();
    }

    public void e() {
        this.g.notifyDataSetChanged();
        this.h.notifyDataSetChanged();
    }
}
