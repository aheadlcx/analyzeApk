package com.izuiyou.auth.api;

import com.izuiyou.auth.api.entity.QQUserInfo;
import com.izuiyou.auth.api.entity.WBUserInfo;
import com.izuiyou.auth.api.entity.WXToken;
import com.izuiyou.auth.api.entity.WXUserInfo;
import retrofit2.a.f;
import retrofit2.a.t;
import retrofit2.b;
import rx.d;

public interface AuthService {
    @f(a = "https://graph.qq.com/oauth2.0/me")
    d<String> getQQUnionId(@t(a = "access_token") String str, @t(a = "unionid") int i);

    @f(a = "https://graph.qq.com/user/get_user_info")
    b<QQUserInfo> qqUserInfo(@t(a = "openid") String str, @t(a = "access_token") String str2, @t(a = "oauth_consumer_key") String str3);

    @f(a = "https://api.weibo.com/2/users/show.json")
    b<WBUserInfo> wbUserInfo(@t(a = "access_token") String str, @t(a = "uid") String str2);

    @f(a = "https://api.weixin.qq.com/sns/oauth2/access_token")
    d<WXToken> wxAuth(@t(a = "appid") String str, @t(a = "secret") String str2, @t(a = "code") String str3, @t(a = "grant_type") String str4);

    @f(a = "https://api.weixin.qq.com/sns/userinfo")
    d<WXUserInfo> wxUserInfo(@t(a = "access_token") String str, @t(a = "openid") String str2);
}
