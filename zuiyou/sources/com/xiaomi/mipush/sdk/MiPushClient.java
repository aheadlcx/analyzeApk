package com.xiaomi.mipush.sdk;

import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences.Editor;
import android.content.pm.PackageInfo;
import android.content.pm.ServiceInfo;
import android.text.TextUtils;
import com.umeng.analytics.b.g;
import com.xiaomi.channel.commonutils.android.f;
import com.xiaomi.channel.commonutils.android.h;
import com.xiaomi.channel.commonutils.logger.b;
import com.xiaomi.channel.commonutils.string.d;
import com.xiaomi.mipush.sdk.MiTinyDataClient.a;
import com.xiaomi.push.mpcd.c;
import com.xiaomi.push.service.am;
import com.xiaomi.push.service.bb;
import com.xiaomi.push.service.module.PushChannelRegion;
import com.xiaomi.push.service.p;
import com.xiaomi.push.service.receivers.NetworkStatusReceiver;
import com.xiaomi.push.service.x;
import com.xiaomi.xmpush.thrift.ad;
import com.xiaomi.xmpush.thrift.ai;
import com.xiaomi.xmpush.thrift.aj;
import com.xiaomi.xmpush.thrift.ao;
import com.xiaomi.xmpush.thrift.aq;
import com.xiaomi.xmpush.thrift.r;
import com.xiaomi.xmpush.thrift.u;
import com.xiaomi.xmpush.thrift.w;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import tv.danmaku.ijk.media.player.IMediaPlayer;

public abstract class MiPushClient {
    private static boolean isOpenHmsPush = false;
    private static x mSyncMIIDHelper;
    private static Context sContext;
    private static long sCurMsgId = System.currentTimeMillis();

    @Deprecated
    public static abstract class MiPushClientCallback {
        private String category;

