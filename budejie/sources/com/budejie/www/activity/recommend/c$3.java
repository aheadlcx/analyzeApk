package com.budejie.www.activity.recommend;

import android.text.TextUtils;
import com.budejie.www.R;
import com.budejie.www.bean.SuggestedFollowsResults;
import com.budejie.www.util.an;
import com.budejie.www.util.as;
import com.google.gson.Gson;
import net.tsz.afinal.a.a;

class c$3 extends a<String> {
    final /* synthetic */ c a;

    c$3(c cVar) {
        this.a = cVar;
    }

    public /* synthetic */ void onSuccess(Object obj) {
        a((String) obj);
    }

    public void onFailure(Throwable th, int i, String str) {
        if (!this.a.isDetached() && c.a(this.a) != null) {
            c.a(this.a).b();
        }
    }

    public void a(String str) {
        try {
            if (!this.a.isDetached()) {
                c.a(this.a).b();
                if (!this.a.isDetached()) {
                    if (TextUtils.isEmpty(str)) {
                        c.a(this.a, an.a(this.a.getActivity(), this.a.getString(R.string.load_failed), -1));
                        c.f(this.a).show();
                        return;
                    }
                    try {
                        SuggestedFollowsResults suggestedFollowsResults = (SuggestedFollowsResults) new Gson().fromJson(str, SuggestedFollowsResults.class);
                        if (suggestedFollowsResults != null) {
                            Object a = as.b().a(suggestedFollowsResults);
                            if (!a.isEmpty()) {
                                c.d(this.a).clear();
                                c.d(this.a).addAll(a);
                                c.e(this.a).a(a);
                                c.a(this.a, as.b().d());
                                c.a(this.a).setPullLoadEnable(true);
                            }
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
