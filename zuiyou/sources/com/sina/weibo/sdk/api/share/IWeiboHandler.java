package com.sina.weibo.sdk.api.share;

public interface IWeiboHandler {

    public interface Response {
        void onResponse(BaseResponse baseResponse);
    }

    public interface Request {
        void onRequest(BaseRequest baseRequest);
    }
}
