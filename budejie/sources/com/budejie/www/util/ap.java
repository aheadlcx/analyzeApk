package com.budejie.www.util;

import android.text.TextUtils;
import com.budejie.www.bean.ListItemObject;
import com.budejie.www.goddubbing.c.a;
import com.tencent.connect.common.Constants;
import java.util.Iterator;
import java.util.List;

public class ap {
    public static void a(List<ListItemObject> list) {
        if (!a.a(list)) {
            Iterator it = list.iterator();
            while (it.hasNext()) {
                ListItemObject listItemObject = (ListItemObject) it.next();
                if (listItemObject == null) {
                    it.remove();
                } else {
                    String type = listItemObject.getType();
                    Object obj = ("41".equals(type) || Constants.VIA_REPORT_TYPE_SHARE_TO_QQ.equals(type)) ? 1 : null;
                    boolean isEmpty = TextUtils.isEmpty(listItemObject.getWid());
                    int height = listItemObject.getHeight();
                    int width = listItemObject.getWidth();
                    Object obj2;
                    if (height <= 0 || width <= 0) {
                        obj2 = 1;
                    } else {
                        obj2 = null;
                    }
                    if (obj == null || isEmpty || r0 != null) {
                        it.remove();
                    }
                }
            }
        }
    }
}
