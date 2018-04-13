package com.qq.e.comm.managers.status;

import android.content.Context;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Build;
import android.os.Build.VERSION;
import android.os.Bundle;
import android.provider.Settings.Secure;
import android.telephony.TelephonyManager;
import android.telephony.cdma.CdmaCellLocation;
import android.telephony.gsm.GsmCellLocation;
import android.util.DisplayMetrics;
import cn.v6.sixrooms.constants.CommonStrs;
import com.baidu.mobads.interfaces.IXAdRequestInfo;
import com.qq.e.comm.util.GDTLogger;
import com.qq.e.comm.util.Md5Util;
import com.qq.e.comm.util.StringUtil;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

public class DeviceStatus {
    private String a;
    private String b;
    private int c;
    private int d;
    private int e;
    private String f;
    private String g;
    private String h;
    private String i;
    private String j;
    private volatile String k;
    private volatile String l;
    private volatile float m;
    public final String model = Build.MODEL;
    private Context n;

    public DeviceStatus(Context context) {
        this.n = context.getApplicationContext();
        DisplayMetrics displayMetrics = context.getResources().getDisplayMetrics();
        this.e = getVersion() > 3 ? displayMetrics.densityDpi : 120;
        this.c = getVersion() > 3 ? a(displayMetrics.density, displayMetrics.widthPixels) : displayMetrics.widthPixels;
        this.d = getVersion() > 3 ? a(displayMetrics.density, displayMetrics.heightPixels) : displayMetrics.heightPixels;
        a();
    }

    private int a(float f, int i) {
        return (this.n.getApplicationInfo().flags & 8192) != 0 ? (int) (((float) i) / f) : i;
    }

    private void a() {
        try {
            final LocationManager locationManager = (LocationManager) this.n.getSystemService(CommonStrs.TYPE_LOCATION);
            if (locationManager != null) {
                Criteria criteria = new Criteria();
                criteria.setAccuracy(2);
                criteria.setAltitudeRequired(false);
                criteria.setBearingRequired(false);
                criteria.setCostAllowed(true);
                criteria.setPowerRequirement(1);
                String bestProvider = locationManager.getBestProvider(criteria, true);
                Location lastKnownLocation = locationManager.getLastKnownLocation(bestProvider);
                if (lastKnownLocation != null) {
                    this.k = lastKnownLocation.getLatitude();
                    this.l = lastKnownLocation.getLongitude();
                    this.m = lastKnownLocation.getAccuracy();
                    return;
                }
                try {
                    locationManager.requestLocationUpdates(bestProvider, 2000, 7000.0f, new LocationListener(this) {
                        private /* synthetic */ DeviceStatus b;

                        public void onLocationChanged(Location location) {
                            try {
                                this.b.k = location.getLatitude();
                                this.b.l = location.getLongitude();
                                locationManager.removeUpdates(this);
                            } catch (Throwable th) {
                            }
                        }

                        public void onProviderDisabled(String str) {
                        }

                        public void onProviderEnabled(String str) {
                        }

                        public void onStatusChanged(String str, int i, Bundle bundle) {
                        }
                    });
                } catch (Throwable th) {
                }
            }
        } catch (Throwable th2) {
        }
    }

    public Carrier getCarrier() {
        String operator = getOperator();
        if (operator != null) {
            if (operator.equals("46000") || operator.equals("46002") || operator.equals("46007") || operator.equals("46020")) {
                return Carrier.CMCC;
            }
            if (operator.equals("46001") || operator.equals("46006")) {
                return Carrier.UNICOM;
            }
            if (operator.equals("46003") || operator.equals("46005")) {
                return Carrier.TELECOM;
            }
        }
        return Carrier.UNKNOWN;
    }

    public String getDataNet() {
        NetworkInfo activeNetworkInfo;
        try {
            activeNetworkInfo = ((ConnectivityManager) this.n.getSystemService("connectivity")).getActiveNetworkInfo();
        } catch (Exception e) {
            activeNetworkInfo = null;
        }
        if (activeNetworkInfo == null) {
            return null;
        }
        String str;
        switch (activeNetworkInfo.getType()) {
            case 0:
                str = "ed";
                break;
            case 1:
                str = IXAdRequestInfo.WIFI;
                break;
            default:
                str = "unknow";
                break;
        }
        this.i = str;
        return this.i;
    }

    public int getDeviceDensity() {
        return this.e;
    }

