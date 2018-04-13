package com.spriteapp.booklibrary.ui.presenter;

import com.spriteapp.booklibrary.a.a;
import com.spriteapp.booklibrary.base.BasePresenter;
import com.spriteapp.booklibrary.ui.view.PublishCommentView;
import com.spriteapp.booklibrary.util.AppUtil;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class PublishCommentPresenter implements BasePresenter<PublishCommentView> {
    private Disposable mDisposable;
    private PublishCommentView mView;

    public void attachView(PublishCommentView publishCommentView) {
        this.mView = publishCommentView;
    }

    public void detachView() {
        this.mView = null;
        if (this.mDisposable != null) {
            this.mDisposable.dispose();
            this.mDisposable = null;
        }
    }

    public void sendComment(int i, String str) {
        if (AppUtil.isNetAvailable(this.mView.getMyContext())) {
            this.mView.showNetWorkProgress();
            a.a().a.b(i, str).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new PublishCommentPresenter$1(this));
        }
    }
}
