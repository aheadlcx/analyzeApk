package cn.xiaochuankeji.tieba.ui.homepage.ugc;

import android.os.Build.VERSION;
import android.os.Bundle;
import android.os.Handler;
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
import android.widget.RelativeLayout;
import android.widget.TextView;
import cn.xiaochuankeji.tieba.R;
import cn.xiaochuankeji.tieba.api.ugcvideo.a;
import cn.xiaochuankeji.tieba.background.utils.g;
import cn.xiaochuankeji.tieba.background.utils.h;
import cn.xiaochuankeji.tieba.json.UgcEventSummaryJson;
import cn.xiaochuankeji.tieba.json.UgcEventSummaryJson.UgcEventJsonInRecommend;
import cn.xiaochuankeji.tieba.json.UgcVideoInfoBean;
import cn.xiaochuankeji.tieba.json.UgcVideoRecommendListJson;
import cn.xiaochuankeji.tieba.network.custom.exception.ClientErrorException;
import cn.xiaochuankeji.tieba.ui.base.MainActivity;
import cn.xiaochuankeji.tieba.ui.homepage.PostLoadedTipsView;
import cn.xiaochuankeji.tieba.ui.homepage.e;
import cn.xiaochuankeji.tieba.ui.mediabrowse.component.RoundProgressBar;
import cn.xiaochuankeji.tieba.ui.ugcvideodetail.UgcVideoActivity;
import cn.xiaochuankeji.tieba.ui.ugcvideodetail.b;
import cn.xiaochuankeji.tieba.widget.CustomEmptyView;
import com.alibaba.fastjson.JSON;
import com.android.a.a.c;
import com.danikula.videocache.q;
import com.lnyp.recyclerview.EndlessRecyclerOnScrollListener;
import com.lnyp.recyclerview.ExStaggeredGridLayoutManager;
import com.lnyp.recyclerview.RecyclerViewLoadingFooter.State;
import com.lnyp.recyclerview.d;
import in.srain.cube.views.ptr.PtrFrameLayout;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import org.greenrobot.eventbus.ThreadMode;
import org.greenrobot.eventbus.l;
import rx.j;

public class i extends c {
    private int A = 0;
    private EndlessRecyclerOnScrollListener B = new EndlessRecyclerOnScrollListener(this) {
        final /* synthetic */ i a;

        {
            this.a = r1;
        }

        public void a(View view) {
            super.a(view);
            d.a(this.a.getActivity(), this.a.b, 6, State.Loading, null);
            this.a.u = 3;
            this.a.i();
            h.a("zy_event_ugcvideo_recommend", "上拉刷新次数");
        }

        public void onScrollStateChanged(RecyclerView recyclerView, int i) {
            super.onScrollStateChanged(recyclerView, i);
            if (i == 0) {
                int thisHeight = this.a.i.getThisHeight();
                if (this.a.z > 0 && this.a.z < thisHeight) {
                    if (((float) this.a.z) / ((float) thisHeight) >= 0.5f) {
                        recyclerView.smoothScrollBy(0, thisHeight - this.a.z);
                    } else {
                        recyclerView.smoothScrollBy(0, -this.a.z);
                    }
                }
            }
            this.a.A = i;
        }

        public void onScrolled(RecyclerView recyclerView, int i, int i2) {
            long j = 0;
            super.onScrolled(recyclerView, i, i2);
            this.a.l();
            this.a.t.invalidateSpanAssignments();
            if (this.a.s.c() > 1) {
                this.a.z = this.a.z + i2;
                int thisHeight = this.a.i.getThisHeight();
                Object tag;
                if (this.a.z >= thisHeight) {
                    this.a.z = 0;
                    this.a.s.c(this.a.i);
                    this.a.b.scrollToPosition(0);
                    tag = this.a.i.getTag();
                    if (tag != null && (tag instanceof UgcEventJsonInRecommend)) {
                        j = ((UgcEventJsonInRecommend) tag).id;
                    }
                    ((UGCTabActivity) this.a.getActivity()).a(j);
                } else if (this.a.z <= 0 || this.a.z >= thisHeight) {
                    this.a.z = 0;
                    this.a.i.setVisibility(0);
                    ((UGCTabActivity) this.a.getActivity()).a(0);
                } else {
                    tag = this.a.i.getTag();
                    if (tag != null && (tag instanceof UgcEventJsonInRecommend)) {
                        long j2 = (long) ((UgcEventJsonInRecommend) tag).img.id;
                        if (0 != j2) {
                            this.a.i.setVisibility(4);
                            ((UGCTabActivity) this.a.getActivity()).a(j2, Math.max(Math.min(((float) this.a.z) / ((float) thisHeight), 1.0f), 0.0f));
                        }
                    }
                }
            }
        }
    };
    private Runnable C = new Runnable(this) {
        final /* synthetic */ i a;

        {
            this.a = r1;
        }

        public void run() {
            this.a.c.setVisibility(8);
        }
    };
    private PtrFrameLayout a;
    private RecyclerView b;
    private PostLoadedTipsView c;
    private RelativeLayout d;
    private RoundProgressBar e;
    private TextView f;
    private e g;
    private CustomEmptyView h;
    private d i;
    private Handler j = new Handler();
    private a k = new a();
    private ArrayList<UgcVideoInfoBean> l = new ArrayList();
    private int m = -1;
    private b n;
    private boolean o = true;
    private boolean p = false;
    private boolean q = false;
    private g r;
    private com.lnyp.recyclerview.a s;
    private ExStaggeredGridLayoutManager t;
    private int u = 2;
    private boolean v = false;
    private cn.xiaochuankeji.tieba.ui.videomaker.sticker.a.a w = new cn.xiaochuankeji.tieba.ui.videomaker.sticker.a.a(n());
    private boolean x = false;
    private UgcEventSummaryJson y;
    private int z = 0;

