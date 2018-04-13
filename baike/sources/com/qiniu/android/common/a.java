package com.qiniu.android.common;

import com.qiniu.android.common.Zone.QueryHandler;
import com.qiniu.android.http.CompletionHandler;
import com.qiniu.android.http.ResponseInfo;
import org.json.JSONException;
import org.json.JSONObject;

class a implements CompletionHandler {
    final /* synthetic */ a a;
    final /* synthetic */ QueryHandler b;
    final /* synthetic */ AutoZone c;

    a(AutoZone autoZone, a aVar, QueryHandler queryHandler) {
        this.c = autoZone;
        this.a = aVar;
        this.b = queryHandler;
    }

    public void complete(ResponseInfo responseInfo, JSONObject jSONObject) {
        if (responseInfo.isOK() && jSONObject != null) {
            try {
                b a = b.a(jSONObject);
                AutoZone.a.put(this.a, a);
                this.c.a(a);
                this.b.onSuccess();
            } catch (JSONException e) {
                e.printStackTrace();
                this.b.onFailure(-1);
            }
        }
    }
}
