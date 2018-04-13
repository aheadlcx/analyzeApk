package cn.xiaochuankeji.tieba.json;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import cn.xiaochuankeji.tieba.json.imgjson.ServerImgJson;
import com.alibaba.fastjson.annotation.JSONField;
import java.io.Serializable;
import java.util.ArrayList;

public class UgcVideoMusicJson implements Parcelable, Serializable {
    public static final Creator<UgcVideoMusicJson> CREATOR = new Creator<UgcVideoMusicJson>() {
        public UgcVideoMusicJson createFromParcel(Parcel parcel) {
            return new UgcVideoMusicJson(parcel);
        }

        public UgcVideoMusicJson[] newArray(int i) {
            return new UgcVideoMusicJson[i];
        }
    };
    @JSONField(name = "cid")
    public long cid;
    @JSONField(name = "dur")
    public int dur;
    @JSONField(name = "favor")
    public int favor;
    @JSONField(name = "id")
    public long id;
    @JSONField(name = "img")
    public ServerImgJson img;
    @JSONField(name = "title")
    public String musicName;
    @JSONField(name = "singers")
    public ArrayList<String> singers;
    @JSONField(name = "mid")
    public long uploaderMid;
    @JSONField(name = "url")
    public String url;

    protected UgcVideoMusicJson() {
    }

    protected UgcVideoMusicJson(Parcel parcel) {
        this.id = parcel.readLong();
        this.cid = parcel.readLong();
        this.url = parcel.readString();
        this.musicName = parcel.readString();
        this.singers = parcel.createStringArrayList();
        this.uploaderMid = parcel.readLong();
        this.dur = parcel.readInt();
    }

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeLong(this.id);
        parcel.writeLong(this.cid);
        parcel.writeString(this.url);
        parcel.writeString(this.musicName);
        parcel.writeStringList(this.singers);
        parcel.writeLong(this.uploaderMid);
        parcel.writeInt(this.dur);
    }
}
