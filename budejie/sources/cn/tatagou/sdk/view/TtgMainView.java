package cn.tatagou.sdk.view;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.net.Uri;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup.LayoutParams;
import android.view.animation.LinearInterpolator;
import android.view.animation.RotateAnimation;
import android.widget.AbsListView;
import android.widget.FrameLayout;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import cn.tatagou.sdk.R;
import cn.tatagou.sdk.R$drawable;
import cn.tatagou.sdk.a.a;
import cn.tatagou.sdk.a.e;
import cn.tatagou.sdk.adapter.d;
import cn.tatagou.sdk.adapter.h;
import cn.tatagou.sdk.android.TtgCallback;
import cn.tatagou.sdk.android.TtgConfig;
import cn.tatagou.sdk.android.TtgConfigKey;
import cn.tatagou.sdk.android.TtgInterface;
import cn.tatagou.sdk.android.TtgSDK;
import cn.tatagou.sdk.pojo.AppCate;
import cn.tatagou.sdk.pojo.CommPojo;
import cn.tatagou.sdk.pojo.HomeData;
import cn.tatagou.sdk.pojo.Special;
import cn.tatagou.sdk.pojo.TtgUrl;
import cn.tatagou.sdk.util.j;
import cn.tatagou.sdk.util.k;
import cn.tatagou.sdk.util.n;
import cn.tatagou.sdk.util.p;
import cn.tatagou.sdk.util.q;
import cn.tatagou.sdk.view.pullview.PullToRefreshLayout;
import cn.tatagou.sdk.view.pullview.PullableListView;
import cn.tatagou.sdk.view.pullview.c;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import com.alibaba.fastjson.parser.Feature;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import okhttp3.ab;
import retrofit2.b;

public class TtgMainView extends LinearLayout implements OnClickListener {
    private static final String COLOR = "#FF969696";
    private static final String DMTTG = "DMTTG_APPCATSPECIALS";
    private static final String TAG = TtgMainView.class.getSimpleName();
    private static TtgUrl mTtgUrl;
    private a<CommPojo<HomeData>> homeApiCallback = new a<CommPojo<HomeData>>(this) {
        final /* synthetic */ TtgMainView a;

        {
            this.a = r1;
        }

        public void onApiDataResult(CommPojo<HomeData> commPojo, int i) {
            super.onApiDataResult(commPojo, i);
            Log.d(TtgMainView.TAG, "onApiDataResult: homeApiCallback");
            if (this.a.mTtgCallback != null) {
                this.a.mTtgCallback.refreshFinish();
            }
            this.a.mPullLayout.refreshFinish(0);
            if (commPojo == null || commPojo.getData() == null) {
                this.a.hideLoading();
                this.a.initFailHintLayout();
                q.showErrorHint(this.a.mActivity, this.a.mTtgLyFailLayout, this.a.mTtgTvFirstTitle, this.a.mTtgTvSecondTitle, i, n.isShowFailLayout(this.a.mHomeData));
                return;
            }
            this.a.mHomeData = (HomeData) commPojo.getData();
            this.a.setMainInfo(this.a.mHomeData);
            this.a.setAutoRefreshAPI(p.str2Long(commPojo.getTimestamp()));
            this.a.onCateDataReady(this.a.mHomeData.getSpecialCats());
        }
    };
    a listViewScrollListener = new a(this) {
        final /* synthetic */ TtgMainView a;

        {
            this.a = r1;
        }

        public void onScrollList(AbsListView absListView, int i, int i2, boolean z) {
            super.onScrollList(absListView, i, i2, z);
            this.a.mLyIcon.setVisibility(i > 5 ? 0 : 8);
            if (z && i > 5) {
                this.a.mRlNum.setVisibility(0);
                this.a.mIconBackTop.setVisibility(8);
                TextView access$600 = this.a.mTvNum;
                if (i > this.a.mTotalNum) {
                    i = this.a.mTotalNum;
                }
                access$600.setText(String.valueOf(i));
                this.a.mTvSumNum.setText(String.valueOf(this.a.mTotalNum));
            }
        }

        public void onStopScroll(boolean z, int i, int i2) {
            super.onStopScroll(z, i, i2);
            if (i > 5) {
                this.a.mIconBackTop.setVisibility(0);
                this.a.mLyIcon.setVisibility(0);
                this.a.mRlNum.setVisibility(8);
            }
            if (i == i2) {
                this.a.pagingSpecialList();
            }
        }
    };
    private c listener = new c(this) {
        final /* synthetic */ TtgMainView a;

        {
            this.a = r1;
        }

        public void onRefreshNet(PullToRefreshLayout pullToRefreshLayout) {
            super.onRefreshNet(pullToRefreshLayout);
            TtgMainView.mTtgUrl = null;
            this.a.getMain("OnRefreshListener");
        }
    };
    private Activity mActivity;
    protected RotateAnimation mAnimation;
    private Runnable mAutoRefreshRunnable = new Runnable(this) {
        final /* synthetic */ TtgMainView a;

        {
            this.a = r1;
        }

        public void run() {
            this.a.mHandler.obtainMessage().sendToTarget();
        }
    };
    private b<ab> mCall;
    protected String mCateId;
    protected int mChangeIndex = -1;
    private FrameLayout mFyScroll;
    private GridView mGvLanMu;
    private Handler mHandler = new Handler(this) {
        final /* synthetic */ TtgMainView a;

        {
            this.a = r1;
        }

        public void handleMessage(Message message) {
            TtgInterface.clearCache();
            this.a.getMain("mHandler");
        }
    };
    public HomeData mHomeData;
    private TextView mIconBackTop;
    private d mLanMuAdapter;
    private PullableListView mLvSession;
    public LinearLayout mLyIcon;
    protected String mMainAction;
    private int mNextPage = 1;
    private int mPage = 2;
    private PullToRefreshLayout mPullLayout;
    private RelativeLayout mRlNum;
    private h mSessionAdapter;
    private List<Special> mSpecialList = new ArrayList();
    private List<Special> mSpecialTotalList = new ArrayList();
    private String mTempCateId;
    private int mTotalNum;
    private TtgCallback mTtgCallback;
    protected ImageView mTtgIvLoading;
    protected LinearLayout mTtgLyFailLayout;
    private TtgScrollView mTtgScrollView;
    protected TextView mTtgTvFirstTitle;
    protected TextView mTtgTvSecondTitle;
    protected TextView mTtgTvTryAgain;
    private TextView mTvBottom;
    private TextView mTvLoadBottom;
    private TextView mTvNum;
    private TextView mTvSumNum;
    private View mView;
    private TagLayout mWordWrapView;

