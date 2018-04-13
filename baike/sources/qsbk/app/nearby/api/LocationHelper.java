package qsbk.app.nearby.api;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import com.baidu.mobstat.StatService;
import com.xiaomi.mipush.sdk.Constants;
import qsbk.app.QsbkApp;
import qsbk.app.R;
import qsbk.app.core.utils.PrefrenceKeys;
import qsbk.app.live.ui.LivePushActivity;
import qsbk.app.nearby.api.LocationCache.Location;
import qsbk.app.utils.LogUtil;
import qsbk.app.utils.SharePreferenceUtils;
import qsbk.app.utils.ToastAndDialog;

public class LocationHelper implements ILocationCallback {
    public static final int REQ_LOCATION_SERVICE = 101;
    private static long a = 0;
    private static double b = 0.0d;
    private static double c = 0.0d;
    private static String d = null;
    private static String e = null;
    private int f = 0;
    private ILocationManager g;
    private LocationCallBack h;
    private LocationWarnUIProvider i;
    private Handler j;
    private Context k;
    private Fragment l = null;

    public interface LocationCallBack {
        public static final int CANCEL = 2;
        public static final int DISABLE = 0;
        public static final int FAIL = 1;

        void onLocateDone(double d, double d2, String str, String str2);

        void onLocateFail(int i);
    }

    public interface LocationWarnUIProvider {
        void onHideServiceDisableWarn();

        void onShowServiceDisableWarn();
    }

    public LocationHelper(Fragment fragment) {
        this.l = fragment;
        this.k = fragment.getActivity();
    }

    public LocationHelper(Context context) {
        this.k = context;
        this.l = null;
    }

    protected static long a() {
        return SharePreferenceUtils.getSharePreferencesLongValue("local_phone_location_time");
    }

