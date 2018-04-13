package com.zhihu.matisse;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;

public class ResultItem implements Parcelable {
    public static final Creator<ResultItem> CREATOR = new Creator<ResultItem>() {
        public ResultItem createFromParcel(Parcel parcel) {
            return new ResultItem(parcel);
        }

        public ResultItem[] newArray(int i) {
            return new ResultItem[i];
        }
    };
    public final int height;
    public final long id;
    public final String mimeType;
    public final String path;
    public final String thumbnailPath;
    public final int width;

    public ResultItem(long j, String str, String str2, String str3, int i, int i2) {
        this.id = j;
        this.path = str;
        this.mimeType = str2;
        this.thumbnailPath = str3;
        this.width = i;
        this.height = i2;
    }

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeLong(this.id);
        parcel.writeInt(this.width);
        parcel.writeInt(this.height);
        parcel.writeString(this.path);
        parcel.writeString(this.mimeType);
        parcel.writeString(this.thumbnailPath);
    }

    protected ResultItem(Parcel parcel) {
        this.id = parcel.readLong();
        this.width = parcel.readInt();
        this.height = parcel.readInt();
        this.path = parcel.readString();
        this.mimeType = parcel.readString();
        this.thumbnailPath = parcel.readString();
    }
}
