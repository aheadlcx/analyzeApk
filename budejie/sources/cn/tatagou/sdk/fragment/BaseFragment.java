package cn.tatagou.sdk.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.animation.LinearInterpolator;
import android.view.animation.RotateAnimation;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.RelativeLayout.LayoutParams;
import android.widget.TextView;
import cn.tatagou.sdk.R;
import cn.tatagou.sdk.R$dimen;
import cn.tatagou.sdk.R$drawable;
import cn.tatagou.sdk.a.c;
import cn.tatagou.sdk.android.TtgConfig;
import cn.tatagou.sdk.android.TtgInterface;
import cn.tatagou.sdk.android.TtgSDK;
import cn.tatagou.sdk.pojo.TitleBar;
import cn.tatagou.sdk.pojo.TtgTitleBar;
import cn.tatagou.sdk.util.p;
import cn.tatagou.sdk.util.q;
import cn.tatagou.sdk.view.IUpdateViewManager;
import cn.tatagou.sdk.view.IconTextView;
import cn.tatagou.sdk.view.TitleBarIconTextView;
import cn.tatagou.sdk.view.UpdateView;
import cn.tatagou.sdk.view.b;
import com.bumptech.glide.i;
import java.util.LinkedList;

public class BaseFragment extends Fragment implements OnClickListener {
    private static final String TAG = BaseFragment.class.getSimpleName();
    protected boolean isVisible = false;
    protected RotateAnimation mAnimation;
    protected EditText mEdtSearch;
    protected boolean mIsInVisible = false;
    protected boolean mIsVisible = false;
    protected RelativeLayout mRlRightIcon;
    protected RelativeLayout mRylToolBar;
    protected ImageView mTtgIvLoading;
    protected LinearLayout mTtgLyFailLayout;
    protected LinearLayout mTtgLyLoading;
    public IconTextView mTtgMyCartCircle;
    protected RelativeLayout mTtgRlSearch;
    protected TextView mTtgTvFirstTitle;
    protected TextView mTtgTvSecondTitle;
    protected TextView mTtgTvTryAgain;
    protected TextView mTvBackup;
    protected TextView mTvMineIcon;
    protected TextView mTvRightIcon;
    protected TitleBarIconTextView mTvSearchIcon;
    protected TextView mTvTitle;
    protected LinkedList<UpdateView> mUpdateViewList;
    protected View mView;

    public void onHiddenChanged(boolean z) {
        super.onHiddenChanged(z);
        if (!this.mIsVisible && !z) {
            this.mIsVisible = true;
            this.mIsInVisible = false;
            onViewVisible();
        } else if (!this.mIsInVisible && z) {
            this.mIsInVisible = true;
            this.mIsVisible = false;
            onViewInVisible();
        }
    }

    protected void addUpdateViewList(UpdateView updateView) {
        if (this.mUpdateViewList == null) {
            this.mUpdateViewList = new LinkedList();
        }
        this.mUpdateViewList.add(0, updateView);
    }

    public void setUserVisibleHint(boolean z) {
        super.setUserVisibleHint(z);
        if (!this.mIsVisible && z) {
            this.mIsVisible = true;
            this.mIsInVisible = false;
            onViewVisible();
        } else if (!this.mIsInVisible && !z) {
            this.mIsVisible = false;
            this.mIsInVisible = true;
            onViewInVisible();
        }
    }

    protected void onViewVisible() {
        this.isVisible = true;
        if (isAdded()) {
            if (this.mRylToolBar != null) {
                this.mRylToolBar.setBackgroundColor(TtgTitleBar.getInstance().getBgColor());
            }
            if (this.mTtgMyCartCircle != null) {
                this.mTtgMyCartCircle.setTextColor(TtgTitleBar.getInstance().getCircleColor());
            }
            if (this.mView != null) {
                View findViewById = this.mView.findViewById(R.id.ttg_status_view);
                if (findViewById != null) {
                    findViewById.setBackgroundColor(TtgTitleBar.getInstance().getStatusBarBgColor());
                }
            }
        }
    }

