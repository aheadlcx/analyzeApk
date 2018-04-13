package com.budejie.www.a;

import com.budejie.www.activity.label.response.ApplyModeratorResponse;
import com.budejie.www.activity.label.response.CommonInfoBean;
import com.budejie.www.activity.label.response.ToTopResponse;
import io.reactivex.Observable;
import retrofit2.b.f;
import retrofit2.b.t;

public interface a {
    @f(a = "/forum/apply/")
    Observable<CommonInfoBean> a(@t(a = "tag_id") String str, @t(a = "type") int i);

    @f(a = "/forum/manage_topic/")
    Observable<CommonInfoBean> a(@t(a = "tag_id") String str, @t(a = "action") String str2, @t(a = "tid") String str3);

    @f(a = "/forum/topic/top/")
    Observable<ToTopResponse> b(@t(a = "tid") String str, @t(a = "tag_id") String str2, @t(a = "action") String str3);

    @f(a = "/forum/deal_apply/")
    Observable<ApplyModeratorResponse> c(@t(a = "tag_id") String str, @t(a = "action") String str2, @t(a = "apply_uid") String str3);

    @f(a = "/forum/user/control/")
    Observable<CommonInfoBean> d(@t(a = "uid") String str, @t(a = "tag_id") String str2, @t(a = "action") String str3);
}
