package com.huawei.hms.support.api.entity.push;

import com.huawei.hms.core.aidl.IMessageEntity;
import com.huawei.hms.core.aidl.a.a;
import com.huawei.hms.support.api.push.HmsPushConst;
import com.huawei.hms.support.api.push.a.a.b.c;

public class AgreementReq implements IMessageEntity {
    @a
    private boolean firstTime;
    @a
    private String pkgName;
    @a
    private String token = "";

    public boolean isFirstTime() {
        return this.firstTime;
    }

    public void setFirstTime(boolean z) {
        this.firstTime = z;
    }

    public String getToken() {
        return this.token;
    }

    public void setToken(String str) {
        this.token = str;
    }

    public String getPkgName() {
        return this.pkgName;
    }

    public void setPkgName(String str) {
        this.pkgName = str;
    }

    public String toString() {
        return getClass().getName() + " {" + HmsPushConst.NEW_LINE + "isFirstTime: " + this.firstTime + HmsPushConst.NEW_LINE + "pkgName: " + this.pkgName + HmsPushConst.NEW_LINE + "token: " + c.a(this.token) + HmsPushConst.NEW_LINE + "}";
    }
}
