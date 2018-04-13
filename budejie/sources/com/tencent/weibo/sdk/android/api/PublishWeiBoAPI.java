package com.tencent.weibo.sdk.android.api;

import android.content.Context;
import com.tencent.connect.common.Constants;
import com.tencent.weibo.sdk.android.api.util.Util;
import com.tencent.weibo.sdk.android.model.AccountModel;
import com.tencent.weibo.sdk.android.model.BaseVO;
import com.tencent.weibo.sdk.android.network.HttpCallback;
import com.tencent.weibo.sdk.android.network.ReqParam;

public class PublishWeiBoAPI extends BaseAPI {
    public static final String MUTUAL_LIST_URL = "https://open.t.qq.com/api/friends/mutual_list";
    public static final String RECENT_USED_URL = "https://open.t.qq.com/api/ht/recent_used";

    public PublishWeiBoAPI(AccountModel accountModel) {
        super(accountModel);
    }

    public void mutual_list(Context context, HttpCallback httpCallback, Class<? extends BaseVO> cls, int i, int i2, int i3, int i4) {
        ReqParam reqParam = new ReqParam();
        reqParam.addParam("format", "json");
        reqParam.addParam("oauth_consumer_key", Util.getSharePersistent(context, "CLIENT_ID"));
        reqParam.addParam("oauth_version", "2.a");
        reqParam.addParam(Constants.PARAM_SCOPE, "all");
        reqParam.addParam("openid", Util.getSharePersistent(context, "OPEN_ID"));
        reqParam.addParam("clientip", Util.getLocalIPAddress(context));
        if (i != 0) {
            reqParam.addParam("fopenid", Integer.valueOf(i));
        }
        reqParam.addParam("startindex", Integer.valueOf(i2));
        reqParam.addParam("install", Integer.valueOf(i3));
        reqParam.addParam("reqnum", Integer.valueOf(i4));
        reqParam.addParam("name", Util.getSharePersistent(context, "NAME"));
        startRequest(context, MUTUAL_LIST_URL, reqParam, httpCallback, cls, "GET", 4);
    }

    public void recent_used(Context context, HttpCallback httpCallback, Class<? extends BaseVO> cls, int i, int i2, int i3) {
        ReqParam reqParam = new ReqParam();
        reqParam.addParam("oauth_consumer_key", Util.getSharePersistent(context, "CLIENT_ID"));
        reqParam.addParam("openid", Util.getSharePersistent(context, "OPEN_ID"));
        reqParam.addParam("clientip", Util.getLocalIPAddress(context));
        reqParam.addParam("oauth_version", "2.a");
        reqParam.addParam(Constants.PARAM_SCOPE, "all");
        reqParam.addParam("format", "json");
        reqParam.addParam("reqnum", Integer.valueOf(i));
        reqParam.addParam("page", Integer.valueOf(i2));
        reqParam.addParam("sorttype", Integer.valueOf(i3));
        startRequest(context, RECENT_USED_URL, reqParam, httpCallback, cls, "GET", 4);
    }
}
