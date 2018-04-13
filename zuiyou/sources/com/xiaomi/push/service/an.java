package com.xiaomi.push.service;

import android.accounts.Account;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences.Editor;
import android.content.pm.PackageManager;
import android.text.TextUtils;
import com.meizu.cloud.pushsdk.notification.model.NotifyType;
import com.tencent.connect.common.Constants;
import com.tencent.tauth.AuthActivity;
import com.xiaomi.channel.commonutils.android.e;
import com.xiaomi.channel.commonutils.logger.b;
import com.xiaomi.smack.packet.c;
import com.xiaomi.smack.packet.d;
import com.xiaomi.smack.util.g;
import com.xiaomi.xmpush.thrift.af;
import com.xiaomi.xmpush.thrift.ai;
import com.xiaomi.xmpush.thrift.au;
import com.xiaomi.xmpush.thrift.r;
import com.xiaomi.xmpush.thrift.u;
import com.xiaomi.xmpush.thrift.z;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.apache.thrift.a;

public class an {
    public static Intent a(byte[] bArr, long j) {
        af a = a(bArr);
        if (a == null) {
            return null;
        }
        Intent intent = new Intent("com.xiaomi.mipush.RECEIVE_MESSAGE");
        intent.putExtra("mipush_payload", bArr);
        intent.putExtra("mrt", Long.toString(j));
        intent.setPackage(a.f);
        return intent;
    }

    public static af a(Context context, af afVar) {
        return a(context, afVar, false, false, false);
    }

    public static af a(Context context, af afVar, boolean z, boolean z2, boolean z3) {
        a zVar = new z();
        zVar.b(afVar.h());
        u m = afVar.m();
        if (m != null) {
            zVar.a(m.b());
            zVar.a(m.d());
            if (!TextUtils.isEmpty(m.f())) {
                zVar.c(m.f());
            }
        }
        zVar.a(au.a(context, afVar));
        zVar.b(au.a(z, z2, z3));
        af a = f.a(afVar.j(), afVar.h(), zVar, com.xiaomi.xmpush.thrift.a.AckMessage);
        m = afVar.m().a();
        m.a("mat", Long.toString(System.currentTimeMillis()));
        a.a(m);
        return a;
    }

    public static af a(byte[] bArr) {
        a afVar = new af();
        try {
            au.a(afVar, bArr);
            return afVar;
        } catch (Throwable th) {
            b.a(th);
            return null;
        }
    }

    private static void a(XMPushService xMPushService, af afVar) {
        xMPushService.a(new y(4, xMPushService, afVar));
    }

    private static void a(XMPushService xMPushService, af afVar, String str) {
        xMPushService.a(new ac(4, xMPushService, afVar, str));
    }

    private static void a(XMPushService xMPushService, af afVar, String str, String str2) {
        xMPushService.a(new ad(4, xMPushService, afVar, str, str2));
    }

    public static void a(XMPushService xMPushService, af afVar, boolean z, boolean z2, boolean z3) {
        a(xMPushService, afVar, z, z2, z3, false);
    }

    public static void a(XMPushService xMPushService, af afVar, boolean z, boolean z2, boolean z3, boolean z4) {
        xMPushService.a(new ae(4, xMPushService, afVar, z, z2, z3, z4));
    }

