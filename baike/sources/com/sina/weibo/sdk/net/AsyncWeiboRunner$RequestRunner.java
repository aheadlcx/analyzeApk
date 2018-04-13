package com.sina.weibo.sdk.net;

import android.content.Context;
import android.os.AsyncTask;
import com.sina.weibo.sdk.exception.WeiboException;

public class AsyncWeiboRunner$RequestRunner extends AsyncTask<Void, Void, AsyncWeiboRunner$a<String>> {
    private final Context a;
    private final String b;
    private final WeiboParameters c;
    private final String d;
    private final RequestListener e;

    protected /* synthetic */ Object doInBackground(Object[] objArr) {
        return a((Void[]) objArr);
    }

    protected /* synthetic */ void onPostExecute(Object obj) {
        a((AsyncWeiboRunner$a) obj);
    }

    public AsyncWeiboRunner$RequestRunner(Context context, String str, WeiboParameters weiboParameters, String str2, RequestListener requestListener) {
        this.a = context;
        this.b = str;
        this.c = weiboParameters;
        this.d = str2;
        this.e = requestListener;
    }

    protected AsyncWeiboRunner$a<String> a(Void... voidArr) {
        try {
            return new AsyncWeiboRunner$a(HttpManager.openUrl(this.a, this.b, this.d, this.c));
        } catch (WeiboException e) {
            return new AsyncWeiboRunner$a(e);
        }
    }

    protected void onPreExecute() {
    }

    protected void a(AsyncWeiboRunner$a<String> asyncWeiboRunner$a) {
        WeiboException error = asyncWeiboRunner$a.getError();
        if (error != null) {
            this.e.onWeiboException(error);
        } else {
            this.e.onComplete((String) asyncWeiboRunner$a.getResult());
        }
    }
}
