package com.xiaomi.mipush.sdk;

import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences.Editor;
import android.content.pm.PackageInfo;
import android.content.pm.ServiceInfo;
import android.text.TextUtils;
import com.baidu.mobstat.Config;
import com.xiaomi.channel.commonutils.android.b;
import com.xiaomi.channel.commonutils.android.g;
import com.xiaomi.channel.commonutils.android.j;
import com.xiaomi.channel.commonutils.misc.f;
import com.xiaomi.channel.commonutils.string.d;
import com.xiaomi.push.service.ah;
import com.xiaomi.push.service.aw;
import com.xiaomi.push.service.h;
import com.xiaomi.push.service.k;
import com.xiaomi.push.service.receivers.NetworkStatusReceiver;
import com.xiaomi.xmpush.thrift.a;
import com.xiaomi.xmpush.thrift.ae;
import com.xiaomi.xmpush.thrift.af;
import com.xiaomi.xmpush.thrift.ak;
import com.xiaomi.xmpush.thrift.am;
import com.xiaomi.xmpush.thrift.ao;
import com.xiaomi.xmpush.thrift.e;
import com.xiaomi.xmpush.thrift.r;
import com.xiaomi.xmpush.thrift.t;
import com.xiaomi.xmpush.thrift.z;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.TimeZone;
import qsbk.app.core.utils.ACache;
import qsbk.app.im.TimeUtils;
import qsbk.app.utils.ListViewHelper;

public abstract class MiPushClient {
    public static final String COMMAND_REGISTER = "register";
    public static final String COMMAND_SET_ACCEPT_TIME = "accept-time";
    public static final String COMMAND_SET_ACCOUNT = "set-account";
    public static final String COMMAND_SET_ALIAS = "set-alias";
    public static final String COMMAND_SUBSCRIBE_TOPIC = "subscribe-topic";
    public static final String COMMAND_UNSET_ACCOUNT = "unset-account";
    public static final String COMMAND_UNSET_ALIAS = "unset-alias";
    public static final String COMMAND_UNSUBSCRIBE_TOPIC = "unsubscibe-topic";
    public static final String PREF_EXTRA = "mipush_extra";
    private static Context a;
    private static long b = System.currentTimeMillis();
    private static z c;

    @Deprecated
    public static abstract class MiPushClientCallback {
        private String a;

        protected String a() {
            return this.a;
        }

        public void onCommandResult(String str, long j, String str2, List<String> list) {
        }

        public void onInitializeResult(long j, String str, String str2) {
        }

        public void onReceiveMessage(MiPushMessage miPushMessage) {
        }

        public void onReceiveMessage(String str, String str2, String str3, boolean z) {
        }

        public void onSubscribeResult(long j, String str, String str2) {
        }

        public void onUnsubscribeResult(long j, String str, String str2) {
        }
    }

    protected static synchronized String a() {
        String str;
        synchronized (MiPushClient.class) {
            str = d.a(4) + b;
            b++;
        }
        return str;
    }

    protected static void a(Context context) {
        Editor edit = context.getSharedPreferences(PREF_EXTRA, 0).edit();
        edit.clear();
        edit.commit();
    }

    static void a(Context context, t tVar) {
        if (a.a(context).i()) {
            String a = d.a(6);
            String c = a.a(context).c();
            String d = a.a(context).d();
            a.a(context).h();
            a.a(context).a(c, d, a);
            af afVar = new af();
            afVar.a(a());
            afVar.b(c);
            afVar.e(d);
            afVar.f(a);
            afVar.d(context.getPackageName());
            afVar.c(b.a(context, context.getPackageName()));
            afVar.a(tVar);
            u.a(context).a(afVar, false);
        }
    }

    static synchronized void a(Context context, String str) {
        synchronized (MiPushClient.class) {
            context.getSharedPreferences(PREF_EXTRA, 0).edit().putLong("alias_" + str, System.currentTimeMillis()).commit();
        }
    }

    static void a(Context context, String str, r rVar, String str2) {
        Object aeVar = new ae();
        if (!TextUtils.isEmpty(str2)) {
            aeVar.b(str2);
        } else if (a.a(context).b()) {
            aeVar.b(a.a(context).c());
        } else {
            com.xiaomi.channel.commonutils.logger.b.d("do not report clicked message");
            return;
        }
        aeVar.c("bar:click");
        aeVar.a(str);
        aeVar.a(false);
        u.a(context).a(aeVar, a.i, false, rVar);
    }

