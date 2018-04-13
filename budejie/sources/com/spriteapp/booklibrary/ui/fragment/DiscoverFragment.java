package com.spriteapp.booklibrary.ui.fragment;

import com.spriteapp.booklibrary.a.d;
import com.spriteapp.booklibrary.a.e;
import com.spriteapp.booklibrary.base.BaseFragment;
import com.spriteapp.booklibrary.util.AppUtil;
import com.spriteapp.booklibrary.widget.ReaderWebView;

public class DiscoverFragment extends BaseFragment {
    private ReaderWebView mWebView;

    public int getLayoutResId() {
        return e.book_readerfragment_discover;
    }

    public void initData() {
        if (AppUtil.isNetAvailable(getContext())) {
            this.mWebView.loadPage("http://s.hxdrive.net/book_weekly");
        }
    }

    public void configViews() {
    }

    public void findViewId() {
        this.mWebView = (ReaderWebView) this.mParentView.findViewById(d.book_reader_discover_web_view);
    }

    protected void lazyLoad() {
        if (!this.mHasLoadedOnce && this.isPrepared) {
            this.mHasLoadedOnce = true;
            initData();
        }
    }
}
