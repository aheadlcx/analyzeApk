package qsbk.app.open.auth;

import android.util.Pair;
import qsbk.app.utils.HttpClient;
import qsbk.app.utils.LogUtil;

class h extends CommHttpAsyncTask {
    final /* synthetic */ String a;
    final /* synthetic */ String b;
    final /* synthetic */ SSOAuthActivity c;

    h(SSOAuthActivity sSOAuthActivity, String str, String str2) {
        this.c = sSOAuthActivity;
        this.a = str;
        this.b = str2;
    }

    public String getJSONResp() throws Exception {
        return null;
    }

    protected Pair<Integer, String> a(String... strArr) {
        try {
            return this.c.a.requestAuthCode(this.c.j, this.a, this.b);
        } catch (Exception e) {
            e.printStackTrace();
            return new Pair(Integer.valueOf(9999), HttpClient.getLocalErrorStr());
        }
    }

    protected void a(Pair<Integer, String> pair) {
        this.c.hideLoadingInfo();
        if (((Integer) pair.first).intValue() == 0) {
            String str = (String) pair.second;
            LogUtil.d("auth code:" + str);
            this.c.runGetAccessTokenProcess(str);
            return;
        }
        LogUtil.d("get auth code fail and show retry:");
        this.c.showAuthFailAndRetry((String) pair.second);
    }
}
