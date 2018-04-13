package cn.v6.sixrooms.bean;

import java.io.Serializable;

public class UoptionBean implements Serializable {
    private String livetype;
    private String picuser;
    private String spic;

    public String getPicuser() {
        return this.picuser;
    }

    public void setPicuser(String str) {
        this.picuser = str;
    }

    public String getSpic() {
        return this.spic;
    }

    public void setSpic(String str) {
        this.spic = str;
    }

    public String getLivetype() {
        return this.livetype;
    }

    public void setLivetype(String str) {
        this.livetype = str;
    }

    public String toString() {
        return "UoptionBean{picuser='" + this.picuser + '\'' + ", spic='" + this.spic + '\'' + ", livetype='" + this.livetype + '\'' + '}';
    }
}
