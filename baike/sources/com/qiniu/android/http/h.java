package com.qiniu.android.http;

import com.qiniu.android.utils.StringMap.Consumer;
import okhttp3.MultipartBody.Builder;

class h implements Consumer {
    final /* synthetic */ Builder a;
    final /* synthetic */ Client b;

    h(Client client, Builder builder) {
        this.b = client;
        this.a = builder;
    }

    public void accept(String str, Object obj) {
        this.a.addFormDataPart(str, obj.toString());
    }
}
