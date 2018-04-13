package com.budejie.www.i.a;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class b {
    private com.budejie.www.i.b.b a;
    private Disposable b;

    public void a(com.budejie.www.i.b.b bVar) {
        this.a = bVar;
    }

    public void a() {
        if (this.b != null) {
            this.b.dispose();
        }
        this.a = null;
    }

    public void a(String str, String str2, String str3) {
        if (this.a != null) {
            this.a.a_();
            com.budejie.www.a.b.b().a.c(str, str2, str3).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new b$1(this));
        }
    }
}
