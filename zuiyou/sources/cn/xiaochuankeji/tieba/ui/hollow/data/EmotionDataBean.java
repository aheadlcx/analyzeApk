package cn.xiaochuankeji.tieba.ui.hollow.data;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import com.alibaba.fastjson.annotation.JSONField;

public class EmotionDataBean implements Parcelable {
    public static final Creator<EmotionDataBean> CREATOR = new Creator<EmotionDataBean>() {
        public /* synthetic */ Object createFromParcel(Parcel parcel) {
            return a(parcel);
        }

        public /* synthetic */ Object[] newArray(int i) {
            return a(i);
        }

        public EmotionDataBean a(Parcel parcel) {
            return new EmotionDataBean(parcel);
        }

        public EmotionDataBean[] a(int i) {
            return new EmotionDataBean[i];
        }
    };
    @JSONField(name = "h")
    public long height;
    @JSONField(name = "id")
    public long id;
    @JSONField(name = "image_id")
    public long imageId;
    @JSONField(name = "w")
    public long width;

    protected EmotionDataBean(Parcel parcel) {
        this.id = parcel.readLong();
        this.width = parcel.readLong();
        this.height = parcel.readLong();
        this.imageId = parcel.readLong();
    }

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeLong(this.id);
        parcel.writeLong(this.width);
        parcel.writeLong(this.height);
        parcel.writeLong(this.imageId);
    }
}
