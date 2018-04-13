package cn.tatagou.sdk.fragment;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;
import android.widget.TextView;
import cn.tatagou.sdk.R;
import cn.tatagou.sdk.a.a;
import cn.tatagou.sdk.a.e;
import cn.tatagou.sdk.activity.TtgSearchGoodsActivity;
import cn.tatagou.sdk.adapter.CatAdapter;
import cn.tatagou.sdk.android.TtgConfig;
import cn.tatagou.sdk.android.TtgConfigKey;
import cn.tatagou.sdk.android.TtgInterface;
import cn.tatagou.sdk.pojo.AppCate;
import cn.tatagou.sdk.pojo.AppHomeData;
import cn.tatagou.sdk.pojo.CommPojo;
import cn.tatagou.sdk.pojo.Config;
import cn.tatagou.sdk.pojo.HomeAd;
import cn.tatagou.sdk.pojo.HomeData;
import cn.tatagou.sdk.pojo.RcmmParam;
import cn.tatagou.sdk.pojo.ResultPojo;
import cn.tatagou.sdk.pojo.TtgTitleBar;
import cn.tatagou.sdk.pojo.TtgUrl;
import cn.tatagou.sdk.util.c;
import cn.tatagou.sdk.util.f;
import cn.tatagou.sdk.util.l;
import cn.tatagou.sdk.util.p;
import cn.tatagou.sdk.util.q;
import cn.tatagou.sdk.view.IUpdateViewManager;
import cn.tatagou.sdk.view.TtgCustomViewPager;
import cn.tatagou.sdk.view.TtgPagerSlidingTab;
import cn.tatagou.sdk.view.UpdateView;
import com.bumptech.glide.i;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import okhttp3.ab;
import okhttp3.w;
import okhttp3.y;
import retrofit2.b;

public class TtgMainFragment extends BaseFragment {
    private static final String TAG = TtgMainFragment.class.getSimpleName();
    private a<CommPojo<HomeData>> homeApiCallback = new TtgMainFragment$10(this);
    private boolean isInitFlag = false;
    private boolean isLoadDataFlag = false;
    public AppCate mAppCate = new AppCate();
    private List<AppCate> mAppCatsList = new ArrayList();
    private b<ab> mCall;
    private CatAdapter mCateAdapter;
    private TtgPagerSlidingTab mCateBar;
    private int mCommApiNum;
    private HomeAd mHomeAd;
    private int mLoadLamMuPicNum;
    private b<ab> mRcmmParamCall;
    private int mRequestPage = 2;
    public TtgCustomViewPager mScrollCatViewPage;
    private String mTtgTargetSpId;
    private c onCallbackListener = new TtgMainFragment$1(this);
    private a<CommPojo<RcmmParam>> rcmmParamApiCallback = new TtgMainFragment$6(this);
    private a<ResultPojo> saveUserInfoCallback = new TtgMainFragment$8(this);

    protected void onViewVisible() {
        super.onViewVisible();
        if (this.isInitFlag) {
            cn.tatagou.sdk.e.a.b.homeShowStatEvent(String.valueOf(TtgConfig.getInstance().getPid()));
            onLazyRequestApi("onViewVisible");
        }
    }

    public static TtgMainFragment newInstance(boolean z) {
        Bundle bundle = new Bundle();
        TtgMainFragment ttgMainFragment = new TtgMainFragment();
        ttgMainFragment.setArguments(bundle);
        return ttgMainFragment;
    }