    static void a(Context context, String str, r rVar, String str2, String str3) {
        org.apache.thrift.a aeVar = new ae();
        if (TextUtils.isEmpty(str3)) {
            com.xiaomi.channel.commonutils.logger.b.d("do not report clicked message");
            return;
        }
        aeVar.b(str3);
        aeVar.c("bar:click");
        aeVar.a(str);
        aeVar.a(false);
        u.a(context).a(aeVar, a.i, false, true, rVar, true, str2, str3);
    }

    static synchronized void a(Context context, String str, String str2) {
        synchronized (MiPushClient.class) {
            context.getSharedPreferences(PREF_EXTRA, 0).edit().putString(Constants.EXTRA_KEY_ACCEPT_TIME, str + Constants.ACCEPT_TIME_SEPARATOR_SP + str2).commit();
        }
    }

    protected static void a(Context context, String str, String str2, String str3) {
        ArrayList arrayList = new ArrayList();
        if (!TextUtils.isEmpty(str2)) {
            arrayList.add(str2);
        }
        if (!COMMAND_SET_ALIAS.equalsIgnoreCase(str) || Math.abs(System.currentTimeMillis() - aliasSetTime(context, str2)) >= TimeUtils.HOUR) {
            if (COMMAND_UNSET_ALIAS.equalsIgnoreCase(str) && aliasSetTime(context, str2) < 0) {
                com.xiaomi.channel.commonutils.logger.b.a("Don't cancel alias for " + arrayList + " is unseted");
            } else if (!COMMAND_SET_ACCOUNT.equalsIgnoreCase(str) || Math.abs(System.currentTimeMillis() - accountSetTime(context, str2)) >= TimeUtils.HOUR) {
                if (!COMMAND_UNSET_ACCOUNT.equalsIgnoreCase(str) || accountSetTime(context, str2) >= 0) {
                    a(context, str, arrayList, str3);
                } else {
                    com.xiaomi.channel.commonutils.logger.b.a("Don't cancel account for " + arrayList + " is unseted");
                }
            } else if (1 == PushMessageHelper.getPushMode(context)) {
                PushMessageHandler.a(context, str3, str, 0, null, arrayList);
            } else {
                PushMessageHelper.sendCommandMessageBroadcast(context, PushMessageHelper.generateCommandMessage(COMMAND_SET_ACCOUNT, arrayList, 0, null, null));
            }
        } else if (1 == PushMessageHelper.getPushMode(context)) {
            PushMessageHandler.a(context, str3, str, 0, null, arrayList);
        } else {
            PushMessageHelper.sendCommandMessageBroadcast(context, PushMessageHelper.generateCommandMessage(COMMAND_SET_ALIAS, arrayList, 0, null, null));
        }
    }

    protected static void a(Context context, String str, ArrayList<String> arrayList, String str2) {
        if (!TextUtils.isEmpty(a.a(context).c())) {
            org.apache.thrift.a zVar = new z();
            zVar.a(a());
            zVar.b(a.a(context).c());
            zVar.c(str);
            Iterator it = arrayList.iterator();
            while (it.hasNext()) {
                zVar.d((String) it.next());
            }
            zVar.f(str2);
            zVar.e(context.getPackageName());
            u.a(context).a(zVar, a.j, null);
        }
    }

    private static void a(Object obj, String str) {
        if (obj == null) {
            throw new IllegalArgumentException("param " + str + " is not nullable");
        }
    }

    public static long accountSetTime(Context context, String str) {
        return context.getSharedPreferences(PREF_EXTRA, 0).getLong("account_" + str, -1);
    }

    public static long aliasSetTime(Context context, String str) {
        return context.getSharedPreferences(PREF_EXTRA, 0).getLong("alias_" + str, -1);
    }

    public static void awakeApps(Context context, String[] strArr) {
        new Thread(new n(strArr, context)).start();
    }

    static synchronized void b(Context context) {
        synchronized (MiPushClient.class) {
            for (String b : getAllAlias(context)) {
                b(context, b);
            }
        }
    }

