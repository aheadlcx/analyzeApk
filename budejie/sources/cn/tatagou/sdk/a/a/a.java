package cn.tatagou.sdk.a.a;

import java.util.HashMap;
import java.util.LinkedHashMap;
import okhttp3.ab;
import retrofit2.b;
import retrofit2.b.c;
import retrofit2.b.e;
import retrofit2.b.f;
import retrofit2.b.k;
import retrofit2.b.n;
import retrofit2.b.o;
import retrofit2.b.t;
import retrofit2.b.u;

public interface a {
    @f(a = "apConfig?")
    @k(a = {"Cache-Control: no-cache"})
    b<ab> apConfig();

    @f(a = "countUnreadFeedback?")
    @k(a = {"Cache-Control: no-cache"})
    b<ab> countUnreadFeedback(@t(a = "page") int i, @t(a = "pusher") String str);

    @retrofit2.b.b(a = "flushMyPath?")
    b<ab> delMyPath(@t(a = "tbUserId") String str);

    @k(a = {"Cache-Control: no-cache"})
    @o(a = "errorReport?")
    b<ab> errorReport(@u LinkedHashMap<String, String> linkedHashMap);

    @f(a = "feedbackType?")
    @k(a = {"Cache-Control: no-cache"})
    b<ab> feedbackType();

    @f(a = "getSpecialCats?")
    @k(a = {"Cache-Control: no-cache"})
    b<ab> getAppCats();

    @f(a = "getCatSpecials?")
    @k(a = {"Cache-Control: no-cache"})
    b<ab> getCatSpecials(@t(a = "id") String str);

    @f(a = "getFeedback?")
    @k(a = {"Cache-Control: no-cache"})
    b<ab> getFeedback(@t(a = "page") int i, @t(a = "pusher") String str);

    @f(a = "hotSearch")
    @k(a = {"Cache-Control: no-cache"})
    b<ab> getHotSearch();

    @f(a = "getMainAd?")
    @k(a = {"Cache-Control: no-cache"})
    b<ab> getMainAd();

    @f(a = "getMyPath?")
    @k(a = {"Cache-Control: no-cache"})
    b<ab> getMyPath(@t(a = "timestamp") String str, @t(a = "records") Integer num, @t(a = "tbUserId") String str2);

    @f(a = "getRcmmParams?")
    @k(a = {"Cache-Control: no-cache"})
    b<ab> getRcmmParams();

    @f(a = "search?")
    @k(a = {"Cache-Control: no-cache"})
    b<ab> getSearch(@t(a = "keyword") String str, @t(a = "sort") String str2, @t(a = "page") int i, @t(a = "pageSize") int i2);

    @f(a = "getSpecialItems?")
    @k(a = {"Cache-Control: no-cache"})
    b<ab> getSpecialItems(@t(a = "id") String str);

    @f(a = "home?")
    @k(a = {"Cache-Control: no-cache"})
    b<ab> home(@t(a = "page") int i, @t(a = "id") String str);

    @e
    @o(a = "newMyPath?")
    b<ab> newMyPath(@c(a = "goodsId") String str, @c(a = "tbUserId") String str2);

    @f(a = "otherInformation?")
    @k(a = {"Cache-Control: no-cache"})
    b<ab> otherInformation();

    @f(a = "suggest/countUnreadFeedback?")
    @k(a = {"Cache-Control: no-cache"})
    b<ab> outCountUnreadFeedback(@t(a = "page") int i, @t(a = "pusher") String str);

    @f(a = "suggest/feedbackType?")
    @k(a = {"Cache-Control: no-cache"})
    b<ab> outFeedbackType();

    @f(a = "suggest/getFeedback?")
    @k(a = {"Cache-Control: no-cache"})
    b<ab> outGetFeedback(@t(a = "page") int i, @t(a = "pusher") String str);

    @o(a = "suggest/readAll?")
    @e
    @k(a = {"Cache-Control: no-cache"})
    b<ab> outReadAll(@c(a = "pusher") String str);

    @k(a = {"Cache-Control: no-cache"})
    @o(a = "suggest/sendFeedback?")
    b<ab> outSendFeedback(@u LinkedHashMap<String, Object> linkedHashMap);

    @o(a = "readAll?")
    @e
    @k(a = {"Cache-Control: no-cache"})
    b<ab> readAll(@c(a = "pusher") String str);

    @n(a = "saveUserInfo?")
    b<ab> saveUserInfo(@t(a = "sex") String str, @t(a = "demographic") String str2);

    @k(a = {"Cache-Control: no-cache"})
    @o(a = "sendFeedback?")
    b<ab> sendFeedback(@u LinkedHashMap<String, Object> linkedHashMap);

    @f(a = "userActivity?")
    @k(a = {"Cache-Control: no-cache "})
    b<ab> userActivity(@u HashMap<String, String> hashMap);
}