    public View newView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        if (this.mView == null) {
            this.mView = layoutInflater.inflate(R.layout.ttg_main_fragment, viewGroup, false);
        }
        return this.mView;
    }

    public void initView(View view) {
        super.initView(view);
        initLoading();
        onBcSuccFlag();
        registerBcFailListener();
        cn.tatagou.sdk.view.c.setTitleBar(getActivity(), view, this.onCallbackListener);
        this.mCateBar = (TtgPagerSlidingTab) view.findViewById(R.id.ttg_cats_tab);
        this.mScrollCatViewPage = (TtgCustomViewPager) view.findViewById(R.id.ttg_viewPage_fragment);
        this.mCateBar.setOnPageSelectedLister(this.onCallbackListener);
        q.onResetShapeThemeColor((LinearLayout) view.findViewById(R.id.ttg_idenity_relative_top), 0, 0, TtgConfig.getInstance().getThemeColor());
    }

    public void onNewIntent(Intent intent) {
        if (intent != null) {
            AppHomeData.getInstance().setTtgUrl((TtgUrl) intent.getParcelableExtra(TtgConfigKey.TTG_URl));
            onTtgUrlDataReady();
        }
    }

    private void onTtgUrlParse() {
        TtgUrl ttgUrl = AppHomeData.getInstance().getTtgUrl();
        if (ttgUrl != null) {
            Uri parseUrl = ttgUrl.parseUrl();
            if (!p.isEmpty(ttgUrl.getHost()) && parseUrl != null) {
                if (this.mAppCate == null) {
                    this.mAppCate = new AppCate();
                }
                if ("ttg://cate".contains(ttgUrl.getHost())) {
                    String queryParameter = parseUrl.getQueryParameter("id");
                    AppCate appCate = this.mAppCate;
                    if (p.isEmpty(queryParameter)) {
                        queryParameter = "1";
                    }
                    appCate.setId(queryParameter);
                    return;
                }
                this.mTtgTargetSpId = parseUrl.getQueryParameter("spId");
            }
        }
    }

    private void onTtgUrlDataReady() {
        onTtgUrlParse();
        TtgUrl ttgUrl = AppHomeData.getInstance().getTtgUrl();
        if (ttgUrl != null && !p.isEmpty(ttgUrl.getHost())) {
            if ("ttg://cate".contains(ttgUrl.getHost())) {
                findCatePositionInCateBar();
            } else if ("ttg://home".contains(ttgUrl.getHost()) && !p.isEmpty(this.mTtgTargetSpId)) {
                onTabSpecialTop();
            }
        }
    }

    protected void initPageData() {
        TtgUrl ttgUrl = (TtgUrl) getActivity().getIntent().getParcelableExtra(TtgConfigKey.TTG_URl);
        if (ttgUrl != null) {
            AppHomeData.getInstance().setTtgUrl(ttgUrl);
        }
        Config.getInstance().setSex(cn.tatagou.sdk.util.b.getSex());
        initNotify();
        this.isInitFlag = true;
        if (!this.mIsInVisible) {
            cn.tatagou.sdk.e.a.b.homeShowStatEvent(String.valueOf(TtgConfig.getInstance().getPid()));
            onLazyRequestApi("initPageData");
        }
    }

    private void onLazyRequestApi(String str) {
        if (!this.isLoadDataFlag) {
            Log.d(TAG, "TTG-Home: " + str);
            this.isLoadDataFlag = true;
            onTtgUrlParse();
            showLoading();
            cn.tatagou.sdk.util.b.getSysCfg();
            onRequestHome();
            requestGetRcmmParamApi();
        }
    }

    private void requestGetRcmmParamApi() {
        this.mRcmmParamCall = ((cn.tatagou.sdk.a.a.a) e.getInstance().getService(cn.tatagou.sdk.a.a.a.class)).getRcmmParams();
        cn.tatagou.sdk.a.b.onCommRequestApi(this.rcmmParamApiCallback, this.mRcmmParamCall, new TtgMainFragment$5(this).getType());
    }

    private void saveUserInfo(String str) {
        cn.tatagou.sdk.a.b.onCommRequestApi(this.saveUserInfoCallback, ((cn.tatagou.sdk.a.a.a) e.getInstance().getService(cn.tatagou.sdk.a.a.a.class)).saveUserInfo(Config.getInstance().getSex(), str), new TtgMainFragment$7(this).getType());
    }

    private void onRequestHome() {
        this.mCall = ((cn.tatagou.sdk.a.a.a) e.getInstance().getService(cn.tatagou.sdk.a.a.a.class)).home(this.mRequestPage, "1");
        cn.tatagou.sdk.a.b.onCommRequestApi(this.homeApiCallback, this.mCall, new TtgMainFragment$9(this).getType());
    }

    private void onMyShoppingShow() {
        hideLoading();
        this.mTtgMyCartCircle.setVisibility(0);
    }

    private void registerBcFailListener() {
        UpdateView updateView = new UpdateView("bcInitFail", new TtgMainFragment$11(this));
        IUpdateViewManager.getInstance().registIUpdateView(updateView);
        addUpdateViewList(updateView);
    }

    private void onActImgDataReady() {
        HomeData homeData = AppHomeData.getInstance().getHomeData();
        if (homeData != null) {
            this.mHomeAd = homeData.getMainAd();
            if (!isAdded() || this.mHomeAd == null) {
                hideLoading();
                return;
            }
            String str;
            switch (p.isEmpty(this.mHomeAd.getType()) ? 1 : Integer.parseInt(this.mHomeAd.getType())) {
                case 1:
                    str = cn.tatagou.sdk.b.a.getStr("actAllUrl");
                    if (!(p.isEmpty(this.mHomeAd.getImg()) || this.mHomeAd.getImg().equals(str))) {
                        loadNoticeImg(this.mHomeAd.getImg());
                        return;
                    }
                case 2:
                    str = cn.tatagou.sdk.b.a.getStr("actDayUrl");
                    long subTimeDay = f.subTimeDay(cn.tatagou.sdk.b.a.getStr("actDayTime"), f.getNowTimeYMD(), "yyyy-MM-dd");
                    if (!p.isEmpty(this.mHomeAd.getImg()) && (!this.mHomeAd.getImg().equals(str) || subTimeDay >= 1)) {
                        loadNoticeImg(this.mHomeAd.getImg());
                        return;
                    }
            }
            hideLoading();
        }
    }

    private void loadNoticeImg(String str) {
        int windowWidth = p.getWindowWidth(getActivity());
        windowWidth = windowWidth == -1 ? 720 : windowWidth - p.dip2px(getActivity(), 40.0f);
        i.a((Fragment) this).a(p.onImgUrlJpg(str)).j().b().a(new TtgMainFragment$12(this, windowWidth, windowWidth));
    }

    private void onActImgShow(Bitmap bitmap) {
        hideLoading();
        ImageView imageView = (ImageView) this.mView.findViewById(R.id.iv_ttg_notice_img);
        LinearLayout linearLayout = (LinearLayout) this.mView.findViewById(R.id.ly_ttg_notice_img);
        if (isAdded() && imageView != null && linearLayout != null) {
            linearLayout.setVisibility(0);
            imageView.setImageBitmap(bitmap);
            imageView.setOnClickListener(this);
            linearLayout.setOnClickListener(this);
            TextView textView = (TextView) this.mView.findViewById(R.id.ttg_icon_close);
            TextView textView2 = (TextView) this.mView.findViewById(R.id.ttg_tv_title_line);
            if (textView2 != null) {
                textView2.setVisibility(8);
            }
            if (textView != null) {
                textView.setOnClickListener(this);
            }
        }
    }

    public void onSearchIconClick() {
        super.onSearchIconClick();
        onSearchClick();
    }

    private void onHomeDataReady(HomeData homeData) {
        homeData.setCurrPage(this.mRequestPage);
        AppHomeData.getInstance().setHomeData(homeData);
        getAddCommApiNum();
        onLoadDataReady(homeData.getTimestamp());
    }

    private void onLoadDataReady(String str) {
        if (this.mCommApiNum == 2) {
            onCateDataReady(str);
        }
    }

    private void onLoadCatePicDataReady(String str) {
        this.mLoadLamMuPicNum = 0;
        if (this.mAppCatsList.size() > 0) {
            boolean z;
            for (AppCate appCate : this.mAppCatsList) {
                if (appCate != null && (TextUtils.isEmpty(appCate.getSelectThumbnail()) || TextUtils.isEmpty(appCate.getThumbnail()))) {
                    z = false;
                    break;
                }
            }
            z = true;
            this.mCateBar.setShowPic(z);
            this.mCateBar.setPstsIndicatorColor(TtgConfig.getInstance().getCateBarColor());
            this.mCateBar.setSelectedTabTextColor(TtgConfig.getInstance().getCateBarColor());
            if (z) {
                for (AppCate appCate2 : this.mAppCatsList) {
                    String substring = appCate2.getThumbnail().substring(appCate2.getThumbnail().lastIndexOf("/") + 1, appCate2.getThumbnail().length());
                    String substring2 = appCate2.getSelectThumbnail().substring(appCate2.getSelectThumbnail().lastIndexOf("/") + 1, appCate2.getSelectThumbnail().length());
                    Bitmap storePic = p.getStorePic(getActivity(), substring);
                    Bitmap storePic2 = p.getStorePic(getActivity(), substring2);
                    onCheckCacheCatePic(storePic, appCate2.getThumbnail(), str);
                    onCheckCacheCatePic(storePic2, appCate2.getSelectThumbnail(), str);
                }
                return;
            }
            LayoutParams layoutParams = (LayoutParams) this.mCateBar.getLayoutParams();
            layoutParams.height = p.dip2px(getActivity(), 36.0f);
            this.mCateBar.setLayoutParams(layoutParams);
            onCateBarShow();
            onActImgOrMyShoppingDataReady();
            onDateError(str);
        }
    }

    private void onCheckCacheCatePic(Bitmap bitmap, String str, String str2) {
        if (bitmap != null) {
            openMain(str2);
        } else {
            loadLanMuPic(str, str2);
        }
    }

    public void loadLanMuPic(String str, String str2) {
        if (TextUtils.isEmpty(str)) {
            str = "https://ss0.bdstatic.com/5aV1bjqh_Q23odCf/static/superman/img/logo/bd_logo1_31bdc765.png";
        }
        new w().a(new y.a().a(str).b()).a(new TtgMainFragment$2(this, str2));
    }

    public void openMain(String str) {
        if (isAdded()) {
            getActivity().runOnUiThread(new TtgMainFragment$3(this, str));
        }
    }

    public void onDateError(String str) {
        if (!p.isEmpty(str) && f.deffDate(str)) {
            l.showToastCenter(getActivity(), getString(R.string.ttg_phone_data));
        }
    }

    private void onActImgOrMyShoppingDataReady() {
        if (!cn.tatagou.sdk.b.a.getBoolean("mineShoppingHint") && TtgTitleBar.getInstance().isMyShoppingIconShown()) {
            onMyShoppingShow();
        }
        if (!isIdentityViewShown()) {
            onActImgDataReady();
        }
    }

    private boolean isIdentityViewShown() {
        Object sex = cn.tatagou.sdk.util.b.getSex();
        if (!TextUtils.isEmpty(sex)) {
            return false;
        }
        hideLoading();
        return cn.tatagou.sdk.view.c.checkNetAndChooseSex(getActivity(), sex, this.mView, this.onCallbackListener);
    }

    private List<AppCate> onRcmmCateDateReady(List<AppCate> list) {
        if (AppHomeData.getInstance().getRcmmParam() == null) {
            return list;
        }
        List<String> rcmmCates = AppHomeData.getInstance().getRcmmParam().getRcmmCates();
        if (rcmmCates == null || rcmmCates.size() == 0) {
            return list;
        }
        if (rcmmCates.size() == 1 && "1".equals(rcmmCates.get(0))) {
            return list;
        }
        Collection linkedList = new LinkedList();
        for (String appCate : rcmmCates) {
            linkedList.add(new AppCate(appCate));
        }
        linkedList.remove(new AppCate("1"));
        Collection linkedList2 = new LinkedList();
        Iterator it = linkedList.iterator();
        while (it.hasNext()) {
            AppCate appCate2 = (AppCate) it.next();
            if (!(appCate2 == null || p.isEmpty(appCate2.getId()))) {
                for (AppCate appCate3 : list) {
                    if (appCate3 != null && appCate2.getId().equals(appCate3.getId())) {
                        linkedList2.add(appCate3);
                    }
                }
            }
        }
        LinkedList linkedList3 = new LinkedList();
        linkedList3.addAll(list);
        linkedList3.removeAll(linkedList);
        linkedList3.addAll(1, linkedList2);
        return linkedList3;
    }

    private void onCateDataReady(String str) {
        HomeData homeData = AppHomeData.getInstance().getHomeData();
        if (homeData != null) {
            Collection specialCats = homeData.getSpecialCats();
            if (specialCats == null || specialCats.size() == 0) {
                if (specialCats == null) {
                    specialCats = new ArrayList();
                }
                specialCats.add(new AppCate("1", getString(R.string.ttg_cate_jinxuan)));
            } else {
                specialCats = onRcmmCateDateReady(specialCats);
            }
            this.mAppCatsList.clear();
            this.mAppCatsList.addAll(specialCats);
            onLoadCatePicDataReady(str);
        }
    }

    private void findCatePositionInCateBar() {
        String id = this.mAppCate == null ? "" : this.mAppCate.getId();
        if (!p.isEmpty(id)) {
            int i = 0;
            while (i < this.mAppCatsList.size()) {
                AppCate appCate = (AppCate) this.mAppCatsList.get(i);
                if (appCate == null || !id.equals(appCate.getId())) {
                    i++;
                } else {
                    this.mScrollCatViewPage.setCurrentItem(i);
                    return;
                }
            }
        }
    }

    private void onCateBarShow() {
        this.mScrollCatViewPage.setOffscreenPageLimit(TtgConfig.getInstance().getCachePages());
        if (this.mCateAdapter == null) {
            this.mCateAdapter = new CatAdapter(getActivity(), getChildFragmentManager() == null ? getActivity().getSupportFragmentManager() : getChildFragmentManager(), this.mAppCatsList, this.mTtgTargetSpId);
            this.mScrollCatViewPage.setAdapter(this.mCateAdapter);
        } else {
            this.mCateAdapter.setItem(this.mAppCatsList);
        }
        this.mCateBar.setViewPager(this.mScrollCatViewPage);
        if (!"1".equals(this.mAppCate == null ? "1" : this.mAppCate.getId())) {
            findCatePositionInCateBar();
        }
    }

    private void onTabSpecialTop() {
        if (this.mCateAdapter != null) {
            if ((this.mAppCate == null ? 0 : this.mAppCate.getPosition()) != 0) {
                this.mCateAdapter.setSpecialId(this.mTtgTargetSpId);
                this.mScrollCatViewPage.setCurrentItem(0);
            }
            if (this.mCateAdapter.a != null) {
                this.mCateAdapter.a.onSpecialTop(this.mTtgTargetSpId);
            }
        }
    }

    public void onClickListener(int i, boolean z) {
        super.onClickListener(i, z);
        if (this.mCateAdapter != null && this.mCateAdapter.a != null) {
            Log.d(TAG, "autoRefresh onClickListener: " + z);
            this.mCateAdapter.a.autoRefresh(z);
        }
    }

    public void onPause() {
        super.onPause();
        AppHomeData.getInstance().setTtgUrl(null);
    }

    private void initNotify() {
        UpdateView updateView = new UpdateView("TtgHome", new TtgMainFragment$4(this));
        IUpdateViewManager.getInstance().registIUpdateView(updateView);
        addUpdateViewList(updateView);
    }

    public void onResume() {
        super.onResume();
    }

    protected void onTitleBarClick() {
        super.onTitleBarClick();
        if (this.mCateAdapter != null && this.mCateAdapter.a != null) {
            this.mCateAdapter.a.setListTop(0);
        }
    }

    public void onDestroy() {
        AppHomeData.getInstance().clear();
        if (this.mCall != null) {
            this.mCall.b();
        }
        if (this.mRcmmParamCall != null) {
            this.mRcmmParamCall.b();
        }
        onReportLogCount();
        super.onDestroy();
    }

    private void onReportLogCount() {
        cn.tatagou.sdk.e.a init = cn.tatagou.sdk.e.a.init(getActivity());
        int eventQueueSize = init.getEventQueueSize();
        String schema = init.getSchema();
        if (eventQueueSize != 1) {
            return;
        }
        if (schema == null || "SLS".equals(schema)) {
            init.statHttpReport();
        }
    }

    public void onClick(View view) {
        super.onClick(view);
        int id = view.getId();
        if (id == R.id.iv_ttg_notice_img) {
            onSaveActImgDataClick();
            if (!p.isEmpty(this.mHomeAd.getTtgUrl())) {
                TtgInterface.recoverTtgMain(getActivity(), this.mHomeAd.getTtgUrl(), TtgConfig.getInstance().getPid());
            }
        } else if (id == R.id.ttg_icon_close || id == R.id.ly_ttg_notice_img) {
            onSaveActImgDataClick();
        }
    }

    public void onRightFirstIconClick() {
        super.onRightFirstIconClick();
        Map hashMap = new HashMap();
        hashMap.put(TtgConfigKey.TTG_EVENT_FROM, "HOME");
        TtgInterface.openBcCarts(getActivity(), hashMap);
    }

    public void onSearchClick() {
        super.onSearchClick();
        startActivity(new Intent(getActivity(), TtgSearchGoodsActivity.class));
    }

    protected void onTitleBarRightIconClick() {
        cn.tatagou.sdk.b.a.saveBoolean("mineShoppingHint", true);
        this.mTtgMyCartCircle.setVisibility(8);
        Map hashMap = new HashMap();
        hashMap.put(TtgConfigKey.TTG_EVENT_FROM, "HOME");
        TtgInterface.openTtgMine(getActivity(), hashMap);
    }

    private synchronized void getAddCommApiNum() {
        this.mCommApiNum++;
    }

    public void onRetryClick() {
        super.onRetryClick();
        this.mCommApiNum = 0;
        onRequestHome();
        requestGetRcmmParamApi();
    }

    protected void onTitleBarLeftIconClick() {
        IUpdateViewManager.getInstance().notifyIUpdateView("ttgGoBackTabHome", Boolean.valueOf(false));
        super.onTitleBarLeftIconClick();
    }

    public void onTitleBack() {
        IUpdateViewManager.getInstance().notifyIUpdateView("ttgGoBackTabHome", Boolean.valueOf(false));
        super.onTitleBack();
    }

    private void onSaveActImgDataClick() {
        if (this.mHomeAd != null) {
            onActImgHideClick();
            switch (p.isEmpty(this.mHomeAd.getType()) ? 1 : Integer.parseInt(this.mHomeAd.getType())) {
                case 1:
                    if (!p.isEmpty(this.mHomeAd.getImg())) {
                        cn.tatagou.sdk.b.a.saveStr("actAllUrl", this.mHomeAd.getImg());
                        return;
                    }
                    return;
                case 2:
                    if (!p.isEmpty(this.mHomeAd.getImg())) {
                        cn.tatagou.sdk.b.a.saveStr("actDayUrl", this.mHomeAd.getImg());
                    }
                    String nowTimeYMD = f.getNowTimeYMD();
                    if (!p.isEmpty(nowTimeYMD)) {
                        cn.tatagou.sdk.b.a.saveStr("actDayTime", nowTimeYMD);
                        return;
                    }
                    return;
                default:
                    return;
            }
        }
    }

    private void onActImgHideClick() {
        LinearLayout linearLayout = (LinearLayout) this.mView.findViewById(R.id.ly_ttg_notice_img);
        if (linearLayout != null) {
            linearLayout.setVisibility(8);
        }
        TextView textView = (TextView) this.mView.findViewById(R.id.ttg_tv_title_line);
        if (textView != null) {
            textView.setVisibility(0);
        }
    }
}
