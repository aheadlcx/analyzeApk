package com.spriteapp.booklibrary.ui.presenter;

import com.spriteapp.booklibrary.a.a;
import com.spriteapp.booklibrary.base.BasePresenter;
import com.spriteapp.booklibrary.ui.view.WebViewView;
import com.spriteapp.booklibrary.util.AppUtil;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class WebViewPresenter implements BasePresenter<WebViewView> {
    private Disposable mDisposable;
    private WebViewView mView;

    public void attachView(WebViewView webViewView) {
        this.mView = webViewView;
    }

    public void detachView() {
        this.mView = null;
        if (this.mDisposable != null) {
            this.mDisposable.dispose();
            this.mDisposable = null;
        }
    }

    public void requestAliPay(String str) {
        if (AppUtil.isNetAvailable(this.mView.getMyContext())) {
            this.mView.showNetWorkProgress();
            a.a().a.a(str).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new WebViewPresenter$1(this));
        }
    }
}
