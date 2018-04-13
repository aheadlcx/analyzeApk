package cn.xiaochuankeji.tieba.push.data;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import android.support.annotation.Keep;

@Keep
public class XSession implements Parcelable {
    public static final Creator<XSession> CREATOR = new Creator<XSession>() {
        public /* synthetic */ Object createFromParcel(Parcel parcel) {
            return a(parcel);
        }

        public /* synthetic */ Object[] newArray(int i) {
            return a(i);
        }

        public XSession a(Parcel parcel) {
            return new XSession(parcel);
        }

        public XSession[] a(int i) {
            return new XSession[i];
        }
    };
    public long session_id;
    public long session_local_id = 0;
    public int session_type;
    public int status = -1;
    public long time;
    public int unread;
    public int weight;
    public long x_last_msg_id;
    public ChatUser x_mask;
    public XMessage x_msg;
    public ChatUser x_other;
    public ChatRoom x_room;
    public long x_room_id;
    public long x_sid;
    public long x_sync;

    public static boolean isSupport(int i) {
        return i == 1 || i == 2 || i == 4;
    }

    public boolean isAnonymous() {
        return this.session_type == 2;
    }

    public boolean isOfficial() {
        return this.x_mask != null && this.x_mask.official == 1;
    }

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeLong(this.session_id);
        parcel.writeInt(this.session_type);
        parcel.writeLong(this.session_local_id);
        parcel.writeLong(this.x_sync);
        parcel.writeLong(this.x_last_msg_id);
        parcel.writeLong(this.x_room_id);
        parcel.writeParcelable(this.x_room, i);
        parcel.writeParcelable(this.x_mask, i);
        parcel.writeParcelable(this.x_other, i);
        parcel.writeLong(this.time);
        parcel.writeInt(this.unread);
        parcel.writeInt(this.status);
        parcel.writeInt(this.weight);
        parcel.writeLong(this.x_sid);
        parcel.writeParcelable(this.x_msg, i);
    }

    protected XSession(Parcel parcel) {
        this.session_id = parcel.readLong();
        this.session_type = parcel.readInt();
        this.session_local_id = parcel.readLong();
        this.x_sync = parcel.readLong();
        this.x_last_msg_id = parcel.readLong();
        this.x_room_id = parcel.readLong();
        this.x_room = (ChatRoom) parcel.readParcelable(ChatRoom.class.getClassLoader());
        this.x_mask = (ChatUser) parcel.readParcelable(ChatUser.class.getClassLoader());
        this.x_other = (ChatUser) parcel.readParcelable(ChatUser.class.getClassLoader());
        this.time = parcel.readLong();
        this.unread = parcel.readInt();
        this.status = parcel.readInt();
        this.weight = parcel.readInt();
        this.x_sid = parcel.readLong();
        this.x_msg = (XMessage) parcel.readParcelable(XMessage.class.getClassLoader());
    }

    public boolean isSelfMsg() {
        return (this.x_msg == null || this.x_mask == null || this.x_msg.msg_uid != this.x_mask.id) ? false : true;
    }
}
