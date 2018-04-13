package com.baidu.location;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import android.text.TextUtils;
import com.alipay.sdk.util.j;
import com.baidu.location.Address.Builder;
import com.baidu.mobstat.Config;
import com.umeng.analytics.pro.b;
import com.xiaomi.mipush.sdk.Constants;
import java.util.ArrayList;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONObject;

public final class BDLocation implements Parcelable {
    public static final String BDLOCATION_BD09LL_TO_GCJ02 = "bd09ll2gcj";
    public static final String BDLOCATION_BD09_TO_GCJ02 = "bd092gcj";
    public static final String BDLOCATION_GCJ02_TO_BD09 = "bd09";
    public static final String BDLOCATION_GCJ02_TO_BD09LL = "bd09ll";
    public static final String BDLOCATION_WGS84_TO_GCJ02 = "gps2gcj";
    public static final Creator<BDLocation> CREATOR = new a();
    public static final int GPS_ACCURACY_BAD = 3;
    public static final int GPS_ACCURACY_GOOD = 1;
    public static final int GPS_ACCURACY_MID = 2;
    public static final int GPS_ACCURACY_UNKNOWN = 0;
    public static final int INDOOR_LOCATION_NEARBY_SURPPORT_TRUE = 2;
    public static final int INDOOR_LOCATION_SOURCE_BLUETOOTH = 4;
    public static final int INDOOR_LOCATION_SOURCE_MAGNETIC = 2;
    public static final int INDOOR_LOCATION_SOURCE_SMALLCELLSTATION = 8;
    public static final int INDOOR_LOCATION_SOURCE_UNKNOWN = 0;
    public static final int INDOOR_LOCATION_SOURCE_WIFI = 1;
    public static final int INDOOR_LOCATION_SURPPORT_FALSE = 0;
    public static final int INDOOR_LOCATION_SURPPORT_TRUE = 1;
    public static final int INDOOR_NETWORK_STATE_HIGH = 2;
    public static final int INDOOR_NETWORK_STATE_LOW = 0;
    public static final int INDOOR_NETWORK_STATE_MIDDLE = 1;
    public static final int LOCATION_WHERE_IN_CN = 1;
    public static final int LOCATION_WHERE_OUT_CN = 0;
    public static final int LOCATION_WHERE_UNKNOW = 2;
    public static final int OPERATORS_TYPE_MOBILE = 1;
    public static final int OPERATORS_TYPE_TELECOMU = 3;
    public static final int OPERATORS_TYPE_UNICOM = 2;
    public static final int OPERATORS_TYPE_UNKONW = 0;
    public static final int TypeCacheLocation = 65;
    public static final int TypeCriteriaException = 62;
    public static final int TypeGpsLocation = 61;
    public static final int TypeNetWorkException = 63;
    public static final int TypeNetWorkLocation = 161;
    public static final int TypeNone = 0;
    public static final int TypeOffLineLocation = 66;
    public static final int TypeOffLineLocationFail = 67;
    public static final int TypeOffLineLocationNetworkFail = 68;
    public static final int TypeServerCheckKeyError = 505;
    public static final int TypeServerDecryptError = 162;
    public static final int TypeServerError = 167;
    public static final int USER_INDDOR_TRUE = 1;
    public static final int USER_INDOOR_FALSE = 0;
    public static final int USER_INDOOR_UNKNOW = -1;
    private String buildingid;
    private String floor;
    private boolean indoorLocMode;
    private boolean isCellChangeFlag;
    private Address mAddr;
    private String mAddrStr;
    private double mAltitude;
    private String mBuildingName;
    private String mCoorType;
    private String mCu;
    private float mDerect;
    private String mDescription;
    private int mGpsAccuracyStatus;
    private boolean mHasAddr;
    private boolean mHasAltitude;
    private boolean mHasRadius;
    private boolean mHasSateNumber;
    private boolean mHasSpeed;
    private int mIndoorLocationSurpport;
    private int mIndoorNetworkState;
    private int mIndoorSource;
    private int mIndoorState;
    private String mIndoorSurpportBuildingID;
    private String mIndoorSurpportBuildingName;
    private double mLatitude;
    private int mLocType;
    private int mLocationWhere;
    private double mLongitude;
    private int mOperators;
    private int mParkState;
    private List<Poi> mPoiList;
    private float mRadius;
    private int mSatelliteNumber;
    private String mSemaAptag;
    private String mSemaPoiRegion;
    private String mSemaRegular;
    private float mSpeed;
    private String mTime;
    private String netWorkLocationType;

