package com.loc;

import android.os.Bundle;
import com.alipay.sdk.util.h;
import com.amap.api.fence.DistrictItem;
import com.amap.api.fence.GeoFence;
import com.amap.api.fence.PoiItem;
import com.amap.api.location.DPoint;
import com.xiaomi.mipush.sdk.Constants;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONObject;

public final class c {
    private static long a = 0;

    public static int a(String str, List<GeoFence> list, Bundle bundle) {
        try {
            JSONObject jSONObject = new JSONObject(str);
            int optInt = jSONObject.optInt("status", 0);
            int optInt2 = jSONObject.optInt("infocode", 0);
            if (optInt != 1) {
                return optInt2;
            }
            JSONArray optJSONArray = jSONObject.optJSONArray("pois");
            if (optJSONArray == null) {
                return optInt2;
            }
            for (int i = 0; i < optJSONArray.length(); i++) {
                GeoFence geoFence = new GeoFence();
                PoiItem poiItem = new PoiItem();
                JSONObject jSONObject2 = optJSONArray.getJSONObject(i);
                poiItem.setPoiId(jSONObject2.optString("id"));
                poiItem.setPoiName(jSONObject2.optString("name"));
                poiItem.setPoiType(jSONObject2.optString("type"));
                poiItem.setTypeCode(jSONObject2.optString("typecode"));
                poiItem.setAddress(jSONObject2.optString("address"));
                String optString = jSONObject2.optString("location");
                if (optString != null) {
                    String[] split = optString.split(Constants.ACCEPT_TIME_SEPARATOR_SP);
                    poiItem.setLongitude(Double.parseDouble(split[0]));
                    poiItem.setLatitude(Double.parseDouble(split[1]));
                    List arrayList = new ArrayList();
                    ArrayList arrayList2 = new ArrayList();
                    DPoint dPoint = new DPoint(poiItem.getLatitude(), poiItem.getLongitude());
                    arrayList2.add(dPoint);
                    arrayList.add(arrayList2);
                    geoFence.setPointList(arrayList);
                    geoFence.setCenter(dPoint);
                }
                poiItem.setTel(jSONObject2.optString("tel"));
                poiItem.setProvince(jSONObject2.optString("pname"));
                poiItem.setCity(jSONObject2.optString("cityname"));
                poiItem.setAdname(jSONObject2.optString("adname"));
                geoFence.setPoiItem(poiItem);
                geoFence.setFenceId(a());
                if (bundle != null) {
                    geoFence.setCustomId(bundle.getString(GeoFence.BUNDLE_KEY_CUSTOMID));
                    geoFence.setPendingIntentAction(bundle.getString("pendingIntentAction"));
                    geoFence.setType(2);
                    geoFence.setRadius(bundle.getFloat("geoRadius"));
                    geoFence.setExpiration(bundle.getLong("expiration"));
                    geoFence.setActivatesAction(bundle.getInt("activatesAction", 1));
                }
                if (list != null) {
                    list.add(geoFence);
                }
            }
            return optInt2;
        } catch (Throwable th) {
            return 5;
        }
    }

    public static synchronized long a() {
        long b;
        synchronized (c.class) {
            Object obj = null;
            while (obj == null) {
                b = de.b();
                if (b == a) {
                    try {
                        Thread.currentThread();
                        Thread.sleep(1);
                    } catch (InterruptedException e) {
                    }
                } else {
                    a = b;
                    obj = 1;
                }
            }
            b = a;
        }
        return b;
    }

    private List<DPoint> a(List<DPoint> list, float f) {
        if (list == null) {
            return null;
        }
        if (list.size() <= 2) {
            return list;
        }
        List<DPoint> arrayList = new ArrayList();
        DPoint dPoint = (DPoint) list.get(0);
        DPoint dPoint2 = (DPoint) list.get(list.size() - 1);
        double d = 0.0d;
        int i = 0;
        for (int i2 = 1; i2 < list.size() - 1; i2++) {
            double longitude;
            DPoint dPoint3 = (DPoint) list.get(i2);
            double longitude2 = dPoint2.getLongitude() - dPoint.getLongitude();
            double latitude = dPoint2.getLatitude() - dPoint.getLatitude();
            double longitude3 = (((dPoint3.getLongitude() - dPoint.getLongitude()) * longitude2) + ((dPoint3.getLatitude() - dPoint.getLatitude()) * latitude)) / ((longitude2 * longitude2) + (latitude * latitude));
            if (longitude3 < 0.0d || (dPoint.getLongitude() == dPoint2.getLongitude() && dPoint.getLatitude() == dPoint2.getLatitude())) {
                longitude = dPoint.getLongitude();
                longitude3 = dPoint.getLatitude();
            } else if (longitude3 > 1.0d) {
                longitude = dPoint2.getLongitude();
                longitude3 = dPoint2.getLatitude();
            } else {
                longitude = dPoint.getLongitude() + (longitude2 * longitude3);
                longitude3 = (longitude3 * latitude) + dPoint.getLatitude();
            }
            longitude3 = (double) de.a(new DPoint(dPoint3.getLatitude(), dPoint3.getLongitude()), new DPoint(longitude3, longitude));
            if (longitude3 > d) {
                i = i2;
                d = longitude3;
            }
        }
        if (d < ((double) f)) {
            arrayList.add(dPoint);
            arrayList.add(dPoint2);
            return arrayList;
        }
        Collection a = a(list.subList(0, i + 1), f);
        Collection a2 = a(list.subList(i, list.size()), f);
        arrayList.addAll(a);
        arrayList.remove(arrayList.size() - 1);
        arrayList.addAll(a2);
        return arrayList;
    }

