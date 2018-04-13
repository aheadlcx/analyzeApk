package com.budejie.www.i.a;

import com.budejie.www.activity.label.response.CommonInfoBean;
import io.reactivex.Observer;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;

class a$4 implements Observer<CommonInfoBean> {
    final /* synthetic */ a a;

    a$4(a aVar) {
        this.a = aVar;
    }

    public /* synthetic */ void onNext(@NonNull Object obj) {
        a((CommonInfoBean) obj);
    }

    public void onSubscribe(@NonNull Disposable disposable) {
        a.c(this.a, disposable);
    }

    public void a(@NonNull CommonInfoBean commonInfoBean) {
        if (a.a(this.a) != null) {
            a.a(this.a).a(commonInfoBean);
        }
    }

    public void onError(@NonNull Throwable th) {
        if (a.d(this.a) != null) {
            a.d(this.a).dispose();
        }
    }

    public void onComplete() {
        if (a.d(this.a) != null) {
            a.d(this.a).dispose();
        }
    }
}
