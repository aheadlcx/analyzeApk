package com.spriteapp.booklibrary.ui.fragment;

import android.webkit.WebChromeClient;
import com.spriteapp.booklibrary.a.d;
import com.spriteapp.booklibrary.a.e;
import com.spriteapp.booklibrary.base.BaseFragment;
import com.spriteapp.booklibrary.enumeration.UpdaterPayEnum;
import com.spriteapp.booklibrary.util.AppUtil;
import com.spriteapp.booklibrary.widget.ReaderWebView;
import de.greenrobot.event.EventBus;

public class MeFragment extends BaseFragment {
    WebChromeClient mChromeClient = new MeFragment$1(this);
    private ReaderWebView mWebView;

    public int getLayoutResId() {
        return e.book_reader_fragment_me;
    }

    public void initData() {
        EventBus.getDefault().register(this);
        this.mWebView.setWebChromeClient(this.mChromeClient);
        if (AppUtil.isNetAvailable(getContext())) {
            this.mWebView.loadPage("http://s.hxdrive.net/user_index");
        }
    }

    public void configViews() {
    }

    public void findViewId() {
        this.mWebView = (ReaderWebView) this.mParentView.findViewById(d.book_reader_me_web_view);
    }

    protected void lazyLoad() {
        if (!this.mHasLoadedOnce && this.isPrepared) {
            this.mHasLoadedOnce = true;
            initData();
        }
    }

    public void onEventMainThread(UpdaterPayEnum updaterPayEnum) {
        if (this.mWebView == null) {
            return;
        }
        if (updaterPayEnum == UpdaterPayEnum.UPDATE_LOGIN_INFO || updaterPayEnum == UpdaterPayEnum.UPDATE_LOGIN_OUT) {
            this.mWebView.loadPage("http://s.hxdrive.net/user_index");
        } else if (updaterPayEnum == UpdaterPayEnum.UPDATE_PAY_RESULT) {
            this.mWebView.reload();
        }
    }

    public void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }
}
