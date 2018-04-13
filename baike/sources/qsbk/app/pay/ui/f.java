package qsbk.app.pay.ui;

import java.util.HashMap;
import java.util.Map;
import qsbk.app.core.net.Callback;

class f extends Callback {
    final /* synthetic */ String a;
    final /* synthetic */ PayActivity b;

    f(PayActivity payActivity, String str) {
        this.b = payActivity;
        this.a = str;
    }

    public Map<String, String> getParams() {
        Map<String, String> hashMap = new HashMap();
        hashMap.put("status", this.a);
        hashMap.put("record_id", Long.toString(this.b.f));
        return hashMap;
    }
}