    private static void b(Context context, PackageInfo packageInfo) {
        ServiceInfo[] serviceInfoArr = packageInfo.services;
        if (serviceInfoArr != null) {
            for (ServiceInfo serviceInfo : serviceInfoArr) {
                if (serviceInfo.exported && serviceInfo.enabled && "com.xiaomi.mipush.sdk.PushMessageHandler".equals(serviceInfo.name) && !context.getPackageName().equals(serviceInfo.packageName)) {
                    try {
                        Thread.sleep(((long) ((Math.random() * 2.0d) + 1.0d)) * 1000);
                        Intent intent = new Intent();
                        intent.setClassName(serviceInfo.packageName, serviceInfo.name);
                        intent.setAction("com.xiaomi.mipush.sdk.WAKEUP");
                        intent.putExtra("waker_pkgname", context.getPackageName());
                        context.startService(intent);
                        return;
                    } catch (Throwable th) {
                        return;
                    }
                }
            }
        }
    }

    static synchronized void b(Context context, String str) {
        synchronized (MiPushClient.class) {
            context.getSharedPreferences(PREF_EXTRA, 0).edit().remove("alias_" + str).commit();
        }
    }

    private static boolean b(Context context, String str, String str2) {
        return TextUtils.equals(f(context), str + Constants.ACCEPT_TIME_SEPARATOR_SP + str2);
    }

    private static void c() {
        f.a(a).a(new o(a), ah.a(a).a(e.A.a(), ACache.TIME_DAY), 5);
    }

    static synchronized void c(Context context) {
        synchronized (MiPushClient.class) {
            for (String d : getAllUserAccount(context)) {
                d(context, d);
            }
        }
    }

    static synchronized void c(Context context, String str) {
        synchronized (MiPushClient.class) {
            context.getSharedPreferences(PREF_EXTRA, 0).edit().putLong("account_" + str, System.currentTimeMillis()).commit();
        }
    }

    public static void clearLocalNotificationType(Context context) {
        u.a(context).f();
    }

    public static void clearNotification(Context context) {
        u.a(context).a(-1);
    }

    public static void clearNotification(Context context, int i) {
        u.a(context).a(i);
    }

    public static void clearNotification(Context context, String str, String str2) {
        u.a(context).a(str, str2);
    }

    private static void d() {
        if (h.b(a)) {
            f.a(a).a(new d(a), ah.a(a).a(e.O.a(), 900));
        }
    }

    static synchronized void d(Context context) {
        synchronized (MiPushClient.class) {
            for (String f : getAllTopic(context)) {
                f(context, f);
            }
        }
    }

    static synchronized void d(Context context, String str) {
        synchronized (MiPushClient.class) {
            context.getSharedPreferences(PREF_EXTRA, 0).edit().remove("account_" + str).commit();
        }
    }

    public static void disablePush(Context context) {
        u.a(context).a(true);
    }

    private static void e() {
        if (ah.a(a).a(e.z.a(), f())) {
            f.a(a).a(new l(), 10);
        }
    }

    static synchronized void e(Context context) {
        synchronized (MiPushClient.class) {
            context.getSharedPreferences(PREF_EXTRA, 0).edit().remove(Constants.EXTRA_KEY_ACCEPT_TIME).commit();
        }
    }

    static synchronized void e(Context context, String str) {
        synchronized (MiPushClient.class) {
            context.getSharedPreferences(PREF_EXTRA, 0).edit().putLong("topic_" + str, System.currentTimeMillis()).commit();
        }
    }

    public static void enablePush(Context context) {
        u.a(context).a(false);
    }

    protected static String f(Context context) {
        return context.getSharedPreferences(PREF_EXTRA, 0).getString(Constants.EXTRA_KEY_ACCEPT_TIME, "00:00-23:59");
    }

    static synchronized void f(Context context, String str) {
        synchronized (MiPushClient.class) {
            context.getSharedPreferences(PREF_EXTRA, 0).edit().remove("topic_" + str).commit();
        }
    }

    private static boolean f() {
        return g.b();
    }

    private static void g() {
        new Thread(new m()).start();
    }

    private static void g(Context context) {
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction("android.net.conn.CONNECTIVITY_CHANGE");
        intentFilter.addCategory("android.intent.category.DEFAULT");
        context.registerReceiver(new NetworkStatusReceiver(null), intentFilter);
    }

