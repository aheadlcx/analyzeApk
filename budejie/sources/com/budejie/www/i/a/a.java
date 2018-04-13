package com.budejie.www.i.a;

import com.budejie.www.a.b;
import com.budejie.www.http.j;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class a {
    private com.budejie.www.i.b.a a;
    private Disposable b;
    private Disposable c;
    private Disposable d;

    public void a(com.budejie.www.i.b.a aVar) {
        this.a = aVar;
    }

    public void a(String str, String str2, String str3) {
        if (this.a != null) {
            this.a.a_();
            b.b().a.a(str, str2, str3).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new a$1(this));
        }
    }

    public void b(String str, String str2, String str3) {
        if (this.a != null) {
            this.a.a_();
            b.b().a.b(str, str2, str3).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new a$2(this));
        }
    }

    public void a(String str) {
        if (this.a != null) {
            b.a().a(j.o(str), new a$3(this));
        }
    }

    public void c(String str, String str2, String str3) {
        if (this.a != null) {
            b.b().a.d(str, str2, str3).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new a$4(this));
        }
    }
}