    public static int b(String str, List<GeoFence> list, Bundle bundle) {
        return a(str, list, bundle);
    }

    public final int c(String str, List<GeoFence> list, Bundle bundle) {
        try {
            long j;
            int i;
            float f;
            String str2;
            String str3;
            JSONObject jSONObject = new JSONObject(str);
            int optInt = jSONObject.optInt("status", 0);
            int optInt2 = jSONObject.optInt("infocode", 0);
            if (bundle != null) {
                String string = bundle.getString(GeoFence.BUNDLE_KEY_CUSTOMID);
                String string2 = bundle.getString("pendingIntentAction");
                float f2 = bundle.getFloat("geoRadius");
                j = bundle.getLong("expiration");
                i = bundle.getInt("activatesAction");
                f = f2;
                str2 = string2;
                str3 = string;
            } else {
                j = 0;
                i = 0;
                f = 0.0f;
                str2 = null;
                str3 = null;
            }
            if (optInt != 1) {
                return optInt2;
            }
            JSONArray optJSONArray = jSONObject.optJSONArray("districts");
            if (optJSONArray == null) {
                return optInt2;
            }
            for (int i2 = 0; i2 < optJSONArray.length(); i2++) {
                ArrayList arrayList = new ArrayList();
                ArrayList arrayList2 = new ArrayList();
                GeoFence geoFence = new GeoFence();
                JSONObject jSONObject2 = optJSONArray.getJSONObject(i2);
                String optString = jSONObject2.optString("citycode");
                String optString2 = jSONObject2.optString("adcode");
                String optString3 = jSONObject2.optString("name");
                String string3 = jSONObject2.getString("center");
                DPoint dPoint = new DPoint();
                if (string3 != null) {
                    String[] split = string3.split(Constants.ACCEPT_TIME_SEPARATOR_SP);
                    dPoint.setLatitude(Double.parseDouble(split[1]));
                    dPoint.setLongitude(Double.parseDouble(split[0]));
                    geoFence.setCenter(dPoint);
                }
                geoFence.setCustomId(str3);
                geoFence.setPendingIntentAction(str2);
                geoFence.setType(3);
                geoFence.setRadius(f);
                geoFence.setExpiration(j);
                geoFence.setActivatesAction(i);
                geoFence.setFenceId(a());
                String optString4 = jSONObject2.optString("polyline");
                if (optString4 != null) {
                    String[] split2 = optString4.split("\\|");
                    int length = split2.length;
                    float f3 = Float.MAX_VALUE;
                    float f4 = Float.MIN_VALUE;
                    int i3 = 0;
                    while (i3 < length) {
                        string3 = split2[i3];
                        DistrictItem districtItem = new DistrictItem();
                        List arrayList3 = new ArrayList();
                        districtItem.setCitycode(optString);
                        districtItem.setAdcode(optString2);
                        districtItem.setDistrictName(optString3);
                        String[] split3 = string3.split(h.b);
                        for (String split4 : split3) {
                            String[] split5 = split4.split(Constants.ACCEPT_TIME_SEPARATOR_SP);
                            if (split5.length > 1) {
                                arrayList3.add(new DPoint(Double.parseDouble(split5[1]), Double.parseDouble(split5[0])));
                            }
                        }
                        if (((float) arrayList3.size()) > 100.0f) {
                            arrayList3 = a(arrayList3, 100.0f);
                        }
                        arrayList2.add(arrayList3);
                        districtItem.setPolyline(arrayList3);
                        arrayList.add(districtItem);
                        f4 = Math.max(f4, a.b(dPoint, arrayList3));
                        i3++;
                        f3 = Math.min(f3, a.a(dPoint, arrayList3));
                    }
                    geoFence.setMaxDis2Center(f4);
                    geoFence.setMinDis2Center(f3);
                    geoFence.setDistrictItemList(arrayList);
                    geoFence.setPointList(arrayList2);
                    if (list != null) {
                        list.add(geoFence);
                    }
                }
            }
            return optInt2;
        } catch (Throwable th) {
            return 5;
        }
    }
}
