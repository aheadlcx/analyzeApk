package cn.tatagou.sdk.fragment;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.FrameLayout;
import android.widget.GridView;
import android.widget.LinearLayout;
import android.widget.TextView;
import cn.tatagou.sdk.R;
import cn.tatagou.sdk.a.e;
import cn.tatagou.sdk.adapter.d;
import cn.tatagou.sdk.adapter.h;
import cn.tatagou.sdk.d.a;
import cn.tatagou.sdk.pojo.AppHomeData;
import cn.tatagou.sdk.pojo.CommPojo;
import cn.tatagou.sdk.pojo.Config;
import cn.tatagou.sdk.pojo.HomeData;
import cn.tatagou.sdk.pojo.RcmmParam;
import cn.tatagou.sdk.pojo.Special;
import cn.tatagou.sdk.util.j;
import cn.tatagou.sdk.util.k;
import cn.tatagou.sdk.util.l;
import cn.tatagou.sdk.util.n;
import cn.tatagou.sdk.util.o;
import cn.tatagou.sdk.util.p;
import cn.tatagou.sdk.view.IUpdateView;
import cn.tatagou.sdk.view.IUpdateViewManager;
import cn.tatagou.sdk.view.TtgScrollView;
import cn.tatagou.sdk.view.UpdateView;
import cn.tatagou.sdk.view.pullview.PullToRefreshLayout;
import cn.tatagou.sdk.view.pullview.PullableListView;
import cn.tatagou.sdk.view.pullview.c;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import com.alibaba.fastjson.parser.Feature;
import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import okhttp3.ab;
import retrofit2.b;

