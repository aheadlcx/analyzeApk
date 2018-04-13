package com.budejie.www.util;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.text.TextUtils;
import com.budejie.www.R;
import com.budejie.www.activity.BudejieApplication;
import com.budejie.www.bean.ListItemObject;
import com.budejie.www.c.d;
import com.budejie.www.f.a;
import com.budejie.www.http.f;
import com.budejie.www.http.j;
import com.umeng.analytics.MobclickAgent;
import net.tsz.afinal.a.b;

public class m {
    public static void a(Activity activity, Handler handler, Bundle bundle) {
        f fVar = new f(activity);
        if (an.a((Context) activity)) {
            ListItemObject listItemObject = (ListItemObject) bundle.getSerializable("data");
            MobclickAgent.onEvent(activity, "collect_label");
            boolean isCollect = listItemObject.isCollect();
            boolean isForwardAndCollect_isweixin = listItemObject.isForwardAndCollect_isweixin();
            if (!isCollect || isForwardAndCollect_isweixin) {
                if (!isForwardAndCollect_isweixin) {
                    handler.sendMessage(handler.obtainMessage(5, activity.getString(R.string.add_collect)));
                }
                fVar.a(null, listItemObject, handler);
                return;
            } else if (!listItemObject.isForwardAndCollect()) {
                handler.sendEmptyMessage(7);
                return;
            } else if (listItemObject.isForwardAndCollectFlag()) {
                handler.sendEmptyMessage(100002);
                return;
            } else {
                return;
            }
        }
        an.a(activity, activity.getString(R.string.nonet), -1).show();
    }

    public static void a(Activity activity, Handler handler, ListItemObject listItemObject) {
        if (handler != null) {
            Boolean a = ((BudejieApplication) activity.getApplication()).g().ae.a();
            listItemObject.setForwardAndCollectFlag(a.booleanValue());
            if (a.booleanValue()) {
                Bundle bundle = new Bundle();
                listItemObject.setForwardAndCollect(true);
                bundle.putSerializable("data", listItemObject);
                a(activity, handler, bundle);
            }
        }
    }

    public static void b(Activity activity, Handler handler, Bundle bundle) {
        String string = bundle.getString("wid");
        if (!TextUtils.isEmpty(bundle.getString("imgPath"))) {
            an.a(bundle.getString("imgPath"));
        }
        ((ListItemObject) bundle.getSerializable("data")).setCollect(false);
        handler.sendMessage(handler.obtainMessage(829, string));
    }

    public static void a(Activity activity, String str, String str2, String str3) {
        new com.budejie.www.http.m().a(activity, str, str3, str2, new m$1(activity));
    }

    public static void a(Context context, String str) {
        SharedPreferences sharedPreferences = context.getSharedPreferences("weiboprefer", 0);
        b j = new j(context).j(context, str, sharedPreferences.getString("collect_version", ""));
        a m_2 = new m$2(sharedPreferences, new d(context), str);
        BudejieApplication.a.a(context, "http://api.budejie.com/api/api_open.php", "get", j, m_2, null, 973);
    }
}
