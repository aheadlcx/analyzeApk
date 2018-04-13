package com.spriteapp.booklibrary.ui.presenter;

import com.spriteapp.booklibrary.a.a;
import com.spriteapp.booklibrary.a.f;
import com.spriteapp.booklibrary.base.BasePresenter;
import com.spriteapp.booklibrary.ui.view.SubscriberContentView;
import com.spriteapp.booklibrary.util.AppUtil;
import com.spriteapp.booklibrary.util.NetworkUtil;
import com.spriteapp.booklibrary.util.SharedPreferencesUtil;
import com.spriteapp.booklibrary.util.ToastUtil;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class SubscriberContentPresenter implements BasePresenter<SubscriberContentView> {
    private Disposable mDisposable;
    private SubscriberContentView mView;

    public void attachView(SubscriberContentView subscriberContentView) {
        this.mView = subscriberContentView;
    }

    public void detachView() {
        this.mView = null;
        if (this.mDisposable != null) {
            this.mDisposable.dispose();
            this.mDisposable = null;
        }
    }

    public void getContent(int i, int i2) {
        getContent(i, i2, SharedPreferencesUtil.getInstance().getInt("hua_xi_book_auto_sub"));
    }

    public void getContent(int i, int i2, int i3) {
        getContent(i, i2, i3, true);
    }

    public void getContent(int i, int i2, int i3, boolean z) {
        if (NetworkUtil.isAvailable(this.mView.getMyContext())) {
            if (z && this.mView != null) {
                this.mView.showNetWorkProgress();
            }
            a.a().a.a(i, i2, i3).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new SubscriberContentPresenter$1(this));
            return;
        }
        ToastUtil.showSingleToast(f.please_check_network_info);
    }

    public void getChapter(int i) {
        getChapter(i, true);
    }

    public void getChapter(int i, boolean z) {
        if (AppUtil.isNetAvailable(this.mView.getMyContext())) {
            if (z) {
                this.mView.showNetWorkProgress();
            }
            a.a().a.b(i).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new SubscriberContentPresenter$2(this));
        }
    }

    public void getBookDetail(int i) {
        getBookDetail(i, true);
    }

    public void getBookDetail(int i, boolean z) {
        if (AppUtil.isNetAvailable(this.mView.getMyContext())) {
            if (z) {
                this.mView.showNetWorkProgress();
            }
            a.a().a.a(i).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new SubscriberContentPresenter$3(this));
        }
    }
}
