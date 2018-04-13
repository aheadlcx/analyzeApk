package cn.xiaochuankeji.tieba.background.h;

import android.content.Context;
import android.os.Build;
import android.os.Build.VERSION;
import cn.htjyb.c.a;
import cn.htjyb.c.e;
import cn.xiaochuan.base.BaseApplication;
import cn.xiaochuankeji.tieba.R;
import cn.xiaochuankeji.tieba.api.config.ConfigService;
import cn.xiaochuankeji.tieba.background.AppController;
import cn.xiaochuankeji.tieba.json.EmptyJson;
import cn.xiaochuankeji.tieba.network.c;
import com.alibaba.fastjson.JSONObject;
import com.iflytek.speech.UtilityConfig;
import com.izuiyou.a.a.b;
import com.meizu.cloud.pushsdk.platform.pushstrategy.Strategy;
import com.tencent.tinker.loader.hotplug.EnvConsts;
import java.io.File;

public class g {
    private static g a;
    private static int n = -1;
    private Context b;
    private String c;
    private String d;
    private String e;
    private String f;
    private int g;
    private int h;
    private String i;
    private String j;
    private String k;
    private String l;
    private String m;

    private g(Context context) {
        this.b = context;
        b();
    }

    public static g a(Context context) {
        if (a == null) {
            a = new g(context);
        }
        return a;
    }

    private void b() {
        this.c = Build.MODEL;
        this.d = "Android";
        this.e = VERSION.RELEASE;
        this.f = a.e(this.b) + "x" + a.d(this.b);
        this.g = a.g(this.b);
        this.i = a.c(this.b);
        this.h = c();
        this.j = this.b.getString(R.string.app_name);
        this.k = e.a(this.b);
        this.l = BaseApplication.getAppContext().getPackageName();
        this.m = AppController.instance().packageChannel();
    }

    public void a() {
        ((ConfigService) c.b(ConfigService.class)).deviceInfo(a(new JSONObject())).a(rx.f.a.c()).a(new rx.e<EmptyJson>(this) {
            final /* synthetic */ g a;

            {
                this.a = r1;
            }

            public /* synthetic */ void onNext(Object obj) {
                a((EmptyJson) obj);
            }

            public void onCompleted() {
            }

            public void onError(Throwable th) {
                b.e(th);
            }

            public void a(EmptyJson emptyJson) {
                b.c(emptyJson);
            }
        });
    }

    private JSONObject a(JSONObject jSONObject) {
        try {
            JSONObject jSONObject2 = new JSONObject();
            JSONObject jSONObject3 = new JSONObject();
            jSONObject2.put("model", this.c);
            jSONObject2.put(com.umeng.analytics.b.g.p, this.d);
            jSONObject2.put("os_ver", this.e);
            jSONObject2.put(com.umeng.analytics.b.g.r, this.f);
            jSONObject2.put("dpi", Integer.valueOf(this.g));
            jSONObject2.put("is_jailbreak", Integer.valueOf(this.h));
            jSONObject2.put("mac", this.i);
            jSONObject2.put("imei", a.b(BaseApplication.getAppContext()));
            jSONObject3.put(Strategy.APP_KEY, "wread");
            jSONObject3.put("app_name", this.j);
            jSONObject3.put("app_ver", this.k);
            jSONObject3.put(EnvConsts.PACKAGE_MANAGER_SRVNAME, this.l);
            jSONObject3.put(com.umeng.analytics.b.g.b, this.m);
            jSONObject3.put("isreg", Integer.valueOf(cn.xiaochuankeji.tieba.background.a.g().d() ? 0 : 1));
            jSONObject.put(UtilityConfig.KEY_DEVICE_INFO, jSONObject2);
            jSONObject.put("app_setup", jSONObject3);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return jSONObject;
    }

    private int c() {
        if (n == 1) {
            return 1;
        }
        if (n == 0) {
            return 0;
        }
        String[] strArr = new String[]{"/system/bin/", "/system/xbin/", "/system/sbin/", "/sbin/", "/vendor/bin/"};
        int i = 0;
        while (i < strArr.length) {
            try {
                File file = new File(strArr[i] + "su");
                if (file == null || !file.exists()) {
                    i++;
                } else {
                    n = 1;
                    return n;
                }
            } catch (Exception e) {
            }
        }
        n = 0;
        return n;
    }
}
