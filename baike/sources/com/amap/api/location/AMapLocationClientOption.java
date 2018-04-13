package com.amap.api.location;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import com.loc.cw;
import org.eclipse.paho.client.mqttv3.MqttTopic;

public class AMapLocationClientOption implements Parcelable, Cloneable {
    public static final Creator<AMapLocationClientOption> CREATOR = new b();
    static String a = "";
    private static AMapLocationProtocol j = AMapLocationProtocol.HTTP;
    private long b = 2000;
    private long c = ((long) cw.e);
    private boolean d = false;
    private boolean e = true;
    private boolean f = true;
    private boolean g = true;
    private boolean h = true;
    private AMapLocationMode i = AMapLocationMode.Hight_Accuracy;
    private boolean k = false;
    private boolean l = false;
    private boolean m = true;
    private boolean n = true;
    private boolean o = false;
    private boolean p = false;
    private boolean q = true;

    public enum AMapLocationMode {
        Battery_Saving,
        Device_Sensors,
        Hight_Accuracy
    }

    public enum AMapLocationProtocol {
        HTTP(0),
        HTTPS(1);
        
        private int a;

        private AMapLocationProtocol(int i) {
            this.a = i;
        }

        public final int getValue() {
            return this.a;
        }
    }

    protected AMapLocationClientOption(Parcel parcel) {
        boolean z = true;
        this.b = parcel.readLong();
        this.c = parcel.readLong();
        this.d = parcel.readByte() != (byte) 0;
        this.e = parcel.readByte() != (byte) 0;
        this.f = parcel.readByte() != (byte) 0;
        this.g = parcel.readByte() != (byte) 0;
        this.h = parcel.readByte() != (byte) 0;
        int readInt = parcel.readInt();
        this.i = readInt == -1 ? AMapLocationMode.Hight_Accuracy : AMapLocationMode.values()[readInt];
        this.k = parcel.readByte() != (byte) 0;
        this.l = parcel.readByte() != (byte) 0;
        this.m = parcel.readByte() != (byte) 0;
        this.n = parcel.readByte() != (byte) 0;
        this.o = parcel.readByte() != (byte) 0;
        this.p = parcel.readByte() != (byte) 0;
        if (parcel.readByte() == (byte) 0) {
            z = false;
        }
        this.q = z;
    }

    public static String getAPIKEY() {
        return a;
    }

    public static void setLocationProtocol(AMapLocationProtocol aMapLocationProtocol) {
        j = aMapLocationProtocol;
    }

    public AMapLocationClientOption clone() {
        try {
            super.clone();
        } catch (Throwable th) {
            th.printStackTrace();
        }
        AMapLocationClientOption aMapLocationClientOption = new AMapLocationClientOption();
        aMapLocationClientOption.b = this.b;
        aMapLocationClientOption.d = this.d;
        aMapLocationClientOption.i = this.i;
        aMapLocationClientOption.e = this.e;
        aMapLocationClientOption.k = this.k;
        aMapLocationClientOption.l = this.l;
        aMapLocationClientOption.f = this.f;
        aMapLocationClientOption.g = this.g;
        aMapLocationClientOption.c = this.c;
        aMapLocationClientOption.m = this.m;
        aMapLocationClientOption.n = this.n;
        aMapLocationClientOption.o = this.o;
        aMapLocationClientOption.p = isSensorEnable();
        aMapLocationClientOption.q = isWifiScan();
        return aMapLocationClientOption;
    }

    public int describeContents() {
        return 0;
    }

    public long getHttpTimeOut() {
        return this.c;
    }

    public long getInterval() {
        return this.b;
    }

    public AMapLocationMode getLocationMode() {
        return this.i;
    }

    public AMapLocationProtocol getLocationProtocol() {
        return j;
    }

    public boolean isGpsFirst() {
        return this.l;
    }

    public boolean isKillProcess() {
        return this.k;
    }

    public boolean isLocationCacheEnable() {
        return this.n;
    }

    public boolean isMockEnable() {
        return this.e;
    }

    public boolean isNeedAddress() {
        return this.f;
    }

    public boolean isOffset() {
        return this.m;
    }

    public boolean isOnceLocation() {
        return this.o ? true : this.d;
    }

    public boolean isOnceLocationLatest() {
        return this.o;
    }

    public boolean isSensorEnable() {
        return this.p;
    }

    public boolean isWifiActiveScan() {
        return this.g;
    }

    public boolean isWifiScan() {
        return this.q;
    }

    public AMapLocationClientOption setGpsFirst(boolean z) {
        this.l = z;
        return this;
    }

    public void setHttpTimeOut(long j) {
        this.c = j;
    }

