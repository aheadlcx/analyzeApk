package com.spriteapp.booklibrary.ui.fragment;

import android.support.v4.widget.SwipeRefreshLayout;
import android.webkit.WebChromeClient;
import com.spriteapp.booklibrary.a.d;
import com.spriteapp.booklibrary.a.e;
import com.spriteapp.booklibrary.base.BaseFragment;
import com.spriteapp.booklibrary.util.AppUtil;
import com.spriteapp.booklibrary.widget.ReaderWebView;

public class BookstoreFragment extends BaseFragment {
    private static final String TAG = "BookstoreFragment";
    WebChromeClient mChromeClient = new BookstoreFragment$2(this);
    private SwipeRefreshLayout mSwipeLayout;
    private ReaderWebView mWebView;

    public int getLayoutResId() {
        return e.book_reader_fragment_bookstore;
    }

    public void initData() {
        this.mWebView.setWebChromeClient(this.mChromeClient);
        setAttrsForSwipeLayout();
        if (AppUtil.isNetAvailable(getContext())) {
            this.mWebView.loadPage("http://s.hxdrive.net/book_store");
        }
    }

    private void setAttrsForSwipeLayout() {
        this.mSwipeLayout.setEnabled(false);
        this.mSwipeLayout.setOnRefreshListener(new BookstoreFragment$1(this));
    }

    public void configViews() {
    }

    public void findViewId() {
        this.mWebView = (ReaderWebView) this.mParentView.findViewById(d.book_reader_book_store_web_view);
        this.mSwipeLayout = (SwipeRefreshLayout) this.mParentView.findViewById(d.book_reader_swipe_layout);
    }

    protected void lazyLoad() {
        if (!this.mHasLoadedOnce && this.isPrepared) {
            this.mHasLoadedOnce = true;
            initData();
        }
    }
}
