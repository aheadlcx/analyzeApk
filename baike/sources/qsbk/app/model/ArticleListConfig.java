package qsbk.app.model;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import java.io.Serializable;

public class ArticleListConfig implements Parcelable, Serializable {
    public static final Creator<ArticleListConfig> CREATOR = new c();
    public boolean mIsShuffle;
    public String mTitle;
    public String mUniqueName;
    public String mUrl;

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeByte(this.mIsShuffle ? (byte) 0 : (byte) 1);
        parcel.writeString(this.mTitle);
        parcel.writeString(this.mUniqueName);
        parcel.writeString(this.mUrl);
    }

    public String toString() {
        return "ArticleListConfig [mTitle=" + this.mTitle + ", mUniqueName=" + this.mUniqueName + ", mUrl=" + this.mUrl + ", mIsShuffle=" + this.mIsShuffle + "]";
    }
}
