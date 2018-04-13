package com.budejie.www.activity.posts;

import android.text.TextUtils;
import com.budejie.www.R;
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
import java.util.Collection;
import net.tsz.afinal.a.a;
import org.json.JSONException;
import org.json.JSONObject;

class b$2 extends a<String> {
    final /* synthetic */ b a;

    b$2(b bVar) {
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
        Observable.just(str).map(new Function<String, ArrayList<ListItemObject>>(this) {
            final /* synthetic */ b$2 a;

            {
                this.a = r1;
            }

            public /* synthetic */ Object apply(Object obj) throws Exception {
                return a((String) obj);
            }

            public ArrayList<ListItemObject> a(String str) {
                String str2;
                String str3 = "";
                try {
                    JSONObject jSONObject = new JSONObject(str);
                    if (jSONObject.has("opends")) {
                        str3 = jSONObject.getString("opends");
                    }
                    str2 = str3;
                } catch (JSONException e) {
                    if (!TextUtils.isEmpty(e.getMessage())) {
                        aa.e("StaggeredListFragment", e.getMessage());
                    }
                    str2 = str3;
                }
                b.d(this.a.a, com.budejie.www.j.a.a(this.a.a.getActivity(), str));
                if (!com.budejie.www.goddubbing.c.a.a(b.E(this.a.a))) {
                    ap.a(b.E(this.a.a));
                    for (ListItemObject listItemObject : b.E(this.a.a)) {
                        if (!(listItemObject == null || TextUtils.isEmpty(listItemObject.getWid()))) {
                            listItemObject.isreport = false;
                            listItemObject.setAddtime("推荐内容");
                            listItemObject.opends = str2;
                        }
                    }
                }
                return null;
            }
        }).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new Observer<ArrayList<ListItemObject>>(this) {
            Disposable a;
            final /* synthetic */ b$2 b;

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
                Collection x = b.x(this.b.a);
                if (!com.budejie.www.goddubbing.c.a.a(b.w(this.b.a))) {
                    b.c(this.b.a, x);
                }
                if (com.budejie.www.goddubbing.c.a.a(x)) {
                    d.b(b.b(this.b.a), b.c(this.b.a), b.b(this.b.a).getString(R.string.not_new_data_click));
                }
            }
        });
    }
}
