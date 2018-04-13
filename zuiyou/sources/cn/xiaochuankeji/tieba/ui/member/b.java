package cn.xiaochuankeji.tieba.ui.member;

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
import cn.xiaochuankeji.tieba.ui.member.model.MemberFollowPostModel;
import cn.xiaochuankeji.tieba.ui.my.followpost.MyFollowPostAdapter;
import cn.xiaochuankeji.tieba.widget.CustomEmptyView;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.a.h;
import com.scwang.smartrefresh.layout.e.a;

public class b extends f implements cn.xiaochuankeji.tieba.ui.recommend.b {
    private RecyclerView a;
    private MyFollowPostAdapter b;
    private MemberFollowPostModel c;
    private SmartRefreshLayout d;
    private CustomEmptyView e;
    private boolean f;
    private long g;

    public static b a(long j) {
        Bundle bundle = new Bundle();
        bundle.putLong("s_key_uid", j);
        b bVar = new b();
        bVar.setArguments(bundle);
        return bVar;
    }

    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        this.g = getArguments().getLong("s_key_uid");
    }

    @Nullable
    public View onCreateView(LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, @Nullable Bundle bundle) {
        return layoutInflater.inflate(R.layout.fragment_follow_post, null);
    }

    public void onViewCreated(View view, @Nullable Bundle bundle) {
        super.onViewCreated(view, bundle);
        this.e = (CustomEmptyView) view.findViewById(R.id.custom_empty_view);
        this.d = (SmartRefreshLayout) view.findViewById(R.id.root);
        this.a = (RecyclerView) view.findViewById(R.id.recycler_view);
        LayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        linearLayoutManager.setOrientation(1);
        this.a.setLayoutManager(linearLayoutManager);
        this.a.setId(R.id.id_stickynavlayout_innerscrollview);
        if (this.b == null) {
            this.b = new MyFollowPostAdapter(getActivity(), "hisArticles");
        }
        this.a.setAdapter(this.b);
        this.c = (MemberFollowPostModel) q.a((Fragment) this).a(MemberFollowPostModel.class);
        this.c.a(this.b);
        if (!this.f) {
            this.c.a(this.g);
        } else if (this.b.getItemCount() == 0) {
            this.e.a(R.drawable.ic_post_empty, "空空如也~");
            this.e.b();
        }
        this.c.a((cn.xiaochuankeji.tieba.ui.recommend.b) this);
        this.d.b(false);
        this.d.a(new a(this) {
            final /* synthetic */ b a;

            {
                this.a = r1;
            }

            public void a(h hVar) {
                this.a.c.b(this.a.g);
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
                    final /* synthetic */ b a;

                    {
                        this.a = r1;
                    }

                    public void onClick(View view) {
                        this.a.c.a(this.a.g);
                    }
                });
            }
        }
        if (z) {
            this.f = true;
            if (i == 0) {
                this.e.a(R.drawable.ic_post_empty, "空空如也~");
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
