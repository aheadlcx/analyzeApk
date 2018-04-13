package com.tencent.weibo.sdk.android.api;

import android.content.Context;
import com.tencent.connect.common.Constants;
import com.tencent.weibo.sdk.android.api.util.Util;
import com.tencent.weibo.sdk.android.model.AccountModel;
import com.tencent.weibo.sdk.android.model.BaseVO;
import com.tencent.weibo.sdk.android.network.HttpCallback;
import com.tencent.weibo.sdk.android.network.ReqParam;

public class LbsAPI extends BaseAPI {
    private static final String SERVER_URL_GetAROUNDNEW = "https://open.t.qq.com/api/lbs/get_around_new";
    private static final String SERVER_URL_GetAROUNDPEOPLE = "https://open.t.qq.com/api/lbs/get_around_people";

    public LbsAPI(AccountModel accountModel) {
        super(accountModel);
    }

    public void getAroundPeople(Context context, String str, double d, double d2, String str2, int i, int i2, HttpCallback httpCallback, Class<? extends BaseVO> cls, int i3) {
        ReqParam reqParam = new ReqParam();
        reqParam.addParam(Constants.PARAM_SCOPE, "all");
        reqParam.addParam("clientip", Util.getLocalIPAddress(context));
        reqParam.addParam("oauth_version", "2.a");
        reqParam.addParam("oauth_consumer_key", Util.getSharePersistent(context, "CLIENT_ID"));
        reqParam.addParam("openid", Util.getSharePersistent(context, "OPEN_ID"));
        reqParam.addParam("format", str);
        reqParam.addParam("longitude", Double.valueOf(d));
        reqParam.addParam("latitude", Double.valueOf(d2));
        reqParam.addParam("pageinfo", str2);
        reqParam.addParam("pagesize", Integer.valueOf(i));
        reqParam.addParam("gender", Integer.valueOf(i2));
        startRequest(context, SERVER_URL_GetAROUNDPEOPLE, reqParam, httpCallback, cls, "POST", i3);
    }

    public void getAroundNew(Context context, String str, double d, double d2, String str2, int i, HttpCallback httpCallback, Class<? extends BaseVO> cls, int i2) {
        ReqParam reqParam = new ReqParam();
        reqParam.addParam(Constants.PARAM_SCOPE, "all");
        reqParam.addParam("clientip", Util.getLocalIPAddress(context));
        reqParam.addParam("oauth_version", "2.a");
        reqParam.addParam("oauth_consumer_key", Util.getSharePersistent(context, "CLIENT_ID"));
        reqParam.addParam("openid", Util.getSharePersistent(context, "OPEN_ID"));
        reqParam.addParam("format", str);
        reqParam.addParam("longitude", Double.valueOf(d));
        reqParam.addParam("latitude", Double.valueOf(d2));
        reqParam.addParam("pageinfo", str2);
        reqParam.addParam("pagesize", Integer.valueOf(i));
        startRequest(context, SERVER_URL_GetAROUNDNEW, reqParam, httpCallback, cls, "POST", i2);
    }
}
