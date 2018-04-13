package com.budejie.www.adapter.c;

import android.app.Activity;
import com.budejie.www.adapter.d;
import com.budejie.www.adapter.d.n;
import com.budejie.www.bean.SuggestedFollowsListItem;

public class c extends com.budejie.www.adapter.c<SuggestedFollowsListItem> {
    Activity a;
    c$a b;

    public c(Activity activity, c$a c_a) {
        this.a = activity;
        this.b = c_a;
    }

    protected d a(SuggestedFollowsListItem suggestedFollowsListItem, int i) {
        return new n(this.a, this.b, suggestedFollowsListItem, i);
    }
}