    public static void a(XMPushService xMPushService, String str, byte[] bArr, Intent intent, boolean z) {
        af a = a(bArr);
        u m = a.m();
        if (c(a) && a((Context) xMPushService, str)) {
            d(xMPushService, a);
        } else if (a(a) && !a((Context) xMPushService, str) && !b(a)) {
            e(xMPushService, a);
        } else if ((ah.b(a) && com.xiaomi.channel.commonutils.android.a.e(xMPushService, a.f)) || a((Context) xMPushService, intent)) {
            String j;
            if (com.xiaomi.xmpush.thrift.a.Registration == a.a()) {
                j = a.j();
                Editor edit = xMPushService.getSharedPreferences("pref_registered_pkg_names", 0).edit();
                edit.putString(j, a.e);
                edit.commit();
            }
            if (m != null && !TextUtils.isEmpty(m.h()) && !TextUtils.isEmpty(m.j()) && m.h != 1 && (ah.a(m.s()) || !ah.a((Context) xMPushService, a.f))) {
                boolean z2 = false;
                j = null;
                if (m != null) {
                    if (m.j != null) {
                        j = (String) m.j.get("jobkey");
                    }
                    if (TextUtils.isEmpty(j)) {
                        j = m.b();
                    }
                    z2 = ai.a(xMPushService, a.f, j);
                }
                if (z2) {
                    b.a("drop a duplicate message, key=" + j);
                } else {
                    ah.b a2 = ah.a((Context) xMPushService, a, bArr);
                    if (a2.b > 0 && !TextUtils.isEmpty(a2.a)) {
                        g.a(xMPushService, a2.a, a2.b, true, System.currentTimeMillis());
                    }
                    if (!ah.b(a)) {
                        Intent intent2 = new Intent("com.xiaomi.mipush.MESSAGE_ARRIVED");
                        intent2.putExtra("mipush_payload", bArr);
                        intent2.setPackage(a.f);
                        try {
                            List queryBroadcastReceivers = xMPushService.getPackageManager().queryBroadcastReceivers(intent2, 0);
                            if (!(queryBroadcastReceivers == null || queryBroadcastReceivers.isEmpty())) {
                                xMPushService.sendBroadcast(intent2, e.a(a.f));
                            }
                        } catch (Exception e) {
                            xMPushService.sendBroadcast(intent2, e.a(a.f));
                        }
                    }
                }
                if (z) {
                    a(xMPushService, a, false, true, false);
                } else {
                    c(xMPushService, a);
                }
            } else if ("com.xiaomi.xmsf".contains(a.f) && !a.c() && m != null && m.s() != null && m.s().containsKey("ab")) {
                c(xMPushService, a);
                b.c("receive abtest message. ack it." + m.b());
            } else if (a(xMPushService, str, a, m)) {
                xMPushService.sendBroadcast(intent, e.a(a.f));
            }
            if (a.a() == com.xiaomi.xmpush.thrift.a.UnRegistration && !"com.xiaomi.xmsf".equals(xMPushService.getPackageName())) {
                xMPushService.stopSelf();
            }
        } else if (com.xiaomi.channel.commonutils.android.a.e(xMPushService, a.f)) {
            b.a("receive a mipush message, we can see the app, but we can't see the receiver.");
        } else {
            a(xMPushService, a);
        }
    }

