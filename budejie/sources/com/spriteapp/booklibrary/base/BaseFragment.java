package com.spriteapp.booklibrary.base;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.spriteapp.booklibrary.widget.loading.CustomDialog;

public abstract class BaseFragment extends Fragment {
    private static final String TAG = "BaseFragment";
    private CustomDialog dialog;
    protected boolean isPrepared = false;
    protected boolean isVisible;
    protected FragmentActivity mActivity;
    protected Context mContext;
    protected boolean mHasLoadedOnce = false;
    protected View mParentView;

    public abstract void configViews();

    public abstract void findViewId();

    public abstract int getLayoutResId();

    public abstract void initData();

    protected abstract void lazyLoad();

    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        this.mParentView = layoutInflater.inflate(getLayoutResId(), viewGroup, false);
        this.mContext = getContext();
        this.isPrepared = true;
        return this.mParentView;
    }

    public void onViewCreated(View view, @Nullable Bundle bundle) {
        super.onViewCreated(view, bundle);
        findViewId();
        lazyLoad();
        configViews();
    }

    public void setUserVisibleHint(boolean z) {
        super.setUserVisibleHint(z);
        this.isVisible = getUserVisibleHint();
        if (getUserVisibleHint()) {
            lazyLoad();
        }
    }

    public void showDialog() {
        if (this.dialog == null) {
            this.dialog = CustomDialog.instance(getActivity());
            this.dialog.setCancelable(true);
        }
        this.dialog.show();
    }

    public void dismissDialog() {
        if (this.dialog != null && this.dialog.isShowing()) {
            this.dialog.dismiss();
            this.dialog = null;
        }
    }
}
