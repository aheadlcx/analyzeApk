package cn.v6.sixrooms.room.presenter;

import android.location.Location;
import android.location.LocationListener;
import android.os.Bundle;
import cn.v6.sixrooms.utils.LogUtils;

final class e implements LocationListener {
    final /* synthetic */ LocationAndMoblieGiftStartPresenter a;

    e(LocationAndMoblieGiftStartPresenter locationAndMoblieGiftStartPresenter) {
        this.a = locationAndMoblieGiftStartPresenter;
    }

    public final void onStatusChanged(String str, int i, Bundle bundle) {
        LogUtils.e("LocationAndMoblieGiftStartPresenter", "onStatusChanged");
    }

    public final void onProviderEnabled(String str) {
        LogUtils.e("LocationAndMoblieGiftStartPresenter", "onProviderEnabled====" + str);
    }

    public final void onProviderDisabled(String str) {
        LogUtils.e("LocationAndMoblieGiftStartPresenter", "onProviderDisabled:===" + str);
    }

    public final void onLocationChanged(Location location) {
        if (location != null) {
            LocationAndMoblieGiftStartPresenter.a(this.a, location.getLongitude());
            LocationAndMoblieGiftStartPresenter.b(this.a, location.getLongitude());
            LocationAndMoblieGiftStartPresenter.a(this.a, LocationAndMoblieGiftStartPresenter.a(this.a), LocationAndMoblieGiftStartPresenter.b(this.a));
            LogUtils.i("LocationAndMoblieGiftStartPresenter", "Location changed : Lat: " + location.getLatitude() + " Lng: " + location.getLongitude());
            LocationAndMoblieGiftStartPresenter.c(this.a).removeUpdates(this);
        }
    }
}
