package com.sina.weibo.sdk.api;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.sina.weibo.sdk.a.d;
import java.io.File;

public class ImageObject extends BaseMediaObject {
    public static final Creator<ImageObject> CREATOR = new Creator<ImageObject>() {
        public /* synthetic */ Object createFromParcel(Parcel parcel) {
            return a(parcel);
        }

        public /* synthetic */ Object[] newArray(int i) {
            return a(i);
        }

        public ImageObject a(Parcel parcel) {
            return new ImageObject(parcel);
        }

        public ImageObject[] a(int i) {
            return new ImageObject[i];
        }
    };
    public byte[] g;
    public String h;

    public ImageObject(Parcel parcel) {
        this.g = parcel.createByteArray();
        this.h = parcel.readString();
    }

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeByteArray(this.g);
        parcel.writeString(this.h);
    }

    public boolean a() {
        if (this.g == null && this.h == null) {
            d.c("Weibo.ImageObject", "imageData and imagePath are null");
            return false;
        } else if (this.g != null && this.g.length > 2097152) {
            d.c("Weibo.ImageObject", "imageData is too large");
            return false;
        } else if (this.h == null || this.h.length() <= 512) {
            if (this.h != null) {
                File file = new File(this.h);
                try {
                    if (!file.exists() || file.length() == 0 || file.length() > 10485760) {
                        d.c("Weibo.ImageObject", "checkArgs fail, image content is too large or not exists");
                        return false;
                    }
                } catch (SecurityException e) {
                    d.c("Weibo.ImageObject", "checkArgs fail, image content is too large or not exists");
                    return false;
                }
            }
            return true;
        } else {
            d.c("Weibo.ImageObject", "imagePath is too length");
            return false;
        }
    }

    protected BaseMediaObject a(String str) {
        return this;
    }

    protected String b() {
        return "";
    }
}