    public BDLocation() {
        this.mLocType = 0;
        this.mTime = null;
        this.mLatitude = Double.MIN_VALUE;
        this.mLongitude = Double.MIN_VALUE;
        this.mHasAltitude = false;
        this.mAltitude = Double.MIN_VALUE;
        this.mHasSpeed = false;
        this.mSpeed = 0.0f;
        this.mHasRadius = false;
        this.mRadius = 0.0f;
        this.mHasSateNumber = false;
        this.mSatelliteNumber = -1;
        this.mDerect = -1.0f;
        this.mCoorType = null;
        this.mHasAddr = false;
        this.mAddrStr = null;
        this.mSemaAptag = null;
        this.mSemaPoiRegion = null;
        this.mSemaRegular = null;
        this.isCellChangeFlag = false;
        this.mAddr = new Builder().build();
        this.floor = null;
        this.buildingid = null;
        this.mBuildingName = null;
        this.indoorLocMode = false;
        this.mParkState = 0;
        this.mLocationWhere = 1;
        this.netWorkLocationType = null;
        this.mCu = "";
        this.mIndoorState = -1;
        this.mIndoorLocationSurpport = 0;
        this.mIndoorNetworkState = 2;
        this.mIndoorSource = 0;
        this.mIndoorSurpportBuildingName = null;
        this.mIndoorSurpportBuildingID = null;
        this.mPoiList = null;
        this.mDescription = null;
        this.mGpsAccuracyStatus = 0;
    }

    private BDLocation(Parcel parcel) {
        this.mLocType = 0;
        this.mTime = null;
        this.mLatitude = Double.MIN_VALUE;
        this.mLongitude = Double.MIN_VALUE;
        this.mHasAltitude = false;
        this.mAltitude = Double.MIN_VALUE;
        this.mHasSpeed = false;
        this.mSpeed = 0.0f;
        this.mHasRadius = false;
        this.mRadius = 0.0f;
        this.mHasSateNumber = false;
        this.mSatelliteNumber = -1;
        this.mDerect = -1.0f;
        this.mCoorType = null;
        this.mHasAddr = false;
        this.mAddrStr = null;
        this.mSemaAptag = null;
        this.mSemaPoiRegion = null;
        this.mSemaRegular = null;
        this.isCellChangeFlag = false;
        this.mAddr = new Builder().build();
        this.floor = null;
        this.buildingid = null;
        this.mBuildingName = null;
        this.indoorLocMode = false;
        this.mParkState = 0;
        this.mLocationWhere = 1;
        this.netWorkLocationType = null;
        this.mCu = "";
        this.mIndoorState = -1;
        this.mIndoorLocationSurpport = 0;
        this.mIndoorNetworkState = 2;
        this.mIndoorSource = 0;
        this.mIndoorSurpportBuildingName = null;
        this.mIndoorSurpportBuildingID = null;
        this.mPoiList = null;
        this.mDescription = null;
        this.mGpsAccuracyStatus = 0;
        this.mLocType = parcel.readInt();
        this.mTime = parcel.readString();
        this.mLatitude = parcel.readDouble();
        this.mLongitude = parcel.readDouble();
        this.mAltitude = parcel.readDouble();
        this.mSpeed = parcel.readFloat();
        this.mRadius = parcel.readFloat();
        this.mSatelliteNumber = parcel.readInt();
        this.mDerect = parcel.readFloat();
        this.floor = parcel.readString();
        this.mParkState = parcel.readInt();
        this.buildingid = parcel.readString();
        this.mBuildingName = parcel.readString();
        this.netWorkLocationType = parcel.readString();
        String readString = parcel.readString();
        String readString2 = parcel.readString();
        String readString3 = parcel.readString();
        String readString4 = parcel.readString();
        String readString5 = parcel.readString();
        String readString6 = parcel.readString();
        parcel.readString();
        String readString7 = parcel.readString();
        this.mAddr = new Builder().country(readString7).countryCode(parcel.readString()).province(readString).city(readString2).cityCode(readString6).district(readString3).street(readString4).streetNumber(readString5).build();
        boolean[] zArr = new boolean[7];
        this.mOperators = parcel.readInt();
        this.mCu = parcel.readString();
        this.mSemaAptag = parcel.readString();
        this.mSemaPoiRegion = parcel.readString();
        this.mSemaRegular = parcel.readString();
        this.mLocationWhere = parcel.readInt();
        this.mDescription = parcel.readString();
        this.mIndoorState = parcel.readInt();
        this.mIndoorLocationSurpport = parcel.readInt();
        this.mIndoorNetworkState = parcel.readInt();
        this.mIndoorSource = parcel.readInt();
        this.mIndoorSurpportBuildingName = parcel.readString();
        this.mIndoorSurpportBuildingID = parcel.readString();
        this.mGpsAccuracyStatus = parcel.readInt();
        try {
            parcel.readBooleanArray(zArr);
            this.mHasAltitude = zArr[0];
            this.mHasSpeed = zArr[1];
            this.mHasRadius = zArr[2];
            this.mHasSateNumber = zArr[3];
            this.mHasAddr = zArr[4];
            this.isCellChangeFlag = zArr[5];
            this.indoorLocMode = zArr[6];
        } catch (Exception e) {
        }
        List arrayList = new ArrayList();
        parcel.readList(arrayList, Poi.class.getClassLoader());
        if (arrayList.size() == 0) {
            this.mPoiList = null;
        } else {
            this.mPoiList = arrayList;
        }
    }

