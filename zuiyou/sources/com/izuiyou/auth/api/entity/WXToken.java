package com.izuiyou.auth.api.entity;

import android.support.annotation.Keep;
import com.alibaba.fastjson.annotation.JSONField;

@Keep
public class WXToken {
    @JSONField(name = "access_token")
    public String access_token;
    @JSONField(name = "expires_in")
    public String expires_in;
    @JSONField(name = "openid")
    public String openid;
    @JSONField(name = "refresh_token")
    public String refresh_token;
    @JSONField(name = "scope")
    public String scope;

    public String toString() {
        return "WXToken{access_token='" + this.access_token + '\'' + ", expires_in='" + this.expires_in + '\'' + ", refresh_token='" + this.refresh_token + '\'' + ", openid='" + this.openid + '\'' + ", scope='" + this.scope + '\'' + '}';
    }
}
