package com.sina.weibo.sdk.net;

import android.content.Context;
import android.os.AsyncTask;
import com.sina.weibo.sdk.exception.WeiboException;

class AsyncWeiboRunner$RequestRunner extends AsyncTask<Void, Void, AsyncWeiboRunner$AsyncTaskResult<String>> {
    private final Context mContext;
    private final String mHttpMethod;
    private final RequestListener mListener;
    private final WeiboParameters mParams;
    private final String mUrl;

    public AsyncWeiboRunner$RequestRunner(Context context, String str, WeiboParameters weiboParameters, String str2, RequestListener requestListener) {
        this.mContext = context;
        this.mUrl = str;
        this.mParams = weiboParameters;
        this.mHttpMethod = str2;
        this.mListener = requestListener;
    }

    protected AsyncWeiboRunner$AsyncTaskResult<String> doInBackground(Void... voidArr) {
        try {
            return new AsyncWeiboRunner$AsyncTaskResult(HttpManager.openUrl(this.mContext, this.mUrl, this.mHttpMethod, this.mParams));
        } catch (WeiboException e) {
            return new AsyncWeiboRunner$AsyncTaskResult(e);
        }
    }

    protected void onPreExecute() {
    }

    protected void onPostExecute(AsyncWeiboRunner$AsyncTaskResult<String> asyncWeiboRunner$AsyncTaskResult) {
        WeiboException error = asyncWeiboRunner$AsyncTaskResult.getError();
        if (error != null) {
            this.mListener.onWeiboException(error);
        } else {
            this.mListener.onComplete((String) asyncWeiboRunner$AsyncTaskResult.getResult());
        }
    }
}