    public int getDeviceHeight() {
        return this.d;
    }

    public int getDeviceWidth() {
        return this.c;
    }

    public String getDid() {
        String plainDid = getPlainDid();
        return StringUtil.isEmpty(plainDid) ? "" : Md5Util.encode(plainDid.toLowerCase());
    }

    public Map<String, String> getLacAndCeilId() {
        int i = 0;
        String operator = getOperator();
        Map<String, String> hashMap = new HashMap();
        if (!(StringUtil.isEmpty(operator) || "null".equalsIgnoreCase(operator))) {
            try {
                int parseInt = Integer.parseInt(operator.substring(0, 3));
                int parseInt2 = Integer.parseInt(operator.substring(3));
                if (parseInt == 460) {
                    int baseStationId;
                    TelephonyManager telephonyManager = (TelephonyManager) this.n.getSystemService("phone");
                    if (parseInt2 == 3 || parseInt2 == 5) {
                        CdmaCellLocation cdmaCellLocation = (CdmaCellLocation) telephonyManager.getCellLocation();
                        i = cdmaCellLocation.getNetworkId();
                        baseStationId = cdmaCellLocation.getBaseStationId();
                    } else {
                        GsmCellLocation gsmCellLocation = (GsmCellLocation) telephonyManager.getCellLocation();
                        if (gsmCellLocation != null) {
                            i = gsmCellLocation.getLac();
                            baseStationId = gsmCellLocation.getCid();
                        } else {
                            baseStationId = 0;
                        }
                    }
                    hashMap.put("lac", i);
                    hashMap.put("cellid", baseStationId);
                }
            } catch (Throwable th) {
                th.printStackTrace();
            }
        }
        return hashMap;
    }

    public String getLanguage() {
        if (this.b == null) {
            this.b = Locale.getDefault().getLanguage().toLowerCase(Locale.US);
            if (this.b.length() == 0) {
                this.b = "en";
            }
        }
        return this.b;
    }

    public String getLat() {
        return this.k;
    }

    public String getLng() {
        return this.l;
    }

    public float getLocationAccuracy() {
        return this.m;
    }

    public NetworkType getNetworkType() {
        int parseInt;
        String dataNet = getDataNet();
        try {
            parseInt = Integer.parseInt(getPhoneNet());
        } catch (NumberFormatException e) {
            parseInt = 0;
        }
        if (dataNet != null && dataNet.equals(IXAdRequestInfo.WIFI)) {
            return NetworkType.WIFI;
        }
        switch (parseInt) {
            case 1:
            case 2:
                return NetworkType.NET_2G;
            case 3:
            case 4:
            case 5:
            case 6:
            case 7:
            case 8:
            case 9:
            case 10:
            case 11:
            case 12:
            case 14:
                return NetworkType.NET_3G;
            case 13:
            case 15:
                return NetworkType.NET_4G;
            default:
                return NetworkType.UNKNOWN;
        }
    }

    public String getOperator() {
        try {
            this.g = ((TelephonyManager) this.n.getSystemService("phone")).getNetworkOperator();
        } catch (Exception e) {
        }
        return this.g;
    }

    public String getPhoneNet() {
        try {
            this.h = ((TelephonyManager) this.n.getSystemService("phone")).getNetworkType();
        } catch (Exception e) {
        }
        return this.h;
    }

    public String getPlainDid() {
        if (!StringUtil.isEmpty(this.j)) {
            return this.j;
        }
        try {
            this.j = ((TelephonyManager) this.n.getSystemService("phone")).getDeviceId();
        } catch (Exception e) {
            GDTLogger.d("Get imei encounter error: " + e.getMessage());
        }
        return StringUtil.isEmpty(this.j) ? "" : this.j;
    }

    public String getScreenOrientation() {
        if (this.n.getResources().getConfiguration().orientation == 2) {
            this.f = "l";
        } else if (this.n.getResources().getConfiguration().orientation == 1) {
            this.f = "p";
        }
        return this.f;
    }

    public String getUid() {
        if (this.a == null) {
            String string = Secure.getString(this.n.getContentResolver(), "android_id");
            this.a = string == null ? Md5Util.encode("emulator") : Md5Util.encode(string);
        }
        return this.a;
    }

    public int getVersion() {
        try {
            return VERSION.SDK_INT;
        } catch (Exception e) {
            return 3;
        }
    }
}
