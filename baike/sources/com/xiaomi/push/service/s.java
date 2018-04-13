package com.xiaomi.push.service;

import android.accounts.Account;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences.Editor;
import android.content.pm.PackageManager;
import android.text.TextUtils;
import com.xiaomi.channel.commonutils.android.f;
import com.xiaomi.channel.commonutils.logger.b;
import com.xiaomi.mipush.sdk.Constants;
import com.xiaomi.smack.packet.c;
import com.xiaomi.smack.packet.d;
import com.xiaomi.smack.util.g;
import com.xiaomi.xmpush.thrift.ab;
import com.xiaomi.xmpush.thrift.aq;
import com.xiaomi.xmpush.thrift.r;
import com.xiaomi.xmpush.thrift.w;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import org.apache.thrift.a;

public class s {
    public static Intent a(byte[] bArr, long j) {
        ab a = a(bArr);
        if (a == null) {
            return null;
        }
        Intent intent = new Intent("com.xiaomi.mipush.RECEIVE_MESSAGE");
        intent.putExtra("mipush_payload", bArr);
        intent.putExtra("mrt", Long.toString(j));
        intent.setPackage(a.f);
        return intent;
    }

    public static ab a(Context context, ab abVar) {
        return a(context, abVar, false, false, false);
    }

    public static ab a(Context context, ab abVar, boolean z, boolean z2, boolean z3) {
        a wVar = new w();
        wVar.b(abVar.h());
        r m = abVar.m();
        if (m != null) {
            wVar.a(m.b());
            wVar.a(m.d());
            if (!TextUtils.isEmpty(m.f())) {
                wVar.c(m.f());
            }
        }
        wVar.a(aq.a(context, abVar));
        wVar.b(aq.a(z, z2, z3));
        ab a = j.a(abVar.j(), abVar.h(), wVar, com.xiaomi.xmpush.thrift.a.AckMessage);
        m = abVar.m().a();
        m.a("mat", Long.toString(System.currentTimeMillis()));
        a.a(m);
        return a;
    }

    public static ab a(byte[] bArr) {
        a abVar = new ab();
        try {
            aq.a(abVar, bArr);
            return abVar;
        } catch (Throwable th) {
            b.a(th);
            return null;
        }
    }

    private static void a(XMPushService xMPushService, ab abVar) {
        xMPushService.a(new bg(4, xMPushService, abVar));
    }

    private static void a(XMPushService xMPushService, ab abVar, String str) {
        xMPushService.a(new bk(4, xMPushService, abVar, str));
    }

    private static void a(XMPushService xMPushService, ab abVar, String str, String str2) {
        xMPushService.a(new bl(4, xMPushService, abVar, str, str2));
    }

    public static void a(XMPushService xMPushService, ab abVar, boolean z, boolean z2, boolean z3) {
        xMPushService.a(new bm(4, xMPushService, abVar, z, z2, z3));
    }

