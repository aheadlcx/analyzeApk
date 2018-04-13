package cn.xiaochuan.push.oppo;

import android.content.Context;
import cn.xiaochuan.base.BaseApplication;
import cn.xiaochuan.push.d;
import com.coloros.mcssdk.a;

public class b implements d {
    public static d a() {
        return new b();
    }

    private b() {
        b();
    }

    public static boolean a(Context context) {
        return a.a(context);
    }

    public void a(long j) {
        com.izuiyou.a.a.b.c(Long.valueOf(j));
        b();
    }

    private void b() {
        try {
            a.c().a(BaseApplication.getAppContext(), "34AckO9p3im80KKWoOcw8k04c", "37619A0903B58Cd8d06fDa2CbcAe629a", new com.coloros.mcssdk.d.b(this) {
                boolean a = true;
                final /* synthetic */ b b;

                {
                    this.b = r2;
                }

                public void a(int i, String str) {
                    super.a(i, str);
                    if (i == 0) {
                        cn.xiaochuan.push.a.a().a("op", str);
                    } else if (this.a) {
                        this.a = false;
                        a.c().e();
                    }
                    com.izuiyou.a.a.b.c("responseCode:" + i + "   registerID:" + str);
                }
            });
        } catch (Exception e) {
            com.izuiyou.a.a.a.d("OPPOPush", e);
        }
    }

    public void b(long j) {
    }

    public void a(int i) {
    }
}
