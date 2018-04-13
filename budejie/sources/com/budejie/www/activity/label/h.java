package com.budejie.www.activity.label;

import android.content.Context;
import android.content.Intent;
import android.text.TextUtils;
import android.widget.TextView;
import com.budejie.www.R;
import com.budejie.www.activity.label.enumeration.PlatePostEnum;
import com.budejie.www.activity.plate.bean.PlateBean;
import com.budejie.www.bean.ListItemObject;
import com.budejie.www.goddubbing.c.a;
import com.budejie.www.h.c;
import com.budejie.www.type.SearchItem;
import com.budejie.www.util.an;
import java.util.Iterator;

public class h {
    private static h c;
    private int a;
    private String b;

    private h() {
    }

    public static h a() {
        if (c == null) {
            c = new h();
        }
        return c;
    }

    public static void a(Context context, String str, String str2, int i) {
        Intent intent = new Intent(context, CommonLabelActivity.class);
        intent.putExtra("theme_id", str);
        intent.putExtra("theme_name", str2);
        intent.putExtra("colum_set", i);
        context.startActivity(intent);
    }

    public static void a(Context context, int i, String str) {
        a(context, String.valueOf(i), str, 1);
    }

    public String a(Context context, String str) {
        return String.format(context.getResources().getString(R.string.text_tag), new Object[]{str});
    }

    public void a(int i) {
        this.a = i;
    }

    public int b() {
        return this.a;
    }

    public void a(String str) {
        this.b = str;
    }

    public String c() {
        return this.b;
    }

    public static void a(ListItemObject listItemObject) {
        if (listItemObject != null) {
            PlateBean c = c(listItemObject, CommonLabelActivity.g);
            if (c != null) {
                if ((c.forum_status == PlatePostEnum.ESSENCE_POST.getCode() ? 1 : null) != null) {
                    c.forum_status = PlatePostEnum.NO_ESSENCE_POST.getCode();
                } else {
                    c.forum_status = PlatePostEnum.ESSENCE_POST.getCode();
                }
            }
        }
    }

    public static void b(ListItemObject listItemObject) {
        if (listItemObject != null) {
            PlateBean c = c(listItemObject, CommonLabelActivity.g);
            if (c != null) {
                if ((c.forum_sort == PlatePostEnum.TOP_POST.getCode() ? 1 : null) != null) {
                    c.forum_sort = PlatePostEnum.NO_TOP_POST.getCode();
                } else {
                    c.forum_sort = PlatePostEnum.TOP_POST.getCode();
                }
            }
        }
    }

    public static boolean a(ListItemObject listItemObject, String str) {
        PlateBean c = c(listItemObject, str);
        if (c != null && c.forum_status == PlatePostEnum.ESSENCE_POST.getCode()) {
            return true;
        }
        return false;
    }

    public static boolean b(ListItemObject listItemObject, String str) {
        PlateBean c = c(listItemObject, str);
        if (c != null && c.forum_sort == PlatePostEnum.TOP_POST.getCode()) {
            return true;
        }
        return false;
    }

    private static PlateBean c(ListItemObject listItemObject, String str) {
        if (listItemObject == null || TextUtils.isEmpty(str)) {
            return null;
        }
        Object plateDatas = listItemObject.getPlateDatas();
        if (a.a(plateDatas)) {
            return null;
        }
        PlateBean plateBean;
        Iterator it = plateDatas.iterator();
        while (it.hasNext()) {
            plateBean = (PlateBean) it.next();
            if (str.equals(plateBean.theme_id)) {
                break;
            }
        }
        plateBean = null;
        return plateBean;
    }

    public static boolean a(Context context, SearchItem searchItem) {
        if (!an.d() || searchItem == null || context == null) {
            return false;
        }
        Object f = an.f(context);
        if (TextUtils.isEmpty(f) || !f.equals(searchItem.getId())) {
            return false;
        }
        return true;
    }

    public static void a(Context context, TextView textView, int i, boolean z) {
        switch (i) {
            case 0:
                textView.setBackgroundResource(c.a().b(R.attr.search_hot_1_background));
                a(context, textView, z);
                return;
            case 1:
                textView.setBackgroundResource(c.a().b(R.attr.search_hot_2_background));
                a(context, textView, z);
                return;
            case 2:
                textView.setBackgroundResource(c.a().b(R.attr.search_hot_3_background));
                a(context, textView, z);
                return;
            default:
                textView.setBackgroundResource(c.a().b(R.attr.search_hot_4_background));
                if (z) {
                    textView.setTextColor(context.getResources().getColor(R.color.hot_list_item_default_day_text_color));
                    return;
                } else {
                    textView.setTextColor(context.getResources().getColor(R.color.hot_list_item_default_night_text_color));
                    return;
                }
        }
    }

    private static void a(Context context, TextView textView, boolean z) {
        if (z) {
            textView.setTextColor(context.getResources().getColor(R.color.white));
        } else {
            textView.setTextColor(context.getResources().getColor(R.color.hot_list_item_night_text_color));
        }
    }
}
