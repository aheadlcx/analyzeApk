package cn.xiaochuankeji.tieba.background.g;

import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Context;
import android.support.v4.content.ContextCompat;
import cn.xiaochuankeji.tieba.analyse.a;
import com.izuiyou.a.a.b;

public class c {
    public static void a(e eVar) {
        d.a().a(eVar);
    }

    public static void b(e eVar) {
        d.a().b(eVar);
    }

    public static void a(Activity activity) {
        b.c("CheckLocationPermission");
        try {
            if (a((Context) activity)) {
                b(activity);
            } else {
                b.a((Context) activity);
            }
        } catch (Throwable e) {
            e.printStackTrace();
            a.a(e);
        }
    }

    private static boolean a(Context context) {
        return a(context, "android.permission.ACCESS_FINE_LOCATION", "android.permission.ACCESS_COARSE_LOCATION");
    }

    private static boolean a(Context context, String... strArr) {
        for (String a : strArr) {
            if (a(context, a)) {
                return true;
            }
        }
        return false;
    }

    private static boolean a(Context context, String str) {
        return ContextCompat.checkSelfPermission(context, str) == -1;
    }

    @TargetApi(23)
    private static void b(Activity activity) {
        if (a((Context) activity)) {
            b.c("RequestLocationPermission");
        }
    }
}
