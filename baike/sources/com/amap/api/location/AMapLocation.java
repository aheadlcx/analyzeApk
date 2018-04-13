package com.amap.api.location;

import android.location.Location;
import android.os.Parcel;
import android.os.Parcelable.Creator;
import android.text.TextUtils;
import com.loc.cw;
import com.loc.de;
import com.sina.weibo.sdk.constant.WBPageConstants.ParamKey;
import com.umeng.analytics.pro.b;
import com.umeng.commonsdk.proguard.g;
import org.eclipse.paho.client.mqttv3.MqttTopic;
import org.json.JSONObject;

public class AMapLocation extends Location {
    public static final Creator<AMapLocation> CREATOR = new a();
    public static final int ERROR_CODE_FAILURE_AUTH = 7;
    public static final int ERROR_CODE_FAILURE_CELL = 11;
    public static final int ERROR_CODE_FAILURE_CONNECTION = 4;
    public static final int ERROR_CODE_FAILURE_INIT = 9;
    public static final int ERROR_CODE_FAILURE_LOCATION = 6;
    public static final int ERROR_CODE_FAILURE_LOCATION_PARAMETER = 3;
    public static final int ERROR_CODE_FAILURE_LOCATION_PERMISSION = 12;
    public static final int ERROR_CODE_FAILURE_NOENOUGHSATELLITES = 14;
    public static final int ERROR_CODE_FAILURE_NOWIFIANDAP = 13;
    public static final int ERROR_CODE_FAILURE_PARSER = 5;
    public static final int ERROR_CODE_FAILURE_SIMULATION_LOCATION = 15;
    public static final int ERROR_CODE_FAILURE_WIFI_INFO = 2;
    public static final int ERROR_CODE_INVALID_PARAMETER = 1;
    public static final int ERROR_CODE_SERVICE_FAIL = 10;
    public static final int ERROR_CODE_UNKNOWN = 8;
    public static final int GPS_ACCURACY_BAD = 0;
    public static final int GPS_ACCURACY_GOOD = 1;
    public static final int GPS_ACCURACY_UNKNOWN = -1;
    public static final int LOCATION_SUCCESS = 0;
    public static final int LOCATION_TYPE_AMAP = 7;
    public static final int LOCATION_TYPE_CELL = 6;
    public static final int LOCATION_TYPE_FAST = 3;
    public static final int LOCATION_TYPE_FIX_CACHE = 4;
    public static final int LOCATION_TYPE_GPS = 1;
    public static final int LOCATION_TYPE_OFFLINE = 8;
    public static final int LOCATION_TYPE_SAME_REQ = 2;
    public static final int LOCATION_TYPE_WIFI = 5;
    protected String a = "";
    protected String b = "";
    private String c = "";
    private String d = "";
    private String e = "";
    private String f = "";
    private String g = "";
    private String h = "";
    private String i = "";
    private String j = "";
    private String k = "";
    private String l = "";
    private String m = "";
    private boolean n = true;
    private int o = 0;
    private String p = "success";
    private String q = "";
    private int r = 0;
    private double s = 0.0d;
    private double t = 0.0d;
    private int u = 0;
    private String v = "";
    private int w = -1;
    private boolean x = false;
    private String y = "";
    private boolean z = false;

    public AMapLocation(Location location) {
        super(location);
        this.s = location.getLatitude();
        this.t = location.getLongitude();
    }

    public AMapLocation(String str) {
        super(str);
    }

    public int describeContents() {
        return 0;
    }

    public float getAccuracy() {
        return super.getAccuracy();
    }

    public String getAdCode() {
        return this.g;
    }

    public String getAddress() {
        return this.h;
    }

    public double getAltitude() {
        return super.getAltitude();
    }

    public String getAoiName() {
        return this.v;
    }

    public float getBearing() {
        return super.getBearing();
    }

    public String getBuildingId() {
        return this.a;
    }

    public String getCity() {
        return this.d;
    }

    public String getCityCode() {
        return this.f;
    }

    public String getCountry() {
        return this.j;
    }

    public String getDescription() {
        return this.y;
    }

    public String getDistrict() {
        return this.e;
    }

    public int getErrorCode() {
        return this.o;
    }

