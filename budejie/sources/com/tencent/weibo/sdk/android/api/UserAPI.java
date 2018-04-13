package com.tencent.weibo.sdk.android.api;

import android.content.Context;
import com.tencent.connect.common.Constants;
import com.tencent.weibo.sdk.android.api.util.Util;
import com.tencent.weibo.sdk.android.model.AccountModel;
import com.tencent.weibo.sdk.android.model.BaseVO;
import com.tencent.weibo.sdk.android.network.HttpCallback;
import com.tencent.weibo.sdk.android.network.ReqParam;

public class UserAPI extends BaseAPI {
    private static final String SERVER_URL_USERINFO = "https://open.t.qq.com/api/user/info";
    private static final String SERVER_URL_USERINFOS = "https://open.t.qq.com/api/user/infos";
    private static final String SERVER_URL_USEROTHERINFO = "https://open.t.qq.com/api/user/other_info";

    public UserAPI(AccountModel accountModel) {
        super(accountModel);
    }

    public void getUserInfo(Context context, String str, HttpCallback httpCallback, Class<? extends BaseVO> cls, int i) {
        ReqParam reqParam = new ReqParam();
        reqParam.addParam(Constants.PARAM_SCOPE, "all");
        reqParam.addParam("clientip", Util.getLocalIPAddress(context));
        reqParam.addParam("oauth_version", "2.a");
        reqParam.addParam("oauth_consumer_key", Util.getSharePersistent(context, "CLIENT_ID"));
        reqParam.addParam("openid", Util.getSharePersistent(context, "OPEN_ID"));
        reqParam.addParam("format", str);
        startRequest(context, SERVER_URL_USERINFO, reqParam, httpCallback, cls, "GET", i);
    }

    public void getUserOtherInfo(Context context, String str, String str2, String str3, HttpCallback httpCallback, Class<? extends BaseVO> cls, int i) {
        ReqParam reqParam = new ReqParam();
        reqParam.addParam(Constants.PARAM_SCOPE, "all");
        reqParam.addParam("clientip", Util.getLocalIPAddress(context));
        reqParam.addParam("oauth_version", "2.a");
        reqParam.addParam("oauth_consumer_key", Util.getSharePersistent(context, "CLIENT_ID"));
        reqParam.addParam("openid", Util.getSharePersistent(context, "OPEN_ID"));
        reqParam.addParam("format", str);
        if (!(str2 == null || "".equals(str2))) {
            reqParam.addParam("name", str2);
        }
        if (!(str3 == null || "".equals(str3))) {
            reqParam.addParam("fopenid", str3);
        }
        startRequest(context, SERVER_URL_USEROTHERINFO, reqParam, httpCallback, cls, "GET", i);
    }

    public void getUserInfos(Context context, String str, String str2, String str3, HttpCallback httpCallback, Class<? extends BaseVO> cls, int i) {
        ReqParam reqParam = new ReqParam();
        reqParam.addParam(Constants.PARAM_SCOPE, "all");
        reqParam.addParam("clientip", Util.getLocalIPAddress(context));
        reqParam.addParam("oauth_version", "2.a");
        reqParam.addParam("oauth_consumer_key", Util.getSharePersistent(context, "CLIENT_ID"));
        reqParam.addParam("openid", Util.getSharePersistent(context, "OPEN_ID"));
        reqParam.addParam("format", str);
        if (!(str2 == null || "".equals(str2))) {
            reqParam.addParam("names", str2);
        }
        if (!(str3 == null || "".equals(str3))) {
            reqParam.addParam("fopenids", str3);
        }
        startRequest(context, SERVER_URL_USERINFOS, reqParam, httpCallback, cls, "GET", i);
    }
}
