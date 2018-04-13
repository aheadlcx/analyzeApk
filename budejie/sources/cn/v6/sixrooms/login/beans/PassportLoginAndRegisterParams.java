package cn.v6.sixrooms.login.beans;

import android.text.TextUtils;
import cn.v6.sixrooms.encrypt.MyEncrypt;
import cn.v6.sixrooms.utils.AppInfoUtils;
import java.io.Serializable;

public class PassportLoginAndRegisterParams implements Serializable {
    private static final long serialVersionUID = 1;
    private String a;
    private String b;
    private String c;
    private String d;
    private String e;
    private String f;
    private String g;
    private String h;
    private String i;

    public String getUsername() {
        return this.a;
    }

    public void setUsername(String str) {
        this.a = str;
    }

    public String getPck() {
        return this.c;
    }

    public void setPck(String str) {
        this.c = str;
    }

    public String getCode() {
        return this.d;
    }

    public void setCode(String str) {
        this.d = str;
    }

    public String getChallenge() {
        return this.e;
    }

    public void setChallenge(String str) {
        this.e = str;
    }

    public String getValidate() {
        return this.f;
    }

    public void setValidate(String str) {
        this.f = str;
    }

    public String getSeccode() {
        return this.g;
    }

    public void setSeccode(String str) {
        this.g = str;
    }

    public String getPassword(boolean z) {
        if (!z) {
            return this.b;
        }
        if (TextUtils.isEmpty(this.d) || TextUtils.isEmpty(this.b)) {
            return this.b;
        }
        return MyEncrypt.instance().getPassword(this.b, AppInfoUtils.getUUID(), this.d);
    }

    public void setPassword(String str) {
        this.b = str;
    }

    public String toString() {
        return "PassportLoginParams [username=" + this.a + ", password=" + this.b + ", pck=" + this.c + ", code=" + this.d + "]";
    }

    public String getPhoneNumber() {
        return this.h;
    }

    public void setPhoneNumber(String str) {
        this.h = str;
    }

    public String getIdentifyingCode() {
        return this.i;
    }

    public void setIdentifyingCode(String str) {
        this.i = str;
    }
}
