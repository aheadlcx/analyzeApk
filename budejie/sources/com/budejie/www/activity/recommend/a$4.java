package com.budejie.www.activity.recommend;

import android.text.TextUtils;
import com.budejie.www.R;
import com.budejie.www.bean.FriendsListResults;
import com.budejie.www.util.an;
import com.budejie.www.util.as;
import com.google.gson.Gson;
import java.util.List;
import net.tsz.afinal.a.a;

class a$4 extends a<String> {
    final /* synthetic */ a a;

    a$4(a aVar) {
        this.a = aVar;
    }

    public /* synthetic */ void onSuccess(Object obj) {
        a((String) obj);
    }

    public void onStart() {
    }

    public void a(String str) {
        if (!this.a.isDetached()) {
            a.a(this.a).b();
            if (!this.a.isDetached()) {
                if (TextUtils.isEmpty(str)) {
                    a.a(this.a, an.a(this.a.getActivity(), this.a.getString(R.string.no_friends), -1));
                    a.o(this.a).show();
                    return;
                }
                try {
                    FriendsListResults friendsListResults = (FriendsListResults) new Gson().fromJson(str, FriendsListResults.class);
                    if (friendsListResults != null) {
                        List a = as.b().a(a.c(this.a), friendsListResults, a.i(this.a), a.j(this.a), a.k(this.a), false);
                        if (!a.isEmpty()) {
                            a.l(this.a).clear();
                            a.l(this.a).addAll(a);
                            a.m(this.a).a(a);
                            a.a(this.a, as.b().e());
                            if (a.size() < a.n(this.a)) {
                                a.a(this.a).setPullLoadEnable(false);
                            } else {
                                a.a(this.a).setPullLoadEnable(true);
                            }
                        }
                        this.a.b();
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                    a.a(this.a, an.a(this.a.getActivity(), this.a.getString(R.string.no_friends), -1));
                    a.o(this.a).show();
                }
            }
        }
    }

    public void onFailure(Throwable th, int i, String str) {
        if (!this.a.isDetached()) {
            a.a(this.a).b();
        }
    }
}
