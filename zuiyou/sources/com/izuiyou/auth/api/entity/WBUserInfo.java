package com.izuiyou.auth.api.entity;

import android.support.annotation.Keep;
import com.alibaba.fastjson.annotation.JSONField;

@Keep
public class WBUserInfo {
    @JSONField(name = "gender")
    public String gender;
    @JSONField(name = "profile_image_url")
    public String profile_image_url;
    @JSONField(name = "screen_name")
    public String screen_name;
}
