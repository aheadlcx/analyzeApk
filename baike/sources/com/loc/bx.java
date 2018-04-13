package com.loc;

import com.amap.api.location.AMapLocation;
import com.xiaomi.mipush.sdk.Constants;

public final class bx {
    double a;
    double b;
    long c;
    float d;
    float e;
    int f;
    String g;

    public bx(AMapLocation aMapLocation, int i) {
        this.a = aMapLocation.getLatitude();
        this.b = aMapLocation.getLongitude();
        this.c = aMapLocation.getTime();
        this.d = aMapLocation.getAccuracy();
        this.e = aMapLocation.getSpeed();
        this.f = i;
        this.g = aMapLocation.getProvider();
    }

    public final boolean equals(Object obj) {
        try {
            if (!(obj instanceof bx)) {
                return false;
            }
            bx bxVar = (bx) obj;
            return this.a == bxVar.a && this.b == bxVar.b && this.f == bxVar.f;
        } catch (Throwable th) {
            return false;
        }
    }

    public final int hashCode() {
        return (Double.valueOf(this.a).hashCode() + Double.valueOf(this.b).hashCode()) + this.f;
    }

    public final String toString() {
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append(this.a);
        stringBuffer.append(Constants.ACCEPT_TIME_SEPARATOR_SP);
        stringBuffer.append(this.b);
        stringBuffer.append(Constants.ACCEPT_TIME_SEPARATOR_SP);
        stringBuffer.append(this.d);
        stringBuffer.append(Constants.ACCEPT_TIME_SEPARATOR_SP);
        stringBuffer.append(this.c);
        stringBuffer.append(Constants.ACCEPT_TIME_SEPARATOR_SP);
        stringBuffer.append(this.e);
        stringBuffer.append(Constants.ACCEPT_TIME_SEPARATOR_SP);
        stringBuffer.append(this.f);
        stringBuffer.append(Constants.ACCEPT_TIME_SEPARATOR_SP);
        stringBuffer.append(this.g);
        return stringBuffer.toString();
    }
}
