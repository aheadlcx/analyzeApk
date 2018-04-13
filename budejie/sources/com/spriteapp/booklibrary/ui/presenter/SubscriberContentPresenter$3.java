package com.spriteapp.booklibrary.ui.presenter;

import com.spriteapp.booklibrary.base.Base;
import com.spriteapp.booklibrary.enumeration.ApiCodeEnum;
import com.spriteapp.booklibrary.model.response.BookDetailResponse;
import io.reactivex.Observer;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;

class SubscriberContentPresenter$3 implements Observer<Base<BookDetailResponse>> {
    final /* synthetic */ SubscriberContentPresenter this$0;

    SubscriberContentPresenter$3(SubscriberContentPresenter subscriberContentPresenter) {
        this.this$0 = subscriberContentPresenter;
    }

    public void onComplete() {
        if (SubscriberContentPresenter.access$000(this.this$0) != null) {
            SubscriberContentPresenter.access$000(this.this$0).disMissProgress();
        }
    }

    public void onSubscribe(@NonNull Disposable disposable) {
        SubscriberContentPresenter.access$102(this.this$0, disposable);
    }

    public void onError(Throwable th) {
        if (SubscriberContentPresenter.access$000(this.this$0) != null) {
            SubscriberContentPresenter.access$000(this.this$0).disMissProgress();
        }
    }

    public void onNext(Base<BookDetailResponse> base) {
        if (base.getCode() == ApiCodeEnum.SUCCESS.getValue() && SubscriberContentPresenter.access$000(this.this$0) != null) {
            SubscriberContentPresenter.access$000(this.this$0).setBookDetail((BookDetailResponse) base.getData());
        }
    }
}