    public BDLocation(BDLocation bDLocation) {
        this.mLocType = 0;
        this.mTime = null;
        this.mLatitude = Double.MIN_VALUE;
        this.mLongitude = Double.MIN_VALUE;
        this.mHasAltitude = false;
        this.mAltitude = Double.MIN_VALUE;
        this.mHasSpeed = false;
        this.mSpeed = 0.0f;
        this.mHasRadius = false;
        this.mRadius = 0.0f;
        this.mHasSateNumber = false;
        this.mSatelliteNumber = -1;
        this.mDerect = -1.0f;
        this.mCoorType = null;
        this.mHasAddr = false;
        this.mAddrStr = null;
        this.mSemaAptag = null;
        this.mSemaPoiRegion = null;
        this.mSemaRegular = null;
        this.isCellChangeFlag = false;
        this.mAddr = new Builder().build();
        this.floor = null;
        this.buildingid = null;
        this.mBuildingName = null;
        this.indoorLocMode = false;
        this.mParkState = 0;
        this.mLocationWhere = 1;
        this.netWorkLocationType = null;
        this.mCu = "";
        this.mIndoorState = -1;
        this.mIndoorLocationSurpport = 0;
        this.mIndoorNetworkState = 2;
        this.mIndoorSource = 0;
        this.mIndoorSurpportBuildingName = null;
        this.mIndoorSurpportBuildingID = null;
        this.mPoiList = null;
        this.mDescription = null;
        this.mGpsAccuracyStatus = 0;
        this.mLocType = bDLocation.mLocType;
        this.mTime = bDLocation.mTime;
        this.mLatitude = bDLocation.mLatitude;
        this.mLongitude = bDLocation.mLongitude;
        this.mHasAltitude = bDLocation.mHasAltitude;
        this.mAltitude = bDLocation.mAltitude;
        this.mHasSpeed = bDLocation.mHasSpeed;
        this.mSpeed = bDLocation.mSpeed;
        this.mHasRadius = bDLocation.mHasRadius;
        this.mRadius = bDLocation.mRadius;
        this.mHasSateNumber = bDLocation.mHasSateNumber;
        this.mSatelliteNumber = bDLocation.mSatelliteNumber;
        this.mDerect = bDLocation.mDerect;
        this.mCoorType = bDLocation.mCoorType;
        this.mHasAddr = bDLocation.mHasAddr;
        this.mAddrStr = bDLocation.mAddrStr;
        this.isCellChangeFlag = bDLocation.isCellChangeFlag;
        this.mAddr = new Builder().country(bDLocation.mAddr.country).countryCode(bDLocation.mAddr.countryCode).province(bDLocation.mAddr.province).city(bDLocation.mAddr.city).cityCode(bDLocation.mAddr.cityCode).district(bDLocation.mAddr.district).street(bDLocation.mAddr.street).streetNumber(bDLocation.mAddr.streetNumber).build();
        this.floor = bDLocation.floor;
        this.buildingid = bDLocation.buildingid;
        this.mBuildingName = bDLocation.mBuildingName;
        this.mLocationWhere = bDLocation.mLocationWhere;
        this.mParkState = bDLocation.mParkState;
        this.indoorLocMode = bDLocation.indoorLocMode;
        this.netWorkLocationType = bDLocation.netWorkLocationType;
        this.mOperators = bDLocation.mOperators;
        this.mCu = bDLocation.mCu;
        this.mSemaAptag = bDLocation.mSemaAptag;
        this.mSemaPoiRegion = bDLocation.mSemaPoiRegion;
        this.mSemaRegular = bDLocation.mSemaRegular;
        this.mIndoorState = bDLocation.mIndoorState;
        this.mIndoorLocationSurpport = bDLocation.mIndoorLocationSurpport;
        this.mIndoorNetworkState = bDLocation.mIndoorLocationSurpport;
        this.mIndoorSource = bDLocation.mIndoorSource;
        this.mIndoorSurpportBuildingName = bDLocation.mIndoorSurpportBuildingName;
        this.mIndoorSurpportBuildingID = bDLocation.mIndoorSurpportBuildingID;
        this.mGpsAccuracyStatus = bDLocation.mGpsAccuracyStatus;
        if (bDLocation.mPoiList == null) {
            this.mPoiList = null;
        } else {
            List arrayList = new ArrayList();
            for (int i = 0; i < bDLocation.mPoiList.size(); i++) {
                Poi poi = (Poi) bDLocation.mPoiList.get(i);
                arrayList.add(new Poi(poi.getId(), poi.getName(), poi.getRank()));
            }
            this.mPoiList = arrayList;
        }
        this.mDescription = bDLocation.mDescription;
    }

