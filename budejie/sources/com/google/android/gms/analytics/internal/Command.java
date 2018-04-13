package com.google.android.gms.analytics.internal;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;

public class Command implements Parcelable {
    public static final String APPEND_CACHE_BUSTER = "appendCacheBuster";
    public static final String APPEND_QUEUE_TIME = "appendQueueTime";
    public static final String APPEND_VERSION = "appendVersion";
    public static final Creator<Command> CREATOR = new Creator<Command>() {
        public Command createFromParcel(Parcel parcel) {
            return new Command(parcel);
        }

        public Command[] newArray(int i) {
            return new Command[i];
        }
    };
    private String id;
    private String urlParam;
    private String value;

    public Command(String str, String str2, String str3) {
        this.id = str;
        this.urlParam = str2;
        this.value = str3;
    }

    public String getId() {
        return this.id;
    }

    public String getUrlParam() {
        return this.urlParam;
    }

    public String getValue() {
        return this.value;
    }

    Command(Parcel parcel) {
        readFromParcel(parcel);
    }

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(this.id);
        parcel.writeString(this.urlParam);
        parcel.writeString(this.value);
    }

    private void readFromParcel(Parcel parcel) {
        this.id = parcel.readString();
        this.urlParam = parcel.readString();
        this.value = parcel.readString();
    }
}
