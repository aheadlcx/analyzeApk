package cn.xiaochuankeji.tieba.background.data.tag;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import com.alibaba.fastjson.annotation.JSONField;
import com.iflytek.cloud.SpeechConstant;
import java.io.Serializable;

public class NavigatorTag implements Parcelable, Serializable {
    public static final Creator<NavigatorTag> CREATOR = new Creator<NavigatorTag>() {
        public /* synthetic */ Object createFromParcel(Parcel parcel) {
            return a(parcel);
        }

        public /* synthetic */ Object[] newArray(int i) {
            return a(i);
        }

        public NavigatorTag a(Parcel parcel) {
            return new NavigatorTag(parcel);
        }

        public NavigatorTag[] a(int i) {
            return new NavigatorTag[i];
        }
    };
    @JSONField(name = "action_info")
    public ActionInfo action_info = new ActionInfo();
    public int crumb = -1;
    @JSONField(name = "ename")
    public String ename;
    @JSONField(name = "focus_weight")
    public int focus_weight;
    @JSONField(name = "frozen")
    public int frozen;
    @JSONField(name = "id")
    public long id;
    @JSONField(name = "is_new")
    public boolean isNew;
    @JSONField(name = "name")
    public String name;
    @JSONField(name = "type")
    public String type;

    public static class ActionInfo implements Parcelable, Serializable {
        public static final Creator<ActionInfo> CREATOR = new Creator<ActionInfo>() {
            public /* synthetic */ Object createFromParcel(Parcel parcel) {
                return a(parcel);
            }

            public /* synthetic */ Object[] newArray(int i) {
                return a(i);
            }

            public ActionInfo a(Parcel parcel) {
                return new ActionInfo(parcel);
            }

            public ActionInfo[] a(int i) {
                return new ActionInfo[i];
            }
        };
        @JSONField(name = "filter")
        public String filter = SpeechConstant.PLUS_LOCAL_ALL;

        public int describeContents() {
            return 0;
        }

        public void writeToParcel(Parcel parcel, int i) {
            parcel.writeString(this.filter);
        }

        protected ActionInfo(Parcel parcel) {
            this.filter = parcel.readString();
        }
    }

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeLong(this.id);
        parcel.writeString(this.name);
        parcel.writeString(this.type);
        parcel.writeString(this.ename);
        parcel.writeParcelable(this.action_info, i);
        parcel.writeInt(this.frozen);
        parcel.writeByte(this.isNew ? (byte) 1 : (byte) 0);
        parcel.writeInt(this.focus_weight);
        parcel.writeInt(this.crumb);
    }

    protected NavigatorTag(Parcel parcel) {
        this.id = parcel.readLong();
        this.name = parcel.readString();
        this.type = parcel.readString();
        this.ename = parcel.readString();
        this.action_info = (ActionInfo) parcel.readParcelable(ActionInfo.class.getClassLoader());
        this.frozen = parcel.readInt();
        this.isNew = parcel.readByte() != (byte) 0;
        this.focus_weight = parcel.readInt();
        this.crumb = parcel.readInt();
    }

    public String toString() {
        return "NavigatorTag{id=" + this.id + ", name='" + this.name + '\'' + ", type='" + this.type + '\'' + ", ename='" + this.ename + '\'' + ", action_info=" + this.action_info + ", frozen=" + this.frozen + ", isNew=" + this.isNew + ", focus_weight=" + this.focus_weight + ", crumb=" + this.crumb + '}';
    }
}
