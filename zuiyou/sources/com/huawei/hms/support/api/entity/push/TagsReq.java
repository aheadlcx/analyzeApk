package com.huawei.hms.support.api.entity.push;

import com.huawei.hms.core.aidl.IMessageEntity;
import com.huawei.hms.core.aidl.a.a;
import com.huawei.hms.support.api.push.HmsPushConst;
import com.huawei.hms.support.api.push.a.a.b.c;

public class TagsReq implements IMessageEntity {
    @a
    private String apkVersion;
    @a
    private String content = "";
    @a
    private long cycle = 0;
    @a
    private int operType;
    @a
    private String pkgName;
    @a
    private int plusType;
    @a
    private String token;

    public String getContent() {
        return this.content;
    }

    public void setContent(String str) {
        this.content = str;
    }

    public long getCycle() {
        return this.cycle;
    }

    public void setCycle(long j) {
        this.cycle = j;
    }

    public int getOperType() {
        return this.operType;
    }

    public void setOperType(int i) {
        this.operType = i;
    }

    public int getPlusType() {
        return this.plusType;
    }

    public void setPlusType(int i) {
        this.plusType = i;
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

    public String getApkVersion() {
        return this.apkVersion;
    }

    public void setApkVersion(String str) {
        this.apkVersion = str;
    }

    public String toString() {
        return getClass().getName() + " {" + HmsPushConst.NEW_LINE + "cycle: " + this.cycle + HmsPushConst.NEW_LINE + "operType: " + this.operType + HmsPushConst.NEW_LINE + "plusType: " + this.plusType + HmsPushConst.NEW_LINE + "token: " + c.a(this.token) + HmsPushConst.NEW_LINE + "pkgName: " + this.pkgName + HmsPushConst.NEW_LINE + "apkVersion: " + this.apkVersion + HmsPushConst.NEW_LINE + "content: " + this.content + HmsPushConst.NEW_LINE + HmsPushConst.NEW_LINE + "}";
    }
}
