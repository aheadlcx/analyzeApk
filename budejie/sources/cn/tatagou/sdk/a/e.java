package cn.tatagou.sdk.a;

import android.os.Build.VERSION;
import android.util.Log;
import cn.tatagou.sdk.android.TtgConfig;
import cn.tatagou.sdk.android.TtgConfigKey;
import cn.tatagou.sdk.android.TtgSDK;
import cn.tatagou.sdk.pojo.Config;
import cn.tatagou.sdk.util.d;
import cn.tatagou.sdk.util.p;
import cn.v6.sixrooms.room.gift.BoxingVoteBean;
import com.alibaba.fastjson.support.spring.FastJsonJsonView;
import com.alipay.sdk.cons.b;
import com.baidu.mobads.interfaces.IXAdRequestInfo;
import com.tencent.connect.common.Constants;
import java.io.File;
import java.io.IOException;
import java.util.concurrent.TimeUnit;
import okhttp3.aa;
import okhttp3.c;
import okhttp3.t;
import okhttp3.w;
import okhttp3.y;
import org.apache.http.entity.mime.MIME;
import retrofit2.m;

public final class e {
    public static final String a = d.a;
    private static final String b = e.class.getSimpleName();
    private static int d = 10485760;
    private static File e;
    private static c f;
    private static w g;
    private final m c = new retrofit2.m.a().a(a).a(g).a();

    static class a {
        private static final e a = new e();
    }

    public static e getInstance() {
        if (g == null) {
            b();
        }
        return a.a;
    }

    private static void b() {
        if (f == null && TtgSDK.getContext() != null) {
            e = new File(TtgSDK.getContext().getCacheDir(), "/ttg/data/cache");
            f = new c(e, (long) d);
        }
        g = new okhttp3.w.a().a(f).a(5000, TimeUnit.MILLISECONDS).b(3000, TimeUnit.MILLISECONDS).c(3000, TimeUnit.MILLISECONDS).a(true).b(new t() {
            public aa intercept(okhttp3.t.a aVar) throws IOException {
                okhttp3.y.a e = aVar.a().e();
                String sVar = aVar.a().a().toString();
                Log.d(e.b, "intercept: " + sVar);
                e.a(aVar.a().a().o().a("source", TtgSDK.sSource).a("appVersion", "2.4.4.6").a("dt", p.getAndroidID(TtgSDK.getContext())).a("deviceId", p.phoneImei(TtgSDK.getContext())).a(com.alipay.sdk.sys.a.h, Config.getInstance().getAppVersion()).a(Constants.PARAM_PLATFORM_ID, "ANDROID").a(IXAdRequestInfo.V, "2.2.5").a(TtgConfigKey.KEY_APPDEVICEID, Config.getInstance().getAppDeviceId()).a(b.c, Config.getInstance().getTraceId()).a("ip", Config.getInstance().getIp()).a("pid", String.valueOf(TtgConfig.getInstance().getPid())).c()).a("User-Agent", "ttgsdka/2.4.4.6").a("Referer", "ttgsdka/2.4.4.6").b(MIME.CONTENT_TYPE, "application/x-www-form-urlencoded; charset=UTF-8").b("content_encoding", "gzip");
                String keyParams = c.getInstance().getKeyParams(sVar);
                Log.d(e.b, "onResponseHome urlKey: " + sVar);
                if (!p.isEmpty(keyParams)) {
                    Log.d(e.b, "onResponseHome etag: " + keyParams);
                    e.a("If-None-Match", keyParams);
                }
                y b = e.b();
                Log.i("okhttp", "uri=" + b.a().a().toString());
                if (!(p.isNetworkOpen(TtgSDK.getContext()) || TtgSDK.getContext() == null)) {
                    b = b.e().a(okhttp3.d.b).b();
                }
                if (VERSION.SDK != null && VERSION.SDK_INT > 13) {
                    b = b.e().b("Connection", BoxingVoteBean.BOXING_VOTE_STATE_CLOSE).b();
                }
                okhttp3.aa.a b2 = aVar.a(b).i().b("Pragma").b("CONTENT_ENCODING", "gzip").b("CONTENT_TYPE", FastJsonJsonView.DEFAULT_CONTENT_TYPE);
                if (!p.isNetworkOpen(TtgSDK.getContext()) || TtgSDK.getContext() == null) {
                    return b2.a("Cache-Control", "public, only-if-cached, max-stale=2419200").a();
                }
                return b2.a("Cache-Control", b.f().toString()).a();
            }
        }).a();
    }

    public <T> T getService(Class<T> cls) {
        return this.c.a(cls);
    }
}
