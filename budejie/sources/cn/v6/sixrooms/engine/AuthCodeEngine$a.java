package cn.v6.sixrooms.engine;

import cn.v6.sixrooms.base.VLAsyncHandler;
import cn.v6.sixrooms.constants.CommonStrs;
import cn.v6.sixrooms.engine.AuthCodeEngine.OnRequestFailedListener;
import org.json.JSONException;
import org.json.JSONObject;

class AuthCodeEngine$a extends VLAsyncHandler<String> {
    final /* synthetic */ AuthCodeEngine a;
    private OnRequestFailedListener b;
    private AuthCodeEngine$OnRequestSuccessListener c;

    AuthCodeEngine$a(AuthCodeEngine authCodeEngine, AuthCodeEngine$OnRequestSuccessListener authCodeEngine$OnRequestSuccessListener, OnRequestFailedListener onRequestFailedListener) {
        this.a = authCodeEngine;
        super(null, 0);
        this.c = authCodeEngine$OnRequestSuccessListener;
        this.b = onRequestFailedListener;
    }

    protected final void handler(boolean z) {
        if (z) {
            try {
                JSONObject jSONObject = new JSONObject((String) getParam());
                String string = jSONObject.getString("flag");
                String string2 = jSONObject.getString("content");
                if ("001".equals(string)) {
                    if (this.c != null) {
                        this.c.onRequestSuccess(string2);
                        return;
                    }
                    throw new IllegalArgumentException("没有成功回调监听");
                } else if (this.b != null) {
                    this.b.handleErrorInfo(string, string2);
                }
            } catch (JSONException e) {
                e.printStackTrace();
                if (this.b != null) {
                    this.b.error(1007);
                }
            }
        } else if (CommonStrs.NET_CONNECT_FAIL.equals(getStr()) && this.b != null) {
            this.b.error(1006);
        }
    }
}
