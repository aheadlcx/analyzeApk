package com.huawei.hms.support.api.entity.push;

import com.huawei.hms.core.aidl.IMessageEntity;
import com.huawei.hms.core.aidl.a.a;
import com.huawei.hms.support.api.push.HmsPushConst;

public class EnableNotifyReq implements IMessageEntity {
    @a
    private boolean enable;
    @a
    private String packageName;

    public String getPackageName() {
        return this.packageName;
    }

    public void setPackageName(String str) {
        this.packageName = str;
    }

    public boolean isEnable() {
        return this.enable;
    }

    public void setEnable(boolean z) {
        this.enable = z;
    }

    public String toString() {
        return getClass().getName() + " {" + HmsPushConst.NEW_LINE + "pkgName: " + this.packageName + HmsPushConst.NEW_LINE + "enable: " + this.enable + HmsPushConst.NEW_LINE + "}";
    }
}
