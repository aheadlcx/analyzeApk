package cn.xiaochuankeji.tieba.ui.homepage.feed;

import android.arch.lifecycle.q;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.annotation.WorkerThread;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.GridLayoutManager.SpanSizeLookup;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.RecyclerView.ItemAnimator;
import android.support.v7.widget.RecyclerView.LayoutManager;
import android.support.v7.widget.RecyclerView.OnScrollListener;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import butterknife.BindView;
import butterknife.ButterKnife;
import cn.xiaochuankeji.tieba.R;
import cn.xiaochuankeji.tieba.background.data.tag.NavigatorTag;
import cn.xiaochuankeji.tieba.background.modules.a.a.a;
import cn.xiaochuankeji.tieba.background.utils.g;
import cn.xiaochuankeji.tieba.json.MemberInfoBean;
import cn.xiaochuankeji.tieba.json.feed.FeedListJson;
import cn.xiaochuankeji.tieba.ui.base.MainActivity;
import cn.xiaochuankeji.tieba.ui.base.f;
import cn.xiaochuankeji.tieba.ui.homepage.PostLoadedTipsView;
import cn.xiaochuankeji.tieba.ui.homepage.feed.a.b;
import cn.xiaochuankeji.tieba.ui.homepage.feed.model.FeedViewModel;
import cn.xiaochuankeji.tieba.ui.recommend.data.RecPostDataBean;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.a.h;
import com.scwang.smartrefresh.layout.e.c;
import java.util.List;
import org.greenrobot.eventbus.ThreadMode;
import org.greenrobot.eventbus.l;

public class FeedFragment extends f implements a, cn.xiaochuankeji.tieba.ui.homepage.feed.model.a {
    private FeedViewModel a;
    private b b;
    private cn.xiaochuankeji.tieba.ui.homepage.feed.a.a c;
    private boolean d;
    private boolean e;
    private boolean f;
    private String g;
    private OnScrollListener h;
    @BindView
    RecyclerView recycler;
    @BindView
    SmartRefreshLayout refresh;
    @BindView
    PostLoadedTipsView tips;

    @Nullable
    public View onCreateView(LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, @Nullable Bundle bundle) {
        cn.xiaochuankeji.tieba.background.a.g().a(this);
        return layoutInflater.inflate(R.layout.fragment_feed, viewGroup, false);
    }

    public void onViewCreated(View view, @Nullable Bundle bundle) {
        super.onViewCreated(view, bundle);
        ButterKnife.a(this, view);
        this.a = (FeedViewModel) q.a((Fragment) this).a(FeedViewModel.class);
        this.a.a((cn.xiaochuankeji.tieba.ui.homepage.feed.model.a) this);
        NavigatorTag navigatorTag = (NavigatorTag) getArguments().getParcelable("fragment_navigator");
        this.c.a(navigatorTag);
        this.a.a(navigatorTag);
        ItemAnimator aVar = new cn.xiaochuankeji.tieba.ui.widget.a.a();
        aVar.setAddDuration(350);
        this.recycler.setItemAnimator(aVar);
        this.refresh.a(false);
        this.refresh.a(new c(this) {
            final /* synthetic */ FeedFragment a;

            {
                this.a = r1;
            }

            public void a_(h hVar) {
                this.a.d = false;
                this.a.f = true;
                this.a.a.a(TextUtils.isEmpty(this.a.g) ? "down" : this.a.g, this.a.e);
                this.a.g = null;
                this.a.e = false;
            }
        });
        this.refresh.a(new com.scwang.smartrefresh.layout.e.a(this) {
            final /* synthetic */ FeedFragment a;

            {
                this.a = r1;
            }

            public void a(h hVar) {
                this.a.d = true;
                this.a.a.c();
            }
        });
        this.a.b();
    }

    public void onResume() {
        super.onResume();
        this.recycler.addOnScrollListener(this.h);
    }

    public void onPause() {
        this.recycler.removeOnScrollListener(this.h);
        super.onPause();
    }

    public void onDestroyView() {
        this.a.a(this.c.a());
        this.a.a(null);
        cn.xiaochuankeji.tieba.background.a.g().b(this);
        super.onDestroyView();
    }

    @l(a = ThreadMode.MAIN)
    public void navRefresh(cn.xiaochuankeji.tieba.b.a aVar) {
        NavigatorTag navigatorTag = aVar.b;
        if (!TextUtils.isEmpty(aVar.a) && navigatorTag != null && this.a != null && !this.f && this.refresh != null) {
            if (navigatorTag.id == ((NavigatorTag) getArguments().getParcelable("fragment_navigator")).id) {
                this.recycler.smoothScrollToPosition(0);
                MainActivity.a("tab_home_page");
                this.g = aVar.a;
                this.e = false;
                this.refresh.a(0, 300, 1.0f);
            }
        }
    }

    private void b(String str) {
        this.tips.setText(str);
        this.tips.setVisibility(0);
        this.tips.postDelayed(new Runnable(this) {
            final /* synthetic */ FeedFragment a;

            {
                this.a = r1;
            }

            public void run() {
                if (this.a.tips != null) {
                    this.a.tips.setVisibility(8);
                }
            }
        }, 1500);
    }