        protected String getCategory() {
            return this.category;
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

    public static long accountSetTime(Context context, String str) {
        return context.getSharedPreferences("mipush_extra", 0).getLong("account_" + str, -1);
    }

    static synchronized void addAcceptTime(Context context, String str, String str2) {
        synchronized (MiPushClient.class) {
            Editor edit = context.getSharedPreferences("mipush_extra", 0).edit();
            edit.putString("accept_time", str + "," + str2);
            p.a(edit);
        }
    }

    static synchronized void addAccount(Context context, String str) {
        synchronized (MiPushClient.class) {
            context.getSharedPreferences("mipush_extra", 0).edit().putLong("account_" + str, System.currentTimeMillis()).commit();
        }
    }

    static synchronized void addAlias(Context context, String str) {
        synchronized (MiPushClient.class) {
            context.getSharedPreferences("mipush_extra", 0).edit().putLong("alias_" + str, System.currentTimeMillis()).commit();
        }
    }

    private static void addPullNotificationTime(Context context) {
        Editor edit = context.getSharedPreferences("mipush_extra", 0).edit();
        edit.putLong("last_pull_notification", System.currentTimeMillis());
        p.a(edit);
    }

    private static void addRegRequestTime(Context context) {
        Editor edit = context.getSharedPreferences("mipush_extra", 0).edit();
        edit.putLong("last_reg_request", System.currentTimeMillis());
        p.a(edit);
    }

    static synchronized void addTopic(Context context, String str) {
        synchronized (MiPushClient.class) {
            context.getSharedPreferences("mipush_extra", 0).edit().putLong("topic_" + str, System.currentTimeMillis()).commit();
        }
    }

    public static long aliasSetTime(Context context, String str) {
        return context.getSharedPreferences("mipush_extra", 0).getLong("alias_" + str, -1);
    }

    public static void awakeApps(Context context, String[] strArr) {
        new Thread(new q(strArr, context)).start();
    }

    private static void awakePushServiceByPackageInfo(Context context, PackageInfo packageInfo) {
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

    private static void checkNotNull(Object obj, Object obj2) {
        if (obj != null) {
        }
    }

    private static void checkNotNull(Object obj, String str) {
        if (obj == null) {
            throw new IllegalArgumentException("param " + str + " is not nullable");
        }
    }

    protected static void clearExtras(Context context) {
        Editor edit = context.getSharedPreferences("mipush_extra", 0).edit();
        edit.clear();
        edit.commit();
    }

    public static void clearLocalNotificationType(Context context) {
        ac.a(context).g();
    }

    public static void clearNotification(Context context) {
        ac.a(context).a(-1);
    }

    public static void clearNotification(Context context, int i) {
        ac.a(context).a(i);
    }

    public static void clearNotification(Context context, String str, String str2) {
        ac.a(context).a(str, str2);
    }

    public static void disablePush(Context context) {
        ac.a(context).a(true);
    }

    private static void enableGeo(Context context, boolean z) {
        if (Math.abs(System.currentTimeMillis() - getGeoEnableTime(context, String.valueOf(z))) > 60000) {
            x.a(context, z);
            i.a(context, z);
            setGeoEnableTime(context, String.valueOf(z));
        }
    }

    public static void enablePush(Context context) {
        ac.a(context).a(false);
    }

    protected static synchronized String generatePacketID() {
        String str;
        synchronized (MiPushClient.class) {
            str = d.a(4) + sCurMsgId;
            sCurMsgId++;
        }
        return str;
    }

    protected static String getAcceptTime(Context context) {
        return context.getSharedPreferences("mipush_extra", 0).getString("accept_time", "00:00-23:59");
    }

    public static List<String> getAllAlias(Context context) {
        List<String> arrayList = new ArrayList();
        for (String str : context.getSharedPreferences("mipush_extra", 0).getAll().keySet()) {
            if (str.startsWith("alias_")) {
                arrayList.add(str.substring("alias_".length()));
            }
        }
        return arrayList;
    }

    public static List<String> getAllTopic(Context context) {
        List<String> arrayList = new ArrayList();
        for (String str : context.getSharedPreferences("mipush_extra", 0).getAll().keySet()) {
            if (str.startsWith("topic_") && !str.contains("**ALL**")) {
                arrayList.add(str.substring("topic_".length()));
            }
        }
        return arrayList;
    }

    public static List<String> getAllUserAccount(Context context) {
        List<String> arrayList = new ArrayList();
        for (String str : context.getSharedPreferences("mipush_extra", 0).getAll().keySet()) {
            if (str.startsWith("account_")) {
                arrayList.add(str.substring("account_".length()));
            }
        }
        return arrayList;
    }

    private static boolean getDefaultSwitch() {
        return f.b();
    }

    private static long getGeoEnableTime(Context context, String str) {
        return context.getSharedPreferences("mipush_extra", 0).getLong("geo_" + str, -1);
    }

    protected static boolean getOpenHmsPush() {
        return isOpenHmsPush;
    }

    public static String getRegId(Context context) {
        return c.a(context).i() ? c.a(context).e() : null;
    }

    @Deprecated
    public static void initialize(Context context, String str, String str2, MiPushClientCallback miPushClientCallback) {
        boolean z = false;
        checkNotNull((Object) context, g.aI);
        checkNotNull((Object) str, "appID");
        checkNotNull((Object) str2, "appToken");
        try {
            sContext = context.getApplicationContext();
            if (sContext == null) {
                sContext = context;
            }
            if (miPushClientCallback != null) {
                PushMessageHandler.a(miPushClientCallback);
            }
            if (h.b(context)) {
                k.a(context);
            }
            if (c.a(sContext).l() != h.a()) {
                z = true;
            }
            if (z || shouldSendRegRequest(sContext)) {
                if (z || !c.a(sContext).a(str, str2) || c.a(sContext).m()) {
                    String a = d.a(6);
                    c.a(sContext).h();
                    c.a(sContext).a(h.a());
                    c.a(sContext).a(str, str2, a);
                    a.a().b("com.xiaomi.xmpushsdk.tinydataPending.appId");
                    clearExtras(sContext);
                    aj ajVar = new aj();
                    ajVar.a(generatePacketID());
                    ajVar.b(str);
                    ajVar.e(str2);
                    ajVar.d(context.getPackageName());
                    ajVar.f(a);
                    ajVar.c(com.xiaomi.channel.commonutils.android.a.a(context, context.getPackageName()));
                    ajVar.b(com.xiaomi.channel.commonutils.android.a.b(context, context.getPackageName()));
                    ajVar.g("3_5_2");
                    ajVar.a(30502);
                    ajVar.h(com.xiaomi.channel.commonutils.android.d.b(sContext));
                    ajVar.a(w.c);
                    a = com.xiaomi.channel.commonutils.android.d.d(sContext);
                    Object f = com.xiaomi.channel.commonutils.android.d.f(sContext);
                    if (!TextUtils.isEmpty(a)) {
                        if (f.b()) {
                            if (!TextUtils.isEmpty(f)) {
                                a = a + "," + f;
                            }
                            ajVar.i(a);
                        }
                        ajVar.k(d.a(a) + "," + com.xiaomi.channel.commonutils.android.d.g(sContext));
                    }
                    ajVar.j(com.xiaomi.channel.commonutils.android.d.a());
                    int b = com.xiaomi.channel.commonutils.android.d.b();
                    if (b >= 0) {
                        ajVar.c(b);
                    }
                    ac.a(sContext).a(ajVar, z);
                    a.a(context);
                    bb.a(context).a("mipush_extra", "mipush_registed", true);
                } else {
                    if (1 == PushMessageHelper.getPushMode(context)) {
                        checkNotNull((Object) miPushClientCallback, "callback");
                        miPushClientCallback.onInitializeResult(0, null, c.a(context).e());
                    } else {
                        List arrayList = new ArrayList();
                        arrayList.add(c.a(context).e());
                        PushMessageHelper.sendCommandMessageBroadcast(sContext, PushMessageHelper.generateCommandMessage("register", arrayList, 0, null, null));
                    }
                    ac.a(context).a();
                    if (c.a(sContext).a()) {
                        org.apache.thrift.a aiVar = new ai();
                        aiVar.b(c.a(context).c());
                        aiVar.c("client_info_update");
                        aiVar.a(generatePacketID());
                        aiVar.h = new HashMap();
                        aiVar.h.put(g.d, com.xiaomi.channel.commonutils.android.a.a(sContext, sContext.getPackageName()));
                        aiVar.h.put("app_version_code", Integer.toString(com.xiaomi.channel.commonutils.android.a.b(sContext, sContext.getPackageName())));
                        aiVar.h.put("push_sdk_vn", "3_5_2");
                        aiVar.h.put("push_sdk_vc", Integer.toString(30502));
                        CharSequence g = c.a(sContext).g();
                        if (!TextUtils.isEmpty(g)) {
                            aiVar.h.put("deviceid", g);
                        }
                        ac.a(context).a(aiVar, com.xiaomi.xmpush.thrift.a.i, false, null);
                        a.a(context);
                    }
                    if (!com.xiaomi.channel.commonutils.android.g.a(sContext, "update_devId", false)) {
                        updateIMEI();
                        com.xiaomi.channel.commonutils.android.g.b(sContext, "update_devId", true);
                    }
                    if (shouldUseMIUIPush(sContext) && shouldPullNotification(sContext)) {
                        org.apache.thrift.a aiVar2 = new ai();
                        aiVar2.b(c.a(sContext).c());
                        aiVar2.c(r.j.W);
                        aiVar2.a(generatePacketID());
                        aiVar2.a(false);
                        ac.a(sContext).a(aiVar2, com.xiaomi.xmpush.thrift.a.i, false, null, false);
                        addPullNotificationTime(sContext);
                    }
                }
                addRegRequestTime(sContext);
                scheduleOcVersionCheckJob();
                scheduleGeoFenceLocUploadJobs();
                scheduleDataCollectionJobs(context);
                w.a(sContext);
                if (mSyncMIIDHelper == null) {
                    mSyncMIIDHelper = new x(sContext);
                }
                mSyncMIIDHelper.a(sContext);
                if ("syncing".equals(u.a(sContext).a(aj.a))) {
                    disablePush(sContext);
                }
                if ("syncing".equals(u.a(sContext).a(aj.b))) {
                    enablePush(sContext);
                }
                if ("syncing".equals(u.a(sContext).a(aj.c))) {
                    syncAssemblePushToken(sContext);
                    return;
                }
                return;
            }
            ac.a(context).a();
            b.a("Could not send  register message within 5s repeatly .");
        } catch (Exception e) {
            b.d(e.toString());
        } catch (Throwable th) {
            b.a(th);
        }
    }

    static void reInitialize(Context context, w wVar) {
        if (c.a(context).i()) {
            String a = d.a(6);
            String c = c.a(context).c();
            String d = c.a(context).d();
            c.a(context).h();
            c.a(context).a(c, d, a);
            aj ajVar = new aj();
            ajVar.a(generatePacketID());
            ajVar.b(c);
            ajVar.e(d);
            ajVar.f(a);
            ajVar.d(context.getPackageName());
            ajVar.c(com.xiaomi.channel.commonutils.android.a.a(context, context.getPackageName()));
            ajVar.a(wVar);
            ac.a(context).a(ajVar, false);
        }
    }

    private static void registerNetworkReceiver(Context context) {
        try {
            IntentFilter intentFilter = new IntentFilter();
            intentFilter.addAction("android.net.conn.CONNECTIVITY_CHANGE");
            intentFilter.addCategory("android.intent.category.DEFAULT");
            context.getApplicationContext().registerReceiver(new NetworkStatusReceiver(null), intentFilter);
        } catch (Throwable th) {
            b.a(th);
        }
    }

    public static void registerPush(Context context, String str, String str2) {
        registerPush(context, str, str2, new PushConfiguration());
    }

    public static void registerPush(Context context, String str, String str2, PushConfiguration pushConfiguration) {
        if (!NetworkStatusReceiver.a()) {
            registerNetworkReceiver(context);
        }
        Object region = pushConfiguration.getRegion();
        isOpenHmsPush = pushConfiguration.getOpenHmsPush();
        checkNotNull(region, PushChannelRegion.China);
        com.xiaomi.push.service.a.a(context).a(region);
        enableGeo(context, pushConfiguration.getGeoEnable());
        a.a();
        new Thread(new n(context, str, str2)).start();
    }

    static synchronized void removeAcceptTime(Context context) {
        synchronized (MiPushClient.class) {
            Editor edit = context.getSharedPreferences("mipush_extra", 0).edit();
            edit.remove("accept_time");
            p.a(edit);
        }
    }

    static synchronized void removeAccount(Context context, String str) {
        synchronized (MiPushClient.class) {
            context.getSharedPreferences("mipush_extra", 0).edit().remove("account_" + str).commit();
        }
    }

    static synchronized void removeAlias(Context context, String str) {
        synchronized (MiPushClient.class) {
            context.getSharedPreferences("mipush_extra", 0).edit().remove("alias_" + str).commit();
        }
    }

    static synchronized void removeAllAccounts(Context context) {
        synchronized (MiPushClient.class) {
            for (String removeAccount : getAllUserAccount(context)) {
                removeAccount(context, removeAccount);
            }
        }
    }

    static synchronized void removeAllAliases(Context context) {
        synchronized (MiPushClient.class) {
            for (String removeAlias : getAllAlias(context)) {
                removeAlias(context, removeAlias);
            }
        }
    }

    static synchronized void removeAllTopics(Context context) {
        synchronized (MiPushClient.class) {
            for (String removeTopic : getAllTopic(context)) {
                removeTopic(context, removeTopic);
            }
        }
    }

    static synchronized void removeTopic(Context context, String str) {
        synchronized (MiPushClient.class) {
            context.getSharedPreferences("mipush_extra", 0).edit().remove("topic_" + str).commit();
        }
    }

    static void reportIgnoreRegMessageClicked(Context context, String str, u uVar, String str2, String str3) {
        org.apache.thrift.a aiVar = new ai();
        if (TextUtils.isEmpty(str3)) {
            b.d("do not report clicked message");
            return;
        }
        aiVar.b(str3);
        aiVar.c("bar:click");
        aiVar.a(str);
        aiVar.a(false);
        ac.a(context).a(aiVar, com.xiaomi.xmpush.thrift.a.i, false, true, uVar, true, str2, str3);
    }

    public static void reportMessageClicked(Context context, MiPushMessage miPushMessage) {
        u uVar = new u();
        uVar.a(miPushMessage.getMessageId());
        uVar.b(miPushMessage.getTopic());
        uVar.d(miPushMessage.getDescription());
        uVar.c(miPushMessage.getTitle());
        uVar.c(miPushMessage.getNotifyId());
        uVar.a(miPushMessage.getNotifyType());
        uVar.b(miPushMessage.getPassThrough());
        uVar.a(miPushMessage.getExtra());
        reportMessageClicked(context, miPushMessage.getMessageId(), uVar, null);
    }

    static void reportMessageClicked(Context context, String str, u uVar, String str2) {
        Object aiVar = new ai();
        if (!TextUtils.isEmpty(str2)) {
            aiVar.b(str2);
        } else if (c.a(context).b()) {
            aiVar.b(c.a(context).c());
        } else {
            b.d("do not report clicked message");
            return;
        }
        aiVar.c("bar:click");
        aiVar.a(str);
        aiVar.a(false);
        ac.a(context).a(aiVar, com.xiaomi.xmpush.thrift.a.i, false, uVar);
    }

    private static void scheduleDataCollectionJobs(Context context) {
        if (am.a(sContext).a(com.xiaomi.xmpush.thrift.g.z.a(), getDefaultSwitch())) {
            c.a().a(new e(context));
            com.xiaomi.channel.commonutils.misc.h.a(sContext).a(new an(), 10);
        }
    }

    private static void scheduleGeoFenceLocUploadJobs() {
        if (x.e(sContext) && !TextUtils.equals("com.xiaomi.xmsf", sContext.getPackageName()) && am.a(sContext).a(com.xiaomi.xmpush.thrift.g.ac.a(), true) && !h.e()) {
            g.a(sContext, true);
            int max = Math.max(60, am.a(sContext).a(com.xiaomi.xmpush.thrift.g.O.a(), IMediaPlayer.MEDIA_INFO_TIMED_TEXT_ERROR));
            com.xiaomi.channel.commonutils.misc.h.a(sContext).a(new g(sContext, max), max, max);
        }
    }

    private static void scheduleOcVersionCheckJob() {
        com.xiaomi.channel.commonutils.misc.h.a(sContext).a(new t(sContext), am.a(sContext).a(com.xiaomi.xmpush.thrift.g.A.a(), 86400), 5);
    }

    public static void setAlias(Context context, String str, String str2) {
        if (!TextUtils.isEmpty(str)) {
            setCommand(context, "set-alias", str, str2);
        }
    }

    protected static void setCommand(Context context, String str, String str2, String str3) {
        ArrayList arrayList = new ArrayList();
        if (!TextUtils.isEmpty(str2)) {
            arrayList.add(str2);
        }
        if (!"set-alias".equalsIgnoreCase(str) || Math.abs(System.currentTimeMillis() - aliasSetTime(context, str2)) >= com.umeng.analytics.a.j) {
            if ("unset-alias".equalsIgnoreCase(str) && aliasSetTime(context, str2) < 0) {
                b.a("Don't cancel alias for " + arrayList + " is unseted");
            } else if (!"set-account".equalsIgnoreCase(str) || Math.abs(System.currentTimeMillis() - accountSetTime(context, str2)) >= com.umeng.analytics.a.j) {
                if (!"unset-account".equalsIgnoreCase(str) || accountSetTime(context, str2) >= 0) {
                    setCommand(context, str, arrayList, str3);
                } else {
                    b.a("Don't cancel account for " + arrayList + " is unseted");
                }
            } else if (1 == PushMessageHelper.getPushMode(context)) {
                PushMessageHandler.a(context, str3, str, 0, null, arrayList);
            } else {
                PushMessageHelper.sendCommandMessageBroadcast(context, PushMessageHelper.generateCommandMessage("set-account", arrayList, 0, null, null));
            }
        } else if (1 == PushMessageHelper.getPushMode(context)) {
            PushMessageHandler.a(context, str3, str, 0, null, arrayList);
        } else {
            PushMessageHelper.sendCommandMessageBroadcast(context, PushMessageHelper.generateCommandMessage("set-alias", arrayList, 0, null, null));
        }
    }

    protected static void setCommand(Context context, String str, ArrayList<String> arrayList, String str2) {
        if (!TextUtils.isEmpty(c.a(context).c())) {
            org.apache.thrift.a adVar = new ad();
            adVar.a(generatePacketID());
            adVar.b(c.a(context).c());
            adVar.c(str);
            Iterator it = arrayList.iterator();
            while (it.hasNext()) {
                adVar.d((String) it.next());
            }
            adVar.f(str2);
            adVar.e(context.getPackageName());
            ac.a(context).a(adVar, com.xiaomi.xmpush.thrift.a.j, null);
        }
    }

    static synchronized void setGeoEnableTime(Context context, String str) {
        synchronized (MiPushClient.class) {
            Editor edit = context.getSharedPreferences("mipush_extra", 0).edit();
            edit.putLong("geo_" + str, System.currentTimeMillis());
            p.a(edit);
        }
    }

    public static void setUserAccount(Context context, String str, String str2) {
        if (!TextUtils.isEmpty(str)) {
            setCommand(context, "set-account", str, str2);
        }
    }

    private static boolean shouldPullNotification(Context context) {
        return Math.abs(System.currentTimeMillis() - context.getSharedPreferences("mipush_extra", 0).getLong("last_pull_notification", -1)) > 300000;
    }

    private static boolean shouldSendRegRequest(Context context) {
        return Math.abs(System.currentTimeMillis() - context.getSharedPreferences("mipush_extra", 0).getLong("last_reg_request", -1)) > 5000;
    }

    public static boolean shouldUseMIUIPush(Context context) {
        return ac.a(context).d();
    }

    public static void subscribe(Context context, String str, String str2) {
        if (!TextUtils.isEmpty(c.a(context).c()) && !TextUtils.isEmpty(str)) {
            if (Math.abs(System.currentTimeMillis() - topicSubscribedTime(context, str)) > com.umeng.analytics.a.i) {
                org.apache.thrift.a aoVar = new ao();
                aoVar.a(generatePacketID());
                aoVar.b(c.a(context).c());
                aoVar.c(str);
                aoVar.d(context.getPackageName());
                aoVar.e(str2);
                ac.a(context).a(aoVar, com.xiaomi.xmpush.thrift.a.c, null);
            } else if (1 == PushMessageHelper.getPushMode(context)) {
                PushMessageHandler.a(context, str2, 0, null, str);
            } else {
                List arrayList = new ArrayList();
                arrayList.add(str);
                PushMessageHelper.sendCommandMessageBroadcast(context, PushMessageHelper.generateCommandMessage("subscribe-topic", arrayList, 0, null, null));
            }
        }
    }

    public static void syncAssemblePushToken(Context context) {
        ac.a(context).c();
    }

    public static long topicSubscribedTime(Context context, String str) {
        return context.getSharedPreferences("mipush_extra", 0).getLong("topic_" + str, -1);
    }

    public static void unregisterPush(Context context) {
        ak.d(context);
        if (c.a(context).b()) {
            aq aqVar = new aq();
            aqVar.a(generatePacketID());
            aqVar.b(c.a(context).c());
            aqVar.c(c.a(context).e());
            aqVar.e(c.a(context).d());
            aqVar.d(context.getPackageName());
            ac.a(context).a(aqVar);
            PushMessageHandler.a();
            c.a(context).j();
            clearLocalNotificationType(context);
            clearNotification(context);
            if (mSyncMIIDHelper != null) {
                p.a(context).b(mSyncMIIDHelper);
            }
            clearExtras(context);
        }
    }

    public static void unsetUserAccount(Context context, String str, String str2) {
        setCommand(context, "unset-account", str, str2);
    }

    private static void updateIMEI() {
        new Thread(new ap()).start();
    }
}
