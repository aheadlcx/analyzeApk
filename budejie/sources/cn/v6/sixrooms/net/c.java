package cn.v6.sixrooms.net;

import cn.v6.sixrooms.base.VLAsyncHandler;
import cn.v6.sixrooms.base.VLBlock;
import org.apache.http.entity.mime.MultipartEntity;

final class c extends VLBlock {
    final /* synthetic */ VLAsyncHandler a;
    final /* synthetic */ String b;
    final /* synthetic */ MultipartEntity c;
    final /* synthetic */ NetworkServiceSingleton d;

    c(NetworkServiceSingleton networkServiceSingleton, VLAsyncHandler vLAsyncHandler, String str, MultipartEntity multipartEntity) {
        this.d = networkServiceSingleton;
        this.a = vLAsyncHandler;
        this.b = str;
        this.c = multipartEntity;
    }

    protected final void process(boolean z) {
        NetworkServiceSingleton.a(this.a, this.b, this.c);
    }
}
