package cn.xiaochuan.jsbridge.data;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import com.alibaba.fastjson.annotation.JSONField;

public class JSShare implements Parcelable {
    public static final Creator<JSShare> CREATOR = new Creator<JSShare>() {
        public /* synthetic */ Object createFromParcel(Parcel parcel) {
            return a(parcel);
        }

        public /* synthetic */ Object[] newArray(int i) {
            return a(i);
        }

        public JSShare a(Parcel parcel) {
            return new JSShare(parcel);
        }

        public JSShare[] a(int i) {
            return new JSShare[i];
        }
    };
    public static final String a = "share";
    @JSONField(name = "dataUrl")
    public String dataUrl;
    @JSONField(name = "desc")
    public String desc;
    @JSONField(name = "img_url")
    public String img_url;
    @JSONField(name = "link")
    public String link;
    @JSONField(name = "platform")
    public String platform;
    @JSONField(name = "title")
    public String title;
    @JSONField(name = "type")
    public String type;

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(this.platform);
        parcel.writeString(this.title);
        parcel.writeString(this.desc);
        parcel.writeString(this.img_url);
        parcel.writeString(this.link);
        parcel.writeString(this.type);
        parcel.writeString(this.dataUrl);
    }

    protected JSShare(Parcel parcel) {
        this.platform = parcel.readString();
        this.title = parcel.readString();
        this.desc = parcel.readString();
        this.img_url = parcel.readString();
        this.link = parcel.readString();
        this.type = parcel.readString();
        this.dataUrl = parcel.readString();
    }
}
