package com.sina.weibo.sdk.api;

import android.os.Parcel;
import android.os.Parcelable.Creator;

public class CmdObject extends BaseMediaObject {
    public static final String CMD_HOME = "home";
    public static final Creator<CmdObject> CREATOR = new a();
    public String cmd;

    public CmdObject(Parcel parcel) {
        this.cmd = parcel.readString();
    }

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(this.cmd);
    }

    public boolean checkArgs() {
        if (this.cmd == null || this.cmd.length() == 0 || this.cmd.length() > 1024) {
            return false;
        }
        return true;
    }

    public int getObjType() {
        return 7;
    }

    protected BaseMediaObject a(String str) {
        return this;
    }

    protected String a() {
        return "";
    }
}