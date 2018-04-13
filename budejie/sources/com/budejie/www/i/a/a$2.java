package com.budejie.www.i.a;

import com.budejie.www.activity.label.response.ToTopResponse;
import io.reactivex.Observer;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;

class a$2 implements Observer<ToTopResponse> {
    final /* synthetic */ a a;

    a$2(a aVar) {
        this.a = aVar;
    }

    public /* synthetic */ void onNext(@NonNull Object obj) {
        a((ToTopResponse) obj);
    }

    public void onSubscribe(@NonNull Disposable disposable) {
        a.b(this.a, disposable);
    }

    public void a(@NonNull ToTopResponse toTopResponse) {
        if (a.a(this.a) != null) {
            a.a(this.a).a(toTopResponse);
        }
    }

    public void onError(@NonNull Throwable th) {
        if (a.c(this.a) != null) {
            a.c(this.a).dispose();
        }
        if (a.a(this.a) != null) {
            a.a(this.a).g();
        }
    }

    public void onComplete() {
        if (a.c(this.a) != null) {
            a.c(this.a).dispose();
        }
        if (a.a(this.a) != null) {
            a.a(this.a).g();
        }
    }
}
