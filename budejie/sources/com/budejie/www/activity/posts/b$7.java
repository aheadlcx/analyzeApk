package com.budejie.www.activity.posts;

import android.text.TextUtils;
import com.budejie.www.bean.ListInfo;
import com.budejie.www.bean.ListItemObject;
import com.budejie.www.busevent.LoadMoreEvent;
import com.budejie.www.busevent.LoadMoreEvent.LoadMoreAction;
import com.budejie.www.util.aa;
import com.budejie.www.util.ap;
import com.budejie.www.widget.xrecyclerview.XRecyclerView;
import com.umeng.analytics.MobclickAgent;
import de.greenrobot.event.EventBus;
import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;
import java.util.ArrayList;
import java.util.List;
import net.tsz.afinal.a.a;

class b$7 extends a<String> {
    final /* synthetic */ b a;

    b$7(b bVar) {
        this.a = bVar;
    }

    public /* synthetic */ void onSuccess(Object obj) {
        a((String) obj);
    }

    public void onFailure(Throwable th, int i, String str) {
        if (!this.a.isDetached()) {
            b.g(this.a).b();
        }
    }

    public void a(final String str) {
        if (!this.a.isDetached()) {
            Observable.just(str).map(new Function<String, ArrayList<ListItemObject>>(this) {
                final /* synthetic */ b$7 b;

                public /* synthetic */ Object apply(Object obj) throws Exception {
                    return a((String) obj);
                }

                public ArrayList<ListItemObject> a(String str) {
                    ListInfo a = com.budejie.www.j.a.a(str);
                    if (a == null) {
                        return null;
                    }
                    b.a(this.b.a, a.count);
                    b.a(this.b.a, a.np);
                    if (b.i(this.b.a) != 0) {
                        b.k(this.b.a);
                    }
                    List a2 = com.budejie.www.j.a.a(this.b.a.getActivity(), str);
                    if (com.budejie.www.goddubbing.c.a.a(a2)) {
                        String str2 = "";
                        if (b.l(this.b.a) != null) {
                            str2 = b.l(this.b.a).url;
                        }
                        MobclickAgent.onEvent(b.b(this.b.a), "E03_A13", "加载更多数据为空－" + str2);
                    } else {
                        b.a(this.b.a, a2);
                        ap.a(a2);
                        b.b(this.b.a, a2);
                        b.a(this.b.a, a2);
                        EventBus.getDefault().post(new LoadMoreEvent(LoadMoreAction.LOAD_FINISH));
                    }
                    return a2;
                }
            }).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new Observer<ArrayList<ListItemObject>>(this) {
                Disposable a;
                final /* synthetic */ b$7 b;

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
                    b.g(this.b.a).b();
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
                    b.g(this.b.a).b();
                    b.b(this.b.a, false);
                    if (!com.budejie.www.goddubbing.c.a.a(arrayList)) {
                        b.h(this.b.a).a(arrayList);
                        XRecyclerView g = b.g(this.b.a);
                        if (b.i(this.b.a) != 0 || b.j(this.b.a) > 0) {
                            z = true;
                        }
                        g.setLoadingMoreEnabled(z);
                    }
                }
            });
        }
    }
}
