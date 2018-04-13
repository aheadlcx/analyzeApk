package com.budejie.www.util;

import android.content.Context;
import android.text.TextUtils;
import com.budejie.www.activity.plate.bean.PlateBean;
import com.budejie.www.bean.Navigation;
import com.budejie.www.bean.Navigations;
import com.budejie.www.bean.TopNavigation;
import com.budejie.www.bean.UserItem;
import java.util.Iterator;
import java.util.List;

public class ah {
    public static void a(Context context, Navigations navigations) {
        int a = a(context);
        if (navigations != null && navigations.menus != null) {
            Iterator it = navigations.menus.iterator();
            while (it.hasNext()) {
                Navigation navigation = (Navigation) it.next();
                if (navigation.submenus != null && navigation.submenus.size() > 0) {
                    for (int size = navigation.submenus.size() - 1; size >= 0; size--) {
                        TopNavigation topNavigation = (TopNavigation) navigation.submenus.get(size);
                        if (a < topNavigation.display_level) {
                            navigation.submenus.remove(size);
                        } else if ("baidu".equals("xiaomi") && ("BDJ_To_SixRooms".equals(topNavigation.url) || "BDJ_To_HuaXiReader".equals(topNavigation.url))) {
                            navigation.submenus.remove(size);
                        }
                    }
                }
            }
        }
    }

    public static void a(Context context, List<PlateBean> list) {
        int a = a(context);
        if (list != null && list.size() > 0) {
            for (int size = list.size() - 1; size >= 0; size--) {
                if (a < ((PlateBean) list.get(size)).display_level) {
                    list.remove(size);
                }
            }
        }
    }

    public static int a(Context context) {
        UserItem g = an.g(context);
        if (g == null || TextUtils.isEmpty(g.getLevel())) {
            return 0;
        }
        return Integer.parseInt(g.getLevel());
    }

    public static String a(PlateBean plateBean) {
        if (plateBean == null) {
            return "";
        }
        int i = plateBean.sub_number;
        String str = "社区共" + i;
        if (i >= 10000) {
            if (i % 10000 >= 1000) {
                str = "社区共" + (i / 10000) + "." + ((i % 10000) / 1000) + "万";
            } else {
                str = "社区共" + (i / 10000) + "万";
            }
        }
        String str2 = "人";
        if (!TextUtils.isEmpty(plateBean.tail)) {
            str2 = plateBean.tail;
            if (str2.length() > 6) {
                str2 = str2.substring(0, 5) + "…";
            }
        }
        return str2;
    }

    public static String a(int i, String str) {
        String str2 = i + "";
        if (i < 10000) {
            return str2;
        }
        if (i % 10000 >= 1000) {
            return (i / 10000) + "." + ((i % 10000) / 1000) + str;
        }
        return (i / 10000) + str;
    }
}