    public static void a(XMPushService xMPushService, String str, byte[] bArr, Intent intent, boolean z) {
        ab a = a(bArr);
        r m = a.m();
        if (c(a) && a((Context) xMPushService, str)) {
            d(xMPushService, a);
        } else if (a(a) && !a((Context) xMPushService, str) && !b(a)) {
            e(xMPushService, a);
        } else if ((ac.b(a) && com.xiaomi.channel.commonutils.android.b.f(xMPushService, a.f)) || a((Context) xMPushService, intent)) {
            String j;
            if (com.xiaomi.xmpush.thrift.a.Registration == a.a()) {
                j = a.j();
                Editor edit = xMPushService.getSharedPreferences("pref_registered_pkg_names", 0).edit();
                edit.putString(j, a.e);
                edit.commit();
                aw.a().b("Registe Success, package name is " + j);
            }
            if (m != null && !TextUtils.isEmpty(m.h()) && !TextUtils.isEmpty(m.j()) && m.h != 1 && (ac.a(m.s()) || !ac.a((Context) xMPushService, a.f))) {
                boolean z2 = false;
                j = null;
                if (m != null) {
                    if (m.j != null) {
                        j = (String) m.j.get("jobkey");
                    }
                    if (TextUtils.isEmpty(j)) {
                        j = m.b();
                    }
                    z2 = ad.a(xMPushService, a.f, j);
                }
                if (z2) {
                    b.a("drop a duplicate message, key=" + j);
                } else {
                    ac.b a2 = ac.a((Context) xMPushService, a, bArr);
                    if (a2.b > 0 && !TextUtils.isEmpty(a2.a)) {
                        g.a(xMPushService, a2.a, a2.b, true, System.currentTimeMillis());
                    }
                    if (!ac.b(a)) {
                        Intent intent2 = new Intent("com.xiaomi.mipush.MESSAGE_ARRIVED");
                        intent2.putExtra("mipush_payload", bArr);
                        intent2.setPackage(a.f);
                        try {
                            List queryBroadcastReceivers = xMPushService.getPackageManager().queryBroadcastReceivers(intent2, 0);
                            if (!(queryBroadcastReceivers == null || queryBroadcastReceivers.isEmpty())) {
                                xMPushService.sendBroadcast(intent2, b.a(a.f));
                            }
                        } catch (Exception e) {
                            xMPushService.sendBroadcast(intent2, b.a(a.f));
                        }
                    }
                }
                if (z) {
                    a(xMPushService, a, false, true, false);
                } else {
                    c(xMPushService, a);
                }
            } else if (!"com.xiaomi.xmsf".contains(a.f) || a.c() || m == null || m.s() == null || !m.s().containsKey("ab")) {
                xMPushService.sendBroadcast(intent, b.a(a.f));
            } else {
                c(xMPushService, a);
                b.c("receive abtest message. ack it." + m.b());
            }
            if (a.a() == com.xiaomi.xmpush.thrift.a.UnRegistration && !"com.xiaomi.xmsf".equals(xMPushService.getPackageName())) {
                xMPushService.stopSelf();
            }
        } else if (com.xiaomi.channel.commonutils.android.b.f(xMPushService, a.f)) {
            b.a("receive a mipush message, we can see the app, but we can't see the receiver.");
        } else {
            a(xMPushService, a);
        }
    }

