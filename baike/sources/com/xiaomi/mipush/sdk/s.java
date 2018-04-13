package com.xiaomi.mipush.sdk;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.net.Uri;
import android.text.TextUtils;
import com.baidu.mobstat.Config;
import com.xiaomi.channel.commonutils.logger.b;
import com.xiaomi.channel.commonutils.string.d;
import com.xiaomi.push.service.ac;
import com.xiaomi.push.service.ah;
import com.xiaomi.push.service.ai;
import com.xiaomi.push.service.am;
import com.xiaomi.xmpush.thrift.aa;
import com.xiaomi.xmpush.thrift.ab;
import com.xiaomi.xmpush.thrift.ad;
import com.xiaomi.xmpush.thrift.ae;
import com.xiaomi.xmpush.thrift.ag;
import com.xiaomi.xmpush.thrift.aj;
import com.xiaomi.xmpush.thrift.al;
import com.xiaomi.xmpush.thrift.an;
import com.xiaomi.xmpush.thrift.ap;
import com.xiaomi.xmpush.thrift.aq;
import com.xiaomi.xmpush.thrift.o;
import com.xiaomi.xmpush.thrift.q;
import com.xiaomi.xmpush.thrift.r;
import com.xiaomi.xmpush.thrift.t;
import com.xiaomi.xmpush.thrift.w;
import com.xiaomi.xmpush.thrift.x;
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
import org.eclipse.paho.client.mqttv3.internal.ClientDefaults;

public class s {
    private static s a = null;
    private static Queue<String> c;
    private static Object d = new Object();
    private Context b;

    private s(Context context) {
        this.b = context.getApplicationContext();
        if (this.b == null) {
            this.b = context;
        }
    }

