package com.xiaomi.push.service;

import com.xiaomi.channel.commonutils.logger.b;
import com.xiaomi.push.service.XMPushService.h;
import com.xiaomi.xmpush.thrift.a;
import com.xiaomi.xmpush.thrift.ae;
import com.xiaomi.xmpush.thrift.aq;
import java.util.ArrayList;
import java.util.Iterator;

class ba extends h {
    final /* synthetic */ ArrayList a;
    final /* synthetic */ az b;

    ba(az azVar, int i, ArrayList arrayList) {
        this.b = azVar;
        this.a = arrayList;
        super(i);
    }

    public void a() {
        String packageName = this.b.a.getPackageName();
        String a = this.b.a(packageName);
        ArrayList a2 = av.a(this.a, packageName, a);
        if (a2 != null) {
            Iterator it = a2.iterator();
            while (it.hasNext()) {
                ae aeVar = (ae) it.next();
                aeVar.a("uploadWay", "longXMPushService");
                this.b.a.a(packageName, aq.a(j.a(packageName, a, aeVar, a.Notification)), true);
            }
            return;
        }
        b.d("Get a null XmPushActionNotification when TinyDataHelper.transToTriftObj() in XMPushService.");
    }

    public String b() {
        return "Send tiny data.";
    }
}
