package qsbk.app.open.auth;

import android.text.TextUtils;
import android.util.Pair;
import qsbk.app.utils.LogUtil;

class f extends CommHttpAsyncTask {
    final /* synthetic */ String a;
    final /* synthetic */ SSOAuthActivity b;

    f(SSOAuthActivity sSOAuthActivity, String str) {
        this.b = sSOAuthActivity;
        this.a = str;
    }

    public String getJSONResp() throws Exception {
        String appInfo = this.b.a.getAppInfo(this.a);
        LogUtil.d("resp:" + appInfo);
        return appInfo;
    }

    protected void a(Pair<Integer, String> pair) {
        this.b.hideLoadingInfo();
        if (((Integer) pair.first).intValue() == 0) {
            LogUtil.d("resp:" + getJSONObj().toString());
            AppInfo appInfo = new AppInfo(getJSONObj());
            if (!(TextUtils.isEmpty(appInfo.getIcon()) || TextUtils.isEmpty(appInfo.getName()))) {
                AppInfo.saveAppInfoToLocal(this.a, appInfo);
            }
            this.b.checkAppInfo(appInfo, this.a);
            return;
        }
        LogUtil.d("code:" + pair.first);
        this.b.showAuthFailAndRetry((String) pair.second);
    }
}
