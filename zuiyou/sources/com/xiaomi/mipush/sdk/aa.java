package com.xiaomi.mipush.sdk;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.net.Uri;
import android.text.TextUtils;
import com.tencent.wcdb.database.SQLiteDatabase;
import com.umeng.analytics.b.g;
import com.xiaomi.channel.commonutils.logger.b;
import com.xiaomi.channel.commonutils.string.d;
import com.xiaomi.push.service.ah;
import com.xiaomi.push.service.am;
import com.xiaomi.push.service.as;
import com.xiaomi.push.service.k;
import com.xiaomi.xmpush.thrift.ae;
import com.xiaomi.xmpush.thrift.af;
import com.xiaomi.xmpush.thrift.ag;
import com.xiaomi.xmpush.thrift.ai;
import com.xiaomi.xmpush.thrift.ak;
import com.xiaomi.xmpush.thrift.an;
import com.xiaomi.xmpush.thrift.ap;
import com.xiaomi.xmpush.thrift.ar;
import com.xiaomi.xmpush.thrift.at;
import com.xiaomi.xmpush.thrift.au;
import com.xiaomi.xmpush.thrift.r;
import com.xiaomi.xmpush.thrift.t;
import com.xiaomi.xmpush.thrift.u;
import com.xiaomi.xmpush.thrift.w;
import com.xiaomi.xmpush.thrift.z;
import java.io.Serializable;
import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.TimeZone;
import org.apache.thrift.a;
import org.apache.thrift.f;

public class aa {
    private static aa a = null;
    private static Queue<String> c;
    private static Object d = new Object();
    private Context b;

    private aa(Context context) {
        this.b = context.getApplicationContext();
        if (this.b == null) {
            this.b = context;
        }
    }

    public static Intent a(Context context, String str, Map<String, String> map) {
        URISyntaxException e;
        MalformedURLException malformedURLException;
        if (map == null || !map.containsKey("notify_effect")) {
            return null;
        }
        Intent launchIntentForPackage;
        String str2 = (String) map.get("notify_effect");
        if (as.a.equals(str2)) {
            try {
                launchIntentForPackage = context.getPackageManager().getLaunchIntentForPackage(str);
            } catch (Exception e2) {
                b.d("Cause: " + e2.getMessage());
                launchIntentForPackage = null;
            }
        } else {
            Intent intent;
            if (as.b.equals(str2)) {
                if (map.containsKey("intent_uri")) {
                    str2 = (String) map.get("intent_uri");
                    if (str2 != null) {
                        try {
                            launchIntentForPackage = Intent.parseUri(str2, 1);
                            try {
                                launchIntentForPackage.setPackage(str);
                            } catch (URISyntaxException e3) {
                                e = e3;
                                b.d("Cause: " + e.getMessage());
                                if (launchIntentForPackage == null) {
                                    return null;
                                }
                                launchIntentForPackage.addFlags(SQLiteDatabase.CREATE_IF_NECESSARY);
                                try {
                                    return context.getPackageManager().resolveActivity(launchIntentForPackage, 65536) != null ? null : launchIntentForPackage;
                                } catch (Exception e22) {
                                    b.d("Cause: " + e22.getMessage());
                                    return null;
                                }
                            }
                        } catch (URISyntaxException e4) {
                            e = e4;
                            launchIntentForPackage = null;
                            b.d("Cause: " + e.getMessage());
                            if (launchIntentForPackage == null) {
                                return null;
                            }
                            launchIntentForPackage.addFlags(SQLiteDatabase.CREATE_IF_NECESSARY);
                            if (context.getPackageManager().resolveActivity(launchIntentForPackage, 65536) != null) {
                            }
                        }
                    }
                    launchIntentForPackage = null;
                } else if (map.containsKey("class_name")) {
                    str2 = (String) map.get("class_name");
                    intent = new Intent();
                    intent.setComponent(new ComponentName(str, str2));
                    try {
                        if (map.containsKey("intent_flag")) {
                            intent.setFlags(Integer.parseInt((String) map.get("intent_flag")));
                        }
                    } catch (NumberFormatException e5) {
                        b.d("Cause by intent_flag: " + e5.getMessage());
                    }
                    launchIntentForPackage = intent;
                }
            } else if (as.c.equals(str2)) {
                str2 = (String) map.get("web_uri");
                if (str2 != null) {
                    str2 = str2.trim();
                    String str3 = (str2.startsWith("http://") || str2.startsWith("https://")) ? str2 : "http://" + str2;
                    try {
                        str2 = new URL(str3).getProtocol();
                        if ("http".equals(str2) || "https".equals(str2)) {
                            launchIntentForPackage = new Intent("android.intent.action.VIEW");
                            try {
                                launchIntentForPackage.setData(Uri.parse(str3));
                            } catch (MalformedURLException e6) {
                                MalformedURLException malformedURLException2 = e6;
                                intent = launchIntentForPackage;
                                malformedURLException = malformedURLException2;
                                b.d("Cause: " + malformedURLException.getMessage());
                                launchIntentForPackage = intent;
                                if (launchIntentForPackage == null) {
                                    return null;
                                }
                                launchIntentForPackage.addFlags(SQLiteDatabase.CREATE_IF_NECESSARY);
                                if (context.getPackageManager().resolveActivity(launchIntentForPackage, 65536) != null) {
                                }
                            }
                        }
                        launchIntentForPackage = null;
                    } catch (MalformedURLException e7) {
                        malformedURLException = e7;
                        intent = null;
                        b.d("Cause: " + malformedURLException.getMessage());
                        launchIntentForPackage = intent;
                        if (launchIntentForPackage == null) {
                            return null;
                        }
                        launchIntentForPackage.addFlags(SQLiteDatabase.CREATE_IF_NECESSARY);
                        if (context.getPackageManager().resolveActivity(launchIntentForPackage, 65536) != null) {
                        }
                    }
                }
            }
            launchIntentForPackage = null;
        }
        if (launchIntentForPackage == null) {
            return null;
        }
        launchIntentForPackage.addFlags(SQLiteDatabase.CREATE_IF_NECESSARY);
        if (context.getPackageManager().resolveActivity(launchIntentForPackage, 65536) != null) {
        }
    }

