package qsbk.app.cache;

import android.support.v4.util.LruCache;
import org.json.JSONObject;

class h extends LruCache<String, JSONObject> {
    final /* synthetic */ TextCache a;

    h(TextCache textCache, int i) {
        this.a = textCache;
        super(i);
    }

    protected int a(String str, JSONObject jSONObject) {
        return 0;
    }
}
