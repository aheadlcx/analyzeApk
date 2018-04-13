package com.izuiyou.auth.api.entity;

import android.support.annotation.Keep;
import com.alibaba.fastjson.annotation.JSONField;

@Keep
public class QQUserInfo {
    @JSONField(name = "figureurl_qq_1")
    public String figureurl_qq_1;
    @JSONField(name = "gender")
    public String gender;
    @JSONField(name = "nickname")
    public String nickname;
}
