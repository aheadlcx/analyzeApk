package com.qiniu.android.storage;

import com.qiniu.android.http.CompletionHandler;
import com.qiniu.android.http.ResponseInfo;
import com.qiniu.android.utils.AndroidNetwork;
import org.json.JSONObject;

class h implements CompletionHandler {
    final /* synthetic */ int a;
    final /* synthetic */ long b;
    final /* synthetic */ f c;

    h(f fVar, int i, long j) {
        this.c = fVar;
        this.a = i;
        this.b = j;
    }

    public void complete(ResponseInfo responseInfo, JSONObject jSONObject) {
        if (responseInfo.isNetworkBroken() && !AndroidNetwork.isNetWorkReady()) {
            this.c.d.f.waitReady();
            if (!AndroidNetwork.isNetWorkReady()) {
                this.c.c.complete(this.c.b, responseInfo, jSONObject);
                return;
            }
        }
        if (responseInfo.isOK()) {
            this.c.c();
            this.c.d.d.progress(this.c.b, 1.0d);
            this.c.c.complete(this.c.b, responseInfo, jSONObject);
        } else if (this.c.f.zone.upHostBackup(this.c.o.token) == null || (((!responseInfo.isNotQiniu() || this.c.o.hasReturnUrl()) && !responseInfo.needRetry()) || this.a >= this.c.f.retryMax)) {
            this.c.c.complete(this.c.b, responseInfo, jSONObject);
        } else {
            this.c.a(this.b, this.a + 1, this.c.f.zone.upHostBackup(this.c.o.token).address);
        }
    }
}