    @WorkerThread
    public void a(@Nullable final FeedListJson feedListJson) {
        rx.a.b.a.a().a().a(new rx.b.a(this) {
            final /* synthetic */ FeedFragment b;

            public void call() {
                if (this.b.refresh != null) {
                    if (feedListJson == null || feedListJson.list == null || feedListJson.list.isEmpty()) {
                        this.b.e = true;
                        this.b.refresh.a(0, 300, 0.3f);
                        return;
                    }
                    int b;
                    this.b.b();
                    this.b.c.a(feedListJson.list);
                    this.b.refresh.a(true);
                    this.b.refresh.g(false);
                    this.b.recycler.setTag(Boolean.valueOf(true));
                    NavigatorTag navigatorTag = (NavigatorTag) this.b.getArguments().getParcelable("fragment_navigator");
                    if (navigatorTag != null) {
                        b = cn.xiaochuankeji.tieba.background.a.q().b(navigatorTag.id);
                    } else {
                        b = -1;
                    }
                    if (b < 0) {
                        return;
                    }
                    if (this.b.a()) {
                        this.b.refresh.a(0, 300, 0.3f);
                        this.b.getArguments().putBoolean("_need_refresh", false);
                        return;
                    }
                    this.b.getArguments().putBoolean("_need_refresh", true);
                }
            }
        });
    }

    public void a(String str) {
        g.a(str);
        this.f = false;
        if (this.refresh != null) {
            this.refresh.m();
            this.refresh.n();
        }
    }

    public void a(String str, boolean z, boolean z2, boolean z3, List<RecPostDataBean> list) {
        this.f = false;
        if (this.refresh != null) {
            this.refresh.m();
            if (z) {
                this.refresh.a(false);
                if (this.b.getItemCount() <= 2 || !z3) {
                    this.a.d();
                    return;
                }
                return;
            }
            this.refresh.a(true);
            this.refresh.g(false);
            if (list == null || list.isEmpty()) {
                if (this.recycler.getTag() == null) {
                    b("暂无更新，到推荐看看吧");
                }
                this.recycler.setTag(null);
                return;
            }
            b("有 " + list.size() + " 条内容更新");
            b();
            if (z2) {
                this.c.a((List) list);
                return;
            }
            this.c.b(list);
            this.recycler.smoothScrollToPosition(0);
        }
    }

    public void a(List<MemberInfoBean> list, String str) {
        if (this.recycler != null) {
            cn.xiaochuankeji.tieba.ui.widget.g.c(getActivity());
            this.refresh.m();
            if (!TextUtils.isEmpty(str)) {
                b(str);
            }
            c();
            this.b.a((List) list);
            this.c.b();
        }
    }

    public void a(boolean z, boolean z2, List<RecPostDataBean> list) {
        if (this.refresh != null) {
            this.refresh.g(!z2);
            this.refresh.n();
            if (z) {
                b();
                this.c.c(list);
            }
        }
    }

    private void b() {
        if (!(this.recycler.getAdapter() instanceof cn.xiaochuankeji.tieba.ui.homepage.feed.a.a)) {
            this.recycler.setLayoutManager(new LinearLayoutManager(getContext(), 1, false));
            this.recycler.setAdapter(this.c);
        }
    }

    private void c() {
        if (!(this.recycler.getAdapter() instanceof b)) {
            LayoutManager gridLayoutManager = new GridLayoutManager(getContext(), 3, 1, false);
            gridLayoutManager.setSpanSizeLookup(new SpanSizeLookup(this) {
                final /* synthetic */ FeedFragment a;

                {
                    this.a = r1;
                }

                public int getSpanSize(int i) {
                    if (this.a.b.getItemViewType(i) == 0) {
                        return 1;
                    }
                    return 3;
                }
            });
            this.recycler.setLayoutManager(gridLayoutManager);
            this.recycler.setAdapter(this.b);
            FeedViewModel.a = true;
        }
    }

    protected void a(String str, boolean z) {
        super.a(str, z);
        if (z) {
            Bundle arguments = getArguments();
            if (arguments == null || !arguments.getBoolean("_need_refresh", false)) {
                com.izuiyou.a.a.b.a();
                if (a()) {
                    d();
                    return;
                }
                return;
            }
            arguments.putBoolean("_need_refresh", false);
            if (this.refresh != null) {
                this.refresh.a(0, 300, 0.3f);
            }
        }
    }

    private void d() {
        if (this.recycler != null && (this.recycler.getAdapter() instanceof b) && !cn.xiaochuankeji.tieba.background.a.g().d()) {
            this.a.a("down", true);
        }
    }

    public void onTokenChanged() {
        if (cn.xiaochuankeji.tieba.background.a.g().d()) {
            rx.a.b.a.a().a().a(new rx.b.a(this) {
                final /* synthetic */ FeedFragment a;

                {
                    this.a = r1;
                }

                public void call() {
                    this.a.c.b();
                    if (!this.a.a() || this.a.refresh == null) {
                        this.a.getArguments().putBoolean("_need_refresh", true);
                        return;
                    }
                    this.a.e = true;
                    this.a.refresh.a(0, 300, 0.3f);
                    this.a.getArguments().putBoolean("_need_refresh", false);
                }
            });
        }
    }

    @l(a = ThreadMode.MAIN)
    public void memberReload(cn.xiaochuankeji.tieba.ui.homepage.feed.b.a aVar) {
        if (this.a != null && !this.a.e()) {
            c();
            cn.xiaochuankeji.tieba.ui.widget.g.a(getActivity());
            this.a.d();
        }
    }
}
