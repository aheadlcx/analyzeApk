package com.budejie.www.activity.posts;

import com.budejie.www.bean.SuggestedFollowsResults;
import com.google.gson.Gson;
import net.tsz.afinal.a.a;

class a$15 extends a<String> {
    final /* synthetic */ a a;

    a$15(a aVar) {
        this.a = aVar;
    }

    public /* synthetic */ void onSuccess(Object obj) {
        a((String) obj);
    }

    public void onFailure(Throwable th, int i, String str) {
        super.onFailure(th, i, str);
        a.b(this.a, false);
    }

    public void a(String str) {
        super.onSuccess(str);
        try {
            SuggestedFollowsResults suggestedFollowsResults = (SuggestedFollowsResults) new Gson().fromJson(str, SuggestedFollowsResults.class);
            if (suggestedFollowsResults != null) {
                a.a(this.a, suggestedFollowsResults.list);
                if (a.n(this.a) != null && a.n(this.a).size() > 0) {
                    a.b(this.a, true);
                    a.o(this.a).setRecommendList(a.n(this.a));
                    a.f(this.a).notifyDataSetChanged();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            a.b(this.a, false);
        }
    }
}