    public AMapLocationClientOption setInterval(long j) {
        if (j <= 800) {
            j = 800;
        }
        this.b = j;
        return this;
    }

    public AMapLocationClientOption setKillProcess(boolean z) {
        this.k = z;
        return this;
    }

    public void setLocationCacheEnable(boolean z) {
        this.n = z;
    }

    public AMapLocationClientOption setLocationMode(AMapLocationMode aMapLocationMode) {
        this.i = aMapLocationMode;
        return this;
    }

    public void setMockEnable(boolean z) {
        this.e = z;
    }

    public AMapLocationClientOption setNeedAddress(boolean z) {
        this.f = z;
        return this;
    }

    public AMapLocationClientOption setOffset(boolean z) {
        this.m = z;
        return this;
    }

    public AMapLocationClientOption setOnceLocation(boolean z) {
        this.d = z;
        return this;
    }

    public void setOnceLocationLatest(boolean z) {
        this.o = z;
    }

    public void setSensorEnable(boolean z) {
        this.p = z;
    }

    public void setWifiActiveScan(boolean z) {
        this.g = z;
        this.h = z;
    }

    public void setWifiScan(boolean z) {
        this.q = z;
        if (this.q) {
            this.g = this.h;
        } else {
            this.g = false;
        }
    }

    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("interval:").append(String.valueOf(this.b)).append(MqttTopic.MULTI_LEVEL_WILDCARD);
        stringBuilder.append("isOnceLocation:").append(String.valueOf(this.d)).append(MqttTopic.MULTI_LEVEL_WILDCARD);
        stringBuilder.append("locationMode:").append(String.valueOf(this.i)).append(MqttTopic.MULTI_LEVEL_WILDCARD);
        stringBuilder.append("isMockEnable:").append(String.valueOf(this.e)).append(MqttTopic.MULTI_LEVEL_WILDCARD);
        stringBuilder.append("isKillProcess:").append(String.valueOf(this.k)).append(MqttTopic.MULTI_LEVEL_WILDCARD);
        stringBuilder.append("isGpsFirst:").append(String.valueOf(this.l)).append(MqttTopic.MULTI_LEVEL_WILDCARD);
        stringBuilder.append("isNeedAddress:").append(String.valueOf(this.f)).append(MqttTopic.MULTI_LEVEL_WILDCARD);
        stringBuilder.append("isWifiActiveScan:").append(String.valueOf(this.g)).append(MqttTopic.MULTI_LEVEL_WILDCARD);
        stringBuilder.append("httpTimeOut:").append(String.valueOf(this.c)).append(MqttTopic.MULTI_LEVEL_WILDCARD);
        stringBuilder.append("isOffset:").append(String.valueOf(this.m)).append(MqttTopic.MULTI_LEVEL_WILDCARD);
        stringBuilder.append("isLocationCacheEnable:").append(String.valueOf(this.n)).append(MqttTopic.MULTI_LEVEL_WILDCARD);
        stringBuilder.append("isLocationCacheEnable:").append(String.valueOf(this.n)).append(MqttTopic.MULTI_LEVEL_WILDCARD);
        stringBuilder.append("isOnceLocationLatest:").append(String.valueOf(this.o)).append(MqttTopic.MULTI_LEVEL_WILDCARD);
        stringBuilder.append("sensorEnable:").append(String.valueOf(this.p)).append(MqttTopic.MULTI_LEVEL_WILDCARD);
        return stringBuilder.toString();
    }

    public void writeToParcel(Parcel parcel, int i) {
        byte b = (byte) 1;
        parcel.writeLong(this.b);
        parcel.writeLong(this.c);
        parcel.writeByte(this.d ? (byte) 1 : (byte) 0);
        parcel.writeByte(this.e ? (byte) 1 : (byte) 0);
        parcel.writeByte(this.f ? (byte) 1 : (byte) 0);
        parcel.writeByte(this.g ? (byte) 1 : (byte) 0);
        parcel.writeByte(this.h ? (byte) 1 : (byte) 0);
        parcel.writeInt(this.i == null ? -1 : this.i.ordinal());
        parcel.writeByte(this.k ? (byte) 1 : (byte) 0);
        parcel.writeByte(this.l ? (byte) 1 : (byte) 0);
        parcel.writeByte(this.m ? (byte) 1 : (byte) 0);
        parcel.writeByte(this.n ? (byte) 1 : (byte) 0);
        parcel.writeByte(this.o ? (byte) 1 : (byte) 0);
        parcel.writeByte(this.p ? (byte) 1 : (byte) 0);
        if (!this.q) {
            b = (byte) 0;
        }
        parcel.writeByte(b);
    }
}
