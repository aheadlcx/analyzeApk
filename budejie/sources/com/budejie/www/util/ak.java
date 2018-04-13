package com.budejie.www.util;

import android.content.Context;
import com.budejie.www.R;
import com.budejie.www.bean.ListItemObject;

public class ak {
    public static boolean a(Context context, ListItemObject listItemObject) {
        if (listItemObject == null) {
            return false;
        }
        int y = an.y(context);
        int x = an.x(context);
        if (listItemObject.getWidth() == 0 || (x * listItemObject.getHeight()) / listItemObject.getWidth() <= (y - an.t(context)) - context.getResources().getDimensionPixelSize(R.dimen.navigation_height)) {
            return false;
        }
        return true;
    }

    public static boolean a(Context context) {
        return context.getResources().getConfiguration().orientation == 2;
    }
}
