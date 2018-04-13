package com.amap.api.fence;

import android.app.PendingIntent;
import android.content.Context;
import com.amap.api.location.DPoint;
import com.loc.a;
import com.loc.au;
import com.loc.cw;
import java.util.ArrayList;
import java.util.List;

public class GeoFenceClient {
    public static final int GEOFENCE_IN = 1;
    public static final int GEOFENCE_OUT = 2;
    public static final int GEOFENCE_STAYED = 4;
    Context a = null;
    GeoFenceManagerBase b = null;

    public GeoFenceClient(Context context) {
        if (context == null) {
            try {
                throw new IllegalArgumentException("Context参数不能为null");
            } catch (Throwable th) {
                cw.a(th, "GeoFenceClient", "<init>");
                return;
            }
        }
        this.a = context.getApplicationContext();
        this.b = a(this.a);
    }

    private static GeoFenceManagerBase a(Context context) {
        GeoFenceManagerBase geoFenceManagerBase;
        try {
            geoFenceManagerBase = (GeoFenceManagerBase) au.a(context, cw.b(), "com.amap.api.fence.GeoFenceManagerWrapper", a.class, new Class[]{Context.class}, new Object[]{context});
        } catch (Throwable th) {
            geoFenceManagerBase = new a(context);
        }
        return geoFenceManagerBase == null ? new a(context) : geoFenceManagerBase;
    }

    public void addGeoFence(DPoint dPoint, float f, String str) {
        try {
            this.b.addRoundGeoFence(dPoint, f, str, null, -1, null);
        } catch (Throwable th) {
            cw.a(th, "GeoFenceClient", "addGeoFence round");
        }
    }

    public void addGeoFence(String str, String str2) {
        try {
            this.b.addDistrictGeoFence(str, str2);
        } catch (Throwable th) {
            cw.a(th, "GeoFenceClient", "addGeoFence district");
        }
    }

    public void addGeoFence(String str, String str2, DPoint dPoint, float f, int i, String str3) {
        try {
            this.b.addNearbyGeoFence(str, str2, dPoint, f, i, str3);
        } catch (Throwable th) {
            cw.a(th, "GeoFenceClient", "addGeoFence searche");
        }
    }

    public void addGeoFence(String str, String str2, String str3, int i, String str4) {
        try {
            this.b.addKeywordGeoFence(str, str2, str3, i, str4);
        } catch (Throwable th) {
            cw.a(th, "GeoFenceClient", "addGeoFence searche");
        }
    }

    public void addGeoFence(List<DPoint> list, String str) {
        try {
            this.b.addPolygonGeoFence(list, str);
        } catch (Throwable th) {
            cw.a(th, "GeoFenceClient", "addGeoFence polygon");
        }
    }

    public PendingIntent createPendingIntent(String str) {
        PendingIntent pendingIntent = null;
        try {
            pendingIntent = this.b.createPendingIntent(str);
        } catch (Throwable th) {
            cw.a(th, "GeoFenceClient", "creatPendingIntent");
        }
        return pendingIntent;
    }

    public List<GeoFence> getAllGeoFence() {
        List<GeoFence> arrayList = new ArrayList();
        try {
            arrayList = this.b.getAllGeoFence();
        } catch (Throwable th) {
            cw.a(th, "GeoFenceClient", "getGeoFenceList");
        }
        return arrayList;
    }

    public void removeGeoFence() {
        try {
            this.b.removeGeoFence();
        } catch (Throwable th) {
            cw.a(th, "GeoFenceClient", "removeGeoFence");
        }
    }

    public boolean removeGeoFence(GeoFence geoFence) {
        try {
            return this.b.removeGeoFence(geoFence);
        } catch (Throwable th) {
            cw.a(th, "GeoFenceClient", "removeGeoFence1");
            return false;
        }
    }

    public void setActivateAction(int i) {
        try {
            this.b.setActivateAction(i);
        } catch (Throwable th) {
            cw.a(th, "GeoFenceClient", "setActivatesAction");
        }
    }

    public void setGeoFenceListener(GeoFenceListener geoFenceListener) {
        try {
            this.b.setGeoFenceListener(geoFenceListener);
        } catch (Throwable th) {
            cw.a(th, "GeoFenceClient", "setGeoFenceListener");
        }
    }
}
