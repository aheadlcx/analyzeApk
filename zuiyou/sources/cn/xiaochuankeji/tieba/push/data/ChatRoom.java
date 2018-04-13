package cn.xiaochuankeji.tieba.push.data;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import android.support.annotation.Keep;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.annotation.JSONField;

@Keep
public class ChatRoom implements Parcelable {
    public static final Creator<ChatRoom> CREATOR = new Creator<ChatRoom>() {
        public /* synthetic */ Object createFromParcel(Parcel parcel) {
            return a(parcel);
        }

        public /* synthetic */ Object[] newArray(int i) {
            return a(i);
        }

        public ChatRoom a(Parcel parcel) {
            return new ChatRoom(parcel);
        }

        public ChatRoom[] a(int i) {
            return new ChatRoom[i];
        }
    };
    @JSONField(name = "room_data")
    public JSONObject room_data;
    @JSONField(name = "room_id")
    public long room_id;
    @JSONField(name = "room_mask")
    public ChatUser room_mask;
    @JSONField(name = "room_name")
    public String room_name;
    @JSONField(name = "room_type")
    public int room_type;

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeLong(this.room_id);
        parcel.writeInt(this.room_type);
        parcel.writeString(this.room_name);
        parcel.writeParcelable(this.room_mask, i);
        parcel.writeSerializable(this.room_data);
    }

    protected ChatRoom(Parcel parcel) {
        this.room_id = parcel.readLong();
        this.room_type = parcel.readInt();
        this.room_name = parcel.readString();
        this.room_mask = (ChatUser) parcel.readParcelable(ChatUser.class.getClassLoader());
        this.room_data = (JSONObject) parcel.readSerializable();
    }
}
