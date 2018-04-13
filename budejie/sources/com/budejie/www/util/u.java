package com.budejie.www.util;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import cn.v6.sixrooms.utils.phone.HistoryOpenHelper;
import com.budejie.www.activity.BudejieApplication;
import com.budejie.www.activity.FansListActivity;
import com.budejie.www.activity.FollowListActivity;
import com.budejie.www.activity.MyFollowListActivity;
import com.budejie.www.bean.Fans;
import com.budejie.www.bean.SuggestedFollowsListItem;
import com.budejie.www.http.NetWorkUtil.RequstMethod;
import com.budejie.www.http.j;
import net.tsz.afinal.a.a;

public class u {
    private static Fans a;

    public static void a(Context context, String str) {
        Intent intent = new Intent();
        intent.putExtra(HistoryOpenHelper.COLUMN_UID, str);
        intent.setClass(context, FansListActivity.class);
        context.startActivity(intent);
    }

    public static void b(Context context, String str) {
        Intent intent = new Intent();
        intent.putExtra(HistoryOpenHelper.COLUMN_UID, str);
        if (ai.b(context.getApplicationContext()).equals(str)) {
            intent.setClass(context, MyFollowListActivity.class);
        } else {
            intent.setClass(context, FollowListActivity.class);
        }
        context.startActivity(intent);
    }

    public static void a(Fans fans) {
        a = fans;
    }

    public static Fans a() {
        return a;
    }

    public static void a(SuggestedFollowsListItem suggestedFollowsListItem, Activity activity, a<String> aVar) {
        aa.b("CollectActivity", "operateFollowForCancel");
        BudejieApplication.a.a(RequstMethod.GET, "http://api.budejie.com/api/api_open.php", new j().e(activity, suggestedFollowsListItem.uid), (a) aVar);
    }
}