public class TtgTabFragment extends BaseFragment {
    private static final String b = TtgTabFragment.class.getSimpleName();
    private b<ab> A;
    private String B;
    private String C;
    private TtgScrollView D;
    private cn.tatagou.sdk.d.b E;
    private a F;
    private long G;
    private boolean H = true;
    private Handler I = new Handler(this) {
        final /* synthetic */ TtgTabFragment a;

        {
            this.a = r1;
        }

        public void handleMessage(Message message) {
            this.a.q = 2;
            this.a.b(true);
        }
    };
    private c J = new c(this) {
        final /* synthetic */ TtgTabFragment a;

        {
            this.a = r1;
        }

        public void onInitView(PullToRefreshLayout pullToRefreshLayout) {
            super.onInitView(pullToRefreshLayout);
            if ("1".equals(this.a.C) && pullToRefreshLayout != null && this.a.isAdded()) {
                pullToRefreshLayout.setTopRefreshHintText(this.a.m == null ? this.a.getString(R.string.ttg_flush_title) : this.a.getString(R.string.ttg_flush_rcmm_title));
            }
        }

        public void onRefreshNet(PullToRefreshLayout pullToRefreshLayout) {
            super.onRefreshNet(pullToRefreshLayout);
            if (this.a.t) {
                this.a.t = false;
                this.a.g.refreshFinish(0);
                return;
            }
            if (this.a.u) {
                this.a.u = false;
            } else {
                cn.tatagou.sdk.e.a.b.pullStatEvent(this.a.C);
            }
            this.a.c();
        }
    };
    private cn.tatagou.sdk.view.a K = new cn.tatagou.sdk.view.a(this) {
        final /* synthetic */ TtgTabFragment a;

        {
            this.a = r1;
        }

        public void onScrollList(AbsListView absListView, int i, int i2, boolean z) {
            super.onScrollList(absListView, i, i2, z);
            this.a.a.setVisibility(i > 5 ? 0 : 8);
            if (z && i > 5) {
                this.a.mView.findViewById(R.id.ttg_rl_num).setVisibility(0);
                this.a.f.setVisibility(8);
                int size = this.a.k.size();
                TextView textView = (TextView) this.a.mView.findViewById(R.id.ttg_tv_num);
                if (i > size) {
                    i = size;
                }
                textView.setText(String.valueOf(i));
                ((TextView) this.a.mView.findViewById(R.id.ttg_tv_sum_num)).setText(String.valueOf(size));
            }
        }

        public void onStopScroll(boolean z, int i, int i2) {
            super.onStopScroll(z, i, i2);
            if (i > 5) {
                this.a.f.setVisibility(0);
                this.a.a.setVisibility(0);
                this.a.mView.findViewById(R.id.ttg_rl_num).setVisibility(8);
            }
            AppHomeData.getInstance().addAllSpHistorySet(this.a.j.subList(0, i > this.a.j.size() ? this.a.j.size() : i));
            if (i == i2) {
                this.a.h();
            }
            if (this.a.o < i) {
                this.a.o = i;
            }
        }
    };
    private cn.tatagou.sdk.util.c L = new cn.tatagou.sdk.util.c(this) {
        final /* synthetic */ TtgTabFragment a;

        {
            this.a = r1;
        }

        public void onRcmmSpClickRefresh(View view) {
            super.onRcmmSpClickRefresh(view);
            this.a.B = null;
            this.a.d.setSelection(1);
            if (p.isNetworkOpen(this.a.getActivity())) {
                cn.tatagou.sdk.e.a.b.rrStatEvent(this.a.C, null);
                if (this.a.g != null) {
                    this.a.t = true;
                    this.a.g.setTopRefreshHintText(this.a.getString(R.string.ttg_flush_rcmm_title));
                    this.a.g.autoRefresh();
                }
                if (o.isSexChange()) {
                    HomeData homeData = AppHomeData.getInstance().getHomeData();
                    if (homeData != null) {
                        this.a.onRcmmDataReady(true, homeData.getNormalSpecialList(), homeData.getTimestamp(), this.a.B, "mCallback");
                        return;
                    }
                    return;
                }
                this.a.a(true, true);
                return;
            }
            l.showToastCenter(this.a.getActivity(), this.a.getString(R.string.ttg_net_bad));
        }
    };
    private Runnable M = new Runnable(this) {
        final /* synthetic */ TtgTabFragment a;

        {
            this.a = r1;
        }

        public void run() {
            this.a.I.obtainMessage().sendToTarget();
        }
    };
    private cn.tatagou.sdk.a.a<CommPojo<HomeData>> N = new cn.tatagou.sdk.a.a<CommPojo<HomeData>>(this) {
        final /* synthetic */ TtgTabFragment a;

        {
            this.a = r1;
        }

        public void onApiDataResult(CommPojo<HomeData> commPojo, int i) {
            boolean z = true;
            super.onApiDataResult(commPojo, i);
            if (this.a.isAdded()) {
                Log.d(TtgTabFragment.b, "onApiDataResult: TAb homeApiCallback");
                this.a.H = true;
                this.a.g.refreshFinish(0);
                if (commPojo != null && commPojo.getData() != null) {
                    ((HomeData) commPojo.getData()).setTimestamp(commPojo.getTimestamp());
                    this.a.onHomeApiDataReady((HomeData) commPojo.getData());
                } else if (this.a.isAdded() && i == 304 && AppHomeData.getInstance().getHomeData() != null) {
                    this.a.a(AppHomeData.getInstance().getHomeData());
                } else {
                    this.a.v.setVisibility(0);
                    this.a.x.setVisibility(8);
                    TtgTabFragment ttgTabFragment = this.a;
                    if (commPojo != null) {
                        i = 20002;
                    }
                    if (this.a.h.getCount() <= 0) {
                        z = false;
                    }
                    ttgTabFragment.onDataError(i, z);
                }
            }
        }
    };
    public LinearLayout a;
    private GridView c;
    private PullableListView d;
    private FrameLayout e;
    private TextView f;
    private PullToRefreshLayout g;
    private h h;
    private d i;
    private List<Special> j = new ArrayList();
    private List<Special> k = new ArrayList();
    private List<Special> l = new ArrayList();
    private List<Special> m = new ArrayList();
    private int n;
    private int o;
    private int p = 2;
    private int q = 2;
    private boolean r = false;
    private boolean s = false;
    private boolean t = false;
    private boolean u = false;
    private TextView v;
    private View w;
    private TextView x;
    private String y;
    private int z = -1;

    protected void onViewInVisible() {
        super.onViewInVisible();
        if (this.z == 1 && this.D != null) {
            this.D.stopScroll();
            if (isAdded()) {
                n.saveSpecialData(getActivity());
            }
        }
    }

    protected void onViewVisible() {
        super.onViewVisible();
        if (this.mView != null && isAdded() && this.z == -1) {
            a(this.mView);
            a();
        }
        if (this.z == 1 && this.D != null) {
            this.D.startScroll();
        }
    }

