package com.alibaba.baichuan.android.trade.config.a;

import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import com.alibaba.baichuan.android.trade.AlibcContext;
import com.alibaba.baichuan.android.trade.constants.ConfigConstant;
import com.alibaba.baichuan.android.trade.utils.log.AlibcLogger;
import java.util.Map;
import org.json.JSONException;
import org.json.JSONObject;

public class c {
    private final String a = c.class.getSimpleName();
    private SharedPreferences b = AlibcContext.context.getSharedPreferences(ConfigConstant.SP_CONFIG_NAME, 0);

    public a a() {
        a aVar;
        JSONException e;
        String string = this.b.getString(ConfigConstant.SP_CONFIG_NAME, null);
        AlibcLogger.d(this.a, "SP里面的值为:" + string);
        if (string == null) {
            return null;
        }
        try {
            JSONObject jSONObject = new JSONObject(string);
            aVar = new a();
            try {
                aVar.a(jSONObject);
                return aVar;
            } catch (JSONException e2) {
                e = e2;
            }
        } catch (JSONException e3) {
            JSONException jSONException = e3;
            aVar = null;
            e = jSONException;
            AlibcLogger.e(this.a, "拼接json出错" + e.getMessage());
            return aVar;
        }
    }

    public void a(a aVar) {
        Editor edit = this.b.edit();
        JSONObject jSONObject = new JSONObject();
        for (String str : aVar.a().keySet()) {
            try {
                jSONObject.put(str, new JSONObject((Map) aVar.a().get(str)));
            } catch (JSONException e) {
                AlibcLogger.e(this.a, "拼接json出错" + e.getMessage());
            }
        }
        edit.putString(ConfigConstant.SP_CONFIG_NAME, jSONObject.toString());
        edit.commit();
    }
}
