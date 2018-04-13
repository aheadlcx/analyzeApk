package com.loc;

import android.os.Bundle;
import com.amap.api.fence.GeoFence;
import com.amap.api.location.AMapLocation;
import com.amap.api.location.AMapLocationListener;
import com.umeng.commonsdk.proguard.g;

final class av implements AMapLocationListener {
    final /* synthetic */ a a;

    av(a aVar) {
        this.a = aVar;
    }

    public final void onLocationChanged(AMapLocation aMapLocation) {
        int i = 8;
        Object obj = 1;
        try {
            Bundle bundle;
            a aVar;
            this.a.q = aMapLocation;
            if (aMapLocation != null) {
                i = aMapLocation.getErrorCode();
                if (aMapLocation.getErrorCode() == 0) {
                    this.a.r = de.b();
                    this.a.a(5, null, 0);
                    if (obj == null) {
                        this.a.t = 0;
                        this.a.a(6, null, 0);
                    }
                    bundle = new Bundle();
                    if (!this.a.l) {
                        this.a.a(7);
                        bundle.putLong(g.az, 2000);
                        this.a.a(8, bundle, 2000);
                    }
                    aVar = this.a;
                    aVar.t++;
                    if (this.a.t >= 3) {
                        bundle.putInt(GeoFence.BUNDLE_KEY_LOCERRORCODE, i);
                        this.a.a(1002, bundle);
                        return;
                    }
                    return;
                }
                a aVar2 = this.a;
                a.a("定位失败", aMapLocation.getErrorCode(), aMapLocation.getErrorInfo(), "locationDetail:" + aMapLocation.getLocationDetail());
            }
            obj = null;
            if (obj == null) {
                bundle = new Bundle();
                if (this.a.l) {
                    this.a.a(7);
                    bundle.putLong(g.az, 2000);
                    this.a.a(8, bundle, 2000);
                }
                aVar = this.a;
                aVar.t++;
                if (this.a.t >= 3) {
                    bundle.putInt(GeoFence.BUNDLE_KEY_LOCERRORCODE, i);
                    this.a.a(1002, bundle);
                    return;
                }
                return;
            }
            this.a.t = 0;
            this.a.a(6, null, 0);
        } catch (Throwable th) {
        }
    }
}