    private static void a(XMPushService xMPushService, byte[] bArr, long j) {
        af a = a(bArr);
        if (a != null) {
            if (TextUtils.isEmpty(a.f)) {
                b.a("receive a mipush message without package name");
                return;
            }
            Long valueOf = Long.valueOf(System.currentTimeMillis());
            Intent a2 = a(bArr, valueOf.longValue());
            String a3 = ah.a(a);
            g.a(xMPushService, a3, j, true, System.currentTimeMillis());
            u m = a.m();
            if (m != null) {
                m.a("mrt", Long.toString(valueOf.longValue()));
            }
            String str;
            if (com.xiaomi.xmpush.thrift.a.SendMessage == a.a() && u.a((Context) xMPushService).a(a.f) && !ah.b(a)) {
                str = "";
                if (m != null) {
                    str = m.b();
                }
                b.a("Drop a message for unregistered, msgid=" + str);
                a(xMPushService, a, a.f);
            } else if (com.xiaomi.xmpush.thrift.a.SendMessage == a.a() && u.a((Context) xMPushService).c(a.f) && !ah.b(a)) {
                str = "";
                if (m != null) {
                    str = m.b();
                }
                b.a("Drop a message for push closed, msgid=" + str);
                a(xMPushService, a, a.f);
            } else if (com.xiaomi.xmpush.thrift.a.SendMessage != a.a() || TextUtils.equals(xMPushService.getPackageName(), "com.xiaomi.xmsf") || TextUtils.equals(xMPushService.getPackageName(), a.f)) {
                if (!(m == null || m.b() == null)) {
                    b.a(String.format("receive a message, appid=%1$s, msgid= %2$s", new Object[]{a.h(), m.b()}));
                }
                if (m != null) {
                    Map s = m.s();
                    if (s != null && s.containsKey("hide") && "true".equalsIgnoreCase((String) s.get("hide"))) {
                        c(xMPushService, a);
                        return;
                    }
                }
                if (!(m == null || m.s() == null || !m.s().containsKey("__miid"))) {
                    str = (String) m.s().get("__miid");
                    Account a4 = e.a(xMPushService);
                    if (a4 == null || !TextUtils.equals(str, a4.name)) {
                        b.a(new StringBuilder().append(str).append(" should be login, but got ").append(a4).toString() == null ? "nothing" : a4.name);
                        a(xMPushService, a, "miid already logout or anther already login", new StringBuilder().append(str).append(" should be login, but got ").append(a4).toString() == null ? "nothing" : a4.name);
                        return;
                    }
                }
                boolean z = m != null && a(m.s());
                if (z) {
                    if (x.e(xMPushService)) {
                        boolean z2;
                        Object obj = (a(m) && x.d(xMPushService)) ? 1 : null;
                        if (obj == null) {
                            z2 = true;
                        } else if (b(xMPushService, a)) {
                            z2 = a(xMPushService, m, bArr);
                        } else {
                            return;
                        }
                        a(xMPushService, a, true, false, false);
                        if (!z2) {
                            return;
                        }
                    }
                    a(xMPushService, a, false, false, false, true);
                    return;
                }
                a(xMPushService, a3, bArr, a2, z);
            } else {
                b.a("Receive a message with wrong package name, expect " + xMPushService.getPackageName() + ", received " + a.f);
                a(xMPushService, a, "unmatched_package", "package should be " + xMPushService.getPackageName() + ", but got " + a.f);
            }
        }
    }

    private static boolean a(Context context, Intent intent) {
        try {
            List queryBroadcastReceivers = context.getPackageManager().queryBroadcastReceivers(intent, 32);
            return (queryBroadcastReceivers == null || queryBroadcastReceivers.isEmpty()) ? false : true;
        } catch (Exception e) {
            return true;
        }
    }

    private static boolean a(Context context, String str) {
        Intent intent = new Intent("com.xiaomi.mipush.miui.CLICK_MESSAGE");
        intent.setPackage(str);
        Intent intent2 = new Intent("com.xiaomi.mipush.miui.RECEIVE_MESSAGE");
        intent2.setPackage(str);
        PackageManager packageManager = context.getPackageManager();
        try {
            return (packageManager.queryBroadcastReceivers(intent2, 32).isEmpty() && packageManager.queryIntentServices(intent, 32).isEmpty()) ? false : true;
        } catch (Throwable e) {
            b.a(e);
            return false;
        }
    }

    private static boolean a(XMPushService xMPushService, u uVar, byte[] bArr) {
        Map s = uVar.s();
        String[] split = ((String) s.get("__geo_ids")).split(",");
        ArrayList arrayList = new ArrayList();
        for (String str : split) {
            if (h.a((Context) xMPushService).a(str) != null) {
                ContentValues contentValues = new ContentValues();
                contentValues.put("geo_id", str);
                contentValues.put("message_id", uVar.b());
                int parseInt = Integer.parseInt((String) s.get("__geo_action"));
                contentValues.put(AuthActivity.ACTION_KEY, Integer.valueOf(parseInt));
                contentValues.put("content", bArr);
                contentValues.put("deadline", Long.valueOf(Long.parseLong((String) s.get("__geo_deadline"))));
                if (TextUtils.equals(h.a((Context) xMPushService).c(str), "Enter") && parseInt == 1) {
                    return true;
                }
                arrayList.add(contentValues);
            }
        }
        if (!j.a((Context) xMPushService).a(arrayList)) {
            b.c("geofence added some new geofence message failed messagi_id:" + uVar.b());
        }
        return false;
    }

