package cn.xiaochuankeji.tieba.ui.videomaker;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import com.iflytek.aiui.AIUIConstant;
import com.iflytek.cloud.SpeechConstant;
import org.json.JSONException;
import org.json.JSONObject;

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
    public final String a;
    public int b;

    public e(String str) {
        this(str, 100);
    }

    public e(String str, int i) {
        this.a = str;
        this.b = i;
    }

    public e(JSONObject jSONObject) throws JSONException {
        this.a = jSONObject.getString(AIUIConstant.RES_TYPE_PATH);
        this.b = jSONObject.getInt(SpeechConstant.VOLUME);
    }

    protected e(Parcel parcel) {
        this.a = parcel.readString();
        this.b = parcel.readInt();
    }

    public JSONObject a() throws JSONException {
        JSONObject jSONObject = new JSONObject();
        jSONObject.put(AIUIConstant.RES_TYPE_PATH, this.a);
        jSONObject.put(SpeechConstant.VOLUME, this.b);
        return jSONObject;
    }

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(this.a);
        parcel.writeInt(this.b);
    }
}
