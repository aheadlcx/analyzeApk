package qsbk.app.utils;

import com.alipay.sdk.util.e;
import com.qiushibaike.httpdns.lib.DomainRecord;
import com.qiushibaike.httpdns.lib.FetchResultListener;
import com.qiushibaike.statsdk.StatSDK;
import qsbk.app.QsbkApp;

class ai implements FetchResultListener {
    final /* synthetic */ String a;
    final /* synthetic */ HttpDNSManager b;

    ai(HttpDNSManager httpDNSManager, String str) {
        this.b = httpDNSManager;
        this.a = str;
    }

    public void onSuccess(DomainRecord domainRecord) {
        StatSDK.onEvent(QsbkApp.mContext, "httpdns", "success", domainRecord.domain, domainRecord.ip, this.a);
    }

    public void onFailure(String str, Exception exception) {
        StatSDK.onEvent(QsbkApp.mContext, "httpdns", e.b, str, exception + "", this.a);
    }
}
