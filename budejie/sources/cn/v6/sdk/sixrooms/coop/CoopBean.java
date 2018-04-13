package cn.v6.sdk.sixrooms.coop;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import java.io.Serializable;

public class CoopBean implements Parcelable, Serializable {
    public static final Creator<CoopBean> CREATOR = new Creator<CoopBean>() {
        public final CoopBean createFromParcel(Parcel parcel) {
            return new CoopBean(parcel);
        }

        public final CoopBean[] newArray(int i) {
            return new CoopBean[i];
        }
    };
    private String coopNick;
    private String coopUid;
    private String flag;
    private String index;
    private String time;
    private String token;
    private String user_pic;

    public CoopBean(String str, String str2, String str3, String str4, String str5) {
        this.coopUid = str;
        this.coopNick = str2;
        this.flag = str3;
        this.token = str4;
        this.time = str5;
    }

    protected CoopBean(Parcel parcel) {
        this.coopUid = parcel.readString();
        this.coopNick = parcel.readString();
        this.flag = parcel.readString();
        this.token = parcel.readString();
        this.time = parcel.readString();
    }

    public String getCoopUid() {
        return this.coopUid;
    }

    public void setCoopUid(String str) {
        this.coopUid = str;
    }

    public String getCoopNick() {
        return this.coopNick;
    }

    public void setCoopNick(String str) {
        this.coopNick = str;
    }

    public String getFlag() {
        return this.flag;
    }

    public void setFlag(String str) {
        this.flag = str;
    }

    public String getToken() {
        return this.token;
    }

    public void setToken(String str) {
        this.token = str;
    }

    public String getTime() {
        return this.time;
    }

    public void setTime(String str) {
        this.time = str;
    }

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(this.coopUid);
        parcel.writeString(this.coopNick);
        parcel.writeString(this.flag);
        parcel.writeString(this.token);
        parcel.writeString(this.time);
    }

    public String getUser_pic() {
        return this.user_pic;
    }

    public void setUser_pic(String str) {
        this.user_pic = str;
    }
}
