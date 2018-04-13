package com.lt;

import android.text.TextUtils;
import com.budejie.www.util.an;
import java.text.SimpleDateFormat;
import java.util.Date;
import net.tsz.afinal.a.a;
import org.json.JSONObject;

class a$3 extends a<String> {
    final /* synthetic */ a a;

    a$3(a aVar) {
        this.a = aVar;
    }

    public /* synthetic */ void onSuccess(Object obj) {
        a((String) obj);
    }

    public void a(String str) {
        if (!TextUtils.isEmpty(str)) {
            try {
                JSONObject jSONObject = new JSONObject(str);
                if (jSONObject.has("state")) {
                    a.c(this.a, jSONObject.getString("state"));
                    an.a(a.a(this.a), a.b(this.a), new SimpleDateFormat("yyyy-MM-dd").format(new Date()), a.d(this.a));
                }
            } catch (Exception e) {
            }
        }
        if (this.a.e()) {
            an.i(a.a(this.a), a.b(this.a));
            a.e(this.a);
        }
    }
}