    public BDLocation(String str) {
        String str2 = null;
        this.mLocType = 0;
        this.mTime = null;
        this.mLatitude = Double.MIN_VALUE;
        this.mLongitude = Double.MIN_VALUE;
        this.mHasAltitude = false;
        this.mAltitude = Double.MIN_VALUE;
        this.mHasSpeed = false;
        this.mSpeed = 0.0f;
        this.mHasRadius = false;
        this.mRadius = 0.0f;
        this.mHasSateNumber = false;
        this.mSatelliteNumber = -1;
        this.mDerect = -1.0f;
        this.mCoorType = null;
        this.mHasAddr = false;
        this.mAddrStr = null;
        this.mSemaAptag = null;
        this.mSemaPoiRegion = null;
        this.mSemaRegular = null;
        this.isCellChangeFlag = false;
        this.mAddr = new Builder().build();
        this.floor = null;
        this.buildingid = null;
        this.mBuildingName = null;
        this.indoorLocMode = false;
        this.mParkState = 0;
        this.mLocationWhere = 1;
        this.netWorkLocationType = null;
        this.mCu = "";
        this.mIndoorState = -1;
        this.mIndoorLocationSurpport = 0;
        this.mIndoorNetworkState = 2;
        this.mIndoorSource = 0;
        this.mIndoorSurpportBuildingName = null;
        this.mIndoorSurpportBuildingID = null;
        this.mPoiList = null;
        this.mDescription = null;
        this.mGpsAccuracyStatus = 0;
        if (str != null && !str.equals("")) {
            try {
                JSONObject jSONObject = new JSONObject(str);
                JSONObject jSONObject2 = jSONObject.getJSONObject(j.c);
                int parseInt = Integer.parseInt(jSONObject2.getString(b.J));
                setLocType(parseInt);
                setTime(jSONObject2.getString("time"));
                JSONObject jSONObject3;
                if (parseInt == 61) {
                    jSONObject3 = jSONObject.getJSONObject("content");
                    jSONObject = jSONObject3.getJSONObject("point");
                    setLatitude(Double.parseDouble(jSONObject.getString("y")));
                    setLongitude(Double.parseDouble(jSONObject.getString("x")));
                    setRadius(Float.parseFloat(jSONObject3.getString("radius")));
                    setSpeed(Float.parseFloat(jSONObject3.getString("s")));
                    setDirection(Float.parseFloat(jSONObject3.getString("d")));
                    setSatelliteNumber(Integer.parseInt(jSONObject3.getString("n")));
                    if (jSONObject3.has("h")) {
                        try {
                            setAltitude(jSONObject3.getDouble("h"));
                        } catch (Exception e) {
                        }
                    }
                    try {
                        if (jSONObject3.has("in_cn")) {
                            setLocationWhere(Integer.parseInt(jSONObject3.getString("in_cn")));
                        } else {
                            setLocationWhere(1);
                        }
                    } catch (Exception e2) {
                    }
                    if (this.mLocationWhere == 0) {
                        setCoorType("wgs84");
                    } else {
                        setCoorType("gcj02");
                    }
                } else if (parseInt == 161) {
                    int i;
                    JSONObject jSONObject4 = jSONObject.getJSONObject("content");
                    jSONObject = jSONObject4.getJSONObject("point");
                    setLatitude(Double.parseDouble(jSONObject.getString("y")));
                    setLongitude(Double.parseDouble(jSONObject.getString("x")));
                    setRadius(Float.parseFloat(jSONObject4.getString("radius")));
                    if (jSONObject4.has("sema")) {
                        Object string;
                        jSONObject2 = jSONObject4.getJSONObject("sema");
                        if (jSONObject2.has("aptag")) {
                            string = jSONObject2.getString("aptag");
                            if (TextUtils.isEmpty(string)) {
                                this.mSemaAptag = "";
                            } else {
                                this.mSemaAptag = string;
                            }
                        }
                        if (jSONObject2.has("aptagd")) {
                            JSONArray jSONArray = jSONObject2.getJSONObject("aptagd").getJSONArray("pois");
                            List arrayList = new ArrayList();
                            for (i = 0; i < jSONArray.length(); i++) {
                                JSONObject jSONObject5 = jSONArray.getJSONObject(i);
                                arrayList.add(new Poi(jSONObject5.getString("pid"), jSONObject5.getString("pname"), jSONObject5.getDouble(Config.PRINCIPAL_PART)));
                            }
                            this.mPoiList = arrayList;
                        }
                        if (jSONObject2.has("poiregion")) {
                            string = jSONObject2.getString("poiregion");
                            if (!TextUtils.isEmpty(string)) {
                                this.mSemaPoiRegion = string;
                            }
                        }
                        if (jSONObject2.has("regular")) {
                            string = jSONObject2.getString("regular");
                            if (!TextUtils.isEmpty(string)) {
                                this.mSemaRegular = string;
                            }
                        }
                    }
                    if (jSONObject4.has("addr")) {
                        String[] split = jSONObject4.getString("addr").split(Constants.ACCEPT_TIME_SEPARATOR_SP);
                        int length = split.length;
                        String str3 = length > 0 ? split[0] : null;
                        String str4 = length > 1 ? split[1] : null;
                        String str5 = length > 2 ? split[2] : null;
                        String str6 = length > 3 ? split[3] : null;
                        String str7 = length > 4 ? split[4] : null;
                        String str8 = length > 5 ? split[5] : null;
                        String str9 = length > 6 ? split[6] : null;
                        if (length > 7) {
                            str2 = split[7];
                        }
                        this.mAddr = new Builder().country(str9).countryCode(str2).province(str3).city(str4).cityCode(str8).district(str5).street(str6).streetNumber(str7).build();
                        this.mHasAddr = true;
                    } else {
                        this.mHasAddr = false;
                        setAddrStr(null);
                    }
                    if (jSONObject4.has("floor")) {
                        this.floor = jSONObject4.getString("floor");
                        if (TextUtils.isEmpty(this.floor)) {
                            this.floor = null;
                        }
                    }
                    if (jSONObject4.has("indoor")) {
                        Object string2 = jSONObject4.getString("indoor");
                        if (!TextUtils.isEmpty(string2)) {
                            setUserIndoorState(Integer.valueOf(string2).intValue());
                        }
                    }
                    if (jSONObject4.has("loctp")) {
                        this.netWorkLocationType = jSONObject4.getString("loctp");
                        if (TextUtils.isEmpty(this.netWorkLocationType)) {
                            this.netWorkLocationType = null;
                        }
                    }
                    if (jSONObject4.has("bldgid")) {
                        this.buildingid = jSONObject4.getString("bldgid");
                        if (TextUtils.isEmpty(this.buildingid)) {
                            this.buildingid = null;
                        }
                    }
                    if (jSONObject4.has("bldg")) {
                        this.mBuildingName = jSONObject4.getString("bldg");
                        if (TextUtils.isEmpty(this.mBuildingName)) {
                            this.mBuildingName = null;
                        }
                    }
                    if (jSONObject4.has("ibav")) {
                        str2 = jSONObject4.getString("ibav");
                        if (TextUtils.isEmpty(str2)) {
                            this.mParkState = 0;
                        } else if (str2.equals("0")) {
                            this.mParkState = 0;
                        } else {
                            this.mParkState = Integer.valueOf(str2).intValue();
                        }
                    }
                    if (jSONObject4.has("indoorflags")) {
                        try {
                            jSONObject3 = jSONObject4.getJSONObject("indoorflags");
                            if (jSONObject3.has("area")) {
                                i = Integer.valueOf(jSONObject3.getString("area")).intValue();
                                if (i == 0) {
                                    setIndoorLocationSurpport(2);
                                } else if (i == 1) {
                                    setIndoorLocationSurpport(1);
                                }
                            }
                            if (jSONObject3.has("support")) {
                                setIndoorLocationSource(Integer.valueOf(jSONObject3.getString("support")).intValue());
                            }
                            if (jSONObject3.has("inbldg")) {
                                this.mIndoorSurpportBuildingName = jSONObject3.getString("inbldg");
                            }
                            if (jSONObject3.has("inbldgid")) {
                                this.mIndoorSurpportBuildingID = jSONObject3.getString("inbldgid");
                            }
                        } catch (Exception e3) {
                            e3.printStackTrace();
                        }
                    }
                    try {
                        if (jSONObject4.has("in_cn")) {
                            setLocationWhere(Integer.parseInt(jSONObject4.getString("in_cn")));
                        } else {
                            setLocationWhere(1);
                        }
                    } catch (Exception e4) {
                    }
                    if (this.mLocationWhere == 0) {
                        setCoorType("wgs84");
                    } else {
                        setCoorType("gcj02");
                    }
                } else if (parseInt == 66 || parseInt == 68) {
                    jSONObject3 = jSONObject.getJSONObject("content");
                    jSONObject = jSONObject3.getJSONObject("point");
                    setLatitude(Double.parseDouble(jSONObject.getString("y")));
                    setLongitude(Double.parseDouble(jSONObject.getString("x")));
                    setRadius(Float.parseFloat(jSONObject3.getString("radius")));
                    setCellChangeFlag(Boolean.valueOf(Boolean.parseBoolean(jSONObject3.getString("isCellChanged"))));
                    setCoorType("gcj02");
                } else if (parseInt == TypeServerError) {
                    setLocationWhere(2);
                }
            } catch (Exception e32) {
                e32.printStackTrace();
                this.mLocType = 0;
                this.mHasAddr = false;
            }
        }
    }

