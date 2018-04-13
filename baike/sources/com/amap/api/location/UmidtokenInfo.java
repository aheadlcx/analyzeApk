package com.amap.api.location;

import android.content.Context;
import android.os.Handler;
import com.loc.cw;
import com.loc.n;
import com.sina.weibo.sdk.statistic.i;

public class UmidtokenInfo {
    static AMapLocationClient a = null;
    static Handler b = new Handler();
    static String c = null;

    static class a implements AMapLocationListener {
        a() {
        }

        public final void onLocationChanged(AMapLocation aMapLocation) {
            try {
                if (UmidtokenInfo.a != null) {
                    UmidtokenInfo.b.removeCallbacksAndMessages(null);
                    UmidtokenInfo.a.onDestroy();
                }
            } catch (Throwable th) {
                cw.a(th, "UmidListener", "onLocationChanged");
            }
        }
    }

    public static String getUmidtoken() {
        return c;
    }

    public static void setUmidtoken(Context context, String str) {
        try {
            c = str;
            n.a(str);
            if (a == null) {
                AMapLocationListener aVar = new a();
                a = new AMapLocationClient(context);
                AMapLocationClientOption aMapLocationClientOption = new AMapLocationClientOption();
                aMapLocationClientOption.setOnceLocation(true);
                aMapLocationClientOption.setNeedAddress(false);
                a.setLocationOption(aMapLocationClientOption);
                a.setLocationListener(aVar);
                a.startLocation();
                b.postDelayed(new e(), i.MIN_UPLOAD_INTERVAL);
            }
        } catch (Throwable th) {
            cw.a(th, "UmidListener", "setUmidtoken");
        }
    }
}
