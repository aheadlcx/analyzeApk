package cn.tatagou.sdk.fragment;

import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.RelativeLayout.LayoutParams;
import android.widget.TextView;
import cn.tatagou.sdk.R;
import cn.tatagou.sdk.R$drawable;
import cn.tatagou.sdk.a.a;
import cn.tatagou.sdk.a.c;
import cn.tatagou.sdk.a.e;
import cn.tatagou.sdk.adapter.f;
import cn.tatagou.sdk.adapter.g;
import cn.tatagou.sdk.android.TtgConfig;
import cn.tatagou.sdk.b.d;
import cn.tatagou.sdk.pojo.CommListPojo;
import cn.tatagou.sdk.pojo.HotSearch;
import cn.tatagou.sdk.pojo.Item;
import cn.tatagou.sdk.pojo.TitleBar;
import cn.tatagou.sdk.pojo.TtgTitleBar;
import cn.tatagou.sdk.util.h;
import cn.tatagou.sdk.util.j;
import cn.tatagou.sdk.util.l;
import cn.tatagou.sdk.util.p;
import cn.tatagou.sdk.view.IUpdateViewManager;
import cn.tatagou.sdk.view.IconTextView;
import cn.tatagou.sdk.view.MyListView;
import cn.tatagou.sdk.view.TagLayout;
import cn.tatagou.sdk.view.UpdateView;
import cn.tatagou.sdk.view.pullview.AutoPullAbleListView;
import cn.tatagou.sdk.view.pullview.AutoPullAbleListView$a;
import cn.tatagou.sdk.view.pullview.AutoPullToRefreshLayout;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import okhttp3.ab;
import org.apache.commons.httpclient.cookie.CookiePolicy;
import retrofit2.b;

public class TtgSearchGoodsFragment extends BaseFragment {
    private static final String d = TtgSearchGoodsFragment.class.getSimpleName();
    private b<ab> A;
    private boolean B = false;
    private TextView C;
    private IconTextView D;
    private int E = 1;
    private b<ab> F;
    private String G = CookiePolicy.DEFAULT;
    private View H;
    private boolean I = true;
    private boolean J = false;
    private boolean K = false;
    private a<CommListPojo<Item>> L = new TtgSearchGoodsFragment$1(this);
    private a<CommListPojo<HotSearch>> M = new TtgSearchGoodsFragment$11(this);
    private AutoPullAbleListView$a N = new TtgSearchGoodsFragment$13(this);
    public LinearLayout a;
    public List<Item> b;
    cn.tatagou.sdk.view.pullview.a c = new TtgSearchGoodsFragment$3(this);
    private LinearLayout e;
    private IconTextView f;
    private TagLayout g;
    private MyListView h;
    private f i;
    private g j;
    private int k;
    private List<String> l;
    private LinearLayout m;
    private LinearLayout n;
    private LinearLayout o;
    private IconTextView p;
    private TextView q;
    private TextView r;
    private IconTextView s;
    private LinearLayout t;
    private IconTextView u;
    private IconTextView v;
    private AutoPullAbleListView w;
    private AutoPullToRefreshLayout x;
    private TextView y;
    private RelativeLayout z;

    public static TtgSearchGoodsFragment newInstance() {
        Bundle bundle = new Bundle();
        TtgSearchGoodsFragment ttgSearchGoodsFragment = new TtgSearchGoodsFragment();
        ttgSearchGoodsFragment.setArguments(bundle);
        return ttgSearchGoodsFragment;
    }

    public void onCreate(@Nullable Bundle bundle) {
        super.onCreate(bundle);
        this.b = new ArrayList();
        this.l = new ArrayList();
        this.j = new g(getActivity(), this.l);
        this.i = new f(getActivity(), this.b, this);
    }

