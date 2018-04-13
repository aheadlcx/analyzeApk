package net.tsz.afinal;

import net.tsz.afinal.a.a;
import org.json.JSONException;
import org.json.JSONObject;

class b$a extends a<Object> {
    final /* synthetic */ b a;

    b$a(b bVar) {
        this.a = bVar;
    }

    public void onSuccess(Object obj) {
        super.onSuccess(obj);
        try {
            JSONObject jSONObject = new JSONObject(obj.toString());
            if (jSONObject.has("status") && jSONObject.getString("status").equals("99")) {
                String string = jSONObject.getString("return");
                if (string != null && string.length() > 9) {
                    jSONObject = a(new JSONObject(string), "data");
                    if (jSONObject.has("devKey")) {
                        b.b(jSONObject.getString("devKey"));
                    }
                    if (jSONObject.has("appSource")) {
                        b.c(jSONObject.getString("appSource"));
                    }
                }
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    private JSONObject a(JSONObject jSONObject, String str) {
        try {
            if (jSONObject.has(str) && jSONObject.get(str) != null && jSONObject.get(str).toString().length() > 1) {
                return new JSONObject(jSONObject.getString(str));
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return null;
    }
}
