package com.budejie.www.i.a;

import com.budejie.www.activity.label.response.ApplyModeratorResponse;
import io.reactivex.Observer;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;

class b$1 implements Observer<ApplyModeratorResponse> {
    final /* synthetic */ b a;

    b$1(b bVar) {
        this.a = bVar;
    }

    public /* synthetic */ void onNext(@NonNull Object obj) {
        a((ApplyModeratorResponse) obj);
    }

    public void onSubscribe(@NonNull Disposable disposable) {
        b.a(this.a, disposable);
    }

    public void a(@NonNull ApplyModeratorResponse applyModeratorResponse) {
        if (b.a(this.a) != null && applyModeratorResponse != null) {
            b.a(this.a).a(applyModeratorResponse);
        }
    }

    public void onError(@NonNull Throwable th) {
        if (b.b(this.a) != null) {
            b.b(this.a).dispose();
        }
        b.a(this.a).g();
    }

    public void onComplete() {
        if (b.b(this.a) != null) {
            b.b(this.a).dispose();
        }
        b.a(this.a).g();
    }
}
