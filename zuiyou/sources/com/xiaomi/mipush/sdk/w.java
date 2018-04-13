package com.xiaomi.mipush.sdk;

import android.content.Context;
import android.content.SharedPreferences;
import android.text.TextUtils;
import com.xiaomi.channel.commonutils.logger.b;
import com.xiaomi.channel.commonutils.misc.c;
import com.xiaomi.channel.commonutils.misc.h;
import com.xiaomi.channel.commonutils.string.d;
import com.xiaomi.push.service.am;
import com.xiaomi.xmpush.thrift.ai;
import com.xiaomi.xmpush.thrift.g;
import java.text.Collator;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Locale;

public class w {
    public static void a(Context context) {
        SharedPreferences sharedPreferences = context.getSharedPreferences("mipush_extra", 0);
        long j = sharedPreferences.getLong("last_sync_info", -1);
        long currentTimeMillis = System.currentTimeMillis() / 1000;
        long a = (long) am.a(context).a(g.SyncInfoFrequency.a(), 1209600);
        if (j == -1) {
            sharedPreferences.edit().putLong("last_sync_info", currentTimeMillis).commit();
        } else if (Math.abs(currentTimeMillis - j) > a) {
            a(context, true);
            sharedPreferences.edit().putLong("last_sync_info", currentTimeMillis).commit();
        }
    }

    public static void a(Context context, ai aiVar) {
        b.a("need to update local info with: " + aiVar.i());
        String str = (String) aiVar.i().get("accept_time");
        if (str != null) {
            MiPushClient.removeAcceptTime(context);
            String[] split = str.split("-");
            if (split.length == 2) {
                MiPushClient.addAcceptTime(context, split[0], split[1]);
                if ("00:00".equals(split[0]) && "00:00".equals(split[1])) {
                    c.a(context).a(true);
                } else {
                    c.a(context).a(false);
                }
            }
        }
        str = (String) aiVar.i().get("aliases");
        if (str != null) {
            MiPushClient.removeAllAliases(context);
            if (!"".equals(str)) {
                for (String addAlias : str.split(",")) {
                    MiPushClient.addAlias(context, addAlias);
                }
            }
        }
        str = (String) aiVar.i().get("topics");
        if (str != null) {
            MiPushClient.removeAllTopics(context);
            if (!"".equals(str)) {
                for (String addAlias2 : str.split(",")) {
                    MiPushClient.addTopic(context, addAlias2);
                }
            }
        }
        str = (String) aiVar.i().get("user_accounts");
        if (str != null) {
            MiPushClient.removeAllAccounts(context);
            if (!"".equals(str)) {
                for (String addAccount : str.split(",")) {
                    MiPushClient.addAccount(context, addAccount);
                }
            }
        }
    }

    public static void a(Context context, boolean z) {
        h.a(context).a(new am(context, z));
    }

    private static String c(List<String> list) {
        String a = d.a(d(list));
        return (TextUtils.isEmpty(a) || a.length() <= 4) ? "" : a.substring(0, 4).toLowerCase();
    }

    private static String d(List<String> list) {
        if (c.a(list)) {
            return "";
        }
        List<String> arrayList = new ArrayList(list);
        Collections.sort(arrayList, Collator.getInstance(Locale.CHINA));
        String str = "";
        for (String str2 : arrayList) {
            if (!TextUtils.isEmpty(str)) {
                str = str + ",";
            }
            str = str + str2;
        }
        return str;
    }
}