    public String getErrorInfo() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(this.p);
        if (this.o != 0) {
            stringBuilder.append(" 请到http://lbs.amap.com/api/android-location-sdk/guide/utilities/errorcode/查看错误码说明");
            stringBuilder.append(",错误详细信息:" + this.q);
        }
        this.p = stringBuilder.toString();
        return this.p;
    }

    public String getFloor() {
        return this.b;
    }

    public int getGpsAccuracyStatus() {
        return this.w;
    }

    public double getLatitude() {
        return this.s;
    }

    public String getLocationDetail() {
        return this.q;
    }

    public int getLocationType() {
        return this.r;
    }

    public double getLongitude() {
        return this.t;
    }

    public String getPoiName() {
        return this.i;
    }

    public String getProvider() {
        return super.getProvider();
    }

    public String getProvince() {
        return this.c;
    }

    public String getRoad() {
        return this.k;
    }

    public int getSatellites() {
        return this.u;
    }

    public float getSpeed() {
        return super.getSpeed();
    }

    public String getStreet() {
        return this.l;
    }

    public String getStreetNum() {
        return this.m;
    }

    public boolean isFixLastLocation() {
        return this.z;
    }

    public boolean isMock() {
        return this.x;
    }

    public boolean isOffset() {
        return this.n;
    }

    public void setAdCode(String str) {
        this.g = str;
    }

    public void setAddress(String str) {
        this.h = str;
    }

    public void setAoiName(String str) {
        this.v = str;
    }

    public void setBuildingId(String str) {
        this.a = str;
    }

    public void setCity(String str) {
        this.d = str;
    }

    public void setCityCode(String str) {
        this.f = str;
    }

    public void setCountry(String str) {
        this.j = str;
    }

    public void setDescription(String str) {
        this.y = str;
    }

    public void setDistrict(String str) {
        this.e = str;
    }

    public void setErrorCode(int i) {
        if (this.o == 0) {
            this.p = de.b(i);
            this.o = i;
        }
    }

    public void setErrorInfo(String str) {
        this.p = str;
    }

    public void setFixLastLocation(boolean z) {
        this.z = z;
    }

    public void setFloor(String str) {
        if (!TextUtils.isEmpty(str)) {
            str = str.replace("F", "");
            try {
                Integer.parseInt(str);
            } catch (Throwable th) {
                str = null;
                cw.a(th, "AmapLoc", "setFloor");
            }
        }
        this.b = str;
    }

    public void setGpsAccuracyStatus(int i) {
        this.w = i;
    }

    public void setLatitude(double d) {
        this.s = d;
    }

    public void setLocationDetail(String str) {
        this.q = str;
    }

    public void setLocationType(int i) {
        this.r = i;
    }

    public void setLongitude(double d) {
        this.t = d;
    }

    public void setMock(boolean z) {
        this.x = z;
    }

    public void setNumber(String str) {
        this.m = str;
    }

    public void setOffset(boolean z) {
        this.n = z;
    }

    public void setPoiName(String str) {
        this.i = str;
    }

    public void setProvince(String str) {
        this.c = str;
    }

    public void setRoad(String str) {
        this.k = str;
    }

    public void setSatellites(int i) {
        this.u = i;
    }

    public void setStreet(String str) {
        this.l = str;
    }

    public JSONObject toJson(int i) {
        try {
            JSONObject jSONObject = new JSONObject();
            switch (i) {
                case 1:
                    try {
                        jSONObject.put("altitude", getAltitude());
                        jSONObject.put("speed", (double) getSpeed());
                        jSONObject.put("bearing", (double) getBearing());
                    } catch (Throwable th) {
                    }
                    jSONObject.put("citycode", this.f);
                    jSONObject.put("adcode", this.g);
                    jSONObject.put(g.N, this.j);
                    jSONObject.put("province", this.c);
                    jSONObject.put("city", this.d);
                    jSONObject.put("district", this.e);
                    jSONObject.put("road", this.k);
                    jSONObject.put("street", this.l);
                    jSONObject.put("number", this.m);
                    jSONObject.put(ParamKey.POINAME, this.i);
                    jSONObject.put("errorCode", this.o);
                    jSONObject.put("errorInfo", this.p);
                    jSONObject.put("locationType", this.r);
                    jSONObject.put("locationDetail", this.q);
                    jSONObject.put("aoiname", this.v);
                    jSONObject.put("address", this.h);
                    jSONObject.put(ParamKey.POIID, this.a);
                    jSONObject.put("floor", this.b);
                    jSONObject.put("description", this.y);
                    break;
                case 2:
                    break;
                case 3:
                    break;
                default:
                    return jSONObject;
            }
            jSONObject.put("time", getTime());
            jSONObject.put(b.H, getProvider());
            jSONObject.put("lon", getLongitude());
            jSONObject.put("lat", getLatitude());
            jSONObject.put("accuracy", (double) getAccuracy());
            jSONObject.put("isOffset", this.n);
            jSONObject.put("isFixLastLocation", this.z);
            return jSONObject;
        } catch (Throwable th2) {
            cw.a(th2, "AmapLoc", "toStr");
            return null;
        }
    }

    public String toStr() {
        return toStr(1);
    }

    public String toStr(int i) {
        JSONObject toJson;
        try {
            toJson = toJson(i);
        } catch (Throwable th) {
            cw.a(th, "AMapLocation", "toStr part2");
            toJson = null;
        }
        return toJson == null ? null : toJson.toString();
    }

    public String toString() {
        StringBuffer stringBuffer = new StringBuffer();
        try {
            stringBuffer.append("latitude=" + this.s + MqttTopic.MULTI_LEVEL_WILDCARD);
            stringBuffer.append("longitude=" + this.t + MqttTopic.MULTI_LEVEL_WILDCARD);
            stringBuffer.append("province=" + this.c + MqttTopic.MULTI_LEVEL_WILDCARD);
            stringBuffer.append("city=" + this.d + MqttTopic.MULTI_LEVEL_WILDCARD);
            stringBuffer.append("district=" + this.e + MqttTopic.MULTI_LEVEL_WILDCARD);
            stringBuffer.append("cityCode=" + this.f + MqttTopic.MULTI_LEVEL_WILDCARD);
            stringBuffer.append("adCode=" + this.g + MqttTopic.MULTI_LEVEL_WILDCARD);
            stringBuffer.append("address=" + this.h + MqttTopic.MULTI_LEVEL_WILDCARD);
            stringBuffer.append("country=" + this.j + MqttTopic.MULTI_LEVEL_WILDCARD);
            stringBuffer.append("road=" + this.k + MqttTopic.MULTI_LEVEL_WILDCARD);
            stringBuffer.append("poiName=" + this.i + MqttTopic.MULTI_LEVEL_WILDCARD);
            stringBuffer.append("street=" + this.l + MqttTopic.MULTI_LEVEL_WILDCARD);
            stringBuffer.append("streetNum=" + this.m + MqttTopic.MULTI_LEVEL_WILDCARD);
            stringBuffer.append("aoiName=" + this.v + MqttTopic.MULTI_LEVEL_WILDCARD);
            stringBuffer.append("poiid=" + this.a + MqttTopic.MULTI_LEVEL_WILDCARD);
            stringBuffer.append("floor=" + this.b + MqttTopic.MULTI_LEVEL_WILDCARD);
            stringBuffer.append("errorCode=" + this.o + MqttTopic.MULTI_LEVEL_WILDCARD);
            stringBuffer.append("errorInfo=" + this.p + MqttTopic.MULTI_LEVEL_WILDCARD);
            stringBuffer.append("locationDetail=" + this.q + MqttTopic.MULTI_LEVEL_WILDCARD);
            stringBuffer.append("description=" + this.y + MqttTopic.MULTI_LEVEL_WILDCARD);
            stringBuffer.append("locationType=" + this.r);
        } catch (Throwable th) {
        }
        return stringBuffer.toString();
    }

    public void writeToParcel(Parcel parcel, int i) {
        int i2 = 1;
        super.writeToParcel(parcel, i);
        parcel.writeString(this.g);
        parcel.writeString(this.h);
        parcel.writeString(this.v);
        parcel.writeString(this.a);
        parcel.writeString(this.d);
        parcel.writeString(this.f);
        parcel.writeString(this.j);
        parcel.writeString(this.e);
        parcel.writeInt(this.o);
        parcel.writeString(this.p);
        parcel.writeString(this.b);
        parcel.writeInt(this.z ? 1 : 0);
        parcel.writeInt(this.n ? 1 : 0);
        parcel.writeDouble(this.s);
        parcel.writeString(this.q);
        parcel.writeInt(this.r);
        parcel.writeDouble(this.t);
        if (!this.x) {
            i2 = 0;
        }
        parcel.writeInt(i2);
        parcel.writeString(this.m);
        parcel.writeString(this.i);
        parcel.writeString(this.c);
        parcel.writeString(this.k);
        parcel.writeInt(this.u);
        parcel.writeInt(this.w);
        parcel.writeString(this.l);
        parcel.writeString(this.y);
    }
}
