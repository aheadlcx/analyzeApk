package cn.v6.sixrooms.bean;

import android.text.TextUtils;
import cn.v6.sixrooms.encrypt.MyEncrypt;
import cn.v6.sixrooms.utils.AppInfoUtils;
import java.io.Serializable;

public class PassportLoginAndRegisterParams implements Serializable {
    private static final long serialVersionUID = 1;
    private String code;
    private String password;
    private String pck;
    private String username;

    public String getUsername() {
        return this.username;
    }

    public void setUsername(String str) {
        this.username = str;
    }

    public String getPck() {
        return this.pck;
    }

    public void setPck(String str) {
        this.pck = str;
    }

    public String getCode() {
        return this.code;
    }

    public void setCode(String str) {
        this.code = str;
    }

    public String getPassword(boolean z) {
        if (!z) {
            return this.password;
        }
        if (TextUtils.isEmpty(this.code) || TextUtils.isEmpty(this.password)) {
            return this.password;
        }
        return MyEncrypt.instance().getPassword(this.password, AppInfoUtils.getUUID(), this.code);
    }

    public void setPassword(String str) {
        this.password = str;
    }

    public String toString() {
        return "PassportLoginParams [username=" + this.username + ", password=" + this.password + ", pck=" + this.pck + ", code=" + this.code + "]";
    }
}