    protected static double[] b() {
        Object sharePreferencesValue = SharePreferenceUtils.getSharePreferencesValue("local_phone_location_new");
        if (TextUtils.isEmpty(sharePreferencesValue)) {
            return null;
        }
        String[] split = sharePreferencesValue.split(Constants.ACCEPT_TIME_SEPARATOR_SP);
        try {
            return new double[]{Double.parseDouble(split[0]), Double.parseDouble(split[1])};
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    protected static String c() {
        return SharePreferenceUtils.getSharePreferencesValue("local_phone_location_district");
    }

    protected static String d() {
        return SharePreferenceUtils.getSharePreferencesValue("local_phone_location_city");
    }

    public static boolean loadCache() {
        long a = a();
        if (a > 0 && (System.currentTimeMillis() / 1000) - a >= 86400) {
            return false;
        }
        double[] b = b();
        if (b == null) {
            return false;
        }
        b = b[0];
        c = b[1];
        d = c();
        e = d();
        return true;
    }

    public static double getLatitude() {
        return b;
    }

    public static double getLongitude() {
        return c;
    }

    public static String getDistrict() {
        return d;
    }

    public static String getCity() {
        return e;
    }

    public void startLocate(LocationCallBack locationCallBack) {
        startLocate(locationCallBack, null);
    }

    public void startLocate(LocationCallBack locationCallBack, LocationWarnUIProvider locationWarnUIProvider) {
        this.h = locationCallBack;
        this.i = locationWarnUIProvider;
        this.f = 0;
        if (this.g == null) {
            g();
        }
    }

    public void stop() {
        if (this.h != null) {
            this.h.onLocateFail(2);
        }
        this.h = null;
    }

    public void distory() {
        stop();
        this.k = null;
        this.l = null;
        this.j = null;
    }

    private void g() {
        boolean isLocationServiceEnabled;
        try {
            isLocationServiceEnabled = NearbyEngine.instance().isLocationServiceEnabled(this.k);
        } catch (Exception e) {
            isLocationServiceEnabled = true;
        }
        if (isLocationServiceEnabled) {
            h();
        } else {
            e();
        }
    }

    private void h() {
        if (System.currentTimeMillis() - a <= LivePushActivity.INNER) {
            if (this.h != null) {
                this.h.onLocateDone(b, c, d, e);
            }
            this.h = null;
            return;
        }
        i();
    }

    private void i() {
        if (this.g == null) {
            this.g = j();
        }
        if (this.g.getLocation(this) == 6) {
            if (this.j == null) {
                this.j = new Handler();
            }
            this.j.postDelayed(new d(this), 2000);
        }
    }

    public LocationWarnUIProvider getSimpleWarnUI() {
        return new e(this);
    }

    protected void e() {
        if (this.i != null) {
            this.i.onShowServiceDisableWarn();
        } else {
            cancelOpenServiceSettings();
        }
    }

    public void openServiceSetting() {
        try {
            Intent intent = new Intent("android.settings.LOCATION_SOURCE_SETTINGS");
            if (this.l != null) {
                this.l.startActivityForResult(intent, 101);
            } else if (this.k instanceof Activity) {
                ((Activity) this.k).startActivityForResult(intent, 101);
            }
        } catch (Exception e) {
            LogUtil.d("open location setting error:" + e.toString());
            a("open_gps_error");
            ToastAndDialog.makeNegativeToast(QsbkApp.mContext, QsbkApp.mContext.getResources().getString(R.string.no_gps_enabled), Integer.valueOf(0)).show();
        }
    }

    public void cancelOpenServiceSettings() {
        if (this.h != null) {
            this.h.onLocateFail(0);
        }
        this.h = null;
    }

    private void a(String str) {
        StatService.onEvent(this.k, "location", str);
    }

    protected void a(double d, double d2, String str, String str2) {
        SharePreferenceUtils.setSharePreferencesValue("local_phone_location_new", d + Constants.ACCEPT_TIME_SEPARATOR_SP + d2);
        SharePreferenceUtils.setSharePreferencesValue("local_phone_location_district", str);
        SharePreferenceUtils.setSharePreferencesValue("local_phone_location_city", str2);
        SharePreferenceUtils.setSharePreferencesValue("local_phone_location_time", System.currentTimeMillis() / 1000);
    }

    protected void f() {
        this.g.stop();
        if (this.g instanceof BDLocationManger) {
            SharePreferenceUtils.setSharePreferencesValue(PrefrenceKeys.PRE_KEY_LOCATION_MANAGER, "gaode");
        } else {
            SharePreferenceUtils.setSharePreferencesValue(PrefrenceKeys.PRE_KEY_LOCATION_MANAGER, "baidu");
        }
        this.g = j();
    }

    private ILocationManager j() {
        if ("gaode".equals(SharePreferenceUtils.getSharePreferencesValue(PrefrenceKeys.PRE_KEY_LOCATION_MANAGER))) {
            LogUtil.d("use gaode location");
            a("use_gd_location");
            return GDLocationManager.instance();
        }
        LogUtil.d("use baidu location");
        a("use_bd_location");
        return BDLocationManger.instance();
    }

    public void onActivityResult(int i, int i2, Intent intent) {
        if (i == 101) {
            if (!NearbyEngine.instance().isLocationServiceEnabled(this.k)) {
                ToastAndDialog.makeNegativeToast(QsbkApp.mContext, "请打开定位服务来使用附近的人的功能", Integer.valueOf(0)).show();
            }
            if (this.i != null) {
                this.i.onHideServiceDisableWarn();
            }
            g();
        }
    }

    public void onLocation(int i, double d, double d2, String str, String str2) {
        LogUtil.d("location type:" + i);
        LogUtil.d("location latitude:" + d);
        LogUtil.d("location longtitude:" + d2);
        if (i == 0 && d == 0.0d && d2 == 0.0d) {
            i = -1;
        }
        if (i == 161 || i == 0) {
            a(d, d2, str, str2);
        }
        if (i == 61 || i == 65 || i == 66 || i == 68 || i == 161 || i == 0) {
            a = System.currentTimeMillis();
            b = d;
            c = d2;
            d = str;
            e = str2;
            LocationCache.getInstance().setLocation(new Location(0, d, d2, str, str2));
            if (this.h != null) {
                this.h.onLocateDone(d, d2, str, str2);
            }
            this.h = null;
            this.g.stop();
            this.g = null;
            return;
        }
        this.f++;
        f();
        if (this.f >= 2) {
            this.f = 0;
            if (this.h != null) {
                double[] b = b();
                if (b != null) {
                    b = b[0];
                    c = b[1];
                    d = c();
                    e = d();
                    this.h.onLocateDone(b[0], b[1], d, e);
                } else {
                    LogUtil.d("get location fail report fail");
                    this.h.onLocateFail(1);
                    a("fail");
                }
            }
            this.h = null;
            this.g.stop();
            this.g = null;
            return;
        }
        LogUtil.d("get location fail retry");
        i();
    }

    public boolean isLoaded() {
        return a != 0;
    }
}
