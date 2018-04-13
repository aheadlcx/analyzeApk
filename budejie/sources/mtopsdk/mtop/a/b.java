package mtopsdk.mtop.a;

import android.content.Context;
import com.taobao.tao.remotebusiness.listener.c;
import mtopsdk.common.util.TBSdkLog$LogEnable;
import mtopsdk.common.util.m;

final class b implements Runnable {
    final /* synthetic */ Context a;
    final /* synthetic */ c b;
    final /* synthetic */ String c;

    b(Context context, c cVar, String str) {
        this.a = context;
        this.b = cVar;
        this.c = str;
    }

    public final void run() {
        if (m.a(TBSdkLog$LogEnable.InfoEnable)) {
            m.b("mtopsdk.MtopSDK", "[init]MtopSDK init Called");
        }
        a.c(this.a, this.b, this.c);
    }
}