    public static i c() {
        return new i();
    }

    @Nullable
    public View onCreateView(LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, @Nullable Bundle bundle) {
        ViewGroup viewGroup2 = (ViewGroup) layoutInflater.inflate(R.layout.fragment_ugcvideo_recommend, null, false);
        this.a = (PtrFrameLayout) viewGroup2.findViewById(R.id.ptrFrameLayout);
        this.b = (RecyclerView) viewGroup2.findViewById(R.id.ultimateRecyclerView);
        this.d = (RelativeLayout) viewGroup2.findViewById(R.id.rl_progress);
        this.e = (RoundProgressBar) viewGroup2.findViewById(R.id.roundPBar);
        this.f = (TextView) viewGroup2.findViewById(R.id.tv_progress);
        this.h = (CustomEmptyView) viewGroup2.findViewById(R.id.vEmptyView);
        this.h.a(R.drawable.ic_topic_empty_post, "此刻啥都没有");
        f();
        e();
        d();
        return viewGroup2;
    }

    public void a(boolean z) {
        super.a(z);
        if (this.r != null) {
            this.r.notifyDataSetChanged();
        }
    }

    private void d() {
        if (getActivity() != null) {
            this.c = new PostLoadedTipsView(getActivity());
            this.c.setVisibility(8);
            RelativeLayout relativeLayout = (RelativeLayout) getActivity().findViewById(R.id.rlRootView);
            LayoutParams layoutParams = new RelativeLayout.LayoutParams(-2, -2);
            int dimensionPixelSize = getResources().getDimensionPixelSize(R.dimen.navbar_height);
            int i = 0;
            if (!c.a()) {
                i = getResources().getDimensionPixelOffset(R.dimen.status_bar_height);
            }
            layoutParams.topMargin = (i + dimensionPixelSize) + cn.xiaochuankeji.tieba.ui.utils.e.a(-10.0f);
            layoutParams.addRule(14);
            this.c.setId(R.id.id_ugc_recommend_tip_view);
            relativeLayout.addView(this.c, layoutParams);
        }
    }

