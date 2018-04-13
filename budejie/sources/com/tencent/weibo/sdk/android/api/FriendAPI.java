package com.tencent.weibo.sdk.android.api;

import android.content.Context;
import com.tencent.connect.common.Constants;
import com.tencent.weibo.sdk.android.api.util.Util;
import com.tencent.weibo.sdk.android.model.AccountModel;
import com.tencent.weibo.sdk.android.model.BaseVO;
import com.tencent.weibo.sdk.android.network.HttpCallback;
import com.tencent.weibo.sdk.android.network.ReqParam;

public class FriendAPI extends BaseAPI {
    private static final String SERVER_URL_ADD = "https://open.t.qq.com/api/friends/add";
    private static final String SERVER_URL_CHECK = "https://open.t.qq.com/api/friends/check";
    private static final String SERVER_URL_FANSLIST = "https://open.t.qq.com/api/friends/fanslist";
    private static final String SERVER_URL_GetINTIMATEFRIENDS = "https://open.t.qq.com/api/friends/get_intimate_friends";
    private static final String SERVER_URL_IDOLLIST = "https://open.t.qq.com/api/friends/idollist";
    private static final String SERVER_URL_MUTUALLIST = "https://open.t.qq.com/api/friends/mutual_list";

    public FriendAPI(AccountModel accountModel) {
        super(accountModel);
    }

    public void getMutualList(Context context, String str, String str2, String str3, int i, int i2, int i3, HttpCallback httpCallback, Class<? extends BaseVO> cls, int i4) {
        ReqParam reqParam = new ReqParam();
        reqParam.addParam(Constants.PARAM_SCOPE, "all");
        reqParam.addParam("clientip", Util.getLocalIPAddress(context));
        reqParam.addParam("oauth_version", "2.a");
        reqParam.addParam("oauth_consumer_key", Util.getSharePersistent(context, "CLIENT_ID"));
        reqParam.addParam("openid", Util.getSharePersistent(context, "OPEN_ID"));
        reqParam.addParam("format", str);
        reqParam.addParam("reqnum", Integer.valueOf(i2));
        reqParam.addParam("install", Integer.valueOf(i3));
        reqParam.addParam("startindex", Integer.valueOf(i));
        if (!(str2 == null || "".equals(str2))) {
            reqParam.addParam("name", str2);
        }
        if (!(str3 == null || "".equals(str3))) {
            reqParam.addParam("fopenid", str3);
        }
        startRequest(context, "https://open.t.qq.com/api/friends/mutual_list", reqParam, httpCallback, cls, "GET", i4);
    }

    public void addFriend(Context context, String str, String str2, String str3, HttpCallback httpCallback, Class<? extends BaseVO> cls, int i) {
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
            reqParam.addParam("fopenids", str3);
        }
        startRequest(context, SERVER_URL_ADD, reqParam, httpCallback, cls, "POST", i);
    }

    public void friendIDolList(Context context, String str, int i, int i2, int i3, int i4, HttpCallback httpCallback, Class<? extends BaseVO> cls, int i5) {
        ReqParam reqParam = new ReqParam();
        reqParam.addParam(Constants.PARAM_SCOPE, "all");
        reqParam.addParam("clientip", Util.getLocalIPAddress(context));
        reqParam.addParam("oauth_version", "2.a");
        reqParam.addParam("oauth_consumer_key", Util.getSharePersistent(context, "CLIENT_ID"));
        reqParam.addParam("openid", Util.getSharePersistent(context, "OPEN_ID"));
        reqParam.addParam("format", str);
        reqParam.addParam("reqnum", Integer.valueOf(i));
        reqParam.addParam("startindex", Integer.valueOf(i2));
        reqParam.addParam("mode", Integer.valueOf(i3));
        reqParam.addParam("install", Integer.valueOf(i4));
        startRequest(context, SERVER_URL_IDOLLIST, reqParam, httpCallback, cls, "GET", i5);
    }

    public void friendFansList(Context context, String str, int i, int i2, int i3, int i4, int i5, HttpCallback httpCallback, Class<? extends BaseVO> cls, int i6) {
        ReqParam reqParam = new ReqParam();
        reqParam.addParam(Constants.PARAM_SCOPE, "all");
        reqParam.addParam("clientip", Util.getLocalIPAddress(context));
        reqParam.addParam("oauth_version", "2.a");
        reqParam.addParam("oauth_consumer_key", Util.getSharePersistent(context, "CLIENT_ID"));
        reqParam.addParam("openid", Util.getSharePersistent(context, "OPEN_ID"));
        reqParam.addParam("format", str);
        reqParam.addParam("reqnum", Integer.valueOf(i));
        reqParam.addParam("startindex", Integer.valueOf(i2));
        reqParam.addParam("mode", Integer.valueOf(i3));
        reqParam.addParam("install", Integer.valueOf(i4));
        reqParam.addParam("sex", Integer.valueOf(i5));
        startRequest(context, SERVER_URL_FANSLIST, reqParam, httpCallback, cls, "GET", i6);
    }

    public void friendCheck(Context context, String str, String str2, String str3, int i, HttpCallback httpCallback, Class<? extends BaseVO> cls, int i2) {
        ReqParam reqParam = new ReqParam();
        reqParam.addParam(Constants.PARAM_SCOPE, "all");
        reqParam.addParam("clientip", Util.getLocalIPAddress(context));
        reqParam.addParam("oauth_version", "2.a");
        reqParam.addParam("oauth_consumer_key", Util.getSharePersistent(context, "CLIENT_ID"));
        reqParam.addParam("openid", Util.getSharePersistent(context, "OPEN_ID"));
        reqParam.addParam("format", str);
        reqParam.addParam("names", str2);
        reqParam.addParam("fopenids", str3);
        reqParam.addParam("flag", Integer.valueOf(i));
        startRequest(context, SERVER_URL_CHECK, reqParam, httpCallback, cls, "GET", i2);
    }

    public void getIntimateFriends(Context context, String str, int i, HttpCallback httpCallback, Class<? extends BaseVO> cls, int i2) {
        ReqParam reqParam = new ReqParam();
        reqParam.addParam(Constants.PARAM_SCOPE, "all");
        reqParam.addParam("clientip", Util.getLocalIPAddress(context));
        reqParam.addParam("oauth_version", "2.a");
        reqParam.addParam("oauth_consumer_key", Util.getSharePersistent(context, "CLIENT_ID"));
        reqParam.addParam("openid", Util.getSharePersistent(context, "OPEN_ID"));
        reqParam.addParam("format", str);
        reqParam.addParam("reqnum", Integer.valueOf(i));
        startRequest(context, SERVER_URL_GetINTIMATEFRIENDS, reqParam, httpCallback, cls, "GET", i2);
    }
}