    private a a(af afVar, boolean z, byte[] bArr) {
        a aVar = null;
        try {
            a a = aq.a(this.b, afVar);
            if (a == null) {
                b.d("receiving an un-recognized message. " + afVar.a);
                return null;
            }
            b.c("receive a message." + a);
            com.xiaomi.xmpush.thrift.a a2 = afVar.a();
            b.a("processing a message, action=" + a2);
            String b;
            String str;
            List list;
            switch (ab.a[a2.ordinal()]) {
                case 1:
                    if (!c.a(this.b).k() || z) {
                        an anVar = (an) a;
                        t l = anVar.l();
                        if (l == null) {
                            b.d("receive an empty message without push content, drop it");
                            return null;
                        }
                        if (z) {
                            if (ah.b(afVar)) {
                                MiPushClient.reportIgnoreRegMessageClicked(this.b, l.b(), afVar.m(), afVar.f, l.d());
                            } else {
                                MiPushClient.reportMessageClicked(this.b, l.b(), afVar.m(), l.d());
                            }
                        }
                        if (!z) {
                            if (!TextUtils.isEmpty(anVar.j()) && MiPushClient.aliasSetTime(this.b, anVar.j()) < 0) {
                                MiPushClient.addAlias(this.b, anVar.j());
                            } else if (!TextUtils.isEmpty(anVar.h()) && MiPushClient.topicSubscribedTime(this.b, anVar.h()) < 0) {
                                MiPushClient.addTopic(this.b, anVar.h());
                            }
                        }
                        CharSequence charSequence = (afVar.h == null || afVar.h.s() == null) ? null : (String) afVar.h.j.get("jobkey");
                        if (TextUtils.isEmpty(charSequence)) {
                            b = l.b();
                        } else {
                            CharSequence charSequence2 = charSequence;
                        }
                        if (z || !b(this.b, b)) {
                            Serializable generateMessage = PushMessageHelper.generateMessage(anVar, afVar.m(), z);
                            if (generateMessage.getPassThrough() == 0 && !z && ah.a(generateMessage.getExtra())) {
                                ah.a(this.b, afVar, bArr);
                                return null;
                            }
                            b.a("receive a message, msgid=" + l.b() + ", jobkey=" + b);
                            if (z && generateMessage.getExtra() != null && generateMessage.getExtra().containsKey("notify_effect")) {
                                Map extra = generateMessage.getExtra();
                                str = (String) extra.get("notify_effect");
                                if (ah.b(afVar)) {
                                    Intent a3 = a(this.b, afVar.f, extra);
                                    if (a3 == null) {
                                        b.a("Getting Intent fail from ignore reg message. ");
                                        return null;
                                    }
                                    Object f = l.f();
                                    if (!TextUtils.isEmpty(f)) {
                                        a3.putExtra("payload", f);
                                    }
                                    this.b.startActivity(a3);
                                    return null;
                                }
                                Intent a4 = a(this.b, this.b.getPackageName(), extra);
                                if (a4 == null) {
                                    return null;
                                }
                                if (!str.equals(as.c)) {
                                    a4.putExtra("key_message", generateMessage);
                                }
                                this.b.startActivity(a4);
                                return null;
                            }
                            Serializable serializable = generateMessage;
                        } else {
                            b.a("drop a duplicate message, key=" + b);
                        }
                        if (afVar.m() != null || z) {
                            return aVar;
                        }
                        a(anVar, afVar);
                        return aVar;
                    }
                    b.a("receive a message in pause state. drop it");
                    return null;
                case 2:
                    ak akVar = (ak) a;
                    if (akVar.f == 0) {
                        c.a(this.b).b(akVar.h, akVar.i);
                    }
                    if (TextUtils.isEmpty(akVar.h)) {
                        list = null;
                    } else {
                        list = new ArrayList();
                        list.add(akVar.h);
                    }
                    aVar = PushMessageHelper.generateCommandMessage("register", list, akVar.f, akVar.g, null);
                    ac.a(this.b).f();
                    return aVar;
                case 3:
                    if (((ar) a).f == 0) {
                        c.a(this.b).h();
                        MiPushClient.clearExtras(this.b);
                    }
                    PushMessageHandler.a();
                    return null;
                case 4:
                    ap apVar = (ap) a;
                    if (apVar.f == 0) {
                        MiPushClient.addTopic(this.b, apVar.h());
                    }
                    if (TextUtils.isEmpty(apVar.h())) {
                        list = null;
                    } else {
                        list = new ArrayList();
                        list.add(apVar.h());
                    }
                    return PushMessageHelper.generateCommandMessage("subscribe-topic", list, apVar.f, apVar.g, apVar.k());
                case 5:
                    at atVar = (at) a;
                    if (atVar.f == 0) {
                        MiPushClient.removeTopic(this.b, atVar.h());
                    }
                    if (TextUtils.isEmpty(atVar.h())) {
                        list = null;
                    } else {
                        list = new ArrayList();
                        list.add(atVar.h());
                    }
                    return PushMessageHelper.generateCommandMessage("unsubscibe-topic", list, atVar.f, atVar.g, atVar.k());
                case 6:
                    ae aeVar = (ae) a;
                    Object e = aeVar.e();
                    List k = aeVar.k();
                    if (aeVar.g == 0) {
                        if (TextUtils.equals(e, "accept-time") && k != null && k.size() > 1) {
                            MiPushClient.addAcceptTime(this.b, (String) k.get(0), (String) k.get(1));
                            if ("00:00".equals(k.get(0)) && "00:00".equals(k.get(1))) {
                                c.a(this.b).a(true);
                            } else {
                                c.a(this.b).a(false);
                            }
                            list = a(TimeZone.getTimeZone("GMT+08"), TimeZone.getDefault(), k);
                            return PushMessageHelper.generateCommandMessage(e, list, aeVar.g, aeVar.h, aeVar.m());
                        } else if (TextUtils.equals(e, "set-alias") && k != null && k.size() > 0) {
                            MiPushClient.addAlias(this.b, (String) k.get(0));
                            list = k;
                            return PushMessageHelper.generateCommandMessage(e, list, aeVar.g, aeVar.h, aeVar.m());
                        } else if (TextUtils.equals(e, "unset-alias") && k != null && k.size() > 0) {
                            MiPushClient.removeAlias(this.b, (String) k.get(0));
                            list = k;
                            return PushMessageHelper.generateCommandMessage(e, list, aeVar.g, aeVar.h, aeVar.m());
                        } else if (TextUtils.equals(e, "set-account") && k != null && k.size() > 0) {
                            MiPushClient.addAccount(this.b, (String) k.get(0));
                            list = k;
                            return PushMessageHelper.generateCommandMessage(e, list, aeVar.g, aeVar.h, aeVar.m());
                        } else if (TextUtils.equals(e, "unset-account") && k != null && k.size() > 0) {
                            MiPushClient.removeAccount(this.b, (String) k.get(0));
                        }
                    }
                    list = k;
                    return PushMessageHelper.generateCommandMessage(e, list, aeVar.g, aeVar.h, aeVar.m());
                case 7:
                    String c;
                    if (a instanceof com.xiaomi.xmpush.thrift.aa) {
                        com.xiaomi.xmpush.thrift.aa aaVar = (com.xiaomi.xmpush.thrift.aa) a;
                        c = aaVar.c();
                        if (r.DisablePushMessage.W.equalsIgnoreCase(aaVar.e)) {
                            if (aaVar.g == 0) {
                                synchronized (u.class) {
                                    if (u.a(this.b).e(c)) {
                                        u.a(this.b).d(c);
                                        if ("syncing".equals(u.a(this.b).a(aj.DISABLE_PUSH))) {
                                            u.a(this.b).a(aj.DISABLE_PUSH, "synced");
                                            MiPushClient.clearNotification(this.b);
                                            MiPushClient.clearLocalNotificationType(this.b);
                                            PushMessageHandler.a();
                                            ac.a(this.b).b();
                                        }
                                    }
                                }
                                return null;
                            } else if ("syncing".equals(u.a(this.b).a(aj.DISABLE_PUSH))) {
                                synchronized (u.class) {
                                    if (u.a(this.b).e(c)) {
                                        if (u.a(this.b).c(c) < 10) {
                                            u.a(this.b).b(c);
                                            ac.a(this.b).a(true, c);
                                        } else {
                                            u.a(this.b).d(c);
                                        }
                                    }
                                }
                                return null;
                            } else {
                                u.a(this.b).d(c);
                                return null;
                            }
                        } else if (r.EnablePushMessage.W.equalsIgnoreCase(aaVar.e)) {
                            if (aaVar.g == 0) {
                                synchronized (u.class) {
                                    if (u.a(this.b).e(c)) {
                                        u.a(this.b).d(c);
                                        if ("syncing".equals(u.a(this.b).a(aj.ENABLE_PUSH))) {
                                            u.a(this.b).a(aj.ENABLE_PUSH, "synced");
                                        }
                                    }
                                }
                                return null;
                            } else if ("syncing".equals(u.a(this.b).a(aj.ENABLE_PUSH))) {
                                synchronized (u.class) {
                                    if (u.a(this.b).e(c)) {
                                        if (u.a(this.b).c(c) < 10) {
                                            u.a(this.b).b(c);
                                            ac.a(this.b).a(false, c);
                                        } else {
                                            u.a(this.b).d(c);
                                        }
                                    }
                                }
                                return null;
                            } else {
                                u.a(this.b).d(c);
                                return null;
                            }
                        } else if (!r.ThirdPartyRegUpdate.W.equalsIgnoreCase(aaVar.e)) {
                            return null;
                        } else {
                            if (aaVar.g == 0) {
                                synchronized (u.class) {
                                    if (u.a(this.b).e(c)) {
                                        u.a(this.b).d(c);
                                        if ("syncing".equals(u.a(this.b).a(aj.UPLOAD_TOKEN))) {
                                            u.a(this.b).a(aj.UPLOAD_TOKEN, "synced");
                                        }
                                    }
                                }
                                return null;
                            } else if ("syncing".equals(u.a(this.b).a(aj.UPLOAD_TOKEN))) {
                                synchronized (u.class) {
                                    if (u.a(this.b).e(c)) {
                                        if (u.a(this.b).c(c) < 10) {
                                            u.a(this.b).b(c);
                                            ac.a(this.b).a(c);
                                        } else {
                                            u.a(this.b).d(c);
                                        }
                                    }
                                }
                                return null;
                            } else {
                                u.a(this.b).d(c);
                                return null;
                            }
                        }
                    } else if (!(a instanceof ai)) {
                        return null;
                    } else {
                        ai aiVar = (ai) a;
                        if ("registration id expired".equalsIgnoreCase(aiVar.e)) {
                            List<String> allAlias = MiPushClient.getAllAlias(this.b);
                            List<String> allTopic = MiPushClient.getAllTopic(this.b);
                            List<String> allUserAccount = MiPushClient.getAllUserAccount(this.b);
                            String acceptTime = MiPushClient.getAcceptTime(this.b);
                            MiPushClient.reInitialize(this.b, w.RegIdExpired);
                            for (String str2 : allAlias) {
                                MiPushClient.setAlias(this.b, str2, null);
                            }
                            for (String str22 : allTopic) {
                                MiPushClient.subscribe(this.b, str22, null);
                            }
                            for (String str222 : allUserAccount) {
                                MiPushClient.setUserAccount(this.b, str222, null);
                            }
                            String[] split = acceptTime.split(",");
                            if (split.length != 2) {
                                return null;
                            }
                            MiPushClient.addAcceptTime(this.b, split[0], split[1]);
                            return null;
                        } else if ("client_info_update_ok".equalsIgnoreCase(aiVar.e)) {
                            if (aiVar.i() == null || !aiVar.i().containsKey(g.d)) {
                                return null;
                            }
                            c.a(this.b).a((String) aiVar.i().get(g.d));
                            return null;
                        } else if ("awake_app".equalsIgnoreCase(aiVar.e)) {
                            if (aiVar.i() == null || !aiVar.i().containsKey("packages")) {
                                return null;
                            }
                            MiPushClient.awakeApps(this.b, ((String) aiVar.i().get("packages")).split(","));
                            return null;
                        } else if (r.NormalClientConfigUpdate.W.equalsIgnoreCase(aiVar.e)) {
                            com.xiaomi.xmpush.thrift.ah ahVar = new com.xiaomi.xmpush.thrift.ah();
                            try {
                                au.a((a) ahVar, aiVar.m());
                                k.a(am.a(this.b), ahVar);
                                return null;
                            } catch (Throwable e2) {
                                b.a(e2);
                                return null;
                            }
                        } else if (r.CustomClientConfigUpdate.W.equalsIgnoreCase(aiVar.e)) {
                            ag agVar = new ag();
                            try {
                                au.a((a) agVar, aiVar.m());
                                k.a(am.a(this.b), agVar);
                                return null;
                            } catch (Throwable e22) {
                                b.a(e22);
                                return null;
                            }
                        } else if (r.SyncInfoResult.W.equalsIgnoreCase(aiVar.e)) {
                            w.a(this.b, aiVar);
                            return null;
                        } else if (r.ForceSync.W.equalsIgnoreCase(aiVar.e)) {
                            b.a("receive force sync notification");
                            w.a(this.b, false);
                            return null;
                        } else if (r.GeoRegsiter.W.equalsIgnoreCase(aiVar.e)) {
                            i.a(this.b).a(aiVar);
                            return null;
                        } else if (r.GeoUnregsiter.W.equalsIgnoreCase(aiVar.e)) {
                            i.a(this.b).b(aiVar);
                            return null;
                        } else if (r.GeoSync.W.equalsIgnoreCase(aiVar.e)) {
                            i.a(this.b).c(aiVar);
                            return null;
                        } else if (r.CancelPushMessage.W.equals(aiVar.e)) {
                            c = "";
                            if (aiVar.i() == null) {
                                return null;
                            }
                            int parseInt;
                            if (aiVar.i().containsKey(as.I)) {
                                c = (String) aiVar.i().get(as.I);
                                if (!TextUtils.isEmpty(c)) {
                                    try {
                                        parseInt = Integer.parseInt(c);
                                    } catch (NumberFormatException e3) {
                                        e3.printStackTrace();
                                        parseInt = -2;
                                    }
                                    if (parseInt < -1) {
                                        MiPushClient.clearNotification(this.b, parseInt);
                                        return null;
                                    }
                                    c = "";
                                    b = "";
                                    if (aiVar.i().containsKey(as.G)) {
                                        c = (String) aiVar.i().get(as.G);
                                    }
                                    MiPushClient.clearNotification(this.b, c, aiVar.i().containsKey(as.H) ? (String) aiVar.i().get(as.H) : b);
                                    return null;
                                }
                            }
                            parseInt = -2;
                            if (parseInt < -1) {
                                c = "";
                                b = "";
                                if (aiVar.i().containsKey(as.G)) {
                                    c = (String) aiVar.i().get(as.G);
                                }
                                if (aiVar.i().containsKey(as.H)) {
                                }
                                MiPushClient.clearNotification(this.b, c, aiVar.i().containsKey(as.H) ? (String) aiVar.i().get(as.H) : b);
                                return null;
                            }
                            MiPushClient.clearNotification(this.b, parseInt);
                            return null;
                        } else if (r.HybridRegisterResult.W.equals(aiVar.e)) {
                            try {
                                r1 = new ak();
                                au.a(r1, aiVar.m());
                                MiPushClient4Hybrid.onReceiveRegisterResult(this.b, r1);
                                return null;
                            } catch (Throwable e222) {
                                b.a(e222);
                                return null;
                            }
                        } else if (!r.HybridUnregisterResult.W.equals(aiVar.e)) {
                            return null;
                        } else {
                            try {
                                r1 = new ar();
                                au.a(r1, aiVar.m());
                                MiPushClient4Hybrid.onReceiveUnregisterResult(this.b, r1);
                                return null;
                            } catch (Throwable e2222) {
                                b.a(e2222);
                                return null;
                            }
                        }
                    }
                default:
                    return null;
            }
        } catch (Throwable e22222) {
            b.a(e22222);
            a(afVar);
            return null;
        } catch (Throwable e222222) {
            b.a(e222222);
            b.d("receive a message which action string is not valid. is the reg expired?");
            return null;
        }
    }

