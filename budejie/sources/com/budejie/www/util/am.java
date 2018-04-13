package com.budejie.www.util;

import android.text.TextUtils;
import com.budejie.www.bean.ListItemObject;

public class am {
    public static int a(ListItemObject listItemObject) {
        int i = 0;
        if (listItemObject != null) {
            Object repost = listItemObject.getRepost();
            if (!TextUtils.isEmpty(repost)) {
                try {
                    i = Integer.parseInt(repost) + 1;
                } catch (NumberFormatException e) {
                }
                listItemObject.setRepost(String.valueOf(i));
            }
        }
        return i;
    }
}
