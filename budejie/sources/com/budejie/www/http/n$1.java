package com.budejie.www.http;

import io.reactivex.Observer;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;

class n$1 implements Observer<Boolean> {
    Disposable a;

    n$1() {
    }

    public /* synthetic */ void onNext(@NonNull Object obj) {
        a((Boolean) obj);
    }

    public void onSubscribe(@NonNull Disposable disposable) {
        this.a = disposable;
    }

    public void a(@NonNull Boolean bool) {
    }

    public void onError(@NonNull Throwable th) {
        if (this.a != null) {
            this.a.dispose();
        }
    }

    public void onComplete() {
        if (this.a != null) {
            this.a.dispose();
        }
    }
}
