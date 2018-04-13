package com.microquation.linkedme.android.indexing;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import android.support.v4.util.ArrayMap;
import com.microquation.linkedme.android.a;
import com.microquation.linkedme.android.util.c;
import java.util.ArrayList;
import java.util.Iterator;
import org.json.JSONArray;
import org.json.JSONObject;

public class LMUniversalObject implements Parcelable {
    public static final Creator CREATOR = new Creator() {
        public LMUniversalObject a(Parcel parcel) {
            return new LMUniversalObject(parcel);
        }

        public LMUniversalObject[] a(int i) {
            return new LMUniversalObject[i];
        }

        public /* synthetic */ Object createFromParcel(Parcel parcel) {
            return a(parcel);
        }

        public /* synthetic */ Object[] newArray(int i) {
            return a(i);
        }
    };
    private String a;
    private String b;
    private String c;
    private String d;
    private String e;
    private final ArrayMap<String, String> f;
    private String g;
    private CONTENT_INDEX_MODE h;
    private final ArrayList<String> i;
    private long j;

    public enum CONTENT_INDEX_MODE {
        PUBLIC,
        PRIVATE
    }

    public LMUniversalObject() {
        this.f = new ArrayMap();
        this.i = new ArrayList();
        this.a = "";
        this.b = "";
        this.c = "";
        this.d = "";
        this.g = "";
        this.h = CONTENT_INDEX_MODE.PUBLIC;
        this.j = 0;
    }

    private LMUniversalObject(Parcel parcel) {
        this();
        this.a = parcel.readString();
        this.b = parcel.readString();
        this.c = parcel.readString();
        this.d = parcel.readString();
        this.e = parcel.readString();
        this.g = parcel.readString();
        this.j = parcel.readLong();
        this.h = CONTENT_INDEX_MODE.values()[parcel.readInt()];
        this.i.addAll((ArrayList) parcel.readSerializable());
        int readInt = parcel.readInt();
        for (int i = 0; i < readInt; i++) {
            this.f.put(parcel.readString(), parcel.readString());
        }
    }

    public static LMUniversalObject a() {
        a a = a.a();
        if (a == null) {
            return null;
        }
        try {
            return a.d() != null ? a.d().optBoolean(c.a.Clicked_LINKEDME_Link.a(), false) ? a(a.d()) : (a.e() == null || a.e().length() <= 0) ? null : a(a.d()) : null;
        } catch (Exception e) {
            return null;
        }
    }

    public static LMUniversalObject a(JSONObject jSONObject) {
        JSONObject optJSONObject = jSONObject.optJSONObject(c.a.Params.a());
        try {
            LMUniversalObject lMUniversalObject = new LMUniversalObject();
            try {
                lMUniversalObject.c = optJSONObject.optString(c.a.ContentTitle.a());
                lMUniversalObject.a = optJSONObject.optString(c.a.CanonicalIdentifier.a());
                lMUniversalObject.b = optJSONObject.optString(c.a.CanonicalUrl.a());
                JSONArray optJSONArray = optJSONObject.optJSONArray(c.a.ContentKeyWords.a());
                if (optJSONArray != null) {
                    int length = optJSONArray.length();
                    for (int i = 0; i < length; i++) {
                        lMUniversalObject.a(optJSONArray.optString(i));
                    }
                }
                lMUniversalObject.d = optJSONObject.optString(c.a.ContentDesc.a());
                lMUniversalObject.e = optJSONObject.optString(c.a.ContentImgUrl.a());
                lMUniversalObject.g = optJSONObject.optString(c.a.ContentType.a());
                lMUniversalObject.j = optJSONObject.optLong(c.a.ContentExpiryTime.a());
                optJSONObject = optJSONObject.optJSONObject(c.a.LKME_METADATA.a());
                Iterator keys = optJSONObject.keys();
                while (keys.hasNext()) {
                    String str = (String) keys.next();
                    lMUniversalObject.a(str, optJSONObject.optString(str));
                }
                return lMUniversalObject;
            } catch (Exception e) {
                return lMUniversalObject;
            }
        } catch (Exception e2) {
            return null;
        }
    }

    public LMUniversalObject a(String str) {
        this.i.add(str);
        return this;
    }

    public LMUniversalObject a(String str, String str2) {
        this.f.put(str, str2);
        return this;
    }

    public int describeContents() {
        return 0;
    }

    public String toString() {
        return "LMUniversalObject{canonicalIdentifier='" + this.a + '\'' + ", canonicalUrl='" + this.b + '\'' + ", title='" + this.c + '\'' + ", description='" + this.d + '\'' + ", imageUrl='" + this.e + '\'' + ", metadata=" + this.f + ", type='" + this.g + '\'' + ", indexMode=" + this.h + ", keywords=" + this.i + ", expirationInMilliSec=" + this.j + '}';
    }

    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(this.a);
        parcel.writeString(this.b);
        parcel.writeString(this.c);
        parcel.writeString(this.d);
        parcel.writeString(this.e);
        parcel.writeString(this.g);
        parcel.writeLong(this.j);
        parcel.writeInt(this.h.ordinal());
        parcel.writeSerializable(this.i);
        int size = this.f.size();
        parcel.writeInt(size);
        for (int i2 = 0; i2 < size; i2++) {
            parcel.writeString((String) this.f.keyAt(i2));
            parcel.writeString((String) this.f.valueAt(i2));
        }
    }
}