    public static void setTtgUrl(TtgUrl ttgUrl) {
        mTtgUrl = ttgUrl;
        Log.d(TAG, "onTtgUrlParse setTtgUrl: " + JSON.toJSONString(mTtgUrl));
    }

    protected void initLoading() {
        if (this.mView != null) {
            this.mTtgIvLoading = (ImageView) this.mView.findViewById(R.id.ttg_iv_loading_img);
            this.mTtgIvLoading.setImageResource(R$drawable.ttg_sdk_loading);
            this.mView.findViewById(R.id.ttg_show_ly_loading).setOnClickListener(this);
            this.mView.findViewById(R.id.ttg_show_ly_loading).setVisibility(8);
            this.mAnimation = new RotateAnimation(0.0f, 360.0f, 1, 0.5f, 1, 0.5f);
            this.mAnimation.setDuration(800);
            this.mAnimation.setInterpolator(new LinearInterpolator());
            this.mAnimation.setFillAfter(true);
            this.mAnimation.setRepeatCount(Integer.MAX_VALUE);
        }
    }

    protected void showLoading() {
        if (this.mTtgIvLoading != null) {
            if (this.mAnimation != null) {
                this.mTtgIvLoading.startAnimation(this.mAnimation);
            }
            this.mTtgIvLoading.setVisibility(0);
            this.mView.findViewById(R.id.ttg_show_ly_loading).setVisibility(0);
        }
        initBcFail();
    }

    protected void hideLoading() {
        if (this.mView != null && this.mTtgIvLoading != null) {
            this.mAnimation.cancel();
            View findViewById = this.mView.findViewById(R.id.ttg_show_ly_loading);
            if (findViewById != null) {
                findViewById.setVisibility(8);
            }
            this.mTtgIvLoading.setVisibility(8);
            if (TtgSDK.sBcInitFlag == -1) {
                initBcFail();
            } else if (this.mTtgLyFailLayout != null) {
                this.mTtgLyFailLayout.setVisibility(8);
            }
        }
    }