    private a a(af afVar, byte[] bArr) {
        String str = null;
        try {
            a a = aq.a(this.b, afVar);
            if (a == null) {
                b.d("message arrived: receiving an un-recognized message. " + afVar.a);
                return null;
            }
            b.c("message arrived: receive a message." + a);
            com.xiaomi.xmpush.thrift.a a2 = afVar.a();
            b.a("message arrived: processing an arrived message, action=" + a2);
            switch (ab.a[a2.ordinal()]) {
                case 1:
                    an anVar = (an) a;
                    t l = anVar.l();
                    if (l == null) {
                        b.d("message arrived: receive an empty message without push content, drop it");
                        return null;
                    }
                    if (!(afVar.h == null || afVar.h.s() == null)) {
                        str = (String) afVar.h.j.get("jobkey");
                    }
                    MiPushMessage generateMessage = PushMessageHelper.generateMessage(anVar, afVar.m(), false);
                    generateMessage.setArrivedMessage(true);
                    b.a("message arrived: receive a message, msgid=" + l.b() + ", jobkey=" + str);
                    return generateMessage;
                default:
                    return null;
            }
        } catch (Throwable e) {
            b.a(e);
            b.d("message arrived: receive a message but decrypt failed. report when click.");
            return null;
        } catch (Throwable e2) {
            b.a(e2);
            b.d("message arrived: receive a message which action string is not valid. is the reg expired?");
            return null;
        }
    }