    private static void a(XMPushService xMPushService, byte[] bArr, long j) {
        ab a = a(bArr);
        if (a != null) {
            if (TextUtils.isEmpty(a.f)) {
                b.a("receive a mipush message without package name");
                return;
            }
            Long valueOf = Long.valueOf(System.currentTimeMillis());
            Intent a2 = a(bArr, valueOf.longValue());
            String a3 = ac.a(a);
            g.a(xMPushService, a3, j, true, System.currentTimeMillis());
            r m = a.m();
            if (m != null) {
                m.a("mrt", Long.toString(valueOf.longValue()));
            }
            String str;
            if (com.xiaomi.xmpush.thrift.a.SendMessage == a.a() && p.a((Context) xMPushService).a(a.f) && !ac.b(a)) {
                str = "";
                if (m != null) {
                    str = m.b();
                }
                b.a("Drop a message for unregistered, msgid=" + str);
                a(xMPushService, a, a.f);
            } else if (com.xiaomi.xmpush.thrift.a.SendMessage == a.a() && p.a((Context) xMPushService).c(a.f) && !ac.b(a)) {
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
                    Account a4 = f.a(xMPushService);
                    if (((a4 == null ? 1 : 0) | (!TextUtils.equals(str, a4.name) ? 1 : 0)) != 0) {
                        b.a(new StringBuilder().append(str).append(" should be login, but got ").append(a4).toString() == null ? "nothing" : a4.name);
                        a(xMPushService, a, "miid already logout or anther already login", new StringBuilder().append(str).append(" should be login, but got ").append(a4).toString() == null ? "nothing" : a4.name);
                        return;
                    }
                }
                boolean z = m != null && a(m.s());
                if (z) {
                    if (b(xMPushService, a)) {
                        boolean a5 = a(xMPushService, m, bArr);
                        a(xMPushService, a, true, false, false);
                        if (!a5) {
                            return;
                        }
                    }
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

    private static boolean a(XMPushService xMPushService, r rVar, byte[] bArr) {
        Map s = rVar.s();
        String[] split = ((String) s.get("__geo_ids")).split(Constants.ACCEPT_TIME_SEPARATOR_SP);
        ArrayList arrayList = new ArrayList();
        for (String str : split) {
            ContentValues contentValues = new ContentValues();
            contentValues.put("geo_id", str);
            contentValues.put("message_id", rVar.b());
            int parseInt = Integer.parseInt((String) s.get("__geo_action"));
            contentValues.put("action", Integer.valueOf(parseInt));
            contentValues.put("content", bArr);
            contentValues.put("deadline", Long.valueOf(Long.parseLong((String) s.get("__geo_deadline"))));
            if (TextUtils.equals(e.a((Context) xMPushService).c(str), "Enter") && parseInt == 1) {
                return true;
            }
            arrayList.add(contentValues);
        }
        if (!g.a((Context) xMPushService).a(arrayList)) {
            b.c("geofence added some new geofence message failed messagi_id:" + rVar.b());
        }
        return false;
    }

    private static boolean a(ab abVar) {
        return "com.xiaomi.xmsf".equals(abVar.f) && abVar.m() != null && abVar.m().s() != null && abVar.m().s().containsKey("miui_package_name");
    }

    private static boolean a(Map<String, String> map) {
        return map != null && map.containsKey("__geo_ids");
    }

    private static boolean b(XMPushService xMPushService, ab abVar) {
        if (!h.a(xMPushService)) {
            return false;
        }
        if (!h.b(xMPushService)) {
            return false;
        }
        if (com.xiaomi.channel.commonutils.android.b.f(xMPushService, abVar.f)) {
            Map s = abVar.m().s();
            return s == null ? false : !com.tencent.connect.common.Constants.VIA_REPORT_TYPE_SET_AVATAR.contains((CharSequence) s.get("__geo_action")) ? false : !TextUtils.isEmpty((CharSequence) s.get("__geo_ids"));
        } else {
            a(xMPushService, abVar);
            return false;
        }
    }

    private static boolean b(ab abVar) {
        Map s = abVar.m().s();
        return s != null && s.containsKey("notify_effect");
    }

    private static void c(XMPushService xMPushService, ab abVar) {
        xMPushService.a(new bh(4, xMPushService, abVar));
    }

    private static boolean c(ab abVar) {
        return (abVar.m() == null || abVar.m().s() == null) ? false : "1".equals(abVar.m().s().get("obslete_ads_message"));
    }

    private static void d(XMPushService xMPushService, ab abVar) {
        xMPushService.a(new bi(4, xMPushService, abVar));
    }

    private static void e(XMPushService xMPushService, ab abVar) {
        xMPushService.a(new bj(4, xMPushService, abVar));
    }

    public void a(Context context, ak.b bVar, boolean z, int i, String str) {
        if (!z) {
            n a = o.a(context);
            if (a != null && "token-expired".equals(str)) {
                try {
                    o.a(context, a.d, a.e, a.f);
                } catch (Throwable e) {
                    b.a(e);
                } catch (Throwable e2) {
                    b.a(e2);
                }
            }
        }
    }

    public void a(XMPushService xMPushService, com.xiaomi.slim.b bVar, ak.b bVar2) {
        try {
            a(xMPushService, bVar.d(bVar2.i), (long) bVar.l());
        } catch (Throwable e) {
            b.a(e);
        }
    }

    public void a(XMPushService xMPushService, d dVar, ak.b bVar) {
        if (dVar instanceof c) {
            c cVar = (c) dVar;
            com.xiaomi.smack.packet.a p = cVar.p("s");
            if (p != null) {
                try {
                    a(xMPushService, aq.b(aq.a(bVar.i, cVar.k()), p.c()), (long) g.a(dVar.c()));
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
