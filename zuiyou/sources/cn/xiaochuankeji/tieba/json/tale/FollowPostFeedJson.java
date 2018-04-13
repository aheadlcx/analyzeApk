package cn.xiaochuankeji.tieba.json.tale;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import com.alibaba.fastjson.annotation.JSONField;
import java.util.List;

public class FollowPostFeedJson implements Parcelable {
    public static final Creator<FollowPostFeedJson> CREATOR = new Creator<FollowPostFeedJson>() {
        public FollowPostFeedJson createFromParcel(Parcel parcel) {
            return new FollowPostFeedJson(parcel);
        }

        public FollowPostFeedJson[] newArray(int i) {
            return new FollowPostFeedJson[i];
        }
    };
    @JSONField(name = "cursor")
    public String cursor;
    @JSONField(name = "themes")
    public List<FollowPostThemeJson> list;
    @JSONField(name = "more")
    public boolean more;

    protected FollowPostFeedJson(Parcel parcel) {
        this.list = parcel.createTypedArrayList(FollowPostThemeJson.CREATOR);
        this.cursor = parcel.readString();
        this.more = parcel.readByte() != (byte) 0;
    }

    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeTypedList(this.list);
        parcel.writeString(this.cursor);
        parcel.writeByte((byte) (this.more ? 1 : 0));
    }

    public int describeContents() {
        return 0;
    }
}
