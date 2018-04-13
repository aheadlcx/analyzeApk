package com.loc;

import android.content.Context;
import android.os.Bundle;
import android.text.TextUtils;
import com.autonavi.aps.amapapi.model.AMapLocationServer;
import com.fasterxml.jackson.core.util.MinimalPrettyPrinter;
import com.tencent.open.SocialConstants;
import com.umeng.commonsdk.proguard.g;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONObject;

public final class ct {
    private StringBuilder a = new StringBuilder();

    public static AMapLocationServer a(String str) {
        try {
            Object obj;
            String b;
            AMapLocationServer aMapLocationServer = new AMapLocationServer("");
            JSONObject optJSONObject = new JSONObject(str).optJSONObject("regeocode");
            JSONObject optJSONObject2 = optJSONObject.optJSONObject("addressComponent");
            aMapLocationServer.setCountry(b(optJSONObject2.optString(g.N)));
            String b2 = b(optJSONObject2.optString("province"));
            aMapLocationServer.setProvince(b2);
            String b3 = b(optJSONObject2.optString("citycode"));
            aMapLocationServer.setCityCode(b3);
            CharSequence optString = optJSONObject2.optString("city");
            if (!b3.endsWith("010") && !b3.endsWith("021") && !b3.endsWith("022") && !b3.endsWith("023")) {
                optString = b(optString);
                aMapLocationServer.setCity(optString);
            } else if (b2 != null && b2.length() > 0) {
                aMapLocationServer.setCity(b2);
                optString = b2;
            }
            if (TextUtils.isEmpty(optString)) {
                aMapLocationServer.setCity(b2);
                obj = b2;
            } else {
                CharSequence charSequence = optString;
            }
            Object b4 = b(optJSONObject2.optString("district"));
            aMapLocationServer.setDistrict(b4);
            Object b5 = b(optJSONObject2.optString("adcode"));
            aMapLocationServer.setAdCode(b5);
            JSONObject optJSONObject3 = optJSONObject2.optJSONObject("streetNumber");
            Object b6 = b(optJSONObject3.optString("street"));
            aMapLocationServer.setStreet(b6);
            aMapLocationServer.setRoad(b6);
            aMapLocationServer.setNumber(b(optJSONObject3.optString("number")));
            JSONArray optJSONArray = optJSONObject.optJSONArray("pois");
            if (optJSONArray.length() > 0) {
                b = b(optJSONArray.getJSONObject(0).optString("name"));
                aMapLocationServer.setPoiName(b);
            } else {
                b = null;
            }
            JSONArray optJSONArray2 = optJSONObject.optJSONArray("aois");
            if (optJSONArray2.length() > 0) {
                aMapLocationServer.setAoiName(b(optJSONArray2.getJSONObject(0).optString("name")));
            }
            StringBuilder stringBuilder = new StringBuilder();
            if (!TextUtils.isEmpty(b2)) {
                stringBuilder.append(b2).append(MinimalPrettyPrinter.DEFAULT_ROOT_VALUE_SEPARATOR);
            }
            if (!(TextUtils.isEmpty(obj) || (b2.contains("市") && b2.equals(obj)))) {
                stringBuilder.append(obj).append(MinimalPrettyPrinter.DEFAULT_ROOT_VALUE_SEPARATOR);
            }
            if (!TextUtils.isEmpty(b4)) {
                stringBuilder.append(b4).append(MinimalPrettyPrinter.DEFAULT_ROOT_VALUE_SEPARATOR);
            }
            if (!TextUtils.isEmpty(b6)) {
                stringBuilder.append(b6).append(MinimalPrettyPrinter.DEFAULT_ROOT_VALUE_SEPARATOR);
            }
            if (!TextUtils.isEmpty(b)) {
                if (!TextUtils.isEmpty(b5)) {
                    stringBuilder.append("靠近");
                }
                stringBuilder.append(b).append(MinimalPrettyPrinter.DEFAULT_ROOT_VALUE_SEPARATOR);
                aMapLocationServer.setDescription("在" + b + "附件");
            }
            Bundle bundle = new Bundle();
            bundle.putString("citycode", aMapLocationServer.getCityCode());
            bundle.putString(SocialConstants.PARAM_APP_DESC, stringBuilder.toString());
            bundle.putString("adcode", aMapLocationServer.getAdCode());
            aMapLocationServer.setExtras(bundle);
            aMapLocationServer.f(stringBuilder.toString());
            if (b5 == null || b5.trim().length() <= 0) {
                aMapLocationServer.setAddress(stringBuilder.toString());
                return aMapLocationServer;
            }
            aMapLocationServer.setAddress(stringBuilder.toString().replace(MinimalPrettyPrinter.DEFAULT_ROOT_VALUE_SEPARATOR, ""));
            return aMapLocationServer;
        } catch (Throwable th) {
            return null;
        }
    }

