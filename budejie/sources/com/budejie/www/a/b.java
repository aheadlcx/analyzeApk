package com.budejie.www.a;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Function;
import java.io.IOException;
import java.util.concurrent.TimeUnit;
import okhttp3.aa;
import okhttp3.e;
import okhttp3.f;
import okhttp3.w;
import retrofit2.adapter.rxjava2.g;
import retrofit2.m;

public class b {
    private static w c;
    private static b d;
    public a a;
    public a b;

    private enum a {
        D_API,
        S_API
    }

    private static class b {
        private static b a = new b("http://d.api.budejie.com", a.D_API);
        private static b b = new b("http://s.budejie.com", a.S_API);
    }

    private b() {
    }

    private b(String str, a aVar) {
        a(new retrofit2.m.a().a(str).a(g.a()).a(retrofit2.a.a.a.a()).a(c()).a(), aVar);
    }

    private void a(m mVar, a aVar) {
        switch (aVar) {
            case D_API:
                this.a = (a) mVar.a(a.class);
                return;
            case S_API:
                this.b = (a) mVar.a(a.class);
                return;
            default:
                return;
        }
    }

    public static b a() {
        if (d == null) {
            d = new b();
        }
        return d;
    }

    public static b b() {
        return b.a;
    }

    private static w c() {
        if (c == null) {
            c = new okhttp3.w.a().a(new com.budejie.www.a.b.a()).a(new com.budejie.www.a.b.b()).a(8000, TimeUnit.MILLISECONDS).a();
        }
        return c;
    }

    public e a(String str, final com.budejie.www.a.a.a aVar) {
        e a = c().a(new okhttp3.y.a().a(str).a().b());
        a.a(new f(this) {
            final /* synthetic */ b b;

            public void onFailure(final e eVar, final IOException iOException) {
                Observable.just(Integer.valueOf(1)).map(new Function<Integer, Boolean>(this) {
                    final /* synthetic */ AnonymousClass1 a;

                    {
                        this.a = r1;
                    }

                    public /* synthetic */ Object apply(@NonNull Object obj) throws Exception {
                        return a((Integer) obj);
                    }

                    public Boolean a(@NonNull Integer num) throws Exception {
                        return Boolean.valueOf(true);
                    }
                }).observeOn(AndroidSchedulers.mainThread()).subscribe(new Observer<Boolean>(this) {
                    Disposable a;
                    final /* synthetic */ AnonymousClass1 d;

                    public /* synthetic */ void onNext(@NonNull Object obj) {
                        a((Boolean) obj);
                    }

                    public void onSubscribe(@NonNull Disposable disposable) {
                        this.a = disposable;
                    }

                    public void a(@NonNull Boolean bool) {
                        if (aVar != null) {
                            aVar.a(eVar, iOException);
                        }
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
                });
            }

            public void onResponse(e eVar, final aa aaVar) throws IOException {
                Observable.just(Integer.valueOf(1)).map(new Function<Integer, String>(this) {
                    final /* synthetic */ AnonymousClass1 b;

                    public /* synthetic */ Object apply(@NonNull Object obj) throws Exception {
                        return a((Integer) obj);
                    }

                    public String a(@NonNull Integer num) throws Exception {
                        if (aaVar == null || aaVar.h() == null) {
                            return null;
                        }
                        return aaVar.h().f();
                    }
                }).observeOn(AndroidSchedulers.mainThread()).subscribe(new Observer<String>(this) {
                    Disposable a;
                    final /* synthetic */ AnonymousClass1 b;

                    {
                        this.b = r1;
                    }

                    public /* synthetic */ void onNext(@NonNull Object obj) {
                        a((String) obj);
                    }

                    public void onSubscribe(@NonNull Disposable disposable) {
                        this.a = disposable;
                    }

                    public void a(@NonNull String str) {
                        if (aVar != null) {
                            aVar.a(str);
                        }
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
                });
            }
        });
        return a;
    }
}
