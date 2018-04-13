package com.budejie.www.activity.posts;

import android.text.TextUtils;
import com.budejie.www.util.aa;
import io.reactivex.Observer;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;

class b$10 implements Observer<Integer> {
    Disposable a;
    final /* synthetic */ b b;

    b$10(b bVar) {
        this.b = bVar;
    }

    public /* synthetic */ void onNext(Object obj) {
        a((Integer) obj);
    }

    public void onComplete() {
        this.a.dispose();
    }

    public void onError(Throwable th) {
        this.a.dispose();
        if (!TextUtils.isEmpty(th.getMessage())) {
            aa.e("StaggeredListFragment", th.getMessage());
        }
    }

    public void onSubscribe(@NonNull Disposable disposable) {
        this.a = disposable;
    }

    public void a(Integer num) {
        b.g(this.b).scrollToPosition(num.intValue());
        b.g(this.b).setNeedRefreshSpan(true);
    }
}
