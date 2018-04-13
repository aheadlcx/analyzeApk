package cn.xiaochuankeji.tieba.ui.videomaker;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import cn.xiaochuankeji.tieba.ui.videomaker.sticker.entity.StickerTrace;
import java.util.List;
import org.json.JSONException;
import org.json.JSONObject;

public class k implements Parcelable {
    public static final Creator<k> CREATOR = new Creator<k>() {
        public /* synthetic */ Object createFromParcel(Parcel parcel) {
            return a(parcel);
        }

        public /* synthetic */ Object[] newArray(int i) {
            return a(i);
        }

        public k a(Parcel parcel) {
            return new k(parcel);
        }

        public k[] a(int i) {
            return new k[i];
        }
    };
    public final String a;
    public final String b;
    public List<StickerTrace> c;
    public final int d;

    public k(String str, String str2, int i) {
        this.a = str;
        this.b = str2;
        this.d = i;
    }

    public k(JSONObject jSONObject) throws JSONException {
        this.a = jSONObject.getString("video_path");
        this.b = jSONObject.optString("audio_path");
        this.d = jSONObject.getInt("duration");
        this.c = StickerTrace.parseJSONArray(jSONObject.optJSONArray("sticker_trace"));
    }

    protected k(Parcel parcel) {
        this.a = parcel.readString();
        this.b = parcel.readString();
        this.d = parcel.readInt();
        this.c = parcel.createTypedArrayList(StickerTrace.CREATOR);
    }

    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(this.a);
        parcel.writeString(this.b);
        parcel.writeInt(this.d);
        parcel.writeTypedList(this.c);
    }

    public int describeContents() {
        return 0;
    }

    public JSONObject a() throws JSONException {
        JSONObject jSONObject = new JSONObject();
        jSONObject.put("video_path", this.a);
        jSONObject.put("audio_path", this.b);
        jSONObject.put("duration", this.d);
        jSONObject.put("sticker_trace", StickerTrace.toJSONArray(this.c));
        return jSONObject;
    }
}
