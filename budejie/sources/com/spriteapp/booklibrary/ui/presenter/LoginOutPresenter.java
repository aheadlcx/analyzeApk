package com.spriteapp.booklibrary.ui.presenter;

import com.spriteapp.booklibrary.a.a;
import com.spriteapp.booklibrary.base.BasePresenter;
import com.spriteapp.booklibrary.ui.view.LoginOutView;
import com.spriteapp.booklibrary.util.AppUtil;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class LoginOutPresenter implements BasePresenter<LoginOutView> {
    private Disposable mDisposable;
    private LoginOutView mView;

    public void attachView(LoginOutView loginOutView) {
        this.mView = loginOutView;
    }

    public void detachView() {
        this.mView = null;
        if (this.mDisposable != null) {
            this.mDisposable.dispose();
            this.mDisposable = null;
        }
    }

    public void loginOut() {
        if (AppUtil.isNetAvailable(this.mView.getMyContext())) {
            this.mView.showNetWorkProgress();
            a.a().a.b(AppUtil.getHeaderSnValue(), AppUtil.getToken()).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new LoginOutPresenter$1(this));
        }
    }
}
