package cn.xiaochuankeji.tieba.push.data;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import android.support.annotation.Keep;
import com.alibaba.fastjson.annotation.JSONField;

@Keep
public class ChatUser implements Parcelable {
    public static final Creator<ChatUser> CREATOR = new Creator<ChatUser>() {
        public /* synthetic */ Object createFromParcel(Parcel parcel) {
            return a(parcel);
        }

        public /* synthetic */ Object[] newArray(int i) {
            return a(i);
        }

        public ChatUser a(Parcel parcel) {
            return new ChatUser(parcel);
        }

        public ChatUser[] a(int i) {
            return new ChatUser[i];
        }
    };
    @JSONField(name = "avatar")
    public long avatar;
    @JSONField(name = "gender")
    public int gender;
    @JSONField(name = "uid")
    public long id;
    @JSONField(name = "name")
    public String name;
    @JSONField(name = "official")
    public int official;

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeLong(this.id);
        parcel.writeLong(this.avatar);
        parcel.writeInt(this.gender);
        parcel.writeString(this.name);
        parcel.writeInt(this.official);
    }

    protected ChatUser(Parcel parcel) {
        this.id = parcel.readLong();
        this.avatar = parcel.readLong();
        this.gender = parcel.readInt();
        this.name = parcel.readString();
        this.official = parcel.readInt();
    }

    public String toString() {
        return "ChatUser{id=" + this.id + ", avatar=" + this.avatar + ", gender=" + this.gender + ", name='" + this.name + '\'' + ", official=" + this.official + '}';
    }
}