    public static List<String> getAllAlias(Context context) {
        List<String> arrayList = new ArrayList();
        for (String str : context.getSharedPreferences(PREF_EXTRA, 0).getAll().keySet()) {
            if (str.startsWith("alias_")) {
                arrayList.add(str.substring("alias_".length()));
            }
        }
        return arrayList;
    }

    public static List<String> getAllTopic(Context context) {
        List<String> arrayList = new ArrayList();
        for (String str : context.getSharedPreferences(PREF_EXTRA, 0).getAll().keySet()) {
            if (str.startsWith("topic_") && !str.contains("**ALL**")) {
                arrayList.add(str.substring("topic_".length()));
            }
        }
        return arrayList;
    }

    public static List<String> getAllUserAccount(Context context) {
        List<String> arrayList = new ArrayList();
        for (String str : context.getSharedPreferences(PREF_EXTRA, 0).getAll().keySet()) {
            if (str.startsWith("account_")) {
                arrayList.add(str.substring("account_".length()));
            }
        }
        return arrayList;
    }

    public static String getRegId(Context context) {
        return a.a(context).i() ? a.a(context).e() : null;
    }

    private static void h(Context context) {
        context.getSharedPreferences(PREF_EXTRA, 0).edit().putLong("last_pull_notification", System.currentTimeMillis()).commit();
    }

    private static boolean i(Context context) {
        return Math.abs(System.currentTimeMillis() - context.getSharedPreferences(PREF_EXTRA, 0).getLong("last_pull_notification", -1)) > ListViewHelper.DEFAULT_TIPS_TO_REFRESH_INTERVAL;
    }

    @Deprecated
    public static void initialize(Context context, String str, String str2, MiPushClientCallback miPushClientCallback) {
        boolean z = false;
        a((Object) context, com.umeng.analytics.pro.b.M);
        a((Object) str, "appID");
        a((Object) str2, "appToken");
        try {
            if (aw.a().b()) {
                aw.a().a(context);
            }
            aw.a().a(new ab(context), "UPLOADER_FROM_MIPUSHCLIENT");
            a = context.getApplicationContext();
            if (a == null) {
                a = context;
            }
            if (miPushClientCallback != null) {
                PushMessageHandler.a(miPushClientCallback);
            }
            if (j.b(context)) {
                h.a(context);
            }
            if (a.a(a).m() != Constants.a()) {
                z = true;
            }
            if (z || k(a)) {
                if (z || !a.a(a).a(str, str2) || a.a(a).n()) {
                    String a = d.a(6);
                    a.a(a).h();
                    a.a(a).a(Constants.a());
                    a.a(a).a(str, str2, a);
                    a(a);
                    af afVar = new af();
                    afVar.a(a());
                    afVar.b(str);
                    afVar.e(str2);
                    afVar.d(context.getPackageName());
                    afVar.f(a);
                    afVar.c(b.a(context, context.getPackageName()));
                    afVar.b(b.b(context, context.getPackageName()));
                    afVar.g("3_2_2");
                    afVar.a(30202);
                    afVar.h(com.xiaomi.channel.commonutils.android.e.b(a));
                    afVar.a(t.c);
                    a = com.xiaomi.channel.commonutils.android.e.d(a);
                    if (!TextUtils.isEmpty(a)) {
                        if (g.b()) {
                            afVar.i(a);
                        }
                        afVar.k(d.a(a));
                    }
                    afVar.j(com.xiaomi.channel.commonutils.android.e.a());
                    int b = com.xiaomi.channel.commonutils.android.e.b();
                    if (b >= 0) {
                        afVar.c(b);
                    }
                    u.a(a).a(afVar, z);
                } else {
                    if (1 == PushMessageHelper.getPushMode(context)) {
                        a((Object) miPushClientCallback, com.alipay.sdk.authjs.a.c);
                        miPushClientCallback.onInitializeResult(0, null, a.a(context).e());
                    } else {
                        List arrayList = new ArrayList();
                        arrayList.add(a.a(context).e());
                        PushMessageHelper.sendCommandMessageBroadcast(a, PushMessageHelper.generateCommandMessage("register", arrayList, 0, null, null));
                    }
                    u.a(context).a();
                    if (a.a(a).a()) {
                        org.apache.thrift.a aeVar = new ae();
                        aeVar.b(a.a(context).c());
                        aeVar.c("client_info_update");
                        aeVar.a(a());
                        aeVar.h = new HashMap();
                        aeVar.h.put("app_version", b.a(a, a.getPackageName()));
                        aeVar.h.put(Constants.EXTRA_KEY_APP_VERSION_CODE, Integer.toString(b.b(a, a.getPackageName())));
                        aeVar.h.put("push_sdk_vn", "3_2_2");
                        aeVar.h.put("push_sdk_vc", Integer.toString(30202));
                        CharSequence g = a.a(a).g();
                        if (!TextUtils.isEmpty(g)) {
                            aeVar.h.put("deviceid", g);
                        }
                        u.a(context).a(aeVar, a.i, false, null);
                    }
                    if (!com.xiaomi.channel.commonutils.android.h.a(a, "update_devId", false)) {
                        g();
                        com.xiaomi.channel.commonutils.android.h.b(a, "update_devId", true);
                    }
                    if (shouldUseMIUIPush(a) && i(a)) {
                        org.apache.thrift.a aeVar2 = new ae();
                        aeVar2.b(a.a(a).c());
                        aeVar2.c("pull");
                        aeVar2.a(a());
                        aeVar2.a(false);
                        u.a(a).a(aeVar2, a.i, false, null, false);
                        h(a);
                    }
                }
                j(a);
                c();
                d();
                e();
                x.a(a);
                if (c == null) {
                    c = new z(a);
                }
                c.a(a);
                if ("disable_syncing".equals(p.a(a).a())) {
                    disablePush(a);
                }
                if ("enable_syncing".equals(p.a(a).a())) {
                    enablePush(a);
                    return;
                }
                return;
            }
            u.a(context).a();
            com.xiaomi.channel.commonutils.logger.b.a("Could not send  register message within 5s repeatly .");
        } catch (Exception e) {
            com.xiaomi.channel.commonutils.logger.b.d(e.toString());
        } catch (Throwable th) {
            com.xiaomi.channel.commonutils.logger.b.a(th);
        }
    }

