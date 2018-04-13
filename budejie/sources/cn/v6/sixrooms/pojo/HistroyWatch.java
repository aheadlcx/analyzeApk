package cn.v6.sixrooms.pojo;

import java.io.Serializable;

public class HistroyWatch implements Serializable {
    private String _id;
    private long date;
    private String level;
    private String pic;
    private String rid;
    private String uid;
    private String username;

    public HistroyWatch(String str, String str2, String str3, String str4, String str5, long j, String str6) {
        this._id = str;
        this.rid = str2;
        this.pic = str3;
        this.username = str4;
        this.level = str5;
        this.date = j;
        this.uid = str6;
    }

    public String get_id() {
        return this._id;
    }

    public void set_id(String str) {
        this._id = str;
    }

    public String getRid() {
        return this.rid;
    }

    public void setRid(String str) {
        this.rid = str;
    }

    public String getPic() {
        return this.pic;
    }

    public void setPic(String str) {
        this.pic = str;
    }

    public String getUsername() {
        return this.username;
    }

    public void setUsername(String str) {
        this.username = str;
    }

    public String getLevel() {
        return this.level;
    }

    public void setLevel(String str) {
        this.level = str;
    }

    public long getDate() {
        return this.date;
    }

    public void setDate(long j) {
        this.date = j;
    }

    public String getUid() {
        return this.uid;
    }

    public void setUid(String str) {
        this.uid = str;
    }

    public String toString() {
        return "HistroyWatch{_id='" + this._id + '\'' + ", rid='" + this.rid + '\'' + ", pic='" + this.pic + '\'' + ", username='" + this.username + '\'' + ", level='" + this.level + '\'' + ", uid='" + this.uid + '\'' + ", date=" + this.date + '}';
    }
}
