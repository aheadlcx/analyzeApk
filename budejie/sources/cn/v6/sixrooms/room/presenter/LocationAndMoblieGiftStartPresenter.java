package cn.v6.sixrooms.room.presenter;

import android.app.Activity;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.support.annotation.NonNull;
import cn.v6.sdk.sixrooms.coop.V6Coop;
import cn.v6.sixrooms.base.VLScheduler;
import cn.v6.sixrooms.constants.CommonStrs;
import cn.v6.sixrooms.engine.GainMobileStarsEngine;
import cn.v6.sixrooms.room.engine.MobileStarsStatusEngine;
import cn.v6.sixrooms.room.statistic.StatisticCodeTable;
import cn.v6.sixrooms.room.statistic.StatisticValue;
import cn.v6.sixrooms.room.utils.LocationUtil;
import cn.v6.sixrooms.utils.AppInfoUtils;
import cn.v6.sixrooms.utils.LogUtils;
import cn.v6.sixrooms.utils.LoginUtils;
import cn.v6.sixrooms.utils.SaveUserInfoUtils;
import cn.v6.sixrooms.utils.SharedPreferencesUtils;
import java.lang.ref.WeakReference;

public class LocationAndMoblieGiftStartPresenter {
    LocationListener a = new e(this);
    private GainMobileStarsEngine b;
    private MobileStarsStatusEngine c;
    private LocationManager d;
    private boolean e = false;
    private WeakReference<Activity> f;
    private LocationAndMobileGiftStarCallBack g;
    private double h;
    private double i;

    public interface LocationAndMobileGiftStarCallBack {
        void error(int i);

        void handleErrorInfo(String str, String str2);

        void hideMobileStarGift();

        void result(String str, String str2);

        void showLoadingDialog();

        void showMobileStarGift();

        void tipLocation();

        void tipLogin();
    }

    public LocationAndMoblieGiftStartPresenter(Activity activity, @NonNull LocationAndMobileGiftStarCallBack locationAndMobileGiftStarCallBack) {
        this.g = locationAndMobileGiftStarCallBack;
        this.f = new WeakReference(activity);
    }

    public void onCreate() {
        this.e = false;
        this.c = new MobileStarsStatusEngine(new f(this));
        this.d = (LocationManager) V6Coop.getInstance().getContext().getSystemService(CommonStrs.TYPE_LOCATION);
        try {
            Location lastKnownLocation;
            if (this.d.isProviderEnabled("gps")) {
                lastKnownLocation = this.d.getLastKnownLocation("gps");
                if (lastKnownLocation != null) {
                    this.h = lastKnownLocation.getLatitude();
                    this.i = lastKnownLocation.getLongitude();
                    a(this.i, this.h);
                    LogUtils.e("LocationAndMoblieGiftStartPresenter", "LastKnownLocation : Lat: " + lastKnownLocation.getLatitude() + " Lng: " + lastKnownLocation.getLongitude() + "gps");
                    if (!LocationUtil.isOpenLocation() && ((Integer) SharedPreferencesUtils.getOnDefault(V6Coop.getInstance().getContext(), 0, SharedPreferencesUtils.TIP_LOCATION_VERSION, Integer.valueOf(0))).intValue() != AppInfoUtils.getAppCode()) {
                        if (this.g != null) {
                            this.g.tipLocation();
                        }
                        SharedPreferencesUtils.putOnDefault(V6Coop.getInstance().getContext(), 0, SharedPreferencesUtils.TIP_LOCATION_VERSION, Integer.valueOf(AppInfoUtils.getAppCode()));
                        return;
                    }
                }
                this.d.requestLocationUpdates("gps", 1000, 0.0f, this.a);
            }
            if (this.d.isProviderEnabled("network")) {
                lastKnownLocation = this.d.getLastKnownLocation("network");
                if (lastKnownLocation != null) {
                    this.h = lastKnownLocation.getLatitude();
                    this.i = lastKnownLocation.getLongitude();
                    a(this.i, this.h);
                    LogUtils.e("LocationAndMoblieGiftStartPresenter", "LastKnownLocation : Lat: " + lastKnownLocation.getLatitude() + " Lng: " + lastKnownLocation.getLongitude() + "gps");
                    if (!LocationUtil.isOpenLocation()) {
                    }
                }
                this.d.requestLocationUpdates("network", 1000, 0.0f, this.a);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        VLScheduler.instance.schedule(0, 0, new g(this));
        if (!LocationUtil.isOpenLocation()) {
        }
    }

    private void a(String str, String str2) {
        LogUtils.d("LocationAndMoblieGiftStartPresenter", "getMobileGiftPrivilege----lng-" + str);
        if (!this.e) {
            LogUtils.i("LocationAndMoblieGiftStartPresenter", "发送：lng/lat = " + str + "," + str2);
            this.c.getMobileGiftPrivilege(LoginUtils.getLoginUID(), SaveUserInfoUtils.getEncpass(V6Coop.getInstance().getContext()), str, str2);
            this.e = true;
        }
    }

    public void onDestory() {
        this.g = null;
        if (this.d != null) {
            if (this.a != null) {
                this.d.removeUpdates(this.a);
                this.a = null;
            }
            this.d = null;
        }
    }

    public void gainMobileGiftStar() {
        this.b = new GainMobileStarsEngine(new h(this));
        if (LoginUtils.getLoginUserBean() == null) {
            if (this.g != null) {
                this.g.tipLogin();
            }
            StatisticValue.getInstance().setFromRegisterPageModule("LiveHallFragment", StatisticCodeTable.MOBILE_STAR);
            return;
        }
        this.b.sendGetMobileStarsRequest(LoginUtils.getLoginUID(), SaveUserInfoUtils.getEncpass(V6Coop.getInstance().getContext()), "r");
    }
}
