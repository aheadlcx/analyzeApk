package com.lt;

import android.text.TextUtils;
import com.budejie.www.util.aa;
import net.tsz.afinal.a.a;
import org.json.JSONObject;

class a$1 extends a<String> {
    final /* synthetic */ a a;

    a$1(a aVar) {
        this.a = aVar;
    }

    public /* synthetic */ void onSuccess(Object obj) {
        a((String) obj);
    }

    public void a(String str) {
        if (!TextUtils.isEmpty(str)) {
            aa.b("test", "getPhoneNumber=" + str);
            try {
                JSONObject jSONObject = new JSONObject(str);
                if (jSONObject.has("data")) {
                    jSONObject = jSONObject.getJSONObject("data");
                    if (jSONObject.has("url")) {
                        Object string = jSONObject.getString("url");
                        if (!TextUtils.isEmpty(string)) {
                            a.a(this.a, string);
                        }
                    }
                }
            } catch (Exception e) {
            }
        }
    }
}
