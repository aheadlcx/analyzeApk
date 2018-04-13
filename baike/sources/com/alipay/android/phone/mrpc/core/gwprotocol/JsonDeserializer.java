package com.alipay.android.phone.mrpc.core.gwprotocol;

import com.alipay.a.a.e;
import com.alipay.android.phone.mrpc.core.RpcException;
import com.alipay.sdk.util.j;
import com.baidu.mobstat.Config;
import java.lang.reflect.Type;
import org.json.JSONObject;
import qsbk.app.activity.security.UnBindActivity;

public class JsonDeserializer extends AbstractDeserializer {
    public JsonDeserializer(Type type, byte[] bArr) {
        super(type, bArr);
    }

    public Object parser() {
        try {
            String str = new String(this.b);
            new StringBuilder("threadid = ").append(Thread.currentThread().getId()).append("; rpc response:  ").append(str);
            JSONObject jSONObject = new JSONObject(str);
            int i = jSONObject.getInt(j.a);
            if (i == 1000) {
                return this.a == String.class ? jSONObject.optString(j.c) : e.a(jSONObject.optString(j.c), this.a);
            } else {
                throw new RpcException(Integer.valueOf(i), jSONObject.optString(UnBindActivity.KEY_TIP));
            }
        } catch (Exception e) {
            throw new RpcException(Integer.valueOf(10), new StringBuilder("response  =").append(new String(this.b)).append(Config.TRACE_TODAY_VISIT_SPLIT).append(e).toString() == null ? "" : e.getMessage());
        }
    }
}
