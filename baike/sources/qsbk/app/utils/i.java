package qsbk.app.utils;

import java.util.Iterator;
import org.json.JSONObject;
import qsbk.app.http.SimpleCallBack;

class i implements SimpleCallBack {
    final /* synthetic */ Iterator a;
    final /* synthetic */ a b;
    final /* synthetic */ h c;

    i(h hVar, Iterator it, a aVar) {
        this.c = hVar;
        this.a = it;
        this.b = aVar;
    }

    public void onSuccess(JSONObject jSONObject) {
        this.a.remove();
    }

    public void onFailure(int i, String str) {
        a aVar = this.b;
        aVar.c++;
        if (this.b.c > 3) {
            this.a.remove();
        }
    }
}