    private void setCellChangeFlag(Boolean bool) {
        this.isCellChangeFlag = bool.booleanValue();
    }

    public int describeContents() {
        return 0;
    }

    public String getAddrStr() {
        return this.mAddr.address;
    }

    public Address getAddress() {
        return this.mAddr;
    }

    public double getAltitude() {
        return this.mAltitude;
    }

    public String getBuildingID() {
        return this.buildingid;
    }

    public String getBuildingName() {
        return this.mBuildingName;
    }

    public String getCity() {
        return this.mAddr.city;
    }

    public String getCityCode() {
        return this.mAddr.cityCode;
    }

    public String getCoorType() {
        return this.mCoorType;
    }

    public String getCountry() {
        return this.mAddr.country;
    }

    public String getCountryCode() {
        return this.mAddr.countryCode;
    }

    @Deprecated
    public float getDerect() {
        return this.mDerect;
    }

    public float getDirection() {
        return this.mDerect;
    }

    public String getDistrict() {
        return this.mAddr.district;
    }

    public String getFloor() {
        return this.floor;
    }

    public int getGpsAccuracyStatus() {
        return this.mGpsAccuracyStatus;
    }

    public int getIndoorLocationSource() {
        return this.mIndoorSource;
    }

    public int getIndoorLocationSurpport() {
        return this.mIndoorLocationSurpport;
    }

