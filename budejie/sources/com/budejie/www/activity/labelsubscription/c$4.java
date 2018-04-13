package com.budejie.www.activity.labelsubscription;

import android.os.Handler;
import android.os.Message;
import com.budejie.www.bean.RecommendSubscription;
import com.budejie.www.util.an;
import java.util.ArrayList;

class c$4 extends Handler {
    final /* synthetic */ c a;

    c$4(c cVar) {
        this.a = cVar;
    }

    public void handleMessage(Message message) {
        int i = message.what;
        if (i == 434534545 || i == 9976575) {
            ArrayList arrayList = (ArrayList) message.obj;
            c.n(this.a).clear();
            c.n(this.a).addAll(arrayList);
            if (arrayList == null) {
                return;
            }
            if (((RecommendSubscription) arrayList.get(2)).getType().equals("recomm_tv") || ((RecommendSubscription) arrayList.get(3)).getType().equals("recomm_tv")) {
                an.a(c.l(this.a));
            }
        }
    }
}
