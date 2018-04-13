package com.qiniu.android.storage;

import com.qiniu.android.http.CompletionHandler;
import com.qiniu.android.http.ResponseInfo;
import com.qiniu.android.utils.AndroidNetwork;
import java.net.URI;
import org.json.JSONObject;

class j implements CompletionHandler {
    final /* synthetic */ int a;
    final /* synthetic */ long b;
    final /* synthetic */ URI c;
    final /* synthetic */ int d;
    final /* synthetic */ f e;

    j(f fVar, int i, long j, URI uri, int i2) {
        this.e = fVar;
        this.a = i;
        this.b = j;
        this.c = uri;
        this.d = i2;
    }

    public void complete(ResponseInfo responseInfo, JSONObject jSONObject) {
        if (responseInfo.isNetworkBroken() && !AndroidNetwork.isNetWorkReady()) {
            this.e.d.f.waitReady();
            if (!AndroidNetwork.isNetWorkReady()) {
                this.e.c.complete(this.e.b, responseInfo, jSONObject);
                return;
            }
        }
        if (f.c(responseInfo, jSONObject)) {
            String str = null;
            if (jSONObject != null || this.a >= this.e.f.retryMax) {
                String str2;
                long j = 0;
                long j2;
                try {
                    str = jSONObject.getString("ctx");
                    str2 = str;
                    j2 = jSONObject.getLong("crc32");
                } catch (Exception e) {
                    e.printStackTrace();
                    long j3 = j;
                    str2 = str;
                    j2 = j3;
                }
                if ((str2 == null || r0 != this.e.n) && this.a < this.e.f.retryMax) {
                    this.e.a(this.b, this.a + 1, this.e.f.zone.upHostBackup(this.e.o.token).address);
                    return;
                }
                this.e.h[(int) (this.b / 4194304)] = str2;
                this.e.c(this.b + ((long) this.d));
                this.e.a(this.b + ((long) this.d), this.a, this.c);
                return;
            }
            this.e.a(this.b, this.a + 1, this.e.f.zone.upHostBackup(this.e.o.token).address);
        } else if (responseInfo.statusCode == 701 && this.a < this.e.f.retryMax) {
            this.e.a((this.b / 4194304) * 4194304, this.a + 1, this.c);
        } else if (this.e.f.zone.upHostBackup(this.e.o.token) == null || (!(f.d(responseInfo, jSONObject) || responseInfo.needRetry()) || this.a >= this.e.f.retryMax)) {
            this.e.c.complete(this.e.b, responseInfo, jSONObject);
        } else {
            this.e.a(this.b, this.a + 1, this.e.f.zone.upHostBackup(this.e.o.token).address);
        }
    }
}
