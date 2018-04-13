package com.xiaomi.mipush.sdk;

import android.content.Context;
import com.xiaomi.channel.commonutils.network.d;
import com.xiaomi.push.service.av;
import com.xiaomi.push.service.aw.b;
import com.xiaomi.xmpush.thrift.a;
import com.xiaomi.xmpush.thrift.ae;
import java.util.ArrayList;
import java.util.Iterator;

public class ab implements b {
    private Context a;

    public ab(Context context) {
        this.a = context;
    }

    public void a(ArrayList<av.b> arrayList) {
        if (arrayList == null) {
            com.xiaomi.channel.commonutils.logger.b.d("[MiTinyDataClient]:requests is null, MiTinyDataClient upload by long connection failed.");
            return;
        }
        ArrayList a = av.a(arrayList, this.a.getPackageName(), a.a(this.a).c());
        if (a != null) {
            Iterator it = a.iterator();
            while (it.hasNext()) {
                ae aeVar = (ae) it.next();
                aeVar.a("uploadWay", "longMiTinyClient");
                u.a(this.a).a(aeVar, a.Notification, true, null);
            }
            return;
        }
        com.xiaomi.channel.commonutils.logger.b.d("Get a null XmPushActionNotification when TinyDataHelper.transToTriftObj() in MiPushClient.");
    }

    public boolean a(av.b bVar) {
        return d.d(this.a);
    }
}
