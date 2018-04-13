package com.xiaomi.push.mpcd;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.text.TextUtils;
import com.xiaomi.channel.commonutils.misc.f;
import com.xiaomi.push.mpcd.job.e;
import com.xiaomi.push.service.am;
import com.xiaomi.xmpush.thrift.d;
import com.xiaomi.xmpush.thrift.g;
import com.xiaomi.xmpush.thrift.k;

public class BroadcastActionsReceiver extends BroadcastReceiver {
    public static final String a = String.valueOf(d.BroadcastActionRestarted.a());
    public static final String b = String.valueOf(d.BroadcastActionChanged.a());

    private void a(Context context, String str, String str2) {
        if (!TextUtils.isEmpty(str2) && !TextUtils.isEmpty(str)) {
            try {
                if (f.a(context, String.valueOf(12), 1)) {
                    k kVar = new k();
                    kVar.a(str + ":" + str2);
                    kVar.a(System.currentTimeMillis());
                    kVar.a(d.BroadcastAction);
                    com.xiaomi.push.mpcd.job.f.a(context, kVar);
                }
            } catch (Throwable th) {
            }
        }
    }

    public void onReceive(Context context, Intent intent) {
        if (intent != null) {
            try {
                Object dataString = intent.getDataString();
                if (!TextUtils.isEmpty(dataString)) {
                    String[] split = dataString.split(":");
                    if (split.length >= 2 && !TextUtils.isEmpty(split[1])) {
                        String str = split[1];
                        long currentTimeMillis = System.currentTimeMillis();
                        boolean a = am.a(context).a(g.BroadcastActionCollectionSwitch.a(), true);
                        if (TextUtils.equals("android.intent.action.PACKAGE_RESTARTED", intent.getAction())) {
                            if (f.a(context, String.valueOf(12), 1) && a) {
                                if (TextUtils.isEmpty(e.a)) {
                                    e.a += a + ":";
                                }
                                e.a += str + "(" + currentTimeMillis + ")" + ",";
                            }
                        } else if (TextUtils.equals("android.intent.action.PACKAGE_CHANGED", intent.getAction())) {
                            if (f.a(context, String.valueOf(12), 1) && a) {
                                if (TextUtils.isEmpty(e.b)) {
                                    e.b += b + ":";
                                }
                                e.b += str + "(" + currentTimeMillis + ")" + ",";
                            }
                        } else if (TextUtils.equals("android.intent.action.PACKAGE_ADDED", intent.getAction())) {
                            if (!intent.getExtras().getBoolean("android.intent.extra.REPLACING") && a) {
                                a(context, String.valueOf(d.BroadcastActionAdded.a()), str);
                            }
                        } else if (TextUtils.equals("android.intent.action.PACKAGE_REMOVED", intent.getAction())) {
                            if (!intent.getExtras().getBoolean("android.intent.extra.REPLACING") && a) {
                                a(context, String.valueOf(d.BroadcastActionRemoved.a()), str);
                            }
                        } else if (TextUtils.equals("android.intent.action.PACKAGE_REPLACED", intent.getAction())) {
                            if (a) {
                                a(context, String.valueOf(d.BroadcastActionReplaced.a()), str);
                            }
                        } else if (TextUtils.equals("android.intent.action.PACKAGE_DATA_CLEARED", intent.getAction()) && a) {
                            a(context, String.valueOf(d.BroadcastActionDataCleared.a()), str);
                        }
                    }
                }
            } catch (Throwable th) {
            }
        }
    }
}