    public static aa a(Context context) {
        if (a == null) {
            a = new aa(context);
        }
        return a;
    }

    private void a() {
        SharedPreferences sharedPreferences = this.b.getSharedPreferences("mipush_extra", 0);
        long currentTimeMillis = System.currentTimeMillis();
        if (Math.abs(currentTimeMillis - sharedPreferences.getLong("last_reinitialize", 0)) > 1800000) {
            MiPushClient.reInitialize(this.b, w.PackageUnregistered);
            sharedPreferences.edit().putLong("last_reinitialize", currentTimeMillis).commit();
        }
    }

    private void a(af afVar) {
        b.a("receive a message but decrypt failed. report now.");
        a aiVar = new ai(afVar.m().a, false);
        aiVar.c(r.DecryptMessageFail.W);
        aiVar.b(afVar.h());
        aiVar.d(afVar.f);
        aiVar.h = new HashMap();
        aiVar.h.put("regid", MiPushClient.getRegId(this.b));
        ac.a(this.b).a(aiVar, com.xiaomi.xmpush.thrift.a.Notification, false, null);
    }

    private void a(an anVar, af afVar) {
        u m = afVar.m();
        a zVar = new z();
        zVar.b(anVar.e());
        zVar.a(anVar.c());
        zVar.a(anVar.l().h());
        if (!TextUtils.isEmpty(anVar.h())) {
            zVar.c(anVar.h());
        }
        if (!TextUtils.isEmpty(anVar.j())) {
            zVar.d(anVar.j());
        }
        zVar.a(au.a(this.b, afVar));
        ac.a(this.b).a(zVar, com.xiaomi.xmpush.thrift.a.AckMessage, m);
    }

