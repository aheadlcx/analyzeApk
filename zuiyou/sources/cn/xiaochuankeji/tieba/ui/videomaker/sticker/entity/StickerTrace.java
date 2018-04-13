package cn.xiaochuankeji.tieba.ui.videomaker.sticker.entity;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.annotation.JSONField;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONException;

public class StickerTrace implements Parcelable {
    public static final Creator<StickerTrace> CREATOR = new Creator<StickerTrace>() {
        public /* synthetic */ Object createFromParcel(Parcel parcel) {
            return a(parcel);
        }

        public /* synthetic */ Object[] newArray(int i) {
            return a(i);
        }

        public StickerTrace a(Parcel parcel) {
            return new StickerTrace(parcel);
        }

        public StickerTrace[] a(int i) {
            return new StickerTrace[i];
        }
    };
    @JSONField(name = "cId")
    public long cId;
    @JSONField(name = "id")
    public long id;
    public List<StickerTrace> m;

    public StickerTrace(long j, long j2) {
        this.id = j;
        this.cId = j2;
    }

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeLong(this.id);
        parcel.writeLong(this.cId);
        parcel.writeTypedList(this.m);
    }

    protected StickerTrace(Parcel parcel) {
        this.id = parcel.readLong();
        this.cId = parcel.readLong();
        this.m = parcel.createTypedArrayList(CREATOR);
    }

    public static JSONArray toJSONArray(List<StickerTrace> list) {
        if (list != null) {
            try {
                return new JSONArray(JSON.toJSONString(list));
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    public static List<StickerTrace> parseJSONArray(JSONArray jSONArray) {
        if (jSONArray != null) {
            return JSON.parseArray(jSONArray.toString(), StickerTrace.class);
        }
        return null;
    }
}
