package com.alibaba.mtl.log.sign;

import com.alibaba.mtl.log.e.i;
import com.alibaba.mtl.log.e.j;

public class BaseRequestAuth implements IRequestAuth {
    private String Y = null;
    private String b = null;

    public String getAppkey() {
        return this.b;
    }

    public String getAppSecret() {
        return this.Y;
    }

    public BaseRequestAuth(String str, String str2) {
        this.b = str;
        this.Y = str2;
    }

    public String getSign(String str) {
        if (this.b == null || this.Y == null) {
            i.a("BaseRequestAuth", "There is no appkey,please check it!");
            return null;
        } else if (str != null) {
            return j.a(j.a((str + this.Y).getBytes()));
        } else {
            return null;
        }
    }
}
