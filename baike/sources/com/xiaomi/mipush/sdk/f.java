package com.xiaomi.mipush.sdk;

import android.content.Context;
import com.fasterxml.jackson.core.util.MinimalPrettyPrinter;
import com.xiaomi.channel.commonutils.logger.b;
import com.xiaomi.push.service.e;
import com.xiaomi.push.service.g;
import com.xiaomi.push.service.h;
import com.xiaomi.xmpush.thrift.ae;
import com.xiaomi.xmpush.thrift.aq;
import com.xiaomi.xmpush.thrift.j;
import com.xiaomi.xmpush.thrift.o;
import com.xiaomi.xmpush.thrift.s;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Set;
import java.util.TreeSet;
import org.apache.thrift.a;

public class f {
    private static volatile f a;
    private final String b = "GeoFenceRegMessageProcessor.";
    private Context c;

    private f(Context context) {
        this.c = context;
    }

    public static f a(Context context) {
        if (a == null) {
            synchronized (f.class) {
                if (a == null) {
                    a = new f(context);
                }
            }
        }
        return a;
    }

    private s a() {
        ArrayList a = e.a(this.c).a();
        s sVar = new s();
        Set treeSet = new TreeSet();
        Iterator it = a.iterator();
        while (it.hasNext()) {
            treeSet.add((j) it.next());
        }
        sVar.a(treeSet);
        return sVar;
    }

    private void a(j jVar) {
        byte[] a = aq.a(jVar);
        a aeVar = new ae("-1", false);
        aeVar.c(o.GeoPackageUninstalled.N);
        aeVar.a(a);
        u.a(this.c).a(aeVar, com.xiaomi.xmpush.thrift.a.Notification, true, null);
        b.a("GeoFenceRegMessageProcessor.report package not exist geo_fencing id:" + jVar.a());
    }

    private void a(j jVar, boolean z) {
        byte[] a = aq.a(jVar);
        a aeVar = new ae("-1", false);
        aeVar.c(z ? o.GeoRegsiterResult.N : o.GeoUnregsiterResult.N);
        aeVar.a(a);
        u.a(this.c).a(aeVar, com.xiaomi.xmpush.thrift.a.Notification, true, null);
        b.a("GeoFenceRegMessageProcessor.report geo_fencing id:" + jVar.a() + MinimalPrettyPrinter.DEFAULT_ROOT_VALUE_SEPARATOR + (z ? "geo_reg" : "geo_unreg"));
    }

    private j d(ae aeVar) {
        if (!h.a(this.c) || !h.b(this.c)) {
            return null;
        }
        try {
            a jVar = new j();
            aq.a(jVar, aeVar.m());
            return jVar;
        } catch (org.apache.thrift.f e) {
            e.printStackTrace();
            return null;
        }
    }

    public void a(ae aeVar) {
        j d = d(aeVar);
        if (d == null) {
            b.d("registration convert geofence object failed notification_id:" + aeVar.c());
        } else if (com.xiaomi.channel.commonutils.android.b.f(this.c, d.g())) {
            if (e.a(this.c).a(d) == -1) {
                b.a("GeoFenceRegMessageProcessor. insert a new geofence failed about geo_id:" + d.a());
            }
            new g(this.c).a(d);
            a(d, true);
            b.a("receive geo reg notification");
        } else {
            a(d);
        }
    }

    public void b(ae aeVar) {
        j d = d(aeVar);
        if (d == null) {
            b.d("unregistration convert geofence object failed notification_id:" + aeVar.c());
        } else if (com.xiaomi.channel.commonutils.android.b.f(this.c, d.g())) {
            if (e.a(this.c).d(d.a()) == 0) {
                b.a("GeoFenceRegMessageProcessor. delete a geofence about geo_id:" + d.a() + " falied");
            }
            if (g.a(this.c).b(d.a()) == 0) {
                b.a("GeoFenceRegMessageProcessor. delete all geofence messages about geo_id:" + d.a() + " failed");
            }
            new g(this.c).a(d.a());
            a(d, false);
            b.a("receive geo unreg notification");
        } else {
            a(d);
        }
    }

    public void c(ae aeVar) {
        if (h.a(this.c) && h.b(this.c) && com.xiaomi.channel.commonutils.android.b.f(this.c, aeVar.i)) {
            Object a = a();
            byte[] a2 = aq.a(a);
            a aeVar2 = new ae("-1", false);
            aeVar2.c(o.GeoUpload.N);
            aeVar2.a(a2);
            u.a(this.c).a(aeVar2, com.xiaomi.xmpush.thrift.a.Notification, true, null);
            b.c("GeoFenceRegMessageProcessor.sync_geo_data. geos size:" + a.a().size());
        }
    }
}