    private void b(af afVar) {
        u m = afVar.m();
        a zVar = new z();
        zVar.b(afVar.h());
        zVar.a(m.b());
        zVar.a(m.d());
        if (!TextUtils.isEmpty(m.f())) {
            zVar.c(m.f());
        }
        zVar.a(au.a(this.b, afVar));
        ac.a(this.b).a(zVar, com.xiaomi.xmpush.thrift.a.AckMessage, false, afVar.m());
    }

    private static boolean b(Context context, String str) {
        boolean z = false;
        synchronized (d) {
            c.a(context);
            SharedPreferences b = c.b(context);
            if (c == null) {
                String[] split = b.getString("pref_msg_ids", "").split(",");
                c = new LinkedList();
                for (Object add : split) {
                    c.add(add);
                }
            }
            if (c.contains(str)) {
                z = true;
            } else {
                c.add(str);
                if (c.size() > 25) {
                    c.poll();
                }
                String a = d.a(c, ",");
                Editor edit = b.edit();
                edit.putString("pref_msg_ids", a);
                p.a(edit);
            }
        }
        return z;
    }

    private boolean c(af afVar) {
        return TextUtils.equals("com.miui.hybrid", afVar.j()) && afVar.m() != null && afVar.m().s() != null && TextUtils.equals("1", (CharSequence) afVar.m().s().get("hybrid_pt"));
    }

