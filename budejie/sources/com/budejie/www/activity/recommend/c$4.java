package com.budejie.www.activity.recommend;

import com.budejie.www.R;
import com.budejie.www.bean.SuggestedFollowsResults;
import com.budejie.www.util.an;
import com.budejie.www.util.as;
import com.google.gson.Gson;
import net.tsz.afinal.a.a;

class c$4 extends a<String> {
    final /* synthetic */ c a;

    c$4(c cVar) {
        this.a = cVar;
    }

    public /* synthetic */ void onSuccess(Object obj) {
        a((String) obj);
    }

    public void onFailure(Throwable th, int i, String str) {
        super.onFailure(th, i, str);
        if (!this.a.isDetached() && c.a(this.a) != null) {
            c.a(this.a).c();
        }
    }

    public void a(String str) {
        super.onSuccess(str);
        try {
            if (!this.a.isDetached()) {
                c.a(this.a).c();
                if (!this.a.isDetached()) {
                    if ("[]".equals(str)) {
                        c.a(this.a).setPullLoadEnable(false);
                        c.a(this.a).a(c.g(this.a).getString(R.string.already_last), null);
                        return;
                    }
                    try {
                        SuggestedFollowsResults suggestedFollowsResults = (SuggestedFollowsResults) new Gson().fromJson(str, SuggestedFollowsResults.class);
                        if (suggestedFollowsResults != null) {
                            Object a = as.b().a(suggestedFollowsResults);
                            if (a.isEmpty()) {
                                c.a(this.a).a(c.g(this.a).getString(R.string.already_last), null);
                                return;
                            }
                            c.d(this.a).addAll(a);
                            c.e(this.a).b(a);
                            c.a(this.a, as.b().d());
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                        c.a(this.a, an.a(this.a.getActivity(), this.a.getString(R.string.load_failed), -1));
                        c.f(this.a).show();
                    }
                }
            }
        } catch (Exception e2) {
        }
    }
}
