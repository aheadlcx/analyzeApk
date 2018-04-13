package cn.v6.sixrooms.room;

import org.json.JSONException;
import org.json.JSONObject;

final class ar implements Runnable {
    final /* synthetic */ int a;
    final /* synthetic */ String b;
    final /* synthetic */ JSONObject c;
    final /* synthetic */ aq d;

    ar(aq aqVar, int i, String str, JSONObject jSONObject) {
        this.d = aqVar;
        this.a = i;
        this.b = str;
        this.c = jSONObject;
    }

    public final void run() {
        try {
            this.d.a.handleIMSocketErrorResult(this.a, this.b, this.c.getString("typeID"), this.c.getString("content"));
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}
