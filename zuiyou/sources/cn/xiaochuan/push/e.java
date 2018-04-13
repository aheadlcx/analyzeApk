package cn.xiaochuan.push;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import android.text.TextUtils;
import cn.xiaochuan.push.b.a;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

public class e implements Parcelable {
    public static final Creator<e> CREATOR = new Creator<e>() {
        public /* synthetic */ Object createFromParcel(Parcel parcel) {
            return a(parcel);
        }

        public /* synthetic */ Object[] newArray(int i) {
            return a(i);
        }

        public e a(Parcel parcel) {
            return new e(parcel);
        }

        public e[] a(int i) {
            return new e[i];
        }
    };
    public String a;
    public String b;
    public String c;
    public String d;
    public boolean e = false;
    public int f = -1;
    public int g;
    public long h;
    public long i;
    public long j;
    public JSONObject k;
    public String l;
    public int m = 0;
    public int n = 0;

    public static e a(JSONObject jSONObject) {
        e eVar = new e();
        eVar.a = jSONObject.getString("id");
        eVar.b = jSONObject.getString("msg_id");
        eVar.f = jSONObject.getIntValue("type");
        eVar.g = jSONObject.getIntValue("notify_id");
        a aVar = new a(jSONObject.getJSONObject("aps"));
        eVar.c = aVar.b;
        eVar.d = aVar.a;
        eVar.k = jSONObject;
        return eVar;
    }

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(this.a);
        parcel.writeString(this.b);
        parcel.writeString(this.c);
        parcel.writeString(this.d);
        parcel.writeByte(this.e ? (byte) 1 : (byte) 0);
        parcel.writeInt(this.f);
        parcel.writeInt(this.g);
        parcel.writeString(this.k == null ? null : this.k.toString());
        parcel.writeLong(this.h);
        parcel.writeLong(this.i);
        parcel.writeLong(this.j);
        parcel.writeString(this.l);
        parcel.writeInt(this.m);
        parcel.writeInt(this.n);
    }

    protected e(Parcel parcel) {
        boolean z = false;
        this.a = parcel.readString();
        this.b = parcel.readString();
        this.c = parcel.readString();
        this.d = parcel.readString();
        if (parcel.readByte() != (byte) 0) {
            z = true;
        }
        this.e = z;
        this.f = parcel.readInt();
        this.g = parcel.readInt();
        Object readString = parcel.readString();
        this.k = TextUtils.isEmpty(readString) ? null : JSON.parseObject(readString);
        this.h = parcel.readLong();
        this.i = parcel.readLong();
        this.j = parcel.readLong();
        this.l = parcel.readString();
        this.m = parcel.readInt();
        this.n = parcel.readInt();
    }

    public String toString() {
        return "PushMessage{id='" + this.a + '\'' + ", msgId='" + this.b + ", notifyId=" + this.g + ", background=" + this.m + ", channel=" + this.l + '\'' + ", title='" + this.c + '\'' + ", description='" + this.d + '\'' + ", isNotified=" + this.e + ", type=" + this.f + ", content=" + this.k + ", createTime=" + this.h + ", clickTime=" + this.i + '}';
    }
}