    private void e() {
        int a;
        this.r = new g(getActivity(), this.l, this.m, false);
        this.s = new com.lnyp.recyclerview.a(this.r);
        this.t = new ExStaggeredGridLayoutManager(2, 1);
        this.t.setGapStrategy(0);
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
        this.b.setAdapter(this.s);
        this.t.a(new com.lnyp.recyclerview.b(this.s, 1));
        this.b.setLayoutManager(this.t);
        if (VERSION.SDK_INT >= 21) {
            this.b.addItemDecoration(((com.lnyp.flexibledivider.a.a) ((com.lnyp.flexibledivider.a.a) new com.lnyp.flexibledivider.a.a(getContext()).a(0)).b(cn.xiaochuankeji.tieba.ui.utils.e.a(12.0f))).b().c());
            this.b.addItemDecoration(((com.marshalchen.ultimaterecyclerview.ui.divideritemdecoration.a.a) ((com.marshalchen.ultimaterecyclerview.ui.divideritemdecoration.a.a) new com.marshalchen.ultimaterecyclerview.ui.divideritemdecoration.a.a(getContext()).a(0)).b(cn.xiaochuankeji.tieba.ui.utils.e.a(10.0f))).b());
        } else {
            this.b.addItemDecoration(((com.lnyp.flexibledivider.a.a) ((com.lnyp.flexibledivider.a.a) new com.lnyp.flexibledivider.a.a(getContext()).a(0)).b(cn.xiaochuankeji.tieba.ui.utils.e.a(5.0f))).b().c());
            this.b.addItemDecoration(((com.marshalchen.ultimaterecyclerview.ui.divideritemdecoration.a.a) ((com.marshalchen.ultimaterecyclerview.ui.divideritemdecoration.a.a) new com.marshalchen.ultimaterecyclerview.ui.divideritemdecoration.a.a(getContext()).a(0)).b(cn.xiaochuankeji.tieba.ui.utils.e.a(5.0f))).b());
        }
        this.b.setItemAnimator(new DefaultItemAnimator(this) {
            final /* synthetic */ i a;

            {
                this.a = r1;
            }

            public boolean canReuseUpdatedViewHolder(ViewHolder viewHolder) {
                return true;
            }
        });
        this.b.addOnScrollListener(this.B);
        com.lnyp.recyclerview.e.a(this.b, view);
        this.i = new d(getContext());
    }

    private void f() {
        this.g = new e(getContext(), R.color.CB);
        this.a.a(this.g);
        this.a.setHeaderView(this.g);
        this.a.setPtrHandler(new in.srain.cube.views.ptr.a(this) {
            final /* synthetic */ i a;

            {
                this.a = r1;
            }

            public boolean a(PtrFrameLayout ptrFrameLayout, View view, View view2) {
                return !this.a.b.canScrollVertically(-1);
            }

            public void a(PtrFrameLayout ptrFrameLayout) {
                if (!this.a.v) {
                    h.a("zy_event_ugcvideo_recommend", "下拉刷新次数");
                    this.a.g();
                }
                this.a.v = false;
                this.a.i();
                this.a.b.scrollToPosition(0);
            }
        });
    }

    private void g() {
        this.k.c().a(rx.a.b.a.a()).b(new j<UgcEventSummaryJson>(this) {
            final /* synthetic */ i a;

            {
                this.a = r1;
            }

            public /* synthetic */ void onNext(Object obj) {
                a((UgcEventSummaryJson) obj);
            }

            public void onCompleted() {
            }

            public void onError(Throwable th) {
                g.a(th.getMessage());
            }

            public void a(UgcEventSummaryJson ugcEventSummaryJson) {
                if (this.a.isAdded()) {
                    this.a.y = ugcEventSummaryJson;
                    this.a.o();
                    this.a.h();
                }
            }
        });
    }

    private void h() {
        ((UGCTabActivity) getActivity()).a(this.y.eventIcon);
        UgcEventJsonInRecommend ugcEventJsonInRecommend = this.y.eventJson;
        if (ugcEventJsonInRecommend == null) {
            if (this.s.c() > 1) {
                this.s.c(this.i);
            }
        } else if (this.s.c() > 1) {
            Object tag = this.i.getTag();
            if (tag != null && (tag instanceof UgcEventJsonInRecommend) && ((UgcEventJsonInRecommend) tag).id != ugcEventJsonInRecommend.id) {
                this.i.setData(ugcEventJsonInRecommend);
            }
        } else {
            this.i.setData(ugcEventJsonInRecommend);
            this.i.setVisibility(0);
            this.s.a(this.i);
        }
    }

