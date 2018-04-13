package com.qiniu.rs;

import com.alipay.sdk.cons.c;
import com.sina.weibo.sdk.constant.WBPageConstants.ParamKey;
import org.json.JSONException;
import org.json.JSONObject;

public class ChunkUploadCallRet extends CallRet {
    protected String a;
    protected String b;
    protected int c;
    protected String d;
    protected long e;

    public ChunkUploadCallRet(CallRet callRet) {
        super(callRet);
    }

    public ChunkUploadCallRet(int i, String str, String str2) {
        super(i, str, str2);
    }

    public ChunkUploadCallRet(int i, Exception exception) {
        this(i, "", exception);
    }

    public ChunkUploadCallRet(int i, String str, Exception exception) {
        super(i, str, exception);
    }

    protected void a() throws JSONException {
        JSONObject jSONObject = new JSONObject(getResponse());
        this.a = jSONObject.optString("ctx", null);
        this.b = jSONObject.optString("checksum", null);
        this.c = jSONObject.optInt(ParamKey.OFFSET, 0);
        this.d = jSONObject.optString(c.f, null);
        this.e = jSONObject.optLong("crc32", 0);
    }

    public String getCtx() {
        return this.a;
    }

    public String getChecksum() {
        return this.b;
    }

    public long getOffset() {
        return (long) this.c;
    }

    public String getHost() {
        return this.d;
    }

    public long getCrc32() {
        return this.e;
    }
}