    public static TtgTabFragment newInstance(String str, String str2) {
        Bundle bundle = new Bundle();
        TtgTabFragment ttgTabFragment = new TtgTabFragment();
        bundle.putString("spId", str);
        bundle.putString("cateId", str2);
        ttgTabFragment.setArguments(bundle);
        return ttgTabFragment;
    }

    public View newView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        if (this.mView == null) {
            this.mView = layoutInflater.inflate(R.layout.ttg_tab_fragment, viewGroup, false);
        }
        initLoading();
        showLoading();
        return this.mView;
    }

    public void onActivityCreated(@Nullable Bundle bundle) {
        super.onActivityCreated(bundle);
        if (this.mView != null && isAdded() && this.z == -1 && this.isVisible) {
            a(this.mView);
            a();
        }
    }

    private void a(View view) {
        super.initView(view);
        this.z = 0;
        this.B = getArguments().getString("spId");
        this.C = getArguments().getString("cateId");
        this.y = "AppCatSpecials".concat(String.valueOf(this.C));
        this.d = (PullableListView) view.findViewById(R.id.ttg_lv_special_list);
        View inflate = LayoutInflater.from(getActivity()).inflate(R.layout.ttg_main_bottom, null);
        this.v = (TextView) inflate.findViewById(R.id.tv_bottom_title);
        this.x = (TextView) inflate.findViewById(R.id.ttg_loadstate_tv);
        this.w = LayoutInflater.from(getActivity()).inflate(R.layout.ttg_top_main, null);
        this.c = (GridView) this.w.findViewById(R.id.gv_columns);
        this.e = (FrameLayout) this.w.findViewById(R.id.fy_scroll);
        this.g = (PullToRefreshLayout) view.findViewById(R.id.ttg_refresh_view);
        this.a = (LinearLayout) view.findViewById(R.id.ttg_ly_icon);
        this.f = (TextView) view.findViewById(R.id.ttg_icon_back_top);
        this.D = new TtgScrollView(getActivity());
        this.e.addView(this.D);
        this.d.addHeaderView(this.w);
        this.d.addFooterView(inflate);
        this.g.setOnRefreshListener(this.J);
        this.d.setCanPullUp(false);
        this.f.setOnClickListener(this);
        this.d.setOnScrollListener(this.K);
        if (this.h == null) {
            this.h = new h(getActivity(), null, this.C, this);
            this.d.setAdapter(this.h);
        }
        cn.tatagou.sdk.view.c.setWnlBottomHeight(getActivity(), this.a, this.v);
    }

    private void c() {
        boolean z;
        long currentTimeMillis = System.currentTimeMillis() - this.G;
        if (this.G == 0 || currentTimeMillis >= 5000) {
            z = false;
        } else {
            z = true;
        }
        this.G = System.currentTimeMillis();
        if ("1".equals(this.C) && r0) {
            z = o.isSexChange();
            if (this.m != null && !z) {
                a(false, true);
                this.g.refreshFinish(0);
                return;
            } else if (z && AppHomeData.getInstance().getHomeData() != null) {
                HomeData homeData = AppHomeData.getInstance().getHomeData();
                onRcmmDataReady(false, homeData.getNormalSpecialList(), homeData.getTimestamp(), this.B, "onPullToRefreshData");
                this.g.refreshFinish(0);
                return;
            }
        }
        Log.d(b, "onPullToRefreshData: 请求API");
        this.B = null;
        this.q = 2;
        b(true);
    }

    public void onSpecialTop(String str) {
        this.B = str;
        if (this.j != null && this.j.size() > 0 && this.k.size() > 0 && !p.isEmpty(this.B)) {
            boolean onFindSpecialTopData = n.onFindSpecialTopData(this.k, this.B);
            this.j.clear();
            Collection arrayList = new ArrayList();
            this.n = this.n > this.k.size() ? this.k.size() : this.n;
            arrayList.addAll(this.k.subList(0, this.n));
            if (this.l != null && this.l.size() > 0) {
                int indexOf = arrayList.indexOf(new Special(str));
                if (indexOf >= 0) {
                    Special special = (Special) this.k.get(indexOf);
                    if (this.l.size() >= 11) {
                        this.l.set(0, special);
                    } else {
                        this.l.add(0, special);
                    }
                }
                this.j.addAll(0, this.l);
                Log.d(b, "onSpecialTop mRcmmSpecialList: " + this.l.size());
                arrayList.removeAll(this.l);
                if (this.h != null) {
                    this.h.setRcmmPosition(this.l.size());
                    n.setSpHintText(this.h, "1".equals(this.C), this.l.size());
                }
            }
            this.j.addAll(arrayList);
            if (onFindSpecialTopData && this.h != null) {
                this.h.setItems(this.j);
                setListTop(1);
            } else if (this.p == 1) {
                g();
            }
        }
    }

    public void setListTop(int i) {
        if (this.d != null) {
            this.d.setSelection(i);
        }
    }

    protected void a() {
        d();
        f();
        e();
    }

    private void d() {
        if ("1".equals(this.C)) {
            UpdateView updateView = new UpdateView("changeSex", new IUpdateView<Boolean>(this) {
                final /* synthetic */ TtgTabFragment a;

                {
                    this.a = r1;
                }

                public void updateView(Boolean bool) {
                    if (bool.booleanValue() && o.isSexChange() && AppHomeData.getInstance().getHomeData() != null) {
                        if (this.a.d != null) {
                            this.a.d.setSelection(0);
                        }
                        this.a.onRcmmDataReady(false, AppHomeData.getInstance().getHomeData().getNormalSpecialList(), AppHomeData.getInstance().getHomeData().getTimestamp(), this.a.B, "registerSaveInfoListener");
                    }
                }
            });
            IUpdateViewManager.getInstance().registIUpdateView(updateView);
            addUpdateViewList(updateView);
        }
    }

    public void onRcmmDataReady(final boolean z, final List<Special> list, String str, String str2, String str3) {
        if (list != null) {
            this.B = str2;
            n.setSpHintText(this.h, "1".equals(this.C), 0);
            this.m = null;
            this.l.clear();
            List linkedList = new LinkedList();
            linkedList.addAll(list);
            RcmmParam rcmmParam = AppHomeData.getInstance().getRcmmParam();
            if (rcmmParam == null || rcmmParam.getRcmmSpecials() == null) {
                d((List) list);
                return;
            }
            this.F = new a(getActivity(), linkedList, new cn.tatagou.sdk.util.c(this) {
                final /* synthetic */ TtgTabFragment c;

                public void onRcmmSpTaskExecuteResult(boolean z) {
                    super.onRcmmSpTaskExecuteResult(z);
                    if (z) {
                        this.c.m = o.onRcmmSpDataReady(this.c.getActivity());
                        if (this.c.m != null) {
                            this.c.a(z, false);
                        } else {
                            this.c.d(list);
                        }
                    } else if (this.c.p == 1) {
                        this.c.g();
                    } else {
                        this.c.d(list);
                    }
                    AppHomeData.getInstance().setSex(Config.getInstance().getSex());
                }
            });
            this.F.execute(new String[]{str});
        }
    }

    private void a(boolean z, boolean z2) {
        if (isAdded()) {
            this.h.setClickCallBack(this.L);
            this.m = z2 ? o.onRcmmSpDataReady(getActivity()) : this.m;
            if (this.m != null) {
                n.setSpHintText(this.h, "1".equals(this.C), this.m.size());
                this.h.setRcmmFinshText(n.getRmFresh(getString(R.string.ttg_icon_refresh), getString(R.string.ttg_icon_rm_refresh)), true);
                this.t = z;
                if (!this.s) {
                    this.n = 0;
                    this.r = true;
                }
                this.l.clear();
                this.l.addAll(this.m);
                d(AppHomeData.getInstance().getHomeData().getNormalSpecialList());
            } else if (this.p != 1) {
                a(true);
            } else if (!z) {
                this.t = false;
                g();
            } else if (this.g != null) {
                this.g.autoRefresh();
            }
        }
    }

    public void autoRefresh(boolean z) {
        if (this.g != null && !z) {
            cn.tatagou.sdk.e.a.b.rrStatEvent(this.C, "Tab");
            this.u = true;
            this.g.autoRefresh();
            if (this.d != null) {
                this.d.setSelection(1);
            }
        }
    }

    private void a(boolean z) {
        if (this.g != null) {
            this.g.setTopRefreshHintText(getString(R.string.ttg_flush_rcmm_title));
        }
        n.setSpHintText(this.h, "1".equals(this.C), 0);
        this.h.setRcmmFinshText(n.getRmEnd(getString(R.string.ttg_rcmm_finish_text)), false);
        if (z) {
            this.h.notifyDataSetChanged();
        }
    }

    private void e() {
        HomeData homeData = AppHomeData.getInstance().getHomeData();
        if ("1".equals(this.C)) {
            if (homeData != null) {
                onHomeDataReady(homeData);
            } else {
                b(false);
            }
        } else if (homeData == null || homeData.getCurrPage() != 2) {
            this.q = 2;
            b(false);
        } else {
            onHomeDataReady(homeData);
        }
    }

    private void f() {
        Map map = (Map) JSON.parseObject(cn.tatagou.sdk.b.a.getStr("sysConfigInfo"), new TypeReference<Map<String, String>>(this) {
            final /* synthetic */ TtgTabFragment a;

            {
                this.a = r1;
            }
        }, new Feature[0]);
        if (map == null || map.size() <= 0) {
            cn.tatagou.sdk.util.b.getSysCfg(new cn.tatagou.sdk.util.c(this) {
                final /* synthetic */ TtgTabFragment a;

                {
                    this.a = r1;
                }

                public void setSysCfg(Map<String, String> map) {
                    n.onSysCfgShow(this.a.C, this.a.v);
                    n.setSpHintText(this.a.h, "1".equals(this.a.C), 0);
                }
            });
            return;
        }
        n.onSysCfgShow(this.C, this.v);
        n.setSpHintText(this.h, "1".equals(this.C), 0);
    }

    private void a(List<Special> list) {
        this.c.setVisibility(0);
        this.c.setNumColumns(n.getNumColumns(list));
        if (this.i == null) {
            this.i = new d(getActivity(), list, this.C, this);
            this.c.setAdapter(this.i);
            return;
        }
        this.i.setItems(list);
    }

    private void b(boolean z) {
        if (this.H) {
            this.H = false;
            this.r = z;
            k.closeRunnable(this.I, this.M);
            this.A = ((cn.tatagou.sdk.a.a.a) e.getInstance().getService(cn.tatagou.sdk.a.a.a.class)).home(this.q, "1");
            cn.tatagou.sdk.a.b.onCommRequestApi(this.N, this.A, new TypeReference<CommPojo<HomeData>>(this) {
                final /* synthetic */ TtgTabFragment a;

                {
                    this.a = r1;
                }
            }.getType());
        }
    }

    private void a(String str) {
        this.z = 1;
        long onRefreshTimeReady = n.onRefreshTimeReady(p.str2Long(str));
        if (onRefreshTimeReady > 0 && this.I != null) {
            this.I.postDelayed(this.M, onRefreshTimeReady);
        }
    }

    public synchronized void onHomeApiDataReady(HomeData homeData) {
        homeData.setCurrPage(this.q);
        AppHomeData.getInstance().setHomeData(homeData);
        onHomeDataReady(homeData);
    }

    private void b(List<Special> list) {
        if (!isAdded() || list == null || list.size() <= 0) {
            this.e.setVisibility(8);
            return;
        }
        this.e.setVisibility(0);
        this.D.onBannerSpecialShow(getActivity(), this.C, list);
    }

    private void c(List<Special> list) {
        if (!isAdded() || list == null || list.size() < 4) {
            this.c.setVisibility(8);
        } else {
            a((List) list);
        }
    }

    private void d(List<Special> list) {
        if (!isAdded() || list == null || list.size() <= 0) {
            hideLoading();
            this.v.setVisibility(0);
            this.x.setVisibility(8);
            return;
        }
        this.E = new cn.tatagou.sdk.d.b(list, this.k, this.l, new cn.tatagou.sdk.util.c(this) {
            final /* synthetic */ TtgTabFragment a;

            {
                this.a = r1;
            }

            public void onNormalSpTaskExecutResult(boolean z) {
                if (this.a.isAdded()) {
                    this.a.hideLoading();
                    if (this.a.r) {
                        this.a.j.clear();
                        this.a.n = 0;
                        if (this.a.l != null && this.a.l.size() > 0 && "1".equals(this.a.C)) {
                            this.a.j.addAll(0, this.a.l);
                        }
                    }
                    if (p.isEmpty(this.a.B) || this.a.p != 1 || z) {
                        this.a.h();
                    } else {
                        this.a.g();
                    }
                }
            }
        });
        this.E.execute(new String[]{this.C, this.B});
    }

    private synchronized void g() {
        showLoading();
        this.q = 2;
        b(true);
    }

    public void onHomeDataReady(HomeData homeData) {
        a(homeData.getTimestamp());
        this.p = homeData.getCurrPage();
        if ("1".equals(this.C)) {
            a(homeData.getBannerSpecialList(), homeData.getCateSpecialList());
            a(homeData);
            b(homeData.getBannerSpecialList());
            c(homeData.getCateSpecialList());
            return;
        }
        d(homeData.getNormalSpecialList());
    }

    private void a(List<Special> list, List<Special> list2) {
        int i = 0;
        if (this.w != null) {
            int i2;
            TextView textView = (TextView) this.w.findViewById(R.id.ttg_tv_space);
            if ((list == null || list.size() <= 0) && (list2 == null || list2.size() <= 0)) {
                i2 = 0;
            } else {
                i2 = 1;
            }
            if (i2 == 0) {
                i = 8;
            }
            textView.setVisibility(i);
        }
    }

    private void a(HomeData homeData) {
        int browserCateSpSize = AppHomeData.getInstance().getBrowserCateSpSize() + AppHomeData.getInstance().getUnBrowserCateSpSize();
        if (o.isSexChange() || browserCateSpSize <= 0) {
            onRcmmDataReady(false, homeData.getNormalSpecialList(), homeData.getTimestamp(), this.B, "onUpdateRcmmSpDataReady");
            return;
        }
        this.m = o.onRcmmSpDataReady(getActivity());
        if (this.m != null) {
            a(false, false);
            return;
        }
        a(false);
        d(homeData.getNormalSpecialList());
    }

    private synchronized void h() {
        if (this.k != null) {
            if (this.n < this.k.size()) {
                i();
                if (this.l.size() >= 0 && "1".equals(this.C)) {
                    this.h.setRcmmPosition(this.l.size());
                    n.setSpHintText(this.h, true, this.l.size());
                }
                if (this.n == j.a && this.B != null) {
                    this.d.setSelection(1);
                }
            } else if (this.p == 1) {
                this.q = 2;
                this.s = true;
                b(false);
            } else {
                this.v.setVisibility(0);
                this.x.setVisibility(8);
            }
        }
    }

    private synchronized void i() {
        int i = 0;
        synchronized (this) {
            this.s = false;
            int size = this.k.size();
            int i2 = this.n + j.a;
            if (i2 <= size) {
                size = i2;
            }
            Collection arrayList = new ArrayList();
            arrayList.addAll(this.k.subList(this.n, size));
            this.n += arrayList.size();
            if (this.l != null && this.l.size() > 0) {
                arrayList.removeAll(this.l);
            }
            this.j.addAll(arrayList);
            this.h.setItems(this.j);
            if (this.x != null) {
                TextView textView = this.x;
                if (this.n < this.k.size() || this.p == 1) {
                    size = 0;
                } else {
                    size = 8;
                }
                textView.setVisibility(size);
            }
            if (this.v != null) {
                TextView textView2 = this.v;
                if (!(this.k.size() == this.n && this.p == 2)) {
                    i = 8;
                }
                textView2.setVisibility(i);
            }
        }
    }

    public void onPause() {
        if (this.D != null) {
            this.D.stopScroll();
        }
        if (isAdded()) {
            n.saveSpecialData(getActivity());
        }
        super.onPause();
    }

    public void onResume() {
        super.onResume();
        if (this.z == 1 && this.D != null) {
            this.D.startScroll();
        }
    }

    public void onClick(View view) {
        super.onClick(view);
        if (view.getId() == R.id.ttg_icon_back_top) {
            this.d.setSelection(0);
        }
    }

    public void onRetryClick() {
        super.onRetryClick();
        b(true);
    }

    public void onDestroyView() {
        super.onDestroyView();
        if (this.mView != null) {
            unbindDrawables(this.mView);
        }
        if (this.A != null) {
            this.A.b();
        }
        if (this.mView != null) {
            ((ViewGroup) this.mView.getParent()).removeView(this.mView);
        }
    }

    public void onDestroy() {
        this.y = null;
        if (this.F != null) {
            this.F.cancel(true);
        }
        if (this.E != null) {
            this.E.cancel(true);
        }
        k.closeRunnable(this.I, this.M);
        if ("1".equals(this.C)) {
            cn.tatagou.sdk.e.a.b.homeNumStatEvent(this.C);
        }
        if (isAdded()) {
            n.saveSpecialData(getActivity());
        }
        super.onDestroy();
    }
}
