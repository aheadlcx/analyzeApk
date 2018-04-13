package com.spriteapp.booklibrary.ui.presenter;

import com.spriteapp.booklibrary.base.Base;
import com.spriteapp.booklibrary.enumeration.ApiCodeEnum;
import com.spriteapp.booklibrary.model.response.PayResponse;
import io.reactivex.Observer;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;

class WebViewPresenter$1 implements Observer<Base<PayResponse>> {
    final /* synthetic */ WebViewPresenter this$0;

    WebViewPresenter$1(WebViewPresenter webViewPresenter) {
        this.this$0 = webViewPresenter;
    }

    public void onComplete() {
        if (WebViewPresenter.access$000(this.this$0) != null) {
            WebViewPresenter.access$000(this.this$0).disMissProgress();
        }
    }

    public void onSubscribe(@NonNull Disposable disposable) {
        WebViewPresenter.access$102(this.this$0, disposable);
    }

    public void onError(Throwable th) {
        if (WebViewPresenter.access$000(this.this$0) != null) {
            WebViewPresenter.access$000(this.this$0).onError(th);
        }
    }

    public void onNext(Base<PayResponse> base) {
        if (base.getCode() == ApiCodeEnum.SUCCESS.getValue() && WebViewPresenter.access$000(this.this$0) != null) {
            WebViewPresenter.access$000(this.this$0).setAliPayResult((PayResponse) base.getData());
        }
    }
}
