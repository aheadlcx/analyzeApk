package com.huawei.hms.support.api.entity.push;

import com.huawei.hms.core.aidl.IMessageEntity;
import com.huawei.hms.core.aidl.a.a;
import com.huawei.hms.support.api.push.HmsPushConst;

public class TokenReq implements IMessageEntity {
    @a
    private boolean firstTime;
    @a
    private String packageName;

    public boolean isFirstTime() {
        return this.firstTime;
    }

    public void setFirstTime(boolean z) {
        this.firstTime = z;
    }

    public String getPackageName() {
        return this.packageName;
    }

    public void setPackageName(String str) {
        this.packageName = str;
    }

    public String toString() {
        return getClass().getName() + " {" + HmsPushConst.NEW_LINE + "pkgName: " + this.packageName + HmsPushConst.NEW_LINE + "isFirstTime: " + this.firstTime + HmsPushConst.NEW_LINE + "}";
    }
}
