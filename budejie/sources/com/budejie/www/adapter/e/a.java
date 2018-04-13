package com.budejie.www.adapter.e;

import android.view.View;
import android.view.View.OnClickListener;
import com.budejie.www.bean.ListItemObject;
import com.budejie.www.bean.SuggestedFollowsListItem;

public interface a {
    void a();

    void a(View view, ListItemObject listItemObject);

    @Deprecated
    void a(View view, ListItemObject listItemObject, int i);

    void a(View view, ListItemObject listItemObject, String str);

    void a(ListItemObject listItemObject, int i);

    void a(SuggestedFollowsListItem suggestedFollowsListItem);

    void a_(String str);

    OnClickListener b();

    void b(View view, ListItemObject listItemObject);

    void c(View view, ListItemObject listItemObject);

    void d(View view, ListItemObject listItemObject);

    void e(View view, ListItemObject listItemObject);
}
