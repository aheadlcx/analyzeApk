package com.tencent.weibo.sdk.android.api;

import android.content.Context;
import com.alibaba.baichuan.android.trade.constants.AppLinkConstants;
import com.tencent.connect.common.Constants;
import com.tencent.weibo.sdk.android.api.util.Util;
import com.tencent.weibo.sdk.android.model.AccountModel;
import com.tencent.weibo.sdk.android.model.BaseVO;
import com.tencent.weibo.sdk.android.network.HttpCallback;
import com.tencent.weibo.sdk.android.network.ReqParam;

public class TimeLineAPI extends BaseAPI {
    private static final String SERVER_URL_HOMETIMELINE = "https://open.t.qq.com/api/statuses/home_timeline";
    private static final String SERVER_URL_HTTIMELINE = "https://open.t.qq.com/api/statuses/ht_timeline_ext";
    private static final String SERVER_URL_USERTIMELINE = "https://open.t.qq.com/api/statuses/user_timeline";

    public TimeLineAPI(AccountModel accountModel) {
        super(accountModel);
    }

    public void getHomeTimeLine(Context context, int i, int i2, int i3, int i4, int i5, String str, HttpCallback httpCallback, Class<? extends BaseVO> cls, int i6) {
        ReqParam reqParam = new ReqParam();
        reqParam.addParam(Constants.PARAM_SCOPE, "all");
        reqParam.addParam("clientip", Util.getLocalIPAddress(context));
        reqParam.addParam("oauth_version", "2.a");
        reqParam.addParam("oauth_consumer_key", Util.getSharePersistent(context, "CLIENT_ID"));
        reqParam.addParam("openid", Util.getSharePersistent(context, "OPEN_ID"));
        reqParam.addParam("format", str);
        reqParam.addParam("pageflag", Integer.valueOf(i));
        reqParam.addParam("type", Integer.valueOf(i4));
        reqParam.addParam("reqnum", Integer.valueOf(i3));
        reqParam.addParam("pagetime", Integer.valueOf(i2));
        reqParam.addParam("contenttype", Integer.valueOf(i5));
        startRequest(context, SERVER_URL_HOMETIMELINE, reqParam, httpCallback, cls, "GET", i6);
    }

    public void getUserTimeLine(Context context, int i, int i2, int i3, int i4, String str, String str2, int i5, int i6, String str3, HttpCallback httpCallback, Class<? extends BaseVO> cls, int i7) {
        ReqParam reqParam = new ReqParam();
        reqParam.addParam(Constants.PARAM_SCOPE, "all");
        reqParam.addParam("clientip", Util.getLocalIPAddress(context));
        reqParam.addParam("oauth_version", "2.a");
        reqParam.addParam("oauth_consumer_key", Util.getSharePersistent(context, "CLIENT_ID"));
        reqParam.addParam("openid", Util.getSharePersistent(context, "OPEN_ID"));
        reqParam.addParam("format", str3);
        reqParam.addParam("pageflag", Integer.valueOf(i));
        reqParam.addParam("pagetime", Integer.valueOf(i2));
        reqParam.addParam("reqnum", Integer.valueOf(i3));
        reqParam.addParam("lastid", Integer.valueOf(i4));
        if (!(str == null || "".equals(str))) {
            reqParam.addParam("name", str);
        }
        if (!(str2 == null || "".equals(str2))) {
            reqParam.addParam("fopenid", str2);
        }
        reqParam.addParam("type", Integer.valueOf(i5));
        reqParam.addParam("contenttype", Integer.valueOf(i6));
        startRequest(context, SERVER_URL_USERTIMELINE, reqParam, httpCallback, cls, "GET", i7);
    }

    public void getHTTimeLine(Context context, String str, int i, String str2, String str3, int i2, int i3, String str4, String str5, int i4, int i5, HttpCallback httpCallback, Class<? extends BaseVO> cls, int i6) {
        ReqParam reqParam = new ReqParam();
        reqParam.addParam(Constants.PARAM_SCOPE, "all");
        reqParam.addParam("clientip", Util.getLocalIPAddress(context));
        reqParam.addParam("oauth_version", "2.a");
        reqParam.addParam("oauth_consumer_key", Util.getSharePersistent(context, "CLIENT_ID"));
        reqParam.addParam("openid", Util.getSharePersistent(context, "OPEN_ID"));
        reqParam.addParam("format", str);
        reqParam.addParam("pageflag", Integer.valueOf(i2));
        reqParam.addParam("reqnum", Integer.valueOf(i));
        reqParam.addParam("tweetid", str2);
        reqParam.addParam(AppLinkConstants.TIME, str3);
        reqParam.addParam("flag", Integer.valueOf(i3));
        if (!(str4 == null || "".equals(str4))) {
            reqParam.addParam("httext", str4);
        }
        if (!(str5 == null || "".equals(str5) || "0".equals(str5))) {
            reqParam.addParam("htid", str5);
        }
        reqParam.addParam("type", Integer.valueOf(i4));
        reqParam.addParam("contenttype", Integer.valueOf(i5));
        startRequest(context, SERVER_URL_HTTIMELINE, reqParam, httpCallback, cls, "GET", i6);
    }
}
