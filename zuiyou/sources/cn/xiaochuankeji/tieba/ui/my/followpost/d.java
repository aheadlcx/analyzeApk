package cn.xiaochuankeji.tieba.ui.my.followpost;

import android.arch.lifecycle.q;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.RecyclerView.LayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import cn.xiaochuankeji.tieba.R;
import cn.xiaochuankeji.tieba.background.utils.g;
import cn.xiaochuankeji.tieba.ui.base.f;
import cn.xiaochuankeji.tieba.ui.my.followpost.model.MySubjectModel;
import cn.xiaochuankeji.tieba.ui.recommend.b;
import cn.xiaochuankeji.tieba.widget.CustomEmptyView;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.a.h;
import com.scwang.smartrefresh.layout.e.a;

public class d extends f implements b {
    private RecyclerView a;
    private c b;
    private MySubjectModel c;
    private SmartRefreshLayout d;
    private CustomEmptyView e;
    private boolean f;

    public static d b() {
        return new d();
    }

    @Nullable
    public View onCreateView(LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, @Nullable Bundle bundle) {
        return layoutInflater.inflate(R.layout.fragment_subject, null);
    }

    public void onViewCreated(View view, @Nullable Bundle bundle) {
        super.onViewCreated(view, bundle);
        this.e = (CustomEmptyView) view.findViewById(R.id.custom_empty_view);
        this.d = (SmartRefreshLayout) view.findViewById(R.id.root);
        this.a = (RecyclerView) view.findViewById(R.id.recycler_view);
        LayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        linearLayoutManager.setOrientation(1);
        this.a.setLayoutManager(linearLayoutManager);
        if (this.b == null) {
            this.b = new c(getActivity());
        }
        this.a.setAdapter(this.b);
        this.c = (MySubjectModel) q.a((Fragment) this).a(MySubjectModel.class);
        this.c.a((b) this);
        this.c.a(this.b);
        if (!this.f) {
            this.c.b();
        } else if (this.b.getItemCount() == 0) {
            this.e.a(R.drawable.ic_topic_empty_post, "木有内容，快来补充吧");
            this.e.b();
        }
        this.d.b(false);
        this.d.a(new a(this) {
            final /* synthetic */ d a;

            {
                this.a = r1;
            }

            public void a(h hVar) {
                this.a.c.c();
            }
        });
    }

    public void a(boolean z, String str, int i, boolean z2) {
        if (!(this.d == null || isDetached())) {
            this.d.m();
            if (z2) {
                this.d.a(true);
            } else {
                this.d.a(false);
            }
        }
        if (!z) {
            g.a(str);
            if (this.b.getItemCount() == 0) {
                this.e.b();
                this.e.setOnClickListener(new OnClickListener(this) {
                    final /* synthetic */ d a;

                    {
                        this.a = r1;
                    }

                    public void onClick(View view) {
                        this.a.c.b();
                    }
                });
            }
        }
        if (z) {
            this.f = true;
            if (i == 0) {
                this.e.a(R.drawable.ic_topic_empty_post, "木有内容，快来补充吧");
                this.e.b();
                return;
            }
            this.e.setVisibility(8);
        }
    }

    public void a(boolean z, String str, boolean z2) {
        if (!(this.d == null || isDetached())) {
            if (z2) {
                this.d.n();
            } else {
                this.d.o();
            }
        }
        if (!z) {
            g.a(str);
        }
    }
}
