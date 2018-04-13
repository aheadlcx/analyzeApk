package com.baidu.mobads.g;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import com.baidu.mobstat.Config;
import org.json.JSONException;
import org.json.JSONObject;

public class e implements Parcelable {
    public static final Creator<e> a = new f();
    private JSONObject b;
    private double c;
    private String d;
    private String e;
    private String f;
    private int g;
    private int h;

    public e(String str) {
        int i = 1;
        try {
            this.b = new JSONObject(str);
            this.c = this.b.getDouble("version");
            this.d = this.b.getString("url");
            this.e = this.b.getString(Config.SIGN);
            this.h = 1;
            this.f = "";
            this.g = 0;
        } catch (JSONException e) {
            this.h = 0;
        }
        if (c() == null) {
            i = 0;
        }
        this.h = i;
    }

    public e(e eVar, String str, Boolean bool) {
        int i;
        int i2 = 1;
        this.c = eVar.b();
        this.d = eVar.c();
        this.e = eVar.d();
        if (eVar.a().booleanValue()) {
            i = 1;
        } else {
            i = 0;
        }
        this.h = i;
        this.f = str;
        if (!bool.booleanValue()) {
            i2 = 0;
        }
        this.g = i2;
    }

    private e(Parcel parcel) {
        this.e = parcel.readString();
        this.h = parcel.readInt();
        this.d = parcel.readString();
        this.c = parcel.readDouble();
        this.f = parcel.readString();
        this.g = parcel.readInt();
    }

    public Boolean a() {
        boolean z = true;
        if (this.h != 1) {
            z = false;
        }
        return Boolean.valueOf(z);
    }

    public double b() {
        return this.c;
    }

    public String c() {
        return this.d;
    }

    public String d() {
        return this.e;
    }

    public String e() {
        return this.f;
    }

    public String toString() {
        return this.b.toString();
    }

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(this.e);
        parcel.writeInt(this.h);
        parcel.writeString(this.d);
        parcel.writeDouble(this.c);
        parcel.writeString(this.f);
        parcel.writeInt(this.g);
    }
}