    private static boolean a(XMPushService xMPushService, String str, af afVar, u uVar) {
        if (uVar == null || uVar.s() == null || !uVar.s().containsKey("__check_alive") || !uVar.s().containsKey("__awake")) {
            return true;
        }
        a aiVar = new ai();
        aiVar.b(afVar.h());
        aiVar.d(str);
        aiVar.c(r.AwakeSystemApp.W);
        aiVar.a(uVar.b());
        aiVar.h = new HashMap();
        boolean d = com.xiaomi.channel.commonutils.android.a.d(xMPushService.getApplicationContext(), str);
        aiVar.h.put("app_running", Boolean.toString(d));
        if (!d) {
            d = Boolean.parseBoolean((String) uVar.s().get("__awake"));
            aiVar.h.put("awaked", Boolean.toString(d));
            if (!d) {
                d = false;
                f.a(xMPushService, f.a(afVar.j(), afVar.h(), aiVar, com.xiaomi.xmpush.thrift.a.Notification));
                return d;
            }
        }
        d = true;
        try {
            f.a(xMPushService, f.a(afVar.j(), afVar.h(), aiVar, com.xiaomi.xmpush.thrift.a.Notification));
            return d;
        } catch (Throwable e) {
            b.a(e);
            return d;
        }
    }

    private static boolean a(af afVar) {
        return "com.xiaomi.xmsf".equals(afVar.f) && afVar.m() != null && afVar.m().s() != null && afVar.m().s().containsKey("miui_package_name");
    }

    public static boolean a(u uVar) {
        if (uVar == null) {
            return false;
        }
        Map s = uVar.s();
        return s != null ? TextUtils.equals("1", (CharSequence) s.get("__geo_local_check")) : false;
    }

    private static boolean a(Map<String, String> map) {
        return map != null && map.containsKey("__geo_ids");
    }

    private static boolean b(XMPushService xMPushService, af afVar) {
        if (!x.a(xMPushService)) {
            return false;
        }
        if (!x.c(xMPushService)) {
            return false;
        }
        if (com.xiaomi.channel.commonutils.android.a.e(xMPushService, afVar.f)) {
            Map s = afVar.m().s();
            return s == null ? false : !Constants.VIA_REPORT_TYPE_SET_AVATAR.contains((CharSequence) s.get("__geo_action")) ? false : !TextUtils.isEmpty((CharSequence) s.get("__geo_ids"));
        } else {
            a(xMPushService, afVar);
            return false;
        }
    }

    private static boolean b(af afVar) {
        Map s = afVar.m().s();
        return s != null && s.containsKey("notify_effect");
    }

    private static void c(XMPushService xMPushService, af afVar) {
        xMPushService.a(new z(4, xMPushService, afVar));
    }

    private static boolean c(af afVar) {
        return (afVar.m() == null || afVar.m().s() == null) ? false : "1".equals(afVar.m().s().get("obslete_ads_message"));
    }

    private static void d(XMPushService xMPushService, af afVar) {
        xMPushService.a(new aa(4, xMPushService, afVar));
    }

    private static void e(XMPushService xMPushService, af afVar) {
        xMPushService.a(new ab(4, xMPushService, afVar));
    }

    public void a(Context context, ap.b bVar, boolean z, int i, String str) {
        if (!z) {
            s a = t.a(context);
            if (a != null && "token-expired".equals(str)) {
                try {
                    t.a(context, a.d, a.e, a.f);
                } catch (Throwable e) {
                    b.a(e);
                } catch (Throwable e2) {
                    b.a(e2);
                }
            }
        }
    }

    public void a(XMPushService xMPushService, com.xiaomi.slim.b bVar, ap.b bVar2) {
        try {
            a(xMPushService, bVar.d(bVar2.i), (long) bVar.l());
        } catch (Throwable e) {
            b.a(e);
        }
    }

    public void a(XMPushService xMPushService, d dVar, ap.b bVar) {
        if (dVar instanceof c) {
            c cVar = (c) dVar;
            com.xiaomi.smack.packet.a p = cVar.p(NotifyType.SOUND);
            if (p != null) {
                try {
                    a(xMPushService, aw.a(aw.a(bVar.i, cVar.k()), p.c()), (long) g.a(dVar.c()));
                    return;
                } catch (Throwable e) {
                    b.a(e);
                    return;
                }
            }
            return;
        }
        b.a("not a mipush message");
    }
}
