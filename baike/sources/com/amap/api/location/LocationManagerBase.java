package com.amap.api.location;

import android.app.PendingIntent;

public interface LocationManagerBase {
    void addGeoFenceAlert(String str, double d, double d2, float f, long j, PendingIntent pendingIntent);

    AMapLocation getLastKnownLocation();

    boolean isStarted();

    void onDestroy();

    void removeGeoFenceAlert(PendingIntent pendingIntent);

    void removeGeoFenceAlert(PendingIntent pendingIntent, String str);

    void setLocationListener(AMapLocationListener aMapLocationListener);

    void setLocationOption(AMapLocationClientOption aMapLocationClientOption);

    void startAssistantLocation();

    void startLocation();

    void stopAssistantLocation();

    void stopLocation();

    void unRegisterLocationListener(AMapLocationListener aMapLocationListener);
}
