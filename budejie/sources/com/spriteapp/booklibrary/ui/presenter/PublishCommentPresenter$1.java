package com.spriteapp.booklibrary.ui.presenter;

import com.spriteapp.booklibrary.base.Base;
import com.spriteapp.booklibrary.enumeration.ApiCodeEnum;
import com.spriteapp.booklibrary.util.ToastUtil;
import io.reactivex.Observer;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;

class PublishCommentPresenter$1 implements Observer<Base<Void>> {
    final /* synthetic */ PublishCommentPresenter this$0;

    PublishCommentPresenter$1(PublishCommentPresenter publishCommentPresenter) {
        this.this$0 = publishCommentPresenter;
    }

    public void onComplete() {
        if (PublishCommentPresenter.access$000(this.this$0) != null) {
            PublishCommentPresenter.access$000(this.this$0).disMissProgress();
        }
    }

    public void onSubscribe(@NonNull Disposable disposable) {
        PublishCommentPresenter.access$102(this.this$0, disposable);
    }

    public void onError(Throwable th) {
        if (PublishCommentPresenter.access$000(this.this$0) != null) {
            PublishCommentPresenter.access$000(this.this$0).onError(th);
        }
    }

    public void onNext(Base<Void> base) {
        if (base.getCode() == ApiCodeEnum.SUCCESS.getValue()) {
            ToastUtil.showSingleToast("发表成功");
            PublishCommentPresenter.access$000(this.this$0).setData(base);
            return;
        }
        ToastUtil.showSingleToast(base.getMessage());
    }
}
