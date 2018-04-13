package com.izuiyou.auth.api.entity;

import android.support.annotation.Keep;
import com.alibaba.fastjson.annotation.JSONField;

@Keep
public class WXUserInfo {
    @JSONField(name = "headimgurl")
    public String headimgurl;
    @JSONField(name = "nickname")
    public String nickname;
    @JSONField(name = "sex")
    public String sex;
}