    public a a(Intent intent) {
        String action = intent.getAction();
        b.a("receive an intent from server, action=" + action);
        String stringExtra = intent.getStringExtra("mrt");
        if (stringExtra == null) {
            stringExtra = Long.toString(System.currentTimeMillis());
        }
        byte[] byteArrayExtra;
        if ("com.xiaomi.mipush.RECEIVE_MESSAGE".equals(action)) {
            byteArrayExtra = intent.getByteArrayExtra("mipush_payload");
            boolean booleanExtra = intent.getBooleanExtra("mipush_notified", false);
            if (byteArrayExtra == null) {
                b.d("receiving an empty message, drop");
                return null;
            }
            af afVar = new af();
            try {
                au.a((a) afVar, byteArrayExtra);
                c a = c.a(this.b);
                u m = afVar.m();
                if (!(afVar.a() != com.xiaomi.xmpush.thrift.a.SendMessage || m == null || a.k() || booleanExtra)) {
                    m.a("mrt", stringExtra);
                    m.a("mat", Long.toString(System.currentTimeMillis()));
                    if (c(afVar)) {
                        b.b("this is a mina's pass through message, ack later");
                        m.a("__mina_message_ts", String.valueOf(m.d()));
                        m.a("__mina_device_status", String.valueOf(au.a(this.b, afVar)));
                        m.a("__mina_appid", afVar.h());
                    } else {
                        b(afVar);
                    }
                }
                if (afVar.a() == com.xiaomi.xmpush.thrift.a.SendMessage && !afVar.c()) {
                    if (!ah.b(afVar)) {
                        action = "drop an un-encrypted messages. %1$s, %2$s";
                        Object[] objArr = new Object[2];
                        objArr[0] = afVar.j();
                        objArr[1] = m != null ? m.b() : "";
                        b.a(String.format(action, objArr));
                        return null;
                    } else if (!(booleanExtra && m.s() != null && m.s().containsKey("notify_effect"))) {
                        b.a(String.format("drop an un-encrypted messages. %1$s, %2$s", new Object[]{afVar.j(), m.b()}));
                        return null;
                    }
                }
                if (a.i() || afVar.a == com.xiaomi.xmpush.thrift.a.Registration) {
                    if (!a.i() || !a.m()) {
                        return a(afVar, booleanExtra, byteArrayExtra);
                    }
                    if (afVar.a == com.xiaomi.xmpush.thrift.a.UnRegistration) {
                        a.h();
                        MiPushClient.clearExtras(this.b);
                        PushMessageHandler.a();
                    } else {
                        MiPushClient.unregisterPush(this.b);
                    }
                } else if (ah.b(afVar)) {
                    return a(afVar, booleanExtra, byteArrayExtra);
                } else {
                    b.d("receive message without registration. need re-register!");
                    a();
                }
            } catch (Throwable e) {
                b.a(e);
            } catch (Throwable e2) {
                b.a(e2);
            }
        } else if ("com.xiaomi.mipush.ERROR".equals(action)) {
            a miPushCommandMessage = new MiPushCommandMessage();
            a afVar2 = new af();
            try {
                byteArrayExtra = intent.getByteArrayExtra("mipush_payload");
                if (byteArrayExtra != null) {
                    au.a(afVar2, byteArrayExtra);
                }
            } catch (f e3) {
            }
            miPushCommandMessage.setCommand(String.valueOf(afVar2.a()));
            miPushCommandMessage.setResultCode((long) intent.getIntExtra("mipush_error_code", 0));
            miPushCommandMessage.setReason(intent.getStringExtra("mipush_error_msg"));
            b.d("receive a error message. code = " + intent.getIntExtra("mipush_error_code", 0) + ", msg= " + intent.getStringExtra("mipush_error_msg"));
            return miPushCommandMessage;
        } else if ("com.xiaomi.mipush.MESSAGE_ARRIVED".equals(action)) {
            byte[] byteArrayExtra2 = intent.getByteArrayExtra("mipush_payload");
            if (byteArrayExtra2 == null) {
                b.d("message arrived: receiving an empty message, drop");
                return null;
            }
            af afVar3 = new af();
            try {
                au.a((a) afVar3, byteArrayExtra2);
                c a2 = c.a(this.b);
                if (ah.b(afVar3)) {
                    b.d("message arrived: receive ignore reg message, ignore!");
                } else if (!a2.i()) {
                    b.d("message arrived: receive message without registration. need unregister or re-register!");
                } else if (!a2.i() || !a2.m()) {
                    return a(afVar3, byteArrayExtra2);
                } else {
                    b.d("message arrived: app info is invalidated");
                }
            } catch (Throwable e22) {
                b.a(e22);
            } catch (Throwable e222) {
                b.a(e222);
            }
        }
        return null;
    }

    public List<String> a(TimeZone timeZone, TimeZone timeZone2, List<String> list) {
        if (timeZone.equals(timeZone2)) {
            return list;
        }
        long rawOffset = (long) (((timeZone.getRawOffset() - timeZone2.getRawOffset()) / 1000) / 60);
        long parseLong = Long.parseLong(((String) list.get(0)).split(":")[0]);
        long parseLong2 = Long.parseLong(((String) list.get(0)).split(":")[1]);
        parseLong = ((((parseLong * 60) + parseLong2) - rawOffset) + 1440) % 1440;
        long parseLong3 = (((Long.parseLong(((String) list.get(1)).split(":")[1]) + (60 * Long.parseLong(((String) list.get(1)).split(":")[0]))) - rawOffset) + 1440) % 1440;
        List arrayList = new ArrayList();
        List list2 = arrayList;
        list2.add(String.format("%1$02d:%2$02d", new Object[]{Long.valueOf(parseLong / 60), Long.valueOf(parseLong % 60)}));
        list2 = arrayList;
        list2.add(String.format("%1$02d:%2$02d", new Object[]{Long.valueOf(parseLong3 / 60), Long.valueOf(parseLong3 % 60)}));
        return arrayList;
    }
}
