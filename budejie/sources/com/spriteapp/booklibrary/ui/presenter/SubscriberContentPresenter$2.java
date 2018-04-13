package com.spriteapp.booklibrary.ui.presenter;

import com.spriteapp.booklibrary.base.Base;
import com.spriteapp.booklibrary.enumeration.ApiCodeEnum;
import com.spriteapp.booklibrary.model.response.BookChapterResponse;
import io.reactivex.Observer;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;
import java.util.List;

class SubscriberContentPresenter$2 implements Observer<Base<List<BookChapterResponse>>> {
    final /* synthetic */ SubscriberContentPresenter this$0;

    SubscriberContentPresenter$2(SubscriberContentPresenter subscriberContentPresenter) {
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

    public void onNext(Base<List<BookChapterResponse>> base) {
        if (base.getCode() == ApiCodeEnum.SUCCESS.getValue() && SubscriberContentPresenter.access$000(this.this$0) != null) {
            SubscriberContentPresenter.access$000(this.this$0).setChapter((List) base.getData());
        }
    }
}
