package cn.xiaochuankeji.tieba.ui.homepage.ugc;

import android.os.Build.VERSION;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.RecyclerView.ViewHolder;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.widget.FrameLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import cn.xiaochuankeji.tieba.R;
import cn.xiaochuankeji.tieba.background.modules.a.a.a;
import cn.xiaochuankeji.tieba.background.utils.g;
import cn.xiaochuankeji.tieba.json.UgcAttriContentJson;
import cn.xiaochuankeji.tieba.json.UgcVideoInfoBean;
import cn.xiaochuankeji.tieba.network.custom.exception.ClientErrorException;
import cn.xiaochuankeji.tieba.ui.homepage.e;
import cn.xiaochuankeji.tieba.ui.mediabrowse.component.RoundProgressBar;
import cn.xiaochuankeji.tieba.ui.ugcvideodetail.UgcVideoActivity;
import cn.xiaochuankeji.tieba.ui.ugcvideodetail.b;
import cn.xiaochuankeji.tieba.widget.CustomEmptyView;
import com.alibaba.fastjson.JSON;
import com.lnyp.recyclerview.EndlessRecyclerOnScrollListener;
import com.lnyp.recyclerview.ExStaggeredGridLayoutManager;
import com.lnyp.recyclerview.RecyclerViewLoadingFooter.State;
import com.lnyp.recyclerview.d;
import in.srain.cube.views.ptr.PtrFrameLayout;
import java.util.List;
import org.greenrobot.eventbus.ThreadMode;
import org.greenrobot.eventbus.l;
import rx.j;

public class h extends c implements a {
    private PtrFrameLayout a;
    private RecyclerView b;
    private CustomEmptyView c;
    private RelativeLayout d;
    private RoundProgressBar e;
    private TextView f;
    private e g;
    private FrameLayout h;
    private b i;
    private g j;
    private com.lnyp.recyclerview.a k;
    private ExStaggeredGridLayoutManager l;
    private int m = 2;
    private boolean n = true;
    private boolean o = false;
    private boolean p = false;
    private int q;
    private cn.xiaochuankeji.tieba.api.ugcvideo.a r = new cn.xiaochuankeji.tieba.api.ugcvideo.a();
    private cn.xiaochuankeji.tieba.ui.videomaker.sticker.a.a s;
    private UgcAttriContentJson t = new UgcAttriContentJson();
    private UgcVideoInfoBean u;
    private boolean v = false;
    private boolean w = false;
    private EndlessRecyclerOnScrollListener x = new EndlessRecyclerOnScrollListener(this) {
        final /* synthetic */ h a;

        {
            this.a = r1;
        }

        public void a(View view) {
            super.a(view);
            if (this.a.t.more == 1) {
                d.a(this.a.getActivity(), this.a.b, 6, State.Loading, null);
                this.a.m = 3;
                this.a.g();
                return;
            }
            d.a(this.a.getActivity(), this.a.b, 6, State.TheEnd, null);
        }

        public void onScrolled(RecyclerView recyclerView, int i, int i2) {
            super.onScrolled(recyclerView, i, i2);
            this.a.e();
        }
    };

