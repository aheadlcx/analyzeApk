package cn.xiaochuankeji.tieba.ui.member.userpost;

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
import cn.xiaochuankeji.tieba.ui.topic.data.c;
import cn.xiaochuankeji.tieba.widget.CustomEmptyView;
import com.meizu.cloud.pushsdk.pushtracer.constant.Parameters;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.a.h;
import com.scwang.smartrefresh.layout.e.a;
import java.util.List;

public class UserPostFragment extends f {
    private a a;
    private UserPostModel b;
    private Unbinder c;
    @BindView
    CustomEmptyView customEmptyView;
    private long d;
    @BindView
    RecyclerView recyclerView;
    @BindView
    SmartRefreshLayout refreshLayout;

    public static UserPostFragment a(long j) {
        UserPostFragment userPostFragment = new UserPostFragment();
        Bundle bundle = new Bundle();
        bundle.putLong(Parameters.SESSION_USER_ID, j);
        userPostFragment.setArguments(bundle);
        return userPostFragment;
    }

    @Nullable
    public View onCreateView(LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, @Nullable Bundle bundle) {
        View inflate = layoutInflater.inflate(R.layout.fragment_user_post, viewGroup, false);
        this.c = ButterKnife.a(this, inflate);
        this.d = getArguments() == null ? 0 : getArguments().getLong(Parameters.SESSION_USER_ID);
        b();
        c();
        d();
        return inflate;
    }

    public void onDestroyView() {
        super.onDestroyView();
        this.c.a();
    }

    private void b() {
        this.a = new a(getActivity());
        this.recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        this.recyclerView.setAdapter(this.a);
        this.recyclerView.setAnimation(null);
    }

    private void c() {
        this.customEmptyView.b(R.drawable.ic_post_empty, "空空如也");
        this.refreshLayout.c(false);
        this.refreshLayout.b(false);
        this.refreshLayout.a(new a(this) {
            final /* synthetic */ UserPostFragment a;

            {
                this.a = r1;
            }

            public void a(final h hVar) {
                this.a.b.a(this.a.d, new a(this) {
                    final /* synthetic */ AnonymousClass1 b;

                    public void a(boolean z) {
                        if (z) {
                            hVar.u();
                        } else {
                            hVar.t();
                        }
                    }

                    public void a() {
                        hVar.u();
                    }
                });
            }
        });
    }

    private void d() {
        this.b = (UserPostModel) q.a((Fragment) this).a(UserPostModel.class);
        this.b.a(this.a);
    }

    public void a(List<c> list, boolean z, long j) {
        if (this.b != null && list != null) {
            if (list.isEmpty()) {
                this.customEmptyView.b();
            } else {
                this.customEmptyView.c();
            }
            this.refreshLayout.a(z);
            this.b.a((List) list, j);
        }
    }
}
