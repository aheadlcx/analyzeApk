package cn.xiaochuankeji.tieba.push.data;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import android.support.annotation.Keep;
import com.alibaba.fastjson.annotation.JSONField;

@Keep
public class XMessage implements Parcelable {
    public static final Creator<XMessage> CREATOR = new Creator<XMessage>() {
        public /* synthetic */ Object createFromParcel(Parcel parcel) {
            return a(parcel);
        }

        public /* synthetic */ Object[] newArray(int i) {
            return a(i);
        }

        public XMessage a(Parcel parcel) {
            return new XMessage(parcel);
        }

        public XMessage[] a(int i) {
            return new XMessage[i];
        }
    };
    @JSONField(name = "content")
    public String content;
    @JSONField(name = "msgid")
    public long msg_id;
    @JSONField(name = "mtype")
    public int msg_type;
    @JSONField(name = "fromuser")
    public long msg_uid;
    @JSONField(name = "time")
    public long time;
    @JSONField(name = "unsup")
    public String unsup;

    public static boolean isSupport(int i) {
        return i == 1 || i == 2 || i == 3 || i == 5 || i == 6 || i == 7 || i == 8 || i == 9 || i == 10 || i == 11 || i == 12 || i == 13;
    }

    public void update(a aVar) {
        this.msg_id = aVar.j;
        this.msg_type = aVar.g;
        this.content = aVar.f;
        this.time = aVar.k;
        this.unsup = aVar.l;
        this.msg_uid = aVar.a;
    }

    public String toString() {
        return "XMessage{msg_id=" + this.msg_id + ", msg_type=" + this.msg_type + ", content='" + this.content + '\'' + ", time=" + this.time + ", unsup='" + this.unsup + '\'' + ", msg_uid=" + this.msg_uid + '}';
    }

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeLong(this.msg_id);
        parcel.writeInt(this.msg_type);
        parcel.writeString(this.content);
        parcel.writeLong(this.time);
        parcel.writeString(this.unsup);
        parcel.writeLong(this.msg_uid);
    }

    protected XMessage(Parcel parcel) {
        this.msg_id = parcel.readLong();
        this.msg_type = parcel.readInt();
        this.content = parcel.readString();
        this.time = parcel.readLong();
        this.unsup = parcel.readString();
        this.msg_uid = parcel.readLong();
    }
}
