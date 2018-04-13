package com.budejie.www.activity.label;

import com.budejie.www.R;
import com.budejie.www.util.an;
import mtopsdk.mtop.antiattack.CheckCodeDO;
import net.tsz.afinal.a.a;
import org.json.JSONException;
import org.json.JSONObject;

class c$8 extends a<String> {
    final /* synthetic */ c a;

    c$8(c cVar) {
        this.a = cVar;
    }

    public /* synthetic */ void onSuccess(Object obj) {
        a((String) obj);
    }

    public void a(String str) {
        try {
            JSONObject jSONObject = new JSONObject(str);
            if (jSONObject.has(CheckCodeDO.CHECKCODE_USER_INPUT_KEY) && jSONObject.getInt(CheckCodeDO.CHECKCODE_USER_INPUT_KEY) == -2) {
                c.b(this.a, false);
                c.a(this.a, new LabelBean());
                c.r(this.a);
                c.a(this.a).setPullRefreshEnable(false);
                c.a(this.a).setPullLoadEnable(false);
                return;
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        c.a(this.a, f.a(str));
        if (c.c(this.a) == null) {
            c.a(this.a, an.a(c.b(this.a), this.a.getString(R.string.load_failed), -1));
            c.g(this.a).show();
            c.a(this.a, new LabelBean());
            c.r(this.a);
            return;
        }
        c.r(this.a);
    }

    public void onFailure(Throwable th, int i, String str) {
        if (this.a.isAdded()) {
            c.a(this.a, an.a(c.b(this.a), this.a.getString(R.string.load_failed), -1));
            c.g(this.a).show();
        }
    }
}
