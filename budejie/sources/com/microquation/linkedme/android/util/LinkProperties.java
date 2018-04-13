package com.microquation.linkedme.android.util;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import android.support.v4.util.ArrayMap;
import android.text.TextUtils;
import com.microquation.linkedme.android.a;
import com.microquation.linkedme.android.f.b;
import com.microquation.linkedme.android.util.c.f;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import org.json.JSONArray;
import org.json.JSONObject;

public class LinkProperties implements Parcelable {
    public static final Creator CREATOR = new LinkProperties$1();
    private final ArrayList<String> a;
    private String b;
    private String c;
    private String d;
    private int e;
    private final ArrayMap<String, String> f;
    private String g;
    private String h;
    private boolean i;
    private String j;

    public LinkProperties() {
        this.a = new ArrayList();
        this.b = "Share";
        this.f = new ArrayMap();
        this.c = "";
        this.d = "";
        this.e = 0;
        this.g = "";
        this.h = "";
        this.i = false;
        this.j = "";
    }

    private LinkProperties(Parcel parcel) {
        int i = 0;
        this();
        this.b = parcel.readString();
        this.c = parcel.readString();
        this.d = parcel.readString();
        this.g = parcel.readString();
        this.e = parcel.readInt();
        this.h = parcel.readString();
        this.i = parcel.readByte() != (byte) 0;
        this.j = parcel.readString();
        this.a.addAll((ArrayList) parcel.readSerializable());
        int readInt = parcel.readInt();
        while (i < readInt) {
            this.f.put(parcel.readString(), parcel.readString());
            i++;
        }
    }

    public static LinkProperties f() {
        a a = a.a();
        if (a == null || a.d() == null) {
            return null;
        }
        JSONObject d = a.d();
        b.a(a.a, "开始解析用户数据：" + d);
        try {
            if (!d.optBoolean(c.a.p.a(), false)) {
                return null;
            }
            JSONObject optJSONObject = d.optJSONObject(c.a.A.a());
            LinkProperties linkProperties = new LinkProperties();
            try {
                JSONArray optJSONArray = optJSONObject.optJSONArray(f.e.a());
                if (optJSONArray != null && optJSONArray.length() > 0) {
                    linkProperties.d(optJSONArray.optString(0));
                }
                optJSONArray = optJSONObject.optJSONArray(f.f.a());
                if (optJSONArray != null && optJSONArray.length() > 0) {
                    linkProperties.b(optJSONArray.optString(0));
                }
                optJSONArray = optJSONObject.optJSONArray(f.g.a());
                if (optJSONArray != null && optJSONArray.length() > 0) {
                    linkProperties.c(optJSONArray.optString(0));
                }
                Object optString = optJSONObject.optString(f.h.a());
                if (!TextUtils.isEmpty(optString)) {
                    linkProperties.e(optString);
                }
                linkProperties.a(optJSONObject.optBoolean(f.i.a()));
                linkProperties.f(optJSONObject.optString(f.j.a()));
                linkProperties.a(optJSONObject.optInt(f.d.a()));
                JSONArray optJSONArray2 = optJSONObject.optJSONArray(f.a.a());
                if (optJSONArray2 != null && optJSONArray2.length() > 0) {
                    int length = optJSONArray2.length();
                    for (int i = 0; i < length; i++) {
                        linkProperties.a(optJSONArray2.optString(i));
                    }
                }
                JSONObject optJSONObject2 = optJSONObject.optJSONObject(c.a.aB.a());
                if (optJSONObject2 != null) {
                    Iterator keys = optJSONObject2.keys();
                    while (keys.hasNext()) {
                        String str = (String) keys.next();
                        linkProperties.a(str, optJSONObject2.optString(str));
                    }
                }
                return linkProperties;
            } catch (Exception e) {
                return linkProperties;
            }
        } catch (Exception e2) {
            return null;
        }
    }

    public LinkProperties a(int i) {
        this.e = i;
        return this;
    }

    public LinkProperties a(String str) {
        this.a.add(str);
        return this;
    }

    public LinkProperties a(String str, String str2) {
        this.f.put(str, str2);
        return this;
    }

    public LinkProperties a(boolean z) {
        this.i = z;
        return this;
    }

    public HashMap<String, String> a() {
        HashMap<String, String> hashMap = new HashMap();
        hashMap.putAll(this.f);
        return hashMap;
    }

    public ArrayMap<String, String> b() {
        return this.f;
    }

    public LinkProperties b(String str) {
        this.b = str;
        return this;
    }

    public LinkProperties c(String str) {
        this.d = str;
        return this;
    }

    public String c() {
        return this.g;
    }

    public LinkProperties d(String str) {
        this.g = str;
        return this;
    }

    public String d() {
        return this.h;
    }

    public int describeContents() {
        return 0;
    }

    public LinkProperties e(String str) {
        this.h = str;
        return this;
    }

    public boolean e() {
        return this.i;
    }

    public LinkProperties f(String str) {
        this.j = str;
        return this;
    }

    public String toString() {
        return "LinkProperties{tags=" + this.a + ", feature='" + this.b + '\'' + ", alias='" + this.c + '\'' + ", stage='" + this.d + '\'' + ", matchDuration=" + this.e + ", controlParams=" + this.f + ", channel='" + this.g + '\'' + ", link='" + this.h + '\'' + ", new_user='" + this.i + '\'' + ", h5_url='" + this.j + '\'' + '}';
    }

    public void writeToParcel(Parcel parcel, int i) {
        int i2 = 0;
        parcel.writeString(this.b);
        parcel.writeString(this.c);
        parcel.writeString(this.d);
        parcel.writeString(this.g);
        parcel.writeInt(this.e);
        parcel.writeString(this.h);
        parcel.writeByte((byte) (this.i ? 1 : 0));
        parcel.writeString(this.j);
        parcel.writeSerializable(this.a);
        parcel.writeInt(this.f.size());
        while (i2 < this.f.size()) {
            parcel.writeString((String) this.f.keyAt(i2));
            parcel.writeString((String) this.f.valueAt(i2));
            i2++;
        }
    }
}
