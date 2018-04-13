package com.budejie.www.activity.a;

import android.app.Activity;

public class a {
    public static d a(Activity activity, String str) {
        if ("posts_type".equals(str)) {
            return new g(activity);
        }
        if ("comments_type".equals(str)) {
            return new f(activity);
        }
        if ("share_type".equals(str)) {
            return new h(activity);
        }
        return null;
    }
}
