package com.tencent.weibo.sdk.android.api;

import android.content.Context;
import android.util.Log;
import com.ali.auth.third.login.LoginConstants;
import com.tencent.connect.common.Constants;
import com.tencent.weibo.sdk.android.api.util.Util;
import com.tencent.weibo.sdk.android.model.AccountModel;
import com.tencent.weibo.sdk.android.model.BaseVO;
import com.tencent.weibo.sdk.android.model.ModelResult;
import com.tencent.weibo.sdk.android.network.HttpCallback;
import com.tencent.weibo.sdk.android.network.HttpReqWeiBo;
import com.tencent.weibo.sdk.android.network.HttpService;
import com.tencent.weibo.sdk.android.network.ReqParam;

public abstract class BaseAPI {
    public static final String API_SERVER = "https://open.t.qq.com/api";
    public static final String HTTPMETHOD_GET = "GET";
    public static final String HTTPMETHOD_POST = "POST";
    public static final String REQUEST_METHOD_GET = "GET";
    public static final String REQUEST_METHOD_POST = "POST";
    private HttpCallback callback = new HttpCallback() {
        public void onResult(Object obj) {
            Log.d("sss", obj);
            if (obj != null) {
                String[] split = ((ModelResult) obj).getObj().toString().split("&");
                String str = split[0].split(LoginConstants.EQUAL)[1];
                BaseAPI.this.mAccessToken = str;
                String str2 = split[1].split(LoginConstants.EQUAL)[1];
                String str3 = split[2].split(LoginConstants.EQUAL)[1];
                String str4 = split[3].split(LoginConstants.EQUAL)[1];
                String str5 = split[4].split(LoginConstants.EQUAL)[1];
                String str6 = split[5].split(LoginConstants.EQUAL)[1];
                Util.saveSharePersistent(BaseAPI.this.mContext, "ACCESS_TOKEN", str);
                Util.saveSharePersistent(BaseAPI.this.mContext, "EXPIRES_IN", str2);
                Util.saveSharePersistent(BaseAPI.this.mContext, "OPEN_ID", str4);
                Util.saveSharePersistent(BaseAPI.this.mContext, "REFRESH_TOKEN", str3);
                Util.saveSharePersistent(BaseAPI.this.mContext, "NAME", str5);
                Util.saveSharePersistent(BaseAPI.this.mContext, "NICK", str6);
                Util.saveSharePersistent(BaseAPI.this.mContext, "AUTHORIZETIME", String.valueOf(System.currentTimeMillis() / 1000));
                BaseAPI.this.weibo = new HttpReqWeiBo(BaseAPI.this.mContext, BaseAPI.this.mRequestUrl, BaseAPI.this.mmCallBack, BaseAPI.this.mmTargetClass, BaseAPI.this.mRequestMethod, Integer.valueOf(BaseAPI.this.mResultType));
                BaseAPI.this.mParams.addParam(Constants.PARAM_ACCESS_TOKEN, BaseAPI.this.mAccessToken);
                BaseAPI.this.weibo.setParam(BaseAPI.this.mParams);
                HttpService.getInstance().addImmediateReq(BaseAPI.this.weibo);
            }
        }
    };
    private String mAccessToken;
    private AccountModel mAccount;
    private Context mContext;
    private ReqParam mParams;
    private String mRequestMethod;
    private String mRequestUrl;
    private int mResultType;
    private HttpCallback mmCallBack;
    private Class<? extends BaseVO> mmTargetClass;
    private HttpReqWeiBo weibo;

    public BaseAPI(AccountModel accountModel) {
        this.mAccount = accountModel;
        if (this.mAccount != null) {
            this.mAccessToken = this.mAccount.getAccessToken();
        }
    }

    protected void startRequest(Context context, String str, ReqParam reqParam, HttpCallback httpCallback, Class<? extends BaseVO> cls, String str2, int i) {
        if (isAuthorizeExpired(context)) {
            this.mContext = context;
            this.mRequestUrl = str;
            this.mParams = reqParam;
            this.mmCallBack = httpCallback;
            this.mmTargetClass = cls;
            this.mRequestMethod = str2;
            this.mResultType = i;
            this.weibo = new HttpReqWeiBo(context, "https://open.t.qq.com/cgi-bin/oauth2/access_token", this.callback, null, "GET", Integer.valueOf(4));
            this.weibo.setParam(refreshToken(context));
            HttpService.getInstance().addImmediateReq(this.weibo);
            return;
        }
        this.weibo = new HttpReqWeiBo(context, str, httpCallback, cls, str2, Integer.valueOf(i));
        reqParam.addParam(Constants.PARAM_ACCESS_TOKEN, this.mAccessToken);
        this.weibo.setParam(reqParam);
        HttpService.getInstance().addImmediateReq(this.weibo);
    }

    private ReqParam refreshToken(Context context) {
        ReqParam reqParam = new ReqParam();
        String sharePersistent = Util.getSharePersistent(context, "CLIENT_ID");
        String sharePersistent2 = Util.getSharePersistent(context, "REFRESH_TOKEN");
        reqParam.addParam(Constants.PARAM_CLIENT_ID, sharePersistent);
        reqParam.addParam("grant_type", "refresh_token");
        reqParam.addParam("refresh_token", sharePersistent2);
        reqParam.addParam("state", Integer.valueOf((((int) Math.random()) * 1000) + 111));
        return reqParam;
    }

    public boolean isAuthorizeExpired(Context context) {
        String sharePersistent = Util.getSharePersistent(context, "AUTHORIZETIME");
        System.out.println("===== : " + sharePersistent);
        String sharePersistent2 = Util.getSharePersistent(context, "EXPIRES_IN");
        System.out.println("====== : " + sharePersistent2);
        long currentTimeMillis = System.currentTimeMillis() / 1000;
        if (sharePersistent2 == null || sharePersistent == null) {
            return false;
        }
        if (Long.valueOf(sharePersistent2).longValue() + Long.valueOf(sharePersistent).longValue() < currentTimeMillis) {
            return true;
        }
        return false;
    }
}
