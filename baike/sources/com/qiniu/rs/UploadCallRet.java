package com.qiniu.rs;

import org.json.JSONException;
import org.json.JSONObject;
import qsbk.app.activity.pay.PayPWDUniversalActivity;

public class UploadCallRet extends CallRet {
    protected String a;
    protected String b;

    public UploadCallRet(CallRet callRet) {
        super(callRet);
    }

    public UploadCallRet(int i, String str, String str2) {
        super(i, str, str2);
    }

    public UploadCallRet(int i, Exception exception) {
        this(i, "", exception);
    }

    public UploadCallRet(int i, String str, Exception exception) {
        super(i, str, exception);
    }

    protected void a() throws JSONException {
        JSONObject jSONObject = new JSONObject(getResponse());
        this.a = jSONObject.optString("hash", null);
        this.b = jSONObject.optString(PayPWDUniversalActivity.KEY, null);
    }

    public String getHash() {
        return this.a;
    }

    public String getKey() {
        return this.b;
    }
}
