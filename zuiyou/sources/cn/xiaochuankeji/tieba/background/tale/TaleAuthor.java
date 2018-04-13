package cn.xiaochuankeji.tieba.background.tale;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import com.alibaba.fastjson.annotation.JSONField;

public class TaleAuthor implements Parcelable {
    public static final Creator<TaleAuthor> CREATOR = new Creator<TaleAuthor>() {
        public TaleAuthor createFromParcel(Parcel parcel) {
            return new TaleAuthor(parcel);
        }

        public TaleAuthor[] newArray(int i) {
            return new TaleAuthor[i];
        }
    };
    @JSONField(name = "avatar")
    public long avatar;
    @JSONField(name = "gender")
    public int gender;
    @JSONField(name = "id")
    public long id;
    @JSONField(name = "name")
    public String name;
    @JSONField(name = "sign")
    public String sign;

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeLong(this.id);
        parcel.writeString(this.name);
        parcel.writeInt(this.gender);
        parcel.writeLong(this.avatar);
        parcel.writeString(this.sign);
    }

    protected TaleAuthor(Parcel parcel) {
        this.id = parcel.readLong();
        this.name = parcel.readString();
        this.gender = parcel.readInt();
        this.avatar = parcel.readLong();
        this.sign = parcel.readString();
    }
}