    public static h c() {
        return new h();
    }

    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        cn.xiaochuankeji.tieba.background.a.g().a(this);
    }

    @Nullable
    public View onCreateView(LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, @Nullable Bundle bundle) {
        ViewGroup viewGroup2 = (ViewGroup) layoutInflater.inflate(R.layout.fragment_ugcvideo_atten, null, false);
        this.c = (CustomEmptyView) viewGroup2.findViewById(R.id.vEmptyView);
        this.c.a(R.drawable.img_attri_empty, "暂无关注，先去推荐里看看吧");
        this.a = (PtrFrameLayout) viewGroup2.findViewById(R.id.ptrFrameLayout);
        this.b = (RecyclerView) viewGroup2.findViewById(R.id.ultimateRecyclerView);
        this.d = (RelativeLayout) viewGroup2.findViewById(R.id.rl_progress);
        this.e = (RoundProgressBar) viewGroup2.findViewById(R.id.roundPBar);
        this.f = (TextView) viewGroup2.findViewById(R.id.tv_progress);
        f();
        d();
        this.h = (FrameLayout) viewGroup2.findViewById(R.id.rootView);
        return viewGroup2;
    }

    private void d() {
        int a;
        this.j = new g(getActivity(), this.t.list, -1, true);
        this.k = new com.lnyp.recyclerview.a(this.j);
        this.l = new ExStaggeredGridLayoutManager(2, 1);
        View view = new View(getContext());
        view.setBackgroundColor(0);
        if (VERSION.SDK_INT >= 21) {
            a = cn.xiaochuankeji.tieba.ui.utils.e.a(10.0f);
        } else {
            a = cn.xiaochuankeji.tieba.ui.utils.e.a(5.0f);
        }
        LayoutParams layoutParams = new StaggeredGridLayoutManager.LayoutParams(-1, a);
        layoutParams.setFullSpan(true);
        view.setLayoutParams(layoutParams);
        this.b.setAdapter(this.k);
        this.l.a(new com.lnyp.recyclerview.b(this.b.getAdapter(), 2));
        this.b.setLayoutManager(this.l);
        if (VERSION.SDK_INT >= 21) {
            this.b.addItemDecoration(((com.lnyp.flexibledivider.a.a) ((com.lnyp.flexibledivider.a.a) new com.lnyp.flexibledivider.a.a(getContext()).a(0)).b(cn.xiaochuankeji.tieba.ui.utils.e.a(12.0f))).b().c());
            this.b.addItemDecoration(((com.marshalchen.ultimaterecyclerview.ui.divideritemdecoration.a.a) ((com.marshalchen.ultimaterecyclerview.ui.divideritemdecoration.a.a) new com.marshalchen.ultimaterecyclerview.ui.divideritemdecoration.a.a(getContext()).a(0)).b(cn.xiaochuankeji.tieba.ui.utils.e.a(10.0f))).b());
        } else {
            this.b.addItemDecoration(((com.lnyp.flexibledivider.a.a) ((com.lnyp.flexibledivider.a.a) new com.lnyp.flexibledivider.a.a(getContext()).a(0)).b(cn.xiaochuankeji.tieba.ui.utils.e.a(5.0f))).b().c());
            this.b.addItemDecoration(((com.marshalchen.ultimaterecyclerview.ui.divideritemdecoration.a.a) ((com.marshalchen.ultimaterecyclerview.ui.divideritemdecoration.a.a) new com.marshalchen.ultimaterecyclerview.ui.divideritemdecoration.a.a(getContext()).a(0)).b(cn.xiaochuankeji.tieba.ui.utils.e.a(5.0f))).b());
        }
        this.b.setItemAnimator(new DefaultItemAnimator(this) {
            final /* synthetic */ h a;

            {
                this.a = r1;
            }

            public boolean canReuseUpdatedViewHolder(ViewHolder viewHolder) {
                return true;
            }
        });
        this.b.addOnScrollListener(this.x);
        com.lnyp.recyclerview.e.a(this.b, view);
    }

    private void e() {
        boolean z = true;
        int[] iArr = new int[2];
        this.l.findLastVisibleItemPositions(iArr);
        int max = Math.max(iArr[0], iArr[1]);
        UGCTabActivity uGCTabActivity = (UGCTabActivity) getActivity();
        if (max <= 6) {
            z = false;
        }
        uGCTabActivity.b(z);
    }

    private void f() {
        this.g = new e(getContext(), R.color.CB);
        this.a.a(this.g);
        this.a.setHeaderView(this.g);
        this.a.setPtrHandler(new in.srain.cube.views.ptr.a(this) {
            final /* synthetic */ h a;

            {
                this.a = r1;
            }

            public boolean a(PtrFrameLayout ptrFrameLayout, View view, View view2) {
                return !this.a.b.canScrollVertically(-1);
            }

            public void a(PtrFrameLayout ptrFrameLayout) {
                if (!this.a.w) {
                    cn.xiaochuankeji.tieba.background.utils.h.a("zy_event_ugcvideo_tab_follow", "下拉刷新次数");
                }
                this.a.w = false;
                this.a.g();
                this.a.b.scrollToPosition(0);
            }
        });
    }

    public void onViewCreated(View view, @Nullable Bundle bundle) {
        super.onViewCreated(view, bundle);
        this.i = new b(getActivity(), new b.a(this) {
            final /* synthetic */ h a;

            {
                this.a = r1;
            }

            public void a(boolean z, String str) {
                this.a.d.setVisibility(8);
                g.a(str);
            }

            public void a(int i, int i2) {
                int i3 = (int) ((((float) i2) * 100.0f) / ((float) i));
                this.a.e.setMax(100);
                this.a.e.setProgress(i3);
                this.a.f.setText("下载中" + i3 + "%");
            }

            public void a() {
                this.a.d.setVisibility(0);
            }
        });
    }

    public void onActivityCreated(@Nullable Bundle bundle) {
        super.onActivityCreated(bundle);
        if (bundle == null) {
            this.q = 1;
        } else {
            this.q = 2;
        }
    }

    private void g() {
        String str = null;
        if (!this.v) {
            String str2;
            this.v = true;
            if (this.m == 3) {
                str2 = this.t.downOffset;
            } else {
                str = this.t.upOffset;
                str2 = null;
            }
            cn.xiaochuankeji.tieba.background.post.b.a().c(System.currentTimeMillis());
            cn.xiaochuankeji.tieba.background.utils.h.a("zy_event_ugcvideo_tab_follow", "总刷新次数");
            if (this.m == 3) {
                cn.xiaochuankeji.tieba.background.utils.h.a("zy_event_ugcvideo_tab_follow", "上拉刷新次数");
            }
            this.r.a(str, str2).a(rx.a.b.a.a()).b(new j<UgcAttriContentJson>(this) {
                final /* synthetic */ h a;

                {
                    this.a = r1;
                }

                public /* synthetic */ void onNext(Object obj) {
                    a((UgcAttriContentJson) obj);
                }

                public void onCompleted() {
                    this.a.v = false;
                    if (!this.a.isAdded()) {
                        return;
                    }
                    if (this.a.m == 2) {
                        this.a.a.c();
                    } else if (this.a.m == 3) {
                        this.a.b.postDelayed(new Runnable(this) {
                            final /* synthetic */ AnonymousClass5 a;

                            {
                                this.a = r1;
                            }

                            public void run() {
                                d.a(this.a.a.getActivity(), this.a.a.b, 6, State.Normal, null);
                            }
                        }, 1000);
                    }
                }

                public void onError(Throwable th) {
                    this.a.v = false;
                    if (this.a.isAdded()) {
                        this.a.j();
                        if (this.a.t.list.size() == 0) {
                            this.a.c.b();
                        }
                        if (th instanceof ClientErrorException) {
                            g.b(th.getMessage());
                        } else if (cn.htjyb.netlib.h.a(this.a.getContext())) {
                            g.a("网络超时");
                        } else {
                            g.b("网络不给力哦~");
                        }
                        this.a.a.c();
                        d.a(this.a.getActivity(), this.a.b, 6, State.Normal, null);
                        this.a.m = 2;
                    }
                }

                public void a(UgcAttriContentJson ugcAttriContentJson) {
                    if (this.a.isAdded()) {
                        this.a.j();
                        int size = ugcAttriContentJson.list.size();
                        if (this.a.m == 3) {
                            if (size == 0) {
                                this.a.t.more = 0;
                            } else {
                                this.a.t.list.addAll(ugcAttriContentJson.list);
                                this.a.t.downOffset = ugcAttriContentJson.downOffset;
                                this.a.t.more = ugcAttriContentJson.more;
                            }
                        } else if (size > 0) {
                            if (this.a.t.clearCache || ugcAttriContentJson.more == 1) {
                                this.a.t.clearCache = false;
                                this.a.t.list.clear();
                                this.a.t.list.addAll(ugcAttriContentJson.list);
                                this.a.t.more = 1;
                                this.a.t.upOffset = ugcAttriContentJson.upOffset;
                                this.a.t.downOffset = ugcAttriContentJson.downOffset;
                            } else {
                                this.a.t.list.addAll(0, ugcAttriContentJson.list);
                                this.a.t.upOffset = ugcAttriContentJson.upOffset;
                            }
                        }
                        if (this.a.t.list.size() > 0) {
                            UgcCrumbManger.a().a(((UgcVideoInfoBean) this.a.t.list.get(0)).id);
                        }
                        this.a.j.notifyDataSetChanged();
                        if (this.a.t.list.size() == 0) {
                            this.a.c.b();
                        } else {
                            this.a.c.setVisibility(8);
                        }
                        this.a.m = 2;
                        this.a.k();
                    }
                }
            });
        }
    }

    public void onResume() {
        super.onResume();
        this.o = true;
        h();
    }

    public void onPause() {
        super.onPause();
        this.o = false;
    }

    public void setUserVisibleHint(boolean z) {
        super.setUserVisibleHint(z);
        this.p = z;
        h();
    }

    private void h() {
        boolean z = false;
        if (this.p && this.o) {
            e();
            cn.xiaochuankeji.tieba.background.utils.h.a("zy_event_ugcvideo_tab_follow", "页面进入");
            if (this.n) {
                this.n = false;
                i();
                return;
            }
            boolean d = cn.xiaochuankeji.tieba.background.a.g().d();
            if (!l() && !d) {
                d = cn.xiaochuankeji.tieba.background.post.b.a().d();
                if (this.t.list.size() == 0) {
                    z = true;
                }
                if (!d && !z) {
                    return;
                }
                if (z) {
                    this.m = 1;
                    g();
                    return;
                }
                this.m = 1;
                this.w = true;
                this.a.d();
                if (this.g != null) {
                    this.g.a();
                }
            }
        }
    }

    private void i() {
        this.s = new cn.xiaochuankeji.tieba.ui.videomaker.sticker.a.a(cn.xiaochuankeji.tieba.background.a.e().r() + "ugcvideo_attri_content.dat");
        this.s.a().a(rx.a.b.a.a()).b(new j<String>(this) {
            final /* synthetic */ h a;

            {
                this.a = r1;
            }

            public /* synthetic */ void onNext(Object obj) {
                a((String) obj);
            }

            public void onCompleted() {
            }

            public void onError(Throwable th) {
            }

            public void a(String str) {
                if (!TextUtils.isEmpty(str)) {
                    UgcAttriContentJson ugcAttriContentJson = (UgcAttriContentJson) JSON.parseObject(str, UgcAttriContentJson.class);
                    this.a.t.more = ugcAttriContentJson.more;
                    this.a.t.upOffset = ugcAttriContentJson.upOffset;
                    this.a.t.downOffset = ugcAttriContentJson.downOffset;
                    this.a.t.clearCache = ugcAttriContentJson.clearCache;
                    this.a.t.list.clear();
                    if (ugcAttriContentJson.list.size() > 0) {
                        if (this.a.t.list.size() > 288) {
                            ugcAttriContentJson.list = ugcAttriContentJson.list.subList(0, 288);
                        }
                        this.a.t.list.addAll(ugcAttriContentJson.list);
                    }
                    this.a.j.notifyDataSetChanged();
                }
                if (!this.a.l() && this.a.q == 1) {
                    this.a.m = 1;
                    this.a.w = true;
                    this.a.a.d();
                    if (this.a.g != null) {
                        this.a.g.a();
                    }
                }
            }
        });
    }

    private void j() {
        List list = this.t.list;
        for (int i = 0; i < list.size(); i++) {
            if (((UgcVideoInfoBean) list.get(i)).isCreatedByUserJustNow) {
                ((UgcVideoInfoBean) list.get(i)).isCreatedByUserJustNow = false;
                this.j.notifyItemChanged(i);
            }
        }
    }

    private void k() {
        this.s.a(JSON.toJSONString(this.t));
    }

    @l(a = ThreadMode.MAIN)
    public void onEventMainThread(UgcVideoActivity.b bVar) {
        if (isVisible()) {
            List list = this.t.list;
            int i = 0;
            while (i < list.size()) {
                UgcVideoInfoBean ugcVideoInfoBean = (UgcVideoInfoBean) list.get(i);
                if (ugcVideoInfoBean.id != bVar.d) {
                    i++;
                } else if (bVar.b == null) {
                    a(ugcVideoInfoBean.id);
                    return;
                } else {
                    list.set(i, bVar.b);
                    this.j.notifyItemChanged(i);
                    k();
                    return;
                }
            }
        }
    }

    private void a(long j) {
        List list = this.t.list;
        for (int i = 0; i < list.size(); i++) {
            if (((UgcVideoInfoBean) list.get(i)).id == j) {
                list.remove(i);
                k();
                this.j.notifyItemRemoved(i);
                break;
            }
        }
        if (list.size() == 0) {
            this.c.b();
        }
    }

    @l(a = ThreadMode.MAIN)
    public void ugcDownloadVideo(cn.xiaochuankeji.tieba.background.d.e eVar) {
        this.i.a(eVar.a);
    }

    public void a(UgcVideoInfoBean ugcVideoInfoBean) {
        this.u = ugcVideoInfoBean;
    }

    private boolean l() {
        if (this.u == null) {
            return false;
        }
        this.t.list.add(0, this.u);
        this.t.clearCache = true;
        this.t.upOffset = null;
        k();
        this.u.isCreatedByUserJustNow = true;
        this.j.notifyItemInserted(0);
        this.c.setVisibility(8);
        if (this.l != null) {
            this.l.scrollToPosition(0);
        }
        this.u = null;
        return true;
    }

    public void b() {
        if (this.c.getVisibility() != 0) {
            this.m = 2;
            this.w = true;
            this.a.d();
            if (this.g != null) {
                this.g.a();
            }
        }
    }

    public void onDestroy() {
        super.onDestroy();
        cn.xiaochuankeji.tieba.background.a.g().b(this);
    }

    public void onTokenChanged() {
        if (!cn.xiaochuankeji.tieba.background.a.g().d()) {
            this.t.list.clear();
            this.t.more = 0;
            this.t.upOffset = null;
            this.t.downOffset = null;
            this.j.notifyDataSetChanged();
            this.c.b();
            if (this.b != null) {
                d.a(getActivity(), this.b, 6, State.Hide, null);
            }
        }
    }
}
