package com.spriteapp.booklibrary.ui.presenter;

import com.spriteapp.booklibrary.base.Base;
import com.spriteapp.booklibrary.enumeration.ApiCodeEnum;
import com.spriteapp.booklibrary.enumeration.ChapterEnum;
import com.spriteapp.booklibrary.model.response.SubscriberContent;
import com.spriteapp.booklibrary.util.ToastUtil;
import io.reactivex.Observer;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;

class SubscriberContentPresenter$1 implements Observer<Base<SubscriberContent>> {
    final /* synthetic */ SubscriberContentPresenter this$0;

    SubscriberContentPresenter$1(SubscriberContentPresenter subscriberContentPresenter) {
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

    public void onNext(Base<SubscriberContent> base) {
        if (SubscriberContentPresenter.access$000(this.this$0) != null) {
            int code = base.getCode();
            if (code == ApiCodeEnum.SUCCESS.getValue() || code == ChapterEnum.BALANCE_SHORT.getCode() || code == ChapterEnum.UN_SUBSCRIBER.getCode()) {
                SubscriberContentPresenter.access$000(this.this$0).setData(base);
            } else if (code == ChapterEnum.UN_LOGIN.getCode() || code == ChapterEnum.USER_UN_LOGIN.getCode()) {
                SubscriberContentPresenter.access$000(this.this$0).toChannelLogin();
            } else {
                ToastUtil.showSingleToast(base.getMessage());
            }
        }
    }
}