    protected void initFailHintLayout() {
        if (this.mView != null && this.mTtgLyFailLayout == null) {
            this.mTtgLyFailLayout = (LinearLayout) this.mView.findViewById(R.id.ttg_ly_fail_layout);
            this.mTtgTvFirstTitle = (TextView) this.mView.findViewById(R.id.ttg_tv_first_title);
            this.mTtgTvSecondTitle = (TextView) this.mView.findViewById(R.id.ttg_tv_second_title);
            this.mTtgTvTryAgain = (TextView) this.mView.findViewById(R.id.ttg_tv_try_again);
            q.onResetShapeThemeColor(this.mTtgTvTryAgain, 0, 0, TtgConfig.getInstance().getThemeColor());
            this.mTtgLyFailLayout.setOnClickListener(this);
            this.mTtgTvTryAgain.setOnClickListener(this);
        }
    }

    private void initBcFail() {
        if (TtgSDK.sBcInitFlag == -1) {
            initFailHintLayout();
            this.mTtgLyFailLayout.setVisibility(0);
            this.mTtgTvSecondTitle.setText(R.string.ttg_bc_fail);
            this.mTtgTvTryAgain.setVisibility(8);
        }
    }

    public TtgMainView(Context context) {
        super(context);
        initView();
    }

    public TtgMainView(Context context, Activity activity) {
        super(context);
        this.mActivity = activity;
        initView();
    }

    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        Log.d(TAG, "TTGMainView onAttachedToWindow: 绑定");
        onTtgUrlDataReady();
        if (this.mChangeIndex != 1) {
            cn.tatagou.sdk.a.c.getInstance().clear();
            initBcFail();
            if (TtgSDK.sBcInitFlag != -1) {
                initData();
                showLoading();
                getMain("onAttachedToWindow");
                cn.tatagou.sdk.e.a.b.homeShowStatEvent(String.valueOf(TtgConfig.getInstance().getPid()));
                return;
            }
            return;
        }
        if (this.mTtgScrollView != null) {
            this.mTtgScrollView.startScroll();
        }
        if (mTtgUrl != null) {
            onOpenTtgUrl(mTtgUrl);
        }
    }

    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        Log.d(TAG, "TTGMainView onDetachedFromWindow: 结束");
        k.closeRunnable(this.mHandler, this.mAutoRefreshRunnable);
        if (this.mTtgScrollView != null) {
            this.mTtgScrollView.stopScroll();
        }
        hideLoading();
        if (this.mCall != null) {
            this.mCall.b();
        }
        IUpdateViewManager.getInstance().unRegistIUpdateView("TtgMainView");
    }

    private void initView() {
        if (this.mView == null) {
            this.mView = inflate(getContext(), R.layout.ttg_tab_fragment, this);
        }
        initLoading();
        initViewLazyLoad(this.mView);
    }

    private void onTtgUrlDataReady() {
        IUpdateViewManager.getInstance().registIUpdateView(new UpdateView("TtgMainView", new IUpdateView<TtgUrl>(this) {
            final /* synthetic */ TtgMainView a;

            {
                this.a = r1;
            }

            public void updateView(TtgUrl ttgUrl) {
                this.a.onOpenTtgUrl(ttgUrl);
            }
        }));
    }

    private void onOpenTtgUrl(TtgUrl ttgUrl) {
        if (ttgUrl != null && this.mHomeData != null && this.mHomeData.getSpecialCats() != null && this.mHomeData.getSpecialCats().size() > 0) {
            mTtgUrl = null;
            onTtgUrlParse(ttgUrl, true);
        }
    }

    private synchronized String onTtgUrlParse(TtgUrl ttgUrl, boolean z) {
        String str;
        Uri parseUrl = ttgUrl.parseUrl();
        if (p.isEmpty(ttgUrl.getHost()) || parseUrl == null) {
            str = null;
        } else {
            if ("ttg://cate".contains(ttgUrl.getHost())) {
                str = parseUrl.getQueryParameter("id");
                if (!p.isEmpty(str)) {
                    if (z) {
                        onPositionCate(this.mHomeData.getSpecialCats(), str);
                    }
                }
            }
            str = null;
        }
        return str;
    }

    private void initViewLazyLoad(View view) {
        this.mCateId = "1";
        this.mMainAction = DMTTG + this.mCateId;
        View inflate = LayoutInflater.from(getContext()).inflate(R.layout.ttg_top_main, null);
        View inflate2 = LayoutInflater.from(getContext()).inflate(R.layout.ttg_main_bottom, null);
        this.mTvBottom = (TextView) inflate2.findViewById(R.id.tv_bottom_title);
        this.mTvLoadBottom = (TextView) inflate2.findViewById(R.id.ttg_loadstate_tv);
        this.mGvLanMu = (GridView) inflate.findViewById(R.id.gv_columns);
        this.mFyScroll = (FrameLayout) inflate.findViewById(R.id.fy_scroll);
        this.mLvSession = (PullableListView) view.findViewById(R.id.ttg_lv_special_list);
        this.mPullLayout = (PullToRefreshLayout) view.findViewById(R.id.ttg_refresh_view);
        this.mLyIcon = (LinearLayout) view.findViewById(R.id.ttg_ly_icon);
        this.mRlNum = (RelativeLayout) view.findViewById(R.id.ttg_rl_num);
        this.mTvNum = (TextView) view.findViewById(R.id.ttg_tv_num);
        this.mTvSumNum = (TextView) view.findViewById(R.id.ttg_tv_sum_num);
        this.mIconBackTop = (TextView) view.findViewById(R.id.ttg_icon_back_top);
        view.findViewById(R.id.ttg_ly_mine_float).setVisibility(0);
        ((TextView) inflate.findViewById(R.id.ttg_tv_space)).setVisibility(0);
        this.mFyScroll.setVisibility(8);
        this.mGvLanMu.setVisibility(8);
        this.mLvSession.addHeaderView(inflate);
        this.mLvSession.addFooterView(inflate2);
        this.mPullLayout.setOnRefreshListener(this.listener);
        this.mLvSession.setCanPullUp(false);
        if (this.mSessionAdapter == null) {
            this.mSessionAdapter = new h(this.mActivity, null, this.mCateId, null);
            this.mLvSession.setAdapter(this.mSessionAdapter);
        }
        this.mTtgScrollView = new TtgScrollView(getContext());
        this.mFyScroll.addView(this.mTtgScrollView);
        this.mIconBackTop.setOnClickListener(this);
        view.findViewById(R.id.iv_ttg_mine).setOnClickListener(this);
        view.findViewById(R.id.iv_ttg_cart).setOnClickListener(this);
        this.mLvSession.setOnScrollListener(this.listViewScrollListener);
        this.mWordWrapView = (TagLayout) inflate.findViewById(R.id.ttg_cats_www);
        inflate.findViewById(R.id.ttg_ly_cats_www).setVisibility(0);
        this.mWordWrapView.setVisibility(0);
    }

    private void onCateDataReady(List<AppCate> list) {
        if (this.mWordWrapView != null && this.mWordWrapView.getChildCount() <= 1) {
            if (list == null || list.size() == 0) {
                if (list == null) {
                    list = new ArrayList();
                }
                list.add(new AppCate("1", getContext().getResources().getString(R.string.ttg_cate_jinxuan)));
            }
            onCateShow(list);
        }
    }

    private void onCateShow(final List<AppCate> list) {
        boolean z = true;
        if (list != null) {
            Object obj;
            this.mWordWrapView.removeAllViews();
            if (this.mTempCateId == null || this.mTempCateId.equals(this.mCateId)) {
                obj = null;
            } else {
                obj = 1;
            }
            if (obj == null || list.indexOf(new AppCate(this.mTempCateId)) <= 0) {
                z = false;
            }
            int i = 0;
            boolean z2 = z;
            while (i < list.size()) {
                final AppCate appCate = (AppCate) list.get(i);
                View textView = new TextView(getContext());
                textView.setText(appCate.getName());
                textView.setTextSize(14.0f);
                textView.setBackgroundResource(R$drawable.sel_shape_cats);
                if ((z2 || i != 0) && !(z2 && this.mTempCateId.equals(appCate.getId()))) {
                    textView.setTextColor(Color.parseColor(COLOR));
                    q.onResetShapeThemeColor(textView, 2, Color.parseColor(COLOR), Color.parseColor("#FFFFFF"));
                } else {
                    onChangeCate(this.mTempCateId, textView, z2);
                    this.mTempCateId = null;
                    z2 = false;
                }
                LayoutParams layoutParams = new LinearLayout.LayoutParams(-2, -2);
                layoutParams.setMargins(p.dip2px(getContext(), 5.0f), p.dip2px(getContext(), 5.0f), p.dip2px(getContext(), 5.0f), p.dip2px(getContext(), 5.0f));
                textView.setLayoutParams(layoutParams);
                textView.setBackgroundResource(R$drawable.sel_shape_cats);
                this.mWordWrapView.addView(textView);
                textView.setOnClickListener(new OnClickListener(this) {
                    final /* synthetic */ TtgMainView c;

                    public void onClick(View view) {
                        if (!p.isEmpty(appCate.getId())) {
                            this.c.onPositionCate(list, appCate.getId());
                        }
                    }
                });
                i++;
            }
        }
    }

    private void onPositionCate(List<AppCate> list, String str) {
        Log.d(TAG, "onPositionCate cateId: " + list.indexOf(new AppCate(str)));
        if (!str.equals(this.mCateId) && list.indexOf(new AppCate(str)) >= 0) {
            for (int i = 0; i < list.size(); i++) {
                AppCate appCate = (AppCate) list.get(i);
                TextView textView = (TextView) this.mWordWrapView.getChildAt(i);
                if (textView != null) {
                    if (str.equals(appCate.getId())) {
                        onChangeCate(str, textView, true);
                    } else {
                        textView.setTextColor(Color.parseColor(COLOR));
                        q.onResetShapeThemeColor(textView, 2, Color.parseColor(COLOR), Color.parseColor("#FFFFFF"));
                    }
                }
            }
        }
    }

    private void onChangeCate(String str, TextView textView, boolean z) {
        textView.setTextColor(TtgConfig.getInstance().getThemeColor());
        q.onResetShapeThemeColor(textView, 2, TtgConfig.getInstance().getThemeColor(), Color.parseColor("#FFFFFF"));
        if (z) {
            cn.tatagou.sdk.e.a.b.cateStatEvent(str);
            n.onSysCfgShow(str, this.mTvBottom);
            this.mCateId = str;
            mTtgUrl = null;
            if (this.mHomeData != null) {
                setMainInfo(this.mHomeData);
                return;
            }
            showLoading();
            getMain("onCateShow");
        }
    }

    public void ttgRefresh(TtgCallback ttgCallback) {
        this.mTtgCallback = ttgCallback;
        mTtgUrl = null;
        getMain("OnRefreshListener");
    }

    protected void initData() {
        Map map = (Map) JSON.parseObject(cn.tatagou.sdk.b.a.getStr("sysConfigInfo"), new TypeReference<Map<String, String>>(this) {
            final /* synthetic */ TtgMainView a;

            {
                this.a = r1;
            }
        }, new Feature[0]);
        if (map == null || map.size() <= 0) {
            cn.tatagou.sdk.util.b.getSysCfg(new cn.tatagou.sdk.util.c(this) {
                final /* synthetic */ TtgMainView a;

                {
                    this.a = r1;
                }

                public void setSysCfg(Map<String, String> map) {
                    n.onSysCfgShow(this.a.mCateId, this.a.mTvBottom);
                    n.setSpHintText(this.a.mSessionAdapter, true, 0);
                }
            });
        } else {
            n.onSysCfgShow(this.mCateId, this.mTvBottom);
            n.setSpHintText(this.mSessionAdapter, true, 0);
        }
        Log.d(TAG, "onTtgUrlParse initData: " + JSON.toJSONString(mTtgUrl));
        if (mTtgUrl != null) {
            this.mTempCateId = onTtgUrlParse(mTtgUrl, false);
        }
    }

    private void setShopType(List<Special> list) {
        this.mGvLanMu.setNumColumns(n.getNumColumns(list));
        if (this.mLanMuAdapter == null) {
            this.mLanMuAdapter = new d(this.mActivity, list, this.mCateId, null);
            this.mGvLanMu.setAdapter(this.mLanMuAdapter);
            return;
        }
        this.mLanMuAdapter.setItems(list);
    }

    protected void getMain(String str) {
        k.closeRunnable(this.mHandler, this.mAutoRefreshRunnable);
        this.mCall = ((cn.tatagou.sdk.a.a.a) e.getInstance().getService(cn.tatagou.sdk.a.a.a.class)).home(this.mPage, "1");
        cn.tatagou.sdk.a.b.onCommRequestApi(this.homeApiCallback, this.mCall, new TypeReference<CommPojo<HomeData>>(this) {
            final /* synthetic */ TtgMainView a;

            {
                this.a = r1;
            }
        }.getType());
    }

    private void setAutoRefreshAPI(long j) {
        long onRefreshTimeReady = n.onRefreshTimeReady(j);
        this.mChangeIndex = 1;
        if (onRefreshTimeReady > 0) {
            this.mHandler.postDelayed(this.mAutoRefreshRunnable, onRefreshTimeReady);
        }
    }

    private void setMainInfo(final HomeData homeData) {
        if (this.mHandler != null) {
            this.mHandler.post(new Runnable(this) {
                final /* synthetic */ TtgMainView b;

                public void run() {
                    List list = null;
                    this.b.mFyScroll.setVisibility(8);
                    this.b.mGvLanMu.setVisibility(8);
                    if ("1".equals(this.b.mCateId)) {
                        List bannerSpecialList = homeData != null ? homeData.getBannerSpecialList() : null;
                        if (bannerSpecialList != null && bannerSpecialList.size() > 0) {
                            this.b.mFyScroll.setVisibility(0);
                            this.b.mTtgScrollView.onBannerSpecialShow(this.b.mActivity, this.b.mCateId, bannerSpecialList);
                        }
                        if (homeData != null) {
                            bannerSpecialList = homeData.getCateSpecialList();
                        } else {
                            bannerSpecialList = null;
                        }
                        if (bannerSpecialList != null && bannerSpecialList.size() >= 4) {
                            this.b.mGvLanMu.setVisibility(0);
                            this.b.setShopType(bannerSpecialList);
                        }
                    }
                    this.b.hideLoading();
                    if (homeData != null) {
                        list = homeData.getNormalSpecialList();
                    }
                    if (list == null || list.size() <= 0) {
                        this.b.mTvBottom.setVisibility(0);
                        this.b.mTvLoadBottom.setVisibility(8);
                        return;
                    }
                    this.b.mNextPage = 1;
                    if (this.b.mSpecialList.size() > 0) {
                        this.b.mSpecialList.clear();
                    }
                    n.onSpecialDataReady(this.b.mCateId, this.b.mSpecialTotalList, list);
                    this.b.mTotalNum = this.b.mSpecialTotalList.size();
                    this.b.pagingSpecialList();
                }
            });
        }
    }

    private void pagingSpecialList() {
        int i = 0;
        if (this.mSpecialTotalList != null) {
            int allPager = j.allPager(this.mSpecialTotalList.size());
            if (this.mNextPage <= 0 || this.mNextPage > allPager) {
                this.mTvBottom.setVisibility(0);
                this.mTvLoadBottom.setVisibility(8);
            } else {
                int i2;
                int i3;
                int lastPage = j.lastPage(this.mNextPage, this.mSpecialTotalList.size());
                int i4 = (this.mNextPage - 1) * j.a;
                if (lastPage - i4 >= j.a) {
                    i2 = 1;
                } else {
                    i2 = 0;
                }
                TextView textView = this.mTvLoadBottom;
                if (i2 != 0) {
                    i3 = 0;
                } else {
                    i3 = 8;
                }
                textView.setVisibility(i3);
                TextView textView2 = this.mTvBottom;
                if (i2 != 0) {
                    i = 8;
                }
                textView2.setVisibility(i);
                for (i = i4; i < lastPage; i++) {
                    this.mSpecialList.add((Special) this.mSpecialTotalList.get(i));
                }
                this.mNextPage = j.next(this.mNextPage, allPager);
            }
            this.mSessionAdapter.setItems(this.mSpecialList, this.mCateId);
        }
    }

    public void onClick(View view) {
        int id = view.getId();
        Map hashMap = new HashMap();
        hashMap.put(TtgConfigKey.TTG_EVENT_FROM, "HOME");
        if (id == R.id.iv_ttg_cart) {
            TtgInterface.openBcCarts(this.mActivity, hashMap);
        } else if (id == R.id.iv_ttg_mine) {
            TtgInterface.openTtgMine(this.mActivity, hashMap);
        } else if (id == R.id.ttg_icon_back_top) {
            this.mLvSession.setSelection(0);
        } else if (id == R.id.ttg_tv_try_again) {
            getMain("ttg_tv_try_again");
        }
    }
}
