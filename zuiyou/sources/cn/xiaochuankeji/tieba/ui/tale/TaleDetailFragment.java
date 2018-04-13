package cn.xiaochuankeji.tieba.ui.tale;

import android.arch.lifecycle.q;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.RecyclerView.Adapter;
import android.support.v7.widget.RecyclerView.OnScrollListener;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import butterknife.BindView;
import butterknife.ButterKnife;
import cn.xiaochuankeji.tieba.R;
import cn.xiaochuankeji.tieba.b.c;
import cn.xiaochuankeji.tieba.background.tale.TaleComment;
import cn.xiaochuankeji.tieba.background.utils.g;
import cn.xiaochuankeji.tieba.ui.base.f;
import cn.xiaochuankeji.tieba.ui.tale.viewmodel.TaleDetailModel;
import cn.xiaochuankeji.tieba.ui.tale.viewmodel.a;
import com.alibaba.fastjson.JSONObject;
import com.izuiyou.a.a.b;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.a.h;
import java.util.List;
import org.greenrobot.eventbus.ThreadMode;
import org.greenrobot.eventbus.l;

public class TaleDetailFragment extends f implements a {
    public long a;
    public String b;
    private int c;
    private LinearLayoutManager d;
    private TaleDetailModel e;
    private b f = new b();
    private long g;
    private long h;
    private boolean i;
    private int j;
    @BindView
    RecyclerView recycler_view;
    @BindView
    SmartRefreshLayout refreshLayout;

    public static TaleDetailFragment b() {
        return new TaleDetailFragment();
    }

    public void a(long j, int i, String str) {
        this.a = j;
        this.c = i;
        this.b = str;
    }

    @Nullable
    public View onCreateView(LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, @Nullable Bundle bundle) {
        View inflate = layoutInflater.inflate(R.layout.fragment_tale_detail, null);
        ButterKnife.a(this, inflate);
        return inflate;
    }

    public void onViewCreated(View view, @Nullable Bundle bundle) {
        super.onViewCreated(view, bundle);
        this.g = getActivity().getIntent().getLongExtra("comment_id", 0);
        this.h = getActivity().getIntent().getLongExtra("start_id", 0);
        this.d = new LinearLayoutManager(getActivity());
        this.d.setOrientation(1);
        this.recycler_view.setItemAnimator(new cn.xiaochuankeji.tieba.ui.widget.a.a());
        this.recycler_view.setLayoutManager(this.d);
        this.recycler_view.setAdapter(this.f);
        this.e = (TaleDetailModel) q.a((Fragment) this).a(TaleDetailModel.class);
        this.e.a(this.c);
        this.e.a((a) this);
        this.e.a(this.f, this.b, this.a);
        this.refreshLayout.a(new com.scwang.smartrefresh.layout.e.a(this) {
            final /* synthetic */ TaleDetailFragment a;

            {
                this.a = r1;
            }

            public void a(h hVar) {
                if (this.a.c == 2) {
                    this.a.e.d();
                } else {
                    this.a.e.c();
                }
            }
        });
        this.refreshLayout.c(false);
        this.refreshLayout.e(false);
        this.refreshLayout.b(false);
        this.refreshLayout.a(false);
        Adapter a = ((TaleDetailActivity) getActivity()).a(this.a);
        if (a != null) {
            this.recycler_view.setAdapter(a);
            this.refreshLayout.a(a.a);
        } else if (this.c == 2) {
            this.e.a((TaleDetailActivity) getActivity(), this.b, this.g, this.h);
        } else {
            this.e.a((TaleDetailActivity) getActivity(), this.b, this.h);
        }
        this.recycler_view.addOnScrollListener(new OnScrollListener(this) {
            final /* synthetic */ TaleDetailFragment a;

            {
                this.a = r1;
            }

            public void onScrolled(RecyclerView recyclerView, int i, int i2) {
                super.onScrolled(recyclerView, i, i2);
                if (this.a.i) {
                    this.a.i = false;
                    int d = this.a.j - this.a.d.findFirstVisibleItemPosition();
                    if (d >= 0 && d < this.a.recycler_view.getChildCount()) {
                        this.a.recycler_view.scrollBy(0, this.a.recycler_view.getChildAt(d).getTop());
                    }
                }
            }
        });
    }

    @l(a = ThreadMode.MAIN)
    public void deleteComment(c cVar) {
        if (this.f != null) {
            this.f.b(cVar.a);
        }
    }

    @l(a = ThreadMode.MAIN)
    public void refreshData(cn.xiaochuankeji.tieba.ui.tale.a.c cVar) {
        if (this.e != null && getUserVisibleHint()) {
            this.f.b(cVar.a);
            JSONObject jSONObject = new JSONObject();
            jSONObject.put("id", Long.valueOf(this.a));
            this.e.a(0);
            this.e.b();
            this.h = 0;
            this.c = 0;
            this.e.a(true, jSONObject);
        }
    }

    @l(a = ThreadMode.MAIN)
    public void createComment(cn.xiaochuankeji.tieba.ui.tale.a.a aVar) {
        if (aVar.a != null && aVar.b == this.a) {
            this.f.b();
            this.f.c(aVar.a.comment);
            if (this.f.getItemCount() > 3) {
                c();
            }
        }
    }

    public void a(boolean z, String str, int i, boolean z2) {
        this.refreshLayout.p();
        this.refreshLayout.a(z2);
        if (z && getActivity() != null && getActivity().getIntent().getBooleanExtra("scroll", false) && this.f.getItemCount() > 3 && getUserVisibleHint()) {
            getActivity().getIntent().putExtra("scroll", false);
            c();
        }
    }

    public void a(boolean z, boolean z2, String str) {
        if (this.refreshLayout != null) {
            if (z2) {
                this.refreshLayout.n();
            } else {
                this.refreshLayout.o();
            }
        }
        if (!z) {
            g.a(str);
        }
    }

    private void c() {
        List a = this.f.a();
        int i = 0;
        while (i < a.size()) {
            if (((TaleComment) a.get(i)).layoutType == R.layout.item_tale_detail_comment_text) {
                break;
            }
            i++;
        }
        i = -1;
        if (i >= 0) {
            a(i);
        }
    }

    private void a(int i) {
        b.b();
        this.j = i;
        int findFirstVisibleItemPosition = this.d.findFirstVisibleItemPosition();
        int findLastVisibleItemPosition = this.d.findLastVisibleItemPosition();
        if (i <= findFirstVisibleItemPosition) {
            this.recycler_view.scrollToPosition(i);
        } else if (i <= findLastVisibleItemPosition) {
            this.recycler_view.scrollBy(0, this.recycler_view.getChildAt(i - findFirstVisibleItemPosition).getTop());
        } else {
            this.recycler_view.scrollToPosition(i);
            this.i = true;
        }
    }

    public void onDestroyView() {
        super.onDestroyView();
        TaleDetailActivity taleDetailActivity = (TaleDetailActivity) getActivity();
        this.f.a = this.refreshLayout.r();
        taleDetailActivity.a(this.a, this.f);
    }
}