    protected View newView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        if (this.mView == null) {
            this.mView = layoutInflater.inflate(R.layout.ttg_searchgoods_fragment, viewGroup, false);
        }
        return this.mView;
    }

    public void onRetryClick() {
        super.onRetryClick();
        a(this.G, this.E);
    }

    protected void initView(View view) {
        super.initView(view);
        initFailHintLayout();
        initLoading();
        a(view);
        this.mEdtSearch.setFocusableInTouchMode(true);
        this.H = this.mView.findViewById(R.id.ttg_fenge_line);
        this.y = (TextView) this.mView.findViewById(R.id.ttg_clear_history);
        this.x = (AutoPullToRefreshLayout) this.mView.findViewById(R.id.auto_pull_footprint);
        this.a = (LinearLayout) view.findViewById(R.id.ttg_ly_icon);
        this.w = (AutoPullAbleListView) this.mView.findViewById(R.id.lv_footprint);
        this.C = (TextView) view.findViewById(R.id.ttg_icon_back_top);
        this.r = (TextView) view.findViewById(R.id.ttg_tv_num);
        this.g = (TagLayout) this.mView.findViewById(R.id.ttg_cats_www);
        this.q = (TextView) view.findViewById(R.id.ttg_tv_sum_num);
        this.h = (MyListView) this.mView.findViewById(R.id.ttg_listview_historyresult);
        this.m = (LinearLayout) this.mView.findViewById(R.id.ttg_search_title);
        this.f = (IconTextView) this.mView.findViewById(R.id.ttg_tv_clear);
        this.e = (LinearLayout) this.mView.findViewById(R.id.ttg_ll_emptytishi);
        this.n = (LinearLayout) this.mView.findViewById(R.id.ttg_ly_cats_www);
        this.o = (LinearLayout) this.mView.findViewById(R.id.ttg_lrl_getseaechcontent);
        this.p = (IconTextView) this.mView.findViewById(R.id.ttg_tv_default);
        this.s = (IconTextView) this.mView.findViewById(R.id.ttg_tv_salenumber);
        this.u = (IconTextView) this.mView.findViewById(R.id.ttg_tv_top);
        this.v = (IconTextView) this.mView.findViewById(R.id.ttg_tv_down);
        this.t = (LinearLayout) this.mView.findViewById(R.id.ttg_linear_price);
        this.D = (IconTextView) this.mView.findViewById(R.id.ttg_tv_price);
        this.z = (RelativeLayout) view.findViewById(R.id.ttg_rl_num);
        this.p.setOnClickListener(this);
        this.s.setOnClickListener(this);
        this.t.setOnClickListener(this);
        this.f.setOnClickListener(this);
        this.y.setOnClickListener(this);
        this.C.setOnClickListener(this);
        this.mEdtSearch.addTextChangedListener(new TtgSearchGoodsFragment$6(this));
        this.h.setOnItemClickListener(new TtgSearchGoodsFragment$7(this));
        this.w.setAdapter(this.i);
        this.w.setOnLoadListener(this.N);
        this.w.setOnLoadScrollListener(this.c);
        this.w.setPullDownFlag(false);
        this.mEdtSearch.setOnEditorActionListener(new TtgSearchGoodsFragment$8(this));
        d();
        c();
    }

    private void c() {
        UpdateView updateView = new UpdateView("searchHideSoftInput", new TtgSearchGoodsFragment$9(this));
        IUpdateViewManager.getInstance().registIUpdateView(updateView);
        addUpdateViewList(updateView);
    }

    private void d() {
        c.getInstance().clear();
        this.F = ((cn.tatagou.sdk.a.a.a) e.getInstance().getService(cn.tatagou.sdk.a.a.a.class)).getHotSearch();
        cn.tatagou.sdk.a.b.onCommRequestApi(this.M, this.F, new TtgSearchGoodsFragment$10(this).getType());
    }

    private void a(CommListPojo<HotSearch> commListPojo) {
        if (commListPojo.getData() == null || commListPojo.getData().size() <= 0) {
            this.J = false;
            return;
        }
        a(commListPojo.getData());
        this.J = true;
    }

    private void a(View view) {
        TtgTitleBar instance = TtgTitleBar.getInstance();
        TitleBar titleBar = new TitleBar();
        titleBar.setLeftIconShow(true);
        titleBar.setSearchShown(true);
        titleBar.setRightIconShow(true);
        titleBar.setTvRightIconfontCode("搜索");
        titleBar.setTvRightIconSize(16);
        titleBar.setBackIconRightPadding(instance.getBackIconSearchRightPadding());
        this.mTtgRlSearch = (RelativeLayout) view.findViewById(R.id.ttg_rl_search);
        ((LayoutParams) this.mTtgRlSearch.getLayoutParams()).rightMargin = p.dip2px(getActivity(), 10.0f);
        setBarTitle(view, titleBar);
    }

    private void a(String str, boolean z) {
        this.w.setFinishText(str);
        this.w.finishLoading(2);
        this.w.setLoadDataFlag(z);
    }

    private synchronized void a(List<Item> list, String str) {
        if (str.equals("304") || "200".equals(str)) {
            if (list != null && list.size() > 0) {
                if (this.E == 1) {
                    this.b.clear();
                }
                if (j.a == list.size()) {
                    this.w.finishLoading(0);
                } else {
                    a(getString(R.string.ttg_icon_pull_data), false);
                }
                this.n.setVisibility(8);
                this.e.setVisibility(8);
                this.o.setVisibility(0);
                this.b.addAll(list);
                this.i.notifyDataSetChanged();
                if (this.E == 1) {
                    this.w.postDelayed(new TtgSearchGoodsFragment$12(this), 200);
                }
                this.E++;
            } else if (this.E == 1) {
                this.o.setVisibility(8);
                this.n.setVisibility(0);
                this.e.setVisibility(0);
            } else {
                a(getString(R.string.ttg_icon_pull_data), false);
            }
        }
    }

    private void a(String str, int i) {
        if (this.I) {
            this.I = false;
            c.getInstance().clear();
            this.A = ((cn.tatagou.sdk.a.a.a) e.getInstance().getService(cn.tatagou.sdk.a.a.a.class)).getSearch(this.mEdtSearch.getText().toString(), str, i, j.a);
            cn.tatagou.sdk.a.b.onCommRequestApi(this.L, this.A, new TtgSearchGoodsFragment$2(this).getType());
        }
    }

    public void onClick(View view) {
        super.onClick(view);
        if (view.getId() == R.id.ttg_tv_clear) {
            this.mEdtSearch.setText("");
            this.f.setVisibility(4);
            this.e.setVisibility(8);
            this.o.setVisibility(8);
            this.g.setVisibility(0);
            h.openKeybord(this.mEdtSearch, getActivity());
            a();
        } else if (view.getId() == R.id.ttg_tv_default) {
            if (!this.G.equals(CookiePolicy.DEFAULT)) {
                this.B = false;
                this.w.setLoadDataFlag(true);
                selectTab(this.p, this.s, this.D);
                this.u.setTextColor(Color.parseColor("#666666"));
                this.v.setTextColor(Color.parseColor("#666666"));
                this.G = CookiePolicy.DEFAULT;
                this.E = 1;
                a(this.G, this.E);
            }
        } else if (view.getId() == R.id.ttg_tv_salenumber) {
            if (!this.G.equals("sale-desc")) {
                this.B = false;
                this.w.setLoadDataFlag(true);
                selectTab(this.s, this.p, this.D);
                this.u.setTextColor(Color.parseColor("#666666"));
                this.v.setTextColor(Color.parseColor("#666666"));
                this.G = "sale-desc";
                this.E = 1;
                a(this.G, this.E);
            }
        } else if (view.getId() == R.id.ttg_linear_price) {
            if (this.I) {
                this.w.setLoadDataFlag(true);
                selectTab(this.D, this.s, this.p);
                if (this.B) {
                    selectPriceOrderBy(this.v, this.u);
                    this.B = false;
                    this.G = "price-desc";
                    this.E = 1;
                } else {
                    selectPriceOrderBy(this.u, this.v);
                    this.B = true;
                    this.G = "price-asc";
                    this.E = 1;
                }
                a(this.G, this.E);
            }
        } else if (view.getId() == R.id.ttg_clear_history) {
            d.getInstance().deleteAllSearchHistory(getActivity());
            this.y.setVisibility(8);
            this.m.setVisibility(8);
            this.l.clear();
            this.j.notifyDataSetChanged();
        } else if (view.getId() == R.id.ttg_icon_back_top) {
            this.w.setSelection(0);
        }
    }

    protected void onTitleBarRightIconClick() {
        super.onTitleBarRightIconClick();
        hideSoftInput();
        if (TextUtils.isEmpty(this.mEdtSearch.getText().toString().trim())) {
            l.showToastCenter(getActivity(), "请输入搜索内容");
            return;
        }
        cn.tatagou.sdk.e.a.b.searchStatEvent(this.mEdtSearch.getText().toString().trim(), "M");
        e();
        this.w.setLoadDataFlag(true);
        this.E = 1;
        this.G = CookiePolicy.DEFAULT;
        selectTab(this.p, this.s, this.D);
        this.u.setTextColor(Color.parseColor("#666666"));
        this.v.setTextColor(Color.parseColor("#666666"));
        hideLoading();
        this.w.finishLoading(0);
        a(this.G, this.E);
    }

    public void onSearchGoods() {
        hideSoftInput();
        if (TextUtils.isEmpty(this.mEdtSearch.getText().toString().trim())) {
            l.showToastCenter(getActivity(), "请输入搜索内容!");
            return;
        }
        e();
        this.w.setLoadDataFlag(true);
        this.E = 1;
        this.G = CookiePolicy.DEFAULT;
        selectTab(this.p, this.s, this.D);
        this.u.setTextColor(Color.parseColor("#666666"));
        this.v.setTextColor(Color.parseColor("#666666"));
        hideLoading();
        this.w.finishLoading(0);
        a(this.G, this.E);
    }

    public void hideSoftInput() {
        if (isAdded() && this.mEdtSearch != null) {
            ((InputMethodManager) getActivity().getSystemService("input_method")).hideSoftInputFromWindow(this.mEdtSearch.getWindowToken(), 0);
        }
    }

    private void e() {
        if (!TextUtils.isEmpty(this.mEdtSearch.getText().toString().trim())) {
            d.getInstance().insertSearchPh(getActivity(), this.mEdtSearch.getText().toString());
        }
    }

    protected void initData() {
        super.initData();
    }

    void a() {
        Collection queryTop10 = d.getInstance().queryTop10(getActivity());
        if (queryTop10 == null || queryTop10.size() <= 0) {
            this.K = false;
        } else {
            this.K = true;
            this.m.setVisibility(0);
            this.l.clear();
            this.l.addAll(queryTop10);
            this.j.notifyDataSetChanged();
            this.y.setVisibility(0);
        }
        if (this.J && this.J) {
            this.H.setVisibility(0);
        } else {
            this.H.setVisibility(8);
        }
    }

    private void a(List<HotSearch> list) {
        this.g.removeAllViews();
        View textView = new TextView(getActivity());
        textView.setText("热搜");
        textView.setTextSize(16.0f);
        textView.setTextColor(Color.parseColor("#0A0A0A"));
        ViewGroup.LayoutParams layoutParams = new LinearLayout.LayoutParams(-2, -2);
        layoutParams.setMargins(p.dip2px(getActivity(), 5.0f), p.dip2px(getActivity(), 8.0f), p.dip2px(getActivity(), 5.0f), p.dip2px(getActivity(), 8.0f));
        textView.setLayoutParams(layoutParams);
        this.g.addView(textView);
        for (int i = 0; i < list.size(); i++) {
            HotSearch hotSearch = (HotSearch) list.get(i);
            View textView2 = new TextView(getActivity());
            if (hotSearch.getStyle().equals("ad")) {
                textView2.setTextSize(14.0f);
                textView2.setText(hotSearch.getKeyword());
                textView2.setPadding(p.dip2px(getActivity(), 8.0f), p.dip2px(getActivity(), 2.0f), p.dip2px(getActivity(), 8.0f), p.dip2px(getActivity(), 2.0f));
                textView2.setLayoutParams(layoutParams);
                textView2.setTextColor(TtgConfig.getInstance().getThemeColor());
                Drawable gradientDrawable = new GradientDrawable();
                gradientDrawable.setColor(Color.parseColor("#ffffff"));
                gradientDrawable.setCornerRadius((float) p.dip2px(getActivity(), 12.0f));
                gradientDrawable.setStroke(2, TtgConfig.getInstance().getThemeColor());
                textView2.setBackgroundDrawable(gradientDrawable);
                textView2.setOnClickListener(new TtgSearchGoodsFragment$4(this, hotSearch));
            }
            if (hotSearch.getStyle().equals("word")) {
                textView2.setText(hotSearch.getKeyword());
                textView2.setTextSize(14.0f);
                textView2.setSelected(false);
                textView2.setTextColor(Color.parseColor("#b0000000"));
                ViewGroup.LayoutParams layoutParams2 = new LinearLayout.LayoutParams(-2, -2);
                layoutParams2.setMargins(p.dip2px(getActivity(), 5.0f), p.dip2px(getActivity(), 8.0f), p.dip2px(getActivity(), 5.0f), p.dip2px(getActivity(), 8.0f));
                textView2.setLayoutParams(layoutParams2);
                textView2.setBackgroundResource(R$drawable.sel_shape_searchcats);
                textView2.setOnClickListener(new TtgSearchGoodsFragment$5(this));
            }
            this.g.addView(textView2);
        }
    }

    public void selectTab(IconTextView iconTextView, IconTextView iconTextView2, IconTextView iconTextView3) {
        iconTextView.setTextColor(TtgConfig.getInstance().getThemeColor());
        iconTextView2.setTextColor(Color.parseColor("#202020"));
        iconTextView3.setTextColor(Color.parseColor("#202020"));
    }

    public void onPause() {
        super.onPause();
        hideSoftInput();
    }

    public void selectPriceOrderBy(IconTextView iconTextView, IconTextView iconTextView2) {
        iconTextView.setTextColor(TtgConfig.getInstance().getThemeColor());
        iconTextView2.setTextColor(Color.parseColor("#666666"));
    }

    public void onDestroy() {
        if (this.A != null) {
            this.A.b();
        }
        super.onDestroy();
    }
}