    private static String b(String str) {
        return "[]".equals(str) ? "" : str;
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final com.autonavi.aps.amapapi.model.AMapLocationServer a(com.autonavi.aps.amapapi.model.AMapLocationServer r13, byte[] r14) {
        /*
        r12 = this;
        r1 = 0;
        if (r14 != 0) goto L_0x0024;
    L_0x0003:
        r0 = 5;
        r13.setErrorCode(r0);	 Catch:{ Throwable -> 0x0353, all -> 0x0346 }
        r0 = r12.a;	 Catch:{ Throwable -> 0x0353, all -> 0x0346 }
        r2 = "binaryResult is null";
        r0.append(r2);	 Catch:{ Throwable -> 0x0353, all -> 0x0346 }
        r0 = r12.a;	 Catch:{ Throwable -> 0x0353, all -> 0x0346 }
        r0 = r0.toString();	 Catch:{ Throwable -> 0x0353, all -> 0x0346 }
        r13.setLocationDetail(r0);	 Catch:{ Throwable -> 0x0353, all -> 0x0346 }
        r0 = r12.a;	 Catch:{ Throwable -> 0x0353, all -> 0x0346 }
        r2 = 0;
        r3 = r12.a;	 Catch:{ Throwable -> 0x0353, all -> 0x0346 }
        r3 = r3.length();	 Catch:{ Throwable -> 0x0353, all -> 0x0346 }
        r0.delete(r2, r3);	 Catch:{ Throwable -> 0x0353, all -> 0x0346 }
    L_0x0023:
        return r13;
    L_0x0024:
        r8 = java.nio.ByteBuffer.wrap(r14);	 Catch:{ Throwable -> 0x0353, all -> 0x0346 }
        r0 = r8.get();	 Catch:{ Throwable -> 0x030c, all -> 0x034e }
        if (r0 != 0) goto L_0x0042;
    L_0x002e:
        r0 = r8.getShort();	 Catch:{ Throwable -> 0x030c, all -> 0x034e }
        r0 = java.lang.String.valueOf(r0);	 Catch:{ Throwable -> 0x030c, all -> 0x034e }
        r13.b(r0);	 Catch:{ Throwable -> 0x030c, all -> 0x034e }
        r8.clear();	 Catch:{ Throwable -> 0x030c, all -> 0x034e }
        if (r8 == 0) goto L_0x0023;
    L_0x003e:
        r8.clear();
        goto L_0x0023;
    L_0x0042:
        r0 = r8.getInt();	 Catch:{ Throwable -> 0x030c, all -> 0x034e }
        r0 = (double) r0;	 Catch:{ Throwable -> 0x030c, all -> 0x034e }
        r2 = 4696837146684686336; // 0x412e848000000000 float:0.0 double:1000000.0;
        r0 = r0 / r2;
        r0 = com.loc.de.a(r0);	 Catch:{ Throwable -> 0x030c, all -> 0x034e }
        r13.setLongitude(r0);	 Catch:{ Throwable -> 0x030c, all -> 0x034e }
        r0 = r8.getInt();	 Catch:{ Throwable -> 0x030c, all -> 0x034e }
        r0 = (double) r0;	 Catch:{ Throwable -> 0x030c, all -> 0x034e }
        r2 = 4696837146684686336; // 0x412e848000000000 float:0.0 double:1000000.0;
        r0 = r0 / r2;
        r0 = com.loc.de.a(r0);	 Catch:{ Throwable -> 0x030c, all -> 0x034e }
        r13.setLatitude(r0);	 Catch:{ Throwable -> 0x030c, all -> 0x034e }
        r0 = r8.getShort();	 Catch:{ Throwable -> 0x030c, all -> 0x034e }
        r0 = (float) r0;	 Catch:{ Throwable -> 0x030c, all -> 0x034e }
        r13.setAccuracy(r0);	 Catch:{ Throwable -> 0x030c, all -> 0x034e }
        r0 = r8.get();	 Catch:{ Throwable -> 0x030c, all -> 0x034e }
        r0 = java.lang.String.valueOf(r0);	 Catch:{ Throwable -> 0x030c, all -> 0x034e }
        r13.c(r0);	 Catch:{ Throwable -> 0x030c, all -> 0x034e }
        r0 = r8.get();	 Catch:{ Throwable -> 0x030c, all -> 0x034e }
        r0 = java.lang.String.valueOf(r0);	 Catch:{ Throwable -> 0x030c, all -> 0x034e }
        r13.d(r0);	 Catch:{ Throwable -> 0x030c, all -> 0x034e }
        r0 = r8.get();	 Catch:{ Throwable -> 0x030c, all -> 0x034e }
        r1 = 1;
        if (r0 != r1) goto L_0x024d;
    L_0x008b:
        r1 = "";
        r2 = "";
        r3 = "";
        r4 = "";
        r5 = "";
        r6 = "";
        r7 = "";
        r0 = r8.get();	 Catch:{ Throwable -> 0x030c, all -> 0x034e }
        r0 = r0 & 255;
        r0 = new byte[r0];	 Catch:{ Throwable -> 0x030c, all -> 0x034e }
        r8.get(r0);	 Catch:{ Throwable -> 0x030c, all -> 0x034e }
        r9 = new java.lang.String;	 Catch:{ Throwable -> 0x037a, all -> 0x034e }
        r10 = "UTF-8";
        r9.<init>(r0, r10);	 Catch:{ Throwable -> 0x037a, all -> 0x034e }
        r13.setCountry(r9);	 Catch:{ Throwable -> 0x037a, all -> 0x034e }
    L_0x00ae:
        r0 = r8.get();	 Catch:{ Throwable -> 0x030c, all -> 0x034e }
        r0 = r0 & 255;
        r9 = new byte[r0];	 Catch:{ Throwable -> 0x030c, all -> 0x034e }
        r8.get(r9);	 Catch:{ Throwable -> 0x030c, all -> 0x034e }
        r0 = new java.lang.String;	 Catch:{ Throwable -> 0x02e5, all -> 0x034e }
        r10 = "UTF-8";
        r0.<init>(r9, r10);	 Catch:{ Throwable -> 0x02e5, all -> 0x034e }
        r13.setProvince(r0);	 Catch:{ Throwable -> 0x0377, all -> 0x034e }
        r11 = r0;
    L_0x00c4:
        r0 = r8.get();	 Catch:{ Throwable -> 0x030c, all -> 0x034e }
        r0 = r0 & 255;
        r1 = new byte[r0];	 Catch:{ Throwable -> 0x030c, all -> 0x034e }
        r8.get(r1);	 Catch:{ Throwable -> 0x030c, all -> 0x034e }
        r0 = new java.lang.String;	 Catch:{ Throwable -> 0x02ea, all -> 0x034e }
        r9 = "UTF-8";
        r0.<init>(r1, r9);	 Catch:{ Throwable -> 0x02ea, all -> 0x034e }
        r13.setCity(r0);	 Catch:{ Throwable -> 0x0374, all -> 0x034e }
        r10 = r0;
    L_0x00da:
        r0 = r8.get();	 Catch:{ Throwable -> 0x030c, all -> 0x034e }
        r0 = r0 & 255;
        r1 = new byte[r0];	 Catch:{ Throwable -> 0x030c, all -> 0x034e }
        r8.get(r1);	 Catch:{ Throwable -> 0x030c, all -> 0x034e }
        r0 = new java.lang.String;	 Catch:{ Throwable -> 0x02ef, all -> 0x034e }
        r2 = "UTF-8";
        r0.<init>(r1, r2);	 Catch:{ Throwable -> 0x02ef, all -> 0x034e }
        r13.setDistrict(r0);	 Catch:{ Throwable -> 0x0371, all -> 0x034e }
        r9 = r0;
    L_0x00f0:
        r0 = r8.get();	 Catch:{ Throwable -> 0x030c, all -> 0x034e }
        r0 = r0 & 255;
        r1 = new byte[r0];	 Catch:{ Throwable -> 0x030c, all -> 0x034e }
        r8.get(r1);	 Catch:{ Throwable -> 0x030c, all -> 0x034e }
        r0 = new java.lang.String;	 Catch:{ Throwable -> 0x02f4, all -> 0x034e }
        r2 = "UTF-8";
        r0.<init>(r1, r2);	 Catch:{ Throwable -> 0x02f4, all -> 0x034e }
        r13.setStreet(r0);	 Catch:{ Throwable -> 0x036f, all -> 0x034e }
        r13.setRoad(r0);	 Catch:{ Throwable -> 0x036f, all -> 0x034e }
        r3 = r0;
    L_0x0109:
        r0 = r8.get();	 Catch:{ Throwable -> 0x030c, all -> 0x034e }
        r0 = r0 & 255;
        r1 = new byte[r0];	 Catch:{ Throwable -> 0x030c, all -> 0x034e }
        r8.get(r1);	 Catch:{ Throwable -> 0x030c, all -> 0x034e }
        r0 = new java.lang.String;	 Catch:{ Throwable -> 0x02f9, all -> 0x034e }
        r2 = "UTF-8";
        r0.<init>(r1, r2);	 Catch:{ Throwable -> 0x02f9, all -> 0x034e }
        r13.setNumber(r0);	 Catch:{ Throwable -> 0x036d, all -> 0x034e }
        r2 = r0;
    L_0x011f:
        r0 = r8.get();	 Catch:{ Throwable -> 0x030c, all -> 0x034e }
        r0 = r0 & 255;
        r1 = new byte[r0];	 Catch:{ Throwable -> 0x030c, all -> 0x034e }
        r8.get(r1);	 Catch:{ Throwable -> 0x030c, all -> 0x034e }
        r0 = new java.lang.String;	 Catch:{ Throwable -> 0x02fe, all -> 0x034e }
        r4 = "UTF-8";
        r0.<init>(r1, r4);	 Catch:{ Throwable -> 0x02fe, all -> 0x034e }
        r13.setPoiName(r0);	 Catch:{ Throwable -> 0x036b, all -> 0x034e }
        r1 = r0;
    L_0x0135:
        r0 = r8.get();	 Catch:{ Throwable -> 0x030c, all -> 0x034e }
        r0 = r0 & 255;
        r0 = new byte[r0];	 Catch:{ Throwable -> 0x030c, all -> 0x034e }
        r8.get(r0);	 Catch:{ Throwable -> 0x030c, all -> 0x034e }
        r4 = new java.lang.String;	 Catch:{ Throwable -> 0x0368, all -> 0x034e }
        r5 = "UTF-8";
        r4.<init>(r0, r5);	 Catch:{ Throwable -> 0x0368, all -> 0x034e }
        r13.setAoiName(r4);	 Catch:{ Throwable -> 0x0368, all -> 0x034e }
    L_0x014a:
        r0 = r8.get();	 Catch:{ Throwable -> 0x030c, all -> 0x034e }
        r0 = r0 & 255;
        r4 = new byte[r0];	 Catch:{ Throwable -> 0x030c, all -> 0x034e }
        r8.get(r4);	 Catch:{ Throwable -> 0x030c, all -> 0x034e }
        r0 = new java.lang.String;	 Catch:{ Throwable -> 0x0361, all -> 0x034e }
        r5 = "UTF-8";
        r0.<init>(r4, r5);	 Catch:{ Throwable -> 0x0361, all -> 0x034e }
        r13.setAdCode(r0);	 Catch:{ Throwable -> 0x0365, all -> 0x034e }
    L_0x015f:
        r4 = r8.get();	 Catch:{ Throwable -> 0x030c, all -> 0x034e }
        r4 = r4 & 255;
        r4 = new byte[r4];	 Catch:{ Throwable -> 0x030c, all -> 0x034e }
        r8.get(r4);	 Catch:{ Throwable -> 0x030c, all -> 0x034e }
        r5 = new java.lang.String;	 Catch:{ Throwable -> 0x035e, all -> 0x034e }
        r6 = "UTF-8";
        r5.<init>(r4, r6);	 Catch:{ Throwable -> 0x035e, all -> 0x034e }
        r13.setCityCode(r5);	 Catch:{ Throwable -> 0x035e, all -> 0x034e }
    L_0x0174:
        r4 = new java.lang.StringBuilder;	 Catch:{ Throwable -> 0x030c, all -> 0x034e }
        r4.<init>();	 Catch:{ Throwable -> 0x030c, all -> 0x034e }
        r5 = android.text.TextUtils.isEmpty(r11);	 Catch:{ Throwable -> 0x030c, all -> 0x034e }
        if (r5 != 0) goto L_0x0188;
    L_0x017f:
        r5 = r4.append(r11);	 Catch:{ Throwable -> 0x030c, all -> 0x034e }
        r6 = " ";
        r5.append(r6);	 Catch:{ Throwable -> 0x030c, all -> 0x034e }
    L_0x0188:
        r5 = android.text.TextUtils.isEmpty(r10);	 Catch:{ Throwable -> 0x030c, all -> 0x034e }
        if (r5 != 0) goto L_0x01a5;
    L_0x018e:
        r5 = "市";
        r5 = r11.contains(r5);	 Catch:{ Throwable -> 0x030c, all -> 0x034e }
        if (r5 == 0) goto L_0x019c;
    L_0x0196:
        r5 = r11.equals(r10);	 Catch:{ Throwable -> 0x030c, all -> 0x034e }
        if (r5 != 0) goto L_0x01a5;
    L_0x019c:
        r5 = r4.append(r10);	 Catch:{ Throwable -> 0x030c, all -> 0x034e }
        r6 = " ";
        r5.append(r6);	 Catch:{ Throwable -> 0x030c, all -> 0x034e }
    L_0x01a5:
        r5 = android.text.TextUtils.isEmpty(r9);	 Catch:{ Throwable -> 0x030c, all -> 0x034e }
        if (r5 != 0) goto L_0x01b4;
    L_0x01ab:
        r5 = r4.append(r9);	 Catch:{ Throwable -> 0x030c, all -> 0x034e }
        r6 = " ";
        r5.append(r6);	 Catch:{ Throwable -> 0x030c, all -> 0x034e }
    L_0x01b4:
        r5 = android.text.TextUtils.isEmpty(r3);	 Catch:{ Throwable -> 0x030c, all -> 0x034e }
        if (r5 != 0) goto L_0x01c3;
    L_0x01ba:
        r3 = r4.append(r3);	 Catch:{ Throwable -> 0x030c, all -> 0x034e }
        r5 = " ";
        r3.append(r5);	 Catch:{ Throwable -> 0x030c, all -> 0x034e }
    L_0x01c3:
        r3 = android.text.TextUtils.isEmpty(r2);	 Catch:{ Throwable -> 0x030c, all -> 0x034e }
        if (r3 != 0) goto L_0x01d2;
    L_0x01c9:
        r2 = r4.append(r2);	 Catch:{ Throwable -> 0x030c, all -> 0x034e }
        r3 = " ";
        r2.append(r3);	 Catch:{ Throwable -> 0x030c, all -> 0x034e }
    L_0x01d2:
        r2 = android.text.TextUtils.isEmpty(r1);	 Catch:{ Throwable -> 0x030c, all -> 0x034e }
        if (r2 != 0) goto L_0x0204;
    L_0x01d8:
        r0 = android.text.TextUtils.isEmpty(r0);	 Catch:{ Throwable -> 0x030c, all -> 0x034e }
        if (r0 != 0) goto L_0x01e3;
    L_0x01de:
        r0 = "靠近";
        r4.append(r0);	 Catch:{ Throwable -> 0x030c, all -> 0x034e }
    L_0x01e3:
        r0 = r4.append(r1);	 Catch:{ Throwable -> 0x030c, all -> 0x034e }
        r2 = " ";
        r0.append(r2);	 Catch:{ Throwable -> 0x030c, all -> 0x034e }
        r0 = new java.lang.StringBuilder;	 Catch:{ Throwable -> 0x030c, all -> 0x034e }
        r2 = "在";
        r0.<init>(r2);	 Catch:{ Throwable -> 0x030c, all -> 0x034e }
        r0 = r0.append(r1);	 Catch:{ Throwable -> 0x030c, all -> 0x034e }
        r1 = "附近";
        r0 = r0.append(r1);	 Catch:{ Throwable -> 0x030c, all -> 0x034e }
        r0 = r0.toString();	 Catch:{ Throwable -> 0x030c, all -> 0x034e }
        r13.setDescription(r0);	 Catch:{ Throwable -> 0x030c, all -> 0x034e }
    L_0x0204:
        r0 = new android.os.Bundle;	 Catch:{ Throwable -> 0x030c, all -> 0x034e }
        r0.<init>();	 Catch:{ Throwable -> 0x030c, all -> 0x034e }
        r1 = "citycode";
        r2 = r13.getCityCode();	 Catch:{ Throwable -> 0x030c, all -> 0x034e }
        r0.putString(r1, r2);	 Catch:{ Throwable -> 0x030c, all -> 0x034e }
        r1 = "desc";
        r2 = r4.toString();	 Catch:{ Throwable -> 0x030c, all -> 0x034e }
        r0.putString(r1, r2);	 Catch:{ Throwable -> 0x030c, all -> 0x034e }
        r1 = "adcode";
        r2 = r13.getAdCode();	 Catch:{ Throwable -> 0x030c, all -> 0x034e }
        r0.putString(r1, r2);	 Catch:{ Throwable -> 0x030c, all -> 0x034e }
        r13.setExtras(r0);	 Catch:{ Throwable -> 0x030c, all -> 0x034e }
        r0 = r4.toString();	 Catch:{ Throwable -> 0x030c, all -> 0x034e }
        r13.f(r0);	 Catch:{ Throwable -> 0x030c, all -> 0x034e }
        r0 = r13.getAdCode();	 Catch:{ Throwable -> 0x030c, all -> 0x034e }
        if (r0 == 0) goto L_0x0303;
    L_0x0234:
        r0 = r0.trim();	 Catch:{ Throwable -> 0x030c, all -> 0x034e }
        r0 = r0.length();	 Catch:{ Throwable -> 0x030c, all -> 0x034e }
        if (r0 <= 0) goto L_0x0303;
    L_0x023e:
        r0 = r4.toString();	 Catch:{ Throwable -> 0x030c, all -> 0x034e }
        r1 = " ";
        r2 = "";
        r0 = r0.replace(r1, r2);	 Catch:{ Throwable -> 0x030c, all -> 0x034e }
        r13.setAddress(r0);	 Catch:{ Throwable -> 0x030c, all -> 0x034e }
    L_0x024d:
        r0 = r8.get();	 Catch:{ Throwable -> 0x030c, all -> 0x034e }
        r0 = r0 & 255;
        r0 = new byte[r0];	 Catch:{ Throwable -> 0x030c, all -> 0x034e }
        r8.get(r0);	 Catch:{ Throwable -> 0x030c, all -> 0x034e }
        r0 = r8.get();	 Catch:{ Throwable -> 0x030c, all -> 0x034e }
        r1 = 1;
        if (r0 != r1) goto L_0x0268;
    L_0x025f:
        r8.getInt();	 Catch:{ Throwable -> 0x030c, all -> 0x034e }
        r8.getInt();	 Catch:{ Throwable -> 0x030c, all -> 0x034e }
        r8.getShort();	 Catch:{ Throwable -> 0x030c, all -> 0x034e }
    L_0x0268:
        r0 = r8.get();	 Catch:{ Throwable -> 0x030c, all -> 0x034e }
        r1 = 1;
        if (r0 != r1) goto L_0x0299;
    L_0x026f:
        r0 = r8.get();	 Catch:{ Throwable -> 0x030c, all -> 0x034e }
        r0 = r0 & 255;
        r0 = new byte[r0];	 Catch:{ Throwable -> 0x030c, all -> 0x034e }
        r8.get(r0);	 Catch:{ Throwable -> 0x030c, all -> 0x034e }
        r1 = new java.lang.String;	 Catch:{ Throwable -> 0x035b, all -> 0x034e }
        r2 = "UTF-8";
        r1.<init>(r0, r2);	 Catch:{ Throwable -> 0x035b, all -> 0x034e }
        r13.setBuildingId(r1);	 Catch:{ Throwable -> 0x035b, all -> 0x034e }
    L_0x0284:
        r0 = r8.get();	 Catch:{ Throwable -> 0x030c, all -> 0x034e }
        r0 = r0 & 255;
        r0 = new byte[r0];	 Catch:{ Throwable -> 0x030c, all -> 0x034e }
        r8.get(r0);	 Catch:{ Throwable -> 0x030c, all -> 0x034e }
        r1 = new java.lang.String;	 Catch:{ Throwable -> 0x0358, all -> 0x034e }
        r2 = "UTF-8";
        r1.<init>(r0, r2);	 Catch:{ Throwable -> 0x0358, all -> 0x034e }
        r13.setFloor(r1);	 Catch:{ Throwable -> 0x0358, all -> 0x034e }
    L_0x0299:
        r0 = r8.get();	 Catch:{ Throwable -> 0x030c, all -> 0x034e }
        r1 = 1;
        if (r0 != r1) goto L_0x02a9;
    L_0x02a0:
        r8.get();	 Catch:{ Throwable -> 0x030c, all -> 0x034e }
        r8.getInt();	 Catch:{ Throwable -> 0x030c, all -> 0x034e }
        r8.get();	 Catch:{ Throwable -> 0x030c, all -> 0x034e }
    L_0x02a9:
        r0 = r8.get();	 Catch:{ Throwable -> 0x030c, all -> 0x034e }
        r1 = 1;
        if (r0 != r1) goto L_0x02b7;
    L_0x02b0:
        r0 = r8.getLong();	 Catch:{ Throwable -> 0x030c, all -> 0x034e }
        r13.setTime(r0);	 Catch:{ Throwable -> 0x030c, all -> 0x034e }
    L_0x02b7:
        r0 = r8.getShort();	 Catch:{ Throwable -> 0x030c, all -> 0x034e }
        r0 = new byte[r0];	 Catch:{ Throwable -> 0x030c, all -> 0x034e }
        r8.get(r0);	 Catch:{ Throwable -> 0x030c, all -> 0x034e }
        r1 = new java.lang.String;	 Catch:{ Throwable -> 0x0355, all -> 0x034e }
        r2 = "UTF-8";
        r1.<init>(r0, r2);	 Catch:{ Throwable -> 0x0355, all -> 0x034e }
        r13.a(r1);	 Catch:{ Throwable -> 0x0355, all -> 0x034e }
    L_0x02ca:
        if (r8 == 0) goto L_0x02cf;
    L_0x02cc:
        r8.clear();
    L_0x02cf:
        r0 = r12.a;
        r0 = r0.length();
        if (r0 <= 0) goto L_0x0023;
    L_0x02d7:
        r0 = r12.a;
        r1 = 0;
        r2 = r12.a;
        r2 = r2.length();
        r0.delete(r1, r2);
        goto L_0x0023;
    L_0x02e5:
        r0 = move-exception;
        r0 = r1;
    L_0x02e7:
        r11 = r0;
        goto L_0x00c4;
    L_0x02ea:
        r0 = move-exception;
        r0 = r2;
    L_0x02ec:
        r10 = r0;
        goto L_0x00da;
    L_0x02ef:
        r0 = move-exception;
        r0 = r3;
    L_0x02f1:
        r9 = r0;
        goto L_0x00f0;
    L_0x02f4:
        r0 = move-exception;
        r0 = r4;
    L_0x02f6:
        r3 = r0;
        goto L_0x0109;
    L_0x02f9:
        r0 = move-exception;
        r0 = r5;
    L_0x02fb:
        r2 = r0;
        goto L_0x011f;
    L_0x02fe:
        r0 = move-exception;
        r0 = r6;
    L_0x0300:
        r1 = r0;
        goto L_0x0135;
    L_0x0303:
        r0 = r4.toString();	 Catch:{ Throwable -> 0x030c, all -> 0x034e }
        r13.setAddress(r0);	 Catch:{ Throwable -> 0x030c, all -> 0x034e }
        goto L_0x024d;
    L_0x030c:
        r0 = move-exception;
        r1 = r8;
    L_0x030e:
        r13 = new com.autonavi.aps.amapapi.model.AMapLocationServer;	 Catch:{ all -> 0x0350 }
        r2 = "";
        r13.<init>(r2);	 Catch:{ all -> 0x0350 }
        r2 = 5;
        r13.setErrorCode(r2);	 Catch:{ all -> 0x0350 }
        r2 = r12.a;	 Catch:{ all -> 0x0350 }
        r3 = new java.lang.StringBuilder;	 Catch:{ all -> 0x0350 }
        r4 = "parser data error:";
        r3.<init>(r4);	 Catch:{ all -> 0x0350 }
        r0 = r0.getMessage();	 Catch:{ all -> 0x0350 }
        r0 = r3.append(r0);	 Catch:{ all -> 0x0350 }
        r0 = r0.toString();	 Catch:{ all -> 0x0350 }
        r2.append(r0);	 Catch:{ all -> 0x0350 }
        r0 = 0;
        r2 = 2054; // 0x806 float:2.878E-42 double:1.015E-320;
        com.loc.db.a(r0, r2);	 Catch:{ all -> 0x0350 }
        r0 = r12.a;	 Catch:{ all -> 0x0350 }
        r0 = r0.toString();	 Catch:{ all -> 0x0350 }
        r13.setLocationDetail(r0);	 Catch:{ all -> 0x0350 }
        if (r1 == 0) goto L_0x02cf;
    L_0x0342:
        r1.clear();
        goto L_0x02cf;
    L_0x0346:
        r0 = move-exception;
        r8 = r1;
    L_0x0348:
        if (r8 == 0) goto L_0x034d;
    L_0x034a:
        r8.clear();
    L_0x034d:
        throw r0;
    L_0x034e:
        r0 = move-exception;
        goto L_0x0348;
    L_0x0350:
        r0 = move-exception;
        r8 = r1;
        goto L_0x0348;
    L_0x0353:
        r0 = move-exception;
        goto L_0x030e;
    L_0x0355:
        r0 = move-exception;
        goto L_0x02ca;
    L_0x0358:
        r0 = move-exception;
        goto L_0x0299;
    L_0x035b:
        r0 = move-exception;
        goto L_0x0284;
    L_0x035e:
        r4 = move-exception;
        goto L_0x0174;
    L_0x0361:
        r0 = move-exception;
        r0 = r7;
        goto L_0x015f;
    L_0x0365:
        r4 = move-exception;
        goto L_0x015f;
    L_0x0368:
        r0 = move-exception;
        goto L_0x014a;
    L_0x036b:
        r1 = move-exception;
        goto L_0x0300;
    L_0x036d:
        r1 = move-exception;
        goto L_0x02fb;
    L_0x036f:
        r1 = move-exception;
        goto L_0x02f6;
    L_0x0371:
        r1 = move-exception;
        goto L_0x02f1;
    L_0x0374:
        r1 = move-exception;
        goto L_0x02ec;
    L_0x0377:
        r1 = move-exception;
        goto L_0x02e7;
    L_0x037a:
        r0 = move-exception;
        goto L_0x00ae;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.loc.ct.a(com.autonavi.aps.amapapi.model.AMapLocationServer, byte[]):com.autonavi.aps.amapapi.model.AMapLocationServer");
    }

    public final AMapLocationServer a(String str, Context context, bo boVar) {
        String string;
        AMapLocationServer aMapLocationServer = new AMapLocationServer("");
        aMapLocationServer.setErrorCode(7);
        try {
            JSONObject jSONObject = new JSONObject(str);
            if (!(jSONObject.has("status") && jSONObject.has("info"))) {
                this.a.append("json is error " + str);
            }
            String string2 = jSONObject.getString("status");
            String string3 = jSONObject.getString("info");
            string = jSONObject.getString("infocode");
            if (string2.equals("0")) {
                this.a.append("auth fail:" + string3);
                db.a(boVar.d, string, string3);
            }
        } catch (Throwable th) {
            this.a.append("json exception error:" + th.getMessage());
            cw.a(th, "parser", "paseAuthFailurJson");
        }
        try {
            this.a.append("#SHA1AndPackage#").append(k.e(context));
            string = (String) ((List) boVar.b.get("gsid")).get(0);
            if (!TextUtils.isEmpty(string)) {
                this.a.append(" #gsid#").append(string);
            }
            Object obj = boVar.c;
            if (!TextUtils.isEmpty(obj)) {
                this.a.append(" #csid#" + obj);
            }
        } catch (Throwable th2) {
        }
        aMapLocationServer.setLocationDetail(this.a.toString());
        if (this.a.length() > 0) {
            this.a.delete(0, this.a.length());
        }
        return aMapLocationServer;
    }
}
