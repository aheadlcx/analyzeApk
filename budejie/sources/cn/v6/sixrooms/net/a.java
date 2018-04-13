package cn.v6.sixrooms.net;

import cn.v6.sixrooms.base.VLAsyncHandler;
import cn.v6.sixrooms.base.VLBlock;

final class a extends VLBlock {
    final /* synthetic */ VLAsyncHandler a;
    final /* synthetic */ String b;
    final /* synthetic */ String c;
    final /* synthetic */ NetworkServiceSingleton d;

    a(NetworkServiceSingleton networkServiceSingleton, VLAsyncHandler vLAsyncHandler, String str, String str2) {
        this.d = networkServiceSingleton;
        this.a = vLAsyncHandler;
        this.b = str;
        this.c = str2;
    }

    protected final void process(boolean z) {
        NetworkServiceSingleton.a(this.a, this.b, this.c);
    }
}
