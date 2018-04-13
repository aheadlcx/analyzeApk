package com.xiaomi.mipush.sdk;

import com.xiaomi.push.service.module.PushChannelRegion;

public class PushConfiguration {
    private boolean mGeoEnable = false;
    private boolean mOpenHmsPush = false;
    private PushChannelRegion mRegion = PushChannelRegion.China;

    public boolean getGeoEnable() {
        return this.mGeoEnable;
    }

    public boolean getOpenHmsPush() {
        return this.mOpenHmsPush;
    }

    public PushChannelRegion getRegion() {
        return this.mRegion;
    }

    public String toString() {
        StringBuffer stringBuffer = new StringBuffer("PushConfiguration{");
        stringBuffer.append("Region:");
        if (this.mRegion == null) {
            stringBuffer.append("null");
        } else {
            stringBuffer.append(this.mRegion.name());
        }
        stringBuffer.append('}');
        return stringBuffer.toString();
    }
}