    protected void onViewInVisible() {
        this.isVisible = false;
    }

    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        try {
            c.getInstance().clear();
            newView(layoutInflater, viewGroup, bundle);
        } catch (Throwable e) {
            Log.e(TAG, "onCreateView onload faile", e);
        }
        return this.mView;
    }

    public void onActivityCreated(@Nullable Bundle bundle) {
        super.onActivityCreated(bundle);
        if (this.mView != null && isAdded()) {
            b.onSetStatusBarColor(getActivity(), this.mView);
            initView(this.mView);
            initData();
        }
    }

    protected View newView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        return this.mView;
    }

    protected void initData() {
    }

    protected void initView(View view) {
    }

    protected void initBcSDK() {
        if (TtgSDK.sBcInitFlag == -1) {
            initFailHintLayout();
            if (this.mTtgLyLoading != null) {
                this.mTtgLyLoading.setVisibility(8);
            }
            this.mTtgLyFailLayout.setVisibility(0);
            this.mTtgTvSecondTitle.setText(R.string.ttg_bc_fail);
            this.mTtgTvTryAgain.setVisibility(8);
        }
    }

    protected void onBcSuccFlag() {
        if (TtgSDK.sBcInitFlag != -1) {
            initPageData();
        } else {
            initBcSDK();
        }
    }

    protected void initPageData() {
    }

    protected void initLoading() {
        if (this.mView != null) {
            this.mTtgLyLoading = (LinearLayout) this.mView.findViewById(R.id.ttg_show_ly_loading);
            this.mTtgIvLoading = (ImageView) this.mView.findViewById(R.id.ttg_iv_loading_img);
            this.mTtgIvLoading.setImageResource(R$drawable.ttg_sdk_loading);
            this.mAnimation = new RotateAnimation(0.0f, 360.0f, 1, 0.5f, 1, 0.5f);
            this.mAnimation.setDuration(1000);
            this.mAnimation.setInterpolator(new LinearInterpolator());
            this.mAnimation.setFillAfter(true);
            this.mAnimation.setRepeatCount(Integer.MAX_VALUE);
        }
    }

    protected void initFailHintLayout() {
        if (this.mView != null && this.mTtgLyFailLayout == null) {
            this.mTtgLyFailLayout = (LinearLayout) this.mView.findViewById(R.id.ttg_ly_fail_layout);
            this.mTtgTvFirstTitle = (TextView) this.mView.findViewById(R.id.ttg_tv_first_title);
            this.mTtgTvSecondTitle = (TextView) this.mView.findViewById(R.id.ttg_tv_second_title);
            this.mTtgTvTryAgain = (TextView) this.mView.findViewById(R.id.ttg_tv_try_again);
            q.onResetShapeThemeColor(this.mTtgTvTryAgain, 0, 0, TtgConfig.getInstance().getThemeColor());
            if (this.mTtgLyFailLayout != null) {
                this.mTtgLyFailLayout.setOnClickListener(this);
            }
            if (this.mTtgTvTryAgain != null) {
                this.mTtgTvTryAgain.setOnClickListener(this);
            }
        }
    }

    protected String onDataError(int i, boolean z) {
        if (!isAdded()) {
            return null;
        }
        hideLoading();
        if (z) {
            return q.showErrorHint(getActivity(), i);
        }
        initFailHintLayout();
        return q.showErrorHint(getActivity(), this.mTtgLyFailLayout, this.mTtgTvFirstTitle, this.mTtgTvSecondTitle, i, false);
    }

    protected void showLoading() {
        if (this.mTtgIvLoading != null) {
            this.mTtgLyLoading.setOnClickListener(this);
            this.mTtgLyLoading.setVisibility(0);
            if (this.mAnimation != null) {
                this.mTtgIvLoading.startAnimation(this.mAnimation);
            }
            this.mTtgIvLoading.setVisibility(0);
        }
        if (TtgSDK.sBcInitFlag == -1) {
            initBcSDK();
        }
    }

    protected void hideLoading() {
        if (this.mView != null && this.mTtgIvLoading != null && this.mTtgLyLoading != null) {
            this.mTtgLyLoading.setVisibility(8);
            this.mTtgIvLoading.setVisibility(8);
            this.mAnimation.cancel();
            if (TtgSDK.sBcInitFlag == -1) {
                initBcSDK();
            } else if (this.mTtgLyFailLayout != null) {
                this.mTtgLyFailLayout.setVisibility(8);
            }
        }
    }

    protected void setBarTitle(View view, TitleBar titleBar) {
        findTitleBarView(view);
        cn.tatagou.sdk.view.c.setTitleHeight(getActivity(), this.mRylToolBar, true);
        this.mRylToolBar.setOnClickListener(this);
        if (titleBar != null) {
            LayoutParams layoutParams;
            if (this.mTtgMyCartCircle != null && titleBar.isSmallCircleShown() && TtgTitleBar.getInstance().isMyShoppingIconShown()) {
                layoutParams = (LayoutParams) this.mTtgMyCartCircle.getLayoutParams();
                layoutParams.leftMargin = p.dip2px(getActivity(), (float) TtgTitleBar.getInstance().getSmallCircleXSize());
                layoutParams.topMargin = p.dip2px(getActivity(), (float) TtgTitleBar.getInstance().getSmallCircleYSize());
                this.mTtgMyCartCircle.setLayoutParams(layoutParams);
                this.mTtgMyCartCircle.setTextColor(TtgTitleBar.getInstance().getCircleColor());
                this.mTtgMyCartCircle.setVisibility(0);
            }
            if (titleBar.isRightIconShow()) {
                this.mTvRightIcon.setText(titleBar.getTvRightIconfontCode());
                this.mTvRightIcon.setVisibility(0);
                this.mRlRightIcon.setVisibility(0);
                this.mTvRightIcon.setOnClickListener(this);
                this.mRlRightIcon.setOnClickListener(this);
                layoutParams = (LayoutParams) this.mRlRightIcon.getLayoutParams();
                layoutParams.addRule(11, -1);
                this.mRlRightIcon.setLayoutParams(layoutParams);
                if (titleBar.getTvRightIconSize() > 0) {
                    this.mTvRightIcon.setTextSize((float) titleBar.getTvRightIconSize());
                }
            }
            if (titleBar.isCartIconShow()) {
                this.mTvMineIcon.setText(titleBar.getTvMineIconFontCode());
                this.mTvMineIcon.setVisibility(0);
                this.mTvMineIcon.setOnClickListener(this);
                if (titleBar.getTvMineIconSize() > 0) {
                    this.mTvMineIcon.setTextSize((float) titleBar.getTvMineIconSize());
                }
            }
            if (titleBar.isSearchIconShown()) {
                this.mTvSearchIcon.setText(titleBar.getTvSearchIconfontCode());
                this.mTvSearchIcon.setVisibility(0);
                this.mTvSearchIcon.setOnClickListener(this);
                if (titleBar.getTvSearchIconSize() > 0) {
                    this.mTvSearchIcon.setTextSize((float) titleBar.getTvSearchIconSize());
                }
            }
            if (titleBar.isSearchShown()) {
                this.mTvTitle.setVisibility(8);
                cn.tatagou.sdk.view.c.setSearchCenter(getActivity(), this.mTtgRlSearch, cn.tatagou.sdk.view.c.getId(this.mTvBackup), titleBar.isCartIconShow() ? cn.tatagou.sdk.view.c.getId(this.mTvMineIcon) : cn.tatagou.sdk.view.c.getId(this.mRlRightIcon), titleBar.isLeftIconShow(), TtgTitleBar.getInstance().isMyShoppingIconShown());
                q.onResetShapeThemeColor(this.mTtgRlSearch, 1, TtgTitleBar.getInstance().getSearchColor(), TtgTitleBar.getInstance().getSearchColor());
                this.mTtgRlSearch.setOnClickListener(this);
                this.mEdtSearch.setOnClickListener(this);
            } else {
                cn.tatagou.sdk.view.c.setTitleCenter(getActivity(), this.mTvTitle, cn.tatagou.sdk.view.c.getId(this.mTvBackup), titleBar.isLeftIconShow());
                this.mTvTitle.setText(titleBar.getTitle());
                this.mTtgRlSearch.setVisibility(8);
                if (titleBar.isSearchIconShown()) {
                    cn.tatagou.sdk.view.c.setSearchIconCenter(this.mTvSearchIcon, this.mTvMineIcon, this.mRlRightIcon, titleBar.isLeftIconShow(), getActivity());
                }
            }
            if (titleBar.isLeftIconShow()) {
                this.mTvBackup.setVisibility(0);
                if (titleBar.isTtgMain() && TtgTitleBar.getInstance().isTitleIconShown() && !TtgInterface.isTtgMainAct()) {
                    this.mTvBackup.setText(getString(R.string.ttg_icon_ttjx));
                    this.mTvBackup.setTextColor(TtgConfig.getInstance().getThemeColor());
                    this.mTvBackup.setPadding(p.dip2px(getActivity(), 12.0f), (int) getResources().getDimension(R$dimen.top_4dp), p.dip2px(getActivity(), 15.0f), 0);
                    return;
                }
                if (!p.isEmpty(TtgTitleBar.getInstance().getBackIcon())) {
                    this.mTvBackup.setText(TtgTitleBar.getInstance().getBackIcon());
                }
                this.mTvBackup.setTextSize((float) TtgTitleBar.getInstance().getBackIconSize());
                this.mTvBackup.setPadding(p.dip2px(getActivity(), (float) TtgTitleBar.getInstance().getBackIconLeftPadding()), 0, p.dip2px(getActivity(), (float) titleBar.getBackIconRightPadding()), 0);
                this.mTvBackup.setOnClickListener(this);
                if (!TtgTitleBar.getInstance().isTitleCenter() && !titleBar.isSearchShown()) {
                    this.mTvTitle.setOnClickListener(this);
                    return;
                }
                return;
            }
            this.mTvBackup.setVisibility(8);
        }
    }

    public void onClickListener(int i, boolean z) {
    }

    private void findTitleBarView(View view) {
        this.mRylToolBar = (RelativeLayout) view.findViewById(R.id.ttg_tb_bar);
        this.mTvSearchIcon = (TitleBarIconTextView) view.findViewById(R.id.ttg_searchicon);
        this.mTtgRlSearch = (RelativeLayout) view.findViewById(R.id.ttg_rl_search);
        this.mTvTitle = (TextView) view.findViewById(R.id.ttg_tv_title);
        this.mEdtSearch = (EditText) view.findViewById(R.id.ttg_edt_search);
        this.mTvBackup = (TextView) view.findViewById(R.id.ttg_tv_backup);
        this.mTvRightIcon = (TextView) view.findViewById(R.id.ttg_tv_right_icon);
        this.mRlRightIcon = (RelativeLayout) view.findViewById(R.id.ttg_rl_right_icon);
        this.mTvMineIcon = (TextView) view.findViewById(R.id.ttg_tv_mine_icon);
        this.mTtgMyCartCircle = (IconTextView) view.findViewById(R.id.ttg_mycart_circle);
    }

    protected void onTitleBarLeftIconClick() {
        if (isAdded()) {
            getActivity().finish();
        }
    }

    protected void onTitleBarRightIconClick() {
    }

    protected void onTitleBarClick() {
    }

    public void onResume() {
        super.onResume();
        i.a((Fragment) this).c();
    }

    protected void unbindDrawables(View view) {
        if (!(view == null || view.getBackground() == null)) {
            view.getBackground().setCallback(null);
        }
        if (view != null && (view instanceof ViewGroup) && !(view instanceof AdapterView)) {
            for (int i = 0; i < ((ViewGroup) view).getChildCount(); i++) {
                unbindDrawables(((ViewGroup) view).getChildAt(i));
            }
            ((ViewGroup) view).removeAllViews();
        }
    }

    public void onPause() {
        super.onPause();
        i.a((Fragment) this).b();
    }

    public void onDestroyView() {
        IUpdateViewManager.getInstance().unRegistIUpdateView(this.mUpdateViewList);
        super.onDestroyView();
    }

    public void onDestroy() {
        IUpdateViewManager.getInstance().unRegistIUpdateView(this.mUpdateViewList);
        this.mView = null;
        if (this.mTtgIvLoading != null) {
            this.mTtgIvLoading.clearAnimation();
        }
        super.onDestroy();
    }

    public void onRetryClick() {
        showLoading();
    }

    public void onSearchClick() {
    }

    public void onRightFirstIconClick() {
    }

    public void onTitleBack() {
        if (isAdded()) {
            getActivity().finish();
        }
    }

    public void onClick(View view) {
        int id = view.getId();
        if (id == R.id.ttg_tv_backup) {
            onTitleBarLeftIconClick();
        } else if (id == R.id.ttg_tv_title) {
            onTitleBack();
        } else if (id == R.id.ttg_tv_right_icon) {
            onTitleBarRightIconClick();
        } else if (id == R.id.ttg_tb_bar) {
            onTitleBarClick();
        } else if (id == R.id.ttg_tv_try_again) {
            onRetryClick();
        } else if (id == R.id.ttg_rl_search || id == R.id.ttg_edt_search) {
            onSearchClick();
        } else if (id == R.id.ttg_tv_mine_icon) {
            onRightFirstIconClick();
        } else if (id == R.id.ttg_searchicon) {
            onSearchIconClick();
        }
    }

    public void onSearchIconClick() {
    }
}
