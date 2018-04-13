package com.budejie.www.activity.posts;

import android.content.Intent;
import android.text.TextUtils;
import com.budejie.www.R;
import com.budejie.www.activity.BudejieApplication;
import com.budejie.www.bean.ListInfo;
import com.budejie.www.bean.ListItemObject;
import com.budejie.www.http.NetWorkUtil.RequstMethod;
import com.budejie.www.http.j;
import com.budejie.www.util.aa;
import com.budejie.www.util.ap;
import com.budejie.www.widget.xrecyclerview.XRecyclerView;
import com.umeng.analytics.MobclickAgent;
import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;
import java.util.ArrayList;
import net.tsz.afinal.a.a;

class b$12 extends a<String> {
    final /* synthetic */ b a;

    b$12(b bVar) {
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
            if (b.u(this.a) == null) {
                b.a(this.a, new String[1]);
            }
            b.u(this.a)[0] = str;
            b.a(this.a, new Observer<ArrayList<ListItemObject>>(this) {
                Disposable a;
                final /* synthetic */ b$12 b;

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
                    boolean z = false;
                    if (com.budejie.www.goddubbing.c.a.a(b.m(this.b.a))) {
                        b.b(this.b.a, R.color.recyclerview_day_background);
                    }
                    boolean z2 = b.v(this.b.a) == 0 && b.q(this.b.a) == 1 && b.l(this.b.a) != null && "推荐".equals(b.l(this.b.a).name);
                    if (z2) {
                        if (b.w(this.b.a) == null) {
                            b.c(this.b.a, new ArrayList());
                        }
                        b.c(this.b.a, arrayList);
                        Object x = b.x(this.b.a);
                        if (com.budejie.www.goddubbing.c.a.a(x) || com.budejie.www.goddubbing.c.a.a(b.w(this.b.a))) {
                            BudejieApplication.a.a(RequstMethod.GET, j.b(), new j(b.b(this.b.a)), b.y(this.b.a));
                            return;
                        }
                        b.g(this.b.a).d();
                        b.t(this.b.a);
                        b.g(this.b.a).setPullRefreshEnabled(true);
                        b.g(this.b.a).setLoadingMoreEnabled(true);
                        b.c(this.b.a, x);
                        return;
                    }
                    z2 = (b.v(this.b.a) != 0 || b.l(this.b.a) == null || TextUtils.isEmpty(b.l(this.b.a).recsys_url)) ? false : true;
                    if (!z2 || com.budejie.www.goddubbing.c.a.a(arrayList)) {
                        b.g(this.b.a).d();
                        b.t(this.b.a);
                        this.b.a.c.edit().putLong("last_refresh_" + b.l(this.b.a).getKey(), System.currentTimeMillis()).apply();
                        b.B(this.b.a);
                        if (!com.budejie.www.goddubbing.c.a.a(arrayList)) {
                            b.m(this.b.a).clear();
                            b.m(this.b.a).addAll(arrayList);
                            b.h(this.b.a).notifyDataSetChanged();
                        }
                        if (b.q(this.b.a) == 3) {
                            b.C(this.b.a);
                        } else {
                            XRecyclerView g = b.g(this.b.a);
                            if (b.i(this.b.a) == 0 || b.m(this.b.a).size() < b.j(this.b.a)) {
                                z = true;
                            }
                            g.setLoadingMoreEnabled(z);
                        }
                        if (1 == b.q(this.b.a) && !TextUtils.isEmpty(b.b(this.b.a).b)) {
                            b.b(this.b.a).sendBroadcast(new Intent(b.b(this.b.a).b));
                            return;
                        }
                        return;
                    }
                    if (b.w(this.b.a) == null) {
                        b.c(this.b.a, new ArrayList());
                    }
                    b.c(this.b.a, arrayList);
                    BudejieApplication.a.a(RequstMethod.GET, j.a(b.l(this.b.a).recsys_url, b.z(this.b.a)), new j(b.b(this.b.a)), b.A(this.b.a));
                }
            });
            Observable.fromArray(b.u(this.a)).map(new Function<String, ArrayList<ListItemObject>>(this) {
                final /* synthetic */ b$12 a;

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
                    b.a(this.a.a, a.count);
                    ArrayList<ListItemObject> a2 = com.budejie.www.j.a.a(this.a.a.getActivity(), str);
                    if (com.budejie.www.goddubbing.c.a.a(a2)) {
                        String str2 = "";
                        if (b.l(this.a.a) != null) {
                            str2 = b.l(this.a.a).url;
                        }
                        MobclickAgent.onEvent(b.b(this.a.a), "E03_A13", "刷新列表数据为空－" + str2);
                    } else {
                        b.a(this.a.a, a.np);
                        if (b.i(this.a.a) != 0) {
                            b.k(this.a.a);
                        }
                        b.d(this.a.a, a2);
                        ap.a(a2);
                        b.e(this.a.a, a2);
                    }
                    return a2;
                }
            }).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(b.D(this.a));
        }
    }
}
