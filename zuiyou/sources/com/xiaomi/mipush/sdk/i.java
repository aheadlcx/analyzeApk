package com.xiaomi.mipush.sdk;

import android.content.Context;
import android.text.TextUtils;
import com.sina.weibo.sdk.exception.WeiboAuthException;
import com.xiaomi.channel.commonutils.logger.b;
import com.xiaomi.push.service.av;
import com.xiaomi.push.service.h;
import com.xiaomi.push.service.j;
import com.xiaomi.push.service.x;
import com.xiaomi.xmpush.thrift.ai;
import com.xiaomi.xmpush.thrift.au;
import com.xiaomi.xmpush.thrift.m;
import com.xiaomi.xmpush.thrift.r;
import com.xiaomi.xmpush.thrift.v;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;
import org.apache.thrift.a;
import org.apache.thrift.f;

public class i {
    private static volatile i a;
    private final String b = "GeoFenceRegMessageProcessor.";
    private Context c;

    private i(Context context) {
        this.c = context;
    }

    public static i a(Context context) {
        if (a == null) {
            synchronized (i.class) {
                if (a == null) {
                    a = new i(context);
                }
            }
        }
        return a;
    }

    private m a(ai aiVar, boolean z) {
        if (z && !x.a(this.c)) {
            return null;
        }
        if (z && !x.c(this.c)) {
            return null;
        }
        try {
            a mVar = new m();
            au.a(mVar, aiVar.m());
            return mVar;
        } catch (f e) {
            e.printStackTrace();
            return null;
        }
    }

    private v a(boolean z) {
        v vVar = new v();
        Set treeSet = new TreeSet();
        if (z) {
            Iterator it = h.a(this.c).a().iterator();
            while (it.hasNext()) {
                treeSet.add((m) it.next());
            }
        }
        vVar.a(treeSet);
        return vVar;
    }

    public static void a(Context context, boolean z) {
        a aiVar = new ai(MiPushClient.generatePacketID(), false);
        aiVar.b(c.a(context).c());
        aiVar.c(r.GeoAuthorized.W);
        aiVar.h = new HashMap();
        aiVar.h.put("permission_to_location", String.valueOf(z));
        ac.a(context).a(aiVar, com.xiaomi.xmpush.thrift.a.Notification, false, null);
    }

    private void a(m mVar) {
        byte[] a = au.a(mVar);
        a aiVar = new ai(MiPushClient.generatePacketID(), false);
        aiVar.c(r.GeoPackageUninstalled.W);
        aiVar.a(a);
        ac.a(this.c).a(aiVar, com.xiaomi.xmpush.thrift.a.Notification, true, null);
        b.c("GeoFenceRegMessageProcessor. report package not exist geo_fencing id:" + mVar.a());
    }

    private void a(m mVar, boolean z, boolean z2) {
        byte[] a = au.a(mVar);
        a aiVar = new ai(MiPushClient.generatePacketID(), false);
        aiVar.c(z ? r.GeoRegsiterResult.W : r.GeoUnregsiterResult.W);
        aiVar.a(a);
        if (z2) {
            aiVar.a("permission_to_location", av.b);
        }
        ac.a(this.c).a(aiVar, com.xiaomi.xmpush.thrift.a.Notification, true, null);
        b.c("GeoFenceRegMessageProcessor. report geo_fencing id:" + mVar.a() + " " + (z ? "geo_reg" : "geo_unreg") + "  isUnauthorized:" + z2);
    }

    public static boolean a(Map<String, String> map) {
        return map == null ? false : TextUtils.equals("1", (CharSequence) map.get("__geo_local_cache"));
    }

    private boolean d(ai aiVar) {
        return a(aiVar.i()) && x.d(this.c);
    }

    public void a(ai aiVar) {
        boolean d = d(aiVar);
        m a = a(aiVar, d);
        if (a == null) {
            b.c("GeoFenceRegMessageProcessor. registration convert geofence object failed notification_id:" + aiVar.c());
        } else if (!x.e(this.c)) {
            a(a, true, true);
        } else if (com.xiaomi.channel.commonutils.android.a.e(this.c, a.g())) {
            if (d) {
                if (h.a(this.c).a(a) == -1) {
                    b.a("GeoFenceRegMessageProcessor. insert a new geofence failed about geo_id:" + a.a());
                }
                new j(this.c).a(a);
                a(a, true, false);
                b.c("GeoFenceRegMessageProcessor. receive geo reg notification");
                return;
            }
            a(a, true, false);
        } else if (d) {
            a(a);
        }
    }

    public void b(ai aiVar) {
        boolean d = d(aiVar);
        m a = a(aiVar, d);
        if (a == null) {
            b.c("GeoFenceRegMessageProcessor. unregistration convert geofence object failed notification_id:" + aiVar.c());
        } else if (!x.e(this.c)) {
            a(a, false, true);
        } else if (com.xiaomi.channel.commonutils.android.a.e(this.c, a.g())) {
            if (d) {
                if (h.a(this.c).d(a.a()) == 0) {
                    b.a("GeoFenceRegMessageProcessor. delete a geofence about geo_id:" + a.a() + " falied");
                }
                if (j.a(this.c).b(a.a()) == 0) {
                    b.a("GeoFenceRegMessageProcessor. delete all geofence messages about geo_id:" + a.a() + " failed");
                }
                new j(this.c).a(a.a());
                a(a, false, false);
                b.c("GeoFenceRegMessageProcessor. receive geo unreg notification");
                return;
            }
            a(a, false, false);
        } else if (d) {
            a(a);
        }
    }

    public void c(ai aiVar) {
        if (x.e(this.c)) {
            boolean d = d(aiVar);
            if (d && !x.a(this.c)) {
                return;
            }
            if ((!d || x.c(this.c)) && com.xiaomi.channel.commonutils.android.a.e(this.c, aiVar.i)) {
                Object a = a(d);
                byte[] a2 = au.a(a);
                a aiVar2 = new ai(WeiboAuthException.DEFAULT_AUTH_ERROR_CODE, false);
                aiVar2.c(r.GeoUpload.W);
                aiVar2.a(a2);
                ac.a(this.c).a(aiVar2, com.xiaomi.xmpush.thrift.a.Notification, true, null);
                b.c("GeoFenceRegMessageProcessor. sync_geo_data. geos size:" + a.a().size());
            }
        }
    }
}
