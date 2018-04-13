package com.budejie.www.i.a;

import com.budejie.www.activity.label.response.CommonInfoBean;
import io.reactivex.Observer;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;

class a$1 implements Observer<CommonInfoBean> {
    final /* synthetic */ a a;

    a$1(a aVar) {
        this.a = aVar;
    }

    public /* synthetic */ void onNext(@NonNull Object obj) {
        a((CommonInfoBean) obj);
    }

    public void onSubscribe(@NonNull Disposable disposable) {
        a.a(this.a, disposable);
    }

    public void a(@NonNull CommonInfoBean commonInfoBean) {
        if (a.a(this.a) != null) {
            a.a(this.a).b(commonInfoBean);
        }
    }

    public void onError(@NonNull Throwable th) {
        if (a.b(this.a) != null) {
            a.b(this.a).dispose();
        }
        if (a.a(this.a) != null) {
            a.a(this.a).g();
        }
    }

    public void onComplete() {
        if (a.b(this.a) != null) {
            a.b(this.a).dispose();
        }
        if (a.a(this.a) != null) {
            a.a(this.a).g();
        }
    }
}
