package cn.xiaochuankeji.tieba.ui.hollow.data;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import com.alibaba.fastjson.annotation.JSONField;

public class MemberDataBean implements Parcelable {
    public static final Creator<MemberDataBean> CREATOR = new Creator<MemberDataBean>() {
        public /* synthetic */ Object createFromParcel(Parcel parcel) {
            return a(parcel);
        }

        public /* synthetic */ Object[] newArray(int i) {
            return a(i);
        }

        public MemberDataBean a(Parcel parcel) {
            return new MemberDataBean(parcel);
        }

        public MemberDataBean[] a(int i) {
            return new MemberDataBean[i];
        }
    };
    @JSONField(name = "avatar")
    public long avatar;
    @JSONField(name = "gender")
    public int gender;
    @JSONField(name = "name")
    public String name;
    @JSONField(name = "xid")
    public long xid;

    protected MemberDataBean(Parcel parcel) {
        this.xid = parcel.readLong();
        this.name = parcel.readString();
        this.avatar = parcel.readLong();
        this.gender = parcel.readInt();
    }

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeLong(this.xid);
        parcel.writeString(this.name);
        parcel.writeLong(this.avatar);
        parcel.writeInt(this.gender);
    }

    public String toString() {
        return "MemberDataBean{xid=" + this.xid + ", name='" + this.name + '\'' + ", avatar=" + this.avatar + ", gender=" + this.gender + '}';
    }
}