    public String getIndoorLocationSurpportBuidlingID() {
        return this.mIndoorSurpportBuildingID;
    }

    public String getIndoorLocationSurpportBuidlingName() {
        return this.mIndoorSurpportBuildingName;
    }

    public int getIndoorNetworkState() {
        return this.mIndoorNetworkState;
    }

    public double getLatitude() {
        return this.mLatitude;
    }

    public int getLocType() {
        return this.mLocType;
    }

    public String getLocTypeDescription() {
        return this.mDescription;
    }

    public String getLocationDescribe() {
        return this.mSemaAptag;
    }

    public int getLocationWhere() {
        return this.mLocationWhere;
    }

    public double getLongitude() {
        return this.mLongitude;
    }

    public String getNetworkLocationType() {
        return this.netWorkLocationType;
    }

    public int getOperators() {
        return this.mOperators;
    }

    public List<Poi> getPoiList() {
        return this.mPoiList;
    }

    public String getProvince() {
        return this.mAddr.province;
    }

    public float getRadius() {
        return this.mRadius;
    }

    public int getSatelliteNumber() {
        this.mHasSateNumber = true;
        return this.mSatelliteNumber;
    }

    @Deprecated
    public String getSemaAptag() {
        return this.mSemaAptag;
    }

    public float getSpeed() {
        return this.mSpeed;
    }

