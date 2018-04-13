package com.huawei.hms.support.api.entity.push;

import com.huawei.hms.core.aidl.IMessageEntity;
import com.huawei.hms.core.aidl.a.a;
import com.huawei.hms.support.api.push.HmsPushConst;
import com.huawei.hms.support.api.push.a.a.b.c;

public class DeleteTokenReq implements IMessageEntity {
    @a
    private String pkgName;
    @a
    private String token;

    public String getPkgName() {
        return this.pkgName;
    }

    public void setPkgName(String str) {
        this.pkgName = str;
    }

    public String getToken() {
        return this.token;
    }

    public void setToken(String str) {
        this.token = str;
    }

    public String toString() {
        return getClass().getName() + " {" + HmsPushConst.NEW_LINE + "pkgName: " + this.pkgName + HmsPushConst.NEW_LINE + "token: " + c.a(this.token) + HmsPushConst.NEW_LINE + "}";
    }
}
