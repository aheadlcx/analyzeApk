package cn.tatagou.sdk.pojo;

import android.net.Uri;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import android.util.Log;
import cn.tatagou.sdk.util.p;

public class TtgUrl implements Parcelable {
    public static final Creator<TtgUrl> CREATOR = new TtgUrl$1();
    private String host;
    private String ttgUrl;

    public TtgUrl(String str) {
        this.ttgUrl = str;
    }

    protected TtgUrl(Parcel parcel) {
        this.ttgUrl = parcel.readString();
        this.host = parcel.readString();
    }

    public Uri parseUrl() {
        try {
            if (p.isEmpty(this.ttgUrl)) {
                return null;
            }
            Uri parse = Uri.parse(this.ttgUrl);
            this.host = parse.getHost();
            return parse;
        } catch (Throwable e) {
            Log.e("TTG", "parseUrl: " + e.getMessage(), e);
            return null;
        }
    }

    public String getHost() {
        return this.host;
    }

    public void setHost(String str) {
        this.host = str;
    }

    public String getTtgUrl() {
        return this.ttgUrl;
    }

    public void setTtgUrl(String str) {
        this.ttgUrl = str;
    }

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(this.ttgUrl);
        parcel.writeString(this.host);
    }
}