    public void onViewCreated(View view, @Nullable Bundle bundle) {
        super.onViewCreated(view, bundle);
        this.n = new b(getActivity(), new b.a(this) {
            final /* synthetic */ i a;

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

    private void i() {
        int i = 1;
        if (!this.x) {
            String str;
            this.x = true;
            if (this.u == 1) {
                str = null;
            } else if (this.u == 2) {
                MainActivity.a("tab_home_page");
                str = "down";
                i = 0;
            } else if (this.u == 3) {
                MainActivity.a("tab_home_page");
                str = "up";
                i = 0;
            } else {
                return;
            }
            cn.xiaochuankeji.tieba.background.post.b.a().a(System.currentTimeMillis());
            h.a("zy_event_ugcvideo_recommend", "总刷新次数");
            this.k.a(str, i).a(rx.a.b.a.a()).b(new j<UgcVideoRecommendListJson>(this) {
                final /* synthetic */ i a;

                {
                    this.a = r1;
                }

                public /* synthetic */ void onNext(Object obj) {
                    a((UgcVideoRecommendListJson) obj);
                }

                public void onCompleted() {
                    this.a.x = false;
                    if (!this.a.isAdded()) {
                        return;
                    }
                    if (this.a.u == 2) {
                        this.a.a.c();
                    } else if (this.a.u == 3) {
                        this.a.b.postDelayed(new Runnable(this) {
                            final /* synthetic */ AnonymousClass6 a;

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
                    this.a.x = false;
                    if (this.a.isAdded()) {
                        if (this.a.l.size() == 0) {
                            this.a.h.b();
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
                        this.a.u = 2;
                    }
                }

                public void a(UgcVideoRecommendListJson ugcVideoRecommendListJson) {
                    if (this.a.isAdded()) {
                        List a = this.a.a(ugcVideoRecommendListJson.followerList);
                        int size = a != null ? a.size() : 0;
                        if (this.a.u == 1) {
                            if (size > 0) {
                                this.a.a(a, true);
                            }
                            this.a.a(size);
                        } else if (this.a.u == 2) {
                            if (size > 0) {
                                this.a.a(a, true);
                            }
                            this.a.a(size);
                        } else if (this.a.u == 3) {
                            if (size > 0) {
                                this.a.a(a, false);
                            } else {
                                g.a("没有更多了");
                            }
                        }
                        if (this.a.l.size() == 0) {
                            this.a.h.b();
                        } else {
                            this.a.h.setVisibility(8);
                        }
                        this.a.u = 2;
                    }
                }
            });
        }
    }

    private List<UgcVideoInfoBean> a(List<UgcVideoInfoBean> list) {
        if (this.l == null || this.l.size() == 0 || list == null || list.size() == 0) {
            return list;
        }
        List<UgcVideoInfoBean> arrayList = new ArrayList();
        for (UgcVideoInfoBean ugcVideoInfoBean : list) {
            Object obj;
            Iterator it = this.l.iterator();
            while (it.hasNext()) {
                if (((UgcVideoInfoBean) it.next()).id == ugcVideoInfoBean.id) {
                    obj = 1;
                    break;
                }
            }
            obj = null;
            if (obj == null) {
                arrayList.add(ugcVideoInfoBean);
            }
        }
        return arrayList;
    }

    public void onDestroy() {
        super.onDestroy();
        this.j.removeCallbacks(this.C);
    }

    public void setUserVisibleHint(boolean z) {
        super.setUserVisibleHint(z);
        this.q = z;
        j();
    }

    public void onResume() {
        super.onResume();
        this.p = true;
        j();
        if (this.r != null) {
            this.r.a();
        }
    }

    public void onPause() {
        super.onPause();
        this.p = false;
        q.a().b().a();
    }

    private void j() {
        if (this.q && this.p) {
            l();
            h.a("zy_event_ugcvideo_recommend", "此刻流展现");
            if (this.o) {
                this.o = false;
                m();
                return;
            }
            k();
        }
    }

    private void k() {
        if (cn.xiaochuankeji.tieba.background.post.b.a().c()) {
            this.u = 1;
            this.v = true;
            this.a.d();
            if (this.g != null) {
                this.g.a();
            }
        }
    }

    private void l() {
        boolean z = true;
        int[] iArr = new int[2];
        this.t.findLastVisibleItemPositions(iArr);
        int max = Math.max(iArr[0], iArr[1]);
        UGCTabActivity uGCTabActivity = (UGCTabActivity) getActivity();
        if (max <= 6) {
            z = false;
        }
        uGCTabActivity.b(z);
    }

    private void m() {
        this.w.a().b(new j<String>(this) {
            final /* synthetic */ i a;

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
                if (TextUtils.isEmpty(str)) {
                    this.a.u = 1;
                    this.a.v = true;
                    this.a.a.d();
                    if (this.a.g != null) {
                        this.a.g.a();
                        return;
                    }
                    return;
                }
                UgcVideoRecommendListJson ugcVideoRecommendListJson = (UgcVideoRecommendListJson) JSON.parseObject(str, UgcVideoRecommendListJson.class);
                if (ugcVideoRecommendListJson != null && ugcVideoRecommendListJson.followerList != null && ugcVideoRecommendListJson.followerList.size() > 0) {
                    if (ugcVideoRecommendListJson.followerList.size() > 288) {
                        ugcVideoRecommendListJson.followerList = ugcVideoRecommendListJson.followerList.subList(0, 288);
                    }
                    for (UgcVideoInfoBean ugcVideoInfoBean : ugcVideoRecommendListJson.followerList) {
                        if (ugcVideoInfoBean != null) {
                            this.a.l.add(ugcVideoInfoBean);
                        }
                    }
                    this.a.m = ugcVideoRecommendListJson.refreshItemIndex;
                    this.a.r.a(this.a.m);
                    this.a.h.setVisibility(8);
                    this.a.y = ugcVideoRecommendListJson.summaryJson;
                    this.a.h();
                    this.a.k();
                }
            }
        });
    }

    private String n() {
        return cn.xiaochuankeji.tieba.background.a.e().r() + "ugc_video_recommend_list.dat";
    }

    private void a(List<UgcVideoInfoBean> list, boolean z) {
        if (z) {
            this.m = list.size();
            this.l.addAll(0, list);
        } else {
            this.l.addAll(list);
        }
        this.r.a(this.m);
        o();
    }

    private void a(int i) {
        CharSequence charSequence;
        if (i > 0) {
            charSequence = "为你选出" + i + "条跟拍好帖";
        } else {
            charSequence = "暂无推荐，那就自己拍一个吧";
        }
        this.c.setText(charSequence);
        this.c.setVisibility(0);
        this.j.removeCallbacks(this.C);
        this.j.postDelayed(this.C, 1500);
    }

    private void o() {
        UgcVideoRecommendListJson ugcVideoRecommendListJson = new UgcVideoRecommendListJson();
        ugcVideoRecommendListJson.followerList = this.l;
        ugcVideoRecommendListJson.refreshItemIndex = this.m;
        ugcVideoRecommendListJson.summaryJson = this.y;
        this.w.a(JSON.toJSONString(ugcVideoRecommendListJson));
    }

    @l(a = ThreadMode.MAIN)
    public void updateCarried(UgcVideoActivity.b bVar) {
        if (isVisible()) {
            int i = 0;
            while (i < this.l.size()) {
                UgcVideoInfoBean ugcVideoInfoBean = (UgcVideoInfoBean) this.l.get(i);
                if (ugcVideoInfoBean.id != bVar.d) {
                    i++;
                } else if (bVar.b == null) {
                    a(ugcVideoInfoBean.id);
                    return;
                } else {
                    this.l.set(i, bVar.b);
                    this.r.notifyItemChanged(i);
                    o();
                    return;
                }
            }
        }
    }

    private void a(long j) {
        for (int i = 0; i < this.l.size(); i++) {
            if (((UgcVideoInfoBean) this.l.get(i)).id == j) {
                this.l.remove(i);
                o();
                this.r.notifyItemRemoved(i);
                break;
            }
        }
        if (this.l.size() == 0) {
            this.h.b();
        }
    }

    @l(a = ThreadMode.MAIN)
    public void ugcDownloadVideo(cn.xiaochuankeji.tieba.background.d.e eVar) {
        this.n.a(eVar.a);
    }

    public void b() {
        if (this.h.getVisibility() != 0) {
            this.u = 2;
            this.v = true;
            this.a.d();
            if (this.g != null) {
                this.g.a();
            }
        }
    }
}
