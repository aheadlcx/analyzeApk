package com.tencent.stat.a;

import android.content.Context;
import com.tencent.stat.StatConfig;
import com.tencent.stat.common.k;
import java.util.Map;
import java.util.Map.Entry;
import org.json.JSONObject;
import qsbk.app.thirdparty.ThirdPartyConstants;

public class a extends e {
    Map<String, ?> a = null;

    public a(Context context, int i, Map<String, ?> map) {
        super(context, i);
        this.a = map;
    }

    public f a() {
        return f.ADDITION;
    }

    public boolean a(JSONObject jSONObject) {
        k.a(jSONObject, ThirdPartyConstants.THIRDPARTY_TYLE_QQ, StatConfig.getQQ());
        if (this.a != null && this.a.size() > 0) {
            for (Entry entry : this.a.entrySet()) {
                jSONObject.put((String) entry.getKey(), entry.getValue());
            }
        }
        return true;
    }
}
