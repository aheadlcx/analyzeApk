package com.budejie.www.util;

import android.content.SharedPreferences;
import com.budejie.www.bean.ListItemObject;
import com.budejie.www.bean.MyCollectItem;
import com.budejie.www.c.d;
import com.budejie.www.f.a;
import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;
import java.util.Iterator;

class m$2 implements a {
    final /* synthetic */ SharedPreferences a;
    final /* synthetic */ d b;
    final /* synthetic */ String c;

    m$2(SharedPreferences sharedPreferences, d dVar, String str) {
        this.a = sharedPreferences;
        this.b = dVar;
        this.c = str;
    }

    public void a(int i, final String str) {
        Observable.just(Integer.valueOf(1)).map(new Function<Integer, Boolean>(this) {
            final /* synthetic */ m$2 b;

            public /* synthetic */ Object apply(@NonNull Object obj) throws Exception {
                return a((Integer) obj);
            }

            public Boolean a(@NonNull Integer num) throws Exception {
                MyCollectItem m = z.m(str);
                if (m == null) {
                    return Boolean.valueOf(true);
                }
                this.b.a.edit().putString("collect_version", m.getVersion()).apply();
                Object addList = m.getAddList();
                if (!com.budejie.www.goddubbing.c.a.a(addList)) {
                    Iterator it = addList.iterator();
                    while (it.hasNext()) {
                        ListItemObject listItemObject = (ListItemObject) it.next();
                        if (listItemObject != null) {
                            this.b.b.a(listItemObject, this.b.c, "yes");
                        }
                    }
                }
                return Boolean.valueOf(true);
            }
        }).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new Observer<Boolean>(this) {
            Disposable a;
            final /* synthetic */ m$2 b;

            {
                this.b = r1;
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
        });
    }

    public void a(int i) {
    }
}
