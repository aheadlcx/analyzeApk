package cn.v6.sixrooms.net;

import cn.v6.sixrooms.base.VLAsyncHandler;
import cn.v6.sixrooms.base.VLBlock;
import java.util.List;

final class b extends VLBlock {
    final /* synthetic */ VLAsyncHandler a;
    final /* synthetic */ String b;
    final /* synthetic */ List c;
    final /* synthetic */ NetworkServiceSingleton d;

    b(NetworkServiceSingleton networkServiceSingleton, VLAsyncHandler vLAsyncHandler, String str, List list) {
        this.d = networkServiceSingleton;
        this.a = vLAsyncHandler;
        this.b = str;
        this.c = list;
    }

    protected final void process(boolean z) {
        NetworkServiceSingleton.a(this.a, this.b, this.c);
    }
}