    private static void j(Context context) {
        context.getSharedPreferences(PREF_EXTRA, 0).edit().putLong("last_reg_request", System.currentTimeMillis()).commit();
    }

    private static boolean k(Context context) {
        return Math.abs(System.currentTimeMillis() - context.getSharedPreferences(PREF_EXTRA, 0).getLong("last_reg_request", -1)) > Config.BPLUS_DELAY_TIME;
    }

    public static void pausePush(Context context, String str) {
        setAcceptTime(context, 0, 0, 0, 0, str);
    }

    public static void registerPush(Context context, String str, String str2) {
        if (!NetworkStatusReceiver.a()) {
            g(context);
        }
        new Thread(new k(context, str, str2)).start();
    }

    public static void reportMessageClicked(Context context, MiPushMessage miPushMessage) {
        r rVar = new r();
        rVar.a(miPushMessage.getMessageId());
        rVar.b(miPushMessage.getTopic());
        rVar.d(miPushMessage.getDescription());
        rVar.c(miPushMessage.getTitle());
        rVar.c(miPushMessage.getNotifyId());
        rVar.a(miPushMessage.getNotifyType());
        rVar.b(miPushMessage.getPassThrough());
        rVar.a(miPushMessage.getExtra());
        a(context, miPushMessage.getMessageId(), rVar, null);
    }

    @Deprecated
    public static void reportMessageClicked(Context context, String str) {
        a(context, str, null, null);
    }

    public static void resumePush(Context context, String str) {
        setAcceptTime(context, 0, 0, 23, 59, str);
    }

