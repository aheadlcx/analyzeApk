package com.budejie.www.activity.posts;

import android.text.TextUtils;
import com.budejie.www.R;
import com.budejie.www.bean.ListInfo;
import com.budejie.www.bean.ListItemObject;
import com.budejie.www.util.aa;
import com.budejie.www.util.ap;
import com.budejie.www.util.d;
import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;
import java.util.ArrayList;
import net.tsz.afinal.a.a;

class b$4 extends a<String> {
    final /* synthetic */ b a;

    b$4(b bVar) {
        this.a = bVar;
    }

    public /* synthetic */ void onSuccess(Object obj) {
        a((String) obj);
    }

    public void onFailure(Throwable th, int i, String str) {
        if (!this.a.isDetached()) {
            b.g(this.a).a(false);
            b.t(this.a);
        }
    }

    public void a(String str) {
        if (!this.a.isDetached()) {
            if (b.F(this.a) == null) {
                b.b(this.a, new String[1]);
            }
            b.F(this.a)[0] = str;
            b.b(this.a, new Observer<ArrayList<ListItemObject>>(this) {
                Disposable a;
                final /* synthetic */ b$4 b;

                {
                    this.b = r1;
                }

                public /* synthetic */ void onNext(Object obj) {
                    a((ArrayList) obj);
                }

                public void onComplete() {
                    this.a.dispose();
                }

                public void onError(Throwable th) {
                    b.g(this.b.a).a(false);
                    b.t(this.b.a);
                    this.a.dispose();
                    if (!TextUtils.isEmpty(th.getMessage())) {
                        aa.e("StaggeredListFragment", th.getMessage());
                    }
                }

                public void onSubscribe(@NonNull Disposable disposable) {
                    this.a = disposable;
                }

                public void a(ArrayList<ListItemObject> arrayList) {
                    b.g(this.b.a).d();
                    b.t(this.b.a);
                    b.g(this.b.a).setPullRefreshEnabled(true);
                    b.g(this.b.a).setLoadingMoreEnabled(true);
                    if (com.budejie.www.goddubbing.c.a.a(arrayList)) {
                        d.b(b.b(this.b.a), b.c(this.b.a), b.b(this.b.a).getString(R.string.not_new_data_click));
                    } else {
                        int size = arrayList.size();
                        String string = b.b(this.b.a).getString(R.string.recommend_tip_click);
                        d.b(b.b(this.b.a), b.c(this.b.a), String.format(string, new Object[]{"" + size}));
                    }
                    if (!com.budejie.www.goddubbing.c.a.a(b.w(this.b.a))) {
                        b.m(this.b.a).clear();
                        arrayList.addAll(b.w(this.b.a));
                        b.m(this.b.a).addAll(arrayList);
                        b.h(this.b.a).notifyDataSetChanged();
                    }
                }
            });
            Observable.fromArray(b.F(this.a)).map(new Function<String, ArrayList<ListItemObject>>(this) {
                final /* synthetic */ b$4 a;

                {
                    this.a = r1;
                }

                public /* synthetic */ Object apply(Object obj) throws Exception {
                    return a((String) obj);
                }

                public ArrayList<ListItemObject> a(String str) {
                    ListInfo a = com.budejie.www.j.a.a(str);
                    if (a == null) {
                        return null;
                    }
                    b.b(this.a.a, a.np);
                    if (b.z(this.a.a) != 0) {
                        b.H(this.a.a);
                    }
                    ArrayList<ListItemObject> a2 = com.budejie.www.j.a.a(this.a.a.getActivity(), str);
                    ap.a(a2);
                    return a2;
                }
            }).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(b.G(this.a));
        }
    }
}