    public static Intent a(Context context, String str, Map<String, String> map) {
        Intent launchIntentForPackage;
        URISyntaxException e;
        MalformedURLException malformedURLException;
        if (map == null || !map.containsKey("notify_effect")) {
            return null;
        }
        String str2 = (String) map.get("notify_effect");
        if (am.a.equals(str2)) {
            try {
                launchIntentForPackage = context.getPackageManager().getLaunchIntentForPackage(str);
            } catch (Exception e2) {
                b.d("Cause: " + e2.getMessage());
                launchIntentForPackage = null;
            }
        } else {
            Intent intent;
            if (am.b.equals(str2)) {
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
                                launchIntentForPackage.addFlags(ClientDefaults.MAX_MSG_SIZE);
                                try {
                                    return context.getPackageManager().resolveActivity(launchIntentForPackage, 65536) != null ? launchIntentForPackage : null;
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
                            launchIntentForPackage.addFlags(ClientDefaults.MAX_MSG_SIZE);
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
            } else if (am.c.equals(str2)) {
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
                                launchIntentForPackage.addFlags(ClientDefaults.MAX_MSG_SIZE);
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
                        launchIntentForPackage.addFlags(ClientDefaults.MAX_MSG_SIZE);
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
        launchIntentForPackage.addFlags(ClientDefaults.MAX_MSG_SIZE);
        if (context.getPackageManager().resolveActivity(launchIntentForPackage, 65536) != null) {
        }
    }

    private a a(ab abVar, boolean z, byte[] bArr) {
        a aVar = null;
        try {
            a a = q.a(this.b, abVar);
            if (a == null) {
                b.d("receiving an un-recognized message. " + abVar.a);
                return null;
            }
            b.c("receive a message." + a);
            com.xiaomi.xmpush.thrift.a a2 = abVar.a();
            b.a("processing a message, action=" + a2);
            String b;
            List list;
            switch (t.a[a2.ordinal()]) {
                case 1:
                    if (!a.a(this.b).l() || z) {
                        aj ajVar = (aj) a;
                        q l = ajVar.l();
                        if (l == null) {
                            b.d("receive an empty message without push content, drop it");
                            return null;
                        }
                        if (z) {
                            if (ac.b(abVar)) {
                                MiPushClient.a(this.b, l.b(), abVar.m(), abVar.f, l.d());
                            } else {
                                MiPushClient.a(this.b, l.b(), abVar.m(), l.d());
                            }
                        }
                        if (!z) {
                            if (!TextUtils.isEmpty(ajVar.j()) && MiPushClient.aliasSetTime(this.b, ajVar.j()) < 0) {
                                MiPushClient.a(this.b, ajVar.j());
                            } else if (!TextUtils.isEmpty(ajVar.h()) && MiPushClient.topicSubscribedTime(this.b, ajVar.h()) < 0) {
                                MiPushClient.e(this.b, ajVar.h());
                            }
                        }
                        CharSequence charSequence = (abVar.h == null || abVar.h.s() == null) ? null : (String) abVar.h.j.get("jobkey");
                        if (TextUtils.isEmpty(charSequence)) {
                            b = l.b();
                        } else {
                            CharSequence charSequence2 = charSequence;
                        }
                        if (z || !a(this.b, b)) {
                            Serializable generateMessage = PushMessageHelper.generateMessage(ajVar, abVar.m(), z);
                            if (generateMessage.getPassThrough() == 0 && !z && ac.a(generateMessage.getExtra())) {
                                ac.a(this.b, abVar, bArr);
                                return null;
                            }
                            b.a("receive a message, msgid=" + l.b() + ", jobkey=" + b);
                            if (z && generateMessage.getExtra() != null && generateMessage.getExtra().containsKey("notify_effect")) {
                                Map extra = generateMessage.getExtra();
                                String str = (String) extra.get("notify_effect");
                                if (ac.b(abVar)) {
                                    Intent a3 = a(this.b, abVar.f, extra);
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
                                if (!str.equals(am.c)) {
                                    a4.putExtra(PushMessageHelper.KEY_MESSAGE, generateMessage);
                                }
                                this.b.startActivity(a4);
                                return null;
                            }
                            Serializable serializable = generateMessage;
                        } else {
                            b.a("drop a duplicate message, key=" + b);
                        }
                        if (abVar.m() != null || z) {
                            return aVar;
                        }
                        a(ajVar, abVar);
                        return aVar;
                    }
                    b.a("receive a message in pause state. drop it");
                    return null;
                case 2:
                    ag agVar = (ag) a;
                    if (agVar.f == 0) {
                        a.a(this.b).b(agVar.h, agVar.i);
                    }
                    if (TextUtils.isEmpty(agVar.h)) {
                        list = null;
                    } else {
                        list = new ArrayList();
                        list.add(agVar.h);
                    }
                    aVar = PushMessageHelper.generateCommandMessage("register", list, agVar.f, agVar.g, null);
                    u.a(this.b).e();
                    return aVar;
                case 3:
                    if (((an) a).f == 0) {
                        a.a(this.b).h();
                        MiPushClient.a(this.b);
                    }
                    PushMessageHandler.a();
                    return null;
                case 4:
                    al alVar = (al) a;
                    if (alVar.f == 0) {
                        MiPushClient.e(this.b, alVar.h());
                    }
                    if (TextUtils.isEmpty(alVar.h())) {
                        list = null;
                    } else {
                        list = new ArrayList();
                        list.add(alVar.h());
                    }
                    return PushMessageHelper.generateCommandMessage(MiPushClient.COMMAND_SUBSCRIBE_TOPIC, list, alVar.f, alVar.g, alVar.k());
                case 5:
                    ap apVar = (ap) a;
                    if (apVar.f == 0) {
                        MiPushClient.f(this.b, apVar.h());
                    }
                    if (TextUtils.isEmpty(apVar.h())) {
                        list = null;
                    } else {
                        list = new ArrayList();
                        list.add(apVar.h());
                    }
                    return PushMessageHelper.generateCommandMessage(MiPushClient.COMMAND_UNSUBSCRIBE_TOPIC, list, apVar.f, apVar.g, apVar.k());
                case 6:
                    aa aaVar = (aa) a;
                    Object e = aaVar.e();
                    List k = aaVar.k();
                    if (aaVar.g == 0) {
                        if (TextUtils.equals(e, MiPushClient.COMMAND_SET_ACCEPT_TIME) && k != null && k.size() > 1) {
                            MiPushClient.a(this.b, (String) k.get(0), (String) k.get(1));
                            if ("00:00".equals(k.get(0)) && "00:00".equals(k.get(1))) {
                                a.a(this.b).a(true);
                            } else {
                                a.a(this.b).a(false);
                            }
                            list = a(TimeZone.getTimeZone("GMT+08"), TimeZone.getDefault(), k);
                            return PushMessageHelper.generateCommandMessage(e, list, aaVar.g, aaVar.h, aaVar.m());
                        } else if (TextUtils.equals(e, MiPushClient.COMMAND_SET_ALIAS) && k != null && k.size() > 0) {
                            MiPushClient.a(this.b, (String) k.get(0));
                            list = k;
                            return PushMessageHelper.generateCommandMessage(e, list, aaVar.g, aaVar.h, aaVar.m());
                        } else if (TextUtils.equals(e, MiPushClient.COMMAND_UNSET_ALIAS) && k != null && k.size() > 0) {
                            MiPushClient.b(this.b, (String) k.get(0));
                            list = k;
                            return PushMessageHelper.generateCommandMessage(e, list, aaVar.g, aaVar.h, aaVar.m());
                        } else if (TextUtils.equals(e, MiPushClient.COMMAND_SET_ACCOUNT) && k != null && k.size() > 0) {
                            MiPushClient.c(this.b, (String) k.get(0));
                            list = k;
                            return PushMessageHelper.generateCommandMessage(e, list, aaVar.g, aaVar.h, aaVar.m());
                        } else if (TextUtils.equals(e, MiPushClient.COMMAND_UNSET_ACCOUNT) && k != null && k.size() > 0) {
                            MiPushClient.d(this.b, (String) k.get(0));
                        }
                    }
                    list = k;
                    return PushMessageHelper.generateCommandMessage(e, list, aaVar.g, aaVar.h, aaVar.m());
                case 7:
                    String c;
                    if (a instanceof x) {
                        x xVar = (x) a;
                        c = xVar.c();
                        if (o.DisablePushMessage.N.equalsIgnoreCase(xVar.e)) {
                            if (xVar.g == 0) {
                                synchronized (p.class) {
                                    if (p.a(this.b).e(c)) {
                                        p.a(this.b).d(c);
                                        if ("disable_syncing".equals(p.a(this.b).a())) {
                                            p.a(this.b).f("disable_synced");
                                            MiPushClient.clearNotification(this.b);
                                            MiPushClient.clearLocalNotificationType(this.b);
                                            PushMessageHandler.a();
                                            u.a(this.b).b();
                                        }
                                    }
                                }
                                return null;
                            } else if ("disable_syncing".equals(p.a(this.b).a())) {
                                synchronized (p.class) {
                                    if (p.a(this.b).e(c)) {
                                        if (p.a(this.b).c(c) < 10) {
                                            p.a(this.b).b(c);
                                            u.a(this.b).a(true, c);
                                        } else {
                                            p.a(this.b).d(c);
                                        }
                                    }
                                }
                                return null;
                            } else {
                                p.a(this.b).d(c);
                                return null;
                            }
                        } else if (!o.EnablePushMessage.N.equalsIgnoreCase(xVar.e)) {
                            return null;
                        } else {
                            if (xVar.g == 0) {
                                synchronized (p.class) {
                                    if (p.a(this.b).e(c)) {
                                        p.a(this.b).d(c);
                                        if ("enable_syncing".equals(p.a(this.b).a())) {
                                            p.a(this.b).f("enable_synced");
                                            p.a(this.b).d(c);
                                        }
                                    }
                                }
                                return null;
                            } else if ("enable_syncing".equals(p.a(this.b).a())) {
                                synchronized (p.class) {
                                    if (p.a(this.b).e(c)) {
                                        if (p.a(this.b).c(c) < 10) {
                                            p.a(this.b).b(c);
                                            u.a(this.b).a(false, c);
                                        } else {
                                            p.a(this.b).d(c);
                                        }
                                    }
                                }
                                return null;
                            } else {
                                p.a(this.b).d(c);
                                return null;
                            }
                        }
                    } else if (!(a instanceof ae)) {
                        return null;
                    } else {
                        ae aeVar = (ae) a;
                        if ("registration id expired".equalsIgnoreCase(aeVar.e)) {
                            MiPushClient.a(this.b, t.RegIdExpired);
                            return null;
                        } else if ("client_info_update_ok".equalsIgnoreCase(aeVar.e)) {
                            if (aeVar.i() == null || !aeVar.i().containsKey("app_version")) {
                                return null;
                            }
                            a.a(this.b).a((String) aeVar.i().get("app_version"));
                            return null;
                        } else if ("awake_app".equalsIgnoreCase(aeVar.e)) {
                            if (aeVar.i() == null || !aeVar.i().containsKey("packages")) {
                                return null;
                            }
                            MiPushClient.awakeApps(this.b, ((String) aeVar.i().get("packages")).split(Constants.ACCEPT_TIME_SEPARATOR_SP));
                            return null;
                        } else if (o.NormalClientConfigUpdate.N.equalsIgnoreCase(aeVar.e)) {
                            ad adVar = new ad();
                            try {
                                aq.a((a) adVar, aeVar.m());
                                ai.a(ah.a(this.b), adVar);
                                return null;
                            } catch (Throwable e2) {
                                b.a(e2);
                                return null;
                            }
                        } else if (o.CustomClientConfigUpdate.N.equalsIgnoreCase(aeVar.e)) {
                            com.xiaomi.xmpush.thrift.ac acVar = new com.xiaomi.xmpush.thrift.ac();
                            try {
                                aq.a((a) acVar, aeVar.m());
                                ai.a(ah.a(this.b), acVar);
                                return null;
                            } catch (Throwable e22) {
                                b.a(e22);
                                return null;
                            }
                        } else if (o.SyncInfoResult.N.equalsIgnoreCase(aeVar.e)) {
                            x.a(this.b, aeVar);
                            return null;
                        } else if (o.ForceSync.N.equalsIgnoreCase(aeVar.e)) {
                            b.a("receive force sync notification");
                            x.a(this.b, false);
                            return null;
                        } else if (o.GeoRegsiter.N.equalsIgnoreCase(aeVar.e)) {
                            f.a(this.b).a(aeVar);
                            return null;
                        } else if (o.GeoUnregsiter.N.equalsIgnoreCase(aeVar.e)) {
                            f.a(this.b).b(aeVar);
                            return null;
                        } else if (o.GeoSync.N.equalsIgnoreCase(aeVar.e)) {
                            f.a(this.b).c(aeVar);
                            return null;
                        } else if (!o.CancelPushMessage.N.equals(aeVar.e)) {
                            return null;
                        } else {
                            c = "";
                            if (aeVar.i() == null) {
                                return null;
                            }
                            int parseInt;
                            if (aeVar.i().containsKey(am.H)) {
                                c = (String) aeVar.i().get(am.H);
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
                                    if (aeVar.i().containsKey(am.F)) {
                                        c = (String) aeVar.i().get(am.F);
                                    }
                                    MiPushClient.clearNotification(this.b, c, aeVar.i().containsKey(am.G) ? (String) aeVar.i().get(am.G) : b);
                                    return null;
                                }
                            }
                            parseInt = -2;
                            if (parseInt < -1) {
                                c = "";
                                b = "";
                                if (aeVar.i().containsKey(am.F)) {
                                    c = (String) aeVar.i().get(am.F);
                                }
                                if (aeVar.i().containsKey(am.G)) {
                                }
                                MiPushClient.clearNotification(this.b, c, aeVar.i().containsKey(am.G) ? (String) aeVar.i().get(am.G) : b);
                                return null;
                            }
                            MiPushClient.clearNotification(this.b, parseInt);
                            return null;
                        }
                    }
                default:
                    return null;
            }
        } catch (Throwable e222) {
            b.a(e222);
            a(abVar);
            return null;
        } catch (Throwable e2222) {
            b.a(e2222);
            b.d("receive a message which action string is not valid. is the reg expired?");
            return null;
        }
    }

    private a a(ab abVar, byte[] bArr) {
        String str = null;
        try {
            a a = q.a(this.b, abVar);
            if (a == null) {
                b.d("message arrived: receiving an un-recognized message. " + abVar.a);
                return null;
            }
            b.c("message arrived: receive a message." + a);
            com.xiaomi.xmpush.thrift.a a2 = abVar.a();
            b.a("message arrived: processing an arrived message, action=" + a2);
            switch (t.a[a2.ordinal()]) {
                case 1:
                    aj ajVar = (aj) a;
                    q l = ajVar.l();
                    if (l == null) {
                        b.d("message arrived: receive an empty message without push content, drop it");
                        return null;
                    }
                    if (!(abVar.h == null || abVar.h.s() == null)) {
                        str = (String) abVar.h.j.get("jobkey");
                    }
                    MiPushMessage generateMessage = PushMessageHelper.generateMessage(ajVar, abVar.m(), false);
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

    public static s a(Context context) {
        if (a == null) {
            a = new s(context);
        }
        return a;
    }

    private void a() {
        SharedPreferences sharedPreferences = this.b.getSharedPreferences(MiPushClient.PREF_EXTRA, 0);
        long currentTimeMillis = System.currentTimeMillis();
        if (Math.abs(currentTimeMillis - sharedPreferences.getLong(Constants.SP_KEY_LAST_REINITIALIZE, 0)) > 1800000) {
            MiPushClient.a(this.b, t.PackageUnregistered);
            sharedPreferences.edit().putLong(Constants.SP_KEY_LAST_REINITIALIZE, currentTimeMillis).commit();
        }
    }

    private void a(ab abVar) {
        b.a("receive a message but decrypt failed. report now.");
        a aeVar = new ae(abVar.m().a, false);
        aeVar.c(o.DecryptMessageFail.N);
        aeVar.b(abVar.h());
        aeVar.d(abVar.f);
        aeVar.h = new HashMap();
        aeVar.h.put("regid", MiPushClient.getRegId(this.b));
        u.a(this.b).a(aeVar, com.xiaomi.xmpush.thrift.a.Notification, false, null);
    }

    private void a(aj ajVar, ab abVar) {
        r m = abVar.m();
        a wVar = new w();
        wVar.b(ajVar.e());
        wVar.a(ajVar.c());
        wVar.a(ajVar.l().h());
        if (!TextUtils.isEmpty(ajVar.h())) {
            wVar.c(ajVar.h());
        }
        if (!TextUtils.isEmpty(ajVar.j())) {
            wVar.d(ajVar.j());
        }
        wVar.a(aq.a(this.b, abVar));
        u.a(this.b).a(wVar, com.xiaomi.xmpush.thrift.a.AckMessage, m);
    }

    private static boolean a(Context context, String str) {
        boolean z = false;
        synchronized (d) {
            SharedPreferences j = a.a(context).j();
            if (c == null) {
                String[] split = j.getString("pref_msg_ids", "").split(Constants.ACCEPT_TIME_SEPARATOR_SP);
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
                String a = d.a(c, Constants.ACCEPT_TIME_SEPARATOR_SP);
                Editor edit = j.edit();
                edit.putString("pref_msg_ids", a);
                edit.commit();
            }
        }
        return z;
    }

    private void b(ab abVar) {
        r m = abVar.m();
        a wVar = new w();
        wVar.b(abVar.h());
        wVar.a(m.b());
        wVar.a(m.d());
        if (!TextUtils.isEmpty(m.f())) {
            wVar.c(m.f());
        }
        wVar.a(aq.a(this.b, abVar));
        u.a(this.b).a(wVar, com.xiaomi.xmpush.thrift.a.AckMessage, false, abVar.m());
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
            ab abVar = new ab();
            try {
                aq.a((a) abVar, byteArrayExtra);
                a a = a.a(this.b);
                r m = abVar.m();
                if (!(abVar.a() != com.xiaomi.xmpush.thrift.a.SendMessage || m == null || a.l() || booleanExtra)) {
                    if (m != null) {
                        abVar.m().a("mrt", stringExtra);
                        abVar.m().a("mat", Long.toString(System.currentTimeMillis()));
                    }
                    b(abVar);
                }
                if (abVar.a() == com.xiaomi.xmpush.thrift.a.SendMessage && !abVar.c()) {
                    if (!ac.b(abVar)) {
                        action = "drop an un-encrypted messages. %1$s, %2$s";
                        Object[] objArr = new Object[2];
                        objArr[0] = abVar.j();
                        objArr[1] = m != null ? m.b() : "";
                        b.a(String.format(action, objArr));
                        return null;
                    } else if (!(booleanExtra && m.s() != null && m.s().containsKey("notify_effect"))) {
                        b.a(String.format("drop an un-encrypted messages. %1$s, %2$s", new Object[]{abVar.j(), m.b()}));
                        return null;
                    }
                }
                if (a.i() || abVar.a == com.xiaomi.xmpush.thrift.a.Registration) {
                    if (!a.i() || !a.n()) {
                        return a(abVar, booleanExtra, byteArrayExtra);
                    }
                    if (abVar.a == com.xiaomi.xmpush.thrift.a.UnRegistration) {
                        a.h();
                        MiPushClient.a(this.b);
                        PushMessageHandler.a();
                    } else {
                        MiPushClient.unregisterPush(this.b);
                    }
                } else if (ac.b(abVar)) {
                    return a(abVar, booleanExtra, byteArrayExtra);
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
            a abVar2 = new ab();
            try {
                byteArrayExtra = intent.getByteArrayExtra("mipush_payload");
                if (byteArrayExtra != null) {
                    aq.a(abVar2, byteArrayExtra);
                }
            } catch (f e3) {
            }
            miPushCommandMessage.setCommand(String.valueOf(abVar2.a()));
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
            ab abVar3 = new ab();
            try {
                aq.a((a) abVar3, byteArrayExtra2);
                a a2 = a.a(this.b);
                if (ac.b(abVar3)) {
                    b.d("message arrived: receive ignore reg message, ignore!");
                } else if (!a2.i()) {
                    b.d("message arrived: receive message without registration. need unregister or re-register!");
                } else if (!a2.i() || !a2.n()) {
                    return a(abVar3, byteArrayExtra2);
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
        long parseLong = Long.parseLong(((String) list.get(0)).split(Config.TRACE_TODAY_VISIT_SPLIT)[0]);
        long parseLong2 = Long.parseLong(((String) list.get(0)).split(Config.TRACE_TODAY_VISIT_SPLIT)[1]);
        parseLong = ((((parseLong * 60) + parseLong2) - rawOffset) + 1440) % 1440;
        long parseLong3 = (((Long.parseLong(((String) list.get(1)).split(Config.TRACE_TODAY_VISIT_SPLIT)[1]) + (60 * Long.parseLong(((String) list.get(1)).split(Config.TRACE_TODAY_VISIT_SPLIT)[0]))) - rawOffset) + 1440) % 1440;
        List arrayList = new ArrayList();
        List list2 = arrayList;
        list2.add(String.format("%1$02d:%2$02d", new Object[]{Long.valueOf(parseLong / 60), Long.valueOf(parseLong % 60)}));
        list2 = arrayList;
        list2.add(String.format("%1$02d:%2$02d", new Object[]{Long.valueOf(parseLong3 / 60), Long.valueOf(parseLong3 % 60)}));
        return arrayList;
    }
}
