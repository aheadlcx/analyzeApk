package com.budejie.www.activity.posts;

import android.text.TextUtils;
import com.budejie.www.R;
import com.budejie.www.adapter.e;
import com.budejie.www.bean.ListItemObject;
import com.budejie.www.goddubbing.c.a;
import com.budejie.www.util.aa;
import io.reactivex.Observer;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;
import java.util.List;

class b$8 implements Observer<List<ListItemObject>> {
    Disposable a;
    final /* synthetic */ b b;

    b$8(b bVar) {
        this.b = bVar;
    }

    public /* synthetic */ void onNext(Object obj) {
        a((List) obj);
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

    public void a(List<ListItemObject> list) {
        long j = 0;
        b.c(this.b, a.a(b.m(this.b)));
        b bVar = this.b;
        if (!b.n(this.b)) {
            j = this.b.c.getLong("np_" + b.l(this.b).getKey(), 0);
        }
        b.a(bVar, j);
        if (b.h(this.b) == null) {
            b.a(this.b, new e(b.b(this.b), b.m(this.b)));
        }
        b.o(this.b);
        if (b.n(this.b)) {
            b.b(this.b, R.color.new_main_background_color);
            b.g(this.b).c();
        }
        b.p(this.b);
    }
}
