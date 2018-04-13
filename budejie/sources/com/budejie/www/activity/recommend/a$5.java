package com.budejie.www.activity.recommend;

import com.budejie.www.R;
import com.budejie.www.bean.FriendsListResults;
import com.budejie.www.util.an;
import com.budejie.www.util.as;
import com.google.gson.Gson;
import net.tsz.afinal.a.a;

class a$5 extends a<String> {
    final /* synthetic */ a a;

    a$5(a aVar) {
        this.a = aVar;
    }

    public /* synthetic */ void onSuccess(Object obj) {
        a((String) obj);
    }

    public void onStart() {
    }

    public void onFailure(Throwable th, int i, String str) {
        super.onFailure(th, i, str);
        if (!this.a.isDetached()) {
            a.a(this.a).c();
        }
    }

    public void a(String str) {
        super.onSuccess(str);
        if (!this.a.isDetached()) {
            a.a(this.a).b();
            a.a(this.a).c();
            if (!this.a.isDetached()) {
                if ("[]".equals(str)) {
                    a.a(this.a).setPullLoadEnable(false);
                    return;
                }
                try {
                    FriendsListResults friendsListResults = (FriendsListResults) new Gson().fromJson(str, FriendsListResults.class);
                    if (friendsListResults != null) {
                        Object a = as.b().a(a.c(this.a), friendsListResults, a.i(this.a), a.j(this.a), a.k(this.a), true);
                        if (a.isEmpty()) {
                            a.a(this.a).a(a.c(this.a).getString(R.string.already_last), null);
                        } else {
                            a.l(this.a).addAll(a);
                            a.m(this.a).b(a);
                            a.a(this.a, as.b().e());
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
}
