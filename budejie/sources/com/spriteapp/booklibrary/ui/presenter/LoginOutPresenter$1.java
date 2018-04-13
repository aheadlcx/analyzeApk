package com.spriteapp.booklibrary.ui.presenter;

import com.spriteapp.booklibrary.base.Base;
import com.spriteapp.booklibrary.enumeration.ApiCodeEnum;
import io.reactivex.Observer;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;

class LoginOutPresenter$1 implements Observer<Base<Void>> {
    final /* synthetic */ LoginOutPresenter this$0;

    LoginOutPresenter$1(LoginOutPresenter loginOutPresenter) {
        this.this$0 = loginOutPresenter;
    }

    public void onComplete() {
        if (LoginOutPresenter.access$000(this.this$0) != null) {
            LoginOutPresenter.access$000(this.this$0).disMissProgress();
        }
    }

    public void onSubscribe(@NonNull Disposable disposable) {
        LoginOutPresenter.access$102(this.this$0, disposable);
    }

    public void onError(Throwable th) {
        if (LoginOutPresenter.access$000(this.this$0) != null) {
            LoginOutPresenter.access$000(this.this$0).onError(th);
        }
    }

    public void onNext(Base<Void> base) {
        if (base.getCode() == ApiCodeEnum.SUCCESS.getValue()) {
            LoginOutPresenter.access$000(this.this$0).setData(base);
        }
    }
}
