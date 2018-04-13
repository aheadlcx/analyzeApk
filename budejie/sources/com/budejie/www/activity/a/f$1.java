package com.budejie.www.activity.a;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import com.budejie.www.R;
import com.budejie.www.activity.PersonalProfileActivity;
import com.budejie.www.adapter.g.a.a;
import com.budejie.www.bean.ListItemObject;
import com.budejie.www.util.an;
import com.budejie.www.util.p;
import com.budejie.www.util.p.c;

class f$1 extends a {
    final /* synthetic */ f a;

    f$1(f fVar) {
        this.a = fVar;
    }

    public void f(View view, ListItemObject listItemObject) {
        f.a(this.a, listItemObject);
        f.a(this.a, listItemObject.getHotcmt().id);
    }

    public void c(View view, ListItemObject listItemObject) {
        f.a(this.a).c(view, listItemObject);
    }

    public void e(View view, ListItemObject listItemObject) {
        an.a(listItemObject, f.b(this.a), f.c(this.a));
        com.budejie.www.util.a.a(f.d(this.a), listItemObject, "", false);
    }

    public void a(View view, ListItemObject listItemObject, String str) {
        Bundle bundle = new Bundle();
        bundle.putString(PersonalProfileActivity.c, listItemObject.getUid());
        bundle.putString(PersonalProfileActivity.d, str);
        f.e(this.a).a(7, bundle).onClick(view);
    }

    public void d(final View view, final ListItemObject listItemObject) {
        if (view.getId() == R.id.hot_cmt_content_layout || view.getId() == R.id.comment_content_all_layout) {
            c anonymousClass1 = new c(this) {
                final /* synthetic */ f$1 c;

                public void a(String str) {
                    if (!TextUtils.isEmpty(str)) {
                        if ("查看原帖".equals(str)) {
                            this.c.e(view, listItemObject);
                        } else if ("删除评论".equals(str)) {
                            this.c.f(view, listItemObject);
                        } else if ("回复TA".equals(str)) {
                            f.b(this.c.a, listItemObject);
                        } else if ("举报".equals(str)) {
                            f.c(this.c.a, listItemObject);
                        }
                    }
                }
            };
            if (an.f(f.d(this.a)).equals(listItemObject.getHotcmt().user.id)) {
                p.a(f.d(this.a), new String[]{"查看原帖", "删除评论"}, anonymousClass1, false);
                return;
            }
            p.a(f.d(this.a), new String[]{"回复TA", "查看原帖", "举报"}, anonymousClass1, false);
            return;
        }
        f.a(this.a).d(view, listItemObject);
    }

    public void a(View view, ListItemObject listItemObject, int i) {
        f.a(this.a).a(view, listItemObject, i);
    }

    public void a(View view, ListItemObject listItemObject) {
        f.a(this.a).a(view, listItemObject);
    }

    public void b(View view, ListItemObject listItemObject) {
        f.a(this.a).b(view, listItemObject);
    }
}
