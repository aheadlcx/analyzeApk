package cn.xiaochuankeji.tieba.ui.topic.ui;

import android.arch.lifecycle.q;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import cn.xiaochuankeji.tieba.R;
import cn.xiaochuankeji.tieba.ui.base.f;
import cn.xiaochuankeji.tieba.ui.topic.data.b;
import cn.xiaochuankeji.tieba.ui.topic.data.c;
import cn.xiaochuankeji.tieba.widget.CustomEmptyView;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.a.h;
import java.io.Serializable;
import java.util.List;
import org.greenrobot.eventbus.ThreadMode;
import org.greenrobot.eventbus.l;

public class PostListFragment extends f {
    private FragmentType a;
    private Unbinder b;
    private long c;
    private a d;
    private PostListModel e;
    @BindView
    CustomEmptyView emptyView;
    @BindView
    RecyclerView recyclerView;
    @BindView
    SmartRefreshLayout refreshLayout;

    public interface a {
        void a(int i);
    }

    public enum FragmentType implements Serializable {
        HOT,
        NEW
    }

    public static PostListFragment a(long j, FragmentType fragmentType) {
        Bundle bundle = new Bundle();
        bundle.putSerializable("FragmentType", fragmentType);
        bundle.putLong("TopicId", j);
        PostListFragment postListFragment = new PostListFragment();
        postListFragment.setArguments(bundle);
        return postListFragment;
    }

    @Nullable
    public View onCreateView(LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, @Nullable Bundle bundle) {
        View inflate = layoutInflater.inflate(R.layout.fragment_post_list, viewGroup, false);
        this.b = ButterKnife.a(this, inflate);
        b();
        c();
        d();
        return inflate;
    }

    public void onDestroyView() {
        super.onDestroyView();
        this.b.a();
    }

    private void b() {
        this.d = new a(getActivity());
        this.recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        this.recyclerView.setAdapter(this.d);
        this.recyclerView.setAnimation(null);
        this.recyclerView.getRecycledViewPool().setMaxRecycledViews(1, 10);
        this.recyclerView.getRecycledViewPool().setMaxRecycledViews(2, 10);
        this.recyclerView.getRecycledViewPool().setMaxRecycledViews(3, 6);
    }

    private void c() {
        this.e = (PostListModel) q.a((Fragment) this).a(PostListModel.class);
        this.e.a(this.d);
        if (getArguments() != null) {
            this.a = (FragmentType) getArguments().getSerializable("FragmentType");
            this.c = getArguments().getLong("TopicId");
        }
    }

    private void d() {
        this.refreshLayout.c(false);
        this.refreshLayout.b(false);
        this.refreshLayout.a(new com.scwang.smartrefresh.layout.e.a(this) {
            final /* synthetic */ PostListFragment a;

            {
                this.a = r1;
            }

            public void a(h hVar) {
                this.a.e();
            }
        });
        this.emptyView.a(c.a.d.a.a.a().d(R.drawable.ic_topic_empty_post), "木有内容，快来补充吧");
    }

    private void e() {
        this.e.b(this.c, this.a, new a(this) {
            final /* synthetic */ PostListFragment a;

            {
                this.a = r1;
            }

            public void a(boolean z, int i) {
                if (this.a.refreshLayout != null) {
                    if (z) {
                        this.a.refreshLayout.n();
                    } else {
                        this.a.refreshLayout.o();
                    }
                }
            }

            public void a() {
                if (this.a.refreshLayout != null) {
                    this.a.refreshLayout.n();
                }
            }
        });
    }

    public void a(List<c> list, String str, boolean z, int i) {
        if (list != null && this.e != null && this.emptyView != null && this.refreshLayout != null) {
            if (list.isEmpty()) {
                this.emptyView.b();
            } else {
                this.emptyView.c();
            }
            this.refreshLayout.a(z);
            this.e.a((List) list, str, i);
        }
    }

    public void b(boolean z) {
        if (this.d.getItemCount() <= 0 || z) {
            a(null);
        }
    }

    public void a(final a aVar) {
        this.e.a(this.c, this.a, new a(this) {
            final /* synthetic */ PostListFragment b;

            public void a(boolean z, int i) {
                if (aVar != null) {
                    aVar.a(i);
                }
                this.b.refreshLayout.a(z);
                if (this.b.emptyView != null) {
                    this.b.emptyView.c();
                }
            }

            public void a() {
                if (aVar != null) {
                    aVar.a(0);
                }
                if (this.b.emptyView != null) {
                    this.b.emptyView.b();
                }
            }
        });
    }

    @l(a = ThreadMode.MAIN)
    public void onDeletePost(cn.xiaochuankeji.tieba.ui.my.mypost.a aVar) {
        if (this.d.a(aVar.a) == 0 && this.emptyView != null) {
            this.emptyView.b();
        }
    }

    @l(a = ThreadMode.MAIN)
    public void onFollowMember(b bVar) {
        if (bVar != null) {
            this.d.a(bVar.b, bVar.a);
        }
    }
}
