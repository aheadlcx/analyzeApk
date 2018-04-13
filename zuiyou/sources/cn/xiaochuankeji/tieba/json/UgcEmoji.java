package cn.xiaochuankeji.tieba.json;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import android.text.TextUtils;
import cn.xiaochuankeji.tieba.json.imgjson.ServerImgJson;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.annotation.JSONField;

public class UgcEmoji implements Parcelable {
    public static final Creator<UgcEmoji> CREATOR = new Creator<UgcEmoji>() {
        public UgcEmoji createFromParcel(Parcel parcel) {
            return new UgcEmoji(parcel);
        }

        public UgcEmoji[] newArray(int i) {
            return new UgcEmoji[i];
        }
    };
    @JSONField(name = "cid")
    public long cid;
    @JSONField(name = "id")
    public long id;
    @JSONField(name = "img")
    public ServerImgJson img;
    @JSONField(name = "img4preview")
    public ServerImgJson img4preview;
    @JSONField(name = "img4small")
    public ServerImgJson img4small;
    @JSONField(name = "magic_url")
    public String magicUrl;
    @JSONField(name = "magic_hint")
    public String magic_hint;
    @JSONField(name = "percent")
    public float percent;
    @JSONField(name = "pos")
    public int pos;
    @JSONField(name = "score")
    public float score;
    @JSONField(serialize = false)
    public int status = -1;
    @JSONField(name = "use_num")
    public int use_num;

    public String toJSON() {
        return JSONObject.toJSONString(this);
    }

    public static UgcEmoji fromJSONString(String str) {
        if (TextUtils.isEmpty(str)) {
            return null;
        }
        return (UgcEmoji) JSONObject.parseObject(str, UgcEmoji.class);
    }

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeLong(this.id);
        parcel.writeLong(this.cid);
        parcel.writeSerializable(this.img);
        parcel.writeSerializable(this.img4small);
        parcel.writeSerializable(this.img4preview);
        parcel.writeFloat(this.percent);
        parcel.writeInt(this.use_num);
        parcel.writeFloat(this.score);
        parcel.writeInt(this.pos);
        parcel.writeInt(this.status);
        parcel.writeString(this.magicUrl);
        parcel.writeString(this.magic_hint);
    }

    protected UgcEmoji(Parcel parcel) {
        this.id = parcel.readLong();
        this.cid = parcel.readLong();
        this.img = (ServerImgJson) parcel.readSerializable();
        this.img4small = (ServerImgJson) parcel.readSerializable();
        this.img4preview = (ServerImgJson) parcel.readSerializable();
        this.percent = parcel.readFloat();
        this.use_num = parcel.readInt();
        this.score = parcel.readFloat();
        this.pos = parcel.readInt();
        this.status = parcel.readInt();
        this.magicUrl = parcel.readString();
        this.magic_hint = parcel.readString();
    }
}
