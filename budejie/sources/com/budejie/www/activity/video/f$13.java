package com.budejie.www.activity.video;

import mtopsdk.mtop.antiattack.CheckCodeDO;
import net.tsz.afinal.a.a;
import org.json.JSONException;
import org.json.JSONObject;

class f$13 extends a<String> {
    final /* synthetic */ f a;

    f$13(f fVar) {
        this.a = fVar;
    }

    public /* synthetic */ void onSuccess(Object obj) {
        a((String) obj);
    }

    public void a(String str) {
        if (str != null) {
            try {
                JSONObject jSONObject = new JSONObject(str);
                if (jSONObject.has(CheckCodeDO.CHECKCODE_USER_INPUT_KEY)) {
                    if (!"0".equals(jSONObject.getString(CheckCodeDO.CHECKCODE_USER_INPUT_KEY))) {
                    }
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    }
}
