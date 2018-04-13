package com.loc;

import android.text.TextUtils;
import com.amap.api.location.AMapLocation;
import com.autonavi.aps.amapapi.model.AMapLocationServer;
import com.baidu.mobstat.Config;

public final class by {
    AMapLocationServer a = null;
    long b = 0;
    long c = 0;
    int d = 0;
    long e = 0;
    AMapLocation f = null;
    long g = 0;
    private boolean h = true;

    private AMapLocationServer b(AMapLocationServer aMapLocationServer) {
        if (de.a(aMapLocationServer)) {
            if (!this.h || !cv.b(aMapLocationServer.getTime())) {
                aMapLocationServer.setLocationType(this.d);
            } else if (aMapLocationServer.getLocationType() == 5 || aMapLocationServer.getLocationType() == 6) {
                aMapLocationServer.setLocationType(2);
            }
        }
        return aMapLocationServer;
    }

    public final AMapLocation a(AMapLocation aMapLocation) {
        if (!de.a(aMapLocation)) {
            return aMapLocation;
        }
        long b = de.b() - this.g;
        this.g = de.b();
        if (b > Config.BPLUS_DELAY_TIME) {
            return aMapLocation;
        }
        if (this.f == null) {
            this.f = aMapLocation;
            return aMapLocation;
        } else if (1 != this.f.getLocationType() && !"gps".equalsIgnoreCase(this.f.getProvider())) {
            this.f = aMapLocation;
            return aMapLocation;
        } else if (this.f.getAltitude() == aMapLocation.getAltitude() && this.f.getLongitude() == aMapLocation.getLongitude()) {
            this.f = aMapLocation;
            return aMapLocation;
        } else {
            b = Math.abs(aMapLocation.getTime() - this.f.getTime());
            if (i.MIN_UPLOAD_INTERVAL < b) {
                this.f = aMapLocation;
                return aMapLocation;
            }
            if (de.a(aMapLocation, this.f) > (((((float) b) * (this.f.getSpeed() + aMapLocation.getSpeed())) / 2000.0f) + (2.0f * (this.f.getAccuracy() + aMapLocation.getAccuracy()))) + 3000.0f) {
                return this.f;
            }
            this.f = aMapLocation;
            return aMapLocation;
        }
    }

    public final AMapLocationServer a(AMapLocationServer aMapLocationServer) {
        if (de.b() - this.e > i.MIN_UPLOAD_INTERVAL) {
            this.a = aMapLocationServer;
            this.e = de.b();
            return this.a;
        }
        this.e = de.b();
        if (!de.a(this.a) || !de.a(aMapLocationServer)) {
            this.b = de.b();
            this.a = aMapLocationServer;
            return this.a;
        } else if (aMapLocationServer.getTime() == this.a.getTime() && aMapLocationServer.getAccuracy() < 300.0f) {
            return aMapLocationServer;
        } else {
            if (aMapLocationServer.getProvider().equals("gps")) {
                this.b = de.b();
                this.a = aMapLocationServer;
                return this.a;
            } else if (aMapLocationServer.c() != this.a.c()) {
                this.b = de.b();
                this.a = aMapLocationServer;
                return this.a;
            } else if (aMapLocationServer.getBuildingId().equals(this.a.getBuildingId()) || TextUtils.isEmpty(aMapLocationServer.getBuildingId())) {
                this.d = aMapLocationServer.getLocationType();
                float a = de.a((AMapLocation) aMapLocationServer, this.a);
                float accuracy = this.a.getAccuracy();
                float accuracy2 = aMapLocationServer.getAccuracy();
                float f = accuracy2 - accuracy;
                long b = de.b();
                long j = b - this.b;
                if ((accuracy < 101.0f && accuracy2 > 299.0f) || (accuracy > 299.0f && accuracy2 > 299.0f)) {
                    if (this.c == 0) {
                        this.c = b;
                    } else if (b - this.c > i.MIN_UPLOAD_INTERVAL) {
                        this.b = b;
                        this.a = aMapLocationServer;
                        this.c = 0;
                        return this.a;
                    }
                    this.a = b(this.a);
                    return this.a;
                } else if (accuracy2 >= 100.0f || accuracy <= 299.0f) {
                    if (accuracy2 <= 299.0f) {
                        this.c = 0;
                    }
                    if (a >= 10.0f || ((double) a) <= 0.1d || accuracy2 <= 5.0f) {
                        if (f < 300.0f) {
                            this.b = de.b();
                            this.a = aMapLocationServer;
                            return this.a;
                        } else if (j >= i.MIN_UPLOAD_INTERVAL) {
                            this.b = de.b();
                            this.a = aMapLocationServer;
                            return this.a;
                        } else {
                            this.a = b(this.a);
                            return this.a;
                        }
                    } else if (f >= -300.0f) {
                        this.a = b(this.a);
                        return this.a;
                    } else if (accuracy / accuracy2 >= 2.0f) {
                        this.b = b;
                        this.a = aMapLocationServer;
                        return this.a;
                    } else {
                        this.a = b(this.a);
                        return this.a;
                    }
                } else {
                    this.b = b;
                    this.a = aMapLocationServer;
                    this.c = 0;
                    return this.a;
                }
            } else {
                this.b = de.b();
                this.a = aMapLocationServer;
                return this.a;
            }
        }
    }

    public final void a() {
        this.a = null;
        this.b = 0;
        this.c = 0;
        this.f = null;
        this.g = 0;
    }

    public final void a(boolean z) {
        this.h = z;
    }
}
