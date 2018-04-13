package cn.xiaochuankeji.tieba.ui.homepage.feed.newfeed;

import android.arch.lifecycle.q;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.GridLayoutManager.SpanSizeLookup;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.RecyclerView.LayoutManager;
import android.support.v7.widget.RecyclerView.OnScrollListener;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import cn.xiaochuankeji.tieba.R;
import cn.xiaochuankeji.tieba.background.data.tag.NavigatorTag;
import cn.xiaochuankeji.tieba.background.modules.a.a.a;
import cn.xiaochuankeji.tieba.ui.base.l;
import cn.xiaochuankeji.tieba.ui.homepage.PostLoadedTipsView;
import cn.xiaochuankeji.tieba.ui.homepage.f;
import cn.xiaochuankeji.tieba.ui.voice.model.VoiceModel.b;
import cn.xiaochuankeji.tieba.ui.widget.g;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.a.h;
import org.greenrobot.eventbus.ThreadMode;
import org.greenrobot.eventbus.c;

public class FeedMainFragment extends l implements a {
    private LinearLayoutManager a;
    private GridLayoutManager b;
    private a c;
    private b d;
    private FeedMainModel e;
    private Unbinder f;
    private b g;
    private NavigatorTag h;
    private boolean i;
    private boolean j;
    @BindView
    View loadingView;
    @BindView
    RecyclerView recyclerView;
    @BindView
    SmartRefreshLayout refreshLayout;
    @BindView
    PostLoadedTipsView tipsView;

    public static FeedMainFragment a(NavigatorTag navigatorTag) {
        FeedMainFragment feedMainFragment = new FeedMainFragment();
        Bundle bundle = new Bundle();
        bundle.putParcelable("navigatorTag", navigatorTag);
        feedMainFragment.setArguments(bundle);
        return feedMainFragment;
    }

    public void onResume() {
        super.onResume();
        if (cn.xiaochuankeji.tieba.background.post.b.a().e()) {
            this.g = new b("down", true);
            this.refreshLayout.q();
        }
    }

    public void onDestroyView() {
        super.onDestroyView();
        this.f.a();
        cn.xiaochuankeji.tieba.background.a.g().b(this);
    }

    protected View a(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        View inflate = layoutInflater.inflate(R.layout.fragment_main_feed, null);
        this.f = ButterKnife.a(this, inflate);
        this.loadingView.setVisibility(0);
        i();
        j();
        return inflate;
    }

    public void onSaveInstanceState(@NonNull Bundle bundle) {
        bundle.setClassLoader(getClass().getClassLoader());
        super.onSaveInstanceState(bundle);
    }

    public void onViewStateRestored(Bundle bundle) {
        if (bundle != null) {
            bundle.setClassLoader(getClass().getClassLoader());
        }
        super.onViewStateRestored(bundle);
    }

    protected void e() {
        cn.xiaochuankeji.tieba.background.a.g().a(this);
        this.h = (NavigatorTag) getArguments().getParcelable("navigatorTag");
        this.i = false;
        this.j = false;
        this.e = (FeedMainModel) q.a((Fragment) this).a(FeedMainModel.class);
        this.e.a(this.c, this.d);
        this.e.a(this.h);
        this.e.a(new a(this) {
            final /* synthetic */ FeedMainFragment a;

            {
                this.a = r1;
            }

            public void a(LoadResultType loadResultType, boolean z) {
                if (this.a.recyclerView != null) {
                    this.a.a(loadResultType);
                    this.a.loadingView.setVisibility(8);
                }
            }

            public void a(int i) {
            }

            public void a() {
                if (this.a.refreshLayout != null) {
                    this.a.refreshLayout.q();
                }
            }
        });
    }

    @org.greenrobot.eventbus.l(a = ThreadMode.MAIN)
    public void navRefresh(cn.xiaochuankeji.tieba.b.a aVar) {
        if (aVar.b != null && this.e != null && this.refreshLayout != null && !this.i && !this.j && !TextUtils.isEmpty(aVar.a) && aVar.b.id == this.h.id) {
            this.recyclerView.smoothScrollToPosition(0);
            this.g = new b("homebutton", false);
            this.refreshLayout.q();
        }
    }

    public void onTokenChanged() {
        if (cn.xiaochuankeji.tieba.background.a.g().d()) {
            rx.a.b.a.a().a().a(new rx.b.a(this) {
                final /* synthetic */ FeedMainFragment a;

                {
                    this.a = r1;
                }

                public void call() {
                    if (!this.a.a() || this.a.refreshLayout == null) {
                        this.a.getArguments().putBoolean("_need_refresh", true);
                        return;
                    }
                    this.a.g = new b("down", true);
                    this.a.refreshLayout.q();
                    this.a.getArguments().putBoolean("_need_refresh", false);
                }
            });
        }
    }

