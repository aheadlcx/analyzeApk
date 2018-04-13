package com.qiniu.android.storage;

import com.qiniu.android.http.Client;
import com.qiniu.android.http.CompletionHandler;
import com.qiniu.android.http.PostArgs;
import com.qiniu.android.http.ProgressHandler;
import com.qiniu.android.http.ResponseInfo;
import com.qiniu.android.utils.AndroidNetwork;
import java.net.URI;
import org.json.JSONObject;

final class d implements CompletionHandler {
    final /* synthetic */ UploadOptions a;
    final /* synthetic */ UpCompletionHandler b;
    final /* synthetic */ String c;
    final /* synthetic */ UpToken d;
    final /* synthetic */ Configuration e;
    final /* synthetic */ Client f;
    final /* synthetic */ PostArgs g;
    final /* synthetic */ ProgressHandler h;

    d(UploadOptions uploadOptions, UpCompletionHandler upCompletionHandler, String str, UpToken upToken, Configuration configuration, Client client, PostArgs postArgs, ProgressHandler progressHandler) {
        this.a = uploadOptions;
        this.b = upCompletionHandler;
        this.c = str;
        this.d = upToken;
        this.e = configuration;
        this.f = client;
        this.g = postArgs;
        this.h = progressHandler;
    }

    public void complete(ResponseInfo responseInfo, JSONObject jSONObject) {
        if (responseInfo.isNetworkBroken() && !AndroidNetwork.isNetWorkReady()) {
            this.a.f.waitReady();
            if (!AndroidNetwork.isNetWorkReady()) {
                this.b.complete(this.c, responseInfo, jSONObject);
                return;
            }
        }
        if (responseInfo.isOK()) {
            this.a.d.progress(this.c, 1.0d);
            this.b.complete(this.c, responseInfo, jSONObject);
        } else if (this.a.e.isCancelled()) {
            this.b.complete(this.c, ResponseInfo.cancelled(this.d), null);
        } else if (responseInfo.needRetry() || (responseInfo.isNotQiniu() && !this.d.hasReturnUrl())) {
            URI uri;
            CompletionHandler eVar = new e(this);
            URI uri2 = this.e.zone.upHost(this.d.token).address;
            if (this.e.zone.upHostBackup(this.d.token) == null || !(responseInfo.needSwitchServer() || responseInfo.isNotQiniu())) {
                uri = uri2;
            } else {
                uri = this.e.zone.upHostBackup(this.d.token).address;
            }
            this.f.asyncMultipartPost(uri.toString(), this.g, this.d, this.h, eVar, this.a.e);
        } else {
            this.b.complete(this.c, responseInfo, jSONObject);
        }
    }
}