    public String getStreet() {
        return this.mAddr.street;
    }

    public String getStreetNumber() {
        return this.mAddr.streetNumber;
    }

    public String getTime() {
        return this.mTime;
    }

    public int getUserIndoorState() {
        return this.mIndoorState;
    }

    public boolean hasAddr() {
        return this.mHasAddr;
    }

    public boolean hasAltitude() {
        return this.mHasAltitude;
    }

    public boolean hasRadius() {
        return this.mHasRadius;
    }

    public boolean hasSateNumber() {
        return this.mHasSateNumber;
    }

    public boolean hasSpeed() {
        return this.mHasSpeed;
    }

    public boolean isCellChangeFlag() {
        return this.isCellChangeFlag;
    }

    public boolean isIndoorLocMode() {
        return this.indoorLocMode;
    }

    public int isParkAvailable() {
        return this.mParkState;
    }

    public void setAddr(Address address) {
        if (address != null) {
            this.mAddr = address;
            this.mHasAddr = true;
        }
    }

    public void setAddrStr(String str) {
        this.mAddrStr = str;
        if (str == null) {
            this.mHasAddr = false;
        } else {
            this.mHasAddr = true;
        }
    }

    public void setAltitude(double d) {
        this.mAltitude = d;
        this.mHasAltitude = true;
    }

    public void setBuildingID(String str) {
        this.buildingid = str;
    }

    public void setBuildingName(String str) {
        this.mBuildingName = str;
    }

    public void setCoorType(String str) {
        this.mCoorType = str;
    }

    public void setDirection(float f) {
        this.mDerect = f;
    }

    public void setFloor(String str) {
        this.floor = str;
    }

    public void setGpsAccuracyStatus(int i) {
        this.mGpsAccuracyStatus = i;
    }

    public void setIndoorLocMode(boolean z) {
        this.indoorLocMode = z;
    }

    public void setIndoorLocationSource(int i) {
        this.mIndoorSource = i;
    }

    public void setIndoorLocationSurpport(int i) {
        this.mIndoorLocationSurpport = i;
    }

    public void setIndoorNetworkState(int i) {
        this.mIndoorNetworkState = i;
    }

    public void setLatitude(double d) {
        this.mLatitude = d;
    }