    public static void setAcceptTime(Context context, int i, int i2, int i3, int i4, String str) {
        if (i < 0 || i >= 24 || i3 < 0 || i3 >= 24 || i2 < 0 || i2 >= 60 || i4 < 0 || i4 >= 60) {
            throw new IllegalArgumentException("the input parameter is not valid.");
        }
        long rawOffset = (long) (((TimeZone.getTimeZone("GMT+08").getRawOffset() - TimeZone.getDefault().getRawOffset()) / 1000) / 60);
        long j = ((((long) ((i * 60) + i2)) + rawOffset) + 1440) % 1440;
        rawOffset = ((rawOffset + ((long) ((i3 * 60) + i4))) + 1440) % 1440;
        ArrayList arrayList = new ArrayList();
        arrayList.add(String.format("%1$02d:%2$02d", new Object[]{Long.valueOf(j / 60), Long.valueOf(j % 60)}));
        arrayList.add(String.format("%1$02d:%2$02d", new Object[]{Long.valueOf(rawOffset / 60), Long.valueOf(rawOffset % 60)}));
        List arrayList2 = new ArrayList();
        arrayList2.add(String.format("%1$02d:%2$02d", new Object[]{Integer.valueOf(i), Integer.valueOf(i2)}));
        arrayList2.add(String.format("%1$02d:%2$02d", new Object[]{Integer.valueOf(i3), Integer.valueOf(i4)}));
        if (!b(context, (String) arrayList.get(0), (String) arrayList.get(1))) {
            a(context, COMMAND_SET_ACCEPT_TIME, arrayList, str);
        } else if (1 == PushMessageHelper.getPushMode(context)) {
            PushMessageHandler.a(context, str, COMMAND_SET_ACCEPT_TIME, 0, null, arrayList2);
        } else {
            PushMessageHelper.sendCommandMessageBroadcast(context, PushMessageHelper.generateCommandMessage(COMMAND_SET_ACCEPT_TIME, arrayList2, 0, null, null));
        }
    }

    public static void setAlias(Context context, String str, String str2) {
        if (!TextUtils.isEmpty(str)) {
            a(context, COMMAND_SET_ALIAS, str, str2);
        }
    }

    public static void setLocalNotificationType(Context context, int i) {
        u.a(context).b(i & -1);
    }

    public static void setUserAccount(Context context, String str, String str2) {
        if (!TextUtils.isEmpty(str)) {
            a(context, COMMAND_SET_ACCOUNT, str, str2);
        }
    }

    public static boolean shouldUseMIUIPush(Context context) {
        return u.a(context).c();
    }

    public static void subscribe(Context context, String str, String str2) {
        if (!TextUtils.isEmpty(a.a(context).c()) && !TextUtils.isEmpty(str)) {
            if (Math.abs(System.currentTimeMillis() - topicSubscribedTime(context, str)) > 86400000) {
                org.apache.thrift.a akVar = new ak();
                akVar.a(a());
                akVar.b(a.a(context).c());
                akVar.c(str);
                akVar.d(context.getPackageName());
                akVar.e(str2);
                u.a(context).a(akVar, a.c, null);
            } else if (1 == PushMessageHelper.getPushMode(context)) {
                PushMessageHandler.a(context, str2, 0, null, str);
            } else {
                List arrayList = new ArrayList();
                arrayList.add(str);
                PushMessageHelper.sendCommandMessageBroadcast(context, PushMessageHelper.generateCommandMessage(COMMAND_SUBSCRIBE_TOPIC, arrayList, 0, null, null));
            }
        }
    }

    public static long topicSubscribedTime(Context context, String str) {
        return context.getSharedPreferences(PREF_EXTRA, 0).getLong("topic_" + str, -1);
    }

    public static void unregisterPush(Context context) {
        if (a.a(context).b()) {
            am amVar = new am();
            amVar.a(a());
            amVar.b(a.a(context).c());
            amVar.c(a.a(context).e());
            amVar.e(a.a(context).d());
            amVar.d(context.getPackageName());
            u.a(context).a(amVar);
            PushMessageHandler.a();
            a.a(context).k();
            a(context);
            clearLocalNotificationType(context);
            clearNotification(context);
            if (c != null) {
                k.a(context).b(c);
            }
        }
    }

    public static void unsetAlias(Context context, String str, String str2) {
        a(context, COMMAND_UNSET_ALIAS, str, str2);
    }

    public static void unsetUserAccount(Context context, String str, String str2) {
        a(context, COMMAND_UNSET_ACCOUNT, str, str2);
    }

    public static void unsubscribe(Context context, String str, String str2) {
        if (!a.a(context).b()) {
            return;
        }
        if (topicSubscribedTime(context, str) < 0) {
            com.xiaomi.channel.commonutils.logger.b.a("Don't cancel subscribe for " + str + " is unsubscribed");
            return;
        }
        org.apache.thrift.a aoVar = new ao();
        aoVar.a(a());
        aoVar.b(a.a(context).c());
        aoVar.c(str);
        aoVar.d(context.getPackageName());
        aoVar.e(str2);
        u.a(context).a(aoVar, a.d, null);
    }
}