    private void i() {
        this.c = new a(getActivity());
        this.d = new b(getActivity());
        this.a = new WrapContentLinearLayoutManager(getActivity());
        this.b = new GridLayoutManager(getActivity(), 3);
        this.b.setSpanSizeLookup(new SpanSizeLookup(this) {
            final /* synthetic */ FeedMainFragment a;

            {
                this.a = r1;
            }

            public int getSpanSize(int i) {
                return (i == 0 || this.a.c.a(i)) ? 3 : 1;
            }
        });
        this.c.a(new d(this) {
            final /* synthetic */ FeedMainFragment a;

            {
                this.a = r1;
            }

            public void a() {
                g.a(this.a.getActivity());
                this.a.e.a(true, new a(this) {
                    final /* synthetic */ AnonymousClass6 a;

                    {
                        this.a = r1;
                    }

                    public void a(LoadResultType loadResultType, boolean z) {
                        g.c(this.a.a.getActivity());
                        this.a.a.a(loadResultType);
                    }

                    public void a(int i) {
                    }

                    public void a() {
                        g.c(this.a.a.getActivity());
                    }
                });
            }
        });
        this.recyclerView.setLayoutManager(this.a);
        this.recyclerView.setAdapter(this.d);
        this.recyclerView.setAnimation(null);
        this.recyclerView.addOnScrollListener(new OnScrollListener(this) {
            final /* synthetic */ FeedMainFragment a;
            private int b = -1;

            {
                this.a = r2;
            }

            public void onScrollStateChanged(RecyclerView recyclerView, int i) {
                super.onScrollStateChanged(recyclerView, i);
            }

            public void onScrolled(RecyclerView recyclerView, int i, int i2) {
                boolean z = true;
                super.onScrolled(recyclerView, i, i2);
                LayoutManager layoutManager = recyclerView.getLayoutManager();
                if (layoutManager instanceof LinearLayoutManager) {
                    int i3;
                    int findFirstVisibleItemPosition = ((LinearLayoutManager) layoutManager).findFirstVisibleItemPosition();
                    if (findFirstVisibleItemPosition > 4) {
                        i3 = 1;
                    } else {
                        i3 = 2;
                    }
                    if (this.b != i3) {
                        this.b = i3;
                        if (findFirstVisibleItemPosition <= 4) {
                            z = false;
                        }
                        f fVar = new f(z);
                        if (findFirstVisibleItemPosition > 4) {
                            fVar.b = this.a.j;
                        }
                        c.a().d(fVar);
                    }
                }
            }
        });
    }

    private void j() {
        this.refreshLayout.a(new com.scwang.smartrefresh.layout.e.c(this) {
            final /* synthetic */ FeedMainFragment a;

            {
                this.a = r1;
            }

            public void a_(h hVar) {
                if (this.a.g != null) {
                    this.a.b(this.a.g.a, this.a.g.b);
                    this.a.g = null;
                    return;
                }
                this.a.b("down", false);
            }
        });
        this.refreshLayout.a(new com.scwang.smartrefresh.layout.e.a(this) {
            final /* synthetic */ FeedMainFragment a;

            {
                this.a = r1;
            }

            public void a(h hVar) {
                this.a.k();
            }
        });
    }

    private void a(String str) {
        this.tipsView.setText(str);
        this.tipsView.setVisibility(0);
        this.tipsView.postDelayed(new Runnable(this) {
            final /* synthetic */ FeedMainFragment a;

            {
                this.a = r1;
            }

            public void run() {
                if (this.a.tipsView != null) {
                    this.a.tipsView.setVisibility(8);
                }
            }
        }, 1500);
    }

    private void a(LoadResultType loadResultType) {
        if (this.recyclerView != null) {
            this.recyclerView.scrollToPosition(0);
            switch (loadResultType) {
                case MEMBER:
                    this.recyclerView.setLayoutManager(this.b);
                    this.recyclerView.setAdapter(this.c);
                    this.refreshLayout.a(false);
                    return;
                case POST:
                    this.recyclerView.setLayoutManager(this.a);
                    this.recyclerView.setAdapter(this.d);
                    this.refreshLayout.a(true);
                    this.refreshLayout.g(false);
                    return;
                default:
                    return;
            }
        }
    }

    private void b(String str, boolean z) {
        this.e.a(str, z, new a(this) {
            final /* synthetic */ FeedMainFragment a;

            {
                this.a = r1;
            }

            public void a(LoadResultType loadResultType, boolean z) {
                this.a.a(loadResultType);
                this.a.loadingView.setVisibility(8);
                if (this.a.refreshLayout != null) {
                    this.a.refreshLayout.m();
                }
            }

            public void a(int i) {
                if (i == 0) {
                    this.a.a("暂无更新，到推荐看看吧");
                } else {
                    this.a.a("有 " + i + " 条内容更新");
                }
            }

            public void a() {
                if (this.a.refreshLayout != null) {
                    this.a.refreshLayout.m();
                }
            }
        });
    }

    private void k() {
        this.e.b(new a(this) {
            final /* synthetic */ FeedMainFragment a;

            {
                this.a = r1;
            }

            public void a(LoadResultType loadResultType, boolean z) {
                if (this.a.refreshLayout != null) {
                    if (z) {
                        this.a.refreshLayout.n();
                        this.a.refreshLayout.g(false);
                        return;
                    }
                    this.a.refreshLayout.o();
                }
            }

            public void a(int i) {
            }

            public void a() {
                if (this.a.refreshLayout != null) {
                    this.a.refreshLayout.n();
                    this.a.refreshLayout.g(false);
                }
            }
        });
    }
}