    public void setLocType(int i) {
        this.mLocType = i;
        switch (i) {
            case 61:
                setLocTypeDescription("GPS location successful!");
                setUserIndoorState(0);
                return;
            case 62:
                setLocTypeDescription("Location failed beacuse we can not get any loc information!");
                return;
            case 63:
            case 67:
                setLocTypeDescription("Offline location failed , please check the net (wifi/cell)!");
                return;
            case 66:
                setLocTypeDescription("Offline location successful!");
                return;
            case 161:
                setLocTypeDescription("NetWork location successful!");
                return;
            case 162:
                setLocTypeDescription("NetWork location failed because baidu location service can not decrypt the request query, please check the so file !");
                return;
            case TypeServerError /*167*/:
                setLocTypeDescription("NetWork location failed because baidu location service can not caculate the location!");
                return;
            case 505:
                setLocTypeDescription("NetWork location failed because baidu location service check the key is unlegal, please check the key in AndroidManifest.xml !");
                return;
            default:
                setLocTypeDescription("UnKnown!");
                return;
        }
    }

    public void setLocTypeDescription(String str) {
        this.mDescription = str;
    }

    public void setLocationDescribe(String str) {
        this.mSemaAptag = str;
    }

    public void setLocationWhere(int i) {
        this.mLocationWhere = i;
    }

    public void setLongitude(double d) {
        this.mLongitude = d;
    }

    public void setNetworkLocationType(String str) {
        this.netWorkLocationType = str;
    }

    public void setOperators(int i) {
        this.mOperators = i;
    }

    public void setParkAvailable(int i) {
        this.mParkState = i;
    }

    public void setPoiList(List<Poi> list) {
        this.mPoiList = list;
    }

    public void setRadius(float f) {
        this.mRadius = f;
        this.mHasRadius = true;
    }

    public void setSatelliteNumber(int i) {
        this.mSatelliteNumber = i;
    }

    public void setSpeed(float f) {
        this.mSpeed = f;
        this.mHasSpeed = true;
    }

    public void setTime(String str) {
        this.mTime = str;
    }

    public void setUserIndoorState(int i) {
        this.mIndoorState = i;
    }

    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(this.mLocType);
        parcel.writeString(this.mTime);
        parcel.writeDouble(this.mLatitude);
        parcel.writeDouble(this.mLongitude);
        parcel.writeDouble(this.mAltitude);
        parcel.writeFloat(this.mSpeed);
        parcel.writeFloat(this.mRadius);
        parcel.writeInt(this.mSatelliteNumber);
        parcel.writeFloat(this.mDerect);
        parcel.writeString(this.floor);
        parcel.writeInt(this.mParkState);
        parcel.writeString(this.buildingid);
        parcel.writeString(this.mBuildingName);
        parcel.writeString(this.netWorkLocationType);
        parcel.writeString(this.mAddr.province);
        parcel.writeString(this.mAddr.city);
        parcel.writeString(this.mAddr.district);
        parcel.writeString(this.mAddr.street);
        parcel.writeString(this.mAddr.streetNumber);
        parcel.writeString(this.mAddr.cityCode);
        parcel.writeString(this.mAddr.address);
        parcel.writeString(this.mAddr.country);
        parcel.writeString(this.mAddr.countryCode);
        parcel.writeInt(this.mOperators);
        parcel.writeString(this.mCu);
        parcel.writeString(this.mSemaAptag);
        parcel.writeString(this.mSemaPoiRegion);
        parcel.writeString(this.mSemaRegular);
        parcel.writeInt(this.mLocationWhere);
        parcel.writeString(this.mDescription);
        parcel.writeInt(this.mIndoorState);
        parcel.writeInt(this.mIndoorLocationSurpport);
        parcel.writeInt(this.mIndoorNetworkState);
        parcel.writeInt(this.mIndoorSource);
        parcel.writeString(this.mIndoorSurpportBuildingName);
        parcel.writeString(this.mIndoorSurpportBuildingID);
        parcel.writeInt(this.mGpsAccuracyStatus);
        parcel.writeBooleanArray(new boolean[]{this.mHasAltitude, this.mHasSpeed, this.mHasRadius, this.mHasSateNumber, this.mHasAddr, this.isCellChangeFlag, this.indoorLocMode});
        parcel.writeList(this.mPoiList);
    }
}
